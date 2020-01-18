// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.core.data_transfer;

import pl.art.lach.mateusz.javaopenchess.core.data_transfer.implementations.PGNNotation;
import pl.art.lach.mateusz.javaopenchess.core.data_transfer.implementations.FenNotation;

public class DataTransferFactory
{
    public static DataExporter getExporterInstance(final TransferFormat format) {
        switch (format) {
            case FEN: {
                return new FenNotation();
            }
            case PGN: {
                return new PGNNotation();
            }
            default: {
                return new FenNotation();
            }
        }
    }
    
    public static DataImporter getImporterInstance(final TransferFormat format) {
        switch (format) {
            case FEN: {
                return new FenNotation();
            }
            case PGN: {
                return new PGNNotation();
            }
            default: {
                return new FenNotation();
            }
        }
    }
}
