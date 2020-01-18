/*    */ package org.junit.rules;
/*    */ 
/*    */ import org.junit.runner.Description;
/*    */ import org.junit.runners.model.Statement;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RunRules
/*    */   extends Statement
/*    */ {
/*    */   private final Statement statement;
/*    */   
/* 15 */   public RunRules(Statement base, Iterable<TestRule> rules, Description description) { this.statement = applyAll(base, rules, description); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 20 */   public void evaluate() throws Throwable { this.statement.evaluate(); }
/*    */ 
/*    */ 
/*    */   
/*    */   private static Statement applyAll(Statement result, Iterable<TestRule> rules, Description description) {
/* 25 */     for (TestRule each : rules) {
/* 26 */       result = each.apply(result, description);
/*    */     }
/* 28 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\rules\RunRules.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */