// 
// Decompiled by Procyon v0.5.36
// 

package Game;

import java.util.Random;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.ArrayList;
import java.awt.Point;
import org.newdawn.slick.Image;

public class Enemy
{
    private Image ghostImage;
    private Point coordinates;
    private boolean alive;
    private float speed;
    private float dx;
    private float dy;
    private int diameter;
    private int radius;
    private int playerToFollow;
    
    public Enemy(final int startX, final int startY) {
        this.coordinates = new Point(0, 0);
        this.alive = false;
        this.speed = 168.0f;
        this.diameter = 84;
        this.radius = this.diameter / 2;
        this.coordinates.setLocation(startX, startY);
        this.loadImage();
    }
    
    public Enemy(final int startX, final int startY, final int imageIndex, final int playerToFollow) {
        this.coordinates = new Point(0, 0);
        this.alive = false;
        this.speed = 168.0f;
        this.diameter = 84;
        this.radius = this.diameter / 2;
        this.coordinates.setLocation(startX, startY);
        this.playerToFollow = playerToFollow;
        this.loadImage(imageIndex);
    }
    
    public Enemy() {
        this.coordinates = new Point(0, 0);
        this.alive = false;
        this.speed = 168.0f;
        this.diameter = 84;
        this.radius = this.diameter / 2;
    }
    
    public void update(final Point playerCoordinates, final int delta) {
        final float rad = (float)Math.atan2(playerCoordinates.x - this.coordinates.getX(), this.coordinates.getY() - playerCoordinates.y);
        this.dx = (float)Math.sin(rad) * this.speed;
        this.dy = -(float)Math.cos(rad) * this.speed;
        float x = (float)this.coordinates.getX();
        float y = (float)this.coordinates.getY();
        x += this.dx * delta / 1000.0f;
        y += this.dy * delta / 1000.0f;
        this.alive = true;
        this.coordinates.setLocation(x, y);
    }
    
    public void render() {
        if (this.isAlive()) {
            this.ghostImage.drawCentered((float)this.coordinates.getX(), (float)this.coordinates.getY());
        }
    }
    
    public void detectCollisionWithBullet(final ArrayList<Bullet> bullets) {
        final Iterator<Bullet> iter = bullets.iterator();
        while (iter.hasNext()) {
            final Bullet bullet = iter.next();
            if (this.isAlive() && bullet.isFired() && Math.sqrt((bullet.getX() - this.getX()) * (bullet.getX() - this.getX()) + (bullet.getY() - this.getY()) * (bullet.getY() - this.getY())) <= this.radius + bullet.getRadius()) {
                this.alive = false;
                Game.Score.incrementScore();
                iter.remove();
            }
        }
    }
    
    public boolean isCollidingWithBullets(final ArrayList<Bullet> bullets) {
        final Iterator<Bullet> iter = bullets.iterator();
        while (iter.hasNext()) {
            final Bullet bullet = iter.next();
            if (this.isAlive() && bullet.isFired() && Math.sqrt((bullet.getX() - this.getX()) * (bullet.getX() - this.getX()) + (bullet.getY() - this.getY()) * (bullet.getY() - this.getY())) <= this.radius + bullet.getRadius()) {
                Game.Score.incrementScore();
                iter.remove();
                return true;
            }
        }
        return false;
    }
    
    public boolean isCollidingWithBullets(final LinkedList<Bullet> bullets) {
        final Iterator<Bullet> iter = bullets.iterator();
        while (iter.hasNext()) {
            final Bullet bullet = iter.next();
            if (this.isAlive() && bullet.isFired() && Math.sqrt((bullet.getX() - this.getX()) * (bullet.getX() - this.getX()) + (bullet.getY() - this.getY()) * (bullet.getY() - this.getY())) <= this.radius + bullet.getRadius()) {
                Game.Score.incrementScore();
                iter.remove();
                return true;
            }
        }
        return false;
    }
    
    public void loadImage() {
        final Random r = new Random();
        this.ghostImage = Game.ghosts[r.nextInt(4)];
    }
    
    public void loadImage(final int imageIndex) {
        this.ghostImage = Game.ghosts[imageIndex];
    }
    
    public boolean isAlive() {
        return this.alive;
    }
    
    public int getX() {
        return (int)this.coordinates.getX();
    }
    
    public int getY() {
        return (int)this.coordinates.getY();
    }
    
    public int getRadius() {
        return this.radius;
    }
    
    public void kill() {
        this.alive = false;
        Game.Score.incrementScore();
    }
    
    public void remove() {
        this.alive = false;
    }
    
    public void setSpeed(final int newSpeed) {
        this.speed = (float)newSpeed;
    }
    
    public int getPlayerToFollow() {
        return this.playerToFollow;
    }
}
