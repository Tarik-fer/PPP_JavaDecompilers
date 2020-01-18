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

public class OrFileFilter extends AbstractFileFilter implements ConditionalFileFilter
{
    private List fileFilters;
    
    public OrFileFilter() {
        this.fileFilters = new ArrayList();
    }
    
    public OrFileFilter(final List fileFilters) {
        if (fileFilters == null) {
            this.fileFilters = new ArrayList();
        }
        else {
            this.fileFilters = new ArrayList(fileFilters);
        }
    }
    
    public OrFileFilter(final IOFileFilter filter1, final IOFileFilter filter2) {
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
        this.fileFilters = fileFilters;
    }
    
    public boolean accept(final File file) {
        for (final IOFileFilter fileFilter : this.fileFilters) {
            if (fileFilter.accept(file)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean accept(final File file, final String name) {
        for (final IOFileFilter fileFilter : this.fileFilters) {
            if (fileFilter.accept(file, name)) {
                return true;
            }
        }
        return false;
    }
}
