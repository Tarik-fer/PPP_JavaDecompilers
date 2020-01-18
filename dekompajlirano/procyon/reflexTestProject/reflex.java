import java.awt.EventQueue;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.TimeUnit;
import java.util.Random;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
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
        return this.startTime = System.currentTimeMillis();
    }
    
    public long stop() {
        return this.endTime = System.currentTimeMillis();
    }
    
    public reflex() throws InterruptedException {
        this.initComponents();
        reflex.reactBut.setEnabled(false);
        reflex.reset.setEnabled(false);
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
        reflex.history = new JTextArea();
        this.time = new JLabel();
        this.jLabel4 = new JLabel();
        reflex.react = new JLabel();
        this.jPanel3 = new JPanel();
        reflex.reset = new JButton();
        reflex.colorBox = new JPanel();
        reflex.begin = new JButton();
        reflex.reactBut = new JButton();
        this.setDefaultCloseOperation(3);
        this.jPanel1.setBorder(BorderFactory.createTitledBorder(null, "Upute", 0, 0, new Font("Arial", 0, 18)));
        this.jLabel1.setFont(new Font("Arial", 0, 14));
        this.jLabel1.setText("Kada se promijeni boja kvadrati\u0107a klikni te na gumb \"Klikni ovdje\"");
        this.jLabel5.setFont(new Font("Arial", 0, 14));
        this.jLabel5.setText("Nakon \u0161to kliknete \"ponovi\" ponovo kliknite na gumb \"Kreni\"");
        this.jLabel3.setFont(new Font("Arial", 0, 14));
        this.jLabel3.setText("Kliknite na \"Kreni\" kako bi ste zapo\u010deli i pomaknite mi\u0161 na gumb \"Klikni ovdje\"");
        final GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel3).addComponent(this.jLabel1).addComponent(this.jLabel5)).addContainerGap(-1, 32767)));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel3).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jLabel1).addGap(18, 18, 18).addComponent(this.jLabel5).addContainerGap()));
        this.jPanel2.setBorder(BorderFactory.createTitledBorder(null, "Vrijeme", 0, 0, new Font("Arial", 0, 18)));
        this.jLabel2.setFont(new Font("Tahoma", 0, 12));
        this.jLabel2.setText("[ms]");
        reflex.history.setEditable(false);
        reflex.history.setColumns(20);
        reflex.history.setFont(new Font("Arial", 0, 14));
        reflex.history.setRows(5);
        this.jScrollPane1.setViewportView(reflex.history);
        this.time.setFont(new Font("Arial", 0, 14));
        this.time.setText("Vrijeme:");
        this.jLabel4.setFont(new Font("Arial", 0, 14));
        this.jLabel4.setText("Povijest rezultata:");
        reflex.react.setFont(new Font("Arial", 0, 14));
        final GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
        this.jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel4).addComponent(this.jScrollPane1, -2, -1, -2).addGroup(jPanel2Layout.createSequentialGroup().addComponent(this.time).addGap(2, 2, 2).addComponent(reflex.react, -2, 48, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel2))).addContainerGap()));
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.time, GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(reflex.react, -2, 17, -2).addComponent(this.jLabel2))).addGap(18, 18, 18).addComponent(this.jLabel4).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jScrollPane1, -2, 156, -2).addContainerGap()));
        this.jPanel3.setBorder(BorderFactory.createTitledBorder(null, "Test", 0, 0, new Font("Arial", 0, 18)));
        reflex.reset.setText("Ponovi");
        reflex.reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                reflex.this.resetActionPerformed(evt);
            }
        });
        reflex.colorBox.setBackground(new Color(0, 0, 255));
        reflex.colorBox.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(final PropertyChangeEvent evt) {
                reflex.this.colorBoxPropertyChange(evt);
            }
        });
        final GroupLayout colorBoxLayout = new GroupLayout(reflex.colorBox);
        reflex.colorBox.setLayout(colorBoxLayout);
        colorBoxLayout.setHorizontalGroup(colorBoxLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 100, 32767));
        colorBoxLayout.setVerticalGroup(colorBoxLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 100, 32767));
        reflex.begin.setText("Kreni");
        reflex.begin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                reflex.this.beginActionPerformed(evt);
            }
        });
        reflex.reactBut.setText("Klikni ovdje");
        reflex.reactBut.setToolTipText("");
        reflex.reactBut.setActionCommand("beg");
        reflex.reactBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                reflex.this.reactButActionPerformed(evt);
            }
        });
        final GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
        this.jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addContainerGap().addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup().addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(reflex.begin).addComponent(reflex.reset)).addGap(18, 18, 18).addComponent(reflex.colorBox, -2, -1, -2)).addComponent(reflex.reactBut, GroupLayout.Alignment.TRAILING, -2, 140, -2)).addContainerGap()));
        jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addContainerGap().addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addComponent(reflex.colorBox, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(reflex.reactBut, -2, 56, -2)).addGroup(jPanel3Layout.createSequentialGroup().addComponent(reflex.begin).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(reflex.reset))).addContainerGap()));
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jPanel2, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.jPanel3, -2, -1, -2)).addComponent(this.jPanel1, -1, -1, 32767)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.filler2, -2, 10, -2).addGap(22, 22, 22)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(46, 46, 46).addComponent(this.filler2, -2, 155, -2).addContainerGap(-1, 32767)).addGroup(layout.createSequentialGroup().addContainerGap(-1, 32767).addComponent(this.jPanel1, -2, -1, -2).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel2, -2, -1, -2).addComponent(this.jPanel3, -2, -1, -2)).addGap(18, 18, 18)));
        this.pack();
    }
    
    private void colorBoxPropertyChange(final PropertyChangeEvent evt) {
    }
    
    private void reactButActionPerformed(final ActionEvent evt) {
        this.stop();
        if (this.getDuration() > 1L) {
            reflex.react.setText(String.valueOf(this.getDuration()));
            reflex.history.append(String.valueOf(this.getDuration()) + "\n");
            reflex.reset.setEnabled(true);
            reflex.begin.setEnabled(false);
            reflex.reactBut.setEnabled(false);
        }
    }
    
    private void beginActionPerformed(final ActionEvent evt) {
        final Random rand = new Random();
        try {
            TimeUnit.SECONDS.sleep(rand.nextInt(7) + 1);
        }
        catch (InterruptedException ex) {
            Logger.getLogger(reflex.class.getName()).log(Level.SEVERE, null, ex);
        }
        reflex.colorBox.setBackground(Color.RED);
        reflex.reactBut.setEnabled(true);
        this.start();
    }
    
    private void resetActionPerformed(final ActionEvent evt) {
        reflex.colorBox.setBackground(Color.BLUE);
        reflex.react.setText("");
        this.startTime = 0L;
        this.endTime = 0L;
        reflex.reactBut.setEnabled(false);
        reflex.reset.setEnabled(false);
        reflex.begin.setEnabled(true);
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
        EventQueue.invokeLater(new Runnable() {
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
    
    private void setText(final String ajme) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
