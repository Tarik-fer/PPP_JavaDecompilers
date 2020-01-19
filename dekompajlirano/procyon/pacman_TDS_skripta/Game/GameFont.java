// 
// Decompiled by Procyon v0.5.36
// 

package Game;

import java.io.InputStream;
import java.io.IOException;
import java.awt.FontFormatException;
import java.awt.Font;
import org.newdawn.slick.util.ResourceLoader;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

public class GameFont
{
    private TrueTypeFont font;
    static Color optionBlue;
    
    public GameFont(final float size) {
        try {
            final InputStream inputStream = ResourceLoader.getResourceAsStream("Font/BitBold.ttf");
            this.font = new TrueTypeFont(Font.createFont(0, inputStream).deriveFont(size), true);
        }
        catch (FontFormatException | IOException ex2) {
            final Exception ex;
            final Exception e = ex;
            System.out.println("Error while loading font");
        }
    }
    
    public void drawCenteredString(final String string, final int x, final int y, final Color color) {
        final int width = this.font.getWidth(string);
        final int height = this.font.getHeight(string);
        this.font.drawString((float)(x - width / 2), (float)(y - height / 2), string, color);
    }
    
    public void drawString(final String string, final int x, final int y, final Color color) {
        this.font.drawString((float)x, (float)y, string, color);
    }
    
    public int getStringWidth(final String string) {
        return this.font.getWidth(string);
    }
    
    public int getStringHeight(final String string) {
        return this.font.getHeight(string);
    }
    
    public TrueTypeFont getFont() {
        return this.font;
    }
    
    static {
        GameFont.optionBlue = new Color(0, 194, 255);
    }
}
