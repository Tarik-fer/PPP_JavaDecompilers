/*    */ package org.junit.internal.builders;
/*    */ 
/*    */ import org.junit.runner.Description;
/*    */ import org.junit.runner.Runner;
/*    */ import org.junit.runner.notification.RunNotifier;
/*    */ 
/*    */ public class IgnoredClassRunner
/*    */   extends Runner {
/*    */   private final Class<?> clazz;
/*    */   
/* 11 */   public IgnoredClassRunner(Class<?> testClass) { this.clazz = testClass; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 16 */   public void run(RunNotifier notifier) { notifier.fireTestIgnored(getDescription()); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 21 */   public Description getDescription() { return Description.createSuiteDescription(this.clazz); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\internal\builders\IgnoredClassRunner.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */