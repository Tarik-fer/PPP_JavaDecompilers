/*     */ package org.jdesktop.application;
/*     */ 
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.LinkedBlockingQueue;
/*     */ import java.util.concurrent.ThreadPoolExecutor;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import javax.swing.SwingUtilities;
/*     */ 
/*     */ public class TaskService
/*     */   extends AbstractBean
/*     */ {
/*     */   private final String name;
/*     */   private final ExecutorService executorService;
/*     */   private final List<Task> tasks;
/*     */   private final PropertyChangeListener taskPCL;
/*     */   
/*     */   public TaskService(String paramString, ExecutorService paramExecutorService) {
/*  23 */     if (paramString == null) {
/*  24 */       throw new IllegalArgumentException("null name");
/*     */     }
/*  26 */     if (paramExecutorService == null) {
/*  27 */       throw new IllegalArgumentException("null executorService");
/*     */     }
/*  29 */     this.name = paramString;
/*  30 */     this.executorService = paramExecutorService;
/*  31 */     this.tasks = new ArrayList<Task>();
/*  32 */     this.taskPCL = new TaskPCL();
/*     */   }
/*     */ 
/*     */   
/*  36 */   public TaskService(String paramString) { this(paramString, new ThreadPoolExecutor(3, 10, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>())); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   public final String getName() { return this.name; }
/*     */ 
/*     */   
/*     */   private List<Task> copyTasksList() {
/*  48 */     synchronized (this.tasks) {
/*  49 */       if (this.tasks.isEmpty()) {
/*  50 */         return Collections.emptyList();
/*     */       }
/*     */       
/*  53 */       return new ArrayList<Task>(this.tasks);
/*     */     } 
/*     */   }
/*     */   
/*     */   private class TaskPCL
/*     */     implements PropertyChangeListener {
/*     */     public void propertyChange(PropertyChangeEvent param1PropertyChangeEvent) {
/*  60 */       String str = param1PropertyChangeEvent.getPropertyName();
/*  61 */       if ("done".equals(str)) {
/*  62 */         Task task = (Task)param1PropertyChangeEvent.getSource();
/*  63 */         if (task.isDone()) {
/*     */           List list2, list1;
/*  65 */           synchronized (TaskService.this.tasks) {
/*  66 */             list1 = TaskService.this.copyTasksList();
/*  67 */             TaskService.this.tasks.remove(task);
/*  68 */             task.removePropertyChangeListener(TaskService.this.taskPCL);
/*  69 */             list2 = TaskService.this.copyTasksList();
/*     */           } 
/*  71 */           TaskService.this.firePropertyChange("tasks", list1, list2);
/*  72 */           Task.InputBlocker inputBlocker = task.getInputBlocker();
/*  73 */           if (inputBlocker != null)
/*  74 */             inputBlocker.unblock(); 
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     private TaskPCL() {} }
/*     */   
/*     */   private void maybeBlockTask(Task paramTask) {
/*  82 */     final Task.InputBlocker inputBlocker = paramTask.getInputBlocker();
/*  83 */     if (inputBlocker == null) {
/*     */       return;
/*     */     }
/*  86 */     if (inputBlocker.getScope() != Task.BlockingScope.NONE)
/*  87 */       if (SwingUtilities.isEventDispatchThread()) {
/*  88 */         inputBlocker.block();
/*     */       } else {
/*     */         
/*  91 */         Runnable runnable = new Runnable() {
/*     */             public void run() {
/*  93 */               inputBlocker.block();
/*     */             }
/*     */           };
/*  96 */         SwingUtilities.invokeLater(runnable);
/*     */       }  
/*     */   }
/*     */   
/*     */   public void execute(Task paramTask) {
/*     */     List<Task> list2, list1;
/* 102 */     if (paramTask == null) {
/* 103 */       throw new IllegalArgumentException("null task");
/*     */     }
/* 105 */     if (!paramTask.isPending() || paramTask.getTaskService() != null) {
/* 106 */       throw new IllegalArgumentException("task has already been executed");
/*     */     }
/* 108 */     paramTask.setTaskService(this);
/*     */ 
/*     */     
/* 111 */     synchronized (this.tasks) {
/* 112 */       list1 = copyTasksList();
/* 113 */       this.tasks.add(paramTask);
/* 114 */       list2 = copyTasksList();
/* 115 */       paramTask.addPropertyChangeListener(this.taskPCL);
/*     */     } 
/* 117 */     firePropertyChange("tasks", list1, list2);
/* 118 */     maybeBlockTask(paramTask);
/* 119 */     this.executorService.execute((Runnable)paramTask);
/*     */   }
/*     */ 
/*     */   
/* 123 */   public List<Task> getTasks() { return copyTasksList(); }
/*     */ 
/*     */ 
/*     */   
/* 127 */   public final void shutdown() { this.executorService.shutdown(); }
/*     */ 
/*     */ 
/*     */   
/* 131 */   public final List<Runnable> shutdownNow() { return this.executorService.shutdownNow(); }
/*     */ 
/*     */ 
/*     */   
/* 135 */   public final boolean isShutdown() { return this.executorService.isShutdown(); }
/*     */ 
/*     */ 
/*     */   
/* 139 */   public final boolean isTerminated() { return this.executorService.isTerminated(); }
/*     */ 
/*     */ 
/*     */   
/* 143 */   public final boolean awaitTermination(long paramLong, TimeUnit paramTimeUnit) throws InterruptedException { return this.executorService.awaitTermination(paramLong, paramTimeUnit); }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\jdesktop\application\TaskService.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */