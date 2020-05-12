/*
 * Decompiled with CFR 0.148.
 */
package SJCE.more.Log;

import SJCE.XChessFrame;
import SJCE.more.Log.LogShow;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ShowClipBoard
extends JDialog {
    public static JTextArea ClipboardText;
    private JScrollPane jScrollPane1;

    public ShowClipBoard(Frame parent, boolean modal) {
        super(parent, modal);
        this.initComponents();
        this.setLocationRelativeTo(XChessFrame.logFrame);
        this.setTitle("ClipBoard");
        this.setIconImage(XChessFrame.frame.FrameIcon.getImage());
    }

    private void initComponents() {
        this.jScrollPane1 = new JScrollPane();
        ClipboardText = new JTextArea();
        this.setDefaultCloseOperation(2);
        this.setTitle("ClipBoard");
        this.setModal(true);
        ClipboardText.setEditable(false);
        ClipboardText.setColumns(20);
        ClipboardText.setRows(5);
        this.jScrollPane1.setViewportView(ClipboardText);
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane1, -1, 356, 32767).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane1, -1, 117, 32767).addContainerGap()));
        this.pack();
        this.setLocationRelativeTo(null);
    }

    public static void scGo() {
        EventQueue.invokeLater(new Runnable(){

            @Override
            public void run() {
                ShowClipBoard dialog = new ShowClipBoard(new JFrame(), true);
                dialog.addWindowListener(new WindowAdapter(){

                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }

        });
    }

}

