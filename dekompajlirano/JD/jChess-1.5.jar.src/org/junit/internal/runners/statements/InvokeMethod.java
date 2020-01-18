/*    */ package org.junit.internal.runners.statements;
/*    */ 
/*    */ import org.junit.runners.model.FrameworkMethod;
/*    */ import org.junit.runners.model.Statement;
/*    */ 
/*    */ public class InvokeMethod extends Statement {
/*    */   private final FrameworkMethod testMethod;
/*    */   private final Object target;
/*    */   
/*    */   public InvokeMethod(FrameworkMethod testMethod, Object target) {
/* 11 */     this.testMethod = testMethod;
/* 12 */     this.target = target;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 17 */   public void evaluate() throws Throwable { this.testMethod.invokeExplosively(this.target, new Object[0]); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\internal\runners\statements\InvokeMethod.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */