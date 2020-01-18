/*    */ package org.hamcrest.core;
/*    */ 
/*    */ import org.hamcrest.BaseMatcher;
/*    */ import org.hamcrest.Description;
/*    */ import org.hamcrest.Factory;
/*    */ import org.hamcrest.Matcher;
/*    */ import org.hamcrest.SelfDescribing;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Is<T>
/*    */   extends BaseMatcher<T>
/*    */ {
/*    */   private final Matcher<T> matcher;
/*    */   
/* 22 */   public Is(Matcher<T> matcher) { this.matcher = matcher; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 27 */   public boolean matches(Object arg) { return this.matcher.matches(arg); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 32 */   public void describeTo(Description description) { description.appendText("is ").appendDescriptionOf((SelfDescribing)this.matcher); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 37 */   public void describeMismatch(Object item, Description mismatchDescription) { this.matcher.describeMismatch(item, mismatchDescription); }
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
/* 52 */   public static <T> Matcher<T> is(Matcher<T> matcher) { return (Matcher<T>)new Is<T>(matcher); }
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
/* 66 */   public static <T> Matcher<T> is(T value) { return is(IsEqual.equalTo(value)); }
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
/*    */   @Deprecated
/*    */   public static <T> Matcher<T> is(Class<T> type) {
/* 82 */     Matcher<T> typeMatcher = IsInstanceOf.instanceOf(type);
/* 83 */     return is(typeMatcher);
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
/*    */   
/*    */   @Factory
/*    */   public static <T> Matcher<T> isA(Class<T> type) {
/* 97 */     Matcher<T> typeMatcher = IsInstanceOf.instanceOf(type);
/* 98 */     return is(typeMatcher);
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\hamcrest\core\Is.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */