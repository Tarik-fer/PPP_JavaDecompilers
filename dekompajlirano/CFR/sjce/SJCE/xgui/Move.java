/*
 * Decompiled with CFR 0.148.
 */
package SJCE.xgui;

import SJCE.xgui.JPanel.BoardUI;
import SJCE.xgui.PiecesUI;

public class Move {
    private static byte[][] CASTLE = new byte[][]{{7, 5}, {0, 3}, {63, 61}, {56, 59}};
    public static int NORMAL_MOVE = 100;
    public static int CASTLE_MOVE = 101;
    public static int PROMOTION_MOVE = 102;
    public static int ENPASSANT_MOVE = 103;
    public static int WOO_CASTLE = 0;
    public static int WOOO_CASTLE = 1;
    public static int BOO_CASTLE = 2;
    public static int BOOO_CASTLE = 3;
    public static int ENPASSANT_CAPTURE = 80;
    private short source;
    private short destination;
    private byte piece;
    private byte captured;
    private byte promoted;

    public Move() {
        this(-1, -1, -1);
    }

    public Move(int source, int destination) {
        this(source, destination, -1);
    }

    public Move(int source, int destination, int piece) {
        this.source = (short)source;
        this.destination = (short)destination;
        this.piece = (byte)piece;
        this.captured = (byte)-1;
        this.promoted = (byte)-1;
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

    public void setPiece(int piece) {
        this.piece = (byte)piece;
    }

    public int getCaptured() {
        return this.captured;
    }

    public void setCaptured(int captured) {
        this.captured = (byte)captured;
    }

    public int getPromoted() {
        return this.promoted;
    }

    public void setPromoted(int promoted) {
        this.promoted = (byte)promoted;
    }

    public int doMove(int[] board) {
        this.setPiece(board[this.source]);
        this.setCaptured(board[this.destination]);
        board[this.destination] = board[this.source];
        board[this.source] = -1;
        Move castle = this.castleMove();
        if (castle != null) {
            board[castle.getDestination()] = board[castle.getSource()];
            board[castle.getSource()] = -1;
            return CASTLE_MOVE;
        }
        int square = this.enPassant(board);
        if (square != -1) {
            this.setCaptured(ENPASSANT_CAPTURE + board[square]);
            board[square] = -1;
            return ENPASSANT_MOVE;
        }
        return NORMAL_MOVE;
    }

    public void undoMove(int[] board, int type) {
        board[this.source] = board[this.destination];
        if (type == ENPASSANT_MOVE) {
            board[BoardUI.getSquare((int)BoardUI.getFile((int)this.getDestination()), (int)BoardUI.getRank((int)this.getSource()))] = this.captured - ENPASSANT_CAPTURE;
            board[this.destination] = -1;
        } else {
            board[this.destination] = this.captured;
        }
        if (type == CASTLE_MOVE) {
            int[] squares = this.castleRookMove();
            board[squares[0]] = board[squares[1]];
            board[squares[1]] = -1;
        }
        if (type == PROMOTION_MOVE) {
            throw new UnsupportedOperationException("Not Implemented");
        }
    }

    public int undoMove(int[] board) {
        int type = NORMAL_MOVE;
        if (this.isCastleMove()) {
            type = CASTLE_MOVE;
        }
        if (this.isEnPassant()) {
            type = ENPASSANT_MOVE;
        }
        this.undoMove(board, type);
        return type;
    }

    public int[] getAffectedSquares(int type) {
        if (type == ENPASSANT_MOVE) {
            return new int[]{this.source, this.destination, BoardUI.getSquare(BoardUI.getFile(this.destination), BoardUI.getRank(this.source))};
        }
        if (type == CASTLE_MOVE) {
            int[] rookMove = this.castleRookMove();
            return new int[]{this.source, this.destination, rookMove[0], rookMove[1]};
        }
        return new int[]{this.source, this.destination};
    }

    private int[] castleRookMove() {
        switch (this.source + this.destination) {
            case 10: {
                return new int[]{CASTLE[WOO_CASTLE][0], CASTLE[WOO_CASTLE][1], 3};
            }
            case 6: {
                return new int[]{CASTLE[WOOO_CASTLE][0], CASTLE[WOOO_CASTLE][1], 3};
            }
            case 122: {
                return new int[]{CASTLE[BOO_CASTLE][0], CASTLE[BOO_CASTLE][1], 9};
            }
            case 118: {
                return new int[]{CASTLE[BOOO_CASTLE][0], CASTLE[BOOO_CASTLE][1], 9};
            }
        }
        return null;
    }

    private Move castleMove() {
        if (!this.isCastleMove()) {
            return null;
        }
        int[] rookMove = this.castleRookMove();
        return new Move(rookMove[0], rookMove[1], rookMove[2]);
    }

    private boolean isCastleMove() {
        if (!PiecesUI.isPiece(PiecesUI.KING, this.piece)) {
            return false;
        }
        if (BoardUI.getRankDistance(this.destination, this.source) != 0) {
            return false;
        }
        return Math.abs(BoardUI.getFileDistance(this.destination, this.source)) == 2;
    }

    private boolean isEnPassant() {
        return this.captured >= ENPASSANT_CAPTURE;
    }

    private int enPassant(int[] board) {
        if (!PiecesUI.isPiece(PiecesUI.PAWN, this.piece)) {
            return -1;
        }
        int distance = Math.abs(BoardUI.getFileDistance(this.destination, this.source));
        if (distance != 1 || board[this.destination] != -1) {
            return -1;
        }
        return BoardUI.getSquare(BoardUI.getFile(this.destination), BoardUI.getRank(this.source));
    }
}

