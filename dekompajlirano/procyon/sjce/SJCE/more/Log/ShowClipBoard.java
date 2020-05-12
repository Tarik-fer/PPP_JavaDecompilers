// 
// Decompiled by Procyon v0.5.36
// 

package SJCE.more.Log;

import java.awt.EventQueue;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;
import java.awt.LayoutManager;
import javax.swing.GroupLayout;
import java.awt.Component;
import SJCE.XChessFrame;
import java.awt.Frame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JDialog;

public class ShowClipBoard extends JDialog
{
    public static JTextArea ClipboardText;
    private JScrollPane jScrollPane1;
    
    public ShowClipBoard(final Frame parent, final boolean modal) {
        super(parent, modal);
        this.initComponents();
        this.setLocationRelativeTo(XChessFrame.logFrame);
        this.setTitle("ClipBoard");
        this.setIconImage(XChessFrame.frame.FrameIcon.getImage());
    }
    
    private void initComponents() {
        this.jScrollPane1 = new JScrollPane();
        ShowClipBoard.ClipboardText = new JTextArea();
        this.setDefaultCloseOperation(2);
        this.setTitle("ClipBoard");
        this.setModal(true);
        ShowClipBoard.ClipboardText.setEditable(false);
        ShowClipBoard.ClipboardText.setColumns(20);
        ShowClipBoard.ClipboardText.setRows(5);
        this.jScrollPane1.setViewportView(ShowClipBoard.ClipboardText);
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane1, -1, 356, 32767).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane1, -1, 117, 32767).addContainerGap()));
        this.pack();
        this.setLocationRelativeTo(null);
    }
    
    public static void scGo() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                final ShowClipBoard dialog = new ShowClipBoard(new JFrame(), true);
                dialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(final WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
}
