package Game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class PauseState {
   public static void update(GameContainer gc, Input input, int delta, int mouseX, int mouseY) throws SlickException {
      Ammo[] var5;
      int var6;
      int var7;
      Ammo ammo;
      if (input.isMousePressed(0)) {
         if (Game.menuButton.isPressed()) {
            Window.clear(input);
            Game.startDelay = 500;
            Game.canSpawnAmmo = 10000;
            Game.paused = false;
            Game.player.reset();
            Game.enemyList.clear();
            Game.bulletList.clear();
            var5 = Game.ammos;
            var6 = var5.length;

            for(var7 = 0; var7 < var6; ++var7) {
               ammo = var5[var7];
               ammo.pick();
            }

            Game.Score.resetScore();
            Game.state = 0;
         }

         if (Game.resumeButton.isPressed()) {
            Window.clear(input);
            Game.state = 1;
         }
      }

      if (input.isKeyPressed(1)) {
         Window.clear(input);
         Game.startDelay = 500;
         Game.canSpawnAmmo = 10000;
         Game.paused = false;
         Game.player.reset();
         Game.enemyList.clear();
         Game.bulletList.clear();
         var5 = Game.ammos;
         var6 = var5.length;

         for(var7 = 0; var7 < var6; ++var7) {
            ammo = var5[var7];
            ammo.pick();
         }

         Game.Score.resetScore();
         Game.state = 0;
      }

      if (input.isKeyPressed(19)) {
         Window.clear(input);
         Game.state = 1;
      }

      Game.resumeButton.hoverEffect();
      Game.menuButton.hoverEffect();
   }

   public static void render(GameContainer gc, Graphics g) throws SlickException {
      Game.resumeButton.render();
      Game.menuButton.render();
   }
}
