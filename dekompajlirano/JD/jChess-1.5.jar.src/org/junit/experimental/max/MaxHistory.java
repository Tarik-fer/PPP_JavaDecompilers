/*     */ package org.junit.experimental.max;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.junit.runner.Description;
/*     */ import org.junit.runner.Result;
/*     */ import org.junit.runner.notification.Failure;
/*     */ import org.junit.runner.notification.RunListener;
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
/*     */ public class MaxHistory
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public static MaxHistory forFolder(File file) {
/*  34 */     if (file.exists()) {
/*     */       try {
/*  36 */         return readHistory(file);
/*  37 */       } catch (CouldNotReadCoreException e) {
/*  38 */         e.printStackTrace();
/*  39 */         file.delete();
/*     */       } 
/*     */     }
/*  42 */     return new MaxHistory(file);
/*     */   }
/*     */ 
/*     */   
/*     */   private static MaxHistory readHistory(File storedResults) throws CouldNotReadCoreException {
/*     */     try {
/*  48 */       FileInputStream file = new FileInputStream(storedResults);
/*     */       try {
/*  50 */         ObjectInputStream stream = new ObjectInputStream(file);
/*     */ 
/*     */       
/*     */       }
/*     */       finally {
/*     */ 
/*     */         
/*  57 */         file.close();
/*     */       } 
/*  59 */     } catch (Exception e) {
/*  60 */       throw new CouldNotReadCoreException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  69 */   private final Map<String, Long> fDurations = new HashMap<String, Long>();
/*  70 */   private final Map<String, Long> fFailureTimestamps = new HashMap<String, Long>();
/*     */   
/*     */   private final File fHistoryStore;
/*     */   
/*  74 */   private MaxHistory(File storedResults) { this.fHistoryStore = storedResults; }
/*     */ 
/*     */   
/*     */   private void save() throws IOException {
/*  78 */     ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(this.fHistoryStore));
/*     */     
/*  80 */     stream.writeObject(this);
/*  81 */     stream.close();
/*     */   }
/*     */ 
/*     */   
/*  85 */   Long getFailureTimestamp(Description key) { return this.fFailureTimestamps.get(key.toString()); }
/*     */ 
/*     */ 
/*     */   
/*  89 */   void putTestFailureTimestamp(Description key, long end) { this.fFailureTimestamps.put(key.toString(), Long.valueOf(end)); }
/*     */ 
/*     */ 
/*     */   
/*  93 */   boolean isNewTest(Description key) { return !this.fDurations.containsKey(key.toString()); }
/*     */ 
/*     */ 
/*     */   
/*  97 */   Long getTestDuration(Description key) { return this.fDurations.get(key.toString()); }
/*     */ 
/*     */ 
/*     */   
/* 101 */   void putTestDuration(Description description, long duration) { this.fDurations.put(description.toString(), Long.valueOf(duration)); }
/*     */   
/*     */   private final class RememberingListener
/*     */     extends RunListener {
/* 105 */     private long overallStart = System.currentTimeMillis();
/*     */     
/* 107 */     private Map<Description, Long> starts = new HashMap<Description, Long>();
/*     */ 
/*     */ 
/*     */     
/* 111 */     public void testStarted(Description description) throws Exception { this.starts.put(description, Long.valueOf(System.nanoTime())); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void testFinished(Description description) throws Exception {
/* 117 */       long end = System.nanoTime();
/* 118 */       long start = ((Long)this.starts.get(description)).longValue();
/* 119 */       MaxHistory.this.putTestDuration(description, end - start);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 124 */     public void testFailure(Failure failure) throws Exception { MaxHistory.this.putTestFailureTimestamp(failure.getDescription(), this.overallStart); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 129 */     public void testRunFinished(Result result) throws Exception { MaxHistory.this.save(); }
/*     */     
/*     */     private RememberingListener() {}
/*     */   }
/*     */   
/*     */   private class TestComparator implements Comparator<Description> {
/*     */     public int compare(Description o1, Description o2) {
/* 136 */       if (MaxHistory.this.isNewTest(o1)) {
/* 137 */         return -1;
/*     */       }
/* 139 */       if (MaxHistory.this.isNewTest(o2)) {
/* 140 */         return 1;
/*     */       }
/*     */       
/* 143 */       int result = getFailure(o2).compareTo(getFailure(o1));
/* 144 */       return (result != 0) ? result : MaxHistory.this.getTestDuration(o1).compareTo(MaxHistory.this.getTestDuration(o2));
/*     */     }
/*     */     
/*     */     private TestComparator() {}
/*     */     
/*     */     private Long getFailure(Description key) {
/* 150 */       Long result = MaxHistory.this.getFailureTimestamp(key);
/* 151 */       if (result == null) {
/* 152 */         return Long.valueOf(0L);
/*     */       }
/* 154 */       return result;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RunListener listener() {
/* 163 */     return new RememberingListener();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Comparator<Description> testComparator() {
/* 171 */     return new TestComparator();
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\experimental\max\MaxHistory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */