// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.core.moves;

import pl.art.lach.mateusz.javaopenchess.core.Colors;

public enum Castling
{
    NONE("", new int[4], new int[4]), 
    SHORT_CASTLING("0-0", new int[] { 4, 7, 6, 7 }, new int[] { 4, 0, 6, 0 }), 
    LONG_CASTLING("0-0-0", new int[] { 4, 7, 2, 7 }, new int[] { 4, 0, 2, 0 });
    
    protected String symbol;
    protected int[] whiteMove;
    protected int[] blackMove;
    
    private Castling(final String symbol, final int[] whiteMove, final int[] blackMove) {
        this.symbol = symbol;
        this.whiteMove = whiteMove;
        this.blackMove = blackMove;
    }
    
    public String getSymbol() {
        return this.symbol;
    }
    
    public int[] getMove(final Colors color) {
        if (Colors.BLACK == color) {
            return this.blackMove;
        }
        return this.whiteMove;
    }
    
    public static boolean isCastling(final String moveInPGN) {
        return moveInPGN.equals(Castling.SHORT_CASTLING.getSymbol()) || moveInPGN.equals(Castling.LONG_CASTLING.getSymbol());
    }
}
