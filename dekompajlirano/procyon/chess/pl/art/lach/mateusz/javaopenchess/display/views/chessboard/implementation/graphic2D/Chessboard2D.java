// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.display.views.chessboard.implementation.graphic2D;

import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
import java.util.Iterator;
import java.util.Set;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import pl.art.lach.mateusz.javaopenchess.core.Square;
import java.awt.image.ImageObserver;
import java.awt.Point;
import pl.art.lach.mateusz.javaopenchess.core.Chessboard;
import org.apache.log4j.Logger;
import pl.art.lach.mateusz.javaopenchess.display.views.chessboard.ChessboardView;

public class Chessboard2D extends ChessboardView
{
    private static final Logger LOG;
    protected Pieces2D pieces2D;
    private static final String[] LETTERS;
    
    public Chessboard2D(final Chessboard chessboard) {
        this.pieces2D = Pieces2D.getInstance();
        this.init(chessboard);
    }
    
    protected final void init(final Chessboard chessboard) {
        this.setChessboard(chessboard);
        this.setVisible(true);
        this.setSize(480, 480);
        this.setLocation(new Point(0, 0));
        this.setDoubleBuffered(true);
        this.drawLabels((int)this.squareHeight);
        this.resizeChessboard(480);
    }
    
    @Override
    public void unselect() {
        this.repaint();
    }
    
    @Override
    public int getChessboardWidht() {
        return this.getChessboardWidht(false);
    }
    
    @Override
    public int getChessboardHeight() {
        return this.getChessboardHeight(false);
    }
    
    @Override
    public int getChessboardWidht(final boolean includeLables) {
        return this.getHeight();
    }
    
    @Override
    public int getChessboardHeight(final boolean includeLabels) {
        if (this.getChessboard().getSettings().isRenderLabels()) {
            return this.image.getHeight(null) + this.getUpDownLabel().getHeight(null);
        }
        return this.image.getHeight(null);
    }
    
    @Override
    public int getSquareHeight() {
        final int result = (int)this.squareHeight;
        return result;
    }
    
    @Override
    public Square getSquare(int clickedX, int clickedY) {
        if (clickedX > this.getChessboardHeight() || clickedY > this.getChessboardWidht()) {
            Chessboard2D.LOG.debug("click out of chessboard.");
            return null;
        }
        if (this.getChessboard().getSettings().isRenderLabels()) {
            clickedX -= this.getUpDownLabel().getHeight(null);
            clickedY -= this.getUpDownLabel().getHeight(null);
        }
        double squareX = clickedX / this.squareHeight;
        double squareY = clickedY / this.squareHeight;
        if (squareX > (int)squareX) {
            squareX = (int)squareX + 1;
        }
        if (squareY > (int)squareY) {
            squareY = (int)squareY + 1;
        }
        Chessboard2D.LOG.debug("square_x: " + squareX + " square_y: " + squareY);
        Square result = null;
        try {
            result = this.getChessboard().getSquare((int)squareX - 1, (int)squareY - 1);
            if (this.getChessboard().getSettings().isUpsideDown()) {
                final int x = this.transposePosition(result.getPozX());
                final int y = this.transposePosition(result.getPozY());
                result = this.getChessboard().getSquare(x, y);
            }
        }
        catch (ArrayIndexOutOfBoundsException exc) {
            Chessboard2D.LOG.error("!!Array out of bounds when getting Square with Chessboard.getSquare(int,int) : " + exc.getMessage());
            return null;
        }
        return result;
    }
    
    public void paintComponent(final Graphics g) {
        final Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        final Point topLeftPoint = this.getTopLeftPoint();
        final Square[][] squares = this.getChessboard().getSquares();
        if (this.getChessboard().getSettings().isRenderLabels()) {
            this.drawLabels();
            g2d.drawImage(this.getUpDownLabel(), 0, 0, null);
            g2d.drawImage(this.getUpDownLabel(), 0, this.image.getHeight(null) + topLeftPoint.y, null);
            g2d.drawImage(this.leftRightLabel, 0, 0, null);
            g2d.drawImage(this.leftRightLabel, this.image.getHeight(null) + topLeftPoint.x, 0, null);
        }
        g2d.drawImage(this.image, topLeftPoint.x, topLeftPoint.y, null);
        this.drawPieces(squares, g2d);
        final Square activeSquare = this.getChessboard().getActiveSquare();
        if (null != activeSquare) {
            this.drawActiveSquare(activeSquare, g2d, topLeftPoint, squares);
            this.drawLegalMoves(g2d, topLeftPoint);
        }
    }
    
    @Override
    public Point getTopLeftPoint() {
        if (this.getChessboard().getSettings().isRenderLabels()) {
            return new Point(this.topLeft.x + this.getUpDownLabel().getHeight(null), this.topLeft.y + this.getUpDownLabel().getHeight(null));
        }
        return this.topLeft;
    }
    
    @Override
    public final void resizeChessboard(int height) {
        if (0 != height) {
            BufferedImage resized = new BufferedImage(height, height, 3);
            Graphics g = resized.createGraphics();
            g.drawImage(ChessboardView.orgImage, 0, 0, height, height, null);
            g.dispose();
            if (!this.getChessboard().getSettings().isRenderLabels()) {
                height += 2 * this.getUpDownLabel().getHeight(null);
            }
            this.image = resized.getScaledInstance(height, height, 0);
            resized = new BufferedImage(height, height, 3);
            g = resized.createGraphics();
            g.drawImage(this.image, 0, 0, height, height, null);
            g.dispose();
            this.squareHeight = (float)(height / 8);
            if (this.getChessboard().getSettings().isRenderLabels()) {
                height += 2 * this.getUpDownLabel().getHeight(null);
            }
            this.setSize(height, height);
            resized = new BufferedImage((int)this.squareHeight, (int)this.squareHeight, 3);
            g = resized.createGraphics();
            g.drawImage(ChessboardView.orgAbleSquare, 0, 0, (int)this.squareHeight, (int)this.squareHeight, null);
            g.dispose();
            ChessboardView.ableSquare = resized.getScaledInstance((int)this.squareHeight, (int)this.squareHeight, 0);
            resized = new BufferedImage((int)this.squareHeight, (int)this.squareHeight, 3);
            g = resized.createGraphics();
            g.drawImage(ChessboardView.orgSelSquare, 0, 0, (int)this.squareHeight, (int)this.squareHeight, null);
            g.dispose();
            ChessboardView.selSquare = resized.getScaledInstance((int)this.squareHeight, (int)this.squareHeight, 0);
            this.pieces2D.resize(this.getSquareHeight());
            this.drawLabels();
        }
    }
    
    protected void drawLabels() {
        this.drawLabels((int)this.squareHeight);
    }
    
    protected final void drawLabels(final int squareHeight) {
        final int minLabelHeight = 20;
        int labelHeight = (int)Math.ceil(squareHeight / 4);
        labelHeight = ((labelHeight < minLabelHeight) ? minLabelHeight : labelHeight);
        final int labelWidth = (int)Math.ceil(squareHeight * 8 + 2 * labelHeight);
        BufferedImage uDL = new BufferedImage(labelWidth + minLabelHeight, labelHeight, 5);
        Graphics2D graph2D = uDL.createGraphics();
        graph2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graph2D.setColor(Color.white);
        graph2D.fillRect(0, 0, labelWidth + minLabelHeight, labelHeight);
        graph2D.setColor(Color.black);
        graph2D.setFont(new Font("Arial", 1, 12));
        int addX = squareHeight / 2;
        if (this.getChessboard().getSettings().isRenderLabels()) {
            addX += labelHeight;
        }
        if (!this.getChessboard().getSettings().isUpsideDown()) {
            for (int i = 1; i <= Chessboard2D.LETTERS.length; ++i) {
                graph2D.drawString(Chessboard2D.LETTERS[i - 1], squareHeight * (i - 1) + addX, 10 + labelHeight / 3);
            }
        }
        else {
            for (int j = 1, k = Chessboard2D.LETTERS.length; k > 0; --k, ++j) {
                graph2D.drawString(Chessboard2D.LETTERS[k - 1], squareHeight * (j - 1) + addX, 10 + labelHeight / 3);
            }
        }
        graph2D.dispose();
        this.setUpDownLabel(uDL);
        uDL = new BufferedImage(labelHeight, labelWidth + minLabelHeight, 5);
        graph2D = uDL.createGraphics();
        graph2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graph2D.setColor(Color.white);
        graph2D.fillRect(0, 0, labelHeight, labelWidth + minLabelHeight);
        graph2D.setColor(Color.black);
        graph2D.setFont(new Font("Arial", 1, 12));
        if (this.getChessboard().getSettings().isUpsideDown()) {
            for (int i = 1; i <= 8; ++i) {
                graph2D.drawString(Integer.toString(i), 3 + labelHeight / 3, squareHeight * (i - 1) + addX);
            }
        }
        else {
            for (int j = 1, k = 8; k > 0; --k, ++j) {
                graph2D.drawString(Integer.toString(k), 3 + labelHeight / 3, squareHeight * (j - 1) + addX);
            }
        }
        graph2D.dispose();
        this.leftRightLabel = uDL;
    }
    
    private void drawLegalMoves(final Graphics2D g2d, final Point topLeftPoint) {
        if (this.getChessboard().getSettings().isDisplayLegalMovesEnabled()) {
            final Set<Square> moves = this.getChessboard().getMoves();
            if (null != moves) {
                for (final Square sq : moves) {
                    int ableSquarePosX = sq.getPozX();
                    int ableSquarePosY = sq.getPozY();
                    if (this.getChessboard().getSettings().isUpsideDown()) {
                        ableSquarePosX = this.transposePosition(ableSquarePosX);
                        ableSquarePosY = this.transposePosition(ableSquarePosY);
                    }
                    g2d.drawImage(Chessboard2D.ableSquare, ableSquarePosX * (int)this.squareHeight + topLeftPoint.x, ableSquarePosY * (int)this.squareHeight + topLeftPoint.y, null);
                }
            }
        }
    }
    
    private void drawActiveSquare(final Square activeSquare, final Graphics2D g2d, final Point topLeftPoint, final Square[][] squares) {
        int activeSquareX = activeSquare.getPozX();
        int activeSquareY = activeSquare.getPozY();
        if (this.getChessboard().getSettings().isUpsideDown()) {
            activeSquareX = this.transposePosition(activeSquareX);
            activeSquareY = this.transposePosition(activeSquareY);
        }
        g2d.drawImage(Chessboard2D.selSquare, activeSquareX * (int)this.squareHeight + topLeftPoint.x, activeSquareY * (int)this.squareHeight + topLeftPoint.y, null);
        final Square tmpSquare = squares[activeSquare.getPozX()][activeSquare.getPozY()];
        if (null != tmpSquare.piece) {
            final Set<Square> moves = tmpSquare.getPiece().getAllMoves();
            this.getChessboard().setMoves(moves);
        }
    }
    
    private void drawPieces(final Square[][] squares, final Graphics2D g2d) {
        for (int i = 0; i < 8; ++i) {
            for (int y = 0; y < 8; ++y) {
                if (squares[i][y].getPiece() != null) {
                    int drawPosI = i;
                    int drawPosY = y;
                    if (this.getChessboard().getSettings().isUpsideDown()) {
                        drawPosI = this.transposePosition(drawPosI);
                        drawPosY = this.transposePosition(drawPosY);
                    }
                    final Piece piece = squares[i][y].getPiece();
                    final Image pieceImage = this.pieces2D.getImage(piece.getPlayer().getColor(), piece);
                    Pieces2D.draw(this, squares[i][y].getPiece(), drawPosI, drawPosY, g2d, pieceImage);
                }
            }
        }
    }
    
    static {
        LOG = Logger.getLogger(Chessboard2D.class);
        LETTERS = new String[] { "a", "b", "c", "d", "e", "f", "g", "h" };
    }
}
