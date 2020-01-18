// 
// Decompiled by Procyon v0.5.36
// 

package org.jdesktop.application;

import java.awt.Component;
import javax.swing.Action;
import java.beans.PropertyChangeEvent;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.CopyOnWriteArrayList;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.logging.Logger;
import org.jdesktop.swingworker.SwingWorker;

public abstract class Task<T, V> extends SwingWorker<T, V>
{
    private static final Logger logger;
    private final Application application;
    private String resourcePrefix;
    private ResourceMap resourceMap;
    private List<TaskListener<T, V>> taskListeners;
    private InputBlocker inputBlocker;
    private String name;
    private String title;
    private String description;
    private long messageTime;
    private String message;
    private long startTime;
    private long doneTime;
    private boolean userCanCancel;
    private boolean progressPropertyIsValid;
    private TaskService taskService;
    
    private void initTask(final ResourceMap resourceMap, final String resourcePrefix) {
        this.resourceMap = resourceMap;
        if (resourcePrefix == null || resourcePrefix.length() == 0) {
            this.resourcePrefix = "";
        }
        else if (resourcePrefix.endsWith(".")) {
            this.resourcePrefix = resourcePrefix;
        }
        else {
            this.resourcePrefix = resourcePrefix + ".";
        }
        if (resourceMap != null) {
            this.title = resourceMap.getString(this.resourceName("title"), new Object[0]);
            this.description = resourceMap.getString(this.resourceName("description"), new Object[0]);
            this.message = resourceMap.getString(this.resourceName("message"), new Object[0]);
            if (this.message != null) {
                this.messageTime = System.currentTimeMillis();
            }
        }
        this.addPropertyChangeListener(new StatePCL());
        this.taskListeners = new CopyOnWriteArrayList<TaskListener<T, V>>();
    }
    
    private ResourceMap defaultResourceMap(final Application application) {
        return application.getContext().getResourceMap(this.getClass(), Task.class);
    }
    
    @Deprecated
    public Task(final Application application, final ResourceMap resourceMap, final String s) {
        this.name = null;
        this.title = null;
        this.description = null;
        this.messageTime = -1L;
        this.message = null;
        this.startTime = -1L;
        this.doneTime = -1L;
        this.userCanCancel = true;
        this.progressPropertyIsValid = false;
        this.taskService = null;
        this.application = application;
        this.initTask(resourceMap, s);
    }
    
    @Deprecated
    public Task(final Application application, final String s) {
        this.name = null;
        this.title = null;
        this.description = null;
        this.messageTime = -1L;
        this.message = null;
        this.startTime = -1L;
        this.doneTime = -1L;
        this.userCanCancel = true;
        this.progressPropertyIsValid = false;
        this.taskService = null;
        this.application = application;
        this.initTask(this.defaultResourceMap(application), s);
    }
    
    public Task(final Application application) {
        this.name = null;
        this.title = null;
        this.description = null;
        this.messageTime = -1L;
        this.message = null;
        this.startTime = -1L;
        this.doneTime = -1L;
        this.userCanCancel = true;
        this.progressPropertyIsValid = false;
        this.taskService = null;
        this.application = application;
        this.initTask(this.defaultResourceMap(application), "");
    }
    
    public final Application getApplication() {
        return this.application;
    }
    
    public final ApplicationContext getContext() {
        return this.getApplication().getContext();
    }
    
    public synchronized TaskService getTaskService() {
        return this.taskService;
    }
    
    synchronized void setTaskService(final TaskService taskService) {
        final TaskService taskService2;
        final TaskService taskService3;
        synchronized (this) {
            taskService2 = this.taskService;
            this.taskService = taskService;
            taskService3 = this.taskService;
        }
        this.firePropertyChange("taskService", taskService2, taskService3);
    }
    
    protected final String resourceName(final String s) {
        return this.resourcePrefix + s;
    }
    
    public final ResourceMap getResourceMap() {
        return this.resourceMap;
    }
    
    public synchronized String getTitle() {
        return this.title;
    }
    
    protected void setTitle(final String title) {
        final String title2;
        final String title3;
        synchronized (this) {
            title2 = this.title;
            this.title = title;
            title3 = this.title;
        }
        this.firePropertyChange("title", title2, title3);
    }
    
    public synchronized String getDescription() {
        return this.description;
    }
    
    protected void setDescription(final String description) {
        final String description2;
        final String description3;
        synchronized (this) {
            description2 = this.description;
            this.description = description;
            description3 = this.description;
        }
        this.firePropertyChange("description", description2, description3);
    }
    
    public long getExecutionDuration(final TimeUnit timeUnit) {
        final long startTime;
        final long doneTime;
        synchronized (this) {
            startTime = this.startTime;
            doneTime = this.doneTime;
        }
        long n;
        if (startTime == -1L) {
            n = 0L;
        }
        else if (doneTime == -1L) {
            n = System.currentTimeMillis() - startTime;
        }
        else {
            n = doneTime - startTime;
        }
        return timeUnit.convert(Math.max(0L, n), TimeUnit.MILLISECONDS);
    }
    
    public String getMessage() {
        return this.message;
    }
    
    protected void setMessage(final String message) {
        final String message2;
        final String message3;
        synchronized (this) {
            message2 = this.message;
            this.message = message;
            message3 = this.message;
            this.messageTime = System.currentTimeMillis();
        }
        this.firePropertyChange("message", message2, message3);
    }
    
    protected final void message(final String message, final Object... array) {
        final ResourceMap resourceMap = this.getResourceMap();
        if (resourceMap != null) {
            this.setMessage(resourceMap.getString(this.resourceName(message), array));
        }
        else {
            this.setMessage(message);
        }
    }
    
    public long getMessageDuration(final TimeUnit timeUnit) {
        final long messageTime;
        synchronized (this) {
            messageTime = this.messageTime;
        }
        return timeUnit.convert((messageTime == -1L) ? 0L : Math.max(0L, System.currentTimeMillis() - messageTime), TimeUnit.MILLISECONDS);
    }
    
    public synchronized boolean getUserCanCancel() {
        return this.userCanCancel;
    }
    
    protected void setUserCanCancel(final boolean userCanCancel) {
        final boolean userCanCancel2;
        final boolean userCanCancel3;
        synchronized (this) {
            userCanCancel2 = this.userCanCancel;
            this.userCanCancel = userCanCancel;
            userCanCancel3 = this.userCanCancel;
        }
        this.firePropertyChange("userCanCancel", userCanCancel2, userCanCancel3);
    }
    
    public synchronized boolean isProgressPropertyValid() {
        return this.progressPropertyIsValid;
    }
    
    protected final void setProgress(final int n, final int n2, final int n3) {
        if (n2 >= n3) {
            throw new IllegalArgumentException("invalid range: min >= max");
        }
        if (n < n2 || n > n3) {
            throw new IllegalArgumentException("invalid value");
        }
        this.setProgress(Math.round((n - n2) / (float)(n3 - n2) * 100.0f));
    }
    
    protected final void setProgress(final float n) {
        if (n < 0.0 || n > 1.0) {
            throw new IllegalArgumentException("invalid percentage");
        }
        this.setProgress(Math.round(n * 100.0f));
    }
    
    protected final void setProgress(final float n, final float n2, final float n3) {
        if (n2 >= n3) {
            throw new IllegalArgumentException("invalid range: min >= max");
        }
        if (n < n2 || n > n3) {
            throw new IllegalArgumentException("invalid value");
        }
        this.setProgress(Math.round((n - n2) / (n3 - n2) * 100.0f));
    }
    
    public final boolean isPending() {
        return this.getState() == StateValue.PENDING;
    }
    
    public final boolean isStarted() {
        return this.getState() == StateValue.STARTED;
    }
    
    @Override
    protected void process(final List<V> list) {
        this.fireProcessListeners(list);
    }
    
    @Override
    protected final void done() {
        try {
            if (this.isCancelled()) {
                this.cancelled();
            }
            else {
                try {
                    this.succeeded(this.get());
                }
                catch (InterruptedException ex) {
                    this.interrupted(ex);
                }
                catch (ExecutionException ex2) {
                    this.failed(ex2.getCause());
                }
            }
        }
        finally {
            try {
                this.finished();
            }
            finally {
                this.setTaskService(null);
            }
        }
    }
    
    protected void cancelled() {
    }
    
    protected void succeeded(final T t) {
    }
    
    protected void interrupted(final InterruptedException ex) {
    }
    
    protected void failed(final Throwable t) {
        Task.logger.log(Level.SEVERE, String.format("%s failed: %s", this, t), t);
    }
    
    protected void finished() {
    }
    
    public void addTaskListener(final TaskListener<T, V> taskListener) {
        if (taskListener == null) {
            throw new IllegalArgumentException("null listener");
        }
        this.taskListeners.add(taskListener);
    }
    
    public void removeTaskListener(final TaskListener<T, V> taskListener) {
        if (taskListener == null) {
            throw new IllegalArgumentException("null listener");
        }
        this.taskListeners.remove(taskListener);
    }
    
    public TaskListener<T, V>[] getTaskListeners() {
        return this.taskListeners.toArray(new TaskListener[this.taskListeners.size()]);
    }
    
    private void fireProcessListeners(final List<V> list) {
        final TaskEvent<List<V>> taskEvent = new TaskEvent<List<V>>(this, list);
        final Iterator<TaskListener<T, V>> iterator = this.taskListeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().process(taskEvent);
        }
    }
    
    private void fireDoInBackgroundListeners() {
        final TaskEvent<Void> taskEvent = new TaskEvent<Void>(this, null);
        final Iterator<TaskListener<T, V>> iterator = this.taskListeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().doInBackground(taskEvent);
        }
    }
    
    private void fireSucceededListeners(final T t) {
        final TaskEvent<T> taskEvent = new TaskEvent<T>(this, t);
        final Iterator<TaskListener<T, V>> iterator = this.taskListeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().succeeded(taskEvent);
        }
    }
    
    private void fireCancelledListeners() {
        final TaskEvent<Void> taskEvent = new TaskEvent<Void>(this, null);
        final Iterator<TaskListener<T, V>> iterator = this.taskListeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().cancelled(taskEvent);
        }
    }
    
    private void fireInterruptedListeners(final InterruptedException ex) {
        final TaskEvent<InterruptedException> taskEvent = new TaskEvent<InterruptedException>(this, ex);
        final Iterator<TaskListener<T, V>> iterator = this.taskListeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().interrupted(taskEvent);
        }
    }
    
    private void fireFailedListeners(final Throwable t) {
        final TaskEvent<Throwable> taskEvent = new TaskEvent<Throwable>(this, t);
        final Iterator<TaskListener<T, V>> iterator = this.taskListeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().failed(taskEvent);
        }
    }
    
    private void fireFinishedListeners() {
        final TaskEvent<Void> taskEvent = new TaskEvent<Void>(this, null);
        final Iterator<TaskListener<T, V>> iterator = this.taskListeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().finished(taskEvent);
        }
    }
    
    private void fireCompletionListeners() {
        try {
            if (this.isCancelled()) {
                this.fireCancelledListeners();
            }
            else {
                try {
                    this.fireSucceededListeners(this.get());
                }
                catch (InterruptedException ex) {
                    this.fireInterruptedListeners(ex);
                }
                catch (ExecutionException ex2) {
                    this.fireFailedListeners(ex2.getCause());
                }
            }
        }
        finally {
            this.fireFinishedListeners();
        }
    }
    
    public final InputBlocker getInputBlocker() {
        return this.inputBlocker;
    }
    
    public final void setInputBlocker(final InputBlocker inputBlocker) {
        if (this.getTaskService() != null) {
            throw new IllegalStateException("task already being executed");
        }
        final InputBlocker inputBlocker2;
        final InputBlocker inputBlocker3;
        synchronized (this) {
            inputBlocker2 = this.inputBlocker;
            this.inputBlocker = inputBlocker;
            inputBlocker3 = this.inputBlocker;
        }
        this.firePropertyChange("inputBlocker", inputBlocker2, inputBlocker3);
    }
    
    static {
        logger = Logger.getLogger(Task.class.getName());
    }
    
    public enum BlockingScope
    {
        NONE, 
        ACTION, 
        COMPONENT, 
        WINDOW, 
        APPLICATION;
    }
    
    private class StatePCL implements PropertyChangeListener
    {
        public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
            final String propertyName = propertyChangeEvent.getPropertyName();
            if ("state".equals(propertyName)) {
                final StateValue stateValue = (StateValue)propertyChangeEvent.getNewValue();
                final Task task = (Task)propertyChangeEvent.getSource();
                switch (stateValue) {
                    case STARTED: {
                        this.taskStarted(task);
                        break;
                    }
                    case DONE: {
                        this.taskDone(task);
                        break;
                    }
                }
            }
            else if ("progress".equals(propertyName)) {
                synchronized (Task.this) {
                    Task.this.progressPropertyIsValid = true;
                }
            }
        }
        
        private void taskStarted(final Task task) {
            synchronized (Task.this) {
                Task.this.startTime = System.currentTimeMillis();
            }
            Task.this.firePropertyChange("started", false, true);
            Task.this.fireDoInBackgroundListeners();
        }
        
        private void taskDone(final Task task) {
            synchronized (Task.this) {
                Task.this.doneTime = System.currentTimeMillis();
            }
            try {
                task.removePropertyChangeListener(this);
                Task.this.firePropertyChange("done", false, true);
                Task.this.fireCompletionListeners();
            }
            finally {
                Task.this.firePropertyChange("completed", false, true);
            }
        }
    }
    
    public abstract static class InputBlocker extends AbstractBean
    {
        private final Task task;
        private final BlockingScope scope;
        private final Object target;
        private final ApplicationAction action;
        
        public InputBlocker(final Task task, final BlockingScope scope, final Object target, final ApplicationAction action) {
            if (task == null) {
                throw new IllegalArgumentException("null task");
            }
            if (task.getTaskService() != null) {
                throw new IllegalStateException("task already being executed");
            }
            switch (scope) {
                case ACTION: {
                    if (!(target instanceof Action)) {
                        throw new IllegalArgumentException("target not an Action");
                    }
                    break;
                }
                case COMPONENT:
                case WINDOW: {
                    if (!(target instanceof Component)) {
                        throw new IllegalArgumentException("target not a Component");
                    }
                    break;
                }
            }
            this.task = task;
            this.scope = scope;
            this.target = target;
            this.action = action;
        }
        
        public InputBlocker(final Task task, final BlockingScope blockingScope, final Object o) {
            this(task, blockingScope, o, (o instanceof ApplicationAction) ? ((ApplicationAction)o) : null);
        }
        
        public final Task getTask() {
            return this.task;
        }
        
        public final BlockingScope getScope() {
            return this.scope;
        }
        
        public final Object getTarget() {
            return this.target;
        }
        
        public final ApplicationAction getAction() {
            return this.action;
        }
        
        protected abstract void block();
        
        protected abstract void unblock();
    }
}
