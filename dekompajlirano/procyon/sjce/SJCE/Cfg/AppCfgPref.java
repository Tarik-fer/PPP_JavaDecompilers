// 
// Decompiled by Procyon v0.5.36
// 

package SJCE.Cfg;

import java.util.prefs.Preferences;
import java.util.prefs.BackingStoreException;
import java.util.logging.Level;
import java.util.logging.Logger;
import SJCE.more.Actions;
import SJCE.XChessFrame;

public class AppCfgPref
{
    public static void Save() {
        final Actions aktion = XChessFrame.aktion;
        final Preferences pref = Actions.pref;
        final String s = "skin";
        final Actions aktion2 = XChessFrame.aktion;
        pref.put(s, Actions.currentLAF);
        final Actions aktion3 = XChessFrame.aktion;
        final Preferences pref2 = Actions.pref;
        final String s2 = "use-clock";
        final Actions aktion4 = XChessFrame.aktion;
        pref2.put(s2, Actions.UseClock);
        final Actions aktion5 = XChessFrame.aktion;
        final Preferences pref3 = Actions.pref;
        final String s3 = "mode";
        final Actions aktion6 = XChessFrame.aktion;
        pref3.put(s3, Actions.Mode);
        final Actions aktion7 = XChessFrame.aktion;
        final Preferences pref4 = Actions.pref;
        final String s4 = "depth";
        final StringBuilder append = new StringBuilder().append("");
        final Actions aktion8 = XChessFrame.aktion;
        pref4.put(s4, append.append(Actions.Depth).toString());
        final Actions aktion9 = XChessFrame.aktion;
        final Preferences pref5 = Actions.pref;
        final String s5 = "time";
        final StringBuilder append2 = new StringBuilder().append("");
        final Actions aktion10 = XChessFrame.aktion;
        pref5.put(s5, append2.append(Actions.Time).toString());
        final Actions aktion11 = XChessFrame.aktion;
        final Preferences pref6 = Actions.pref;
        final String s6 = "bt-fig";
        final Actions aktion12 = XChessFrame.aktion;
        pref6.put(s6, Actions.BoardThemeFig);
        final Actions aktion13 = XChessFrame.aktion;
        final Preferences pref7 = Actions.pref;
        final String s7 = "bt-fon";
        final Actions aktion14 = XChessFrame.aktion;
        pref7.put(s7, Actions.BoardThemeFon);
        final Actions aktion15 = XChessFrame.aktion;
        final Preferences pref8 = Actions.pref;
        final String s8 = "wp-ce";
        final Actions aktion16 = XChessFrame.aktion;
        pref8.put(s8, Actions.whitePlayerCE);
        final Actions aktion17 = XChessFrame.aktion;
        final Preferences pref9 = Actions.pref;
        final String s9 = "wp-tip";
        final Actions aktion18 = XChessFrame.aktion;
        pref9.put(s9, Actions.whitePlayerTip);
        final Actions aktion19 = XChessFrame.aktion;
        final Preferences pref10 = Actions.pref;
        final String s10 = "bp-ce";
        final Actions aktion20 = XChessFrame.aktion;
        pref10.put(s10, Actions.blackPlayerCE);
        final Actions aktion21 = XChessFrame.aktion;
        final Preferences pref11 = Actions.pref;
        final String s11 = "bp-tip";
        final Actions aktion22 = XChessFrame.aktion;
        pref11.put(s11, Actions.blackPlayerTip);
        final Actions aktion23 = XChessFrame.aktion;
        final Preferences pref12 = Actions.pref;
        final String s12 = "mixer";
        final StringBuilder append3 = new StringBuilder().append("");
        final Actions aktion24 = XChessFrame.aktion;
        pref12.put(s12, append3.append(Actions.currentMixer).toString());
        final Actions aktion25 = XChessFrame.aktion;
        final Preferences pref13 = Actions.pref;
        final String s13 = "mute";
        final Actions aktion26 = XChessFrame.aktion;
        pref13.put(s13, Actions.currentMute);
        final Actions aktion27 = XChessFrame.aktion;
        final Preferences pref14 = Actions.pref;
        final String s14 = "use-sound";
        final Actions aktion28 = XChessFrame.aktion;
        pref14.put(s14, Actions.useSound);
        try {
            final Actions aktion29 = XChessFrame.aktion;
            Actions.pref.flush();
            final Actions aktion30 = XChessFrame.aktion;
            Actions.pref.sync();
        }
        catch (BackingStoreException ex) {
            Logger.getLogger(AppCfgPref.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void Load() {
        final Actions aktion = XChessFrame.aktion;
        final Actions aktion2 = XChessFrame.aktion;
        Actions.currentLAF = Actions.pref.get("skin", "org.pushingpixels.substance.api.skin.SubstanceSaharaLookAndFeel");
        final Actions aktion3 = XChessFrame.aktion;
        final Actions aktion4 = XChessFrame.aktion;
        Actions.UseClock = Actions.pref.get("use-clock", "true");
        final Actions aktion5 = XChessFrame.aktion;
        final Actions aktion6 = XChessFrame.aktion;
        Actions.Mode = Actions.pref.get("mode", "easy");
        final Actions aktion7 = XChessFrame.aktion;
        final Actions aktion8 = XChessFrame.aktion;
        Actions.BoardThemeFig = Actions.pref.get("bt-fig", "alpha");
        final Actions aktion9 = XChessFrame.aktion;
        final Actions aktion10 = XChessFrame.aktion;
        Actions.BoardThemeFon = Actions.pref.get("bt-fon", "sea-green");
        final Actions aktion11 = XChessFrame.aktion;
        final Actions aktion12 = XChessFrame.aktion;
        Actions.whitePlayerCE = Actions.pref.get("wp-ce", "Human");
        final Actions aktion13 = XChessFrame.aktion;
        final Actions aktion14 = XChessFrame.aktion;
        Actions.whitePlayerTip = Actions.pref.get("wp-tip", "human");
        final Actions aktion15 = XChessFrame.aktion;
        final Actions aktion16 = XChessFrame.aktion;
        Actions.blackPlayerCE = Actions.pref.get("bp-ce", "Koedem");
        final Actions aktion17 = XChessFrame.aktion;
        final Actions aktion18 = XChessFrame.aktion;
        Actions.blackPlayerTip = Actions.pref.get("bp-tip", "uci");
        final Actions aktion19 = XChessFrame.aktion;
        final Actions aktion20 = XChessFrame.aktion;
        Actions.Depth = Integer.parseInt(Actions.pref.get("depth", "2"));
        final Actions aktion21 = XChessFrame.aktion;
        final Actions aktion22 = XChessFrame.aktion;
        Actions.Time = Integer.parseInt(Actions.pref.get("time", "5"));
        final Actions aktion23 = XChessFrame.aktion;
        final Actions aktion24 = XChessFrame.aktion;
        Actions.currentMute = Actions.pref.get("mute", "false");
        final Actions aktion25 = XChessFrame.aktion;
        final Actions aktion26 = XChessFrame.aktion;
        Actions.useSound = Actions.pref.get("use-sound", "false");
        final Actions aktion27 = XChessFrame.aktion;
        final Actions aktion28 = XChessFrame.aktion;
        Actions.currentMixer = Integer.parseInt(Actions.pref.get("mixer", "100"));
    }
}
