/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  org.newdawn.slick.Input
 */
package Game;

import java.awt.Dimension;
import java.awt.Toolkit;
import org.newdawn.slick.Input;

public class Window {
    public static final int WIDTH;
    public static final int HEIGHT;
    public static final int HALF_WIDTH;
    public static final int HALF_HEIGHT;

    public static void clear(Input input) {
        input.clearKeyPressedRecord();
        input.clearMousePressedRecord();
    }

    static {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int)screenSize.getWidth();
        int screenHeight = (int)screenSize.getHeight();
        WIDTH = screenWidth;
        HEIGHT = screenHeight;
        HALF_WIDTH = screenWidth / 2;
        HALF_HEIGHT = screenHeight / 2;
    }
}

