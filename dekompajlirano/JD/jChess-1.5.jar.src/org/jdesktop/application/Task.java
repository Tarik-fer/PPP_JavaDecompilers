/*      */ package org.jdesktop.application;
/*      */ 
/*      */ import java.beans.PropertyChangeEvent;
/*      */ import java.beans.PropertyChangeListener;
/*      */ import java.util.List;
/*      */ import java.util.concurrent.CopyOnWriteArrayList;
/*      */ import java.util.concurrent.ExecutionException;
/*      */ import java.util.concurrent.TimeUnit;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.Logger;
/*      */ import org.jdesktop.swingworker.SwingWorker;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class Task<T, V>
/*      */   extends SwingWorker<T, V>
/*      */ {
/*  128 */   private static final Logger logger = Logger.getLogger(Task.class.getName());
/*      */   private final Application application;
/*      */   private String resourcePrefix;
/*      */   private ResourceMap resourceMap;
/*      */   private List<TaskListener<T, V>> taskListeners;
/*      */   private InputBlocker inputBlocker;
/*  134 */   private String name = null;
/*  135 */   private String title = null;
/*  136 */   private String description = null;
/*  137 */   private long messageTime = -1L;
/*  138 */   private String message = null;
/*  139 */   private long startTime = -1L;
/*  140 */   private long doneTime = -1L;
/*      */   private boolean userCanCancel = true;
/*      */   private boolean progressPropertyIsValid = false;
/*  143 */   private TaskService taskService = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public enum BlockingScope
/*      */   {
/*  157 */     NONE,
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  162 */     ACTION,
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  167 */     COMPONENT,
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  172 */     WINDOW,
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  177 */     APPLICATION;
/*      */   }
/*      */   
/*      */   private void initTask(ResourceMap paramResourceMap, String paramString) {
/*  181 */     this.resourceMap = paramResourceMap;
/*  182 */     if (paramString == null || paramString.length() == 0) {
/*  183 */       this.resourcePrefix = "";
/*      */     }
/*  185 */     else if (paramString.endsWith(".")) {
/*  186 */       this.resourcePrefix = paramString;
/*      */     } else {
/*      */       
/*  189 */       this.resourcePrefix = paramString + ".";
/*      */     } 
/*  191 */     if (paramResourceMap != null) {
/*  192 */       this.title = paramResourceMap.getString(resourceName("title"), new Object[0]);
/*  193 */       this.description = paramResourceMap.getString(resourceName("description"), new Object[0]);
/*  194 */       this.message = paramResourceMap.getString(resourceName("message"), new Object[0]);
/*  195 */       if (this.message != null) {
/*  196 */         this.messageTime = System.currentTimeMillis();
/*      */       }
/*      */     } 
/*  199 */     addPropertyChangeListener(new StatePCL());
/*  200 */     this.taskListeners = new CopyOnWriteArrayList<TaskListener<T, V>>();
/*      */   }
/*      */ 
/*      */   
/*  204 */   private ResourceMap defaultResourceMap(Application paramApplication) { return paramApplication.getContext().getResourceMap(getClass(), Task.class); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public Task(Application paramApplication, ResourceMap paramResourceMap, String paramString) {
/*  236 */     this.application = paramApplication;
/*  237 */     initTask(paramResourceMap, paramString);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public Task(Application paramApplication, String paramString) {
/*  266 */     this.application = paramApplication;
/*  267 */     initTask(defaultResourceMap(paramApplication), paramString);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Task(Application paramApplication) {
/*  277 */     this.application = paramApplication;
/*  278 */     initTask(defaultResourceMap(paramApplication), "");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*  283 */   public final Application getApplication() { return this.application; }
/*      */ 
/*      */ 
/*      */   
/*  287 */   public final ApplicationContext getContext() { return getApplication().getContext(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  303 */   public synchronized TaskService getTaskService() { return this.taskService; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   synchronized void setTaskService(TaskService paramTaskService) {
/*      */     TaskService taskService2, taskService1;
/*  312 */     synchronized (this) {
/*  313 */       taskService1 = this.taskService;
/*  314 */       this.taskService = paramTaskService;
/*  315 */       taskService2 = this.taskService;
/*      */     } 
/*  317 */     firePropertyChange("taskService", taskService1, taskService2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  335 */   protected final String resourceName(String paramString) { return this.resourcePrefix + paramString; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  348 */   public final ResourceMap getResourceMap() { return this.resourceMap; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  366 */   public synchronized String getTitle() { return this.title; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setTitle(String paramString) {
/*      */     String str2, str1;
/*  389 */     synchronized (this) {
/*  390 */       str1 = this.title;
/*  391 */       this.title = paramString;
/*  392 */       str2 = this.title;
/*      */     } 
/*  394 */     firePropertyChange("title", str1, str2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  412 */   public synchronized String getDescription() { return this.description; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setDescription(String paramString) {
/*      */     String str2, str1;
/*  431 */     synchronized (this) {
/*  432 */       str1 = this.description;
/*  433 */       this.description = paramString;
/*  434 */       str2 = this.description;
/*      */     } 
/*  436 */     firePropertyChange("description", str1, str2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getExecutionDuration(TimeUnit paramTimeUnit) {
/*      */     long l3;
/*      */     long l2;
/*      */     long l1;
/*  456 */     synchronized (this) {
/*  457 */       l1 = this.startTime;
/*  458 */       l2 = this.doneTime;
/*      */     } 
/*  460 */     if (l1 == -1L) {
/*  461 */       l3 = 0L;
/*      */     }
/*  463 */     else if (l2 == -1L) {
/*  464 */       l3 = System.currentTimeMillis() - l1;
/*      */     } else {
/*      */       
/*  467 */       l3 = l2 - l1;
/*      */     } 
/*  469 */     return paramTimeUnit.convert(Math.max(0L, l3), TimeUnit.MILLISECONDS);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  485 */   public String getMessage() { return this.message; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setMessage(String paramString) {
/*      */     String str2, str1;
/*  528 */     synchronized (this) {
/*  529 */       str1 = this.message;
/*  530 */       this.message = paramString;
/*  531 */       str2 = this.message;
/*  532 */       this.messageTime = System.currentTimeMillis();
/*      */     } 
/*  534 */     firePropertyChange("message", str1, str2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final void message(String paramString, Object... paramVarArgs) {
/*  559 */     ResourceMap resourceMap1 = getResourceMap();
/*  560 */     if (resourceMap1 != null) {
/*  561 */       setMessage(resourceMap1.getString(resourceName(paramString), paramVarArgs));
/*      */     } else {
/*      */       
/*  564 */       setMessage(paramString);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getMessageDuration(TimeUnit paramTimeUnit) {
/*      */     long l1;
/*  578 */     synchronized (this) {
/*  579 */       l1 = this.messageTime;
/*      */     } 
/*  581 */     long l2 = (l1 == -1L) ? 0L : Math.max(0L, System.currentTimeMillis() - l1);
/*  582 */     return paramTimeUnit.convert(l2, TimeUnit.MILLISECONDS);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  597 */   public synchronized boolean getUserCanCancel() { return this.userCanCancel; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setUserCanCancel(boolean paramBoolean) {
/*      */     boolean bool2, bool1;
/*  619 */     synchronized (this) {
/*  620 */       bool1 = this.userCanCancel;
/*  621 */       this.userCanCancel = paramBoolean;
/*  622 */       bool2 = this.userCanCancel;
/*      */     } 
/*  624 */     firePropertyChange("userCanCancel", Boolean.valueOf(bool1), Boolean.valueOf(bool2));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  644 */   public synchronized boolean isProgressPropertyValid() { return this.progressPropertyIsValid; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final void setProgress(int paramInt1, int paramInt2, int paramInt3) {
/*  660 */     if (paramInt2 >= paramInt3) {
/*  661 */       throw new IllegalArgumentException("invalid range: min >= max");
/*      */     }
/*  663 */     if (paramInt1 < paramInt2 || paramInt1 > paramInt3) {
/*  664 */       throw new IllegalArgumentException("invalid value");
/*      */     }
/*  666 */     float f = (paramInt1 - paramInt2) / (paramInt3 - paramInt2);
/*  667 */     setProgress(Math.round(f * 100.0F));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final void setProgress(float paramFloat) {
/*  678 */     if (paramFloat < 0.0D || paramFloat > 1.0D) {
/*  679 */       throw new IllegalArgumentException("invalid percentage");
/*      */     }
/*  681 */     setProgress(Math.round(paramFloat * 100.0F));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final void setProgress(float paramFloat1, float paramFloat2, float paramFloat3) {
/*  697 */     if (paramFloat2 >= paramFloat3) {
/*  698 */       throw new IllegalArgumentException("invalid range: min >= max");
/*      */     }
/*  700 */     if (paramFloat1 < paramFloat2 || paramFloat1 > paramFloat3) {
/*  701 */       throw new IllegalArgumentException("invalid value");
/*      */     }
/*  703 */     float f = (paramFloat1 - paramFloat2) / (paramFloat3 - paramFloat2);
/*  704 */     setProgress(Math.round(f * 100.0F));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  716 */   public final boolean isPending() { return (getState() == SwingWorker.StateValue.PENDING); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  728 */   public final boolean isStarted() { return (getState() == SwingWorker.StateValue.STARTED); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  741 */   protected void process(List<V> paramList) { fireProcessListeners(paramList); }
/*      */ 
/*      */   
/*      */   protected final void done() {
/*      */     try {
/*  746 */       if (isCancelled()) {
/*  747 */         cancelled();
/*      */       } else {
/*      */         
/*      */         try {
/*  751 */           succeeded((T)get());
/*      */         }
/*  753 */         catch (InterruptedException interruptedException) {
/*  754 */           interrupted(interruptedException);
/*      */         }
/*  756 */         catch (ExecutionException executionException) {
/*  757 */           failed(executionException.getCause());
/*      */         } 
/*      */       } 
/*      */     } finally {
/*      */       
/*      */       try {
/*  763 */         finished();
/*      */       } finally {
/*      */         
/*  766 */         setTaskService(null);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void cancelled() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void succeeded(T paramT) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void interrupted(InterruptedException paramInterruptedException) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void failed(Throwable paramThrowable) {
/*  823 */     String str = String.format("%s failed: %s", new Object[] { this, paramThrowable });
/*  824 */     logger.log(Level.SEVERE, str, paramThrowable);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void finished() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addTaskListener(TaskListener<T, V> paramTaskListener) {
/*  854 */     if (paramTaskListener == null) {
/*  855 */       throw new IllegalArgumentException("null listener");
/*      */     }
/*  857 */     this.taskListeners.add(paramTaskListener);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeTaskListener(TaskListener<T, V> paramTaskListener) {
/*  868 */     if (paramTaskListener == null) {
/*  869 */       throw new IllegalArgumentException("null listener");
/*      */     }
/*  871 */     this.taskListeners.remove(paramTaskListener);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  882 */   public TaskListener<T, V>[] getTaskListeners() { return this.taskListeners.toArray((TaskListener<T, V>[])new TaskListener[this.taskListeners.size()]); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void fireProcessListeners(List<V> paramList) {
/*  889 */     TaskEvent<List<V>> taskEvent = new TaskEvent<List<V>>(this, paramList);
/*  890 */     for (TaskListener<T, V> taskListener : this.taskListeners) {
/*  891 */       taskListener.process(taskEvent);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void fireDoInBackgroundListeners() {
/*  899 */     TaskEvent<Object> taskEvent = new TaskEvent<Object>(this, null);
/*  900 */     for (TaskListener<T, V> taskListener : this.taskListeners) {
/*  901 */       taskListener.doInBackground((TaskEvent)taskEvent);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void fireSucceededListeners(T paramT) {
/*  909 */     TaskEvent<T> taskEvent = new TaskEvent<T>(this, paramT);
/*  910 */     for (TaskListener<T, V> taskListener : this.taskListeners) {
/*  911 */       taskListener.succeeded(taskEvent);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void fireCancelledListeners() {
/*  919 */     TaskEvent<Object> taskEvent = new TaskEvent<Object>(this, null);
/*  920 */     for (TaskListener<T, V> taskListener : this.taskListeners) {
/*  921 */       taskListener.cancelled((TaskEvent)taskEvent);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void fireInterruptedListeners(InterruptedException paramInterruptedException) {
/*  929 */     TaskEvent<InterruptedException> taskEvent = new TaskEvent<InterruptedException>(this, paramInterruptedException);
/*  930 */     for (TaskListener<T, V> taskListener : this.taskListeners) {
/*  931 */       taskListener.interrupted(taskEvent);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void fireFailedListeners(Throwable paramThrowable) {
/*  939 */     TaskEvent<Throwable> taskEvent = new TaskEvent<Throwable>(this, paramThrowable);
/*  940 */     for (TaskListener<T, V> taskListener : this.taskListeners) {
/*  941 */       taskListener.failed(taskEvent);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void fireFinishedListeners() {
/*  949 */     TaskEvent<Object> taskEvent = new TaskEvent<Object>(this, null);
/*  950 */     for (TaskListener<T, V> taskListener : this.taskListeners) {
/*  951 */       taskListener.finished((TaskEvent)taskEvent);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void fireCompletionListeners() {
/*      */     try {
/*  960 */       if (isCancelled()) {
/*  961 */         fireCancelledListeners();
/*      */       } else {
/*      */         
/*      */         try {
/*  965 */           fireSucceededListeners((T)get());
/*      */         }
/*  967 */         catch (InterruptedException interruptedException) {
/*  968 */           fireInterruptedListeners(interruptedException);
/*      */         }
/*  970 */         catch (ExecutionException executionException) {
/*  971 */           fireFailedListeners(executionException.getCause());
/*      */         } 
/*      */       } 
/*      */     } finally {
/*      */       
/*  976 */       fireFinishedListeners();
/*      */     } 
/*      */   }
/*      */   
/*      */   private class StatePCL implements PropertyChangeListener {
/*      */     public void propertyChange(PropertyChangeEvent param1PropertyChangeEvent) {
/*  982 */       String str = param1PropertyChangeEvent.getPropertyName();
/*  983 */       if ("state".equals(str)) {
/*  984 */         SwingWorker.StateValue stateValue = (SwingWorker.StateValue)param1PropertyChangeEvent.getNewValue();
/*  985 */         Task task = (Task)param1PropertyChangeEvent.getSource();
/*  986 */         switch (stateValue) { case ACTION:
/*  987 */             taskStarted(task); break;
/*  988 */           case COMPONENT: taskDone(task);
/*      */             break; }
/*      */       
/*  991 */       } else if ("progress".equals(str)) {
/*  992 */         synchronized (Task.this) {
/*  993 */           Task.this.progressPropertyIsValid = true;
/*      */         } 
/*      */       } 
/*      */     } private StatePCL() {}
/*      */     private void taskStarted(Task param1Task) {
/*  998 */       synchronized (Task.this) {
/*  999 */         Task.this.startTime = System.currentTimeMillis();
/*      */       } 
/* 1001 */       Task.this.firePropertyChange("started", Boolean.valueOf(false), Boolean.valueOf(true));
/* 1002 */       Task.this.fireDoInBackgroundListeners();
/*      */     }
/*      */     private void taskDone(Task param1Task) {
/* 1005 */       synchronized (Task.this) {
/* 1006 */         Task.this.doneTime = System.currentTimeMillis();
/*      */       } 
/*      */       try {
/* 1009 */         param1Task.removePropertyChangeListener(this);
/* 1010 */         Task.this.firePropertyChange("done", Boolean.valueOf(false), Boolean.valueOf(true));
/* 1011 */         Task.this.fireCompletionListeners();
/*      */       } finally {
/*      */         
/* 1014 */         Task.this.firePropertyChange("completed", Boolean.valueOf(false), Boolean.valueOf(true));
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1027 */   public final InputBlocker getInputBlocker() { return this.inputBlocker; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setInputBlocker(InputBlocker paramInputBlocker) {
/*      */     InputBlocker inputBlocker2, inputBlocker1;
/* 1046 */     if (getTaskService() != null) {
/* 1047 */       throw new IllegalStateException("task already being executed");
/*      */     }
/*      */     
/* 1050 */     synchronized (this) {
/* 1051 */       inputBlocker1 = this.inputBlocker;
/* 1052 */       this.inputBlocker = paramInputBlocker;
/* 1053 */       inputBlocker2 = this.inputBlocker;
/*      */     } 
/* 1055 */     firePropertyChange("inputBlocker", inputBlocker1, inputBlocker2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static abstract class InputBlocker
/*      */     extends AbstractBean
/*      */   {
/*      */     private final Task task;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private final Task.BlockingScope scope;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private final Object target;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private final ApplicationAction action;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public InputBlocker(Task param1Task, Task.BlockingScope param1BlockingScope, Object param1Object, ApplicationAction param1ApplicationAction) {
/* 1115 */       if (param1Task == null) {
/* 1116 */         throw new IllegalArgumentException("null task");
/*      */       }
/* 1118 */       if (param1Task.getTaskService() != null) {
/* 1119 */         throw new IllegalStateException("task already being executed");
/*      */       }
/* 1121 */       switch (param1BlockingScope) {
/*      */         case ACTION:
/* 1123 */           if (!(param1Object instanceof javax.swing.Action)) {
/* 1124 */             throw new IllegalArgumentException("target not an Action");
/*      */           }
/*      */           break;
/*      */         case COMPONENT:
/*      */         case WINDOW:
/* 1129 */           if (!(param1Object instanceof java.awt.Component)) {
/* 1130 */             throw new IllegalArgumentException("target not a Component");
/*      */           }
/*      */           break;
/*      */       } 
/* 1134 */       this.task = param1Task;
/* 1135 */       this.scope = param1BlockingScope;
/* 1136 */       this.target = param1Object;
/* 1137 */       this.action = param1ApplicationAction;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1152 */     public InputBlocker(Task param1Task, Task.BlockingScope param1BlockingScope, Object param1Object) { this(param1Task, param1BlockingScope, param1Object, (param1Object instanceof ApplicationAction) ? (ApplicationAction)param1Object : null); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1164 */     public final Task getTask() { return this.task; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1174 */     public final Task.BlockingScope getScope() { return this.scope; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1187 */     public final Object getTarget() { return this.target; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1205 */     public final ApplicationAction getAction() { return this.action; }
/*      */     
/*      */     protected abstract void block();
/*      */     
/*      */     protected abstract void unblock();
/*      */   }
/*      */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\jdesktop\application\Task.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */