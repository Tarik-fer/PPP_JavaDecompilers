// 
// Decompiled by Procyon v0.5.36
// 

package Game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;
import org.newdawn.slick.GameContainer;

public class GameOverState
{
    public static void update(final GameContainer gc, final Input input, final int delta, final int mouseX, final int mouseY) throws SlickException {
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
    
    public static void render(final GameContainer gc, final Graphics g) throws SlickException {
        Game.youLostImage.drawCentered((float)Window.HALF_WIDTH, (float)(Window.HEIGHT / 4));
        Game.menuButton.render();
        Game.retryButton.render();
    }
}
