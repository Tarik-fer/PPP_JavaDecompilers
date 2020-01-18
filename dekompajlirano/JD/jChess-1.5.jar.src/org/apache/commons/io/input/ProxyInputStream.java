/*    */ package org.apache.commons.io.input;
/*    */ 
/*    */ import java.io.FilterInputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
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
/*    */ public abstract class ProxyInputStream
/*    */   extends FilterInputStream
/*    */ {
/* 43 */   public ProxyInputStream(InputStream proxy) { super(proxy); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 49 */   public int read() throws IOException { return this.in.read(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 54 */   public int read(byte[] bts) throws IOException { return this.in.read(bts); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 59 */   public int read(byte[] bts, int st, int end) throws IOException { return this.in.read(bts, st, end); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 64 */   public long skip(long ln) throws IOException { return this.in.skip(ln); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 69 */   public int available() throws IOException { return this.in.available(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 74 */   public void close() throws IOException { this.in.close(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 79 */   public synchronized void mark(int idx) { this.in.mark(idx); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 84 */   public synchronized void reset() throws IOException { this.in.reset(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 89 */   public boolean markSupported() { return this.in.markSupported(); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\commons\io\input\ProxyInputStream.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.2
 */