/*
 * Decompiled with CFR 0.148.
 */
package SJCE.xgui.EventObject;

import SJCE.xgui.Agent.EngineAgent;
import java.util.EventObject;

public class EngineEvent
extends EventObject {
    private String data;

    public EngineEvent(Object source) {
        super(source);
    }

    public EngineEvent(Object source, String data) {
        super(source);
        this.data = data;
    }

    public EngineAgent getEngine() {
        return (EngineAgent)this.getSource();
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

