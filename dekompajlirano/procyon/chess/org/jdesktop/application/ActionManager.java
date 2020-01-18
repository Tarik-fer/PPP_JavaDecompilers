// 
// Decompiled by Procyon v0.5.36
// 

package org.jdesktop.application;

import java.beans.PropertyChangeEvent;
import javax.swing.Action;
import javax.swing.JComponent;
import java.beans.PropertyChangeListener;
import java.awt.KeyboardFocusManager;
import java.util.Iterator;
import javax.swing.ActionMap;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import java.util.logging.Logger;

public class ActionManager extends AbstractBean
{
    private static final Logger logger;
    private final ApplicationContext context;
    private final WeakHashMap<Object, WeakReference<ApplicationActionMap>> actionMaps;
    private ApplicationActionMap globalActionMap;
    
    protected ActionManager(final ApplicationContext context) {
        this.globalActionMap = null;
        if (context == null) {
            throw new IllegalArgumentException("null context");
        }
        this.context = context;
        this.actionMaps = new WeakHashMap<Object, WeakReference<ApplicationActionMap>>();
    }
    
    protected final ApplicationContext getContext() {
        return this.context;
    }
    
    private ApplicationActionMap createActionMapChain(final Class clazz, final Class clazz2, final Object o, final ResourceMap resourceMap) {
        final ArrayList<Class> list = new ArrayList<Class>();
        Class superclass = clazz;
        while (true) {
            list.add(superclass);
            if (superclass.equals(clazz2)) {
                break;
            }
            superclass = superclass.getSuperclass();
        }
        Collections.reverse(list);
        final ApplicationContext context = this.getContext();
        ActionMap parent = null;
        final Iterator<Object> iterator = list.iterator();
        while (iterator.hasNext()) {
            final ApplicationActionMap applicationActionMap = new ApplicationActionMap(context, iterator.next(), o, resourceMap);
            applicationActionMap.setParent(parent);
            parent = applicationActionMap;
        }
        return (ApplicationActionMap)parent;
    }
    
    public ApplicationActionMap getActionMap() {
        if (this.globalActionMap == null) {
            final ApplicationContext context = this.getContext();
            this.globalActionMap = this.createActionMapChain(context.getApplicationClass(), Application.class, context.getApplication(), context.getResourceMap());
            this.initProxyActionSupport();
        }
        return this.globalActionMap;
    }
    
    private void initProxyActionSupport() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener(new KeyboardFocusPCL());
    }
    
    public ApplicationActionMap getActionMap(final Class clazz, final Object o) {
        if (clazz == null) {
            throw new IllegalArgumentException("null actionsClass");
        }
        if (o == null) {
            throw new IllegalArgumentException("null actionsObject");
        }
        if (!clazz.isAssignableFrom(o.getClass())) {
            throw new IllegalArgumentException("actionsObject not instanceof actionsClass");
        }
        synchronized (this.actionMaps) {
            final WeakReference<ApplicationActionMap> weakReference = this.actionMaps.get(o);
            ApplicationActionMap applicationActionMap = (weakReference != null) ? weakReference.get() : null;
            if (applicationActionMap == null || applicationActionMap.getActionsClass() != clazz) {
                final ApplicationContext context = this.getContext();
                final Class<?> class1 = o.getClass();
                ActionMap actionMap;
                for (applicationActionMap = (ApplicationActionMap)(actionMap = this.createActionMapChain(class1, clazz, o, context.getResourceMap(class1, clazz))); actionMap.getParent() != null; actionMap = actionMap.getParent()) {}
                actionMap.setParent(this.getActionMap());
                this.actionMaps.put(o, new WeakReference<ApplicationActionMap>(applicationActionMap));
            }
            return applicationActionMap;
        }
    }
    
    private void updateAllProxyActions(final JComponent component, final JComponent component2) {
        if (component2 != null) {
            final ActionMap actionMap = component2.getActionMap();
            if (actionMap != null) {
                this.updateProxyActions(this.getActionMap(), actionMap, component2);
                final Iterator<WeakReference<ApplicationActionMap>> iterator = this.actionMaps.values().iterator();
                while (iterator.hasNext()) {
                    final ApplicationActionMap applicationActionMap = iterator.next().get();
                    if (applicationActionMap == null) {
                        continue;
                    }
                    this.updateProxyActions(applicationActionMap, actionMap, component2);
                }
            }
        }
    }
    
    private void updateProxyActions(final ApplicationActionMap applicationActionMap, final ActionMap actionMap, final JComponent proxySource) {
        for (final ApplicationAction applicationAction : applicationActionMap.getProxyActions()) {
            final Action value = actionMap.get(applicationAction.getName());
            if (value != null) {
                applicationAction.setProxy(value);
                applicationAction.setProxySource(proxySource);
            }
            else {
                applicationAction.setProxy(null);
                applicationAction.setProxySource(null);
            }
        }
    }
    
    static {
        logger = Logger.getLogger(ActionManager.class.getName());
    }
    
    private final class KeyboardFocusPCL implements PropertyChangeListener
    {
        private final TextActions textActions;
        
        KeyboardFocusPCL() {
            this.textActions = new TextActions(ActionManager.this.getContext());
        }
        
        public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
            if (propertyChangeEvent.getPropertyName() == "permanentFocusOwner") {
                final JComponent focusOwner = ActionManager.this.getContext().getFocusOwner();
                final Object newValue = propertyChangeEvent.getNewValue();
                final JComponent focusOwner2 = (newValue instanceof JComponent) ? ((JComponent)newValue) : null;
                this.textActions.updateFocusOwner(focusOwner, focusOwner2);
                ActionManager.this.getContext().setFocusOwner(focusOwner2);
                ActionManager.this.updateAllProxyActions(focusOwner, focusOwner2);
            }
        }
    }
}
