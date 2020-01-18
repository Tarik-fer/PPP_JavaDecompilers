/*    */ package pl.art.lach.mateusz.javaopenchess.core.pieces.implementation;
/*    */ 
/*    */ import pl.art.lach.mateusz.javaopenchess.core.Chessboard;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.Behavior;
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
/*    */ public class Rook
/*    */   extends Piece
/*    */ {
/*    */   protected boolean wasMotioned = false;
/*    */   
/*    */   public Rook(Chessboard chessboard, Player player) {
/* 47 */     super(chessboard, player);
/* 48 */     this.value = 5;
/* 49 */     this.symbol = "R";
/* 50 */     addBehavior((Behavior)new RookBehavior(this));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 58 */   public boolean getWasMotioned() { return this.wasMotioned; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 66 */   public void setWasMotioned(boolean wasMotioned) { this.wasMotioned = wasMotioned; }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\core\pieces\implementation\Rook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */