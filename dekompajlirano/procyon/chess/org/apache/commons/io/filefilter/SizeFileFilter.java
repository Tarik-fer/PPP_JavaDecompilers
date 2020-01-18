// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.commons.io.filefilter;

import java.io.File;

public class SizeFileFilter extends AbstractFileFilter
{
    private long size;
    private boolean acceptLarger;
    
    public SizeFileFilter(final long size) {
        this(size, true);
    }
    
    public SizeFileFilter(final long size, final boolean acceptLarger) {
        if (size < 0L) {
            throw new IllegalArgumentException("The size must be non-negative");
        }
        this.size = size;
        this.acceptLarger = acceptLarger;
    }
    
    public boolean accept(final File file) {
        final boolean smaller = file.length() < this.size;
        return this.acceptLarger ? (!smaller) : smaller;
    }
}
