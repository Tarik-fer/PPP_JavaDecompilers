// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.commons.io.filefilter;

import java.util.Iterator;
import java.io.File;
import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

public class AndFileFilter extends AbstractFileFilter implements ConditionalFileFilter
{
    private List fileFilters;
    
    public AndFileFilter() {
        this.fileFilters = new ArrayList();
    }
    
    public AndFileFilter(final List fileFilters) {
        if (fileFilters == null) {
            this.fileFilters = new ArrayList();
        }
        else {
            this.fileFilters = new ArrayList(fileFilters);
        }
    }
    
    public AndFileFilter(final IOFileFilter filter1, final IOFileFilter filter2) {
        if (filter1 == null || filter2 == null) {
            throw new IllegalArgumentException("The filters must not be null");
        }
        this.fileFilters = new ArrayList();
        this.addFileFilter(filter1);
        this.addFileFilter(filter2);
    }
    
    public void addFileFilter(final IOFileFilter ioFileFilter) {
        this.fileFilters.add(ioFileFilter);
    }
    
    public List getFileFilters() {
        return Collections.unmodifiableList((List<?>)this.fileFilters);
    }
    
    public boolean removeFileFilter(final IOFileFilter ioFileFilter) {
        return this.fileFilters.remove(ioFileFilter);
    }
    
    public void setFileFilters(final List fileFilters) {
        this.fileFilters = new ArrayList(fileFilters);
    }
    
    public boolean accept(final File file) {
        if (this.fileFilters.size() == 0) {
            return false;
        }
        for (final IOFileFilter fileFilter : this.fileFilters) {
            if (!fileFilter.accept(file)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean accept(final File file, final String name) {
        if (this.fileFilters.size() == 0) {
            return false;
        }
        for (final IOFileFilter fileFilter : this.fileFilters) {
            if (!fileFilter.accept(file, name)) {
                return false;
            }
        }
        return true;
    }
}
