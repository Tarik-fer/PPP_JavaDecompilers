/*     */ package pl.art.lach.mateusz.javaopenchess.core;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Point;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.ComponentListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.io.File;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTabbedPane;
/*     */ import javax.swing.JTextField;
/*     */ import org.apache.log4j.Logger;
/*     */ import pl.art.lach.mateusz.javaopenchess.JChessApp;
/*     */ import pl.art.lach.mateusz.javaopenchess.JChessView;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.ai.AI;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.data_transfer.DataExporter;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.data_transfer.DataImporter;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.data_transfer.DataTransferFactory;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.data_transfer.TransferFormat;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.exceptions.ReadGameError;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.moves.Move;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.moves.MovesHistory;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.PieceFactory;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.King;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Pawn;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.players.Player;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.players.PlayerType;
/*     */ import pl.art.lach.mateusz.javaopenchess.display.panels.LocalSettingsView;
/*     */ import pl.art.lach.mateusz.javaopenchess.display.views.chessboard.ChessboardView;
/*     */ import pl.art.lach.mateusz.javaopenchess.display.windows.JChessTabbedPane;
/*     */ import pl.art.lach.mateusz.javaopenchess.network.Chat;
/*     */ import pl.art.lach.mateusz.javaopenchess.network.Client;
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
/*     */ public class Game
/*     */   extends JPanel
/*     */   implements ComponentListener, MouseListener
/*     */ {
/*  65 */   private static final Logger LOG = Logger.getLogger(Game.class);
/*     */ 
/*     */ 
/*     */   
/*     */   protected Settings settings;
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean blockedChessboard;
/*     */ 
/*     */ 
/*     */   
/*     */   protected Chessboard chessboard;
/*     */ 
/*     */ 
/*     */   
/*     */   protected Player activePlayer;
/*     */ 
/*     */ 
/*     */   
/*     */   protected GameClock gameClock;
/*     */ 
/*     */ 
/*     */   
/*     */   protected Client client;
/*     */ 
/*     */ 
/*     */   
/*     */   protected MovesHistory moves;
/*     */ 
/*     */ 
/*     */   
/*     */   protected Chat chat;
/*     */ 
/*     */ 
/*     */   
/*     */   protected JTabbedPane tabPane;
/*     */ 
/*     */ 
/*     */   
/*     */   protected JTextField fenState;
/*     */ 
/*     */ 
/*     */   
/*     */   protected LocalSettingsView localSettingsView;
/*     */ 
/*     */ 
/*     */   
/* 113 */   private AI ai = null;
/*     */   
/*     */   private boolean isEndOfGame = false;
/*     */   
/*     */   private static final String FEN_PREFIX_NAME = "FEN: ";
/*     */   
/* 119 */   private DataExporter fenExporter = DataTransferFactory.getExporterInstance(TransferFormat.FEN);
/*     */ 
/*     */ 
/*     */   
/* 123 */   public Game() { init(); }
/*     */ 
/*     */ 
/*     */   
/*     */   protected final void init() {
/* 128 */     setLayout(null);
/* 129 */     this.moves = new MovesHistory(this);
/* 130 */     this.settings = new Settings();
/* 131 */     this.chessboard = new Chessboard(getSettings(), this.moves);
/*     */     
/* 133 */     ChessboardView chessboardView = this.chessboard.getChessboardView();
/* 134 */     int chessboardWidth = chessboardView.getChessboardWidht(true);
/* 135 */     add((Component)chessboardView);
/*     */ 
/*     */     
/* 138 */     this.gameClock = new GameClock(this);
/* 139 */     this.gameClock.setSize(new Dimension(200, 100));
/* 140 */     this.gameClock.setLocation(new Point(500, 0));
/* 141 */     add(this.gameClock);
/*     */     
/* 143 */     JScrollPane movesHistory = this.moves.getScrollPane();
/* 144 */     movesHistory.setSize(new Dimension(180, 350));
/* 145 */     movesHistory.setLocation(new Point(500, 121));
/* 146 */     add(movesHistory);
/*     */     
/* 148 */     this.chat = new Chat();
/* 149 */     this.tabPane = new JTabbedPane();
/* 150 */     this.localSettingsView = new LocalSettingsView(this);
/* 151 */     this.tabPane.addTab(Settings.lang("game_chat"), (Component)this.chat);
/* 152 */     this.tabPane.addTab(Settings.lang("game_settings"), (Component)this.localSettingsView);
/* 153 */     this.tabPane.setSize(new Dimension(380, 100));
/* 154 */     this.tabPane.setLocation(new Point(chessboardWidth, chessboardWidth / 2));
/* 155 */     this.tabPane.setMinimumSize(new Dimension(400, 100));
/* 156 */     add(this.tabPane);
/*     */     
/* 158 */     this.fenState = new JTextField();
/* 159 */     this.fenState.setEditable(false);
/* 160 */     this.fenState.setSize(new Dimension(chessboardWidth + 180, 20));
/* 161 */     this.fenState.setLocation(new Point(0, 500));
/* 162 */     add(this.fenState);
/*     */     
/* 164 */     setBlockedChessboard(false);
/* 165 */     setLayout(null);
/* 166 */     setDoubleBuffered(true);
/* 167 */     chessboardView.addMouseListener(this);
/* 168 */     addComponentListener(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 173 */   public final void updateFenStateText() { this.fenState.setText("FEN: " + exportGame(this.fenExporter)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String saveGame(File path, DataExporter dataExporter) {
/* 184 */     File file = path;
/* 185 */     FileWriter fileW = null;
/* 186 */     String str = null;
/*     */     
/*     */     try {
/* 189 */       fileW = new FileWriter(file);
/* 190 */       str = exportGame(dataExporter);
/* 191 */       fileW.write(str);
/* 192 */       fileW.flush();
/* 193 */       fileW.close();
/* 194 */       JOptionPane.showMessageDialog(this, Settings.lang("game_saved_properly"));
/*     */     }
/* 196 */     catch (IOException exc) {
/*     */       
/* 198 */       LOG.error("error writing to file: ", exc);
/* 199 */       JOptionPane.showMessageDialog(this, Settings.lang("error_writing_to_file") + ": " + exc);
/* 200 */       return null;
/*     */     } 
/* 202 */     return str;
/*     */   }
/*     */ 
/*     */   
/*     */   public String exportGame(DataExporter de) {
/* 207 */     if (null == de)
/*     */     {
/* 209 */       return null;
/*     */     }
/* 211 */     return de.exportData(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void importGame(String dataInString, DataImporter di) throws ReadGameError {
/* 216 */     if (null == di) {
/*     */       return;
/*     */     }
/*     */     
/* 220 */     di.importData(dataInString, this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void newGame() {
/* 228 */     getChessboard().setPieces4NewGame(
/* 229 */         getSettings().getPlayerWhite(), 
/* 230 */         getSettings().getPlayerBlack());
/*     */ 
/*     */     
/* 233 */     this.activePlayer = getSettings().getPlayerWhite();
/* 234 */     if (this.activePlayer.getPlayerType() != PlayerType.LOCAL_USER)
/*     */     {
/* 236 */       setBlockedChessboard(true);
/*     */     }
/* 238 */     runRenderingArtifactDirtyFix();
/* 239 */     updateFenStateText();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void runRenderingArtifactDirtyFix() throws ArrayIndexOutOfBoundsException {
/* 246 */     JChessView jChessView = JChessApp.getJavaChessView();
/* 247 */     if (null != jChessView) {
/*     */       
/* 249 */       Game activeGame = JChessApp.getJavaChessView().getActiveTabGame();
/* 250 */       if (null != activeGame) {
/* 251 */         Chessboard activeChessboard = activeGame.getChessboard();
/* 252 */         ChessboardView chessboardView = activeChessboard.getChessboardView();
/* 253 */         if (JChessApp.getJavaChessView().getNumberOfOpenedTabs() == 0) {
/*     */           
/* 255 */           chessboardView.resizeChessboard(chessboardView.getChessboardHeight(false));
/* 256 */           activeChessboard.repaint();
/* 257 */           activeGame.repaint();
/*     */         } 
/*     */       } 
/* 260 */       this.chessboard.repaint();
/* 261 */       repaint();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endGame(String message) {
/* 271 */     setBlockedChessboard(true);
/* 272 */     this.isEndOfGame = true;
/* 273 */     LOG.debug(message);
/* 274 */     JOptionPane.showMessageDialog(null, message);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void switchActive() {
/* 281 */     if (this.activePlayer == getSettings().getPlayerWhite()) {
/*     */       
/* 283 */       this.activePlayer = getSettings().getPlayerBlack();
/*     */     }
/*     */     else {
/*     */       
/* 287 */       this.activePlayer = getSettings().getPlayerWhite();
/*     */     } 
/* 289 */     getGameClock().switchClocks();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 297 */   public Player getActivePlayer() { return this.activePlayer; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 302 */   public void setActivePlayer(Player player) { this.activePlayer = player; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void nextMove() {
/* 309 */     switchActive();
/* 310 */     LOG.debug(String.format("next move, active player: %s, color: %s, type: %s", new Object[] { this.activePlayer
/* 311 */             .getName(), this.activePlayer
/* 312 */             .getColor().name(), this.activePlayer
/* 313 */             .getPlayerType().name() }));
/*     */ 
/*     */     
/* 316 */     if (this.activePlayer.getPlayerType() == PlayerType.LOCAL_USER) {
/*     */       
/* 318 */       setBlockedChessboard(false);
/*     */     }
/* 320 */     else if (this.activePlayer.getPlayerType() == PlayerType.NETWORK_USER || this.activePlayer
/* 321 */       .getPlayerType() == PlayerType.COMPUTER) {
/*     */       
/* 323 */       setBlockedChessboard(true);
/*     */     } 
/* 325 */     updateFenStateText();
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
/*     */ 
/*     */   
/*     */   public boolean simulateMove(int beginX, int beginY, int endX, int endY, String promoted) {
/*     */     try {
/* 340 */       Square begin = getChessboard().getSquare(beginX, beginY);
/* 341 */       Square end = getChessboard().getSquare(endX, endY);
/* 342 */       getChessboard().select(begin);
/* 343 */       if (getChessboard().getActiveSquare().getPiece().getAllMoves().contains(end)) {
/*     */         
/* 345 */         getChessboard().move(begin, end);
/* 346 */         if (null != promoted && !"".equals(promoted))
/*     */         {
/* 348 */           Piece promotedPiece = PieceFactory.getPiece(
/* 349 */               getChessboard(), end
/* 350 */               .getPiece().getPlayer().getColor(), promoted, this.activePlayer);
/*     */ 
/*     */           
/* 353 */           end.setPiece(promotedPiece);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 358 */         LOG.debug(String.format("Bad move: beginX: %s beginY: %s endX: %s endY: %s", new Object[] {
/*     */                 
/* 360 */                 Integer.valueOf(beginX), Integer.valueOf(beginY), Integer.valueOf(endX), Integer.valueOf(endY)
/*     */               }));
/* 362 */         return false;
/*     */       } 
/* 364 */       getChessboard().unselect();
/* 365 */       nextMove();
/*     */       
/* 367 */       return true;
/*     */     
/*     */     }
/* 370 */     catch (StringIndexOutOfBoundsException|ArrayIndexOutOfBoundsException|NullPointerException exc) {
/*     */       
/* 372 */       LOG.error("simulateMove error: ", exc);
/* 373 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseClicked(MouseEvent arg0) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean undo() {
/* 384 */     boolean status = false;
/*     */     
/* 386 */     if (getSettings().getGameType() != GameTypes.NETWORK) {
/*     */       
/* 388 */       status = getChessboard().undo();
/* 389 */       if (status) {
/*     */         
/* 391 */         switchActive();
/*     */       }
/*     */       else {
/*     */         
/* 395 */         getChessboard().repaint();
/*     */       } 
/* 397 */       if (getSettings().isGameAgainstComputer())
/*     */       {
/* 399 */         if (getActivePlayer().getPlayerType() == PlayerType.COMPUTER)
/*     */         {
/* 401 */           undo();
/*     */         }
/*     */       }
/*     */     }
/* 405 */     else if (getSettings().getGameType() == GameTypes.NETWORK) {
/*     */       
/* 407 */       getClient().sendUndoAsk();
/* 408 */       status = true;
/*     */     } 
/* 410 */     updateFenStateText();
/* 411 */     return status;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean rewindToBegin() {
/* 416 */     boolean result = false;
/*     */     
/* 418 */     if (getSettings().getGameType() == GameTypes.LOCAL) {
/*     */       
/* 420 */       while (getChessboard().undo())
/*     */       {
/* 422 */         result = true;
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 427 */       throw new UnsupportedOperationException(
/* 428 */           Settings.lang("operation_supported_only_in_local_game"));
/*     */     } 
/*     */     
/* 431 */     updateFenStateText();
/* 432 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean rewindToEnd() throws UnsupportedOperationException {
/* 437 */     boolean result = false;
/*     */     
/* 439 */     if (getSettings().getGameType() == GameTypes.LOCAL) {
/*     */       
/* 441 */       while (getChessboard().redo())
/*     */       {
/* 443 */         result = true;
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 448 */       throw new UnsupportedOperationException(
/* 449 */           Settings.lang("operation_supported_only_in_local_game"));
/*     */     } 
/*     */     
/* 452 */     updateFenStateText();
/* 453 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean redo() {
/* 458 */     boolean status = getChessboard().redo();
/* 459 */     if (getSettings().getGameType() == GameTypes.LOCAL) {
/*     */       
/* 461 */       if (status)
/*     */       {
/* 463 */         nextMove();
/*     */       }
/*     */       else
/*     */       {
/* 467 */         getChessboard().repaint();
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 472 */       throw new UnsupportedOperationException(
/* 473 */           Settings.lang("operation_supported_only_in_local_game"));
/*     */     } 
/*     */     
/* 476 */     updateFenStateText();
/* 477 */     return status;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mousePressed(MouseEvent event) {
/* 484 */     if (event.getButton() == 3) {
/*     */       
/* 486 */       undo();
/*     */     }
/* 488 */     else if (event.getButton() == 2 && getSettings().getGameType() == GameTypes.LOCAL) {
/*     */       
/* 490 */       redo();
/*     */     }
/* 492 */     else if (event.getButton() == 1) {
/*     */       
/* 494 */       if (!isChessboardBlocked()) {
/*     */         
/* 496 */         moveActionInvoked(event);
/*     */       }
/*     */       else {
/*     */         
/* 500 */         LOG.debug("Chessboard is blocked");
/*     */       } 
/*     */     } 
/* 503 */     updateFenStateText();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void moveActionInvoked(MouseEvent event) throws ArrayIndexOutOfBoundsException {
/*     */     try {
/* 510 */       int x = event.getX();
/* 511 */       int y = event.getY();
/*     */       
/* 513 */       Square sq = getChessboard().getChessboardView().getSquare(x, y);
/* 514 */       if (cannotInvokeMoveAction(sq)) {
/*     */         return;
/*     */       }
/*     */       
/* 518 */       if (isSelectAction(sq)) {
/*     */         
/* 520 */         selectSquare(sq);
/*     */       }
/* 522 */       else if (getChessboard().getActiveSquare() == sq) {
/*     */         
/* 524 */         getChessboard().unselect();
/*     */       }
/* 526 */       else if (canInvokeMoveAction(sq)) {
/*     */         King king;
/* 528 */         if (getSettings().getGameType() == GameTypes.LOCAL) {
/*     */           
/* 530 */           getChessboard().move(getChessboard().getActiveSquare(), sq);
/*     */         }
/* 532 */         else if (getSettings().getGameType() == GameTypes.NETWORK) {
/*     */           
/* 534 */           moveNetworkActionInvoked(sq);
/*     */         } 
/* 536 */         getChessboard().unselect();
/*     */ 
/*     */         
/* 539 */         nextMove();
/*     */ 
/*     */ 
/*     */         
/* 543 */         if (this.activePlayer == getSettings().getPlayerWhite()) {
/*     */           
/* 545 */           king = getChessboard().getKingWhite();
/*     */         }
/*     */         else {
/*     */           
/* 549 */           king = getChessboard().getKingBlack();
/*     */         } 
/*     */         
/* 552 */         switch (king.isCheckmatedOrStalemated()) {
/*     */           
/*     */           case 1:
/* 555 */             endGame("Checkmate! " + king.getPlayer().getColor().toString() + " player lose!");
/*     */             break;
/*     */           case 2:
/* 558 */             endGame("Stalemate! Draw!");
/*     */             break;
/*     */         } 
/*     */       } 
/* 562 */       if (canDoComputerMove())
/*     */       {
/* 564 */         doComputerMove();
/* 565 */         highlighTabIfInactive();
/*     */       }
/*     */     
/*     */     }
/* 569 */     catch (NullPointerException exc) {
/*     */       
/* 571 */       LOG.error("NullPointerException: " + exc.getMessage(), exc);
/* 572 */       getChessboard().repaint();
/*     */     } 
/* 574 */     updateFenStateText();
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean canInvokeMoveAction(Square sq) {
/* 579 */     return (getChessboard().getActiveSquare() != null && (getChessboard().getActiveSquare()).piece != null && 
/* 580 */       getChessboard().getActiveSquare().getPiece().getAllMoves().contains(sq));
/*     */   }
/*     */ 
/*     */   
/*     */   private void selectSquare(Square sq) {
/* 585 */     getChessboard().unselect();
/* 586 */     getChessboard().select(sq);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isSelectAction(Square sq) {
/* 591 */     return (sq.piece != null && sq.getPiece().getPlayer() == this.activePlayer && sq != 
/* 592 */       getChessboard().getActiveSquare());
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean cannotInvokeMoveAction(Square sq) {
/* 597 */     return ((sq == null && sq.piece == null && getChessboard().getActiveSquare() == null) || (
/* 598 */       getChessboard().getActiveSquare() == null && sq.piece != null && sq
/* 599 */       .getPiece().getPlayer() != this.activePlayer));
/*     */   }
/*     */ 
/*     */   
/*     */   private void moveNetworkActionInvoked(Square sq) {
/* 604 */     Square from = getChessboard().getActiveSquare();
/* 605 */     boolean canBePromoted = (Pawn.class == from.getPiece().getClass() && Pawn.canBePromoted(sq));
/* 606 */     getChessboard().move(from, sq);
/* 607 */     Piece promoted = null;
/* 608 */     if (canBePromoted)
/*     */     {
/* 610 */       promoted = sq.getPiece();
/*     */     }
/* 612 */     getClient().sendMove(from
/* 613 */         .getPozX(), from
/* 614 */         .getPozY(), sq
/* 615 */         .getPozX(), sq
/* 616 */         .getPozY(), (null == promoted) ? "" : promoted
/* 617 */         .getName());
/*     */   }
/*     */ 
/*     */   
/*     */   private void highlighTabIfInactive() throws ArrayIndexOutOfBoundsException {
/* 622 */     JChessView jChessView = JChessApp.getJavaChessView();
/* 623 */     int tabNumber = jChessView.getTabNumber(this);
/* 624 */     if (jChessView.getActiveTabGame() != this)
/*     */     {
/* 626 */       jChessView.getGamesPane().setForegroundAt(tabNumber, JChessTabbedPane.EVENT_COLOR);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean canDoComputerMove() {
/* 632 */     return (!this.isEndOfGame && 
/* 633 */       getSettings().isGameAgainstComputer() && 
/* 634 */       getActivePlayer().getPlayerType() == PlayerType.COMPUTER && null != 
/* 635 */       getAi());
/*     */   }
/*     */ 
/*     */   
/*     */   public void doComputerMove() {
/* 640 */     Move move = getAi().getMove(this, getMoves().getLastMoveFromHistory());
/* 641 */     getChessboard().move(move.getFrom(), move.getTo());
/* 642 */     if (null != move.getPromotedPiece())
/*     */     {
/* 644 */       move.getTo().setPiece(move.getPromotedPiece());
/*     */     }
/* 646 */     nextMove();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseReleased(MouseEvent arg0) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseEntered(MouseEvent arg0) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseExited(MouseEvent arg0) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 667 */   public void componentResized(ComponentEvent e) { resizeGame(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentMoved(ComponentEvent e) {
/* 673 */     componentResized(e);
/* 674 */     repaint();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 680 */   public void componentShown(ComponentEvent e) { componentResized(e); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentHidden(ComponentEvent e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 693 */   public Chessboard getChessboard() { return this.chessboard; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 701 */   public final Settings getSettings() { return this.settings; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 709 */   public boolean isChessboardBlocked() { return isBlockedChessboard(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 717 */   public GameClock getGameClock() { return this.gameClock; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 725 */   public Client getClient() { return this.client; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 733 */   public MovesHistory getMoves() { return this.moves; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 741 */   public Chat getChat() { return this.chat; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSettings(Settings settings) {
/* 750 */     this.settings = settings;
/* 751 */     this.chessboard.setSettings(settings);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 759 */   public void setClient(Client client) { this.client = client; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 767 */   public void setChat(Chat chat) { this.chat = chat; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void repaint() {
/* 773 */     super.repaint();
/* 774 */     if (null != this.tabPane)
/*     */     {
/* 776 */       this.tabPane.repaint();
/*     */     }
/* 778 */     if (null != this.localSettingsView)
/*     */     {
/* 780 */       this.localSettingsView.repaint();
/*     */     }
/* 782 */     if (null != this.chessboard)
/*     */     {
/* 784 */       getChessboard().repaint();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void resizeGame() {
/* 790 */     int height = (getHeight() >= getWidth()) ? getWidth() : getHeight();
/* 791 */     int chessHeight = (int)(Math.round(height * 0.8D / 8.0D) * 8L);
/*     */     
/* 793 */     JScrollPane movesScrollPane = getMoves().getScrollPane();
/* 794 */     ChessboardView chessboardView = getChessboard().getChessboardView();
/* 795 */     chessboardView.resizeChessboard(chessHeight);
/* 796 */     chessHeight = chessboardView.getHeight();
/* 797 */     int chessWidthWithLabels = chessboardView.getChessboardWidht(true);
/* 798 */     movesScrollPane.setLocation(new Point(chessHeight + 5, 100));
/* 799 */     movesScrollPane.setSize(movesScrollPane.getWidth(), chessHeight - 100 - chessWidthWithLabels / 4);
/* 800 */     this.fenState.setLocation(new Point(0, chessHeight + 5));
/* 801 */     this.fenState.setSize(new Dimension(chessWidthWithLabels + 180, 30));
/* 802 */     getGameClock().setLocation(new Point(chessHeight + 5, 0));
/* 803 */     if (null != this.tabPane) {
/*     */       
/* 805 */       this.tabPane.setLocation(new Point(chessWidthWithLabels + 5, chessWidthWithLabels / 4 * 3));
/* 806 */       this.tabPane.setSize(new Dimension(movesScrollPane.getWidth(), chessWidthWithLabels / 4));
/* 807 */       this.tabPane.repaint();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 815 */   public AI getAi() { return this.ai; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 822 */   public void setAi(AI ai) { this.ai = ai; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 829 */   public boolean isIsEndOfGame() { return this.isEndOfGame; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 836 */   public void setIsEndOfGame(boolean isEndOfGame) { this.isEndOfGame = isEndOfGame; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 844 */   public boolean isBlockedChessboard() { return this.blockedChessboard; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 852 */   public void setBlockedChessboard(boolean blockedChessboard) { this.blockedChessboard = blockedChessboard; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 860 */   public JTextField getFenState() { return this.fenState; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 868 */   public void setFenState(JTextField fenState) { this.fenState = fenState; }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\core\Game.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */