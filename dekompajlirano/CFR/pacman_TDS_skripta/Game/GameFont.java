/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  org.newdawn.slick.Color
 *  org.newdawn.slick.TrueTypeFont
 *  org.newdawn.slick.util.ResourceLoader
 */
package Game;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

public class GameFont {
    private TrueTypeFont font;
    static Color optionBlue = new Color(0, 194, 255);

    public GameFont(float size) {
        try {
            InputStream inputStream = ResourceLoader.getResourceAsStream((String)"Font/BitBold.ttf");
            this.font = new TrueTypeFont(Font.createFont(0, inputStream).deriveFont(size), true);
        }
        catch (FontFormatException | IOException e) {
            System.out.println("Error while loading font");
        }
    }

    public void drawCenteredString(String string, int x, int y, Color color) {
        int width = this.font.getWidth(string);
        int height = this.font.getHeight(string);
        this.font.drawString((float)(x - width / 2), (float)(y - height / 2), string, color);
    }

    public void drawString(String string, int x, int y, Color color) {
        this.font.drawString((float)x, (float)y, string, color);
    }

    public int getStringWidth(String string) {
        return this.font.getWidth(string);
    }

    public int getStringHeight(String string) {
        return this.font.getHeight(string);
    }

    public TrueTypeFont getFont() {
        return this.font;
    }
}

