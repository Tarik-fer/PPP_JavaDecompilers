/*
 * Decompiled with CFR 0.148.
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class f
implements ActionListener {
    private /* synthetic */ reflex a;

    f(reflex reflex2) {
        this.a = reflex2;
    }

    @Override
    public final void actionPerformed(ActionEvent actionEvent) {
        reflex.c(this.a, actionEvent);
    }
}