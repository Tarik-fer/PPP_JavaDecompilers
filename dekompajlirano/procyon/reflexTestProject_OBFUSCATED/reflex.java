import java.util.concurrent.TimeUnit;
import java.util.Random;
import java.beans.PropertyChangeEvent;
import java.awt.event.ActionEvent;
import java.awt.EventQueue;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import java.beans.PropertyChangeListener;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.LayoutStyle;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.GroupLayout;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;

// 
// Decompiled by Procyon v0.5.36
// 

public class reflex extends JFrame
{
    private long a;
    private long b;
    private static JButton c;
    private static JPanel d;
    private Box.Filler e;
    private static JTextArea f;
    private JLabel g;
    private JLabel h;
    private JLabel i;
    private JLabel j;
    private JLabel k;
    private JPanel l;
    private JPanel m;
    private JPanel n;
    private JScrollPane o;
    private static JLabel p;
    private static JButton q;
    private static JButton r;
    private JLabel s;
    
    long a() {
        return this.b - this.a;
    }
    
    public reflex() {
        this.e = new Box.Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(0, 32767));
        this.l = new JPanel();
        this.g = new JLabel();
        this.k = new JLabel();
        this.i = new JLabel();
        this.m = new JPanel();
        this.h = new JLabel();
        this.o = new JScrollPane();
        reflex.f = new JTextArea();
        this.s = new JLabel();
        this.j = new JLabel();
        reflex.p = new JLabel();
        this.n = new JPanel();
        reflex.r = new JButton();
        reflex.d = new JPanel();
        reflex.c = new JButton();
        reflex.q = new JButton();
        this.setDefaultCloseOperation(3);
        this.l.setBorder(BorderFactory.createTitledBorder(null, "Upute", 0, 0, new Font("Arial", 0, 18)));
        this.g.setFont(new Font("Arial", 0, 14));
        this.g.setText("Kada se promijeni boja kvadrati\u0107a klikni te na gumb \"Klikni ovdje\"");
        this.k.setFont(new Font("Arial", 0, 14));
        this.k.setText("Nakon \u0161to kliknete \"ponovi\" ponovo kliknite na gumb \"Kreni\"");
        this.i.setFont(new Font("Arial", 0, 14));
        this.i.setText("Kliknite na \"Kreni\" kako bi ste zapo\u010deli i pomaknite mi\u0161 na gumb \"Klikni ovdje\"");
        final GroupLayout layout = new GroupLayout(this.l);
        this.l.setLayout(layout);
        final GroupLayout groupLayout = layout;
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.i).addComponent(this.g).addComponent(this.k)).addContainerGap(-1, 32767)));
        final GroupLayout groupLayout2 = layout;
        groupLayout2.setVerticalGroup(groupLayout2.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.i).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.g).addGap(18, 18, 18).addComponent(this.k).addContainerGap()));
        this.m.setBorder(BorderFactory.createTitledBorder(null, "Vrijeme", 0, 0, new Font("Arial", 0, 18)));
        this.h.setFont(new Font("Tahoma", 0, 12));
        this.h.setText("[ms]");
        reflex.f.setEditable(false);
        reflex.f.setColumns(20);
        reflex.f.setFont(new Font("Arial", 0, 14));
        reflex.f.setRows(5);
        this.o.setViewportView(reflex.f);
        this.s.setFont(new Font("Arial", 0, 14));
        this.s.setText("Vrijeme:");
        this.j.setFont(new Font("Arial", 0, 14));
        this.j.setText("Povijest rezultata:");
        reflex.p.setFont(new Font("Arial", 0, 14));
        final GroupLayout layout2 = new GroupLayout(this.m);
        this.m.setLayout(layout2);
        final GroupLayout groupLayout3 = layout2;
        groupLayout3.setHorizontalGroup(groupLayout3.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout2.createSequentialGroup().addContainerGap().addGroup(layout2.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.j).addComponent(this.o, -2, -1, -2).addGroup(layout2.createSequentialGroup().addComponent(this.s).addGap(2, 2, 2).addComponent(reflex.p, -2, 48, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.h))).addContainerGap()));
        final GroupLayout groupLayout4 = layout2;
        groupLayout4.setVerticalGroup(groupLayout4.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout2.createSequentialGroup().addContainerGap().addGroup(layout2.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.s, GroupLayout.Alignment.LEADING).addGroup(layout2.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(reflex.p, -2, 17, -2).addComponent(this.h))).addGap(18, 18, 18).addComponent(this.j).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.o, -2, 156, -2).addContainerGap()));
        this.n.setBorder(BorderFactory.createTitledBorder(null, "Test", 0, 0, new Font("Arial", 0, 18)));
        reflex.r.setText("Ponovi");
        reflex.r.addActionListener(new c(this));
        reflex.d.setBackground(new Color(0, 0, 255));
        reflex.d.addPropertyChangeListener(new d(this));
        final GroupLayout layout3 = new GroupLayout(reflex.d);
        reflex.d.setLayout(layout3);
        final GroupLayout groupLayout5 = layout3;
        groupLayout5.setHorizontalGroup(groupLayout5.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 100, 32767));
        final GroupLayout groupLayout6 = layout3;
        groupLayout6.setVerticalGroup(groupLayout6.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 100, 32767));
        reflex.c.setText("Kreni");
        reflex.c.addActionListener(new e(this));
        reflex.q.setText("Klikni ovdje");
        reflex.q.setToolTipText("");
        reflex.q.setActionCommand("beg");
        reflex.q.addActionListener(new f(this));
        final GroupLayout layout4 = new GroupLayout(this.n);
        this.n.setLayout(layout4);
        final GroupLayout groupLayout7 = layout4;
        groupLayout7.setHorizontalGroup(groupLayout7.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout4.createSequentialGroup().addContainerGap().addGroup(layout4.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout4.createSequentialGroup().addGroup(layout4.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(reflex.c).addComponent(reflex.r)).addGap(18, 18, 18).addComponent(reflex.d, -2, -1, -2)).addComponent(reflex.q, GroupLayout.Alignment.TRAILING, -2, 140, -2)).addContainerGap()));
        final GroupLayout groupLayout8 = layout4;
        groupLayout8.setVerticalGroup(groupLayout8.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout4.createSequentialGroup().addContainerGap().addGroup(layout4.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout4.createSequentialGroup().addComponent(reflex.d, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(reflex.q, -2, 56, -2)).addGroup(layout4.createSequentialGroup().addComponent(reflex.c).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(reflex.r))).addContainerGap()));
        final GroupLayout layout5 = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout5);
        final GroupLayout groupLayout9 = layout5;
        groupLayout9.setHorizontalGroup(groupLayout9.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout5.createSequentialGroup().addContainerGap().addGroup(layout5.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout5.createSequentialGroup().addComponent(this.m, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.n, -2, -1, -2)).addComponent(this.l, -1, -1, 32767)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.e, -2, 10, -2).addGap(22, 22, 22)));
        final GroupLayout groupLayout10 = layout5;
        groupLayout10.setVerticalGroup(groupLayout10.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout5.createSequentialGroup().addGap(46, 46, 46).addComponent(this.e, -2, 155, -2).addContainerGap(-1, 32767)).addGroup(layout5.createSequentialGroup().addContainerGap(-1, 32767).addComponent(this.l, -2, -1, -2).addGap(18, 18, 18).addGroup(layout5.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.m, -2, -1, -2).addComponent(this.n, -2, -1, -2)).addGap(18, 18, 18)));
        this.pack();
        reflex.q.setEnabled(false);
        reflex.r.setEnabled(false);
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
            Logger.getLogger(reflex.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(reflex.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(reflex.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(reflex.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new g());
    }

	public static void c(reflex a2, ActionEvent actionEvent) {
		// TODO Auto-generated method stub
		
	}

	public static void b(reflex a2, ActionEvent actionEvent) {
		// TODO Auto-generated method stub
		
	}

	public static void a(reflex a2, ActionEvent actionEvent) {
		// TODO Auto-generated method stub
		
	}

	public static void a(reflex a2, PropertyChangeEvent propertyChangeEvent) {
		// TODO Auto-generated method stub
		
	}
}
