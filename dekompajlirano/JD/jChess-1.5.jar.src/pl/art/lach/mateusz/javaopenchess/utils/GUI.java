/*     */ package pl.art.lach.mateusz.javaopenchess.utils;
/*     */ 
/*     */ import java.awt.Image;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Arrays;
/*     */ import java.util.Properties;
/*     */ import javax.imageio.ImageIO;
/*     */ import org.apache.log4j.Logger;
/*     */ import pl.art.lach.mateusz.javaopenchess.JChessApp;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Colors;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Game;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GUI
/*     */ {
/*     */   private static Properties configFile;
/*     */   private static final String IMAGE_PATH = "theme/%s/images/%s";
/*  44 */   private static final Logger LOG = Logger.getLogger(GUI.class);
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String JAR_FILENAME = "[a-zA-Z0-9%!@\\-#$%^&*\\(\\)\\[\\]\\{\\}\\.\\,\\s]+\\.jar";
/*     */ 
/*     */   
/*     */   private static final String CONFIG_FILENAME = "config.txt";
/*     */ 
/*     */   
/*     */   private static final String THEME = "THEME";
/*     */ 
/*     */   
/*     */   private static final String SLASH = "/";
/*     */ 
/*     */   
/*     */   private static final String SPACE_IN_HEX = "%20";
/*     */ 
/*     */   
/*  63 */   private Game game = new Game();
/*     */ 
/*     */ 
/*     */   
/*     */   public static Image loadPieceImage(String pieceName, Colors color, int size, String fileExt) {
/*  68 */     String colorSymbol = String.valueOf(color.getSymbol()).toUpperCase();
/*  69 */     return loadImage(pieceName + "-" + colorSymbol + size + fileExt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Image loadImage(String name) {
/*  79 */     if (null == getConfigFile())
/*     */     {
/*  81 */       return null;
/*     */     }
/*  83 */     return loadAndReturnImage(name);
/*     */   }
/*     */ 
/*     */   
/*     */   private static Image loadAndReturnImage(String name) {
/*  88 */     Image img = null;
/*     */     
/*     */     try {
/*  91 */       String imageLink = String.format("theme/%s/images/%s", new Object[] { getConfigFile().getProperty("THEME", "default"), name });
/*  92 */       LOG.debug("THEME: " + getConfigFile().getProperty("THEME"));
/*  93 */       img = ImageIO.read(JChessApp.class.getResourceAsStream(imageLink));
/*     */     }
/*  95 */     catch (Exception e) {
/*     */       
/*  97 */       LOG.error("some error loading image!, message: " + e.getMessage() + " stackTrace: " + 
/*  98 */           Arrays.toString((Object[])e.getStackTrace()));
/*     */     } 
/*     */     
/* 101 */     return img;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 107 */   public static boolean themeIsValid(String name) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getJarPath() {
/* 116 */     String path = GUI.class.getProtectionDomain().getCodeSource().getLocation().getFile();
/* 117 */     path = path.replaceAll("[a-zA-Z0-9%!@\\-#$%^&*\\(\\)\\[\\]\\{\\}\\.\\,\\s]+\\.jar", "");
/* 118 */     int lastSlash = path.lastIndexOf(File.separator);
/* 119 */     if (path.length() - 1 == lastSlash)
/*     */     {
/* 121 */       path = path.substring(0, lastSlash);
/*     */     }
/* 123 */     path = path.replace("%20", " ");
/* 124 */     return path;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static String getFullConfigFilePath() {
/* 130 */     String result = getJarPath() + "config.txt";
/* 131 */     if (result.startsWith("/"))
/*     */     {
/* 133 */       result = result.replaceFirst("/", "");
/*     */     }
/* 135 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized Properties getConfigFile() {
/* 141 */     if (null == configFile)
/*     */     {
/* 143 */       loadConfigFile();
/*     */     }
/* 145 */     return configFile;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void loadConfigFile() {
/* 150 */     Properties defConfFile = new Properties();
/* 151 */     Properties confFile = new Properties();
/*     */     
/* 153 */     File outFile = new File(getFullConfigFilePath());
/* 154 */     loadDefaultConfigFile(defConfFile);
/* 155 */     if (!outFile.exists())
/*     */     {
/* 157 */       saveConfigFileOutsideJar(defConfFile, outFile);
/*     */     }
/* 159 */     loadOuterConfigFile(confFile, outFile);
/* 160 */     configFile = confFile;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void loadOuterConfigFile(Properties confFile, File outFile) {
/*     */     try {
/* 167 */       confFile.load(new FileInputStream(outFile));
/*     */     }
/* 169 */     catch (IOException e) {
/*     */       
/* 171 */       LOG.error("IOException, some error during getting config file!,, message: " + e.getMessage() + " stackTrace: " + 
/* 172 */           Arrays.toString((Object[])e.getStackTrace()));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void saveConfigFileOutsideJar(Properties defConfFile, File outFile) {
/*     */     try {
/* 181 */       defConfFile.store(new FileOutputStream(outFile), null);
/*     */     }
/* 183 */     catch (IOException e) {
/*     */       
/* 185 */       LOG.error("IOException, some error during getting config file!,, message: " + e.getMessage() + " stackTrace: " + 
/* 186 */           Arrays.toString((Object[])e.getStackTrace()));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void loadDefaultConfigFile(Properties defConfFile) {
/*     */     try {
/* 195 */       InputStream is = GUI.class.getResourceAsStream("config.txt");
/* 196 */       if (null != is) {
/* 197 */         defConfFile.load(is);
/*     */       }
/*     */     }
/* 200 */     catch (IOException|NullPointerException e) {
/*     */       
/* 202 */       LOG.error("IOException, some error during getting config file!, message: " + e.getMessage() + " stackTrace: " + 
/* 203 */           Arrays.toString((Object[])e.getStackTrace()));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 213 */   public Game getGame() { return this.game; }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenches\\utils\GUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */