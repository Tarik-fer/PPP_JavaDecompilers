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
/*    */ 
/*    */ 
/*    */ public abstract class CustomTypeSafeMatcher<T>
/*    */   extends TypeSafeMatcher<T>
/*    */ {
/*    */   private final String fixedDescription;
/*    */   
/*    */   public CustomTypeSafeMatcher(String description) {
/* 29 */     if (description == null) {
/* 30 */       throw new IllegalArgumentException("Description must be non null!");
/*    */     }
/* 32 */     this.fixedDescription = description;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 37 */   public final void describeTo(Description description) { description.appendText(this.fixedDescription); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\hamcrest\CustomTypeSafeMatcher.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */