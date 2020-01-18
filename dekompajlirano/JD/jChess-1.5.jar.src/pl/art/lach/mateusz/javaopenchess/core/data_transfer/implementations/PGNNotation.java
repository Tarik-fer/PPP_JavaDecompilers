/*     */ package pl.art.lach.mateusz.javaopenchess.core.data_transfer.implementations;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.StringReader;
/*     */ import java.util.Calendar;
/*     */ import org.apache.log4j.Logger;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Game;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.GameFactory;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.data_transfer.DataExporter;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.data_transfer.DataImporter;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.exceptions.ReadGameError;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.players.PlayerType;
/*     */ import pl.art.lach.mateusz.javaopenchess.utils.GameModes;
/*     */ import pl.art.lach.mateusz.javaopenchess.utils.GameTypes;
/*     */ import pl.art.lach.mateusz.javaopenchess.utils.Settings;
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
/*     */ 
/*     */ public class PGNNotation
/*     */   implements DataImporter, DataExporter
/*     */ {
/*  40 */   private static final Logger LOG = Logger.getLogger(Game.class);
/*     */ 
/*     */   
/*     */   private static final String BLACK_COLOR_INTRO = "[Black";
/*     */ 
/*     */   
/*     */   private static final String WHITE_COLOR_INTRO = "[White";
/*     */ 
/*     */   
/*     */   private static final String START_MOVES_LINE_INTRO = "1.";
/*     */ 
/*     */   
/*     */   public Game importData(String data) throws ReadGameError {
/*     */     String whiteName, blackName, tempStr;
/*  54 */     BufferedReader br = new BufferedReader(new StringReader(data));
/*     */ 
/*     */     
/*     */     try {
/*  58 */       tempStr = getLineWithVar(br, "[White");
/*  59 */       whiteName = getValue(tempStr);
/*  60 */       tempStr = getLineWithVar(br, "[Black");
/*  61 */       blackName = getValue(tempStr);
/*  62 */       tempStr = getLineWithVar(br, "1.");
/*     */     }
/*  64 */     catch (ReadGameError err) {
/*     */       
/*  66 */       LOG.error("Error reading file: " + err);
/*  67 */       return null;
/*     */     } 
/*  69 */     Game game = GameFactory.instance(GameModes.LOAD_GAME, GameTypes.LOCAL, whiteName, blackName, PlayerType.LOCAL_USER, PlayerType.LOCAL_USER, true, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  79 */     importData(tempStr, game);
/*  80 */     game.getChessboard().repaint();
/*  81 */     return game;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void importData(String data, Game game) throws ReadGameError {
/*  87 */     game.setBlockedChessboard(true);
/*  88 */     importData(new BufferedReader(new StringReader(data)), game);
/*  89 */     game.setBlockedChessboard(false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  94 */   private void importData(BufferedReader br, Game game) throws ReadGameError { game.getMoves().setMoves(getLineWithVar(br, "1.")); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String exportData(Game game) {
/* 100 */     Calendar cal = Calendar.getInstance();
/* 101 */     Settings sett = game.getSettings();
/* 102 */     StringBuilder strBuilder = new StringBuilder();
/* 103 */     String header = String.format("[Event \"Game\"]\n[Date \"%s.%s.%s\"]\n[White \"%s\"]\n[Black \"%s\"]\n\n", new Object[] {
/*     */           
/* 105 */           Integer.valueOf(cal.get(1)), Integer.valueOf(cal.get(2) + 1), Integer.valueOf(cal.get(5)), sett
/* 106 */           .getPlayerWhite().getName(), sett.getPlayerBlack().getName()
/*     */         });
/* 108 */     strBuilder.append(header);
/* 109 */     strBuilder.append(game.getMoves().getMovesInString());
/* 110 */     return strBuilder.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String getLineWithVar(BufferedReader br, String srcStr) throws ReadGameError {
/* 121 */     String str = new String();
/*     */ 
/*     */     
/*     */     do {
/*     */       try {
/* 126 */         str = br.readLine();
/*     */       }
/* 128 */       catch (IOException exc) {
/*     */         
/* 130 */         LOG.error("Something wrong reading file: ", exc);
/* 131 */         throw new ReadGameError("Something wrong reading file: " + exc);
/*     */       } 
/* 133 */       if (str == null) {
/*     */         
/* 135 */         LOG.error("Something wrong reading file, str == null.");
/* 136 */         throw new ReadGameError("Something wrong reading file, str == null.");
/*     */       } 
/* 138 */     } while (!str.startsWith(srcStr));
/*     */     
/* 140 */     return str;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String getValue(String line) throws ReadGameError {
/* 152 */     int from = line.indexOf("\"");
/* 153 */     int to = line.lastIndexOf("\"");
/* 154 */     int size = line.length() - 1;
/* 155 */     String result = "";
/* 156 */     if (to < from || from > size || to > size || to < 0 || from < 0)
/*     */     {
/* 158 */       throw new ReadGameError("Error reading value from PGN header section.");
/*     */     }
/*     */     
/*     */     try {
/* 162 */       result = line.substring(from + 1, to);
/*     */     }
/* 164 */     catch (StringIndexOutOfBoundsException exc) {
/*     */       
/* 166 */       LOG.error("error getting value: ", exc);
/* 167 */       return "none";
/*     */     } 
/* 169 */     return result;
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\core\data_transfer\implementations\PGNNotation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */