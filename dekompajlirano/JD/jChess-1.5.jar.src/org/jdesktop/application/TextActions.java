/*     */ package org.jdesktop.application;
/*     */ 
/*     */ import java.awt.AWTEvent;
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.datatransfer.Clipboard;
/*     */ import java.awt.datatransfer.DataFlavor;
/*     */ import java.awt.datatransfer.FlavorEvent;
/*     */ import java.awt.datatransfer.FlavorListener;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.InputEvent;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import javax.swing.AbstractAction;
/*     */ import javax.swing.Action;
/*     */ import javax.swing.ActionMap;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.event.CaretEvent;
/*     */ import javax.swing.event.CaretListener;
/*     */ import javax.swing.text.Caret;
/*     */ import javax.swing.text.JTextComponent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class TextActions
/*     */   extends AbstractBean
/*     */ {
/*     */   private final ApplicationContext context;
/*     */   private final CaretListener textComponentCaretListener;
/*     */   private final PropertyChangeListener textComponentPCL;
/*  51 */   private final String markerActionKey = "TextActions.markerAction";
/*     */   private final Action markerAction;
/*     */   private boolean copyEnabled = false;
/*     */   private boolean cutEnabled = false;
/*     */   private boolean pasteEnabled = false;
/*     */   private boolean deleteEnabled = false;
/*     */   
/*     */   public TextActions(ApplicationContext paramApplicationContext) {
/*  59 */     this.context = paramApplicationContext;
/*  60 */     this.markerAction = new AbstractAction() {
/*     */         public void actionPerformed(ActionEvent param1ActionEvent) {}
/*     */       };
/*  63 */     this.textComponentCaretListener = new TextComponentCaretListener();
/*  64 */     this.textComponentPCL = new TextComponentPCL();
/*  65 */     getClipboard().addFlavorListener(new ClipboardListener());
/*     */   }
/*     */ 
/*     */   
/*  69 */   private ApplicationContext getContext() { return this.context; }
/*     */ 
/*     */ 
/*     */   
/*  73 */   private JComponent getFocusOwner() { return getContext().getFocusOwner(); }
/*     */ 
/*     */ 
/*     */   
/*  77 */   private Clipboard getClipboard() { return getContext().getClipboard(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateFocusOwner(JComponent paramJComponent1, JComponent paramJComponent2) {
/*  84 */     if (paramJComponent1 instanceof JTextComponent) {
/*  85 */       JTextComponent jTextComponent = (JTextComponent)paramJComponent1;
/*  86 */       jTextComponent.removeCaretListener(this.textComponentCaretListener);
/*  87 */       jTextComponent.removePropertyChangeListener(this.textComponentPCL);
/*     */     } 
/*  89 */     if (paramJComponent2 instanceof JTextComponent) {
/*  90 */       JTextComponent jTextComponent = (JTextComponent)paramJComponent2;
/*  91 */       maybeInstallTextActions(jTextComponent);
/*  92 */       updateTextActions(jTextComponent);
/*  93 */       jTextComponent.addCaretListener(this.textComponentCaretListener);
/*  94 */       jTextComponent.addPropertyChangeListener(this.textComponentPCL);
/*     */     }
/*  96 */     else if (paramJComponent2 == null) {
/*  97 */       setCopyEnabled(false);
/*  98 */       setCutEnabled(false);
/*  99 */       setPasteEnabled(false);
/* 100 */       setDeleteEnabled(false);
/*     */     } 
/*     */   }
/*     */   
/*     */   private final class ClipboardListener implements FlavorListener {
/*     */     public void flavorsChanged(FlavorEvent param1FlavorEvent) {
/* 106 */       JComponent jComponent = TextActions.this.getFocusOwner();
/* 107 */       if (jComponent instanceof JTextComponent)
/* 108 */         TextActions.this.updateTextActions((JTextComponent)jComponent); 
/*     */     }
/*     */     
/*     */     private ClipboardListener() {} }
/*     */   
/*     */   private final class TextComponentCaretListener implements CaretListener { private TextComponentCaretListener() {}
/*     */     
/* 115 */     public void caretUpdate(CaretEvent param1CaretEvent) { TextActions.this.updateTextActions((JTextComponent)param1CaretEvent.getSource()); } }
/*     */   
/*     */   private final class TextComponentPCL implements PropertyChangeListener {
/*     */     private TextComponentPCL() {}
/*     */     
/*     */     public void propertyChange(PropertyChangeEvent param1PropertyChangeEvent) {
/* 121 */       String str = param1PropertyChangeEvent.getPropertyName();
/* 122 */       if (str == null || "editable".equals(str)) {
/* 123 */         TextActions.this.updateTextActions((JTextComponent)param1PropertyChangeEvent.getSource());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void updateTextActions(JTextComponent paramJTextComponent) {
/* 129 */     Caret caret = paramJTextComponent.getCaret();
/* 130 */     boolean bool = (caret.getDot() != caret.getMark()) ? true : false;
/* 131 */     boolean bool1 = paramJTextComponent.isEditable();
/* 132 */     boolean bool2 = getClipboard().isDataFlavorAvailable(DataFlavor.stringFlavor);
/* 133 */     setCopyEnabled(bool);
/* 134 */     setCutEnabled((bool1 && bool));
/* 135 */     setDeleteEnabled((bool1 && bool));
/* 136 */     setPasteEnabled((bool1 && bool2));
/*     */   }
/*     */ 
/*     */   
/*     */   private void maybeInstallTextActions(JTextComponent paramJTextComponent) {
/* 141 */     ActionMap actionMap = paramJTextComponent.getActionMap();
/* 142 */     if (actionMap.get("TextActions.markerAction") == null) {
/* 143 */       actionMap.put("TextActions.markerAction", this.markerAction);
/* 144 */       ApplicationActionMap applicationActionMap = getContext().getActionMap(getClass(), this);
/* 145 */       for (Object object : applicationActionMap.keys()) {
/* 146 */         actionMap.put(object, applicationActionMap.get(object));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getCurrentEventModifiers() {
/* 155 */     int i = 0;
/* 156 */     AWTEvent aWTEvent = EventQueue.getCurrentEvent();
/* 157 */     if (aWTEvent instanceof InputEvent) {
/* 158 */       i = ((InputEvent)aWTEvent).getModifiers();
/*     */     }
/* 160 */     else if (aWTEvent instanceof ActionEvent) {
/* 161 */       i = ((ActionEvent)aWTEvent).getModifiers();
/*     */     } 
/* 163 */     return i;
/*     */   }
/*     */   
/*     */   private void invokeTextAction(JTextComponent paramJTextComponent, String paramString) {
/* 167 */     ActionMap actionMap = paramJTextComponent.getActionMap().getParent();
/* 168 */     long l = EventQueue.getMostRecentEventTime();
/* 169 */     int i = getCurrentEventModifiers();
/* 170 */     ActionEvent actionEvent = new ActionEvent(paramJTextComponent, 1001, paramString, l, i);
/*     */     
/* 172 */     actionMap.get(paramString).actionPerformed(actionEvent);
/*     */   }
/*     */   
/*     */   @Action(enabledProperty = "cutEnabled")
/*     */   public void cut(ActionEvent paramActionEvent) {
/* 177 */     Object object = paramActionEvent.getSource();
/* 178 */     if (object instanceof JTextComponent) {
/* 179 */       invokeTextAction((JTextComponent)object, "cut");
/*     */     }
/*     */   }
/*     */   
/* 183 */   public boolean isCutEnabled() { return this.cutEnabled; }
/*     */   
/*     */   public void setCutEnabled(boolean paramBoolean) {
/* 186 */     boolean bool = this.cutEnabled;
/* 187 */     this.cutEnabled = paramBoolean;
/* 188 */     firePropertyChange("cutEnabled", Boolean.valueOf(bool), Boolean.valueOf(this.cutEnabled));
/*     */   }
/*     */   
/*     */   @Action(enabledProperty = "copyEnabled")
/*     */   public void copy(ActionEvent paramActionEvent) {
/* 193 */     Object object = paramActionEvent.getSource();
/* 194 */     if (object instanceof JTextComponent) {
/* 195 */       invokeTextAction((JTextComponent)object, "copy");
/*     */     }
/*     */   }
/*     */   
/* 199 */   public boolean isCopyEnabled() { return this.copyEnabled; }
/*     */   
/*     */   public void setCopyEnabled(boolean paramBoolean) {
/* 202 */     boolean bool = this.copyEnabled;
/* 203 */     this.copyEnabled = paramBoolean;
/* 204 */     firePropertyChange("copyEnabled", Boolean.valueOf(bool), Boolean.valueOf(this.copyEnabled));
/*     */   }
/*     */   
/*     */   @Action(enabledProperty = "pasteEnabled")
/*     */   public void paste(ActionEvent paramActionEvent) {
/* 209 */     Object object = paramActionEvent.getSource();
/* 210 */     if (object instanceof JTextComponent) {
/* 211 */       invokeTextAction((JTextComponent)object, "paste");
/*     */     }
/*     */   }
/*     */   
/* 215 */   public boolean isPasteEnabled() { return this.pasteEnabled; }
/*     */   
/*     */   public void setPasteEnabled(boolean paramBoolean) {
/* 218 */     boolean bool = this.pasteEnabled;
/* 219 */     this.pasteEnabled = paramBoolean;
/* 220 */     firePropertyChange("pasteEnabled", Boolean.valueOf(bool), Boolean.valueOf(this.pasteEnabled));
/*     */   }
/*     */   
/*     */   @Action(enabledProperty = "deleteEnabled")
/*     */   public void delete(ActionEvent paramActionEvent) {
/* 225 */     Object object = paramActionEvent.getSource();
/* 226 */     if (object instanceof JTextComponent)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 234 */       invokeTextAction((JTextComponent)object, "delete-next");
/*     */     }
/*     */   }
/*     */   
/* 238 */   public boolean isDeleteEnabled() { return this.deleteEnabled; }
/*     */   
/*     */   public void setDeleteEnabled(boolean paramBoolean) {
/* 241 */     boolean bool = this.deleteEnabled;
/* 242 */     this.deleteEnabled = paramBoolean;
/* 243 */     firePropertyChange("deleteEnabled", Boolean.valueOf(bool), Boolean.valueOf(this.deleteEnabled));
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\jdesktop\application\TextActions.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */