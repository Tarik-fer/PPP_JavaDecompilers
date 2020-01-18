/*    */ package org.junit.rules;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.hamcrest.CoreMatchers;
/*    */ import org.hamcrest.Matcher;
/*    */ import org.junit.matchers.JUnitMatchers;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class ExpectedExceptionMatcherBuilder
/*    */ {
/* 16 */   private final List<Matcher<?>> matchers = new ArrayList<Matcher<?>>();
/*    */ 
/*    */   
/* 19 */   void add(Matcher<?> matcher) { this.matchers.add(matcher); }
/*    */ 
/*    */ 
/*    */   
/* 23 */   boolean expectsThrowable() { return !this.matchers.isEmpty(); }
/*    */ 
/*    */ 
/*    */   
/* 27 */   Matcher<Throwable> build() { return JUnitMatchers.isThrowable(allOfTheMatchers()); }
/*    */ 
/*    */   
/*    */   private Matcher<Throwable> allOfTheMatchers() {
/* 31 */     if (this.matchers.size() == 1) {
/* 32 */       return cast(this.matchers.get(0));
/*    */     }
/* 34 */     return CoreMatchers.allOf(castedMatchers());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 39 */   private List<Matcher<? super Throwable>> castedMatchers() { return (List)new ArrayList<Matcher<?>>(this.matchers); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 44 */   private Matcher<Throwable> cast(Matcher<?> singleMatcher) { return (Matcher)singleMatcher; }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\rules\ExpectedExceptionMatcherBuilder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */