/*
 * Decompiled with CFR 0.148.
 */
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

final class d
implements PropertyChangeListener {
    private /* synthetic */ reflex a;

    d(reflex reflex2) {
        this.a = reflex2;
    }

    @Override
    public final void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        reflex.a(this.a, propertyChangeEvent);
    }
}