/*     */ package org.junit.rules;
/*     */ 
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import org.junit.internal.runners.statements.FailOnTimeout;
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
/*     */ public class Timeout
/*     */   implements TestRule
/*     */ {
/*     */   private final long timeout;
/*     */   private final TimeUnit timeUnit;
/*     */   private final boolean lookForStuckThread;
/*     */   
/*  51 */   public static Builder builder() { return new Builder(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*  68 */   public Timeout(int millis) { this(millis, TimeUnit.MILLISECONDS); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Timeout(long timeout, TimeUnit timeUnit) {
/*  81 */     this.timeout = timeout;
/*  82 */     this.timeUnit = timeUnit;
/*  83 */     this.lookForStuckThread = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Timeout(Builder builder) {
/*  93 */     this.timeout = builder.getTimeout();
/*  94 */     this.timeUnit = builder.getTimeUnit();
/*  95 */     this.lookForStuckThread = builder.getLookingForStuckThread();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 105 */   public static Timeout millis(long millis) { return new Timeout(millis, TimeUnit.MILLISECONDS); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 115 */   public static Timeout seconds(long seconds) { return new Timeout(seconds, TimeUnit.SECONDS); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 124 */   protected final long getTimeout(TimeUnit unit) { return unit.convert(this.timeout, this.timeUnit); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 134 */   protected final boolean getLookingForStuckThread() { return this.lookForStuckThread; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 147 */   protected Statement createFailOnTimeoutStatement(Statement statement) throws Exception { return (Statement)FailOnTimeout.builder().withTimeout(this.timeout, this.timeUnit).withLookingForStuckThread(this.lookForStuckThread).build(statement); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Statement apply(Statement base, Description description) {
/*     */     try {
/* 155 */       return createFailOnTimeoutStatement(base);
/* 156 */     } catch (Exception e) {
/* 157 */       return new Statement()
/*     */         {
/* 159 */           public void evaluate() throws Throwable { throw new RuntimeException("Invalid parameters for Timeout", e); }
/*     */         };
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Builder
/*     */   {
/*     */     private boolean lookForStuckThread = false;
/*     */ 
/*     */     
/* 172 */     private long timeout = 0L;
/* 173 */     private TimeUnit timeUnit = TimeUnit.SECONDS;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder withTimeout(long timeout, TimeUnit unit) {
/* 194 */       this.timeout = timeout;
/* 195 */       this.timeUnit = unit;
/* 196 */       return this;
/*     */     }
/*     */ 
/*     */     
/* 200 */     protected long getTimeout() { return this.timeout; }
/*     */ 
/*     */ 
/*     */     
/* 204 */     protected TimeUnit getTimeUnit() { return this.timeUnit; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder withLookingForStuckThread(boolean enable) {
/* 217 */       this.lookForStuckThread = enable;
/* 218 */       return this;
/*     */     }
/*     */ 
/*     */     
/* 222 */     protected boolean getLookingForStuckThread() { return this.lookForStuckThread; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 230 */     public Timeout build() { return new Timeout(this); }
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\rules\Timeout.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */