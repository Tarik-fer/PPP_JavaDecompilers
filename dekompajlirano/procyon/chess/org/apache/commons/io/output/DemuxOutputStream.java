// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.commons.io.output;

import java.io.IOException;
import java.io.OutputStream;

public class DemuxOutputStream extends OutputStream
{
    private InheritableThreadLocal m_streams;
    
    public DemuxOutputStream() {
        this.m_streams = new InheritableThreadLocal();
    }
    
    public OutputStream bindStream(final OutputStream output) {
        final OutputStream stream = this.getStream();
        this.m_streams.set(output);
        return stream;
    }
    
    public void close() throws IOException {
        final OutputStream output = this.getStream();
        if (null != output) {
            output.close();
        }
    }
    
    public void flush() throws IOException {
        final OutputStream output = this.getStream();
        if (null != output) {
            output.flush();
        }
    }
    
    public void write(final int ch) throws IOException {
        final OutputStream output = this.getStream();
        if (null != output) {
            output.write(ch);
        }
    }
    
    private OutputStream getStream() {
        return (OutputStream)this.m_streams.get();
    }
}
