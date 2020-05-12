/*
 * Decompiled with CFR 0.148.
 */
package SJCE;

import SJCE.Cfg.AppCfgPref;
import SJCE.Cfg.BoardThemeSelect;
import SJCE.more.Actions;
import SJCE.more.Log.LogShow;
import SJCE.more.Mixer.ControlSound;
import SJCE.more.Mixer.MixerFrame;
import SJCE.more.Run_Thread;
import SJCE.xgui.Agent.Agent;
import SJCE.xgui.Agent.EngineAgent;
import SJCE.xgui.Agent.EngineAgentExt;
import SJCE.xgui.Agent.UserAgent;
import SJCE.xgui.ChessTheme;
import SJCE.xgui.EngineIO;
import SJCE.xgui.EventObject.EngineEvent;
import SJCE.xgui.Interfaces.EngineAdapter;
import SJCE.xgui.Interfaces.IChessContext;
import SJCE.xgui.Interfaces.IEngineListener;
import SJCE.xgui.Interfaces.IMainFrameConst;
import SJCE.xgui.JList.MoveListUI;
import SJCE.xgui.JPanel.BoardUI;
import SJCE.xgui.JPanel.ChessClock;
import SJCE.xgui.Utility;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.accessibility.Accessible;
import javax.accessibility.AccessibleContext;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

public class XChessFrame
extends JFrame
implements IChessContext,
IMainFrameConst {
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
    public ImageIcon welcomeIcon = new ImageIcon(this.getClass().getResource("/SJCE/img/sjce-130x87.png"));
    public ImageIcon FrameIcon = new ImageIcon(this.getClass().getResource("/SJCE/img/SubFrameIcon.png"));
    public static final String sjceTitle = "SJCE = Strong Java Chess Engines, build 08.08.18";
    public static LogShow logFrame;
    public ButtonGroup mDepthRadioGroup = new ButtonGroup();
    public ButtonGroup mTimeRadioGroup = new ButtonGroup();
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
        this.initComponents();
        this.setTitle(sjceTitle);
        ImageIcon icone = new ImageIcon(this.getClass().getResource("/SJCE/img/SubFrameIcon.png"));
        this.setIconImage(icone.getImage());
        selectCE.addAll(Arrays.asList(selectEnginesArray));
        this.boardSetSize();
        comboWPlayerCE.setModel(new DefaultComboBoxModel<String>(selectEnginesArray));
        comboWPlayerCE.setSelectedItem(Actions.whitePlayerCE);
        comboBPlayerCE.setModel(new DefaultComboBoxModel<String>(selectEnginesArray));
        comboBPlayerCE.setSelectedItem(Actions.blackPlayerCE);
        bcomboDepth.setModel(new DefaultComboBoxModel<String>(arrayDepth));
        bcomboDepth.setSelectedItem("" + Actions.Depth);
        outputArea = new JTextArea(){
            Image image;
            {
                this.image = XChessFrame.this.welcomeIcon.getImage();
                this.setOpaque(false);
            }

            @Override
            public void paint(Graphics g) {
                g.drawImage(this.image, outputArea.getWidth() - 135, 0, this);
                super.paint(g);
            }
        };
        outputArea.setEditable(false);
        outputArea.append("Please, select chess engine and press New Game !");
        this.mDepthRadioGroup.add(mDepth2);
        this.mDepthRadioGroup.add(mDepth3);
        this.mDepthRadioGroup.add(mDepth4);
        this.mDepthRadioGroup.add(mDepth5);
        this.mDepthRadioGroup.add(mDepth6);
        this.mDepthRadioGroup.add(mDepth7);
        this.mDepthRadioGroup.add(mDepth8);
        this.mDepthRadioGroup.add(mDepth9);
        this.mTimeRadioGroup.add(mTime05);
        this.mTimeRadioGroup.add(mTime10);
        this.mTimeRadioGroup.add(mTime15);
        this.mTimeRadioGroup.add(mTime20);
        this.mTimeRadioGroup.add(mTime25);
        this.mTimeRadioGroup.add(mTime30);
    }

    public void MixerInit() {
        if (Actions.currentMixer > 0 & Actions.currentMixer < 33) {
            bSoundMixer.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/audio_volume_low.png")));
        }
        if (Actions.currentMixer > 33 & Actions.currentMixer < 66) {
            bSoundMixer.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/audio_volume_medium.png")));
        }
        if (Actions.currentMixer > 66) {
            bSoundMixer.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/audio_volume_high.png")));
        }
        if (Actions.currentMixer == 0) {
            bSoundMixer.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/audio_volume_muted.png")));
        }
        if (Actions.currentMute.equals("true")) {
            ControlSound.setMasterOutputMute(true);
            System.out.println("Volume set to ZERO = 0");
            bSoundMixer.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/audio_volume_muted.png")));
        }
        try {
            ControlSound.setMasterOutputVolume((float)Actions.currentMixer / 100.0f);
        }
        catch (RuntimeException rr) {
            System.out.println("Master output port not found");
        }
    }

    public void MixerSet() {
        MixerFrame mf = new MixerFrame(frame, true);
        mf.setLocationRelativeTo(frame);
        mf.setVisible(true);
        MixerFrame.MixerFrameSlider.setValue(Actions.currentMixer);
        this.MixerInit();
    }

    public void boardSetSize() {
        borderPanel.setSize(415, 415);
        borderPanel.setPreferredSize(new Dimension(415, 415));
        borderPanel.setMinimumSize(new Dimension(415, 415));
        this.horizontalSplit.setDividerLocation(415);
        this.horizontalSplit.setDividerSize(0);
        this.verticalSplit.setDividerLocation(415);
        this.verticalSplit.setDividerSize(0);
    }

    public void newGame(int agent1, int agent2) {
        Actions.enemyTip = "";
        Actions.gameTip = "";
        Actions.uciAllMovesString = "";
        Actions.enginePromotionFig = "";
        Actions.enginePromotionType = "";
        Actions.blackRivalMovesString = "";
        Actions.whiteRivalMovesString = "";
        aktion.restColorWhite_restColorBlack();
        this.loadBoardTheme();
        if (alphaAgent != null) {
            alphaAgent.dispose();
        }
        if (betaAgent != null) {
            betaAgent.dispose();
        }
        if (EngineAgent.engineIOwhite != null) {
            EngineAgent.engineIOwhite.destroy();
        }
        if (EngineAgent.engineIOblack != null) {
            EngineAgent.engineIOblack.destroy();
        }
        outputArea.setText("");
        chessClock.stop();
        chessClock.setTime(Actions.Time * 60000);
        chessClock.setTurn(0);
        boardUI.setBoard(Utility.INITIAL_BOARD);
        moveListUI.clear();
        alphaAgent = Actions.whitePlayerCE.equals("Human") ? new UserAgent(frame, "Human", "white", "human") : EngineAgent.createEngine(frame, Actions.whitePlayerCE, "white", Actions.whitePlayerTip);
        betaAgent = Actions.blackPlayerCE.equals("Human") ? new UserAgent(frame, "Human", "black", "human") : EngineAgent.createEngine(frame, Actions.blackPlayerCE, "black", Actions.blackPlayerTip);
        alphaAgent.setOpponentAgent(betaAgent);
        alphaAgent.setTurn(0);
        betaAgent.setOpponentAgent(alphaAgent);
        betaAgent.setTurn(1);
        alphaAgent.newGame();
        betaAgent.newGame();
        System.out.println("whitePlayerTip = " + Actions.whitePlayerTip);
        System.out.println("blackPlayerTip = " + Actions.blackPlayerTip);
        if (Actions.whitePlayerTip.equals("xboard")) {
            if (Actions.blackPlayerTip.equals("uci")) {
                Actions.enemyTip = "another";
            }
        }
        if (Actions.whitePlayerTip.equals("xboard")) {
            if (Actions.blackPlayerTip.equals("xboard")) {
                Actions.enemyTip = "like";
            }
        }
        if (Actions.whitePlayerTip.equals("uci")) {
            if (Actions.blackPlayerTip.equals("xboard")) {
                Actions.enemyTip = "another";
            }
        }
        if (Actions.whitePlayerTip.equals("uci")) {
            if (Actions.blackPlayerTip.equals("uci")) {
                Actions.enemyTip = "like";
            }
        }
        System.out.println("Enemy Type = " + Actions.enemyTip);
        System.out.println("Depth = " + Actions.Depth);
        System.out.println("Time = " + Actions.Time);
        if (!Actions.whitePlayerCE.equals("Human")) {
            if (!Actions.blackPlayerCE.equals("Human")) {
                Actions.gameTip = "EE";
                bUndoLast.setEnabled(false);
                mUndoLast.setEnabled(false);
                if (Actions.useSound.equals("true")) {
                    aktion.useSoundSwitch();
                }
            }
        }
        if (Actions.whitePlayerCE.equals("Human")) {
            if (Actions.blackPlayerCE.equals("Human")) {
                Actions.gameTip = "HH";
            }
        }
        if (Actions.whitePlayerCE.equals("Human")) {
            if (!Actions.blackPlayerCE.equals("Human")) {
                Actions.gameTip = "HE";
                ((EngineAgent)betaAgent).addIEngineListener(new EngineAdapter(){

                    @Override
                    public void dataPrinted(EngineEvent e) {
                        outputArea.setText(outputArea.getText() + "[OUT] " + e.getData() + "\n");
                    }

                    @Override
                    public void dataEntered(EngineEvent e) {
                        outputArea.setText(outputArea.getText() + "[IN] " + e.getData() + "\n");
                    }
                });
            }
        }
        if (!Actions.whitePlayerCE.equals("Human")) {
            if (Actions.blackPlayerCE.equals("Human")) {
                Actions.gameTip = "EH";
                ((EngineAgent)alphaAgent).addIEngineListener(new EngineAdapter(){

                    @Override
                    public void dataPrinted(EngineEvent e) {
                        outputArea.setText(outputArea.getText() + "[OUT] " + e.getData() + "\n");
                    }

                    @Override
                    public void dataEntered(EngineEvent e) {
                        outputArea.setText(outputArea.getText() + "[IN] " + e.getData() + "\n");
                    }
                });
            }
        }
        System.out.println("Game Type = " + Actions.gameTip);
        this.loadBoardTheme();
        if (Actions.whitePlayerTip.equals("xboard")) {
            try {
                Thread.sleep(2000L);
            }
            catch (InterruptedException ex) {
                Logger.getLogger(XChessFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (!Actions.whitePlayerCE.equals("ArabianKnight")) {
                if (!Actions.whitePlayerCE.equals("OliThink")) {
                    if (!Actions.whitePlayerCE.equals("Eden")) {
                        if (!Actions.whitePlayerCE.equals("Tiffanys")) {
                            Actions.sendEngineCmd("white", "white");
                        }
                    }
                }
            }
            Actions.sendEngineCmd("white", "go");
            System.out.println("========================= RUN WHITE ===========================");
        }
        if (Actions.whitePlayerTip.equals("uci")) {
            try {
                Thread.sleep(2000L);
            }
            catch (InterruptedException ex) {
                Logger.getLogger(XChessFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            Actions.sendEngineCmd("white", "position startpos");
            Actions.sendEngineCmd("white", "go depth " + Actions.Depth);
            System.out.println("========================= RUN WHITE ===========================");
        }
    }

    @Override
    public BoardUI getBoardUI() {
        return boardUI;
    }

    @Override
    public ChessClock getChessClock() {
        return chessClock;
    }

    @Override
    public MoveListUI getMoveListUI() {
        return moveListUI;
    }

    public void changeLF() {
        String changeLook = (String)JOptionPane.showInputDialog(this, "Choose Skin Here:", "Select Skin", 3, new ImageIcon(this.getClass().getResource("/SJCE/img/select-by-color-32.png")), lookAndFeelsDisplay.toArray(), null);
        if (changeLook != null) {
            for (int a = 0; a < lookAndFeelsDisplay.size(); ++a) {
                if (!changeLook.equals(lookAndFeelsDisplay.get(a))) continue;
                try {
                    Actions.currentLAF = lookAndFeelsRealNames.get(a);
                    UIManager.setLookAndFeel(lookAndFeelsRealNames.get(a));
                    SwingUtilities.updateComponentTreeUI(this);
                    this.pack();
                    borderPanel.updateUI();
                    break;
                }
                catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
                    System.err.println(ex);
                    ex.printStackTrace(System.err);
                }
            }
        }
    }

    public void changeCE(String colorCE) {
        String changeLook = "";
        block4 : switch (colorCE) {
            case "white": {
                changeLook = (String)JOptionPane.showInputDialog(this, "Choose White Player Here:", "Select White Player", 3, new ImageIcon(this.getClass().getResource("/SJCE/img/knight-white-32.png")), selectCE.toArray(), null);
                if (changeLook == null) break;
                for (int a = 0; a < selectCE.size(); ++a) {
                    if (!changeLook.equals(selectCE.get(a))) continue;
                    Actions.whitePlayerCE = selectCE.get(a);
                    comboWPlayerCE.setSelectedItem(Actions.whitePlayerCE);
                    break block4;
                }
                break;
            }
            case "black": {
                changeLook = (String)JOptionPane.showInputDialog(this, "Choose Black Player Here:", "Select Black Player", 3, new ImageIcon(this.getClass().getResource("/SJCE/img/knight-black-32.png")), selectCE.toArray(), null);
                if (changeLook == null) break;
                for (int a = 0; a < selectCE.size(); ++a) {
                    if (!changeLook.equals(selectCE.get(a))) continue;
                    Actions.blackPlayerCE = selectCE.get(a);
                    comboBPlayerCE.setSelectedItem(Actions.blackPlayerCE);
                    break block4;
                }
                break;
            }
        }
    }

    public void loadBoardTheme() {
        for (int i = 1; i < 3; ++i) {
            XChessFrame.boardUI.chessTheme.loadTheme();
            XChessFrame.boardUI.chessTheme.adjustTheme(boardUI.getSize());
            boardUI.update(SQUARES);
            borderPanel.repaint();
            borderPanel.updateUI();
            boardUI.repaint();
            boardUI.updateUI();
            SwingUtilities.updateComponentTreeUI(this);
            boardUI.update(SQUARES);
            boardUI.repaint();
            boardUI.updateUI();
        }
    }

    public void changeBoardTheme() {
        BoardThemeSelect bts = new BoardThemeSelect(frame, true);
        bts.setVisible(true);
    }

    private void initComponents() {
        this.verticalSplit = new JSplitPane();
        scrollOutputArea = new JScrollPane();
        outputArea = new JTextArea();
        this.horizontalSplit = new JSplitPane();
        sidePanel = new JPanel();
        scrollMoveList = new JScrollPane();
        borderPanel = new JPanel();
        this.jToolBar2 = new JToolBar();
        this.jToolBar1 = new JToolBar();
        this.jButton3 = new JButton();
        bUndoLast = new JButton();
        this.jButton1 = new JButton();
        this.bSelectWhite = new JButton();
        this.bChangeSkin = new JButton();
        bBoardTheme = new JButton();
        this.bLinks = new JButton();
        this.jButton2 = new JButton();
        bAbout = new JButton();
        this.jToolBar3 = new JToolBar();
        this.bNew = new JButton();
        this.bKillAll = new JButton();
        this.bSaveCfg = new JButton();
        bSoundMixer = new JButton();
        bUseSound = new JToggleButton();
        bcomboMode = new JComboBox();
        bcomboDepth = new JComboBox();
        bcomboTime = new JComboBox();
        bUseClock = new JToggleButton();
        this.bSendWhite = new JButton();
        this.bSendBlack = new JButton();
        comboWPlayerCE = new JComboBox();
        this.jSeparator2 = new JToolBar.Separator();
        comboBPlayerCE = new JComboBox();
        this.jSeparator1 = new JToolBar.Separator();
        this.jMenuBar1 = new JMenuBar();
        this.mFile = new JMenu();
        this.mNewGame = new JMenuItem();
        this.jMenuItem2 = new JMenuItem();
        this.mExit = new JMenuItem();
        this.mOptions = new JMenu();
        this.mTimeMenu = new JMenu();
        mUseClock = new JCheckBoxMenuItem();
        mTime = new JMenu();
        mTime05 = new JRadioButtonMenuItem();
        mTime10 = new JRadioButtonMenuItem();
        mTime15 = new JRadioButtonMenuItem();
        mTime20 = new JRadioButtonMenuItem();
        mTime25 = new JRadioButtonMenuItem();
        mTime30 = new JRadioButtonMenuItem();
        this.jMenu1 = new JMenu();
        mUseSound = new JCheckBoxMenuItem();
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
        mModeEasy = new JRadioButtonMenuItem();
        mModeHard = new JRadioButtonMenuItem();
        this.mEngineDepth = new JMenu();
        mDepth2 = new JRadioButtonMenuItem();
        mDepth3 = new JRadioButtonMenuItem();
        mDepth4 = new JRadioButtonMenuItem();
        mDepth5 = new JRadioButtonMenuItem();
        mDepth6 = new JRadioButtonMenuItem();
        mDepth7 = new JRadioButtonMenuItem();
        mDepth8 = new JRadioButtonMenuItem();
        mDepth9 = new JRadioButtonMenuItem();
        this.mSendWhite = new JMenuItem();
        this.mSendBlack = new JMenuItem();
        this.mKillAll = new JMenuItem();
        mUndoLast = new JMenuItem();
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
        scrollOutputArea.setBorder(BorderFactory.createTitledBorder("Engine Output"));
        outputArea.setEditable(false);
        outputArea.setColumns(20);
        outputArea.setRows(5);
        outputArea.setMaximumSize(new Dimension(102, 62));
        outputArea.setMinimumSize(new Dimension(102, 62));
        scrollOutputArea.setViewportView(outputArea);
        this.verticalSplit.setBottomComponent(scrollOutputArea);
        scrollOutputArea.getAccessibleContext().setAccessibleParent(this.verticalSplit);
        this.horizontalSplit.setDividerLocation(415);
        this.horizontalSplit.setDividerSize(0);
        this.horizontalSplit.setEnabled(false);
        this.horizontalSplit.setOpaque(false);
        sidePanel.setBorder(BorderFactory.createTitledBorder("Moves"));
        sidePanel.setMaximumSize(new Dimension(120, 450));
        sidePanel.setMinimumSize(new Dimension(120, 450));
        sidePanel.setOpaque(false);
        sidePanel.setPreferredSize(new Dimension(120, 450));
        GroupLayout sidePanelLayout = new GroupLayout(sidePanel);
        sidePanel.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(sidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(scrollMoveList, -1, 417, 32767));
        sidePanelLayout.setVerticalGroup(sidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(scrollMoveList, GroupLayout.Alignment.TRAILING, -1, 428, 32767));
        this.horizontalSplit.setRightComponent(sidePanel);
        sidePanel.getAccessibleContext().setAccessibleParent(this.horizontalSplit);
        borderPanel.setBorder(BorderFactory.createTitledBorder("Chess Board"));
        borderPanel.setDoubleBuffered(false);
        borderPanel.setMaximumSize(new Dimension(410, 410));
        borderPanel.setMinimumSize(new Dimension(410, 410));
        borderPanel.setPreferredSize(new Dimension(410, 410));
        GroupLayout borderPanelLayout = new GroupLayout(borderPanel);
        borderPanel.setLayout(borderPanelLayout);
        borderPanelLayout.setHorizontalGroup(borderPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 404, 32767));
        borderPanelLayout.setVerticalGroup(borderPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 428, 32767));
        this.horizontalSplit.setLeftComponent(borderPanel);
        this.verticalSplit.setTopComponent(this.horizontalSplit);
        this.horizontalSplit.getAccessibleContext().setAccessibleParent(this.verticalSplit);
        this.getContentPane().add((Component)this.verticalSplit, "Center");
        this.jToolBar2.setFloatable(false);
        this.jToolBar2.setOrientation(1);
        this.jToolBar1.setFloatable(false);
        this.jButton3.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/log-24-blue.png")));
        this.jButton3.setToolTipText("Show Game Log in separate window");
        this.jButton3.setFocusable(false);
        this.jButton3.setHorizontalTextPosition(0);
        this.jButton3.setVerticalTextPosition(3);
        this.jButton3.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.jButton3ActionPerformed(evt);
            }
        });
        this.jToolBar1.add(this.jButton3);
        bUndoLast.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/undo-red-24.png")));
        bUndoLast.setToolTipText("Undo Last One Move");
        bUndoLast.setFocusable(false);
        bUndoLast.setHorizontalTextPosition(0);
        bUndoLast.setVerticalTextPosition(3);
        bUndoLast.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.bUndoLastActionPerformed(evt);
            }
        });
        this.jToolBar1.add(bUndoLast);
        this.jButton1.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/knight-black-24.png")));
        this.jButton1.setToolTipText("Select Black Player Chess Engine");
        this.jButton1.setFocusable(false);
        this.jButton1.setHorizontalTextPosition(0);
        this.jButton1.setVerticalTextPosition(3);
        this.jButton1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.jButton1ActionPerformed(evt);
            }
        });
        this.jToolBar1.add(this.jButton1);
        this.bSelectWhite.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/knight-white-24.png")));
        this.bSelectWhite.setToolTipText("Select White Player Chess Engine");
        this.bSelectWhite.setFocusable(false);
        this.bSelectWhite.setHorizontalTextPosition(0);
        this.bSelectWhite.setVerticalTextPosition(3);
        this.bSelectWhite.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.bSelectWhiteActionPerformed(evt);
            }
        });
        this.jToolBar1.add(this.bSelectWhite);
        this.bChangeSkin.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/skin_color_chooser-24.png")));
        this.bChangeSkin.setToolTipText("Change Skin");
        this.bChangeSkin.setFocusable(false);
        this.bChangeSkin.setHorizontalTextPosition(0);
        this.bChangeSkin.setVerticalTextPosition(3);
        this.bChangeSkin.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.bChangeSkinActionPerformed(evt);
            }
        });
        this.jToolBar1.add(this.bChangeSkin);
        bBoardTheme.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/color-swatch-24.png")));
        bBoardTheme.setToolTipText("Select Board Theme");
        bBoardTheme.setFocusable(false);
        bBoardTheme.setHorizontalTextPosition(0);
        bBoardTheme.setVerticalTextPosition(3);
        bBoardTheme.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.bBoardThemeActionPerformed(evt);
            }
        });
        this.jToolBar1.add(bBoardTheme);
        this.bLinks.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/x-b-24.png")));
        this.bLinks.setToolTipText("Xboard Engine Links & Ratings");
        this.bLinks.setFocusable(false);
        this.bLinks.setHorizontalTextPosition(0);
        this.bLinks.setVerticalTextPosition(3);
        this.bLinks.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.bLinksActionPerformed(evt);
            }
        });
        this.jToolBar1.add(this.bLinks);
        this.jButton2.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/U-blue-24.png")));
        this.jButton2.setToolTipText("Uci Engines Links & Ratings");
        this.jButton2.setFocusable(false);
        this.jButton2.setHorizontalTextPosition(0);
        this.jButton2.setVerticalTextPosition(3);
        this.jButton2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.jButton2ActionPerformed(evt);
            }
        });
        this.jToolBar1.add(this.jButton2);
        bAbout.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/info-book-green.png")));
        bAbout.setToolTipText("About");
        bAbout.setFocusable(false);
        bAbout.setHorizontalTextPosition(0);
        bAbout.setVerticalTextPosition(3);
        bAbout.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.bAboutActionPerformed(evt);
            }
        });
        this.jToolBar1.add(bAbout);
        this.jToolBar2.add(this.jToolBar1);
        this.jToolBar3.setFloatable(false);
        this.bNew.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/new.png")));
        this.bNew.setToolTipText("New Game");
        this.bNew.setFocusable(false);
        this.bNew.setHorizontalTextPosition(0);
        this.bNew.setVerticalTextPosition(3);
        this.bNew.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.bNewActionPerformed(evt);
            }
        });
        this.jToolBar3.add(this.bNew);
        this.bKillAll.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/stop-red.png")));
        this.bKillAll.setToolTipText("Kill All Engines and reset Board");
        this.bKillAll.setFocusable(false);
        this.bKillAll.setHorizontalTextPosition(0);
        this.bKillAll.setVerticalTextPosition(3);
        this.bKillAll.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.bKillAllActionPerformed(evt);
            }
        });
        this.jToolBar3.add(this.bKillAll);
        this.bSaveCfg.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/floppy_disk_green.png")));
        this.bSaveCfg.setToolTipText("Save Config");
        this.bSaveCfg.setFocusable(false);
        this.bSaveCfg.setHorizontalTextPosition(0);
        this.bSaveCfg.setVerticalTextPosition(3);
        this.bSaveCfg.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.bSaveCfgActionPerformed(evt);
            }
        });
        this.jToolBar3.add(this.bSaveCfg);
        bSoundMixer.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/audio_volume_high.png")));
        bSoundMixer.setToolTipText("Sound Volume");
        bSoundMixer.setFocusable(false);
        bSoundMixer.setHorizontalTextPosition(0);
        bSoundMixer.setVerticalTextPosition(3);
        bSoundMixer.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.bSoundMixerActionPerformed(evt);
            }
        });
        this.jToolBar3.add(bSoundMixer);
        bUseSound.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/sound-add-icon.png")));
        bUseSound.setToolTipText("Use Sound");
        bUseSound.setFocusable(false);
        bUseSound.setHorizontalTextPosition(0);
        bUseSound.setVerticalTextPosition(3);
        bUseSound.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.bUseSoundActionPerformed(evt);
            }
        });
        this.jToolBar3.add(bUseSound);
        bcomboMode.setModel(new DefaultComboBoxModel<String>(new String[]{"hard", "easy"}));
        bcomboMode.setToolTipText("Engine mode");
        bcomboMode.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.bcomboModeActionPerformed(evt);
            }
        });
        this.jToolBar3.add(bcomboMode);
        bcomboDepth.setModel(new DefaultComboBoxModel<String>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8"}));
        bcomboDepth.setToolTipText("Search Depth ");
        bcomboDepth.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.bcomboDepthActionPerformed(evt);
            }
        });
        this.jToolBar3.add(bcomboDepth);
        bcomboTime.setModel(new DefaultComboBoxModel<String>(new String[]{"5", "10", "15", "20", "25", "30"}));
        bcomboTime.setToolTipText("Set Time (min)");
        bcomboTime.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.bcomboTimeActionPerformed(evt);
            }
        });
        this.jToolBar3.add(bcomboTime);
        bUseClock.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/clock-plus-24.png")));
        bUseClock.setToolTipText("Use Clock");
        bUseClock.setFocusable(false);
        bUseClock.setHorizontalTextPosition(0);
        bUseClock.setVerticalTextPosition(3);
        bUseClock.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.bUseClockActionPerformed(evt);
            }
        });
        this.jToolBar3.add(bUseClock);
        this.bSendWhite.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/send-white-24-green.png")));
        this.bSendWhite.setToolTipText("Send Command to White CE");
        this.bSendWhite.setFocusable(false);
        this.bSendWhite.setHorizontalTextPosition(0);
        this.bSendWhite.setVerticalTextPosition(3);
        this.bSendWhite.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.bSendWhiteActionPerformed(evt);
            }
        });
        this.jToolBar3.add(this.bSendWhite);
        this.bSendBlack.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/send-black-24-right.png")));
        this.bSendBlack.setToolTipText("Send Command to Black CE");
        this.bSendBlack.setFocusable(false);
        this.bSendBlack.setHorizontalTextPosition(0);
        this.bSendBlack.setVerticalTextPosition(3);
        this.bSendBlack.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.bSendBlackActionPerformed(evt);
            }
        });
        this.jToolBar3.add(this.bSendBlack);
        comboWPlayerCE.setModel(new DefaultComboBoxModel<String>(new String[]{"Animats", "ArabianKnight ", "BremboCE", "CaveChess", "CupCake", "ChessBotX       ", "DeepBrutePos", "Frittle", "FrankWalter", "Gladiator", "GNU Chess", "Jchess", "Javalin", "KingsOut      ", "OliThink", "Tiffanys"}));
        comboWPlayerCE.setToolTipText("Select White Player Chess Engine");
        comboWPlayerCE.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.comboWPlayerCEActionPerformed(evt);
            }
        });
        this.jToolBar3.add(comboWPlayerCE);
        this.jToolBar3.add(this.jSeparator2);
        comboBPlayerCE.setModel(new DefaultComboBoxModel<String>(new String[]{"Animats", "ArabianKnight ", "BremboCE", "CaveChess", "CupCake", "ChessBotX       ", "DeepBrutePos", "Frittle", "FrankWalter", "Gladiator", "GNU Chess", "Jchess", "Javalin", "KingsOut      ", "OliThink", "Tiffanys"}));
        comboBPlayerCE.setToolTipText("Select Black Player Chess Engine");
        comboBPlayerCE.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.comboBPlayerCEActionPerformed(evt);
            }
        });
        this.jToolBar3.add(comboBPlayerCE);
        this.jToolBar3.add(this.jSeparator1);
        this.jToolBar2.add(this.jToolBar3);
        this.getContentPane().add((Component)this.jToolBar2, "North");
        this.mFile.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/Actions-document-open-icon.png")));
        this.mFile.setText("File");
        this.mNewGame.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/new.png")));
        this.mNewGame.setText("New Game");
        this.mNewGame.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.mNewGameActionPerformed(evt);
            }
        });
        this.mFile.add(this.mNewGame);
        this.jMenuItem2.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/log-16-blue.png")));
        this.jMenuItem2.setText("View Game Log");
        this.jMenuItem2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.jMenuItem2ActionPerformed(evt);
            }
        });
        this.mFile.add(this.jMenuItem2);
        this.mExit.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/quit.png")));
        this.mExit.setText("Exit");
        this.mExit.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.mExitActionPerformed(evt);
            }
        });
        this.mFile.add(this.mExit);
        this.jMenuBar1.add(this.mFile);
        this.mOptions.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/application-16.png")));
        this.mOptions.setText("Options");
        this.mTimeMenu.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/time-16.png")));
        this.mTimeMenu.setText("Time");
        mUseClock.setText("Use Clock");
        mUseClock.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/Alarm-clock-16.png")));
        mUseClock.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.mUseClockActionPerformed(evt);
            }
        });
        this.mTimeMenu.add(mUseClock);
        mTime.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/calendar_select_day.png")));
        mTime.setText("Set Time (min)");
        mTime05.setText("5");
        mTime05.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.mTime05ActionPerformed(evt);
            }
        });
        mTime.add(mTime05);
        mTime10.setText("10");
        mTime10.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.mTime10ActionPerformed(evt);
            }
        });
        mTime.add(mTime10);
        mTime15.setText("15");
        mTime15.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.mTime15ActionPerformed(evt);
            }
        });
        mTime.add(mTime15);
        mTime20.setText("20");
        mTime20.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.mTime20ActionPerformed(evt);
            }
        });
        mTime.add(mTime20);
        mTime25.setText("25");
        mTime25.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.mTime25ActionPerformed(evt);
            }
        });
        mTime.add(mTime25);
        mTime30.setText("30");
        mTime30.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.mTime30ActionPerformed(evt);
            }
        });
        mTime.add(mTime30);
        this.mTimeMenu.add(mTime);
        this.mOptions.add(this.mTimeMenu);
        this.jMenu1.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/sound-juk.png")));
        this.jMenu1.setText("Sound Control");
        mUseSound.setSelected(true);
        mUseSound.setText("Use Sound");
        mUseSound.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/document_music_playlist.png")));
        mUseSound.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.mUseSoundActionPerformed(evt);
            }
        });
        this.jMenu1.add(mUseSound);
        this.jMenuItem3.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/volume_loud.png")));
        this.jMenuItem3.setText("Sound Volume");
        this.jMenuItem3.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.jMenuItem3ActionPerformed(evt);
            }
        });
        this.jMenu1.add(this.jMenuItem3);
        this.mOptions.add(this.jMenu1);
        this.mBoardTheme.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/color_swatch.png")));
        this.mBoardTheme.setText("Change Board Theme");
        this.mBoardTheme.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.mBoardThemeActionPerformed(evt);
            }
        });
        this.mOptions.add(this.mBoardTheme);
        this.mChangeSkin.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/gnome_color_chooser.png")));
        this.mChangeSkin.setText("Change Skin");
        this.mChangeSkin.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.mChangeSkinActionPerformed(evt);
            }
        });
        this.mOptions.add(this.mChangeSkin);
        this.mSaveCfg.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/save.png")));
        this.mSaveCfg.setText("Save Config");
        this.mSaveCfg.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.mSaveCfgActionPerformed(evt);
            }
        });
        this.mOptions.add(this.mSaveCfg);
        this.jMenuBar1.add(this.mOptions);
        this.mPlayers.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/play_green-1.png")));
        this.mPlayers.setText("Players");
        this.mSelectCEwhite.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/white_knight-16.png")));
        this.mSelectCEwhite.setText("Select White Player Chess Engine");
        this.mSelectCEwhite.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.mSelectCEwhiteActionPerformed(evt);
            }
        });
        this.mPlayers.add(this.mSelectCEwhite);
        this.mSelectCEblack.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/black-knight-16.png")));
        this.mSelectCEblack.setText("Select Black Player Chess Engine");
        this.mSelectCEblack.setToolTipText("");
        this.mSelectCEblack.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
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
        mModeEasy.setText("Easy ( pondering OFF )");
        mModeEasy.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.mModeEasyActionPerformed(evt);
            }
        });
        this.mEngineMode.add(mModeEasy);
        mModeHard.setText("Hard ( pondering ON )");
        mModeHard.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.mModeHardActionPerformed(evt);
            }
        });
        this.mEngineMode.add(mModeHard);
        this.mEngineConfig.add(this.mEngineMode);
        this.mEngineDepth.setText("Engine Search Depth");
        this.mEngineDepth.setToolTipText("");
        mDepth2.setText("2");
        mDepth2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.mDepth2ActionPerformed(evt);
            }
        });
        this.mEngineDepth.add(mDepth2);
        mDepth3.setText("3");
        mDepth3.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.mDepth3ActionPerformed(evt);
            }
        });
        this.mEngineDepth.add(mDepth3);
        mDepth4.setText("4");
        mDepth4.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.mDepth4ActionPerformed(evt);
            }
        });
        this.mEngineDepth.add(mDepth4);
        mDepth5.setText("5");
        mDepth5.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.mDepth5ActionPerformed(evt);
            }
        });
        this.mEngineDepth.add(mDepth5);
        mDepth6.setText("6");
        mDepth6.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.mDepth6ActionPerformed(evt);
            }
        });
        this.mEngineDepth.add(mDepth6);
        mDepth7.setText("7");
        mDepth7.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.mDepth7ActionPerformed(evt);
            }
        });
        this.mEngineDepth.add(mDepth7);
        mDepth8.setText("8");
        mDepth8.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.mDepth8ActionPerformed(evt);
            }
        });
        this.mEngineDepth.add(mDepth8);
        mDepth9.setText("9");
        mDepth9.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.mDepth9ActionPerformed(evt);
            }
        });
        this.mEngineDepth.add(mDepth9);
        this.mEngineConfig.add(this.mEngineDepth);
        this.mChessEngines.add(this.mEngineConfig);
        this.mSendWhite.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/send-white-16-green.png")));
        this.mSendWhite.setText("Send Command to White CE");
        this.mSendWhite.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.mSendWhiteActionPerformed(evt);
            }
        });
        this.mChessEngines.add(this.mSendWhite);
        this.mSendBlack.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/send-black-16-right.png")));
        this.mSendBlack.setText("Send Command to Black CE");
        this.mSendBlack.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.mSendBlackActionPerformed(evt);
            }
        });
        this.mChessEngines.add(this.mSendBlack);
        this.mKillAll.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/stop_red.png")));
        this.mKillAll.setText("Kill All Engine Process");
        this.mKillAll.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.mKillAllActionPerformed(evt);
            }
        });
        this.mChessEngines.add(this.mKillAll);
        mUndoLast.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/undo-red-16.png")));
        mUndoLast.setText("Undo Last One Move");
        mUndoLast.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.mUndoLastActionPerformed(evt);
            }
        });
        this.mChessEngines.add(mUndoLast);
        this.jMenuBar1.add(this.mChessEngines);
        this.mInfo.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/info-green-16.png")));
        this.mInfo.setText("Info");
        this.jMenuItem4.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/x-b-16.png")));
        this.jMenuItem4.setText("Xboard Engines Links & Ratings");
        this.jMenuItem4.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.jMenuItem4ActionPerformed(evt);
            }
        });
        this.mInfo.add(this.jMenuItem4);
        this.jMenuItem5.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/U-blue-16.png")));
        this.jMenuItem5.setText("Uci Engines Links & Ratings");
        this.jMenuItem5.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.jMenuItem5ActionPerformed(evt);
            }
        });
        this.mInfo.add(this.jMenuItem5);
        this.jMenuItem1.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/help-green-16.png")));
        this.jMenuItem1.setText("About");
        this.jMenuItem1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                XChessFrame.this.jMenuItem1ActionPerformed(evt);
            }
        });
        this.mInfo.add(this.jMenuItem1);
        this.jMenuBar1.add(this.mInfo);
        this.setJMenuBar(this.jMenuBar1);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private void mExitActionPerformed(ActionEvent evt) {
        int r = JOptionPane.showConfirmDialog(frame, "Really Quit ?", "Quit ?", 0);
        if (r == 0) {
            System.exit(0);
        }
    }

    private void mNewGameActionPerformed(ActionEvent evt) {
        this.newGame(0, 1);
    }

    private void mModeEasyActionPerformed(ActionEvent evt) {
        Actions.Mode = "easy";
        mModeEasy.setSelected(true);
        mModeHard.setSelected(false);
        bcomboMode.setSelectedItem(Actions.Mode);
    }

    private void mModeHardActionPerformed(ActionEvent evt) {
        Actions.Mode = "hard";
        mModeEasy.setSelected(false);
        mModeHard.setSelected(true);
        bcomboMode.setSelectedItem(Actions.Mode);
    }

    private void mDepth9ActionPerformed(ActionEvent evt) {
        Actions.changeDepth(9);
    }

    private void mDepth2ActionPerformed(ActionEvent evt) {
        Actions.changeDepth(2);
    }

    private void mDepth3ActionPerformed(ActionEvent evt) {
        Actions.changeDepth(3);
    }

    private void mDepth4ActionPerformed(ActionEvent evt) {
        Actions.changeDepth(4);
    }

    private void mDepth5ActionPerformed(ActionEvent evt) {
        Actions.changeDepth(5);
    }

    private void mDepth6ActionPerformed(ActionEvent evt) {
        Actions.changeDepth(6);
    }

    private void mDepth7ActionPerformed(ActionEvent evt) {
        Actions.changeDepth(7);
    }

    private void mChangeSkinActionPerformed(ActionEvent evt) {
        this.changeLF();
        borderPanel.add((Component)boardUI, "Center");
    }

    private void mTime05ActionPerformed(ActionEvent evt) {
        Actions.changeTime(5);
    }

    private void mTime10ActionPerformed(ActionEvent evt) {
        Actions.changeTime(10);
    }

    private void mTime15ActionPerformed(ActionEvent evt) {
        Actions.changeTime(15);
    }

    private void mTime20ActionPerformed(ActionEvent evt) {
        Actions.changeTime(20);
    }

    private void mTime25ActionPerformed(ActionEvent evt) {
        Actions.changeTime(25);
    }

    private void mTime30ActionPerformed(ActionEvent evt) {
        Actions.changeTime(30);
    }

    private void jMenuItem1ActionPerformed(ActionEvent evt) {
        aktion.about(this);
    }

    private void mUseClockActionPerformed(ActionEvent evt) {
        aktion.useTime();
    }

    private void mSaveCfgActionPerformed(ActionEvent evt) {
        AppCfgPref.Save();
    }

    private void mSelectCEwhiteActionPerformed(ActionEvent evt) {
        this.changeCE("white");
    }

    private void mBoardThemeActionPerformed(ActionEvent evt) {
        this.changeBoardTheme();
    }

    private void mDepth8ActionPerformed(ActionEvent evt) {
        Actions.changeDepth(8);
    }

    private void mKillAllActionPerformed(ActionEvent evt) {
        aktion.killAllEngines();
    }

    private void jMenuItem4ActionPerformed(ActionEvent evt) {
        aktion.LinksX();
    }

    private void mSelectCEblackActionPerformed(ActionEvent evt) {
        this.changeCE("black");
    }

    private void jMenuItem3ActionPerformed(ActionEvent evt) {
        this.MixerSet();
    }

    private void mUseSoundActionPerformed(ActionEvent evt) {
        aktion.useSoundSwitch();
    }

    private void mSendWhiteActionPerformed(ActionEvent evt) {
        aktion.sendEngineCmd("white");
    }

    private void mSendBlackActionPerformed(ActionEvent evt) {
        aktion.sendEngineCmd("black");
    }

    private void bLinksActionPerformed(ActionEvent evt) {
        aktion.LinksX();
    }

    private void bAboutActionPerformed(ActionEvent evt) {
        aktion.about(this);
    }

    private void bNewActionPerformed(ActionEvent evt) {
        frame.newGame(0, 1);
    }

    private void bKillAllActionPerformed(ActionEvent evt) {
        aktion.killAllEngines();
    }

    private void bSaveCfgActionPerformed(ActionEvent evt) {
        AppCfgPref.Save();
    }

    private void bBoardThemeActionPerformed(ActionEvent evt) {
        this.changeBoardTheme();
    }

    private void bChangeSkinActionPerformed(ActionEvent evt) {
        this.changeLF();
        borderPanel.add((Component)boardUI, "Center");
    }

    private void bcomboModeActionPerformed(ActionEvent evt) {
        Actions.Mode = bcomboMode.getSelectedItem().toString();
        if (Actions.Mode.equals("hard")) {
            mModeHard.setSelected(true);
            mModeEasy.setSelected(false);
        } else {
            mModeHard.setSelected(false);
            mModeEasy.setSelected(true);
        }
    }

    private void bcomboDepthActionPerformed(ActionEvent evt) {
        Actions.Depth = Integer.parseInt(bcomboDepth.getSelectedItem().toString());
        Actions.changeDepth(Actions.Depth);
    }

    private void bcomboTimeActionPerformed(ActionEvent evt) {
        Actions.Time = Integer.parseInt(bcomboTime.getSelectedItem().toString());
        Actions.changeTime(Actions.Time);
    }

    private void bUseClockActionPerformed(ActionEvent evt) {
        aktion.useTime();
    }

    private void bSoundMixerActionPerformed(ActionEvent evt) {
        this.MixerSet();
    }

    private void bUseSoundActionPerformed(ActionEvent evt) {
        aktion.useSoundSwitch();
    }

    private void bSendWhiteActionPerformed(ActionEvent evt) {
        aktion.sendEngineCmd("white");
    }

    private void comboWPlayerCEActionPerformed(ActionEvent evt) {
        Actions.viborCE(comboWPlayerCE.getSelectedItem().toString(), "white");
    }

    private void bSendBlackActionPerformed(ActionEvent evt) {
        aktion.sendEngineCmd("black");
    }

    private void comboBPlayerCEActionPerformed(ActionEvent evt) {
        Actions.viborCE(comboBPlayerCE.getSelectedItem().toString(), "black");
    }

    private void bSelectWhiteActionPerformed(ActionEvent evt) {
        this.changeCE("white");
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
        this.changeCE("black");
    }

    private void bUndoLastActionPerformed(ActionEvent evt) {
        Actions.bmUndoLastMoveEventHandler();
    }

    private void mUndoLastActionPerformed(ActionEvent evt) {
        Actions.bmUndoLastMoveEventHandler();
    }

    private void jButton2ActionPerformed(ActionEvent evt) {
        aktion.LinksU();
    }

    private void jMenuItem5ActionPerformed(ActionEvent evt) {
        aktion.LinksU();
    }

    private void jButton3ActionPerformed(ActionEvent evt) {
        Actions.logShow();
    }

    private void jMenuItem2ActionPerformed(ActionEvent evt) {
        Actions.logShow();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){

            @Override
            public void run() {
                frame = new XChessFrame();
                chessClock = new ChessClock();
                frame.getRootPane().setWindowDecorationStyle(1);
                JFrame.setDefaultLookAndFeelDecorated(true);
                JDialog.setDefaultLookAndFeelDecorated(true);
                AppCfgPref.Load();
                frame.MixerInit();
                moveListUI = new MoveListUI();
                boardUI = new BoardUI();
                moveListUI.setSelectionMode(0);
                scrollOutputArea.setViewportView(outputArea);
                sidePanel.setLayout(new BorderLayout());
                sidePanel.add((Component)scrollMoveList, "Center");
                scrollMoveList.setViewportView(moveListUI);
                BL = new BorderLayout();
                borderPanel.setLayout(BL);
                boardUI.setOpaque(true);
                aktion.appInit();
                Actions.InstallLF();
                Actions.setSkin();
                Actions.viborCE(Actions.whitePlayerCE, "white");
                Actions.viborCE(Actions.blackPlayerCE, "black");
                frame.setVisible(true);
                borderPanel.add((Component)boardUI, "Center");
                frame.boardSetSize();
                System.out.println("boardUI = " + boardUI.getSize());
                System.out.println("borderPanel = " + borderPanel.getSize());
                new Run_Thread();
            }
        });
    }

    static {
        lookAndFeelsDisplay = new ArrayList<String>();
        lookAndFeelsRealNames = new ArrayList<String>();
        aktion = new Actions();
        selectCE = new ArrayList<String>();
    }

}

