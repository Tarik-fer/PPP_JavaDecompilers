import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.36
// 

final class a implements ActionListener
{
    private /* synthetic */ mainReflex a;
    
    a(final mainReflex a) {
        this.a = a;
    }
    
    @Override
    public final void actionPerformed(final ActionEvent actionEvent) {
        mainReflex.a(this.a, actionEvent);
    }
}
