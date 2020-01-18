// 
// Decompiled by Procyon v0.5.36
// 

package org.jdesktop.application;

import java.util.List;

public interface TaskListener<T, V>
{
    void doInBackground(final TaskEvent<Void> p0);
    
    void process(final TaskEvent<List<V>> p0);
    
    void succeeded(final TaskEvent<T> p0);
    
    void failed(final TaskEvent<Throwable> p0);
    
    void cancelled(final TaskEvent<Void> p0);
    
    void interrupted(final TaskEvent<InterruptedException> p0);
    
    void finished(final TaskEvent<Void> p0);
    
    public static class Adapter<T, V> implements TaskListener<T, V>
    {
        public void doInBackground(final TaskEvent<Void> taskEvent) {
        }
        
        public void process(final TaskEvent<List<V>> taskEvent) {
        }
        
        public void succeeded(final TaskEvent<T> taskEvent) {
        }
        
        public void failed(final TaskEvent<Throwable> taskEvent) {
        }
        
        public void cancelled(final TaskEvent<Void> taskEvent) {
        }
        
        public void interrupted(final TaskEvent<InterruptedException> taskEvent) {
        }
        
        public void finished(final TaskEvent<Void> taskEvent) {
        }
    }
}
