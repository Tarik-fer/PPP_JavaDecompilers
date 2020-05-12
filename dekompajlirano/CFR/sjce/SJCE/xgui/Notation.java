/*
 * Decompiled with CFR 0.148.
 */
package SJCE.xgui;

import SJCE.more.Actions;
import SJCE.xgui.Move;

public class Notation {
    public static final char[] FILE_CHAR = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
    public static final char[] RANK_CHAR = new char[]{'1', '2', '3', '4', '5', '6', '7', '8'};

    public int getFile(char f) {
        return f - 97;
    }

    public int getRank(char r) {
        return r - 49;
    }

    public char getFile(int f) {
        return (char)(97 + f);
    }

    public char getRank(int r) {
        return (char)(49 + r);
    }

    public static String toString(int square) {
        return new String(new char[]{FILE_CHAR[square % 8], RANK_CHAR[square / 8]});
    }

    public static int toSquare(String square) {
        return (square.charAt(1) - 49) * 8 + square.charAt(0) - 97;
    }

    public static String toString(Move move) {
        String bufer = "";
        bufer = Notation.toString(move.getSource()) + Notation.toString(move.getDestination()) + Actions.enginePromotionFig;
        return bufer;
    }

    public static Move toMove(String notation) {
        return new Move(Notation.toSquare(notation.substring(0, 2)), Notation.toSquare(notation.substring(2, 4)));
    }
}

