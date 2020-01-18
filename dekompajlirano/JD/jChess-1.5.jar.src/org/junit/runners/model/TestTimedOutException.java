/*    */ package org.junit.runners.model;
/*    */ 
/*    */ import java.util.concurrent.TimeUnit;
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
/*    */ public class TestTimedOutException
/*    */   extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 31935685163547539L;
/*    */   private final TimeUnit timeUnit;
/*    */   private final long timeout;
/*    */   
/*    */   public TestTimedOutException(long timeout, TimeUnit timeUnit) {
/* 25 */     super(String.format("test timed out after %d %s", new Object[] { Long.valueOf(timeout), timeUnit.name().toLowerCase() }));
/*    */     
/* 27 */     this.timeUnit = timeUnit;
/* 28 */     this.timeout = timeout;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 35 */   public long getTimeout() { return this.timeout; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 42 */   public TimeUnit getTimeUnit() { return this.timeUnit; }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\runners\model\TestTimedOutException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */