/*    */ package org.hamcrest.core;
/*    */ 
/*    */ import org.hamcrest.Description;
/*    */ import org.hamcrest.Factory;
/*    */ import org.hamcrest.Matcher;
/*    */ import org.hamcrest.SelfDescribing;
/*    */ import org.hamcrest.TypeSafeDiagnosingMatcher;
/*    */ 
/*    */ public class Every<T> extends TypeSafeDiagnosingMatcher<Iterable<T>> {
/*    */   private final Matcher<? super T> matcher;
/*    */   
/* 12 */   public Every(Matcher<? super T> matcher) { this.matcher = matcher; }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean matchesSafely(Iterable<T> collection, Description mismatchDescription) {
/* 17 */     for (T t : collection) {
/* 18 */       if (!this.matcher.matches(t)) {
/* 19 */         mismatchDescription.appendText("an item ");
/* 20 */         this.matcher.describeMismatch(t, mismatchDescription);
/* 21 */         return false;
/*    */       } 
/*    */     } 
/* 24 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 29 */   public void describeTo(Description description) { description.appendText("every item is ").appendDescriptionOf((SelfDescribing)this.matcher); }
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
/*    */   @Factory
/* 45 */   public static <U> Matcher<Iterable<U>> everyItem(Matcher<U> itemMatcher) { return (Matcher)new Every<U>(itemMatcher); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\hamcrest\core\Every.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */