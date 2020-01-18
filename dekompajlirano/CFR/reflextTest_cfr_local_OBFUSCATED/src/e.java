
/*
 * Decompiled with CFR 0.148.
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class e
implements ActionListener {
    private /* synthetic */ reflex a;

    e(reflex reflex2) {
        this.a = reflex2;
    }

    @Override
    public final void actionPerformed(ActionEvent actionEvent) {
        reflex.b(this.a, actionEvent);
    }
}