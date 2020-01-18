/*     */ package org.apache.commons.io.filefilter;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.List;
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
/*     */ public class SuffixFileFilter
/*     */   extends AbstractFileFilter
/*     */ {
/*     */   private String[] suffixes;
/*     */   
/*     */   public SuffixFileFilter(String suffix) {
/*  57 */     if (suffix == null) {
/*  58 */       throw new IllegalArgumentException("The suffix must not be null");
/*     */     }
/*  60 */     this.suffixes = new String[] { suffix };
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
/*     */   public SuffixFileFilter(String[] suffixes) {
/*  73 */     if (suffixes == null) {
/*  74 */       throw new IllegalArgumentException("The array of suffixes must not be null");
/*     */     }
/*  76 */     this.suffixes = suffixes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SuffixFileFilter(List suffixes) {
/*  87 */     if (suffixes == null) {
/*  88 */       throw new IllegalArgumentException("The list of suffixes must not be null");
/*     */     }
/*  90 */     this.suffixes = (String[])suffixes.toArray((Object[])new String[suffixes.size()]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean accept(File file) {
/* 100 */     String name = file.getName();
/* 101 */     for (int i = 0; i < this.suffixes.length; i++) {
/* 102 */       if (name.endsWith(this.suffixes[i])) {
/* 103 */         return true;
/*     */       }
/*     */     } 
/* 106 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean accept(File file, String name) {
/* 117 */     for (int i = 0; i < this.suffixes.length; i++) {
/* 118 */       if (name.endsWith(this.suffixes[i])) {
/* 119 */         return true;
/*     */       }
/*     */     } 
/* 122 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\commons\io\filefilter\SuffixFileFilter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.2
 */