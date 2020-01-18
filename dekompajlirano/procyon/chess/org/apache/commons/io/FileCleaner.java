// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.commons.io;

import java.io.File;

public class FileCleaner
{
    static final FileCleaningTracker theInstance;
    
    public static void track(final File file, final Object marker) {
        FileCleaner.theInstance.track(file, marker);
    }
    
    public static void track(final File file, final Object marker, final FileDeleteStrategy deleteStrategy) {
        FileCleaner.theInstance.track(file, marker, deleteStrategy);
    }
    
    public static void track(final String path, final Object marker) {
        FileCleaner.theInstance.track(path, marker);
    }
    
    public static void track(final String path, final Object marker, final FileDeleteStrategy deleteStrategy) {
        FileCleaner.theInstance.track(path, marker, deleteStrategy);
    }
    
    public static int getTrackCount() {
        return FileCleaner.theInstance.getTrackCount();
    }
    
    public static synchronized void exitWhenFinished() {
        FileCleaner.theInstance.exitWhenFinished();
    }
    
    public static FileCleaningTracker getInstance() {
        return FileCleaner.theInstance;
    }
    
    static {
        theInstance = new FileCleaningTracker();
    }
}
