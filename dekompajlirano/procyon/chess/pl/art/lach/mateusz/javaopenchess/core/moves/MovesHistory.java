// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.core.moves;

import java.util.Set;
import pl.art.lach.mateusz.javaopenchess.core.Colors;
import pl.art.lach.mateusz.javaopenchess.core.exceptions.ReadGameError;
import java.util.Iterator;
import java.util.EmptyStackException;
import pl.art.lach.mateusz.javaopenchess.utils.GameTypes;
import pl.art.lach.mateusz.javaopenchess.core.Chessboard;
import java.awt.Rectangle;
import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Pawn;
import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
import pl.art.lach.mateusz.javaopenchess.core.Square;
import javax.swing.event.TableModelListener;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.table.TableModel;
import pl.art.lach.mateusz.javaopenchess.utils.Settings;
import java.util.Stack;
import pl.art.lach.mateusz.javaopenchess.core.Game;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import javax.swing.table.AbstractTableModel;

public class MovesHistory extends AbstractTableModel
{
    private static final Logger LOG;
    private static final int CHAR_TINY_X_ASCII = 120;
    private static final int CHAR_HYPHEN_ASCII = 45;
    private static final int CHAR_R_ASCII = 82;
    private static final int CHAR_Q_ASCII = 81;
    private static final int CHAR_N_ASCII = 78;
    private static final int CHAR_K_ASCII = 75;
    private static final int CHAR_B_ASCII = 66;
    private static final int CHAR_TINY_H_ASCII = 104;
    public static final String SYMBOL_CHECK = "+";
    public static final String SYMBOL_CHECK_MATE = "#";
    private static final int CHAR_TINY_A_ASCII = 97;
    public static final String SYMBOL_NORMAL_MOVE = "-";
    public static final String SYMBOL_PIECE_TAKEN = "x";
    public static final String SYMBOL_EN_PASSANT = "(e.p)";
    private ArrayList<String> moves;
    private int columnsNum;
    private int rowsNum;
    private String[] names;
    private NotEditableTableModel tableModel;
    private JScrollPane scrollPane;
    private JTable table;
    private boolean enterBlack;
    private Game game;
    private Stack<Move> moveBackStack;
    protected Stack<Move> moveForwardStack;
    private int fiftyMoveRuleCounter;
    
    public MovesHistory(final Game game) {
        this.moves = new ArrayList<String>();
        this.columnsNum = 3;
        this.rowsNum = 0;
        this.names = new String[] { Settings.lang("white"), Settings.lang("black") };
        this.enterBlack = false;
        this.moveBackStack = new Stack<Move>();
        this.moveForwardStack = new Stack<Move>();
        this.fiftyMoveRuleCounter = 0;
        this.tableModel = new NotEditableTableModel();
        this.table = new JTable(this.tableModel);
        (this.scrollPane = new JScrollPane(this.table)).setMaximumSize(new Dimension(100, 100));
        this.table.setMinimumSize(new Dimension(100, 100));
        this.game = game;
        this.tableModel.addColumn(this.names[0]);
        this.tableModel.addColumn(this.names[1]);
        this.addTableModelListener(null);
        this.tableModel.addTableModelListener(null);
        this.scrollPane.setAutoscrolls(true);
    }
    
    public void draw() {
    }
    
    @Override
    public String getValueAt(final int x, final int y) {
        return this.moves.get(y * 2 - 1 + (x - 1));
    }
    
    @Override
    public int getRowCount() {
        return this.rowsNum;
    }
    
    @Override
    public int getColumnCount() {
        return this.columnsNum;
    }
    
    protected void addRow() {
        this.tableModel.addRow(new String[2]);
    }
    
    protected void addCastling(final String move) {
        this.moves.remove(this.moves.size() - 1);
        if (!this.enterBlack) {
            this.tableModel.setValueAt(move, this.tableModel.getRowCount() - 1, 1);
        }
        else {
            this.tableModel.setValueAt(move, this.tableModel.getRowCount() - 1, 0);
        }
        this.moves.add(move);
    }
    
    @Override
    public boolean isCellEditable(final int a, final int b) {
        return false;
    }
    
    protected void addMove2Table(final String str) {
        try {
            if (!this.enterBlack) {
                this.addRow();
                this.rowsNum = this.tableModel.getRowCount() - 1;
                this.tableModel.setValueAt(str, this.rowsNum, 0);
            }
            else {
                this.tableModel.setValueAt(str, this.rowsNum, 1);
                this.rowsNum = this.tableModel.getRowCount() - 1;
            }
            this.enterBlack = !this.enterBlack;
            this.table.scrollRectToVisible(this.table.getCellRect(this.table.getRowCount() - 1, 0, true));
        }
        catch (ArrayIndexOutOfBoundsException exc) {
            if (this.rowsNum > 0) {
                --this.rowsNum;
                this.addMove2Table(str);
            }
        }
    }
    
    public void addMove(final String move) {
        if (isMoveCorrect(move)) {
            this.moves.add(move);
            this.addMove2Table(move);
            this.moveForwardStack.clear();
        }
    }
    
    public void addMove(final Square begin, final Square end, final boolean registerInHistory, final Castling castlingMove, boolean wasEnPassant, final Piece promotedPiece) {
        String locMove = begin.getPiece().getSymbol();
        if (this.game.getSettings().isUpsideDown()) {
            locMove = this.addMoveHandleUpsideDown(locMove, begin);
        }
        else {
            locMove = this.addMoveHandleNormalSetup(locMove, begin);
        }
        if (end.piece != null) {
            locMove += "x";
        }
        else {
            locMove += "-";
        }
        if (this.game.getSettings().isUpsideDown()) {
            locMove = this.addMoveHandleUpsideDown(locMove, end);
        }
        else {
            locMove = this.addMoveHandleNormalSetup(locMove, end);
        }
        if (Pawn.class == begin.getPiece().getClass() && begin.getPozX() - end.getPozX() != 0 && end.piece == null) {
            locMove += "(e.p)";
            wasEnPassant = true;
        }
        if (this.isBlackOrWhiteKingCheck()) {
            if (this.isBlackOrWhiteKingCheckmatedOrStalemated()) {
                locMove += "#";
            }
            else {
                locMove += "+";
            }
        }
        if (castlingMove != Castling.NONE) {
            this.addCastling(castlingMove.getSymbol());
        }
        else {
            this.moves.add(locMove);
            this.addMove2Table(locMove);
        }
        this.scrollPane.scrollRectToVisible(new Rectangle(0, this.scrollPane.getHeight() - 2, 1, 1));
        if (registerInHistory) {
            final Move moveToAdd = new Move(new Square(begin), new Square(end), begin.piece, end.piece, castlingMove, wasEnPassant, promotedPiece);
            this.getMoveBackStack().add(moveToAdd);
        }
    }
    
    private boolean isBlackOrWhiteKingCheckmatedOrStalemated() {
        return (!this.enterBlack && this.game.getChessboard().getKingBlack().isCheckmatedOrStalemated() == 1) || (this.enterBlack && this.game.getChessboard().getKingWhite().isCheckmatedOrStalemated() == 1);
    }
    
    private boolean isBlackOrWhiteKingCheck() {
        return (!this.enterBlack && this.game.getChessboard().getKingBlack().isChecked()) || (this.enterBlack && this.game.getChessboard().getKingWhite().isChecked());
    }
    
    private String addMoveHandleNormalSetup(String locMove, final Square begin) {
        locMove += Character.toString((char)(begin.getPozX() + 97));
        locMove += Integer.toString(8 - begin.getPozY());
        return locMove;
    }
    
    private String addMoveHandleUpsideDown(String locMove, final Square begin) {
        locMove += Character.toString((char)(Chessboard.getBottom() - begin.getPozX() + 97));
        locMove += Integer.toString(begin.getPozY() + 1);
        return locMove;
    }
    
    public void clearMoveForwardStack() {
        this.moveForwardStack.clear();
    }
    
    public JScrollPane getScrollPane() {
        return this.scrollPane;
    }
    
    public ArrayList<String> getMoves() {
        return this.moves;
    }
    
    public synchronized Move getLastMoveFromHistory() {
        try {
            final Move last = this.getMoveBackStack().get(this.getMoveBackStack().size() - 1);
            return last;
        }
        catch (ArrayIndexOutOfBoundsException exc) {
            return null;
        }
    }
    
    public synchronized Move getNextMoveFromHistory() {
        try {
            final Move next = this.moveForwardStack.get(this.moveForwardStack.size() - 1);
            return next;
        }
        catch (ArrayIndexOutOfBoundsException exc) {
            MovesHistory.LOG.error("ArrayIndexOutOfBoundsException: ", exc);
            return null;
        }
    }
    
    public synchronized Move undo() {
        try {
            final Move last = this.getMoveBackStack().pop();
            if (last != null) {
                if (this.game.getSettings().getGameType() == GameTypes.LOCAL) {
                    this.moveForwardStack.push(last);
                }
                if (this.enterBlack) {
                    this.tableModel.setValueAt("", this.tableModel.getRowCount() - 1, 0);
                    this.tableModel.removeRow(this.tableModel.getRowCount() - 1);
                    if (this.rowsNum > 0) {
                        --this.rowsNum;
                    }
                }
                else if (this.tableModel.getRowCount() > 0) {
                    this.tableModel.setValueAt("", this.tableModel.getRowCount() - 1, 1);
                }
                this.moves.remove(this.moves.size() - 1);
                this.enterBlack = !this.enterBlack;
            }
            return last;
        }
        catch (EmptyStackException exc) {
            MovesHistory.LOG.error("EmptyStackException: ", exc);
            this.enterBlack = false;
            return null;
        }
        catch (ArrayIndexOutOfBoundsException exc2) {
            MovesHistory.LOG.error("ArrayIndexOutOfBoundsException: ", exc2);
            return null;
        }
    }
    
    public synchronized Move redo() {
        try {
            if (this.game.getSettings().getGameType() == GameTypes.LOCAL) {
                final Move first = this.moveForwardStack.pop();
                this.getMoveBackStack().push(first);
                return first;
            }
            return null;
        }
        catch (EmptyStackException exc) {
            MovesHistory.LOG.error("redo: EmptyStackException: ", exc);
            return null;
        }
    }
    
    public static boolean isMoveCorrect(final String move) {
        if (move.equals(Castling.SHORT_CASTLING.getSymbol()) || move.equals(Castling.LONG_CASTLING.getSymbol())) {
            return true;
        }
        try {
            int from = 0;
            int sign = move.charAt(from);
            switch (sign) {
                case 66:
                case 75:
                case 78:
                case 81:
                case 82: {
                    from = 1;
                    break;
                }
            }
            sign = move.charAt(from);
            MovesHistory.LOG.debug("isMoveCorrect/sign: " + sign);
            if (sign < 97 || sign > 104) {
                return false;
            }
            sign = move.charAt(from + 1);
            if (sign < 49 || sign > 56) {
                return false;
            }
            if (move.length() > 3) {
                sign = move.charAt(from + 2);
                if (sign != 45 && sign != 120) {
                    return false;
                }
                sign = move.charAt(from + 3);
                if (sign < 97 || sign > 104) {
                    return false;
                }
                sign = move.charAt(from + 4);
                if (sign < 49 || sign > 56) {
                    return false;
                }
            }
        }
        catch (StringIndexOutOfBoundsException exc) {
            MovesHistory.LOG.error("isMoveCorrect/StringIndexOutOfBoundsException: ", exc);
            return false;
        }
        return true;
    }
    
    public void addMoves(final ArrayList<String> list) {
        for (final String singleMove : list) {
            if (isMoveCorrect(singleMove)) {
                this.addMove(singleMove);
            }
        }
    }
    
    public String getMovesInString() {
        int n = 1;
        int i = 0;
        String str = new String();
        for (final String locMove : this.getMoves()) {
            if (i % 2 == 0) {
                str = str + n + ". ";
                ++n;
            }
            str = str + locMove + " ";
            ++i;
        }
        return str;
    }
    
    public void setMoves(final String moves) throws ReadGameError {
        int from = 0;
        int to = 0;
        int n = 1;
        String currentMove = "";
        final ArrayList<String> tempArray = new ArrayList<String>();
        final int tempStrSize = moves.length() - 1;
        do {
            from = moves.indexOf(" ", from);
            to = moves.indexOf(" ", from + 1);
            if (0 > from) {
                break;
            }
            if (0 > to) {
                break;
            }
            try {
                currentMove = moves.substring(from + 1, to).trim();
                tempArray.add(currentMove);
                MovesHistory.LOG.debug(String.format("Processed following move in PGN: %s", currentMove));
            }
            catch (StringIndexOutOfBoundsException exc) {
                MovesHistory.LOG.error("setMoves/StringIndexOutOfBoundsException: error parsing file to load: ", exc);
                break;
            }
            if (n % 2 == 0) {
                from = moves.indexOf(".", to);
                if (from < to) {
                    break;
                }
            }
            else {
                from = to;
            }
            ++n;
        } while (from <= tempStrSize && to <= tempStrSize);
        for (final String locMove : tempArray) {
            if (!isMoveCorrect(locMove.trim())) {
                throw new ReadGameError(String.format(Settings.lang("invalid_file_to_load"), locMove), locMove);
            }
        }
        boolean canMove = false;
        for (final String locMove2 : tempArray) {
            if (Castling.isCastling(locMove2)) {
                int[] values = new int[4];
                final Colors color = this.game.getActivePlayer().getColor();
                if (locMove2.equals(Castling.LONG_CASTLING.getSymbol())) {
                    values = Castling.LONG_CASTLING.getMove(color);
                }
                else if (locMove2.equals(Castling.SHORT_CASTLING.getSymbol())) {
                    values = Castling.SHORT_CASTLING.getMove(color);
                }
                canMove = this.game.simulateMove(values[0], values[1], values[2], values[3], null);
                if (!canMove) {
                    throw new ReadGameError(String.format(Settings.lang("illegal_move_on"), locMove2), locMove2);
                }
                continue;
            }
            else {
                from = 0;
                final int num = locMove2.charAt(from);
                if (num <= 90 && num >= 65) {
                    from = 1;
                }
                int xFrom = 9;
                int yFrom = 9;
                int xTo = 9;
                int yTo = 9;
                boolean pieceFound = false;
                if (locMove2.length() <= 3) {
                    final Square[][] squares = this.game.getChessboard().getSquares();
                    xTo = locMove2.charAt(from) - 'a';
                    yTo = Chessboard.getBottom() - (locMove2.charAt(from + 1) - '1');
                    for (int i = 0; i < squares.length && !pieceFound; ++i) {
                        for (int j = 0; j < squares[i].length && !pieceFound; ++j) {
                            if (squares[i][j].piece != null) {
                                if (this.game.getActivePlayer().getColor() == squares[i][j].getPiece().getPlayer().getColor()) {
                                    final Set<Square> pieceMoves = squares[i][j].getPiece().getAllMoves();
                                    for (final Object square : pieceMoves) {
                                        final Square currSquare = (Square)square;
                                        if (currSquare.getPozX() == xTo && currSquare.getPozY() == yTo) {
                                            xFrom = squares[i][j].getPiece().getSquare().getPozX();
                                            yFrom = squares[i][j].getPiece().getSquare().getPozY();
                                            pieceFound = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                else {
                    xFrom = locMove2.charAt(from) - 'a';
                    yFrom = Chessboard.getBottom() - (locMove2.charAt(from + 1) - '1');
                    xTo = locMove2.charAt(from + 3) - 'a';
                    yTo = Chessboard.getBottom() - (locMove2.charAt(from + 4) - '1');
                }
                canMove = this.game.simulateMove(xFrom, yFrom, xTo, yTo, null);
                if (!canMove) {
                    this.game.getChessboard().resetActiveSquare();
                    throw new ReadGameError(String.format(Settings.lang("illegal_move_on"), locMove2), locMove2);
                }
                continue;
            }
        }
    }
    
    public Stack<Move> getMoveBackStack() {
        return this.moveBackStack;
    }
    
    public void decrementFiftyMoveRule() {
        --this.fiftyMoveRuleCounter;
    }
    
    public void incrementFiftyMoveRule(final Move move) {
        if (!(move.getMovedPiece() instanceof Pawn) && null == move.getTakenPiece()) {
            ++this.fiftyMoveRuleCounter;
        }
    }
    
    public int getFiftyMoveRuleCounter() {
        return this.fiftyMoveRuleCounter;
    }
    
    public void setFiftyMoveRuleCounter(final int fiftyMoveRuleCounter) {
        this.fiftyMoveRuleCounter = fiftyMoveRuleCounter;
    }
    
    static {
        LOG = Logger.getLogger(MovesHistory.class);
    }
}
