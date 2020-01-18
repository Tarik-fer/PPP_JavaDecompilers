/*    */ package org.junit.runner;
/*    */ 
/*    */ import org.junit.runner.notification.RunNotifier;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class Runner
/*    */   implements Describable
/*    */ {
/*    */   public abstract Description getDescription();
/*    */   
/*    */   public abstract void run(RunNotifier paramRunNotifier);
/*    */   
/* 41 */   public int testCount() { return getDescription().testCount(); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\runner\Runner.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */