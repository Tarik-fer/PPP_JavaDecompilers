// 
// Decompiled by Procyon v0.5.36
// 

package SJCE.xgui;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import SJCE.more.Actions;
import SJCE.XChessFrame;
import java.awt.Image;
import java.util.HashMap;

public class ChessTheme
{
    private String themePathFig;
    private String themePathFon;
    private static ChessTheme chessTheme;
    private HashMap<String, String> themeMap;
    private Image[] squareImages;
    private Image[] pieceImages;
    private Image[] highlightImages;
    
    public ChessTheme() {
        this.themeMap = new HashMap<String, String>();
        this.squareImages = new Image[2];
        this.pieceImages = new Image[12];
        this.highlightImages = new Image[2];
        final StringBuilder append = new StringBuilder().append("/SJCE/img/themes/fig/");
        final Actions aktion = XChessFrame.aktion;
        this.themePathFig = append.append(Actions.BoardThemeFig).append("/").toString();
        final StringBuilder append2 = new StringBuilder().append("/SJCE/img/themes/fon/");
        final Actions aktion2 = XChessFrame.aktion;
        this.themePathFon = append2.append(Actions.BoardThemeFon).append("/").toString();
        this.loadTheme();
    }
    
    public void loadTheme() {
        final StringBuilder append = new StringBuilder().append("/SJCE/img/themes/fig/");
        final Actions aktion = XChessFrame.aktion;
        this.themePathFig = append.append(Actions.BoardThemeFig).append("/").toString();
        final StringBuilder append2 = new StringBuilder().append("/SJCE/img/themes/fon/");
        final Actions aktion2 = XChessFrame.aktion;
        this.themePathFon = append2.append(Actions.BoardThemeFon).append("/").toString();
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
        return ChessTheme.chessTheme;
    }
    
    public Image getSquareImage(final int id) {
        return this.squareImages[id];
    }
    
    public Image getPieceImage(final int id) {
        return this.pieceImages[id];
    }
    
    public Image getHighlight(final int id) {
        return this.highlightImages[id];
    }
    
    public void adjustTheme(final Dimension dimension) {
        this.loadTheme();
        final int swidth = (int)(dimension.width / 8.0);
        final int sheight = (int)(dimension.height / 8.0);
        this.squareImages[0] = this.scaleImage(this.squareImages[0], swidth, sheight);
        this.squareImages[1] = this.scaleImage(this.squareImages[1], swidth, sheight);
        for (int i = 0; i < 12; ++i) {
            this.pieceImages[i] = this.scaleImage(this.pieceImages[i], swidth, sheight);
        }
        this.highlightImages[0] = this.scaleImage(this.highlightImages[0], swidth, sheight);
        this.highlightImages[1] = this.scaleImage(this.highlightImages[1], swidth, sheight);
    }
    
    private Image scaleImage(final Image image, final int swidth, final int sheight) {
        return image.getScaledInstance(swidth, sheight, 4);
    }
    
    static {
        ChessTheme.chessTheme = new ChessTheme();
    }
}
