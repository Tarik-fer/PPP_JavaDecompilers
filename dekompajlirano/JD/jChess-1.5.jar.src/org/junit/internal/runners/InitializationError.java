/*    */ package org.junit.internal.runners;
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
/*    */ @Deprecated
/*    */ public class InitializationError
/*    */   extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private final List<Throwable> fErrors;
/*    */   
/* 23 */   public InitializationError(List<Throwable> errors) { this.fErrors = errors; }
/*    */ 
/*    */ 
/*    */   
/* 27 */   public InitializationError(Throwable... errors) { this(Arrays.asList(errors)); }
/*    */ 
/*    */ 
/*    */   
/* 31 */   public InitializationError(String string) { this(new Throwable[] { new Exception(string) }); }
/*    */ 
/*    */ 
/*    */   
/* 35 */   public List<Throwable> getCauses() { return this.fErrors; }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\internal\runners\InitializationError.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */