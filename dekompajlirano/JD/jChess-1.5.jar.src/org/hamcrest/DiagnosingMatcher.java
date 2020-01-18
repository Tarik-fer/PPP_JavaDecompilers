/*    */ package org.hamcrest;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class DiagnosingMatcher<T>
/*    */   extends BaseMatcher<T>
/*    */ {
/* 12 */   public final boolean matches(Object item) { return matches(item, Description.NONE); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 17 */   public final void describeMismatch(Object item, Description mismatchDescription) { matches(item, mismatchDescription); }
/*    */   
/*    */   protected abstract boolean matches(Object paramObject, Description paramDescription);
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\hamcrest\DiagnosingMatcher.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */