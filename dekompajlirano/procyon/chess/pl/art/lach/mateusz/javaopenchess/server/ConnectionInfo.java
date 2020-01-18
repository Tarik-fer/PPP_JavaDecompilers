// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.server;

public enum ConnectionInfo
{
    EVERYTHING_IS_OK(0), 
    ERR_WRONG_TABLE_ID(1), 
    ERR_TABLE_IS_FULL(2), 
    ERR_GAME_WITHOUT_OBSERVERS(3), 
    ERR_INVALID_PASSWORD(4);
    
    private int value;
    
    private ConnectionInfo(final int value) {
        this.value = value;
    }
    
    public static ConnectionInfo get(final int id) {
        switch (id) {
            case 0: {
                return ConnectionInfo.EVERYTHING_IS_OK;
            }
            case 1: {
                return ConnectionInfo.ERR_WRONG_TABLE_ID;
            }
            case 2: {
                return ConnectionInfo.ERR_TABLE_IS_FULL;
            }
            case 3: {
                return ConnectionInfo.ERR_GAME_WITHOUT_OBSERVERS;
            }
            case 4: {
                return ConnectionInfo.ERR_INVALID_PASSWORD;
            }
            default: {
                return null;
            }
        }
    }
    
    public int getValue() {
        return this.value;
    }
}
