package Game;

import java.util.Random;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Ammo {
   private Image ammoImage;
   private int size = 30;
   private int x;
   private int y;
   private boolean picked = false;
   private boolean isPickable = false;

   public Ammo(GameContainer gc) {
      this.isPickable = true;
      this.x = (new Random()).nextInt(gc.getWidth()) + 1;
      this.y = (new Random()).nextInt(gc.getHeight()) + 1;
      this.loadImage();
   }

   public Ammo() {
      this.isPickable = false;
   }

   private void loadImage() {
      try {
         this.ammoImage = new Image("Images/Ammos.png");
      } catch (SlickException var2) {
         System.out.println("ammoImage not found!");
      }

   }

   public void render() {
      if (!this.picked && this.isPickable) {
         this.ammoImage.drawCentered((float)this.x, (float)this.y);
      }

   }

   public int getX() {
      return this.x;
   }

   public int getY() {
      return this.y;
   }

   public int getRadius() {
      return this.size;
   }

   public void pick() {
      this.picked = true;
      this.isPickable = false;
   }

   public boolean alreadyPicked() {
      return this.picked;
   }

   public boolean isPickable() {
      return this.isPickable;
   }
}
