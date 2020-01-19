package Game;

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
   private float speed = 168.0F;
   private float dx;
   private float dy;
   private int diameter = 84;
   private int radius;
   private int playerToFollow;

   public Enemy(int startX, int startY) {
      this.radius = this.diameter / 2;
      this.coordinates.setLocation(startX, startY);
      this.loadImage();
   }

   public Enemy(int startX, int startY, int imageIndex, int playerToFollow) {
      this.radius = this.diameter / 2;
      this.coordinates.setLocation(startX, startY);
      this.playerToFollow = playerToFollow;
      this.loadImage(imageIndex);
   }

   public Enemy() {
      this.radius = this.diameter / 2;
   }

   public void update(Point playerCoordinates, int delta) {
      float rad = (float)Math.atan2((double)playerCoordinates.x - this.coordinates.getX(), this.coordinates.getY() - (double)playerCoordinates.y);
      this.dx = (float)Math.sin((double)rad) * this.speed;
      this.dy = -((float)Math.cos((double)rad)) * this.speed;
      float x = (float)this.coordinates.getX();
      float y = (float)this.coordinates.getY();
      x += this.dx * (float)delta / 1000.0F;
      y += this.dy * (float)delta / 1000.0F;
      this.alive = true;
      this.coordinates.setLocation((double)x, (double)y);
   }

   public void render() {
      if (this.isAlive()) {
         this.ghostImage.drawCentered((float)this.coordinates.getX(), (float)this.coordinates.getY());
      }

   }

   public void detectCollisionWithBullet(ArrayList bullets) {
      Iterator iter = bullets.iterator();

      while(iter.hasNext()) {
         Bullet bullet = (Bullet)iter.next();
         if (this.isAlive() && bullet.isFired() && Math.sqrt((double)((bullet.getX() - this.getX()) * (bullet.getX() - this.getX()) + (bullet.getY() - this.getY()) * (bullet.getY() - this.getY()))) <= (double)(this.radius + bullet.getRadius())) {
            this.alive = false;
            Game.Score.incrementScore();
            iter.remove();
         }
      }

   }

   public boolean isCollidingWithBullets(ArrayList bullets) {
      Iterator iter = bullets.iterator();

      Bullet bullet;
      do {
         if (!iter.hasNext()) {
            return false;
         }

         bullet = (Bullet)iter.next();
      } while(!this.isAlive() || !bullet.isFired() || Math.sqrt((double)((bullet.getX() - this.getX()) * (bullet.getX() - this.getX()) + (bullet.getY() - this.getY()) * (bullet.getY() - this.getY()))) > (double)(this.radius + bullet.getRadius()));

      Game.Score.incrementScore();
      iter.remove();
      return true;
   }

   public boolean isCollidingWithBullets(LinkedList bullets) {
      Iterator iter = bullets.iterator();

      Bullet bullet;
      do {
         if (!iter.hasNext()) {
            return false;
         }

         bullet = (Bullet)iter.next();
      } while(!this.isAlive() || !bullet.isFired() || Math.sqrt((double)((bullet.getX() - this.getX()) * (bullet.getX() - this.getX()) + (bullet.getY() - this.getY()) * (bullet.getY() - this.getY()))) > (double)(this.radius + bullet.getRadius()));

      Game.Score.incrementScore();
      iter.remove();
      return true;
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
      this.speed = (float)newSpeed;
   }

   public int getPlayerToFollow() {
      return this.playerToFollow;
   }
}
