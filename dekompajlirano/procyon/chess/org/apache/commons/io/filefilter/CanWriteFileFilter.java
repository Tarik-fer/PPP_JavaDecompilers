// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.commons.io.filefilter;

import java.io.File;

public class CanWriteFileFilter extends AbstractFileFilter
{
    public static final IOFileFilter CAN_WRITE;
    public static final IOFileFilter CANNOT_WRITE;
    
    protected CanWriteFileFilter() {
    }
    
    public boolean accept(final File file) {
        return file.canWrite();
    }
    
    static {
        CAN_WRITE = new CanWriteFileFilter();
        CANNOT_WRITE = new NotFileFilter(CanWriteFileFilter.CAN_WRITE);
    }
}
