/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  org.pushingpixels.lafwidget.animation.AnimationConfigurationManager
 *  org.pushingpixels.lafwidget.animation.AnimationFacet
 */
package SJCE.more;

import SJCE.XChessFrame;
import SJCE.more.Links.CElinksU;
import SJCE.more.Links.CElinksX;
import SJCE.more.Log.ClipboardTextTransfer;
import SJCE.more.Log.LogShow;
import SJCE.more.Log.ShowClipBoard;
import SJCE.more.Log.TextTransfer;
import SJCE.xgui.Agent.EngineAgent;
import SJCE.xgui.EngineIO;
import SJCE.xgui.JList.MoveListUI;
import SJCE.xgui.JPanel.BoardUI;
import SJCE.xgui.JPanel.ChessClock;
import SJCE.xgui.Move;
import SJCE.xgui.Utility;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.io.File;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.pushingpixels.lafwidget.animation.AnimationConfigurationManager;
import org.pushingpixels.lafwidget.animation.AnimationFacet;

public class Actions {
    public static String Mode = "hard";
    public static int Depth;
    public static String currentLAF;
    public static int Time;
    public static String UseClock;
    public static String BoardThemeFig;
    public static String BoardThemeFon;
    public static int Prohod_White_Event;
    public static int Prohod_White_Destination;
    public static int Prohod_Black_Event;
    public static int Prohod_Black_Destination;
    public static ImageIcon ceIcon;
    public static String uciAllMovesString;
    public static String enginePromotionType;
    public static String enginePromotionFig;
    public static final Color moveColor;
    public static final Color restColor;
    public static String whitePlayerCE;
    public static String whitePlayerTip;
    public static String blackPlayerCE;
    public static String blackPlayerTip;
    public static String gameTip;
    public static String enemyTip;
    public static String currentMute;
    public static int currentMixer;
    public static String useSound;
    public static int promotionCount;
    public static Move whiteLastMove;
    public static Move blackLastMove;
    public static String blackRivalMovesString;
    public static String whiteRivalMovesString;
    public static String jchecsEngineTip;
    public static List<String> jchecsCeSelect;
    public static Preferences pref;

    public Actions() {
        jchecsCeSelect.add("jChecs.AlphaBeta");
        jchecsCeSelect.add("jChecs.NegaScout");
    }

    public static void changeDepth(int dd) {
        XChessFrame.frame.mDepthRadioGroup.clearSelection();
        Depth = dd;
        switch (dd) {
            case 2: {
                XChessFrame.mDepth2.setSelected(true);
                break;
            }
            case 3: {
                XChessFrame.mDepth3.setSelected(true);
                break;
            }
            case 4: {
                XChessFrame.mDepth4.setSelected(true);
                break;
            }
            case 5: {
                XChessFrame.mDepth5.setSelected(true);
                break;
            }
            case 6: {
                XChessFrame.mDepth6.setSelected(true);
                break;
            }
            case 7: {
                XChessFrame.mDepth7.setSelected(true);
                break;
            }
            case 8: {
                XChessFrame.mDepth8.setSelected(true);
                break;
            }
            case 9: {
                XChessFrame.mDepth9.setSelected(true);
            }
        }
        XChessFrame.bcomboDepth.setSelectedItem("" + Depth);
    }

    public static void changeTime(int tt) {
        XChessFrame.frame.mTimeRadioGroup.clearSelection();
        Time = tt;
        switch (tt) {
            case 5: {
                XChessFrame.mTime05.setSelected(true);
                break;
            }
            case 10: {
                XChessFrame.mTime10.setSelected(true);
                break;
            }
            case 15: {
                XChessFrame.mTime15.setSelected(true);
                break;
            }
            case 20: {
                XChessFrame.mTime20.setSelected(true);
                break;
            }
            case 25: {
                XChessFrame.mTime25.setSelected(true);
                break;
            }
            case 30: {
                XChessFrame.mTime30.setSelected(true);
            }
        }
        XChessFrame.bcomboTime.setSelectedItem("" + Time);
    }

    public static void MyInstLF(String lf) {
        XChessFrame.lookAndFeelsDisplay.add(lf);
        XChessFrame.lookAndFeelsRealNames.add(lf);
    }

    public static void InstallLF() {
        Actions.MyInstLF("org.pushingpixels.substance.api.skin.SubstanceSaharaLookAndFeel");
        Actions.MyInstLF("org.pushingpixels.substance.api.skin.SubstanceAutumnLookAndFeel");
        Actions.MyInstLF("org.pushingpixels.substance.api.skin.SubstanceCremeLookAndFeel");
        Actions.MyInstLF("org.pushingpixels.substance.api.skin.SubstanceCremeCoffeeLookAndFeel");
        Actions.MyInstLF("org.pushingpixels.substance.api.skin.SubstanceModerateLookAndFeel");
        Actions.MyInstLF("org.pushingpixels.substance.api.skin.SubstanceMagellanLookAndFeel");
        Actions.MyInstLF("org.pushingpixels.substance.api.skin.SubstanceMistAquaLookAndFeel");
        Actions.MyInstLF("org.pushingpixels.substance.api.skin.SubstanceMistSilverLookAndFeel");
        Actions.MyInstLF("org.pushingpixels.substance.api.skin.SubstanceOfficeBlue2007LookAndFeel");
        Actions.MyInstLF("org.pushingpixels.substance.api.skin.SubstanceOfficeBlack2007LookAndFeel");
        Actions.MyInstLF("org.pushingpixels.substance.api.skin.SubstanceOfficeSilver2007LookAndFeel");
        Actions.MyInstLF("org.pushingpixels.substance.api.skin.SubstanceNebulaLookAndFeel");
        Actions.MyInstLF("org.pushingpixels.substance.api.skin.SubstanceNebulaBrickWallLookAndFeel");
        Actions.MyInstLF("org.pushingpixels.substance.api.skin.SubstanceGeminiLookAndFeel");
        Actions.MyInstLF("org.pushingpixels.substance.api.skin.SubstanceDustCoffeeLookAndFeel");
        Actions.MyInstLF("org.pushingpixels.substance.api.skin.SubstanceDustLookAndFeel");
        Actions.MyInstLF("org.pushingpixels.substance.api.skin.SubstanceRavenLookAndFeel");
        Actions.MyInstLF("org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel");
        Actions.MyInstLF("org.pushingpixels.substance.api.skin.SubstanceGraphiteAquaLookAndFeel");
        Actions.MyInstLF("org.pushingpixels.substance.api.skin.SubstanceGraphiteGlassLookAndFeel");
        Actions.MyInstLF("org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel");
        Actions.MyInstLF("org.pushingpixels.substance.api.skin.SubstanceBusinessBlueSteelLookAndFeel");
        Actions.MyInstLF("org.pushingpixels.substance.api.skin.SubstanceBusinessLookAndFeel");
        Actions.MyInstLF("org.pushingpixels.substance.api.skin.SubstanceMarinerLookAndFeel");
        Actions.MyInstLF("org.pushingpixels.substance.api.skin.SubstanceCeruleanLookAndFeel");
        Actions.MyInstLF("org.pushingpixels.substance.api.skin.SubstanceTwilightLookAndFeel");
    }

    public void about(JFrame frame) {
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/SJCE/img/sjce-130x87.png"));
        JOptionPane.showMessageDialog(frame, "SJCE - free portable cross-platform graphical chess game.\nSupport many best free java xboard/uci chess ehgines.\nIt is possible to play Human-Human, Human-Engine and\nEngine-vs-Engine, both White and Black.\nSpecial thanks for Norbert Raimund Leisner - \nhttp://computer-chess.org/ , and also for Dr. \nRoland Stuckardt - http://www.stuckardt.de/ ,\nand also for all Java-chess-engine developers.\nTested on Windows/Linux. Need jre1.8.\nRoman Koldaev, Saratov city, Russia \nHome = http://sjce.sf.net , or \nhttps://github.com/harp077/sjce ,\nMail = harp07@mail.ru .", "SJCE = Strong Java Chess Engines, build 08.08.18", 1, icon);
    }

    public void LinksX() {
        CElinksX celx = new CElinksX(XChessFrame.frame, true);
        celx.setVisible(true);
    }

    public void LinksU() {
        CElinksU celu = new CElinksU(XChessFrame.frame, true);
        celu.setVisible(true);
    }

    public void useTime() {
        if (UseClock.equals("true")) {
            UseClock = "false";
            XChessFrame.bUseClock.setSelected(false);
            XChessFrame.mUseClock.setSelected(false);
            XChessFrame.sidePanel.remove(XChessFrame.chessClock);
            XChessFrame.mTime.setEnabled(false);
            XChessFrame.bcomboTime.setEnabled(false);
            XChessFrame.sidePanel.updateUI();
            XChessFrame.mUseClock.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/clock-stop-16.png")));
            XChessFrame.bUseClock.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/clock-stop-24.png")));
        } else {
            UseClock = "true";
            XChessFrame.bUseClock.setSelected(true);
            XChessFrame.mUseClock.setSelected(true);
            XChessFrame.sidePanel.add((Component)XChessFrame.chessClock, "North");
            XChessFrame.mTime.setEnabled(true);
            XChessFrame.bcomboTime.setEnabled(true);
            XChessFrame.sidePanel.updateUI();
            XChessFrame.mUseClock.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/clock-plus-16.png")));
            XChessFrame.bUseClock.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/clock-plus-24.png")));
        }
    }

    public void useSoundSwitch() {
        if (useSound.equals("true")) {
            useSound = "false";
            XChessFrame.mUseSound.setSelected(false);
            XChessFrame.bUseSound.setSelected(false);
            XChessFrame.mUseSound.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/speaker_minus.png")));
            XChessFrame.bUseSound.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/sound-delete-icon.png")));
        } else {
            useSound = "true";
            XChessFrame.mUseSound.setSelected(true);
            XChessFrame.bUseSound.setSelected(true);
            XChessFrame.mUseSound.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/speaker_plus.png")));
            XChessFrame.bUseSound.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/sound-add-icon.png")));
        }
    }

    public static void setSkin() {
        try {
            UIManager.setLookAndFeel(currentLAF);
            SwingUtilities.updateComponentTreeUI(XChessFrame.frame);
            XChessFrame.frame.pack();
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(XChessFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex) {
            Logger.getLogger(XChessFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex) {
            Logger.getLogger(XChessFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(XChessFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void appInit() {
        Actions.enableEffects();
        if (Mode.equals("hard")) {
            XChessFrame.mModeHard.setSelected(true);
            XChessFrame.mModeEasy.setSelected(false);
        } else {
            XChessFrame.mModeHard.setSelected(false);
            XChessFrame.mModeEasy.setSelected(true);
        }
        XChessFrame.bcomboTime.setSelectedItem("" + Time);
        XChessFrame.bcomboMode.setSelectedItem(Mode);
        XChessFrame.bcomboDepth.setSelectedItem("" + Depth);
        if (UseClock.equals("true")) {
            XChessFrame.bUseClock.setSelected(true);
            XChessFrame.mUseClock.setSelected(true);
            XChessFrame.sidePanel.add((Component)XChessFrame.chessClock, "North");
            XChessFrame.mTime.setEnabled(true);
            XChessFrame.bcomboTime.setEnabled(true);
            XChessFrame.mUseClock.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/clock-plus-16.png")));
            XChessFrame.bUseClock.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/clock-plus-24.png")));
        } else {
            XChessFrame.bUseClock.setSelected(false);
            XChessFrame.mUseClock.setSelected(false);
            XChessFrame.sidePanel.remove(XChessFrame.chessClock);
            XChessFrame.mTime.setEnabled(false);
            XChessFrame.bcomboTime.setEnabled(false);
            XChessFrame.mUseClock.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/clock-stop-16.png")));
            XChessFrame.bUseClock.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/clock-stop-24.png")));
        }
        Actions.changeDepth(Depth);
        if (useSound.equals("false")) {
            useSound = "false";
            XChessFrame.mUseSound.setSelected(false);
            XChessFrame.bUseSound.setSelected(false);
            XChessFrame.mUseSound.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/speaker_minus.png")));
            XChessFrame.bUseSound.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/sound-delete-icon.png")));
        } else {
            useSound = "true";
            XChessFrame.mUseSound.setSelected(true);
            XChessFrame.bUseSound.setSelected(true);
            XChessFrame.mUseSound.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/speaker_plus.png")));
            XChessFrame.bUseSound.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/sound-add-icon.png")));
        }
    }

    public static void viborCE(String ce, String colorCE) {
        if (colorCE.equals("white")) {
            whitePlayerCE = ce;
            XChessFrame.comboWPlayerCE.setSelectedItem(ce);
        } else {
            blackPlayerCE = ce;
            XChessFrame.comboBPlayerCE.setSelectedItem(ce);
        }
        switch (ce) {
            case "Human": {
                if (colorCE.equals("white")) {
                    Actions.setIconAndTipCE("ce/logo/user-white.png", colorCE, "human");
                    break;
                }
                Actions.setIconAndTipCE("ce/logo/user-black.png", colorCE, "human");
                break;
            }
            case "Chess22k": {
                Actions.setIconAndTipCE("ce/logo/22k-well_100x50.png", colorCE, "uci");
                break;
            }
            case "Alf": {
                Actions.setIconAndTipCE("ce/logo/alf_100x50.gif", colorCE, "xboard");
                break;
            }
            case "Animats": {
                Actions.setIconAndTipCE("ce/logo/animatschess_100x50.gif", colorCE, "xboard");
                XChessFrame.bUndoLast.setEnabled(false);
                XChessFrame.mUndoLast.setEnabled(false);
                break;
            }
            case "ArabianKnight": {
                Actions.setIconAndTipCE("ce/logo/arabian_knight_100x50.gif", colorCE, "xboard");
                break;
            }
            case "Bagatur": {
                Actions.setIconAndTipCE("ce/logo/bagatur_100x50.png", colorCE, "uci");
                break;
            }
            case "BremboCE": {
                Actions.setIconAndTipCE("ce/logo/brembo-well_100x50.png", colorCE, "xboard");
                break;
            }
            case "Calculon": {
                Actions.setIconAndTipCE("ce/logo/calculon-black_100x50.png", colorCE, "uci");
                break;
            }
            case "Carballo": {
                Actions.setIconAndTipCE("ce/logo/carballo_100x50.png", colorCE, "uci");
                break;
            }
            case "CaveChess": {
                Actions.setIconAndTipCE("ce/logo/cave_100x50.png", colorCE, "xboard");
                XChessFrame.bUndoLast.setEnabled(false);
                XChessFrame.mUndoLast.setEnabled(false);
                break;
            }
            case "CupCake": {
                Actions.setIconAndTipCE("ce/logo/cupcake_100x50.gif", colorCE, "xboard");
                XChessFrame.bUndoLast.setEnabled(false);
                XChessFrame.mUndoLast.setEnabled(false);
                break;
            }
            case "Cuckoo": {
                Actions.setIconAndTipCE("ce/logo/cuckoochess_100x50.gif", colorCE, "uci");
                break;
            }
            case "ChessBotX": {
                Actions.setIconAndTipCE("ce/logo/ChessBotX 3.png", colorCE, "xboard");
                XChessFrame.bUndoLast.setEnabled(false);
                XChessFrame.mUndoLast.setEnabled(false);
                break;
            }
            case "DeepBrutePos": {
                Actions.setIconAndTipCE("ce/logo/pos_100x50.gif", colorCE, "xboard");
                XChessFrame.bUndoLast.setEnabled(false);
                XChessFrame.mUndoLast.setEnabled(false);
                break;
            }
            case "Detroid": {
                Actions.setIconAndTipCE("ce/logo/detroid-well_100x50.png", colorCE, "uci");
                break;
            }
            case "Eden": {
                Actions.setIconAndTipCE("ce/logo/Eden2_100x50.gif", colorCE, "uci");
                break;
            }
            case "FairyPrincess": {
                Actions.setIconAndTipCE("ce/logo/fp_100x50.png", colorCE, "xboard");
                break;
            }
            case "Fischerle": {
                Actions.setIconAndTipCE("ce/logo/fischerle_100x50.png", colorCE, "uci");
                JOptionPane.showMessageDialog(XChessFrame.frame, "Fischerle create by Dr. Roland Stuckardt - http://www.stuckardt.de/\nSJCE use Fischerle version 0.9.70 SE 32 bit\nDocuments about Fischerle and licence see please\nin folder: /ce/Fischerle/");
                break;
            }
            case "Flux": {
                Actions.setIconAndTipCE("ce/logo/fluxII_100x50.gif", colorCE, "uci");
                break;
            }
            case "Frittle": {
                Actions.setIconAndTipCE("ce/logo/frittle_100x50.gif", colorCE, "xboard");
                break;
            }
            case "FrankWalter": {
                Actions.setIconAndTipCE("ce/logo/frank-walter_100x50.gif", colorCE, "xboard");
                break;
            }
            case "Gladiator": {
                Actions.setIconAndTipCE("ce/logo/gladiator7_100x50.jpg", colorCE, "xboard");
                XChessFrame.bUndoLast.setEnabled(false);
                XChessFrame.mUndoLast.setEnabled(false);
                break;
            }
            case "GNU Chess": {
                Actions.setIconAndTipCE("ce/logo/gnu-chess_100x50.png", colorCE, "xboard");
                break;
            }
            case "Jchess": {
                Actions.setIconAndTipCE("ce/logo/jchess_100x50.gif", colorCE, "xboard");
                XChessFrame.bUndoLast.setEnabled(false);
                XChessFrame.mUndoLast.setEnabled(false);
                break;
            }
            case "Javalin": {
                Actions.setIconAndTipCE("ce/logo/javalin-native_100x50.png", colorCE, "xboard");
                XChessFrame.bUndoLast.setEnabled(false);
                XChessFrame.mUndoLast.setEnabled(false);
                break;
            }
            case "jChecs": {
                Actions.setIconAndTipCE("ce/logo/jchecs-native_100x50.png", colorCE, "xboard");
                XChessFrame.aktion.gojChecsSelectCE();
                break;
            }
            case "Kasparov": {
                Actions.setIconAndTipCE("ce/logo/kasparov-chess_100x50.png", colorCE, "uci");
                break;
            }
            case "KennyClassIQ": {
                Actions.setIconAndTipCE("ce/logo/KennyClassIQ_100x50.png", colorCE, "xboard");
                break;
            }
            case "KingsOut": {
                Actions.setIconAndTipCE("ce/logo/kingsout_100x50_2.gif", colorCE, "xboard");
                break;
            }
            case "Koedem": {
                Actions.setIconAndTipCE("ce/logo/koedem_100x50.png", colorCE, "uci");
                break;
            }
            case "Krudo": {
                Actions.setIconAndTipCE("ce/logo/krudo.png", colorCE, "uci");
                break;
            }
            case "Magnum": {
                Actions.setIconAndTipCE("ce/logo/magnum_100x50.gif", colorCE, "uci");
                break;
            }
            case "Mediocre": {
                Actions.setIconAndTipCE("ce/logo/mediocre_100x50.gif", colorCE, "uci");
                break;
            }
            case "OliThink": {
                Actions.setIconAndTipCE("ce/logo/olithink-java_100x50.gif", colorCE, "xboard");
                break;
            }
            case "Presbyter": {
                Actions.setIconAndTipCE("ce/logo/presbyter_100x50.png", colorCE, "uci");
                break;
            }
            case "Phoenix": {
                Actions.setIconAndTipCE("ce/logo/phoenix-well_100x27.png", colorCE, "uci");
                break;
            }
            case "Pulse": {
                Actions.setIconAndTipCE("ce/logo/pulse_100x50.png", colorCE, "uci");
                break;
            }
            case "Rival": {
                Actions.setIconAndTipCE("ce/logo/RivalUCI_100x50.gif", colorCE, "uci");
                break;
            }
            case "Rumney": {
                Actions.setIconAndTipCE("ce/logo/rumney_100x29.png", colorCE, "uci");
                break;
            }
            case "Talvmenni": {
                Actions.setIconAndTipCE("ce/logo/talvmenni_100x50.gif", colorCE, "xboard");
                XChessFrame.bUndoLast.setEnabled(false);
                XChessFrame.mUndoLast.setEnabled(false);
                break;
            }
            case "Tiffanys": {
                Actions.setIconAndTipCE("ce/logo/Tiffanys 2.png", colorCE, "xboard");
                XChessFrame.bUndoLast.setEnabled(false);
                XChessFrame.mUndoLast.setEnabled(false);
                break;
            }
            case "Tri-OS": {
                Actions.setIconAndTipCE("ce/logo/tri-os_100x50.png", colorCE, "xboard");
                break;
            }
            case "Unidexter": {
                Actions.setIconAndTipCE("ce/logo/unidexter_100x50.png", colorCE, "uci");
                break;
            }
            case "Ziggy": {
                Actions.setIconAndTipCE("ce/logo/ziggy_100x50.gif", colorCE, "uci");
            }
        }
    }

    public static void CopyToClipBoard(String cps) {
        ClipboardTextTransfer textTransfer = new ClipboardTextTransfer();
        textTransfer.setClipboardContents(cps);
    }

    public static void enableEffects() {
        AnimationConfigurationManager.getInstance().allowAnimations(AnimationFacet.ARM);
        AnimationConfigurationManager.getInstance().allowAnimations(AnimationFacet.FOCUS);
        AnimationConfigurationManager.getInstance().allowAnimations(AnimationFacet.FOCUS_LOOP_ANIMATION);
        AnimationConfigurationManager.getInstance().allowAnimations(AnimationFacet.GHOSTING_BUTTON_PRESS);
        AnimationConfigurationManager.getInstance().allowAnimations(AnimationFacet.GHOSTING_ICON_ROLLOVER);
        AnimationConfigurationManager.getInstance().allowAnimations(AnimationFacet.ICON_GLOW);
        AnimationConfigurationManager.getInstance().allowAnimations(AnimationFacet.PRESS);
        AnimationConfigurationManager.getInstance().allowAnimations(AnimationFacet.ROLLOVER);
        AnimationConfigurationManager.getInstance().allowAnimations(AnimationFacet.SELECTION);
    }

    public static void setIconAndTipCE(String pathce, String colorCEn, String tipCE) {
        XChessFrame.bUndoLast.setEnabled(true);
        XChessFrame.mUndoLast.setEnabled(true);
        if (colorCEn.equals("white")) {
            whitePlayerTip = tipCE;
            XChessFrame.chessClock.label[2].setIcon(new ImageIcon(pathce));
        } else {
            blackPlayerTip = tipCE;
            XChessFrame.chessClock.label[3].setIcon(new ImageIcon(pathce));
        }
    }

    public void killEngineProc(String color) {
        switch (color) {
            case "white": {
                if (EngineAgent.engineIOwhite == null) break;
                EngineAgent.engineIOwhite.writeLine("quit");
                EngineAgent.engineIOwhite.destroy();
                break;
            }
            case "black": {
                if (EngineAgent.engineIOblack == null) break;
                EngineAgent.engineIOblack.writeLine("quit");
                EngineAgent.engineIOblack.destroy();
            }
        }
    }

    public void killAllEngines() {
        XChessFrame.aktion.killEngineProc("white");
        XChessFrame.aktion.killEngineProc("black");
        XChessFrame.outputArea.setText("Please, select chess engine and press New Game !");
        XChessFrame.chessClock.stop();
        XChessFrame.chessClock.setTime(Time * 60000);
        XChessFrame.boardUI.setBoard(Utility.INITIAL_BOARD);
        XChessFrame.moveListUI.clear();
        XChessFrame.frame.loadBoardTheme();
        this.restColorWhite_restColorBlack();
    }

    public void sendEngineCmd(String color) {
        switch (color) {
            case "white": {
                if (EngineAgent.engineIOwhite != null) {
                    String buf = "";
                    buf = (String)JOptionPane.showInputDialog(XChessFrame.frame, " Please, enter Command for send to White Engine ( be careful ! ): ", "Command for send to White Engine", 2, new ImageIcon(this.getClass().getResource("/SJCE/img/knight-white-32.png")), null, null);
                    if (buf == null || buf.length() <= 0) break;
                    EngineAgent.engineIOwhite.writeLine(buf);
                    XChessFrame.outputArea.append("<write to White Engine>: " + buf + "\n");
                    if (!buf.equals("quit")) break;
                    XChessFrame.aktion.killEngineProc("white");
                    break;
                }
                JOptionPane.showMessageDialog(XChessFrame.frame, "White Engine process not running !");
                break;
            }
            case "black": {
                if (EngineAgent.engineIOblack != null) {
                    String buf = "";
                    buf = (String)JOptionPane.showInputDialog(XChessFrame.frame, " Please, enter Command for send to Black Engine ( be careful ! ): ", "Command for send to Black Engine", 2, new ImageIcon(this.getClass().getResource("/SJCE/img/knight-black-32.png")), null, null);
                    if (buf == null || buf.length() <= 0) break;
                    EngineAgent.engineIOblack.writeLine(buf);
                    XChessFrame.outputArea.append("<write to Black Engine>: " + buf + "\n");
                    if (!buf.equals("quit")) break;
                    XChessFrame.aktion.killEngineProc("black");
                    break;
                }
                JOptionPane.showMessageDialog(XChessFrame.frame, "Black Engine process not running !");
            }
        }
    }

    public static void sendEngineCmd(String color, String buf) {
        switch (color) {
            case "white": {
                if (EngineAgent.engineIOwhite == null || buf == null || buf.length() <= 0) break;
                EngineAgent.engineIOwhite.writeLine(buf);
                XChessFrame.outputArea.append("<write to White Engine>: " + buf + "\n");
                break;
            }
            case "black": {
                if (EngineAgent.engineIOblack == null || buf == null || buf.length() <= 0) break;
                EngineAgent.engineIOblack.writeLine(buf);
                XChessFrame.outputArea.append("<write to Black Engine>: " + buf + "\n");
            }
        }
    }

    public void moveColorWhite_restColorBlack() {
        XChessFrame.chessClock.label[0].setBackground(moveColor);
        XChessFrame.chessClock.label[2].setBackground(moveColor);
        XChessFrame.chessClock.label[1].setBackground(restColor);
        XChessFrame.chessClock.label[3].setBackground(restColor);
    }

    public void restColorWhite_moveColorBlack() {
        XChessFrame.chessClock.label[0].setBackground(restColor);
        XChessFrame.chessClock.label[2].setBackground(restColor);
        XChessFrame.chessClock.label[1].setBackground(moveColor);
        XChessFrame.chessClock.label[3].setBackground(moveColor);
    }

    public void restColorWhite_restColorBlack() {
        XChessFrame.chessClock.label[0].setBackground(restColor);
        XChessFrame.chessClock.label[2].setBackground(restColor);
        XChessFrame.chessClock.label[1].setBackground(restColor);
        XChessFrame.chessClock.label[3].setBackground(restColor);
    }

    public static void playWAV(String filename) {
        File snd = null;
        snd = new File(filename);
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(snd));
            clip.start();
        }
        catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }

    public static void undoLastMove(Move move, String color) {
        if (!gameTip.equals("EE")) {
            if (whitePlayerTip.equals("uci") || blackPlayerTip.equals("uci")) {
                Object bf = uciAllMovesString.trim().split("\\s+");
                String bufer = "";
                for (int i = 0; i < ((Object)bf).length - 1; ++i) {
                    bufer = bufer + " " + (String)bf[i];
                }
                uciAllMovesString = bufer;
            }
            block4 : switch (color) {
                case "white": {
                    if (!whitePlayerTip.equals("xboard")) break;
                    switch (whitePlayerCE) {
                        case "Alf": {
                            Actions.sendEngineCmd("white", "undo");
                            Actions.sendEngineCmd("white", "undo");
                            break block4;
                        }
                        case "ArabianKnight": {
                            Actions.sendEngineCmd("white", "undo");
                            Actions.sendEngineCmd("white", "undo");
                            break block4;
                        }
                        case "BremboCE": {
                            Actions.sendEngineCmd("white", "remove");
                            break block4;
                        }
                        case "FrankWalter": {
                            Actions.sendEngineCmd("white", "undo");
                            Actions.sendEngineCmd("white", "undo");
                            break block4;
                        }
                        case "Frittle": {
                            Actions.sendEngineCmd("white", "undo");
                            Actions.sendEngineCmd("white", "undo");
                            break block4;
                        }
                        case "GNU Chess": {
                            Actions.sendEngineCmd("white", "undo");
                            Actions.sendEngineCmd("white", "undo");
                            break block4;
                        }
                        case "KingsOut": {
                            Actions.sendEngineCmd("white", "remove");
                            break block4;
                        }
                        case "OliThink": {
                            Actions.sendEngineCmd("white", "remove");
                            break block4;
                        }
                        case "Tri-OS": {
                            Actions.sendEngineCmd("white", "undo");
                            break block4;
                        }
                        case "jChecs": {
                            Actions.sendEngineCmd("white", "undo");
                            Actions.sendEngineCmd("white", "undo");
                        }
                    }
                    break;
                }
                case "black": {
                    if (!blackPlayerTip.equals("xboard")) break;
                    switch (blackPlayerCE) {
                        case "Alf": {
                            Actions.sendEngineCmd("black", "undo");
                            Actions.sendEngineCmd("black", "undo");
                            break block4;
                        }
                        case "ArabianKnight": {
                            Actions.sendEngineCmd("black", "undo");
                            Actions.sendEngineCmd("black", "undo");
                            break block4;
                        }
                        case "BremboCE": {
                            Actions.sendEngineCmd("black", "remove");
                            break block4;
                        }
                        case "FrankWalter": {
                            Actions.sendEngineCmd("black", "undo");
                            Actions.sendEngineCmd("black", "undo");
                            break block4;
                        }
                        case "Frittle": {
                            Actions.sendEngineCmd("black", "undo");
                            Actions.sendEngineCmd("black", "undo");
                            break block4;
                        }
                        case "GNU Chess": {
                            Actions.sendEngineCmd("black", "undo");
                            Actions.sendEngineCmd("black", "undo");
                            break block4;
                        }
                        case "KingsOut": {
                            Actions.sendEngineCmd("black", "remove");
                            break block4;
                        }
                        case "OliThink": {
                            Actions.sendEngineCmd("black", "remove");
                            break block4;
                        }
                        case "Tri-OS": {
                            Actions.sendEngineCmd("black", "undo");
                            break block4;
                        }
                        case "jChecs": {
                            Actions.sendEngineCmd("black", "undo");
                            Actions.sendEngineCmd("black", "undo");
                        }
                    }
                }
            }
            int source = move.getSource();
            int destin = move.getDestination();
            move.undoMove(XChessFrame.boardUI.getBoard());
            XChessFrame.boardUI.update(source);
            XChessFrame.boardUI.update(destin);
            XChessFrame.boardUI.update(XChessFrame.SQUARES);
            MoveListUI.moveList.remove(MoveListUI.moveList.lastIndexOf(move));
            MoveListUI.listModel.removeElementAt(MoveListUI.listModel.size() - 1);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void bmUndoLastMoveEventHandler() {
        if (gameTip.equals("EE")) {
            JOptionPane.showMessageDialog(XChessFrame.frame, "Undo not support for engine tournament !");
            return;
        }
        if (whitePlayerCE.equals("Animats") || whitePlayerCE.equals("CaveChess") || whitePlayerCE.equals("CupCake") || whitePlayerCE.equals("ChessBotX") || whitePlayerCE.equals("DeepBrutePos") || whitePlayerCE.equals("Tiffanys") || whitePlayerCE.equals("Gladiator") || whitePlayerCE.equals("Talvmenni") || whitePlayerCE.equals("Jchess") || whitePlayerCE.equals("Javalin")) {
            JOptionPane.showMessageDialog(XChessFrame.frame, "This engine not support undo or remove comands !");
            return;
        }
        if (blackPlayerCE.equals("Animats") || blackPlayerCE.equals("CaveChess") || blackPlayerCE.equals("CupCake") || blackPlayerCE.equals("ChessBotX") || blackPlayerCE.equals("DeepBrutePos") || blackPlayerCE.equals("Tiffanys") || blackPlayerCE.equals("Gladiator") || blackPlayerCE.equals("Talvmenni") || blackPlayerCE.equals("Jchess") || blackPlayerCE.equals("Javalin")) {
            JOptionPane.showMessageDialog(XChessFrame.frame, "This engine not support undo or remove comands  !");
            return;
        }
        if (whiteLastMove != null) {
            if (blackLastMove != null) {
                if (gameTip.equals("EH")) {
                    Actions.undoLastMove(whiteLastMove, "white");
                    Actions.undoLastMove(blackLastMove, "black");
                }
                if (gameTip.equals("HE")) {
                    Actions.undoLastMove(blackLastMove, "black");
                    Actions.undoLastMove(whiteLastMove, "white");
                }
                if (gameTip.equals("HH")) {
                    Actions.undoLastMove(whiteLastMove, "white");
                    Actions.undoLastMove(blackLastMove, "black");
                }
                whiteLastMove = null;
                blackLastMove = null;
                return;
            }
        }
        JOptionPane.showMessageDialog(XChessFrame.frame, "Please, at first make a move and then you can make undo !");
    }

    public static void logShow() {
        XChessFrame.logFrame = new LogShow();
        LogShow.gameLogTextArea.setText(XChessFrame.outputArea.getText());
        XChessFrame.logFrame.setVisible(true);
    }

    public static void clipboardShow() {
        TextTransfer textTransfer = new TextTransfer();
        String buf = textTransfer.getClipboardContents();
        ShowClipBoard sc = new ShowClipBoard(XChessFrame.logFrame, true);
        ShowClipBoard.ClipboardText.setText(buf);
        sc.setVisible(true);
    }

    public void gojChecsSelectCE() {
        String changeLook = (String)JOptionPane.showInputDialog(XChessFrame.frame, "Choose jChecs engine algorithm Here:", "Select jChecs engine algorithm", 3, new ImageIcon(this.getClass().getResource("/SJCE/img/jchecs-native_100x50.png")), jchecsCeSelect.toArray(), null);
        if (changeLook != null) {
            for (int a = 0; a < jchecsCeSelect.size(); ++a) {
                if (!changeLook.equals(jchecsCeSelect.get(a))) continue;
                jchecsEngineTip = jchecsCeSelect.get(a);
                break;
            }
        }
    }

    static {
        currentLAF = "org.pushingpixels.substance.api.skin.SubstanceSaharaLookAndFeel";
        UseClock = "true";
        BoardThemeFig = "cyan-red";
        BoardThemeFon = "yellow-green";
        Prohod_White_Event = 0;
        Prohod_White_Destination = -1;
        Prohod_Black_Event = 0;
        Prohod_Black_Destination = -1;
        uciAllMovesString = "";
        enginePromotionType = "";
        enginePromotionFig = "";
        moveColor = Color.GREEN;
        restColor = Color.WHITE;
        whitePlayerCE = "";
        whitePlayerTip = "";
        blackPlayerCE = "";
        blackPlayerTip = "";
        gameTip = "";
        enemyTip = "";
        currentMute = "false";
        currentMixer = 100;
        useSound = "true";
        promotionCount = 0;
        whiteLastMove = null;
        blackLastMove = null;
        blackRivalMovesString = "";
        whiteRivalMovesString = "";
        jchecsEngineTip = "jChecs.NegaScout";
        jchecsCeSelect = new ArrayList<String>();
        pref = Preferences.userRoot().node("sjce/cfg");
    }
}

