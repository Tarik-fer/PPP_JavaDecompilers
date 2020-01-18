// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;

public class CountingInputStream extends ProxyInputStream
{
    private long count;
    
    public CountingInputStream(final InputStream in) {
        super(in);
    }
    
    public int read(final byte[] b) throws IOException {
        final int found = super.read(b);
        this.count += ((found >= 0) ? found : 0L);
        return found;
    }
    
    public int read(final byte[] b, final int off, final int len) throws IOException {
        final int found = super.read(b, off, len);
        this.count += ((found >= 0) ? found : 0L);
        return found;
    }
    
    public int read() throws IOException {
        final int found = super.read();
        this.count += ((found >= 0) ? 1 : 0);
        return found;
    }
    
    public long skip(final long length) throws IOException {
        final long skip = super.skip(length);
        this.count += skip;
        return skip;
    }
    
    public synchronized int getCount() {
        final long result = this.getByteCount();
        if (result > 2147483647L) {
            throw new ArithmeticException("The byte count " + result + " is too large to be converted to an int");
        }
        return (int)result;
    }
    
    public synchronized int resetCount() {
        final long result = this.resetByteCount();
        if (result > 2147483647L) {
            throw new ArithmeticException("The byte count " + result + " is too large to be converted to an int");
        }
        return (int)result;
    }
    
    public synchronized long getByteCount() {
        return this.count;
    }
    
    public synchronized long resetByteCount() {
        final long tmp = this.count;
        this.count = 0L;
        return tmp;
    }
}
