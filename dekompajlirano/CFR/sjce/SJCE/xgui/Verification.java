/*
 * Decompiled with CFR 0.148.
 */
package SJCE.xgui;

import SJCE.xgui.JPanel.BoardUI;
import SJCE.xgui.Move;
import SJCE.xgui.PiecesUI;
import SJCE.xgui.Utility;
import java.util.HashMap;

public class Verification {
    public static final byte RANK_2 = 1;
    public static final byte RANK_7 = 6;
    private static final int OFF = -10;
    private static final int[] toGeneralBoard = new int[]{-10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, 0, 1, 2, 3, 4, 5, 6, 7, -10, -10, -10, -10, 8, 9, 10, 11, 12, 13, 14, 15, -10, -10, -10, -10, 16, 17, 18, 19, 20, 21, 22, 23, -10, -10, -10, -10, 24, 25, 26, 27, 28, 29, 30, 31, -10, -10, -10, -10, 32, 33, 34, 35, 36, 37, 38, 39, -10, -10, -10, -10, 40, 41, 42, 43, 44, 45, 46, 47, -10, -10, -10, -10, 48, 49, 50, 51, 52, 53, 54, 55, -10, -10, -10, -10, 56, 57, 58, 59, 60, 61, 62, 63, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10};
    private static final int[] toTestBoard = new int[]{26, 27, 28, 29, 30, 31, 32, 33, 38, 39, 40, 41, 42, 43, 44, 45, 50, 51, 52, 53, 54, 55, 56, 57, 62, 63, 64, 65, 66, 67, 68, 69, 74, 75, 76, 77, 78, 79, 80, 81, 86, 87, 88, 89, 90, 91, 92, 93, 98, 99, 100, 101, 102, 103, 104, 105, 110, 111, 112, 113, 114, 115, 116, 117};
    public static final int OFF_BOARD = -10;
    public static final int PIECE_BLOCKADE = -50;
    public static final int LIMIT_EXCEED = -51;
    public static final int INVALID_DIRECTION = -52;
    public static final int SUCCESSFUL = 10;
    public int scanSquare;
    public int scanPiece;
    public static final int INVALID_MOVE = -1000;
    public static final int INVALID_BLOCK = -1001;
    public static final int VALID_MOVE = 1000;
    private int[] board;
    private HashMap<Integer, String> messageMap = new HashMap(10);
    private static final int EMPTY_DESTINATION = 100;

    public int scanMove(int source, int destination, int direction, int limit) {
        return this.scanPath(source, destination, direction, limit);
    }

    public int scanMove(int source, int destination, int direction) {
        return this.scanPath(source, destination, direction, Integer.MAX_VALUE);
    }

    public int scanAttack(int source, int direction, int limit) {
        int result = this.scanPath(source, Integer.MAX_VALUE, direction, limit);
        if (Utility.equalOr((Number)result, (Number)-50, (Number[])new Integer[]{-10, -51})) {
            return 10;
        }
        return result;
    }

    public int scanAttack(int source, int direction) {
        return this.scanAttack(source, direction, Integer.MAX_VALUE);
    }

    public int scanPath(int source, int destination, int direction, int limit) {
        this.scanSquare = source;
        this.scanPiece = this.board[source];
        if (direction == 0) {
            return -52;
        }
        source = toTestBoard[source];
        destination = destination >= 64 ? Integer.MAX_VALUE : toTestBoard[destination];
        int i = source + direction;
        int l = 0;
        do {
            this.scanSquare = toGeneralBoard[i];
            int n = this.scanPiece = toGeneralBoard[i] == -10 ? -1 : this.board[toGeneralBoard[i]];
            if (toGeneralBoard[i] == -10) {
                return -10;
            }
            if (i == destination) break;
            if (this.board[toGeneralBoard[i]] != -1) {
                return -50;
            }
            if (l >= limit - 1) {
                return -51;
            }
            i += direction;
            ++l;
        } while (true);
        return 10;
    }

    public Verification(int[] board) {
        this.board = board;
        this.messageMap.put(-1000, "Move is not Valid");
    }

    public int initialVerify(Move move, int type) {
        int source = move.getSource();
        int destination = move.getDestination();
        int piece = move.getPiece();
        int fileDisplacement = BoardUI.getFileDistance(destination, source);
        int rankDisplacement = BoardUI.getRankDistance(destination, source);
        if (PiecesUI.isPiece(PiecesUI.PAWN, piece)) {
            if (Math.abs(rankDisplacement) == 2) {
                if (PiecesUI.getColor(piece) == 0 ? BoardUI.getRank(source) != 1 : BoardUI.getRank(source) != 6) {
                    return -1000;
                }
                int direction = this.compare(fileDisplacement, rankDisplacement, 0, 0, 12, 0, 0, 0, -12, 0, 0);
                return this.combineResult(this.scanMove(source, destination, direction, 2), 100);
            }
            if (PiecesUI.getColor(piece) == 0 ? rankDisplacement != 1 : rankDisplacement != -1) {
                return -1000;
            }
            int direction = this.compare(fileDisplacement, rankDisplacement, 0, 13, 12, 11, 0, -13, -12, -11, 0);
            int result = this.scanMove(source, destination, direction, 1);
            if (fileDisplacement == 0 && this.scanPiece != -1) {
                return -1000;
            }
            if (Math.abs(fileDisplacement) == 1 && this.scanPiece == -1) {
                return -1000;
            }
            return this.combineResult(result, piece);
        }
        if (PiecesUI.isPiece(PiecesUI.KNIGHT, piece)) {
            int direction = Math.abs(fileDisplacement) > Math.abs(rankDisplacement) ? this.compare(fileDisplacement, rankDisplacement, 0, 14, 0, 10, 0, -14, 0, -10, 0) : this.compare(fileDisplacement, rankDisplacement, 0, 25, 0, 23, 0, -25, 0, -23, 0);
            return this.combineResult(this.scanMove(source, destination, direction, 1), piece);
        }
        if (PiecesUI.isPiece(PiecesUI.BISHOP, piece)) {
            int direction = this.compare(fileDisplacement, rankDisplacement, 0, 13, 0, 11, 0, -13, 0, -11, 0);
            return this.combineResult(this.scanMove(source, destination, direction), piece);
        }
        if (PiecesUI.isPiece(PiecesUI.ROOK, piece)) {
            int direction = this.compare(fileDisplacement, rankDisplacement, 1, 0, 12, 0, -1, 0, -12, 0, 0);
            return this.combineResult(this.scanMove(source, destination, direction), piece);
        }
        if (PiecesUI.isPiece(PiecesUI.QUEEN, piece)) {
            int direction = this.compare(fileDisplacement, rankDisplacement, 1, 13, 12, 11, -1, -13, -12, -11, 0);
            return this.combineResult(this.scanMove(source, destination, direction), piece);
        }
        if (PiecesUI.isPiece(PiecesUI.KING, piece)) {
            if (type == Move.CASTLE_MOVE) {
                int direction = this.compare(fileDisplacement, rankDisplacement, 1, 0, 0, 0, -1, 0, 0, 0, 0);
                int result = fileDisplacement < 0 ? this.scanMove(source, destination - 1, direction, 3) : this.scanMove(source, destination, direction, 2);
                return this.combineResult(result, 100);
            }
            int direction = this.compare(fileDisplacement, rankDisplacement, 1, 13, 12, 11, -1, -13, -12, -11, 0);
            return this.combineResult(this.scanMove(source, destination, direction, 1), piece);
        }
        return 1000;
    }

    private int combineResult(int scanResult, int flag) {
        if (scanResult < 0) {
            return -1000;
        }
        if (this.scanPiece != -1) {
            if (flag == 100) {
                return -1000;
            }
            if (PiecesUI.sameColor(flag, this.scanPiece)) {
                return -1000;
            }
        }
        return 1000;
    }

    private int compare(int fileDisplacement, int rankDisplacement, int fprz, int fprp, int fzrp, int fnrp, int fnrz, int fnrn, int fzrn, int fprn, int fzrz) {
        return Utility.compare(fileDisplacement, Utility.compare(rankDisplacement, fprp, fprz, fprn), Utility.compare(rankDisplacement, fzrp, fzrz, fzrn), Utility.compare(rankDisplacement, fnrp, fnrz, fnrn));
    }

    public int finalVerify(Move move) {
        int color = PiecesUI.getColor(move.getPiece());
        if (this.findAttacker(PiecesUI.indexOf(this.board, PiecesUI.KING[color]), PiecesUI.switchColor(color)) != -1) {
            return -1000;
        }
        return 1000;
    }

    private int findAttacker(int square, int color) {
        int[] directionList;
        for (int direction : directionList = new int[]{-13, -11, 11, 13}) {
            if (this.scanAttack(square, direction) < 0) {
                return -1;
            }
            if (this.scanPiece != PiecesUI.BISHOP[color] && this.scanPiece != PiecesUI.QUEEN[color]) continue;
            return this.scanPiece;
        }
        for (int direction : directionList) {
            if (this.scanAttack(square, direction, 1) < 0) {
                return -1;
            }
            if (this.scanPiece == PiecesUI.KING[color]) {
                return this.scanPiece;
            }
            if (this.scanPiece != PiecesUI.PAWN[color] || !(color == 0 ? direction == -11 || direction == -13 : direction == 11 || direction == 13)) continue;
            return this.scanPiece;
        }
        for (int direction : directionList = new int[]{-12, -1, 1, 12}) {
            if (this.scanAttack(square, direction) < 0) {
                return -1;
            }
            if (this.scanPiece != PiecesUI.ROOK[color] && this.scanPiece != PiecesUI.QUEEN[color]) continue;
            return this.scanPiece;
        }
        for (int direction : directionList) {
            if (this.scanAttack(square, direction, 1) < 0) {
                return -1;
            }
            if (this.scanPiece != PiecesUI.KING[color]) continue;
            return this.scanPiece;
        }
        for (int direction : directionList = new int[]{-25, -23, -14, -10, 10, 14, 23, 25}) {
            if (this.scanAttack(square, direction, 1) < 0) {
                return -1;
            }
            if (this.scanPiece != PiecesUI.KNIGHT[color]) continue;
            return this.scanPiece;
        }
        return -1;
    }

    public String getMessage(int result) {
        return this.messageMap.get(result);
    }
}

