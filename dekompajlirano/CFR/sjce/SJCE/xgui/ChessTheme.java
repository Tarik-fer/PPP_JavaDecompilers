/*
 * Decompiled with CFR 0.148.
 */
package SJCE.xgui;

import SJCE.more.Actions;
import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;
import java.util.HashMap;
import javax.swing.ImageIcon;

public class ChessTheme {
    private String themePathFig;
    private String themePathFon;
    private static ChessTheme chessTheme = new ChessTheme();
    private HashMap<String, String> themeMap = new HashMap();
    private Image[] squareImages = new Image[2];
    private Image[] pieceImages = new Image[12];
    private Image[] highlightImages = new Image[2];

    public ChessTheme() {
        this.themePathFig = "/SJCE/img/themes/fig/" + Actions.BoardThemeFig + "/";
        this.themePathFon = "/SJCE/img/themes/fon/" + Actions.BoardThemeFon + "/";
        this.loadTheme();
    }

    public void loadTheme() {
        this.themePathFig = "/SJCE/img/themes/fig/" + Actions.BoardThemeFig + "/";
        this.themePathFon = "/SJCE/img/themes/fon/" + Actions.BoardThemeFon + "/";
        this.squareImages[0] = new ImageIcon(this.getClass().getResource(this.themePathFon + "ws.png")).getImage();
        this.squareImages[1] = new ImageIcon(this.getClass().getResource(this.themePathFon + "bs.png")).getImage();
        this.pieceImages[0] = new ImageIcon(this.getClass().getResource(this.themePathFig + "wp.png")).getImage();
        this.pieceImages[1] = new ImageIcon(this.getClass().getResource(this.themePathFig + "wk.png")).getImage();
        this.pieceImages[2] = new ImageIcon(this.getClass().getResource(this.themePathFig + "wo.png")).getImage();
        this.pieceImages[3] = new ImageIcon(this.getClass().getResource(this.themePathFig + "wl.png")).getImage();
        this.pieceImages[4] = new ImageIcon(this.getClass().getResource(this.themePathFig + "wf.png")).getImage();
        this.pieceImages[5] = new ImageIcon(this.getClass().getResource(this.themePathFig + "wg.png")).getImage();
        this.pieceImages[6] = new ImageIcon(this.getClass().getResource(this.themePathFig + "bp.png")).getImage();
        this.pieceImages[7] = new ImageIcon(this.getClass().getResource(this.themePathFig + "bk.png")).getImage();
        this.pieceImages[8] = new ImageIcon(this.getClass().getResource(this.themePathFig + "bo.png")).getImage();
        this.pieceImages[9] = new ImageIcon(this.getClass().getResource(this.themePathFig + "bl.png")).getImage();
        this.pieceImages[10] = new ImageIcon(this.getClass().getResource(this.themePathFig + "bf.png")).getImage();
        this.pieceImages[11] = new ImageIcon(this.getClass().getResource(this.themePathFig + "bg.png")).getImage();
        this.highlightImages[0] = new ImageIcon(this.getClass().getResource("/SJCE/img/themes/highlight-select.png")).getImage();
        this.highlightImages[1] = new ImageIcon(this.getClass().getResource("/SJCE/img/themes/highlight-move.png")).getImage();
        this.themeMap.clear();
    }

    public static ChessTheme getChessTheme() {
        return chessTheme;
    }

    public Image getSquareImage(int id) {
        return this.squareImages[id];
    }

    public Image getPieceImage(int id) {
        return this.pieceImages[id];
    }

    public Image getHighlight(int id) {
        return this.highlightImages[id];
    }

    public void adjustTheme(Dimension dimension) {
        this.loadTheme();
        int swidth = (int)((double)dimension.width / 8.0);
        int sheight = (int)((double)dimension.height / 8.0);
        this.squareImages[0] = this.scaleImage(this.squareImages[0], swidth, sheight);
        this.squareImages[1] = this.scaleImage(this.squareImages[1], swidth, sheight);
        for (int i = 0; i < 12; ++i) {
            this.pieceImages[i] = this.scaleImage(this.pieceImages[i], swidth, sheight);
        }
        this.highlightImages[0] = this.scaleImage(this.highlightImages[0], swidth, sheight);
        this.highlightImages[1] = this.scaleImage(this.highlightImages[1], swidth, sheight);
    }

    private Image scaleImage(Image image, int swidth, int sheight) {
        return image.getScaledInstance(swidth, sheight, 4);
    }
}

