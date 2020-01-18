// 
// Decompiled by Procyon v0.5.36
// 

package org.jdesktop.application;

import java.awt.event.ComponentEvent;
import java.awt.event.HierarchyEvent;
import java.util.EventObject;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.util.Iterator;
import java.lang.reflect.Method;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JWindow;
import java.io.IOException;
import javax.swing.JDialog;
import javax.swing.JComponent;
import java.awt.Container;
import javax.swing.JRootPane;
import java.util.logging.Level;
import java.awt.event.ComponentListener;
import java.awt.event.HierarchyListener;
import java.awt.event.WindowListener;
import javax.swing.RootPaneContainer;
import java.awt.Component;
import java.awt.Window;
import javax.swing.JFrame;
import java.util.logging.Logger;

public abstract class SingleFrameApplication extends Application
{
    private static final Logger logger;
    private ResourceMap appResources;
    private FrameView mainView;
    
    public SingleFrameApplication() {
        this.appResources = null;
        this.mainView = null;
    }
    
    public final JFrame getMainFrame() {
        return this.getMainView().getFrame();
    }
    
    protected final void setMainFrame(final JFrame frame) {
        this.getMainView().setFrame(frame);
    }
    
    private String sessionFilename(final Window window) {
        if (window == null) {
            return null;
        }
        final String name = window.getName();
        return (name == null) ? null : (name + ".session.xml");
    }
    
    protected void configureWindow(final Window window) {
        this.getContext().getResourceMap().injectComponents(window);
    }
    
    private void initRootPaneContainer(final RootPaneContainer rootPaneContainer) {
        final JRootPane rootPane = rootPaneContainer.getRootPane();
        final String s = "SingleFrameApplication.initRootPaneContainer";
        if (rootPane.getClientProperty(s) != null) {
            return;
        }
        rootPane.putClientProperty(s, Boolean.TRUE);
        final Container parent = rootPane.getParent();
        if (parent instanceof Window) {
            this.configureWindow((Window)parent);
        }
        final JFrame mainFrame = this.getMainFrame();
        if (rootPaneContainer == mainFrame) {
            mainFrame.addWindowListener(new MainFrameListener());
            mainFrame.setDefaultCloseOperation(0);
        }
        else if (parent instanceof Window) {
            ((Window)parent).addHierarchyListener(new SecondaryWindowListener());
        }
        if (parent instanceof JFrame) {
            parent.addComponentListener(new FrameBoundsListener());
        }
        if (parent instanceof Window) {
            final Window window = (Window)parent;
            if (!parent.isValid() || parent.getWidth() == 0 || parent.getHeight() == 0) {
                window.pack();
            }
            if (!window.isLocationByPlatform() && parent.getX() == 0 && parent.getY() == 0) {
                Window owner = window.getOwner();
                if (owner == null) {
                    owner = ((window != mainFrame) ? mainFrame : null);
                }
                window.setLocationRelativeTo(owner);
            }
        }
        if (parent instanceof Window) {
            final String sessionFilename = this.sessionFilename((Window)parent);
            if (sessionFilename != null) {
                try {
                    this.getContext().getSessionStorage().restore(parent, sessionFilename);
                }
                catch (Exception ex) {
                    SingleFrameApplication.logger.log(Level.WARNING, String.format("couldn't restore sesssion [%s]", sessionFilename), ex);
                }
            }
        }
    }
    
    protected void show(final JComponent component) {
        if (component == null) {
            throw new IllegalArgumentException("null JComponent");
        }
        final JFrame mainFrame = this.getMainFrame();
        mainFrame.getContentPane().add(component, "Center");
        this.initRootPaneContainer(mainFrame);
        mainFrame.setVisible(true);
    }
    
    public void show(final JDialog dialog) {
        if (dialog == null) {
            throw new IllegalArgumentException("null JDialog");
        }
        this.initRootPaneContainer(dialog);
        dialog.setVisible(true);
    }
    
    public void show(final JFrame frame) {
        if (frame == null) {
            throw new IllegalArgumentException("null JFrame");
        }
        this.initRootPaneContainer(frame);
        frame.setVisible(true);
    }
    
    private void saveSession(final Window window) {
        final String sessionFilename = this.sessionFilename(window);
        if (sessionFilename != null) {
            try {
                this.getContext().getSessionStorage().save(window, sessionFilename);
            }
            catch (IOException ex) {
                SingleFrameApplication.logger.log(Level.WARNING, "couldn't save sesssion", ex);
            }
        }
    }
    
    private boolean isVisibleWindow(final Window window) {
        return window.isVisible() && (window instanceof JFrame || window instanceof JDialog || window instanceof JWindow);
    }
    
    private List<Window> getVisibleSecondaryWindows() {
        final ArrayList<Window> list = new ArrayList<Window>();
        Method method = null;
        try {
            method = Window.class.getMethod("getWindows", (Class<?>[])new Class[0]);
        }
        catch (Exception ex2) {}
        if (method != null) {
            Window[] array;
            try {
                array = (Window[])method.invoke(null, new Object[0]);
            }
            catch (Exception ex) {
                throw new Error("HCTB - can't get top level windows list", ex);
            }
            if (array != null) {
                for (final Window window : array) {
                    if (this.isVisibleWindow(window)) {
                        list.add(window);
                    }
                }
            }
        }
        else {
            final Frame[] frames = Frame.getFrames();
            if (frames != null) {
                for (final Frame frame : frames) {
                    if (this.isVisibleWindow(frame)) {
                        list.add(frame);
                    }
                }
            }
        }
        return list;
    }
    
    @Override
    protected void shutdown() {
        this.saveSession(this.getMainFrame());
        final Iterator<Window> iterator = this.getVisibleSecondaryWindows().iterator();
        while (iterator.hasNext()) {
            this.saveSession(iterator.next());
        }
    }
    
    public FrameView getMainView() {
        if (this.mainView == null) {
            this.mainView = new FrameView(this);
        }
        return this.mainView;
    }
    
    @Override
    public void show(final View view) {
        if (this.mainView == null && view instanceof FrameView) {
            this.mainView = (FrameView)view;
        }
        final RootPaneContainer rootPaneContainer = (RootPaneContainer)view.getRootPane().getParent();
        this.initRootPaneContainer(rootPaneContainer);
        ((Window)rootPaneContainer).setVisible(true);
    }
    
    static {
        logger = Logger.getLogger(SingleFrameApplication.class.getName());
    }
    
    private class MainFrameListener extends WindowAdapter
    {
        @Override
        public void windowClosing(final WindowEvent windowEvent) {
            SingleFrameApplication.this.exit(windowEvent);
        }
    }
    
    private class SecondaryWindowListener implements HierarchyListener
    {
        public void hierarchyChanged(final HierarchyEvent hierarchyEvent) {
            if ((hierarchyEvent.getChangeFlags() & 0x4L) != 0x0L && hierarchyEvent.getSource() instanceof Window) {
                final Window window = (Window)hierarchyEvent.getSource();
                if (!window.isShowing()) {
                    SingleFrameApplication.this.saveSession(window);
                }
            }
        }
    }
    
    private static class FrameBoundsListener implements ComponentListener
    {
        private void maybeSaveFrameSize(final ComponentEvent componentEvent) {
            if (componentEvent.getComponent() instanceof JFrame) {
                final JFrame frame = (JFrame)componentEvent.getComponent();
                if ((frame.getExtendedState() & 0x6) == 0x0) {
                    frame.getRootPane().putClientProperty("WindowState.normalBounds", frame.getBounds());
                }
            }
        }
        
        public void componentResized(final ComponentEvent componentEvent) {
            this.maybeSaveFrameSize(componentEvent);
        }
        
        public void componentMoved(final ComponentEvent componentEvent) {
        }
        
        public void componentHidden(final ComponentEvent componentEvent) {
        }
        
        public void componentShown(final ComponentEvent componentEvent) {
        }
    }
}
