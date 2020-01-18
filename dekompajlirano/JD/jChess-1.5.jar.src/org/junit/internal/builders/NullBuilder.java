/*   */ package org.junit.internal.builders;
/*   */ 
/*   */ import org.junit.runner.Runner;
/*   */ import org.junit.runners.model.RunnerBuilder;
/*   */ 
/*   */ public class NullBuilder
/*   */   extends RunnerBuilder
/*   */ {
/* 9 */   public Runner runnerForClass(Class<?> each) throws Throwable { return null; }
/*   */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\internal\builders\NullBuilder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */