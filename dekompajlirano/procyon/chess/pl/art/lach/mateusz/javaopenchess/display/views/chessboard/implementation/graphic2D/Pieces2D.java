// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.display.views.chessboard.implementation.graphic2D;

import java.util.Collection;
import java.util.Arrays;
import java.awt.Point;
import java.awt.image.ImageObserver;
import java.awt.image.BufferedImage;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import pl.art.lach.mateusz.javaopenchess.display.views.chessboard.ChessboardView;
import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Bishop;
import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.King;
import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Rook;
import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Queen;
import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Knight;
import pl.art.lach.mateusz.javaopenchess.utils.GUI;
import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Pawn;
import java.util.Iterator;
import java.util.HashMap;
import java.util.TreeSet;
import java.awt.Image;
import pl.art.lach.mateusz.javaopenchess.core.Colors;
import java.util.Map;
import org.apache.log4j.Logger;

public class Pieces2D
{
    private static final Logger LOG;
    private static final String FILE_EXT = ".png";
    protected Map<Colors, Map<String, Image>> currentImageSet;
    protected static Pieces2D instance;
    protected static int currentSize;
    protected static TreeSet<Integer> setsSizes;
    protected static Map<Integer, Map<Colors, Map<String, Image>>> imageSets;
    
    public Map<Colors, Map<String, Image>> getCurrentImageSet() {
        return this.currentImageSet;
    }
    
    public void setCurrentImageSet(final Map<Colors, Map<String, Image>> aImages) {
        this.currentImageSet = aImages;
    }
    
    public static int getCurrentSize() {
        return Pieces2D.currentSize;
    }
    
    public static void setCurrentSize(final int aCurrentSize) {
        Pieces2D.currentSize = aCurrentSize;
    }
    
    protected Pieces2D() {
        this.currentImageSet = new HashMap<Colors, Map<String, Image>>();
        this.currentImageSet = Pieces2D.imageSets.get(Pieces2D.currentSize);
    }
    
    public final void resize(final int squareSize) {
        if (null != Pieces2D.setsSizes) {
            final int closest = this.getSizeToLoad(squareSize);
            if (Pieces2D.currentSize != closest) {
                Pieces2D.currentSize = closest;
                this.currentImageSet = Pieces2D.imageSets.get(Pieces2D.currentSize);
            }
        }
    }
    
    private static Map<Integer, Map<Colors, Map<String, Image>>> initImagesSets() {
        final Map<Integer, Map<Colors, Map<String, Image>>> result = new HashMap<Integer, Map<Colors, Map<String, Image>>>();
        for (final int size : Pieces2D.setsSizes) {
            final Map<Colors, Map<String, Image>> localImages = new HashMap<Colors, Map<String, Image>>();
            localImages.put(Colors.BLACK, getPieceMap(Colors.BLACK, size));
            localImages.put(Colors.WHITE, getPieceMap(Colors.WHITE, size));
            result.put(size, localImages);
        }
        return result;
    }
    
    private static Map<String, Image> getPieceMap(final Colors color, final int size) {
        final Map<String, Image> result = new HashMap<String, Image>();
        result.put(Pawn.class.getName(), GUI.loadPieceImage(Pawn.class.getSimpleName(), color, size, ".png"));
        result.put(Knight.class.getName(), GUI.loadPieceImage(Knight.class.getSimpleName(), color, size, ".png"));
        result.put(Queen.class.getName(), GUI.loadPieceImage(Queen.class.getSimpleName(), color, size, ".png"));
        result.put(Rook.class.getName(), GUI.loadPieceImage(Rook.class.getSimpleName(), color, size, ".png"));
        result.put(King.class.getName(), GUI.loadPieceImage(King.class.getSimpleName(), color, size, ".png"));
        result.put(Bishop.class.getName(), GUI.loadPieceImage(Bishop.class.getSimpleName(), color, size, ".png"));
        return result;
    }
    
    private int getSizeToLoad(final int squareHeight) {
        Integer closest = Pieces2D.setsSizes.ceiling(squareHeight);
        if (null == closest) {
            closest = Pieces2D.setsSizes.last();
        }
        return closest;
    }
    
    public static synchronized Pieces2D getInstance() {
        if (null == Pieces2D.instance) {
            Pieces2D.instance = new Pieces2D();
        }
        return Pieces2D.instance;
    }
    
    public Image getImage(final Colors color, final Piece piece) {
        return this.getCurrentImageSet().get(color).get(piece.getClass().getName());
    }
    
    public static void draw(final ChessboardView chessboardView, final Piece piece, final Graphics g, final Image image) {
        draw(chessboardView, piece, piece.getSquare().getPozX(), piece.getSquare().getPozY(), g, image);
    }
    
    public static void draw(final ChessboardView chessboardView, final Piece piece, final int pozX, final int pozY, final Graphics g, Image image) {
        try {
            final Graphics2D g2d = (Graphics2D)g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            final Point topLeft = chessboardView.getTopLeftPoint();
            final int height = chessboardView.getSquareHeight();
            final int x = pozX * height + topLeft.x;
            final int y = pozY * height + topLeft.y;
            if (image != null && g != null) {
                final Image tempImage = image;
                final BufferedImage resized = new BufferedImage(height, height, 3);
                final Graphics2D imageGr = resized.createGraphics();
                imageGr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                imageGr.drawImage(tempImage, 0, 0, height, height, null);
                imageGr.dispose();
                image = resized.getScaledInstance(height, height, 0);
                g2d.drawImage(image, x, y, null);
            }
            else {
                Pieces2D.LOG.error("Piece/draw: image is null!");
            }
        }
        catch (NullPointerException exc) {
            Pieces2D.LOG.error("Something wrong when painting piece: " + exc.getMessage());
        }
    }
    
    static {
        LOG = Logger.getLogger(Pieces2D.class);
        Pieces2D.instance = null;
        Pieces2D.currentSize = 55;
        Pieces2D.setsSizes = new TreeSet<Integer>(Arrays.asList(25, 55, 70, 100));
        Pieces2D.imageSets = initImagesSets();
    }
}
