/*     */ package org.junit.runner;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.ObjectStreamClass;
/*     */ import java.io.ObjectStreamField;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.CopyOnWriteArrayList;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import java.util.concurrent.atomic.AtomicLong;
/*     */ import org.junit.runner.notification.Failure;
/*     */ import org.junit.runner.notification.RunListener;
/*     */ import org.junit.runner.notification.RunListener.ThreadSafe;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Result
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  27 */   private static final ObjectStreamField[] serialPersistentFields = ObjectStreamClass.lookup(SerializedForm.class).getFields();
/*     */   
/*     */   private final AtomicInteger count;
/*     */   
/*     */   private final AtomicInteger ignoreCount;
/*     */   
/*     */   private final CopyOnWriteArrayList<Failure> failures;
/*     */   private final AtomicLong runTime;
/*     */   private final AtomicLong startTime;
/*     */   private SerializedForm serializedForm;
/*     */   
/*     */   public Result() {
/*  39 */     this.count = new AtomicInteger();
/*  40 */     this.ignoreCount = new AtomicInteger();
/*  41 */     this.failures = new CopyOnWriteArrayList<Failure>();
/*  42 */     this.runTime = new AtomicLong();
/*  43 */     this.startTime = new AtomicLong();
/*     */   }
/*     */   
/*     */   private Result(SerializedForm serializedForm) {
/*  47 */     this.count = serializedForm.fCount;
/*  48 */     this.ignoreCount = serializedForm.fIgnoreCount;
/*  49 */     this.failures = new CopyOnWriteArrayList<Failure>(serializedForm.fFailures);
/*  50 */     this.runTime = new AtomicLong(serializedForm.fRunTime);
/*  51 */     this.startTime = new AtomicLong(serializedForm.fStartTime);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   public int getRunCount() { return this.count.get(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   public int getFailureCount() { return this.failures.size(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   public long getRunTime() { return this.runTime.get(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  79 */   public List<Failure> getFailures() { return this.failures; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   public int getIgnoreCount() { return this.ignoreCount.get(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  93 */   public boolean wasSuccessful() { return (getFailureCount() == 0); }
/*     */ 
/*     */   
/*     */   private void writeObject(ObjectOutputStream s) throws IOException {
/*  97 */     SerializedForm serializedForm = new SerializedForm(this);
/*  98 */     serializedForm.serialize(s);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 103 */   private void readObject(ObjectInputStream s) throws ClassNotFoundException, IOException { this.serializedForm = SerializedForm.deserialize(s); }
/*     */ 
/*     */ 
/*     */   
/* 107 */   private Object readResolve() { return new Result(this.serializedForm); }
/*     */   
/*     */   @ThreadSafe
/*     */   private class Listener
/*     */     extends RunListener {
/*     */     private Listener() {}
/*     */     
/* 114 */     public void testRunStarted(Description description) throws Exception { Result.this.startTime.set(System.currentTimeMillis()); }
/*     */ 
/*     */ 
/*     */     
/*     */     public void testRunFinished(Result result) throws Exception {
/* 119 */       long endTime = System.currentTimeMillis();
/* 120 */       Result.this.runTime.addAndGet(endTime - Result.this.startTime.get());
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 125 */     public void testFinished(Description description) throws Exception { Result.this.count.getAndIncrement(); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 130 */     public void testFailure(Failure failure) throws Exception { Result.this.failures.add(failure); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 135 */     public void testIgnored(Description description) throws Exception { Result.this.ignoreCount.getAndIncrement(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void testAssumptionFailure(Failure failure) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RunListener createListener() {
/* 148 */     return new Listener();
/*     */   }
/*     */ 
/*     */   
/*     */   private static class SerializedForm
/*     */     implements Serializable
/*     */   {
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     private final AtomicInteger fCount;
/*     */     private final AtomicInteger fIgnoreCount;
/*     */     private final List<Failure> fFailures;
/*     */     private final long fRunTime;
/*     */     private final long fStartTime;
/*     */     
/*     */     public SerializedForm(Result result) {
/* 164 */       this.fCount = result.count;
/* 165 */       this.fIgnoreCount = result.ignoreCount;
/* 166 */       this.fFailures = Collections.synchronizedList(new ArrayList<Failure>(result.failures));
/* 167 */       this.fRunTime = result.runTime.longValue();
/* 168 */       this.fStartTime = result.startTime.longValue();
/*     */     }
/*     */ 
/*     */     
/*     */     private SerializedForm(ObjectInputStream.GetField fields) throws IOException {
/* 173 */       this.fCount = (AtomicInteger)fields.get("fCount", null);
/* 174 */       this.fIgnoreCount = (AtomicInteger)fields.get("fIgnoreCount", null);
/* 175 */       this.fFailures = (List<Failure>)fields.get("fFailures", null);
/* 176 */       this.fRunTime = fields.get("fRunTime", 0L);
/* 177 */       this.fStartTime = fields.get("fStartTime", 0L);
/*     */     }
/*     */     
/*     */     public void serialize(ObjectOutputStream s) throws IOException {
/* 181 */       ObjectOutputStream.PutField fields = s.putFields();
/* 182 */       fields.put("fCount", this.fCount);
/* 183 */       fields.put("fIgnoreCount", this.fIgnoreCount);
/* 184 */       fields.put("fFailures", this.fFailures);
/* 185 */       fields.put("fRunTime", this.fRunTime);
/* 186 */       fields.put("fStartTime", this.fStartTime);
/* 187 */       s.writeFields();
/*     */     }
/*     */ 
/*     */     
/*     */     public static SerializedForm deserialize(ObjectInputStream s) throws ClassNotFoundException, IOException {
/* 192 */       ObjectInputStream.GetField fields = s.readFields();
/* 193 */       return new SerializedForm(fields);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\runner\Result.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */