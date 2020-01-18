/*    */ package org.junit.internal.builders;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import org.junit.runner.Runner;
/*    */ import org.junit.runners.model.RunnerBuilder;
/*    */ 
/*    */ public class AllDefaultPossibilitiesBuilder
/*    */   extends RunnerBuilder
/*    */ {
/*    */   private final boolean canUseSuiteMethod;
/*    */   
/* 13 */   public AllDefaultPossibilitiesBuilder(boolean canUseSuiteMethod) { this.canUseSuiteMethod = canUseSuiteMethod; }
/*    */ 
/*    */ 
/*    */   
/*    */   public Runner runnerForClass(Class<?> testClass) throws Throwable {
/* 18 */     List<RunnerBuilder> builders = Arrays.asList(new RunnerBuilder[] { ignoredBuilder(), annotatedBuilder(), suiteMethodBuilder(), junit3Builder(), junit4Builder() });
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 25 */     for (RunnerBuilder each : builders) {
/* 26 */       Runner runner = each.safeRunnerForClass(testClass);
/* 27 */       if (runner != null) {
/* 28 */         return runner;
/*    */       }
/*    */     } 
/* 31 */     return null;
/*    */   }
/*    */ 
/*    */   
/* 35 */   protected JUnit4Builder junit4Builder() { return new JUnit4Builder(); }
/*    */ 
/*    */ 
/*    */   
/* 39 */   protected JUnit3Builder junit3Builder() { return new JUnit3Builder(); }
/*    */ 
/*    */ 
/*    */   
/* 43 */   protected AnnotatedBuilder annotatedBuilder() { return new AnnotatedBuilder(this); }
/*    */ 
/*    */ 
/*    */   
/* 47 */   protected IgnoredBuilder ignoredBuilder() { return new IgnoredBuilder(); }
/*    */ 
/*    */   
/*    */   protected RunnerBuilder suiteMethodBuilder() {
/* 51 */     if (this.canUseSuiteMethod) {
/* 52 */       return new SuiteMethodBuilder();
/*    */     }
/* 54 */     return new NullBuilder();
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\internal\builders\AllDefaultPossibilitiesBuilder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */