// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.core;

import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;

public class Square
{
    protected int pozX;
    protected int pozY;
    public Piece piece;
    
    public Square(final int pozX, final int pozY, final Piece piece) {
        this.piece = null;
        this.pozX = pozX;
        this.pozY = pozY;
        this.piece = piece;
    }
    
    public Square(final Square square) {
        this.piece = null;
        this.pozX = square.pozX;
        this.pozY = square.pozY;
        this.piece = square.piece;
    }
    
    public Square clone(final Square square) {
        return new Square(square);
    }
    
    public void setPiece(final Piece piece) {
        this.piece = piece;
        if (null != this.piece) {
            this.piece.setSquare(this);
        }
    }
    
    public int getPozX() {
        return this.pozX;
    }
    
    public void setPozX(final int pozX) {
        this.pozX = pozX;
    }
    
    public int getPozY() {
        return this.pozY;
    }
    
    public void setPozY(final int pozY) {
        this.pozY = pozY;
    }
    
    public Piece getPiece() {
        return this.piece;
    }
    
    public boolean isEmptyOrSamePiece(final Piece piece) {
        return null == this.piece || this.piece == piece;
    }
    
    public String getAlgebraicNotation() {
        final String letter = String.valueOf((char)(this.pozX + 97));
        final String result = letter + (Math.abs(7 - this.pozY) + 1);
        return result;
    }
}
