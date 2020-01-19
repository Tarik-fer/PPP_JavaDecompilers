package Game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

public class Bullet {
   private Point Coordinates = new Point(0.0F, 0.0F);
   private boolean fired = false;
   private final float speed = 564.0F;
   private int startX;
   private int startY;
   private int destX;
   private int destY;
   private float dx;
   private float dy;
   private int diameter = 6;
   private int radius;

   public Bullet(int startX, int startY, int destX, int destY) {
      this.radius = this.diameter / 2;
      this.startX = startX;
      this.startY = startY;
      this.destX = destX;
      this.destY = destY;
      this.setFired();
      this.Coordinates.setLocation((float)this.startX, (float)this.startY);
      float rad = (float)Math.atan2((double)(this.destX - this.startX), (double)(this.startY - this.destY));
      float var10001 = (float)Math.sin((double)rad);
      this.getClass();
      this.dx = var10001 * 564.0F;
      var10001 = -((float)Math.cos((double)rad));
      this.getClass();
      this.dy = var10001 * 564.0F;
   }

   public Bullet() {
      this.radius = this.diameter / 2;
   }

   public void render() {
      if (this.isFired()) {
         (new Graphics()).fillOval(this.Coordinates.getX() - (float)this.radius, this.Coordinates.getY() - (float)this.radius, (float)this.diameter, (float)this.diameter);
      }

   }

   public void update(int delta) {
      if (this.isFired()) {
         float x = this.Coordinates.getX();
         float y = this.Coordinates.getY();
         x += this.dx * (float)delta / 1000.0F;
         y += this.dy * (float)delta / 1000.0F;
         this.Coordinates.setLocation(x, y);
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
      return this.Coordinates.getX() < -10.0F || this.Coordinates.getX() > (float)(gc.getWidth() + 10) || this.Coordinates.getY() < -10.0F || this.Coordinates.getY() > (float)(gc.getHeight() + 10);
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
