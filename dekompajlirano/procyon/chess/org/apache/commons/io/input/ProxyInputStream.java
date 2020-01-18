// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import java.io.FilterInputStream;

public abstract class ProxyInputStream extends FilterInputStream
{
    public ProxyInputStream(final InputStream proxy) {
        super(proxy);
    }
    
    public int read() throws IOException {
        return this.in.read();
    }
    
    public int read(final byte[] bts) throws IOException {
        return this.in.read(bts);
    }
    
    public int read(final byte[] bts, final int st, final int end) throws IOException {
        return this.in.read(bts, st, end);
    }
    
    public long skip(final long ln) throws IOException {
        return this.in.skip(ln);
    }
    
    public int available() throws IOException {
        return this.in.available();
    }
    
    public void close() throws IOException {
        this.in.close();
    }
    
    public synchronized void mark(final int idx) {
        this.in.mark(idx);
    }
    
    public synchronized void reset() throws IOException {
        this.in.reset();
    }
    
    public boolean markSupported() {
        return this.in.markSupported();
    }
}
