/*    */ package org.hamcrest;
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
/*    */ public abstract class Condition<T>
/*    */ {
/* 14 */   public static final NotMatched<Object> NOT_MATCHED = new NotMatched();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private Condition() {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 25 */   public final boolean matching(Matcher<T> match) { return matching(match, ""); }
/* 26 */   public final <U> Condition<U> then(Step<? super T, U> mapping) { return and(mapping); }
/*    */ 
/*    */ 
/*    */   
/* 30 */   public static <T> Condition<T> notMatched() { return NOT_MATCHED; }
/*    */ 
/*    */ 
/*    */   
/* 34 */   public static <T> Condition<T> matched(T theValue, Description mismatch) { return new Matched<T>(theValue, mismatch); }
/*    */   public abstract boolean matching(Matcher<T> paramMatcher, String paramString);
/*    */   public abstract <U> Condition<U> and(Step<? super T, U> paramStep);
/*    */   public static interface Step<I, O> {
/*    */     Condition<O> apply(I param1I, Description param1Description); }
/*    */   private static final class Matched<T> extends Condition<T> { private final T theValue;
/*    */     
/*    */     private Matched(T theValue, Description mismatch) {
/* 42 */       this.theValue = theValue;
/* 43 */       this.mismatch = mismatch;
/*    */     }
/*    */     private final Description mismatch;
/*    */     
/*    */     public boolean matching(Matcher<T> matcher, String message) {
/* 48 */       if (matcher.matches(this.theValue)) {
/* 49 */         return true;
/*    */       }
/* 51 */       this.mismatch.appendText(message);
/* 52 */       matcher.describeMismatch(this.theValue, this.mismatch);
/* 53 */       return false;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 58 */     public <U> Condition<U> and(Condition.Step<? super T, U> next) { return next.apply(this.theValue, this.mismatch); } }
/*    */   
/*    */   private static final class NotMatched<T> extends Condition<T> {
/*    */     private NotMatched() {}
/*    */     
/* 63 */     public boolean matching(Matcher<T> match, String message) { return false; }
/*    */ 
/*    */     
/* 66 */     public <U> Condition<U> and(Condition.Step<? super T, U> mapping) { return notMatched(); }
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\hamcrest\Condition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */