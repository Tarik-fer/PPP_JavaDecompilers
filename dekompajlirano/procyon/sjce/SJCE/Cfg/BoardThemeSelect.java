// 
// Decompiled by Procyon v0.5.36
// 

package SJCE.Cfg;

import java.awt.EventQueue;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;
import javax.swing.LayoutStyle;
import java.awt.LayoutManager;
import javax.swing.GroupLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import SJCE.more.Actions;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.Component;
import SJCE.XChessFrame;
import java.awt.Frame;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JDialog;

public class BoardThemeSelect extends JDialog
{
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
    
    public BoardThemeSelect(final Frame parent, final boolean modal) {
        super(parent, modal);
        this.initComponents();
        this.setLocationRelativeTo(XChessFrame.frame);
        final JComboBox<String> comboFig = BoardThemeSelect.comboFig;
        final XChessFrame frame = XChessFrame.frame;
        comboFig.setModel(new DefaultComboBoxModel<String>(XChessFrame.selectBoardThemeFig));
        final JComboBox<String> comboFon = BoardThemeSelect.comboFon;
        final XChessFrame frame2 = XChessFrame.frame;
        comboFon.setModel(new DefaultComboBoxModel<String>(XChessFrame.selectBoardThemeFon));
        final JComboBox<String> comboFig2 = BoardThemeSelect.comboFig;
        final Actions aktion = XChessFrame.aktion;
        comboFig2.setSelectedItem(Actions.BoardThemeFig);
        final JComboBox<String> comboFon2 = BoardThemeSelect.comboFon;
        final Actions aktion2 = XChessFrame.aktion;
        comboFon2.setSelectedItem(Actions.BoardThemeFon);
        BoardThemeSelect.lwhiteFig.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/themes/fig/" + BoardThemeSelect.comboFig.getSelectedItem().toString() + "/wg.png")));
        BoardThemeSelect.lblackFig.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/themes/fig/" + BoardThemeSelect.comboFig.getSelectedItem().toString() + "/bg.png")));
        BoardThemeSelect.lwhiteFon.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/themes/fon/" + BoardThemeSelect.comboFon.getSelectedItem().toString() + "/ws.png")));
        BoardThemeSelect.lblackFon.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/themes/fon/" + BoardThemeSelect.comboFon.getSelectedItem().toString() + "/bs.png")));
    }
    
    private void initComponents() {
        this.jToolBar1 = new JToolBar();
        this.jToolBar2 = new JToolBar();
        BoardThemeSelect.lwhiteFig = new JLabel();
        this.jSeparator1 = new JToolBar.Separator();
        BoardThemeSelect.lblackFig = new JLabel();
        BoardThemeSelect.comboFig = new JComboBox<String>();
        this.jToolBar3 = new JToolBar();
        this.jToolBar4 = new JToolBar();
        BoardThemeSelect.lwhiteFon = new JLabel();
        BoardThemeSelect.lblackFon = new JLabel();
        BoardThemeSelect.comboFon = new JComboBox<String>();
        this.bOk = new JButton();
        this.bCancel = new JButton();
        this.setDefaultCloseOperation(2);
        this.setTitle("Board Theme ");
        this.jToolBar1.setBorder(BorderFactory.createTitledBorder("Piece"));
        this.jToolBar1.setOrientation(1);
        this.jToolBar2.setBorder(BorderFactory.createTitledBorder("Example"));
        this.jToolBar2.setFloatable(false);
        BoardThemeSelect.lwhiteFig.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/themes/fig/alpha/wg.png")));
        this.jToolBar2.add(BoardThemeSelect.lwhiteFig);
        this.jToolBar2.add(this.jSeparator1);
        BoardThemeSelect.lblackFig.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/themes/fig/alpha/bg.png")));
        this.jToolBar2.add(BoardThemeSelect.lblackFig);
        this.jToolBar1.add(this.jToolBar2);
        BoardThemeSelect.comboFig.setModel(new DefaultComboBoxModel<String>(new String[] { "white-black", "white-yellow", "white-green", "white-red", "yellow-green", "yellow-red", "cyan-red" }));
        BoardThemeSelect.comboFig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                BoardThemeSelect.this.comboFigActionPerformed(evt);
            }
        });
        this.jToolBar1.add(BoardThemeSelect.comboFig);
        this.jToolBar3.setBorder(BorderFactory.createTitledBorder("Board"));
        this.jToolBar3.setOrientation(1);
        this.jToolBar4.setBorder(BorderFactory.createTitledBorder("Example"));
        this.jToolBar4.setFloatable(false);
        BoardThemeSelect.lwhiteFon.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/themes/fon/sea-green/ws.png")));
        this.jToolBar4.add(BoardThemeSelect.lwhiteFon);
        BoardThemeSelect.lblackFon.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/themes/fon/sea-green/bs.png")));
        this.jToolBar4.add(BoardThemeSelect.lblackFon);
        this.jToolBar3.add(this.jToolBar4);
        BoardThemeSelect.comboFon.setModel(new DefaultComboBoxModel<String>(new String[] { "white-green", "white-magenta", "white-cyan", "yellow-red", "yellow-green", "cyan-magenta", "bisque-orange", "forest", "tree" }));
        BoardThemeSelect.comboFon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                BoardThemeSelect.this.comboFonActionPerformed(evt);
            }
        });
        this.jToolBar3.add(BoardThemeSelect.comboFon);
        this.bOk.setText("Apply");
        this.bOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                BoardThemeSelect.this.bOkActionPerformed(evt);
            }
        });
        this.bCancel.setText("Cancel");
        this.bCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                BoardThemeSelect.this.bCancelActionPerformed(evt);
            }
        });
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addGroup(layout.createSequentialGroup().addComponent(this.bOk, -2, 73, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 24, 32767).addComponent(this.bCancel)).addComponent(this.jToolBar3, -1, -1, 32767).addComponent(this.jToolBar1, -1, -1, 32767)).addContainerGap(-1, 32767)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jToolBar1, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jToolBar3, -2, 116, -2).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.bOk).addComponent(this.bCancel)).addContainerGap(-1, 32767)));
        this.pack();
        this.setLocationRelativeTo(null);
    }
    
    private void bCancelActionPerformed(final ActionEvent evt) {
        this.setVisible(false);
        this.dispose();
    }
    
    private void comboFigActionPerformed(final ActionEvent evt) {
        BoardThemeSelect.lwhiteFig.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/themes/fig/" + BoardThemeSelect.comboFig.getSelectedItem().toString() + "/wg.png")));
        BoardThemeSelect.lblackFig.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/themes/fig/" + BoardThemeSelect.comboFig.getSelectedItem().toString() + "/bg.png")));
    }
    
    private void comboFonActionPerformed(final ActionEvent evt) {
        BoardThemeSelect.lwhiteFon.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/themes/fon/" + BoardThemeSelect.comboFon.getSelectedItem().toString() + "/ws.png")));
        BoardThemeSelect.lblackFon.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/themes/fon/" + BoardThemeSelect.comboFon.getSelectedItem().toString() + "/bs.png")));
    }
    
    private void bOkActionPerformed(final ActionEvent evt) {
        final Actions aktion = XChessFrame.aktion;
        Actions.BoardThemeFig = BoardThemeSelect.comboFig.getSelectedItem().toString();
        final Actions aktion2 = XChessFrame.aktion;
        Actions.BoardThemeFon = BoardThemeSelect.comboFon.getSelectedItem().toString();
        XChessFrame.frame.loadBoardTheme();
        System.out.println("boardUI = " + XChessFrame.boardUI.getSize());
        System.out.println("borderPanel = " + XChessFrame.borderPanel.getSize());
    }
    
    public static void goBTS() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                final BoardThemeSelect dialog = new BoardThemeSelect(new JFrame(), true);
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
