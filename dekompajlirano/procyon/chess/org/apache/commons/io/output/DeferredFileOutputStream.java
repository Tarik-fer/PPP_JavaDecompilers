// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.commons.io.output;

import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.io.OutputStream;

public class DeferredFileOutputStream extends ThresholdingOutputStream
{
    private ByteArrayOutputStream memoryOutputStream;
    private OutputStream currentOutputStream;
    private File outputFile;
    private boolean closed;
    
    public DeferredFileOutputStream(final int threshold, final File outputFile) {
        super(threshold);
        this.closed = false;
        this.outputFile = outputFile;
        this.memoryOutputStream = new ByteArrayOutputStream();
        this.currentOutputStream = this.memoryOutputStream;
    }
    
    protected OutputStream getStream() throws IOException {
        return this.currentOutputStream;
    }
    
    protected void thresholdReached() throws IOException {
        final FileOutputStream fos = new FileOutputStream(this.outputFile);
        this.memoryOutputStream.writeTo(fos);
        this.currentOutputStream = fos;
        this.memoryOutputStream = null;
    }
    
    public boolean isInMemory() {
        return !this.isThresholdExceeded();
    }
    
    public byte[] getData() {
        if (this.memoryOutputStream != null) {
            return this.memoryOutputStream.toByteArray();
        }
        return null;
    }
    
    public File getFile() {
        return this.outputFile;
    }
    
    public void close() throws IOException {
        super.close();
        this.closed = true;
    }
    
    public void writeTo(final OutputStream out) throws IOException {
        if (!this.closed) {
            throw new IOException("Stream not closed");
        }
        if (this.isInMemory()) {
            this.memoryOutputStream.writeTo(out);
        }
        else {
            final FileInputStream fis = new FileInputStream(this.outputFile);
            try {
                IOUtils.copy(fis, out);
            }
            finally {
                IOUtils.closeQuietly(fis);
            }
        }
    }
}
