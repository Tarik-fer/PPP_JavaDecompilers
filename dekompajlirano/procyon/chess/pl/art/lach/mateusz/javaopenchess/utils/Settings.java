// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.utils;

import java.util.MissingResourceException;
import java.util.Locale;
import pl.art.lach.mateusz.javaopenchess.JChessApp;
import pl.art.lach.mateusz.javaopenchess.core.players.PlayerType;
import pl.art.lach.mateusz.javaopenchess.core.players.implementation.HumanPlayer;
import pl.art.lach.mateusz.javaopenchess.core.Colors;
import pl.art.lach.mateusz.javaopenchess.core.players.Player;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;
import java.io.Serializable;

public class Settings implements Serializable
{
    private static final Logger LOG;
    private static final String RESOURCES_I18N_MAIN = ".resources.i18n.main";
    private static ResourceBundle loc;
    protected int timeForGame;
    protected boolean runningChat;
    protected boolean runningGameClock;
    protected boolean timeLimitSet;
    protected boolean upsideDown;
    protected boolean displayLegalMovesEnabled;
    protected GameModes gameMode;
    protected Player playerWhite;
    protected Player playerBlack;
    protected GameTypes gameType;
    protected boolean renderLabels;
    
    public Settings() {
        this(new HumanPlayer("", Colors.WHITE.getColorName()), new HumanPlayer("", Colors.BLACK.getColorName()));
    }
    
    public Settings(final Player playerWhite, final Player playerBlack) {
        this.timeLimitSet = false;
        this.displayLegalMovesEnabled = true;
        this.renderLabels = true;
        this.playerWhite = playerWhite;
        this.playerBlack = playerBlack;
        this.timeLimitSet = false;
        this.gameMode = GameModes.NEW_GAME;
    }
    
    public boolean isRunningChat() {
        return this.runningChat;
    }
    
    public boolean isRunningGameClock() {
        return this.runningGameClock;
    }
    
    public boolean isTimeLimitSet() {
        return this.timeLimitSet;
    }
    
    public boolean isUpsideDown() {
        return this.upsideDown;
    }
    
    public GameModes getGameMode() {
        return this.gameMode;
    }
    
    public Player getPlayerWhite() {
        return this.playerWhite;
    }
    
    public Player getPlayerBlack() {
        return this.playerBlack;
    }
    
    public void setPlayerWhite(final Player player) {
        this.playerWhite = player;
    }
    
    public void setPlayerBlack(final Player player) {
        this.playerBlack = player;
    }
    
    public GameTypes getGameType() {
        return this.gameType;
    }
    
    public boolean isRenderLabels() {
        return this.renderLabels;
    }
    
    public void setRenderLabels(final boolean renderLabels) {
        this.renderLabels = renderLabels;
    }
    
    public void setUpsideDown(final boolean upsideDown) {
        this.upsideDown = upsideDown;
    }
    
    public void setGameMode(final GameModes gameMode) {
        this.gameMode = gameMode;
    }
    
    public void setGameType(final GameTypes gameType) {
        this.gameType = gameType;
    }
    
    public void setTimeForGame(final int timeForGame) {
        this.timeLimitSet = true;
        this.timeForGame = timeForGame;
    }
    
    public boolean isDisplayLegalMovesEnabled() {
        return this.displayLegalMovesEnabled;
    }
    
    public void setDisplayLegalMovesEnabled(final boolean displayLegalMovesEnabled) {
        this.displayLegalMovesEnabled = displayLegalMovesEnabled;
    }
    
    public int getTimeForGame() {
        return this.timeForGame;
    }
    
    public boolean isGameAgainstComputer() {
        return this.playerBlack.getPlayerType() == PlayerType.COMPUTER || this.playerWhite.getPlayerType() == PlayerType.COMPUTER;
    }
    
    public static String lang(final String key) {
        if (Settings.loc == null) {
            Settings.loc = ResourceBundle.getBundle(JChessApp.MAIN_PACKAGE_NAME + ".resources.i18n.main");
            Locale.setDefault(Locale.ENGLISH);
        }
        String result = "";
        try {
            result = Settings.loc.getString(key);
        }
        catch (MissingResourceException exc) {
            result = key;
        }
        Settings.LOG.debug("Locale: " + Settings.loc.getLocale().toString());
        return result;
    }
    
    static {
        LOG = Logger.getLogger(Settings.class);
        Settings.loc = null;
    }
}
