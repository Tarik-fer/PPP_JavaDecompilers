/*    */ package junit.extensions;
/*    */ 
/*    */ import junit.framework.Test;
/*    */ import junit.framework.TestResult;
/*    */ 
/*    */ 
/*    */ public class RepeatedTest
/*    */   extends TestDecorator
/*    */ {
/*    */   private int fTimesRepeat;
/*    */   
/*    */   public RepeatedTest(Test test, int repeat) {
/* 13 */     super(test);
/* 14 */     if (repeat < 0) {
/* 15 */       throw new IllegalArgumentException("Repetition count must be >= 0");
/*    */     }
/* 17 */     this.fTimesRepeat = repeat;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 22 */   public int countTestCases() { return super.countTestCases() * this.fTimesRepeat; }
/*    */ 
/*    */ 
/*    */   
/*    */   public void run(TestResult result) {
/* 27 */     for (int i = 0; i < this.fTimesRepeat && 
/* 28 */       !result.shouldStop(); i++)
/*    */     {
/*    */       
/* 31 */       super.run(result);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 37 */   public String toString() { return super.toString() + "(repeated)"; }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\junit\extensions\RepeatedTest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */