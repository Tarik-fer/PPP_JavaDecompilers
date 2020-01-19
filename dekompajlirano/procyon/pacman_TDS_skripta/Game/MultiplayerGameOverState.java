// 
// Decompiled by Procyon v0.5.36
// 

package Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.GameContainer;

public class MultiplayerGameOverState
{
    public static void update(final GameContainer gc, final Input input, final int delta, final int mouseX, final int mouseY) {
        if (input.isMousePressed(0)) {
            if (Game.menuButton.isPressed()) {
                Window.clear(input);
                MultiplayerMenuState.isServer = false;
                Game.isServer = false;
                Game.state = 0;
            }
            if (Game.retryButton.isPressed()) {
                Window.clear(input);
                ++Game.multiplayerGameID;
                Game.state = 3;
            }
        }
        if (input.isKeyPressed(1)) {
            Window.clear(input);
            MultiplayerMenuState.isServer = false;
            Game.isServer = false;
            Game.state = 0;
        }
        if (input.isKeyPressed(19)) {
            Window.clear(input);
            ++Game.multiplayerGameID;
            Game.state = 3;
        }
        Game.menuButton.hoverEffect();
        Game.retryButton.hoverEffect();
    }
    
    public static void render(final GameContainer gc, final Graphics g) {
        Game.playFont.drawCenteredString(Game.winnerString, Window.HALF_WIDTH, Window.HEIGHT / 4, Color.red);
        Game.menuButton.render();
        Game.retryButton.render();
    }
}
