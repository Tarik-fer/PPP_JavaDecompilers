/*     */ package pl.art.lach.mateusz.javaopenchess.display.panels;
/*     */ 
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JPanel;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Game;
/*     */ import pl.art.lach.mateusz.javaopenchess.utils.Settings;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LocalSettingsView
/*     */   extends JPanel
/*     */   implements ActionListener
/*     */ {
/*     */   private JCheckBox isUpsideDown;
/*     */   private JCheckBox isDisplayLegalMovesEnabled;
/*     */   private JCheckBox isRenderLabelsEnabled;
/*     */   private GridBagConstraints gbc;
/*     */   private GridBagLayout gbl;
/*     */   private Game game;
/*     */   
/*     */   public LocalSettingsView(Game game) {
/*  47 */     this.game = game;
/*     */     
/*  49 */     this.gbc = new GridBagConstraints();
/*  50 */     this.gbl = new GridBagLayout();
/*     */     
/*  52 */     setLayout(this.gbl);
/*     */     
/*  54 */     initUpsideDownControl();
/*  55 */     initDisplayLegalMovesControl();
/*  56 */     initRenderLabelsControl();
/*  57 */     refreshCheckBoxesState();
/*     */   }
/*     */ 
/*     */   
/*     */   private void initUpsideDownControl() {
/*  62 */     this.isUpsideDown = new JCheckBox();
/*  63 */     this.isUpsideDown.setText(Settings.lang("upside_down"));
/*  64 */     this.isUpsideDown.setSize(this.isUpsideDown.getHeight(), getWidth());
/*  65 */     this.gbc.gridx = 0;
/*  66 */     this.gbc.gridy = 0;
/*  67 */     this.gbc.insets = new Insets(3, 3, 3, 3);
/*  68 */     this.gbl.setConstraints(this.isUpsideDown, this.gbc);
/*  69 */     add(this.isUpsideDown);
/*     */     
/*  71 */     this.isUpsideDown.addActionListener(this);
/*     */   }
/*     */ 
/*     */   
/*     */   private void initDisplayLegalMovesControl() {
/*  76 */     this.isDisplayLegalMovesEnabled = new JCheckBox();
/*  77 */     this.isDisplayLegalMovesEnabled.setText(Settings.lang("display_legal_moves"));
/*     */     
/*  79 */     this.gbc.gridx = 0;
/*  80 */     this.gbc.gridy = 1;
/*  81 */     this.gbl.setConstraints(this.isDisplayLegalMovesEnabled, this.gbc);
/*  82 */     add(this.isDisplayLegalMovesEnabled);
/*     */     
/*  84 */     this.isDisplayLegalMovesEnabled.addActionListener(this);
/*     */   }
/*     */ 
/*     */   
/*     */   private void initRenderLabelsControl() {
/*  89 */     this.isRenderLabelsEnabled = new JCheckBox();
/*  90 */     this.isRenderLabelsEnabled.setText(Settings.lang("display_labels"));
/*     */     
/*  92 */     this.gbc.gridx = 0;
/*  93 */     this.gbc.gridy = 2;
/*  94 */     this.gbl.setConstraints(this.isRenderLabelsEnabled, this.gbc);
/*  95 */     add(this.isRenderLabelsEnabled);
/*     */     
/*  97 */     this.isRenderLabelsEnabled.addActionListener(this);
/*     */   }
/*     */ 
/*     */   
/*     */   private void refreshCheckBoxesState() {
/* 102 */     if (isInitiatedCorrectly()) {
/*     */       
/* 104 */       this.isUpsideDown.setSelected(this.game.getSettings().isUpsideDown());
/* 105 */       this.isDisplayLegalMovesEnabled.setSelected(this.game.getSettings().isDisplayLegalMovesEnabled());
/* 106 */       this.isRenderLabelsEnabled.setSelected(this.game.getSettings().isRenderLabels());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 113 */     JCheckBox clickedComponent = (JCheckBox)e.getSource();
/* 114 */     if (clickedComponent == this.isUpsideDown) {
/*     */       
/* 116 */       this.game.getSettings().setUpsideDown(this.isUpsideDown.isSelected());
/*     */     }
/* 118 */     else if (clickedComponent == this.isDisplayLegalMovesEnabled) {
/*     */       
/* 120 */       this.game.getSettings().setDisplayLegalMovesEnabled(this.isDisplayLegalMovesEnabled.isSelected());
/*     */     }
/* 122 */     else if (clickedComponent == this.isRenderLabelsEnabled) {
/*     */       
/* 124 */       this.game.getSettings().setRenderLabels(this.isRenderLabelsEnabled.isSelected());
/* 125 */       this.game.resizeGame();
/*     */     } 
/* 127 */     this.game.repaint();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void repaint() {
/* 133 */     refreshCheckBoxesState();
/* 134 */     super.repaint();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 139 */   private boolean isInitiatedCorrectly() { return (null != this.isUpsideDown && null != this.isDisplayLegalMovesEnabled && null != this.isRenderLabelsEnabled); }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\display\panels\LocalSettingsView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */