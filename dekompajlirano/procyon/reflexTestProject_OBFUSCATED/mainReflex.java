import java.awt.event.ActionEvent;
import java.awt.EventQueue;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.LayoutStyle;
import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.GroupLayout;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;

// 
// Decompiled by Procyon v0.5.36
// 

public class mainReflex extends JFrame
{
    private JButton a;
    private JButton b;
    private JLabel c;
    private JLabel d;
    
    public mainReflex() {
        this.c = new JLabel();
        this.a = new JButton();
        this.b = new JButton();
        this.d = new JLabel();
        this.setDefaultCloseOperation(3);
        this.c.setFont(new Font("Arial", 0, 18));
        this.c.setText("Reflexen");
        this.a.setText("Reflex");
        this.a.addActionListener(new a(this));
        this.b.setText("Reflex + aim");
        this.d.setText("Tatrik Karamehmedovi\u0107");
        this.d.setToolTipText("");
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        final GroupLayout groupLayout = layout;
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(-1, 32767).addComponent(this.d).addContainerGap()).addGroup(layout.createSequentialGroup().addGap(48, 48, 48).addComponent(this.a).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 101, 32767).addComponent(this.b).addGap(52, 52, 52)).addGroup(layout.createSequentialGroup().addGap(127, 127, 127).addComponent(this.c, -2, 81, -2).addContainerGap(-1, 32767)));
        final GroupLayout groupLayout2 = layout;
        groupLayout2.setVerticalGroup(groupLayout2.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(24, 24, 24).addComponent(this.c).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 62, 32767).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.a).addComponent(this.b)).addGap(62, 62, 62).addComponent(this.d).addContainerGap()));
        this.pack();
    }
    
    public static void main(final String[] array) {
        try {
            UIManager.LookAndFeelInfo[] installedLookAndFeels;
            for (int length = (installedLookAndFeels = UIManager.getInstalledLookAndFeels()).length, i = 0; i < length; ++i) {
                final UIManager.LookAndFeelInfo lookAndFeelInfo = installedLookAndFeels[i];
                if ("Nimbus".equals(lookAndFeelInfo.getName())) {
                    UIManager.setLookAndFeel(lookAndFeelInfo.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(mainReflex.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(mainReflex.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(mainReflex.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(mainReflex.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new b());
    }

	public static void a(mainReflex a2, ActionEvent actionEvent) {
		// TODO Auto-generated method stub
		
	}
}
