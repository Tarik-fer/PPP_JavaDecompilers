// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.commons.io.output;

import java.io.IOException;
import java.io.OutputStream;
import java.io.FilterOutputStream;

public class ProxyOutputStream extends FilterOutputStream
{
    public ProxyOutputStream(final OutputStream proxy) {
        super(proxy);
    }
    
    public void write(final int idx) throws IOException {
        this.out.write(idx);
    }
    
    public void write(final byte[] bts) throws IOException {
        this.out.write(bts);
    }
    
    public void write(final byte[] bts, final int st, final int end) throws IOException {
        this.out.write(bts, st, end);
    }
    
    public void flush() throws IOException {
        this.out.flush();
    }
    
    public void close() throws IOException {
        this.out.close();
    }
}
