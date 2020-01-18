// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.commons.io.filefilter;

import java.util.List;

public interface ConditionalFileFilter
{
    void addFileFilter(final IOFileFilter p0);
    
    List getFileFilters();
    
    boolean removeFileFilter(final IOFileFilter p0);
    
    void setFileFilters(final List p0);
}
