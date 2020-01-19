/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  org.newdawn.slick.GameContainer
 *  org.newdawn.slick.Graphics
 *  org.newdawn.slick.Image
 *  org.newdawn.slick.Input
 *  org.newdawn.slick.SlickException
 *  org.newdawn.slick.Sound
 */
package Game;

import Game.Button;
import Game.Game;
import Game.Window;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class GameOverState {
    public static void update(GameContainer gc, Input input, int delta, int mouseX, int mouseY) throws SlickException {
        if (input.isMousePressed(0)) {
            if (Game.menuButton.isPressed()) {
                Window.clear(input);
                Game.state = 0;
            }
            if (Game.retryButton.isPressed()) {
                Window.clear(input);
                Game.openingSound.play();
                Game.state = 1;
            }
        }
        if (input.isKeyPressed(1)) {
            Window.clear(input);
            Game.state = 0;
        }
        if (input.isKeyPressed(19)) {
            Window.clear(input);
            Game.openingSound.play();
            Game.state = 1;
        }
        Game.menuButton.hoverEffect();
        Game.retryButton.hoverEffect();
    }

    public static void render(GameContainer gc, Graphics g) throws SlickException {
        Game.youLostImage.drawCentered((float)Window.HALF_WIDTH, (float)(Window.HEIGHT / 4));
        Game.menuButton.render();
        Game.retryButton.render();
    }
}

