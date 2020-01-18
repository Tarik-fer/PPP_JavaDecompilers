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
/*    */ public class IsSame<T>
/*    */   extends BaseMatcher<T>
/*    */ {
/*    */   private final T object;
/*    */   
/* 18 */   public IsSame(T object) { this.object = object; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 23 */   public boolean matches(Object arg) { return (arg == this.object); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 28 */   public void describeTo(Description description) { description.appendText("sameInstance(").appendValue(this.object).appendText(")"); }
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
/* 42 */   public static <T> Matcher<T> sameInstance(T target) { return (Matcher<T>)new IsSame<T>(target); }
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
/* 54 */   public static <T> Matcher<T> theInstance(T target) { return (Matcher<T>)new IsSame<T>(target); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\hamcrest\core\IsSame.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */