/*     */ package org.jdesktop.application;
/*     */ 
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.datatransfer.Clipboard;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.CopyOnWriteArrayList;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.JComponent;
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
/*     */ public class ApplicationContext
/*     */   extends AbstractBean
/*     */ {
/*  48 */   private static final Logger logger = Logger.getLogger(ApplicationContext.class.getName());
/*     */   private final List<TaskService> taskServices;
/*     */   private final List<TaskService> taskServicesReadOnly;
/*     */   private ResourceManager resourceManager;
/*     */   private ActionManager actionManager;
/*     */   private LocalStorage localStorage;
/*     */   private SessionStorage sessionStorage;
/*  55 */   private Application application = null;
/*  56 */   private Class applicationClass = null;
/*  57 */   private JComponent focusOwner = null;
/*  58 */   private Clipboard clipboard = null;
/*  59 */   private Throwable uncaughtException = null;
/*  60 */   private TaskMonitor taskMonitor = null;
/*     */ 
/*     */   
/*     */   protected ApplicationContext() {
/*  64 */     this.resourceManager = new ResourceManager(this);
/*  65 */     this.actionManager = new ActionManager(this);
/*  66 */     this.localStorage = new LocalStorage(this);
/*  67 */     this.sessionStorage = new SessionStorage(this);
/*  68 */     this.taskServices = new CopyOnWriteArrayList<TaskService>();
/*  69 */     this.taskServices.add(new TaskService("default"));
/*  70 */     this.taskServicesReadOnly = Collections.unmodifiableList(this.taskServices);
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
/*  84 */   public final synchronized Class getApplicationClass() { return this.applicationClass; }
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
/*     */   public final synchronized void setApplicationClass(Class paramClass) {
/*  99 */     if (this.application != null) {
/* 100 */       throw new IllegalStateException("application has been launched");
/*     */     }
/* 102 */     this.applicationClass = paramClass;
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
/* 113 */   public final synchronized Application getApplication() { return this.application; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   synchronized void setApplication(Application paramApplication) {
/* 119 */     if (this.application != null) {
/* 120 */       throw new IllegalStateException("application has already been launched");
/*     */     }
/* 122 */     this.application = paramApplication;
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
/* 134 */   public final ResourceManager getResourceManager() { return this.resourceManager; }
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
/*     */   protected void setResourceManager(ResourceManager paramResourceManager) {
/* 150 */     if (paramResourceManager == null) {
/* 151 */       throw new IllegalArgumentException("null resourceManager");
/*     */     }
/* 153 */     ResourceManager resourceManager1 = this.resourceManager;
/* 154 */     this.resourceManager = paramResourceManager;
/* 155 */     firePropertyChange("resourceManager", resourceManager1, this.resourceManager);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 178 */   public final ResourceMap getResourceMap(Class paramClass) { return getResourceManager().getResourceMap(paramClass, paramClass); }
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
/* 203 */   public final ResourceMap getResourceMap(Class paramClass1, Class paramClass2) { return getResourceManager().getResourceMap(paramClass1, paramClass2); }
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
/* 224 */   public final ResourceMap getResourceMap() { return getResourceManager().getResourceMap(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 233 */   public final ActionManager getActionManager() { return this.actionManager; }
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
/*     */   protected void setActionManager(ActionManager paramActionManager) {
/* 249 */     if (paramActionManager == null) {
/* 250 */       throw new IllegalArgumentException("null actionManager");
/*     */     }
/* 252 */     ActionManager actionManager1 = this.actionManager;
/* 253 */     this.actionManager = paramActionManager;
/* 254 */     firePropertyChange("actionManager", actionManager1, this.actionManager);
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
/*     */ 
/*     */   
/* 272 */   public final ApplicationActionMap getActionMap() { return getActionManager().getActionMap(); }
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
/* 290 */   public final ApplicationActionMap getActionMap(Class paramClass, Object paramObject) { return getActionManager().getActionMap(paramClass, paramObject); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final ApplicationActionMap getActionMap(Object paramObject) {
/* 300 */     if (paramObject == null) {
/* 301 */       throw new IllegalArgumentException("null actionsObject");
/*     */     }
/* 303 */     return getActionManager().getActionMap(paramObject.getClass(), paramObject);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 312 */   public final LocalStorage getLocalStorage() { return this.localStorage; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setLocalStorage(LocalStorage paramLocalStorage) {
/* 321 */     if (paramLocalStorage == null) {
/* 322 */       throw new IllegalArgumentException("null localStorage");
/*     */     }
/* 324 */     LocalStorage localStorage1 = this.localStorage;
/* 325 */     this.localStorage = paramLocalStorage;
/* 326 */     firePropertyChange("localStorage", localStorage1, this.localStorage);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 336 */   public final SessionStorage getSessionStorage() { return this.sessionStorage; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setSessionStorage(SessionStorage paramSessionStorage) {
/* 345 */     if (paramSessionStorage == null) {
/* 346 */       throw new IllegalArgumentException("null sessionStorage");
/*     */     }
/* 348 */     SessionStorage sessionStorage1 = this.sessionStorage;
/* 349 */     this.sessionStorage = paramSessionStorage;
/* 350 */     firePropertyChange("sessionStorage", sessionStorage1, this.sessionStorage);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Clipboard getClipboard() {
/* 357 */     if (this.clipboard == null) {
/*     */       try {
/* 359 */         this.clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
/*     */       }
/* 361 */       catch (SecurityException securityException) {
/* 362 */         this.clipboard = new Clipboard("sandbox");
/*     */       } 
/*     */     }
/* 365 */     return this.clipboard;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 371 */   public JComponent getFocusOwner() { return this.focusOwner; }
/*     */   
/*     */   void setFocusOwner(JComponent paramJComponent) {
/* 374 */     JComponent jComponent = this.focusOwner;
/* 375 */     this.focusOwner = paramJComponent;
/* 376 */     firePropertyChange("focusOwner", jComponent, this.focusOwner);
/*     */   }
/*     */ 
/*     */   
/* 380 */   private List<TaskService> copyTaskServices() { return new ArrayList<TaskService>(this.taskServices); }
/*     */ 
/*     */   
/*     */   public void addTaskService(TaskService paramTaskService) {
/* 384 */     if (paramTaskService == null) {
/* 385 */       throw new IllegalArgumentException("null taskService");
/*     */     }
/* 387 */     List<TaskService> list1 = null, list2 = null;
/* 388 */     boolean bool = false;
/* 389 */     synchronized (this.taskServices) {
/* 390 */       if (!this.taskServices.contains(paramTaskService)) {
/* 391 */         list1 = copyTaskServices();
/* 392 */         this.taskServices.add(paramTaskService);
/* 393 */         list2 = copyTaskServices();
/* 394 */         bool = true;
/*     */       } 
/*     */     } 
/* 397 */     if (bool) {
/* 398 */       firePropertyChange("taskServices", list1, list2);
/*     */     }
/*     */   }
/*     */   
/*     */   public void removeTaskService(TaskService paramTaskService) {
/* 403 */     if (paramTaskService == null) {
/* 404 */       throw new IllegalArgumentException("null taskService");
/*     */     }
/* 406 */     List<TaskService> list1 = null, list2 = null;
/* 407 */     boolean bool = false;
/* 408 */     synchronized (this.taskServices) {
/* 409 */       if (this.taskServices.contains(paramTaskService)) {
/* 410 */         list1 = copyTaskServices();
/* 411 */         this.taskServices.remove(paramTaskService);
/* 412 */         list2 = copyTaskServices();
/* 413 */         bool = true;
/*     */       } 
/*     */     } 
/* 416 */     if (bool) {
/* 417 */       firePropertyChange("taskServices", list1, list2);
/*     */     }
/*     */   }
/*     */   
/*     */   public TaskService getTaskService(String paramString) {
/* 422 */     if (paramString == null) {
/* 423 */       throw new IllegalArgumentException("null name");
/*     */     }
/* 425 */     for (TaskService taskService : this.taskServices) {
/* 426 */       if (paramString.equals(taskService.getName())) {
/* 427 */         return taskService;
/*     */       }
/*     */     } 
/* 430 */     return null;
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
/*     */ 
/*     */   
/* 448 */   public final TaskService getTaskService() { return getTaskService("default"); }
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
/* 459 */   public List<TaskService> getTaskServices() { return this.taskServicesReadOnly; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final TaskMonitor getTaskMonitor() {
/* 470 */     if (this.taskMonitor == null) {
/* 471 */       this.taskMonitor = new TaskMonitor(this);
/*     */     }
/* 473 */     return this.taskMonitor;
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\jdesktop\application\ApplicationContext.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */