// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.implementation;

import java.util.HashSet;
import pl.art.lach.mateusz.javaopenchess.core.Square;
import java.util.Set;
import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
import pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.Behavior;

public class KnightBehavior extends Behavior
{
    public KnightBehavior(final Piece piece) {
        super(piece);
    }
    
    @Override
    public Set<Square> getSquaresInRange() {
        final Set<Square> list = new HashSet<Square>();
        final Square[][] squares = this.piece.getChessboard().getSquares();
        final int pozX = this.piece.getSquare().getPozX();
        final int pozY = this.piece.getSquare().getPozY();
        final int[][] array;
        final int[][] squaresInRange = array = new int[][] { { pozX - 2, pozY + 1 }, { pozX - 1, pozY + 2 }, { pozX + 1, pozY + 2 }, { pozX + 2, pozY + 1 }, { pozX + 2, pozY - 1 }, { pozX + 1, pozY - 2 }, { pozX - 1, pozY - 2 }, { pozX - 2, pozY - 1 } };
        for (final int[] squareCoordinates : array) {
            final int x = squareCoordinates[0];
            final int y = squareCoordinates[1];
            if (!this.piece.isOut(x, y)) {
                list.add(squares[x][y]);
            }
        }
        return list;
    }
}
