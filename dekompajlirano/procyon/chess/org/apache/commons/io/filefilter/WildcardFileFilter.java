// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.commons.io.filefilter;

import org.apache.commons.io.FilenameUtils;
import java.io.File;
import java.util.List;
import org.apache.commons.io.IOCase;

public class WildcardFileFilter extends AbstractFileFilter
{
    private String[] wildcards;
    private IOCase caseSensitivity;
    
    public WildcardFileFilter(final String wildcard) {
        this(wildcard, null);
    }
    
    public WildcardFileFilter(final String wildcard, final IOCase caseSensitivity) {
        if (wildcard == null) {
            throw new IllegalArgumentException("The wildcard must not be null");
        }
        this.wildcards = new String[] { wildcard };
        this.caseSensitivity = ((caseSensitivity == null) ? IOCase.SENSITIVE : caseSensitivity);
    }
    
    public WildcardFileFilter(final String[] wildcards) {
        this(wildcards, null);
    }
    
    public WildcardFileFilter(final String[] wildcards, final IOCase caseSensitivity) {
        if (wildcards == null) {
            throw new IllegalArgumentException("The wildcard array must not be null");
        }
        this.wildcards = wildcards;
        this.caseSensitivity = ((caseSensitivity == null) ? IOCase.SENSITIVE : caseSensitivity);
    }
    
    public WildcardFileFilter(final List wildcards) {
        this(wildcards, null);
    }
    
    public WildcardFileFilter(final List wildcards, final IOCase caseSensitivity) {
        if (wildcards == null) {
            throw new IllegalArgumentException("The wildcard list must not be null");
        }
        this.wildcards = wildcards.toArray(new String[wildcards.size()]);
        this.caseSensitivity = ((caseSensitivity == null) ? IOCase.SENSITIVE : caseSensitivity);
    }
    
    public boolean accept(final File dir, final String name) {
        for (int i = 0; i < this.wildcards.length; ++i) {
            if (FilenameUtils.wildcardMatch(name, this.wildcards[i], this.caseSensitivity)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean accept(final File file) {
        final String name = file.getName();
        for (int i = 0; i < this.wildcards.length; ++i) {
            if (FilenameUtils.wildcardMatch(name, this.wildcards[i], this.caseSensitivity)) {
                return true;
            }
        }
        return false;
    }
}
