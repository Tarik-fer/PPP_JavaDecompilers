// 
// Decompiled by Procyon v0.5.36
// 

package org.jdesktop.application;

import org.jdesktop.swingworker.SwingWorker;
import java.beans.PropertyChangeEvent;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;
import java.beans.PropertyChangeListener;

public class TaskMonitor extends AbstractBean
{
    private final PropertyChangeListener applicationPCL;
    private final PropertyChangeListener taskServicePCL;
    private final PropertyChangeListener taskPCL;
    private final LinkedList<Task> taskQueue;
    private boolean autoUpdateForegroundTask;
    private Task foregroundTask;
    
    public TaskMonitor(final ApplicationContext applicationContext) {
        this.autoUpdateForegroundTask = true;
        this.foregroundTask = null;
        this.applicationPCL = new ApplicationPCL();
        this.taskServicePCL = new TaskServicePCL();
        this.taskPCL = new TaskPCL();
        this.taskQueue = new LinkedList<Task>();
        applicationContext.addPropertyChangeListener(this.applicationPCL);
        final Iterator<TaskService> iterator = applicationContext.getTaskServices().iterator();
        while (iterator.hasNext()) {
            iterator.next().addPropertyChangeListener(this.taskServicePCL);
        }
    }
    
    public void setForegroundTask(final Task foregroundTask) {
        final Task foregroundTask2 = this.foregroundTask;
        if (foregroundTask2 != null) {
            foregroundTask2.removePropertyChangeListener(this.taskPCL);
        }
        this.foregroundTask = foregroundTask;
        final Task foregroundTask3 = this.foregroundTask;
        if (foregroundTask3 != null) {
            foregroundTask3.addPropertyChangeListener(this.taskPCL);
        }
        this.firePropertyChange("foregroundTask", foregroundTask2, foregroundTask3);
    }
    
    public Task getForegroundTask() {
        return this.foregroundTask;
    }
    
    public boolean getAutoUpdateForegroundTask() {
        return this.autoUpdateForegroundTask;
    }
    
    public void setAutoUpdateForegroundTask(final boolean autoUpdateForegroundTask) {
        final boolean autoUpdateForegroundTask2 = this.autoUpdateForegroundTask;
        this.autoUpdateForegroundTask = autoUpdateForegroundTask;
        this.firePropertyChange("autoUpdateForegroundTask", autoUpdateForegroundTask2, this.autoUpdateForegroundTask);
    }
    
    private List<Task> copyTaskQueue() {
        synchronized (this.taskQueue) {
            if (this.taskQueue.isEmpty()) {
                return (List<Task>)Collections.emptyList();
            }
            return new ArrayList<Task>(this.taskQueue);
        }
    }
    
    public List<Task> getTasks() {
        return this.copyTaskQueue();
    }
    
    private void updateTasks(final List<Task> list, final List<Task> list2) {
        boolean b = false;
        final List<Task> copyTaskQueue = this.copyTaskQueue();
        for (final Task task : list) {
            if (!list2.contains(task) && this.taskQueue.remove(task)) {
                b = true;
            }
        }
        for (final Task task2 : list2) {
            if (!this.taskQueue.contains(task2)) {
                this.taskQueue.addLast(task2);
                b = true;
            }
        }
        final Iterator<Object> iterator3 = this.taskQueue.iterator();
        while (iterator3.hasNext()) {
            if (iterator3.next().isDone()) {
                iterator3.remove();
                b = true;
            }
        }
        if (b) {
            this.firePropertyChange("tasks", copyTaskQueue, this.copyTaskQueue());
        }
        if (this.autoUpdateForegroundTask && this.getForegroundTask() == null) {
            this.setForegroundTask(this.taskQueue.isEmpty() ? null : this.taskQueue.getLast());
        }
    }
    
    private class ApplicationPCL implements PropertyChangeListener
    {
        public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
            if ("taskServices".equals(propertyChangeEvent.getPropertyName())) {
                final List list = (List)propertyChangeEvent.getOldValue();
                final List list2 = (List)propertyChangeEvent.getNewValue();
                final Iterator<TaskService> iterator = list.iterator();
                while (iterator.hasNext()) {
                    iterator.next().removePropertyChangeListener(TaskMonitor.this.taskServicePCL);
                }
                final Iterator<TaskService> iterator2 = list2.iterator();
                while (iterator2.hasNext()) {
                    iterator2.next().addPropertyChangeListener(TaskMonitor.this.taskServicePCL);
                }
            }
        }
    }
    
    private class TaskServicePCL implements PropertyChangeListener
    {
        public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
            if ("tasks".equals(propertyChangeEvent.getPropertyName())) {
                TaskMonitor.this.updateTasks((List)propertyChangeEvent.getOldValue(), (List)propertyChangeEvent.getNewValue());
            }
        }
    }
    
    private class TaskPCL implements PropertyChangeListener
    {
        private void fireStateChange(final Task task, final String s) {
            TaskMonitor.this.firePropertyChange(new PropertyChangeEvent(task, s, false, true));
        }
        
        public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
            final String propertyName = propertyChangeEvent.getPropertyName();
            final Task task = (Task)propertyChangeEvent.getSource();
            propertyChangeEvent.getNewValue();
            if (task != null && task == TaskMonitor.this.getForegroundTask()) {
                TaskMonitor.this.firePropertyChange(propertyChangeEvent);
                if ("state".equals(propertyName)) {
                    switch ((SwingWorker.StateValue)propertyChangeEvent.getNewValue()) {
                        case PENDING: {
                            this.fireStateChange(task, "pending");
                            break;
                        }
                        case STARTED: {
                            this.fireStateChange(task, "started");
                            break;
                        }
                        case DONE: {
                            this.fireStateChange(task, "done");
                            TaskMonitor.this.setForegroundTask(null);
                            break;
                        }
                    }
                }
            }
        }
    }
}
