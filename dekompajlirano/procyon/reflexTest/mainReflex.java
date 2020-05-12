import java.awt.EventQueue;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.LayoutStyle;
import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.GroupLayout;
import java.awt.event.ActionEvent;
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
    private JButton jButton1;
    private JButton jButton2;
    private JLabel jLabel1;
    private JLabel jLabel2;
    
    public mainReflex() {
        this.initComponents();
    }
    
    private void initComponents() {
        this.jLabel1 = new JLabel();
        this.jButton1 = new JButton();
        this.jButton2 = new JButton();
        this.jLabel2 = new JLabel();
        this.setDefaultCloseOperation(3);
        this.jLabel1.setFont(new Font("Arial", 0, 18));
        this.jLabel1.setText("Reflexen");
        this.jButton1.setText("Reflex");
        this.jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                mainReflex.this.jButton1ActionPerformed(evt);
            }
        });
        this.jButton2.setText("Reflex + aim");
        this.jLabel2.setText("Tatrik Karamehmedovi\u0107");
        this.jLabel2.setToolTipText("");
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(-1, 32767).addComponent(this.jLabel2).addContainerGap()).addGroup(layout.createSequentialGroup().addGap(48, 48, 48).addComponent(this.jButton1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 101, 32767).addComponent(this.jButton2).addGap(52, 52, 52)).addGroup(layout.createSequentialGroup().addGap(127, 127, 127).addComponent(this.jLabel1, -2, 81, -2).addContainerGap(-1, 32767)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(24, 24, 24).addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 62, 32767).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton1).addComponent(this.jButton2)).addGap(62, 62, 62).addComponent(this.jLabel2).addContainerGap()));
        this.pack();
    }
    
    private void jButton1ActionPerformed(final ActionEvent evt) {
    }
    
    public static void main(final String[] args) {
        try {
            for (final UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
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
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new mainReflex().setVisible(true);
            }
        });
    }
}
