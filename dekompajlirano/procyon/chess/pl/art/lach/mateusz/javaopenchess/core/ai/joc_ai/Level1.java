// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.core.ai.joc_ai;

import java.util.Iterator;
import java.util.List;
import pl.art.lach.mateusz.javaopenchess.core.Chessboard;
import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Queen;
import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Pawn;
import java.util.Collection;
import pl.art.lach.mateusz.javaopenchess.core.Square;
import java.util.Random;
import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
import java.util.ArrayList;
import pl.art.lach.mateusz.javaopenchess.core.moves.Move;
import pl.art.lach.mateusz.javaopenchess.core.Game;
import pl.art.lach.mateusz.javaopenchess.core.ai.AI;

public class Level1 implements AI
{
    @Override
    public Move getMove(final Game game, final Move lastMove) {
        final Chessboard chessboard = game.getChessboard();
        final List<Piece> pieces = chessboard.getAllPieces(game.getActivePlayer().getColor());
        final List<Piece> moveAblePieces = new ArrayList<Piece>();
        for (final Piece piece : pieces) {
            if (0 < piece.getAllMoves().size()) {
                moveAblePieces.add(piece);
            }
        }
        int size = moveAblePieces.size();
        final Random rand = new Random();
        int random = rand.nextInt(size);
        final Piece piece2 = moveAblePieces.get(random);
        Piece promotedPiece = null;
        size = piece2.getAllMoves().size();
        random = rand.nextInt(size);
        final List<Square> squares = new ArrayList<Square>(piece2.getAllMoves());
        final Square sq = squares.get(random);
        if (piece2 instanceof Pawn && Pawn.canBePromoted(sq)) {
            promotedPiece = new Queen(chessboard, game.getActivePlayer());
        }
        return new Move(piece2.getSquare(), sq, piece2, sq.getPiece(), promotedPiece);
    }
}
