package Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class HealthBar {
   private int x;
   private int y;

   public HealthBar(int x, int y) {
      this.x = x;
      this.y = y;
   }

   public void render(int health, Graphics g) {
      g.setColor(Color.red);
      g.fillRect((float)this.x, (float)this.y, (float)health, 10.0F);
      g.setColor(Color.white);
   }

   public void render(int x, int y, int health, Graphics g) {
      g.setColor(Color.green);
      g.fillRect((float)x, (float)y, (float)health, 6.0F);
      g.setColor(Color.white);
   }

   public void render(int x, int y, int health, Graphics g, boolean isAnotherPlayer) {
      g.setColor(GameFont.optionBlue);
      g.fillRect((float)x, (float)y, (float)health, 6.0F);
      g.setColor(Color.white);
   }
}
