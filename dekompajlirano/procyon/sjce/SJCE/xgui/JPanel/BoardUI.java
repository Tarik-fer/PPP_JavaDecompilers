// 
// Decompiled by Procyon v0.5.36
// 

package SJCE.xgui.JPanel;

import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Iterator;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import SJCE.xgui.Utility;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.awt.Dimension;
import SJCE.xgui.PiecesUI;
import SJCE.xgui.ChessTheme;
import javax.swing.JPanel;

public class BoardUI extends JPanel
{
    public static final int FILE_RANK = 8;
    public static final int SQUARE_COUNT = 64;
    public static final int OFF_BOARD = -10;
    public static final int NO_SQUARE = -1;
    public static final int SQUARE_WHITE = 0;
    public static final int SQUARE_BLACK = 1;
    public static final int HIGHLIGHT_NONE = -1;
    public static final int HIGHLIGHT_SELECT = 0;
    public static final int HIGHLIGHT_MOVE = 1;
    public ChessTheme chessTheme;
    private PiecesUI piecesUI;
    private Dimension squareSize;
    private HashMap<Integer, Integer> highlightMap;
    public BufferedImage boardImage;
    
    public BoardUI() {
        this.chessTheme = ChessTheme.getChessTheme();
        this.piecesUI = new PiecesUI();
        this.squareSize = new Dimension();
        this.highlightMap = new HashMap<Integer, Integer>(10);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                BoardUI.this.squareSize.setSize((int)(BoardUI.this.getWidth() / 8.0), (int)(BoardUI.this.getHeight() / 8.0));
                BoardUI.this.chessTheme.adjustTheme(BoardUI.this.getSize());
                BoardUI.this.boardImage = new BufferedImage(BoardUI.this.getWidth(), BoardUI.this.getHeight(), 2);
                BoardUI.this.offPaint();
            }
        });
        this.setPreferredSize(new Dimension(400, 400));
        this.setMinimumSize(new Dimension(400, 400));
        this.setSize(400, 400);
        this.setBoard(Utility.INITIAL_BOARD);
    }
    
    public void offPaint() {
        if (this.boardImage != null) {
            this.offPaint(0, 0, this.boardImage.getWidth(), this.boardImage.getHeight());
        }
    }
    
    private void offPaint(final int x, final int y, final int width, final int height) {
        if (this.boardImage == null) {
            return;
        }
        final Graphics2D g2d = this.boardImage.createGraphics();
        g2d.clipRect(x, y, width, height);
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if ((i + j) % 2 == 0) {
                    g2d.drawImage(this.chessTheme.getSquareImage(1), i * this.squareSize.width, (8 - j - 1) * this.squareSize.height, null);
                }
                else {
                    g2d.drawImage(this.chessTheme.getSquareImage(0), i * this.squareSize.width, (8 - j - 1) * this.squareSize.height, null);
                }
            }
        }
        for (final int key : this.highlightMap.keySet()) {
            final Point point = this.getSquare(key);
            g2d.drawImage(this.chessTheme.getHighlight(this.highlightMap.get(key)), point.x, point.y, null);
        }
        final int[] board = this.piecesUI.getBoard();
        for (int k = 0; k < board.length; ++k) {
            if (board[k] != -1) {
                final Point point = this.getSquare(k);
                g2d.drawImage(this.chessTheme.getPieceImage(board[k]), point.x, point.y, null);
            }
        }
        this.repaint(x, y, width, height);
    }
    
    @Override
    public void paint(final Graphics g) {
        super.paint(g);
        g.drawImage(this.boardImage, 0, 0, this);
    }
    
    public void addHighlight(final int highlight, final int square) {
        this.highlightMap.put(square, highlight);
        this.update(square);
    }
    
    public void removeHighlight(final int square) {
        this.highlightMap.remove(square);
        this.update(square);
    }
    
    public Point getSquare(final int square) {
        return new Point(square % 8 * this.squareSize.width, (8 - square / 8 - 1) * this.squareSize.height);
    }
    
    public int getSquare(final Point point) {
        final double file = point.x / (double)this.squareSize.width;
        final double rank = point.y / (double)this.squareSize.height;
        if (file < 0.0 || file > 8.0) {
            return -10;
        }
        if (rank < 0.0 || rank > 8.0) {
            return -10;
        }
        return (int)file + (8 - (int)rank - 1) * 8;
    }
    
    public void update(final int square) {
        final Point point = this.getSquare(square);
        this.offPaint(point.x, point.y, this.squareSize.width, this.squareSize.height);
    }
    
    public void update(final int[] squares) {
        for (final int square : squares) {
            this.update(square);
        }
    }
    
    public int[] getBoard() {
        return this.piecesUI.getBoard();
    }
    
    public void setBoard(final int[] board) {
        this.piecesUI.setBoard(board);
        this.offPaint();
    }
    
    public int getPiece(final int square) {
        return this.piecesUI.getPiece(square);
    }
    
    public void setPiece(final int piece, final int square, final boolean repaint) {
        this.piecesUI.setPiece(piece, square);
        if (repaint) {
            this.update(square);
        }
    }
    
    public void setPiece(final int piece, final int square) {
        this.setPiece(piece, square, true);
    }
    
    public void drawImage(final Image image, final Point point) {
        final Graphics2D g2d = this.boardImage.createGraphics();
        g2d.drawImage(image, point.x, point.y, null);
        this.repaint(point.x, point.y, image.getWidth(null), image.getHeight(null));
    }
    
    public void clearImage(final Rectangle rect) {
        this.offPaint(rect.x, rect.y, rect.width, rect.height);
    }
    
    public void removePiece(final int square, final boolean repaint) {
        this.piecesUI.removePiece(square);
        if (repaint) {
            this.update(square);
        }
    }
    
    public void removePiece(final int square) {
        this.removePiece(square, true);
    }
    
    public static int getSquare(final int file, final int rank) {
        return rank * 8 + file;
    }
    
    public static int getFile(final int square) {
        return square % 8;
    }
    
    public static int getRank(final int square) {
        return square / 8;
    }
    
    public static int getRankDistance(final int square1, final int square2) {
        return getRank(square1) - getRank(square2);
    }
    
    public static int getFileDistance(final int square1, final int square2) {
        return getFile(square1) - getFile(square2);
    }
    
    public ChessTheme getChessTheme() {
        return this.chessTheme;
    }
}
