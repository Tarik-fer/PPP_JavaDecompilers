// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.commons.io.output;

import java.io.IOException;
import java.io.Writer;
import java.io.FilterWriter;

public class ProxyWriter extends FilterWriter
{
    public ProxyWriter(final Writer proxy) {
        super(proxy);
    }
    
    public void write(final int idx) throws IOException {
        this.out.write(idx);
    }
    
    public void write(final char[] chr) throws IOException {
        this.out.write(chr);
    }
    
    public void write(final char[] chr, final int st, final int end) throws IOException {
        this.out.write(chr, st, end);
    }
    
    public void write(final String str) throws IOException {
        this.out.write(str);
    }
    
    public void write(final String str, final int st, final int end) throws IOException {
        this.out.write(str, st, end);
    }
    
    public void flush() throws IOException {
        this.out.flush();
    }
    
    public void close() throws IOException {
        this.out.close();
    }
}
