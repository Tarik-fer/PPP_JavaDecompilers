/*
 * Decompiled with CFR 0.148.
 */
package SJCE.more.Mixer;

import SJCE.XChessFrame;
import SJCE.more.Actions;
import SJCE.more.Mixer.ControlSound;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.PrintStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.JSlider;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MixerFrame
extends JDialog {
    public static final int RET_CANCEL = 0;
    public static final int RET_OK = 1;
    public static JSlider MixerFrameSlider;
    public static JCheckBox bMute;
    private int returnStatus = 0;

    public MixerFrame(Frame parent, boolean modal) {
        super(parent, modal);
        this.initComponents();
        this.setIconImage(XChessFrame.frame.FrameIcon.getImage());
        String cancelName = "cancel";
        MixerFrameSlider.setMajorTickSpacing(20);
        MixerFrameSlider.setMinorTickSpacing(10);
        MixerFrameSlider.setPaintTicks(true);
        MixerFrameSlider.setPaintLabels(true);
        try {
            MixerFrameSlider.setValue(Math.round(100.0f * ControlSound.getMasterOutputVolume().floatValue()));
            if (Actions.currentMute.equals("true") || ControlSound.getMasterOutputMute().booleanValue()) {
                ControlSound.setMasterOutputMute(true);
                bMute.setSelected(true);
                System.out.println("Volume set to ZERO = 0");
                MixerFrameSlider.setEnabled(false);
            } else {
                ControlSound.setMasterOutputMute(false);
                bMute.setSelected(false);
                ControlSound.setMasterOutputVolume((float)MixerFrameSlider.getValue() / 100.0f);
                Actions.currentMixer = MixerFrameSlider.getValue();
                MixerFrameSlider.setEnabled(true);
            }
        }
        catch (NullPointerException nn) {
            System.out.println("Master output port not found");
            MixerFrameSlider.setEnabled(false);
            bMute.setEnabled(false);
            this.doClose(0);
        }
        catch (RuntimeException rr) {
            System.out.println("Master output port not found");
            MixerFrameSlider.setEnabled(false);
            bMute.setEnabled(false);
            this.doClose(0);
        }
        InputMap inputMap = this.getRootPane().getInputMap(1);
        inputMap.put(KeyStroke.getKeyStroke(27, 0), cancelName);
        ActionMap actionMap = this.getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction(){

            @Override
            public void actionPerformed(ActionEvent e) {
                MixerFrame.this.doClose(0);
            }
        });
    }

    public int getReturnStatus() {
        return this.returnStatus;
    }

    private void initComponents() {
        MixerFrameSlider = new JSlider();
        bMute = new JCheckBox();
        this.setLocation(new Point(100, 100));
        this.setName("VolumeSlider");
        this.setResizable(false);
        MixerFrameSlider.setOrientation(1);
        MixerFrameSlider.setToolTipText("Sound Volume");
        MixerFrameSlider.setValue(100);
        MixerFrameSlider.addChangeListener(new ChangeListener(){

            @Override
            public void stateChanged(ChangeEvent evt) {
                MixerFrame.this.MixerFrameSliderStateChanged(evt);
            }
        });
        bMute.setText("Mute");
        bMute.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                MixerFrame.this.bMuteActionPerformed(evt);
            }
        });
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(bMute).addComponent(MixerFrameSlider, -2, -1, -2)).addContainerGap(-1, 32767)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(MixerFrameSlider, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(bMute).addContainerGap(13, 32767)));
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private void MixerFrameSliderStateChanged(ChangeEvent evt) {
        ControlSound.setMasterOutputVolume((float)MixerFrameSlider.getValue() / 100.0f);
        Actions.currentMixer = MixerFrameSlider.getValue();
        this.MixerIconSet();
    }

    private void bMuteActionPerformed(ActionEvent evt) {
        if (Actions.currentMute.equals("false")) {
            Actions.currentMute = "true";
            ControlSound.setMasterOutputMute(true);
            bMute.setSelected(true);
            System.out.println("Volume set to ZERO = 0");
            MixerFrameSlider.setEnabled(false);
            XChessFrame.bSoundMixer.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/audio_volume_muted.png")));
        } else {
            Actions.currentMute = "false";
            ControlSound.setMasterOutputMute(false);
            bMute.setSelected(false);
            ControlSound.setMasterOutputVolume((float)MixerFrameSlider.getValue() / 100.0f);
            Actions.currentMixer = MixerFrameSlider.getValue();
            MixerFrameSlider.setEnabled(true);
            this.MixerIconSet();
        }
    }

    public void MixerIconSet() {
        if (Actions.currentMixer > 0 & Actions.currentMixer < 33) {
            XChessFrame.bSoundMixer.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/audio_volume_low.png")));
        }
        if (Actions.currentMixer > 33 & Actions.currentMixer < 66) {
            XChessFrame.bSoundMixer.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/audio_volume_medium.png")));
        }
        if (Actions.currentMixer > 66) {
            XChessFrame.bSoundMixer.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/audio_volume_high.png")));
        }
        if (Actions.currentMixer == 0) {
            XChessFrame.bSoundMixer.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/audio_volume_muted.png")));
        }
        if (Actions.currentMute.equals("true")) {
            ControlSound.setMasterOutputMute(true);
            System.out.println("Volume set to ZERO = 0");
            XChessFrame.bSoundMixer.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/audio_volume_muted.png")));
        }
    }

    private void doClose(int retStatus) {
        this.returnStatus = retStatus;
        this.setVisible(false);
        this.dispose();
    }

    public static void gogo() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (!"Metal".equals(info.getName())) continue;
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(MixerFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex) {
            Logger.getLogger(MixerFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex) {
            Logger.getLogger(MixerFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MixerFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        EventQueue.invokeLater(new Runnable(){

            @Override
            public void run() {
                MixerFrame dialog = new MixerFrame(new JFrame(), true);
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

