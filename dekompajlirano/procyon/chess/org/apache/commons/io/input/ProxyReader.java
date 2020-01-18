// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.commons.io.input;

import java.io.IOException;
import java.io.Reader;
import java.io.FilterReader;

public abstract class ProxyReader extends FilterReader
{
    public ProxyReader(final Reader proxy) {
        super(proxy);
    }
    
    public int read() throws IOException {
        return this.in.read();
    }
    
    public int read(final char[] chr) throws IOException {
        return this.in.read(chr);
    }
    
    public int read(final char[] chr, final int st, final int end) throws IOException {
        return this.in.read(chr, st, end);
    }
    
    public long skip(final long ln) throws IOException {
        return this.in.skip(ln);
    }
    
    public boolean ready() throws IOException {
        return this.in.ready();
    }
    
    public void close() throws IOException {
        this.in.close();
    }
    
    public synchronized void mark(final int idx) throws IOException {
        this.in.mark(idx);
    }
    
    public synchronized void reset() throws IOException {
        this.in.reset();
    }
    
    public boolean markSupported() {
        return this.in.markSupported();
    }
}
