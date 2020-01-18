/*    */ package org.junit.experimental.results;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.junit.runner.Result;
/*    */ import org.junit.runner.notification.Failure;
/*    */ import org.junit.runner.notification.RunListener;
/*    */ 
/*    */ 
/*    */ class FailureList
/*    */ {
/*    */   private final List<Failure> failures;
/*    */   
/* 13 */   public FailureList(List<Failure> failures) { this.failures = failures; }
/*    */ 
/*    */   
/*    */   public Result result() {
/* 17 */     Result result = new Result();
/* 18 */     RunListener listener = result.createListener();
/* 19 */     for (Failure failure : this.failures) {
/*    */       try {
/* 21 */         listener.testFailure(failure);
/* 22 */       } catch (Exception e) {
/* 23 */         throw new RuntimeException("I can't believe this happened");
/*    */       } 
/*    */     } 
/* 26 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\experimental\results\FailureList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */