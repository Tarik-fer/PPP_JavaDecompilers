/*    */ package org.jdesktop.application;
/*    */ 
/*    */ import java.util.logging.Logger;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JRootPane;
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
/*    */ public class FrameView
/*    */   extends View
/*    */ {
/* 19 */   private static final Logger logger = Logger.getLogger(FrameView.class.getName());
/* 20 */   private JFrame frame = null;
/*    */ 
/*    */   
/* 23 */   public FrameView(Application paramApplication) { super(paramApplication); }
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
/*    */   public JFrame getFrame() {
/* 42 */     if (this.frame == null) {
/* 43 */       String str = getContext().getResourceMap().getString("Application.title", new Object[0]);
/* 44 */       this.frame = new JFrame(str);
/* 45 */       this.frame.setName("mainFrame");
/*    */     } 
/* 47 */     return this.frame;
/*    */   }
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
/*    */   public void setFrame(JFrame paramJFrame) {
/* 73 */     if (paramJFrame == null) {
/* 74 */       throw new IllegalArgumentException("null JFrame");
/*    */     }
/* 76 */     if (this.frame != null) {
/* 77 */       throw new IllegalStateException("frame already set");
/*    */     }
/* 79 */     this.frame = paramJFrame;
/* 80 */     firePropertyChange("frame", null, this.frame);
/*    */   }
/*    */ 
/*    */   
/* 84 */   public JRootPane getRootPane() { return getFrame().getRootPane(); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\jdesktop\application\FrameView.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */