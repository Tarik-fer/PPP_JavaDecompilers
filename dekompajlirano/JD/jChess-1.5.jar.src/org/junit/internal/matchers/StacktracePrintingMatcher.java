/*    */ package org.junit.internal.matchers;
/*    */ 
/*    */ import java.io.PrintWriter;
/*    */ import java.io.StringWriter;
/*    */ import org.hamcrest.Description;
/*    */ import org.hamcrest.Factory;
/*    */ import org.hamcrest.Matcher;
/*    */ import org.hamcrest.TypeSafeMatcher;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StacktracePrintingMatcher<T extends Throwable>
/*    */   extends TypeSafeMatcher<T>
/*    */ {
/*    */   private final Matcher<T> throwableMatcher;
/*    */   
/* 20 */   public StacktracePrintingMatcher(Matcher<T> throwableMatcher) { this.throwableMatcher = throwableMatcher; }
/*    */ 
/*    */ 
/*    */   
/* 24 */   public void describeTo(Description description) { this.throwableMatcher.describeTo(description); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 29 */   protected boolean matchesSafely(T item) { return this.throwableMatcher.matches(item); }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void describeMismatchSafely(T item, Description description) {
/* 34 */     this.throwableMatcher.describeMismatch(item, description);
/* 35 */     description.appendText("\nStacktrace was: ");
/* 36 */     description.appendText(readStacktrace((Throwable)item));
/*    */   }
/*    */   
/*    */   private String readStacktrace(Throwable throwable) {
/* 40 */     StringWriter stringWriter = new StringWriter();
/* 41 */     throwable.printStackTrace(new PrintWriter(stringWriter));
/* 42 */     return stringWriter.toString();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Factory
/* 48 */   public static <T extends Throwable> Matcher<T> isThrowable(Matcher<T> throwableMatcher) { return (Matcher<T>)new StacktracePrintingMatcher<T>(throwableMatcher); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Factory
/* 54 */   public static <T extends Exception> Matcher<T> isException(Matcher<T> exceptionMatcher) { return (Matcher)new StacktracePrintingMatcher<Throwable>((Matcher)exceptionMatcher); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\internal\matchers\StacktracePrintingMatcher.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */