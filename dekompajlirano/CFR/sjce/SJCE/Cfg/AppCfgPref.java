/*
 * Decompiled with CFR 0.148.
 */
package SJCE.Cfg;

import SJCE.more.Actions;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class AppCfgPref {
    public static void Save() {
        Actions.pref.put("skin", Actions.currentLAF);
        Actions.pref.put("use-clock", Actions.UseClock);
        Actions.pref.put("mode", Actions.Mode);
        Actions.pref.put("depth", "" + Actions.Depth);
        Actions.pref.put("time", "" + Actions.Time);
        Actions.pref.put("bt-fig", Actions.BoardThemeFig);
        Actions.pref.put("bt-fon", Actions.BoardThemeFon);
        Actions.pref.put("wp-ce", Actions.whitePlayerCE);
        Actions.pref.put("wp-tip", Actions.whitePlayerTip);
        Actions.pref.put("bp-ce", Actions.blackPlayerCE);
        Actions.pref.put("bp-tip", Actions.blackPlayerTip);
        Actions.pref.put("mixer", "" + Actions.currentMixer);
        Actions.pref.put("mute", Actions.currentMute);
        Actions.pref.put("use-sound", Actions.useSound);
        try {
            Actions.pref.flush();
            Actions.pref.sync();
        }
        catch (BackingStoreException ex) {
            Logger.getLogger(AppCfgPref.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void Load() {
        Actions.currentLAF = Actions.pref.get("skin", "org.pushingpixels.substance.api.skin.SubstanceSaharaLookAndFeel");
        Actions.UseClock = Actions.pref.get("use-clock", "true");
        Actions.Mode = Actions.pref.get("mode", "easy");
        Actions.BoardThemeFig = Actions.pref.get("bt-fig", "alpha");
        Actions.BoardThemeFon = Actions.pref.get("bt-fon", "sea-green");
        Actions.whitePlayerCE = Actions.pref.get("wp-ce", "Human");
        Actions.whitePlayerTip = Actions.pref.get("wp-tip", "human");
        Actions.blackPlayerCE = Actions.pref.get("bp-ce", "Koedem");
        Actions.blackPlayerTip = Actions.pref.get("bp-tip", "uci");
        Actions.Depth = Integer.parseInt(Actions.pref.get("depth", "2"));
        Actions.Time = Integer.parseInt(Actions.pref.get("time", "5"));
        Actions.currentMute = Actions.pref.get("mute", "false");
        Actions.useSound = Actions.pref.get("use-sound", "false");
        Actions.currentMixer = Integer.parseInt(Actions.pref.get("mixer", "100"));
    }
}

