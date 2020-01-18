/*
 * Decompiled with CFR 0.148.
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class c
implements ActionListener {
    private /* synthetic */ reflex a;

    c(reflex reflex2) {
        this.a = reflex2;
    }

    @Override
    public final void actionPerformed(ActionEvent actionEvent) {
        reflex.a(this.a, actionEvent);
    }
}