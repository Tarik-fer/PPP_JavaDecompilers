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
/*    */ public class Level2
/*    */   implements AI
/*    */ {
/*    */   public Move getMove(Game game, Move lastMove) {
/* 39 */     Chessboard chessboard = game.getChessboard();
/* 40 */     List<Piece> pieces = chessboard.getAllPieces(game.getActivePlayer().getColor());
/*    */     
/* 42 */     int bestMark = 0;
/* 43 */     List<Move> movesList = new ArrayList<>();
/* 44 */     for (Piece piece : pieces) {
/*    */       
/* 46 */       if (0 < piece.getAllMoves().size()) {
/*    */         
/* 48 */         List<Square> squares = new ArrayList<>(piece.getAllMoves());
/* 49 */         if (squares.size() > 0)
/*    */         {
/* 51 */           for (Square sq : squares) {
/*    */             
/* 53 */             Piece takenPiece = sq.getPiece();
/* 54 */             Object object = null;
/* 55 */             if (piece instanceof Pawn)
/*    */             {
/* 57 */               if (Pawn.canBePromoted(sq))
/*    */               {
/* 59 */                 object = new Queen(chessboard, game.getActivePlayer());
/*    */               }
/*    */             }
/* 62 */             Move move = new Move(piece.getSquare(), sq, piece, sq.getPiece(), (Piece)object);
/* 63 */             int currentMark = 0;
/* 64 */             if (null != takenPiece)
/*    */             {
/* 66 */               currentMark = takenPiece.getValue();
/*    */             }
/* 68 */             if (currentMark > bestMark) {
/*    */               
/* 70 */               movesList.clear();
/* 71 */               movesList.add(move);
/* 72 */               bestMark = currentMark; continue;
/*    */             } 
/* 74 */             if (currentMark == bestMark)
/*    */             {
/* 76 */               movesList.add(move);
/*    */             }
/*    */           } 
/*    */         }
/*    */       } 
/*    */     } 
/* 82 */     int size = movesList.size();
/* 83 */     Random rand = new Random();
/* 84 */     return movesList.get(rand.nextInt(size));
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\core\ai\joc_ai\Level2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */