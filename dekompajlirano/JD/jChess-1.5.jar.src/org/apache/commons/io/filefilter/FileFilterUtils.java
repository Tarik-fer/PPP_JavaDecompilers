/*     */ package org.apache.commons.io.filefilter;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileFilter;
/*     */ import java.io.FilenameFilter;
/*     */ import java.util.Date;
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
/*     */ public class FileFilterUtils
/*     */ {
/*     */   private static IOFileFilter cvsFilter;
/*     */   private static IOFileFilter svnFilter;
/*     */   
/*  53 */   public static IOFileFilter prefixFileFilter(String prefix) { return new PrefixFileFilter(prefix); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   public static IOFileFilter suffixFileFilter(String suffix) { return new SuffixFileFilter(suffix); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  73 */   public static IOFileFilter nameFileFilter(String name) { return new NameFileFilter(name); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   public static IOFileFilter directoryFileFilter() { return DirectoryFileFilter.DIRECTORY; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  91 */   public static IOFileFilter fileFileFilter() { return FileFileFilter.FILE; }
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
/* 103 */   public static IOFileFilter andFileFilter(IOFileFilter filter1, IOFileFilter filter2) { return new AndFileFilter(filter1, filter2); }
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
/* 114 */   public static IOFileFilter orFileFilter(IOFileFilter filter1, IOFileFilter filter2) { return new OrFileFilter(filter1, filter2); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 124 */   public static IOFileFilter notFileFilter(IOFileFilter filter) { return new NotFileFilter(filter); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 134 */   public static IOFileFilter trueFileFilter() { return TrueFileFilter.TRUE; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 143 */   public static IOFileFilter falseFileFilter() { return FalseFileFilter.FALSE; }
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
/* 155 */   public static IOFileFilter asFileFilter(FileFilter filter) { return new DelegateFileFilter(filter); }
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
/* 166 */   public static IOFileFilter asFileFilter(FilenameFilter filter) { return new DelegateFileFilter(filter); }
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
/* 179 */   public static IOFileFilter ageFileFilter(long cutoff) { return new AgeFileFilter(cutoff); }
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
/* 191 */   public static IOFileFilter ageFileFilter(long cutoff, boolean acceptOlder) { return new AgeFileFilter(cutoff, acceptOlder); }
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
/* 203 */   public static IOFileFilter ageFileFilter(Date cutoffDate) { return new AgeFileFilter(cutoffDate); }
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
/* 215 */   public static IOFileFilter ageFileFilter(Date cutoffDate, boolean acceptOlder) { return new AgeFileFilter(cutoffDate, acceptOlder); }
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
/* 228 */   public static IOFileFilter ageFileFilter(File cutoffReference) { return new AgeFileFilter(cutoffReference); }
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
/* 241 */   public static IOFileFilter ageFileFilter(File cutoffReference, boolean acceptOlder) { return new AgeFileFilter(cutoffReference, acceptOlder); }
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
/* 253 */   public static IOFileFilter sizeFileFilter(long threshold) { return new SizeFileFilter(threshold); }
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
/* 265 */   public static IOFileFilter sizeFileFilter(long threshold, boolean acceptLarger) { return new SizeFileFilter(threshold, acceptLarger); }
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
/*     */   public static IOFileFilter sizeRangeFileFilter(long minSizeInclusive, long maxSizeInclusive) {
/* 278 */     IOFileFilter minimumFilter = new SizeFileFilter(minSizeInclusive, true);
/* 279 */     IOFileFilter maximumFilter = new SizeFileFilter(maxSizeInclusive + 1L, false);
/* 280 */     return new AndFileFilter(minimumFilter, maximumFilter);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IOFileFilter makeCVSAware(IOFileFilter filter) {
/* 300 */     if (cvsFilter == null) {
/* 301 */       cvsFilter = notFileFilter(andFileFilter(directoryFileFilter(), nameFileFilter("CVS")));
/*     */     }
/*     */     
/* 304 */     if (filter == null) {
/* 305 */       return cvsFilter;
/*     */     }
/* 307 */     return andFileFilter(filter, cvsFilter);
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
/*     */   
/*     */   public static IOFileFilter makeSVNAware(IOFileFilter filter) {
/* 321 */     if (svnFilter == null) {
/* 322 */       svnFilter = notFileFilter(andFileFilter(directoryFileFilter(), nameFileFilter(".svn")));
/*     */     }
/*     */     
/* 325 */     if (filter == null) {
/* 326 */       return svnFilter;
/*     */     }
/* 328 */     return andFileFilter(filter, svnFilter);
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
/*     */   public static IOFileFilter makeDirectoryOnly(IOFileFilter filter) {
/* 341 */     if (filter == null) {
/* 342 */       return DirectoryFileFilter.DIRECTORY;
/*     */     }
/* 344 */     return new AndFileFilter(DirectoryFileFilter.DIRECTORY, filter);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IOFileFilter makeFileOnly(IOFileFilter filter) {
/* 355 */     if (filter == null) {
/* 356 */       return FileFileFilter.FILE;
/*     */     }
/* 358 */     return new AndFileFilter(FileFileFilter.FILE, filter);
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\commons\io\filefilter\FileFilterUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.2
 */