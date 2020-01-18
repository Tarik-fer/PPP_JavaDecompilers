/*     */ package org.apache.log4j.lf5.viewer.categoryexplorer;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.util.EventObject;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.JTree;
/*     */ import javax.swing.event.CellEditorListener;
/*     */ import javax.swing.event.ChangeEvent;
/*     */ import javax.swing.event.EventListenerList;
/*     */ import javax.swing.table.TableCellEditor;
/*     */ import javax.swing.tree.TreeCellEditor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CategoryAbstractCellEditor
/*     */   implements TableCellEditor, TreeCellEditor
/*     */ {
/*  49 */   protected EventListenerList _listenerList = new EventListenerList();
/*     */   protected Object _value;
/*  51 */   protected ChangeEvent _changeEvent = null;
/*  52 */   protected int _clickCountToStart = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   public Object getCellEditorValue() { return this._value; }
/*     */ 
/*     */ 
/*     */   
/*  71 */   public void setCellEditorValue(Object value) { this._value = value; }
/*     */ 
/*     */ 
/*     */   
/*  75 */   public void setClickCountToStart(int count) { this._clickCountToStart = count; }
/*     */ 
/*     */ 
/*     */   
/*  79 */   public int getClickCountToStart() { return this._clickCountToStart; }
/*     */ 
/*     */   
/*     */   public boolean isCellEditable(EventObject anEvent) {
/*  83 */     if (anEvent instanceof MouseEvent && (
/*  84 */       (MouseEvent)anEvent).getClickCount() < this._clickCountToStart) {
/*  85 */       return false;
/*     */     }
/*     */     
/*  88 */     return true;
/*     */   }
/*     */   
/*     */   public boolean shouldSelectCell(EventObject anEvent) {
/*  92 */     if (isCellEditable(anEvent) && (
/*  93 */       anEvent == null || ((MouseEvent)anEvent).getClickCount() >= this._clickCountToStart))
/*     */     {
/*  95 */       return true;
/*     */     }
/*     */     
/*  98 */     return false;
/*     */   }
/*     */   
/*     */   public boolean stopCellEditing() {
/* 102 */     fireEditingStopped();
/* 103 */     return true;
/*     */   }
/*     */ 
/*     */   
/* 107 */   public void cancelCellEditing() { fireEditingCanceled(); }
/*     */ 
/*     */ 
/*     */   
/* 111 */   public void addCellEditorListener(CellEditorListener l) { this._listenerList.add(CellEditorListener.class, l); }
/*     */ 
/*     */ 
/*     */   
/* 115 */   public void removeCellEditorListener(CellEditorListener l) { this._listenerList.remove(CellEditorListener.class, l); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 123 */   public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row) { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 130 */   public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fireEditingStopped() {
/* 137 */     Object[] listeners = this._listenerList.getListenerList();
/*     */     
/* 139 */     for (int i = listeners.length - 2; i >= 0; i -= 2) {
/* 140 */       if (listeners[i] == CellEditorListener.class) {
/* 141 */         if (this._changeEvent == null) {
/* 142 */           this._changeEvent = new ChangeEvent(this);
/*     */         }
/*     */         
/* 145 */         ((CellEditorListener)listeners[i + 1]).editingStopped(this._changeEvent);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void fireEditingCanceled() {
/* 151 */     Object[] listeners = this._listenerList.getListenerList();
/*     */     
/* 153 */     for (int i = listeners.length - 2; i >= 0; i -= 2) {
/* 154 */       if (listeners[i] == CellEditorListener.class) {
/* 155 */         if (this._changeEvent == null) {
/* 156 */           this._changeEvent = new ChangeEvent(this);
/*     */         }
/*     */         
/* 159 */         ((CellEditorListener)listeners[i + 1]).editingCanceled(this._changeEvent);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\log4j\lf5\viewer\categoryexplorer\CategoryAbstractCellEditor.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.2
 */