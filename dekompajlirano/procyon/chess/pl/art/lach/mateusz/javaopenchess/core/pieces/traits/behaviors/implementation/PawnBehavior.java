// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.implementation;

import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.King;
import pl.art.lach.mateusz.javaopenchess.core.Colors;
import pl.art.lach.mateusz.javaopenchess.core.Chessboard;
import java.util.HashSet;
import pl.art.lach.mateusz.javaopenchess.core.Square;
import java.util.Set;
import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
import pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.Behavior;

public class PawnBehavior extends Behavior
{
    public PawnBehavior(final Piece piece) {
        super(piece);
    }
    
    @Override
    public Set<Square> getSquaresInRange() {
        final Set<Square> list = new HashSet<Square>();
        int first = this.piece.getSquare().getPozY() - 1;
        int second = this.piece.getSquare().getPozY() - 2;
        final Chessboard chessboard = this.piece.getChessboard();
        if (this.piece.getPlayer().isGoDown()) {
            first = this.piece.getSquare().getPozY() + 1;
            second = this.piece.getSquare().getPozY() + 2;
        }
        if (this.piece.isOut(first, first)) {
            return list;
        }
        Square sq = chessboard.getSquare(this.piece.getSquare().getPozX(), first);
        if (sq.getPiece() == null) {
            final King kingWhite = chessboard.getKingWhite();
            final King kingBlack = chessboard.getKingBlack();
            list.add(chessboard.getSquares()[this.piece.getSquare().getPozX()][first]);
            if ((this.piece.getPlayer().isGoDown() && this.piece.getSquare().getPozY() == 1) || (!this.piece.getPlayer().isGoDown() && this.piece.getSquare().getPozY() == 6)) {
                final Square sq2 = chessboard.getSquare(this.piece.getSquare().getPozX(), second);
                if (sq2.getPiece() == null) {
                    list.add(chessboard.getSquare(this.piece.getSquare().getPozX(), second));
                }
            }
        }
        if (!this.piece.isOut(this.piece.getSquare().getPozX() - 1, this.piece.getSquare().getPozY())) {
            sq = chessboard.getSquares()[this.piece.getSquare().getPozX() - 1][first];
            if (sq.getPiece() != null && this.piece.getPlayer() != sq.getPiece().getPlayer()) {
                list.add(chessboard.getSquares()[this.piece.getSquare().getPozX() - 1][first]);
            }
            sq = chessboard.getSquares()[this.piece.getSquare().getPozX() - 1][this.piece.getSquare().getPozY()];
            if (Chessboard.wasEnPassant(sq) && this.piece.getPlayer() != sq.getPiece().getPlayer()) {
                list.add(chessboard.getSquares()[this.piece.getSquare().getPozX() - 1][first]);
            }
        }
        if (!this.piece.isOut(this.piece.getSquare().getPozX() + 1, this.piece.getSquare().getPozY())) {
            sq = chessboard.getSquares()[this.piece.getSquare().getPozX() + 1][first];
            if (sq.getPiece() != null && this.piece.getPlayer() != sq.getPiece().getPlayer()) {
                list.add(chessboard.getSquares()[this.piece.getSquare().getPozX() + 1][first]);
            }
            sq = chessboard.getSquares()[this.piece.getSquare().getPozX() + 1][this.piece.getSquare().getPozY()];
            if (Chessboard.wasEnPassant(sq) && this.piece.getPlayer() != sq.getPiece().getPlayer()) {
                if (this.piece.getPlayer().getColor() == Colors.WHITE) {
                    list.add(chessboard.getSquares()[this.piece.getSquare().getPozX() + 1][first]);
                }
                else {
                    list.add(chessboard.getSquares()[this.piece.getSquare().getPozX() + 1][first]);
                }
            }
        }
        return list;
    }
}
