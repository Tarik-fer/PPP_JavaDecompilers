// 
// Decompiled by Procyon v0.5.36
// 

package org.jdesktop.application;

import java.util.Iterator;
import javax.swing.JPanel;
import java.util.Collection;
import java.util.ArrayList;
import java.awt.Container;
import java.awt.Component;
import java.util.Collections;
import javax.swing.JToolBar;
import java.util.List;
import javax.swing.JMenuBar;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import java.util.logging.Logger;

public class View extends AbstractBean
{
    private static final Logger logger;
    private final Application application;
    private ResourceMap resourceMap;
    private JRootPane rootPane;
    private JComponent component;
    private JMenuBar menuBar;
    private List<JToolBar> toolBars;
    private JComponent toolBarsPanel;
    private JComponent statusBar;
    
    public View(final Application application) {
        this.resourceMap = null;
        this.rootPane = null;
        this.component = null;
        this.menuBar = null;
        this.toolBars = Collections.emptyList();
        this.toolBarsPanel = null;
        this.statusBar = null;
        if (application == null) {
            throw new IllegalArgumentException("null application");
        }
        this.application = application;
    }
    
    public final Application getApplication() {
        return this.application;
    }
    
    public final ApplicationContext getContext() {
        return this.getApplication().getContext();
    }
    
    public ResourceMap getResourceMap() {
        if (this.resourceMap == null) {
            this.resourceMap = this.getContext().getResourceMap(this.getClass(), View.class);
        }
        return this.resourceMap;
    }
    
    public JRootPane getRootPane() {
        if (this.rootPane == null) {
            (this.rootPane = new JRootPane()).setOpaque(true);
        }
        return this.rootPane;
    }
    
    private void replaceContentPaneChild(final JComponent component, final JComponent component2, final String s) {
        final Container contentPane = this.getRootPane().getContentPane();
        if (component != null) {
            contentPane.remove(component);
        }
        if (component2 != null) {
            contentPane.add(component2, s);
        }
    }
    
    public JComponent getComponent() {
        return this.component;
    }
    
    public void setComponent(final JComponent component) {
        final JComponent component2 = this.component;
        this.replaceContentPaneChild(component2, this.component = component, "Center");
        this.firePropertyChange("component", component2, this.component);
    }
    
    public JMenuBar getMenuBar() {
        return this.menuBar;
    }
    
    public void setMenuBar(final JMenuBar menuBar) {
        final JMenuBar menuBar2 = this.getMenuBar();
        this.menuBar = menuBar;
        this.getRootPane().setJMenuBar(menuBar);
        this.firePropertyChange("menuBar", menuBar2, menuBar);
    }
    
    public List<JToolBar> getToolBars() {
        return this.toolBars;
    }
    
    public void setToolBars(final List<JToolBar> list) {
        if (list == null) {
            throw new IllegalArgumentException("null toolbars");
        }
        final List<JToolBar> toolBars = this.getToolBars();
        this.toolBars = Collections.unmodifiableList((List<? extends JToolBar>)new ArrayList<JToolBar>(list));
        final JComponent toolBarsPanel = this.toolBarsPanel;
        JComponent component = null;
        if (this.toolBars.size() == 1) {
            component = list.get(0);
        }
        else if (this.toolBars.size() > 1) {
            component = new JPanel();
            final Iterator<JToolBar> iterator = this.toolBars.iterator();
            while (iterator.hasNext()) {
                component.add(iterator.next());
            }
        }
        this.replaceContentPaneChild(toolBarsPanel, component, "North");
        this.firePropertyChange("toolBars", toolBars, this.toolBars);
    }
    
    public final JToolBar getToolBar() {
        final List<JToolBar> toolBars = this.getToolBars();
        return (toolBars.size() == 0) ? null : toolBars.get(0);
    }
    
    public final void setToolBar(final JToolBar toolBar) {
        final JToolBar toolBar2 = this.getToolBar();
        List<JToolBar> toolBars = Collections.emptyList();
        if (toolBar != null) {
            toolBars = Collections.singletonList(toolBar);
        }
        this.setToolBars(toolBars);
        this.firePropertyChange("toolBar", toolBar2, toolBar);
    }
    
    public JComponent getStatusBar() {
        return this.statusBar;
    }
    
    public void setStatusBar(final JComponent statusBar) {
        final JComponent statusBar2 = this.statusBar;
        this.replaceContentPaneChild(statusBar2, this.statusBar = statusBar, "South");
        this.firePropertyChange("statusBar", statusBar2, this.statusBar);
    }
    
    static {
        logger = Logger.getLogger(View.class.getName());
    }
}
