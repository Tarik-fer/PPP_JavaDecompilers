// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.core.pieces;

import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.King;
import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Pawn;
import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Knight;
import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Bishop;
import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Rook;
import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Queen;
import pl.art.lach.mateusz.javaopenchess.core.players.Player;
import pl.art.lach.mateusz.javaopenchess.core.Colors;
import pl.art.lach.mateusz.javaopenchess.core.Chessboard;

public class PieceFactory
{
    public static final Piece getPiece(final Chessboard chessboard, final Colors color, final String pieceType, final Player player) {
        return getPiece(chessboard, color.getColorName(), pieceType, player);
    }
    
    public static final Piece getPiece(final Chessboard chessboard, final String color, final String pieceType, final Player player) {
        Piece piece = null;
        switch (pieceType) {
            case "Queen": {
                piece = new Queen(chessboard, player);
                break;
            }
            case "Rook": {
                piece = new Rook(chessboard, player);
                break;
            }
            case "Bishop": {
                piece = new Bishop(chessboard, player);
                break;
            }
            case "Knight": {
                piece = new Knight(chessboard, player);
                break;
            }
            case "Pawn": {
                piece = new Pawn(chessboard, player);
                break;
            }
        }
        return piece;
    }
    
    public static final Piece getPieceFromFenNotation(final Chessboard chessboard, String pieceChar, final Player whitePlayer, final Player blackPlayer) {
        Piece result = null;
        Player player = null;
        if (pieceChar.toLowerCase().equals(pieceChar)) {
            player = blackPlayer;
        }
        else {
            player = whitePlayer;
        }
        final String lowerCase;
        pieceChar = (lowerCase = pieceChar.toLowerCase());
        switch (lowerCase) {
            case "p": {
                result = new Pawn(chessboard, player);
                break;
            }
            case "b": {
                result = new Bishop(chessboard, player);
                break;
            }
            case "q": {
                result = new Queen(chessboard, player);
                break;
            }
            case "r": {
                result = new Rook(chessboard, player);
                break;
            }
            case "k": {
                result = new King(chessboard, player);
                break;
            }
            case "n": {
                result = new Knight(chessboard, player);
                break;
            }
            default: {
                result = null;
                break;
            }
        }
        return result;
    }
}
