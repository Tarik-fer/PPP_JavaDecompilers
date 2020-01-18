/*    */ package pl.art.lach.mateusz.javaopenchess.core.pieces.implementation;
/*    */ 
/*    */ import pl.art.lach.mateusz.javaopenchess.core.Chessboard;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.Behavior;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.implementation.KnightBehavior;
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
/*    */ public class Knight
/*    */   extends Piece
/*    */ {
/*    */   public Knight(Chessboard chessboard, Player player) {
/* 32 */     super(chessboard, player);
/* 33 */     this.value = 3;
/* 34 */     this.symbol = "N";
/* 35 */     addBehavior((Behavior)new KnightBehavior(this));
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\core\pieces\implementation\Knight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */