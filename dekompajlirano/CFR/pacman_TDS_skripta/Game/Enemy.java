/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  org.newdawn.slick.Image
 */
package Game;

import Game.Bullet;
import Game.Game;
import Game.ScoreManager;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import org.newdawn.slick.Image;

public class Enemy {
    private Image ghostImage;
    private Point coordinates = new Point(0, 0);
    private boolean alive = false;
    private float speed = 168.0f;
    private float dx;
    private float dy;
    private int diameter = 84;
    private int radius = this.diameter / 2;
    private int playerToFollow;

    public Enemy(int startX, int startY) {
        this.coordinates.setLocation(startX, startY);
        this.loadImage();
    }

    public Enemy(int startX, int startY, int imageIndex, int playerToFollow) {
        this.coordinates.setLocation(startX, startY);
        this.playerToFollow = playerToFollow;
        this.loadImage(imageIndex);
    }

    public Enemy() {
    }

    public void update(Point playerCoordinates, int delta) {
        float rad = (float)Math.atan2((double)playerCoordinates.x - this.coordinates.getX(), this.coordinates.getY() - (double)playerCoordinates.y);
        this.dx = (float)Math.sin(rad) * this.speed;
        this.dy = -((float)Math.cos(rad)) * this.speed;
        float x = (float)this.coordinates.getX();
        float y = (float)this.coordinates.getY();
        this.alive = true;
        this.coordinates.setLocation(x += this.dx * (float)delta / 1000.0f, y += this.dy * (float)delta / 1000.0f);
    }

    public void render() {
        if (this.isAlive()) {
            this.ghostImage.drawCentered((float)this.coordinates.getX(), (float)this.coordinates.getY());
        }
    }

    public void detectCollisionWithBullet(ArrayList<Bullet> bullets) {
        Iterator<Bullet> iter = bullets.iterator();
        while (iter.hasNext()) {
            Bullet bullet = iter.next();
            if (!this.isAlive() || !bullet.isFired() || !(Math.sqrt((bullet.getX() - this.getX()) * (bullet.getX() - this.getX()) + (bullet.getY() - this.getY()) * (bullet.getY() - this.getY())) <= (double)(this.radius + bullet.getRadius()))) continue;
            this.alive = false;
            Game.Score.incrementScore();
            iter.remove();
        }
    }

    public boolean isCollidingWithBullets(ArrayList<Bullet> bullets) {
        Iterator<Bullet> iter = bullets.iterator();
        while (iter.hasNext()) {
            Bullet bullet = iter.next();
            if (!this.isAlive() || !bullet.isFired() || !(Math.sqrt((bullet.getX() - this.getX()) * (bullet.getX() - this.getX()) + (bullet.getY() - this.getY()) * (bullet.getY() - this.getY())) <= (double)(this.radius + bullet.getRadius()))) continue;
            Game.Score.incrementScore();
            iter.remove();
            return true;
        }
        return false;
    }

    public boolean isCollidingWithBullets(LinkedList<Bullet> bullets) {
        Iterator iter = bullets.iterator();
        while (iter.hasNext()) {
            Bullet bullet = (Bullet)iter.next();
            if (!this.isAlive() || !bullet.isFired() || !(Math.sqrt((bullet.getX() - this.getX()) * (bullet.getX() - this.getX()) + (bullet.getY() - this.getY()) * (bullet.getY() - this.getY())) <= (double)(this.radius + bullet.getRadius()))) continue;
            Game.Score.incrementScore();
            iter.remove();
            return true;
        }
        return false;
    }

    public void loadImage() {
        Random r = new Random();
        this.ghostImage = Game.ghosts[r.nextInt(4)];
    }

    public void loadImage(int imageIndex) {
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

    public void setSpeed(int newSpeed) {
        this.speed = newSpeed;
    }

    public int getPlayerToFollow() {
        return this.playerToFollow;
    }
}

