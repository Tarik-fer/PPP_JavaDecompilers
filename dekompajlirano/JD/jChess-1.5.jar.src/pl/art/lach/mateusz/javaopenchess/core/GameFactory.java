/*    */ package pl.art.lach.mateusz.javaopenchess.core;
/*    */ 
/*    */ import pl.art.lach.mateusz.javaopenchess.core.players.Player;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.players.PlayerType;
/*    */ import pl.art.lach.mateusz.javaopenchess.utils.GameModes;
/*    */ import pl.art.lach.mateusz.javaopenchess.utils.GameTypes;
/*    */ import pl.art.lach.mateusz.javaopenchess.utils.Settings;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GameFactory
/*    */ {
/*    */   public static Game instance(GameModes gameMode, GameTypes gameType, String whiteName, String blackName, PlayerType whiteType, PlayerType blackType, boolean setPieces4newGame) {
/* 34 */     Game game = new Game();
/* 35 */     Settings sett = game.getSettings();
/* 36 */     Player whitePlayer = sett.getPlayerWhite();
/* 37 */     Player blackPlayer = sett.getPlayerBlack();
/* 38 */     sett.setGameMode(GameModes.NEW_GAME);
/* 39 */     blackPlayer.setName(whiteName);
/* 40 */     whitePlayer.setName(blackName);
/* 41 */     whitePlayer.setType(whiteType);
/* 42 */     blackPlayer.setType(blackType);
/* 43 */     sett.setGameType(GameTypes.LOCAL);
/* 44 */     if (setPieces4newGame)
/*    */     {
/* 46 */       game.getChessboard().setPieces4NewGame(whitePlayer, blackPlayer);
/*    */     }
/* 48 */     game.setActivePlayer(whitePlayer);
/* 49 */     return game;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 55 */   public static Game instance(GameModes gameMode, GameTypes gameType, String whiteName, String blackName, PlayerType whiteType, PlayerType blackType) { return instance(gameMode, gameType, whiteName, blackName, whiteType, blackType, true); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Game instance(GameModes gameMode, GameTypes gameType, String whiteName, String blackName, PlayerType whiteType, PlayerType blackType, boolean setPieces4newGame, boolean chatEnabled) {
/* 62 */     Game game = instance(gameMode, gameType, whiteName, blackName, whiteType, blackType);
/* 63 */     game.getChat().setEnabled(chatEnabled);
/* 64 */     return game;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static Game instance(GameModes gameMode, GameTypes gameType, Player whitePlayer, Player blackPlayer) {
/* 70 */     return instance(gameMode, gameType, whitePlayer
/*    */         
/* 72 */         .getName(), blackPlayer
/* 73 */         .getName(), whitePlayer
/* 74 */         .getPlayerType(), blackPlayer
/* 75 */         .getPlayerType());
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\core\GameFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */