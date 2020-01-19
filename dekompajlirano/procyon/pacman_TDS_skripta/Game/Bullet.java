// 
// Decompiled by Procyon v0.5.36
// 

package Game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

public class Bullet
{
    private Point Coordinates;
    private boolean fired;
    private final float speed = 564.0f;
    private int startX;
    private int startY;
    private int destX;
    private int destY;
    private float dx;
    private float dy;
    private int diameter;
    private int radius;
    
    public Bullet(final int startX, final int startY, final int destX, final int destY) {
        this.Coordinates = new Point(0.0f, 0.0f);
        this.fired = false;
        this.diameter = 6;
        this.radius = this.diameter / 2;
        this.startX = startX;
        this.startY = startY;
        this.destX = destX;
        this.destY = destY;
        this.setFired();
        this.Coordinates.setLocation((float)this.startX, (float)this.startY);
        final float rad = (float)Math.atan2(this.destX - this.startX, this.startY - this.destY);
        final float n = (float)Math.sin(rad);
        this.getClass();
        this.dx = n * 564.0f;
        final float n2 = -(float)Math.cos(rad);
        this.getClass();
        this.dy = n2 * 564.0f;
    }
    
    public Bullet() {
        this.Coordinates = new Point(0.0f, 0.0f);
        this.fired = false;
        this.diameter = 6;
        this.radius = this.diameter / 2;
    }
    
    public void render() {
        if (this.isFired()) {
            new Graphics().fillOval(this.Coordinates.getX() - this.radius, this.Coordinates.getY() - this.radius, (float)this.diameter, (float)this.diameter);
        }
    }
    
    public void update(final int delta) {
        if (this.isFired()) {
            float x = this.Coordinates.getX();
            float y = this.Coordinates.getY();
            x += this.dx * delta / 1000.0f;
            y += this.dy * delta / 1000.0f;
            this.Coordinates.setLocation(x, y);
        }
    }
    
    public boolean isFired() {
        return this.fired;
    }
    
    public void setFired() {
        this.fired = true;
    }
    
    public void removeFromGameIfOutOfBounds(final GameContainer gc) {
        if (this.isOutOfBounds(gc)) {
            this.fired = false;
        }
    }
    
    public boolean isOutOfBounds(final GameContainer gc) {
        return this.Coordinates.getX() < -10.0f || this.Coordinates.getX() > gc.getWidth() + 10 || this.Coordinates.getY() < -10.0f || this.Coordinates.getY() > gc.getHeight() + 10;
    }
    
    public void remove() {
        this.fired = false;
    }
    
    public int getX() {
        return (int)this.Coordinates.getX();
    }
    
    public int getY() {
        return (int)this.Coordinates.getY();
    }
    
    public int getRadius() {
        return this.radius;
    }
}
