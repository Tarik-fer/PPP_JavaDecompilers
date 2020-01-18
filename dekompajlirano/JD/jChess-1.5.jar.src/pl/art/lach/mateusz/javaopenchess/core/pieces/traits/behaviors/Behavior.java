/*    */ package pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.Colors;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.Square;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.King;
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
/*    */ public abstract class Behavior
/*    */ {
/*    */   protected Piece piece;
/*    */   
/* 33 */   public Behavior(Piece piece) { this.piece = piece; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract Set<Square> getSquaresInRange();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Set<Square> getLegalMoves() {
/* 54 */     King ourKing = (this.piece.getPlayer().getColor() == Colors.WHITE) ? this.piece.getChessboard().getKingWhite() : this.piece.getChessboard().getKingBlack();
/*    */ 
/*    */     
/* 57 */     King oponentsKing = (this.piece.getPlayer().getColor() == Colors.WHITE) ? this.piece.getChessboard().getKingBlack() : this.piece.getChessboard().getKingWhite();
/*    */     
/* 59 */     Set<Square> result = new HashSet<>();
/* 60 */     for (Square sq : getSquaresInRange()) {
/*    */       
/* 62 */       if (canMove(this.piece, sq, ourKing, oponentsKing))
/*    */       {
/* 64 */         result.add(sq);
/*    */       }
/*    */     } 
/* 67 */     return result;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean canMove(Piece piece, Square sq, King ourKing, King oponentsKing) {
/* 72 */     return (ourKing.willBeSafeAfterMove(piece.getSquare(), sq) && (null == sq
/* 73 */       .getPiece() || piece.getPlayer() != sq.getPiece().getPlayer()) && sq
/* 74 */       .getPiece() != oponentsKing);
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\core\pieces\traits\behaviors\Behavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */