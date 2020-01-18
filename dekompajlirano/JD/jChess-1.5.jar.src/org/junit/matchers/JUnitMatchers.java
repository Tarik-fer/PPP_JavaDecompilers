/*     */ package org.junit.matchers;
/*     */ 
/*     */ import org.hamcrest.CoreMatchers;
/*     */ import org.hamcrest.Matcher;
/*     */ import org.hamcrest.core.CombinableMatcher;
/*     */ import org.junit.internal.matchers.StacktracePrintingMatcher;
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
/*     */ public class JUnitMatchers
/*     */ {
/*     */   @Deprecated
/*  22 */   public static <T> Matcher<Iterable<? super T>> hasItem(T element) { return CoreMatchers.hasItem(element); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*  31 */   public static <T> Matcher<Iterable<? super T>> hasItem(Matcher<? super T> elementMatcher) { return CoreMatchers.hasItem(elementMatcher); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*  40 */   public static <T> Matcher<Iterable<T>> hasItems(T... elements) { return CoreMatchers.hasItems((Object[])elements); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*  51 */   public static <T> Matcher<Iterable<T>> hasItems(Matcher<? super T>... elementMatchers) { return CoreMatchers.hasItems((Matcher[])elementMatchers); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*  60 */   public static <T> Matcher<Iterable<T>> everyItem(Matcher<T> elementMatcher) { return CoreMatchers.everyItem(elementMatcher); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*  69 */   public static Matcher<String> containsString(String substring) { return CoreMatchers.containsString(substring); }
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
/*     */   @Deprecated
/*  82 */   public static <T> CombinableMatcher.CombinableBothMatcher<T> both(Matcher<? super T> matcher) { return CoreMatchers.both(matcher); }
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
/*     */   @Deprecated
/*  95 */   public static <T> CombinableMatcher.CombinableEitherMatcher<T> either(Matcher<? super T> matcher) { return CoreMatchers.either(matcher); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 103 */   public static <T extends Throwable> Matcher<T> isThrowable(Matcher<T> throwableMatcher) { return StacktracePrintingMatcher.isThrowable(throwableMatcher); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 111 */   public static <T extends Exception> Matcher<T> isException(Matcher<T> exceptionMatcher) { return StacktracePrintingMatcher.isException(exceptionMatcher); }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\matchers\JUnitMatchers.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */