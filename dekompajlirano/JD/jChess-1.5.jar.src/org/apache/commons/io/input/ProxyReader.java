/*    */ package org.apache.commons.io.input;
/*    */ 
/*    */ import java.io.FilterReader;
/*    */ import java.io.IOException;
/*    */ import java.io.Reader;
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
/*    */ public abstract class ProxyReader
/*    */   extends FilterReader
/*    */ {
/* 43 */   public ProxyReader(Reader proxy) { super(proxy); }
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
/* 54 */   public int read(char[] chr) throws IOException { return this.in.read(chr); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 59 */   public int read(char[] chr, int st, int end) throws IOException { return this.in.read(chr, st, end); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 64 */   public long skip(long ln) throws IOException { return this.in.skip(ln); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 69 */   public boolean ready() throws IOException { return this.in.ready(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 74 */   public void close() throws IOException { this.in.close(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 79 */   public synchronized void mark(int idx) throws IOException { this.in.mark(idx); }
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


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\commons\io\input\ProxyReader.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.2
 */