/*    */ package pl.art.lach.mateusz.javaopenchess.display.windows;
/*    */ 
/*    */ import java.awt.EventQueue;
/*    */ import javax.swing.GroupLayout;
/*    */ import javax.swing.JDialog;
/*    */ import javax.swing.JTabbedPane;
/*    */ import pl.art.lach.mateusz.javaopenchess.utils.Settings;
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
/*    */ public class NewGameWindow
/*    */   extends JDialog
/*    */ {
/*    */   private JTabbedPane jTabbedPane1;
/*    */   
/*    */   public NewGameWindow() {
/* 38 */     initComponents();
/* 39 */     setSize(400, 700);
/* 40 */     setDefaultCloseOperation(1);
/* 41 */     this.jTabbedPane1.addTab(Settings.lang("local_game"), new DrawLocalSettings(this));
/* 42 */     this.jTabbedPane1.addTab(Settings.lang("network_game"), new DrawNetworkSettings(this));
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
/*    */   private void initComponents() {
/* 55 */     this.jTabbedPane1 = new JTabbedPane();
/*    */     
/* 57 */     setDefaultCloseOperation(2);
/* 58 */     setAlwaysOnTop(true);
/* 59 */     setName("Form");
/*    */     
/* 61 */     this.jTabbedPane1.setName("jTabbedPane1");
/*    */     
/* 63 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 64 */     getContentPane().setLayout(layout);
/* 65 */     layout.setHorizontalGroup(layout
/* 66 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 67 */         .addGroup(layout.createSequentialGroup()
/* 68 */           .addContainerGap()
/* 69 */           .addComponent(this.jTabbedPane1, -1, 376, 32767)
/* 70 */           .addContainerGap()));
/*    */     
/* 72 */     layout.setVerticalGroup(layout
/* 73 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 74 */         .addGroup(layout.createSequentialGroup()
/* 75 */           .addGap(20, 20, 20)
/* 76 */           .addComponent(this.jTabbedPane1, -1, 268, 32767)
/* 77 */           .addContainerGap()));
/*    */ 
/*    */     
/* 80 */     pack();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void main(String[] args) {
/* 87 */     EventQueue.invokeLater(() -> (
/* 88 */         new NewGameWindow()).setVisible(true));
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\display\windows\NewGameWindow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */