// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.core.players;

import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
import pl.art.lach.mateusz.javaopenchess.core.Chessboard;
import pl.art.lach.mateusz.javaopenchess.core.Colors;
import java.io.Serializable;

public interface Player extends Serializable
{
    public static final String CPU_NAME = "CPU";
    
    Colors getColor();
    
    String getName();
    
    PlayerType getPlayerType();
    
    boolean isGoDown();
    
    void setGoDown(final boolean p0);
    
    void setName(final String p0);
    
    void setType(final PlayerType p0);
    
    Piece getPromotionPiece(final Chessboard p0);
}
