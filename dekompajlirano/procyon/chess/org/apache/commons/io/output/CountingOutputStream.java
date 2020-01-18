// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.commons.io.output;

import java.io.IOException;
import java.io.OutputStream;

public class CountingOutputStream extends ProxyOutputStream
{
    private long count;
    
    public CountingOutputStream(final OutputStream out) {
        super(out);
    }
    
    public void write(final byte[] b) throws IOException {
        this.count += b.length;
        super.write(b);
    }
    
    public void write(final byte[] b, final int off, final int len) throws IOException {
        this.count += len;
        super.write(b, off, len);
    }
    
    public void write(final int b) throws IOException {
        ++this.count;
        super.write(b);
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
