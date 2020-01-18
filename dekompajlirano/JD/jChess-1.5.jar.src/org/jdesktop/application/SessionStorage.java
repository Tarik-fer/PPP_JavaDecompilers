/*      */ package org.jdesktop.application;
/*      */ 
/*      */ import java.awt.Component;
/*      */ import java.awt.Container;
/*      */ import java.awt.Dialog;
/*      */ import java.awt.Frame;
/*      */ import java.awt.GraphicsConfiguration;
/*      */ import java.awt.GraphicsEnvironment;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.Window;
/*      */ import java.io.IOException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.logging.Logger;
/*      */ import javax.swing.JComponent;
/*      */ import javax.swing.JFrame;
/*      */ import javax.swing.JSplitPane;
/*      */ import javax.swing.JTabbedPane;
/*      */ import javax.swing.JTable;
/*      */ import javax.swing.table.TableColumn;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class SessionStorage
/*      */ {
/*  100 */   private static Logger logger = Logger.getLogger(SessionStorage.class.getName());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final Map<Class, Property> propertyMap;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final ApplicationContext context;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected SessionStorage(ApplicationContext paramApplicationContext) {
/*  149 */     if (paramApplicationContext == null) {
/*  150 */       throw new IllegalArgumentException("null context");
/*      */     }
/*  152 */     this.context = paramApplicationContext;
/*  153 */     this.propertyMap = (Map)new HashMap<Class<?>, Property>();
/*  154 */     this.propertyMap.put(Window.class, new WindowProperty());
/*  155 */     this.propertyMap.put(JTabbedPane.class, new TabbedPaneProperty());
/*  156 */     this.propertyMap.put(JSplitPane.class, new SplitPaneProperty());
/*  157 */     this.propertyMap.put(JTable.class, new TableProperty());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*  162 */   protected final ApplicationContext getContext() { return this.context; }
/*      */ 
/*      */   
/*      */   private void checkSaveRestoreArgs(Component paramComponent, String paramString) {
/*  166 */     if (paramComponent == null) {
/*  167 */       throw new IllegalArgumentException("null root");
/*      */     }
/*  169 */     if (paramString == null) {
/*  170 */       throw new IllegalArgumentException("null fileName");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  177 */   private String getComponentName(Component paramComponent) { return paramComponent.getName(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String getComponentPathname(Component paramComponent) {
/*  206 */     String str = getComponentName(paramComponent);
/*  207 */     if (str == null) {
/*  208 */       return null;
/*      */     }
/*  210 */     StringBuilder stringBuilder = new StringBuilder(str);
/*  211 */     while (paramComponent.getParent() != null && !(paramComponent instanceof Window) && !(paramComponent instanceof java.applet.Applet)) {
/*  212 */       paramComponent = paramComponent.getParent();
/*  213 */       str = getComponentName(paramComponent);
/*  214 */       if (str == null) {
/*  215 */         int i = paramComponent.getParent().getComponentZOrder(paramComponent);
/*  216 */         if (i >= 0) {
/*  217 */           Class<?> clazz = paramComponent.getClass();
/*  218 */           str = clazz.getSimpleName();
/*  219 */           if (str.length() == 0) {
/*  220 */             str = "Anonymous" + clazz.getSuperclass().getSimpleName();
/*      */           }
/*  222 */           str = str + i;
/*      */         
/*      */         }
/*      */         else {
/*      */           
/*  227 */           logger.warning("Couldn't compute pathname for " + paramComponent);
/*  228 */           return null;
/*      */         } 
/*      */       } 
/*  231 */       stringBuilder.append("/").append(str);
/*      */     } 
/*  233 */     return stringBuilder.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void saveTree(List<Component> paramList, Map<String, Object> paramMap) {
/*  244 */     ArrayList<? super Component> arrayList = new ArrayList();
/*  245 */     for (Component component : paramList) {
/*  246 */       if (component != null) {
/*  247 */         Property property = getProperty(component);
/*  248 */         if (property != null) {
/*  249 */           String str = getComponentPathname(component);
/*  250 */           if (str != null) {
/*  251 */             Object object = property.getSessionState(component);
/*  252 */             if (object != null) {
/*  253 */               paramMap.put(str, object);
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*  258 */       if (component instanceof Container) {
/*  259 */         Component[] arrayOfComponent = ((Container)component).getComponents();
/*  260 */         if (arrayOfComponent != null && arrayOfComponent.length > 0) {
/*  261 */           Collections.addAll(arrayList, arrayOfComponent);
/*      */         }
/*      */       } 
/*      */     } 
/*  265 */     if (arrayList.size() > 0) {
/*  266 */       saveTree((List)arrayList, paramMap);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void save(Component paramComponent, String paramString) throws IOException {
/*  318 */     checkSaveRestoreArgs(paramComponent, paramString);
/*  319 */     HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
/*  320 */     saveTree(Collections.singletonList(paramComponent), (Map)hashMap);
/*  321 */     LocalStorage localStorage = getContext().getLocalStorage();
/*  322 */     localStorage.save(hashMap, paramString);
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
/*      */   private void restoreTree(List<Component> paramList, Map<String, Object> paramMap) {
/*  334 */     ArrayList<? super Component> arrayList = new ArrayList();
/*  335 */     for (Component component : paramList) {
/*  336 */       if (component != null) {
/*  337 */         Property property = getProperty(component);
/*  338 */         if (property != null) {
/*  339 */           String str = getComponentPathname(component);
/*  340 */           if (str != null) {
/*  341 */             Object object = paramMap.get(str);
/*  342 */             if (object != null) {
/*  343 */               property.setSessionState(component, object);
/*      */             } else {
/*      */               
/*  346 */               logger.warning("No saved state for " + component);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*  351 */       if (component instanceof Container) {
/*  352 */         Component[] arrayOfComponent = ((Container)component).getComponents();
/*  353 */         if (arrayOfComponent != null && arrayOfComponent.length > 0) {
/*  354 */           Collections.addAll(arrayList, arrayOfComponent);
/*      */         }
/*      */       } 
/*      */     } 
/*  358 */     if (arrayList.size() > 0) {
/*  359 */       restoreTree((List)arrayList, paramMap);
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void restore(Component paramComponent, String paramString) throws IOException {
/*  380 */     checkSaveRestoreArgs(paramComponent, paramString);
/*  381 */     LocalStorage localStorage = getContext().getLocalStorage();
/*  382 */     Map<String, Object> map = (Map)localStorage.load(paramString);
/*  383 */     if (map != null) {
/*  384 */       restoreTree(Collections.singletonList(paramComponent), map);
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
/*      */   public static interface Property
/*      */   {
/*      */     Object getSessionState(Component param1Component);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void setSessionState(Component param1Component, Object param1Object);
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
/*      */   public static class WindowState
/*      */   {
/*      */     private final Rectangle bounds;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  444 */     private Rectangle gcBounds = null;
/*      */     private int screenCount;
/*  446 */     private int frameState = 0;
/*      */     
/*  448 */     public WindowState() { this.bounds = new Rectangle(); }
/*      */     
/*      */     public WindowState(Rectangle param1Rectangle1, Rectangle param1Rectangle2, int param1Int1, int param1Int2) {
/*  451 */       if (param1Rectangle1 == null) {
/*  452 */         throw new IllegalArgumentException("null bounds");
/*      */       }
/*  454 */       if (param1Int1 < 1) {
/*  455 */         throw new IllegalArgumentException("invalid screenCount");
/*      */       }
/*  457 */       this.bounds = param1Rectangle1;
/*  458 */       this.gcBounds = param1Rectangle2;
/*  459 */       this.screenCount = param1Int1;
/*  460 */       this.frameState = param1Int2;
/*      */     }
/*      */     
/*  463 */     public Rectangle getBounds() { return new Rectangle(this.bounds); }
/*      */ 
/*      */     
/*  466 */     public void setBounds(Rectangle param1Rectangle) { this.bounds.setBounds(param1Rectangle); }
/*      */ 
/*      */     
/*  469 */     public int getScreenCount() { return this.screenCount; }
/*      */ 
/*      */     
/*  472 */     public void setScreenCount(int param1Int) { this.screenCount = param1Int; }
/*      */ 
/*      */     
/*  475 */     public int getFrameState() { return this.frameState; }
/*      */ 
/*      */     
/*  478 */     public void setFrameState(int param1Int) { this.frameState = param1Int; }
/*      */ 
/*      */     
/*  481 */     public Rectangle getGraphicsConfigurationBounds() { return (this.gcBounds == null) ? null : new Rectangle(this.gcBounds); }
/*      */ 
/*      */     
/*  484 */     public void setGraphicsConfigurationBounds(Rectangle param1Rectangle) { this.gcBounds = (param1Rectangle == null) ? null : new Rectangle(param1Rectangle); }
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
/*      */   public static class WindowProperty
/*      */     implements Property
/*      */   {
/*      */     private void checkComponent(Component param1Component) {
/*  510 */       if (param1Component == null) {
/*  511 */         throw new IllegalArgumentException("null component");
/*      */       }
/*  513 */       if (!(param1Component instanceof Window)) {
/*  514 */         throw new IllegalArgumentException("invalid component");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*  519 */     private int getScreenCount() { return (GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()).length; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Object getSessionState(Component param1Component) {
/*  536 */       checkComponent(param1Component);
/*  537 */       int i = 0;
/*  538 */       if (param1Component instanceof Frame) {
/*  539 */         i = ((Frame)param1Component).getExtendedState();
/*      */       }
/*  541 */       GraphicsConfiguration graphicsConfiguration = param1Component.getGraphicsConfiguration();
/*  542 */       Rectangle rectangle1 = (graphicsConfiguration == null) ? null : graphicsConfiguration.getBounds();
/*  543 */       Rectangle rectangle2 = param1Component.getBounds();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  548 */       if (param1Component instanceof JFrame && 0 != (i & 0x6)) {
/*  549 */         String str = "WindowState.normalBounds";
/*  550 */         Object object = ((JFrame)param1Component).getRootPane().getClientProperty(str);
/*  551 */         if (object instanceof Rectangle) {
/*  552 */           rectangle2 = (Rectangle)object;
/*      */         }
/*      */       } 
/*  555 */       return new SessionStorage.WindowState(rectangle2, rectangle1, getScreenCount(), i);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setSessionState(Component param1Component, Object param1Object) {
/*  585 */       checkComponent(param1Component);
/*  586 */       if (param1Object != null && !(param1Object instanceof SessionStorage.WindowState)) {
/*  587 */         throw new IllegalArgumentException("invalid state");
/*      */       }
/*  589 */       Window window = (Window)param1Component;
/*  590 */       if (!window.isLocationByPlatform() && param1Object != null) {
/*  591 */         SessionStorage.WindowState windowState = (SessionStorage.WindowState)param1Object;
/*  592 */         Rectangle rectangle1 = windowState.getGraphicsConfigurationBounds();
/*  593 */         int i = windowState.getScreenCount();
/*  594 */         GraphicsConfiguration graphicsConfiguration = param1Component.getGraphicsConfiguration();
/*  595 */         Rectangle rectangle2 = (graphicsConfiguration == null) ? null : graphicsConfiguration.getBounds();
/*  596 */         int j = getScreenCount();
/*  597 */         if (rectangle1 != null && rectangle1.equals(rectangle2) && i == j) {
/*  598 */           boolean bool = true;
/*  599 */           if (window instanceof Frame) {
/*  600 */             bool = ((Frame)window).isResizable();
/*      */           }
/*  602 */           else if (window instanceof Dialog) {
/*  603 */             bool = ((Dialog)window).isResizable();
/*      */           } 
/*  605 */           if (bool) {
/*  606 */             window.setBounds(windowState.getBounds());
/*      */           }
/*      */         } 
/*  609 */         if (window instanceof Frame) {
/*  610 */           ((Frame)window).setExtendedState(windowState.getFrameState());
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class TabbedPaneState
/*      */   {
/*      */     private int selectedIndex;
/*      */ 
/*      */ 
/*      */     
/*      */     private int tabCount;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public TabbedPaneState() {
/*  631 */       this.selectedIndex = -1;
/*  632 */       this.tabCount = 0;
/*      */     }
/*      */     public TabbedPaneState(int param1Int1, int param1Int2) {
/*  635 */       if (param1Int2 < 0) {
/*  636 */         throw new IllegalArgumentException("invalid tabCount");
/*      */       }
/*  638 */       if (param1Int1 < -1 || param1Int1 > param1Int2) {
/*  639 */         throw new IllegalArgumentException("invalid selectedIndex");
/*      */       }
/*  641 */       this.selectedIndex = param1Int1;
/*  642 */       this.tabCount = param1Int2;
/*      */     }
/*  644 */     public int getSelectedIndex() { return this.selectedIndex; }
/*      */     public void setSelectedIndex(int param1Int) {
/*  646 */       if (param1Int < -1) {
/*  647 */         throw new IllegalArgumentException("invalid selectedIndex");
/*      */       }
/*  649 */       this.selectedIndex = param1Int;
/*      */     }
/*  651 */     public int getTabCount() { return this.tabCount; }
/*      */     public void setTabCount(int param1Int) {
/*  653 */       if (param1Int < 0) {
/*  654 */         throw new IllegalArgumentException("invalid tabCount");
/*      */       }
/*  656 */       this.tabCount = param1Int;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class TabbedPaneProperty
/*      */     implements Property
/*      */   {
/*      */     private void checkComponent(Component param1Component) {
/*  682 */       if (param1Component == null) {
/*  683 */         throw new IllegalArgumentException("null component");
/*      */       }
/*  685 */       if (!(param1Component instanceof JTabbedPane)) {
/*  686 */         throw new IllegalArgumentException("invalid component");
/*      */       }
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
/*      */     
/*      */     public Object getSessionState(Component param1Component) {
/*  704 */       checkComponent(param1Component);
/*  705 */       JTabbedPane jTabbedPane = (JTabbedPane)param1Component;
/*  706 */       return new SessionStorage.TabbedPaneState(jTabbedPane.getSelectedIndex(), jTabbedPane.getTabCount());
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
/*      */ 
/*      */     
/*      */     public void setSessionState(Component param1Component, Object param1Object) {
/*  724 */       checkComponent(param1Component);
/*  725 */       if (param1Object != null && !(param1Object instanceof SessionStorage.TabbedPaneState)) {
/*  726 */         throw new IllegalArgumentException("invalid state");
/*      */       }
/*  728 */       JTabbedPane jTabbedPane = (JTabbedPane)param1Component;
/*  729 */       SessionStorage.TabbedPaneState tabbedPaneState = (SessionStorage.TabbedPaneState)param1Object;
/*  730 */       if (jTabbedPane.getTabCount() == tabbedPaneState.getTabCount()) {
/*  731 */         jTabbedPane.setSelectedIndex(tabbedPaneState.getSelectedIndex());
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
/*      */   public static class SplitPaneState
/*      */   {
/*  749 */     private int dividerLocation = -1;
/*  750 */     private int orientation = 1;
/*      */     private void checkOrientation(int param1Int) {
/*  752 */       if (param1Int != 1 && param1Int != 0)
/*      */       {
/*  754 */         throw new IllegalArgumentException("invalid orientation"); } 
/*      */     }
/*      */     public SplitPaneState() {}
/*      */     
/*      */     public SplitPaneState(int param1Int1, int param1Int2) {
/*  759 */       checkOrientation(param1Int2);
/*  760 */       if (param1Int1 < -1) {
/*  761 */         throw new IllegalArgumentException("invalid dividerLocation");
/*      */       }
/*  763 */       this.dividerLocation = param1Int1;
/*  764 */       this.orientation = param1Int2;
/*      */     }
/*  766 */     public int getDividerLocation() { return this.dividerLocation; }
/*      */     public void setDividerLocation(int param1Int) {
/*  768 */       if (param1Int < -1) {
/*  769 */         throw new IllegalArgumentException("invalid dividerLocation");
/*      */       }
/*  771 */       this.dividerLocation = param1Int;
/*      */     }
/*  773 */     public int getOrientation() { return this.orientation; }
/*      */     public void setOrientation(int param1Int) {
/*  775 */       checkOrientation(param1Int);
/*  776 */       this.orientation = param1Int;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class SplitPaneProperty
/*      */     implements Property
/*      */   {
/*      */     private void checkComponent(Component param1Component) {
/*  802 */       if (param1Component == null) {
/*  803 */         throw new IllegalArgumentException("null component");
/*      */       }
/*  805 */       if (!(param1Component instanceof JSplitPane)) {
/*  806 */         throw new IllegalArgumentException("invalid component");
/*      */       }
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
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Object getSessionState(Component param1Component) {
/*  827 */       checkComponent(param1Component);
/*  828 */       JSplitPane jSplitPane = (JSplitPane)param1Component;
/*  829 */       return new SessionStorage.SplitPaneState(jSplitPane.getUI().getDividerLocation(jSplitPane), jSplitPane.getOrientation());
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
/*      */ 
/*      */     
/*      */     public void setSessionState(Component param1Component, Object param1Object) {
/*  847 */       checkComponent(param1Component);
/*  848 */       if (param1Object != null && !(param1Object instanceof SessionStorage.SplitPaneState)) {
/*  849 */         throw new IllegalArgumentException("invalid state");
/*      */       }
/*  851 */       JSplitPane jSplitPane = (JSplitPane)param1Component;
/*  852 */       SessionStorage.SplitPaneState splitPaneState = (SessionStorage.SplitPaneState)param1Object;
/*  853 */       if (jSplitPane.getOrientation() == splitPaneState.getOrientation()) {
/*  854 */         jSplitPane.setDividerLocation(splitPaneState.getDividerLocation());
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
/*      */   public static class TableState
/*      */   {
/*  869 */     private int[] columnWidths = new int[0];
/*      */     private int[] copyColumnWidths(int[] param1ArrayOfint) {
/*  871 */       if (param1ArrayOfint == null) {
/*  872 */         throw new IllegalArgumentException("invalid columnWidths");
/*      */       }
/*  874 */       int[] arrayOfInt = new int[param1ArrayOfint.length];
/*  875 */       System.arraycopy(param1ArrayOfint, 0, arrayOfInt, 0, param1ArrayOfint.length);
/*  876 */       return arrayOfInt;
/*      */     }
/*      */     public TableState() {}
/*      */     
/*  880 */     public TableState(int[] param1ArrayOfint) { this.columnWidths = copyColumnWidths(param1ArrayOfint); }
/*      */     
/*  882 */     public int[] getColumnWidths() { return copyColumnWidths(this.columnWidths); }
/*      */     
/*  884 */     public void setColumnWidths(int[] param1ArrayOfint) { this.columnWidths = copyColumnWidths(param1ArrayOfint); }
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
/*      */   public static class TableProperty
/*      */     implements Property
/*      */   {
/*      */     private void checkComponent(Component param1Component) {
/*  912 */       if (param1Component == null) {
/*  913 */         throw new IllegalArgumentException("null component");
/*      */       }
/*  915 */       if (!(param1Component instanceof JTable)) {
/*  916 */         throw new IllegalArgumentException("invalid component");
/*      */       }
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
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Object getSessionState(Component param1Component) {
/*  937 */       checkComponent(param1Component);
/*  938 */       JTable jTable = (JTable)param1Component;
/*  939 */       int[] arrayOfInt = new int[jTable.getColumnCount()];
/*  940 */       boolean bool = false;
/*  941 */       for (byte b = 0; b < arrayOfInt.length; b++) {
/*  942 */         TableColumn tableColumn = jTable.getColumnModel().getColumn(b);
/*  943 */         arrayOfInt[b] = tableColumn.getResizable() ? tableColumn.getWidth() : -1;
/*  944 */         if (tableColumn.getResizable()) {
/*  945 */           bool = true;
/*      */         }
/*      */       } 
/*  948 */       return bool ? new SessionStorage.TableState(arrayOfInt) : null;
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
/*      */     
/*      */     public void setSessionState(Component param1Component, Object param1Object) {
/*  965 */       checkComponent(param1Component);
/*  966 */       if (!(param1Object instanceof SessionStorage.TableState)) {
/*  967 */         throw new IllegalArgumentException("invalid state");
/*      */       }
/*  969 */       JTable jTable = (JTable)param1Component;
/*  970 */       int[] arrayOfInt = ((SessionStorage.TableState)param1Object).getColumnWidths();
/*  971 */       if (jTable.getColumnCount() == arrayOfInt.length) {
/*  972 */         for (byte b = 0; b < arrayOfInt.length; b++) {
/*  973 */           if (arrayOfInt[b] != -1) {
/*  974 */             TableColumn tableColumn = jTable.getColumnModel().getColumn(b);
/*  975 */             if (tableColumn.getResizable()) {
/*  976 */               tableColumn.setPreferredWidth(arrayOfInt[b]);
/*      */             }
/*      */           } 
/*      */         } 
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private void checkClassArg(Class paramClass) {
/*  985 */     if (paramClass == null) {
/*  986 */       throw new IllegalArgumentException("null class");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Property getProperty(Class paramClass) {
/* 1009 */     checkClassArg(paramClass);
/* 1010 */     while (paramClass != null) {
/* 1011 */       Property property = this.propertyMap.get(paramClass);
/* 1012 */       if (property != null) return property; 
/* 1013 */       paramClass = paramClass.getSuperclass();
/*      */     } 
/* 1015 */     return null;
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
/*      */   public void putProperty(Class paramClass, Property paramProperty) {
/* 1036 */     checkClassArg(paramClass);
/* 1037 */     this.propertyMap.put(paramClass, paramProperty);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final Property getProperty(Component paramComponent) {
/* 1073 */     if (paramComponent == null) {
/* 1074 */       throw new IllegalArgumentException("null component");
/*      */     }
/* 1076 */     if (paramComponent instanceof Property) {
/* 1077 */       return (Property)paramComponent;
/*      */     }
/*      */     
/* 1080 */     Property property = null;
/* 1081 */     if (paramComponent instanceof JComponent) {
/* 1082 */       Object object = ((JComponent)paramComponent).getClientProperty(Property.class);
/* 1083 */       property = (object instanceof Property) ? (Property)object : null;
/*      */     } 
/* 1085 */     return (property != null) ? property : getProperty(paramComponent.getClass());
/*      */   }
/*      */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\jdesktop\application\SessionStorage.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */