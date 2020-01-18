/*    */ package pl.art.lach.mateusz.javaopenchess.core.pieces.implementation;
/*    */ 
/*    */ import pl.art.lach.mateusz.javaopenchess.core.Chessboard;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.Behavior;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.implementation.BishopBehavior;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.implementation.RookBehavior;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.players.Player;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Queen
/*    */   extends Piece
/*    */ {
/*    */   public Queen(Chessboard chessboard, Player player) {
/* 46 */     super(chessboard, player);
/* 47 */     this.value = 9;
/* 48 */     this.symbol = "Q";
/* 49 */     addBehavior((Behavior)new RookBehavior(this));
/* 50 */     addBehavior((Behavior)new BishopBehavior(this));
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\core\pieces\implementation\Queen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */