/*    */ package org.junit.runner;
/*    */ 
/*    */ import org.junit.runners.Suite;
/*    */ import org.junit.runners.model.InitializationError;
/*    */ import org.junit.runners.model.RunnerBuilder;
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
/*    */ public class Computer
/*    */ {
/* 19 */   public static Computer serial() { return new Computer(); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 28 */   public Runner getSuite(final RunnerBuilder builder, Class<?>[] classes) throws InitializationError { return (Runner)new Suite(new RunnerBuilder()
/*    */         {
/*    */           public Runner runnerForClass(Class<?> testClass) throws Throwable {
/* 31 */             return Computer.this.getRunner(builder, testClass);
/*    */           }
/*    */         }classes); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 40 */   protected Runner getRunner(RunnerBuilder builder, Class<?> testClass) throws Throwable { return builder.runnerForClass(testClass); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\runner\Computer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */