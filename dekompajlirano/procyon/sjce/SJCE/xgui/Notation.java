// 
// Decompiled by Procyon v0.5.36
// 

package SJCE.xgui;

import SJCE.more.Actions;
import SJCE.XChessFrame;

public class Notation
{
    public static final char[] FILE_CHAR;
    public static final char[] RANK_CHAR;
    
    public int getFile(final char f) {
        return f - 'a';
    }
    
    public int getRank(final char r) {
        return r - '1';
    }
    
    public char getFile(final int f) {
        return (char)(97 + f);
    }
    
    public char getRank(final int r) {
        return (char)(49 + r);
    }
    
    public static String toString(final int square) {
        return new String(new char[] { Notation.FILE_CHAR[square % 8], Notation.RANK_CHAR[square / 8] });
    }
    
    public static int toSquare(final String square) {
        return (square.charAt(1) - '1') * 8 + square.charAt(0) - 97;
    }
    
    public static String toString(final Move move) {
        String bufer = "";
        final StringBuilder append = new StringBuilder().append(toString(move.getSource())).append(toString(move.getDestination()));
        final Actions aktion = XChessFrame.aktion;
        bufer = append.append(Actions.enginePromotionFig).toString();
        return bufer;
    }
    
    public static Move toMove(final String notation) {
        return new Move(toSquare(notation.substring(0, 2)), toSquare(notation.substring(2, 4)));
    }
    
    static {
        FILE_CHAR = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' };
        RANK_CHAR = new char[] { '1', '2', '3', '4', '5', '6', '7', '8' };
    }
}
