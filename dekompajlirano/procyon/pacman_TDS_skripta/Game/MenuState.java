// 
// Decompiled by Procyon v0.5.36
// 

package Game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.GameContainer;

public class MenuState
{
    public static void update(final GameContainer gc, final Input input, final int delta, final int mouseX, final int mouseY) {
        if (input.isMousePressed(0)) {
            if (Game.playButton.isPressed()) {
                Window.clear(input);
                Game.openingSound.play();
                Game.state = 1;
            }
            if (Game.multiplayerButton.isPressed()) {
                Window.clear(input);
                Game.state = 2;
                Game.IPTextField.setText("");
                Game.DestinationPortTextField.setText("");
                Game.SourcePortTextField.setText("");
            }
            if (Game.leaderboardButton.isPressed()) {
                Window.clear(input);
                Game.state = 5;
            }
            if (Game.quitButton.isPressed()) {
                System.exit(0);
            }
        }
        if (input.isKeyPressed(28)) {
            Window.clear(input);
            Game.openingSound.play();
            Game.state = 1;
        }
        if (input.isKeyPressed(50)) {
            Window.clear(input);
            Game.state = 2;
            Game.IPTextField.setText("");
            Game.DestinationPortTextField.setText("");
            Game.SourcePortTextField.setText("");
        }
        if (input.isKeyPressed(38)) {
            Window.clear(input);
            Game.state = 5;
        }
        if (input.isKeyPressed(1)) {
            System.exit(0);
        }
        Game.playButton.hoverEffect();
        Game.multiplayerButton.hoverEffect();
        Game.leaderboardButton.hoverEffect();
        Game.quitButton.hoverEffect();
    }
    
    public static void render(final GameContainer gc, final Graphics g) {
        Game.titleImage.drawCentered((float)Window.HALF_WIDTH, (float)(Window.HEIGHT / 5));
        Game.playButton.render();
        Game.multiplayerButton.render();
        Game.leaderboardButton.render();
        Game.quitButton.render();
    }
}
