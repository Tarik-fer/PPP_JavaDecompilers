/*
 * Decompiled with CFR 0.148.
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

public class reflex
extends JFrame {
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

    private long a() {
        return this.b - this.a;
    }

    public reflex() {
        reflex reflex2 = this;
        this.e = new Box.Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(0, 32767));
        reflex2.l = new JPanel();
        reflex2.g = new JLabel();
        reflex2.k = new JLabel();
        reflex2.i = new JLabel();
        reflex2.m = new JPanel();
        reflex2.h = new JLabel();
        reflex2.o = new JScrollPane();
        f = new JTextArea();
        reflex2.s = new JLabel();
        reflex2.j = new JLabel();
        p = new JLabel();
        reflex2.n = new JPanel();
        r = new JButton();
        d = new JPanel();
        c = new JButton();
        q = new JButton();
        reflex2.setDefaultCloseOperation(3);
        reflex2.l.setBorder(BorderFactory.createTitledBorder(null, "Upute", 0, 0, new Font("Arial", 0, 18)));
        reflex2.g.setFont(new Font("Arial", 0, 14));
        reflex2.g.setText("Kada se promijeni boja kvadrati\u0107a klikni te na gumb \"Klikni ovdje\"");
        reflex2.k.setFont(new Font("Arial", 0, 14));
        reflex2.k.setText("Nakon \u0161to kliknete \"ponovi\" ponovo kliknite na gumb \"Kreni\"");
        reflex2.i.setFont(new Font("Arial", 0, 14));
        reflex2.i.setText("Kliknite na \"Kreni\" kako bi ste zapo\u010deli i pomaknite mi\u0161 na gumb \"Klikni ovdje\"");
        GroupLayout groupLayout = new GroupLayout(reflex2.l);
        reflex2.l.setLayout(groupLayout);
        GroupLayout groupLayout2 = groupLayout;
        groupLayout2.setHorizontalGroup(groupLayout2.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(reflex2.i).addComponent(reflex2.g).addComponent(reflex2.k)).addContainerGap(-1, 32767)));
        GroupLayout groupLayout3 = groupLayout;
        groupLayout3.setVerticalGroup(groupLayout3.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(reflex2.i).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(reflex2.g).addGap(18, 18, 18).addComponent(reflex2.k).addContainerGap()));
        reflex2.m.setBorder(BorderFactory.createTitledBorder(null, "Vrijeme", 0, 0, new Font("Arial", 0, 18)));
        reflex2.h.setFont(new Font("Tahoma", 0, 12));
        reflex2.h.setText("[ms]");
        f.setEditable(false);
        f.setColumns(20);
        f.setFont(new Font("Arial", 0, 14));
        f.setRows(5);
        reflex2.o.setViewportView(f);
        reflex2.s.setFont(new Font("Arial", 0, 14));
        reflex2.s.setText("Vrijeme:");
        reflex2.j.setFont(new Font("Arial", 0, 14));
        reflex2.j.setText("Povijest rezultata:");
        p.setFont(new Font("Arial", 0, 14));
        groupLayout = new GroupLayout(reflex2.m);
        reflex2.m.setLayout(groupLayout);
        GroupLayout groupLayout4 = groupLayout;
        groupLayout4.setHorizontalGroup(groupLayout4.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(reflex2.j).addComponent(reflex2.o, -2, -1, -2).addGroup(groupLayout.createSequentialGroup().addComponent(reflex2.s).addGap(2, 2, 2).addComponent(p, -2, 48, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(reflex2.h))).addContainerGap()));
        GroupLayout groupLayout5 = groupLayout;
        groupLayout5.setVerticalGroup(groupLayout5.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(reflex2.s, GroupLayout.Alignment.LEADING).addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(p, -2, 17, -2).addComponent(reflex2.h))).addGap(18, 18, 18).addComponent(reflex2.j).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(reflex2.o, -2, 156, -2).addContainerGap()));
        reflex2.n.setBorder(BorderFactory.createTitledBorder(null, "Test", 0, 0, new Font("Arial", 0, 18)));
        r.setText("Ponovi");
        r.addActionListener(new c(reflex2));
        d.setBackground(new Color(0, 0, 255));
        d.addPropertyChangeListener(new d(reflex2));
        groupLayout = new GroupLayout(d);
        d.setLayout(groupLayout);
        GroupLayout groupLayout6 = groupLayout;
        groupLayout6.setHorizontalGroup(groupLayout6.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 100, 32767));
        GroupLayout groupLayout7 = groupLayout;
        groupLayout7.setVerticalGroup(groupLayout7.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 100, 32767));
        c.setText("Kreni");
        c.addActionListener(new e(reflex2));
        q.setText("Klikni ovdje");
        q.setToolTipText("");
        q.setActionCommand("beg");
        q.addActionListener(new f(reflex2));
        groupLayout = new GroupLayout(reflex2.n);
        reflex2.n.setLayout(groupLayout);
        GroupLayout groupLayout8 = groupLayout;
        groupLayout8.setHorizontalGroup(groupLayout8.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, groupLayout.createSequentialGroup().addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(c).addComponent(r)).addGap(18, 18, 18).addComponent(d, -2, -1, -2)).addComponent(q, GroupLayout.Alignment.TRAILING, -2, 140, -2)).addContainerGap()));
        GroupLayout groupLayout9 = groupLayout;
        groupLayout9.setVerticalGroup(groupLayout9.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addComponent(d, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(q, -2, 56, -2)).addGroup(groupLayout.createSequentialGroup().addComponent(c).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(r))).addContainerGap()));
        groupLayout = new GroupLayout(reflex2.getContentPane());
        reflex2.getContentPane().setLayout(groupLayout);
        GroupLayout groupLayout10 = groupLayout;
        groupLayout10.setHorizontalGroup(groupLayout10.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addComponent(reflex2.m, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(reflex2.n, -2, -1, -2)).addComponent(reflex2.l, -1, -1, 32767)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(reflex2.e, -2, 10, -2).addGap(22, 22, 22)));
        GroupLayout groupLayout11 = groupLayout;
        groupLayout11.setVerticalGroup(groupLayout11.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addGap(46, 46, 46).addComponent(reflex2.e, -2, 155, -2).addContainerGap(-1, 32767)).addGroup(groupLayout.createSequentialGroup().addContainerGap(-1, 32767).addComponent(reflex2.l, -2, -1, -2).addGap(18, 18, 18).addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(reflex2.m, -2, -1, -2).addComponent(reflex2.n, -2, -1, -2)).addGap(18, 18, 18)));
        reflex2.pack();
        q.setEnabled(false);
        r.setEnabled(false);
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
            Logger.getLogger(reflex.class.getName()).log(Level.SEVERE, null, classNotFoundException);
        }
        catch (InstantiationException instantiationException) {
            Logger.getLogger(reflex.class.getName()).log(Level.SEVERE, null, instantiationException);
        }
        catch (IllegalAccessException illegalAccessException) {
            Logger.getLogger(reflex.class.getName()).log(Level.SEVERE, null, illegalAccessException);
        }
        catch (UnsupportedLookAndFeelException unsupportedLookAndFeelException) {
            Logger.getLogger(reflex.class.getName()).log(Level.SEVERE, null, unsupportedLookAndFeelException);
        }
        EventQueue.invokeLater(new g());
    }

    static /* synthetic */ void a(reflex reflex2, ActionEvent actionEvent) {
        d.setBackground(Color.BLUE);
        p.setText("");
        reflex2.a = 0L;
        reflex2.b = 0L;
        q.setEnabled(false);
        r.setEnabled(false);
        c.setEnabled(true);
    }

    static /* synthetic */ void a(reflex reflex2, PropertyChangeEvent propertyChangeEvent) {
    }

    static /* synthetic */ void b(reflex reflex2, ActionEvent serializable) {
        Random serializable = new Random();
        try {
            TimeUnit.SECONDS.sleep(serializable.nextInt(7) + 1);
        }
        catch (InterruptedException interruptedException) {
            Logger.getLogger(reflex.class.getName()).log(Level.SEVERE, null, interruptedException);
        }
        d.setBackground(Color.RED);
        q.setEnabled(true);
        v0.a = System.currentTimeMillis();
    }

    static /* synthetic */ void c(reflex reflex2, ActionEvent serializable) {
        serializable = reflex2;
        reflex2.b = System.currentTimeMillis();
        if (reflex2.a() > 1L) {
            p.setText(String.valueOf(reflex2.a()));
            f.append(String.valueOf(reflex2.a()) + "\n");
            r.setEnabled(true);
            c.setEnabled(false);
            q.setEnabled(false);
        }
    }
}