/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  org.newdawn.slick.Graphics
 *  org.newdawn.slick.Image
 *  org.newdawn.slick.Input
 *  org.newdawn.slick.SlickException
 *  org.newdawn.slick.Sound
 */
package Game;

import Game.Ammo;
import Game.Bullet;
import Game.Enemy;
import Game.Game;
import Game.HealthBar;
import Game.Window;
import java.awt.Point;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Player {
    private Image playerImage;
    private int health = 100;
    private int rotation = 0;
    private int radius = 22;
    private Point Coordinates = new Point(Window.WIDTH / 2, Window.HEIGHT / 2);
    private final int speed = 324;
    private final int damage = 10;
    private boolean alive = true;
    private HealthBar healthbar;
    private int ammos = 50;
    private int ID = Game.players++;

    public Player() {
        this.loadImage();
        this.healthbar = new HealthBar(0, 0);
    }

    public void render(Graphics g) {
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

    public void update(Input input, int delta) {
        if (this.alive) {
            this.rotation = (int)this.getAngleFromPoint(new Point(input.getMouseX(), input.getMouseY()), this.Coordinates);
            this.getClass();
            int speed = 324 * delta / 1000;
            if (input.isKeyDown(32)) {
                this.Coordinates.x += speed;
            }
            if (input.isKeyDown(30)) {
                this.Coordinates.x -= speed;
            }
            if (input.isKeyDown(17)) {
                this.Coordinates.y -= speed;
            }
            if (input.isKeyDown(31)) {
                this.Coordinates.y += speed;
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

    public void detectCollisionWithEnemies(ArrayList<Enemy> enemies) throws SlickException {
        try {
            Iterator<Enemy> iter = enemies.iterator();
            while (iter.hasNext()) {
                Enemy enemy = iter.next();
                if (this.health == 0 || !enemy.isAlive() || !(Math.sqrt((enemy.getX() - this.getX()) * (enemy.getX() - this.getX()) + (enemy.getY() - this.getY()) * (enemy.getY() - this.getY())) <= (double)(this.radius + enemy.getRadius()))) continue;
                iter.remove();
                this.hit();
            }
        }
        catch (ConcurrentModificationException e) {
            System.out.println("Poteva andare peggio...");
        }
    }

    public void detectCollisionWithBullet(ArrayList<Bullet> bullets) throws SlickException {
        Iterator<Bullet> iter = bullets.iterator();
        while (iter.hasNext()) {
            Bullet bullet = iter.next();
            if (!this.isAlive() || !(Math.sqrt((bullet.getX() - this.getX()) * (bullet.getX() - this.getX()) + (bullet.getY() - this.getY()) * (bullet.getY() - this.getY())) <= (double)(this.radius + bullet.getRadius()))) continue;
            this.hit();
            iter.remove();
        }
    }

    public void checkIfPickedUpAmmos(Ammo[] ammos) throws SlickException {
        for (Ammo ammo : ammos) {
            if (this.health == 0 || ammo.alreadyPicked() || !(Math.sqrt((ammo.getX() - this.getX()) * (ammo.getX() - this.getX()) + (ammo.getY() - this.getY()) * (ammo.getY() - this.getY())) <= (double)(this.radius + ammo.getRadius()))) continue;
            ammo.pick();
            new Sound("Sounds/pacmanPickedAmmo.wav").play();
            this.ammos += 50;
        }
    }

    private double getAngleFromPoint(Point firstPoint, Point secondPoint) {
        double r = secondPoint.x > firstPoint.x ? Math.atan2(secondPoint.x - firstPoint.x, firstPoint.y - secondPoint.y) * 180.0 / 3.141592653589793 + 90.0 : (secondPoint.x < firstPoint.x ? 360.0 - Math.atan2(firstPoint.x - secondPoint.x, firstPoint.y - secondPoint.y) * 180.0 / 3.141592653589793 + 90.0 : Math.atan2(0.0, 0.0) + 90.0);
        if (r == 90.0 && Game.mouseY < this.Coordinates.y) {
            return 270.0;
        }
        return r;
    }

    public void hit() {
        this.getClass();
        this.health -= 10;
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

