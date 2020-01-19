package Game;

import java.awt.Point;
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
   private Point Coordinates;
   private final int speed;
   private final int damage;
   private boolean alive;
   private HealthBar healthbar;
   private int ammos;
   private int ID;

   public Player() {
      this.Coordinates = new Point(Window.WIDTH / 2, Window.HEIGHT / 2);
      this.speed = 324;
      this.damage = 10;
      this.alive = true;
      this.ammos = 50;
      this.ID = Game.players++;
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
      } catch (SlickException var2) {
         System.out.println("playerImage not found");
      }

   }

   public void update(Input input, int delta) {
      if (this.alive) {
         this.rotation = (int)this.getAngleFromPoint(new Point(input.getMouseX(), input.getMouseY()), this.Coordinates);
         this.getClass();
         int speed = 324 * delta / 1000;
         Point var10000;
         if (input.isKeyDown(32)) {
            var10000 = this.Coordinates;
            var10000.x += speed;
         }

         if (input.isKeyDown(30)) {
            var10000 = this.Coordinates;
            var10000.x -= speed;
         }

         if (input.isKeyDown(17)) {
            var10000 = this.Coordinates;
            var10000.y -= speed;
         }

         if (input.isKeyDown(31)) {
            var10000 = this.Coordinates;
            var10000.y += speed;
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

   public void checkIfPickedUpAmmos(Ammo[] ammos) throws SlickException {
      Ammo[] var2 = ammos;
      int var3 = ammos.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         Ammo ammo = var2[var4];
         if (this.health != 0 && !ammo.alreadyPicked() && Math.sqrt((double)((ammo.getX() - this.getX()) * (ammo.getX() - this.getX()) + (ammo.getY() - this.getY()) * (ammo.getY() - this.getY()))) <= (double)(this.radius + ammo.getRadius())) {
            ammo.pick();
            (new Sound("Sounds/pacmanPickedAmmo.wav")).play();
            this.ammos += 50;
         }
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
