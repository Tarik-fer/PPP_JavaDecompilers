// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.commons.io.filefilter;

import java.io.File;
import java.util.List;

public class SuffixFileFilter extends AbstractFileFilter
{
    private String[] suffixes;
    
    public SuffixFileFilter(final String suffix) {
        if (suffix == null) {
            throw new IllegalArgumentException("The suffix must not be null");
        }
        this.suffixes = new String[] { suffix };
    }
    
    public SuffixFileFilter(final String[] suffixes) {
        if (suffixes == null) {
            throw new IllegalArgumentException("The array of suffixes must not be null");
        }
        this.suffixes = suffixes;
    }
    
    public SuffixFileFilter(final List suffixes) {
        if (suffixes == null) {
            throw new IllegalArgumentException("The list of suffixes must not be null");
        }
        this.suffixes = suffixes.toArray(new String[suffixes.size()]);
    }
    
    public boolean accept(final File file) {
        final String name = file.getName();
        for (int i = 0; i < this.suffixes.length; ++i) {
            if (name.endsWith(this.suffixes[i])) {
                return true;
            }
        }
        return false;
    }
    
    public boolean accept(final File file, final String name) {
        for (int i = 0; i < this.suffixes.length; ++i) {
            if (name.endsWith(this.suffixes[i])) {
                return true;
            }
        }
        return false;
    }
}
