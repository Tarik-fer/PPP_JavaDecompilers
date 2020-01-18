/*     */ package org.junit.rules;
/*     */ 
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import org.junit.AssumptionViolatedException;
/*     */ import org.junit.runner.Description;
/*     */ import org.junit.runners.model.Statement;
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
/*     */ public abstract class Stopwatch
/*     */   implements TestRule
/*     */ {
/*     */   private final Clock clock;
/*     */   private volatile long startNanos;
/*     */   private volatile long endNanos;
/*     */   
/*  85 */   public Stopwatch() { this(new Clock()); }
/*     */ 
/*     */ 
/*     */   
/*  89 */   Stopwatch(Clock clock) { this.clock = clock; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  99 */   public long runtime(TimeUnit unit) { return unit.convert(getNanos(), TimeUnit.NANOSECONDS); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void succeeded(long nanos, Description description) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void failed(long nanos, Throwable e, Description description) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void skipped(long nanos, AssumptionViolatedException e, Description description) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void finished(long nanos, Description description) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private long getNanos() {
/* 127 */     if (this.startNanos == 0L) {
/* 128 */       throw new IllegalStateException("Test has not started");
/*     */     }
/* 130 */     long currentEndNanos = this.endNanos;
/* 131 */     if (currentEndNanos == 0L) {
/* 132 */       currentEndNanos = this.clock.nanoTime();
/*     */     }
/*     */     
/* 135 */     return currentEndNanos - this.startNanos;
/*     */   }
/*     */   
/*     */   private void starting() {
/* 139 */     this.startNanos = this.clock.nanoTime();
/* 140 */     this.endNanos = 0L;
/*     */   }
/*     */ 
/*     */   
/* 144 */   private void stopping() { this.endNanos = this.clock.nanoTime(); }
/*     */ 
/*     */ 
/*     */   
/* 148 */   public final Statement apply(Statement base, Description description) { return (new InternalWatcher()).apply(base, description); }
/*     */   
/*     */   private class InternalWatcher
/*     */     extends TestWatcher {
/*     */     private InternalWatcher() {}
/*     */     
/* 154 */     protected void starting(Description description) { Stopwatch.this.starting(); }
/*     */ 
/*     */ 
/*     */     
/* 158 */     protected void finished(Description description) { Stopwatch.this.finished(Stopwatch.this.getNanos(), description); }
/*     */ 
/*     */     
/*     */     protected void succeeded(Description description) {
/* 162 */       Stopwatch.this.stopping();
/* 163 */       Stopwatch.this.succeeded(Stopwatch.this.getNanos(), description);
/*     */     }
/*     */     
/*     */     protected void failed(Throwable e, Description description) {
/* 167 */       Stopwatch.this.stopping();
/* 168 */       Stopwatch.this.failed(Stopwatch.this.getNanos(), e, description);
/*     */     }
/*     */     
/*     */     protected void skipped(AssumptionViolatedException e, Description description) {
/* 172 */       Stopwatch.this.stopping();
/* 173 */       Stopwatch.this.skipped(Stopwatch.this.getNanos(), e, description);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   static class Clock
/*     */   {
/* 180 */     public long nanoTime() { return System.nanoTime(); }
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\rules\Stopwatch.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */