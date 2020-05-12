// 
// Decompiled by Procyon v0.5.36
// 

package SJCE.xgui;

import SJCE.xgui.JPanel.BoardUI;

public class Move
{
    private static byte[][] CASTLE;
    public static int NORMAL_MOVE;
    public static int CASTLE_MOVE;
    public static int PROMOTION_MOVE;
    public static int ENPASSANT_MOVE;
    public static int WOO_CASTLE;
    public static int WOOO_CASTLE;
    public static int BOO_CASTLE;
    public static int BOOO_CASTLE;
    public static int ENPASSANT_CAPTURE;
    private short source;
    private short destination;
    private byte piece;
    private byte captured;
    private byte promoted;
    
    public Move() {
        this(-1, -1, -1);
    }
    
    public Move(final int source, final int destination) {
        this(source, destination, -1);
    }
    
    public Move(final int source, final int destination, final int piece) {
        this.source = (short)source;
        this.destination = (short)destination;
        this.piece = (byte)piece;
        this.captured = -1;
        this.promoted = -1;
    }
    
    public int getSource() {
        return this.source;
    }
    
    public int getDestination() {
        return this.destination;
    }
    
    public int getPiece() {
        return this.piece;
    }
    
    public void setPiece(final int piece) {
        this.piece = (byte)piece;
    }
    
    public int getCaptured() {
        return this.captured;
    }
    
    public void setCaptured(final int captured) {
        this.captured = (byte)captured;
    }
    
    public int getPromoted() {
        return this.promoted;
    }
    
    public void setPromoted(final int promoted) {
        this.promoted = (byte)promoted;
    }
    
    public int doMove(final int[] board) {
        this.setPiece(board[this.source]);
        this.setCaptured(board[this.destination]);
        board[this.destination] = board[this.source];
        board[this.source] = -1;
        final Move castle = this.castleMove();
        if (castle != null) {
            board[castle.getDestination()] = board[castle.getSource()];
            board[castle.getSource()] = -1;
            return Move.CASTLE_MOVE;
        }
        final int square = this.enPassant(board);
        if (square != -1) {
            this.setCaptured(Move.ENPASSANT_CAPTURE + board[square]);
            board[square] = -1;
            return Move.ENPASSANT_MOVE;
        }
        return Move.NORMAL_MOVE;
    }
    
    public void undoMove(final int[] board, final int type) {
        board[this.source] = board[this.destination];
        if (type == Move.ENPASSANT_MOVE) {
            board[BoardUI.getSquare(BoardUI.getFile(this.getDestination()), BoardUI.getRank(this.getSource()))] = this.captured - Move.ENPASSANT_CAPTURE;
            board[this.destination] = -1;
        }
        else {
            board[this.destination] = this.captured;
        }
        if (type == Move.CASTLE_MOVE) {
            final int[] squares = this.castleRookMove();
            board[squares[0]] = board[squares[1]];
            board[squares[1]] = -1;
        }
        if (type == Move.PROMOTION_MOVE) {
            throw new UnsupportedOperationException("Not Implemented");
        }
    }
    
    public int undoMove(final int[] board) {
        int type = Move.NORMAL_MOVE;
        if (this.isCastleMove()) {
            type = Move.CASTLE_MOVE;
        }
        if (this.isEnPassant()) {
            type = Move.ENPASSANT_MOVE;
        }
        this.undoMove(board, type);
        return type;
    }
    
    public int[] getAffectedSquares(final int type) {
        if (type == Move.ENPASSANT_MOVE) {
            return new int[] { this.source, this.destination, BoardUI.getSquare(BoardUI.getFile(this.destination), BoardUI.getRank(this.source)) };
        }
        if (type == Move.CASTLE_MOVE) {
            final int[] rookMove = this.castleRookMove();
            return new int[] { this.source, this.destination, rookMove[0], rookMove[1] };
        }
        return new int[] { this.source, this.destination };
    }
    
    private int[] castleRookMove() {
        switch (this.source + this.destination) {
            case 10: {
                return new int[] { Move.CASTLE[Move.WOO_CASTLE][0], Move.CASTLE[Move.WOO_CASTLE][1], 3 };
            }
            case 6: {
                return new int[] { Move.CASTLE[Move.WOOO_CASTLE][0], Move.CASTLE[Move.WOOO_CASTLE][1], 3 };
            }
            case 122: {
                return new int[] { Move.CASTLE[Move.BOO_CASTLE][0], Move.CASTLE[Move.BOO_CASTLE][1], 9 };
            }
            case 118: {
                return new int[] { Move.CASTLE[Move.BOOO_CASTLE][0], Move.CASTLE[Move.BOOO_CASTLE][1], 9 };
            }
            default: {
                return null;
            }
        }
    }
    
    private Move castleMove() {
        if (!this.isCastleMove()) {
            return null;
        }
        final int[] rookMove = this.castleRookMove();
        return new Move(rookMove[0], rookMove[1], rookMove[2]);
    }
    
    private boolean isCastleMove() {
        return PiecesUI.isPiece(PiecesUI.KING, this.piece) && BoardUI.getRankDistance(this.destination, this.source) == 0 && Math.abs(BoardUI.getFileDistance(this.destination, this.source)) == 2;
    }
    
    private boolean isEnPassant() {
        return this.captured >= Move.ENPASSANT_CAPTURE;
    }
    
    private int enPassant(final int[] board) {
        if (!PiecesUI.isPiece(PiecesUI.PAWN, this.piece)) {
            return -1;
        }
        final int distance = Math.abs(BoardUI.getFileDistance(this.destination, this.source));
        if (distance != 1 || board[this.destination] != -1) {
            return -1;
        }
        return BoardUI.getSquare(BoardUI.getFile(this.destination), BoardUI.getRank(this.source));
    }
    
    static {
        Move.CASTLE = new byte[][] { { 7, 5 }, { 0, 3 }, { 63, 61 }, { 56, 59 } };
        Move.NORMAL_MOVE = 100;
        Move.CASTLE_MOVE = 101;
        Move.PROMOTION_MOVE = 102;
        Move.ENPASSANT_MOVE = 103;
        Move.WOO_CASTLE = 0;
        Move.WOOO_CASTLE = 1;
        Move.BOO_CASTLE = 2;
        Move.BOOO_CASTLE = 3;
        Move.ENPASSANT_CAPTURE = 80;
    }
}
