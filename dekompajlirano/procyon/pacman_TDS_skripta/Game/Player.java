// 
// Decompiled by Procyon v0.5.36
// 

package Game;

import org.newdawn.slick.Sound;
import java.util.Iterator;
import java.util.ConcurrentModificationException;
import java.util.ArrayList;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import java.awt.Point;
import org.newdawn.slick.Image;

public class Player
{
    private Image playerImage;
    private int health;
    private int rotation;
    private int radius;
    private Point Coordinates;
    private final int speed = 324;
    private final int damage = 10;
    private boolean alive;
    private HealthBar healthbar;
    private int ammos;
    private int ID;
    
    public Player() {
        this.health = 100;
        this.rotation = 0;
        this.radius = 22;
        this.Coordinates = new Point(Window.WIDTH / 2, Window.HEIGHT / 2);
        this.alive = true;
        this.ammos = 50;
        this.ID = Game.players++;
        this.loadImage();
        this.healthbar = new HealthBar(0, 0);
    }
    
    public void render(final Graphics g) {
        if (this.isAlive()) {
            this.playerImage.drawCentered((float)this.Coordinates.x, (float)this.Coordinates.y);
            this.playerImage.setRotation((float)this.rotation);
            this.healthbar.render(this.Coordinates.x - 24, this.Coordinates.y - 50, this.getHealth() / 2, g);
        }
    }
    
    public void loadImage() {
        try {
            this.playerImage = new Image("Images/Pac Man.png");
        }
        catch (SlickException e) {
            System.out.println("playerImage not found");
        }
    }
    
    public void update(final Input input, final int delta) {
        if (this.alive) {
            this.rotation = (int)this.getAngleFromPoint(new Point(input.getMouseX(), input.getMouseY()), this.Coordinates);
            this.getClass();
            final int speed = 324 * delta / 1000;
            if (input.isKeyDown(32)) {
                final Point coordinates = this.Coordinates;
                coordinates.x += speed;
            }
            if (input.isKeyDown(30)) {
                final Point coordinates2 = this.Coordinates;
                coordinates2.x -= speed;
            }
            if (input.isKeyDown(17)) {
                final Point coordinates3 = this.Coordinates;
                coordinates3.y -= speed;
            }
            if (input.isKeyDown(31)) {
                final Point coordinates4 = this.Coordinates;
                coordinates4.y += speed;
            }
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
    
    public void checkIfPickedUpAmmos(final Ammo[] ammos) throws SlickException {
        for (final Ammo ammo : ammos) {
            if (this.health != 0 && !ammo.alreadyPicked() && Math.sqrt((ammo.getX() - this.getX()) * (ammo.getX() - this.getX()) + (ammo.getY() - this.getY()) * (ammo.getY() - this.getY())) <= this.radius + ammo.getRadius()) {
                ammo.pick();
                new Sound("Sounds/pacmanPickedAmmo.wav").play();
                this.ammos += 50;
            }
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
    
    public int getAmmos() {
        return this.ammos;
    }
    
    public void removeAmmo() {
        --this.ammos;
    }
    
    public int getID() {
        return this.ID;
    }
    
    public void reset() {
        this.alive = true;
        this.health = 100;
        this.ammos = 50;
        this.loadImage();
        this.Coordinates = new Point(Window.WIDTH / 2, Window.HEIGHT / 2);
    }
}
