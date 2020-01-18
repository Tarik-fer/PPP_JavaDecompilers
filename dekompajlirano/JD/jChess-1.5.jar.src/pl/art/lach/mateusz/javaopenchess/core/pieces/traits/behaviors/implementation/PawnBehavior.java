/*     */ package pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.implementation;
/*     */ 
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Chessboard;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Colors;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Square;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.King;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.Behavior;
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
/*     */ public class PawnBehavior
/*     */   extends Behavior
/*     */ {
/*  35 */   public PawnBehavior(Piece piece) { super(piece); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<Square> getSquaresInRange() {
/*  41 */     Set<Square> list = new HashSet<>();
/*     */ 
/*     */     
/*  44 */     int first = this.piece.getSquare().getPozY() - 1;
/*  45 */     int second = this.piece.getSquare().getPozY() - 2;
/*  46 */     Chessboard chessboard = this.piece.getChessboard();
/*     */     
/*  48 */     if (this.piece.getPlayer().isGoDown()) {
/*     */       
/*  50 */       first = this.piece.getSquare().getPozY() + 1;
/*  51 */       second = this.piece.getSquare().getPozY() + 2;
/*     */     } 
/*  53 */     if (this.piece.isOut(first, first))
/*     */     {
/*  55 */       return list;
/*     */     }
/*  57 */     Square sq = chessboard.getSquare(this.piece.getSquare().getPozX(), first);
/*  58 */     if (sq.getPiece() == null) {
/*     */       
/*  60 */       King kingWhite = chessboard.getKingWhite();
/*  61 */       King kingBlack = chessboard.getKingBlack();
/*     */       
/*  63 */       list.add(chessboard.getSquares()[this.piece.getSquare().getPozX()][first]);
/*     */       
/*  65 */       if ((this.piece.getPlayer().isGoDown() && this.piece.getSquare().getPozY() == 1) || (!this.piece.getPlayer().isGoDown() && this.piece.getSquare().getPozY() == 6)) {
/*     */         
/*  67 */         Square sq1 = chessboard.getSquare(this.piece.getSquare().getPozX(), second);
/*  68 */         if (sq1.getPiece() == null)
/*     */         {
/*  70 */           list.add(chessboard.getSquare(this.piece.getSquare().getPozX(), second));
/*     */         }
/*     */       } 
/*     */     } 
/*  74 */     if (!this.piece.isOut(this.piece.getSquare().getPozX() - 1, this.piece.getSquare().getPozY())) {
/*     */ 
/*     */       
/*  77 */       sq = chessboard.getSquares()[this.piece.getSquare().getPozX() - 1][first];
/*  78 */       if (sq.getPiece() != null)
/*     */       {
/*  80 */         if (this.piece.getPlayer() != sq.getPiece().getPlayer())
/*     */         {
/*  82 */           list.add(chessboard.getSquares()[this.piece.getSquare().getPozX() - 1][first]);
/*     */         }
/*     */       }
/*     */ 
/*     */       
/*  87 */       sq = chessboard.getSquares()[this.piece.getSquare().getPozX() - 1][this.piece.getSquare().getPozY()];
/*  88 */       if (Chessboard.wasEnPassant(sq))
/*     */       {
/*  90 */         if (this.piece.getPlayer() != sq.getPiece().getPlayer())
/*     */         {
/*  92 */           list.add(chessboard.getSquares()[this.piece.getSquare().getPozX() - 1][first]);
/*     */         }
/*     */       }
/*     */     } 
/*  96 */     if (!this.piece.isOut(this.piece.getSquare().getPozX() + 1, this.piece.getSquare().getPozY())) {
/*     */ 
/*     */       
/*  99 */       sq = chessboard.getSquares()[this.piece.getSquare().getPozX() + 1][first];
/* 100 */       if (sq.getPiece() != null)
/*     */       {
/* 102 */         if (this.piece.getPlayer() != sq.getPiece().getPlayer())
/*     */         {
/* 104 */           list.add(chessboard.getSquares()[this.piece.getSquare().getPozX() + 1][first]);
/*     */         }
/*     */       }
/*     */ 
/*     */       
/* 109 */       sq = chessboard.getSquares()[this.piece.getSquare().getPozX() + 1][this.piece.getSquare().getPozY()];
/* 110 */       if (Chessboard.wasEnPassant(sq))
/*     */       {
/* 112 */         if (this.piece.getPlayer() != sq.getPiece().getPlayer())
/*     */         {
/*     */ 
/*     */           
/* 116 */           if (this.piece.getPlayer().getColor() == Colors.WHITE) {
/*     */             
/* 118 */             list.add(chessboard.getSquares()[this.piece.getSquare().getPozX() + 1][first]);
/*     */           }
/*     */           else {
/*     */             
/* 122 */             list.add(chessboard.getSquares()[this.piece.getSquare().getPozX() + 1][first]);
/*     */           } 
/*     */         }
/*     */       }
/*     */     } 
/* 127 */     return list;
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\core\pieces\traits\behaviors\implementation\PawnBehavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */