// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.implementation;

import java.util.Collection;
import java.util.HashSet;
import pl.art.lach.mateusz.javaopenchess.core.Square;
import java.util.Set;
import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;

public class RookBehavior extends LongRangePieceBehavior
{
    public RookBehavior(final Piece piece) {
        super(piece);
    }
    
    @Override
    public Set<Square> getSquaresInRange() {
        final Set<Square> list = new HashSet<Square>();
        list.addAll(this.getMovesForDirection(0, -1));
        list.addAll(this.getMovesForDirection(-1, 0));
        list.addAll(this.getMovesForDirection(1, 0));
        list.addAll(this.getMovesForDirection(0, 1));
        return list;
    }
}
