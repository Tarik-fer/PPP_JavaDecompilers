/*    */ package org.junit.runners.model;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
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
/*    */ 
/*    */ public class InitializationError
/*    */   extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private final List<Throwable> fErrors;
/*    */   
/* 26 */   public InitializationError(List<Throwable> errors) { this.fErrors = errors; }
/*    */ 
/*    */ 
/*    */   
/* 30 */   public InitializationError(Throwable error) { this(Arrays.asList(new Throwable[] { error })); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 38 */   public InitializationError(String string) { this(new Exception(string)); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 45 */   public List<Throwable> getCauses() { return this.fErrors; }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\runners\model\InitializationError.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */