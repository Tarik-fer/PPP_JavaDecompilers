import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

// 
// Decompiled by Procyon v0.5.36
// 

final class d implements PropertyChangeListener
{
    private /* synthetic */ reflex a;
    
    d(final reflex a) {
        this.a = a;
    }
    
    @Override
    public final void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        reflex.a(this.a, propertyChangeEvent);
    }
}
