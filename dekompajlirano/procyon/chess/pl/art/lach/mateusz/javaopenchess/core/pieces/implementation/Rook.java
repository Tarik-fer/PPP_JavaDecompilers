// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.core.pieces.implementation;

import pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.Behavior;
import pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.implementation.RookBehavior;
import pl.art.lach.mateusz.javaopenchess.core.players.Player;
import pl.art.lach.mateusz.javaopenchess.core.Chessboard;
import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;

public class Rook extends Piece
{
    protected boolean wasMotioned;
    
    public Rook(final Chessboard chessboard, final Player player) {
        super(chessboard, player);
        this.wasMotioned = false;
        this.value = 5;
        this.symbol = "R";
        this.addBehavior(new RookBehavior(this));
    }
    
    public boolean getWasMotioned() {
        return this.wasMotioned;
    }
    
    public void setWasMotioned(final boolean wasMotioned) {
        this.wasMotioned = wasMotioned;
    }
}
