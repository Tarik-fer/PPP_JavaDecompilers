/*     */ package pl.art.lach.mateusz.javaopenchess.core.data_transfer.implementations;
/*     */ 
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Chessboard;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Colors;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Game;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.GameFactory;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Square;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Squares;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.data_transfer.DataExporter;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.data_transfer.DataImporter;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.exceptions.ReadGameError;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.PieceFactory;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.King;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Pawn;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Rook;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.players.Player;
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
/*     */ 
/*     */ public class FenNotation
/*     */   implements DataImporter, DataExporter
/*     */ {
/*     */   public static final String INITIAL_STATE = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
/*     */   private static final String BLACK_QUEEN_SYMBOL = "q";
/*     */   private static final String BLACK_KING_SYMBOL = "k";
/*  49 */   private final String WHITE_QUEEN_SYMBOL = "Q";
/*     */   
/*     */   private static final String WHITE_KING_SYMBOL = "K";
/*     */   
/*     */   private static final int PIECES_STATE_NUM = 0;
/*     */   
/*     */   private static final int ACTIVE_PLAYER_NUM = 1;
/*     */   
/*     */   private static final int CASTLING_STATE_NUM = 2;
/*     */   
/*     */   private static final int EN_PASSANT_STATE_NUM = 3;
/*     */   
/*     */   private static final int HALF_COUNTER_STATE_NUM = 4;
/*     */   
/*     */   private static final int FULL_COUNTER_STATE_NUM = 5;
/*     */   
/*     */   private static final String CHAR_PLAYER_WHITE = "w";
/*     */   
/*     */   private static final String CHAR_PLAYER_BLACK = "b";
/*     */   
/*     */   public static final String ROW_SEPARATOR = "/";
/*     */   
/*     */   public static final String FIELD_SEPARATOR = " ";
/*     */   
/*     */   public static final String FIELD_EMPTY = "-";
/*     */   
/*     */   private static final String SQUARE_PREFIX = "SQ_";
/*     */   private static final int NUMBER_OF_FIELDS = 6;
/*     */   private static final int NUMBER_OF_ROWS = 8;
/*     */   
/*     */   public Game importData(String data) throws ReadGameError {
/*  80 */     String whiteName = "--";
/*  81 */     String blackName = "--";
/*  82 */     Game game = GameFactory.instance(GameModes.LOAD_GAME, GameTypes.LOCAL, whiteName, blackName, PlayerType.LOCAL_USER, PlayerType.LOCAL_USER, true, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  92 */     importData(data, game);
/*  93 */     game.getChessboard().repaint();
/*  94 */     return game;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void importData(String data, Game game) throws ReadGameError {
/* 100 */     Chessboard chessboard = game.getChessboard();
/* 101 */     chessboard.clear();
/* 102 */     String[] fields = data.split(" ");
/* 103 */     if (6 != fields.length)
/*     */     {
/* 105 */       throw new ReadGameError(
/* 106 */           Settings.lang("invalid_fen_state"), 
/* 107 */           Settings.lang("invalid_fen_number_of_fields"));
/*     */     }
/*     */     
/* 110 */     Player blackPlayer = game.getSettings().getPlayerBlack();
/* 111 */     Player whitePlayer = game.getSettings().getPlayerWhite();
/* 112 */     importPieces(fields[0], game, whitePlayer, blackPlayer);
/* 113 */     importActivePlayer(fields[1], game);
/* 114 */     importCastlingState(fields[2], chessboard);
/* 115 */     importEnPassantState(fields[3], chessboard, game);
/* 116 */     importCounters(fields, game);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void importCounters(String[] fields, Game game) throws ReadGameError {
/*     */     try {
/* 124 */       Integer halfCounter = Integer.valueOf(Integer.parseInt(fields[4]));
/* 125 */       game.getChessboard().setHalfCounter(halfCounter.intValue());
/* 126 */       game.getChessboard().setFullMoveCounterAdd(
/* 127 */           Integer.parseInt(fields[5]));
/*     */     
/*     */     }
/* 130 */     catch (NumberFormatException exc) {
/*     */       
/* 132 */       throw new ReadGameError(Settings.lang("invalid_fen_state"), fields[4]);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void importEnPassantState(String enPassantState, Chessboard chessboard, Game game) throws ReadGameError {
/* 138 */     if (!"-".equals(enPassantState) && enPassantState.length() == 2) {
/*     */       
/*     */       try {
/*     */         
/* 142 */         Squares sqX = Squares.valueOf("SQ_" + enPassantState.substring(0, 1).toUpperCase());
/* 143 */         Squares sqY = Squares.valueOf("SQ_" + enPassantState.substring(1, 2).toUpperCase());
/* 144 */         if (Squares.SQ_3 == sqY) {
/*     */           
/* 146 */           sqY = Squares.SQ_4;
/*     */         }
/* 148 */         else if (Squares.SQ_5 == sqY) {
/*     */           
/* 150 */           sqY = Squares.SQ_6;
/*     */         }
/*     */         else {
/*     */           
/* 154 */           throw new ReadGameError(Settings.lang("invalid_fen_state"), enPassantState);
/*     */         } 
/* 156 */         Square sq = chessboard.getSquare(sqX, sqY);
/* 157 */         Piece piece = sq.getPiece();
/* 158 */         if (null != piece && Pawn.class == piece.getClass())
/*     */         {
/* 160 */           game.getChessboard().setTwoSquareMovedPawn((Pawn)sq.getPiece());
/*     */         }
/*     */       }
/* 163 */       catch (IllegalStateException exc) {
/*     */         
/* 165 */         throw new ReadGameError(Settings.lang("invalid_fen_state"), enPassantState);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void importCastlingState(String castlingState, Chessboard chessboard) throws ReadGameError {
/* 172 */     for (int i = 0, size = castlingState.length(); i < size; i++) {
/*     */       
/* 174 */       String state = castlingState.substring(i, i + 1);
/* 175 */       if (!"-".equals(state))
/*     */       {
/* 177 */         switch (state) {
/*     */           
/*     */           case "K":
/* 180 */             setupCastlingState(chessboard
/* 181 */                 .getSquare(Squares.SQ_E, Squares.SQ_1), chessboard
/* 182 */                 .getSquare(Squares.SQ_H, Squares.SQ_1));
/*     */             break;
/*     */           
/*     */           case "Q":
/* 186 */             setupCastlingState(chessboard
/* 187 */                 .getSquare(Squares.SQ_E, Squares.SQ_1), chessboard
/* 188 */                 .getSquare(Squares.SQ_A, Squares.SQ_1));
/*     */             break;
/*     */           
/*     */           case "k":
/* 192 */             setupCastlingState(chessboard
/* 193 */                 .getSquare(Squares.SQ_E, Squares.SQ_8), chessboard
/* 194 */                 .getSquare(Squares.SQ_H, Squares.SQ_8));
/*     */             break;
/*     */           
/*     */           case "q":
/* 198 */             setupCastlingState(chessboard
/* 199 */                 .getSquare(Squares.SQ_E, Squares.SQ_8), chessboard
/* 200 */                 .getSquare(Squares.SQ_A, Squares.SQ_8));
/*     */             break;
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setupCastlingState(Square kingSquare, Square rookSquare) throws ReadGameError {
/* 215 */     Piece piece = kingSquare.getPiece();
/* 216 */     if (King.class == piece.getClass()) {
/*     */       
/* 218 */       King king = (King)piece;
/* 219 */       king.setWasMotioned(false);
/*     */     }
/*     */     else {
/*     */       
/* 223 */       throw new ReadGameError(Settings.lang("invalid_fen_state"));
/*     */     } 
/* 225 */     piece = rookSquare.getPiece();
/* 226 */     if (Rook.class == piece.getClass()) {
/*     */       
/* 228 */       Rook rook = (Rook)piece;
/* 229 */       rook.setWasMotioned(false);
/*     */     }
/*     */     else {
/*     */       
/* 233 */       throw new ReadGameError(Settings.lang("invalid_fen_state"));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void importActivePlayer(String activePlayer, Game game) {
/* 239 */     if ("w".equals(activePlayer)) {
/*     */       
/* 241 */       game.setActivePlayer(game.getSettings().getPlayerWhite());
/*     */     }
/*     */     else {
/*     */       
/* 245 */       game.setActivePlayer(game.getSettings().getPlayerBlack());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void importPieces(String piecesStateString, Game game, Player whitePlayer, Player blackPlayer) throws ReadGameError {
/* 253 */     int currentY = Squares.SQ_8.getValue();
/* 254 */     String[] rows = piecesStateString.split("/");
/* 255 */     if (8 != rows.length)
/*     */     {
/* 257 */       throw new ReadGameError(
/* 258 */           Settings.lang("invalid_fen_state"), 
/* 259 */           Settings.lang("invalid_fen_number_of_rows"));
/*     */     }
/*     */     
/* 262 */     for (String row : piecesStateString.split("/")) {
/*     */       
/* 264 */       int currentX = Squares.SQ_A.getValue();
/* 265 */       for (int i = 0; i < row.length(); i++) {
/*     */         
/* 267 */         String currChar = row.substring(i, i + 1);
/*     */         
/*     */         try {
/* 270 */           Integer currNumber = Integer.valueOf(Integer.parseInt(currChar));
/* 271 */           currentX += currNumber.intValue();
/*     */         }
/* 273 */         catch (NumberFormatException nfe) {
/*     */           
/* 275 */           Piece piece = PieceFactory.getPieceFromFenNotation(game
/* 276 */               .getChessboard(), currChar, whitePlayer, blackPlayer);
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 281 */           Square square = game.getChessboard().getSquare(currentX, currentY);
/* 282 */           square.setPiece(piece);
/* 283 */           currentX++;
/*     */         } 
/*     */       } 
/* 286 */       currentY++;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String exportData(Game game) {
/* 294 */     StringBuilder result = new StringBuilder();
/* 295 */     result.append(exportChessboardFields(game));
/* 296 */     result.append(" ");
/* 297 */     result.append(exportActivePlayer(game));
/* 298 */     result.append(" ");
/* 299 */     result.append(exportCastlingState(game));
/* 300 */     result.append(" ");
/* 301 */     result.append(exportEnPassantState(game));
/* 302 */     result.append(" ");
/* 303 */     result.append(game.getChessboard().getHalfCounter());
/* 304 */     result.append(exportFullMoveCounter(game));
/* 305 */     return result.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   private String exportFullMoveCounter(Game game) {
/* 310 */     int size = game.getMoves().getMoveBackStack().size();
/* 311 */     int counter = size / 2 + 1;
/* 312 */     int counterAdd = game.getChessboard().getFullMoveCounterAdd();
/* 313 */     if (counterAdd > 0)
/*     */     {
/* 315 */       counter += counterAdd - 1;
/*     */     }
/* 317 */     return " " + counter;
/*     */   }
/*     */ 
/*     */   
/*     */   private String exportEnPassantState(Game game) {
/* 322 */     StringBuilder result = new StringBuilder();
/* 323 */     Pawn pawn = game.getChessboard().getTwoSquareMovedPawn();
/* 324 */     if (null != pawn) {
/*     */       
/* 326 */       Square pawnSquare = pawn.getSquare();
/* 327 */       Square testSquare = null;
/* 328 */       if (Colors.WHITE == pawn.getPlayer().getColor()) {
/*     */         
/* 330 */         testSquare = new Square(pawnSquare.getPozX(), pawnSquare.getPozY() + 1, null);
/*     */       }
/*     */       else {
/*     */         
/* 334 */         testSquare = new Square(pawnSquare.getPozX(), pawnSquare.getPozY() - 1, null);
/*     */       } 
/* 336 */       result.append(testSquare.getAlgebraicNotation());
/*     */     }
/*     */     else {
/*     */       
/* 340 */       result.append("-");
/*     */     } 
/* 342 */     return result.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   private String exportCastlingState(Game game) {
/* 347 */     String result = "";
/* 348 */     Chessboard chessboard = game.getChessboard();
/* 349 */     result = result + exportCastlingOfOneColor(chessboard.getKingWhite(), chessboard, Squares.SQ_1);
/* 350 */     result = result + exportCastlingOfOneColor(chessboard.getKingBlack(), chessboard, Squares.SQ_8);
/* 351 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   private String exportCastlingOfOneColor(King king, Chessboard chessboard, Squares squareLine) {
/* 356 */     String result = "";
/* 357 */     Colors color = king.getPlayer().getColor();
/* 358 */     if (!king.getWasMotioned()) {
/*     */       
/* 360 */       Piece piece = chessboard.getSquare(Squares.SQ_A, squareLine).getPiece();
/* 361 */       if (piece instanceof Rook) {
/*     */         
/* 363 */         Rook rightRook = (Rook)piece;
/* 364 */         if (rightRook.getWasMotioned()) {
/*     */           
/* 366 */           result = result + "-";
/*     */         }
/*     */         else {
/*     */           
/* 370 */           result = result + ((color == Colors.WHITE) ? "K" : "k");
/*     */         } 
/*     */       } 
/*     */       
/* 374 */       piece = chessboard.getSquare(Squares.SQ_H, squareLine).getPiece();
/* 375 */       if (piece instanceof Rook)
/*     */       {
/* 377 */         Rook leftRook = (Rook)piece;
/* 378 */         if (leftRook.getWasMotioned())
/*     */         {
/* 380 */           result = result + "-";
/*     */         }
/*     */         else
/*     */         {
/* 384 */           result = result + ((color == Colors.WHITE) ? "Q" : "q");
/*     */         }
/*     */       
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 391 */       result = result + "-";
/*     */     } 
/* 393 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   private String exportChessboardFields(Game game) {
/* 398 */     String result = "";
/* 399 */     Chessboard chessboard = game.getChessboard();
/* 400 */     for (int y = 0; y <= 7; y++) {
/*     */       
/* 402 */       int emptySquares = 0;
/* 403 */       for (int x = 0; x <= 7; x++) {
/*     */         
/* 405 */         Square sq = chessboard.getSquare(x, y);
/* 406 */         Piece piece = sq.getPiece();
/* 407 */         if (null == piece) {
/*     */           
/* 409 */           emptySquares++;
/*     */         }
/*     */         else {
/*     */           
/* 413 */           if (0 != emptySquares) {
/*     */             
/* 415 */             result = result + emptySquares;
/* 416 */             emptySquares = 0;
/*     */           } 
/* 418 */           String symbol = null;
/* 419 */           if (piece instanceof Pawn) {
/*     */             
/* 421 */             symbol = "P";
/*     */           }
/*     */           else {
/*     */             
/* 425 */             symbol = piece.getSymbol();
/*     */           } 
/* 427 */           result = result + ((piece.getPlayer().getColor() == Colors.WHITE) ? symbol.toUpperCase() : symbol.toLowerCase());
/*     */         } 
/*     */       } 
/* 430 */       if (0 != emptySquares)
/*     */       {
/* 432 */         result = result + emptySquares;
/*     */       }
/* 434 */       if (7 != y)
/*     */       {
/* 436 */         result = result + "/";
/*     */       }
/* 438 */       emptySquares = 0;
/*     */     } 
/* 440 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   private String exportActivePlayer(Game game) {
/* 445 */     String result = "";
/* 446 */     if (Colors.WHITE == game.getActivePlayer().getColor()) {
/*     */       
/* 448 */       result = result + "w";
/*     */     }
/*     */     else {
/*     */       
/* 452 */       result = result + "b";
/*     */     } 
/* 454 */     return result;
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\core\data_transfer\implementations\FenNotation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */