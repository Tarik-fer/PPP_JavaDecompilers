// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors;

import java.util.Iterator;
import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.King;
import java.util.HashSet;
import pl.art.lach.mateusz.javaopenchess.core.Colors;
import pl.art.lach.mateusz.javaopenchess.core.Square;
import java.util.Set;
import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;

public abstract class Behavior
{
    protected Piece piece;
    
    public Behavior(final Piece piece) {
        this.piece = piece;
    }
    
    public abstract Set<Square> getSquaresInRange();
    
    public Set<Square> getLegalMoves() {
        final King ourKing = (this.piece.getPlayer().getColor() == Colors.WHITE) ? this.piece.getChessboard().getKingWhite() : this.piece.getChessboard().getKingBlack();
        final King oponentsKing = (this.piece.getPlayer().getColor() == Colors.WHITE) ? this.piece.getChessboard().getKingBlack() : this.piece.getChessboard().getKingWhite();
        final Set<Square> result = new HashSet<Square>();
        for (final Square sq : this.getSquaresInRange()) {
            if (this.canMove(this.piece, sq, ourKing, oponentsKing)) {
                result.add(sq);
            }
        }
        return result;
    }
    
    private boolean canMove(final Piece piece, final Square sq, final King ourKing, final King oponentsKing) {
        return ourKing.willBeSafeAfterMove(piece.getSquare(), sq) && (null == sq.getPiece() || piece.getPlayer() != sq.getPiece().getPlayer()) && sq.getPiece() != oponentsKing;
    }
}
