/*    */ package org.apache.commons.io.output;
/*    */ 
/*    */ import java.io.FilterOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
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
/*    */ public class ProxyOutputStream
/*    */   extends FilterOutputStream
/*    */ {
/* 40 */   public ProxyOutputStream(OutputStream proxy) { super(proxy); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 46 */   public void write(int idx) throws IOException { this.out.write(idx); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 51 */   public void write(byte[] bts) throws IOException { this.out.write(bts); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 56 */   public void write(byte[] bts, int st, int end) throws IOException { this.out.write(bts, st, end); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 61 */   public void flush() throws IOException { this.out.flush(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 66 */   public void close() throws IOException { this.out.close(); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\commons\io\output\ProxyOutputStream.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.2
 */