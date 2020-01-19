/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  org.newdawn.slick.Color
 *  org.newdawn.slick.GameContainer
 *  org.newdawn.slick.Graphics
 *  org.newdawn.slick.Input
 */
package Game;

import Game.Button;
import Game.Game;
import Game.GameFont;
import Game.MultiplayerMenuState;
import Game.Window;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class MultiplayerGameOverState {
    public static void update(GameContainer gc, Input input, int delta, int mouseX, int mouseY) {
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

    public static void render(GameContainer gc, Graphics g) {
        Game.playFont.drawCenteredString(Game.winnerString, Window.HALF_WIDTH, Window.HEIGHT / 4, Color.red);
        Game.menuButton.render();
        Game.retryButton.render();
    }
}

