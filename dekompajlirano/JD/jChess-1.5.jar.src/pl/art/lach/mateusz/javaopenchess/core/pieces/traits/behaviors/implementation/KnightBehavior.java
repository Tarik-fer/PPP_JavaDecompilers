/*    */ package pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.implementation;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.Square;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.Behavior;
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
/*    */ public class KnightBehavior
/*    */   extends Behavior
/*    */ {
/* 32 */   public KnightBehavior(Piece piece) { super(piece); }
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
/*    */   public Set<Square> getSquaresInRange() {
/* 54 */     Set<Square> list = new HashSet<>();
/* 55 */     Square[][] squares = this.piece.getChessboard().getSquares();
/*    */     
/* 57 */     int pozX = this.piece.getSquare().getPozX();
/* 58 */     int pozY = this.piece.getSquare().getPozY();
/*    */     
/* 60 */     int[][] squaresInRange = { { pozX - 2, pozY + 1 }, { pozX - 1, pozY + 2 }, { pozX + 1, pozY + 2 }, { pozX + 2, pozY + 1 }, { pozX + 2, pozY - 1 }, { pozX + 1, pozY - 2 }, { pozX - 1, pozY - 2 }, { pozX - 2, pozY - 1 } };
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
/* 71 */     for (int[] squareCoordinates : squaresInRange) {
/*    */       
/* 73 */       int x = squareCoordinates[0];
/* 74 */       int y = squareCoordinates[1];
/* 75 */       if (!this.piece.isOut(x, y))
/*    */       {
/* 77 */         list.add(squares[x][y]);
/*    */       }
/*    */     } 
/* 80 */     return list;
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\core\pieces\traits\behaviors\implementation\KnightBehavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */