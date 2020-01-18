/*    */ package org.hamcrest.internal;
/*    */ 
/*    */ import java.lang.reflect.Array;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class ArrayIterator implements Iterator<Object> {
/*    */   private final Object array;
/*  8 */   private int currentIndex = 0;
/*    */   
/*    */   public ArrayIterator(Object array) {
/* 11 */     if (!array.getClass().isArray()) {
/* 12 */       throw new IllegalArgumentException("not an array");
/*    */     }
/* 14 */     this.array = array;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 19 */   public boolean hasNext() { return (this.currentIndex < Array.getLength(this.array)); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 24 */   public Object next() { return Array.get(this.array, this.currentIndex++); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 29 */   public void remove() { throw new UnsupportedOperationException("cannot remove items from an array"); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\hamcrest\internal\ArrayIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */