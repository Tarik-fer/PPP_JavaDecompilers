/*    */ package org.junit;
/*    */ 
/*    */ import org.hamcrest.Matcher;
/*    */ import org.junit.internal.AssumptionViolatedException;
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
/*    */ public class AssumptionViolatedException
/*    */   extends AssumptionViolatedException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/* 22 */   public <T> AssumptionViolatedException(T actual, Matcher<T> matcher) { super(actual, matcher); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 30 */   public <T> AssumptionViolatedException(String message, T expected, Matcher<T> matcher) { super(message, expected, matcher); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 37 */   public AssumptionViolatedException(String message) { super(message); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 44 */   public AssumptionViolatedException(String assumption, Throwable t) { super(assumption, t); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\AssumptionViolatedException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */