// 
// Decompiled by Procyon v0.5.36
// 

package org.jdesktop.application;

import javax.swing.table.TableColumn;
import java.awt.Dialog;
import java.awt.GraphicsConfiguration;
import javax.swing.JFrame;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import javax.swing.JComponent;
import java.io.IOException;
import java.util.Iterator;
import java.util.Collection;
import java.util.Collections;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import java.applet.Applet;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import java.awt.Window;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class SessionStorage
{
    private static Logger logger;
    private final Map<Class, Property> propertyMap;
    private final ApplicationContext context;
    
    protected SessionStorage(final ApplicationContext context) {
        if (context == null) {
            throw new IllegalArgumentException("null context");
        }
        this.context = context;
        (this.propertyMap = new HashMap<Class, Property>()).put(Window.class, new WindowProperty());
        this.propertyMap.put(JTabbedPane.class, new TabbedPaneProperty());
        this.propertyMap.put(JSplitPane.class, new SplitPaneProperty());
        this.propertyMap.put(JTable.class, new TableProperty());
    }
    
    protected final ApplicationContext getContext() {
        return this.context;
    }
    
    private void checkSaveRestoreArgs(final Component component, final String s) {
        if (component == null) {
            throw new IllegalArgumentException("null root");
        }
        if (s == null) {
            throw new IllegalArgumentException("null fileName");
        }
    }
    
    private String getComponentName(final Component component) {
        return component.getName();
    }
    
    private String getComponentPathname(Component parent) {
        final String componentName = this.getComponentName(parent);
        if (componentName == null) {
            return null;
        }
        final StringBuilder sb = new StringBuilder(componentName);
        while (parent.getParent() != null && !(parent instanceof Window) && !(parent instanceof Applet)) {
            parent = parent.getParent();
            String s = this.getComponentName(parent);
            if (s == null) {
                final int componentZOrder = parent.getParent().getComponentZOrder(parent);
                if (componentZOrder < 0) {
                    SessionStorage.logger.warning("Couldn't compute pathname for " + parent);
                    return null;
                }
                final Class<? extends Component> class1 = parent.getClass();
                String s2 = class1.getSimpleName();
                if (s2.length() == 0) {
                    s2 = "Anonymous" + class1.getSuperclass().getSimpleName();
                }
                s = s2 + componentZOrder;
            }
            sb.append("/").append(s);
        }
        return sb.toString();
    }
    
    private void saveTree(final List<Component> list, final Map<String, Object> map) {
        final ArrayList<Object> list2 = new ArrayList<Object>();
        for (final Component component : list) {
            if (component != null) {
                final Property property = this.getProperty(component);
                if (property != null) {
                    final String componentPathname = this.getComponentPathname(component);
                    if (componentPathname != null) {
                        final Object sessionState = property.getSessionState(component);
                        if (sessionState != null) {
                            map.put(componentPathname, sessionState);
                        }
                    }
                }
            }
            if (component instanceof Container) {
                final Component[] components = ((Container)component).getComponents();
                if (components == null || components.length <= 0) {
                    continue;
                }
                Collections.addAll(list2, components);
            }
        }
        if (list2.size() > 0) {
            this.saveTree((List<Component>)list2, map);
        }
    }
    
    public void save(final Component component, final String s) throws IOException {
        this.checkSaveRestoreArgs(component, s);
        final HashMap<String, Object> hashMap = new HashMap<String, Object>();
        this.saveTree(Collections.singletonList(component), hashMap);
        this.getContext().getLocalStorage().save(hashMap, s);
    }
    
    private void restoreTree(final List<Component> list, final Map<String, Object> map) {
        final ArrayList<Object> list2 = new ArrayList<Object>();
        for (final Component component : list) {
            if (component != null) {
                final Property property = this.getProperty(component);
                if (property != null) {
                    final String componentPathname = this.getComponentPathname(component);
                    if (componentPathname != null) {
                        final Object value = map.get(componentPathname);
                        if (value != null) {
                            property.setSessionState(component, value);
                        }
                        else {
                            SessionStorage.logger.warning("No saved state for " + component);
                        }
                    }
                }
            }
            if (component instanceof Container) {
                final Component[] components = ((Container)component).getComponents();
                if (components == null || components.length <= 0) {
                    continue;
                }
                Collections.addAll(list2, components);
            }
        }
        if (list2.size() > 0) {
            this.restoreTree((List<Component>)list2, map);
        }
    }
    
    public void restore(final Component component, final String s) throws IOException {
        this.checkSaveRestoreArgs(component, s);
        final Map map = (Map)this.getContext().getLocalStorage().load(s);
        if (map != null) {
            this.restoreTree(Collections.singletonList(component), map);
        }
    }
    
    private void checkClassArg(final Class clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("null class");
        }
    }
    
    public Property getProperty(Class superclass) {
        this.checkClassArg(superclass);
        while (superclass != null) {
            final Property property = this.propertyMap.get(superclass);
            if (property != null) {
                return property;
            }
            superclass = superclass.getSuperclass();
        }
        return null;
    }
    
    public void putProperty(final Class clazz, final Property property) {
        this.checkClassArg(clazz);
        this.propertyMap.put(clazz, property);
    }
    
    public final Property getProperty(final Component component) {
        if (component == null) {
            throw new IllegalArgumentException("null component");
        }
        if (component instanceof Property) {
            return (Property)component;
        }
        Property property = null;
        if (component instanceof JComponent) {
            final Object clientProperty = ((JComponent)component).getClientProperty(Property.class);
            property = ((clientProperty instanceof Property) ? ((Property)clientProperty) : null);
        }
        return (property != null) ? property : this.getProperty(component.getClass());
    }
    
    static {
        SessionStorage.logger = Logger.getLogger(SessionStorage.class.getName());
    }
    
    public static class WindowState
    {
        private final Rectangle bounds;
        private Rectangle gcBounds;
        private int screenCount;
        private int frameState;
        
        public WindowState() {
            this.gcBounds = null;
            this.frameState = 0;
            this.bounds = new Rectangle();
        }
        
        public WindowState(final Rectangle bounds, final Rectangle gcBounds, final int screenCount, final int frameState) {
            this.gcBounds = null;
            this.frameState = 0;
            if (bounds == null) {
                throw new IllegalArgumentException("null bounds");
            }
            if (screenCount < 1) {
                throw new IllegalArgumentException("invalid screenCount");
            }
            this.bounds = bounds;
            this.gcBounds = gcBounds;
            this.screenCount = screenCount;
            this.frameState = frameState;
        }
        
        public Rectangle getBounds() {
            return new Rectangle(this.bounds);
        }
        
        public void setBounds(final Rectangle bounds) {
            this.bounds.setBounds(bounds);
        }
        
        public int getScreenCount() {
            return this.screenCount;
        }
        
        public void setScreenCount(final int screenCount) {
            this.screenCount = screenCount;
        }
        
        public int getFrameState() {
            return this.frameState;
        }
        
        public void setFrameState(final int frameState) {
            this.frameState = frameState;
        }
        
        public Rectangle getGraphicsConfigurationBounds() {
            return (this.gcBounds == null) ? null : new Rectangle(this.gcBounds);
        }
        
        public void setGraphicsConfigurationBounds(final Rectangle rectangle) {
            this.gcBounds = ((rectangle == null) ? null : new Rectangle(rectangle));
        }
    }
    
    public static class WindowProperty implements Property
    {
        private void checkComponent(final Component component) {
            if (component == null) {
                throw new IllegalArgumentException("null component");
            }
            if (!(component instanceof Window)) {
                throw new IllegalArgumentException("invalid component");
            }
        }
        
        private int getScreenCount() {
            return GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices().length;
        }
        
        public Object getSessionState(final Component component) {
            this.checkComponent(component);
            int extendedState = 0;
            if (component instanceof Frame) {
                extendedState = ((Frame)component).getExtendedState();
            }
            final GraphicsConfiguration graphicsConfiguration = component.getGraphicsConfiguration();
            final Rectangle rectangle = (graphicsConfiguration == null) ? null : graphicsConfiguration.getBounds();
            Rectangle bounds = component.getBounds();
            if (component instanceof JFrame && 0x0 != (extendedState & 0x6)) {
                final Object clientProperty = ((JFrame)component).getRootPane().getClientProperty("WindowState.normalBounds");
                if (clientProperty instanceof Rectangle) {
                    bounds = (Rectangle)clientProperty;
                }
            }
            return new WindowState(bounds, rectangle, this.getScreenCount(), extendedState);
        }
        
        public void setSessionState(final Component component, final Object o) {
            this.checkComponent(component);
            if (o != null && !(o instanceof WindowState)) {
                throw new IllegalArgumentException("invalid state");
            }
            final Window window = (Window)component;
            if (!window.isLocationByPlatform() && o != null) {
                final WindowState windowState = (WindowState)o;
                final Rectangle graphicsConfigurationBounds = windowState.getGraphicsConfigurationBounds();
                final int screenCount = windowState.getScreenCount();
                final GraphicsConfiguration graphicsConfiguration = component.getGraphicsConfiguration();
                final Rectangle rectangle = (graphicsConfiguration == null) ? null : graphicsConfiguration.getBounds();
                final int screenCount2 = this.getScreenCount();
                if (graphicsConfigurationBounds != null && graphicsConfigurationBounds.equals(rectangle) && screenCount == screenCount2) {
                    boolean b = true;
                    if (window instanceof Frame) {
                        b = ((Frame)window).isResizable();
                    }
                    else if (window instanceof Dialog) {
                        b = ((Dialog)window).isResizable();
                    }
                    if (b) {
                        window.setBounds(windowState.getBounds());
                    }
                }
                if (window instanceof Frame) {
                    ((Frame)window).setExtendedState(windowState.getFrameState());
                }
            }
        }
    }
    
    public static class TabbedPaneState
    {
        private int selectedIndex;
        private int tabCount;
        
        public TabbedPaneState() {
            this.selectedIndex = -1;
            this.tabCount = 0;
        }
        
        public TabbedPaneState(final int selectedIndex, final int tabCount) {
            if (tabCount < 0) {
                throw new IllegalArgumentException("invalid tabCount");
            }
            if (selectedIndex < -1 || selectedIndex > tabCount) {
                throw new IllegalArgumentException("invalid selectedIndex");
            }
            this.selectedIndex = selectedIndex;
            this.tabCount = tabCount;
        }
        
        public int getSelectedIndex() {
            return this.selectedIndex;
        }
        
        public void setSelectedIndex(final int selectedIndex) {
            if (selectedIndex < -1) {
                throw new IllegalArgumentException("invalid selectedIndex");
            }
            this.selectedIndex = selectedIndex;
        }
        
        public int getTabCount() {
            return this.tabCount;
        }
        
        public void setTabCount(final int tabCount) {
            if (tabCount < 0) {
                throw new IllegalArgumentException("invalid tabCount");
            }
            this.tabCount = tabCount;
        }
    }
    
    public static class TabbedPaneProperty implements Property
    {
        private void checkComponent(final Component component) {
            if (component == null) {
                throw new IllegalArgumentException("null component");
            }
            if (!(component instanceof JTabbedPane)) {
                throw new IllegalArgumentException("invalid component");
            }
        }
        
        public Object getSessionState(final Component component) {
            this.checkComponent(component);
            final JTabbedPane tabbedPane = (JTabbedPane)component;
            return new TabbedPaneState(tabbedPane.getSelectedIndex(), tabbedPane.getTabCount());
        }
        
        public void setSessionState(final Component component, final Object o) {
            this.checkComponent(component);
            if (o != null && !(o instanceof TabbedPaneState)) {
                throw new IllegalArgumentException("invalid state");
            }
            final JTabbedPane tabbedPane = (JTabbedPane)component;
            final TabbedPaneState tabbedPaneState = (TabbedPaneState)o;
            if (tabbedPane.getTabCount() == tabbedPaneState.getTabCount()) {
                tabbedPane.setSelectedIndex(tabbedPaneState.getSelectedIndex());
            }
        }
    }
    
    public static class SplitPaneState
    {
        private int dividerLocation;
        private int orientation;
        
        private void checkOrientation(final int n) {
            if (n != 1 && n != 0) {
                throw new IllegalArgumentException("invalid orientation");
            }
        }
        
        public SplitPaneState() {
            this.dividerLocation = -1;
            this.orientation = 1;
        }
        
        public SplitPaneState(final int dividerLocation, final int orientation) {
            this.dividerLocation = -1;
            this.orientation = 1;
            this.checkOrientation(orientation);
            if (dividerLocation < -1) {
                throw new IllegalArgumentException("invalid dividerLocation");
            }
            this.dividerLocation = dividerLocation;
            this.orientation = orientation;
        }
        
        public int getDividerLocation() {
            return this.dividerLocation;
        }
        
        public void setDividerLocation(final int dividerLocation) {
            if (dividerLocation < -1) {
                throw new IllegalArgumentException("invalid dividerLocation");
            }
            this.dividerLocation = dividerLocation;
        }
        
        public int getOrientation() {
            return this.orientation;
        }
        
        public void setOrientation(final int orientation) {
            this.checkOrientation(orientation);
            this.orientation = orientation;
        }
    }
    
    public static class SplitPaneProperty implements Property
    {
        private void checkComponent(final Component component) {
            if (component == null) {
                throw new IllegalArgumentException("null component");
            }
            if (!(component instanceof JSplitPane)) {
                throw new IllegalArgumentException("invalid component");
            }
        }
        
        public Object getSessionState(final Component component) {
            this.checkComponent(component);
            final JSplitPane splitPane = (JSplitPane)component;
            return new SplitPaneState(splitPane.getUI().getDividerLocation(splitPane), splitPane.getOrientation());
        }
        
        public void setSessionState(final Component component, final Object o) {
            this.checkComponent(component);
            if (o != null && !(o instanceof SplitPaneState)) {
                throw new IllegalArgumentException("invalid state");
            }
            final JSplitPane splitPane = (JSplitPane)component;
            final SplitPaneState splitPaneState = (SplitPaneState)o;
            if (splitPane.getOrientation() == splitPaneState.getOrientation()) {
                splitPane.setDividerLocation(splitPaneState.getDividerLocation());
            }
        }
    }
    
    public static class TableState
    {
        private int[] columnWidths;
        
        private int[] copyColumnWidths(final int[] array) {
            if (array == null) {
                throw new IllegalArgumentException("invalid columnWidths");
            }
            final int[] array2 = new int[array.length];
            System.arraycopy(array, 0, array2, 0, array.length);
            return array2;
        }
        
        public TableState() {
            this.columnWidths = new int[0];
        }
        
        public TableState(final int[] array) {
            this.columnWidths = new int[0];
            this.columnWidths = this.copyColumnWidths(array);
        }
        
        public int[] getColumnWidths() {
            return this.copyColumnWidths(this.columnWidths);
        }
        
        public void setColumnWidths(final int[] array) {
            this.columnWidths = this.copyColumnWidths(array);
        }
    }
    
    public static class TableProperty implements Property
    {
        private void checkComponent(final Component component) {
            if (component == null) {
                throw new IllegalArgumentException("null component");
            }
            if (!(component instanceof JTable)) {
                throw new IllegalArgumentException("invalid component");
            }
        }
        
        public Object getSessionState(final Component component) {
            this.checkComponent(component);
            final JTable table = (JTable)component;
            final int[] array = new int[table.getColumnCount()];
            boolean b = false;
            for (int i = 0; i < array.length; ++i) {
                final TableColumn column = table.getColumnModel().getColumn(i);
                array[i] = (column.getResizable() ? column.getWidth() : -1);
                if (column.getResizable()) {
                    b = true;
                }
            }
            return b ? new TableState(array) : null;
        }
        
        public void setSessionState(final Component component, final Object o) {
            this.checkComponent(component);
            if (!(o instanceof TableState)) {
                throw new IllegalArgumentException("invalid state");
            }
            final JTable table = (JTable)component;
            final int[] columnWidths = ((TableState)o).getColumnWidths();
            if (table.getColumnCount() == columnWidths.length) {
                for (int i = 0; i < columnWidths.length; ++i) {
                    if (columnWidths[i] != -1) {
                        final TableColumn column = table.getColumnModel().getColumn(i);
                        if (column.getResizable()) {
                            column.setPreferredWidth(columnWidths[i]);
                        }
                    }
                }
            }
        }
    }
    
    public interface Property
    {
        Object getSessionState(final Component p0);
        
        void setSessionState(final Component p0, final Object p1);
    }
}
