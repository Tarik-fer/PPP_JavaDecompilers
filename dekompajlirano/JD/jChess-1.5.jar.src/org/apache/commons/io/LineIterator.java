/*     */ package org.apache.commons.io;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
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
/*     */ public class LineIterator
/*     */   implements Iterator
/*     */ {
/*     */   private final BufferedReader bufferedReader;
/*     */   private String cachedLine;
/*     */   private boolean finished = false;
/*     */   
/*     */   public LineIterator(Reader reader) throws IllegalArgumentException {
/*  69 */     if (reader == null) {
/*  70 */       throw new IllegalArgumentException("Reader must not be null");
/*     */     }
/*  72 */     if (reader instanceof BufferedReader) {
/*  73 */       this.bufferedReader = (BufferedReader)reader;
/*     */     } else {
/*  75 */       this.bufferedReader = new BufferedReader(reader);
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
/*     */   
/*     */   public boolean hasNext() {
/*  89 */     if (this.cachedLine != null)
/*  90 */       return true; 
/*  91 */     if (this.finished)
/*  92 */       return false; 
/*     */     try {
/*     */       String line;
/*     */       do {
/*  96 */         line = this.bufferedReader.readLine();
/*  97 */         if (line == null)
/*  98 */         { this.finished = true;
/*  99 */           return false; } 
/* 100 */       } while (!isValidLine(line));
/* 101 */       this.cachedLine = line;
/* 102 */       return true;
/*     */     
/*     */     }
/* 105 */     catch (IOException ioe) {
/* 106 */       close();
/* 107 */       throw new IllegalStateException(ioe.toString());
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
/* 119 */   protected boolean isValidLine(String line) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 129 */   public Object next() { return nextLine(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String nextLine() {
/* 139 */     if (!hasNext()) {
/* 140 */       throw new NoSuchElementException("No more lines");
/*     */     }
/* 142 */     String currentLine = this.cachedLine;
/* 143 */     this.cachedLine = null;
/* 144 */     return currentLine;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/* 155 */     this.finished = true;
/* 156 */     IOUtils.closeQuietly(this.bufferedReader);
/* 157 */     this.cachedLine = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 166 */   public void remove() { throw new UnsupportedOperationException("Remove unsupported on LineIterator"); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void closeQuietly(LineIterator iterator) {
/* 176 */     if (iterator != null)
/* 177 */       iterator.close(); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\commons\io\LineIterator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.2
 */