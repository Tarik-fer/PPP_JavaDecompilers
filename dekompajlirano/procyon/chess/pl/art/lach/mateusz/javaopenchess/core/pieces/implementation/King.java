// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.core.pieces.implementation;

import pl.art.lach.mateusz.javaopenchess.core.moves.Castling;
import pl.art.lach.mateusz.javaopenchess.core.Square;
import pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.Behavior;
import pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.implementation.KingBehavior;
import pl.art.lach.mateusz.javaopenchess.core.players.Player;
import pl.art.lach.mateusz.javaopenchess.core.Chessboard;
import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;

public class King extends Piece
{
    protected boolean wasMotioned;
    
    public King(final Chessboard chessboard, final Player player) {
        super(chessboard, player);
        this.wasMotioned = false;
        this.value = 99;
        this.symbol = "K";
        this.addBehavior(new KingBehavior(this));
    }
    
    public boolean isChecked() {
        return !this.isSafe(this.square);
    }
    
    public int isCheckmatedOrStalemated() {
        if (!this.getAllMoves().isEmpty()) {
            return 0;
        }
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                final Piece piece = this.getChessboard().getSquare(i, j).getPiece();
                if (null != piece && piece.getPlayer() == this.getPlayer() && !piece.getAllMoves().isEmpty()) {
                    return 0;
                }
            }
        }
        if (this.isChecked()) {
            return 1;
        }
        return 2;
    }
    
    public boolean isSafe() {
        return this.isSafe(this.getSquare());
    }
    
    public boolean isSafe(final Square s) {
        final Square[][] squares = this.chessboard.getSquares();
        for (int i = 0; i < squares.length; ++i) {
            for (int j = 0; j < squares[i].length; ++j) {
                final Square sq = squares[i][j];
                final Piece piece = sq.getPiece();
                if (piece != null && piece.getPlayer().getColor() != this.getPlayer().getColor() && piece != this && piece.getSquaresInRange().contains(s)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean willBeSafeAfterMove(final Square currentSquare, final Square futureSquare) {
        final Piece tmp = futureSquare.piece;
        futureSquare.piece = currentSquare.piece;
        currentSquare.piece = null;
        boolean ret = false;
        if (futureSquare.getPiece().getClass() == King.class) {
            ret = this.isSafe(futureSquare);
        }
        else {
            ret = this.isSafe();
        }
        currentSquare.piece = futureSquare.piece;
        futureSquare.piece = tmp;
        return ret;
    }
    
    public boolean willBeSafeAfterMove(final Square futureSquare) {
        return this.willBeSafeAfterMove(this.getSquare(), futureSquare);
    }
    
    public boolean getWasMotioned() {
        return this.wasMotioned;
    }
    
    public void setWasMotioned(final boolean wasMotioned) {
        this.wasMotioned = wasMotioned;
    }
    
    public static Castling getCastling(final Square begin, final Square end) {
        Castling result = Castling.NONE;
        if (begin.getPozX() + 2 == end.getPozX()) {
            result = Castling.SHORT_CASTLING;
        }
        else if (begin.getPozX() - 2 == end.getPozX()) {
            result = Castling.LONG_CASTLING;
        }
        return result;
    }
}
