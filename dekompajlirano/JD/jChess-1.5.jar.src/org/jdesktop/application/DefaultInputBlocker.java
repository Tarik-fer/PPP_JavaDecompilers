/*     */ package org.jdesktop.application;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Font;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.Action;
/*     */ import javax.swing.InputVerifier;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JMenuBar;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JProgressBar;
/*     */ import javax.swing.JTextArea;
/*     */ import javax.swing.RootPaneContainer;
/*     */ import javax.swing.Timer;
/*     */ import javax.swing.event.MouseInputAdapter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class DefaultInputBlocker
/*     */   extends Task.InputBlocker
/*     */ {
/*  43 */   private static final Logger logger = Logger.getLogger(DefaultInputBlocker.class.getName());
/*  44 */   private JDialog modalDialog = null;
/*     */ 
/*     */   
/*  47 */   DefaultInputBlocker(Task paramTask, Task.BlockingScope paramBlockingScope, Object paramObject, ApplicationAction paramApplicationAction) { super(paramTask, paramBlockingScope, paramObject, paramApplicationAction); }
/*     */ 
/*     */   
/*     */   private void setActionTargetBlocked(boolean paramBoolean) {
/*  51 */     Action action = (Action)getTarget();
/*  52 */     action.setEnabled(!paramBoolean);
/*     */   }
/*     */   
/*     */   private void setComponentTargetBlocked(boolean paramBoolean) {
/*  56 */     Component component = (Component)getTarget();
/*  57 */     component.setEnabled(!paramBoolean);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void blockingDialogComponents(Component paramComponent, List<Component> paramList) {
/*  65 */     String str = paramComponent.getName();
/*  66 */     if (str != null && str.startsWith("BlockingDialog")) {
/*  67 */       paramList.add(paramComponent);
/*     */     }
/*  69 */     if (paramComponent instanceof Container)
/*  70 */       for (Component component : ((Container)paramComponent).getComponents()) {
/*  71 */         blockingDialogComponents(component, paramList);
/*     */       } 
/*     */   }
/*     */   
/*     */   private List<Component> blockingDialogComponents(Component paramComponent) {
/*  76 */     ArrayList<Component> arrayList = new ArrayList();
/*  77 */     blockingDialogComponents(paramComponent, arrayList);
/*  78 */     return arrayList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void injectBlockingDialogComponents(Component paramComponent) {
/*  86 */     ResourceMap resourceMap = getTask().getResourceMap();
/*  87 */     if (resourceMap != null) {
/*  88 */       resourceMap.injectComponents(paramComponent);
/*     */     }
/*  90 */     ApplicationAction applicationAction = getAction();
/*  91 */     if (applicationAction != null) {
/*  92 */       ResourceMap resourceMap1 = applicationAction.getResourceMap();
/*  93 */       String str = applicationAction.getName();
/*  94 */       for (Component component : blockingDialogComponents(paramComponent)) {
/*  95 */         component.setName(str + "." + component.getName());
/*     */       }
/*  97 */       resourceMap1.injectComponents(paramComponent);
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
/*     */   private JDialog createBlockingDialog() {
/* 121 */     JOptionPane jOptionPane = new JOptionPane();
/*     */ 
/*     */ 
/*     */     
/* 125 */     if (getTask().getUserCanCancel()) {
/* 126 */       JButton jButton = new JButton();
/* 127 */       jButton.setName("BlockingDialog.cancelButton");
/* 128 */       ActionListener actionListener = new ActionListener() {
/*     */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 130 */             DefaultInputBlocker.this.getTask().cancel(true);
/*     */           }
/*     */         };
/* 133 */       jButton.addActionListener(actionListener);
/* 134 */       jOptionPane.setOptions(new Object[] { jButton });
/*     */     } else {
/*     */       
/* 137 */       jOptionPane.setOptions(new Object[0]);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 142 */     Component component = (Component)getTarget();
/* 143 */     String str1 = getTask().getTitle();
/* 144 */     String str2 = (str1 == null) ? "BlockingDialog" : str1;
/* 145 */     final JDialog dialog = jOptionPane.createDialog(component, str2);
/* 146 */     jDialog.setModal(true);
/* 147 */     jDialog.setName("BlockingDialog");
/* 148 */     jDialog.setDefaultCloseOperation(0);
/* 149 */     WindowAdapter windowAdapter = new WindowAdapter() {
/*     */         public void windowClosing(WindowEvent param1WindowEvent) {
/* 151 */           if (DefaultInputBlocker.this.getTask().getUserCanCancel()) {
/* 152 */             DefaultInputBlocker.this.getTask().cancel(true);
/* 153 */             dialog.setVisible(false);
/*     */           } 
/*     */         }
/*     */       };
/* 157 */     jDialog.addWindowListener(windowAdapter);
/* 158 */     jOptionPane.setName("BlockingDialog.optionPane");
/* 159 */     injectBlockingDialogComponents(jDialog);
/*     */ 
/*     */ 
/*     */     
/* 163 */     recreateOptionPaneMessage(jOptionPane);
/* 164 */     jDialog.pack();
/* 165 */     return jDialog;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void recreateOptionPaneMessage(JOptionPane paramJOptionPane) {
/* 175 */     Object object = paramJOptionPane.getMessage();
/* 176 */     if (object instanceof String) {
/* 177 */       Font font = paramJOptionPane.getFont();
/* 178 */       final JTextArea textArea = new JTextArea((String)object);
/* 179 */       jTextArea.setFont(font);
/* 180 */       int i = jTextArea.getFontMetrics(font).getHeight();
/* 181 */       Insets insets = new Insets(0, 0, i, 24);
/* 182 */       jTextArea.setMargin(insets);
/* 183 */       jTextArea.setEditable(false);
/* 184 */       jTextArea.setWrapStyleWord(true);
/* 185 */       jTextArea.setBackground(paramJOptionPane.getBackground());
/* 186 */       JPanel jPanel = new JPanel(new BorderLayout());
/* 187 */       jPanel.add(jTextArea, "Center");
/* 188 */       final JProgressBar progressBar = new JProgressBar();
/* 189 */       jProgressBar.setName("BlockingDialog.progressBar");
/* 190 */       jProgressBar.setIndeterminate(true);
/* 191 */       PropertyChangeListener propertyChangeListener = new PropertyChangeListener() {
/*     */           public void propertyChange(PropertyChangeEvent param1PropertyChangeEvent) {
/* 193 */             if ("progress".equals(param1PropertyChangeEvent.getPropertyName())) {
/* 194 */               progressBar.setIndeterminate(false);
/* 195 */               progressBar.setValue(((Integer)param1PropertyChangeEvent.getNewValue()).intValue());
/* 196 */               DefaultInputBlocker.this.updateStatusBarString(progressBar);
/*     */             }
/* 198 */             else if ("message".equals(param1PropertyChangeEvent.getPropertyName())) {
/* 199 */               textArea.setText((String)param1PropertyChangeEvent.getNewValue());
/*     */             } 
/*     */           }
/*     */         };
/* 203 */       getTask().addPropertyChangeListener(propertyChangeListener);
/* 204 */       jPanel.add(jProgressBar, "South");
/* 205 */       injectBlockingDialogComponents(jPanel);
/* 206 */       paramJOptionPane.setMessage(jPanel);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateStatusBarString(JProgressBar paramJProgressBar) {
/* 211 */     if (!paramJProgressBar.isStringPainted()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 220 */     String str1 = "progressBarStringFormat";
/* 221 */     if (paramJProgressBar.getClientProperty(str1) == null) {
/* 222 */       paramJProgressBar.putClientProperty(str1, paramJProgressBar.getString());
/*     */     }
/* 224 */     String str2 = (String)paramJProgressBar.getClientProperty(str1);
/* 225 */     if (paramJProgressBar.getValue() <= 0) {
/* 226 */       paramJProgressBar.setString("");
/*     */     }
/* 228 */     else if (str2 == null) {
/* 229 */       paramJProgressBar.setString(null);
/*     */     } else {
/*     */       
/* 232 */       double d = paramJProgressBar.getValue() / 100.0D;
/* 233 */       long l1 = getTask().getExecutionDuration(TimeUnit.SECONDS);
/* 234 */       long l2 = l1 / 60L;
/* 235 */       long l3 = (long)(0.5D + l1 / d) - l1;
/* 236 */       long l4 = l3 / 60L;
/* 237 */       String str = String.format(str2, new Object[] { Long.valueOf(l2), Long.valueOf(l1 - l2 * 60L), Long.valueOf(l4), Long.valueOf(l3 - l4 * 60L) });
/*     */       
/* 239 */       paramJProgressBar.setString(str);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void showBusyGlassPane(boolean paramBoolean) {
/* 245 */     RootPaneContainer rootPaneContainer = null;
/* 246 */     Component component = (Component)getTarget();
/* 247 */     while (component != null) {
/* 248 */       if (component instanceof RootPaneContainer) {
/* 249 */         rootPaneContainer = (RootPaneContainer)component;
/*     */         break;
/*     */       } 
/* 252 */       component = component.getParent();
/*     */     } 
/* 254 */     if (rootPaneContainer != null) {
/* 255 */       if (paramBoolean) {
/* 256 */         JMenuBar jMenuBar = rootPaneContainer.getRootPane().getJMenuBar();
/* 257 */         if (jMenuBar != null) {
/* 258 */           jMenuBar.putClientProperty(this, Boolean.valueOf(jMenuBar.isEnabled()));
/* 259 */           jMenuBar.setEnabled(false);
/*     */         } 
/* 261 */         BusyGlassPane busyGlassPane = new BusyGlassPane();
/* 262 */         InputVerifier inputVerifier = new InputVerifier() {
/*     */             public boolean verify(JComponent param1JComponent) {
/* 264 */               return !param1JComponent.isVisible();
/*     */             }
/*     */           };
/* 267 */         busyGlassPane.setInputVerifier(inputVerifier);
/* 268 */         Component component1 = rootPaneContainer.getGlassPane();
/* 269 */         rootPaneContainer.getRootPane().putClientProperty(this, component1);
/* 270 */         rootPaneContainer.setGlassPane(busyGlassPane);
/* 271 */         busyGlassPane.setVisible(true);
/* 272 */         busyGlassPane.revalidate();
/*     */       } else {
/*     */         
/* 275 */         JMenuBar jMenuBar = rootPaneContainer.getRootPane().getJMenuBar();
/* 276 */         if (jMenuBar != null) {
/* 277 */           boolean bool = ((Boolean)jMenuBar.getClientProperty(this)).booleanValue();
/* 278 */           jMenuBar.putClientProperty(this, null);
/* 279 */           jMenuBar.setEnabled(bool);
/*     */         } 
/* 281 */         Component component1 = (Component)rootPaneContainer.getRootPane().getClientProperty(this);
/* 282 */         rootPaneContainer.getRootPane().putClientProperty(this, null);
/* 283 */         if (!component1.isVisible()) {
/* 284 */           rootPaneContainer.getGlassPane().setVisible(false);
/*     */         }
/* 286 */         rootPaneContainer.setGlassPane(component1);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static class BusyGlassPane
/*     */     extends JPanel
/*     */   {
/*     */     BusyGlassPane() {
/* 296 */       super(null, false);
/* 297 */       setVisible(false);
/* 298 */       setOpaque(false);
/* 299 */       setCursor(Cursor.getPredefinedCursor(3));
/* 300 */       MouseInputAdapter mouseInputAdapter = new MouseInputAdapter() {  };
/* 301 */       addMouseMotionListener(mouseInputAdapter);
/* 302 */       addMouseListener(mouseInputAdapter);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int blockingDialogDelay() {
/* 313 */     Integer integer = null;
/* 314 */     String str = "BlockingDialogTimer.delay";
/* 315 */     ApplicationAction applicationAction = getAction();
/* 316 */     if (applicationAction != null) {
/* 317 */       ResourceMap resourceMap1 = applicationAction.getResourceMap();
/* 318 */       String str1 = applicationAction.getName();
/* 319 */       integer = resourceMap1.getInteger(str1 + "." + str);
/*     */     } 
/* 321 */     ResourceMap resourceMap = getTask().getResourceMap();
/* 322 */     if (integer == null && resourceMap != null) {
/* 323 */       integer = resourceMap.getInteger(str);
/*     */     }
/* 325 */     return (integer == null) ? 0 : integer.intValue();
/*     */   }
/*     */   
/*     */   private void showBlockingDialog(boolean paramBoolean) {
/* 329 */     if (paramBoolean) {
/* 330 */       if (this.modalDialog != null) {
/* 331 */         String str = String.format("unexpected InputBlocker state [%s] %s", new Object[] { Boolean.valueOf(paramBoolean), this });
/* 332 */         logger.warning(str);
/* 333 */         this.modalDialog.dispose();
/*     */       } 
/* 335 */       this.modalDialog = createBlockingDialog();
/* 336 */       ActionListener actionListener = new ActionListener() {
/*     */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 338 */             if (DefaultInputBlocker.this.modalDialog != null) {
/* 339 */               DefaultInputBlocker.this.modalDialog.setVisible(true);
/*     */             }
/*     */           }
/*     */         };
/* 343 */       Timer timer = new Timer(blockingDialogDelay(), actionListener);
/* 344 */       timer.setRepeats(false);
/* 345 */       timer.start();
/*     */     
/*     */     }
/* 348 */     else if (this.modalDialog != null) {
/* 349 */       this.modalDialog.dispose();
/* 350 */       this.modalDialog = null;
/*     */     } else {
/*     */       
/* 353 */       String str = String.format("unexpected InputBlocker state [%s] %s", new Object[] { Boolean.valueOf(paramBoolean), this });
/* 354 */       logger.warning(str);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void block() {
/* 360 */     switch (getScope()) {
/*     */       case ACTION:
/* 362 */         setActionTargetBlocked(true);
/*     */         break;
/*     */       case COMPONENT:
/* 365 */         setComponentTargetBlocked(true);
/*     */         break;
/*     */       case WINDOW:
/*     */       case APPLICATION:
/* 369 */         showBusyGlassPane(true);
/* 370 */         showBlockingDialog(true);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void unblock() {
/* 376 */     switch (getScope()) {
/*     */       case ACTION:
/* 378 */         setActionTargetBlocked(false);
/*     */         break;
/*     */       case COMPONENT:
/* 381 */         setComponentTargetBlocked(false);
/*     */         break;
/*     */       case WINDOW:
/*     */       case APPLICATION:
/* 385 */         showBusyGlassPane(false);
/* 386 */         showBlockingDialog(false);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\jdesktop\application\DefaultInputBlocker.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */