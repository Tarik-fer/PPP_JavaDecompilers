/*
 * Decompiled with CFR 0.148.
 */
package SJCE.xgui.Interfaces;

import SJCE.xgui.EventObject.EngineEvent;
import java.util.EventListener;

public interface IEngineListener
extends EventListener {
    public void movePrinted(EngineEvent var1);

    public void illegalPrinted(EngineEvent var1);

    public void dataPrinted(EngineEvent var1);

    public void dataEntered(EngineEvent var1);
}

