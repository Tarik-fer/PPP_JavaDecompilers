/*    */ package org.hamcrest.core;
/*    */ 
/*    */ import java.lang.reflect.Array;
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
/*    */ 
/*    */ public class IsEqual<T>
/*    */   extends BaseMatcher<T>
/*    */ {
/*    */   private final Object expectedValue;
/*    */   
/* 21 */   public IsEqual(T equalArg) { this.expectedValue = equalArg; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 26 */   public boolean matches(Object actualValue) { return areEqual(actualValue, this.expectedValue); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 31 */   public void describeTo(Description description) { description.appendValue(this.expectedValue); }
/*    */ 
/*    */   
/*    */   private static boolean areEqual(Object actual, Object expected) {
/* 35 */     if (actual == null) {
/* 36 */       return (expected == null);
/*    */     }
/*    */     
/* 39 */     if (expected != null && isArray(actual)) {
/* 40 */       return (isArray(expected) && areArraysEqual(actual, expected));
/*    */     }
/*    */     
/* 43 */     return actual.equals(expected);
/*    */   }
/*    */ 
/*    */   
/* 47 */   private static boolean areArraysEqual(Object actualArray, Object expectedArray) { return (areArrayLengthsEqual(actualArray, expectedArray) && areArrayElementsEqual(actualArray, expectedArray)); }
/*    */ 
/*    */ 
/*    */   
/* 51 */   private static boolean areArrayLengthsEqual(Object actualArray, Object expectedArray) { return (Array.getLength(actualArray) == Array.getLength(expectedArray)); }
/*    */ 
/*    */   
/*    */   private static boolean areArrayElementsEqual(Object actualArray, Object expectedArray) {
/* 55 */     for (int i = 0; i < Array.getLength(actualArray); i++) {
/* 56 */       if (!areEqual(Array.get(actualArray, i), Array.get(expectedArray, i))) {
/* 57 */         return false;
/*    */       }
/*    */     } 
/* 60 */     return true;
/*    */   }
/*    */ 
/*    */   
/* 64 */   private static boolean isArray(Object o) { return o.getClass().isArray(); }
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
/* 92 */   public static <T> Matcher<T> equalTo(T operand) { return (Matcher<T>)new IsEqual<T>(operand); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\hamcrest\core\IsEqual.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */