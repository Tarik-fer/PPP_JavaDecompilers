/*    */ package pl.art.lach.mateusz.javaopenchess.core.ai.joc_ai;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.Chessboard;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.Game;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.Square;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.ai.AI;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.moves.Move;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Pawn;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Queen;
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
/*    */ public class Level1
/*    */   implements AI
/*    */ {
/*    */   public Move getMove(Game game, Move lastMove) {
/* 39 */     Chessboard chessboard = game.getChessboard();
/* 40 */     List<Piece> pieces = chessboard.getAllPieces(game.getActivePlayer().getColor());
/* 41 */     List<Piece> moveAblePieces = new ArrayList<>();
/*    */     
/* 43 */     for (Piece piece : pieces) {
/*    */       
/* 45 */       if (0 < piece.getAllMoves().size())
/*    */       {
/* 47 */         moveAblePieces.add(piece);
/*    */       }
/*    */     } 
/*    */     
/* 51 */     int size = moveAblePieces.size();
/* 52 */     Random rand = new Random();
/* 53 */     int random = rand.nextInt(size);
/*    */     
/* 55 */     Piece piece = moveAblePieces.get(random);
/* 56 */     Object object = null;
/* 57 */     size = piece.getAllMoves().size();
/* 58 */     random = rand.nextInt(size);
/*    */     
/* 60 */     List<Square> squares = new ArrayList<>(piece.getAllMoves());
/* 61 */     Square sq = squares.get(random);
/* 62 */     if (piece instanceof Pawn)
/*    */     {
/* 64 */       if (Pawn.canBePromoted(sq))
/*    */       {
/* 66 */         object = new Queen(chessboard, game.getActivePlayer());
/*    */       }
/*    */     }
/* 69 */     return new Move(piece.getSquare(), sq, piece, sq.getPiece(), (Piece)object);
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\core\ai\joc_ai\Level1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */