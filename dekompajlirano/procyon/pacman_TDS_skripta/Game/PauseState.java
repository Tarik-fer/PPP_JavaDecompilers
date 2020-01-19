// 
// Decompiled by Procyon v0.5.36
// 

package Game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;
import org.newdawn.slick.GameContainer;

public class PauseState
{
    public static void update(final GameContainer gc, final Input input, final int delta, final int mouseX, final int mouseY) throws SlickException {
        if (input.isMousePressed(0)) {
            if (Game.menuButton.isPressed()) {
                Window.clear(input);
                Game.startDelay = 500;
                Game.canSpawnAmmo = 10000;
                Game.paused = false;
                Game.player.reset();
                Game.enemyList.clear();
                Game.bulletList.clear();
                for (final Ammo ammo : Game.ammos) {
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
            for (final Ammo ammo : Game.ammos) {
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
    
    public static void render(final GameContainer gc, final Graphics g) throws SlickException {
        Game.resumeButton.render();
        Game.menuButton.render();
    }
}
