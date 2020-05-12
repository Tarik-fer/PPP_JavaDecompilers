// 
// Decompiled by Procyon v0.5.36
// 

package SJCE;

import java.util.ArrayList;
import java.awt.EventQueue;
import SJCE.more.Run_Thread;
import javax.swing.JDialog;
import SJCE.Cfg.AppCfgPref;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.GroupLayout;
import javax.accessibility.Accessible;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import SJCE.Cfg.BoardThemeSelect;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JOptionPane;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import SJCE.xgui.Interfaces.IEngineListener;
import SJCE.xgui.EventObject.EngineEvent;
import SJCE.xgui.Interfaces.EngineAdapter;
import SJCE.xgui.Agent.UserAgent;
import SJCE.xgui.Utility;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Frame;
import SJCE.more.Mixer.MixerFrame;
import SJCE.more.Mixer.ControlSound;
import javax.swing.Icon;
import javax.swing.AbstractButton;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.util.Collection;
import java.util.Arrays;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToolBar;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import SJCE.more.Log.LogShow;
import javax.swing.ImageIcon;
import SJCE.xgui.Agent.EngineAgentExt;
import SJCE.xgui.Agent.EngineAgent;
import SJCE.more.Actions;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import SJCE.xgui.Agent.Agent;
import SJCE.xgui.JPanel.BoardUI;
import SJCE.xgui.JList.MoveListUI;
import SJCE.xgui.JPanel.ChessClock;
import SJCE.xgui.Interfaces.IMainFrameConst;
import SJCE.xgui.Interfaces.IChessContext;
import javax.swing.JFrame;

public class XChessFrame extends JFrame implements IChessContext, IMainFrameConst
{
    public static ChessClock chessClock;
    public static MoveListUI moveListUI;
    public static BoardUI boardUI;
    public static Agent alphaAgent;
    public static Agent betaAgent;
    public static XChessFrame frame;
    public static BorderLayout BL;
    public static ActionEvent ae;
    public static List<String> lookAndFeelsDisplay;
    public static List<String> lookAndFeelsRealNames;
    public static Actions aktion;
    public static List<String> selectCE;
    public static EngineAgent whiteEngineAgent;
    public static EngineAgent blackEngineAgent;
    public static EngineAgentExt whiteEngineAgentExt;
    public static EngineAgentExt blackEngineAgentExt;
    public static final int borderPanelSize = 415;
    public static final int boardUISize = 400;
    public ImageIcon welcomeIcon;
    public ImageIcon FrameIcon;
    public static final String sjceTitle = "SJCE = Strong Java Chess Engines, build 08.08.18";
    public static LogShow logFrame;
    public ButtonGroup mDepthRadioGroup;
    public ButtonGroup mTimeRadioGroup;
    public static JButton bAbout;
    public static JButton bBoardTheme;
    private JButton bChangeSkin;
    private JButton bKillAll;
    private JButton bLinks;
    private JButton bNew;
    private JButton bSaveCfg;
    private JButton bSelectWhite;
    private JButton bSendBlack;
    private JButton bSendWhite;
    public static JButton bSoundMixer;
    public static JButton bUndoLast;
    public static JToggleButton bUseClock;
    public static JToggleButton bUseSound;
    public static JComboBox<String> bcomboDepth;
    public static JComboBox<String> bcomboMode;
    public static JComboBox bcomboTime;
    public static JPanel borderPanel;
    public static JComboBox<String> comboBPlayerCE;
    public static JComboBox<String> comboWPlayerCE;
    private JSplitPane horizontalSplit;
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JMenu jMenu1;
    private JMenuBar jMenuBar1;
    private JMenuItem jMenuItem1;
    private JMenuItem jMenuItem2;
    private JMenuItem jMenuItem3;
    private JMenuItem jMenuItem4;
    private JMenuItem jMenuItem5;
    private JToolBar.Separator jSeparator1;
    private JToolBar.Separator jSeparator2;
    private JToolBar jToolBar1;
    private JToolBar jToolBar2;
    private JToolBar jToolBar3;
    private JMenuItem mBoardTheme;
    private JMenuItem mChangeSkin;
    private JMenu mChessEngines;
    public static JRadioButtonMenuItem mDepth2;
    public static JRadioButtonMenuItem mDepth3;
    public static JRadioButtonMenuItem mDepth4;
    public static JRadioButtonMenuItem mDepth5;
    public static JRadioButtonMenuItem mDepth6;
    public static JRadioButtonMenuItem mDepth7;
    public static JRadioButtonMenuItem mDepth8;
    public static JRadioButtonMenuItem mDepth9;
    private JMenu mEngineConfig;
    private JMenu mEngineDepth;
    private JMenu mEngineMode;
    private JMenuItem mExit;
    private JMenu mFile;
    private JMenu mInfo;
    private JMenuItem mKillAll;
    public static JRadioButtonMenuItem mModeEasy;
    public static JRadioButtonMenuItem mModeHard;
    private JMenuItem mNewGame;
    private JMenu mOptions;
    private JMenu mPlayers;
    private JMenuItem mSaveCfg;
    private JMenuItem mSelectCEblack;
    private JMenuItem mSelectCEwhite;
    private JMenuItem mSendBlack;
    private JMenuItem mSendWhite;
    public static JMenu mTime;
    public static JRadioButtonMenuItem mTime05;
    public static JRadioButtonMenuItem mTime10;
    public static JRadioButtonMenuItem mTime15;
    public static JRadioButtonMenuItem mTime20;
    public static JRadioButtonMenuItem mTime25;
    public static JRadioButtonMenuItem mTime30;
    private JMenu mTimeMenu;
    public static JMenuItem mUndoLast;
    public static JCheckBoxMenuItem mUseClock;
    public static JCheckBoxMenuItem mUseSound;
    public static JTextArea outputArea;
    public static JScrollPane scrollMoveList;
    public static JScrollPane scrollOutputArea;
    public static JPanel sidePanel;
    private JSplitPane verticalSplit;
    
    public XChessFrame() {
        this.welcomeIcon = new ImageIcon(this.getClass().getResource("/SJCE/img/sjce-130x87.png"));
        this.FrameIcon = new ImageIcon(this.getClass().getResource("/SJCE/img/SubFrameIcon.png"));
        this.mDepthRadioGroup = new ButtonGroup();
        this.mTimeRadioGroup = new ButtonGroup();
        this.initComponents();
        this.setTitle("SJCE = Strong Java Chess Engines, build 08.08.18");
        final ImageIcon icone = new ImageIcon(this.getClass().getResource("/SJCE/img/SubFrameIcon.png"));
        this.setIconImage(icone.getImage());
        XChessFrame.selectCE.addAll(Arrays.asList(XChessFrame.selectEnginesArray));
        this.boardSetSize();
        XChessFrame.comboWPlayerCE.setModel(new DefaultComboBoxModel<String>(XChessFrame.selectEnginesArray));
        final JComboBox<String> comboWPlayerCE = XChessFrame.comboWPlayerCE;
        final Actions aktion = XChessFrame.aktion;
        comboWPlayerCE.setSelectedItem(Actions.whitePlayerCE);
        XChessFrame.comboBPlayerCE.setModel(new DefaultComboBoxModel<String>(XChessFrame.selectEnginesArray));
        final JComboBox<String> comboBPlayerCE = XChessFrame.comboBPlayerCE;
        final Actions aktion2 = XChessFrame.aktion;
        comboBPlayerCE.setSelectedItem(Actions.blackPlayerCE);
        XChessFrame.bcomboDepth.setModel(new DefaultComboBoxModel<String>(XChessFrame.arrayDepth));
        final JComboBox<String> bcomboDepth = XChessFrame.bcomboDepth;
        final StringBuilder append = new StringBuilder().append("");
        final Actions aktion3 = XChessFrame.aktion;
        bcomboDepth.setSelectedItem(append.append(Actions.Depth).toString());
        (XChessFrame.outputArea = new JTextArea() {
            Image image = XChessFrame.this.welcomeIcon.getImage();
            
            {
                this.setOpaque(false);
            }
            
            @Override
            public void paint(final Graphics g) {
                g.drawImage(this.image, XChessFrame.outputArea.getWidth() - 135, 0, this);
                super.paint(g);
            }
        }).setEditable(false);
        XChessFrame.outputArea.append("Please, select chess engine and press New Game !");
        this.mDepthRadioGroup.add(XChessFrame.mDepth2);
        this.mDepthRadioGroup.add(XChessFrame.mDepth3);
        this.mDepthRadioGroup.add(XChessFrame.mDepth4);
        this.mDepthRadioGroup.add(XChessFrame.mDepth5);
        this.mDepthRadioGroup.add(XChessFrame.mDepth6);
        this.mDepthRadioGroup.add(XChessFrame.mDepth7);
        this.mDepthRadioGroup.add(XChessFrame.mDepth8);
        this.mDepthRadioGroup.add(XChessFrame.mDepth9);
        this.mTimeRadioGroup.add(XChessFrame.mTime05);
        this.mTimeRadioGroup.add(XChessFrame.mTime10);
        this.mTimeRadioGroup.add(XChessFrame.mTime15);
        this.mTimeRadioGroup.add(XChessFrame.mTime20);
        this.mTimeRadioGroup.add(XChessFrame.mTime25);
        this.mTimeRadioGroup.add(XChessFrame.mTime30);
    }
    
    public void MixerInit() {
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
        try {
            ControlSound.setMasterOutputVolume(Actions.currentMixer / 100.0f);
        }
        catch (RuntimeException rr) {
            System.out.println("Master output port not found");
        }
    }
    
    public void MixerSet() {
        final MixerFrame mf = new MixerFrame(XChessFrame.frame, true);
        mf.setLocationRelativeTo(XChessFrame.frame);
        mf.setVisible(true);
        MixerFrame.MixerFrameSlider.setValue(Actions.currentMixer);
        this.MixerInit();
    }
    
    public void boardSetSize() {
        XChessFrame.borderPanel.setSize(415, 415);
        XChessFrame.borderPanel.setPreferredSize(new Dimension(415, 415));
        XChessFrame.borderPanel.setMinimumSize(new Dimension(415, 415));
        this.horizontalSplit.setDividerLocation(415);
        this.horizontalSplit.setDividerSize(0);
        this.verticalSplit.setDividerLocation(415);
        this.verticalSplit.setDividerSize(0);
    }
    
    public void newGame(final int agent1, final int agent2) {
        final Actions aktion = XChessFrame.aktion;
        Actions.enemyTip = "";
        final Actions aktion2 = XChessFrame.aktion;
        Actions.gameTip = "";
        final Actions aktion3 = XChessFrame.aktion;
        Actions.uciAllMovesString = "";
        final Actions aktion4 = XChessFrame.aktion;
        Actions.enginePromotionFig = "";
        final Actions aktion5 = XChessFrame.aktion;
        Actions.enginePromotionType = "";
        final Actions aktion6 = XChessFrame.aktion;
        Actions.blackRivalMovesString = "";
        final Actions aktion7 = XChessFrame.aktion;
        Actions.whiteRivalMovesString = "";
        XChessFrame.aktion.restColorWhite_restColorBlack();
        this.loadBoardTheme();
        if (XChessFrame.alphaAgent != null) {
            XChessFrame.alphaAgent.dispose();
        }
        if (XChessFrame.betaAgent != null) {
            XChessFrame.betaAgent.dispose();
        }
        final EngineAgent whiteEngineAgent = XChessFrame.whiteEngineAgent;
        if (EngineAgent.engineIOwhite != null) {
            final EngineAgent whiteEngineAgent2 = XChessFrame.whiteEngineAgent;
            EngineAgent.engineIOwhite.destroy();
        }
        final EngineAgent blackEngineAgent = XChessFrame.blackEngineAgent;
        if (EngineAgent.engineIOblack != null) {
            final EngineAgent blackEngineAgent2 = XChessFrame.blackEngineAgent;
            EngineAgent.engineIOblack.destroy();
        }
        XChessFrame.outputArea.setText("");
        XChessFrame.chessClock.stop();
        final ChessClock chessClock = XChessFrame.chessClock;
        final Actions aktion8 = XChessFrame.aktion;
        chessClock.setTime(Actions.Time * 60000);
        XChessFrame.chessClock.setTurn(0);
        XChessFrame.boardUI.setBoard(Utility.INITIAL_BOARD);
        XChessFrame.moveListUI.clear();
        final Actions aktion9 = XChessFrame.aktion;
        if (Actions.whitePlayerCE.equals("Human")) {
            XChessFrame.alphaAgent = new UserAgent(XChessFrame.frame, "Human", "white", "human");
        }
        else {
            final EngineAgent whiteEngineAgent3 = XChessFrame.whiteEngineAgent;
            final XChessFrame frame = XChessFrame.frame;
            final Actions aktion10 = XChessFrame.aktion;
            final String whitePlayerCE = Actions.whitePlayerCE;
            final String colorCE = "white";
            final Actions aktion11 = XChessFrame.aktion;
            XChessFrame.alphaAgent = EngineAgent.createEngine(frame, whitePlayerCE, colorCE, Actions.whitePlayerTip);
        }
        final Actions aktion12 = XChessFrame.aktion;
        if (Actions.blackPlayerCE.equals("Human")) {
            XChessFrame.betaAgent = new UserAgent(XChessFrame.frame, "Human", "black", "human");
        }
        else {
            final EngineAgent blackEngineAgent3 = XChessFrame.blackEngineAgent;
            final XChessFrame frame2 = XChessFrame.frame;
            final Actions aktion13 = XChessFrame.aktion;
            final String blackPlayerCE = Actions.blackPlayerCE;
            final String colorCE2 = "black";
            final Actions aktion14 = XChessFrame.aktion;
            XChessFrame.betaAgent = EngineAgent.createEngine(frame2, blackPlayerCE, colorCE2, Actions.blackPlayerTip);
        }
        XChessFrame.alphaAgent.setOpponentAgent(XChessFrame.betaAgent);
        final Agent alphaAgent = XChessFrame.alphaAgent;
        final ChessClock chessClock2 = XChessFrame.chessClock;
        alphaAgent.setTurn(0);
        XChessFrame.betaAgent.setOpponentAgent(XChessFrame.alphaAgent);
        final Agent betaAgent = XChessFrame.betaAgent;
        final ChessClock chessClock3 = XChessFrame.chessClock;
        betaAgent.setTurn(1);
        XChessFrame.alphaAgent.newGame();
        XChessFrame.betaAgent.newGame();
        final PrintStream out = System.out;
        final StringBuilder append = new StringBuilder().append("whitePlayerTip = ");
        final Actions aktion15 = XChessFrame.aktion;
        out.println(append.append(Actions.whitePlayerTip).toString());
        final PrintStream out2 = System.out;
        final StringBuilder append2 = new StringBuilder().append("blackPlayerTip = ");
        final Actions aktion16 = XChessFrame.aktion;
        out2.println(append2.append(Actions.blackPlayerTip).toString());
        final Actions aktion17 = XChessFrame.aktion;
        if (Actions.whitePlayerTip.equals("xboard")) {
            final Actions aktion18 = XChessFrame.aktion;
            if (Actions.blackPlayerTip.equals("uci")) {
                final Actions aktion19 = XChessFrame.aktion;
                Actions.enemyTip = "another";
            }
        }
        final Actions aktion20 = XChessFrame.aktion;
        if (Actions.whitePlayerTip.equals("xboard")) {
            final Actions aktion21 = XChessFrame.aktion;
            if (Actions.blackPlayerTip.equals("xboard")) {
                final Actions aktion22 = XChessFrame.aktion;
                Actions.enemyTip = "like";
            }
        }
        final Actions aktion23 = XChessFrame.aktion;
        if (Actions.whitePlayerTip.equals("uci")) {
            final Actions aktion24 = XChessFrame.aktion;
            if (Actions.blackPlayerTip.equals("xboard")) {
                final Actions aktion25 = XChessFrame.aktion;
                Actions.enemyTip = "another";
            }
        }
        final Actions aktion26 = XChessFrame.aktion;
        if (Actions.whitePlayerTip.equals("uci")) {
            final Actions aktion27 = XChessFrame.aktion;
            if (Actions.blackPlayerTip.equals("uci")) {
                final Actions aktion28 = XChessFrame.aktion;
                Actions.enemyTip = "like";
            }
        }
        final PrintStream out3 = System.out;
        final StringBuilder append3 = new StringBuilder().append("Enemy Type = ");
        final Actions aktion29 = XChessFrame.aktion;
        out3.println(append3.append(Actions.enemyTip).toString());
        final PrintStream out4 = System.out;
        final StringBuilder append4 = new StringBuilder().append("Depth = ");
        final Actions aktion30 = XChessFrame.aktion;
        out4.println(append4.append(Actions.Depth).toString());
        final PrintStream out5 = System.out;
        final StringBuilder append5 = new StringBuilder().append("Time = ");
        final Actions aktion31 = XChessFrame.aktion;
        out5.println(append5.append(Actions.Time).toString());
        final Actions aktion32 = XChessFrame.aktion;
        if (!Actions.whitePlayerCE.equals("Human")) {
            final Actions aktion33 = XChessFrame.aktion;
            if (!Actions.blackPlayerCE.equals("Human")) {
                final Actions aktion34 = XChessFrame.aktion;
                Actions.gameTip = "EE";
                final XChessFrame frame3 = XChessFrame.frame;
                XChessFrame.bUndoLast.setEnabled(false);
                final XChessFrame frame4 = XChessFrame.frame;
                XChessFrame.mUndoLast.setEnabled(false);
                final Actions aktion35 = XChessFrame.aktion;
                if (Actions.useSound.equals("true")) {
                    XChessFrame.aktion.useSoundSwitch();
                }
            }
        }
        final Actions aktion36 = XChessFrame.aktion;
        if (Actions.whitePlayerCE.equals("Human")) {
            final Actions aktion37 = XChessFrame.aktion;
            if (Actions.blackPlayerCE.equals("Human")) {
                final Actions aktion38 = XChessFrame.aktion;
                Actions.gameTip = "HH";
            }
        }
        final Actions aktion39 = XChessFrame.aktion;
        if (Actions.whitePlayerCE.equals("Human")) {
            final Actions aktion40 = XChessFrame.aktion;
            if (!Actions.blackPlayerCE.equals("Human")) {
                final Actions aktion41 = XChessFrame.aktion;
                Actions.gameTip = "HE";
                ((EngineAgent)XChessFrame.betaAgent).addIEngineListener(new EngineAdapter() {
                    @Override
                    public void dataPrinted(final EngineEvent e) {
                        XChessFrame.outputArea.setText(XChessFrame.outputArea.getText() + "[OUT] " + e.getData() + "\n");
                    }
                    
                    @Override
                    public void dataEntered(final EngineEvent e) {
                        XChessFrame.outputArea.setText(XChessFrame.outputArea.getText() + "[IN] " + e.getData() + "\n");
                    }
                });
            }
        }
        final Actions aktion42 = XChessFrame.aktion;
        if (!Actions.whitePlayerCE.equals("Human")) {
            final Actions aktion43 = XChessFrame.aktion;
            if (Actions.blackPlayerCE.equals("Human")) {
                final Actions aktion44 = XChessFrame.aktion;
                Actions.gameTip = "EH";
                ((EngineAgent)XChessFrame.alphaAgent).addIEngineListener(new EngineAdapter() {
                    @Override
                    public void dataPrinted(final EngineEvent e) {
                        XChessFrame.outputArea.setText(XChessFrame.outputArea.getText() + "[OUT] " + e.getData() + "\n");
                    }
                    
                    @Override
                    public void dataEntered(final EngineEvent e) {
                        XChessFrame.outputArea.setText(XChessFrame.outputArea.getText() + "[IN] " + e.getData() + "\n");
                    }
                });
            }
        }
        final PrintStream out6 = System.out;
        final StringBuilder append6 = new StringBuilder().append("Game Type = ");
        final Actions aktion45 = XChessFrame.aktion;
        out6.println(append6.append(Actions.gameTip).toString());
        this.loadBoardTheme();
        final Actions aktion46 = XChessFrame.aktion;
        if (Actions.whitePlayerTip.equals("xboard")) {
            try {
                Thread.sleep(2000L);
            }
            catch (InterruptedException ex) {
                Logger.getLogger(XChessFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            final Actions aktion47 = XChessFrame.aktion;
            if (!Actions.whitePlayerCE.equals("ArabianKnight")) {
                final Actions aktion48 = XChessFrame.aktion;
                if (!Actions.whitePlayerCE.equals("OliThink")) {
                    final Actions aktion49 = XChessFrame.aktion;
                    if (!Actions.whitePlayerCE.equals("Eden")) {
                        final Actions aktion50 = XChessFrame.aktion;
                        if (!Actions.whitePlayerCE.equals("Tiffanys")) {
                            final Actions aktion51 = XChessFrame.aktion;
                            Actions.sendEngineCmd("white", "white");
                        }
                    }
                }
            }
            final Actions aktion52 = XChessFrame.aktion;
            Actions.sendEngineCmd("white", "go");
            System.out.println("========================= RUN WHITE ===========================");
        }
        final Actions aktion53 = XChessFrame.aktion;
        if (Actions.whitePlayerTip.equals("uci")) {
            try {
                Thread.sleep(2000L);
            }
            catch (InterruptedException ex) {
                Logger.getLogger(XChessFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            final Actions aktion54 = XChessFrame.aktion;
            Actions.sendEngineCmd("white", "position startpos");
            final Actions aktion55 = XChessFrame.aktion;
            final String color = "white";
            final StringBuilder append7 = new StringBuilder().append("go depth ");
            final Actions aktion56 = XChessFrame.aktion;
            Actions.sendEngineCmd(color, append7.append(Actions.Depth).toString());
            System.out.println("========================= RUN WHITE ===========================");
        }
    }
    
    @Override
    public BoardUI getBoardUI() {
        return XChessFrame.boardUI;
    }
    
    @Override
    public ChessClock getChessClock() {
        return XChessFrame.chessClock;
    }
    
    @Override
    public MoveListUI getMoveListUI() {
        return XChessFrame.moveListUI;
    }
    
    public void changeLF() {
        final String changeLook = (String)JOptionPane.showInputDialog(this, "Choose Skin Here:", "Select Skin", 3, new ImageIcon(this.getClass().getResource("/SJCE/img/select-by-color-32.png")), XChessFrame.lookAndFeelsDisplay.toArray(), null);
        if (changeLook != null) {
            for (int a = 0; a < XChessFrame.lookAndFeelsDisplay.size(); ++a) {
                if (changeLook.equals(XChessFrame.lookAndFeelsDisplay.get(a))) {
                    try {
                        final Actions aktion = XChessFrame.aktion;
                        Actions.currentLAF = XChessFrame.lookAndFeelsRealNames.get(a);
                        UIManager.setLookAndFeel(XChessFrame.lookAndFeelsRealNames.get(a));
                        SwingUtilities.updateComponentTreeUI(this);
                        this.pack();
                        XChessFrame.borderPanel.updateUI();
                        break;
                    }
                    catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex3) {
                        final Exception ex2;
                        final Exception ex = ex2;
                        System.err.println(ex);
                        ex.printStackTrace(System.err);
                    }
                }
            }
        }
    }
    
    public void changeCE(final String colorCE) {
        String changeLook = "";
        switch (colorCE) {
            case "white": {
                changeLook = (String)JOptionPane.showInputDialog(this, "Choose White Player Here:", "Select White Player", 3, new ImageIcon(this.getClass().getResource("/SJCE/img/knight-white-32.png")), XChessFrame.selectCE.toArray(), null);
                if (changeLook != null) {
                    for (int a = 0; a < XChessFrame.selectCE.size(); ++a) {
                        if (changeLook.equals(XChessFrame.selectCE.get(a))) {
                            final Actions aktion = XChessFrame.aktion;
                            Actions.whitePlayerCE = XChessFrame.selectCE.get(a);
                            final JComboBox<String> comboWPlayerCE = XChessFrame.comboWPlayerCE;
                            final Actions aktion2 = XChessFrame.aktion;
                            comboWPlayerCE.setSelectedItem(Actions.whitePlayerCE);
                            break;
                        }
                    }
                    break;
                }
                break;
            }
            case "black": {
                changeLook = (String)JOptionPane.showInputDialog(this, "Choose Black Player Here:", "Select Black Player", 3, new ImageIcon(this.getClass().getResource("/SJCE/img/knight-black-32.png")), XChessFrame.selectCE.toArray(), null);
                if (changeLook != null) {
                    for (int a = 0; a < XChessFrame.selectCE.size(); ++a) {
                        if (changeLook.equals(XChessFrame.selectCE.get(a))) {
                            final Actions aktion3 = XChessFrame.aktion;
                            Actions.blackPlayerCE = XChessFrame.selectCE.get(a);
                            final JComboBox<String> comboBPlayerCE = XChessFrame.comboBPlayerCE;
                            final Actions aktion4 = XChessFrame.aktion;
                            comboBPlayerCE.setSelectedItem(Actions.blackPlayerCE);
                            break;
                        }
                    }
                    break;
                }
                break;
            }
        }
    }
    
    public void loadBoardTheme() {
        for (int i = 1; i < 3; ++i) {
            XChessFrame.boardUI.chessTheme.loadTheme();
            XChessFrame.boardUI.chessTheme.adjustTheme(XChessFrame.boardUI.getSize());
            XChessFrame.boardUI.update(XChessFrame.SQUARES);
            XChessFrame.borderPanel.repaint();
            XChessFrame.borderPanel.updateUI();
            XChessFrame.boardUI.repaint();
            XChessFrame.boardUI.updateUI();
            SwingUtilities.updateComponentTreeUI(this);
            XChessFrame.boardUI.update(XChessFrame.SQUARES);
            XChessFrame.boardUI.repaint();
            XChessFrame.boardUI.updateUI();
        }
    }
    
    public void changeBoardTheme() {
        final BoardThemeSelect bts = new BoardThemeSelect(XChessFrame.frame, true);
        bts.setVisible(true);
    }
    
    private void initComponents() {
        this.verticalSplit = new JSplitPane();
        XChessFrame.scrollOutputArea = new JScrollPane();
        XChessFrame.outputArea = new JTextArea();
        this.horizontalSplit = new JSplitPane();
        XChessFrame.sidePanel = new JPanel();
        XChessFrame.scrollMoveList = new JScrollPane();
        XChessFrame.borderPanel = new JPanel();
        this.jToolBar2 = new JToolBar();
        this.jToolBar1 = new JToolBar();
        this.jButton3 = new JButton();
        XChessFrame.bUndoLast = new JButton();
        this.jButton1 = new JButton();
        this.bSelectWhite = new JButton();
        this.bChangeSkin = new JButton();
        XChessFrame.bBoardTheme = new JButton();
        this.bLinks = new JButton();
        this.jButton2 = new JButton();
        XChessFrame.bAbout = new JButton();
        this.jToolBar3 = new JToolBar();
        this.bNew = new JButton();
        this.bKillAll = new JButton();
        this.bSaveCfg = new JButton();
        XChessFrame.bSoundMixer = new JButton();
        XChessFrame.bUseSound = new JToggleButton();
        XChessFrame.bcomboMode = new JComboBox<String>();
        XChessFrame.bcomboDepth = new JComboBox<String>();
        XChessFrame.bcomboTime = new JComboBox();
        XChessFrame.bUseClock = new JToggleButton();
        this.bSendWhite = new JButton();
        this.bSendBlack = new JButton();
        XChessFrame.comboWPlayerCE = new JComboBox<String>();
        this.jSeparator2 = new JToolBar.Separator();
        XChessFrame.comboBPlayerCE = new JComboBox<String>();
        this.jSeparator1 = new JToolBar.Separator();
        this.jMenuBar1 = new JMenuBar();
        this.mFile = new JMenu();
        this.mNewGame = new JMenuItem();
        this.jMenuItem2 = new JMenuItem();
        this.mExit = new JMenuItem();
        this.mOptions = new JMenu();
        this.mTimeMenu = new JMenu();
        XChessFrame.mUseClock = new JCheckBoxMenuItem();
        XChessFrame.mTime = new JMenu();
        XChessFrame.mTime05 = new JRadioButtonMenuItem();
        XChessFrame.mTime10 = new JRadioButtonMenuItem();
        XChessFrame.mTime15 = new JRadioButtonMenuItem();
        XChessFrame.mTime20 = new JRadioButtonMenuItem();
        XChessFrame.mTime25 = new JRadioButtonMenuItem();
        XChessFrame.mTime30 = new JRadioButtonMenuItem();
        this.jMenu1 = new JMenu();
        XChessFrame.mUseSound = new JCheckBoxMenuItem();
        this.jMenuItem3 = new JMenuItem();
        this.mBoardTheme = new JMenuItem();
        this.mChangeSkin = new JMenuItem();
        this.mSaveCfg = new JMenuItem();
        this.mPlayers = new JMenu();
        this.mSelectCEwhite = new JMenuItem();
        this.mSelectCEblack = new JMenuItem();
        this.mChessEngines = new JMenu();
        this.mEngineConfig = new JMenu();
        this.mEngineMode = new JMenu();
        XChessFrame.mModeEasy = new JRadioButtonMenuItem();
        XChessFrame.mModeHard = new JRadioButtonMenuItem();
        this.mEngineDepth = new JMenu();
        XChessFrame.mDepth2 = new JRadioButtonMenuItem();
        XChessFrame.mDepth3 = new JRadioButtonMenuItem();
        XChessFrame.mDepth4 = new JRadioButtonMenuItem();
        XChessFrame.mDepth5 = new JRadioButtonMenuItem();
        XChessFrame.mDepth6 = new JRadioButtonMenuItem();
        XChessFrame.mDepth7 = new JRadioButtonMenuItem();
        XChessFrame.mDepth8 = new JRadioButtonMenuItem();
        XChessFrame.mDepth9 = new JRadioButtonMenuItem();
        this.mSendWhite = new JMenuItem();
        this.mSendBlack = new JMenuItem();
        this.mKillAll = new JMenuItem();
        XChessFrame.mUndoLast = new JMenuItem();
        this.mInfo = new JMenu();
        this.jMenuItem4 = new JMenuItem();
        this.jMenuItem5 = new JMenuItem();
        this.jMenuItem1 = new JMenuItem();
        this.setDefaultCloseOperation(3);
        this.setMinimumSize(new Dimension(610, 650));
        this.setUndecorated(true);
        this.setResizable(false);
        this.verticalSplit.setDividerLocation(415);
        this.verticalSplit.setDividerSize(0);
        this.verticalSplit.setOrientation(0);
        this.verticalSplit.setEnabled(false);
        this.verticalSplit.setMaximumSize(new Dimension(550, 550));
        this.verticalSplit.setMinimumSize(new Dimension(550, 550));
        this.verticalSplit.setOpaque(false);
        this.verticalSplit.setPreferredSize(new Dimension(550, 550));
        XChessFrame.scrollOutputArea.setBorder(BorderFactory.createTitledBorder("Engine Output"));
        XChessFrame.outputArea.setEditable(false);
        XChessFrame.outputArea.setColumns(20);
        XChessFrame.outputArea.setRows(5);
        XChessFrame.outputArea.setMaximumSize(new Dimension(102, 62));
        XChessFrame.outputArea.setMinimumSize(new Dimension(102, 62));
        XChessFrame.scrollOutputArea.setViewportView(XChessFrame.outputArea);
        this.verticalSplit.setBottomComponent(XChessFrame.scrollOutputArea);
        XChessFrame.scrollOutputArea.getAccessibleContext().setAccessibleParent(this.verticalSplit);
        this.horizontalSplit.setDividerLocation(415);
        this.horizontalSplit.setDividerSize(0);
        this.horizontalSplit.setEnabled(false);
        this.horizontalSplit.setOpaque(false);
        XChessFrame.sidePanel.setBorder(BorderFactory.createTitledBorder("Moves"));
        XChessFrame.sidePanel.setMaximumSize(new Dimension(120, 450));
        XChessFrame.sidePanel.setMinimumSize(new Dimension(120, 450));
        XChessFrame.sidePanel.setOpaque(false);
        XChessFrame.sidePanel.setPreferredSize(new Dimension(120, 450));
        final GroupLayout sidePanelLayout = new GroupLayout(XChessFrame.sidePanel);
        XChessFrame.sidePanel.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(sidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(XChessFrame.scrollMoveList, -1, 417, 32767));
        sidePanelLayout.setVerticalGroup(sidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(XChessFrame.scrollMoveList, GroupLayout.Alignment.TRAILING, -1, 428, 32767));
        this.horizontalSplit.setRightComponent(XChessFrame.sidePanel);
        XChessFrame.sidePanel.getAccessibleContext().setAccessibleParent(this.horizontalSplit);
        XChessFrame.borderPanel.setBorder(BorderFactory.createTitledBorder("Chess Board"));
        XChessFrame.borderPanel.setDoubleBuffered(false);
        XChessFrame.borderPanel.setMaximumSize(new Dimension(410, 410));
        XChessFrame.borderPanel.setMinimumSize(new Dimension(410, 410));
        XChessFrame.borderPanel.setPreferredSize(new Dimension(410, 410));
        final GroupLayout borderPanelLayout = new GroupLayout(XChessFrame.borderPanel);
        XChessFrame.borderPanel.setLayout(borderPanelLayout);
        borderPanelLayout.setHorizontalGroup(borderPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 404, 32767));
        borderPanelLayout.setVerticalGroup(borderPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 428, 32767));
        this.horizontalSplit.setLeftComponent(XChessFrame.borderPanel);
        this.verticalSplit.setTopComponent(this.horizontalSplit);
        this.horizontalSplit.getAccessibleContext().setAccessibleParent(this.verticalSplit);
        this.getContentPane().add(this.verticalSplit, "Center");
        this.jToolBar2.setFloatable(false);
        this.jToolBar2.setOrientation(1);
        this.jToolBar1.setFloatable(false);
        this.jButton3.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/log-24-blue.png")));
        this.jButton3.setToolTipText("Show Game Log in separate window");
        this.jButton3.setFocusable(false);
        this.jButton3.setHorizontalTextPosition(0);
        this.jButton3.setVerticalTextPosition(3);
        this.jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.jButton3ActionPerformed(evt);
            }
        });
        this.jToolBar1.add(this.jButton3);
        XChessFrame.bUndoLast.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/undo-red-24.png")));
        XChessFrame.bUndoLast.setToolTipText("Undo Last One Move");
        XChessFrame.bUndoLast.setFocusable(false);
        XChessFrame.bUndoLast.setHorizontalTextPosition(0);
        XChessFrame.bUndoLast.setVerticalTextPosition(3);
        XChessFrame.bUndoLast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.bUndoLastActionPerformed(evt);
            }
        });
        this.jToolBar1.add(XChessFrame.bUndoLast);
        this.jButton1.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/knight-black-24.png")));
        this.jButton1.setToolTipText("Select Black Player Chess Engine");
        this.jButton1.setFocusable(false);
        this.jButton1.setHorizontalTextPosition(0);
        this.jButton1.setVerticalTextPosition(3);
        this.jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.jButton1ActionPerformed(evt);
            }
        });
        this.jToolBar1.add(this.jButton1);
        this.bSelectWhite.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/knight-white-24.png")));
        this.bSelectWhite.setToolTipText("Select White Player Chess Engine");
        this.bSelectWhite.setFocusable(false);
        this.bSelectWhite.setHorizontalTextPosition(0);
        this.bSelectWhite.setVerticalTextPosition(3);
        this.bSelectWhite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.bSelectWhiteActionPerformed(evt);
            }
        });
        this.jToolBar1.add(this.bSelectWhite);
        this.bChangeSkin.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/skin_color_chooser-24.png")));
        this.bChangeSkin.setToolTipText("Change Skin");
        this.bChangeSkin.setFocusable(false);
        this.bChangeSkin.setHorizontalTextPosition(0);
        this.bChangeSkin.setVerticalTextPosition(3);
        this.bChangeSkin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.bChangeSkinActionPerformed(evt);
            }
        });
        this.jToolBar1.add(this.bChangeSkin);
        XChessFrame.bBoardTheme.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/color-swatch-24.png")));
        XChessFrame.bBoardTheme.setToolTipText("Select Board Theme");
        XChessFrame.bBoardTheme.setFocusable(false);
        XChessFrame.bBoardTheme.setHorizontalTextPosition(0);
        XChessFrame.bBoardTheme.setVerticalTextPosition(3);
        XChessFrame.bBoardTheme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.bBoardThemeActionPerformed(evt);
            }
        });
        this.jToolBar1.add(XChessFrame.bBoardTheme);
        this.bLinks.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/x-b-24.png")));
        this.bLinks.setToolTipText("Xboard Engine Links & Ratings");
        this.bLinks.setFocusable(false);
        this.bLinks.setHorizontalTextPosition(0);
        this.bLinks.setVerticalTextPosition(3);
        this.bLinks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.bLinksActionPerformed(evt);
            }
        });
        this.jToolBar1.add(this.bLinks);
        this.jButton2.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/U-blue-24.png")));
        this.jButton2.setToolTipText("Uci Engines Links & Ratings");
        this.jButton2.setFocusable(false);
        this.jButton2.setHorizontalTextPosition(0);
        this.jButton2.setVerticalTextPosition(3);
        this.jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.jButton2ActionPerformed(evt);
            }
        });
        this.jToolBar1.add(this.jButton2);
        XChessFrame.bAbout.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/info-book-green.png")));
        XChessFrame.bAbout.setToolTipText("About");
        XChessFrame.bAbout.setFocusable(false);
        XChessFrame.bAbout.setHorizontalTextPosition(0);
        XChessFrame.bAbout.setVerticalTextPosition(3);
        XChessFrame.bAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.bAboutActionPerformed(evt);
            }
        });
        this.jToolBar1.add(XChessFrame.bAbout);
        this.jToolBar2.add(this.jToolBar1);
        this.jToolBar3.setFloatable(false);
        this.bNew.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/new.png")));
        this.bNew.setToolTipText("New Game");
        this.bNew.setFocusable(false);
        this.bNew.setHorizontalTextPosition(0);
        this.bNew.setVerticalTextPosition(3);
        this.bNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.bNewActionPerformed(evt);
            }
        });
        this.jToolBar3.add(this.bNew);
        this.bKillAll.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/stop-red.png")));
        this.bKillAll.setToolTipText("Kill All Engines and reset Board");
        this.bKillAll.setFocusable(false);
        this.bKillAll.setHorizontalTextPosition(0);
        this.bKillAll.setVerticalTextPosition(3);
        this.bKillAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.bKillAllActionPerformed(evt);
            }
        });
        this.jToolBar3.add(this.bKillAll);
        this.bSaveCfg.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/floppy_disk_green.png")));
        this.bSaveCfg.setToolTipText("Save Config");
        this.bSaveCfg.setFocusable(false);
        this.bSaveCfg.setHorizontalTextPosition(0);
        this.bSaveCfg.setVerticalTextPosition(3);
        this.bSaveCfg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.bSaveCfgActionPerformed(evt);
            }
        });
        this.jToolBar3.add(this.bSaveCfg);
        XChessFrame.bSoundMixer.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/audio_volume_high.png")));
        XChessFrame.bSoundMixer.setToolTipText("Sound Volume");
        XChessFrame.bSoundMixer.setFocusable(false);
        XChessFrame.bSoundMixer.setHorizontalTextPosition(0);
        XChessFrame.bSoundMixer.setVerticalTextPosition(3);
        XChessFrame.bSoundMixer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.bSoundMixerActionPerformed(evt);
            }
        });
        this.jToolBar3.add(XChessFrame.bSoundMixer);
        XChessFrame.bUseSound.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/sound-add-icon.png")));
        XChessFrame.bUseSound.setToolTipText("Use Sound");
        XChessFrame.bUseSound.setFocusable(false);
        XChessFrame.bUseSound.setHorizontalTextPosition(0);
        XChessFrame.bUseSound.setVerticalTextPosition(3);
        XChessFrame.bUseSound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.bUseSoundActionPerformed(evt);
            }
        });
        this.jToolBar3.add(XChessFrame.bUseSound);
        XChessFrame.bcomboMode.setModel(new DefaultComboBoxModel<String>(new String[] { "hard", "easy" }));
        XChessFrame.bcomboMode.setToolTipText("Engine mode");
        XChessFrame.bcomboMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.bcomboModeActionPerformed(evt);
            }
        });
        this.jToolBar3.add(XChessFrame.bcomboMode);
        XChessFrame.bcomboDepth.setModel(new DefaultComboBoxModel<String>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8" }));
        XChessFrame.bcomboDepth.setToolTipText("Search Depth ");
        XChessFrame.bcomboDepth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.bcomboDepthActionPerformed(evt);
            }
        });
        this.jToolBar3.add(XChessFrame.bcomboDepth);
        XChessFrame.bcomboTime.setModel(new DefaultComboBoxModel<String>(new String[] { "5", "10", "15", "20", "25", "30" }));
        XChessFrame.bcomboTime.setToolTipText("Set Time (min)");
        XChessFrame.bcomboTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.bcomboTimeActionPerformed(evt);
            }
        });
        this.jToolBar3.add(XChessFrame.bcomboTime);
        XChessFrame.bUseClock.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/clock-plus-24.png")));
        XChessFrame.bUseClock.setToolTipText("Use Clock");
        XChessFrame.bUseClock.setFocusable(false);
        XChessFrame.bUseClock.setHorizontalTextPosition(0);
        XChessFrame.bUseClock.setVerticalTextPosition(3);
        XChessFrame.bUseClock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.bUseClockActionPerformed(evt);
            }
        });
        this.jToolBar3.add(XChessFrame.bUseClock);
        this.bSendWhite.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/send-white-24-green.png")));
        this.bSendWhite.setToolTipText("Send Command to White CE");
        this.bSendWhite.setFocusable(false);
        this.bSendWhite.setHorizontalTextPosition(0);
        this.bSendWhite.setVerticalTextPosition(3);
        this.bSendWhite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.bSendWhiteActionPerformed(evt);
            }
        });
        this.jToolBar3.add(this.bSendWhite);
        this.bSendBlack.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/send-black-24-right.png")));
        this.bSendBlack.setToolTipText("Send Command to Black CE");
        this.bSendBlack.setFocusable(false);
        this.bSendBlack.setHorizontalTextPosition(0);
        this.bSendBlack.setVerticalTextPosition(3);
        this.bSendBlack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.bSendBlackActionPerformed(evt);
            }
        });
        this.jToolBar3.add(this.bSendBlack);
        XChessFrame.comboWPlayerCE.setModel(new DefaultComboBoxModel<String>(new String[] { "Animats", "ArabianKnight ", "BremboCE", "CaveChess", "CupCake", "ChessBotX       ", "DeepBrutePos", "Frittle", "FrankWalter", "Gladiator", "GNU Chess", "Jchess", "Javalin", "KingsOut      ", "OliThink", "Tiffanys" }));
        XChessFrame.comboWPlayerCE.setToolTipText("Select White Player Chess Engine");
        XChessFrame.comboWPlayerCE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.comboWPlayerCEActionPerformed(evt);
            }
        });
        this.jToolBar3.add(XChessFrame.comboWPlayerCE);
        this.jToolBar3.add(this.jSeparator2);
        XChessFrame.comboBPlayerCE.setModel(new DefaultComboBoxModel<String>(new String[] { "Animats", "ArabianKnight ", "BremboCE", "CaveChess", "CupCake", "ChessBotX       ", "DeepBrutePos", "Frittle", "FrankWalter", "Gladiator", "GNU Chess", "Jchess", "Javalin", "KingsOut      ", "OliThink", "Tiffanys" }));
        XChessFrame.comboBPlayerCE.setToolTipText("Select Black Player Chess Engine");
        XChessFrame.comboBPlayerCE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.comboBPlayerCEActionPerformed(evt);
            }
        });
        this.jToolBar3.add(XChessFrame.comboBPlayerCE);
        this.jToolBar3.add(this.jSeparator1);
        this.jToolBar2.add(this.jToolBar3);
        this.getContentPane().add(this.jToolBar2, "North");
        this.mFile.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/Actions-document-open-icon.png")));
        this.mFile.setText("File");
        this.mNewGame.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/new.png")));
        this.mNewGame.setText("New Game");
        this.mNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.mNewGameActionPerformed(evt);
            }
        });
        this.mFile.add(this.mNewGame);
        this.jMenuItem2.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/log-16-blue.png")));
        this.jMenuItem2.setText("View Game Log");
        this.jMenuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.jMenuItem2ActionPerformed(evt);
            }
        });
        this.mFile.add(this.jMenuItem2);
        this.mExit.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/quit.png")));
        this.mExit.setText("Exit");
        this.mExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.mExitActionPerformed(evt);
            }
        });
        this.mFile.add(this.mExit);
        this.jMenuBar1.add(this.mFile);
        this.mOptions.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/application-16.png")));
        this.mOptions.setText("Options");
        this.mTimeMenu.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/time-16.png")));
        this.mTimeMenu.setText("Time");
        XChessFrame.mUseClock.setText("Use Clock");
        XChessFrame.mUseClock.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/Alarm-clock-16.png")));
        XChessFrame.mUseClock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.mUseClockActionPerformed(evt);
            }
        });
        this.mTimeMenu.add(XChessFrame.mUseClock);
        XChessFrame.mTime.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/calendar_select_day.png")));
        XChessFrame.mTime.setText("Set Time (min)");
        XChessFrame.mTime05.setText("5");
        XChessFrame.mTime05.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.mTime05ActionPerformed(evt);
            }
        });
        XChessFrame.mTime.add(XChessFrame.mTime05);
        XChessFrame.mTime10.setText("10");
        XChessFrame.mTime10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.mTime10ActionPerformed(evt);
            }
        });
        XChessFrame.mTime.add(XChessFrame.mTime10);
        XChessFrame.mTime15.setText("15");
        XChessFrame.mTime15.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.mTime15ActionPerformed(evt);
            }
        });
        XChessFrame.mTime.add(XChessFrame.mTime15);
        XChessFrame.mTime20.setText("20");
        XChessFrame.mTime20.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.mTime20ActionPerformed(evt);
            }
        });
        XChessFrame.mTime.add(XChessFrame.mTime20);
        XChessFrame.mTime25.setText("25");
        XChessFrame.mTime25.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.mTime25ActionPerformed(evt);
            }
        });
        XChessFrame.mTime.add(XChessFrame.mTime25);
        XChessFrame.mTime30.setText("30");
        XChessFrame.mTime30.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.mTime30ActionPerformed(evt);
            }
        });
        XChessFrame.mTime.add(XChessFrame.mTime30);
        this.mTimeMenu.add(XChessFrame.mTime);
        this.mOptions.add(this.mTimeMenu);
        this.jMenu1.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/sound-juk.png")));
        this.jMenu1.setText("Sound Control");
        XChessFrame.mUseSound.setSelected(true);
        XChessFrame.mUseSound.setText("Use Sound");
        XChessFrame.mUseSound.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/document_music_playlist.png")));
        XChessFrame.mUseSound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.mUseSoundActionPerformed(evt);
            }
        });
        this.jMenu1.add(XChessFrame.mUseSound);
        this.jMenuItem3.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/volume_loud.png")));
        this.jMenuItem3.setText("Sound Volume");
        this.jMenuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.jMenuItem3ActionPerformed(evt);
            }
        });
        this.jMenu1.add(this.jMenuItem3);
        this.mOptions.add(this.jMenu1);
        this.mBoardTheme.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/color_swatch.png")));
        this.mBoardTheme.setText("Change Board Theme");
        this.mBoardTheme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.mBoardThemeActionPerformed(evt);
            }
        });
        this.mOptions.add(this.mBoardTheme);
        this.mChangeSkin.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/gnome_color_chooser.png")));
        this.mChangeSkin.setText("Change Skin");
        this.mChangeSkin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.mChangeSkinActionPerformed(evt);
            }
        });
        this.mOptions.add(this.mChangeSkin);
        this.mSaveCfg.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/save.png")));
        this.mSaveCfg.setText("Save Config");
        this.mSaveCfg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.mSaveCfgActionPerformed(evt);
            }
        });
        this.mOptions.add(this.mSaveCfg);
        this.jMenuBar1.add(this.mOptions);
        this.mPlayers.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/play_green-1.png")));
        this.mPlayers.setText("Players");
        this.mSelectCEwhite.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/white_knight-16.png")));
        this.mSelectCEwhite.setText("Select White Player Chess Engine");
        this.mSelectCEwhite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.mSelectCEwhiteActionPerformed(evt);
            }
        });
        this.mPlayers.add(this.mSelectCEwhite);
        this.mSelectCEblack.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/black-knight-16.png")));
        this.mSelectCEblack.setText("Select Black Player Chess Engine");
        this.mSelectCEblack.setToolTipText("");
        this.mSelectCEblack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.mSelectCEblackActionPerformed(evt);
            }
        });
        this.mPlayers.add(this.mSelectCEblack);
        this.jMenuBar1.add(this.mPlayers);
        this.mChessEngines.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/Knight-Yellow-16.png")));
        this.mChessEngines.setText("Engines");
        this.mEngineConfig.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/options-16.png")));
        this.mEngineConfig.setText("Engine Config");
        this.mEngineConfig.setToolTipText("for Frittle/KingsOut/ArabianKnight");
        this.mEngineMode.setText("Engine Mode");
        this.mEngineMode.setToolTipText("");
        XChessFrame.mModeEasy.setText("Easy ( pondering OFF )");
        XChessFrame.mModeEasy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.mModeEasyActionPerformed(evt);
            }
        });
        this.mEngineMode.add(XChessFrame.mModeEasy);
        XChessFrame.mModeHard.setText("Hard ( pondering ON )");
        XChessFrame.mModeHard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.mModeHardActionPerformed(evt);
            }
        });
        this.mEngineMode.add(XChessFrame.mModeHard);
        this.mEngineConfig.add(this.mEngineMode);
        this.mEngineDepth.setText("Engine Search Depth");
        this.mEngineDepth.setToolTipText("");
        XChessFrame.mDepth2.setText("2");
        XChessFrame.mDepth2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.mDepth2ActionPerformed(evt);
            }
        });
        this.mEngineDepth.add(XChessFrame.mDepth2);
        XChessFrame.mDepth3.setText("3");
        XChessFrame.mDepth3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.mDepth3ActionPerformed(evt);
            }
        });
        this.mEngineDepth.add(XChessFrame.mDepth3);
        XChessFrame.mDepth4.setText("4");
        XChessFrame.mDepth4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.mDepth4ActionPerformed(evt);
            }
        });
        this.mEngineDepth.add(XChessFrame.mDepth4);
        XChessFrame.mDepth5.setText("5");
        XChessFrame.mDepth5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.mDepth5ActionPerformed(evt);
            }
        });
        this.mEngineDepth.add(XChessFrame.mDepth5);
        XChessFrame.mDepth6.setText("6");
        XChessFrame.mDepth6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.mDepth6ActionPerformed(evt);
            }
        });
        this.mEngineDepth.add(XChessFrame.mDepth6);
        XChessFrame.mDepth7.setText("7");
        XChessFrame.mDepth7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.mDepth7ActionPerformed(evt);
            }
        });
        this.mEngineDepth.add(XChessFrame.mDepth7);
        XChessFrame.mDepth8.setText("8");
        XChessFrame.mDepth8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.mDepth8ActionPerformed(evt);
            }
        });
        this.mEngineDepth.add(XChessFrame.mDepth8);
        XChessFrame.mDepth9.setText("9");
        XChessFrame.mDepth9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.mDepth9ActionPerformed(evt);
            }
        });
        this.mEngineDepth.add(XChessFrame.mDepth9);
        this.mEngineConfig.add(this.mEngineDepth);
        this.mChessEngines.add(this.mEngineConfig);
        this.mSendWhite.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/send-white-16-green.png")));
        this.mSendWhite.setText("Send Command to White CE");
        this.mSendWhite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.mSendWhiteActionPerformed(evt);
            }
        });
        this.mChessEngines.add(this.mSendWhite);
        this.mSendBlack.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/send-black-16-right.png")));
        this.mSendBlack.setText("Send Command to Black CE");
        this.mSendBlack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.mSendBlackActionPerformed(evt);
            }
        });
        this.mChessEngines.add(this.mSendBlack);
        this.mKillAll.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/stop_red.png")));
        this.mKillAll.setText("Kill All Engine Process");
        this.mKillAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.mKillAllActionPerformed(evt);
            }
        });
        this.mChessEngines.add(this.mKillAll);
        XChessFrame.mUndoLast.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/undo-red-16.png")));
        XChessFrame.mUndoLast.setText("Undo Last One Move");
        XChessFrame.mUndoLast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.mUndoLastActionPerformed(evt);
            }
        });
        this.mChessEngines.add(XChessFrame.mUndoLast);
        this.jMenuBar1.add(this.mChessEngines);
        this.mInfo.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/info-green-16.png")));
        this.mInfo.setText("Info");
        this.jMenuItem4.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/x-b-16.png")));
        this.jMenuItem4.setText("Xboard Engines Links & Ratings");
        this.jMenuItem4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.jMenuItem4ActionPerformed(evt);
            }
        });
        this.mInfo.add(this.jMenuItem4);
        this.jMenuItem5.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/U-blue-16.png")));
        this.jMenuItem5.setText("Uci Engines Links & Ratings");
        this.jMenuItem5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.jMenuItem5ActionPerformed(evt);
            }
        });
        this.mInfo.add(this.jMenuItem5);
        this.jMenuItem1.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/help-green-16.png")));
        this.jMenuItem1.setText("About");
        this.jMenuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                XChessFrame.this.jMenuItem1ActionPerformed(evt);
            }
        });
        this.mInfo.add(this.jMenuItem1);
        this.jMenuBar1.add(this.mInfo);
        this.setJMenuBar(this.jMenuBar1);
        this.pack();
        this.setLocationRelativeTo(null);
    }
    
    private void mExitActionPerformed(final ActionEvent evt) {
        final int r = JOptionPane.showConfirmDialog(XChessFrame.frame, "Really Quit ?", "Quit ?", 0);
        if (r == 0) {
            System.exit(0);
        }
    }
    
    private void mNewGameActionPerformed(final ActionEvent evt) {
        this.newGame(0, 1);
    }
    
    private void mModeEasyActionPerformed(final ActionEvent evt) {
        final Actions aktion = XChessFrame.aktion;
        Actions.Mode = "easy";
        XChessFrame.mModeEasy.setSelected(true);
        XChessFrame.mModeHard.setSelected(false);
        final JComboBox<String> bcomboMode = XChessFrame.bcomboMode;
        final Actions aktion2 = XChessFrame.aktion;
        bcomboMode.setSelectedItem(Actions.Mode);
    }
    
    private void mModeHardActionPerformed(final ActionEvent evt) {
        final Actions aktion = XChessFrame.aktion;
        Actions.Mode = "hard";
        XChessFrame.mModeEasy.setSelected(false);
        XChessFrame.mModeHard.setSelected(true);
        final JComboBox<String> bcomboMode = XChessFrame.bcomboMode;
        final Actions aktion2 = XChessFrame.aktion;
        bcomboMode.setSelectedItem(Actions.Mode);
    }
    
    private void mDepth9ActionPerformed(final ActionEvent evt) {
        final Actions aktion = XChessFrame.aktion;
        Actions.changeDepth(9);
    }
    
    private void mDepth2ActionPerformed(final ActionEvent evt) {
        final Actions aktion = XChessFrame.aktion;
        Actions.changeDepth(2);
    }
    
    private void mDepth3ActionPerformed(final ActionEvent evt) {
        final Actions aktion = XChessFrame.aktion;
        Actions.changeDepth(3);
    }
    
    private void mDepth4ActionPerformed(final ActionEvent evt) {
        final Actions aktion = XChessFrame.aktion;
        Actions.changeDepth(4);
    }
    
    private void mDepth5ActionPerformed(final ActionEvent evt) {
        final Actions aktion = XChessFrame.aktion;
        Actions.changeDepth(5);
    }
    
    private void mDepth6ActionPerformed(final ActionEvent evt) {
        final Actions aktion = XChessFrame.aktion;
        Actions.changeDepth(6);
    }
    
    private void mDepth7ActionPerformed(final ActionEvent evt) {
        final Actions aktion = XChessFrame.aktion;
        Actions.changeDepth(7);
    }
    
    private void mChangeSkinActionPerformed(final ActionEvent evt) {
        this.changeLF();
        XChessFrame.borderPanel.add(XChessFrame.boardUI, "Center");
    }
    
    private void mTime05ActionPerformed(final ActionEvent evt) {
        final Actions aktion = XChessFrame.aktion;
        Actions.changeTime(5);
    }
    
    private void mTime10ActionPerformed(final ActionEvent evt) {
        final Actions aktion = XChessFrame.aktion;
        Actions.changeTime(10);
    }
    
    private void mTime15ActionPerformed(final ActionEvent evt) {
        final Actions aktion = XChessFrame.aktion;
        Actions.changeTime(15);
    }
    
    private void mTime20ActionPerformed(final ActionEvent evt) {
        final Actions aktion = XChessFrame.aktion;
        Actions.changeTime(20);
    }
    
    private void mTime25ActionPerformed(final ActionEvent evt) {
        final Actions aktion = XChessFrame.aktion;
        Actions.changeTime(25);
    }
    
    private void mTime30ActionPerformed(final ActionEvent evt) {
        final Actions aktion = XChessFrame.aktion;
        Actions.changeTime(30);
    }
    
    private void jMenuItem1ActionPerformed(final ActionEvent evt) {
        XChessFrame.aktion.about(this);
    }
    
    private void mUseClockActionPerformed(final ActionEvent evt) {
        XChessFrame.aktion.useTime();
    }
    
    private void mSaveCfgActionPerformed(final ActionEvent evt) {
        AppCfgPref.Save();
    }
    
    private void mSelectCEwhiteActionPerformed(final ActionEvent evt) {
        this.changeCE("white");
    }
    
    private void mBoardThemeActionPerformed(final ActionEvent evt) {
        this.changeBoardTheme();
    }
    
    private void mDepth8ActionPerformed(final ActionEvent evt) {
        final Actions aktion = XChessFrame.aktion;
        Actions.changeDepth(8);
    }
    
    private void mKillAllActionPerformed(final ActionEvent evt) {
        XChessFrame.aktion.killAllEngines();
    }
    
    private void jMenuItem4ActionPerformed(final ActionEvent evt) {
        XChessFrame.aktion.LinksX();
    }
    
    private void mSelectCEblackActionPerformed(final ActionEvent evt) {
        this.changeCE("black");
    }
    
    private void jMenuItem3ActionPerformed(final ActionEvent evt) {
        this.MixerSet();
    }
    
    private void mUseSoundActionPerformed(final ActionEvent evt) {
        XChessFrame.aktion.useSoundSwitch();
    }
    
    private void mSendWhiteActionPerformed(final ActionEvent evt) {
        XChessFrame.aktion.sendEngineCmd("white");
    }
    
    private void mSendBlackActionPerformed(final ActionEvent evt) {
        XChessFrame.aktion.sendEngineCmd("black");
    }
    
    private void bLinksActionPerformed(final ActionEvent evt) {
        XChessFrame.aktion.LinksX();
    }
    
    private void bAboutActionPerformed(final ActionEvent evt) {
        XChessFrame.aktion.about(this);
    }
    
    private void bNewActionPerformed(final ActionEvent evt) {
        XChessFrame.frame.newGame(0, 1);
    }
    
    private void bKillAllActionPerformed(final ActionEvent evt) {
        XChessFrame.aktion.killAllEngines();
    }
    
    private void bSaveCfgActionPerformed(final ActionEvent evt) {
        AppCfgPref.Save();
    }
    
    private void bBoardThemeActionPerformed(final ActionEvent evt) {
        this.changeBoardTheme();
    }
    
    private void bChangeSkinActionPerformed(final ActionEvent evt) {
        this.changeLF();
        XChessFrame.borderPanel.add(XChessFrame.boardUI, "Center");
    }
    
    private void bcomboModeActionPerformed(final ActionEvent evt) {
        final Actions aktion = XChessFrame.aktion;
        Actions.Mode = XChessFrame.bcomboMode.getSelectedItem().toString();
        final Actions aktion2 = XChessFrame.aktion;
        if (Actions.Mode.equals("hard")) {
            XChessFrame.mModeHard.setSelected(true);
            XChessFrame.mModeEasy.setSelected(false);
        }
        else {
            XChessFrame.mModeHard.setSelected(false);
            XChessFrame.mModeEasy.setSelected(true);
        }
    }
    
    private void bcomboDepthActionPerformed(final ActionEvent evt) {
        final Actions aktion = XChessFrame.aktion;
        Actions.Depth = Integer.parseInt(XChessFrame.bcomboDepth.getSelectedItem().toString());
        final Actions aktion2 = XChessFrame.aktion;
        final Actions aktion3 = XChessFrame.aktion;
        Actions.changeDepth(Actions.Depth);
    }
    
    private void bcomboTimeActionPerformed(final ActionEvent evt) {
        final Actions aktion = XChessFrame.aktion;
        Actions.Time = Integer.parseInt(XChessFrame.bcomboTime.getSelectedItem().toString());
        final Actions aktion2 = XChessFrame.aktion;
        final Actions aktion3 = XChessFrame.aktion;
        Actions.changeTime(Actions.Time);
    }
    
    private void bUseClockActionPerformed(final ActionEvent evt) {
        XChessFrame.aktion.useTime();
    }
    
    private void bSoundMixerActionPerformed(final ActionEvent evt) {
        this.MixerSet();
    }
    
    private void bUseSoundActionPerformed(final ActionEvent evt) {
        XChessFrame.aktion.useSoundSwitch();
    }
    
    private void bSendWhiteActionPerformed(final ActionEvent evt) {
        XChessFrame.aktion.sendEngineCmd("white");
    }
    
    private void comboWPlayerCEActionPerformed(final ActionEvent evt) {
        final Actions aktion = XChessFrame.aktion;
        Actions.viborCE(XChessFrame.comboWPlayerCE.getSelectedItem().toString(), "white");
    }
    
    private void bSendBlackActionPerformed(final ActionEvent evt) {
        XChessFrame.aktion.sendEngineCmd("black");
    }
    
    private void comboBPlayerCEActionPerformed(final ActionEvent evt) {
        final Actions aktion = XChessFrame.aktion;
        Actions.viborCE(XChessFrame.comboBPlayerCE.getSelectedItem().toString(), "black");
    }
    
    private void bSelectWhiteActionPerformed(final ActionEvent evt) {
        this.changeCE("white");
    }
    
    private void jButton1ActionPerformed(final ActionEvent evt) {
        this.changeCE("black");
    }
    
    private void bUndoLastActionPerformed(final ActionEvent evt) {
        final Actions aktion = XChessFrame.aktion;
        Actions.bmUndoLastMoveEventHandler();
    }
    
    private void mUndoLastActionPerformed(final ActionEvent evt) {
        final Actions aktion = XChessFrame.aktion;
        Actions.bmUndoLastMoveEventHandler();
    }
    
    private void jButton2ActionPerformed(final ActionEvent evt) {
        XChessFrame.aktion.LinksU();
    }
    
    private void jMenuItem5ActionPerformed(final ActionEvent evt) {
        XChessFrame.aktion.LinksU();
    }
    
    private void jButton3ActionPerformed(final ActionEvent evt) {
        final Actions aktion = XChessFrame.aktion;
        Actions.logShow();
    }
    
    private void jMenuItem2ActionPerformed(final ActionEvent evt) {
        final Actions aktion = XChessFrame.aktion;
        Actions.logShow();
    }
    
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                XChessFrame.frame = new XChessFrame();
                XChessFrame.chessClock = new ChessClock();
                XChessFrame.frame.getRootPane().setWindowDecorationStyle(1);
                JFrame.setDefaultLookAndFeelDecorated(true);
                JDialog.setDefaultLookAndFeelDecorated(true);
                AppCfgPref.Load();
                XChessFrame.frame.MixerInit();
                XChessFrame.moveListUI = new MoveListUI();
                XChessFrame.boardUI = new BoardUI();
                XChessFrame.moveListUI.setSelectionMode(0);
                XChessFrame.scrollOutputArea.setViewportView(XChessFrame.outputArea);
                XChessFrame.sidePanel.setLayout(new BorderLayout());
                XChessFrame.sidePanel.add(XChessFrame.scrollMoveList, "Center");
                XChessFrame.scrollMoveList.setViewportView(XChessFrame.moveListUI);
                XChessFrame.borderPanel.setLayout(XChessFrame.BL = new BorderLayout());
                XChessFrame.boardUI.setOpaque(true);
                XChessFrame.aktion.appInit();
                final Actions aktion = XChessFrame.aktion;
                Actions.InstallLF();
                final Actions aktion2 = XChessFrame.aktion;
                Actions.setSkin();
                final Actions aktion3 = XChessFrame.aktion;
                final Actions aktion4 = XChessFrame.aktion;
                Actions.viborCE(Actions.whitePlayerCE, "white");
                final Actions aktion5 = XChessFrame.aktion;
                final Actions aktion6 = XChessFrame.aktion;
                Actions.viborCE(Actions.blackPlayerCE, "black");
                XChessFrame.frame.setVisible(true);
                XChessFrame.borderPanel.add(XChessFrame.boardUI, "Center");
                XChessFrame.frame.boardSetSize();
                System.out.println("boardUI = " + XChessFrame.boardUI.getSize());
                System.out.println("borderPanel = " + XChessFrame.borderPanel.getSize());
                new Run_Thread();
            }
        });
    }
    
    static {
        XChessFrame.lookAndFeelsDisplay = new ArrayList<String>();
        XChessFrame.lookAndFeelsRealNames = new ArrayList<String>();
        XChessFrame.aktion = new Actions();
        XChessFrame.selectCE = new ArrayList<String>();
    }
}
