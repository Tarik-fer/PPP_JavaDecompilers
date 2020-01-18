// 
// Decompiled by Procyon v0.5.36
// 

package org.jdesktop.application;

import javax.swing.SwingUtilities;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AbstractBean
{
    private final PropertyChangeSupport pcs;
    
    public AbstractBean() {
        this.pcs = new EDTPropertyChangeSupport(this);
    }
    
    public void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.pcs.addPropertyChangeListener(propertyChangeListener);
    }
    
    public void removePropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.pcs.removePropertyChangeListener(propertyChangeListener);
    }
    
    public void addPropertyChangeListener(final String s, final PropertyChangeListener propertyChangeListener) {
        this.pcs.addPropertyChangeListener(s, propertyChangeListener);
    }
    
    public synchronized void removePropertyChangeListener(final String s, final PropertyChangeListener propertyChangeListener) {
        this.pcs.removePropertyChangeListener(s, propertyChangeListener);
    }
    
    public PropertyChangeListener[] getPropertyChangeListeners() {
        return this.pcs.getPropertyChangeListeners();
    }
    
    protected void firePropertyChange(final String s, final Object o, final Object o2) {
        if (o != null && o2 != null && o.equals(o2)) {
            return;
        }
        this.pcs.firePropertyChange(s, o, o2);
    }
    
    protected void firePropertyChange(final PropertyChangeEvent propertyChangeEvent) {
        this.pcs.firePropertyChange(propertyChangeEvent);
    }
    
    private static class EDTPropertyChangeSupport extends PropertyChangeSupport
    {
        EDTPropertyChangeSupport(final Object o) {
            super(o);
        }
        
        @Override
        public void firePropertyChange(final PropertyChangeEvent propertyChangeEvent) {
            if (SwingUtilities.isEventDispatchThread()) {
                super.firePropertyChange(propertyChangeEvent);
            }
            else {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        EDTPropertyChangeSupport.this.firePropertyChange(propertyChangeEvent);
                    }
                });
            }
        }
    }
}
