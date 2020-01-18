// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.commons.io.output;

import java.io.IOException;
import java.io.OutputStream;

public class TeeOutputStream extends ProxyOutputStream
{
    protected OutputStream branch;
    
    public TeeOutputStream(final OutputStream out, final OutputStream branch) {
        super(out);
        this.branch = branch;
    }
    
    public synchronized void write(final byte[] b) throws IOException {
        super.write(b);
        this.branch.write(b);
    }
    
    public synchronized void write(final byte[] b, final int off, final int len) throws IOException {
        super.write(b, off, len);
        this.branch.write(b, off, len);
    }
    
    public synchronized void write(final int b) throws IOException {
        super.write(b);
        this.branch.write(b);
    }
    
    public void flush() throws IOException {
        super.flush();
        this.branch.flush();
    }
    
    public void close() throws IOException {
        super.close();
        this.branch.close();
    }
}
