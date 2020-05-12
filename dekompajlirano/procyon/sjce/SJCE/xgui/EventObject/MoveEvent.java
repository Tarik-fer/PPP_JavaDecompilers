// 
// Decompiled by Procyon v0.5.36
// 

package SJCE.xgui.EventObject;

import SJCE.xgui.Move;
import java.util.EventObject;

public class MoveEvent extends EventObject
{
    private Move move;
    
    public MoveEvent(final Object source, final Move move) {
        super(source);
        this.move = move;
    }
    
    public Move getMove() {
        return this.move;
    }
    
    public void setMove(final Move move) {
        this.move = move;
    }
}
