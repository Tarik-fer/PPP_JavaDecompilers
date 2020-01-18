// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.commons.io.filefilter;

import java.io.File;

public class NotFileFilter extends AbstractFileFilter
{
    private IOFileFilter filter;
    
    public NotFileFilter(final IOFileFilter filter) {
        if (filter == null) {
            throw new IllegalArgumentException("The filter must not be null");
        }
        this.filter = filter;
    }
    
    public boolean accept(final File file) {
        return !this.filter.accept(file);
    }
    
    public boolean accept(final File file, final String name) {
        return !this.filter.accept(file, name);
    }
}
