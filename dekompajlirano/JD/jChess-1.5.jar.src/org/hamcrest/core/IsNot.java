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
/*    */ public class IsNot<T>
/*    */   extends BaseMatcher<T>
/*    */ {
/*    */   private final Matcher<T> matcher;
/*    */   
/* 20 */   public IsNot(Matcher<T> matcher) { this.matcher = matcher; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 25 */   public boolean matches(Object arg) { return !this.matcher.matches(arg); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 30 */   public void describeTo(Description description) { description.appendText("not ").appendDescriptionOf((SelfDescribing)this.matcher); }
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
/* 46 */   public static <T> Matcher<T> not(Matcher<T> matcher) { return (Matcher<T>)new IsNot<T>(matcher); }
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
/* 62 */   public static <T> Matcher<T> not(T value) { return not(IsEqual.equalTo(value)); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\hamcrest\core\IsNot.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */