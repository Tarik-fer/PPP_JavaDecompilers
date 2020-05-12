/*
 * Decompiled with CFR 0.148.
 */
package SJCE.Cfg;

import SJCE.XChessFrame;
import SJCE.more.Actions;
import SJCE.xgui.JPanel.BoardUI;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.PrintStream;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.LayoutStyle;
import javax.swing.border.Border;

public class BoardThemeSelect
extends JDialog {
    private JButton bCancel;
    private JButton bOk;
    public static JComboBox<String> comboFig;
    public static JComboBox<String> comboFon;
    private JToolBar.Separator jSeparator1;
    private JToolBar jToolBar1;
    private JToolBar jToolBar2;
    private JToolBar jToolBar3;
    private JToolBar jToolBar4;
    public static JLabel lblackFig;
    public static JLabel lblackFon;
    public static JLabel lwhiteFig;
    public static JLabel lwhiteFon;

    public BoardThemeSelect(Frame parent, boolean modal) {
        super(parent, modal);
        this.initComponents();
        this.setLocationRelativeTo(XChessFrame.frame);
        comboFig.setModel(new DefaultComboBoxModel<String>(XChessFrame.selectBoardThemeFig));
        comboFon.setModel(new DefaultComboBoxModel<String>(XChessFrame.selectBoardThemeFon));
        comboFig.setSelectedItem(Actions.BoardThemeFig);
        comboFon.setSelectedItem(Actions.BoardThemeFon);
        lwhiteFig.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/themes/fig/" + comboFig.getSelectedItem().toString() + "/wg.png")));
        lblackFig.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/themes/fig/" + comboFig.getSelectedItem().toString() + "/bg.png")));
        lwhiteFon.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/themes/fon/" + comboFon.getSelectedItem().toString() + "/ws.png")));
        lblackFon.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/themes/fon/" + comboFon.getSelectedItem().toString() + "/bs.png")));
    }

    private void initComponents() {
        this.jToolBar1 = new JToolBar();
        this.jToolBar2 = new JToolBar();
        lwhiteFig = new JLabel();
        this.jSeparator1 = new JToolBar.Separator();
        lblackFig = new JLabel();
        comboFig = new JComboBox();
        this.jToolBar3 = new JToolBar();
        this.jToolBar4 = new JToolBar();
        lwhiteFon = new JLabel();
        lblackFon = new JLabel();
        comboFon = new JComboBox();
        this.bOk = new JButton();
        this.bCancel = new JButton();
        this.setDefaultCloseOperation(2);
        this.setTitle("Board Theme ");
        this.jToolBar1.setBorder(BorderFactory.createTitledBorder("Piece"));
        this.jToolBar1.setOrientation(1);
        this.jToolBar2.setBorder(BorderFactory.createTitledBorder("Example"));
        this.jToolBar2.setFloatable(false);
        lwhiteFig.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/themes/fig/alpha/wg.png")));
        this.jToolBar2.add(lwhiteFig);
        this.jToolBar2.add(this.jSeparator1);
        lblackFig.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/themes/fig/alpha/bg.png")));
        this.jToolBar2.add(lblackFig);
        this.jToolBar1.add(this.jToolBar2);
        comboFig.setModel(new DefaultComboBoxModel<String>(new String[]{"white-black", "white-yellow", "white-green", "white-red", "yellow-green", "yellow-red", "cyan-red"}));
        comboFig.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                BoardThemeSelect.this.comboFigActionPerformed(evt);
            }
        });
        this.jToolBar1.add(comboFig);
        this.jToolBar3.setBorder(BorderFactory.createTitledBorder("Board"));
        this.jToolBar3.setOrientation(1);
        this.jToolBar4.setBorder(BorderFactory.createTitledBorder("Example"));
        this.jToolBar4.setFloatable(false);
        lwhiteFon.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/themes/fon/sea-green/ws.png")));
        this.jToolBar4.add(lwhiteFon);
        lblackFon.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/themes/fon/sea-green/bs.png")));
        this.jToolBar4.add(lblackFon);
        this.jToolBar3.add(this.jToolBar4);
        comboFon.setModel(new DefaultComboBoxModel<String>(new String[]{"white-green", "white-magenta", "white-cyan", "yellow-red", "yellow-green", "cyan-magenta", "bisque-orange", "forest", "tree"}));
        comboFon.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                BoardThemeSelect.this.comboFonActionPerformed(evt);
            }
        });
        this.jToolBar3.add(comboFon);
        this.bOk.setText("Apply");
        this.bOk.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                BoardThemeSelect.this.bOkActionPerformed(evt);
            }
        });
        this.bCancel.setText("Cancel");
        this.bCancel.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                BoardThemeSelect.this.bCancelActionPerformed(evt);
            }
        });
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addGroup(layout.createSequentialGroup().addComponent(this.bOk, -2, 73, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 24, 32767).addComponent(this.bCancel)).addComponent(this.jToolBar3, -1, -1, 32767).addComponent(this.jToolBar1, -1, -1, 32767)).addContainerGap(-1, 32767)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jToolBar1, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jToolBar3, -2, 116, -2).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.bOk).addComponent(this.bCancel)).addContainerGap(-1, 32767)));
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private void bCancelActionPerformed(ActionEvent evt) {
        this.setVisible(false);
        this.dispose();
    }

    private void comboFigActionPerformed(ActionEvent evt) {
        lwhiteFig.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/themes/fig/" + comboFig.getSelectedItem().toString() + "/wg.png")));
        lblackFig.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/themes/fig/" + comboFig.getSelectedItem().toString() + "/bg.png")));
    }

    private void comboFonActionPerformed(ActionEvent evt) {
        lwhiteFon.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/themes/fon/" + comboFon.getSelectedItem().toString() + "/ws.png")));
        lblackFon.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/themes/fon/" + comboFon.getSelectedItem().toString() + "/bs.png")));
    }

    private void bOkActionPerformed(ActionEvent evt) {
        Actions.BoardThemeFig = comboFig.getSelectedItem().toString();
        Actions.BoardThemeFon = comboFon.getSelectedItem().toString();
        XChessFrame.frame.loadBoardTheme();
        System.out.println("boardUI = " + XChessFrame.boardUI.getSize());
        System.out.println("borderPanel = " + XChessFrame.borderPanel.getSize());
    }

    public static void goBTS() {
        EventQueue.invokeLater(new Runnable(){

            @Override
            public void run() {
                BoardThemeSelect dialog = new BoardThemeSelect(new JFrame(), true);
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

