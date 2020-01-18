// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.core.pieces.implementation;

import pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.Behavior;
import pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.implementation.KnightBehavior;
import pl.art.lach.mateusz.javaopenchess.core.players.Player;
import pl.art.lach.mateusz.javaopenchess.core.Chessboard;
import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;

public class Knight extends Piece
{
    public Knight(final Chessboard chessboard, final Player player) {
        super(chessboard, player);
        this.value = 3;
        this.symbol = "N";
        this.addBehavior(new KnightBehavior(this));
    }
}
