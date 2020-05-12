// 
// Decompiled by Procyon v0.5.36
// 

package SJCE.xgui;

public class Utility
{
    public static final int[] INITIAL_BOARD;
    
    public static int compare(final int value, final int p, final int z, final int n) {
        if (value > 0) {
            return p;
        }
        if (value < 0) {
            return n;
        }
        return z;
    }
    
    public static <T extends Number> boolean equalOr(final T value, final T alt, final T... alts) {
        boolean result = value == alt;
        for (final T a : alts) {
            result |= (value == a);
        }
        return result;
    }
    
    public static <T extends Number> boolean equalAnd(final T value, final T alt, final T... alts) {
        boolean result = value == alt;
        for (final T a : alts) {
            result &= (value == a);
        }
        return result;
    }
    
    static {
        INITIAL_BOARD = new int[] { 3, 1, 2, 4, 5, 2, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 6, 6, 6, 6, 6, 6, 6, 6, 9, 7, 8, 10, 11, 8, 7, 9 };
    }
}
