// 
// Decompiled by Procyon v0.5.36
// 

package org.jdesktop.application;

import javax.swing.JRootPane;
import javax.swing.JFrame;
import java.util.logging.Logger;

public class FrameView extends View
{
    private static final Logger logger;
    private JFrame frame;
    
    public FrameView(final Application application) {
        super(application);
        this.frame = null;
    }
    
    public JFrame getFrame() {
        if (this.frame == null) {
            (this.frame = new JFrame(this.getContext().getResourceMap().getString("Application.title", new Object[0]))).setName("mainFrame");
        }
        return this.frame;
    }
    
    public void setFrame(final JFrame frame) {
        if (frame == null) {
            throw new IllegalArgumentException("null JFrame");
        }
        if (this.frame != null) {
            throw new IllegalStateException("frame already set");
        }
        this.firePropertyChange("frame", null, this.frame = frame);
    }
    
    @Override
    public JRootPane getRootPane() {
        return this.getFrame().getRootPane();
    }
    
    static {
        logger = Logger.getLogger(FrameView.class.getName());
    }
}
