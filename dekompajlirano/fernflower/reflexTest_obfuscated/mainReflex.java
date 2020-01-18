import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
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
   private JButton a = new JButton();
   private JButton b = new JButton();
   private JLabel c = new JLabel();
   private JLabel d = new JLabel();

   public mainReflex() {
      this.setDefaultCloseOperation(3);
      this.c.setFont(new Font("Arial", 0, 18));
      this.c.setText("Reflexen");
      this.a.setText("Reflex");
      this.a.addActionListener(new a(this));
      this.b.setText("Reflex + aim");
      this.d.setText("Tatrik Karamehmedović");
      this.d.setToolTipText("");
      GroupLayout var2 = new GroupLayout(this.getContentPane());
      this.getContentPane().setLayout(var2);
      var2.setHorizontalGroup(var2.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, var2.createSequentialGroup().addContainerGap(-1, 32767).addComponent(this.d).addContainerGap()).addGroup(var2.createSequentialGroup().addGap(48, 48, 48).addComponent(this.a).addPreferredGap(ComponentPlacement.RELATED, 101, 32767).addComponent(this.b).addGap(52, 52, 52)).addGroup(var2.createSequentialGroup().addGap(127, 127, 127).addComponent(this.c, -2, 81, -2).addContainerGap(-1, 32767)));
      var2.setVerticalGroup(var2.createParallelGroup(Alignment.LEADING).addGroup(var2.createSequentialGroup().addGap(24, 24, 24).addComponent(this.c).addPreferredGap(ComponentPlacement.RELATED, 62, 32767).addGroup(var2.createParallelGroup(Alignment.BASELINE).addComponent(this.a).addComponent(this.b)).addGap(62, 62, 62).addComponent(this.d).addContainerGap()));
      this.pack();
   }

   public static void main(String[] var0) {
      try {
         LookAndFeelInfo[] var8;
         int var1 = (var8 = UIManager.getInstalledLookAndFeels()).length;

         for(int var2 = 0; var2 < var1; ++var2) {
            LookAndFeelInfo var3 = var8[var2];
            if ("Nimbus".equals(var3.getName())) {
               UIManager.setLookAndFeel(var3.getClassName());
               break;
            }
         }
      } catch (ClassNotFoundException var4) {
         Logger.getLogger(mainReflex.class.getName()).log(Level.SEVERE, (String)null, var4);
      } catch (InstantiationException var5) {
         Logger.getLogger(mainReflex.class.getName()).log(Level.SEVERE, (String)null, var5);
      } catch (IllegalAccessException var6) {
         Logger.getLogger(mainReflex.class.getName()).log(Level.SEVERE, (String)null, var6);
      } catch (UnsupportedLookAndFeelException var7) {
         Logger.getLogger(mainReflex.class.getName()).log(Level.SEVERE, (String)null, var7);
      }

      EventQueue.invokeLater(new b());
   }

   // $FF: synthetic method
   static void a(mainReflex var0, ActionEvent var1) {
   }
}
