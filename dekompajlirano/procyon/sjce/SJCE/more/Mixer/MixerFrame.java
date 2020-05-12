// 
// Decompiled by Procyon v0.5.36
// 

package SJCE.more.Mixer;

import java.awt.EventQueue;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle;
import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.GroupLayout;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Point;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.Action;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import SJCE.more.Actions;
import SJCE.XChessFrame;
import java.awt.Frame;
import javax.swing.JCheckBox;
import javax.swing.JSlider;
import javax.swing.JDialog;

public class MixerFrame extends JDialog
{
    public static final int RET_CANCEL = 0;
    public static final int RET_OK = 1;
    public static JSlider MixerFrameSlider;
    public static JCheckBox bMute;
    private int returnStatus;
    
    public MixerFrame(final Frame parent, final boolean modal) {
        super(parent, modal);
        this.returnStatus = 0;
        this.initComponents();
        this.setIconImage(XChessFrame.frame.FrameIcon.getImage());
        final String cancelName = "cancel";
        MixerFrame.MixerFrameSlider.setMajorTickSpacing(20);
        MixerFrame.MixerFrameSlider.setMinorTickSpacing(10);
        MixerFrame.MixerFrameSlider.setPaintTicks(true);
        MixerFrame.MixerFrameSlider.setPaintLabels(true);
        try {
            MixerFrame.MixerFrameSlider.setValue(Math.round(100.0f * ControlSound.getMasterOutputVolume()));
            if (Actions.currentMute.equals("true") || ControlSound.getMasterOutputMute()) {
                ControlSound.setMasterOutputMute(true);
                MixerFrame.bMute.setSelected(true);
                System.out.println("Volume set to ZERO = 0");
                MixerFrame.MixerFrameSlider.setEnabled(false);
            }
            else {
                ControlSound.setMasterOutputMute(false);
                MixerFrame.bMute.setSelected(false);
                ControlSound.setMasterOutputVolume(MixerFrame.MixerFrameSlider.getValue() / 100.0f);
                Actions.currentMixer = MixerFrame.MixerFrameSlider.getValue();
                MixerFrame.MixerFrameSlider.setEnabled(true);
            }
        }
        catch (NullPointerException nn) {
            System.out.println("Master output port not found");
            MixerFrame.MixerFrameSlider.setEnabled(false);
            MixerFrame.bMute.setEnabled(false);
            this.doClose(0);
        }
        catch (RuntimeException rr) {
            System.out.println("Master output port not found");
            MixerFrame.MixerFrameSlider.setEnabled(false);
            MixerFrame.bMute.setEnabled(false);
            this.doClose(0);
        }
        final InputMap inputMap = this.getRootPane().getInputMap(1);
        inputMap.put(KeyStroke.getKeyStroke(27, 0), cancelName);
        final ActionMap actionMap = this.getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                MixerFrame.this.doClose(0);
            }
        });
    }
    
    public int getReturnStatus() {
        return this.returnStatus;
    }
    
    private void initComponents() {
        MixerFrame.MixerFrameSlider = new JSlider();
        MixerFrame.bMute = new JCheckBox();
        this.setLocation(new Point(100, 100));
        this.setName("VolumeSlider");
        this.setResizable(false);
        MixerFrame.MixerFrameSlider.setOrientation(1);
        MixerFrame.MixerFrameSlider.setToolTipText("Sound Volume");
        MixerFrame.MixerFrameSlider.setValue(100);
        MixerFrame.MixerFrameSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                MixerFrame.this.MixerFrameSliderStateChanged(evt);
            }
        });
        MixerFrame.bMute.setText("Mute");
        MixerFrame.bMute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                MixerFrame.this.bMuteActionPerformed(evt);
            }
        });
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(MixerFrame.bMute).addComponent(MixerFrame.MixerFrameSlider, -2, -1, -2)).addContainerGap(-1, 32767)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(MixerFrame.MixerFrameSlider, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(MixerFrame.bMute).addContainerGap(13, 32767)));
        this.pack();
        this.setLocationRelativeTo(null);
    }
    
    private void MixerFrameSliderStateChanged(final ChangeEvent evt) {
        ControlSound.setMasterOutputVolume(MixerFrame.MixerFrameSlider.getValue() / 100.0f);
        Actions.currentMixer = MixerFrame.MixerFrameSlider.getValue();
        this.MixerIconSet();
    }
    
    private void bMuteActionPerformed(final ActionEvent evt) {
        if (Actions.currentMute.equals("false")) {
            Actions.currentMute = "true";
            ControlSound.setMasterOutputMute(true);
            MixerFrame.bMute.setSelected(true);
            System.out.println("Volume set to ZERO = 0");
            MixerFrame.MixerFrameSlider.setEnabled(false);
            final XChessFrame frame = XChessFrame.frame;
            XChessFrame.bSoundMixer.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/audio_volume_muted.png")));
        }
        else {
            Actions.currentMute = "false";
            ControlSound.setMasterOutputMute(false);
            MixerFrame.bMute.setSelected(false);
            ControlSound.setMasterOutputVolume(MixerFrame.MixerFrameSlider.getValue() / 100.0f);
            Actions.currentMixer = MixerFrame.MixerFrameSlider.getValue();
            MixerFrame.MixerFrameSlider.setEnabled(true);
            this.MixerIconSet();
        }
    }
    
    public void MixerIconSet() {
        if (Actions.currentMixer > 0 & Actions.currentMixer < 33) {
            final XChessFrame frame = XChessFrame.frame;
            XChessFrame.bSoundMixer.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/audio_volume_low.png")));
        }
        if (Actions.currentMixer > 33 & Actions.currentMixer < 66) {
            final XChessFrame frame2 = XChessFrame.frame;
            XChessFrame.bSoundMixer.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/audio_volume_medium.png")));
        }
        if (Actions.currentMixer > 66) {
            final XChessFrame frame3 = XChessFrame.frame;
            XChessFrame.bSoundMixer.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/audio_volume_high.png")));
        }
        if (Actions.currentMixer == 0) {
            final XChessFrame frame4 = XChessFrame.frame;
            XChessFrame.bSoundMixer.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/audio_volume_muted.png")));
        }
        if (Actions.currentMute.equals("true")) {
            ControlSound.setMasterOutputMute(true);
            System.out.println("Volume set to ZERO = 0");
            final XChessFrame frame5 = XChessFrame.frame;
            XChessFrame.bSoundMixer.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/audio_volume_muted.png")));
        }
    }
    
    private void doClose(final int retStatus) {
        this.returnStatus = retStatus;
        this.setVisible(false);
        this.dispose();
    }
    
    public static void gogo() {
        try {
            for (final UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(MixerFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(MixerFrame.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(MixerFrame.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(MixerFrame.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                final MixerFrame dialog = new MixerFrame(new JFrame(), true);
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
