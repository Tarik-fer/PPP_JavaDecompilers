/*    */ package org.apache.log4j.config;
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
/*    */ public class PropertySetterException
/*    */   extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = -1352613734254235861L;
/*    */   protected Throwable rootCause;
/*    */   
/* 33 */   public PropertySetterException(String msg) { super(msg); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 40 */   public PropertySetterException(Throwable rootCause) { this.rootCause = rootCause; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getMessage() {
/* 48 */     String msg = super.getMessage();
/* 49 */     if (msg == null && this.rootCause != null) {
/* 50 */       msg = this.rootCause.getMessage();
/*    */     }
/* 52 */     return msg;
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\log4j\config\PropertySetterException.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.2
 */