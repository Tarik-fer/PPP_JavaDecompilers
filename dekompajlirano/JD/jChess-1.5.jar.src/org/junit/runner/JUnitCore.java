/*     */ package org.junit.runner;
/*     */ 
/*     */ import junit.framework.Test;
/*     */ import junit.runner.Version;
/*     */ import org.junit.internal.JUnitSystem;
/*     */ import org.junit.internal.RealSystem;
/*     */ import org.junit.internal.TextListener;
/*     */ import org.junit.internal.runners.JUnit38ClassRunner;
/*     */ import org.junit.runner.notification.RunListener;
/*     */ import org.junit.runner.notification.RunNotifier;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JUnitCore
/*     */ {
/*  25 */   private final RunNotifier notifier = new RunNotifier();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String... args) {
/*  36 */     Result result = (new JUnitCore()).runMain((JUnitSystem)new RealSystem(), args);
/*  37 */     System.exit(result.wasSuccessful() ? 0 : 1);
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
/*  49 */   public static Result runClasses(Class<?>... classes) { return runClasses(defaultComputer(), classes); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   public static Result runClasses(Computer computer, Class<?>... classes) { return (new JUnitCore()).run(computer, classes); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Result runMain(JUnitSystem system, String... args) {
/*  70 */     system.out().println("JUnit version " + Version.id());
/*     */     
/*  72 */     JUnitCommandLineParseResult jUnitCommandLineParseResult = JUnitCommandLineParseResult.parse(args);
/*     */     
/*  74 */     TextListener textListener = new TextListener(system);
/*  75 */     addListener((RunListener)textListener);
/*     */     
/*  77 */     return run(jUnitCommandLineParseResult.createRequest(defaultComputer()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  84 */   public String getVersion() { return Version.id(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  94 */   public Result run(Class<?>... classes) { return run(defaultComputer(), classes); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 105 */   public Result run(Computer computer, Class<?>... classes) { return run(Request.classes(computer, classes)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 115 */   public Result run(Request request) { return run(request.getRunner()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 125 */   public Result run(Test test) { return run((Runner)new JUnit38ClassRunner(test)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Result run(Runner runner) {
/* 132 */     Result result = new Result();
/* 133 */     RunListener listener = result.createListener();
/* 134 */     this.notifier.addFirstListener(listener);
/*     */     try {
/* 136 */       this.notifier.fireTestRunStarted(runner.getDescription());
/* 137 */       runner.run(this.notifier);
/* 138 */       this.notifier.fireTestRunFinished(result);
/*     */     } finally {
/* 140 */       removeListener(listener);
/*     */     } 
/* 142 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 152 */   public void addListener(RunListener listener) { this.notifier.addListener(listener); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 161 */   public void removeListener(RunListener listener) { this.notifier.removeListener(listener); }
/*     */ 
/*     */ 
/*     */   
/* 165 */   static Computer defaultComputer() { return new Computer(); }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\runner\JUnitCore.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */