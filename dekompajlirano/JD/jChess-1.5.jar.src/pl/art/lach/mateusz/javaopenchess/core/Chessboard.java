/*     */ package pl.art.lach.mateusz.javaopenchess.core;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Set;
/*     */ import org.apache.log4j.Logger;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.moves.Castling;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.moves.Move;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.moves.MovesHistory;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Bishop;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.King;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Knight;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Pawn;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Queen;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Rook;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.players.Player;
/*     */ import pl.art.lach.mateusz.javaopenchess.display.views.chessboard.ChessboardView;
/*     */ import pl.art.lach.mateusz.javaopenchess.display.views.chessboard.implementation.graphic2D.Chessboard2D;
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
/*     */ 
/*     */ public class Chessboard
/*     */ {
/*  45 */   private static final Logger LOG = Logger.getLogger(Chessboard.class);
/*     */ 
/*     */   
/*     */   protected static final int TOP = 0;
/*     */ 
/*     */   
/*     */   protected static final int BOTTOM = 7;
/*     */ 
/*     */   
/*     */   public static final int LAST_SQUARE = 7;
/*     */ 
/*     */   
/*     */   public static final int FIRST_SQUARE = 0;
/*     */ 
/*     */   
/*     */   public static final int NUMBER_OF_SQUARES = 8;
/*     */ 
/*     */   
/*     */   protected Square[][] squares;
/*     */ 
/*     */   
/*     */   private Set<Square> moves;
/*     */   
/*     */   private Settings settings;
/*     */   
/*     */   protected King kingWhite;
/*     */   
/*     */   protected King kingBlack;
/*     */   
/*  74 */   private Pawn twoSquareMovedPawn = null;
/*     */ 
/*     */   
/*     */   private MovesHistory movesObject;
/*     */ 
/*     */   
/*     */   protected Square activeSquare;
/*     */ 
/*     */   
/*     */   protected int activeSquareX;
/*     */   
/*     */   protected int activeSquareY;
/*     */   
/*     */   private ChessboardView chessboardView;
/*     */   
/*  89 */   private int halfCounter = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  95 */   private int fullMoveCounterAdd = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Chessboard(Settings settings, MovesHistory moves) {
/* 104 */     this.settings = settings;
/* 105 */     this.chessboardView = (ChessboardView)new Chessboard2D(this);
/*     */     
/* 107 */     this.activeSquareX = 0;
/* 108 */     this.activeSquareY = 0;
/*     */     
/* 110 */     this.squares = new Square[8][8];
/*     */     
/* 112 */     for (int i = 0; i < 8; i++) {
/*     */       
/* 114 */       for (int y = 0; y < 8; y++)
/*     */       {
/* 116 */         this.squares[i][y] = new Square(i, y, null);
/*     */       }
/*     */     } 
/* 119 */     this.movesObject = moves;
/*     */   }
/*     */ 
/*     */   
/*     */   public Chessboard(Settings settings, MovesHistory moves, ChessboardView chessboardView) {
/* 124 */     this(settings, moves);
/* 125 */     this.chessboardView = chessboardView;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 133 */   public static int getTop() { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 141 */   public static int getBottom() { return 7; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPieces4NewGame(Player plWhite, Player plBlack) {
/* 146 */     Player player = plBlack;
/* 147 */     Player player1 = plWhite;
/* 148 */     setFigures4NewGame(0, player);
/* 149 */     setPawns4NewGame(1, player);
/* 150 */     setFigures4NewGame(7, player1);
/* 151 */     setPawns4NewGame(6, player1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setFigures4NewGame(int i, Player player) {
/* 162 */     if (i != 0 && i != 7) {
/*     */       
/* 164 */       LOG.error("error setting figures like rook etc.");
/*     */       return;
/*     */     } 
/* 167 */     if (i == 0)
/*     */     {
/* 169 */       player.setGoDown(true);
/*     */     }
/*     */     
/* 172 */     getSquare(0, i).setPiece((Piece)new Rook(this, player));
/* 173 */     getSquare(7, i).setPiece((Piece)new Rook(this, player));
/* 174 */     getSquare(1, i).setPiece((Piece)new Knight(this, player));
/* 175 */     getSquare(6, i).setPiece((Piece)new Knight(this, player));
/* 176 */     getSquare(2, i).setPiece((Piece)new Bishop(this, player));
/* 177 */     getSquare(5, i).setPiece((Piece)new Bishop(this, player));
/*     */ 
/*     */     
/* 180 */     getSquare(3, i).setPiece((Piece)new Queen(this, player));
/* 181 */     if (player.getColor() == Colors.WHITE) {
/*     */       
/* 183 */       this.kingWhite = new King(this, player);
/* 184 */       getSquare(4, i).setPiece((Piece)this.kingWhite);
/*     */     }
/*     */     else {
/*     */       
/* 188 */       this.kingBlack = new King(this, player);
/* 189 */       getSquare(4, i).setPiece((Piece)this.kingBlack);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setPawns4NewGame(int i, Player player) {
/* 199 */     if (i != 1 && i != 6) {
/*     */       
/* 201 */       LOG.error("error setting pawns etc.");
/*     */       return;
/*     */     } 
/* 204 */     for (int x = 0; x < 8; x++)
/*     */     {
/* 206 */       getSquare(x, i).setPiece((Piece)new Pawn(this, player));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void select(Square sq) {
/* 215 */     setActiveSquare(sq);
/* 216 */     setActiveSquareX(sq.getPozX() + 1);
/* 217 */     setActiveSquareY(sq.getPozY() + 1);
/*     */     
/* 219 */     LOG.debug(String.format("active_x: %s active_y: %s", new Object[] {
/* 220 */             Integer.valueOf(getActiveSquareX()), Integer.valueOf(getActiveSquareY())
/*     */           }));
/* 222 */     getChessboardView().repaint();
/*     */   }
/*     */ 
/*     */   
/*     */   public void unselect() {
/* 227 */     setActiveSquareX(0);
/* 228 */     setActiveSquareY(0);
/* 229 */     setActiveSquare(null);
/*     */     
/* 231 */     getChessboardView().unselect();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 236 */   public void resetActiveSquare() { setActiveSquare(null); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 241 */   public void move(Square begin, Square end) { move(begin, end, true); }
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
/*     */   public void move(int xFrom, int yFrom, int xTo, int yTo) {
/*     */     Square toSQ, fromSQ;
/*     */     try {
/* 257 */       fromSQ = getSquare(xFrom, yFrom);
/* 258 */       toSQ = getSquare(xTo, yTo);
/*     */     }
/* 260 */     catch (IndexOutOfBoundsException exc) {
/*     */       
/* 262 */       LOG.error("error moving piece: " + exc.getMessage());
/*     */       return;
/*     */     } 
/* 265 */     move(fromSQ, toSQ, true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 270 */   public void move(Square begin, Square end, boolean refresh) { move(begin, end, refresh, true); }
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
/*     */   public void move(Square begin, Square end, boolean refresh, boolean clearForwardHistory) {
/* 282 */     Castling castling = Castling.NONE;
/* 283 */     Piece promotedPiece = null;
/* 284 */     Piece takenPiece = null;
/* 285 */     boolean wasEnPassant = false;
/* 286 */     if (null != end.piece) {
/*     */       
/* 288 */       takenPiece = end.piece;
/* 289 */       end.getPiece().setSquare(null);
/*     */     } 
/*     */     
/* 292 */     Square tempBegin = new Square(begin);
/* 293 */     Square tempEnd = new Square(end);
/*     */     
/* 295 */     begin.getPiece().setSquare(end);
/* 296 */     end.piece = begin.piece;
/* 297 */     begin.piece = null;
/*     */     
/* 299 */     if (King.class == end.getPiece().getClass()) {
/*     */       
/* 301 */       castling = moveKing(end, castling, begin);
/*     */     }
/* 303 */     else if (Rook.class == end.getPiece().getClass()) {
/*     */       
/* 305 */       moveRook(end);
/*     */     }
/* 307 */     else if (Pawn.class == end.getPiece().getClass()) {
/*     */       
/* 309 */       wasEnPassant = movePawn(end, begin, tempEnd, wasEnPassant);
/*     */       
/* 311 */       if (Pawn.canBePromoted(end))
/*     */       {
/* 313 */         promotedPiece = promotePawn(clearForwardHistory, end, promotedPiece);
/*     */       }
/*     */     }
/* 316 */     else if (Pawn.class != end.getPiece().getClass()) {
/*     */       
/* 318 */       setTwoSquareMovedPawn(null);
/*     */     } 
/*     */     
/* 321 */     if (refresh) {
/*     */       
/* 323 */       unselect();
/* 324 */       repaint();
/*     */     } 
/*     */     
/* 327 */     handleHalfMoveCounter(end, takenPiece);
/* 328 */     handleHistory(clearForwardHistory, tempBegin, tempEnd, castling, wasEnPassant, promotedPiece);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void handleHalfMoveCounter(Square end, Piece takenPiece) {
/* 334 */     if (isHalfMove(end, takenPiece)) {
/*     */       
/* 336 */       this.halfCounter++;
/*     */     }
/*     */     else {
/*     */       
/* 340 */       this.halfCounter = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 346 */   private static boolean isHalfMove(Square end, Piece takenPiece) { return (!(end.getPiece() instanceof Pawn) && null == takenPiece); }
/*     */ 
/*     */ 
/*     */   
/*     */   private void handleHistory(boolean clearForwardHistory, Square tempBegin, Square tempEnd, Castling castling, boolean wasEnPassant, Piece promotedPiece) {
/* 351 */     if (clearForwardHistory) {
/*     */       
/* 353 */       this.movesObject.clearMoveForwardStack();
/* 354 */       this.movesObject.addMove(tempBegin, tempEnd, true, castling, wasEnPassant, promotedPiece);
/*     */     }
/*     */     else {
/*     */       
/* 358 */       this.movesObject.addMove(tempBegin, tempEnd, false, castling, wasEnPassant, promotedPiece);
/*     */     } 
/* 360 */     if (getSettings().isGameAgainstComputer());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean movePawn(Square end, Square begin, Square tempEnd, boolean wasEnPassant) {
/* 368 */     if (getTwoSquareMovedPawn() != null && getSquares()[end.getPozX()][begin.getPozY()] == getTwoSquareMovedPawn().getSquare()) {
/*     */       
/* 370 */       tempEnd.piece = (getSquares()[end.getPozX()][begin.getPozY()]).piece;
/*     */       
/* 372 */       (this.squares[end.pozX][begin.pozY]).piece = null;
/* 373 */       wasEnPassant = true;
/*     */     } 
/* 375 */     if (begin.getPozY() - end.getPozY() == 2 || end.getPozY() - begin.getPozY() == 2) {
/*     */       
/* 377 */       setTwoSquareMovedPawn((Pawn)end.piece);
/*     */     }
/*     */     else {
/*     */       
/* 381 */       setTwoSquareMovedPawn(null);
/*     */     } 
/* 383 */     return wasEnPassant;
/*     */   }
/*     */ 
/*     */   
/*     */   public Piece promotePawn(boolean clearForwardHistory, Square end, Piece promotedPiece) {
/* 388 */     if (clearForwardHistory) {
/*     */       
/* 390 */       Piece piece = end.getPiece().getPlayer().getPromotionPiece(this);
/* 391 */       if (null != piece) {
/*     */         
/* 393 */         piece.setChessboard(end.getPiece().getChessboard());
/* 394 */         piece.setPlayer(end.getPiece().getPlayer());
/* 395 */         piece.setSquare(end.getPiece().getSquare());
/* 396 */         end.piece = piece;
/* 397 */         promotedPiece = end.piece;
/*     */       } 
/*     */     } 
/* 400 */     return promotedPiece;
/*     */   }
/*     */ 
/*     */   
/*     */   private void moveRook(Square end) {
/* 405 */     if (!((Rook)end.piece).getWasMotioned())
/*     */     {
/* 407 */       ((Rook)end.piece).setWasMotioned(true);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private Castling moveKing(Square end, Castling castling, Square begin) {
/* 413 */     if (!((King)end.piece).getWasMotioned())
/*     */     {
/* 415 */       ((King)end.piece).setWasMotioned(true);
/*     */     }
/*     */     
/* 418 */     castling = King.getCastling(begin, end);
/* 419 */     if (Castling.SHORT_CASTLING == castling) {
/*     */       
/* 421 */       move(getSquare(7, begin.getPozY()), getSquare(end.getPozX() - 1, begin.getPozY()), false, false);
/*     */     }
/* 423 */     else if (Castling.LONG_CASTLING == castling) {
/*     */       
/* 425 */       move(getSquare(0, begin.getPozY()), getSquare(end.getPozX() + 1, begin.getPozY()), false, false);
/*     */     } 
/*     */     
/* 428 */     return castling;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 434 */   public boolean redo() { return redo(true); }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean redo(boolean refresh) {
/* 439 */     if (getSettings().getGameType() == GameTypes.LOCAL) {
/*     */       
/* 441 */       Move first = this.movesObject.redo();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 446 */       if (first != null) {
/*     */         
/* 448 */         Square from = first.getFrom();
/* 449 */         Square to = first.getTo();
/*     */         
/* 451 */         move(getSquares()[from.getPozX()][from.getPozY()], getSquares()[to.getPozX()][to.getPozY()], true, false);
/* 452 */         if (first.getPromotedPiece() != null) {
/*     */           
/* 454 */           Pawn pawn = (Pawn)(getSquares()[to.getPozX()][to.getPozY()]).piece;
/* 455 */           pawn.setSquare(null);
/*     */           
/* 457 */           (this.squares[to.pozX][to.pozY]).piece = first.getPromotedPiece();
/* 458 */           Piece promoted = (getSquares()[to.getPozX()][to.getPozY()]).piece;
/* 459 */           promoted.setSquare(getSquares()[to.getPozX()][to.getPozY()]);
/*     */         } 
/* 461 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 465 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 470 */   public boolean undo() { return undo(true); }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized boolean undo(boolean refresh) {
/* 475 */     Move last = this.movesObject.undo();
/*     */     
/* 477 */     if (canUndo(last))
/*     */     {
/* 479 */       return processUndoOperation(last, refresh);
/*     */     }
/* 481 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean processUndoOperation(Move last, boolean refresh) {
/* 486 */     Square begin = last.getFrom();
/* 487 */     Square end = last.getTo();
/*     */     
/*     */     try {
/* 490 */       Piece moved = last.getMovedPiece();
/* 491 */       (this.squares[begin.pozX][begin.pozY]).piece = moved;
/*     */       
/* 493 */       moved.setSquare(getSquares()[begin.getPozX()][begin.getPozY()]);
/*     */       
/* 495 */       Piece taken = last.getTakenPiece();
/* 496 */       if (last.getCastlingMove() != Castling.NONE) {
/*     */         
/* 498 */         handleUndoCastling(last, end, begin, moved);
/*     */       }
/* 500 */       else if (Rook.class == moved.getClass()) {
/*     */         
/* 502 */         ((Rook)moved).setWasMotioned(false);
/*     */       }
/* 504 */       else if (Pawn.class == moved.getClass() && last.wasEnPassant()) {
/*     */         
/* 506 */         handleEnPessant(last, end, begin);
/*     */       }
/* 508 */       else if (Pawn.class == moved.getClass() && last.getPromotedPiece() != null) {
/*     */         
/* 510 */         handlePawnPromotion(end);
/*     */       } 
/*     */ 
/*     */       
/* 514 */       Move oneMoveEarlier = this.movesObject.getLastMoveFromHistory();
/* 515 */       if (oneMoveEarlier != null && oneMoveEarlier.wasPawnTwoFieldsMove()) {
/*     */         
/* 517 */         int toPozX = oneMoveEarlier.getTo().getPozX();
/* 518 */         int toPozY = oneMoveEarlier.getTo().getPozY();
/* 519 */         Piece canBeTakenEnPassant = getSquare(toPozX, toPozY).getPiece();
/* 520 */         if (Pawn.class == canBeTakenEnPassant.getClass())
/*     */         {
/* 522 */           setTwoSquareMovedPawn((Pawn)canBeTakenEnPassant);
/*     */         }
/*     */       } 
/*     */       
/* 526 */       if (taken != null && !last.wasEnPassant()) {
/*     */         
/* 528 */         (this.squares[end.pozX][end.pozY]).piece = taken;
/* 529 */         taken.setSquare(getSquares()[end.getPozX()][end.getPozY()]);
/*     */       }
/*     */       else {
/*     */         
/* 533 */         (this.squares[end.pozX][end.pozY]).piece = null;
/*     */       } 
/*     */       
/* 536 */       if (refresh)
/*     */       {
/* 538 */         unselect();
/* 539 */         repaint();
/*     */       }
/*     */     
/*     */     }
/* 543 */     catch (ArrayIndexOutOfBoundsException|NullPointerException exc) {
/*     */       
/* 545 */       LOG.error(
/* 546 */           String.format("error: %s exc object: ", new Object[] { exc.getClass() }), exc);
/*     */ 
/*     */       
/* 549 */       return false;
/*     */     } 
/*     */     
/* 552 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void handleUndoCastling(Move last, Square end, Square begin, Piece moved) {
/* 557 */     Piece rook = null;
/* 558 */     if (last.getCastlingMove() == Castling.SHORT_CASTLING) {
/*     */       
/* 560 */       rook = handleShortCastling(rook, end, begin);
/*     */     }
/*     */     else {
/*     */       
/* 564 */       rook = handleLongCastling(rook, end, begin);
/*     */     } 
/* 566 */     ((King)moved).setWasMotioned(false);
/* 567 */     ((Rook)rook).setWasMotioned(false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 572 */   private static boolean canUndo(Move last) { return (last != null && last.getFrom() != null); }
/*     */ 
/*     */ 
/*     */   
/*     */   private void handlePawnPromotion(Square end) {
/* 577 */     Piece promoted = (getSquares()[end.getPozX()][end.getPozY()]).piece;
/* 578 */     promoted.setSquare(null);
/* 579 */     (this.squares[end.pozX][end.pozY]).piece = null;
/*     */   }
/*     */ 
/*     */   
/*     */   private void handleEnPessant(Move last, Square end, Square begin) {
/* 584 */     Pawn pawn = (Pawn)last.getTakenPiece();
/* 585 */     (this.squares[end.pozX][begin.pozY]).piece = (Piece)pawn;
/* 586 */     pawn.setSquare(getSquares()[end.getPozX()][begin.getPozY()]);
/*     */   }
/*     */ 
/*     */   
/*     */   private Piece handleLongCastling(Piece rook, Square end, Square begin) {
/* 591 */     rook = (getSquares()[end.getPozX() + 1][end.getPozY()]).piece;
/* 592 */     (this.squares[0][begin.pozY]).piece = rook;
/* 593 */     rook.setSquare(getSquares()[0][begin.getPozY()]);
/* 594 */     (this.squares[end.pozX + 1][end.pozY]).piece = null;
/* 595 */     return rook;
/*     */   }
/*     */ 
/*     */   
/*     */   private Piece handleShortCastling(Piece rook, Square end, Square begin) {
/* 600 */     rook = (getSquares()[end.getPozX() - 1][end.getPozY()]).piece;
/* 601 */     (this.squares[7][begin.pozY]).piece = rook;
/* 602 */     rook.setSquare(getSquares()[7][begin.getPozY()]);
/* 603 */     (this.squares[end.pozX - 1][end.pozY]).piece = null;
/* 604 */     return rook;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 612 */   public Square[][] getSquares() { return this.squares; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Square getSquare(int x, int y) {
/*     */     try {
/* 619 */       return this.squares[x][y];
/*     */     }
/* 621 */     catch (ArrayIndexOutOfBoundsException exc) {
/*     */       
/* 623 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 629 */   public Square getSquare(Squares squareX, Squares squareY) { return getSquare(squareX.getValue(), squareY.getValue()); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 634 */     for (int i = 0; i < this.squares.length; i++) {
/*     */       
/* 636 */       for (int j = 0; j < (this.squares[i]).length; j++) {
/*     */         
/* 638 */         Piece piece = this.squares[i][j].getPiece();
/* 639 */         piece = null;
/* 640 */         this.squares[i][j].setPiece(null);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 650 */   public Square getActiveSquare() { return this.activeSquare; }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Piece> getAllPieces(Colors color) {
/* 655 */     ArrayList<Piece> result = new ArrayList<>();
/* 656 */     for (int i = 0; i < this.squares.length; i++) {
/*     */       
/* 658 */       for (int j = 0; j < (this.squares[i]).length; j++) {
/*     */         
/* 660 */         Square sq = this.squares[i][j];
/* 661 */         if (null != sq.getPiece() && (sq
/* 662 */           .getPiece().getPlayer().getColor() == color || color == null))
/*     */         {
/* 664 */           result.add(sq.getPiece());
/*     */         }
/*     */       } 
/*     */     } 
/* 668 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean wasEnPassant(Square sq) {
/* 673 */     return (sq.getPiece() != null && sq
/* 674 */       .getPiece().getChessboard().getTwoSquareMovedPawn() != null && sq == sq
/* 675 */       .getPiece().getChessboard().getTwoSquareMovedPawn().getSquare());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 683 */   public King getKingWhite() { return this.kingWhite; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 691 */   public King getKingBlack() { return this.kingBlack; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKingWhite(King kingWhite, Square sq) {
/* 696 */     this.kingWhite = kingWhite;
/* 697 */     getSquare(sq.getPozX(), sq.getPozY()).setPiece((Piece)this.kingWhite);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setKingBlack(King kingBlack, Square sq) {
/* 702 */     this.kingBlack = kingBlack;
/* 703 */     getSquare(sq.getPozX(), sq.getPozY()).setPiece((Piece)this.kingBlack);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 710 */   public Pawn getTwoSquareMovedPawn() { return this.twoSquareMovedPawn; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 718 */   public ChessboardView getChessboardView() { return this.chessboardView; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 726 */   public void setChessboardView(ChessboardView chessboardView) { this.chessboardView = chessboardView; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 731 */   public void repaint() { getChessboardView().repaint(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 739 */   public Settings getSettings() { return this.settings; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSettings(Settings settings) {
/* 747 */     this.settings = settings;
/*     */     
/* 749 */     for (int i = 0; i < 7; i++) {
/*     */       
/* 751 */       for (int j = 0; j < 7; j++) {
/*     */         
/* 753 */         Square sq = getSquare(i, j);
/* 754 */         if (null != sq.getPiece())
/*     */         {
/* 756 */           if (Colors.WHITE == sq.getPiece().getPlayer().getColor()) {
/*     */             
/* 758 */             sq.getPiece().setPlayer(settings.getPlayerWhite());
/*     */           }
/*     */           else {
/*     */             
/* 762 */             sq.getPiece().setPlayer(settings.getPlayerBlack());
/*     */           } 
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
/* 774 */   public Set<Square> getMoves() { return this.moves; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 782 */   public void setMoves(Set<Square> moves) { this.moves = moves; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 790 */   public void setActiveSquare(Square activeSquare) { this.activeSquare = activeSquare; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 798 */   public int getActiveSquareX() { return this.activeSquareX; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 806 */   public void setActiveSquareX(int activeSquareX) { this.activeSquareX = activeSquareX; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 814 */   public int getActiveSquareY() { return this.activeSquareY; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 822 */   public void setActiveSquareY(int activeSquareY) { this.activeSquareY = activeSquareY; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 830 */   public void setTwoSquareMovedPawn(Pawn twoSquareMovedPawn) { this.twoSquareMovedPawn = twoSquareMovedPawn; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 838 */   public int getHalfCounter() { return this.halfCounter; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 846 */   public void setHalfCounter(int halfCounter) { this.halfCounter = halfCounter; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 854 */   public int getFullMoveCounterAdd() { return this.fullMoveCounterAdd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 862 */   public void setFullMoveCounterAdd(int fullMoveCounterAdd) { this.fullMoveCounterAdd = fullMoveCounterAdd; }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\core\Chessboard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */