// 
// Decompiled by Procyon v0.5.36
// 

package SJCE.more.Log;

import java.awt.EventQueue;
import SJCE.more.Actions;
import java.awt.LayoutManager;
import javax.swing.GroupLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Component;
import SJCE.XChessFrame;
import javax.swing.JToolBar;
import javax.swing.JScrollPane;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JFrame;

public class LogShow extends JFrame
{
    public static JTextArea gameLogTextArea;
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton4;
    private JMenu jMenu1;
    private JMenuBar jMenuBar1;
    private JMenuItem jMenuItem1;
    private JMenuItem jMenuItem2;
    private JMenuItem jMenuItem3;
    private JMenuItem jMenuItem4;
    private JScrollPane jScrollPane1;
    private JToolBar jToolBar1;
    private JToolBar jToolBar2;
    
    public LogShow() {
        this.initComponents();
        this.setLocationRelativeTo(XChessFrame.frame);
        this.setTitle("Game Log");
        this.setIconImage(XChessFrame.frame.FrameIcon.getImage());
    }
    
    private void initComponents() {
        this.jToolBar2 = new JToolBar();
        this.jToolBar1 = new JToolBar();
        this.jButton1 = new JButton();
        this.jButton2 = new JButton();
        this.jButton3 = new JButton();
        this.jButton4 = new JButton();
        this.jScrollPane1 = new JScrollPane();
        LogShow.gameLogTextArea = new JTextArea();
        this.jMenuBar1 = new JMenuBar();
        this.jMenu1 = new JMenu();
        this.jMenuItem2 = new JMenuItem();
        this.jMenuItem4 = new JMenuItem();
        this.jMenuItem1 = new JMenuItem();
        this.jMenuItem3 = new JMenuItem();
        this.setDefaultCloseOperation(2);
        this.setTitle("Game Log");
        this.jToolBar2.setFloatable(false);
        this.jToolBar2.setOrientation(1);
        this.jToolBar1.setFloatable(false);
        this.jButton1.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/floppy_disk_green.png")));
        this.jButton1.setToolTipText("Save Log to file");
        this.jButton1.setFocusable(false);
        this.jButton1.setHorizontalTextPosition(0);
        this.jButton1.setVerticalTextPosition(3);
        this.jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                LogShow.this.jButton1ActionPerformed(evt);
            }
        });
        this.jToolBar1.add(this.jButton1);
        this.jButton2.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/copy.png")));
        this.jButton2.setToolTipText("Copy Log to ClipBoard");
        this.jButton2.setFocusable(false);
        this.jButton2.setHorizontalTextPosition(0);
        this.jButton2.setVerticalTextPosition(3);
        this.jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                LogShow.this.jButton2ActionPerformed(evt);
            }
        });
        this.jToolBar1.add(this.jButton2);
        this.jButton3.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/clipboard_search.png")));
        this.jButton3.setToolTipText("Show ClipBoard");
        this.jButton3.setFocusable(false);
        this.jButton3.setHorizontalTextPosition(0);
        this.jButton3.setVerticalTextPosition(3);
        this.jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                LogShow.this.jButton3ActionPerformed(evt);
            }
        });
        this.jToolBar1.add(this.jButton3);
        this.jButton4.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/Log-Out-24.png")));
        this.jButton4.setToolTipText("Close window");
        this.jButton4.setFocusable(false);
        this.jButton4.setHorizontalTextPosition(0);
        this.jButton4.setVerticalTextPosition(3);
        this.jButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                LogShow.this.jButton4ActionPerformed(evt);
            }
        });
        this.jToolBar1.add(this.jButton4);
        this.jToolBar2.add(this.jToolBar1);
        LogShow.gameLogTextArea.setEditable(false);
        LogShow.gameLogTextArea.setColumns(20);
        LogShow.gameLogTextArea.setRows(5);
        this.jScrollPane1.setViewportView(LogShow.gameLogTextArea);
        this.jToolBar2.add(this.jScrollPane1);
        this.jMenu1.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/Actions-document-open-icon.png")));
        this.jMenu1.setText("File");
        this.jMenuItem2.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/clipboard_plus.png")));
        this.jMenuItem2.setText("Copy Log to ClipBoard");
        this.jMenuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                LogShow.this.jMenuItem2ActionPerformed(evt);
            }
        });
        this.jMenu1.add(this.jMenuItem2);
        this.jMenuItem4.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/clipboard_lupa-16.png")));
        this.jMenuItem4.setText("Show ClipBoard");
        this.jMenuItem4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                LogShow.this.jMenuItem4ActionPerformed(evt);
            }
        });
        this.jMenu1.add(this.jMenuItem4);
        this.jMenuItem1.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/save.png")));
        this.jMenuItem1.setText("Save Log");
        this.jMenuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                LogShow.this.jMenuItem1ActionPerformed(evt);
            }
        });
        this.jMenu1.add(this.jMenuItem1);
        this.jMenuItem3.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/Log-Out-16.png")));
        this.jMenuItem3.setText("Exit");
        this.jMenuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                LogShow.this.jMenuItem3ActionPerformed(evt);
            }
        });
        this.jMenu1.add(this.jMenuItem3);
        this.jMenuBar1.add(this.jMenu1);
        this.setJMenuBar(this.jMenuBar1);
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jToolBar2, -1, 464, 32767).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addComponent(this.jToolBar2, -1, 367, 32767).addContainerGap()));
        this.pack();
    }
    
    private void jButton1ActionPerformed(final ActionEvent evt) {
        LogSave.Save();
    }
    
    private void jMenuItem1ActionPerformed(final ActionEvent evt) {
        LogSave.Save();
    }
    
    private void jMenuItem2ActionPerformed(final ActionEvent evt) {
        final Actions aktion = XChessFrame.aktion;
        final XChessFrame frame = XChessFrame.frame;
        Actions.CopyToClipBoard(XChessFrame.outputArea.getText());
    }
    
    private void jMenuItem4ActionPerformed(final ActionEvent evt) {
        final Actions aktion = XChessFrame.aktion;
        Actions.clipboardShow();
    }
    
    private void jButton2ActionPerformed(final ActionEvent evt) {
        final Actions aktion = XChessFrame.aktion;
        final XChessFrame frame = XChessFrame.frame;
        Actions.CopyToClipBoard(XChessFrame.outputArea.getText());
    }
    
    private void jButton3ActionPerformed(final ActionEvent evt) {
        final Actions aktion = XChessFrame.aktion;
        Actions.clipboardShow();
    }
    
    private void jMenuItem3ActionPerformed(final ActionEvent evt) {
        this.setVisible(false);
        this.dispose();
    }
    
    private void jButton4ActionPerformed(final ActionEvent evt) {
        this.setVisible(false);
        this.dispose();
    }
    
    public static void gogo() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LogShow().setVisible(true);
            }
        });
    }
}
