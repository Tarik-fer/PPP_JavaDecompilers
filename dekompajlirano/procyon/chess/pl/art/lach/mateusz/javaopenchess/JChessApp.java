// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess;

import java.io.IOException;
import org.apache.log4j.PropertyConfigurator;
import java.util.Properties;
import org.jdesktop.application.Application;
import java.awt.Window;
import org.jdesktop.application.View;
import org.jdesktop.application.SingleFrameApplication;

public class JChessApp extends SingleFrameApplication
{
    protected static JChessView javaChessView;
    public static final String LOG_FILE = "log4j.properties";
    public static final String MAIN_PACKAGE_NAME;
    
    public static JChessView getJavaChessView() {
        return JChessApp.javaChessView;
    }
    
    @Override
    protected void startup() {
        JChessApp.javaChessView = new JChessView(this);
        this.show(getJavaChessView());
    }
    
    @Override
    protected void configureWindow(final Window root) {
    }
    
    public static JChessApp getApplication() {
        return Application.getInstance(JChessApp.class);
    }
    
    public static void main(final String[] args) {
        Application.launch(JChessApp.class, args);
        final Properties logProp = new Properties();
        try {
            logProp.load(JChessApp.class.getClassLoader().getResourceAsStream("log4j.properties"));
            PropertyConfigurator.configure(logProp);
        }
        catch (NullPointerException | IOException ex2) {
            final Exception ex;
            final Exception e = ex;
            System.err.println("Logging not enabled : " + e.getMessage());
        }
    }
    
    static {
        MAIN_PACKAGE_NAME = JChessApp.class.getPackage().getName();
    }
}
