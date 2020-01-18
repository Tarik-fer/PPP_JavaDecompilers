// 
// Decompiled by Procyon v0.5.36
// 

package org.jdesktop.application;

import java.beans.PropertyChangeEvent;
import javax.swing.SwingUtilities;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class TaskService extends AbstractBean
{
    private final String name;
    private final ExecutorService executorService;
    private final List<Task> tasks;
    private final PropertyChangeListener taskPCL;
    
    public TaskService(final String name, final ExecutorService executorService) {
        if (name == null) {
            throw new IllegalArgumentException("null name");
        }
        if (executorService == null) {
            throw new IllegalArgumentException("null executorService");
        }
        this.name = name;
        this.executorService = executorService;
        this.tasks = new ArrayList<Task>();
        this.taskPCL = new TaskPCL();
    }
    
    public TaskService(final String s) {
        this(s, new ThreadPoolExecutor(3, 10, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>()));
    }
    
    public final String getName() {
        return this.name;
    }
    
    private List<Task> copyTasksList() {
        synchronized (this.tasks) {
            if (this.tasks.isEmpty()) {
                return (List<Task>)Collections.emptyList();
            }
            return new ArrayList<Task>(this.tasks);
        }
    }
    
    private void maybeBlockTask(final Task task) {
        final Task.InputBlocker inputBlocker = task.getInputBlocker();
        if (inputBlocker == null) {
            return;
        }
        if (inputBlocker.getScope() != Task.BlockingScope.NONE) {
            if (SwingUtilities.isEventDispatchThread()) {
                inputBlocker.block();
            }
            else {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        inputBlocker.block();
                    }
                });
            }
        }
    }
    
    public void execute(final Task task) {
        if (task == null) {
            throw new IllegalArgumentException("null task");
        }
        if (!task.isPending() || task.getTaskService() != null) {
            throw new IllegalArgumentException("task has already been executed");
        }
        task.setTaskService(this);
        final List<Task> copyTasksList;
        final List<Task> copyTasksList2;
        synchronized (this.tasks) {
            copyTasksList = this.copyTasksList();
            this.tasks.add(task);
            copyTasksList2 = this.copyTasksList();
            task.addPropertyChangeListener(this.taskPCL);
        }
        this.firePropertyChange("tasks", copyTasksList, copyTasksList2);
        this.maybeBlockTask(task);
        this.executorService.execute(task);
    }
    
    public List<Task> getTasks() {
        return this.copyTasksList();
    }
    
    public final void shutdown() {
        this.executorService.shutdown();
    }
    
    public final List<Runnable> shutdownNow() {
        return this.executorService.shutdownNow();
    }
    
    public final boolean isShutdown() {
        return this.executorService.isShutdown();
    }
    
    public final boolean isTerminated() {
        return this.executorService.isTerminated();
    }
    
    public final boolean awaitTermination(final long n, final TimeUnit timeUnit) throws InterruptedException {
        return this.executorService.awaitTermination(n, timeUnit);
    }
    
    private class TaskPCL implements PropertyChangeListener
    {
        public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
            if ("done".equals(propertyChangeEvent.getPropertyName())) {
                final Task task = (Task)propertyChangeEvent.getSource();
                if (task.isDone()) {
                    final List access$200;
                    final List access$201;
                    synchronized (TaskService.this.tasks) {
                        access$200 = TaskService.this.copyTasksList();
                        TaskService.this.tasks.remove(task);
                        task.removePropertyChangeListener(TaskService.this.taskPCL);
                        access$201 = TaskService.this.copyTasksList();
                    }
                    TaskService.this.firePropertyChange("tasks", access$200, access$201);
                    final Task.InputBlocker inputBlocker = task.getInputBlocker();
                    if (inputBlocker != null) {
                        inputBlocker.unblock();
                    }
                }
            }
        }
    }
}
