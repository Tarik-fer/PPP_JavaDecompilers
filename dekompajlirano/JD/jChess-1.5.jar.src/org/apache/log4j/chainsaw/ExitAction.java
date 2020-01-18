/*    */ package org.apache.log4j.chainsaw;
/*    */ 
/*    */ import java.awt.event.ActionEvent;
/*    */ import javax.swing.AbstractAction;
/*    */ import org.apache.log4j.Logger;
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
/*    */ class ExitAction
/*    */   extends AbstractAction
/*    */ {
/* 33 */   private static final Logger LOG = Logger.getLogger(ExitAction.class);
/*    */   
/* 35 */   public static final ExitAction INSTANCE = new ExitAction();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void actionPerformed(ActionEvent aIgnore) {
/* 45 */     LOG.info("shutting down");
/* 46 */     System.exit(0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\log4j\chainsaw\ExitAction.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.2
 */