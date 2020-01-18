// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.commons.io.filefilter;

import java.io.File;
import java.util.List;
import org.apache.commons.io.IOCase;

public class NameFileFilter extends AbstractFileFilter
{
    private String[] names;
    private IOCase caseSensitivity;
    
    public NameFileFilter(final String name) {
        this(name, null);
    }
    
    public NameFileFilter(final String name, final IOCase caseSensitivity) {
        if (name == null) {
            throw new IllegalArgumentException("The wildcard must not be null");
        }
        this.names = new String[] { name };
        this.caseSensitivity = ((caseSensitivity == null) ? IOCase.SENSITIVE : caseSensitivity);
    }
    
    public NameFileFilter(final String[] names) {
        this(names, null);
    }
    
    public NameFileFilter(final String[] names, final IOCase caseSensitivity) {
        if (names == null) {
            throw new IllegalArgumentException("The array of names must not be null");
        }
        this.names = names;
        this.caseSensitivity = ((caseSensitivity == null) ? IOCase.SENSITIVE : caseSensitivity);
    }
    
    public NameFileFilter(final List names) {
        this(names, null);
    }
    
    public NameFileFilter(final List names, final IOCase caseSensitivity) {
        if (names == null) {
            throw new IllegalArgumentException("The list of names must not be null");
        }
        this.names = names.toArray(new String[names.size()]);
        this.caseSensitivity = ((caseSensitivity == null) ? IOCase.SENSITIVE : caseSensitivity);
    }
    
    public boolean accept(final File file) {
        final String name = file.getName();
        for (int i = 0; i < this.names.length; ++i) {
            if (this.caseSensitivity.checkEquals(name, this.names[i])) {
                return true;
            }
        }
        return false;
    }
    
    public boolean accept(final File file, final String name) {
        for (int i = 0; i < this.names.length; ++i) {
            if (this.caseSensitivity.checkEquals(name, this.names[i])) {
                return true;
            }
        }
        return false;
    }
}
