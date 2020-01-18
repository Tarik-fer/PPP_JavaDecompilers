/*    */ package org.junit.internal.runners.model;
/*    */ 
/*    */ import java.lang.reflect.InvocationTargetException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ReflectiveCallable
/*    */ {
/*    */   public Object run() throws Throwable {
/*    */     try {
/* 12 */       return runReflectiveCall();
/* 13 */     } catch (InvocationTargetException e) {
/* 14 */       throw e.getTargetException();
/*    */     } 
/*    */   }
/*    */   
/*    */   protected abstract Object runReflectiveCall() throws Throwable;
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\internal\runners\model\ReflectiveCallable.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */