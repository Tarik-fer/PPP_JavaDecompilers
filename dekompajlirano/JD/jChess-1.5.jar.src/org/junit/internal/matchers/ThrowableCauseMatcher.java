/*    */ package org.junit.internal.matchers;
/*    */ 
/*    */ import org.hamcrest.Description;
/*    */ import org.hamcrest.Factory;
/*    */ import org.hamcrest.Matcher;
/*    */ import org.hamcrest.SelfDescribing;
/*    */ import org.hamcrest.TypeSafeMatcher;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ThrowableCauseMatcher<T extends Throwable>
/*    */   extends TypeSafeMatcher<T>
/*    */ {
/*    */   private final Matcher<? extends Throwable> causeMatcher;
/*    */   
/* 20 */   public ThrowableCauseMatcher(Matcher<? extends Throwable> causeMatcher) { this.causeMatcher = causeMatcher; }
/*    */ 
/*    */   
/*    */   public void describeTo(Description description) {
/* 24 */     description.appendText("exception with cause ");
/* 25 */     description.appendDescriptionOf((SelfDescribing)this.causeMatcher);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 30 */   protected boolean matchesSafely(T item) { return this.causeMatcher.matches(item.getCause()); }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void describeMismatchSafely(T item, Description description) {
/* 35 */     description.appendText("cause ");
/* 36 */     this.causeMatcher.describeMismatch(item.getCause(), description);
/*    */   }
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
/* 48 */   public static <T extends Throwable> Matcher<T> hasCause(Matcher<? extends Throwable> matcher) { return (Matcher)new ThrowableCauseMatcher<Throwable>(matcher); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\internal\matchers\ThrowableCauseMatcher.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */