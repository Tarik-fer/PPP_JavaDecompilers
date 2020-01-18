/*    */ package org.hamcrest.core;
/*    */ 
/*    */ import org.hamcrest.BaseMatcher;
/*    */ import org.hamcrest.Description;
/*    */ import org.hamcrest.Factory;
/*    */ import org.hamcrest.Matcher;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IsAnything<T>
/*    */   extends BaseMatcher<T>
/*    */ {
/*    */   private final String message;
/*    */   
/* 19 */   public IsAnything() { this("ANYTHING"); }
/*    */ 
/*    */ 
/*    */   
/* 23 */   public IsAnything(String message) { this.message = message; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 28 */   public boolean matches(Object o) { return true; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 33 */   public void describeTo(Description description) { description.appendText(this.message); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Factory
/* 41 */   public static Matcher<Object> anything() { return (Matcher<Object>)new IsAnything(); }
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
/* 53 */   public static Matcher<Object> anything(String description) { return (Matcher<Object>)new IsAnything(description); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\hamcrest\core\IsAnything.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */