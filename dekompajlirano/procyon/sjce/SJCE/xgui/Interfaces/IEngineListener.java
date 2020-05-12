// 
// Decompiled by Procyon v0.5.36
// 

package SJCE.xgui.Interfaces;

import SJCE.xgui.EventObject.EngineEvent;
import java.util.EventListener;

public interface IEngineListener extends EventListener
{
    void movePrinted(final EngineEvent p0);
    
    void illegalPrinted(final EngineEvent p0);
    
    void dataPrinted(final EngineEvent p0);
    
    void dataEntered(final EngineEvent p0);
}
