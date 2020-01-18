/*     */ package pl.art.lach.mateusz.javaopenchess.core.pieces;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import org.apache.log4j.Logger;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Chessboard;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Colors;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Square;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.Behavior;
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
/*     */ public abstract class Piece
/*     */ {
/*  36 */   private static final Logger LOG = Logger.getLogger(Piece.class);
/*     */   
/*     */   protected Chessboard chessboard;
/*     */   
/*     */   protected Square square;
/*     */   
/*     */   protected Player player;
/*     */   
/*     */   protected String name;
/*     */   
/*     */   protected String symbol;
/*     */   
/*  48 */   protected short value = 0;
/*     */   
/*  50 */   protected Set<Behavior> behaviors = new HashSet<>();
/*     */ 
/*     */   
/*     */   public Piece(Chessboard chessboard, Player player) {
/*  54 */     this.chessboard = chessboard;
/*  55 */     this.player = player;
/*  56 */     this.name = getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   public short getValue() { return this.value; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  70 */   public final void addBehavior(Behavior behavior) { this.behaviors.add(behavior); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   public final Set<Behavior> getBehaviors() { return Collections.unmodifiableSet(this.behaviors); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   public void setBehaviors(Set<Behavior> behaviors) { this.behaviors = behaviors; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void clean() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean canMove(Square square, ArrayList allmoves) {
/*  91 */     ArrayList moves = allmoves;
/*  92 */     for (Iterator<Square> it = moves.iterator(); it.hasNext(); ) {
/*     */       
/*  94 */       Square sq = it.next();
/*  95 */       if (sq == square)
/*     */       {
/*  97 */         return true;
/*     */       }
/*     */     } 
/* 100 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canMove(int newX, int newY) {
/* 111 */     boolean result = false;
/*     */     
/* 113 */     Square[][] squares = this.chessboard.getSquares();
/* 114 */     if (!isOut(newX, newY) && checkPiece(newX, newY))
/*     */     {
/* 116 */       if (getPlayer().getColor() == Colors.WHITE) {
/*     */         
/* 118 */         if (this.chessboard.getKingWhite().willBeSafeAfterMove(this.square, squares[newX][newY]))
/*     */         {
/* 120 */           result = true;
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 125 */       else if (this.chessboard.getKingBlack().willBeSafeAfterMove(this.square, squares[newX][newY])) {
/*     */         
/* 127 */         result = true;
/*     */       } 
/*     */     }
/*     */     
/* 131 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<Square> getAllMoves() {
/* 140 */     Set<Square> moves = new HashSet<>();
/* 141 */     for (Behavior behavior : this.behaviors)
/*     */     {
/* 143 */       moves.addAll(behavior.getLegalMoves());
/*     */     }
/* 145 */     return moves;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<Square> getSquaresInRange() {
/* 150 */     Set<Square> moves = new HashSet<>();
/* 151 */     for (Behavior behavior : this.behaviors)
/*     */     {
/* 153 */       moves.addAll(behavior.getSquaresInRange());
/*     */     }
/* 155 */     return moves;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 165 */   public boolean isOut(int x, int y) { return (x < 0 || x > 7 || y < 0 || y > 7); }
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
/*     */   public boolean checkPiece(int x, int y) {
/* 177 */     if ((getChessboard().getSquares()[x][y]).piece != null && 
/* 178 */       getChessboard().getSquares()[x][y].getPiece().getName().equals("King"))
/*     */     {
/* 180 */       return false;
/*     */     }
/* 182 */     Piece piece = (getChessboard().getSquares()[x][y]).piece;
/* 183 */     if (piece == null || piece
/* 184 */       .getPlayer() != getPlayer())
/*     */     {
/* 186 */       return true;
/*     */     }
/* 188 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean otherOwner(int x, int y) {
/* 198 */     Square sq = getChessboard().getSquare(x, y);
/* 199 */     if (sq.piece == null)
/*     */     {
/* 201 */       return false;
/*     */     }
/* 203 */     if (getPlayer() != sq.getPiece().getPlayer())
/*     */     {
/* 205 */       return true;
/*     */     }
/* 207 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 212 */   public String getSymbol() { return this.symbol; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 220 */   public Chessboard getChessboard() { return this.chessboard; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 228 */   public void setChessboard(Chessboard chessboard) { this.chessboard = chessboard; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 236 */   public Square getSquare() { return this.square; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 244 */   public void setSquare(Square square) { this.square = square; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 252 */   public Player getPlayer() { return this.player; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 260 */   public void setPlayer(Player player) { this.player = player; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 268 */   public String getName() { return this.name; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 276 */   public void setName(String name) { this.name = name; }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\core\pieces\Piece.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */