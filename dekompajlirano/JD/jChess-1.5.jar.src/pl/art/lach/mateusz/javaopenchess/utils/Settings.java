/*     */ package pl.art.lach.mateusz.javaopenchess.utils;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Locale;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.PropertyResourceBundle;
/*     */ import java.util.ResourceBundle;
/*     */ import org.apache.log4j.Logger;
/*     */ import pl.art.lach.mateusz.javaopenchess.JChessApp;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Colors;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.players.Player;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.players.PlayerType;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.players.implementation.HumanPlayer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Settings
/*     */   implements Serializable
/*     */ {
/*  36 */   private static final Logger LOG = Logger.getLogger(Settings.class);
/*     */   
/*     */   private static final String RESOURCES_I18N_MAIN = ".resources.i18n.main";
/*     */   
/*  40 */   private static ResourceBundle loc = null;
/*     */ 
/*     */   
/*     */   protected int timeForGame;
/*     */ 
/*     */   
/*     */   protected boolean runningChat;
/*     */ 
/*     */   
/*     */   protected boolean runningGameClock;
/*     */   
/*     */   protected boolean timeLimitSet = false;
/*     */   
/*     */   protected boolean upsideDown;
/*     */   
/*     */   protected boolean displayLegalMovesEnabled = true;
/*     */   
/*     */   protected GameModes gameMode;
/*     */   
/*     */   protected Player playerWhite;
/*     */   
/*     */   protected Player playerBlack;
/*     */   
/*     */   protected GameTypes gameType;
/*     */   
/*     */   protected boolean renderLabels = true;
/*     */ 
/*     */   
/*     */   public Settings() {
/*  69 */     this((Player)new HumanPlayer("", Colors.WHITE
/*  70 */           .getColorName()), (Player)new HumanPlayer("", Colors.BLACK
/*  71 */           .getColorName()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Settings(Player playerWhite, Player playerBlack) {
/*  77 */     this.playerWhite = playerWhite;
/*  78 */     this.playerBlack = playerBlack;
/*  79 */     this.timeLimitSet = false;
/*     */     
/*  81 */     this.gameMode = GameModes.NEW_GAME;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  89 */   public boolean isRunningChat() { return this.runningChat; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   public boolean isRunningGameClock() { return this.runningGameClock; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 105 */   public boolean isTimeLimitSet() { return this.timeLimitSet; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 110 */   public boolean isUpsideDown() { return this.upsideDown; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 118 */   public GameModes getGameMode() { return this.gameMode; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 126 */   public Player getPlayerWhite() { return this.playerWhite; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 134 */   public Player getPlayerBlack() { return this.playerBlack; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 139 */   public void setPlayerWhite(Player player) { this.playerWhite = player; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 144 */   public void setPlayerBlack(Player player) { this.playerBlack = player; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 152 */   public GameTypes getGameType() { return this.gameType; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 160 */   public boolean isRenderLabels() { return this.renderLabels; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 165 */   public void setRenderLabels(boolean renderLabels) { this.renderLabels = renderLabels; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 173 */   public void setUpsideDown(boolean upsideDown) { this.upsideDown = upsideDown; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 181 */   public void setGameMode(GameModes gameMode) { this.gameMode = gameMode; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 189 */   public void setGameType(GameTypes gameType) { this.gameType = gameType; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTimeForGame(int timeForGame) {
/* 197 */     this.timeLimitSet = true;
/* 198 */     this.timeForGame = timeForGame;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 206 */   public boolean isDisplayLegalMovesEnabled() { return this.displayLegalMovesEnabled; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 214 */   public void setDisplayLegalMovesEnabled(boolean displayLegalMovesEnabled) { this.displayLegalMovesEnabled = displayLegalMovesEnabled; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 223 */   public int getTimeForGame() { return this.timeForGame; }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isGameAgainstComputer() {
/* 228 */     return (this.playerBlack.getPlayerType() == PlayerType.COMPUTER || this.playerWhite
/* 229 */       .getPlayerType() == PlayerType.COMPUTER);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String lang(String key) {
/* 234 */     if (loc == null) {
/*     */       
/* 236 */       loc = PropertyResourceBundle.getBundle(JChessApp.MAIN_PACKAGE_NAME + ".resources.i18n.main");
/* 237 */       Locale.setDefault(Locale.ENGLISH);
/*     */     } 
/* 239 */     String result = "";
/*     */     
/*     */     try {
/* 242 */       result = loc.getString(key);
/*     */     }
/* 244 */     catch (MissingResourceException exc) {
/*     */       
/* 246 */       result = key;
/*     */     } 
/* 248 */     LOG.debug("Locale: " + loc.getLocale().toString());
/* 249 */     return result;
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenches\\utils\Settings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */