// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.core.moves;

import pl.art.lach.mateusz.javaopenchess.core.Chessboard;
import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Pawn;
import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.King;
import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
import pl.art.lach.mateusz.javaopenchess.core.Square;

public class Move
{
    protected Square from;
    protected Square to;
    protected Piece movedPiece;
    protected Piece takenPiece;
    protected Piece promotedTo;
    protected boolean wasEnPassant;
    protected Castling castlingMove;
    protected boolean wasPawnTwoFieldsMove;
    
    public Move(final Square from, final Square to, final Piece movedPiece, final Piece takenPiece, final Piece promotedPiece) {
        this(from, to, movedPiece, takenPiece, Castling.NONE, false, promotedPiece);
        if (King.class == movedPiece.getClass()) {
            this.castlingMove = King.getCastling(from, to);
        }
        if (Pawn.class == movedPiece.getClass()) {
            this.wasEnPassant = Pawn.wasEnPassant(from, to);
        }
    }
    
    public Move(final Square from, final Square to, final Piece movedPiece, final Piece takenPiece, final Castling castlingMove, final boolean wasEnPassant, final Piece promotedPiece) {
        this.from = null;
        this.to = null;
        this.movedPiece = null;
        this.takenPiece = null;
        this.promotedTo = null;
        this.wasEnPassant = false;
        this.castlingMove = Castling.NONE;
        this.wasPawnTwoFieldsMove = false;
        this.from = from;
        this.to = to;
        this.movedPiece = movedPiece;
        this.takenPiece = takenPiece;
        this.castlingMove = castlingMove;
        this.wasEnPassant = wasEnPassant;
        if (Pawn.class == movedPiece.getClass() && Math.abs(to.getPozY() - from.getPozY()) == 2) {
            this.wasPawnTwoFieldsMove = true;
        }
        else if ((Pawn.class == movedPiece.getClass() && to.getPozY() == Chessboard.getBottom()) || (to.getPozY() == Chessboard.getTop() && promotedPiece != null)) {
            this.promotedTo = promotedPiece;
        }
    }
    
    public Square getFrom() {
        return this.from;
    }
    
    public Square getTo() {
        return this.to;
    }
    
    public Piece getMovedPiece() {
        return this.movedPiece;
    }
    
    public Piece getTakenPiece() {
        return this.takenPiece;
    }
    
    public boolean wasEnPassant() {
        return this.wasEnPassant;
    }
    
    public boolean wasPawnTwoFieldsMove() {
        return this.wasPawnTwoFieldsMove;
    }
    
    public Castling getCastlingMove() {
        return this.castlingMove;
    }
    
    public Piece getPromotedPiece() {
        return this.promotedTo;
    }
}
