// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.core;

import pl.art.lach.mateusz.javaopenchess.core.players.Player;
import pl.art.lach.mateusz.javaopenchess.utils.Settings;
import pl.art.lach.mateusz.javaopenchess.core.players.PlayerType;
import pl.art.lach.mateusz.javaopenchess.utils.GameTypes;
import pl.art.lach.mateusz.javaopenchess.utils.GameModes;

public class GameFactory
{
    public static Game instance(final GameModes gameMode, final GameTypes gameType, final String whiteName, final String blackName, final PlayerType whiteType, final PlayerType blackType, final boolean setPieces4newGame) {
        final Game game = new Game();
        final Settings sett = game.getSettings();
        final Player whitePlayer = sett.getPlayerWhite();
        final Player blackPlayer = sett.getPlayerBlack();
        sett.setGameMode(GameModes.NEW_GAME);
        blackPlayer.setName(whiteName);
        whitePlayer.setName(blackName);
        whitePlayer.setType(whiteType);
        blackPlayer.setType(blackType);
        sett.setGameType(GameTypes.LOCAL);
        if (setPieces4newGame) {
            game.getChessboard().setPieces4NewGame(whitePlayer, blackPlayer);
        }
        game.setActivePlayer(whitePlayer);
        return game;
    }
    
    public static Game instance(final GameModes gameMode, final GameTypes gameType, final String whiteName, final String blackName, final PlayerType whiteType, final PlayerType blackType) {
        return instance(gameMode, gameType, whiteName, blackName, whiteType, blackType, true);
    }
    
    public static Game instance(final GameModes gameMode, final GameTypes gameType, final String whiteName, final String blackName, final PlayerType whiteType, final PlayerType blackType, final boolean setPieces4newGame, final boolean chatEnabled) {
        final Game game = instance(gameMode, gameType, whiteName, blackName, whiteType, blackType);
        game.getChat().setEnabled(chatEnabled);
        return game;
    }
    
    public static Game instance(final GameModes gameMode, final GameTypes gameType, final Player whitePlayer, final Player blackPlayer) {
        return instance(gameMode, gameType, whitePlayer.getName(), blackPlayer.getName(), whitePlayer.getPlayerType(), blackPlayer.getPlayerType());
    }
}
