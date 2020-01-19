// 
// Decompiled by Procyon v0.5.36
// 

package Game;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ArrayList;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import java.awt.Point;
import org.newdawn.slick.Image;

public class Opponent
{
    private Image playerImage;
    private int health;
    private int rotation;
    private int radius;
    private Point Coordinates;
    private final int damage = 10;
    private boolean alive;
    private HealthBar healthbar;
    static int ID;
    private float xRatio;
    private float yRatio;
    
    public Opponent() {
        this.health = 100;
        this.rotation = 0;
        this.radius = 45;
        this.Coordinates = new Point(Window.HALF_WIDTH, Window.HALF_HEIGHT);
        this.alive = true;
        this.xRatio = 1.0f;
        this.yRatio = 1.0f;
        this.loadImage();
        this.healthbar = new HealthBar(this.Coordinates.x, this.Coordinates.y);
    }
    
    public void render(final Graphics g) {
        if (this.isAlive()) {
            this.playerImage.drawCentered((float)this.Coordinates.x, (float)this.Coordinates.y);
            this.playerImage.setRotation((float)this.rotation);
            this.healthbar.render(this.Coordinates.x - 24, this.Coordinates.y - 80 + 30, this.getHealth() / 2, g, true);
        }
    }
    
    public void loadImage() {
        try {
            this.playerImage = new Image("Images/Pac Man.png");
        }
        catch (SlickException ex) {
            System.out.println("playerImage not found");
        }
    }
    
    public void update(final Point oppCoordinates, final int x, final int y) {
        if (this.alive) {
            this.rotation = (int)this.getAngleFromPoint(new Point(x, y), this.Coordinates);
            this.Coordinates.x = (int)(oppCoordinates.x * this.xRatio);
            this.Coordinates.y = (int)(oppCoordinates.y * this.yRatio);
            if (this.Coordinates.y >= Window.HEIGHT - 45) {
                this.Coordinates.y = Window.HEIGHT - 45;
            }
            if (this.Coordinates.y <= 45) {
                this.Coordinates.y = 45;
            }
            if (this.Coordinates.x >= Window.WIDTH - 45) {
                this.Coordinates.x = Window.WIDTH - 45;
            }
            if (this.Coordinates.x <= 45) {
                this.Coordinates.x = 45;
            }
            if (this.health == 0) {
                this.alive = false;
            }
        }
    }
    
    public void detectCollisionWithBullet(final Bullet[] bullets) throws SlickException {
        for (final Bullet bullet : bullets) {
            if (this.isAlive() && Math.sqrt((bullet.getX() - this.getX()) * (bullet.getX() - this.getX()) + (bullet.getY() - this.getY()) * (bullet.getY() - this.getY())) <= this.radius + bullet.getRadius()) {
                bullet.remove();
            }
        }
    }
    
    public void detectCollisionWithBullet(final ArrayList<Bullet> bullets) throws SlickException {
        final Iterator<Bullet> iter = bullets.iterator();
        while (iter.hasNext()) {
            final Bullet bullet = iter.next();
            if (this.isAlive() && Math.sqrt((bullet.getX() - this.getX()) * (bullet.getX() - this.getX()) + (bullet.getY() - this.getY()) * (bullet.getY() - this.getY())) <= this.radius + bullet.getRadius()) {
                this.hit();
                iter.remove();
            }
        }
    }
    
    public void detectCollisionWithEnemies(final ArrayList<Enemy> enemies) throws SlickException {
        try {
            final Iterator<Enemy> iter = enemies.iterator();
            while (iter.hasNext()) {
                final Enemy enemy = iter.next();
                if (this.health != 0 && enemy.isAlive() && Math.sqrt((enemy.getX() - this.getX()) * (enemy.getX() - this.getX()) + (enemy.getY() - this.getY()) * (enemy.getY() - this.getY())) <= this.radius + enemy.getRadius()) {
                    iter.remove();
                    this.hit();
                }
            }
        }
        catch (ConcurrentModificationException e) {
            System.out.println("Poteva andare peggio...");
        }
    }
    
    private double getAngleFromPoint(final Point firstPoint, final Point secondPoint) {
        double r;
        if (secondPoint.x > firstPoint.x) {
            r = Math.atan2(secondPoint.x - firstPoint.x, firstPoint.y - secondPoint.y) * 180.0 / 3.141592653589793 + 90.0;
        }
        else if (secondPoint.x < firstPoint.x) {
            r = 360.0 - Math.atan2(firstPoint.x - secondPoint.x, firstPoint.y - secondPoint.y) * 180.0 / 3.141592653589793 + 90.0;
        }
        else {
            r = Math.atan2(0.0, 0.0) + 90.0;
        }
        if (r == 90.0 && Game.mouseY < this.Coordinates.y) {
            return 270.0;
        }
        return r;
    }
    
    public void hit() {
        final int health = this.health;
        this.getClass();
        this.health = health - 10;
    }
    
    public int getHealth() {
        return this.health;
    }
    
    public void setHealth(final int health) {
        this.health = health;
    }
    
    public int getX() {
        return (int)this.Coordinates.getX();
    }
    
    public int getY() {
        return (int)this.Coordinates.getY();
    }
    
    public boolean isAlive() {
        return this.alive;
    }
    
    public Point getCoordinates() {
        return this.Coordinates;
    }
    
    public int getID() {
        return Opponent.ID;
    }
    
    public void reset() {
        this.alive = true;
        this.health = 100;
        this.loadImage();
        this.Coordinates = new Point(Window.HALF_WIDTH, Window.HALF_HEIGHT);
    }
    
    static {
        Opponent.ID = Game.players++;
    }
}
