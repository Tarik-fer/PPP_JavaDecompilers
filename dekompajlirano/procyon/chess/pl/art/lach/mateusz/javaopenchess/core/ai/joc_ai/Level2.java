// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.core.ai.joc_ai;

import java.util.Iterator;
import java.util.List;
import pl.art.lach.mateusz.javaopenchess.core.Chessboard;
import java.util.Random;
import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Queen;
import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Pawn;
import java.util.Collection;
import pl.art.lach.mateusz.javaopenchess.core.Square;
import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
import java.util.ArrayList;
import pl.art.lach.mateusz.javaopenchess.core.moves.Move;
import pl.art.lach.mateusz.javaopenchess.core.Game;
import pl.art.lach.mateusz.javaopenchess.core.ai.AI;

public class Level2 implements AI
{
    @Override
    public Move getMove(final Game game, final Move lastMove) {
        final Chessboard chessboard = game.getChessboard();
        final List<Piece> pieces = chessboard.getAllPieces(game.getActivePlayer().getColor());
        int bestMark = 0;
        final List<Move> movesList = new ArrayList<Move>();
        for (final Piece piece : pieces) {
            if (0 < piece.getAllMoves().size()) {
                final List<Square> squares = new ArrayList<Square>(piece.getAllMoves());
                if (squares.size() <= 0) {
                    continue;
                }
                for (final Square sq : squares) {
                    final Piece takenPiece = sq.getPiece();
                    Piece promotedPiece = null;
                    if (piece instanceof Pawn && Pawn.canBePromoted(sq)) {
                        promotedPiece = new Queen(chessboard, game.getActivePlayer());
                    }
                    final Move move = new Move(piece.getSquare(), sq, piece, sq.getPiece(), promotedPiece);
                    int currentMark = 0;
                    if (null != takenPiece) {
                        currentMark = takenPiece.getValue();
                    }
                    if (currentMark > bestMark) {
                        movesList.clear();
                        movesList.add(move);
                        bestMark = currentMark;
                    }
                    else {
                        if (currentMark != bestMark) {
                            continue;
                        }
                        movesList.add(move);
                    }
                }
            }
        }
        final int size = movesList.size();
        final Random rand = new Random();
        return movesList.get(rand.nextInt(size));
    }
}
