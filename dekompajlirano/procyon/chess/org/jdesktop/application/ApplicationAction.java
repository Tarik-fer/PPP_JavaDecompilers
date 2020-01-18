// 
// Decompiled by Procyon v0.5.36
// 

package org.jdesktop.application;

import java.beans.PropertyChangeEvent;
import java.lang.annotation.Annotation;
import javax.swing.ActionMap;
import java.awt.event.ActionEvent;
import javax.swing.Icon;
import javax.swing.KeyStroke;
import java.beans.PropertyChangeListener;
import javax.swing.Action;
import java.lang.reflect.Method;
import java.util.logging.Logger;
import javax.swing.AbstractAction;

public class ApplicationAction extends AbstractAction
{
    private static final Logger logger;
    private final ApplicationActionMap appAM;
    private final ResourceMap resourceMap;
    private final String actionName;
    private final Method actionMethod;
    private final String enabledProperty;
    private final Method isEnabledMethod;
    private final Method setEnabledMethod;
    private final String selectedProperty;
    private final Method isSelectedMethod;
    private final Method setSelectedMethod;
    private final Task.BlockingScope block;
    private Action proxy;
    private Object proxySource;
    private PropertyChangeListener proxyPCL;
    private static final String SELECTED_KEY = "SwingSelectedKey";
    private static final String DISPLAYED_MNEMONIC_INDEX_KEY = "SwingDisplayedMnemonicIndexKey";
    private static final String LARGE_ICON_KEY = "SwingLargeIconKey";
    
    public ApplicationAction(final ApplicationActionMap appAM, final ResourceMap resourceMap, final String actionName, final Method actionMethod, final String enabledProperty, final String selectedProperty, final Task.BlockingScope block) {
        this.proxy = null;
        this.proxySource = null;
        this.proxyPCL = null;
        if (appAM == null) {
            throw new IllegalArgumentException("null appAM");
        }
        if (actionName == null) {
            throw new IllegalArgumentException("null baseName");
        }
        this.appAM = appAM;
        this.resourceMap = resourceMap;
        this.actionName = actionName;
        this.actionMethod = actionMethod;
        this.enabledProperty = enabledProperty;
        this.selectedProperty = selectedProperty;
        this.block = block;
        if (enabledProperty != null) {
            this.setEnabledMethod = this.propertySetMethod(enabledProperty, Boolean.TYPE);
            this.isEnabledMethod = this.propertyGetMethod(enabledProperty);
            if (this.isEnabledMethod == null) {
                throw this.newNoSuchPropertyException(enabledProperty);
            }
        }
        else {
            this.isEnabledMethod = null;
            this.setEnabledMethod = null;
        }
        if (selectedProperty != null) {
            this.setSelectedMethod = this.propertySetMethod(selectedProperty, Boolean.TYPE);
            this.isSelectedMethod = this.propertyGetMethod(selectedProperty);
            if (this.isSelectedMethod == null) {
                throw this.newNoSuchPropertyException(selectedProperty);
            }
            super.putValue("SwingSelectedKey", Boolean.FALSE);
        }
        else {
            this.isSelectedMethod = null;
            this.setSelectedMethod = null;
        }
        if (resourceMap != null) {
            this.initActionProperties(resourceMap, actionName);
        }
    }
    
    ApplicationAction(final ApplicationActionMap applicationActionMap, final ResourceMap resourceMap, final String s) {
        this(applicationActionMap, resourceMap, s, null, null, null, Task.BlockingScope.NONE);
    }
    
    private IllegalArgumentException newNoSuchPropertyException(final String s) {
        return new IllegalArgumentException(String.format("no property named %s in %s", s, this.appAM.getActionsClass().getName()));
    }
    
    String getEnabledProperty() {
        return this.enabledProperty;
    }
    
    String getSelectedProperty() {
        return this.selectedProperty;
    }
    
    public Action getProxy() {
        return this.proxy;
    }
    
    public void setProxy(final Action proxy) {
        final Action proxy2 = this.proxy;
        this.proxy = proxy;
        if (proxy2 != null) {
            proxy2.removePropertyChangeListener(this.proxyPCL);
            this.proxyPCL = null;
        }
        if (this.proxy != null) {
            this.updateProxyProperties();
            proxy.addPropertyChangeListener(this.proxyPCL = new ProxyPCL());
        }
        else if (proxy2 != null) {
            this.setEnabled(false);
            this.setSelected(false);
        }
        this.firePropertyChange("proxy", proxy2, this.proxy);
    }
    
    public Object getProxySource() {
        return this.proxySource;
    }
    
    public void setProxySource(final Object proxySource) {
        this.firePropertyChange("proxySource", this.proxySource, this.proxySource = proxySource);
    }
    
    private void maybePutDescriptionValue(final String s, final Action action) {
        final Object value = action.getValue(s);
        if (value instanceof String) {
            this.putValue(s, value);
        }
    }
    
    private void updateProxyProperties() {
        final Action proxy = this.getProxy();
        if (proxy != null) {
            this.setEnabled(proxy.isEnabled());
            final Object value = proxy.getValue("SwingSelectedKey");
            this.setSelected(value instanceof Boolean && (boolean)value);
            this.maybePutDescriptionValue("ShortDescription", proxy);
            this.maybePutDescriptionValue("LongDescription", proxy);
        }
    }
    
    private void initActionProperties(final ResourceMap resourceMap, final String s) {
        boolean b = false;
        final String string = resourceMap.getString(s + ".Action.text", new Object[0]);
        if (string != null) {
            MnemonicText.configure(this, string);
            b = true;
        }
        final Integer keyCode = resourceMap.getKeyCode(s + ".Action.mnemonic");
        if (keyCode != null) {
            this.putValue("MnemonicKey", keyCode);
        }
        final Integer integer = resourceMap.getInteger(s + ".Action.displayedMnemonicIndex");
        if (integer != null) {
            this.putValue("SwingDisplayedMnemonicIndexKey", integer);
        }
        final KeyStroke keyStroke = resourceMap.getKeyStroke(s + ".Action.accelerator");
        if (keyStroke != null) {
            this.putValue("AcceleratorKey", keyStroke);
        }
        final Icon icon = resourceMap.getIcon(s + ".Action.icon");
        if (icon != null) {
            this.putValue("SmallIcon", icon);
            this.putValue("SwingLargeIconKey", icon);
            b = true;
        }
        final Icon icon2 = resourceMap.getIcon(s + ".Action.smallIcon");
        if (icon2 != null) {
            this.putValue("SmallIcon", icon2);
            b = true;
        }
        final Icon icon3 = resourceMap.getIcon(s + ".Action.largeIcon");
        if (icon3 != null) {
            this.putValue("SwingLargeIconKey", icon3);
            b = true;
        }
        this.putValue("ShortDescription", resourceMap.getString(s + ".Action.shortDescription", new Object[0]));
        this.putValue("LongDescription", resourceMap.getString(s + ".Action.longDescription", new Object[0]));
        this.putValue("ActionCommandKey", resourceMap.getString(s + ".Action.command", new Object[0]));
        if (!b) {
            this.putValue("Name", this.actionName);
        }
    }
    
    private String propertyMethodName(final String s, final String s2) {
        return s + s2.substring(0, 1).toUpperCase() + s2.substring(1);
    }
    
    private Method propertyGetMethod(final String s) {
        final String[] array = { this.propertyMethodName("is", s), this.propertyMethodName("get", s) };
        final Class actionsClass = this.appAM.getActionsClass();
        final String[] array2 = array;
        final int length = array2.length;
        int i = 0;
        while (i < length) {
            final String s2 = array2[i];
            try {
                return actionsClass.getMethod(s2, (Class[])new Class[0]);
            }
            catch (NoSuchMethodException ex) {
                ++i;
                continue;
            }
            break;
        }
        return null;
    }
    
    private Method propertySetMethod(final String s, final Class clazz) {
        final Class actionsClass = this.appAM.getActionsClass();
        try {
            return actionsClass.getMethod(this.propertyMethodName("set", s), clazz);
        }
        catch (NoSuchMethodException ex) {
            return null;
        }
    }
    
    public String getName() {
        return this.actionName;
    }
    
    public ResourceMap getResourceMap() {
        return this.resourceMap;
    }
    
    protected Object getActionArgument(final Class clazz, final String s, final ActionEvent actionEvent) {
        Object o = null;
        if (clazz == ActionEvent.class) {
            o = actionEvent;
        }
        else if (clazz == Action.class) {
            o = this;
        }
        else if (clazz == ActionMap.class) {
            o = this.appAM;
        }
        else if (clazz == ResourceMap.class) {
            o = this.resourceMap;
        }
        else if (clazz == ApplicationContext.class) {
            o = this.appAM.getContext();
        }
        else if (clazz == Application.class) {
            o = this.appAM.getContext().getApplication();
        }
        else {
            this.actionFailed(actionEvent, new IllegalArgumentException("unrecognized @Action method parameter"));
        }
        return o;
    }
    
    private Task.InputBlocker createInputBlocker(final Task task, final ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (this.block == Task.BlockingScope.ACTION) {
            source = this;
        }
        return new DefaultInputBlocker(task, this.block, source, this);
    }
    
    private void noProxyActionPerformed(final ActionEvent actionEvent) {
        Object invoke = null;
        final Annotation[][] parameterAnnotations = this.actionMethod.getParameterAnnotations();
        final Class<?>[] parameterTypes = this.actionMethod.getParameterTypes();
        final Object[] array = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; ++i) {
            String value = null;
            for (final Annotation annotation : parameterAnnotations[i]) {
                if (annotation instanceof org.jdesktop.application.Action.Parameter) {
                    value = ((org.jdesktop.application.Action.Parameter)annotation).value();
                    break;
                }
            }
            array[i] = this.getActionArgument(parameterTypes[i], value, actionEvent);
        }
        try {
            invoke = this.actionMethod.invoke(this.appAM.getActionsObject(), array);
        }
        catch (Exception ex) {
            this.actionFailed(actionEvent, ex);
        }
        if (invoke instanceof Task) {
            final Task task = (Task)invoke;
            if (task.getInputBlocker() == null) {
                task.setInputBlocker(this.createInputBlocker(task, actionEvent));
            }
            this.appAM.getContext().getTaskService().execute(task);
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Action proxy = this.getProxy();
        if (proxy != null) {
            actionEvent.setSource(this.getProxySource());
            proxy.actionPerformed(actionEvent);
        }
        else if (this.actionMethod != null) {
            this.noProxyActionPerformed(actionEvent);
        }
    }
    
    @Override
    public boolean isEnabled() {
        if (this.getProxy() != null || this.isEnabledMethod == null) {
            return super.isEnabled();
        }
        try {
            return (boolean)this.isEnabledMethod.invoke(this.appAM.getActionsObject(), new Object[0]);
        }
        catch (Exception ex) {
            throw this.newInvokeError(this.isEnabledMethod, ex, new Object[0]);
        }
    }
    
    @Override
    public void setEnabled(final boolean enabled) {
        if (this.getProxy() != null || this.setEnabledMethod == null) {
            super.setEnabled(enabled);
        }
        else {
            try {
                this.setEnabledMethod.invoke(this.appAM.getActionsObject(), enabled);
            }
            catch (Exception ex) {
                throw this.newInvokeError(this.setEnabledMethod, ex, enabled);
            }
        }
    }
    
    public boolean isSelected() {
        if (this.getProxy() != null || this.isSelectedMethod == null) {
            final Object value = this.getValue("SwingSelectedKey");
            return value instanceof Boolean && (boolean)value;
        }
        try {
            return (boolean)this.isSelectedMethod.invoke(this.appAM.getActionsObject(), new Object[0]);
        }
        catch (Exception ex) {
            throw this.newInvokeError(this.isSelectedMethod, ex, new Object[0]);
        }
    }
    
    public void setSelected(final boolean b) {
        if (this.getProxy() != null || this.setSelectedMethod == null) {
            super.putValue("SwingSelectedKey", b);
        }
        else {
            try {
                super.putValue("SwingSelectedKey", b);
                if (b != this.isSelected()) {
                    this.setSelectedMethod.invoke(this.appAM.getActionsObject(), b);
                }
            }
            catch (Exception ex) {
                throw this.newInvokeError(this.setSelectedMethod, ex, b);
            }
        }
    }
    
    @Override
    public void putValue(final String s, final Object o) {
        if ("SwingSelectedKey".equals(s) && o instanceof Boolean) {
            this.setSelected((boolean)o);
        }
        else {
            super.putValue(s, o);
        }
    }
    
    private Error newInvokeError(final Method method, final Exception ex, final Object... array) {
        String string = (array.length == 0) ? "" : array[0].toString();
        for (int i = 1; i < array.length; ++i) {
            string = string + ", " + array[i];
        }
        return new Error(String.format("%s.%s(%s) failed", this.appAM.getActionsObject().getClass().getName(), method, string), ex);
    }
    
    void forwardPropertyChangeEvent(final PropertyChangeEvent propertyChangeEvent, final String s) {
        if ("selected".equals(s) && propertyChangeEvent.getNewValue() instanceof Boolean) {
            this.putValue("SwingSelectedKey", propertyChangeEvent.getNewValue());
        }
        this.firePropertyChange(s, propertyChangeEvent.getOldValue(), propertyChangeEvent.getNewValue());
    }
    
    private void actionFailed(final ActionEvent actionEvent, final Exception ex) {
        throw new Error(ex);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getName());
        sb.append(" ");
        final boolean enabled = this.isEnabled();
        if (!enabled) {
            sb.append("(");
        }
        sb.append(this.getName());
        final Object value = this.getValue("SwingSelectedKey");
        if (value instanceof Boolean && (boolean)value) {
            sb.append("+");
        }
        if (!enabled) {
            sb.append(")");
        }
        final Object value2 = this.getValue("Name");
        if (value2 instanceof String) {
            sb.append(" \"");
            sb.append((String)value2);
            sb.append("\"");
        }
        this.proxy = this.getProxy();
        if (this.proxy != null) {
            sb.append(" Proxy for: ");
            sb.append(this.proxy.toString());
        }
        return sb.toString();
    }
    
    static {
        logger = Logger.getLogger(ApplicationAction.class.getName());
    }
    
    private class ProxyPCL implements PropertyChangeListener
    {
        public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
            final String propertyName = propertyChangeEvent.getPropertyName();
            if (propertyName == null || "enabled".equals(propertyName) || "selected".equals(propertyName) || "ShortDescription".equals(propertyName) || "LongDescription".equals(propertyName)) {
                ApplicationAction.this.updateProxyProperties();
            }
        }
    }
}
