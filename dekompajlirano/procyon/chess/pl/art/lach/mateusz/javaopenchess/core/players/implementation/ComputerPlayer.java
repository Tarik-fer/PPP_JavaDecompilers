// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.core.players.implementation;

import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
import pl.art.lach.mateusz.javaopenchess.core.Chessboard;
import pl.art.lach.mateusz.javaopenchess.core.players.PlayerType;
import pl.art.lach.mateusz.javaopenchess.core.Colors;
import pl.art.lach.mateusz.javaopenchess.core.players.Player;

public class ComputerPlayer implements Player
{
    protected String name;
    protected Colors color;
    protected PlayerType playerType;
    protected boolean goDown;
    
    public ComputerPlayer() {
        this.playerType = PlayerType.COMPUTER;
    }
    
    public ComputerPlayer(final String name, final String color) {
        this(name, Colors.valueOf(color.toUpperCase()));
    }
    
    public ComputerPlayer(final String name, final Colors color) {
        this();
        this.name = name;
        this.color = color;
        this.goDown = false;
    }
    
    @Override
    public void setName(final String name) {
        this.name = name;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public void setType(final PlayerType type) {
        this.playerType = type;
    }
    
    @Override
    public Colors getColor() {
        return this.color;
    }
    
    @Override
    public PlayerType getPlayerType() {
        return this.playerType;
    }
    
    @Override
    public boolean isGoDown() {
        return this.goDown;
    }
    
    @Override
    public void setGoDown(final boolean goDown) {
        this.goDown = goDown;
    }
    
    @Override
    public Piece getPromotionPiece(final Chessboard chessboard) {
        return null;
    }
}
