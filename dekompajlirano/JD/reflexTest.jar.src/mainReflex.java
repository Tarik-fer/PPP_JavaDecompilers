/*     */ import java.awt.EventQueue;
/*     */ import java.awt.Font;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.LayoutStyle;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.UnsupportedLookAndFeelException;
/*     */ 
/*     */ public class mainReflex
/*     */   extends JFrame {
/*  17 */   public mainReflex() { initComponents(); }
/*     */ 
/*     */   
/*     */   private JButton jButton1;
/*     */   
/*     */   private JButton jButton2;
/*     */   
/*     */   private JLabel jLabel1;
/*     */   
/*     */   private JLabel jLabel2;
/*     */   
/*     */   private void initComponents() {
/*  29 */     this.jLabel1 = new JLabel();
/*  30 */     this.jButton1 = new JButton();
/*  31 */     this.jButton2 = new JButton();
/*  32 */     this.jLabel2 = new JLabel();
/*     */     
/*  34 */     setDefaultCloseOperation(3);
/*     */     
/*  36 */     this.jLabel1.setFont(new Font("Arial", 0, 18));
/*  37 */     this.jLabel1.setText("Reflexen");
/*     */     
/*  39 */     this.jButton1.setText("Reflex");
/*  40 */     this.jButton1.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/*  42 */             mainReflex.this.jButton1ActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/*  46 */     this.jButton2.setText("Reflex + aim");
/*     */     
/*  48 */     this.jLabel2.setText("Tatrik Karamehmedović");
/*  49 */     this.jLabel2.setToolTipText("");
/*     */     
/*  51 */     GroupLayout layout = new GroupLayout(getContentPane());
/*  52 */     getContentPane().setLayout(layout);
/*  53 */     layout.setHorizontalGroup(layout
/*  54 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  55 */         .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
/*  56 */           .addContainerGap(-1, 32767)
/*  57 */           .addComponent(this.jLabel2)
/*  58 */           .addContainerGap())
/*  59 */         .addGroup(layout.createSequentialGroup()
/*  60 */           .addGap(48, 48, 48)
/*  61 */           .addComponent(this.jButton1)
/*  62 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 101, 32767)
/*  63 */           .addComponent(this.jButton2)
/*  64 */           .addGap(52, 52, 52))
/*  65 */         .addGroup(layout.createSequentialGroup()
/*  66 */           .addGap(127, 127, 127)
/*  67 */           .addComponent(this.jLabel1, -2, 81, -2)
/*  68 */           .addContainerGap(-1, 32767)));
/*     */     
/*  70 */     layout.setVerticalGroup(layout
/*  71 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  72 */         .addGroup(layout.createSequentialGroup()
/*  73 */           .addGap(24, 24, 24)
/*  74 */           .addComponent(this.jLabel1)
/*  75 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 62, 32767)
/*  76 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/*  77 */             .addComponent(this.jButton1)
/*  78 */             .addComponent(this.jButton2))
/*  79 */           .addGap(62, 62, 62)
/*  80 */           .addComponent(this.jLabel2)
/*  81 */           .addContainerGap()));
/*     */ 
/*     */     
/*  84 */     pack();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/*     */     try {
/* 101 */       for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
/* 102 */         if ("Nimbus".equals(info.getName())) {
/* 103 */           UIManager.setLookAndFeel(info.getClassName());
/*     */           break;
/*     */         } 
/*     */       } 
/* 107 */     } catch (ClassNotFoundException ex) {
/* 108 */       Logger.getLogger(mainReflex.class.getName()).log(Level.SEVERE, null, ex);
/* 109 */     } catch (InstantiationException ex) {
/* 110 */       Logger.getLogger(mainReflex.class.getName()).log(Level.SEVERE, null, ex);
/* 111 */     } catch (IllegalAccessException ex) {
/* 112 */       Logger.getLogger(mainReflex.class.getName()).log(Level.SEVERE, null, ex);
/* 113 */     } catch (UnsupportedLookAndFeelException ex) {
/* 114 */       Logger.getLogger(mainReflex.class.getName()).log(Level.SEVERE, null, ex);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 119 */     EventQueue.invokeLater(new Runnable()
/*     */         {
/* 121 */           public void run() { (new mainReflex()).setVisible(true); }
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\reflexTest\dist\reflexTest.jar!\mainReflex.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */