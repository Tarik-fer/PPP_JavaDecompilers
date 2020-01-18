/*    */ package junit.framework;
/*    */ 
/*    */ import java.io.PrintWriter;
/*    */ import java.io.StringWriter;
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
/*    */ public class TestFailure
/*    */ {
/*    */   protected Test fFailedTest;
/*    */   protected Throwable fThrownException;
/*    */   
/*    */   public TestFailure(Test failedTest, Throwable thrownException) {
/* 21 */     this.fFailedTest = failedTest;
/* 22 */     this.fThrownException = thrownException;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 29 */   public Test failedTest() { return this.fFailedTest; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 36 */   public Throwable thrownException() { return this.fThrownException; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 44 */   public String toString() { return this.fFailedTest + ": " + this.fThrownException.getMessage(); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String trace() {
/* 52 */     StringWriter stringWriter = new StringWriter();
/* 53 */     PrintWriter writer = new PrintWriter(stringWriter);
/* 54 */     thrownException().printStackTrace(writer);
/* 55 */     return stringWriter.toString();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 62 */   public String exceptionMessage() { return thrownException().getMessage(); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 71 */   public boolean isFailure() { return thrownException() instanceof AssertionFailedError; }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\junit\framework\TestFailure.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */