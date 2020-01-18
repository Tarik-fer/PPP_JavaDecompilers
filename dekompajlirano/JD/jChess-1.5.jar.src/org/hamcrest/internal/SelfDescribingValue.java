/*    */ package org.hamcrest.internal;
/*    */ 
/*    */ import org.hamcrest.Description;
/*    */ import org.hamcrest.SelfDescribing;
/*    */ 
/*    */ public class SelfDescribingValue<T>
/*    */   implements SelfDescribing {
/*    */   private T value;
/*    */   
/* 10 */   public SelfDescribingValue(T value) { this.value = value; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 15 */   public void describeTo(Description description) { description.appendValue(this.value); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\hamcrest\internal\SelfDescribingValue.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */