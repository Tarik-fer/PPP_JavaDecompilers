/*    */ package org.hamcrest.internal;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import org.hamcrest.SelfDescribing;
/*    */ 
/*    */ public class SelfDescribingValueIterator<T>
/*    */   implements Iterator<SelfDescribing>
/*    */ {
/*    */   private Iterator<T> values;
/*    */   
/* 11 */   public SelfDescribingValueIterator(Iterator<T> values) { this.values = values; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 16 */   public boolean hasNext() { return this.values.hasNext(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 21 */   public SelfDescribing next() { return new SelfDescribingValue(this.values.next()); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 26 */   public void remove() { this.values.remove(); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\hamcrest\internal\SelfDescribingValueIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */