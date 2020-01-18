// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.commons.io.filefilter;

import java.io.File;

public class FileFileFilter extends AbstractFileFilter
{
    public static final IOFileFilter FILE;
    
    protected FileFileFilter() {
    }
    
    public boolean accept(final File file) {
        return file.isFile();
    }
    
    static {
        FILE = new FileFileFilter();
    }
}
