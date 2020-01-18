/*     */ package pl.art.lach.mateusz.javaopenchess.core;
/*     */ 
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
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
/*     */ public class Square
/*     */ {
/*     */   protected int pozX;
/*     */   protected int pozY;
/*  42 */   public Piece piece = null;
/*     */ 
/*     */   
/*     */   public Square(int pozX, int pozY, Piece piece) {
/*  46 */     this.pozX = pozX;
/*  47 */     this.pozY = pozY;
/*  48 */     this.piece = piece;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Square(Square square) {
/*  54 */     this.pozX = square.pozX;
/*  55 */     this.pozY = square.pozY;
/*  56 */     this.piece = square.piece;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  61 */   public Square clone(Square square) { return new Square(square); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPiece(Piece piece) {
/*  66 */     this.piece = piece;
/*  67 */     if (null != this.piece)
/*     */     {
/*  69 */       this.piece.setSquare(this);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   public int getPozX() { return this.pozX; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   public void setPozX(int pozX) { this.pozX = pozX; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  94 */   public int getPozY() { return this.pozY; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   public void setPozY(int pozY) { this.pozY = pozY; }
/*     */ 
/*     */ 
/*     */   
/* 106 */   public Piece getPiece() { return this.piece; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 111 */   public boolean isEmptyOrSamePiece(Piece piece) { return (null == this.piece || this.piece == piece); }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAlgebraicNotation() {
/* 116 */     String letter = String.valueOf((char)(this.pozX + 97));
/* 117 */     String result = letter + (Math.abs(7 - this.pozY) + 1);
/* 118 */     return result;
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\core\Square.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */