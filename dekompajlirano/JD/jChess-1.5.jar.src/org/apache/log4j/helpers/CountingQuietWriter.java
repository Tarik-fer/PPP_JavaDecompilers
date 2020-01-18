/*    */ package org.apache.log4j.helpers;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.Writer;
/*    */ import org.apache.log4j.spi.ErrorHandler;
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
/*    */ public class CountingQuietWriter
/*    */   extends QuietWriter
/*    */ {
/*    */   protected long count;
/*    */   
/* 39 */   public CountingQuietWriter(Writer writer, ErrorHandler eh) { super(writer, eh); }
/*    */ 
/*    */ 
/*    */   
/*    */   public void write(String string) {
/*    */     try {
/* 45 */       this.out.write(string);
/* 46 */       this.count += string.length();
/*    */     }
/* 48 */     catch (IOException e) {
/* 49 */       this.errorHandler.error("Write failure.", e, 1);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 55 */   public long getCount() { return this.count; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 60 */   public void setCount(long count) { this.count = count; }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\log4j\helpers\CountingQuietWriter.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.2
 */