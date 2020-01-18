/*    */ package org.junit.runner.notification;
/*    */ 
/*    */ import java.io.PrintWriter;
/*    */ import java.io.Serializable;
/*    */ import java.io.StringWriter;
/*    */ import org.junit.runner.Description;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Failure
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private final Description fDescription;
/*    */   private final Throwable fThrownException;
/*    */   
/*    */   public Failure(Description description, Throwable thrownException) {
/* 36 */     this.fThrownException = thrownException;
/* 37 */     this.fDescription = description;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 44 */   public String getTestHeader() { return this.fDescription.getDisplayName(); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 51 */   public Description getDescription() { return this.fDescription; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 59 */   public Throwable getException() { return this.fThrownException; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 64 */   public String toString() { return getTestHeader() + ": " + this.fThrownException.getMessage(); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getTrace() {
/* 73 */     StringWriter stringWriter = new StringWriter();
/* 74 */     PrintWriter writer = new PrintWriter(stringWriter);
/* 75 */     getException().printStackTrace(writer);
/* 76 */     return stringWriter.toString();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 85 */   public String getMessage() { return getException().getMessage(); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\runner\notification\Failure.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */