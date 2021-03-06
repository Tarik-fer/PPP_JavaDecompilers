/*    */ package junit.extensions;
/*    */ 
/*    */ import junit.framework.Test;
/*    */ import junit.framework.TestCase;
/*    */ import junit.framework.TestResult;
/*    */ import junit.framework.TestSuite;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ActiveTestSuite
/*    */   extends TestSuite
/*    */ {
/*    */   private volatile int fActiveTestDeathCount;
/*    */   
/*    */   public ActiveTestSuite() {}
/*    */   
/* 21 */   public ActiveTestSuite(Class<? extends TestCase> theClass) { super(theClass); }
/*    */ 
/*    */ 
/*    */   
/* 25 */   public ActiveTestSuite(String name) { super(name); }
/*    */ 
/*    */ 
/*    */   
/* 29 */   public ActiveTestSuite(Class<? extends TestCase> theClass, String name) { super(theClass, name); }
/*    */ 
/*    */ 
/*    */   
/*    */   public void run(TestResult result) {
/* 34 */     this.fActiveTestDeathCount = 0;
/* 35 */     super.run(result);
/* 36 */     waitUntilFinished();
/*    */   }
/*    */ 
/*    */   
/*    */   public void runTest(final Test test, final TestResult result) {
/* 41 */     Thread t = new Thread()
/*    */       {
/*    */         
/*    */         public void run()
/*    */         {
/*    */           try {
/* 47 */             test.run(result);
/*    */           } finally {
/* 49 */             ActiveTestSuite.this.runFinished();
/*    */           } 
/*    */         }
/*    */       };
/* 53 */     t.start();
/*    */   }
/*    */   
/*    */   synchronized void waitUntilFinished() {
/* 57 */     while (this.fActiveTestDeathCount < testCount()) {
/*    */       try {
/* 59 */         wait();
/* 60 */       } catch (InterruptedException e) {
/*    */         return;
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public synchronized void runFinished() {
/* 67 */     this.fActiveTestDeathCount++;
/* 68 */     notifyAll();
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\junit\extensions\ActiveTestSuite.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */