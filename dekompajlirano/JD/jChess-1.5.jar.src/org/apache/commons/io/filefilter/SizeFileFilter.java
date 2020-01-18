/*    */ package org.apache.commons.io.filefilter;
/*    */ 
/*    */ import java.io.File;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SizeFileFilter
/*    */   extends AbstractFileFilter
/*    */ {
/*    */   private long size;
/*    */   private boolean acceptLarger;
/*    */   
/* 55 */   public SizeFileFilter(long size) { this(size, true); }
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
/*    */   public SizeFileFilter(long size, boolean acceptLarger) {
/* 68 */     if (size < 0L) {
/* 69 */       throw new IllegalArgumentException("The size must be non-negative");
/*    */     }
/* 71 */     this.size = size;
/* 72 */     this.acceptLarger = acceptLarger;
/*    */   }
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
/*    */   
/*    */   public boolean accept(File file) {
/* 88 */     boolean smaller = (file.length() < this.size);
/* 89 */     return this.acceptLarger ? (!smaller) : smaller;
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\commons\io\filefilter\SizeFileFilter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.2
 */