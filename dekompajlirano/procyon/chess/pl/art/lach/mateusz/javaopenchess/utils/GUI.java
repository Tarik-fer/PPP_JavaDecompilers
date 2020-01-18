// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.utils;

import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.File;
import java.util.Arrays;
import javax.imageio.ImageIO;
import pl.art.lach.mateusz.javaopenchess.JChessApp;
import java.awt.Image;
import pl.art.lach.mateusz.javaopenchess.core.Colors;
import pl.art.lach.mateusz.javaopenchess.core.Game;
import org.apache.log4j.Logger;
import java.util.Properties;

public class GUI
{
    private static Properties configFile;
    private static final String IMAGE_PATH = "theme/%s/images/%s";
    private static final Logger LOG;
    private static final String JAR_FILENAME = "[a-zA-Z0-9%!@\\-#$%^&*\\(\\)\\[\\]\\{\\}\\.\\,\\s]+\\.jar";
    private static final String CONFIG_FILENAME = "config.txt";
    private static final String THEME = "THEME";
    private static final String SLASH = "/";
    private static final String SPACE_IN_HEX = "%20";
    private Game game;
    
    public GUI() {
        this.game = new Game();
    }
    
    public static Image loadPieceImage(final String pieceName, final Colors color, final int size, final String fileExt) {
        final String colorSymbol = String.valueOf(color.getSymbol()).toUpperCase();
        return loadImage(pieceName + "-" + colorSymbol + size + fileExt);
    }
    
    public static Image loadImage(final String name) {
        if (null == getConfigFile()) {
            return null;
        }
        return loadAndReturnImage(name);
    }
    
    private static Image loadAndReturnImage(final String name) {
        Image img = null;
        try {
            final String imageLink = String.format("theme/%s/images/%s", getConfigFile().getProperty("THEME", "default"), name);
            GUI.LOG.debug("THEME: " + getConfigFile().getProperty("THEME"));
            img = ImageIO.read(JChessApp.class.getResourceAsStream(imageLink));
        }
        catch (Exception e) {
            GUI.LOG.error("some error loading image!, message: " + e.getMessage() + " stackTrace: " + Arrays.toString(e.getStackTrace()));
        }
        return img;
    }
    
    public static boolean themeIsValid(final String name) {
        return true;
    }
    
    public static String getJarPath() {
        String path = GUI.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        path = path.replaceAll("[a-zA-Z0-9%!@\\-#$%^&*\\(\\)\\[\\]\\{\\}\\.\\,\\s]+\\.jar", "");
        final int lastSlash = path.lastIndexOf(File.separator);
        if (path.length() - 1 == lastSlash) {
            path = path.substring(0, lastSlash);
        }
        path = path.replace("%20", " ");
        return path;
    }
    
    private static String getFullConfigFilePath() {
        String result = getJarPath() + "config.txt";
        if (result.startsWith("/")) {
            result = result.replaceFirst("/", "");
        }
        return result;
    }
    
    public static synchronized Properties getConfigFile() {
        if (null == GUI.configFile) {
            loadConfigFile();
        }
        return GUI.configFile;
    }
    
    private static void loadConfigFile() {
        final Properties defConfFile = new Properties();
        final Properties confFile = new Properties();
        final File outFile = new File(getFullConfigFilePath());
        loadDefaultConfigFile(defConfFile);
        if (!outFile.exists()) {
            saveConfigFileOutsideJar(defConfFile, outFile);
        }
        loadOuterConfigFile(confFile, outFile);
        GUI.configFile = confFile;
    }
    
    private static void loadOuterConfigFile(final Properties confFile, final File outFile) {
        try {
            confFile.load(new FileInputStream(outFile));
        }
        catch (IOException e) {
            GUI.LOG.error("IOException, some error during getting config file!,, message: " + e.getMessage() + " stackTrace: " + Arrays.toString(e.getStackTrace()));
        }
    }
    
    private static void saveConfigFileOutsideJar(final Properties defConfFile, final File outFile) {
        try {
            defConfFile.store(new FileOutputStream(outFile), null);
        }
        catch (IOException e) {
            GUI.LOG.error("IOException, some error during getting config file!,, message: " + e.getMessage() + " stackTrace: " + Arrays.toString(e.getStackTrace()));
        }
    }
    
    private static void loadDefaultConfigFile(final Properties defConfFile) {
        try {
            final InputStream is = GUI.class.getResourceAsStream("config.txt");
            if (null != is) {
                defConfFile.load(is);
            }
        }
        catch (IOException | NullPointerException ex2) {
            final Exception ex;
            final Exception e = ex;
            GUI.LOG.error("IOException, some error during getting config file!, message: " + e.getMessage() + " stackTrace: " + Arrays.toString(e.getStackTrace()));
        }
    }
    
    public Game getGame() {
        return this.game;
    }
    
    static {
        LOG = Logger.getLogger(GUI.class);
    }
}
