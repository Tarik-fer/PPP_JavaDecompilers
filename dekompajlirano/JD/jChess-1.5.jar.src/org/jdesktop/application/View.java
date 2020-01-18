/*     */ package org.jdesktop.application;
/*     */ 
/*     */ import java.awt.Container;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JMenuBar;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JRootPane;
/*     */ import javax.swing.JToolBar;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class View
/*     */   extends AbstractBean
/*     */ {
/*  60 */   private static final Logger logger = Logger.getLogger(View.class.getName());
/*     */   private final Application application;
/*  62 */   private ResourceMap resourceMap = null;
/*  63 */   private JRootPane rootPane = null;
/*  64 */   private JComponent component = null;
/*  65 */   private JMenuBar menuBar = null;
/*  66 */   private List<JToolBar> toolBars = Collections.emptyList();
/*  67 */   private JComponent toolBarsPanel = null;
/*  68 */   private JComponent statusBar = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public View(Application paramApplication) {
/*  78 */     if (paramApplication == null) {
/*  79 */       throw new IllegalArgumentException("null application");
/*     */     }
/*  81 */     this.application = paramApplication;
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
/*  93 */   public final Application getApplication() { return this.application; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 107 */   public final ApplicationContext getContext() { return getApplication().getContext(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceMap getResourceMap() {
/* 118 */     if (this.resourceMap == null) {
/* 119 */       this.resourceMap = getContext().getResourceMap(getClass(), View.class);
/*     */     }
/* 121 */     return this.resourceMap;
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
/*     */   public JRootPane getRootPane() {
/* 137 */     if (this.rootPane == null) {
/* 138 */       this.rootPane = new JRootPane();
/* 139 */       this.rootPane.setOpaque(true);
/*     */     } 
/* 141 */     return this.rootPane;
/*     */   }
/*     */   
/*     */   private void replaceContentPaneChild(JComponent paramJComponent1, JComponent paramJComponent2, String paramString) {
/* 145 */     Container container = getRootPane().getContentPane();
/* 146 */     if (paramJComponent1 != null) {
/* 147 */       container.remove(paramJComponent1);
/*     */     }
/* 149 */     if (paramJComponent2 != null) {
/* 150 */       container.add(paramJComponent2, paramString);
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
/* 161 */   public JComponent getComponent() { return this.component; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setComponent(JComponent paramJComponent) {
/* 176 */     JComponent jComponent = this.component;
/* 177 */     this.component = paramJComponent;
/* 178 */     replaceContentPaneChild(jComponent, this.component, "Center");
/* 179 */     firePropertyChange("component", jComponent, this.component);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 189 */   public JMenuBar getMenuBar() { return this.menuBar; }
/*     */ 
/*     */   
/*     */   public void setMenuBar(JMenuBar paramJMenuBar) {
/* 193 */     JMenuBar jMenuBar = getMenuBar();
/* 194 */     this.menuBar = paramJMenuBar;
/* 195 */     getRootPane().setJMenuBar(paramJMenuBar);
/* 196 */     firePropertyChange("menuBar", jMenuBar, paramJMenuBar);
/*     */   }
/*     */ 
/*     */   
/* 200 */   public List<JToolBar> getToolBars() { return this.toolBars; }
/*     */ 
/*     */   
/*     */   public void setToolBars(List<JToolBar> paramList) {
/* 204 */     if (paramList == null) {
/* 205 */       throw new IllegalArgumentException("null toolbars");
/*     */     }
/* 207 */     List<JToolBar> list = getToolBars();
/* 208 */     this.toolBars = Collections.unmodifiableList(new ArrayList<JToolBar>(paramList));
/* 209 */     JComponent jComponent1 = this.toolBarsPanel;
/* 210 */     JComponent jComponent2 = null;
/* 211 */     if (this.toolBars.size() == 1) {
/* 212 */       jComponent2 = paramList.get(0);
/*     */     }
/* 214 */     else if (this.toolBars.size() > 1) {
/* 215 */       jComponent2 = new JPanel();
/* 216 */       for (JToolBar jToolBar : this.toolBars) {
/* 217 */         jComponent2.add(jToolBar);
/*     */       }
/*     */     } 
/* 220 */     replaceContentPaneChild(jComponent1, jComponent2, "North");
/* 221 */     firePropertyChange("toolBars", list, this.toolBars);
/*     */   }
/*     */   
/*     */   public final JToolBar getToolBar() {
/* 225 */     List<JToolBar> list = getToolBars();
/* 226 */     return (list.size() == 0) ? null : list.get(0);
/*     */   }
/*     */   
/*     */   public final void setToolBar(JToolBar paramJToolBar) {
/* 230 */     JToolBar jToolBar = getToolBar();
/* 231 */     List<?> list = Collections.emptyList();
/* 232 */     if (paramJToolBar != null) {
/* 233 */       list = Collections.singletonList(paramJToolBar);
/*     */     }
/* 235 */     setToolBars((List)list);
/* 236 */     firePropertyChange("toolBar", jToolBar, paramJToolBar);
/*     */   }
/*     */ 
/*     */   
/* 240 */   public JComponent getStatusBar() { return this.statusBar; }
/*     */ 
/*     */   
/*     */   public void setStatusBar(JComponent paramJComponent) {
/* 244 */     JComponent jComponent = this.statusBar;
/* 245 */     this.statusBar = paramJComponent;
/* 246 */     replaceContentPaneChild(jComponent, this.statusBar, "South");
/* 247 */     firePropertyChange("statusBar", jComponent, this.statusBar);
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\jdesktop\application\View.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */