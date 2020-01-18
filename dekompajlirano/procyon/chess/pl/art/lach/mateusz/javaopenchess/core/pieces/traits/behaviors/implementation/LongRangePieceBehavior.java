// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.implementation;

import java.util.HashSet;
import pl.art.lach.mateusz.javaopenchess.core.Square;
import java.util.Set;
import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
import pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.Behavior;

abstract class LongRangePieceBehavior extends Behavior
{
    protected static final int DIRECTION_LEFT = -1;
    protected static final int DIRECTION_RIGHT = 1;
    protected static final int DIRECTION_UP = -1;
    protected static final int DIRECTION_BOTTOM = 1;
    protected static final int DIRECTION_NILL = 0;
    
    public LongRangePieceBehavior(final Piece piece) {
        super(piece);
    }
    
    protected Set<Square> getMovesForDirection(final int moveX, final int moveY) {
        final Set<Square> list = new HashSet<Square>();
        for (int h = this.piece.getSquare().getPozX(), i = this.piece.getSquare().getPozY(); !this.piece.isOut(h, i); h += moveX, i += moveY) {
            if (this.piece.getSquare().getPozX() != h || this.piece.getSquare().getPozY() != i) {
                final Square sq = this.piece.getChessboard().getSquare(h, i);
                if (null != sq.getPiece() && this.piece.getPlayer() == sq.getPiece().getPlayer()) {
                    break;
                }
                list.add(this.piece.getChessboard().getSquare(h, i));
                if (this.piece.otherOwner(h, i)) {
                    break;
                }
            }
        }
        return list;
    }
}
