/*    */ package pl.art.lach.mateusz.javaopenchess.core.pieces.implementation;
/*    */ 
/*    */ import pl.art.lach.mateusz.javaopenchess.core.Chessboard;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.Behavior;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.implementation.BishopBehavior;
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
/*    */ 
/*    */ 
/*    */ public class Bishop
/*    */   extends Piece
/*    */ {
/*    */   public Bishop(Chessboard chessboard, Player player) {
/* 47 */     super(chessboard, player);
/* 48 */     this.value = 3;
/* 49 */     this.symbol = "B";
/* 50 */     addBehavior((Behavior)new BishopBehavior(this));
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\core\pieces\implementation\Bishop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */