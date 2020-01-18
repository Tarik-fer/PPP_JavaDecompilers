/*     */ package org.junit;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import org.hamcrest.CoreMatchers;
/*     */ import org.hamcrest.Matcher;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Assume
/*     */ {
/*  41 */   public static void assumeTrue(boolean b) { assumeThat(Boolean.valueOf(b), CoreMatchers.is(Boolean.valueOf(true))); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   public static void assumeFalse(boolean b) { assumeTrue(!b); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   public static void assumeTrue(String message, boolean b) { if (!b) throw new AssumptionViolatedException(message);
/*     */      }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   public static void assumeFalse(String message, boolean b) { assumeTrue(message, !b); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  73 */   public static void assumeNotNull(Object... objects) { assumeThat(Arrays.asList(objects), CoreMatchers.everyItem(CoreMatchers.notNullValue())); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T> void assumeThat(T actual, Matcher<T> matcher) {
/*  94 */     if (!matcher.matches(actual)) {
/*  95 */       throw new AssumptionViolatedException(actual, matcher);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T> void assumeThat(String message, T actual, Matcher<T> matcher) {
/* 117 */     if (!matcher.matches(actual)) {
/* 118 */       throw new AssumptionViolatedException(message, actual, matcher);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 142 */   public static void assumeNoException(Throwable e) { assumeThat(e, CoreMatchers.nullValue()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 156 */   public static void assumeNoException(String message, Throwable e) { assumeThat(message, e, CoreMatchers.nullValue()); }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\Assume.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */