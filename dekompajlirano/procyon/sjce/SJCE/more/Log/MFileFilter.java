// 
// Decompiled by Procyon v0.5.36
// 

package SJCE.more.Log;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class MFileFilter extends FileFilter
{
    String ext;
    
    public MFileFilter(final String txt) {
        this.ext = txt;
    }
    
    @Override
    public boolean accept(final File f) {
        return f != null && (f.isDirectory() || f.getName().endsWith(this.ext) || f.getName().endsWith(this.ext.toUpperCase()));
    }
    
    @Override
    public String getDescription() {
        return "Files:  *" + this.ext;
    }
}
