// 
// Decompiled by Procyon v0.5.36
// 

package org.jdesktop.swingworker;

import java.awt.event.ActionEvent;
import javax.swing.Timer;
import java.util.Iterator;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.LinkedBlockingQueue;
import javax.swing.SwingUtilities;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutionException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Future;

public abstract class SwingWorker<T, V> implements Future<T>, Runnable
{
    private static final int MAX_WORKER_THREADS = 10;
    private volatile int progress;
    private volatile StateValue state;
    private final FutureTask<T> future;
    private final SwingPropertyChangeSupport propertyChangeSupport;
    private AccumulativeRunnable<V> doProcess;
    private AccumulativeRunnable<Integer> doNotifyProgressChange;
    private static final AccumulativeRunnable<Runnable> doSubmit;
    private static ExecutorService executorService;
    
    public SwingWorker() {
        this.future = new FutureTask<T>(new Callable<T>() {
            public T call() throws Exception {
                SwingWorker.this.setState(StateValue.STARTED);
                return SwingWorker.this.doInBackground();
            }
        }) {
            @Override
            protected void done() {
                SwingWorker.this.doneEDT();
                SwingWorker.this.setState(StateValue.DONE);
            }
        };
        this.state = StateValue.PENDING;
        this.propertyChangeSupport = new SwingPropertyChangeSupport(this, true);
        this.doProcess = null;
        this.doNotifyProgressChange = null;
    }
    
    protected abstract T doInBackground() throws Exception;
    
    public final void run() {
        this.future.run();
    }
    
    protected final void publish(final V... array) {
        synchronized (this) {
            if (this.doProcess == null) {
                this.doProcess = new AccumulativeRunnable<V>() {
                    public void run(final List<V> list) {
                        SwingWorker.this.process(list);
                    }
                    
                    @Override
                    protected void submit() {
                        SwingWorker.doSubmit.add(this);
                    }
                };
            }
        }
        this.doProcess.add(array);
    }
    
    protected void process(final List<V> list) {
    }
    
    protected void done() {
    }
    
    protected final void setProgress(final int progress) {
        if (progress < 0 || progress > 100) {
            throw new IllegalArgumentException("the value should be from 0 to 100");
        }
        if (this.progress == progress) {
            return;
        }
        final int progress2 = this.progress;
        this.progress = progress;
        if (!this.getPropertyChangeSupport().hasListeners("progress")) {
            return;
        }
        synchronized (this) {
            if (this.doNotifyProgressChange == null) {
                this.doNotifyProgressChange = new AccumulativeRunnable<Integer>() {
                    public void run(final List<Integer> list) {
                        SwingWorker.this.firePropertyChange("progress", list.get(0), list.get(list.size() - 1));
                    }
                    
                    @Override
                    protected void submit() {
                        SwingWorker.doSubmit.add(this);
                    }
                };
            }
        }
        this.doNotifyProgressChange.add(progress2, progress);
    }
    
    public final int getProgress() {
        return this.progress;
    }
    
    public final void execute() {
        getWorkersExecutorService().execute(this);
    }
    
    public final boolean cancel(final boolean b) {
        return this.future.cancel(b);
    }
    
    public final boolean isCancelled() {
        return this.future.isCancelled();
    }
    
    public final boolean isDone() {
        return this.future.isDone();
    }
    
    public final T get() throws InterruptedException, ExecutionException {
        return this.future.get();
    }
    
    public final T get(final long n, final TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.future.get(n, timeUnit);
    }
    
    public final void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.getPropertyChangeSupport().addPropertyChangeListener(propertyChangeListener);
    }
    
    public final void removePropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.getPropertyChangeSupport().removePropertyChangeListener(propertyChangeListener);
    }
    
    public final void firePropertyChange(final String s, final Object o, final Object o2) {
        this.getPropertyChangeSupport().firePropertyChange(s, o, o2);
    }
    
    public final PropertyChangeSupport getPropertyChangeSupport() {
        return this.propertyChangeSupport;
    }
    
    public final StateValue getState() {
        if (this.isDone()) {
            return StateValue.DONE;
        }
        return this.state;
    }
    
    private void setState(final StateValue state) {
        this.firePropertyChange("state", this.state, this.state = state);
    }
    
    private void doneEDT() {
        final Runnable runnable = new Runnable() {
            public void run() {
                SwingWorker.this.done();
            }
        };
        if (SwingUtilities.isEventDispatchThread()) {
            runnable.run();
        }
        else {
            SwingUtilities.invokeLater(runnable);
        }
    }
    
    private static synchronized ExecutorService getWorkersExecutorService() {
        if (SwingWorker.executorService == null) {
            SwingWorker.executorService = new ThreadPoolExecutor(0, 10, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactory() {
                final ThreadFactory defaultFactory = Executors.defaultThreadFactory();
                
                public Thread newThread(final Runnable runnable) {
                    final Thread thread = this.defaultFactory.newThread(runnable);
                    thread.setName("SwingWorker-" + thread.getName());
                    return thread;
                }
            }) {
                private final ReentrantLock pauseLock = new ReentrantLock();
                private final Condition unpaused = this.pauseLock.newCondition();
                private boolean isPaused = false;
                private final ReentrantLock executeLock = new ReentrantLock();
                
                @Override
                public void execute(final Runnable runnable) {
                    this.executeLock.lock();
                    try {
                        this.pauseLock.lock();
                        try {
                            this.isPaused = true;
                        }
                        finally {
                            this.pauseLock.unlock();
                        }
                        this.setCorePoolSize(10);
                        super.execute(runnable);
                        this.setCorePoolSize(0);
                        this.pauseLock.lock();
                        try {
                            this.isPaused = false;
                            this.unpaused.signalAll();
                        }
                        finally {
                            this.pauseLock.unlock();
                        }
                    }
                    finally {
                        this.executeLock.unlock();
                    }
                }
                
                @Override
                protected void afterExecute(final Runnable runnable, final Throwable t) {
                    super.afterExecute(runnable, t);
                    this.pauseLock.lock();
                    try {
                        while (this.isPaused) {
                            this.unpaused.await();
                        }
                    }
                    catch (InterruptedException ex) {}
                    finally {
                        this.pauseLock.unlock();
                    }
                }
            };
        }
        return SwingWorker.executorService;
    }
    
    static {
        doSubmit = new DoSubmitAccumulativeRunnable();
        SwingWorker.executorService = null;
    }
    
    private static class DoSubmitAccumulativeRunnable extends AccumulativeRunnable<Runnable> implements ActionListener
    {
        private static final int DELAY = 33;
        
        @Override
        protected void run(final List<Runnable> list) {
            final Iterator<Runnable> iterator = list.iterator();
            while (iterator.hasNext()) {
                iterator.next().run();
            }
        }
        
        @Override
        protected void submit() {
            final Timer timer = new Timer(33, this);
            timer.setRepeats(false);
            timer.start();
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            this.run();
        }
    }
    
    public enum StateValue
    {
        PENDING, 
        STARTED, 
        DONE;
    }
}
