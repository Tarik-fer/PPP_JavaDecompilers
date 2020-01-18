// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.implementation;

import java.util.Iterator;
import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Rook;
import java.util.HashSet;
import pl.art.lach.mateusz.javaopenchess.core.Square;
import java.util.Set;
import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.King;
import pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.Behavior;

public class KingBehavior extends Behavior
{
    public KingBehavior(final King piece) {
        super(piece);
    }
    
    @Override
    public Set<Square> getSquaresInRange() {
        final Set<Square> list = new HashSet<Square>();
        final King king = (King)this.piece;
        for (int i = king.getSquare().getPozX() - 1; i <= king.getSquare().getPozX() + 1; ++i) {
            for (int y = king.getSquare().getPozY() - 1; y <= king.getSquare().getPozY() + 1; ++y) {
                if (!king.isOut(i, y)) {
                    final Square sq = king.getChessboard().getSquare(i, y);
                    if (king.getSquare() != sq) {
                        if (null == sq.getPiece() || sq.getPiece().getPlayer() != this.piece.getPlayer()) {
                            list.add(sq);
                        }
                    }
                }
            }
        }
        if (!king.getWasMotioned()) {
            if (king.getChessboard().getSquares()[0][king.getSquare().getPozY()].getPiece() != null && king.getChessboard().getSquares()[0][king.getSquare().getPozY()].getPiece().getName().equals("Rook")) {
                boolean canCastling = true;
                final Rook rook = (Rook)king.getChessboard().getSquare(0, king.getSquare().getPozY()).getPiece();
                if (!rook.getWasMotioned()) {
                    for (int j = king.getSquare().getPozX() - 1; j > 0; --j) {
                        if (king.getChessboard().getSquare(j, king.getSquare().getPozY()).getPiece() != null) {
                            canCastling = false;
                            break;
                        }
                    }
                    final Square sq = king.getChessboard().getSquare(king.getSquare().getPozX() - 2, king.getSquare().getPozY());
                    final Square sq2 = king.getChessboard().getSquare(king.getSquare().getPozX() - 1, king.getSquare().getPozY());
                    if (canCastling) {
                        list.add(sq);
                    }
                }
            }
            if (king.getChessboard().getSquares()[7][king.getSquare().getPozY()].getPiece() != null && king.getChessboard().getSquares()[7][king.getSquare().getPozY()].getPiece().getName().equals("Rook")) {
                boolean canCastling = true;
                final Rook rook = (Rook)king.getChessboard().getSquares()[7][king.getSquare().getPozY()].getPiece();
                if (!rook.getWasMotioned()) {
                    for (int j = king.getSquare().getPozX() + 1; j < 7; ++j) {
                        if (king.getChessboard().getSquares()[j][king.getSquare().getPozY()].getPiece() != null) {
                            canCastling = false;
                            break;
                        }
                    }
                    final Square sq = king.getChessboard().getSquares()[king.getSquare().getPozX() + 2][king.getSquare().getPozY()];
                    final Square sq2 = king.getChessboard().getSquares()[king.getSquare().getPozX() + 1][king.getSquare().getPozY()];
                    if (canCastling) {
                        list.add(sq);
                    }
                }
            }
        }
        return list;
    }
    
    @Override
    public Set<Square> getLegalMoves() {
        final Set<Square> list = super.getLegalMoves();
        final Set<Square> result = new HashSet<Square>();
        for (final Square sq : list) {
            if (((King)this.piece).isSafe(sq)) {
                result.add(sq);
            }
        }
        return result;
    }
}
