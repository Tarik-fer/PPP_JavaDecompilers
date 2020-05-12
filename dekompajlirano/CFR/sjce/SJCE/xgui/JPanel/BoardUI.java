/*
 * Decompiled with CFR 0.148.
 */
package SJCE.xgui.JPanel;

import SJCE.xgui.ChessTheme;
import SJCE.xgui.PiecesUI;
import SJCE.xgui.Utility;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.HashMap;
import java.util.Set;
import javax.swing.JPanel;

public class BoardUI
extends JPanel {
    public static final int FILE_RANK = 8;
    public static final int SQUARE_COUNT = 64;
    public static final int OFF_BOARD = -10;
    public static final int NO_SQUARE = -1;
    public static final int SQUARE_WHITE = 0;
    public static final int SQUARE_BLACK = 1;
    public static final int HIGHLIGHT_NONE = -1;
    public static final int HIGHLIGHT_SELECT = 0;
    public static final int HIGHLIGHT_MOVE = 1;
    public ChessTheme chessTheme = ChessTheme.getChessTheme();
    private PiecesUI piecesUI = new PiecesUI();
    private Dimension squareSize = new Dimension();
    private HashMap<Integer, Integer> highlightMap = new HashMap(10);
    public BufferedImage boardImage;

    public BoardUI() {
        this.addComponentListener(new ComponentAdapter(){

            @Override
            public void componentResized(ComponentEvent e) {
                BoardUI.this.squareSize.setSize((int)((double)BoardUI.this.getWidth() / 8.0), (int)((double)BoardUI.this.getHeight() / 8.0));
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

    private void offPaint(int x, int y, int width, int height) {
        Point point;
        if (this.boardImage == null) {
            return;
        }
        Graphics2D g2d = this.boardImage.createGraphics();
        g2d.clipRect(x, y, width, height);
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if ((i + j) % 2 == 0) {
                    g2d.drawImage(this.chessTheme.getSquareImage(1), i * this.squareSize.width, (8 - j - 1) * this.squareSize.height, null);
                    continue;
                }
                g2d.drawImage(this.chessTheme.getSquareImage(0), i * this.squareSize.width, (8 - j - 1) * this.squareSize.height, null);
            }
        }
        for (int key : this.highlightMap.keySet()) {
            point = this.getSquare(key);
            g2d.drawImage(this.chessTheme.getHighlight(this.highlightMap.get(key)), point.x, point.y, null);
        }
        int[] board = this.piecesUI.getBoard();
        for (int i = 0; i < board.length; ++i) {
            if (board[i] == -1) continue;
            point = this.getSquare(i);
            g2d.drawImage(this.chessTheme.getPieceImage(board[i]), point.x, point.y, null);
        }
        this.repaint(x, y, width, height);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(this.boardImage, 0, 0, this);
    }

    public void addHighlight(int highlight, int square) {
        this.highlightMap.put(square, highlight);
        this.update(square);
    }

    public void removeHighlight(int square) {
        this.highlightMap.remove(square);
        this.update(square);
    }

    public Point getSquare(int square) {
        return new Point(square % 8 * this.squareSize.width, (8 - square / 8 - 1) * this.squareSize.height);
    }

    public int getSquare(Point point) {
        double file = (double)point.x / (double)this.squareSize.width;
        double rank = (double)point.y / (double)this.squareSize.height;
        if (file < 0.0 || file > 8.0) {
            return -10;
        }
        if (rank < 0.0 || rank > 8.0) {
            return -10;
        }
        return (int)file + (8 - (int)rank - 1) * 8;
    }

    public void update(int square) {
        Point point = this.getSquare(square);
        this.offPaint(point.x, point.y, this.squareSize.width, this.squareSize.height);
    }

    public void update(int[] squares) {
        for (int square : squares) {
            this.update(square);
        }
    }

    public int[] getBoard() {
        return this.piecesUI.getBoard();
    }

    public void setBoard(int[] board) {
        this.piecesUI.setBoard(board);
        this.offPaint();
    }

    public int getPiece(int square) {
        return this.piecesUI.getPiece(square);
    }

    public void setPiece(int piece, int square, boolean repaint) {
        this.piecesUI.setPiece(piece, square);
        if (repaint) {
            this.update(square);
        }
    }

    public void setPiece(int piece, int square) {
        this.setPiece(piece, square, true);
    }

    public void drawImage(Image image, Point point) {
        Graphics2D g2d = this.boardImage.createGraphics();
        g2d.drawImage(image, point.x, point.y, null);
        this.repaint(point.x, point.y, image.getWidth(null), image.getHeight(null));
    }

    public void clearImage(Rectangle rect) {
        this.offPaint(rect.x, rect.y, rect.width, rect.height);
    }

    public void removePiece(int square, boolean repaint) {
        this.piecesUI.removePiece(square);
        if (repaint) {
            this.update(square);
        }
    }

    public void removePiece(int square) {
        this.removePiece(square, true);
    }

    public static int getSquare(int file, int rank) {
        return rank * 8 + file;
    }

    public static int getFile(int square) {
        return square % 8;
    }

    public static int getRank(int square) {
        return square / 8;
    }

    public static int getRankDistance(int square1, int square2) {
        return BoardUI.getRank(square1) - BoardUI.getRank(square2);
    }

    public static int getFileDistance(int square1, int square2) {
        return BoardUI.getFile(square1) - BoardUI.getFile(square2);
    }

    public ChessTheme getChessTheme() {
        return this.chessTheme;
    }

}

