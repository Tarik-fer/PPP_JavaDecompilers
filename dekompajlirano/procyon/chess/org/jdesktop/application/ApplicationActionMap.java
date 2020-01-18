// 
// Decompiled by Procyon v0.5.36
// 

package org.jdesktop.application;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;
import javax.swing.Action;
import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ActionMap;

public class ApplicationActionMap extends ActionMap
{
    private final ApplicationContext context;
    private final ResourceMap resourceMap;
    private final Class actionsClass;
    private final Object actionsObject;
    private final List<ApplicationAction> proxyActions;
    
    public ApplicationActionMap(final ApplicationContext context, final Class actionsClass, final Object actionsObject, final ResourceMap resourceMap) {
        if (context == null) {
            throw new IllegalArgumentException("null context");
        }
        if (actionsClass == null) {
            throw new IllegalArgumentException("null actionsClass");
        }
        if (actionsObject == null) {
            throw new IllegalArgumentException("null actionsObject");
        }
        if (!actionsClass.isInstance(actionsObject)) {
            throw new IllegalArgumentException("actionsObject not an instanceof actionsClass");
        }
        this.context = context;
        this.actionsClass = actionsClass;
        this.actionsObject = actionsObject;
        this.resourceMap = resourceMap;
        this.proxyActions = new ArrayList<ApplicationAction>();
        this.addAnnotationActions(resourceMap);
        this.maybeAddActionsPCL();
    }
    
    public final ApplicationContext getContext() {
        return this.context;
    }
    
    public final Class getActionsClass() {
        return this.actionsClass;
    }
    
    public final Object getActionsObject() {
        return this.actionsObject;
    }
    
    public List<ApplicationAction> getProxyActions() {
        final ArrayList<ApplicationAction> list = new ArrayList<ApplicationAction>(this.proxyActions);
        for (ActionMap actionMap = this.getParent(); actionMap != null; actionMap = actionMap.getParent()) {
            if (actionMap instanceof ApplicationActionMap) {
                list.addAll(((ApplicationActionMap)actionMap).proxyActions);
            }
        }
        return (List<ApplicationAction>)Collections.unmodifiableList((List<?>)list);
    }
    
    private String aString(final String s, final String s2) {
        return (s.length() == 0) ? s2 : s;
    }
    
    private void putAction(final String s, final ApplicationAction applicationAction) {
        if (this.get(s) != null) {}
        this.put(s, applicationAction);
    }
    
    private void addAnnotationActions(final ResourceMap resourceMap) {
        final Class actionsClass = this.getActionsClass();
        for (final Method method : actionsClass.getDeclaredMethods()) {
            final org.jdesktop.application.Action action = method.getAnnotation(org.jdesktop.application.Action.class);
            if (action != null) {
                final String name = method.getName();
                final String aString = this.aString(action.enabledProperty(), null);
                final String aString2 = this.aString(action.selectedProperty(), null);
                final String aString3 = this.aString(action.name(), name);
                this.putAction(aString3, new ApplicationAction(this, resourceMap, aString3, method, aString, aString2, action.block()));
            }
        }
        final ProxyActions proxyActions = actionsClass.getAnnotation(ProxyActions.class);
        if (proxyActions != null) {
            for (final String s : proxyActions.value()) {
                final ApplicationAction applicationAction = new ApplicationAction(this, resourceMap, s);
                applicationAction.setEnabled(false);
                this.putAction(s, applicationAction);
                this.proxyActions.add(applicationAction);
            }
        }
    }
    
    private void maybeAddActionsPCL() {
        boolean b = false;
        final Object[] keys = this.keys();
        if (keys != null) {
            final Object[] array = keys;
            for (int length = array.length, i = 0; i < length; ++i) {
                final Action value = this.get(array[i]);
                if (value instanceof ApplicationAction) {
                    final ApplicationAction applicationAction = (ApplicationAction)value;
                    if (applicationAction.getEnabledProperty() != null || applicationAction.getSelectedProperty() != null) {
                        b = true;
                        break;
                    }
                }
            }
            if (b) {
                try {
                    this.getActionsClass().getMethod("addPropertyChangeListener", PropertyChangeListener.class).invoke(this.getActionsObject(), new ActionsPCL());
                }
                catch (Exception ex) {
                    throw new Error("addPropertyChangeListener undefined " + this.actionsClass, ex);
                }
            }
        }
    }
    
    private class ActionsPCL implements PropertyChangeListener
    {
        public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
            final String propertyName = propertyChangeEvent.getPropertyName();
            final Object[] keys = ApplicationActionMap.this.keys();
            if (keys != null) {
                final Object[] array = keys;
                for (int length = array.length, i = 0; i < length; ++i) {
                    final Action value = ApplicationActionMap.this.get(array[i]);
                    if (value instanceof ApplicationAction) {
                        final ApplicationAction applicationAction = (ApplicationAction)value;
                        if (propertyName.equals(applicationAction.getEnabledProperty())) {
                            applicationAction.forwardPropertyChangeEvent(propertyChangeEvent, "enabled");
                        }
                        else if (propertyName.equals(applicationAction.getSelectedProperty())) {
                            applicationAction.forwardPropertyChangeEvent(propertyChangeEvent, "selected");
                        }
                    }
                }
            }
        }
    }
}
