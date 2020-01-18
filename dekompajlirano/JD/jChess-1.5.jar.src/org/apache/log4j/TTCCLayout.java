/*     */ package org.apache.log4j;
/*     */ 
/*     */ import org.apache.log4j.helpers.DateLayout;
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
/*     */ public class TTCCLayout
/*     */   extends DateLayout
/*     */ {
/*     */   private boolean threadPrinting = true;
/*     */   private boolean categoryPrefixing = true;
/*     */   private boolean contextPrinting = true;
/*  82 */   protected final StringBuffer buf = new StringBuffer(256);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  92 */   public TTCCLayout() { setDateFormat("RELATIVE", null); }
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
/* 105 */   public TTCCLayout(String dateFormatType) { setDateFormat(dateFormatType); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 115 */   public void setThreadPrinting(boolean threadPrinting) { this.threadPrinting = threadPrinting; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 123 */   public boolean getThreadPrinting() { return this.threadPrinting; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 132 */   public void setCategoryPrefixing(boolean categoryPrefixing) { this.categoryPrefixing = categoryPrefixing; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 140 */   public boolean getCategoryPrefixing() { return this.categoryPrefixing; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 150 */   public void setContextPrinting(boolean contextPrinting) { this.contextPrinting = contextPrinting; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 158 */   public boolean getContextPrinting() { return this.contextPrinting; }
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
/*     */   public String format(LoggingEvent event) {
/* 176 */     this.buf.setLength(0);
/*     */     
/* 178 */     dateFormat(this.buf, event);
/*     */     
/* 180 */     if (this.threadPrinting) {
/* 181 */       this.buf.append('[');
/* 182 */       this.buf.append(event.getThreadName());
/* 183 */       this.buf.append("] ");
/*     */     } 
/* 185 */     this.buf.append(event.getLevel().toString());
/* 186 */     this.buf.append(' ');
/*     */     
/* 188 */     if (this.categoryPrefixing) {
/* 189 */       this.buf.append(event.getLoggerName());
/* 190 */       this.buf.append(' ');
/*     */     } 
/*     */     
/* 193 */     if (this.contextPrinting) {
/* 194 */       String ndc = event.getNDC();
/*     */       
/* 196 */       if (ndc != null) {
/* 197 */         this.buf.append(ndc);
/* 198 */         this.buf.append(' ');
/*     */       } 
/*     */     } 
/* 201 */     this.buf.append("- ");
/* 202 */     this.buf.append(event.getRenderedMessage());
/* 203 */     this.buf.append(LINE_SEP);
/* 204 */     return this.buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 215 */   public boolean ignoresThrowable() { return true; }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\log4j\TTCCLayout.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.2
 */