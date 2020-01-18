// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.core.data_transfer.implementations;

import pl.art.lach.mateusz.javaopenchess.core.Colors;
import pl.art.lach.mateusz.javaopenchess.core.pieces.PieceFactory;
import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Rook;
import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.King;
import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
import pl.art.lach.mateusz.javaopenchess.core.Square;
import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Pawn;
import pl.art.lach.mateusz.javaopenchess.core.Squares;
import pl.art.lach.mateusz.javaopenchess.core.players.Player;
import pl.art.lach.mateusz.javaopenchess.core.Chessboard;
import pl.art.lach.mateusz.javaopenchess.utils.Settings;
import pl.art.lach.mateusz.javaopenchess.core.exceptions.ReadGameError;
import pl.art.lach.mateusz.javaopenchess.core.GameFactory;
import pl.art.lach.mateusz.javaopenchess.core.players.PlayerType;
import pl.art.lach.mateusz.javaopenchess.utils.GameTypes;
import pl.art.lach.mateusz.javaopenchess.utils.GameModes;
import pl.art.lach.mateusz.javaopenchess.core.Game;
import pl.art.lach.mateusz.javaopenchess.core.data_transfer.DataExporter;
import pl.art.lach.mateusz.javaopenchess.core.data_transfer.DataImporter;

public class FenNotation implements DataImporter, DataExporter
{
    public static final String INITIAL_STATE = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
    private static final String BLACK_QUEEN_SYMBOL = "q";
    private static final String BLACK_KING_SYMBOL = "k";
    private final String WHITE_QUEEN_SYMBOL = "Q";
    private static final String WHITE_KING_SYMBOL = "K";
    private static final int PIECES_STATE_NUM = 0;
    private static final int ACTIVE_PLAYER_NUM = 1;
    private static final int CASTLING_STATE_NUM = 2;
    private static final int EN_PASSANT_STATE_NUM = 3;
    private static final int HALF_COUNTER_STATE_NUM = 4;
    private static final int FULL_COUNTER_STATE_NUM = 5;
    private static final String CHAR_PLAYER_WHITE = "w";
    private static final String CHAR_PLAYER_BLACK = "b";
    public static final String ROW_SEPARATOR = "/";
    public static final String FIELD_SEPARATOR = " ";
    public static final String FIELD_EMPTY = "-";
    private static final String SQUARE_PREFIX = "SQ_";
    private static final int NUMBER_OF_FIELDS = 6;
    private static final int NUMBER_OF_ROWS = 8;
    
    @Override
    public Game importData(final String data) throws ReadGameError {
        final String whiteName = "--";
        final String blackName = "--";
        final Game game = GameFactory.instance(GameModes.LOAD_GAME, GameTypes.LOCAL, whiteName, blackName, PlayerType.LOCAL_USER, PlayerType.LOCAL_USER, true, false);
        this.importData(data, game);
        game.getChessboard().repaint();
        return game;
    }
    
    @Override
    public void importData(final String data, final Game game) throws ReadGameError {
        final Chessboard chessboard = game.getChessboard();
        chessboard.clear();
        final String[] fields = data.split(" ");
        if (6 != fields.length) {
            throw new ReadGameError(Settings.lang("invalid_fen_state"), Settings.lang("invalid_fen_number_of_fields"));
        }
        final Player blackPlayer = game.getSettings().getPlayerBlack();
        final Player whitePlayer = game.getSettings().getPlayerWhite();
        this.importPieces(fields[0], game, whitePlayer, blackPlayer);
        this.importActivePlayer(fields[1], game);
        this.importCastlingState(fields[2], chessboard);
        this.importEnPassantState(fields[3], chessboard, game);
        this.importCounters(fields, game);
    }
    
    private void importCounters(final String[] fields, final Game game) throws ReadGameError {
        try {
            final Integer halfCounter = Integer.parseInt(fields[4]);
            game.getChessboard().setHalfCounter(halfCounter);
            game.getChessboard().setFullMoveCounterAdd(Integer.parseInt(fields[5]));
        }
        catch (NumberFormatException exc) {
            throw new ReadGameError(Settings.lang("invalid_fen_state"), fields[4]);
        }
    }
    
    private void importEnPassantState(final String enPassantState, final Chessboard chessboard, final Game game) throws ReadGameError {
        if (!"-".equals(enPassantState) && enPassantState.length() == 2) {
            try {
                final Squares sqX = Squares.valueOf("SQ_" + enPassantState.substring(0, 1).toUpperCase());
                Squares sqY = Squares.valueOf("SQ_" + enPassantState.substring(1, 2).toUpperCase());
                if (Squares.SQ_3 == sqY) {
                    sqY = Squares.SQ_4;
                }
                else {
                    if (Squares.SQ_5 != sqY) {
                        throw new ReadGameError(Settings.lang("invalid_fen_state"), enPassantState);
                    }
                    sqY = Squares.SQ_6;
                }
                final Square sq = chessboard.getSquare(sqX, sqY);
                final Piece piece = sq.getPiece();
                if (null != piece && Pawn.class == piece.getClass()) {
                    game.getChessboard().setTwoSquareMovedPawn((Pawn)sq.getPiece());
                }
            }
            catch (IllegalStateException exc) {
                throw new ReadGameError(Settings.lang("invalid_fen_state"), enPassantState);
            }
        }
    }
    
    private void importCastlingState(final String castlingState, final Chessboard chessboard) throws ReadGameError {
        for (int i = 0, size = castlingState.length(); i < size; ++i) {
            final String state = castlingState.substring(i, i + 1);
            if (!"-".equals(state)) {
                final String s = state;
                switch (s) {
                    case "K": {
                        this.setupCastlingState(chessboard.getSquare(Squares.SQ_E, Squares.SQ_1), chessboard.getSquare(Squares.SQ_H, Squares.SQ_1));
                        break;
                    }
                    case "Q": {
                        this.setupCastlingState(chessboard.getSquare(Squares.SQ_E, Squares.SQ_1), chessboard.getSquare(Squares.SQ_A, Squares.SQ_1));
                        break;
                    }
                    case "k": {
                        this.setupCastlingState(chessboard.getSquare(Squares.SQ_E, Squares.SQ_8), chessboard.getSquare(Squares.SQ_H, Squares.SQ_8));
                        break;
                    }
                    case "q": {
                        this.setupCastlingState(chessboard.getSquare(Squares.SQ_E, Squares.SQ_8), chessboard.getSquare(Squares.SQ_A, Squares.SQ_8));
                        break;
                    }
                }
            }
        }
    }
    
    private void setupCastlingState(final Square kingSquare, final Square rookSquare) throws ReadGameError {
        Piece piece = kingSquare.getPiece();
        if (King.class != piece.getClass()) {
            throw new ReadGameError(Settings.lang("invalid_fen_state"));
        }
        final King king = (King)piece;
        king.setWasMotioned(false);
        piece = rookSquare.getPiece();
        if (Rook.class == piece.getClass()) {
            final Rook rook = (Rook)piece;
            rook.setWasMotioned(false);
            return;
        }
        throw new ReadGameError(Settings.lang("invalid_fen_state"));
    }
    
    private void importActivePlayer(final String activePlayer, final Game game) {
        if ("w".equals(activePlayer)) {
            game.setActivePlayer(game.getSettings().getPlayerWhite());
        }
        else {
            game.setActivePlayer(game.getSettings().getPlayerBlack());
        }
    }
    
    private void importPieces(final String piecesStateString, final Game game, final Player whitePlayer, final Player blackPlayer) throws ReadGameError {
        int currentY = Squares.SQ_8.getValue();
        final String[] rows = piecesStateString.split("/");
        if (8 != rows.length) {
            throw new ReadGameError(Settings.lang("invalid_fen_state"), Settings.lang("invalid_fen_number_of_rows"));
        }
        for (final String row : piecesStateString.split("/")) {
            int currentX = Squares.SQ_A.getValue();
            for (int i = 0; i < row.length(); ++i) {
                final String currChar = row.substring(i, i + 1);
                try {
                    final Integer currNumber = Integer.parseInt(currChar);
                    currentX += currNumber;
                }
                catch (NumberFormatException nfe) {
                    final Piece piece = PieceFactory.getPieceFromFenNotation(game.getChessboard(), currChar, whitePlayer, blackPlayer);
                    final Square square = game.getChessboard().getSquare(currentX, currentY);
                    square.setPiece(piece);
                    ++currentX;
                }
            }
            ++currentY;
        }
    }
    
    @Override
    public String exportData(final Game game) {
        final StringBuilder result = new StringBuilder();
        result.append(this.exportChessboardFields(game));
        result.append(" ");
        result.append(this.exportActivePlayer(game));
        result.append(" ");
        result.append(this.exportCastlingState(game));
        result.append(" ");
        result.append(this.exportEnPassantState(game));
        result.append(" ");
        result.append(game.getChessboard().getHalfCounter());
        result.append(this.exportFullMoveCounter(game));
        return result.toString();
    }
    
    private String exportFullMoveCounter(final Game game) {
        final int size = game.getMoves().getMoveBackStack().size();
        int counter = size / 2 + 1;
        final int counterAdd = game.getChessboard().getFullMoveCounterAdd();
        if (counterAdd > 0) {
            counter += counterAdd - 1;
        }
        return " " + counter;
    }
    
    private String exportEnPassantState(final Game game) {
        final StringBuilder result = new StringBuilder();
        final Pawn pawn = game.getChessboard().getTwoSquareMovedPawn();
        if (null != pawn) {
            final Square pawnSquare = pawn.getSquare();
            Square testSquare = null;
            if (Colors.WHITE == pawn.getPlayer().getColor()) {
                testSquare = new Square(pawnSquare.getPozX(), pawnSquare.getPozY() + 1, null);
            }
            else {
                testSquare = new Square(pawnSquare.getPozX(), pawnSquare.getPozY() - 1, null);
            }
            result.append(testSquare.getAlgebraicNotation());
        }
        else {
            result.append("-");
        }
        return result.toString();
    }
    
    private String exportCastlingState(final Game game) {
        String result = "";
        final Chessboard chessboard = game.getChessboard();
        result += this.exportCastlingOfOneColor(chessboard.getKingWhite(), chessboard, Squares.SQ_1);
        result += this.exportCastlingOfOneColor(chessboard.getKingBlack(), chessboard, Squares.SQ_8);
        return result;
    }
    
    private String exportCastlingOfOneColor(final King king, final Chessboard chessboard, final Squares squareLine) {
        String result = "";
        final Colors color = king.getPlayer().getColor();
        if (!king.getWasMotioned()) {
            Piece piece = chessboard.getSquare(Squares.SQ_A, squareLine).getPiece();
            if (piece instanceof Rook) {
                final Rook rightRook = (Rook)piece;
                if (rightRook.getWasMotioned()) {
                    result += "-";
                }
                else {
                    result += ((color == Colors.WHITE) ? "K" : "k");
                }
            }
            piece = chessboard.getSquare(Squares.SQ_H, squareLine).getPiece();
            if (piece instanceof Rook) {
                final Rook leftRook = (Rook)piece;
                if (leftRook.getWasMotioned()) {
                    result += "-";
                }
                else {
                    result += ((color == Colors.WHITE) ? "Q" : "q");
                }
            }
        }
        else {
            result += "-";
        }
        return result;
    }
    
    private String exportChessboardFields(final Game game) {
        String result = "";
        final Chessboard chessboard = game.getChessboard();
        for (int y = 0; y <= 7; ++y) {
            int emptySquares = 0;
            for (int x = 0; x <= 7; ++x) {
                final Square sq = chessboard.getSquare(x, y);
                final Piece piece = sq.getPiece();
                if (null == piece) {
                    ++emptySquares;
                }
                else {
                    if (0 != emptySquares) {
                        result += emptySquares;
                        emptySquares = 0;
                    }
                    String symbol = null;
                    if (piece instanceof Pawn) {
                        symbol = "P";
                    }
                    else {
                        symbol = piece.getSymbol();
                    }
                    result += ((piece.getPlayer().getColor() == Colors.WHITE) ? symbol.toUpperCase() : symbol.toLowerCase());
                }
            }
            if (0 != emptySquares) {
                result += emptySquares;
            }
            if (7 != y) {
                result += "/";
            }
            emptySquares = 0;
        }
        return result;
    }
    
    private String exportActivePlayer(final Game game) {
        String result = "";
        if (Colors.WHITE == game.getActivePlayer().getColor()) {
            result += "w";
        }
        else {
            result += "b";
        }
        return result;
    }
}
