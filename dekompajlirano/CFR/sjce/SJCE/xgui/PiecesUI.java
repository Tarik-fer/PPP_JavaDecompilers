/*
 * Decompiled with CFR 0.148.
 */
package SJCE.xgui;

import java.util.Arrays;

public class PiecesUI {
    public static final int NO_PIECE = -1;
    public static final int PIECE_ACCUMULATED = 32;
    public static final int PIECE_TOTAL = 12;
    public static final int PIECE_SIDE = 6;
    public static final int[] PAWN = new int[]{0, 6};
    public static final int[] KNIGHT = new int[]{1, 7};
    public static final int[] BISHOP = new int[]{2, 8};
    public static final int[] ROOK = new int[]{3, 9};
    public static final int[] QUEEN = new int[]{4, 10};
    public static final int[] KING = new int[]{5, 11};
    public static final int WHITE_PAWN = 0;
    public static final int WHITE_KNIGHT = 1;
    public static final int WHITE_BISHOP = 2;
    public static final int WHITE_ROOK = 3;
    public static final int WHITE_QUEEN = 4;
    public static final int WHITE_KING = 5;
    public static final int BLACK_PAWN = 6;
    public static final int BLACK_KNIGHT = 7;
    public static final int BLACK_BISHOP = 8;
    public static final int BLACK_ROOK = 9;
    public static final int BLACK_QUEEN = 10;
    public static final int BLACK_KING = 11;
    public static final int WHITE_START = 0;
    public static final int WHITE_END = 5;
    public static final int BLACK_START = 6;
    public static final int BLACK_END = 11;
    public static final int PIECE_MIN = 0;
    public static final int PIECE_MAX = 11;
    public static final int COLOR_WHITE = 0;
    public static final int COLOR_BLACK = 1;
    private int[] board = new int[64];

    public PiecesUI() {
        Arrays.fill(this.board, -1);
    }

    public static boolean isPiece(int[] expected, int unknown) {
        return unknown % 6 == expected[0];
    }

    public static int getColor(int piece) {
        if (piece >= 0 && piece <= 5) {
            return 0;
        }
        if (piece >= 6 && piece <= 11) {
            return 1;
        }
        throw new IllegalArgumentException("Invalid piece: " + piece);
    }

    public static boolean sameColor(int piece1, int piece2) {
        return PiecesUI.getColor(piece1) == PiecesUI.getColor(piece2);
    }

    public int[] getBoard() {
        return this.board;
    }

    public void setBoard(int[] board) {
        System.arraycopy(board, 0, this.board, 0, board.length);
    }

    public int getPiece(int square) {
        return this.board[square];
    }

    public void setPiece(int piece, int square) {
        this.board[square] = piece;
    }

    public void removePiece(int square) {
        this.board[square] = -1;
    }

    public int indexOf(int piece) {
        for (int i = 0; i < this.board.length; ++i) {
            if (this.board[i] != piece) continue;
            return i;
        }
        return -1;
    }

    public static int indexOf(int[] board, int piece) {
        for (int i = 0; i < board.length; ++i) {
            if (board[i] != piece) continue;
            return i;
        }
        return -1;
    }

    public static int switchColor(int color) {
        return (color + 1) % 2;
    }
}

