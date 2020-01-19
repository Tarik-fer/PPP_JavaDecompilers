/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  org.newdawn.slick.GameContainer
 *  org.newdawn.slick.Graphics
 *  org.newdawn.slick.geom.Point
 */
package Game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

public class Bullet {
    private Point Coordinates = new Point(0.0f, 0.0f);
    private boolean fired = false;
    private final float speed = 564.0f;
    private int startX;
    private int startY;
    private int destX;
    private int destY;
    private float dx;
    private float dy;
    private int diameter = 6;
    private int radius = this.diameter / 2;

    public Bullet(int startX, int startY, int destX, int destY) {
        this.startX = startX;
        this.startY = startY;
        this.destX = destX;
        this.destY = destY;
        this.setFired();
        this.Coordinates.setLocation((float)this.startX, (float)this.startY);
        float rad = (float)Math.atan2(this.destX - this.startX, this.startY - this.destY);
        this.getClass();
        this.dx = (float)Math.sin(rad) * 564.0f;
        this.getClass();
        this.dy = -((float)Math.cos(rad)) * 564.0f;
    }

    public Bullet() {
    }

    public void render() {
        if (this.isFired()) {
            new Graphics().fillOval(this.Coordinates.getX() - (float)this.radius, this.Coordinates.getY() - (float)this.radius, (float)this.diameter, (float)this.diameter);
        }
    }

    public void update(int delta) {
        if (this.isFired()) {
            float x = this.Coordinates.getX();
            float y = this.Coordinates.getY();
            this.Coordinates.setLocation(x += this.dx * (float)delta / 1000.0f, y += this.dy * (float)delta / 1000.0f);
        }
    }

    public boolean isFired() {
        return this.fired;
    }

    public void setFired() {
        this.fired = true;
    }

    public void removeFromGameIfOutOfBounds(GameContainer gc) {
        if (this.isOutOfBounds(gc)) {
            this.fired = false;
        }
    }

    public boolean isOutOfBounds(GameContainer gc) {
        return this.Coordinates.getX() < -10.0f || this.Coordinates.getX() > (float)(gc.getWidth() + 10) || this.Coordinates.getY() < -10.0f || this.Coordinates.getY() > (float)(gc.getHeight() + 10);
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

