// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.core.data_transfer.implementations;

import java.io.IOException;
import pl.art.lach.mateusz.javaopenchess.utils.Settings;
import java.util.Calendar;
import pl.art.lach.mateusz.javaopenchess.core.GameFactory;
import pl.art.lach.mateusz.javaopenchess.core.players.PlayerType;
import pl.art.lach.mateusz.javaopenchess.utils.GameTypes;
import pl.art.lach.mateusz.javaopenchess.utils.GameModes;
import pl.art.lach.mateusz.javaopenchess.core.exceptions.ReadGameError;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.StringReader;
import pl.art.lach.mateusz.javaopenchess.core.Game;
import org.apache.log4j.Logger;
import pl.art.lach.mateusz.javaopenchess.core.data_transfer.DataExporter;
import pl.art.lach.mateusz.javaopenchess.core.data_transfer.DataImporter;

public class PGNNotation implements DataImporter, DataExporter
{
    private static final Logger LOG;
    private static final String BLACK_COLOR_INTRO = "[Black";
    private static final String WHITE_COLOR_INTRO = "[White";
    private static final String START_MOVES_LINE_INTRO = "1.";
    
    @Override
    public Game importData(final String data) throws ReadGameError {
        final BufferedReader br = new BufferedReader(new StringReader(data));
        String tempStr;
        String whiteName;
        String blackName;
        try {
            tempStr = getLineWithVar(br, "[White");
            whiteName = getValue(tempStr);
            tempStr = getLineWithVar(br, "[Black");
            blackName = getValue(tempStr);
            tempStr = getLineWithVar(br, "1.");
        }
        catch (ReadGameError err) {
            PGNNotation.LOG.error("Error reading file: " + err);
            return null;
        }
        final Game game = GameFactory.instance(GameModes.LOAD_GAME, GameTypes.LOCAL, whiteName, blackName, PlayerType.LOCAL_USER, PlayerType.LOCAL_USER, true, false);
        this.importData(tempStr, game);
        game.getChessboard().repaint();
        return game;
    }
    
    @Override
    public void importData(final String data, final Game game) throws ReadGameError {
        game.setBlockedChessboard(true);
        this.importData(new BufferedReader(new StringReader(data)), game);
        game.setBlockedChessboard(false);
    }
    
    private void importData(final BufferedReader br, final Game game) throws ReadGameError {
        game.getMoves().setMoves(getLineWithVar(br, "1."));
    }
    
    @Override
    public String exportData(final Game game) {
        final Calendar cal = Calendar.getInstance();
        final Settings sett = game.getSettings();
        final StringBuilder strBuilder = new StringBuilder();
        final String header = String.format("[Event \"Game\"]\n[Date \"%s.%s.%s\"]\n[White \"%s\"]\n[Black \"%s\"]\n\n", cal.get(1), cal.get(2) + 1, cal.get(5), sett.getPlayerWhite().getName(), sett.getPlayerBlack().getName());
        strBuilder.append(header);
        strBuilder.append(game.getMoves().getMovesInString());
        return strBuilder.toString();
    }
    
    private static String getLineWithVar(final BufferedReader br, final String srcStr) throws ReadGameError {
        String str = new String();
        while (true) {
            try {
                str = br.readLine();
            }
            catch (IOException exc) {
                PGNNotation.LOG.error("Something wrong reading file: ", exc);
                throw new ReadGameError("Something wrong reading file: " + exc);
            }
            if (str == null) {
                PGNNotation.LOG.error("Something wrong reading file, str == null.");
                throw new ReadGameError("Something wrong reading file, str == null.");
            }
            if (str.startsWith(srcStr)) {
                return str;
            }
        }
    }
    
    private static String getValue(final String line) throws ReadGameError {
        final int from = line.indexOf("\"");
        final int to = line.lastIndexOf("\"");
        final int size = line.length() - 1;
        String result = "";
        if (to < from || from > size || to > size || to < 0 || from < 0) {
            throw new ReadGameError("Error reading value from PGN header section.");
        }
        try {
            result = line.substring(from + 1, to);
        }
        catch (StringIndexOutOfBoundsException exc) {
            PGNNotation.LOG.error("error getting value: ", exc);
            return "none";
        }
        return result;
    }
    
    static {
        LOG = Logger.getLogger(Game.class);
    }
}
