/*     */ package pl.art.lach.mateusz.javaopenchess.display.views.chessboard;
/*     */ 
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Image;
/*     */ import java.awt.Point;
/*     */ import javax.swing.JPanel;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Chessboard;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Square;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ChessboardView
/*     */   extends JPanel
/*     */ {
/*     */   private static final int CENTER_POSITION = 3;
/*  35 */   protected static final Image orgImage = GUI.loadImage("chessboard.png");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  40 */   protected Image image = orgImage;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  45 */   protected static final Image orgSelSquare = GUI.loadImage("sel_square.png");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   protected static Image selSquare = orgSelSquare;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   protected static final Image orgAbleSquare = GUI.loadImage("able_square.png");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   protected static Image ableSquare = orgAbleSquare;
/*     */ 
/*     */   
/*  63 */   private Image upDownLabel = null;
/*     */   
/*  65 */   protected Image leftRightLabel = null;
/*     */   
/*  67 */   protected Point topLeft = new Point(0, 0);
/*     */ 
/*     */   
/*     */   protected float squareHeight;
/*     */ 
/*     */   
/*     */   public static final int imgX = 5;
/*     */ 
/*     */   
/*     */   public static final int imgY = 5;
/*     */ 
/*     */   
/*     */   public static final int imgWidht = 480;
/*     */ 
/*     */   
/*     */   public static final int imgHeight = 480;
/*     */ 
/*     */   
/*     */   private Chessboard chessboard;
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract Square getSquare(int paramInt1, int paramInt2);
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void unselect();
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int getChessboardWidht();
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int getChessboardHeight();
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int getChessboardWidht(boolean paramBoolean);
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int getChessboardHeight(boolean paramBoolean);
/*     */ 
/*     */   
/*     */   public abstract int getSquareHeight();
/*     */ 
/*     */   
/*     */   public abstract void resizeChessboard(int paramInt);
/*     */ 
/*     */   
/*     */   public abstract Point getTopLeftPoint();
/*     */ 
/*     */   
/* 121 */   public void update(Graphics g) { repaint(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 129 */   public Chessboard getChessboard() { return this.chessboard; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 137 */   public void setChessboard(Chessboard chessboard) { this.chessboard = chessboard; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 145 */   public Image getUpDownLabel() { return this.upDownLabel; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 153 */   public void setUpDownLabel(Image upDownLabel) { this.upDownLabel = upDownLabel; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 158 */   public int transposePosition(int squarePosition) { return transposePosition(squarePosition, 3); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 163 */   public int transposePosition(int squarePosition, int centerPosition) { return -(squarePosition - centerPosition) + centerPosition + 1; }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\display\views\chessboard\ChessboardView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */