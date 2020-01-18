/*    */ package org.hamcrest.core;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import org.hamcrest.Description;
/*    */ import org.hamcrest.Factory;
/*    */ import org.hamcrest.Matcher;
/*    */ import org.hamcrest.SelfDescribing;
/*    */ import org.hamcrest.TypeSafeDiagnosingMatcher;
/*    */ 
/*    */ public class CombinableMatcher<T> extends TypeSafeDiagnosingMatcher<T> {
/* 11 */   public CombinableMatcher(Matcher<? super T> matcher) { this.matcher = matcher; }
/*    */   
/*    */   private final Matcher<? super T> matcher;
/*    */   
/*    */   protected boolean matchesSafely(T item, Description mismatch) {
/* 16 */     if (!this.matcher.matches(item)) {
/* 17 */       this.matcher.describeMismatch(item, mismatch);
/* 18 */       return false;
/*    */     } 
/* 20 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 25 */   public void describeTo(Description description) { description.appendDescriptionOf((SelfDescribing)this.matcher); }
/*    */ 
/*    */ 
/*    */   
/* 29 */   public CombinableMatcher<T> and(Matcher<? super T> other) { return new CombinableMatcher((Matcher<? super T>)new AllOf<T>(templatedListWith(other))); }
/*    */ 
/*    */ 
/*    */   
/* 33 */   public CombinableMatcher<T> or(Matcher<? super T> other) { return new CombinableMatcher((Matcher<? super T>)new AnyOf<T>(templatedListWith(other))); }
/*    */ 
/*    */   
/*    */   private ArrayList<Matcher<? super T>> templatedListWith(Matcher<? super T> other) {
/* 37 */     ArrayList<Matcher<? super T>> matchers = new ArrayList<Matcher<? super T>>();
/* 38 */     matchers.add(this.matcher);
/* 39 */     matchers.add(other);
/* 40 */     return matchers;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Factory
/* 51 */   public static <LHS> CombinableBothMatcher<LHS> both(Matcher<? super LHS> matcher) { return new CombinableBothMatcher<LHS>(matcher); }
/*    */   
/*    */   public static final class CombinableBothMatcher<X>
/*    */   {
/*    */     private final Matcher<? super X> first;
/*    */     
/* 57 */     public CombinableBothMatcher(Matcher<? super X> matcher) { this.first = matcher; }
/*    */ 
/*    */     
/* 60 */     public CombinableMatcher<X> and(Matcher<? super X> other) { return (new CombinableMatcher<X>(this.first)).and(other); }
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
/* 72 */   public static <LHS> CombinableEitherMatcher<LHS> either(Matcher<? super LHS> matcher) { return new CombinableEitherMatcher<LHS>(matcher); }
/*    */   
/*    */   public static final class CombinableEitherMatcher<X>
/*    */   {
/*    */     private final Matcher<? super X> first;
/*    */     
/* 78 */     public CombinableEitherMatcher(Matcher<? super X> matcher) { this.first = matcher; }
/*    */ 
/*    */     
/* 81 */     public CombinableMatcher<X> or(Matcher<? super X> other) { return (new CombinableMatcher<X>(this.first)).or(other); }
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\hamcrest\core\CombinableMatcher.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */