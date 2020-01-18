/*     */ package org.jdesktop.application;
/*     */ 
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.jdesktop.swingworker.SwingWorker;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TaskMonitor
/*     */   extends AbstractBean
/*     */ {
/*     */   private final PropertyChangeListener applicationPCL;
/*     */   private final PropertyChangeListener taskServicePCL;
/*     */   private final PropertyChangeListener taskPCL;
/*     */   private final LinkedList<Task> taskQueue;
/*     */   private boolean autoUpdateForegroundTask = true;
/*  74 */   private Task foregroundTask = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TaskMonitor(ApplicationContext paramApplicationContext) {
/*  80 */     this.applicationPCL = new ApplicationPCL();
/*  81 */     this.taskServicePCL = new TaskServicePCL();
/*  82 */     this.taskPCL = new TaskPCL();
/*  83 */     this.taskQueue = new LinkedList<Task>();
/*  84 */     paramApplicationContext.addPropertyChangeListener(this.applicationPCL);
/*  85 */     for (TaskService taskService : paramApplicationContext.getTaskServices()) {
/*  86 */       taskService.addPropertyChangeListener(this.taskServicePCL);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setForegroundTask(Task paramTask) {
/* 104 */     Task task1 = this.foregroundTask;
/* 105 */     if (task1 != null) {
/* 106 */       task1.removePropertyChangeListener(this.taskPCL);
/*     */     }
/* 108 */     this.foregroundTask = paramTask;
/* 109 */     Task task2 = this.foregroundTask;
/* 110 */     if (task2 != null) {
/* 111 */       task2.addPropertyChangeListener(this.taskPCL);
/*     */     }
/* 113 */     firePropertyChange("foregroundTask", task1, task2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 125 */   public Task getForegroundTask() { return this.foregroundTask; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 140 */   public boolean getAutoUpdateForegroundTask() { return this.autoUpdateForegroundTask; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAutoUpdateForegroundTask(boolean paramBoolean) {
/* 155 */     boolean bool = this.autoUpdateForegroundTask;
/* 156 */     this.autoUpdateForegroundTask = paramBoolean;
/* 157 */     firePropertyChange("autoUpdateForegroundTask", Boolean.valueOf(bool), Boolean.valueOf(this.autoUpdateForegroundTask));
/*     */   }
/*     */   
/*     */   private List<Task> copyTaskQueue() {
/* 161 */     synchronized (this.taskQueue) {
/* 162 */       if (this.taskQueue.isEmpty()) {
/* 163 */         return Collections.emptyList();
/*     */       }
/*     */       
/* 166 */       return new ArrayList<Task>(this.taskQueue);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 181 */   public List<Task> getTasks() { return copyTaskQueue(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateTasks(List<Task> paramList1, List<Task> paramList2) {
/* 189 */     boolean bool = false;
/* 190 */     List<Task> list = copyTaskQueue();
/*     */     
/* 192 */     for (Task task : paramList1) {
/* 193 */       if (!paramList2.contains(task) && 
/* 194 */         this.taskQueue.remove(task)) {
/* 195 */         bool = true;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 200 */     for (Task task : paramList2) {
/* 201 */       if (!this.taskQueue.contains(task)) {
/* 202 */         this.taskQueue.addLast(task);
/* 203 */         bool = true;
/*     */       } 
/*     */     } 
/*     */     
/* 207 */     Iterator<Task> iterator = this.taskQueue.iterator();
/* 208 */     while (iterator.hasNext()) {
/* 209 */       Task task = iterator.next();
/* 210 */       if (task.isDone()) {
/* 211 */         iterator.remove();
/* 212 */         bool = true;
/*     */       } 
/*     */     } 
/*     */     
/* 216 */     if (bool) {
/* 217 */       List<Task> list1 = copyTaskQueue();
/* 218 */       firePropertyChange("tasks", list, list1);
/*     */     } 
/*     */     
/* 221 */     if (this.autoUpdateForegroundTask && getForegroundTask() == null) {
/* 222 */       setForegroundTask(this.taskQueue.isEmpty() ? null : this.taskQueue.getLast());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private class ApplicationPCL
/*     */     implements PropertyChangeListener
/*     */   {
/*     */     private ApplicationPCL() {}
/*     */ 
/*     */     
/*     */     public void propertyChange(PropertyChangeEvent param1PropertyChangeEvent) {
/* 235 */       String str = param1PropertyChangeEvent.getPropertyName();
/* 236 */       if ("taskServices".equals(str)) {
/* 237 */         List list1 = (List)param1PropertyChangeEvent.getOldValue();
/* 238 */         List list2 = (List)param1PropertyChangeEvent.getNewValue();
/* 239 */         for (TaskService taskService : list1) {
/* 240 */           taskService.removePropertyChangeListener(TaskMonitor.this.taskServicePCL);
/*     */         }
/* 242 */         for (TaskService taskService : list2) {
/* 243 */           taskService.addPropertyChangeListener(TaskMonitor.this.taskServicePCL);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private class TaskServicePCL
/*     */     implements PropertyChangeListener
/*     */   {
/*     */     private TaskServicePCL() {}
/*     */     
/*     */     public void propertyChange(PropertyChangeEvent param1PropertyChangeEvent) {
/* 256 */       String str = param1PropertyChangeEvent.getPropertyName();
/* 257 */       if ("tasks".equals(str)) {
/* 258 */         List list1 = (List)param1PropertyChangeEvent.getOldValue();
/* 259 */         List list2 = (List)param1PropertyChangeEvent.getNewValue();
/* 260 */         TaskMonitor.this.updateTasks(list1, list2);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private class TaskPCL
/*     */     implements PropertyChangeListener
/*     */   {
/*     */     private TaskPCL() {}
/*     */ 
/*     */     
/* 272 */     private void fireStateChange(Task param1Task, String param1String) { TaskMonitor.this.firePropertyChange(new PropertyChangeEvent(param1Task, param1String, Boolean.valueOf(false), Boolean.valueOf(true))); }
/*     */     
/*     */     public void propertyChange(PropertyChangeEvent param1PropertyChangeEvent) {
/* 275 */       String str = param1PropertyChangeEvent.getPropertyName();
/* 276 */       Task task = (Task)param1PropertyChangeEvent.getSource();
/* 277 */       Object object = param1PropertyChangeEvent.getNewValue();
/* 278 */       if (task != null && task == TaskMonitor.this.getForegroundTask()) {
/* 279 */         TaskMonitor.this.firePropertyChange(param1PropertyChangeEvent);
/* 280 */         if ("state".equals(str)) {
/* 281 */           SwingWorker.StateValue stateValue = (SwingWorker.StateValue)param1PropertyChangeEvent.getNewValue();
/* 282 */           switch (stateValue) { case PENDING:
/* 283 */               fireStateChange(task, "pending"); break;
/* 284 */             case STARTED: fireStateChange(task, "started"); break;
/*     */             case DONE:
/* 286 */               fireStateChange(task, "done");
/* 287 */               TaskMonitor.this.setForegroundTask(null);
/*     */               break; }
/*     */         
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\jdesktop\application\TaskMonitor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */