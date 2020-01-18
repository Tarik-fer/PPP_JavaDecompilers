/*    */ package pl.art.lach.mateusz.javaopenchess.core.players.implementation;
/*    */ 
/*    */ import pl.art.lach.mateusz.javaopenchess.core.Chessboard;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.Colors;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.players.PlayerType;
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
/*    */ public class NetworkPlayer
/*    */   extends ComputerPlayer
/*    */ {
/* 37 */   public NetworkPlayer() { this.playerType = PlayerType.NETWORK_USER; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public NetworkPlayer(String name, String color) {
/* 47 */     super(name, color);
/* 48 */     this.playerType = PlayerType.NETWORK_USER;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public NetworkPlayer(String name, Colors color) {
/* 58 */     super(name, color);
/* 59 */     this.playerType = PlayerType.NETWORK_USER;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 65 */   public Piece getPromotionPiece(Chessboard chessboard) { return null; }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\core\players\implementation\NetworkPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */