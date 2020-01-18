/*    */ package org.junit.internal.builders;
/*    */ 
/*    */ import org.junit.runner.Runner;
/*    */ import org.junit.runners.BlockJUnit4ClassRunner;
/*    */ import org.junit.runners.model.RunnerBuilder;
/*    */ 
/*    */ public class JUnit4Builder
/*    */   extends RunnerBuilder
/*    */ {
/* 10 */   public Runner runnerForClass(Class<?> testClass) throws Throwable { return (Runner)new BlockJUnit4ClassRunner(testClass); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\internal\builders\JUnit4Builder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */