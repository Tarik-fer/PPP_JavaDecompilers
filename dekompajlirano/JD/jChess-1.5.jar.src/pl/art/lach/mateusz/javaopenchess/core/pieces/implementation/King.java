/*     */ package pl.art.lach.mateusz.javaopenchess.core.pieces.implementation;
/*     */ 
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Chessboard;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Square;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.moves.Castling;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.Behavior;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.implementation.KingBehavior;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.players.Player;
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
/*     */ 
/*     */ public class King
/*     */   extends Piece
/*     */ {
/*     */   protected boolean wasMotioned = false;
/*     */   
/*     */   public King(Chessboard chessboard, Player player) {
/*  51 */     super(chessboard, player);
/*  52 */     this.value = 99;
/*  53 */     this.symbol = "K";
/*  54 */     addBehavior((Behavior)new KingBehavior(this));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   public boolean isChecked() { return !isSafe(this.square); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int isCheckmatedOrStalemated() {
/*  72 */     if (getAllMoves().isEmpty()) {
/*     */       
/*  74 */       for (int i = 0; i < 8; i++) {
/*     */         
/*  76 */         for (int j = 0; j < 8; j++) {
/*     */           
/*  78 */           Piece piece = getChessboard().getSquare(i, j).getPiece();
/*  79 */           if (null != piece && piece.getPlayer() == getPlayer() && !piece.getAllMoves().isEmpty())
/*     */           {
/*  81 */             return 0;
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/*  86 */       if (isChecked())
/*     */       {
/*  88 */         return 1;
/*     */       }
/*     */ 
/*     */       
/*  92 */       return 2;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  97 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 107 */   public boolean isSafe() { return isSafe(getSquare()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSafe(Square s) {
/* 116 */     Square[][] squares = this.chessboard.getSquares();
/* 117 */     for (int i = 0; i < squares.length; i++) {
/*     */       
/* 119 */       for (int j = 0; j < (squares[i]).length; j++) {
/*     */         
/* 121 */         Square sq = squares[i][j];
/* 122 */         Piece piece = sq.getPiece();
/* 123 */         if (piece != null)
/*     */         {
/* 125 */           if (piece.getPlayer().getColor() != getPlayer().getColor() && piece != this)
/*     */           {
/* 127 */             if (piece.getSquaresInRange().contains(s))
/*     */             {
/* 129 */               return false;
/*     */             }
/*     */           }
/*     */         }
/*     */       } 
/*     */     } 
/* 135 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean willBeSafeAfterMove(Square currentSquare, Square futureSquare) {
/* 145 */     Piece tmp = futureSquare.piece;
/* 146 */     futureSquare.piece = currentSquare.piece;
/* 147 */     currentSquare.piece = null;
/*     */     
/* 149 */     boolean ret = false;
/* 150 */     if (futureSquare.getPiece().getClass() == King.class) {
/*     */       
/* 152 */       ret = isSafe(futureSquare);
/*     */     }
/*     */     else {
/*     */       
/* 156 */       ret = isSafe();
/*     */     } 
/*     */     
/* 159 */     currentSquare.piece = futureSquare.piece;
/* 160 */     futureSquare.piece = tmp;
/*     */     
/* 162 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 171 */   public boolean willBeSafeAfterMove(Square futureSquare) { return willBeSafeAfterMove(getSquare(), futureSquare); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 179 */   public boolean getWasMotioned() { return this.wasMotioned; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 187 */   public void setWasMotioned(boolean wasMotioned) { this.wasMotioned = wasMotioned; }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Castling getCastling(Square begin, Square end) {
/* 192 */     Castling result = Castling.NONE;
/* 193 */     if (begin.getPozX() + 2 == end.getPozX()) {
/*     */       
/* 195 */       result = Castling.SHORT_CASTLING;
/*     */     }
/* 197 */     else if (begin.getPozX() - 2 == end.getPozX()) {
/*     */       
/* 199 */       result = Castling.LONG_CASTLING;
/*     */     } 
/* 201 */     return result;
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\core\pieces\implementation\King.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */