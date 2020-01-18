/*
 * Decompiled with CFR 0.145.
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
    public long startTime;
    public long endTime;
    public static JButton begin;
    public static JPanel colorBox;
    private Box.Filler filler2;
    public static JTextArea history;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JScrollPane jScrollPane1;
    public static JLabel react;
    public static JButton reactBut;
    public static JButton reset;
    private JLabel time;

    public long getDuration() {
        return this.endTime - this.startTime;
    }

    public long start() {
        this.startTime = System.currentTimeMillis();
        return this.startTime;
    }

    public long stop() {
        this.endTime = System.currentTimeMillis();
        return this.endTime;
    }

    public reflex() throws InterruptedException {
        this.initComponents();
        reactBut.setEnabled(false);
        reset.setEnabled(false);
    }

    private void initComponents() {
        this.filler2 = new Box.Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(0, 32767));
        this.jPanel1 = new JPanel();
        this.jLabel1 = new JLabel();
        this.jLabel5 = new JLabel();
        this.jLabel3 = new JLabel();
        this.jPanel2 = new JPanel();
        this.jLabel2 = new JLabel();
        this.jScrollPane1 = new JScrollPane();
        history = new JTextArea();
        this.time = new JLabel();
        this.jLabel4 = new JLabel();
        react = new JLabel();
        this.jPanel3 = new JPanel();
        reset = new JButton();
        colorBox = new JPanel();
        begin = new JButton();
        reactBut = new JButton();
        this.setDefaultCloseOperation(3);
        this.jPanel1.setBorder(BorderFactory.createTitledBorder(null, "Upute", 0, 0, new Font("Arial", 0, 18)));
        this.jLabel1.setFont(new Font("Arial", 0, 14));
        this.jLabel1.setText("Kada se promijeni boja kvadrati\u0107a klikni te na gumb \"Klikni ovdje\"");
        this.jLabel5.setFont(new Font("Arial", 0, 14));
        this.jLabel5.setText("Nakon \u0161to kliknete \"ponovi\" ponovo kliknite na gumb \"Kreni\"");
        this.jLabel3.setFont(new Font("Arial", 0, 14));
        this.jLabel3.setText("Kliknite na \"Kreni\" kako bi ste zapo\u010deli i pomaknite mi\u0161 na gumb \"Klikni ovdje\"");
        GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel3).addComponent(this.jLabel1).addComponent(this.jLabel5)).addContainerGap(-1, 32767)));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel3).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jLabel1).addGap(18, 18, 18).addComponent(this.jLabel5).addContainerGap()));
        this.jPanel2.setBorder(BorderFactory.createTitledBorder(null, "Vrijeme", 0, 0, new Font("Arial", 0, 18)));
        this.jLabel2.setFont(new Font("Tahoma", 0, 12));
        this.jLabel2.setText("[ms]");
        history.setEditable(false);
        history.setColumns(20);
        history.setFont(new Font("Arial", 0, 14));
        history.setRows(5);
        this.jScrollPane1.setViewportView(history);
        this.time.setFont(new Font("Arial", 0, 14));
        this.time.setText("Vrijeme:");
        this.jLabel4.setFont(new Font("Arial", 0, 14));
        this.jLabel4.setText("Povijest rezultata:");
        react.setFont(new Font("Arial", 0, 14));
        GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
        this.jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel4).addComponent(this.jScrollPane1, -2, -1, -2).addGroup(jPanel2Layout.createSequentialGroup().addComponent(this.time).addGap(2, 2, 2).addComponent(react, -2, 48, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel2))).addContainerGap()));
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.time, GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(react, -2, 17, -2).addComponent(this.jLabel2))).addGap(18, 18, 18).addComponent(this.jLabel4).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jScrollPane1, -2, 156, -2).addContainerGap()));
        this.jPanel3.setBorder(BorderFactory.createTitledBorder(null, "Test", 0, 0, new Font("Arial", 0, 18)));
        reset.setText("Ponovi");
        reset.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                reflex.this.resetActionPerformed(evt);
            }
        });
        colorBox.setBackground(new Color(0, 0, 255));
        colorBox.addPropertyChangeListener(new PropertyChangeListener(){

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                reflex.this.colorBoxPropertyChange(evt);
            }
        });
        GroupLayout colorBoxLayout = new GroupLayout(colorBox);
        colorBox.setLayout(colorBoxLayout);
        colorBoxLayout.setHorizontalGroup(colorBoxLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 100, 32767));
        colorBoxLayout.setVerticalGroup(colorBoxLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 100, 32767));
        begin.setText("Kreni");
        begin.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                reflex.this.beginActionPerformed(evt);
            }
        });
        reactBut.setText("Klikni ovdje");
        reactBut.setToolTipText("");
        reactBut.setActionCommand("beg");
        reactBut.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                reflex.this.reactButActionPerformed(evt);
            }
        });
        GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
        this.jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addContainerGap().addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup().addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(begin).addComponent(reset)).addGap(18, 18, 18).addComponent(colorBox, -2, -1, -2)).addComponent(reactBut, GroupLayout.Alignment.TRAILING, -2, 140, -2)).addContainerGap()));
        jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addContainerGap().addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addComponent(colorBox, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(reactBut, -2, 56, -2)).addGroup(jPanel3Layout.createSequentialGroup().addComponent(begin).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(reset))).addContainerGap()));
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jPanel2, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.jPanel3, -2, -1, -2)).addComponent(this.jPanel1, -1, -1, 32767)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.filler2, -2, 10, -2).addGap(22, 22, 22)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(46, 46, 46).addComponent(this.filler2, -2, 155, -2).addContainerGap(-1, 32767)).addGroup(layout.createSequentialGroup().addContainerGap(-1, 32767).addComponent(this.jPanel1, -2, -1, -2).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel2, -2, -1, -2).addComponent(this.jPanel3, -2, -1, -2)).addGap(18, 18, 18)));
        this.pack();
    }

    private void colorBoxPropertyChange(PropertyChangeEvent evt) {
    }

    private void reactButActionPerformed(ActionEvent evt) {
        this.stop();
        if (this.getDuration() > 1L) {
            react.setText(String.valueOf(this.getDuration()));
            history.append(String.valueOf(this.getDuration()) + "\n");
            reset.setEnabled(true);
            begin.setEnabled(false);
            reactBut.setEnabled(false);
        }
    }

    private void beginActionPerformed(ActionEvent evt) {
        Random rand = new Random();
        try {
            TimeUnit.SECONDS.sleep(rand.nextInt(7) + 1);
        }
        catch (InterruptedException ex) {
            Logger.getLogger(reflex.class.getName()).log(Level.SEVERE, null, ex);
        }
        colorBox.setBackground(Color.RED);
        reactBut.setEnabled(true);
        this.start();
    }

    private void resetActionPerformed(ActionEvent evt) {
        colorBox.setBackground(Color.BLUE);
        react.setText("");
        this.startTime = 0L;
        this.endTime = 0L;
        reactBut.setEnabled(false);
        reset.setEnabled(false);
        begin.setEnabled(true);
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (!"Nimbus".equals(info.getName())) continue;
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(reflex.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex) {
            Logger.getLogger(reflex.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex) {
            Logger.getLogger(reflex.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(reflex.class.getName()).log(Level.SEVERE, null, ex);
        }
        EventQueue.invokeLater(new Runnable(){

            @Override
            public void run() {
                try {
                    new reflex().setVisible(true);
                }
                catch (InterruptedException ex) {
                    Logger.getLogger(reflex.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void setText(String ajme) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

