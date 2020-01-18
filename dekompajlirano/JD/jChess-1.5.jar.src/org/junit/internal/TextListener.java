/*    */ package org.junit.internal;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.text.NumberFormat;
/*    */ import java.util.List;
/*    */ import org.junit.runner.Description;
/*    */ import org.junit.runner.Result;
/*    */ import org.junit.runner.notification.Failure;
/*    */ import org.junit.runner.notification.RunListener;
/*    */ 
/*    */ 
/*    */ public class TextListener
/*    */   extends RunListener
/*    */ {
/*    */   private final PrintStream writer;
/*    */   
/* 17 */   public TextListener(JUnitSystem system) { this(system.out()); }
/*    */ 
/*    */ 
/*    */   
/* 21 */   public TextListener(PrintStream writer) { this.writer = writer; }
/*    */ 
/*    */ 
/*    */   
/*    */   public void testRunFinished(Result result) {
/* 26 */     printHeader(result.getRunTime());
/* 27 */     printFailures(result);
/* 28 */     printFooter(result);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 33 */   public void testStarted(Description description) { this.writer.append('.'); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 38 */   public void testFailure(Failure failure) { this.writer.append('E'); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 43 */   public void testIgnored(Description description) { this.writer.append('I'); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 51 */   private PrintStream getWriter() { return this.writer; }
/*    */ 
/*    */   
/*    */   protected void printHeader(long runTime) {
/* 55 */     getWriter().println();
/* 56 */     getWriter().println("Time: " + elapsedTimeAsString(runTime));
/*    */   }
/*    */   
/*    */   protected void printFailures(Result result) {
/* 60 */     List<Failure> failures = result.getFailures();
/* 61 */     if (failures.size() == 0) {
/*    */       return;
/*    */     }
/* 64 */     if (failures.size() == 1) {
/* 65 */       getWriter().println("There was " + failures.size() + " failure:");
/*    */     } else {
/* 67 */       getWriter().println("There were " + failures.size() + " failures:");
/*    */     } 
/* 69 */     int i = 1;
/* 70 */     for (Failure each : failures) {
/* 71 */       printFailure(each, "" + i++);
/*    */     }
/*    */   }
/*    */   
/*    */   protected void printFailure(Failure each, String prefix) {
/* 76 */     getWriter().println(prefix + ") " + each.getTestHeader());
/* 77 */     getWriter().print(each.getTrace());
/*    */   }
/*    */   
/*    */   protected void printFooter(Result result) {
/* 81 */     if (result.wasSuccessful()) {
/* 82 */       getWriter().println();
/* 83 */       getWriter().print("OK");
/* 84 */       getWriter().println(" (" + result.getRunCount() + " test" + ((result.getRunCount() == 1) ? "" : "s") + ")");
/*    */     } else {
/*    */       
/* 87 */       getWriter().println();
/* 88 */       getWriter().println("FAILURES!!!");
/* 89 */       getWriter().println("Tests run: " + result.getRunCount() + ",  Failures: " + result.getFailureCount());
/*    */     } 
/* 91 */     getWriter().println();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 99 */   protected String elapsedTimeAsString(long runTime) { return NumberFormat.getInstance().format(runTime / 1000.0D); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\internal\TextListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */