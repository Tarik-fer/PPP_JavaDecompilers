// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.core.exceptions;

public class ReadGameError extends Exception
{
    private String message;
    private String move;
    
    public ReadGameError(final String message) {
        this.message = message;
    }
    
    public ReadGameError(final String message, final String move) {
        this(message);
        this.move = move;
    }
    
    @Override
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(final String message) {
        this.message = message;
    }
    
    public String getMove() {
        return this.move;
    }
    
    public void setMove(final String move) {
        this.move = move;
    }
}
