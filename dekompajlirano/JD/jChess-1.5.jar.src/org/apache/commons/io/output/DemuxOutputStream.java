/*     */ package org.apache.commons.io.output;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
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
/*     */ public class DemuxOutputStream
/*     */   extends OutputStream
/*     */ {
/*  32 */   private InheritableThreadLocal m_streams = new InheritableThreadLocal();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OutputStream bindStream(OutputStream output) {
/*  42 */     OutputStream stream = getStream();
/*  43 */     this.m_streams.set(output);
/*  44 */     return stream;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/*  55 */     OutputStream output = getStream();
/*  56 */     if (null != output)
/*     */     {
/*  58 */       output.close();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void flush() throws IOException {
/*  70 */     OutputStream output = getStream();
/*  71 */     if (null != output)
/*     */     {
/*  73 */       output.flush();
/*     */     }
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
/*     */   public void write(int ch) throws IOException {
/*  86 */     OutputStream output = getStream();
/*  87 */     if (null != output)
/*     */     {
/*  89 */       output.write(ch);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   private OutputStream getStream() { return this.m_streams.get(); }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\commons\io\output\DemuxOutputStream.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.2
 */