/*    */ package org.junit.runners.parameterized;
/*    */ 
/*    */ import org.junit.runner.Runner;
/*    */ import org.junit.runners.model.InitializationError;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockJUnit4ClassRunnerWithParametersFactory
/*    */   implements ParametersRunnerFactory
/*    */ {
/* 16 */   public Runner createRunnerForTestWithParameters(TestWithParameters test) throws InitializationError { return (Runner)new BlockJUnit4ClassRunnerWithParameters(test); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\runners\parameterized\BlockJUnit4ClassRunnerWithParametersFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */