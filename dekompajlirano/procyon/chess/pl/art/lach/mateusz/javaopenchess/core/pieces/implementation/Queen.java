// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.core.pieces.implementation;

import pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.implementation.BishopBehavior;
import pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.Behavior;
import pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.implementation.RookBehavior;
import pl.art.lach.mateusz.javaopenchess.core.players.Player;
import pl.art.lach.mateusz.javaopenchess.core.Chessboard;
import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;

public class Queen extends Piece
{
    public Queen(final Chessboard chessboard, final Player player) {
        super(chessboard, player);
        this.value = 9;
        this.symbol = "Q";
        this.addBehavior(new RookBehavior(this));
        this.addBehavior(new BishopBehavior(this));
    }
}
