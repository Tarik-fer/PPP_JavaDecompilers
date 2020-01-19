// 
// Decompiled by Procyon v0.5.36
// 

package Game;

import java.awt.Dimension;
import java.awt.Toolkit;
import org.newdawn.slick.Input;

public class Window
{
    public static final int WIDTH;
    public static final int HEIGHT;
    public static final int HALF_WIDTH;
    public static final int HALF_HEIGHT;
    
    public static void clear(final Input input) {
        input.clearKeyPressedRecord();
        input.clearMousePressedRecord();
    }
    
    static {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int screenWidth = (int)screenSize.getWidth();
        final int screenHeight = (int)screenSize.getHeight();
        WIDTH = screenWidth;
        HEIGHT = screenHeight;
        HALF_WIDTH = screenWidth / 2;
        HALF_HEIGHT = screenHeight / 2;
    }
}
