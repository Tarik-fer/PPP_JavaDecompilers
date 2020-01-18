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
/*    */ public abstract class BaseMatcher<T>
/*    */   implements Matcher<T>
/*    */ {
/*    */   @Deprecated
/*    */   public final void _dont_implement_Matcher___instead_extend_BaseMatcher_() {}
/*    */   
/* 23 */   public void describeMismatch(Object item, Description description) { description.appendText("was ").appendValue(item); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 28 */   public String toString() { return StringDescription.toString(this); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\hamcrest\BaseMatcher.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */