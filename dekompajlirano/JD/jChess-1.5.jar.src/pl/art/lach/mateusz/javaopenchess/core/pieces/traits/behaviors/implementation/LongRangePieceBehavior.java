/*    */ package pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.implementation;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.Square;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.Behavior;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ abstract class LongRangePieceBehavior
/*    */   extends Behavior
/*    */ {
/*    */   protected static final int DIRECTION_LEFT = -1;
/*    */   protected static final int DIRECTION_RIGHT = 1;
/*    */   protected static final int DIRECTION_UP = -1;
/*    */   protected static final int DIRECTION_BOTTOM = 1;
/*    */   protected static final int DIRECTION_NILL = 0;
/*    */   
/* 42 */   public LongRangePieceBehavior(Piece piece) { super(piece); }
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
/*    */   protected Set<Square> getMovesForDirection(int moveX, int moveY) {
/* 54 */     Set<Square> list = new HashSet<>();
/*    */     int i;
/* 56 */     for (int h = this.piece.getSquare().getPozX(); !this.piece.isOut(h, i); h += moveX, i += moveY) {
/*    */       
/* 58 */       if (this.piece.getSquare().getPozX() != h || this.piece.getSquare().getPozY() != i) {
/*    */ 
/*    */ 
/*    */         
/* 62 */         Square sq = this.piece.getChessboard().getSquare(h, i);
/* 63 */         if (null == sq.getPiece() || this.piece.getPlayer() != sq.getPiece().getPlayer()) {
/*    */           
/* 65 */           list.add(this.piece.getChessboard().getSquare(h, i));
/*    */           
/* 67 */           if (this.piece.otherOwner(h, i)) {
/*    */             break;
/*    */           }
/*    */         } else {
/*    */           break;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 77 */     return list;
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\core\pieces\traits\behaviors\implementation\LongRangePieceBehavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */