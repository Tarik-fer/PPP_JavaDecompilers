/*    */ package org.apache.log4j.or;
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
/*    */ 
/*    */ class DefaultRenderer
/*    */   implements ObjectRenderer
/*    */ {
/*    */   public String doRender(Object o) {
/*    */     try {
/* 37 */       return o.toString();
/* 38 */     } catch (Exception ex) {
/* 39 */       return ex.toString();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\log4j\or\DefaultRenderer.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.2
 */