/*    */ package pl.art.lach.mateusz.javaopenchess.core.pieces;
/*    */ 
/*    */ import pl.art.lach.mateusz.javaopenchess.core.Chessboard;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.Colors;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Bishop;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.King;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Knight;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Pawn;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Queen;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Rook;
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
/*    */ public class PieceFactory
/*    */ {
/* 35 */   public static final Piece getPiece(Chessboard chessboard, Colors color, String pieceType, Player player) { return getPiece(chessboard, color.getColorName(), pieceType, player); } public static final Piece getPiece(Chessboard chessboard, String color, String pieceType, Player player) { Pawn pawn;
/*    */     Queen queen;
/*    */     Knight knight;
/*    */     Rook rook;
/*    */     Bishop bishop;
/* 40 */     Piece piece = null;
/* 41 */     switch (pieceType) {
/*    */       
/*    */       case "Queen":
/* 44 */         queen = new Queen(chessboard, player);
/*    */         break;
/*    */       case "Rook":
/* 47 */         rook = new Rook(chessboard, player);
/*    */         break;
/*    */       case "Bishop":
/* 50 */         bishop = new Bishop(chessboard, player);
/*    */         break;
/*    */       case "Knight":
/* 53 */         knight = new Knight(chessboard, player);
/*    */         break;
/*    */       case "Pawn":
/* 56 */         pawn = new Pawn(chessboard, player);
/*    */         break;
/*    */     } 
/* 59 */     return (Piece)pawn; } public static final Piece getPieceFromFenNotation(Chessboard chessboard, String pieceChar, Player whitePlayer, Player blackPlayer) { Rook rook;
/*    */     Bishop bishop;
/*    */     Queen queen;
/*    */     King king;
/*    */     Pawn pawn;
/* 64 */     Piece result = null;
/* 65 */     Player player = null;
/* 66 */     if (pieceChar.toLowerCase().equals(pieceChar)) {
/*    */       
/* 68 */       player = blackPlayer;
/*    */     }
/*    */     else {
/*    */       
/* 72 */       player = whitePlayer;
/*    */     } 
/* 74 */     pieceChar = pieceChar.toLowerCase();
/* 75 */     switch (pieceChar)
/*    */     
/*    */     { case "p":
/* 78 */         return (Piece)new Pawn(chessboard, player);
/*    */       
/*    */       case "b":
/* 81 */         return (Piece)new Bishop(chessboard, player);
/*    */       
/*    */       case "q":
/* 84 */         return (Piece)new Queen(chessboard, player);
/*    */       
/*    */       case "r":
/* 87 */         return (Piece)new Rook(chessboard, player);
/*    */       
/*    */       case "k":
/* 90 */         return (Piece)new King(chessboard, player);
/*    */       
/*    */       case "n":
/* 93 */         knight = new Knight(chessboard, player);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 99 */         return (Piece)knight; }  Knight knight = null; return (Piece)knight; }
/*    */ 
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\core\pieces\PieceFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */