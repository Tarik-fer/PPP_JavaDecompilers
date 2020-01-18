/*     */ package pl.art.lach.mateusz.javaopenchess.core.players.implementation;
/*     */ 
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Chessboard;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Colors;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.players.Player;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.players.PlayerType;
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
/*     */ public class ComputerPlayer
/*     */   implements Player
/*     */ {
/*     */   protected String name;
/*     */   protected Colors color;
/*  55 */   protected PlayerType playerType = PlayerType.COMPUTER;
/*     */ 
/*     */   
/*     */   protected boolean goDown;
/*     */ 
/*     */ 
/*     */   
/*     */   public ComputerPlayer() {}
/*     */ 
/*     */   
/*  65 */   public ComputerPlayer(String name, String color) { this(name, Colors.valueOf(color.toUpperCase())); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComputerPlayer(String name, Colors color) {
/*  75 */     this();
/*  76 */     this.name = name;
/*  77 */     this.color = color;
/*  78 */     this.goDown = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   public void setName(String name) { this.name = name; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  96 */   public String getName() { return this.name; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 105 */   public void setType(PlayerType type) { this.playerType = type; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 114 */   public Colors getColor() { return this.color; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 123 */   public PlayerType getPlayerType() { return this.playerType; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 132 */   public boolean isGoDown() { return this.goDown; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   public void setGoDown(boolean goDown) { this.goDown = goDown; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 143 */   public Piece getPromotionPiece(Chessboard chessboard) { return null; }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\core\players\implementation\ComputerPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */