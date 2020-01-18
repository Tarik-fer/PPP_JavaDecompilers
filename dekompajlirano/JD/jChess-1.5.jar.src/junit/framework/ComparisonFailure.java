/*    */ package junit.framework;
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
/*    */ 
/*    */ public class ComparisonFailure
/*    */   extends AssertionFailedError
/*    */ {
/*    */   private static final int MAX_CONTEXT_LENGTH = 20;
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String fExpected;
/*    */   private String fActual;
/*    */   
/*    */   public ComparisonFailure(String message, String expected, String actual) {
/* 23 */     super(message);
/* 24 */     this.fExpected = expected;
/* 25 */     this.fActual = actual;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 36 */   public String getMessage() { return (new ComparisonCompactor(20, this.fExpected, this.fActual)).compact(super.getMessage()); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 45 */   public String getActual() { return this.fActual; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 54 */   public String getExpected() { return this.fExpected; }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\junit\framework\ComparisonFailure.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */