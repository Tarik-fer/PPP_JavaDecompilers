// 
// Decompiled by Procyon v0.5.36
// 

package Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class HealthBar
{
    private int x;
    private int y;
    
    public HealthBar(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    
    public void render(final int health, final Graphics g) {
        g.setColor(Color.red);
        g.fillRect((float)this.x, (float)this.y, (float)health, 10.0f);
        g.setColor(Color.white);
    }
    
    public void render(final int x, final int y, final int health, final Graphics g) {
        g.setColor(Color.green);
        g.fillRect((float)x, (float)y, (float)health, 6.0f);
        g.setColor(Color.white);
    }
    
    public void render(final int x, final int y, final int health, final Graphics g, final boolean isAnotherPlayer) {
        g.setColor(GameFont.optionBlue);
        g.fillRect((float)x, (float)y, (float)health, 6.0f);
        g.setColor(Color.white);
    }
}
