// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.core.players.implementation;

import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
import pl.art.lach.mateusz.javaopenchess.core.Chessboard;
import pl.art.lach.mateusz.javaopenchess.core.Colors;
import pl.art.lach.mateusz.javaopenchess.core.players.PlayerType;

public class NetworkPlayer extends ComputerPlayer
{
    public NetworkPlayer() {
        this.playerType = PlayerType.NETWORK_USER;
    }
    
    public NetworkPlayer(final String name, final String color) {
        super(name, color);
        this.playerType = PlayerType.NETWORK_USER;
    }
    
    public NetworkPlayer(final String name, final Colors color) {
        super(name, color);
        this.playerType = PlayerType.NETWORK_USER;
    }
    
    @Override
    public Piece getPromotionPiece(final Chessboard chessboard) {
        return null;
    }
}
