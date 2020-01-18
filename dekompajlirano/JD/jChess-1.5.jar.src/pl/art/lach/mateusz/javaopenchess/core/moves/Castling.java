/*    */ package pl.art.lach.mateusz.javaopenchess.core.moves;
/*    */ 
/*    */ import pl.art.lach.mateusz.javaopenchess.core.Colors;
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
/*    */ public enum Castling
/*    */ {
/* 24 */   NONE("", new int[4], new int[4]),
/*    */   
/* 26 */   SHORT_CASTLING("0-0", new int[] { 4, 7, 6, 7 }, new int[] { 4, 0, 6, 0
/*    */     }),
/* 28 */   LONG_CASTLING("0-0-0", new int[] { 4, 7, 2, 7 }, new int[] { 4, 0, 2, 0 });
/*    */   
/*    */   protected String symbol;
/*    */   
/*    */   protected int[] whiteMove;
/*    */   
/*    */   protected int[] blackMove;
/*    */ 
/*    */   
/*    */   Castling(String symbol, int[] whiteMove, int[] blackMove) {
/* 38 */     this.symbol = symbol;
/* 39 */     this.whiteMove = whiteMove;
/* 40 */     this.blackMove = blackMove;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 45 */   public String getSymbol() { return this.symbol; }
/*    */ 
/*    */ 
/*    */   
/*    */   public int[] getMove(Colors color) {
/* 50 */     if (Colors.BLACK == color)
/*    */     {
/* 52 */       return this.blackMove;
/*    */     }
/*    */ 
/*    */     
/* 56 */     return this.whiteMove;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean isCastling(String moveInPGN) {
/* 62 */     return (moveInPGN.equals(SHORT_CASTLING.getSymbol()) || moveInPGN
/* 63 */       .equals(LONG_CASTLING.getSymbol()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\core\moves\Castling.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */