/*    */ package org.junit.runner;
/*    */ 
/*    */ public final class FilterFactoryParams {
/*    */   private final Description topLevelDescription;
/*    */   private final String args;
/*    */   
/*    */   public FilterFactoryParams(Description topLevelDescription, String args) {
/*  8 */     if (args == null || topLevelDescription == null) {
/*  9 */       throw new NullPointerException();
/*    */     }
/*    */     
/* 12 */     this.topLevelDescription = topLevelDescription;
/* 13 */     this.args = args;
/*    */   }
/*    */ 
/*    */   
/* 17 */   public String getArgs() { return this.args; }
/*    */ 
/*    */ 
/*    */   
/* 21 */   public Description getTopLevelDescription() { return this.topLevelDescription; }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\runner\FilterFactoryParams.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */