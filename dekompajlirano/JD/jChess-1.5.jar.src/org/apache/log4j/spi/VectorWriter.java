/*    */ package org.apache.log4j.spi;
/*    */ 
/*    */ import java.io.PrintWriter;
/*    */ import java.util.Vector;
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
/*    */ class VectorWriter
/*    */   extends PrintWriter
/*    */ {
/*    */   private Vector v;
/*    */   
/*    */   VectorWriter() {
/* 36 */     super(new NullWriter());
/* 37 */     this.v = new Vector();
/*    */   }
/*    */ 
/*    */   
/* 41 */   public void print(Object o) { this.v.addElement(String.valueOf(o)); }
/*    */ 
/*    */ 
/*    */   
/* 45 */   public void print(char[] chars) { this.v.addElement(new String(chars)); }
/*    */ 
/*    */ 
/*    */   
/* 49 */   public void print(String s) { this.v.addElement(s); }
/*    */ 
/*    */ 
/*    */   
/* 53 */   public void println(Object o) { this.v.addElement(String.valueOf(o)); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 60 */   public void println(char[] chars) { this.v.addElement(new String(chars)); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 65 */   public void println(String s) { this.v.addElement(s); }
/*    */ 
/*    */ 
/*    */   
/* 69 */   public void write(char[] chars) { this.v.addElement(new String(chars)); }
/*    */ 
/*    */ 
/*    */   
/* 73 */   public void write(char[] chars, int off, int len) { this.v.addElement(new String(chars, off, len)); }
/*    */ 
/*    */ 
/*    */   
/* 77 */   public void write(String s, int off, int len) { this.v.addElement(s.substring(off, off + len)); }
/*    */ 
/*    */ 
/*    */   
/* 81 */   public void write(String s) { this.v.addElement(s); }
/*    */ 
/*    */   
/*    */   public String[] toStringArray() {
/* 85 */     int len = this.v.size();
/* 86 */     String[] sa = new String[len];
/* 87 */     for (int i = 0; i < len; i++) {
/* 88 */       sa[i] = this.v.elementAt(i);
/*    */     }
/* 90 */     return sa;
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\log4j\spi\VectorWriter.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.2
 */