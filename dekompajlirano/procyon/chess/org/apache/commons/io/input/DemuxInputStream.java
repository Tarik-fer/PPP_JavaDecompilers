// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;

public class DemuxInputStream extends InputStream
{
    private InheritableThreadLocal m_streams;
    
    public DemuxInputStream() {
        this.m_streams = new InheritableThreadLocal();
    }
    
    public InputStream bindStream(final InputStream input) {
        final InputStream oldValue = this.getStream();
        this.m_streams.set(input);
        return oldValue;
    }
    
    public void close() throws IOException {
        final InputStream input = this.getStream();
        if (null != input) {
            input.close();
        }
    }
    
    public int read() throws IOException {
        final InputStream input = this.getStream();
        if (null != input) {
            return input.read();
        }
        return -1;
    }
    
    private InputStream getStream() {
        return (InputStream)this.m_streams.get();
    }
}
