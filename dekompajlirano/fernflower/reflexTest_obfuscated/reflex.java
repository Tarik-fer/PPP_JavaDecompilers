import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.Box.Filler;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.Border;

public class reflex extends JFrame {
   private long a;
   private long b;
   private static JButton c;
   private static JPanel d;
   private Filler e = new Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(0, 32767));
   private static JTextArea f;
   private JLabel g = new JLabel();
   private JLabel h = new JLabel();
   private JLabel i = new JLabel();
   private JLabel j;
   private JLabel k = new JLabel();
   private JPanel l = new JPanel();
   private JPanel m = new JPanel();
   private JPanel n;
   private JScrollPane o = new JScrollPane();
   private static JLabel p;
   private static JButton q;
   private static JButton r;
   private JLabel s;

   private long a() {
      return this.b - this.a;
   }

   public reflex() {
      f = new JTextArea();
      this.s = new JLabel();
      this.j = new JLabel();
      p = new JLabel();
      this.n = new JPanel();
      r = new JButton();
      d = new JPanel();
      c = new JButton();
      q = new JButton();
      this.setDefaultCloseOperation(3);
      this.l.setBorder(BorderFactory.createTitledBorder((Border)null, "Upute", 0, 0, new Font("Arial", 0, 18)));
      this.g.setFont(new Font("Arial", 0, 14));
      this.g.setText("Kada se promijeni boja kvadratića klikni te na gumb \"Klikni ovdje\"");
      this.k.setFont(new Font("Arial", 0, 14));
      this.k.setText("Nakon što kliknete \"ponovi\" ponovo kliknite na gumb \"Kreni\"");
      this.i.setFont(new Font("Arial", 0, 14));
      this.i.setText("Kliknite na \"Kreni\" kako bi ste započeli i pomaknite miš na gumb \"Klikni ovdje\"");
      GroupLayout var2 = new GroupLayout(this.l);
      this.l.setLayout(var2);
      var2.setHorizontalGroup(var2.createParallelGroup(Alignment.LEADING).addGroup(var2.createSequentialGroup().addContainerGap().addGroup(var2.createParallelGroup(Alignment.LEADING).addComponent(this.i).addComponent(this.g).addComponent(this.k)).addContainerGap(-1, 32767)));
      var2.setVerticalGroup(var2.createParallelGroup(Alignment.LEADING).addGroup(var2.createSequentialGroup().addContainerGap().addComponent(this.i).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.g).addGap(18, 18, 18).addComponent(this.k).addContainerGap()));
      this.m.setBorder(BorderFactory.createTitledBorder((Border)null, "Vrijeme", 0, 0, new Font("Arial", 0, 18)));
      this.h.setFont(new Font("Tahoma", 0, 12));
      this.h.setText("[ms]");
      f.setEditable(false);
      f.setColumns(20);
      f.setFont(new Font("Arial", 0, 14));
      f.setRows(5);
      this.o.setViewportView(f);
      this.s.setFont(new Font("Arial", 0, 14));
      this.s.setText("Vrijeme:");
      this.j.setFont(new Font("Arial", 0, 14));
      this.j.setText("Povijest rezultata:");
      p.setFont(new Font("Arial", 0, 14));
      var2 = new GroupLayout(this.m);
      this.m.setLayout(var2);
      var2.setHorizontalGroup(var2.createParallelGroup(Alignment.LEADING).addGroup(var2.createSequentialGroup().addContainerGap().addGroup(var2.createParallelGroup(Alignment.LEADING).addComponent(this.j).addComponent(this.o, -2, -1, -2).addGroup(var2.createSequentialGroup().addComponent(this.s).addGap(2, 2, 2).addComponent(p, -2, 48, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.h))).addContainerGap()));
      var2.setVerticalGroup(var2.createParallelGroup(Alignment.LEADING).addGroup(var2.createSequentialGroup().addContainerGap().addGroup(var2.createParallelGroup(Alignment.TRAILING).addComponent(this.s, Alignment.LEADING).addGroup(var2.createParallelGroup(Alignment.LEADING).addComponent(p, -2, 17, -2).addComponent(this.h))).addGap(18, 18, 18).addComponent(this.j).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.o, -2, 156, -2).addContainerGap()));
      this.n.setBorder(BorderFactory.createTitledBorder((Border)null, "Test", 0, 0, new Font("Arial", 0, 18)));
      r.setText("Ponovi");
      r.addActionListener(new c(this));
      d.setBackground(new Color(0, 0, 255));
      d.addPropertyChangeListener(new d(this));
      var2 = new GroupLayout(d);
      d.setLayout(var2);
      var2.setHorizontalGroup(var2.createParallelGroup(Alignment.LEADING).addGap(0, 100, 32767));
      var2.setVerticalGroup(var2.createParallelGroup(Alignment.LEADING).addGap(0, 100, 32767));
      c.setText("Kreni");
      c.addActionListener(new e(this));
      q.setText("Klikni ovdje");
      q.setToolTipText("");
      q.setActionCommand("beg");
      q.addActionListener(new f(this));
      var2 = new GroupLayout(this.n);
      this.n.setLayout(var2);
      var2.setHorizontalGroup(var2.createParallelGroup(Alignment.LEADING).addGroup(var2.createSequentialGroup().addContainerGap().addGroup(var2.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, var2.createSequentialGroup().addGroup(var2.createParallelGroup(Alignment.TRAILING).addComponent(c).addComponent(r)).addGap(18, 18, 18).addComponent(d, -2, -1, -2)).addComponent(q, Alignment.TRAILING, -2, 140, -2)).addContainerGap()));
      var2.setVerticalGroup(var2.createParallelGroup(Alignment.LEADING).addGroup(var2.createSequentialGroup().addContainerGap().addGroup(var2.createParallelGroup(Alignment.LEADING).addGroup(var2.createSequentialGroup().addComponent(d, -2, -1, -2).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(q, -2, 56, -2)).addGroup(var2.createSequentialGroup().addComponent(c).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(r))).addContainerGap()));
      var2 = new GroupLayout(this.getContentPane());
      this.getContentPane().setLayout(var2);
      var2.setHorizontalGroup(var2.createParallelGroup(Alignment.LEADING).addGroup(var2.createSequentialGroup().addContainerGap().addGroup(var2.createParallelGroup(Alignment.LEADING).addGroup(var2.createSequentialGroup().addComponent(this.m, -2, -1, -2).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addComponent(this.n, -2, -1, -2)).addComponent(this.l, -1, -1, 32767)).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.e, -2, 10, -2).addGap(22, 22, 22)));
      var2.setVerticalGroup(var2.createParallelGroup(Alignment.LEADING).addGroup(var2.createSequentialGroup().addGap(46, 46, 46).addComponent(this.e, -2, 155, -2).addContainerGap(-1, 32767)).addGroup(var2.createSequentialGroup().addContainerGap(-1, 32767).addComponent(this.l, -2, -1, -2).addGap(18, 18, 18).addGroup(var2.createParallelGroup(Alignment.LEADING).addComponent(this.m, -2, -1, -2).addComponent(this.n, -2, -1, -2)).addGap(18, 18, 18)));
      this.pack();
      q.setEnabled(false);
      r.setEnabled(false);
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
         Logger.getLogger(reflex.class.getName()).log(Level.SEVERE, (String)null, var4);
      } catch (InstantiationException var5) {
         Logger.getLogger(reflex.class.getName()).log(Level.SEVERE, (String)null, var5);
      } catch (IllegalAccessException var6) {
         Logger.getLogger(reflex.class.getName()).log(Level.SEVERE, (String)null, var6);
      } catch (UnsupportedLookAndFeelException var7) {
         Logger.getLogger(reflex.class.getName()).log(Level.SEVERE, (String)null, var7);
      }

      EventQueue.invokeLater(new g());
   }

   // $FF: synthetic method
   static void a(reflex var0, ActionEvent var1) {
      d.setBackground(Color.BLUE);
      p.setText("");
      var0.a = 0L;
      var0.b = 0L;
      q.setEnabled(false);
      r.setEnabled(false);
      c.setEnabled(true);
   }

   // $FF: synthetic method
   static void a(reflex var0, PropertyChangeEvent var1) {
   }

   // $FF: synthetic method
   static void b(reflex var0, ActionEvent var1) {
      Random var3 = new Random();

      try {
         TimeUnit.SECONDS.sleep((long)(var3.nextInt(7) + 1));
      } catch (InterruptedException var2) {
         Logger.getLogger(reflex.class.getName()).log(Level.SEVERE, (String)null, var2);
      }

      d.setBackground(Color.RED);
      q.setEnabled(true);
      var0.a = System.currentTimeMillis();
      long var10000 = var0.a;
   }

   // $FF: synthetic method
   static void c(reflex var0, ActionEvent var1) {
      reflex var2;
      (var2 = var0).b = System.currentTimeMillis();
      long var10000 = var2.b;
      if (var0.a() > 1L) {
         p.setText(String.valueOf(var0.a()));
         f.append(var0.a() + "\n");
         r.setEnabled(true);
         c.setEnabled(false);
         q.setEnabled(false);
      }

   }
}
