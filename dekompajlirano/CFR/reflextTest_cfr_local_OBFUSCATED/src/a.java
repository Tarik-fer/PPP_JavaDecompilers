/*
 * Decompiled with CFR 0.148.
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class a
implements ActionListener {
    private /* synthetic */ mainReflex a;

    a(mainReflex mainReflex2) {
        this.a = mainReflex2;
    }

    @Override
    public final void actionPerformed(ActionEvent actionEvent) {
        mainReflex.a(this.a, actionEvent);
    }
}