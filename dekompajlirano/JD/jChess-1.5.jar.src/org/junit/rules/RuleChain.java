/*    */ package org.junit.rules;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
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
/*    */ 
/*    */ 
/*    */ public class RuleChain
/*    */   implements TestRule
/*    */ {
/* 44 */   private static final RuleChain EMPTY_CHAIN = new RuleChain(Collections.emptyList());
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private List<TestRule> rulesStartingWithInnerMost;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 56 */   public static RuleChain emptyRuleChain() { return EMPTY_CHAIN; }
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
/* 67 */   public static RuleChain outerRule(TestRule outerRule) { return emptyRuleChain().around(outerRule); }
/*    */ 
/*    */ 
/*    */   
/* 71 */   private RuleChain(List<TestRule> rules) { this.rulesStartingWithInnerMost = rules; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RuleChain around(TestRule enclosedRule) {
/* 82 */     List<TestRule> rulesOfNewChain = new ArrayList<TestRule>();
/* 83 */     rulesOfNewChain.add(enclosedRule);
/* 84 */     rulesOfNewChain.addAll(this.rulesStartingWithInnerMost);
/* 85 */     return new RuleChain(rulesOfNewChain);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Statement apply(Statement base, Description description) {
/* 92 */     for (TestRule each : this.rulesStartingWithInnerMost) {
/* 93 */       base = each.apply(base, description);
/*    */     }
/* 95 */     return base;
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\rules\RuleChain.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */