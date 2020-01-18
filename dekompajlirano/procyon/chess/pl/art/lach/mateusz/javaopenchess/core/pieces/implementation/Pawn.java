// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.core.pieces.implementation;

import pl.art.lach.mateusz.javaopenchess.core.Square;
import pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.implementation.PawnBehavior;
import pl.art.lach.mateusz.javaopenchess.core.players.Player;
import pl.art.lach.mateusz.javaopenchess.core.Chessboard;
import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;

public class Pawn extends Piece
{
    protected boolean down;
    
    public Pawn(final Chessboard chessboard, final Player player) {
        super(chessboard, player);
        this.value = 1;
        this.symbol = "";
        this.behaviors.add(new PawnBehavior(this));
    }
    
    @Deprecated
    void promote(final Piece newPiece) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public boolean isDown() {
        return this.down;
    }
    
    public static boolean wasEnPassant(final Square from, final Square to) {
        return from.getPozX() != to.getPozX() && from.getPozY() != to.getPozY() && null == to.getPiece();
    }
    
    public static boolean wasTwoFieldsMove(final Square from, final Square to) {
        return Math.abs(from.getPozY() - to.getPozY()) == 2;
    }
    
    public static boolean canBePromoted(final Square end) {
        return end.getPozY() == 0 || end.getPozY() == 7;
    }
}
