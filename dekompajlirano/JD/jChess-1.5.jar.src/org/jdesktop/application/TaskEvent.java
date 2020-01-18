/*    */ package org.jdesktop.application;
/*    */ 
/*    */ import java.util.EventObject;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TaskEvent<T>
/*    */   extends EventObject
/*    */ {
/*    */   private final T value;
/*    */   
/* 23 */   public final T getValue() { return this.value; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public TaskEvent(Task paramTask, T paramT) {
/* 33 */     super(paramTask);
/* 34 */     this.value = paramT;
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\jdesktop\application\TaskEvent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */