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
/*    */ 
/*    */ 
/*    */ public class AssertionFailedError
/*    */   extends AssertionError
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public AssertionFailedError() {}
/*    */   
/* 23 */   public AssertionFailedError(String message) { super(defaultString(message)); }
/*    */ 
/*    */ 
/*    */   
/* 27 */   private static String defaultString(String message) { return (message == null) ? "" : message; }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\junit\framework\AssertionFailedError.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */