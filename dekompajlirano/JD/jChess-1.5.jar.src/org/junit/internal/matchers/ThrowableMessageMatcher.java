/*    */ package org.junit.internal.matchers;
/*    */ 
/*    */ import org.hamcrest.Description;
/*    */ import org.hamcrest.Factory;
/*    */ import org.hamcrest.Matcher;
/*    */ import org.hamcrest.SelfDescribing;
/*    */ import org.hamcrest.TypeSafeMatcher;
/*    */ 
/*    */ public class ThrowableMessageMatcher<T extends Throwable>
/*    */   extends TypeSafeMatcher<T>
/*    */ {
/*    */   private final Matcher<String> matcher;
/*    */   
/* 14 */   public ThrowableMessageMatcher(Matcher<String> matcher) { this.matcher = matcher; }
/*    */ 
/*    */   
/*    */   public void describeTo(Description description) {
/* 18 */     description.appendText("exception with message ");
/* 19 */     description.appendDescriptionOf((SelfDescribing)this.matcher);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 24 */   protected boolean matchesSafely(T item) { return this.matcher.matches(item.getMessage()); }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void describeMismatchSafely(T item, Description description) {
/* 29 */     description.appendText("message ");
/* 30 */     this.matcher.describeMismatch(item.getMessage(), description);
/*    */   }
/*    */ 
/*    */   
/*    */   @Factory
/* 35 */   public static <T extends Throwable> Matcher<T> hasMessage(Matcher<String> matcher) { return (Matcher)new ThrowableMessageMatcher<Throwable>(matcher); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\internal\matchers\ThrowableMessageMatcher.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */