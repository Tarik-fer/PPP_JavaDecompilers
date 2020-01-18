/*     */ package org.jdesktop.application;
/*     */ 
/*     */ import java.awt.ActiveEvent;
/*     */ import java.awt.Component;
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.Window;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.PaintEvent;
/*     */ import java.beans.Beans;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.util.EventListener;
/*     */ import java.util.EventObject;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.CopyOnWriteArrayList;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.UIManager;
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
/*     */ @ProxyActions({"cut", "copy", "paste", "delete"})
/*     */ public abstract class Application
/*     */   extends AbstractBean
/*     */ {
/* 127 */   private static final Logger logger = Logger.getLogger(Application.class.getName());
/* 128 */   private static Application application = null;
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
/* 142 */   private final List<ExitListener> exitListeners = new CopyOnWriteArrayList<ExitListener>();
/* 143 */   private final ApplicationContext context = new ApplicationContext();
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
/*     */   public static synchronized <T extends Application> void launch(final Class<T> applicationClass, final String[] args) {
/* 166 */     Runnable runnable = new Runnable() {
/*     */         public void run() {
/*     */           try {
/* 169 */             application = Application.create(applicationClass);
/* 170 */             application.initialize(args);
/* 171 */             application.startup();
/* 172 */             application.waitForReady();
/*     */           }
/* 174 */           catch (Exception exception) {
/* 175 */             String str = String.format("Application %s failed to launch", new Object[] { this.val$applicationClass });
/* 176 */             logger.log(Level.SEVERE, str, exception);
/* 177 */             throw new Error(str, exception);
/*     */           } 
/*     */         }
/*     */       };
/* 181 */     SwingUtilities.invokeLater(runnable);
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
/*     */   static <T extends Application> T create(Class<T> paramClass) throws Exception {
/* 195 */     if (!Beans.isDesignTime()) {
/*     */       
/*     */       try {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 202 */         System.setProperty("java.net.useSystemProxies", "true");
/*     */       }
/* 204 */       catch (SecurityException securityException) {}
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 214 */     Constructor<T> constructor = paramClass.getDeclaredConstructor(new Class[0]);
/* 215 */     if (!constructor.isAccessible()) {
/*     */       try {
/* 217 */         constructor.setAccessible(true);
/*     */       }
/* 219 */       catch (SecurityException securityException) {}
/*     */     }
/*     */ 
/*     */     
/* 223 */     Application application1 = (Application)constructor.newInstance(new Object[0]);
/*     */ 
/*     */ 
/*     */     
/* 227 */     ApplicationContext applicationContext = application1.getContext();
/* 228 */     applicationContext.setApplicationClass(paramClass);
/* 229 */     applicationContext.setApplication(application1);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 234 */     ResourceMap resourceMap = applicationContext.getResourceMap();
/*     */     
/* 236 */     resourceMap.putResource("platform", platform());
/*     */     
/* 238 */     if (!Beans.isDesignTime()) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 243 */       String str1 = "Application.lookAndFeel";
/* 244 */       String str2 = resourceMap.getString(str1, new Object[0]);
/* 245 */       String str3 = (str2 == null) ? "system" : str2;
/*     */       try {
/* 247 */         if (str3.equalsIgnoreCase("system")) {
/* 248 */           String str = UIManager.getSystemLookAndFeelClassName();
/* 249 */           UIManager.setLookAndFeel(str);
/*     */         }
/* 251 */         else if (!str3.equalsIgnoreCase("default")) {
/* 252 */           UIManager.setLookAndFeel(str3);
/*     */         }
/*     */       
/* 255 */       } catch (Exception exception) {
/* 256 */         String str = "Couldn't set LookandFeel " + str1 + " = \"" + str2 + "\"";
/* 257 */         logger.log(Level.WARNING, str, exception);
/*     */       } 
/*     */     } 
/*     */     
/* 261 */     return (T)application1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String platform() {
/* 268 */     String str = "default";
/*     */     try {
/* 270 */       String str1 = System.getProperty("os.name");
/* 271 */       if (str1 != null && str1.toLowerCase().startsWith("mac os x")) {
/* 272 */         str = "osx";
/*     */       }
/*     */     }
/* 275 */     catch (SecurityException securityException) {}
/*     */     
/* 277 */     return str;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 283 */   void waitForReady() { (new DoWaitForEmptyEventQ()).execute(); }
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
/*     */   protected void initialize(String[] paramArrayOfString) {}
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
/*     */   protected void ready() {}
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
/*     */   protected void shutdown() {}
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
/*     */   private static class NotifyingEvent
/*     */     extends PaintEvent
/*     */     implements ActiveEvent
/*     */   {
/*     */     private boolean dispatched = false;
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
/*     */     private boolean qEmpty = false;
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
/* 363 */     NotifyingEvent(Component param1Component) { super(param1Component, 801, null); }
/*     */     
/* 365 */     synchronized boolean isDispatched() { return this.dispatched; }
/* 366 */     synchronized boolean isEventQEmpty() { return this.qEmpty; }
/*     */     public void dispatch() {
/* 368 */       EventQueue eventQueue = Toolkit.getDefaultToolkit().getSystemEventQueue();
/* 369 */       synchronized (this) {
/* 370 */         this.qEmpty = (eventQueue.peekEvent() == null);
/* 371 */         this.dispatched = true;
/* 372 */         notifyAll();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void waitForEmptyEventQ() {
/* 381 */     boolean bool = false;
/* 382 */     JPanel jPanel = new JPanel();
/* 383 */     EventQueue eventQueue = Toolkit.getDefaultToolkit().getSystemEventQueue();
/* 384 */     while (!bool) {
/* 385 */       NotifyingEvent notifyingEvent = new NotifyingEvent(jPanel);
/* 386 */       eventQueue.postEvent(notifyingEvent);
/* 387 */       synchronized (notifyingEvent) {
/* 388 */         while (!notifyingEvent.isDispatched()) {
/*     */           try {
/* 390 */             notifyingEvent.wait();
/*     */           }
/* 392 */           catch (InterruptedException interruptedException) {}
/*     */         } 
/*     */         
/* 395 */         bool = notifyingEvent.isEventQEmpty();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private class DoWaitForEmptyEventQ
/*     */     extends Task<Void, Void>
/*     */   {
/*     */     DoWaitForEmptyEventQ() {
/* 404 */       super(Application.this);
/*     */     } protected Void doInBackground() {
/* 406 */       Application.this.waitForEmptyEventQ();
/* 407 */       return null;
/*     */     }
/*     */     
/* 410 */     protected void finished() { Application.this.ready(); }
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
/* 423 */   public final void exit() { exit(null); }
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
/*     */   public void exit(EventObject paramEventObject) {
/* 463 */     for (ExitListener exitListener : this.exitListeners) {
/* 464 */       if (!exitListener.canExit(paramEventObject)) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */     try {
/* 469 */       for (ExitListener exitListener : this.exitListeners) {
/*     */         try {
/* 471 */           exitListener.willExit(paramEventObject);
/*     */         }
/* 473 */         catch (Exception exception) {
/* 474 */           logger.log(Level.WARNING, "ExitListener.willExit() failed", exception);
/*     */         } 
/*     */       } 
/* 477 */       shutdown();
/*     */     }
/* 479 */     catch (Exception exception) {
/* 480 */       logger.log(Level.WARNING, "unexpected error in Application.shutdown()", exception);
/*     */     } finally {
/*     */       
/* 483 */       end();
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
/* 494 */   protected void end() { Runtime.getRuntime().exit(0); }
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
/* 531 */   public void addExitListener(ExitListener paramExitListener) { this.exitListeners.add(paramExitListener); }
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
/* 542 */   public void removeExitListener(ExitListener paramExitListener) { this.exitListeners.remove(paramExitListener); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExitListener[] getExitListeners() {
/* 551 */     int i = this.exitListeners.size();
/* 552 */     return this.exitListeners.toArray(new ExitListener[i]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Action
/* 563 */   public void quit(ActionEvent paramActionEvent) { exit(paramActionEvent); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 572 */   public final ApplicationContext getContext() { return this.context; }
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
/*     */   public static synchronized <T extends Application> T getInstance(Class<T> paramClass) {
/* 591 */     if (application == null) {
/*     */       
/*     */       try {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 598 */         application = create((Class)paramClass);
/*     */       }
/* 600 */       catch (Exception exception) {
/* 601 */         String str = String.format("Couldn't construct %s", new Object[] { paramClass });
/* 602 */         throw new Error(str, exception);
/*     */       } 
/*     */     }
/* 605 */     return paramClass.cast(application);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static interface ExitListener
/*     */     extends EventListener
/*     */   {
/*     */     boolean canExit(EventObject param1EventObject);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void willExit(EventObject param1EventObject);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized Application getInstance() {
/* 630 */     if (application == null) {
/* 631 */       application = new NoApplication();
/*     */     }
/* 633 */     return application;
/*     */   }
/*     */   
/*     */   private static class NoApplication extends Application {
/*     */     protected NoApplication() {
/* 638 */       ApplicationContext applicationContext = getContext();
/* 639 */       applicationContext.setApplicationClass(getClass());
/* 640 */       applicationContext.setApplication(this);
/* 641 */       ResourceMap resourceMap = applicationContext.getResourceMap();
/* 642 */       resourceMap.putResource("platform", Application.platform());
/*     */     }
/*     */ 
/*     */     
/*     */     protected void startup() {}
/*     */   }
/*     */ 
/*     */   
/*     */   public void show(View paramView) {
/* 651 */     Window window = (Window)paramView.getRootPane().getParent();
/* 652 */     if (window != null) {
/* 653 */       window.pack();
/* 654 */       window.setVisible(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 659 */   public void hide(View paramView) { paramView.getRootPane().getParent().setVisible(false); }
/*     */   
/*     */   protected abstract void startup();
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\jdesktop\application\Application.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */