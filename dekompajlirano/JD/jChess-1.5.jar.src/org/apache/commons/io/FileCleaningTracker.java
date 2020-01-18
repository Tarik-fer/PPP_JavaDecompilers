/*     */ package org.apache.commons.io;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.lang.ref.PhantomReference;
/*     */ import java.lang.ref.ReferenceQueue;
/*     */ import java.util.Collection;
/*     */ import java.util.Vector;
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
/*     */ public class FileCleaningTracker
/*     */ {
/*  47 */   ReferenceQueue q = new ReferenceQueue();
/*     */ 
/*     */ 
/*     */   
/*  51 */   final Collection trackers = new Vector();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   volatile boolean exitWhenFinished = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Thread reaper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   public void track(File file, Object marker) { track(file, marker, (FileDeleteStrategy)null); }
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
/*     */   public void track(File file, Object marker, FileDeleteStrategy deleteStrategy) {
/*  86 */     if (file == null) {
/*  87 */       throw new NullPointerException("The file must not be null");
/*     */     }
/*  89 */     addTracker(file.getPath(), marker, deleteStrategy);
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
/* 102 */   public void track(String path, Object marker) { track(path, marker, (FileDeleteStrategy)null); }
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
/*     */   public void track(String path, Object marker, FileDeleteStrategy deleteStrategy) {
/* 116 */     if (path == null) {
/* 117 */       throw new NullPointerException("The path must not be null");
/*     */     }
/* 119 */     addTracker(path, marker, deleteStrategy);
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
/*     */   private synchronized void addTracker(String path, Object marker, FileDeleteStrategy deleteStrategy) {
/* 131 */     if (this.exitWhenFinished) {
/* 132 */       throw new IllegalStateException("No new trackers can be added once exitWhenFinished() is called");
/*     */     }
/* 134 */     if (this.reaper == null) {
/* 135 */       this.reaper = new Reaper(this);
/* 136 */       this.reaper.start();
/*     */     } 
/* 138 */     this.trackers.add(new Tracker(path, deleteStrategy, marker, this.q));
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
/* 149 */   public int getTrackCount() { return this.trackers.size(); }
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
/*     */   public synchronized void exitWhenFinished() {
/* 175 */     this.exitWhenFinished = true;
/* 176 */     if (this.reaper != null) {
/* 177 */       synchronized (this.reaper) {
/* 178 */         this.reaper.interrupt();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private final class Reaper
/*     */     extends Thread
/*     */   {
/*     */     private final FileCleaningTracker this$0;
/*     */     
/*     */     Reaper(FileCleaningTracker this$0) {
/* 190 */       super("File Reaper"); this.this$0 = this$0;
/* 191 */       setPriority(10);
/* 192 */       setDaemon(true);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void run() {
/* 201 */       while (!this.this$0.exitWhenFinished || this.this$0.trackers.size() > 0) {
/* 202 */         FileCleaningTracker.Tracker tracker = null;
/*     */         
/*     */         try {
/* 205 */           tracker = (FileCleaningTracker.Tracker)this.this$0.q.remove();
/* 206 */         } catch (Exception e) {
/*     */           continue;
/*     */         } 
/* 209 */         if (tracker != null) {
/* 210 */           tracker.delete();
/* 211 */           tracker.clear();
/* 212 */           this.this$0.trackers.remove(tracker);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final class Tracker
/*     */     extends PhantomReference
/*     */   {
/*     */     private final String path;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private final FileDeleteStrategy deleteStrategy;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     Tracker(String path, FileDeleteStrategy deleteStrategy, Object marker, ReferenceQueue queue) {
/* 242 */       super((T)marker, queue);
/* 243 */       this.path = path;
/* 244 */       this.deleteStrategy = (deleteStrategy == null) ? FileDeleteStrategy.NORMAL : deleteStrategy;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 254 */     public boolean delete() { return this.deleteStrategy.deleteQuietly(new File(this.path)); }
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\commons\io\FileCleaningTracker.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.2
 */