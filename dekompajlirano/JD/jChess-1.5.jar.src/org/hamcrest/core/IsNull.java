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
/*    */ public class IsNull<T>
/*    */   extends BaseMatcher<T>
/*    */ {
/* 17 */   public boolean matches(Object o) { return (o == null); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 22 */   public void describeTo(Description description) { description.appendText("null"); }
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
/* 34 */   public static Matcher<Object> nullValue() { return (Matcher<Object>)new IsNull(); }
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
/* 48 */   public static Matcher<Object> notNullValue() { return IsNot.not(nullValue()); }
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
/* 63 */   public static <T> Matcher<T> nullValue(Class<T> type) { return (Matcher<T>)new IsNull(); }
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
/*    */   
/*    */   @Factory
/* 81 */   public static <T> Matcher<T> notNullValue(Class<T> type) { return IsNot.not(nullValue(type)); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\hamcrest\core\IsNull.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */