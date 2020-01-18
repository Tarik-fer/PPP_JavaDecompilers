/*    */ package org.junit.internal;
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
/*    */ public final class Throwables
/*    */ {
/*    */   public static Exception rethrowAsException(Throwable e) throws Exception {
/* 34 */     rethrow(e);
/* 35 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 40 */   private static <T extends Throwable> void rethrow(Throwable e) throws T { throw e; }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\internal\Throwables.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */