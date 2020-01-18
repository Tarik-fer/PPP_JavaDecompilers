/*    */ package org.junit.internal.runners.statements;
/*    */ 
/*    */ import org.junit.runners.model.Statement;
/*    */ 
/*    */ public class Fail
/*    */   extends Statement {
/*    */   private final Throwable error;
/*    */   
/*  9 */   public Fail(Throwable e) { this.error = e; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 14 */   public void evaluate() throws Throwable { throw this.error; }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\internal\runners\statements\Fail.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */