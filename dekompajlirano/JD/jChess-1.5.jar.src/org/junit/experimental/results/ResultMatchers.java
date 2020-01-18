/*    */ package org.junit.experimental.results;
/*    */ 
/*    */ import org.hamcrest.BaseMatcher;
/*    */ import org.hamcrest.Description;
/*    */ import org.hamcrest.Matcher;
/*    */ import org.hamcrest.TypeSafeMatcher;
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
/*    */ public class ResultMatchers
/*    */ {
/* 21 */   public static Matcher<PrintableResult> isSuccessful() { return failureCountIs(0); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Matcher<PrintableResult> failureCountIs(final int count) {
/* 28 */     return (Matcher<PrintableResult>)new TypeSafeMatcher<PrintableResult>()
/*    */       {
/* 30 */         public void describeTo(Description description) { description.appendText("has " + count + " failures"); }
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 35 */         public boolean matchesSafely(PrintableResult item) { return (item.failureCount() == count); }
/*    */       };
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Matcher<Object> hasSingleFailureContaining(final String string) {
/* 44 */     return (Matcher<Object>)new BaseMatcher<Object>()
/*    */       {
/* 46 */         public boolean matches(Object item) { return (item.toString().contains(string) && ResultMatchers.failureCountIs(1).matches(item)); }
/*    */ 
/*    */ 
/*    */         
/* 50 */         public void describeTo(Description description) { description.appendText("has single failure containing " + string); }
/*    */       };
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Matcher<PrintableResult> hasFailureContaining(final String string) {
/* 60 */     return (Matcher<PrintableResult>)new BaseMatcher<PrintableResult>()
/*    */       {
/* 62 */         public boolean matches(Object item) { return item.toString().contains(string); }
/*    */ 
/*    */ 
/*    */         
/* 66 */         public void describeTo(Description description) { description.appendText("has failure containing " + string); }
/*    */       };
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\experimental\results\ResultMatchers.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */