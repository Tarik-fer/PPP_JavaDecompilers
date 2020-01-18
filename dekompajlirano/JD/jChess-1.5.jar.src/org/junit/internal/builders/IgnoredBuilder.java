/*    */ package org.junit.internal.builders;
/*    */ 
/*    */ import org.junit.Ignore;
/*    */ import org.junit.runner.Runner;
/*    */ import org.junit.runners.model.RunnerBuilder;
/*    */ 
/*    */ public class IgnoredBuilder
/*    */   extends RunnerBuilder {
/*    */   public Runner runnerForClass(Class<?> testClass) {
/* 10 */     if (testClass.getAnnotation((Class)Ignore.class) != null) {
/* 11 */       return new IgnoredClassRunner(testClass);
/*    */     }
/* 13 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\internal\builders\IgnoredBuilder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */