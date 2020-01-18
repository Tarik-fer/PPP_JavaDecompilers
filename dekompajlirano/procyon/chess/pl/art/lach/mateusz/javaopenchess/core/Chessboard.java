// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.core;

import java.util.ArrayList;
import pl.art.lach.mateusz.javaopenchess.core.moves.Move;
import pl.art.lach.mateusz.javaopenchess.utils.GameTypes;
import pl.art.lach.mateusz.javaopenchess.core.moves.Castling;
import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Queen;
import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Bishop;
import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Knight;
import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Rook;
import pl.art.lach.mateusz.javaopenchess.core.players.Player;
import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
import pl.art.lach.mateusz.javaopenchess.display.views.chessboard.implementation.graphic2D.Chessboard2D;
import pl.art.lach.mateusz.javaopenchess.display.views.chessboard.ChessboardView;
import pl.art.lach.mateusz.javaopenchess.core.moves.MovesHistory;
import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Pawn;
import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.King;
import pl.art.lach.mateusz.javaopenchess.utils.Settings;
import java.util.Set;
import org.apache.log4j.Logger;

public class Chessboard
{
    private static final Logger LOG;
    protected static final int TOP = 0;
    protected static final int BOTTOM = 7;
    public static final int LAST_SQUARE = 7;
    public static final int FIRST_SQUARE = 0;
    public static final int NUMBER_OF_SQUARES = 8;
    protected Square[][] squares;
    private Set<Square> moves;
    private Settings settings;
    protected King kingWhite;
    protected King kingBlack;
    private Pawn twoSquareMovedPawn;
    private MovesHistory movesObject;
    protected Square activeSquare;
    protected int activeSquareX;
    protected int activeSquareY;
    private ChessboardView chessboardView;
    private int halfCounter;
    private int fullMoveCounterAdd;
    
    public Chessboard(final Settings settings, final MovesHistory moves) {
        this.twoSquareMovedPawn = null;
        this.halfCounter = 0;
        this.fullMoveCounterAdd = 0;
        this.settings = settings;
        this.chessboardView = new Chessboard2D(this);
        this.activeSquareX = 0;
        this.activeSquareY = 0;
        this.squares = new Square[8][8];
        for (int i = 0; i < 8; ++i) {
            for (int y = 0; y < 8; ++y) {
                this.squares[i][y] = new Square(i, y, null);
            }
        }
        this.movesObject = moves;
    }
    
    public Chessboard(final Settings settings, final MovesHistory moves, final ChessboardView chessboardView) {
        this(settings, moves);
        this.chessboardView = chessboardView;
    }
    
    public static int getTop() {
        return 0;
    }
    
    public static int getBottom() {
        return 7;
    }
    
    public void setPieces4NewGame(final Player plWhite, final Player plBlack) {
        final Player player = plBlack;
        final Player player2 = plWhite;
        this.setFigures4NewGame(0, player);
        this.setPawns4NewGame(1, player);
        this.setFigures4NewGame(7, player2);
        this.setPawns4NewGame(6, player2);
    }
    
    private void setFigures4NewGame(final int i, final Player player) {
        if (i != 0 && i != 7) {
            Chessboard.LOG.error("error setting figures like rook etc.");
            return;
        }
        if (i == 0) {
            player.setGoDown(true);
        }
        this.getSquare(0, i).setPiece(new Rook(this, player));
        this.getSquare(7, i).setPiece(new Rook(this, player));
        this.getSquare(1, i).setPiece(new Knight(this, player));
        this.getSquare(6, i).setPiece(new Knight(this, player));
        this.getSquare(2, i).setPiece(new Bishop(this, player));
        this.getSquare(5, i).setPiece(new Bishop(this, player));
        this.getSquare(3, i).setPiece(new Queen(this, player));
        if (player.getColor() == Colors.WHITE) {
            this.kingWhite = new King(this, player);
            this.getSquare(4, i).setPiece(this.kingWhite);
        }
        else {
            this.kingBlack = new King(this, player);
            this.getSquare(4, i).setPiece(this.kingBlack);
        }
    }
    
    private void setPawns4NewGame(final int i, final Player player) {
        if (i != 1 && i != 6) {
            Chessboard.LOG.error("error setting pawns etc.");
            return;
        }
        for (int x = 0; x < 8; ++x) {
            this.getSquare(x, i).setPiece(new Pawn(this, player));
        }
    }
    
    public void select(final Square sq) {
        this.setActiveSquare(sq);
        this.setActiveSquareX(sq.getPozX() + 1);
        this.setActiveSquareY(sq.getPozY() + 1);
        Chessboard.LOG.debug(String.format("active_x: %s active_y: %s", this.getActiveSquareX(), this.getActiveSquareY()));
        this.getChessboardView().repaint();
    }
    
    public void unselect() {
        this.setActiveSquareX(0);
        this.setActiveSquareY(0);
        this.setActiveSquare(null);
        this.getChessboardView().unselect();
    }
    
    public void resetActiveSquare() {
        this.setActiveSquare(null);
    }
    
    public void move(final Square begin, final Square end) {
        this.move(begin, end, true);
    }
    
    public void move(final int xFrom, final int yFrom, final int xTo, final int yTo) {
        Square fromSQ;
        Square toSQ;
        try {
            fromSQ = this.getSquare(xFrom, yFrom);
            toSQ = this.getSquare(xTo, yTo);
        }
        catch (IndexOutOfBoundsException exc) {
            Chessboard.LOG.error("error moving piece: " + exc.getMessage());
            return;
        }
        this.move(fromSQ, toSQ, true);
    }
    
    public void move(final Square begin, final Square end, final boolean refresh) {
        this.move(begin, end, refresh, true);
    }
    
    public void move(final Square begin, final Square end, final boolean refresh, final boolean clearForwardHistory) {
        Castling castling = Castling.NONE;
        Piece promotedPiece = null;
        Piece takenPiece = null;
        boolean wasEnPassant = false;
        if (null != end.piece) {
            takenPiece = end.piece;
            end.getPiece().setSquare(null);
        }
        final Square tempBegin = new Square(begin);
        final Square tempEnd = new Square(end);
        begin.getPiece().setSquare(end);
        end.piece = begin.piece;
        begin.piece = null;
        if (King.class == end.getPiece().getClass()) {
            castling = this.moveKing(end, castling, begin);
        }
        else if (Rook.class == end.getPiece().getClass()) {
            this.moveRook(end);
        }
        else if (Pawn.class == end.getPiece().getClass()) {
            wasEnPassant = this.movePawn(end, begin, tempEnd, wasEnPassant);
            if (Pawn.canBePromoted(end)) {
                promotedPiece = this.promotePawn(clearForwardHistory, end, promotedPiece);
            }
        }
        else if (Pawn.class != end.getPiece().getClass()) {
            this.setTwoSquareMovedPawn(null);
        }
        if (refresh) {
            this.unselect();
            this.repaint();
        }
        this.handleHalfMoveCounter(end, takenPiece);
        this.handleHistory(clearForwardHistory, tempBegin, tempEnd, castling, wasEnPassant, promotedPiece);
    }
    
    private void handleHalfMoveCounter(final Square end, final Piece takenPiece) {
        if (isHalfMove(end, takenPiece)) {
            ++this.halfCounter;
        }
        else {
            this.halfCounter = 0;
        }
    }
    
    private static boolean isHalfMove(final Square end, final Piece takenPiece) {
        return !(end.getPiece() instanceof Pawn) && null == takenPiece;
    }
    
    private void handleHistory(final boolean clearForwardHistory, final Square tempBegin, final Square tempEnd, final Castling castling, final boolean wasEnPassant, final Piece promotedPiece) {
        if (clearForwardHistory) {
            this.movesObject.clearMoveForwardStack();
            this.movesObject.addMove(tempBegin, tempEnd, true, castling, wasEnPassant, promotedPiece);
        }
        else {
            this.movesObject.addMove(tempBegin, tempEnd, false, castling, wasEnPassant, promotedPiece);
        }
        if (this.getSettings().isGameAgainstComputer()) {}
    }
    
    public boolean movePawn(final Square end, final Square begin, final Square tempEnd, boolean wasEnPassant) {
        if (this.getTwoSquareMovedPawn() != null && this.getSquares()[end.getPozX()][begin.getPozY()] == this.getTwoSquareMovedPawn().getSquare()) {
            tempEnd.piece = this.getSquares()[end.getPozX()][begin.getPozY()].piece;
            this.squares[end.pozX][begin.pozY].piece = null;
            wasEnPassant = true;
        }
        if (begin.getPozY() - end.getPozY() == 2 || end.getPozY() - begin.getPozY() == 2) {
            this.setTwoSquareMovedPawn((Pawn)end.piece);
        }
        else {
            this.setTwoSquareMovedPawn(null);
        }
        return wasEnPassant;
    }
    
    public Piece promotePawn(final boolean clearForwardHistory, final Square end, Piece promotedPiece) {
        if (clearForwardHistory) {
            final Piece piece = end.getPiece().getPlayer().getPromotionPiece(this);
            if (null != piece) {
                piece.setChessboard(end.getPiece().getChessboard());
                piece.setPlayer(end.getPiece().getPlayer());
                piece.setSquare(end.getPiece().getSquare());
                end.piece = piece;
                promotedPiece = end.piece;
            }
        }
        return promotedPiece;
    }
    
    private void moveRook(final Square end) {
        if (!((Rook)end.piece).getWasMotioned()) {
            ((Rook)end.piece).setWasMotioned(true);
        }
    }
    
    private Castling moveKing(final Square end, Castling castling, final Square begin) {
        if (!((King)end.piece).getWasMotioned()) {
            ((King)end.piece).setWasMotioned(true);
        }
        castling = King.getCastling(begin, end);
        if (Castling.SHORT_CASTLING == castling) {
            this.move(this.getSquare(7, begin.getPozY()), this.getSquare(end.getPozX() - 1, begin.getPozY()), false, false);
        }
        else if (Castling.LONG_CASTLING == castling) {
            this.move(this.getSquare(0, begin.getPozY()), this.getSquare(end.getPozX() + 1, begin.getPozY()), false, false);
        }
        return castling;
    }
    
    public boolean redo() {
        return this.redo(true);
    }
    
    public boolean redo(final boolean refresh) {
        if (this.getSettings().getGameType() == GameTypes.LOCAL) {
            final Move first = this.movesObject.redo();
            if (first != null) {
                final Square from = first.getFrom();
                final Square to = first.getTo();
                this.move(this.getSquares()[from.getPozX()][from.getPozY()], this.getSquares()[to.getPozX()][to.getPozY()], true, false);
                if (first.getPromotedPiece() != null) {
                    final Pawn pawn = (Pawn)this.getSquares()[to.getPozX()][to.getPozY()].piece;
                    pawn.setSquare(null);
                    this.squares[to.pozX][to.pozY].piece = first.getPromotedPiece();
                    final Piece promoted = this.getSquares()[to.getPozX()][to.getPozY()].piece;
                    promoted.setSquare(this.getSquares()[to.getPozX()][to.getPozY()]);
                }
                return true;
            }
        }
        return false;
    }
    
    public boolean undo() {
        return this.undo(true);
    }
    
    public synchronized boolean undo(final boolean refresh) {
        final Move last = this.movesObject.undo();
        return canUndo(last) && this.processUndoOperation(last, refresh);
    }
    
    private boolean processUndoOperation(final Move last, final boolean refresh) {
        final Square begin = last.getFrom();
        final Square end = last.getTo();
        try {
            final Piece moved = last.getMovedPiece();
            (this.squares[begin.pozX][begin.pozY].piece = moved).setSquare(this.getSquares()[begin.getPozX()][begin.getPozY()]);
            final Piece taken = last.getTakenPiece();
            if (last.getCastlingMove() != Castling.NONE) {
                this.handleUndoCastling(last, end, begin, moved);
            }
            else if (Rook.class == moved.getClass()) {
                ((Rook)moved).setWasMotioned(false);
            }
            else if (Pawn.class == moved.getClass() && last.wasEnPassant()) {
                this.handleEnPessant(last, end, begin);
            }
            else if (Pawn.class == moved.getClass() && last.getPromotedPiece() != null) {
                this.handlePawnPromotion(end);
            }
            final Move oneMoveEarlier = this.movesObject.getLastMoveFromHistory();
            if (oneMoveEarlier != null && oneMoveEarlier.wasPawnTwoFieldsMove()) {
                final int toPozX = oneMoveEarlier.getTo().getPozX();
                final int toPozY = oneMoveEarlier.getTo().getPozY();
                final Piece canBeTakenEnPassant = this.getSquare(toPozX, toPozY).getPiece();
                if (Pawn.class == canBeTakenEnPassant.getClass()) {
                    this.setTwoSquareMovedPawn((Pawn)canBeTakenEnPassant);
                }
            }
            if (taken != null && !last.wasEnPassant()) {
                (this.squares[end.pozX][end.pozY].piece = taken).setSquare(this.getSquares()[end.getPozX()][end.getPozY()]);
            }
            else {
                this.squares[end.pozX][end.pozY].piece = null;
            }
            if (refresh) {
                this.unselect();
                this.repaint();
            }
        }
        catch (ArrayIndexOutOfBoundsException | NullPointerException ex2) {
            final RuntimeException ex;
            final RuntimeException exc = ex;
            Chessboard.LOG.error(String.format("error: %s exc object: ", exc.getClass()), exc);
            return false;
        }
        return true;
    }
    
    private void handleUndoCastling(final Move last, final Square end, final Square begin, final Piece moved) {
        Piece rook = null;
        if (last.getCastlingMove() == Castling.SHORT_CASTLING) {
            rook = this.handleShortCastling(rook, end, begin);
        }
        else {
            rook = this.handleLongCastling(rook, end, begin);
        }
        ((King)moved).setWasMotioned(false);
        ((Rook)rook).setWasMotioned(false);
    }
    
    private static boolean canUndo(final Move last) {
        return last != null && last.getFrom() != null;
    }
    
    private void handlePawnPromotion(final Square end) {
        final Piece promoted = this.getSquares()[end.getPozX()][end.getPozY()].piece;
        promoted.setSquare(null);
        this.squares[end.pozX][end.pozY].piece = null;
    }
    
    private void handleEnPessant(final Move last, final Square end, final Square begin) {
        final Pawn pawn = (Pawn)last.getTakenPiece();
        (this.squares[end.pozX][begin.pozY].piece = pawn).setSquare(this.getSquares()[end.getPozX()][begin.getPozY()]);
    }
    
    private Piece handleLongCastling(Piece rook, final Square end, final Square begin) {
        rook = this.getSquares()[end.getPozX() + 1][end.getPozY()].piece;
        (this.squares[0][begin.pozY].piece = rook).setSquare(this.getSquares()[0][begin.getPozY()]);
        this.squares[end.pozX + 1][end.pozY].piece = null;
        return rook;
    }
    
    private Piece handleShortCastling(Piece rook, final Square end, final Square begin) {
        rook = this.getSquares()[end.getPozX() - 1][end.getPozY()].piece;
        (this.squares[7][begin.pozY].piece = rook).setSquare(this.getSquares()[7][begin.getPozY()]);
        this.squares[end.pozX - 1][end.pozY].piece = null;
        return rook;
    }
    
    public Square[][] getSquares() {
        return this.squares;
    }
    
    public Square getSquare(final int x, final int y) {
        try {
            return this.squares[x][y];
        }
        catch (ArrayIndexOutOfBoundsException exc) {
            return null;
        }
    }
    
    public Square getSquare(final Squares squareX, final Squares squareY) {
        return this.getSquare(squareX.getValue(), squareY.getValue());
    }
    
    public void clear() {
        for (int i = 0; i < this.squares.length; ++i) {
            for (int j = 0; j < this.squares[i].length; ++j) {
                Piece piece = this.squares[i][j].getPiece();
                piece = null;
                this.squares[i][j].setPiece(null);
            }
        }
    }
    
    public Square getActiveSquare() {
        return this.activeSquare;
    }
    
    public ArrayList<Piece> getAllPieces(final Colors color) {
        final ArrayList<Piece> result = new ArrayList<Piece>();
        for (int i = 0; i < this.squares.length; ++i) {
            for (int j = 0; j < this.squares[i].length; ++j) {
                final Square sq = this.squares[i][j];
                if (null != sq.getPiece() && (sq.getPiece().getPlayer().getColor() == color || color == null)) {
                    result.add(sq.getPiece());
                }
            }
        }
        return result;
    }
    
    public static boolean wasEnPassant(final Square sq) {
        return sq.getPiece() != null && sq.getPiece().getChessboard().getTwoSquareMovedPawn() != null && sq == sq.getPiece().getChessboard().getTwoSquareMovedPawn().getSquare();
    }
    
    public King getKingWhite() {
        return this.kingWhite;
    }
    
    public King getKingBlack() {
        return this.kingBlack;
    }
    
    public void setKingWhite(final King kingWhite, final Square sq) {
        this.kingWhite = kingWhite;
        this.getSquare(sq.getPozX(), sq.getPozY()).setPiece(this.kingWhite);
    }
    
    public void setKingBlack(final King kingBlack, final Square sq) {
        this.kingBlack = kingBlack;
        this.getSquare(sq.getPozX(), sq.getPozY()).setPiece(this.kingBlack);
    }
    
    public Pawn getTwoSquareMovedPawn() {
        return this.twoSquareMovedPawn;
    }
    
    public ChessboardView getChessboardView() {
        return this.chessboardView;
    }
    
    public void setChessboardView(final ChessboardView chessboardView) {
        this.chessboardView = chessboardView;
    }
    
    public void repaint() {
        this.getChessboardView().repaint();
    }
    
    public Settings getSettings() {
        return this.settings;
    }
    
    public void setSettings(final Settings settings) {
        this.settings = settings;
        for (int i = 0; i < 7; ++i) {
            for (int j = 0; j < 7; ++j) {
                final Square sq = this.getSquare(i, j);
                if (null != sq.getPiece()) {
                    if (Colors.WHITE == sq.getPiece().getPlayer().getColor()) {
                        sq.getPiece().setPlayer(settings.getPlayerWhite());
                    }
                    else {
                        sq.getPiece().setPlayer(settings.getPlayerBlack());
                    }
                }
            }
        }
    }
    
    public Set<Square> getMoves() {
        return this.moves;
    }
    
    public void setMoves(final Set<Square> moves) {
        this.moves = moves;
    }
    
    public void setActiveSquare(final Square activeSquare) {
        this.activeSquare = activeSquare;
    }
    
    public int getActiveSquareX() {
        return this.activeSquareX;
    }
    
    public void setActiveSquareX(final int activeSquareX) {
        this.activeSquareX = activeSquareX;
    }
    
    public int getActiveSquareY() {
        return this.activeSquareY;
    }
    
    public void setActiveSquareY(final int activeSquareY) {
        this.activeSquareY = activeSquareY;
    }
    
    public void setTwoSquareMovedPawn(final Pawn twoSquareMovedPawn) {
        this.twoSquareMovedPawn = twoSquareMovedPawn;
    }
    
    public int getHalfCounter() {
        return this.halfCounter;
    }
    
    public void setHalfCounter(final int halfCounter) {
        this.halfCounter = halfCounter;
    }
    
    public int getFullMoveCounterAdd() {
        return this.fullMoveCounterAdd;
    }
    
    public void setFullMoveCounterAdd(final int fullMoveCounterAdd) {
        this.fullMoveCounterAdd = fullMoveCounterAdd;
    }
    
    static {
        LOG = Logger.getLogger(Chessboard.class);
    }
}
