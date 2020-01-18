// 
// Decompiled by Procyon v0.5.36
// 

package org.jdesktop.application;

import java.util.EventObject;

public class TaskEvent<T> extends EventObject
{
    private final T value;
    
    public final T getValue() {
        return this.value;
    }
    
    public TaskEvent(final Task task, final T value) {
        super(task);
        this.value = value;
    }
}
