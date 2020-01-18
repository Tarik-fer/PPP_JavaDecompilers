// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.core.players.implementation;

import pl.art.lach.mateusz.javaopenchess.core.players.Player;
import pl.art.lach.mateusz.javaopenchess.core.pieces.PieceFactory;
import pl.art.lach.mateusz.javaopenchess.JChessApp;
import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
import pl.art.lach.mateusz.javaopenchess.core.Chessboard;
import pl.art.lach.mateusz.javaopenchess.core.Colors;
import pl.art.lach.mateusz.javaopenchess.core.players.PlayerType;

public class HumanPlayer extends ComputerPlayer
{
    public HumanPlayer() {
        this.playerType = PlayerType.LOCAL_USER;
    }
    
    public HumanPlayer(final String name, final String color) {
        super(name, Colors.valueOf(color.toUpperCase()));
        this.playerType = PlayerType.LOCAL_USER;
    }
    
    public HumanPlayer(final String name, final Colors color) {
        super(name, color);
        this.playerType = PlayerType.LOCAL_USER;
    }
    
    @Override
    public Piece getPromotionPiece(final Chessboard chessboard) {
        final String colorSymbol = this.color.getSymbolAsString().toUpperCase();
        final String newPiece = JChessApp.getJavaChessView().showPawnPromotionBox(colorSymbol);
        return PieceFactory.getPiece(chessboard, colorSymbol, newPiece, this);
    }
}
