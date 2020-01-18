// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.core.data_transfer;

import pl.art.lach.mateusz.javaopenchess.core.exceptions.ReadGameError;
import pl.art.lach.mateusz.javaopenchess.core.Game;

public interface DataImporter
{
    Game importData(final String p0) throws ReadGameError;
    
    void importData(final String p0, final Game p1) throws ReadGameError;
}
