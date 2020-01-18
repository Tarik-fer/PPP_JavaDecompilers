/*     */ package pl.art.lach.mateusz.javaopenchess.display.views.chessboard.implementation.graphic2D;
/*     */ 
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.Point;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.TreeSet;
/*     */ import org.apache.log4j.Logger;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Colors;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Bishop;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.King;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Knight;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Pawn;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Queen;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.pieces.implementation.Rook;
/*     */ import pl.art.lach.mateusz.javaopenchess.display.views.chessboard.ChessboardView;
/*     */ import pl.art.lach.mateusz.javaopenchess.utils.GUI;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Pieces2D
/*     */ {
/*  45 */   private static final Logger LOG = Logger.getLogger(Pieces2D.class);
/*     */   
/*     */   private static final String FILE_EXT = ".png";
/*     */   
/*  49 */   protected Map<Colors, Map<String, Image>> currentImageSet = new HashMap<>();
/*     */   
/*  51 */   protected static Pieces2D instance = null;
/*     */   
/*  53 */   protected static int currentSize = 55;
/*     */   
/*  55 */   protected static TreeSet<Integer> setsSizes = new TreeSet<>(Arrays.asList(new Integer[] { Integer.valueOf(25), Integer.valueOf(55), Integer.valueOf(70), Integer.valueOf(100) }));
/*     */   
/*  57 */   protected static Map<Integer, Map<Colors, Map<String, Image>>> imageSets = initImagesSets();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   public Map<Colors, Map<String, Image>> getCurrentImageSet() { return this.currentImageSet; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   public void setCurrentImageSet(Map<Colors, Map<String, Image>> aImages) { this.currentImageSet = aImages; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   public static int getCurrentSize() { return currentSize; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   public static void setCurrentSize(int aCurrentSize) { currentSize = aCurrentSize; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  93 */   protected Pieces2D() { this.currentImageSet = imageSets.get(Integer.valueOf(currentSize)); }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void resize(int squareSize) {
/*  98 */     if (null != setsSizes) {
/*     */       
/* 100 */       int closest = getSizeToLoad(squareSize);
/* 101 */       if (currentSize != closest) {
/*     */         
/* 103 */         currentSize = closest;
/* 104 */         this.currentImageSet = imageSets.get(Integer.valueOf(currentSize));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static Map<Integer, Map<Colors, Map<String, Image>>> initImagesSets() {
/* 111 */     Map<Integer, Map<Colors, Map<String, Image>>> result = new HashMap<>();
/* 112 */     for (Iterator<Integer> iterator = setsSizes.iterator(); iterator.hasNext(); ) { int size = ((Integer)iterator.next()).intValue();
/*     */       
/* 114 */       Map<Colors, Map<String, Image>> localImages = new HashMap<>();
/* 115 */       localImages.put(Colors.BLACK, getPieceMap(Colors.BLACK, size));
/* 116 */       localImages.put(Colors.WHITE, getPieceMap(Colors.WHITE, size));
/* 117 */       result.put(Integer.valueOf(size), localImages); }
/*     */     
/* 119 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   private static Map<String, Image> getPieceMap(Colors color, int size) {
/* 124 */     Map<String, Image> result = new HashMap<>();
/*     */     
/* 126 */     result.put(Pawn.class.getName(), GUI.loadPieceImage(Pawn.class.getSimpleName(), color, size, ".png"));
/* 127 */     result.put(Knight.class.getName(), GUI.loadPieceImage(Knight.class.getSimpleName(), color, size, ".png"));
/* 128 */     result.put(Queen.class.getName(), GUI.loadPieceImage(Queen.class.getSimpleName(), color, size, ".png"));
/* 129 */     result.put(Rook.class.getName(), GUI.loadPieceImage(Rook.class.getSimpleName(), color, size, ".png"));
/* 130 */     result.put(King.class.getName(), GUI.loadPieceImage(King.class.getSimpleName(), color, size, ".png"));
/* 131 */     result.put(Bishop.class.getName(), GUI.loadPieceImage(Bishop.class.getSimpleName(), color, size, ".png"));
/*     */     
/* 133 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   private int getSizeToLoad(int squareHeight) {
/* 138 */     Integer closest = setsSizes.ceiling(Integer.valueOf(squareHeight));
/* 139 */     if (null == closest)
/*     */     {
/* 141 */       closest = setsSizes.last();
/*     */     }
/* 143 */     return closest.intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public static synchronized Pieces2D getInstance() {
/* 148 */     if (null == instance)
/*     */     {
/* 150 */       instance = new Pieces2D();
/*     */     }
/* 152 */     return instance;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 157 */   public Image getImage(Colors color, Piece piece) { return (Image)((Map)getCurrentImageSet().get(color)).get(piece.getClass().getName()); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 162 */   public static void draw(ChessboardView chessboardView, Piece piece, Graphics g, Image image) { draw(chessboardView, piece, piece.getSquare().getPozX(), piece.getSquare().getPozY(), g, image); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void draw(ChessboardView chessboardView, Piece piece, int pozX, int pozY, Graphics g, Image image) {
/*     */     try {
/* 169 */       Graphics2D g2d = (Graphics2D)g;
/* 170 */       g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 171 */       Point topLeft = chessboardView.getTopLeftPoint();
/* 172 */       int height = chessboardView.getSquareHeight();
/* 173 */       int x = pozX * height + topLeft.x;
/* 174 */       int y = pozY * height + topLeft.y;
/* 175 */       if (image != null && g != null)
/*     */       {
/* 177 */         Image tempImage = image;
/* 178 */         BufferedImage resized = new BufferedImage(height, height, 3);
/* 179 */         Graphics2D imageGr = resized.createGraphics();
/* 180 */         imageGr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 181 */         imageGr.drawImage(tempImage, 0, 0, height, height, null);
/* 182 */         imageGr.dispose();
/* 183 */         image = resized.getScaledInstance(height, height, 0);
/* 184 */         g2d.drawImage(image, x, y, null);
/*     */       }
/*     */       else
/*     */       {
/* 188 */         LOG.error("Piece/draw: image is null!");
/*     */       }
/*     */     
/* 191 */     } catch (NullPointerException exc) {
/*     */       
/* 193 */       LOG.error("Something wrong when painting piece: " + exc.getMessage());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\display\views\chessboard\implementation\graphic2D\Pieces2D.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */