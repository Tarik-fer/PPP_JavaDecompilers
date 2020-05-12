import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager.LookAndFeelInfo;

public class mainReflex extends JFrame {
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
         public void actionPerformed(ActionEvent evt) {
            mainReflex.this.jButton1ActionPerformed(evt);
         }
      });
      this.jButton2.setText("Reflex + aim");
      this.jLabel2.setText("Tatrik Karamehmedović");
      this.jLabel2.setToolTipText("");
      GroupLayout layout = new GroupLayout(this.getContentPane());
      this.getContentPane().setLayout(layout);
      layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(-1, 32767).addComponent(this.jLabel2).addContainerGap()).addGroup(layout.createSequentialGroup().addGap(48, 48, 48).addComponent(this.jButton1).addPreferredGap(ComponentPlacement.RELATED, 101, 32767).addComponent(this.jButton2).addGap(52, 52, 52)).addGroup(layout.createSequentialGroup().addGap(127, 127, 127).addComponent(this.jLabel1, -2, 81, -2).addContainerGap(-1, 32767)));
      layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(24, 24, 24).addComponent(this.jLabel1).addPreferredGap(ComponentPlacement.RELATED, 62, 32767).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jButton1).addComponent(this.jButton2)).addGap(62, 62, 62).addComponent(this.jLabel2).addContainerGap()));
      this.pack();
   }

   private void jButton1ActionPerformed(ActionEvent evt) {
   }

   public static void main(String[] args) {
      try {
         LookAndFeelInfo[] var1 = UIManager.getInstalledLookAndFeels();
         int var2 = var1.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            LookAndFeelInfo info = var1[var3];
            if ("Nimbus".equals(info.getName())) {
               UIManager.setLookAndFeel(info.getClassName());
               break;
            }
         }
      } catch (ClassNotFoundException var5) {
         Logger.getLogger(mainReflex.class.getName()).log(Level.SEVERE, (String)null, var5);
      } catch (InstantiationException var6) {
         Logger.getLogger(mainReflex.class.getName()).log(Level.SEVERE, (String)null, var6);
      } catch (IllegalAccessException var7) {
         Logger.getLogger(mainReflex.class.getName()).log(Level.SEVERE, (String)null, var7);
      } catch (UnsupportedLookAndFeelException var8) {
         Logger.getLogger(mainReflex.class.getName()).log(Level.SEVERE, (String)null, var8);
      }

      EventQueue.invokeLater(new Runnable() {
         public void run() {
            (new mainReflex()).setVisible(true);
         }
      });
   }
}
