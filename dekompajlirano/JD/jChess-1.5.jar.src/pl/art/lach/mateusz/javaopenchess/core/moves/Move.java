/*     */ package pl.art.lach.mateusz.javaopenchess.core.moves;
/*     */ 
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Chessboard;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Square;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.King;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Pawn;
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
/*     */ public class Move
/*     */ {
/*  29 */   protected Square from = null;
/*     */   
/*  31 */   protected Square to = null;
/*     */   
/*  33 */   protected Piece movedPiece = null;
/*     */   
/*  35 */   protected Piece takenPiece = null;
/*     */   
/*  37 */   protected Piece promotedTo = null;
/*     */   
/*     */   protected boolean wasEnPassant = false;
/*     */   
/*  41 */   protected Castling castlingMove = Castling.NONE;
/*     */   
/*     */   protected boolean wasPawnTwoFieldsMove = false;
/*     */ 
/*     */   
/*     */   public Move(Square from, Square to, Piece movedPiece, Piece takenPiece, Piece promotedPiece) {
/*  47 */     this(from, to, movedPiece, takenPiece, Castling.NONE, false, promotedPiece);
/*  48 */     if (King.class == movedPiece.getClass())
/*     */     {
/*  50 */       this.castlingMove = King.getCastling(from, to);
/*     */     }
/*  52 */     if (Pawn.class == movedPiece.getClass())
/*     */     {
/*  54 */       this.wasEnPassant = Pawn.wasEnPassant(from, to);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Move(Square from, Square to, Piece movedPiece, Piece takenPiece, Castling castlingMove, boolean wasEnPassant, Piece promotedPiece) {
/*  61 */     this.from = from;
/*  62 */     this.to = to;
/*     */     
/*  64 */     this.movedPiece = movedPiece;
/*  65 */     this.takenPiece = takenPiece;
/*     */     
/*  67 */     this.castlingMove = castlingMove;
/*  68 */     this.wasEnPassant = wasEnPassant;
/*     */     
/*  70 */     if (Pawn.class == movedPiece.getClass() && 
/*  71 */       Math.abs(to.getPozY() - from.getPozY()) == 2) {
/*     */       
/*  73 */       this.wasPawnTwoFieldsMove = true;
/*     */     }
/*  75 */     else if ((Pawn.class == movedPiece.getClass() && to.getPozY() == Chessboard.getBottom()) || (to
/*  76 */       .getPozY() == Chessboard.getTop() && promotedPiece != null)) {
/*     */       
/*  78 */       this.promotedTo = promotedPiece;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  84 */   public Square getFrom() { return this.from; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  89 */   public Square getTo() { return this.to; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  94 */   public Piece getMovedPiece() { return this.movedPiece; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  99 */   public Piece getTakenPiece() { return this.takenPiece; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   public boolean wasEnPassant() { return this.wasEnPassant; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 109 */   public boolean wasPawnTwoFieldsMove() { return this.wasPawnTwoFieldsMove; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 114 */   public Castling getCastlingMove() { return this.castlingMove; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 119 */   public Piece getPromotedPiece() { return this.promotedTo; }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\core\moves\Move.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */