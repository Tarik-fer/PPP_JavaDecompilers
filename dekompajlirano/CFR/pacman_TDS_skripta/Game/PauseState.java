/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  org.newdawn.slick.GameContainer
 *  org.newdawn.slick.Graphics
 *  org.newdawn.slick.Input
 *  org.newdawn.slick.SlickException
 */
package Game;

import Game.Ammo;
import Game.Bullet;
import Game.Button;
import Game.Enemy;
import Game.Game;
import Game.Player;
import Game.ScoreManager;
import Game.Window;
import java.util.ArrayList;
import java.util.LinkedList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class PauseState {
    public static void update(GameContainer gc, Input input, int delta, int mouseX, int mouseY) throws SlickException {
        if (input.isMousePressed(0)) {
            if (Game.menuButton.isPressed()) {
                Window.clear(input);
                Game.startDelay = 500;
                Game.canSpawnAmmo = 10000;
                Game.paused = false;
                Game.player.reset();
                Game.enemyList.clear();
                Game.bulletList.clear();
                for (Ammo ammo : Game.ammos) {
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
            for (Ammo ammo : Game.ammos) {
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

