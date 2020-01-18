/*     */ package pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.implementation;
/*     */ 
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Square;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.King;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Rook;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.Behavior;
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
/*     */ public class KingBehavior
/*     */   extends Behavior
/*     */ {
/*  33 */   public KingBehavior(King piece) { super((Piece)piece); }
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
/*     */   public Set<Square> getSquaresInRange() {
/*  54 */     Set<Square> list = new HashSet<>();
/*     */ 
/*     */     
/*  57 */     King king = (King)this.piece;
/*     */     
/*  59 */     for (int i = king.getSquare().getPozX() - 1; i <= king.getSquare().getPozX() + 1; i++) {
/*     */       
/*  61 */       for (int y = king.getSquare().getPozY() - 1; y <= king.getSquare().getPozY() + 1; y++) {
/*     */         
/*  63 */         if (!king.isOut(i, y)) {
/*     */           
/*  65 */           Square sq = king.getChessboard().getSquare(i, y);
/*  66 */           if (king.getSquare() != sq)
/*     */           {
/*     */ 
/*     */             
/*  70 */             if (null == sq.getPiece() || sq.getPiece().getPlayer() != this.piece.getPlayer())
/*     */             {
/*  72 */               list.add(sq);
/*     */             }
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*  78 */     if (!king.getWasMotioned()) {
/*     */       
/*  80 */       if (king.getChessboard().getSquares()[0][king.getSquare().getPozY()].getPiece() != null && king
/*  81 */         .getChessboard().getSquares()[0][king.getSquare().getPozY()].getPiece().getName().equals("Rook")) {
/*     */         
/*  83 */         boolean canCastling = true;
/*     */         
/*  85 */         Rook rook = (Rook)king.getChessboard().getSquare(0, king.getSquare().getPozY()).getPiece();
/*  86 */         if (!rook.getWasMotioned()) {
/*     */           
/*  88 */           for (int i = king.getSquare().getPozX() - 1; i > 0; i--) {
/*     */             
/*  90 */             if (king.getChessboard().getSquare(i, king.getSquare().getPozY()).getPiece() != null) {
/*     */               
/*  92 */               canCastling = false;
/*     */               break;
/*     */             } 
/*     */           } 
/*  96 */           Square sq = king.getChessboard().getSquare(king.getSquare().getPozX() - 2, king.getSquare().getPozY());
/*  97 */           Square sq1 = king.getChessboard().getSquare(king.getSquare().getPozX() - 1, king.getSquare().getPozY());
/*     */           
/*  99 */           if (canCastling)
/*     */           {
/* 101 */             list.add(sq);
/*     */           }
/*     */         } 
/*     */       } 
/* 105 */       if (king.getChessboard().getSquares()[7][king.getSquare().getPozY()].getPiece() != null && king
/* 106 */         .getChessboard().getSquares()[7][king.getSquare().getPozY()].getPiece().getName().equals("Rook")) {
/*     */         
/* 108 */         boolean canCastling = true;
/* 109 */         Rook rook = (Rook)king.getChessboard().getSquares()[7][king.getSquare().getPozY()].getPiece();
/* 110 */         if (!rook.getWasMotioned()) {
/*     */           
/* 112 */           for (int i = king.getSquare().getPozX() + 1; i < 7; i++) {
/*     */             
/* 114 */             if (king.getChessboard().getSquares()[i][king.getSquare().getPozY()].getPiece() != null) {
/*     */               
/* 116 */               canCastling = false;
/*     */               break;
/*     */             } 
/*     */           } 
/* 120 */           Square sq = king.getChessboard().getSquares()[king.getSquare().getPozX() + 2][king.getSquare().getPozY()];
/* 121 */           Square sq1 = king.getChessboard().getSquares()[king.getSquare().getPozX() + 1][king.getSquare().getPozY()];
/* 122 */           if (canCastling)
/*     */           {
/* 124 */             list.add(sq);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 129 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<Square> getLegalMoves() {
/* 135 */     Set<Square> list = super.getLegalMoves();
/* 136 */     Set<Square> result = new HashSet<>();
/* 137 */     for (Square sq : list) {
/*     */       
/* 139 */       if (((King)this.piece).isSafe(sq))
/*     */       {
/* 141 */         result.add(sq);
/*     */       }
/*     */     } 
/* 144 */     return result;
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\core\pieces\traits\behaviors\implementation\KingBehavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */