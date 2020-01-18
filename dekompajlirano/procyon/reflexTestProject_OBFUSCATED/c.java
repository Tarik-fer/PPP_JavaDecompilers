import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.36
// 

final class c implements ActionListener
{
    private /* synthetic */ reflex a;
    
    c(final reflex a) {
        this.a = a;
    }
    
    @Override
    public final void actionPerformed(final ActionEvent actionEvent) {
        reflex.a(this.a, actionEvent);
    }
}
