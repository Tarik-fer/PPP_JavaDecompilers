/*     */ package org.jdesktop.application;
/*     */ 
/*     */ import java.awt.Container;
/*     */ import java.awt.Frame;
/*     */ import java.awt.Window;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.ComponentListener;
/*     */ import java.awt.event.HierarchyEvent;
/*     */ import java.awt.event.HierarchyListener;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JRootPane;
/*     */ import javax.swing.RootPaneContainer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class SingleFrameApplication
/*     */   extends Application
/*     */ {
/*  96 */   private static final Logger logger = Logger.getLogger(SingleFrameApplication.class.getName());
/*  97 */   private ResourceMap appResources = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 125 */   public final JFrame getMainFrame() { return getMainView().getFrame(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 149 */   protected final void setMainFrame(JFrame paramJFrame) { getMainView().setFrame(paramJFrame); }
/*     */ 
/*     */   
/*     */   private String sessionFilename(Window paramWindow) {
/* 153 */     if (paramWindow == null) {
/* 154 */       return null;
/*     */     }
/*     */     
/* 157 */     String str = paramWindow.getName();
/* 158 */     return (str == null) ? null : (str + ".session.xml");
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
/*     */   
/* 182 */   protected void configureWindow(Window paramWindow) { getContext().getResourceMap().injectComponents(paramWindow); }
/*     */ 
/*     */   
/*     */   private void initRootPaneContainer(RootPaneContainer paramRootPaneContainer) {
/* 186 */     JRootPane jRootPane = paramRootPaneContainer.getRootPane();
/*     */     
/* 188 */     String str = "SingleFrameApplication.initRootPaneContainer";
/* 189 */     if (jRootPane.getClientProperty(str) != null) {
/*     */       return;
/*     */     }
/* 192 */     jRootPane.putClientProperty(str, Boolean.TRUE);
/*     */     
/* 194 */     Container container = jRootPane.getParent();
/* 195 */     if (container instanceof Window) {
/* 196 */       configureWindow((Window)container);
/*     */     }
/*     */     
/* 199 */     JFrame jFrame = getMainFrame();
/* 200 */     if (paramRootPaneContainer == jFrame) {
/* 201 */       jFrame.addWindowListener(new MainFrameListener());
/* 202 */       jFrame.setDefaultCloseOperation(0);
/*     */     }
/* 204 */     else if (container instanceof Window) {
/* 205 */       Window window = (Window)container;
/* 206 */       window.addHierarchyListener(new SecondaryWindowListener());
/*     */     } 
/*     */     
/* 209 */     if (container instanceof JFrame) {
/* 210 */       container.addComponentListener(new FrameBoundsListener());
/*     */     }
/*     */     
/* 213 */     if (container instanceof Window) {
/* 214 */       Window window = (Window)container;
/* 215 */       if (!container.isValid() || container.getWidth() == 0 || container.getHeight() == 0) {
/* 216 */         window.pack();
/*     */       }
/* 218 */       if (!window.isLocationByPlatform() && container.getX() == 0 && container.getY() == 0) {
/* 219 */         Window window1 = window.getOwner();
/* 220 */         if (window1 == null) {
/* 221 */           window1 = (window != jFrame) ? jFrame : null;
/*     */         }
/* 223 */         window.setLocationRelativeTo(window1);
/*     */       } 
/*     */     } 
/*     */     
/* 227 */     if (container instanceof Window) {
/* 228 */       String str1 = sessionFilename((Window)container);
/* 229 */       if (str1 != null) {
/*     */         try {
/* 231 */           getContext().getSessionStorage().restore(container, str1);
/*     */         }
/* 233 */         catch (Exception exception) {
/* 234 */           String str2 = String.format("couldn't restore sesssion [%s]", new Object[] { str1 });
/* 235 */           logger.log(Level.WARNING, str2, exception);
/*     */         } 
/*     */       }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void show(JComponent paramJComponent) {
/* 264 */     if (paramJComponent == null) {
/* 265 */       throw new IllegalArgumentException("null JComponent");
/*     */     }
/* 267 */     JFrame jFrame = getMainFrame();
/* 268 */     jFrame.getContentPane().add(paramJComponent, "Center");
/* 269 */     initRootPaneContainer(jFrame);
/* 270 */     jFrame.setVisible(true);
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
/*     */   public void show(JDialog paramJDialog) {
/* 292 */     if (paramJDialog == null) {
/* 293 */       throw new IllegalArgumentException("null JDialog");
/*     */     }
/* 295 */     initRootPaneContainer(paramJDialog);
/* 296 */     paramJDialog.setVisible(true);
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
/*     */   public void show(JFrame paramJFrame) {
/* 317 */     if (paramJFrame == null) {
/* 318 */       throw new IllegalArgumentException("null JFrame");
/*     */     }
/* 320 */     initRootPaneContainer(paramJFrame);
/* 321 */     paramJFrame.setVisible(true);
/*     */   }
/*     */   
/*     */   private void saveSession(Window paramWindow) {
/* 325 */     String str = sessionFilename(paramWindow);
/* 326 */     if (str != null) {
/*     */       try {
/* 328 */         getContext().getSessionStorage().save(paramWindow, str);
/*     */       }
/* 330 */       catch (IOException iOException) {
/* 331 */         logger.log(Level.WARNING, "couldn't save sesssion", iOException);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/* 337 */   private boolean isVisibleWindow(Window paramWindow) { return (paramWindow.isVisible() && (paramWindow instanceof JFrame || paramWindow instanceof JDialog || paramWindow instanceof javax.swing.JWindow)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<Window> getVisibleSecondaryWindows() {
/* 347 */     ArrayList<Window> arrayList = new ArrayList();
/* 348 */     Method method = null;
/*     */     try {
/* 350 */       method = Window.class.getMethod("getWindows", new Class[0]);
/*     */     }
/* 352 */     catch (Exception exception) {}
/*     */     
/* 354 */     if (method != null) {
/* 355 */       Window[] arrayOfWindow = null;
/*     */       try {
/* 357 */         arrayOfWindow = (Window[])method.invoke(null, new Object[0]);
/*     */       }
/* 359 */       catch (Exception exception) {
/* 360 */         throw new Error("HCTB - can't get top level windows list", exception);
/*     */       } 
/* 362 */       if (arrayOfWindow != null) {
/* 363 */         for (Window window : arrayOfWindow) {
/* 364 */           if (isVisibleWindow(window)) {
/* 365 */             arrayList.add(window);
/*     */           }
/*     */         } 
/*     */       }
/*     */     } else {
/*     */       
/* 371 */       Frame[] arrayOfFrame = Frame.getFrames();
/* 372 */       if (arrayOfFrame != null) {
/* 373 */         for (Frame frame : arrayOfFrame) {
/* 374 */           if (isVisibleWindow(frame)) {
/* 375 */             arrayList.add(frame);
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/* 380 */     return arrayList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void shutdown() {
/* 389 */     saveSession(getMainFrame());
/* 390 */     for (Window window : getVisibleSecondaryWindows())
/* 391 */       saveSession(window); 
/*     */   }
/*     */   
/*     */   private class MainFrameListener extends WindowAdapter {
/*     */     private MainFrameListener() {}
/*     */     
/* 397 */     public void windowClosing(WindowEvent param1WindowEvent) { SingleFrameApplication.this.exit(param1WindowEvent); }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private class SecondaryWindowListener
/*     */     implements HierarchyListener
/*     */   {
/*     */     private SecondaryWindowListener() {}
/*     */ 
/*     */ 
/*     */     
/*     */     public void hierarchyChanged(HierarchyEvent param1HierarchyEvent) {
/* 411 */       if ((param1HierarchyEvent.getChangeFlags() & 0x4L) != 0L && 
/* 412 */         param1HierarchyEvent.getSource() instanceof Window) {
/* 413 */         Window window = (Window)param1HierarchyEvent.getSource();
/* 414 */         if (!window.isShowing()) {
/* 415 */           SingleFrameApplication.this.saveSession(window);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static class FrameBoundsListener
/*     */     implements ComponentListener
/*     */   {
/*     */     private FrameBoundsListener() {}
/*     */ 
/*     */     
/*     */     private void maybeSaveFrameSize(ComponentEvent param1ComponentEvent) {
/* 429 */       if (param1ComponentEvent.getComponent() instanceof JFrame) {
/* 430 */         JFrame jFrame = (JFrame)param1ComponentEvent.getComponent();
/* 431 */         if ((jFrame.getExtendedState() & 0x6) == 0) {
/* 432 */           String str = "WindowState.normalBounds";
/* 433 */           jFrame.getRootPane().putClientProperty(str, jFrame.getBounds());
/*     */         } 
/*     */       } 
/*     */     }
/* 437 */     public void componentResized(ComponentEvent param1ComponentEvent) { maybeSaveFrameSize(param1ComponentEvent); }
/*     */ 
/*     */     
/*     */     public void componentMoved(ComponentEvent param1ComponentEvent) {}
/*     */ 
/*     */     
/*     */     public void componentHidden(ComponentEvent param1ComponentEvent) {}
/*     */ 
/*     */     
/*     */     public void componentShown(ComponentEvent param1ComponentEvent) {}
/*     */   }
/*     */   
/* 449 */   private FrameView mainView = null;
/*     */   
/*     */   public FrameView getMainView() {
/* 452 */     if (this.mainView == null) {
/* 453 */       this.mainView = new FrameView(this);
/*     */     }
/* 455 */     return this.mainView;
/*     */   }
/*     */   
/*     */   public void show(View paramView) {
/* 459 */     if (this.mainView == null && paramView instanceof FrameView) {
/* 460 */       this.mainView = (FrameView)paramView;
/*     */     }
/* 462 */     RootPaneContainer rootPaneContainer = (RootPaneContainer)paramView.getRootPane().getParent();
/* 463 */     initRootPaneContainer(rootPaneContainer);
/* 464 */     ((Window)rootPaneContainer).setVisible(true);
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\jdesktop\application\SingleFrameApplication.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */