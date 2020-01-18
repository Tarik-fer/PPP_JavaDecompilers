// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.core.pieces;

import java.util.Collection;
import pl.art.lach.mateusz.javaopenchess.core.Colors;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import pl.art.lach.mateusz.javaopenchess.core.pieces.traits.behaviors.Behavior;
import java.util.Set;
import pl.art.lach.mateusz.javaopenchess.core.players.Player;
import pl.art.lach.mateusz.javaopenchess.core.Square;
import pl.art.lach.mateusz.javaopenchess.core.Chessboard;
import org.apache.log4j.Logger;

public abstract class Piece
{
    private static final Logger LOG;
    protected Chessboard chessboard;
    protected Square square;
    protected Player player;
    protected String name;
    protected String symbol;
    protected short value;
    protected Set<Behavior> behaviors;
    
    public Piece(final Chessboard chessboard, final Player player) {
        this.value = 0;
        this.behaviors = new HashSet<Behavior>();
        this.chessboard = chessboard;
        this.player = player;
        this.name = this.getClass().getSimpleName();
    }
    
    public short getValue() {
        return this.value;
    }
    
    public final void addBehavior(final Behavior behavior) {
        this.behaviors.add(behavior);
    }
    
    public final Set<Behavior> getBehaviors() {
        return Collections.unmodifiableSet((Set<? extends Behavior>)this.behaviors);
    }
    
    public void setBehaviors(final Set<Behavior> behaviors) {
        this.behaviors = behaviors;
    }
    
    void clean() {
    }
    
    boolean canMove(final Square square, final ArrayList allmoves) {
        final ArrayList moves = allmoves;
        for (final Square sq : moves) {
            if (sq == square) {
                return true;
            }
        }
        return false;
    }
    
    public boolean canMove(final int newX, final int newY) {
        boolean result = false;
        final Square[][] squares = this.chessboard.getSquares();
        if (!this.isOut(newX, newY) && this.checkPiece(newX, newY)) {
            if (this.getPlayer().getColor() == Colors.WHITE) {
                if (this.chessboard.getKingWhite().willBeSafeAfterMove(this.square, squares[newX][newY])) {
                    result = true;
                }
            }
            else if (this.chessboard.getKingBlack().willBeSafeAfterMove(this.square, squares[newX][newY])) {
                result = true;
            }
        }
        return result;
    }
    
    public Set<Square> getAllMoves() {
        final Set<Square> moves = new HashSet<Square>();
        for (final Behavior behavior : this.behaviors) {
            moves.addAll(behavior.getLegalMoves());
        }
        return moves;
    }
    
    public Set<Square> getSquaresInRange() {
        final Set<Square> moves = new HashSet<Square>();
        for (final Behavior behavior : this.behaviors) {
            moves.addAll(behavior.getSquaresInRange());
        }
        return moves;
    }
    
    public boolean isOut(final int x, final int y) {
        return x < 0 || x > 7 || y < 0 || y > 7;
    }
    
    public boolean checkPiece(final int x, final int y) {
        if (this.getChessboard().getSquares()[x][y].piece != null && this.getChessboard().getSquares()[x][y].getPiece().getName().equals("King")) {
            return false;
        }
        final Piece piece = this.getChessboard().getSquares()[x][y].piece;
        return piece == null || piece.getPlayer() != this.getPlayer();
    }
    
    public boolean otherOwner(final int x, final int y) {
        final Square sq = this.getChessboard().getSquare(x, y);
        return sq.piece != null && this.getPlayer() != sq.getPiece().getPlayer();
    }
    
    public String getSymbol() {
        return this.symbol;
    }
    
    public Chessboard getChessboard() {
        return this.chessboard;
    }
    
    public void setChessboard(final Chessboard chessboard) {
        this.chessboard = chessboard;
    }
    
    public Square getSquare() {
        return this.square;
    }
    
    public void setSquare(final Square square) {
        this.square = square;
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public void setPlayer(final Player player) {
        this.player = player;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    static {
        LOG = Logger.getLogger(Piece.class);
    }
}
