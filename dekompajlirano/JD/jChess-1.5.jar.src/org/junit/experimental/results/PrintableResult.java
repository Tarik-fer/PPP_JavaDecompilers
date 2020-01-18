/*    */ package org.junit.experimental.results;
/*    */ 
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.PrintStream;
/*    */ import java.util.List;
/*    */ import org.junit.internal.TextListener;
/*    */ import org.junit.runner.JUnitCore;
/*    */ import org.junit.runner.Request;
/*    */ import org.junit.runner.Result;
/*    */ import org.junit.runner.notification.Failure;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PrintableResult
/*    */ {
/*    */   private Result result;
/*    */   
/* 29 */   public static PrintableResult testResult(Class<?> type) { return testResult(Request.aClass(type)); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 36 */   public static PrintableResult testResult(Request request) { return new PrintableResult((new JUnitCore()).run(request)); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 43 */   public PrintableResult(List<Failure> failures) { this((new FailureList(failures)).result()); }
/*    */ 
/*    */ 
/*    */   
/* 47 */   private PrintableResult(Result result) { this.result = result; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 54 */   public int failureCount() { return this.result.getFailures().size(); }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 59 */     ByteArrayOutputStream stream = new ByteArrayOutputStream();
/* 60 */     (new TextListener(new PrintStream(stream))).testRunFinished(this.result);
/* 61 */     return stream.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\experimental\results\PrintableResult.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */