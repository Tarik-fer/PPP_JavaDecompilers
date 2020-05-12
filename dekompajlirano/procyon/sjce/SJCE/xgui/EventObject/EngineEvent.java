// 
// Decompiled by Procyon v0.5.36
// 

package SJCE.xgui.EventObject;

import SJCE.xgui.Agent.EngineAgent;
import java.util.EventObject;

public class EngineEvent extends EventObject
{
    private String data;
    
    public EngineEvent(final Object source) {
        super(source);
    }
    
    public EngineEvent(final Object source, final String data) {
        super(source);
        this.data = data;
    }
    
    public EngineAgent getEngine() {
        return (EngineAgent)this.getSource();
    }
    
    public String getData() {
        return this.data;
    }
    
    public void setData(final String data) {
        this.data = data;
    }
}
