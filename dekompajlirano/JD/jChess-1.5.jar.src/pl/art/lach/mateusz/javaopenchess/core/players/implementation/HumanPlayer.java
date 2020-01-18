/*    */ package pl.art.lach.mateusz.javaopenchess.core.players.implementation;
/*    */ 
/*    */ import pl.art.lach.mateusz.javaopenchess.JChessApp;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.Chessboard;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.Colors;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.pieces.PieceFactory;
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
/*    */ public class HumanPlayer
/*    */   extends ComputerPlayer
/*    */ {
/* 37 */   public HumanPlayer() { this.playerType = PlayerType.LOCAL_USER; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public HumanPlayer(String name, String color) {
/* 47 */     super(name, Colors.valueOf(color.toUpperCase()));
/* 48 */     this.playerType = PlayerType.LOCAL_USER;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public HumanPlayer(String name, Colors color) {
/* 58 */     super(name, color);
/* 59 */     this.playerType = PlayerType.LOCAL_USER;
/*    */   }
/*    */ 
/*    */   
/*    */   public Piece getPromotionPiece(Chessboard chessboard) {
/* 64 */     String colorSymbol = this.color.getSymbolAsString().toUpperCase();
/* 65 */     String newPiece = JChessApp.getJavaChessView().showPawnPromotionBox(colorSymbol);
/* 66 */     return PieceFactory.getPiece(chessboard, colorSymbol, newPiece, this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\core\players\implementation\HumanPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */