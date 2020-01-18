/*     */ package pl.art.lach.mateusz.javaopenchess.display.views.chessboard.implementation.graphic2D;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.Point;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import org.apache.log4j.Logger;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Chessboard;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Square;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
/*     */ import pl.art.lach.mateusz.javaopenchess.display.views.chessboard.ChessboardView;
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
/*     */ public class Chessboard2D
/*     */   extends ChessboardView
/*     */ {
/*  38 */   private static final Logger LOG = Logger.getLogger(Chessboard2D.class);
/*     */   
/*  40 */   protected Pieces2D pieces2D = Pieces2D.getInstance();
/*     */   
/*  42 */   private static final String[] LETTERS = new String[] { "a", "b", "c", "d", "e", "f", "g", "h" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   public Chessboard2D(Chessboard chessboard) { init(chessboard); }
/*     */ 
/*     */ 
/*     */   
/*     */   protected final void init(Chessboard chessboard) {
/*  53 */     setChessboard(chessboard);
/*     */     
/*  55 */     setVisible(true);
/*  56 */     setSize(480, 480);
/*  57 */     setLocation(new Point(0, 0));
/*  58 */     setDoubleBuffered(true);
/*     */     
/*  60 */     drawLabels((int)this.squareHeight);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  66 */     resizeChessboard(480);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   public void unselect() { repaint(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   public int getChessboardWidht() { return getChessboardWidht(false); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  84 */   public int getChessboardHeight() { return getChessboardHeight(false); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  91 */   public int getChessboardWidht(boolean includeLables) { return getHeight(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getChessboardHeight(boolean includeLabels) {
/*  98 */     if (getChessboard().getSettings().isRenderLabels())
/*     */     {
/* 100 */       return this.image.getHeight(null) + getUpDownLabel().getHeight(null);
/*     */     }
/* 102 */     return this.image.getHeight(null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSquareHeight() {
/* 108 */     int result = (int)this.squareHeight;
/* 109 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Square getSquare(int clickedX, int clickedY) {
/* 115 */     if (clickedX > getChessboardHeight() || clickedY > getChessboardWidht()) {
/*     */       
/* 117 */       LOG.debug("click out of chessboard.");
/* 118 */       return null;
/*     */     } 
/* 120 */     if (getChessboard().getSettings().isRenderLabels()) {
/*     */       
/* 122 */       clickedX -= getUpDownLabel().getHeight(null);
/* 123 */       clickedY -= getUpDownLabel().getHeight(null);
/*     */     } 
/* 125 */     double squareX = (clickedX / this.squareHeight);
/* 126 */     double squareY = (clickedY / this.squareHeight);
/*     */     
/* 128 */     if (squareX > (int)squareX)
/*     */     {
/* 130 */       squareX = ((int)squareX + 1);
/*     */     }
/* 132 */     if (squareY > (int)squareY)
/*     */     {
/* 134 */       squareY = ((int)squareY + 1);
/*     */     }
/*     */     
/* 137 */     LOG.debug("square_x: " + squareX + " square_y: " + squareY);
/* 138 */     Square result = null;
/*     */     
/*     */     try {
/* 141 */       result = getChessboard().getSquare((int)squareX - 1, (int)squareY - 1);
/* 142 */       if (getChessboard().getSettings().isUpsideDown())
/*     */       {
/* 144 */         int x = transposePosition(result.getPozX());
/* 145 */         int y = transposePosition(result.getPozY());
/* 146 */         result = getChessboard().getSquare(x, y);
/*     */       }
/*     */     
/* 149 */     } catch (ArrayIndexOutOfBoundsException exc) {
/*     */       
/* 151 */       LOG.error("!!Array out of bounds when getting Square with Chessboard.getSquare(int,int) : " + exc.getMessage());
/* 152 */       return null;
/*     */     } 
/* 154 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void paintComponent(Graphics g) {
/* 160 */     Graphics2D g2d = (Graphics2D)g;
/* 161 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 162 */     Point topLeftPoint = getTopLeftPoint();
/* 163 */     Square[][] squares = getChessboard().getSquares();
/* 164 */     if (getChessboard().getSettings().isRenderLabels()) {
/*     */       
/* 166 */       drawLabels();
/* 167 */       g2d.drawImage(getUpDownLabel(), 0, 0, null);
/* 168 */       g2d.drawImage(getUpDownLabel(), 0, this.image.getHeight(null) + topLeftPoint.y, null);
/* 169 */       g2d.drawImage(this.leftRightLabel, 0, 0, null);
/* 170 */       g2d.drawImage(this.leftRightLabel, this.image.getHeight(null) + topLeftPoint.x, 0, null);
/*     */     } 
/* 172 */     g2d.drawImage(this.image, topLeftPoint.x, topLeftPoint.y, null);
/* 173 */     drawPieces(squares, g2d);
/*     */     
/* 175 */     Square activeSquare = getChessboard().getActiveSquare();
/*     */     
/* 177 */     if (null != activeSquare) {
/*     */       
/* 179 */       drawActiveSquare(activeSquare, g2d, topLeftPoint, squares);
/* 180 */       drawLegalMoves(g2d, topLeftPoint);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Point getTopLeftPoint() {
/* 187 */     if (getChessboard().getSettings().isRenderLabels())
/*     */     {
/* 189 */       return new Point(this.topLeft.x + getUpDownLabel().getHeight(null), this.topLeft.y + getUpDownLabel().getHeight(null));
/*     */     }
/* 191 */     return this.topLeft;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void resizeChessboard(int height) {
/* 197 */     if (0 != height) {
/*     */       
/* 199 */       BufferedImage resized = new BufferedImage(height, height, 3);
/* 200 */       Graphics g = resized.createGraphics();
/* 201 */       g.drawImage(ChessboardView.orgImage, 0, 0, height, height, null);
/* 202 */       g.dispose();
/* 203 */       if (!getChessboard().getSettings().isRenderLabels())
/*     */       {
/*     */ 
/*     */ 
/*     */         
/* 208 */         height += 2 * getUpDownLabel().getHeight(null);
/*     */       }
/* 210 */       this.image = resized.getScaledInstance(height, height, 0);
/*     */       
/* 212 */       resized = new BufferedImage(height, height, 3);
/* 213 */       g = resized.createGraphics();
/* 214 */       g.drawImage(this.image, 0, 0, height, height, null);
/* 215 */       g.dispose();
/*     */       
/* 217 */       this.squareHeight = (height / 8);
/* 218 */       if (getChessboard().getSettings().isRenderLabels())
/*     */       {
/*     */ 
/*     */ 
/*     */         
/* 223 */         height += 2 * getUpDownLabel().getHeight(null);
/*     */       }
/* 225 */       setSize(height, height);
/*     */       
/* 227 */       resized = new BufferedImage((int)this.squareHeight, (int)this.squareHeight, 3);
/* 228 */       g = resized.createGraphics();
/* 229 */       g.drawImage(ChessboardView.orgAbleSquare, 0, 0, (int)this.squareHeight, (int)this.squareHeight, null);
/* 230 */       g.dispose();
/* 231 */       ChessboardView.ableSquare = resized.getScaledInstance((int)this.squareHeight, (int)this.squareHeight, 0);
/*     */       
/* 233 */       resized = new BufferedImage((int)this.squareHeight, (int)this.squareHeight, 3);
/* 234 */       g = resized.createGraphics();
/* 235 */       g.drawImage(ChessboardView.orgSelSquare, 0, 0, (int)this.squareHeight, (int)this.squareHeight, null);
/* 236 */       g.dispose();
/* 237 */       ChessboardView.selSquare = resized.getScaledInstance((int)this.squareHeight, (int)this.squareHeight, 0);
/* 238 */       this.pieces2D.resize(getSquareHeight());
/* 239 */       drawLabels();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 245 */   protected void drawLabels() { drawLabels((int)this.squareHeight); }
/*     */ 
/*     */ 
/*     */   
/*     */   protected final void drawLabels(int squareHeight) {
/* 250 */     int minLabelHeight = 20;
/* 251 */     int labelHeight = (int)Math.ceil((squareHeight / 4));
/* 252 */     labelHeight = (labelHeight < minLabelHeight) ? minLabelHeight : labelHeight;
/* 253 */     int labelWidth = (int)Math.ceil((squareHeight * 8 + 2 * labelHeight));
/* 254 */     BufferedImage uDL = new BufferedImage(labelWidth + minLabelHeight, labelHeight, 5);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 259 */     Graphics2D graph2D = uDL.createGraphics();
/* 260 */     graph2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 261 */     graph2D.setColor(Color.white);
/*     */     
/* 263 */     graph2D.fillRect(0, 0, labelWidth + minLabelHeight, labelHeight);
/* 264 */     graph2D.setColor(Color.black);
/* 265 */     graph2D.setFont(new Font("Arial", 1, 12));
/* 266 */     int addX = squareHeight / 2;
/*     */     
/* 268 */     if (getChessboard().getSettings().isRenderLabels())
/*     */     {
/* 270 */       addX += labelHeight;
/*     */     }
/*     */     
/* 273 */     if (!getChessboard().getSettings().isUpsideDown()) {
/*     */       
/* 275 */       for (int i = 1; i <= LETTERS.length; i++)
/*     */       {
/* 277 */         graph2D.drawString(LETTERS[i - 1], squareHeight * (i - 1) + addX, 10 + labelHeight / 3);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 282 */       int j = 1;
/* 283 */       for (int i = LETTERS.length; i > 0; i--, j++)
/*     */       {
/* 285 */         graph2D.drawString(LETTERS[i - 1], squareHeight * (j - 1) + addX, 10 + labelHeight / 3);
/*     */       }
/*     */     } 
/* 288 */     graph2D.dispose();
/* 289 */     setUpDownLabel(uDL);
/*     */     
/* 291 */     uDL = new BufferedImage(labelHeight, labelWidth + minLabelHeight, 5);
/* 292 */     graph2D = uDL.createGraphics();
/* 293 */     graph2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 294 */     graph2D.setColor(Color.white);
/*     */     
/* 296 */     graph2D.fillRect(0, 0, labelHeight, labelWidth + minLabelHeight);
/* 297 */     graph2D.setColor(Color.black);
/* 298 */     graph2D.setFont(new Font("Arial", 1, 12));
/*     */     
/* 300 */     if (getChessboard().getSettings().isUpsideDown()) {
/*     */       
/* 302 */       for (int i = 1; i <= 8; i++)
/*     */       {
/* 304 */         graph2D.drawString(Integer.toString(i), 3 + labelHeight / 3, squareHeight * (i - 1) + addX);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 309 */       int j = 1;
/* 310 */       for (int i = 8; i > 0; i--, j++)
/*     */       {
/* 312 */         graph2D.drawString(Integer.toString(i), 3 + labelHeight / 3, squareHeight * (j - 1) + addX);
/*     */       }
/*     */     } 
/* 315 */     graph2D.dispose();
/* 316 */     this.leftRightLabel = uDL;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawLegalMoves(Graphics2D g2d, Point topLeftPoint) {
/* 322 */     if (getChessboard().getSettings().isDisplayLegalMovesEnabled()) {
/*     */       
/* 324 */       Set<Square> moves = getChessboard().getMoves();
/* 325 */       if (null != moves)
/*     */       {
/* 327 */         for (Iterator<Square> it = moves.iterator(); it.hasNext(); ) {
/*     */           
/* 329 */           Square sq = it.next();
/* 330 */           int ableSquarePosX = sq.getPozX();
/* 331 */           int ableSquarePosY = sq.getPozY();
/* 332 */           if (getChessboard().getSettings().isUpsideDown()) {
/*     */             
/* 334 */             ableSquarePosX = transposePosition(ableSquarePosX);
/* 335 */             ableSquarePosY = transposePosition(ableSquarePosY);
/*     */           } 
/* 337 */           g2d.drawImage(ableSquare, ableSquarePosX * (int)this.squareHeight + topLeftPoint.x, ableSquarePosY * (int)this.squareHeight + topLeftPoint.y, null);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawActiveSquare(Square activeSquare, Graphics2D g2d, Point topLeftPoint, Square[][] squares) {
/* 348 */     int activeSquareX = activeSquare.getPozX();
/* 349 */     int activeSquareY = activeSquare.getPozY();
/* 350 */     if (getChessboard().getSettings().isUpsideDown()) {
/*     */       
/* 352 */       activeSquareX = transposePosition(activeSquareX);
/* 353 */       activeSquareY = transposePosition(activeSquareY);
/*     */     } 
/*     */     
/* 356 */     g2d.drawImage(selSquare, activeSquareX * (int)this.squareHeight + topLeftPoint.x, activeSquareY * (int)this.squareHeight + topLeftPoint.y, null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 362 */     Square tmpSquare = squares[activeSquare.getPozX()][activeSquare.getPozY()];
/*     */     
/* 364 */     if (null != tmpSquare.piece) {
/*     */       
/* 366 */       Set<Square> moves = tmpSquare.getPiece().getAllMoves();
/* 367 */       getChessboard().setMoves(moves);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawPieces(Square[][] squares, Graphics2D g2d) {
/* 373 */     for (int i = 0; i < 8; i++) {
/*     */       
/* 375 */       for (int y = 0; y < 8; y++) {
/*     */         
/* 377 */         if (squares[i][y].getPiece() != null) {
/*     */           
/* 379 */           int drawPosI = i;
/* 380 */           int drawPosY = y;
/* 381 */           if (getChessboard().getSettings().isUpsideDown()) {
/*     */             
/* 383 */             drawPosI = transposePosition(drawPosI);
/* 384 */             drawPosY = transposePosition(drawPosY);
/*     */           } 
/* 386 */           Piece piece = squares[i][y].getPiece();
/* 387 */           Image pieceImage = this.pieces2D.getImage(piece.getPlayer().getColor(), piece);
/* 388 */           Pieces2D.draw(this, squares[i][y].getPiece(), drawPosI, drawPosY, g2d, pieceImage);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\display\views\chessboard\implementation\graphic2D\Chessboard2D.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */