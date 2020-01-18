/*     */ package org.jdesktop.application;
/*     */ 
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.beans.PropertyChangeSupport;
/*     */ import javax.swing.SwingUtilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AbstractBean
/*     */ {
/*  26 */   private final PropertyChangeSupport pcs = new EDTPropertyChangeSupport(this);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  43 */   public void addPropertyChangeListener(PropertyChangeListener paramPropertyChangeListener) { this.pcs.addPropertyChangeListener(paramPropertyChangeListener); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   public void removePropertyChangeListener(PropertyChangeListener paramPropertyChangeListener) { this.pcs.removePropertyChangeListener(paramPropertyChangeListener); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   public void addPropertyChangeListener(String paramString, PropertyChangeListener paramPropertyChangeListener) { this.pcs.addPropertyChangeListener(paramString, paramPropertyChangeListener); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  93 */   public synchronized void removePropertyChangeListener(String paramString, PropertyChangeListener paramPropertyChangeListener) { this.pcs.removePropertyChangeListener(paramString, paramPropertyChangeListener); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 103 */   public PropertyChangeListener[] getPropertyChangeListeners() { return this.pcs.getPropertyChangeListeners(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void firePropertyChange(String paramString, Object paramObject1, Object paramObject2) {
/* 119 */     if (paramObject1 != null && paramObject2 != null && paramObject1.equals(paramObject2)) {
/*     */       return;
/*     */     }
/* 122 */     this.pcs.firePropertyChange(paramString, paramObject1, paramObject2);
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
/* 138 */   protected void firePropertyChange(PropertyChangeEvent paramPropertyChangeEvent) { this.pcs.firePropertyChange(paramPropertyChangeEvent); }
/*     */   
/*     */   private static class EDTPropertyChangeSupport
/*     */     extends PropertyChangeSupport
/*     */   {
/* 143 */     EDTPropertyChangeSupport(Object param1Object) { super(param1Object); }
/*     */     
/*     */     public void firePropertyChange(final PropertyChangeEvent e) {
/* 146 */       if (SwingUtilities.isEventDispatchThread()) {
/* 147 */         super.firePropertyChange(e);
/*     */       } else {
/*     */         
/* 150 */         Runnable runnable = new Runnable() {
/*     */             public void run() {
/* 152 */               AbstractBean.EDTPropertyChangeSupport.this.firePropertyChange(e);
/*     */             }
/*     */           };
/* 155 */         SwingUtilities.invokeLater(runnable);
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\jdesktop\application\AbstractBean.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */