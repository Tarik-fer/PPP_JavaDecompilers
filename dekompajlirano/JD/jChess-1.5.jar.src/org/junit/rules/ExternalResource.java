/*    */ package org.junit.rules;
/*    */ 
/*    */ import org.junit.runner.Description;
/*    */ import org.junit.runners.model.Statement;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ExternalResource
/*    */   implements TestRule
/*    */ {
/* 39 */   public Statement apply(Statement base, Description description) { return statement(base); }
/*    */ 
/*    */   
/*    */   private Statement statement(final Statement base) {
/* 43 */     return new Statement()
/*    */       {
/*    */         public void evaluate() throws Throwable {
/* 46 */           ExternalResource.this.before();
/*    */           try {
/* 48 */             base.evaluate();
/*    */           } finally {
/* 50 */             ExternalResource.this.after();
/*    */           } 
/*    */         }
/*    */       };
/*    */   }
/*    */   
/*    */   protected void before() throws Throwable {}
/*    */   
/*    */   protected void after() {}
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\rules\ExternalResource.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */