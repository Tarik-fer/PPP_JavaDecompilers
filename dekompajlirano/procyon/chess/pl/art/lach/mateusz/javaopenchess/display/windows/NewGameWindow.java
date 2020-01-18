// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.display.windows;

import java.awt.EventQueue;
import java.awt.LayoutManager;
import javax.swing.GroupLayout;
import java.awt.Component;
import pl.art.lach.mateusz.javaopenchess.utils.Settings;
import javax.swing.JTabbedPane;
import javax.swing.JDialog;

public class NewGameWindow extends JDialog
{
    private JTabbedPane jTabbedPane1;
    
    public NewGameWindow() {
        this.initComponents();
        this.setSize(400, 700);
        this.setDefaultCloseOperation(1);
        this.jTabbedPane1.addTab(Settings.lang("local_game"), new DrawLocalSettings(this));
        this.jTabbedPane1.addTab(Settings.lang("network_game"), new DrawNetworkSettings(this));
    }
    
    private void initComponents() {
        this.jTabbedPane1 = new JTabbedPane();
        this.setDefaultCloseOperation(2);
        this.setAlwaysOnTop(true);
        this.setName("Form");
        this.jTabbedPane1.setName("jTabbedPane1");
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jTabbedPane1, -1, 376, 32767).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(20, 20, 20).addComponent(this.jTabbedPane1, -1, 268, 32767).addContainerGap()));
        this.pack();
    }
    
    public static void main(final String[] args) {
        EventQueue.invokeLater(() -> new NewGameWindow().setVisible(true));
    }
}
