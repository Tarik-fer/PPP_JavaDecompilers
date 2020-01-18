/*     */ package pl.art.lach.mateusz.javaopenchess.display.windows;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import pl.art.lach.mateusz.javaopenchess.utils.GUI;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PawnPromotionWindow
/*     */   extends JDialog
/*     */   implements ActionListener
/*     */ {
/*     */   JButton knightButton;
/*     */   JButton bishopButton;
/*     */   JButton rookButton;
/*     */   JButton queenButton;
/*     */   GridBagLayout gbl;
/*     */   public String result;
/*     */   GridBagConstraints gbc;
/*     */   
/*     */   public PawnPromotionWindow(Frame parent, String color) {
/*  44 */     super(parent);
/*  45 */     setTitle("Choose piece");
/*  46 */     setMinimumSize(new Dimension(520, 130));
/*  47 */     setSize(new Dimension(520, 130));
/*  48 */     setMaximumSize(new Dimension(520, 130));
/*  49 */     setResizable(false);
/*  50 */     setLayout(new GridLayout(1, 4));
/*     */ 
/*     */     
/*  53 */     this.gbl = new GridBagLayout();
/*  54 */     this.gbc = new GridBagConstraints();
/*  55 */     this.knightButton = new JButton(new ImageIcon(GUI.loadImage("Knight-" + color + "70.png")));
/*  56 */     this.bishopButton = new JButton(new ImageIcon(GUI.loadImage("Bishop-" + color + "70.png")));
/*  57 */     this.rookButton = new JButton(new ImageIcon(GUI.loadImage("Rook-" + color + "70.png")));
/*  58 */     this.queenButton = new JButton(new ImageIcon(GUI.loadImage("Queen-" + color + "70.png")));
/*  59 */     this.result = "";
/*     */     
/*  61 */     this.knightButton.addActionListener(this);
/*  62 */     this.bishopButton.addActionListener(this);
/*  63 */     this.rookButton.addActionListener(this);
/*  64 */     this.queenButton.addActionListener(this);
/*     */     
/*  66 */     add(this.queenButton);
/*  67 */     add(this.rookButton);
/*  68 */     add(this.bishopButton);
/*  69 */     add(this.knightButton);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColor(String color) {
/*  77 */     this.knightButton.setIcon(new ImageIcon(GUI.loadImage("Knight-" + color + "70.png")));
/*  78 */     this.bishopButton.setIcon(new ImageIcon(GUI.loadImage("Bishop-" + color + "70.png")));
/*  79 */     this.rookButton.setIcon(new ImageIcon(GUI.loadImage("Rook-" + color + "70.png")));
/*  80 */     this.queenButton.setIcon(new ImageIcon(GUI.loadImage("Queen-" + color + "70.png")));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent arg0) {
/*  88 */     if (arg0.getSource() == this.queenButton) {
/*     */       
/*  90 */       this.result = "Queen";
/*     */     }
/*  92 */     else if (arg0.getSource() == this.rookButton) {
/*     */       
/*  94 */       this.result = "Rook";
/*     */     }
/*  96 */     else if (arg0.getSource() == this.bishopButton) {
/*     */       
/*  98 */       this.result = "Bishop";
/*     */     }
/*     */     else {
/*     */       
/* 102 */       this.result = "Knight";
/*     */     } 
/* 104 */     setVisible(false);
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\display\windows\PawnPromotionWindow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */