/*     */ package junit.framework;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Enumeration;
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
/*     */ public class TestResult
/*     */ {
/*  25 */   protected List<TestFailure> fFailures = new ArrayList<TestFailure>();
/*  26 */   protected List<TestFailure> fErrors = new ArrayList<TestFailure>();
/*  27 */   protected List<TestListener> fListeners = new ArrayList<TestListener>();
/*  28 */   protected int fRunTests = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean fStop = false;
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void addError(Test test, Throwable e) {
/*  37 */     this.fErrors.add(new TestFailure(test, e));
/*  38 */     for (TestListener each : cloneListeners()) {
/*  39 */       each.addError(test, e);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void addFailure(Test test, AssertionFailedError e) {
/*  48 */     this.fFailures.add(new TestFailure(test, e));
/*  49 */     for (TestListener each : cloneListeners()) {
/*  50 */       each.addFailure(test, e);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   public synchronized void addListener(TestListener listener) { this.fListeners.add(listener); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   public synchronized void removeListener(TestListener listener) { this.fListeners.remove(listener); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private synchronized List<TestListener> cloneListeners() {
/*  72 */     List<TestListener> result = new ArrayList<TestListener>();
/*  73 */     result.addAll(this.fListeners);
/*  74 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endTest(Test test) {
/*  81 */     for (TestListener each : cloneListeners()) {
/*  82 */       each.endTest(test);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   public synchronized int errorCount() { return this.fErrors.size(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   public synchronized Enumeration<TestFailure> errors() { return Collections.enumeration(this.fErrors); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 105 */   public synchronized int failureCount() { return this.fFailures.size(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 112 */   public synchronized Enumeration<TestFailure> failures() { return Collections.enumeration(this.fFailures); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void run(final TestCase test) {
/* 119 */     startTest(test);
/* 120 */     Protectable p = new Protectable() {
/*     */         public void protect() throws Throwable {
/* 122 */           test.runBare();
/*     */         }
/*     */       };
/* 125 */     runProtected(test, p);
/*     */     
/* 127 */     endTest(test);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 134 */   public synchronized int runCount() { return this.fRunTests; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void runProtected(Test test, Protectable p) {
/*     */     try {
/* 142 */       p.protect();
/* 143 */     } catch (AssertionFailedError e) {
/* 144 */       addFailure(test, e);
/* 145 */     } catch (ThreadDeath e) {
/* 146 */       throw e;
/* 147 */     } catch (Throwable e) {
/* 148 */       addError(test, e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 156 */   public synchronized boolean shouldStop() { return this.fStop; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startTest(Test test) {
/* 163 */     int count = test.countTestCases();
/* 164 */     synchronized (this) {
/* 165 */       this.fRunTests += count;
/*     */     } 
/* 167 */     for (TestListener each : cloneListeners()) {
/* 168 */       each.startTest(test);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 176 */   public synchronized void stop() { this.fStop = true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 183 */   public synchronized boolean wasSuccessful() { return (failureCount() == 0 && errorCount() == 0); }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\junit\framework\TestResult.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */