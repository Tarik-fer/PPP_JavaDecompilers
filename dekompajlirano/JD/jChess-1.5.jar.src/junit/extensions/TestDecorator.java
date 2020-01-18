/*    */ package junit.extensions;
/*    */ 
/*    */ import junit.framework.Assert;
/*    */ import junit.framework.Test;
/*    */ import junit.framework.TestResult;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TestDecorator
/*    */   extends Assert
/*    */   implements Test
/*    */ {
/*    */   protected Test fTest;
/*    */   
/* 16 */   public TestDecorator(Test test) { this.fTest = test; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 23 */   public void basicRun(TestResult result) { this.fTest.run(result); }
/*    */ 
/*    */ 
/*    */   
/* 27 */   public int countTestCases() { return this.fTest.countTestCases(); }
/*    */ 
/*    */ 
/*    */   
/* 31 */   public void run(TestResult result) { basicRun(result); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 36 */   public String toString() { return this.fTest.toString(); }
/*    */ 
/*    */ 
/*    */   
/* 40 */   public Test getTest() { return this.fTest; }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\junit\extensions\TestDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */