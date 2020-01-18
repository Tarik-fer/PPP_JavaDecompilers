/*     */ package org.apache.log4j.helpers;
/*     */ 
/*     */ import org.apache.log4j.spi.LoggingEvent;
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
/*     */ public class BoundedFIFO
/*     */ {
/*     */   LoggingEvent[] buf;
/*  34 */   int numElements = 0;
/*  35 */   int first = 0;
/*  36 */   int next = 0;
/*     */ 
/*     */   
/*     */   int maxSize;
/*     */ 
/*     */ 
/*     */   
/*     */   public BoundedFIFO(int maxSize) {
/*  44 */     if (maxSize < 1) {
/*  45 */       throw new IllegalArgumentException("The maxSize argument (" + maxSize + ") is not a positive integer.");
/*     */     }
/*     */     
/*  48 */     this.maxSize = maxSize;
/*  49 */     this.buf = new LoggingEvent[maxSize];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LoggingEvent get() {
/*  57 */     if (this.numElements == 0) {
/*  58 */       return null;
/*     */     }
/*  60 */     LoggingEvent r = this.buf[this.first];
/*  61 */     this.buf[this.first] = null;
/*     */     
/*  63 */     if (++this.first == this.maxSize) {
/*  64 */       this.first = 0;
/*     */     }
/*  66 */     this.numElements--;
/*  67 */     return r;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void put(LoggingEvent o) {
/*  76 */     if (this.numElements != this.maxSize) {
/*  77 */       this.buf[this.next] = o;
/*  78 */       if (++this.next == this.maxSize) {
/*  79 */         this.next = 0;
/*     */       }
/*  81 */       this.numElements++;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   public int getMaxSize() { return this.maxSize; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  98 */   public boolean isFull() { return (this.numElements == this.maxSize); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   public int length() { return this.numElements; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 113 */   int min(int a, int b) { return (a < b) ? a : b; }
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
/*     */   public synchronized void resize(int newSize) {
/* 126 */     if (newSize == this.maxSize) {
/*     */       return;
/*     */     }
/*     */     
/* 130 */     LoggingEvent[] tmp = new LoggingEvent[newSize];
/*     */ 
/*     */     
/* 133 */     int len1 = this.maxSize - this.first;
/*     */ 
/*     */     
/* 136 */     len1 = min(len1, newSize);
/*     */ 
/*     */ 
/*     */     
/* 140 */     len1 = min(len1, this.numElements);
/*     */ 
/*     */     
/* 143 */     System.arraycopy(this.buf, this.first, tmp, 0, len1);
/*     */ 
/*     */     
/* 146 */     int len2 = 0;
/* 147 */     if (len1 < this.numElements && len1 < newSize) {
/* 148 */       len2 = this.numElements - len1;
/* 149 */       len2 = min(len2, newSize - len1);
/* 150 */       System.arraycopy(this.buf, 0, tmp, len1, len2);
/*     */     } 
/*     */     
/* 153 */     this.buf = tmp;
/* 154 */     this.maxSize = newSize;
/* 155 */     this.first = 0;
/* 156 */     this.numElements = len1 + len2;
/* 157 */     this.next = this.numElements;
/* 158 */     if (this.next == this.maxSize) {
/* 159 */       this.next = 0;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 169 */   public boolean wasEmpty() { return (this.numElements == 1); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 178 */   public boolean wasFull() { return (this.numElements + 1 == this.maxSize); }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\log4j\helpers\BoundedFIFO.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.2
 */