import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class mainReflex extends JFrame {
  private JButton a;
  
  private JButton b;
  
  private JLabel c;
  
  private JLabel d;
  
  public mainReflex() {
    mainReflex mainReflex1;
    (mainReflex1 = this).c = new JLabel();
    mainReflex1.a = new JButton();
    mainReflex1.b = new JButton();
    mainReflex1.d = new JLabel();
    mainReflex1.setDefaultCloseOperation(3);
    mainReflex1.c.setFont(new Font("Arial", 0, 18));
    mainReflex1.c.setText("Reflexen");
    mainReflex1.a.setText("Reflex");
    mainReflex1.a.addActionListener(new a(mainReflex1));
    mainReflex1.b.setText("Reflex + aim");
    mainReflex1.d.setText("Tatrik Karamehmedović");
    mainReflex1.d.setToolTipText("");
    GroupLayout groupLayout = new GroupLayout(mainReflex1.getContentPane());
    mainReflex1.getContentPane().setLayout(groupLayout);
    groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, groupLayout.createSequentialGroup().addContainerGap(-1, 32767).addComponent(mainReflex1.d).addContainerGap()).addGroup(groupLayout.createSequentialGroup().addGap(48, 48, 48).addComponent(mainReflex1.a).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 101, 32767).addComponent(mainReflex1.b).addGap(52, 52, 52)).addGroup(groupLayout.createSequentialGroup().addGap(127, 127, 127).addComponent(mainReflex1.c, -2, 81, -2).addContainerGap(-1, 32767)));
    groupLayout.setVerticalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addGap(24, 24, 24).addComponent(mainReflex1.c).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 62, 32767).addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(mainReflex1.a).addComponent(mainReflex1.b)).addGap(62, 62, 62).addComponent(mainReflex1.d).addContainerGap()));
    mainReflex1.pack();
  }
  
  public static void main(String[] paramArrayOfString) {
    try {
      UIManager.LookAndFeelInfo[] arrayOfLookAndFeelInfo;
      int i = (arrayOfLookAndFeelInfo = UIManager.getInstalledLookAndFeels()).length;
      for (byte b1 = 0; b1 < i; b1++) {
        UIManager.LookAndFeelInfo lookAndFeelInfo = arrayOfLookAndFeelInfo[b1];
        if ("Nimbus".equals(lookAndFeelInfo.getName())) {
          UIManager.setLookAndFeel(lookAndFeelInfo.getClassName());
          break;
        } 
      } 
    } catch (ClassNotFoundException classNotFoundException) {
      Logger.getLogger(mainReflex.class.getName()).log(Level.SEVERE, null, classNotFoundException);
    } catch (InstantiationException instantiationException) {
      Logger.getLogger(mainReflex.class.getName()).log(Level.SEVERE, null, instantiationException);
    } catch (IllegalAccessException illegalAccessException) {
      Logger.getLogger(mainReflex.class.getName()).log(Level.SEVERE, null, illegalAccessException);
    } catch (UnsupportedLookAndFeelException unsupportedLookAndFeelException) {
      Logger.getLogger(mainReflex.class.getName()).log(Level.SEVERE, null, unsupportedLookAndFeelException);
    } 
    EventQueue.invokeLater(new b());
  }
}


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\reflexTestObfuscated\reflexTestObfuscated.jar!\mainReflex.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */