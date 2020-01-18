
/*
 * Decompiled with CFR 0.148.
 */
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class mainReflex
extends JFrame {
    private JButton a;
    private JButton b;
    private JLabel c;
    private JLabel d;

    public mainReflex() {
        mainReflex mainReflex2 = this;
        this.c = new JLabel();
        mainReflex2.a = new JButton();
        mainReflex2.b = new JButton();
        mainReflex2.d = new JLabel();
        mainReflex2.setDefaultCloseOperation(3);
        mainReflex2.c.setFont(new Font("Arial", 0, 18));
        mainReflex2.c.setText("Reflexen");
        mainReflex2.a.setText("Reflex");
        mainReflex2.a.addActionListener(new a(mainReflex2));
        mainReflex2.b.setText("Reflex + aim");
        mainReflex2.d.setText("Tatrik Karamehmedovi\u0107");
        mainReflex2.d.setToolTipText("");
        GroupLayout groupLayout = new GroupLayout(mainReflex2.getContentPane());
        mainReflex2.getContentPane().setLayout(groupLayout);
        GroupLayout groupLayout2 = groupLayout;
        groupLayout2.setHorizontalGroup(groupLayout2.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, groupLayout.createSequentialGroup().addContainerGap(-1, 32767).addComponent(mainReflex2.d).addContainerGap()).addGroup(groupLayout.createSequentialGroup().addGap(48, 48, 48).addComponent(mainReflex2.a).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 101, 32767).addComponent(mainReflex2.b).addGap(52, 52, 52)).addGroup(groupLayout.createSequentialGroup().addGap(127, 127, 127).addComponent(mainReflex2.c, -2, 81, -2).addContainerGap(-1, 32767)));
        GroupLayout groupLayout3 = groupLayout;
        groupLayout3.setVerticalGroup(groupLayout3.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addGap(24, 24, 24).addComponent(mainReflex2.c).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 62, 32767).addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(mainReflex2.a).addComponent(mainReflex2.b)).addGap(62, 62, 62).addComponent(mainReflex2.d).addContainerGap()));
        mainReflex2.pack();
    }

    public static void main(String[] arrobject) {
        try {
            for (UIManager.LookAndFeelInfo lookAndFeelInfo : UIManager.getInstalledLookAndFeels()) {
                if (!"Nimbus".equals(lookAndFeelInfo.getName())) continue;
                UIManager.setLookAndFeel(lookAndFeelInfo.getClassName());
                break;
            }
        }
        catch (ClassNotFoundException classNotFoundException) {
            Logger.getLogger(mainReflex.class.getName()).log(Level.SEVERE, null, classNotFoundException);
        }
        catch (InstantiationException instantiationException) {
            Logger.getLogger(mainReflex.class.getName()).log(Level.SEVERE, null, instantiationException);
        }
        catch (IllegalAccessException illegalAccessException) {
            Logger.getLogger(mainReflex.class.getName()).log(Level.SEVERE, null, illegalAccessException);
        }
        catch (UnsupportedLookAndFeelException unsupportedLookAndFeelException) {
            Logger.getLogger(mainReflex.class.getName()).log(Level.SEVERE, null, unsupportedLookAndFeelException);
        }
        EventQueue.invokeLater(new b());
    }

    static /* synthetic */ void a(mainReflex mainReflex2, ActionEvent actionEvent) {
    }
}