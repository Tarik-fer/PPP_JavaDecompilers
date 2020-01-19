package Game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Opponent {
   private Image playerImage;
   private int health = 100;
   private int rotation = 0;
   private int radius = 45;
   private Point Coordinates;
   private final int damage;
   private boolean alive;
   private HealthBar healthbar;
   static int ID;
   private float xRatio;
   private float yRatio;

   public Opponent() {
      this.Coordinates = new Point(Window.HALF_WIDTH, Window.HALF_HEIGHT);
      this.damage = 10;
      this.alive = true;
      this.xRatio = 1.0F;
      this.yRatio = 1.0F;
      this.loadImage();
      this.healthbar = new HealthBar(this.Coordinates.x, this.Coordinates.y);
   }

   public void render(Graphics g) {
      if (this.isAlive()) {
         this.playerImage.drawCentered((float)this.Coordinates.x, (float)this.Coordinates.y);
         this.playerImage.setRotation((float)this.rotation);
         this.healthbar.render(this.Coordinates.x - 24, this.Coordinates.y - 80 + 30, this.getHealth() / 2, g, true);
      }

   }

   public void loadImage() {
      try {
         this.playerImage = new Image("Images/Pac Man.png");
      } catch (SlickException var2) {
         System.out.println("playerImage not found");
      }

   }

   public void update(Point oppCoordinates, int x, int y) {
      if (this.alive) {
         this.rotation = (int)this.getAngleFromPoint(new Point(x, y), this.Coordinates);
         this.Coordinates.x = (int)((float)oppCoordinates.x * this.xRatio);
         this.Coordinates.y = (int)((float)oppCoordinates.y * this.yRatio);
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

   public void detectCollisionWithBullet(Bullet[] bullets) throws SlickException {
      Bullet[] var2 = bullets;
      int var3 = bullets.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         Bullet bullet = var2[var4];
         if (this.isAlive() && Math.sqrt((double)((bullet.getX() - this.getX()) * (bullet.getX() - this.getX()) + (bullet.getY() - this.getY()) * (bullet.getY() - this.getY()))) <= (double)(this.radius + bullet.getRadius())) {
            bullet.remove();
         }
      }

   }

   public void detectCollisionWithBullet(ArrayList bullets) throws SlickException {
      Iterator iter = bullets.iterator();

      while(iter.hasNext()) {
         Bullet bullet = (Bullet)iter.next();
         if (this.isAlive() && Math.sqrt((double)((bullet.getX() - this.getX()) * (bullet.getX() - this.getX()) + (bullet.getY() - this.getY()) * (bullet.getY() - this.getY()))) <= (double)(this.radius + bullet.getRadius())) {
            this.hit();
            iter.remove();
         }
      }

   }

   public void detectCollisionWithEnemies(ArrayList enemies) throws SlickException {
      try {
         Iterator iter = enemies.iterator();

         while(iter.hasNext()) {
            Enemy enemy = (Enemy)iter.next();
            if (this.health != 0 && enemy.isAlive() && Math.sqrt((double)((enemy.getX() - this.getX()) * (enemy.getX() - this.getX()) + (enemy.getY() - this.getY()) * (enemy.getY() - this.getY()))) <= (double)(this.radius + enemy.getRadius())) {
               iter.remove();
               this.hit();
            }
         }
      } catch (ConcurrentModificationException var4) {
         System.out.println("Poteva andare peggio...");
      }

   }

   private double getAngleFromPoint(Point firstPoint, Point secondPoint) {
      double r;
      if (secondPoint.x > firstPoint.x) {
         r = Math.atan2((double)(secondPoint.x - firstPoint.x), (double)(firstPoint.y - secondPoint.y)) * 180.0D / 3.141592653589793D + 90.0D;
      } else if (secondPoint.x < firstPoint.x) {
         r = 360.0D - Math.atan2((double)(firstPoint.x - secondPoint.x), (double)(firstPoint.y - secondPoint.y)) * 180.0D / 3.141592653589793D + 90.0D;
      } else {
         r = Math.atan2(0.0D, 0.0D) + 90.0D;
      }

      return r == 90.0D && Game.mouseY < this.Coordinates.y ? 270.0D : r;
   }

   public void hit() {
      int var10001 = this.health;
      this.getClass();
      this.health = var10001 - 10;
   }

   public int getHealth() {
      return this.health;
   }

   public void setHealth(int health) {
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
      return ID;
   }

   public void reset() {
      this.alive = true;
      this.health = 100;
      this.loadImage();
      this.Coordinates = new Point(Window.HALF_WIDTH, Window.HALF_HEIGHT);
   }

   static {
      ID = Game.players++;
   }
}
