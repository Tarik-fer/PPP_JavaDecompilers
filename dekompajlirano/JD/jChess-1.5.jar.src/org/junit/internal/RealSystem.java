/*    */ package org.junit.internal;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RealSystem
/*    */   implements JUnitSystem
/*    */ {
/*    */   @Deprecated
/* 12 */   public void exit(int code) { System.exit(code); }
/*    */ 
/*    */ 
/*    */   
/* 16 */   public PrintStream out() { return System.out; }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\internal\RealSystem.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */