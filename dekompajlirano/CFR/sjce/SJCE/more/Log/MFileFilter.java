/*
 * Decompiled with CFR 0.148.
 */
package SJCE.more.Log;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class MFileFilter
extends FileFilter {
    String ext;

    public MFileFilter(String txt) {
        this.ext = txt;
    }

    @Override
    public boolean accept(File f) {
        if (f == null) {
            return false;
        }
        if (f.isDirectory()) {
            return true;
        }
        return f.getName().endsWith(this.ext) || f.getName().endsWith(this.ext.toUpperCase());
    }

    @Override
    public String getDescription() {
        return "Files:  *" + this.ext;
    }
}

