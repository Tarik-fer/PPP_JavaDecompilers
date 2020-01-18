// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.core;

import java.awt.event.ComponentEvent;
import pl.art.lach.mateusz.javaopenchess.core.moves.Move;
import pl.art.lach.mateusz.javaopenchess.display.windows.JChessTabbedPane;
import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Pawn;
import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.King;
import pl.art.lach.mateusz.javaopenchess.utils.GameTypes;
import java.awt.event.MouseEvent;
import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
import pl.art.lach.mateusz.javaopenchess.core.pieces.PieceFactory;
import pl.art.lach.mateusz.javaopenchess.JChessView;
import pl.art.lach.mateusz.javaopenchess.JChessApp;
import pl.art.lach.mateusz.javaopenchess.core.players.PlayerType;
import pl.art.lach.mateusz.javaopenchess.core.exceptions.ReadGameError;
import pl.art.lach.mateusz.javaopenchess.core.data_transfer.DataImporter;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.File;
import javax.swing.JScrollPane;
import pl.art.lach.mateusz.javaopenchess.display.views.chessboard.ChessboardView;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.LayoutManager;
import pl.art.lach.mateusz.javaopenchess.core.data_transfer.DataTransferFactory;
import pl.art.lach.mateusz.javaopenchess.core.data_transfer.TransferFormat;
import pl.art.lach.mateusz.javaopenchess.core.data_transfer.DataExporter;
import pl.art.lach.mateusz.javaopenchess.core.ai.AI;
import pl.art.lach.mateusz.javaopenchess.display.panels.LocalSettingsView;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import pl.art.lach.mateusz.javaopenchess.network.Chat;
import pl.art.lach.mateusz.javaopenchess.core.moves.MovesHistory;
import pl.art.lach.mateusz.javaopenchess.network.Client;
import pl.art.lach.mateusz.javaopenchess.core.players.Player;
import pl.art.lach.mateusz.javaopenchess.utils.Settings;
import org.apache.log4j.Logger;
import java.awt.event.MouseListener;
import java.awt.event.ComponentListener;
import javax.swing.JPanel;

public class Game extends JPanel implements ComponentListener, MouseListener
{
    private static final Logger LOG;
    protected Settings settings;
    private boolean blockedChessboard;
    protected Chessboard chessboard;
    protected Player activePlayer;
    protected GameClock gameClock;
    protected Client client;
    protected MovesHistory moves;
    protected Chat chat;
    protected JTabbedPane tabPane;
    protected JTextField fenState;
    protected LocalSettingsView localSettingsView;
    private AI ai;
    private boolean isEndOfGame;
    private static final String FEN_PREFIX_NAME = "FEN: ";
    private DataExporter fenExporter;
    
    public Game() {
        this.ai = null;
        this.isEndOfGame = false;
        this.fenExporter = DataTransferFactory.getExporterInstance(TransferFormat.FEN);
        this.init();
    }
    
    protected final void init() {
        this.setLayout(null);
        this.moves = new MovesHistory(this);
        this.settings = new Settings();
        this.chessboard = new Chessboard(this.getSettings(), this.moves);
        final ChessboardView chessboardView = this.chessboard.getChessboardView();
        final int chessboardWidth = chessboardView.getChessboardWidht(true);
        this.add(chessboardView);
        (this.gameClock = new GameClock(this)).setSize(new Dimension(200, 100));
        this.gameClock.setLocation(new Point(500, 0));
        this.add(this.gameClock);
        final JScrollPane movesHistory = this.moves.getScrollPane();
        movesHistory.setSize(new Dimension(180, 350));
        movesHistory.setLocation(new Point(500, 121));
        this.add(movesHistory);
        this.chat = new Chat();
        this.tabPane = new JTabbedPane();
        this.localSettingsView = new LocalSettingsView(this);
        this.tabPane.addTab(Settings.lang("game_chat"), this.chat);
        this.tabPane.addTab(Settings.lang("game_settings"), this.localSettingsView);
        this.tabPane.setSize(new Dimension(380, 100));
        this.tabPane.setLocation(new Point(chessboardWidth, chessboardWidth / 2));
        this.tabPane.setMinimumSize(new Dimension(400, 100));
        this.add(this.tabPane);
        (this.fenState = new JTextField()).setEditable(false);
        this.fenState.setSize(new Dimension(chessboardWidth + 180, 20));
        this.fenState.setLocation(new Point(0, 500));
        this.add(this.fenState);
        this.setBlockedChessboard(false);
        this.setLayout(null);
        this.setDoubleBuffered(true);
        chessboardView.addMouseListener(this);
        this.addComponentListener(this);
    }
    
    public final void updateFenStateText() {
        this.fenState.setText("FEN: " + this.exportGame(this.fenExporter));
    }
    
    public String saveGame(final File path, final DataExporter dataExporter) {
        final File file = path;
        FileWriter fileW = null;
        String str = null;
        try {
            fileW = new FileWriter(file);
            str = this.exportGame(dataExporter);
            fileW.write(str);
            fileW.flush();
            fileW.close();
            JOptionPane.showMessageDialog(this, Settings.lang("game_saved_properly"));
        }
        catch (IOException exc) {
            Game.LOG.error("error writing to file: ", exc);
            JOptionPane.showMessageDialog(this, Settings.lang("error_writing_to_file") + ": " + exc);
            return null;
        }
        return str;
    }
    
    public String exportGame(final DataExporter de) {
        if (null == de) {
            return null;
        }
        return de.exportData(this);
    }
    
    public void importGame(final String dataInString, final DataImporter di) throws ReadGameError {
        if (null == di) {
            return;
        }
        di.importData(dataInString, this);
    }
    
    public void newGame() {
        this.getChessboard().setPieces4NewGame(this.getSettings().getPlayerWhite(), this.getSettings().getPlayerBlack());
        this.activePlayer = this.getSettings().getPlayerWhite();
        if (this.activePlayer.getPlayerType() != PlayerType.LOCAL_USER) {
            this.setBlockedChessboard(true);
        }
        this.runRenderingArtifactDirtyFix();
        this.updateFenStateText();
    }
    
    private void runRenderingArtifactDirtyFix() throws ArrayIndexOutOfBoundsException {
        final JChessView jChessView = JChessApp.getJavaChessView();
        if (null != jChessView) {
            final Game activeGame = JChessApp.getJavaChessView().getActiveTabGame();
            if (null != activeGame) {
                final Chessboard activeChessboard = activeGame.getChessboard();
                final ChessboardView chessboardView = activeChessboard.getChessboardView();
                if (JChessApp.getJavaChessView().getNumberOfOpenedTabs() == 0) {
                    chessboardView.resizeChessboard(chessboardView.getChessboardHeight(false));
                    activeChessboard.repaint();
                    activeGame.repaint();
                }
            }
            this.chessboard.repaint();
            this.repaint();
        }
    }
    
    public void endGame(final String message) {
        this.setBlockedChessboard(true);
        this.isEndOfGame = true;
        Game.LOG.debug(message);
        JOptionPane.showMessageDialog(null, message);
    }
    
    public void switchActive() {
        if (this.activePlayer == this.getSettings().getPlayerWhite()) {
            this.activePlayer = this.getSettings().getPlayerBlack();
        }
        else {
            this.activePlayer = this.getSettings().getPlayerWhite();
        }
        this.getGameClock().switchClocks();
    }
    
    public Player getActivePlayer() {
        return this.activePlayer;
    }
    
    public void setActivePlayer(final Player player) {
        this.activePlayer = player;
    }
    
    public void nextMove() {
        this.switchActive();
        Game.LOG.debug(String.format("next move, active player: %s, color: %s, type: %s", this.activePlayer.getName(), this.activePlayer.getColor().name(), this.activePlayer.getPlayerType().name()));
        if (this.activePlayer.getPlayerType() == PlayerType.LOCAL_USER) {
            this.setBlockedChessboard(false);
        }
        else if (this.activePlayer.getPlayerType() == PlayerType.NETWORK_USER || this.activePlayer.getPlayerType() == PlayerType.COMPUTER) {
            this.setBlockedChessboard(true);
        }
        this.updateFenStateText();
    }
    
    public boolean simulateMove(final int beginX, final int beginY, final int endX, final int endY, final String promoted) {
        try {
            final Square begin = this.getChessboard().getSquare(beginX, beginY);
            final Square end = this.getChessboard().getSquare(endX, endY);
            this.getChessboard().select(begin);
            if (!this.getChessboard().getActiveSquare().getPiece().getAllMoves().contains(end)) {
                Game.LOG.debug(String.format("Bad move: beginX: %s beginY: %s endX: %s endY: %s", beginX, beginY, endX, endY));
                return false;
            }
            this.getChessboard().move(begin, end);
            if (null != promoted && !"".equals(promoted)) {
                final Piece promotedPiece = PieceFactory.getPiece(this.getChessboard(), end.getPiece().getPlayer().getColor(), promoted, this.activePlayer);
                end.setPiece(promotedPiece);
            }
            try {
                this.getChessboard().unselect();
                this.nextMove();
                return true;
            }
            catch (ArrayIndexOutOfBoundsException exc) {
                Game.LOG.error("simulateMove error: ", exc);
                return false;
            }
        }
        catch (StringIndexOutOfBoundsException ex) {}
        catch (ArrayIndexOutOfBoundsException ex2) {}
        catch (NullPointerException ex3) {}
    }
    
    @Override
    public void mouseClicked(final MouseEvent arg0) {
    }
    
    public boolean undo() {
        boolean status = false;
        if (this.getSettings().getGameType() != GameTypes.NETWORK) {
            status = this.getChessboard().undo();
            if (status) {
                this.switchActive();
            }
            else {
                this.getChessboard().repaint();
            }
            if (this.getSettings().isGameAgainstComputer() && this.getActivePlayer().getPlayerType() == PlayerType.COMPUTER) {
                this.undo();
            }
        }
        else if (this.getSettings().getGameType() == GameTypes.NETWORK) {
            this.getClient().sendUndoAsk();
            status = true;
        }
        this.updateFenStateText();
        return status;
    }
    
    public boolean rewindToBegin() {
        boolean result = false;
        if (this.getSettings().getGameType() == GameTypes.LOCAL) {
            while (this.getChessboard().undo()) {
                result = true;
            }
            this.updateFenStateText();
            return result;
        }
        throw new UnsupportedOperationException(Settings.lang("operation_supported_only_in_local_game"));
    }
    
    public boolean rewindToEnd() throws UnsupportedOperationException {
        boolean result = false;
        if (this.getSettings().getGameType() == GameTypes.LOCAL) {
            while (this.getChessboard().redo()) {
                result = true;
            }
            this.updateFenStateText();
            return result;
        }
        throw new UnsupportedOperationException(Settings.lang("operation_supported_only_in_local_game"));
    }
    
    public boolean redo() {
        final boolean status = this.getChessboard().redo();
        if (this.getSettings().getGameType() == GameTypes.LOCAL) {
            if (status) {
                this.nextMove();
            }
            else {
                this.getChessboard().repaint();
            }
            this.updateFenStateText();
            return status;
        }
        throw new UnsupportedOperationException(Settings.lang("operation_supported_only_in_local_game"));
    }
    
    @Override
    public void mousePressed(final MouseEvent event) {
        if (event.getButton() == 3) {
            this.undo();
        }
        else if (event.getButton() == 2 && this.getSettings().getGameType() == GameTypes.LOCAL) {
            this.redo();
        }
        else if (event.getButton() == 1) {
            if (!this.isChessboardBlocked()) {
                this.moveActionInvoked(event);
            }
            else {
                Game.LOG.debug("Chessboard is blocked");
            }
        }
        this.updateFenStateText();
    }
    
    private void moveActionInvoked(final MouseEvent event) throws ArrayIndexOutOfBoundsException {
        try {
            final int x = event.getX();
            final int y = event.getY();
            final Square sq = this.getChessboard().getChessboardView().getSquare(x, y);
            if (this.cannotInvokeMoveAction(sq)) {
                return;
            }
            if (this.isSelectAction(sq)) {
                this.selectSquare(sq);
            }
            else if (this.getChessboard().getActiveSquare() == sq) {
                this.getChessboard().unselect();
            }
            else if (this.canInvokeMoveAction(sq)) {
                if (this.getSettings().getGameType() == GameTypes.LOCAL) {
                    this.getChessboard().move(this.getChessboard().getActiveSquare(), sq);
                }
                else if (this.getSettings().getGameType() == GameTypes.NETWORK) {
                    this.moveNetworkActionInvoked(sq);
                }
                this.getChessboard().unselect();
                this.nextMove();
                King king;
                if (this.activePlayer == this.getSettings().getPlayerWhite()) {
                    king = this.getChessboard().getKingWhite();
                }
                else {
                    king = this.getChessboard().getKingBlack();
                }
                switch (king.isCheckmatedOrStalemated()) {
                    case 1: {
                        this.endGame("Checkmate! " + king.getPlayer().getColor().toString() + " player lose!");
                        break;
                    }
                    case 2: {
                        this.endGame("Stalemate! Draw!");
                        break;
                    }
                }
            }
            if (this.canDoComputerMove()) {
                this.doComputerMove();
                this.highlighTabIfInactive();
            }
        }
        catch (NullPointerException exc) {
            Game.LOG.error("NullPointerException: " + exc.getMessage(), exc);
            this.getChessboard().repaint();
        }
        this.updateFenStateText();
    }
    
    private boolean canInvokeMoveAction(final Square sq) {
        return this.getChessboard().getActiveSquare() != null && this.getChessboard().getActiveSquare().piece != null && this.getChessboard().getActiveSquare().getPiece().getAllMoves().contains(sq);
    }
    
    private void selectSquare(final Square sq) {
        this.getChessboard().unselect();
        this.getChessboard().select(sq);
    }
    
    private boolean isSelectAction(final Square sq) {
        return sq.piece != null && sq.getPiece().getPlayer() == this.activePlayer && sq != this.getChessboard().getActiveSquare();
    }
    
    private boolean cannotInvokeMoveAction(final Square sq) {
        return (sq == null && sq.piece == null && this.getChessboard().getActiveSquare() == null) || (this.getChessboard().getActiveSquare() == null && sq.piece != null && sq.getPiece().getPlayer() != this.activePlayer);
    }
    
    private void moveNetworkActionInvoked(final Square sq) {
        final Square from = this.getChessboard().getActiveSquare();
        final boolean canBePromoted = Pawn.class == from.getPiece().getClass() && Pawn.canBePromoted(sq);
        this.getChessboard().move(from, sq);
        Piece promoted = null;
        if (canBePromoted) {
            promoted = sq.getPiece();
        }
        this.getClient().sendMove(from.getPozX(), from.getPozY(), sq.getPozX(), sq.getPozY(), (null == promoted) ? "" : promoted.getName());
    }
    
    private void highlighTabIfInactive() throws ArrayIndexOutOfBoundsException {
        final JChessView jChessView = JChessApp.getJavaChessView();
        final int tabNumber = jChessView.getTabNumber(this);
        if (jChessView.getActiveTabGame() != this) {
            jChessView.getGamesPane().setForegroundAt(tabNumber, JChessTabbedPane.EVENT_COLOR);
        }
    }
    
    private boolean canDoComputerMove() {
        return !this.isEndOfGame && this.getSettings().isGameAgainstComputer() && this.getActivePlayer().getPlayerType() == PlayerType.COMPUTER && null != this.getAi();
    }
    
    public void doComputerMove() {
        final Move move = this.getAi().getMove(this, this.getMoves().getLastMoveFromHistory());
        this.getChessboard().move(move.getFrom(), move.getTo());
        if (null != move.getPromotedPiece()) {
            move.getTo().setPiece(move.getPromotedPiece());
        }
        this.nextMove();
    }
    
    @Override
    public void mouseReleased(final MouseEvent arg0) {
    }
    
    @Override
    public void mouseEntered(final MouseEvent arg0) {
    }
    
    @Override
    public void mouseExited(final MouseEvent arg0) {
    }
    
    @Override
    public void componentResized(final ComponentEvent e) {
        this.resizeGame();
    }
    
    @Override
    public void componentMoved(final ComponentEvent e) {
        this.componentResized(e);
        this.repaint();
    }
    
    @Override
    public void componentShown(final ComponentEvent e) {
        this.componentResized(e);
    }
    
    @Override
    public void componentHidden(final ComponentEvent e) {
    }
    
    public Chessboard getChessboard() {
        return this.chessboard;
    }
    
    public final Settings getSettings() {
        return this.settings;
    }
    
    public boolean isChessboardBlocked() {
        return this.isBlockedChessboard();
    }
    
    public GameClock getGameClock() {
        return this.gameClock;
    }
    
    public Client getClient() {
        return this.client;
    }
    
    public MovesHistory getMoves() {
        return this.moves;
    }
    
    public Chat getChat() {
        return this.chat;
    }
    
    public void setSettings(final Settings settings) {
        this.settings = settings;
        this.chessboard.setSettings(settings);
    }
    
    public void setClient(final Client client) {
        this.client = client;
    }
    
    public void setChat(final Chat chat) {
        this.chat = chat;
    }
    
    @Override
    public void repaint() {
        super.repaint();
        if (null != this.tabPane) {
            this.tabPane.repaint();
        }
        if (null != this.localSettingsView) {
            this.localSettingsView.repaint();
        }
        if (null != this.chessboard) {
            this.getChessboard().repaint();
        }
    }
    
    public void resizeGame() {
        final int height = (this.getHeight() >= this.getWidth()) ? this.getWidth() : this.getHeight();
        int chessHeight = (int)(Math.round(height * 0.8 / 8.0) * 8L);
        final JScrollPane movesScrollPane = this.getMoves().getScrollPane();
        final ChessboardView chessboardView = this.getChessboard().getChessboardView();
        chessboardView.resizeChessboard(chessHeight);
        chessHeight = chessboardView.getHeight();
        final int chessWidthWithLabels = chessboardView.getChessboardWidht(true);
        movesScrollPane.setLocation(new Point(chessHeight + 5, 100));
        movesScrollPane.setSize(movesScrollPane.getWidth(), chessHeight - 100 - chessWidthWithLabels / 4);
        this.fenState.setLocation(new Point(0, chessHeight + 5));
        this.fenState.setSize(new Dimension(chessWidthWithLabels + 180, 30));
        this.getGameClock().setLocation(new Point(chessHeight + 5, 0));
        if (null != this.tabPane) {
            this.tabPane.setLocation(new Point(chessWidthWithLabels + 5, chessWidthWithLabels / 4 * 3));
            this.tabPane.setSize(new Dimension(movesScrollPane.getWidth(), chessWidthWithLabels / 4));
            this.tabPane.repaint();
        }
    }
    
    public AI getAi() {
        return this.ai;
    }
    
    public void setAi(final AI ai) {
        this.ai = ai;
    }
    
    public boolean isIsEndOfGame() {
        return this.isEndOfGame;
    }
    
    public void setIsEndOfGame(final boolean isEndOfGame) {
        this.isEndOfGame = isEndOfGame;
    }
    
    public boolean isBlockedChessboard() {
        return this.blockedChessboard;
    }
    
    public void setBlockedChessboard(final boolean blockedChessboard) {
        this.blockedChessboard = blockedChessboard;
    }
    
    public JTextField getFenState() {
        return this.fenState;
    }
    
    public void setFenState(final JTextField fenState) {
        this.fenState = fenState;
    }
    
    static {
        LOG = Logger.getLogger(Game.class);
    }
}
