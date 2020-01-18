/*    */ package org.apache.commons.io.output;
/*    */ 
/*    */ import java.io.FilterWriter;
/*    */ import java.io.IOException;
/*    */ import java.io.Writer;
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
/*    */ public class ProxyWriter
/*    */   extends FilterWriter
/*    */ {
/* 42 */   public ProxyWriter(Writer proxy) { super(proxy); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 48 */   public void write(int idx) throws IOException { this.out.write(idx); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 53 */   public void write(char[] chr) throws IOException { this.out.write(chr); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 58 */   public void write(char[] chr, int st, int end) throws IOException { this.out.write(chr, st, end); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 63 */   public void write(String str) throws IOException { this.out.write(str); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 68 */   public void write(String str, int st, int end) throws IOException { this.out.write(str, st, end); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 73 */   public void flush() throws IOException { this.out.flush(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 78 */   public void close() throws IOException { this.out.close(); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\commons\io\output\ProxyWriter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.2
 */