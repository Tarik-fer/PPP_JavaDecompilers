/*     */ package pl.art.lach.mateusz.javaopenchess.core.moves;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Rectangle;
/*     */ import java.util.ArrayList;
/*     */ import java.util.EmptyStackException;
/*     */ import java.util.Set;
/*     */ import java.util.Stack;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.table.AbstractTableModel;
/*     */ import org.apache.log4j.Logger;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Chessboard;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Colors;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Game;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Square;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.exceptions.ReadGameError;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Pawn;
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
/*     */ public class MovesHistory
/*     */   extends AbstractTableModel
/*     */ {
/*  47 */   private static final Logger LOG = Logger.getLogger(MovesHistory.class);
/*     */   
/*     */   private static final int CHAR_TINY_X_ASCII = 120;
/*     */   
/*     */   private static final int CHAR_HYPHEN_ASCII = 45;
/*     */   
/*     */   private static final int CHAR_R_ASCII = 82;
/*     */   
/*     */   private static final int CHAR_Q_ASCII = 81;
/*     */   
/*     */   private static final int CHAR_N_ASCII = 78;
/*     */   
/*     */   private static final int CHAR_K_ASCII = 75;
/*     */   
/*     */   private static final int CHAR_B_ASCII = 66;
/*     */   
/*     */   private static final int CHAR_TINY_H_ASCII = 104;
/*     */   
/*     */   public static final String SYMBOL_CHECK = "+";
/*     */   
/*     */   public static final String SYMBOL_CHECK_MATE = "#";
/*     */   
/*     */   private static final int CHAR_TINY_A_ASCII = 97;
/*     */   
/*     */   public static final String SYMBOL_NORMAL_MOVE = "-";
/*     */   
/*     */   public static final String SYMBOL_PIECE_TAKEN = "x";
/*     */   
/*     */   public static final String SYMBOL_EN_PASSANT = "(e.p)";
/*     */   
/*  77 */   private ArrayList<String> moves = new ArrayList<>();
/*     */   
/*  79 */   private int columnsNum = 3;
/*     */   
/*  81 */   private int rowsNum = 0;
/*     */   
/*  83 */   private String[] names = new String[] {
/*     */       
/*  85 */       Settings.lang("white"), Settings.lang("black")
/*     */     };
/*     */   
/*     */   private NotEditableTableModel tableModel;
/*     */   
/*     */   private JScrollPane scrollPane;
/*     */   
/*     */   private JTable table;
/*     */   
/*     */   private boolean enterBlack = false;
/*     */   
/*     */   private Game game;
/*     */   
/*  98 */   private Stack<Move> moveBackStack = new Stack<>();
/*     */   
/* 100 */   protected Stack<Move> moveForwardStack = new Stack<>();
/*     */   
/* 102 */   private int fiftyMoveRuleCounter = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   public MovesHistory(Game game) {
/* 107 */     this.tableModel = new NotEditableTableModel();
/* 108 */     this.table = new JTable(this.tableModel);
/* 109 */     this.scrollPane = new JScrollPane(this.table);
/*     */     
/* 111 */     this.scrollPane.setMaximumSize(new Dimension(100, 100));
/* 112 */     this.table.setMinimumSize(new Dimension(100, 100));
/* 113 */     this.game = game;
/*     */     
/* 115 */     this.tableModel.addColumn(this.names[0]);
/* 116 */     this.tableModel.addColumn(this.names[1]);
/* 117 */     addTableModelListener(null);
/* 118 */     this.tableModel.addTableModelListener(null);
/* 119 */     this.scrollPane.setAutoscrolls(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void draw() {}
/*     */ 
/*     */ 
/*     */   
/* 129 */   public String getValueAt(int x, int y) { return this.moves.get(y * 2 - 1 + x - 1); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 135 */   public int getRowCount() { return this.rowsNum; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 141 */   public int getColumnCount() { return this.columnsNum; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 146 */   protected void addRow() { this.tableModel.addRow((Object[])new String[2]); }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addCastling(String move) {
/* 151 */     this.moves.remove(this.moves.size() - 1);
/* 152 */     if (!this.enterBlack) {
/*     */       
/* 154 */       this.tableModel.setValueAt(move, this.tableModel.getRowCount() - 1, 1);
/*     */     }
/*     */     else {
/*     */       
/* 158 */       this.tableModel.setValueAt(move, this.tableModel.getRowCount() - 1, 0);
/*     */     } 
/* 160 */     this.moves.add(move);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 166 */   public boolean isCellEditable(int a, int b) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addMove2Table(String str) {
/*     */     try {
/* 176 */       if (!this.enterBlack) {
/*     */         
/* 178 */         addRow();
/* 179 */         this.rowsNum = this.tableModel.getRowCount() - 1;
/* 180 */         this.tableModel.setValueAt(str, this.rowsNum, 0);
/*     */       }
/*     */       else {
/*     */         
/* 184 */         this.tableModel.setValueAt(str, this.rowsNum, 1);
/* 185 */         this.rowsNum = this.tableModel.getRowCount() - 1;
/*     */       } 
/* 187 */       this.enterBlack = !this.enterBlack;
/* 188 */       this.table.scrollRectToVisible(this.table.getCellRect(this.table.getRowCount() - 1, 0, true));
/*     */     
/*     */     }
/* 191 */     catch (ArrayIndexOutOfBoundsException exc) {
/*     */       
/* 193 */       if (this.rowsNum > 0) {
/*     */         
/* 195 */         this.rowsNum--;
/* 196 */         addMove2Table(str);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addMove(String move) {
/* 207 */     if (isMoveCorrect(move)) {
/*     */       
/* 209 */       this.moves.add(move);
/* 210 */       addMove2Table(move);
/* 211 */       this.moveForwardStack.clear();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addMove(Square begin, Square end, boolean registerInHistory, Castling castlingMove, boolean wasEnPassant, Piece promotedPiece) {
/* 219 */     String locMove = begin.getPiece().getSymbol();
/*     */     
/* 221 */     if (this.game.getSettings().isUpsideDown()) {
/*     */       
/* 223 */       locMove = addMoveHandleUpsideDown(locMove, begin);
/*     */     }
/*     */     else {
/*     */       
/* 227 */       locMove = addMoveHandleNormalSetup(locMove, begin);
/*     */     } 
/*     */     
/* 230 */     if (end.piece != null) {
/*     */       
/* 232 */       locMove = locMove + "x";
/*     */     }
/*     */     else {
/*     */       
/* 236 */       locMove = locMove + "-";
/*     */     } 
/*     */     
/* 239 */     if (this.game.getSettings().isUpsideDown()) {
/*     */       
/* 241 */       locMove = addMoveHandleUpsideDown(locMove, end);
/*     */     }
/*     */     else {
/*     */       
/* 245 */       locMove = addMoveHandleNormalSetup(locMove, end);
/*     */     } 
/*     */     
/* 248 */     if (Pawn.class == begin.getPiece().getClass() && begin.getPozX() - end.getPozX() != 0 && end.piece == null) {
/*     */       
/* 250 */       locMove = locMove + "(e.p)";
/* 251 */       wasEnPassant = true;
/*     */     } 
/* 253 */     if (isBlackOrWhiteKingCheck())
/*     */     {
/* 255 */       if (isBlackOrWhiteKingCheckmatedOrStalemated()) {
/*     */         
/* 257 */         locMove = locMove + "#";
/*     */       }
/*     */       else {
/*     */         
/* 261 */         locMove = locMove + "+";
/*     */       } 
/*     */     }
/* 264 */     if (castlingMove != Castling.NONE) {
/*     */       
/* 266 */       addCastling(castlingMove.getSymbol());
/*     */     }
/*     */     else {
/*     */       
/* 270 */       this.moves.add(locMove);
/* 271 */       addMove2Table(locMove);
/*     */     } 
/* 273 */     this.scrollPane.scrollRectToVisible(new Rectangle(0, this.scrollPane.getHeight() - 2, 1, 1));
/*     */     
/* 275 */     if (registerInHistory) {
/*     */       
/* 277 */       Move moveToAdd = new Move(new Square(begin), new Square(end), begin.piece, end.piece, castlingMove, wasEnPassant, promotedPiece);
/* 278 */       getMoveBackStack().add(moveToAdd);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isBlackOrWhiteKingCheckmatedOrStalemated() {
/* 284 */     return ((!this.enterBlack && this.game.getChessboard().getKingBlack().isCheckmatedOrStalemated() == 1) || (this.enterBlack && this.game
/* 285 */       .getChessboard().getKingWhite().isCheckmatedOrStalemated() == 1));
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isBlackOrWhiteKingCheck() {
/* 290 */     return ((!this.enterBlack && this.game.getChessboard().getKingBlack().isChecked()) || (this.enterBlack && this.game
/* 291 */       .getChessboard().getKingWhite().isChecked()));
/*     */   }
/*     */ 
/*     */   
/*     */   private String addMoveHandleNormalSetup(String locMove, Square begin) {
/* 296 */     locMove = locMove + Character.toString((char)(begin.getPozX() + 97));
/* 297 */     locMove = locMove + Integer.toString(8 - begin.getPozY());
/* 298 */     return locMove;
/*     */   }
/*     */ 
/*     */   
/*     */   private String addMoveHandleUpsideDown(String locMove, Square begin) {
/* 303 */     locMove = locMove + Character.toString((char)(Chessboard.getBottom() - begin.getPozX() + 97));
/* 304 */     locMove = locMove + Integer.toString(begin.getPozY() + 1);
/* 305 */     return locMove;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 311 */   public void clearMoveForwardStack() { this.moveForwardStack.clear(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 316 */   public JScrollPane getScrollPane() { return this.scrollPane; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 321 */   public ArrayList<String> getMoves() { return this.moves; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized Move getLastMoveFromHistory() {
/*     */     try {
/* 328 */       Move last = getMoveBackStack().get(getMoveBackStack().size() - 1);
/* 329 */       return last;
/*     */     }
/* 331 */     catch (ArrayIndexOutOfBoundsException exc) {
/*     */       
/* 333 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized Move getNextMoveFromHistory() {
/*     */     try {
/* 341 */       Move next = this.moveForwardStack.get(this.moveForwardStack.size() - 1);
/* 342 */       return next;
/*     */     }
/* 344 */     catch (ArrayIndexOutOfBoundsException exc) {
/*     */       
/* 346 */       LOG.error("ArrayIndexOutOfBoundsException: ", exc);
/* 347 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized Move undo() {
/*     */     try {
/* 356 */       Move last = getMoveBackStack().pop();
/* 357 */       if (last != null) {
/*     */         
/* 359 */         if (this.game.getSettings().getGameType() == GameTypes.LOCAL)
/*     */         {
/* 361 */           this.moveForwardStack.push(last);
/*     */         }
/* 363 */         if (this.enterBlack) {
/*     */           
/* 365 */           this.tableModel.setValueAt("", this.tableModel.getRowCount() - 1, 0);
/* 366 */           this.tableModel.removeRow(this.tableModel.getRowCount() - 1);
/*     */           
/* 368 */           if (this.rowsNum > 0)
/*     */           {
/* 370 */             this.rowsNum--;
/*     */           
/*     */           }
/*     */         
/*     */         }
/* 375 */         else if (this.tableModel.getRowCount() > 0) {
/*     */           
/* 377 */           this.tableModel.setValueAt("", this.tableModel.getRowCount() - 1, 1);
/*     */         } 
/*     */         
/* 380 */         this.moves.remove(this.moves.size() - 1);
/* 381 */         this.enterBlack = !this.enterBlack;
/*     */       } 
/* 383 */       return last;
/*     */     }
/* 385 */     catch (EmptyStackException exc) {
/*     */       
/* 387 */       LOG.error("EmptyStackException: ", exc);
/* 388 */       this.enterBlack = false;
/* 389 */       return null;
/*     */     }
/* 391 */     catch (ArrayIndexOutOfBoundsException exc) {
/*     */       
/* 393 */       LOG.error("ArrayIndexOutOfBoundsException: ", exc);
/* 394 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized Move redo() {
/*     */     try {
/* 402 */       if (this.game.getSettings().getGameType() == GameTypes.LOCAL) {
/*     */         
/* 404 */         Move first = this.moveForwardStack.pop();
/* 405 */         getMoveBackStack().push(first);
/*     */         
/* 407 */         return first;
/*     */       } 
/* 409 */       return null;
/*     */     }
/* 411 */     catch (EmptyStackException exc) {
/*     */       
/* 413 */       LOG.error("redo: EmptyStackException: ", exc);
/* 414 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isMoveCorrect(String move) {
/* 424 */     if (move.equals(Castling.SHORT_CASTLING.getSymbol()) || move.equals(Castling.LONG_CASTLING.getSymbol()))
/*     */     {
/* 426 */       return true;
/*     */     }
/*     */     
/*     */     try {
/* 430 */       int from = 0;
/* 431 */       int sign = move.charAt(from);
/* 432 */       switch (sign) {
/*     */         
/*     */         case 66:
/*     */         case 75:
/*     */         case 78:
/*     */         case 81:
/*     */         case 82:
/* 439 */           from = 1;
/*     */           break;
/*     */       } 
/* 442 */       sign = move.charAt(from);
/* 443 */       LOG.debug("isMoveCorrect/sign: " + sign);
/* 444 */       if (sign < 97 || sign > 104)
/*     */       {
/* 446 */         return false;
/*     */       }
/* 448 */       sign = move.charAt(from + 1);
/* 449 */       if (sign < 49 || sign > 56)
/*     */       {
/* 451 */         return false;
/*     */       }
/* 453 */       if (move.length() > 3)
/*     */       {
/* 455 */         sign = move.charAt(from + 2);
/* 456 */         if (sign != 45 && sign != 120)
/*     */         {
/* 458 */           return false;
/*     */         }
/* 460 */         sign = move.charAt(from + 3);
/* 461 */         if (sign < 97 || sign > 104)
/*     */         {
/* 463 */           return false;
/*     */         }
/* 465 */         sign = move.charAt(from + 4);
/* 466 */         if (sign < 49 || sign > 56)
/*     */         {
/* 468 */           return false;
/*     */         }
/*     */       }
/*     */     
/* 472 */     } catch (StringIndexOutOfBoundsException exc) {
/*     */       
/* 474 */       LOG.error("isMoveCorrect/StringIndexOutOfBoundsException: ", exc);
/* 475 */       return false;
/*     */     } 
/*     */     
/* 478 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addMoves(ArrayList<String> list) {
/* 483 */     for (String singleMove : list) {
/*     */       
/* 485 */       if (isMoveCorrect(singleMove))
/*     */       {
/* 487 */         addMove(singleMove);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMovesInString() {
/* 497 */     int n = 1;
/* 498 */     int i = 0;
/* 499 */     String str = new String();
/* 500 */     for (String locMove : getMoves()) {
/*     */       
/* 502 */       if (i % 2 == 0) {
/*     */         
/* 504 */         str = str + n + ". ";
/* 505 */         n++;
/*     */       } 
/* 507 */       str = str + locMove + " ";
/* 508 */       i++;
/*     */     } 
/* 510 */     return str;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMoves(String moves) throws ReadGameError {
/* 519 */     int from = 0;
/* 520 */     int to = 0;
/* 521 */     int n = 1;
/* 522 */     String currentMove = "";
/* 523 */     ArrayList<String> tempArray = new ArrayList<>();
/* 524 */     int tempStrSize = moves.length() - 1;
/*     */     
/*     */     do {
/* 527 */       from = moves.indexOf(" ", from);
/* 528 */       to = moves.indexOf(" ", from + 1);
/* 529 */       if (0 > from || 0 > to) {
/*     */         break;
/*     */       }
/*     */ 
/*     */       
/*     */       try {
/* 535 */         currentMove = moves.substring(from + 1, to).trim();
/* 536 */         tempArray.add(currentMove);
/* 537 */         LOG.debug(String.format("Processed following move in PGN: %s", new Object[] { currentMove }));
/*     */       }
/* 539 */       catch (StringIndexOutOfBoundsException exc) {
/*     */         
/* 541 */         LOG.error("setMoves/StringIndexOutOfBoundsException: error parsing file to load: ", exc);
/*     */         break;
/*     */       } 
/* 544 */       if (n % 2 == 0) {
/*     */         
/* 546 */         from = moves.indexOf(".", to);
/* 547 */         if (from < to)
/*     */         {
/*     */           break;
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 554 */         from = to;
/*     */       } 
/* 556 */       n++;
/* 557 */     } while (from <= tempStrSize && to <= tempStrSize);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 562 */     for (String locMove : tempArray) {
/*     */       
/* 564 */       if (!isMoveCorrect(locMove.trim()))
/*     */       {
/* 566 */         throw new ReadGameError(
/* 567 */             String.format(Settings.lang("invalid_file_to_load"), new Object[] { locMove }), locMove);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 572 */     boolean canMove = false;
/* 573 */     for (String locMove : tempArray) {
/*     */       
/* 575 */       if (Castling.isCastling(locMove)) {
/*     */         
/* 577 */         int[] values = new int[4];
/* 578 */         Colors color = this.game.getActivePlayer().getColor();
/* 579 */         if (locMove.equals(Castling.LONG_CASTLING.getSymbol())) {
/*     */           
/* 581 */           values = Castling.LONG_CASTLING.getMove(color);
/*     */         }
/* 583 */         else if (locMove.equals(Castling.SHORT_CASTLING.getSymbol())) {
/*     */           
/* 585 */           values = Castling.SHORT_CASTLING.getMove(color);
/*     */         } 
/* 587 */         canMove = this.game.simulateMove(values[0], values[1], values[2], values[3], null);
/*     */         
/* 589 */         if (!canMove)
/*     */         {
/* 591 */           throw new ReadGameError(
/* 592 */               String.format(Settings.lang("illegal_move_on"), new Object[] { locMove }), locMove);
/*     */         }
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 598 */       from = 0;
/* 599 */       int num = locMove.charAt(from);
/* 600 */       if (num <= 90 && num >= 65)
/*     */       {
/* 602 */         from = 1;
/*     */       }
/* 604 */       int xFrom = 9;
/* 605 */       int yFrom = 9;
/* 606 */       int xTo = 9;
/* 607 */       int yTo = 9;
/* 608 */       boolean pieceFound = false;
/* 609 */       if (locMove.length() <= 3) {
/*     */         
/* 611 */         Square[][] squares = this.game.getChessboard().getSquares();
/* 612 */         xTo = locMove.charAt(from) - 97;
/* 613 */         yTo = Chessboard.getBottom() - locMove.charAt(from + 1) - 49;
/* 614 */         for (int i = 0; i < squares.length && !pieceFound; i++) {
/*     */           
/* 616 */           for (int j = 0; j < (squares[i]).length && !pieceFound; j++) {
/*     */             
/* 618 */             if ((squares[i][j]).piece != null && this.game
/* 619 */               .getActivePlayer().getColor() == squares[i][j].getPiece().getPlayer().getColor()) {
/*     */ 
/*     */ 
/*     */               
/* 623 */               Set<Square> pieceMoves = squares[i][j].getPiece().getAllMoves();
/* 624 */               for (Square square : pieceMoves) {
/*     */                 
/* 626 */                 Square currSquare = square;
/* 627 */                 if (currSquare.getPozX() == xTo && currSquare.getPozY() == yTo) {
/*     */                   
/* 629 */                   xFrom = squares[i][j].getPiece().getSquare().getPozX();
/* 630 */                   yFrom = squares[i][j].getPiece().getSquare().getPozY();
/* 631 */                   pieceFound = true;
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } else {
/*     */         
/* 639 */         xFrom = locMove.charAt(from) - 97;
/* 640 */         yFrom = Chessboard.getBottom() - locMove.charAt(from + 1) - 49;
/* 641 */         xTo = locMove.charAt(from + 3) - 97;
/* 642 */         yTo = Chessboard.getBottom() - locMove.charAt(from + 4) - 49;
/*     */       } 
/* 644 */       canMove = this.game.simulateMove(xFrom, yFrom, xTo, yTo, null);
/* 645 */       if (!canMove) {
/*     */         
/* 647 */         this.game.getChessboard().resetActiveSquare();
/* 648 */         throw new ReadGameError(
/* 649 */             String.format(Settings.lang("illegal_move_on"), new Object[] { locMove }), locMove);
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
/* 661 */   public Stack<Move> getMoveBackStack() { return this.moveBackStack; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 666 */   public void decrementFiftyMoveRule() { this.fiftyMoveRuleCounter--; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void incrementFiftyMoveRule(Move move) {
/* 671 */     if (!(move.getMovedPiece() instanceof Pawn) && null == move.getTakenPiece())
/*     */     {
/* 673 */       this.fiftyMoveRuleCounter++;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 682 */   public int getFiftyMoveRuleCounter() { return this.fiftyMoveRuleCounter; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 690 */   public void setFiftyMoveRuleCounter(int fiftyMoveRuleCounter) { this.fiftyMoveRuleCounter = fiftyMoveRuleCounter; }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\core\moves\MovesHistory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */