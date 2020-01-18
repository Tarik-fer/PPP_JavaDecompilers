// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.commons.io.filefilter;

import java.io.File;
import java.util.Date;
import java.io.FilenameFilter;
import java.io.FileFilter;

public class FileFilterUtils
{
    private static IOFileFilter cvsFilter;
    private static IOFileFilter svnFilter;
    
    public static IOFileFilter prefixFileFilter(final String prefix) {
        return new PrefixFileFilter(prefix);
    }
    
    public static IOFileFilter suffixFileFilter(final String suffix) {
        return new SuffixFileFilter(suffix);
    }
    
    public static IOFileFilter nameFileFilter(final String name) {
        return new NameFileFilter(name);
    }
    
    public static IOFileFilter directoryFileFilter() {
        return DirectoryFileFilter.DIRECTORY;
    }
    
    public static IOFileFilter fileFileFilter() {
        return FileFileFilter.FILE;
    }
    
    public static IOFileFilter andFileFilter(final IOFileFilter filter1, final IOFileFilter filter2) {
        return new AndFileFilter(filter1, filter2);
    }
    
    public static IOFileFilter orFileFilter(final IOFileFilter filter1, final IOFileFilter filter2) {
        return new OrFileFilter(filter1, filter2);
    }
    
    public static IOFileFilter notFileFilter(final IOFileFilter filter) {
        return new NotFileFilter(filter);
    }
    
    public static IOFileFilter trueFileFilter() {
        return TrueFileFilter.TRUE;
    }
    
    public static IOFileFilter falseFileFilter() {
        return FalseFileFilter.FALSE;
    }
    
    public static IOFileFilter asFileFilter(final FileFilter filter) {
        return new DelegateFileFilter(filter);
    }
    
    public static IOFileFilter asFileFilter(final FilenameFilter filter) {
        return new DelegateFileFilter(filter);
    }
    
    public static IOFileFilter ageFileFilter(final long cutoff) {
        return new AgeFileFilter(cutoff);
    }
    
    public static IOFileFilter ageFileFilter(final long cutoff, final boolean acceptOlder) {
        return new AgeFileFilter(cutoff, acceptOlder);
    }
    
    public static IOFileFilter ageFileFilter(final Date cutoffDate) {
        return new AgeFileFilter(cutoffDate);
    }
    
    public static IOFileFilter ageFileFilter(final Date cutoffDate, final boolean acceptOlder) {
        return new AgeFileFilter(cutoffDate, acceptOlder);
    }
    
    public static IOFileFilter ageFileFilter(final File cutoffReference) {
        return new AgeFileFilter(cutoffReference);
    }
    
    public static IOFileFilter ageFileFilter(final File cutoffReference, final boolean acceptOlder) {
        return new AgeFileFilter(cutoffReference, acceptOlder);
    }
    
    public static IOFileFilter sizeFileFilter(final long threshold) {
        return new SizeFileFilter(threshold);
    }
    
    public static IOFileFilter sizeFileFilter(final long threshold, final boolean acceptLarger) {
        return new SizeFileFilter(threshold, acceptLarger);
    }
    
    public static IOFileFilter sizeRangeFileFilter(final long minSizeInclusive, final long maxSizeInclusive) {
        final IOFileFilter minimumFilter = new SizeFileFilter(minSizeInclusive, true);
        final IOFileFilter maximumFilter = new SizeFileFilter(maxSizeInclusive + 1L, false);
        return new AndFileFilter(minimumFilter, maximumFilter);
    }
    
    public static IOFileFilter makeCVSAware(final IOFileFilter filter) {
        if (FileFilterUtils.cvsFilter == null) {
            FileFilterUtils.cvsFilter = notFileFilter(andFileFilter(directoryFileFilter(), nameFileFilter("CVS")));
        }
        if (filter == null) {
            return FileFilterUtils.cvsFilter;
        }
        return andFileFilter(filter, FileFilterUtils.cvsFilter);
    }
    
    public static IOFileFilter makeSVNAware(final IOFileFilter filter) {
        if (FileFilterUtils.svnFilter == null) {
            FileFilterUtils.svnFilter = notFileFilter(andFileFilter(directoryFileFilter(), nameFileFilter(".svn")));
        }
        if (filter == null) {
            return FileFilterUtils.svnFilter;
        }
        return andFileFilter(filter, FileFilterUtils.svnFilter);
    }
    
    public static IOFileFilter makeDirectoryOnly(final IOFileFilter filter) {
        if (filter == null) {
            return DirectoryFileFilter.DIRECTORY;
        }
        return new AndFileFilter(DirectoryFileFilter.DIRECTORY, filter);
    }
    
    public static IOFileFilter makeFileOnly(final IOFileFilter filter) {
        if (filter == null) {
            return FileFileFilter.FILE;
        }
        return new AndFileFilter(FileFileFilter.FILE, filter);
    }
}
