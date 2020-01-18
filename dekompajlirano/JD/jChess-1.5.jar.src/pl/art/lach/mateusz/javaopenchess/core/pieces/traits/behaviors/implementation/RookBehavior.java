/*    */ package pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.implementation;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.Square;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
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
/*    */ public class RookBehavior
/*    */   extends LongRangePieceBehavior
/*    */ {
/* 31 */   public RookBehavior(Piece piece) { super(piece); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Set<Square> getSquaresInRange() {
/* 41 */     Set<Square> list = new HashSet<>();
/*    */     
/* 43 */     list.addAll(getMovesForDirection(0, -1));
/* 44 */     list.addAll(getMovesForDirection(-1, 0));
/* 45 */     list.addAll(getMovesForDirection(1, 0));
/* 46 */     list.addAll(getMovesForDirection(0, 1));
/*    */     
/* 48 */     return list;
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\core\pieces\traits\behaviors\implementation\RookBehavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */