// 
// Decompiled by Procyon v0.5.36
// 

package SJCE.more;

import java.util.ArrayList;
import SJCE.more.Log.ShowClipBoard;
import SJCE.more.Log.TextTransfer;
import javax.swing.JTextArea;
import SJCE.more.Log.LogShow;
import SJCE.xgui.JPanel.BoardUI;
import SJCE.xgui.JList.MoveListUI;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import SJCE.xgui.Utility;
import SJCE.xgui.Agent.EngineAgent;
import org.pushingpixels.lafwidget.animation.AnimationFacet;
import org.pushingpixels.lafwidget.animation.AnimationConfigurationManager;
import SJCE.more.Log.ClipboardTextTransfer;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JPanel;
import SJCE.more.Links.CElinksU;
import java.awt.Frame;
import SJCE.more.Links.CElinksX;
import javax.swing.Icon;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import SJCE.XChessFrame;
import java.util.prefs.Preferences;
import java.util.List;
import SJCE.xgui.Move;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Actions
{
    public static String Mode;
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
        Actions.jchecsCeSelect.add("jChecs.AlphaBeta");
        Actions.jchecsCeSelect.add("jChecs.NegaScout");
    }
    
    public static void changeDepth(final int dd) {
        XChessFrame.frame.mDepthRadioGroup.clearSelection();
        final Actions aktion = XChessFrame.aktion;
        switch (Actions.Depth = dd) {
            case 2: {
                final XChessFrame frame = XChessFrame.frame;
                XChessFrame.mDepth2.setSelected(true);
                break;
            }
            case 3: {
                final XChessFrame frame2 = XChessFrame.frame;
                XChessFrame.mDepth3.setSelected(true);
                break;
            }
            case 4: {
                final XChessFrame frame3 = XChessFrame.frame;
                XChessFrame.mDepth4.setSelected(true);
                break;
            }
            case 5: {
                final XChessFrame frame4 = XChessFrame.frame;
                XChessFrame.mDepth5.setSelected(true);
                break;
            }
            case 6: {
                final XChessFrame frame5 = XChessFrame.frame;
                XChessFrame.mDepth6.setSelected(true);
                break;
            }
            case 7: {
                final XChessFrame frame6 = XChessFrame.frame;
                XChessFrame.mDepth7.setSelected(true);
                break;
            }
            case 8: {
                final XChessFrame frame7 = XChessFrame.frame;
                XChessFrame.mDepth8.setSelected(true);
                break;
            }
            case 9: {
                final XChessFrame frame8 = XChessFrame.frame;
                XChessFrame.mDepth9.setSelected(true);
                break;
            }
        }
        final JComboBox<String> bcomboDepth = XChessFrame.bcomboDepth;
        final StringBuilder append = new StringBuilder().append("");
        final Actions aktion2 = XChessFrame.aktion;
        bcomboDepth.setSelectedItem(append.append(Actions.Depth).toString());
    }
    
    public static void changeTime(final int tt) {
        XChessFrame.frame.mTimeRadioGroup.clearSelection();
        final Actions aktion = XChessFrame.aktion;
        switch (Actions.Time = tt) {
            case 5: {
                final XChessFrame frame = XChessFrame.frame;
                XChessFrame.mTime05.setSelected(true);
                break;
            }
            case 10: {
                final XChessFrame frame2 = XChessFrame.frame;
                XChessFrame.mTime10.setSelected(true);
                break;
            }
            case 15: {
                final XChessFrame frame3 = XChessFrame.frame;
                XChessFrame.mTime15.setSelected(true);
                break;
            }
            case 20: {
                final XChessFrame frame4 = XChessFrame.frame;
                XChessFrame.mTime20.setSelected(true);
                break;
            }
            case 25: {
                final XChessFrame frame5 = XChessFrame.frame;
                XChessFrame.mTime25.setSelected(true);
                break;
            }
            case 30: {
                final XChessFrame frame6 = XChessFrame.frame;
                XChessFrame.mTime30.setSelected(true);
                break;
            }
        }
        final JComboBox bcomboTime = XChessFrame.bcomboTime;
        final StringBuilder append = new StringBuilder().append("");
        final Actions aktion2 = XChessFrame.aktion;
        bcomboTime.setSelectedItem(append.append(Actions.Time).toString());
    }
    
    public static void MyInstLF(final String lf) {
        XChessFrame.lookAndFeelsDisplay.add(lf);
        XChessFrame.lookAndFeelsRealNames.add(lf);
    }
    
    public static void InstallLF() {
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceSaharaLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceAutumnLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceCremeLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceCremeCoffeeLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceModerateLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceMagellanLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceMistAquaLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceMistSilverLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceOfficeBlue2007LookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceOfficeBlack2007LookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceOfficeSilver2007LookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceNebulaLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceNebulaBrickWallLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceGeminiLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceDustCoffeeLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceDustLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceRavenLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceGraphiteAquaLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceGraphiteGlassLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceBusinessBlueSteelLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceBusinessLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceMarinerLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceCeruleanLookAndFeel");
        MyInstLF("org.pushingpixels.substance.api.skin.SubstanceTwilightLookAndFeel");
    }
    
    public void about(final JFrame frame) {
        final ImageIcon icon = new ImageIcon(this.getClass().getResource("/SJCE/img/sjce-130x87.png"));
        JOptionPane.showMessageDialog(frame, "SJCE - free portable cross-platform graphical chess game.\nSupport many best free java xboard/uci chess ehgines.\nIt is possible to play Human-Human, Human-Engine and\nEngine-vs-Engine, both White and Black.\nSpecial thanks for Norbert Raimund Leisner - \nhttp://computer-chess.org/ , and also for Dr. \nRoland Stuckardt - http://www.stuckardt.de/ ,\nand also for all Java-chess-engine developers.\nTested on Windows/Linux. Need jre1.8.\nRoman Koldaev, Saratov city, Russia \nHome = http://sjce.sf.net , or \nhttps://github.com/harp077/sjce ,\nMail = harp07@mail.ru .", "SJCE = Strong Java Chess Engines, build 08.08.18", 1, icon);
    }
    
    public void LinksX() {
        final CElinksX celx = new CElinksX(XChessFrame.frame, true);
        celx.setVisible(true);
    }
    
    public void LinksU() {
        final CElinksU celu = new CElinksU(XChessFrame.frame, true);
        celu.setVisible(true);
    }
    
    public void useTime() {
        final Actions aktion = XChessFrame.aktion;
        if (Actions.UseClock.equals("true")) {
            final Actions aktion2 = XChessFrame.aktion;
            Actions.UseClock = "false";
            XChessFrame.bUseClock.setSelected(false);
            XChessFrame.mUseClock.setSelected(false);
            final JPanel sidePanel = XChessFrame.sidePanel;
            final XChessFrame frame = XChessFrame.frame;
            sidePanel.remove(XChessFrame.chessClock);
            XChessFrame.mTime.setEnabled(false);
            XChessFrame.bcomboTime.setEnabled(false);
            XChessFrame.sidePanel.updateUI();
            final XChessFrame frame2 = XChessFrame.frame;
            XChessFrame.mUseClock.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/clock-stop-16.png")));
            final XChessFrame frame3 = XChessFrame.frame;
            XChessFrame.bUseClock.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/clock-stop-24.png")));
        }
        else {
            final Actions aktion3 = XChessFrame.aktion;
            Actions.UseClock = "true";
            XChessFrame.bUseClock.setSelected(true);
            XChessFrame.mUseClock.setSelected(true);
            final JPanel sidePanel2 = XChessFrame.sidePanel;
            final XChessFrame frame4 = XChessFrame.frame;
            sidePanel2.add(XChessFrame.chessClock, "North");
            XChessFrame.mTime.setEnabled(true);
            XChessFrame.bcomboTime.setEnabled(true);
            XChessFrame.sidePanel.updateUI();
            final XChessFrame frame5 = XChessFrame.frame;
            XChessFrame.mUseClock.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/clock-plus-16.png")));
            final XChessFrame frame6 = XChessFrame.frame;
            XChessFrame.bUseClock.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/clock-plus-24.png")));
        }
    }
    
    public void useSoundSwitch() {
        final Actions aktion = XChessFrame.aktion;
        if (Actions.useSound.equals("true")) {
            final Actions aktion2 = XChessFrame.aktion;
            Actions.useSound = "false";
            final XChessFrame frame = XChessFrame.frame;
            XChessFrame.mUseSound.setSelected(false);
            final XChessFrame frame2 = XChessFrame.frame;
            XChessFrame.bUseSound.setSelected(false);
            final XChessFrame frame3 = XChessFrame.frame;
            XChessFrame.mUseSound.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/speaker_minus.png")));
            final XChessFrame frame4 = XChessFrame.frame;
            XChessFrame.bUseSound.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/sound-delete-icon.png")));
        }
        else {
            final Actions aktion3 = XChessFrame.aktion;
            Actions.useSound = "true";
            final XChessFrame frame5 = XChessFrame.frame;
            XChessFrame.mUseSound.setSelected(true);
            final XChessFrame frame6 = XChessFrame.frame;
            XChessFrame.bUseSound.setSelected(true);
            final XChessFrame frame7 = XChessFrame.frame;
            XChessFrame.mUseSound.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/speaker_plus.png")));
            final XChessFrame frame8 = XChessFrame.frame;
            XChessFrame.bUseSound.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/sound-add-icon.png")));
        }
    }
    
    public static void setSkin() {
        try {
            final Actions aktion = XChessFrame.aktion;
            UIManager.setLookAndFeel(Actions.currentLAF);
            SwingUtilities.updateComponentTreeUI(XChessFrame.frame);
            XChessFrame.frame.pack();
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(XChessFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(XChessFrame.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(XChessFrame.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(XChessFrame.class.getName()).log(Level.SEVERE, null, ex4);
        }
    }
    
    public void appInit() {
        final Actions aktion = XChessFrame.aktion;
        enableEffects();
        final Actions aktion2 = XChessFrame.aktion;
        if (Actions.Mode.equals("hard")) {
            XChessFrame.mModeHard.setSelected(true);
            XChessFrame.mModeEasy.setSelected(false);
        }
        else {
            XChessFrame.mModeHard.setSelected(false);
            XChessFrame.mModeEasy.setSelected(true);
        }
        final JComboBox bcomboTime = XChessFrame.bcomboTime;
        final StringBuilder append = new StringBuilder().append("");
        final Actions aktion3 = XChessFrame.aktion;
        bcomboTime.setSelectedItem(append.append(Actions.Time).toString());
        final JComboBox<String> bcomboMode = XChessFrame.bcomboMode;
        final Actions aktion4 = XChessFrame.aktion;
        bcomboMode.setSelectedItem(Actions.Mode);
        final JComboBox<String> bcomboDepth = XChessFrame.bcomboDepth;
        final StringBuilder append2 = new StringBuilder().append("");
        final Actions aktion5 = XChessFrame.aktion;
        bcomboDepth.setSelectedItem(append2.append(Actions.Depth).toString());
        final Actions aktion6 = XChessFrame.aktion;
        if (Actions.UseClock.equals("true")) {
            XChessFrame.bUseClock.setSelected(true);
            XChessFrame.mUseClock.setSelected(true);
            XChessFrame.sidePanel.add(XChessFrame.chessClock, "North");
            XChessFrame.mTime.setEnabled(true);
            XChessFrame.bcomboTime.setEnabled(true);
            final XChessFrame frame = XChessFrame.frame;
            XChessFrame.mUseClock.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/clock-plus-16.png")));
            final XChessFrame frame2 = XChessFrame.frame;
            XChessFrame.bUseClock.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/clock-plus-24.png")));
        }
        else {
            XChessFrame.bUseClock.setSelected(false);
            XChessFrame.mUseClock.setSelected(false);
            XChessFrame.sidePanel.remove(XChessFrame.chessClock);
            XChessFrame.mTime.setEnabled(false);
            XChessFrame.bcomboTime.setEnabled(false);
            final XChessFrame frame3 = XChessFrame.frame;
            XChessFrame.mUseClock.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/clock-stop-16.png")));
            final XChessFrame frame4 = XChessFrame.frame;
            XChessFrame.bUseClock.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/clock-stop-24.png")));
        }
        final Actions aktion7 = XChessFrame.aktion;
        final Actions aktion8 = XChessFrame.aktion;
        changeDepth(Actions.Depth);
        final Actions aktion9 = XChessFrame.aktion;
        if (Actions.useSound.equals("false")) {
            final Actions aktion10 = XChessFrame.aktion;
            Actions.useSound = "false";
            final XChessFrame frame5 = XChessFrame.frame;
            XChessFrame.mUseSound.setSelected(false);
            final XChessFrame frame6 = XChessFrame.frame;
            XChessFrame.bUseSound.setSelected(false);
            final XChessFrame frame7 = XChessFrame.frame;
            XChessFrame.mUseSound.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/speaker_minus.png")));
            final XChessFrame frame8 = XChessFrame.frame;
            XChessFrame.bUseSound.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/sound-delete-icon.png")));
        }
        else {
            final Actions aktion11 = XChessFrame.aktion;
            Actions.useSound = "true";
            final XChessFrame frame9 = XChessFrame.frame;
            XChessFrame.mUseSound.setSelected(true);
            final XChessFrame frame10 = XChessFrame.frame;
            XChessFrame.bUseSound.setSelected(true);
            final XChessFrame frame11 = XChessFrame.frame;
            XChessFrame.mUseSound.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/16x16/speaker_plus.png")));
            final XChessFrame frame12 = XChessFrame.frame;
            XChessFrame.bUseSound.setIcon(new ImageIcon(this.getClass().getResource("/SJCE/img/24x24/sound-add-icon.png")));
        }
    }
    
    public static void viborCE(final String ce, final String colorCE) {
        if (colorCE.equals("white")) {
            Actions.whitePlayerCE = ce;
            XChessFrame.comboWPlayerCE.setSelectedItem(ce);
        }
        else {
            Actions.blackPlayerCE = ce;
            XChessFrame.comboBPlayerCE.setSelectedItem(ce);
        }
        switch (ce) {
            case "Human": {
                if (colorCE.equals("white")) {
                    setIconAndTipCE("ce/logo/user-white.png", colorCE, "human");
                    break;
                }
                setIconAndTipCE("ce/logo/user-black.png", colorCE, "human");
                break;
            }
            case "Chess22k": {
                setIconAndTipCE("ce/logo/22k-well_100x50.png", colorCE, "uci");
                break;
            }
            case "Alf": {
                setIconAndTipCE("ce/logo/alf_100x50.gif", colorCE, "xboard");
                break;
            }
            case "Animats": {
                setIconAndTipCE("ce/logo/animatschess_100x50.gif", colorCE, "xboard");
                final XChessFrame frame = XChessFrame.frame;
                XChessFrame.bUndoLast.setEnabled(false);
                final XChessFrame frame2 = XChessFrame.frame;
                XChessFrame.mUndoLast.setEnabled(false);
                break;
            }
            case "ArabianKnight": {
                setIconAndTipCE("ce/logo/arabian_knight_100x50.gif", colorCE, "xboard");
                break;
            }
            case "Bagatur": {
                setIconAndTipCE("ce/logo/bagatur_100x50.png", colorCE, "uci");
                break;
            }
            case "BremboCE": {
                setIconAndTipCE("ce/logo/brembo-well_100x50.png", colorCE, "xboard");
                break;
            }
            case "Calculon": {
                setIconAndTipCE("ce/logo/calculon-black_100x50.png", colorCE, "uci");
                break;
            }
            case "Carballo": {
                setIconAndTipCE("ce/logo/carballo_100x50.png", colorCE, "uci");
                break;
            }
            case "CaveChess": {
                setIconAndTipCE("ce/logo/cave_100x50.png", colorCE, "xboard");
                final XChessFrame frame3 = XChessFrame.frame;
                XChessFrame.bUndoLast.setEnabled(false);
                final XChessFrame frame4 = XChessFrame.frame;
                XChessFrame.mUndoLast.setEnabled(false);
                break;
            }
            case "CupCake": {
                setIconAndTipCE("ce/logo/cupcake_100x50.gif", colorCE, "xboard");
                final XChessFrame frame5 = XChessFrame.frame;
                XChessFrame.bUndoLast.setEnabled(false);
                final XChessFrame frame6 = XChessFrame.frame;
                XChessFrame.mUndoLast.setEnabled(false);
                break;
            }
            case "Cuckoo": {
                setIconAndTipCE("ce/logo/cuckoochess_100x50.gif", colorCE, "uci");
                break;
            }
            case "ChessBotX": {
                setIconAndTipCE("ce/logo/ChessBotX 3.png", colorCE, "xboard");
                final XChessFrame frame7 = XChessFrame.frame;
                XChessFrame.bUndoLast.setEnabled(false);
                final XChessFrame frame8 = XChessFrame.frame;
                XChessFrame.mUndoLast.setEnabled(false);
                break;
            }
            case "DeepBrutePos": {
                setIconAndTipCE("ce/logo/pos_100x50.gif", colorCE, "xboard");
                final XChessFrame frame9 = XChessFrame.frame;
                XChessFrame.bUndoLast.setEnabled(false);
                final XChessFrame frame10 = XChessFrame.frame;
                XChessFrame.mUndoLast.setEnabled(false);
                break;
            }
            case "Detroid": {
                setIconAndTipCE("ce/logo/detroid-well_100x50.png", colorCE, "uci");
                break;
            }
            case "Eden": {
                setIconAndTipCE("ce/logo/Eden2_100x50.gif", colorCE, "uci");
                break;
            }
            case "FairyPrincess": {
                setIconAndTipCE("ce/logo/fp_100x50.png", colorCE, "xboard");
                break;
            }
            case "Fischerle": {
                setIconAndTipCE("ce/logo/fischerle_100x50.png", colorCE, "uci");
                JOptionPane.showMessageDialog(XChessFrame.frame, "Fischerle create by Dr. Roland Stuckardt - http://www.stuckardt.de/\nSJCE use Fischerle version 0.9.70 SE 32 bit\nDocuments about Fischerle and licence see please\nin folder: /ce/Fischerle/");
                break;
            }
            case "Flux": {
                setIconAndTipCE("ce/logo/fluxII_100x50.gif", colorCE, "uci");
                break;
            }
            case "Frittle": {
                setIconAndTipCE("ce/logo/frittle_100x50.gif", colorCE, "xboard");
                break;
            }
            case "FrankWalter": {
                setIconAndTipCE("ce/logo/frank-walter_100x50.gif", colorCE, "xboard");
                break;
            }
            case "Gladiator": {
                setIconAndTipCE("ce/logo/gladiator7_100x50.jpg", colorCE, "xboard");
                final XChessFrame frame11 = XChessFrame.frame;
                XChessFrame.bUndoLast.setEnabled(false);
                final XChessFrame frame12 = XChessFrame.frame;
                XChessFrame.mUndoLast.setEnabled(false);
                break;
            }
            case "GNU Chess": {
                setIconAndTipCE("ce/logo/gnu-chess_100x50.png", colorCE, "xboard");
                break;
            }
            case "Jchess": {
                setIconAndTipCE("ce/logo/jchess_100x50.gif", colorCE, "xboard");
                final XChessFrame frame13 = XChessFrame.frame;
                XChessFrame.bUndoLast.setEnabled(false);
                final XChessFrame frame14 = XChessFrame.frame;
                XChessFrame.mUndoLast.setEnabled(false);
                break;
            }
            case "Javalin": {
                setIconAndTipCE("ce/logo/javalin-native_100x50.png", colorCE, "xboard");
                final XChessFrame frame15 = XChessFrame.frame;
                XChessFrame.bUndoLast.setEnabled(false);
                final XChessFrame frame16 = XChessFrame.frame;
                XChessFrame.mUndoLast.setEnabled(false);
                break;
            }
            case "jChecs": {
                setIconAndTipCE("ce/logo/jchecs-native_100x50.png", colorCE, "xboard");
                XChessFrame.aktion.gojChecsSelectCE();
                break;
            }
            case "Kasparov": {
                setIconAndTipCE("ce/logo/kasparov-chess_100x50.png", colorCE, "uci");
                break;
            }
            case "KennyClassIQ": {
                setIconAndTipCE("ce/logo/KennyClassIQ_100x50.png", colorCE, "xboard");
                break;
            }
            case "KingsOut": {
                setIconAndTipCE("ce/logo/kingsout_100x50_2.gif", colorCE, "xboard");
                break;
            }
            case "Koedem": {
                setIconAndTipCE("ce/logo/koedem_100x50.png", colorCE, "uci");
                break;
            }
            case "Krudo": {
                setIconAndTipCE("ce/logo/krudo.png", colorCE, "uci");
                break;
            }
            case "Magnum": {
                setIconAndTipCE("ce/logo/magnum_100x50.gif", colorCE, "uci");
                break;
            }
            case "Mediocre": {
                setIconAndTipCE("ce/logo/mediocre_100x50.gif", colorCE, "uci");
                break;
            }
            case "OliThink": {
                setIconAndTipCE("ce/logo/olithink-java_100x50.gif", colorCE, "xboard");
                break;
            }
            case "Presbyter": {
                setIconAndTipCE("ce/logo/presbyter_100x50.png", colorCE, "uci");
                break;
            }
            case "Phoenix": {
                setIconAndTipCE("ce/logo/phoenix-well_100x27.png", colorCE, "uci");
                break;
            }
            case "Pulse": {
                setIconAndTipCE("ce/logo/pulse_100x50.png", colorCE, "uci");
                break;
            }
            case "Rival": {
                setIconAndTipCE("ce/logo/RivalUCI_100x50.gif", colorCE, "uci");
                break;
            }
            case "Rumney": {
                setIconAndTipCE("ce/logo/rumney_100x29.png", colorCE, "uci");
                break;
            }
            case "Talvmenni": {
                setIconAndTipCE("ce/logo/talvmenni_100x50.gif", colorCE, "xboard");
                final XChessFrame frame17 = XChessFrame.frame;
                XChessFrame.bUndoLast.setEnabled(false);
                final XChessFrame frame18 = XChessFrame.frame;
                XChessFrame.mUndoLast.setEnabled(false);
                break;
            }
            case "Tiffanys": {
                setIconAndTipCE("ce/logo/Tiffanys 2.png", colorCE, "xboard");
                final XChessFrame frame19 = XChessFrame.frame;
                XChessFrame.bUndoLast.setEnabled(false);
                final XChessFrame frame20 = XChessFrame.frame;
                XChessFrame.mUndoLast.setEnabled(false);
                break;
            }
            case "Tri-OS": {
                setIconAndTipCE("ce/logo/tri-os_100x50.png", colorCE, "xboard");
                break;
            }
            case "Unidexter": {
                setIconAndTipCE("ce/logo/unidexter_100x50.png", colorCE, "uci");
                break;
            }
            case "Ziggy": {
                setIconAndTipCE("ce/logo/ziggy_100x50.gif", colorCE, "uci");
                break;
            }
        }
    }
    
    public static void CopyToClipBoard(final String cps) {
        final ClipboardTextTransfer textTransfer = new ClipboardTextTransfer();
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
    
    public static void setIconAndTipCE(final String pathce, final String colorCEn, final String tipCE) {
        final XChessFrame frame = XChessFrame.frame;
        XChessFrame.bUndoLast.setEnabled(true);
        final XChessFrame frame2 = XChessFrame.frame;
        XChessFrame.mUndoLast.setEnabled(true);
        if (colorCEn.equals("white")) {
            Actions.whitePlayerTip = tipCE;
            final XChessFrame frame3 = XChessFrame.frame;
            XChessFrame.chessClock.label[2].setIcon(new ImageIcon(pathce));
        }
        else {
            Actions.blackPlayerTip = tipCE;
            final XChessFrame frame4 = XChessFrame.frame;
            XChessFrame.chessClock.label[3].setIcon(new ImageIcon(pathce));
        }
    }
    
    public void killEngineProc(final String color) {
        switch (color) {
            case "white": {
                final XChessFrame frame = XChessFrame.frame;
                final EngineAgent whiteEngineAgent = XChessFrame.whiteEngineAgent;
                if (EngineAgent.engineIOwhite != null) {
                    final XChessFrame frame2 = XChessFrame.frame;
                    final EngineAgent whiteEngineAgent2 = XChessFrame.whiteEngineAgent;
                    EngineAgent.engineIOwhite.writeLine("quit");
                    final XChessFrame frame3 = XChessFrame.frame;
                    final EngineAgent whiteEngineAgent3 = XChessFrame.whiteEngineAgent;
                    EngineAgent.engineIOwhite.destroy();
                    break;
                }
                break;
            }
            case "black": {
                final XChessFrame frame4 = XChessFrame.frame;
                final EngineAgent blackEngineAgent = XChessFrame.blackEngineAgent;
                if (EngineAgent.engineIOblack != null) {
                    final XChessFrame frame5 = XChessFrame.frame;
                    final EngineAgent blackEngineAgent2 = XChessFrame.blackEngineAgent;
                    EngineAgent.engineIOblack.writeLine("quit");
                    final XChessFrame frame6 = XChessFrame.frame;
                    final EngineAgent blackEngineAgent3 = XChessFrame.blackEngineAgent;
                    EngineAgent.engineIOblack.destroy();
                    break;
                }
                break;
            }
        }
    }
    
    public void killAllEngines() {
        XChessFrame.aktion.killEngineProc("white");
        XChessFrame.aktion.killEngineProc("black");
        XChessFrame.outputArea.setText("Please, select chess engine and press New Game !");
        XChessFrame.chessClock.stop();
        XChessFrame.chessClock.setTime(Actions.Time * 60000);
        XChessFrame.boardUI.setBoard(Utility.INITIAL_BOARD);
        XChessFrame.moveListUI.clear();
        XChessFrame.frame.loadBoardTheme();
        this.restColorWhite_restColorBlack();
    }
    
    public void sendEngineCmd(final String color) {
        switch (color) {
            case "white": {
                final XChessFrame frame = XChessFrame.frame;
                final EngineAgent whiteEngineAgent = XChessFrame.whiteEngineAgent;
                if (EngineAgent.engineIOwhite != null) {
                    String buf = "";
                    buf = (String)JOptionPane.showInputDialog(XChessFrame.frame, " Please, enter Command for send to White Engine ( be careful ! ): ", "Command for send to White Engine", 2, new ImageIcon(this.getClass().getResource("/SJCE/img/knight-white-32.png")), null, null);
                    if (buf != null && buf.length() > 0) {
                        final XChessFrame frame2 = XChessFrame.frame;
                        final EngineAgent whiteEngineAgent2 = XChessFrame.whiteEngineAgent;
                        EngineAgent.engineIOwhite.writeLine(buf);
                        XChessFrame.outputArea.append("<write to White Engine>: " + buf + "\n");
                        if (buf.equals("quit")) {
                            XChessFrame.aktion.killEngineProc("white");
                        }
                    }
                    break;
                }
                JOptionPane.showMessageDialog(XChessFrame.frame, "White Engine process not running !");
                break;
            }
            case "black": {
                final XChessFrame frame3 = XChessFrame.frame;
                final EngineAgent blackEngineAgent = XChessFrame.blackEngineAgent;
                if (EngineAgent.engineIOblack != null) {
                    String buf = "";
                    buf = (String)JOptionPane.showInputDialog(XChessFrame.frame, " Please, enter Command for send to Black Engine ( be careful ! ): ", "Command for send to Black Engine", 2, new ImageIcon(this.getClass().getResource("/SJCE/img/knight-black-32.png")), null, null);
                    if (buf != null && buf.length() > 0) {
                        final XChessFrame frame4 = XChessFrame.frame;
                        final EngineAgent blackEngineAgent2 = XChessFrame.blackEngineAgent;
                        EngineAgent.engineIOblack.writeLine(buf);
                        XChessFrame.outputArea.append("<write to Black Engine>: " + buf + "\n");
                        if (buf.equals("quit")) {
                            XChessFrame.aktion.killEngineProc("black");
                        }
                    }
                    break;
                }
                JOptionPane.showMessageDialog(XChessFrame.frame, "Black Engine process not running !");
                break;
            }
        }
    }
    
    public static void sendEngineCmd(final String color, final String buf) {
        switch (color) {
            case "white": {
                final XChessFrame frame = XChessFrame.frame;
                final EngineAgent whiteEngineAgent = XChessFrame.whiteEngineAgent;
                if (EngineAgent.engineIOwhite != null && buf != null && buf.length() > 0) {
                    final XChessFrame frame2 = XChessFrame.frame;
                    final EngineAgent whiteEngineAgent2 = XChessFrame.whiteEngineAgent;
                    EngineAgent.engineIOwhite.writeLine(buf);
                    XChessFrame.outputArea.append("<write to White Engine>: " + buf + "\n");
                    break;
                }
                break;
            }
            case "black": {
                final XChessFrame frame3 = XChessFrame.frame;
                final EngineAgent blackEngineAgent = XChessFrame.blackEngineAgent;
                if (EngineAgent.engineIOblack != null && buf != null && buf.length() > 0) {
                    final XChessFrame frame4 = XChessFrame.frame;
                    final EngineAgent blackEngineAgent2 = XChessFrame.blackEngineAgent;
                    EngineAgent.engineIOblack.writeLine(buf);
                    XChessFrame.outputArea.append("<write to Black Engine>: " + buf + "\n");
                    break;
                }
                break;
            }
        }
    }
    
    public void moveColorWhite_restColorBlack() {
        final XChessFrame frame = XChessFrame.frame;
        XChessFrame.chessClock.label[0].setBackground(Actions.moveColor);
        final XChessFrame frame2 = XChessFrame.frame;
        XChessFrame.chessClock.label[2].setBackground(Actions.moveColor);
        final XChessFrame frame3 = XChessFrame.frame;
        XChessFrame.chessClock.label[1].setBackground(Actions.restColor);
        final XChessFrame frame4 = XChessFrame.frame;
        XChessFrame.chessClock.label[3].setBackground(Actions.restColor);
    }
    
    public void restColorWhite_moveColorBlack() {
        final XChessFrame frame = XChessFrame.frame;
        XChessFrame.chessClock.label[0].setBackground(Actions.restColor);
        final XChessFrame frame2 = XChessFrame.frame;
        XChessFrame.chessClock.label[2].setBackground(Actions.restColor);
        final XChessFrame frame3 = XChessFrame.frame;
        XChessFrame.chessClock.label[1].setBackground(Actions.moveColor);
        final XChessFrame frame4 = XChessFrame.frame;
        XChessFrame.chessClock.label[3].setBackground(Actions.moveColor);
    }
    
    public void restColorWhite_restColorBlack() {
        final XChessFrame frame = XChessFrame.frame;
        XChessFrame.chessClock.label[0].setBackground(Actions.restColor);
        final XChessFrame frame2 = XChessFrame.frame;
        XChessFrame.chessClock.label[2].setBackground(Actions.restColor);
        final XChessFrame frame3 = XChessFrame.frame;
        XChessFrame.chessClock.label[1].setBackground(Actions.restColor);
        final XChessFrame frame4 = XChessFrame.frame;
        XChessFrame.chessClock.label[3].setBackground(Actions.restColor);
    }
    
    public static void playWAV(final String filename) {
        File snd = null;
        snd = new File(filename);
        try {
            final Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(snd));
            clip.start();
        }
        catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }
    
    public static void undoLastMove(final Move move, final String color) {
        if (!Actions.gameTip.equals("EE")) {
            if (Actions.whitePlayerTip.equals("uci") || Actions.blackPlayerTip.equals("uci")) {
                final String[] bf = Actions.uciAllMovesString.trim().split("\\s+");
                String bufer = "";
                for (int i = 0; i < bf.length - 1; ++i) {
                    bufer = bufer + " " + bf[i];
                }
                Actions.uciAllMovesString = bufer;
            }
            int n = -1;
            switch (color.hashCode()) {
                case 113101865: {
                    if (color.equals("white")) {
                        n = 0;
                        break;
                    }
                    break;
                }
                case 93818879: {
                    if (color.equals("black")) {
                        n = 1;
                        break;
                    }
                    break;
                }
            }
            Label_1179: {
                switch (n) {
                    case 0: {
                        if (Actions.whitePlayerTip.equals("xboard")) {
                            final String whitePlayerCE = Actions.whitePlayerCE;
                            switch (whitePlayerCE) {
                                case "Alf": {
                                    sendEngineCmd("white", "undo");
                                    sendEngineCmd("white", "undo");
                                    break;
                                }
                                case "ArabianKnight": {
                                    sendEngineCmd("white", "undo");
                                    sendEngineCmd("white", "undo");
                                    break;
                                }
                                case "BremboCE": {
                                    sendEngineCmd("white", "remove");
                                    break;
                                }
                                case "FrankWalter": {
                                    sendEngineCmd("white", "undo");
                                    sendEngineCmd("white", "undo");
                                    break;
                                }
                                case "Frittle": {
                                    sendEngineCmd("white", "undo");
                                    sendEngineCmd("white", "undo");
                                    break;
                                }
                                case "GNU Chess": {
                                    sendEngineCmd("white", "undo");
                                    sendEngineCmd("white", "undo");
                                    break;
                                }
                                case "KingsOut": {
                                    sendEngineCmd("white", "remove");
                                    break;
                                }
                                case "OliThink": {
                                    sendEngineCmd("white", "remove");
                                    break;
                                }
                                case "Tri-OS": {
                                    sendEngineCmd("white", "undo");
                                    break;
                                }
                                case "jChecs": {
                                    sendEngineCmd("white", "undo");
                                    sendEngineCmd("white", "undo");
                                    break;
                                }
                            }
                            break;
                        }
                        break;
                    }
                    case 1: {
                        if (!Actions.blackPlayerTip.equals("xboard")) {
                            break;
                        }
                        final String blackPlayerCE = Actions.blackPlayerCE;
                        switch (blackPlayerCE) {
                            case "Alf": {
                                sendEngineCmd("black", "undo");
                                sendEngineCmd("black", "undo");
                                break Label_1179;
                            }
                            case "ArabianKnight": {
                                sendEngineCmd("black", "undo");
                                sendEngineCmd("black", "undo");
                                break Label_1179;
                            }
                            case "BremboCE": {
                                sendEngineCmd("black", "remove");
                                break Label_1179;
                            }
                            case "FrankWalter": {
                                sendEngineCmd("black", "undo");
                                sendEngineCmd("black", "undo");
                                break Label_1179;
                            }
                            case "Frittle": {
                                sendEngineCmd("black", "undo");
                                sendEngineCmd("black", "undo");
                                break Label_1179;
                            }
                            case "GNU Chess": {
                                sendEngineCmd("black", "undo");
                                sendEngineCmd("black", "undo");
                                break Label_1179;
                            }
                            case "KingsOut": {
                                sendEngineCmd("black", "remove");
                                break Label_1179;
                            }
                            case "OliThink": {
                                sendEngineCmd("black", "remove");
                                break Label_1179;
                            }
                            case "Tri-OS": {
                                sendEngineCmd("black", "undo");
                                break Label_1179;
                            }
                            case "jChecs": {
                                sendEngineCmd("black", "undo");
                                sendEngineCmd("black", "undo");
                                break Label_1179;
                            }
                        }
                        break;
                    }
                }
            }
            final int source = move.getSource();
            final int destin = move.getDestination();
            move.undoMove(XChessFrame.boardUI.getBoard());
            XChessFrame.boardUI.update(source);
            XChessFrame.boardUI.update(destin);
            final BoardUI boardUI = XChessFrame.boardUI;
            final XChessFrame frame = XChessFrame.frame;
            boardUI.update(XChessFrame.SQUARES);
            MoveListUI.moveList.remove(MoveListUI.moveList.lastIndexOf(move));
            MoveListUI.listModel.removeElementAt(MoveListUI.listModel.size() - 1);
        }
    }
    
    public static void bmUndoLastMoveEventHandler() {
        if (Actions.gameTip.equals("EE")) {
            JOptionPane.showMessageDialog(XChessFrame.frame, "Undo not support for engine tournament !");
            return;
        }
        if (Actions.whitePlayerCE.equals("Animats") || Actions.whitePlayerCE.equals("CaveChess") || Actions.whitePlayerCE.equals("CupCake") || Actions.whitePlayerCE.equals("ChessBotX") || Actions.whitePlayerCE.equals("DeepBrutePos") || Actions.whitePlayerCE.equals("Tiffanys") || Actions.whitePlayerCE.equals("Gladiator") || Actions.whitePlayerCE.equals("Talvmenni") || Actions.whitePlayerCE.equals("Jchess") || Actions.whitePlayerCE.equals("Javalin")) {
            JOptionPane.showMessageDialog(XChessFrame.frame, "This engine not support undo or remove comands !");
            return;
        }
        if (Actions.blackPlayerCE.equals("Animats") || Actions.blackPlayerCE.equals("CaveChess") || Actions.blackPlayerCE.equals("CupCake") || Actions.blackPlayerCE.equals("ChessBotX") || Actions.blackPlayerCE.equals("DeepBrutePos") || Actions.blackPlayerCE.equals("Tiffanys") || Actions.blackPlayerCE.equals("Gladiator") || Actions.blackPlayerCE.equals("Talvmenni") || Actions.blackPlayerCE.equals("Jchess") || Actions.blackPlayerCE.equals("Javalin")) {
            JOptionPane.showMessageDialog(XChessFrame.frame, "This engine not support undo or remove comands  !");
            return;
        }
        final Actions aktion = XChessFrame.aktion;
        if (Actions.whiteLastMove != null) {
            final Actions aktion2 = XChessFrame.aktion;
            if (Actions.blackLastMove != null) {
                if (Actions.gameTip.equals("EH")) {
                    final Actions aktion3 = XChessFrame.aktion;
                    final Actions aktion4 = XChessFrame.aktion;
                    undoLastMove(Actions.whiteLastMove, "white");
                    final Actions aktion5 = XChessFrame.aktion;
                    final Actions aktion6 = XChessFrame.aktion;
                    undoLastMove(Actions.blackLastMove, "black");
                }
                if (Actions.gameTip.equals("HE")) {
                    final Actions aktion7 = XChessFrame.aktion;
                    final Actions aktion8 = XChessFrame.aktion;
                    undoLastMove(Actions.blackLastMove, "black");
                    final Actions aktion9 = XChessFrame.aktion;
                    final Actions aktion10 = XChessFrame.aktion;
                    undoLastMove(Actions.whiteLastMove, "white");
                }
                if (Actions.gameTip.equals("HH")) {
                    final Actions aktion11 = XChessFrame.aktion;
                    final Actions aktion12 = XChessFrame.aktion;
                    undoLastMove(Actions.whiteLastMove, "white");
                    final Actions aktion13 = XChessFrame.aktion;
                    final Actions aktion14 = XChessFrame.aktion;
                    undoLastMove(Actions.blackLastMove, "black");
                }
                final Actions aktion15 = XChessFrame.aktion;
                Actions.whiteLastMove = null;
                final Actions aktion16 = XChessFrame.aktion;
                Actions.blackLastMove = null;
                return;
            }
        }
        JOptionPane.showMessageDialog(XChessFrame.frame, "Please, at first make a move and then you can make undo !");
    }
    
    public static void logShow() {
        XChessFrame.logFrame = new LogShow();
        final LogShow logFrame = XChessFrame.logFrame;
        final JTextArea gameLogTextArea = LogShow.gameLogTextArea;
        final XChessFrame frame = XChessFrame.frame;
        gameLogTextArea.setText(XChessFrame.outputArea.getText());
        XChessFrame.logFrame.setVisible(true);
    }
    
    public static void clipboardShow() {
        final TextTransfer textTransfer = new TextTransfer();
        final String buf = textTransfer.getClipboardContents();
        final ShowClipBoard sc = new ShowClipBoard(XChessFrame.logFrame, true);
        ShowClipBoard.ClipboardText.setText(buf);
        sc.setVisible(true);
    }
    
    public void gojChecsSelectCE() {
        final String changeLook = (String)JOptionPane.showInputDialog(XChessFrame.frame, "Choose jChecs engine algorithm Here:", "Select jChecs engine algorithm", 3, new ImageIcon(this.getClass().getResource("/SJCE/img/jchecs-native_100x50.png")), Actions.jchecsCeSelect.toArray(), null);
        if (changeLook != null) {
            for (int a = 0; a < Actions.jchecsCeSelect.size(); ++a) {
                if (changeLook.equals(Actions.jchecsCeSelect.get(a))) {
                    Actions.jchecsEngineTip = Actions.jchecsCeSelect.get(a);
                    break;
                }
            }
        }
    }
    
    static {
        Actions.Mode = "hard";
        Actions.currentLAF = "org.pushingpixels.substance.api.skin.SubstanceSaharaLookAndFeel";
        Actions.UseClock = "true";
        Actions.BoardThemeFig = "cyan-red";
        Actions.BoardThemeFon = "yellow-green";
        Actions.Prohod_White_Event = 0;
        Actions.Prohod_White_Destination = -1;
        Actions.Prohod_Black_Event = 0;
        Actions.Prohod_Black_Destination = -1;
        Actions.uciAllMovesString = "";
        Actions.enginePromotionType = "";
        Actions.enginePromotionFig = "";
        moveColor = Color.GREEN;
        restColor = Color.WHITE;
        Actions.whitePlayerCE = "";
        Actions.whitePlayerTip = "";
        Actions.blackPlayerCE = "";
        Actions.blackPlayerTip = "";
        Actions.gameTip = "";
        Actions.enemyTip = "";
        Actions.currentMute = "false";
        Actions.currentMixer = 100;
        Actions.useSound = "true";
        Actions.promotionCount = 0;
        Actions.whiteLastMove = null;
        Actions.blackLastMove = null;
        Actions.blackRivalMovesString = "";
        Actions.whiteRivalMovesString = "";
        Actions.jchecsEngineTip = "jChecs.NegaScout";
        Actions.jchecsCeSelect = new ArrayList<String>();
        Actions.pref = Preferences.userRoot().node("sjce/cfg");
    }
}
