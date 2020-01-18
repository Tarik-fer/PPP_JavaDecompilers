/*    */ package org.hamcrest;
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
/*    */ public abstract class CustomMatcher<T>
/*    */   extends BaseMatcher<T>
/*    */ {
/*    */   private final String fixedDescription;
/*    */   
/*    */   public CustomMatcher(String description) {
/* 27 */     if (description == null) {
/* 28 */       throw new IllegalArgumentException("Description should be non null!");
/*    */     }
/* 30 */     this.fixedDescription = description;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 35 */   public final void describeTo(Description description) { description.appendText(this.fixedDescription); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\hamcrest\CustomMatcher.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */