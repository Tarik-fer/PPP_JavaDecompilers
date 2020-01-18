/*     */ package pl.art.lach.mateusz.javaopenchess.core.pieces.implementation;
/*     */ 
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Chessboard;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Square;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.implementation.PawnBehavior;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.players.Player;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Pawn
/*     */   extends Piece
/*     */ {
/*     */   protected boolean down;
/*     */   
/*     */   public Pawn(Chessboard chessboard, Player player) {
/*  70 */     super(chessboard, player);
/*  71 */     this.value = 1;
/*  72 */     this.symbol = "";
/*  73 */     this.behaviors.add(new PawnBehavior(this));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*  79 */   void promote(Piece newPiece) { throw new UnsupportedOperationException("Not supported yet."); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   public boolean isDown() { return this.down; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  92 */   public static boolean wasEnPassant(Square from, Square to) { return (from.getPozX() != to.getPozX() && from.getPozY() != to.getPozY() && null == to.getPiece()); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   public static boolean wasTwoFieldsMove(Square from, Square to) { return (Math.abs(from.getPozY() - to.getPozY()) == 2); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   public static boolean canBePromoted(Square end) { return (end.getPozY() == 0 || end.getPozY() == 7); }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\core\pieces\implementation\Pawn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */