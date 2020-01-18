// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.display.views.chessboard;

import pl.art.lach.mateusz.javaopenchess.utils.GUI;
import java.awt.Graphics;
import pl.art.lach.mateusz.javaopenchess.core.Square;
import pl.art.lach.mateusz.javaopenchess.core.Chessboard;
import java.awt.Point;
import java.awt.Image;
import javax.swing.JPanel;

public abstract class ChessboardView extends JPanel
{
    private static final int CENTER_POSITION = 3;
    protected static final Image orgImage;
    protected Image image;
    protected static final Image orgSelSquare;
    protected static Image selSquare;
    protected static final Image orgAbleSquare;
    protected static Image ableSquare;
    private Image upDownLabel;
    protected Image leftRightLabel;
    protected Point topLeft;
    protected float squareHeight;
    public static final int imgX = 5;
    public static final int imgY = 5;
    public static final int imgWidht = 480;
    public static final int imgHeight = 480;
    private Chessboard chessboard;
    
    public ChessboardView() {
        this.image = ChessboardView.orgImage;
        this.upDownLabel = null;
        this.leftRightLabel = null;
        this.topLeft = new Point(0, 0);
    }
    
    public abstract Square getSquare(final int p0, final int p1);
    
    public abstract void unselect();
    
    public abstract int getChessboardWidht();
    
    public abstract int getChessboardHeight();
    
    public abstract int getChessboardWidht(final boolean p0);
    
    public abstract int getChessboardHeight(final boolean p0);
    
    public abstract int getSquareHeight();
    
    public abstract void resizeChessboard(final int p0);
    
    public abstract Point getTopLeftPoint();
    
    @Override
    public void update(final Graphics g) {
        this.repaint();
    }
    
    public Chessboard getChessboard() {
        return this.chessboard;
    }
    
    public void setChessboard(final Chessboard chessboard) {
        this.chessboard = chessboard;
    }
    
    public Image getUpDownLabel() {
        return this.upDownLabel;
    }
    
    public void setUpDownLabel(final Image upDownLabel) {
        this.upDownLabel = upDownLabel;
    }
    
    public int transposePosition(final int squarePosition) {
        return this.transposePosition(squarePosition, 3);
    }
    
    public int transposePosition(final int squarePosition, final int centerPosition) {
        return -(squarePosition - centerPosition) + centerPosition + 1;
    }
    
    static {
        orgImage = GUI.loadImage("chessboard.png");
        orgSelSquare = GUI.loadImage("sel_square.png");
        ChessboardView.selSquare = ChessboardView.orgSelSquare;
        orgAbleSquare = GUI.loadImage("able_square.png");
        ChessboardView.ableSquare = ChessboardView.orgAbleSquare;
    }
}
