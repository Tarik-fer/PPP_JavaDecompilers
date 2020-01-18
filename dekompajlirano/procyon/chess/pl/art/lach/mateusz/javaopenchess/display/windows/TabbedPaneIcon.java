// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.display.windows;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;
import javax.swing.Icon;

class TabbedPaneIcon implements Icon
{
    private int x_pos;
    private int y_pos;
    private int width;
    private int height;
    private Icon fileIcon;
    
    public TabbedPaneIcon(final Icon fileIcon) {
        this.fileIcon = fileIcon;
        this.width = 16;
        this.height = 16;
    }
    
    @Override
    public void paintIcon(final Component c, final Graphics g, final int x, final int y) {
        this.x_pos = x;
        this.y_pos = y;
        final Color col = g.getColor();
        g.setColor(Color.black);
        final int yP = y + 2;
        g.drawLine(x + 3, yP + 3, x + 10, yP + 10);
        g.drawLine(x + 3, yP + 4, x + 9, yP + 10);
        g.drawLine(x + 4, yP + 3, x + 10, yP + 9);
        g.drawLine(x + 10, yP + 3, x + 3, yP + 10);
        g.drawLine(x + 10, yP + 4, x + 4, yP + 10);
        g.drawLine(x + 9, yP + 3, x + 3, yP + 9);
        g.setColor(col);
        if (this.fileIcon != null) {
            this.fileIcon.paintIcon(c, g, x + this.width, yP);
        }
    }
    
    @Override
    public int getIconWidth() {
        return this.width + ((this.fileIcon != null) ? this.fileIcon.getIconWidth() : 0);
    }
    
    @Override
    public int getIconHeight() {
        return this.height;
    }
    
    public Rectangle getBounds() {
        return new Rectangle(this.x_pos, this.y_pos, this.width, this.height);
    }
}
