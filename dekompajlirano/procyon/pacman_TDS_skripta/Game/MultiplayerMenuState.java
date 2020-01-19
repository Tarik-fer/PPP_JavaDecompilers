// 
// Decompiled by Procyon v0.5.36
// 

package Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.GameContainer;

public class MultiplayerMenuState
{
    static int errorTimer;
    static boolean error;
    static boolean isServer;
    
    public static void update(final GameContainer gc, final Input input, final int delta, final int mouseX, final int mouseY) {
        if (input.isMousePressed(0)) {
            if (Game.menuButton.isPressed()) {
                Window.clear(input);
                Game.IPTextField.setText("");
                Game.DestinationPortTextField.setText("");
                Game.SourcePortTextField.setText("");
                MultiplayerMenuState.isServer = false;
                Game.isServer = false;
                Game.state = 0;
            }
            if (Game.playButton.isPressed(Window.HALF_WIDTH, Window.HEIGHT - Window.HEIGHT / 4)) {
                if (Game.IPTextField.getText() != null && !Game.IPTextField.getText().equals("") && Game.SourcePortTextField.getText() != null && !Game.SourcePortTextField.getText().equals("") && Game.DestinationPortTextField.getText() != null && !Game.DestinationPortTextField.getText().equals("")) {
                    Window.clear(input);
                    Game.IP = Game.IPTextField.getText();
                    Game.SourcePort = Integer.parseInt(Game.SourcePortTextField.getText());
                    Game.DestinationPort = Integer.parseInt(Game.DestinationPortTextField.getText());
                    Game.IPTextField.setText("");
                    Game.DestinationPortTextField.setText("");
                    Game.SourcePortTextField.setText("");
                    ++Game.multiplayerGameID;
                    Game.state = 3;
                }
                else {
                    MultiplayerMenuState.error = true;
                }
            }
            if (Game.serverButton.isPressed()) {
                Game.isServer = !Game.isServer;
                MultiplayerMenuState.isServer = !MultiplayerMenuState.isServer;
            }
        }
        if (input.isKeyPressed(1)) {
            Window.clear(input);
            Game.IPTextField.setText("");
            Game.DestinationPortTextField.setText("");
            Game.SourcePortTextField.setText("");
            MultiplayerMenuState.isServer = false;
            Game.isServer = false;
            Game.state = 0;
        }
        if (input.isKeyPressed(28)) {
            if (Game.IPTextField.getText() != null && !Game.IPTextField.getText().equals("") && Game.SourcePortTextField.getText() != null && !Game.SourcePortTextField.getText().equals("") && Game.DestinationPortTextField.getText() != null && !Game.DestinationPortTextField.getText().equals("")) {
                Window.clear(input);
                Game.IP = Game.IPTextField.getText();
                Game.SourcePort = Integer.parseInt(Game.SourcePortTextField.getText());
                Game.DestinationPort = Integer.parseInt(Game.DestinationPortTextField.getText());
                Game.IPTextField.setText("");
                Game.DestinationPortTextField.setText("");
                Game.SourcePortTextField.setText("");
                ++Game.multiplayerGameID;
                Game.state = 3;
            }
            else {
                MultiplayerMenuState.error = true;
            }
        }
        if (MultiplayerMenuState.error) {
            MultiplayerMenuState.errorTimer -= delta;
            if (MultiplayerMenuState.errorTimer < 0) {
                MultiplayerMenuState.error = false;
                MultiplayerMenuState.errorTimer = 800;
            }
        }
        Game.menuButton.hoverEffect();
        Game.playButton.hoverEffect(Window.HALF_WIDTH, Window.HEIGHT - Window.HEIGHT / 4);
        Game.serverButton.hoverEffect();
    }
    
    public static void render(final GameContainer gc, final Graphics g) {
        Game.IPTextField.render((GUIContext)gc, g);
        Game.DestinationPortTextField.render((GUIContext)gc, g);
        Game.SourcePortTextField.render((GUIContext)gc, g);
        Game.IPFont.drawString("IP:", Window.HALF_WIDTH - 300, Window.HALF_HEIGHT - 200, Color.white);
        Game.SourcePortFont.drawString("SOURCE PORT:", Window.HALF_WIDTH - 400, Window.HALF_HEIGHT - 120, Color.white);
        Game.DestinationPortFont.drawString("DEST. PORT:", Window.HALF_WIDTH - 380, Window.HALF_HEIGHT - 48, Color.white);
        Game.smallFont.drawString("YOUR IP: " + Game.localIP, Window.WIDTH / 64, Window.HEIGHT / 40, Color.white);
        Game.menuButton.render();
        if (MultiplayerMenuState.isServer) {
            Game.serverButton.setColor(Color.green);
        }
        else {
            Game.serverButton.setColor(Color.red);
        }
        Game.serverButton.render();
        if (!MultiplayerMenuState.error) {
            Game.playButton.render(Window.HALF_WIDTH, Window.HEIGHT - Window.HEIGHT / 4);
        }
        else {
            Game.playButton.render("FILL ALL THE FIELDS!", Window.HALF_WIDTH, Window.HEIGHT - Window.HEIGHT / 4);
        }
    }
    
    static {
        MultiplayerMenuState.errorTimer = 800;
        MultiplayerMenuState.error = false;
        MultiplayerMenuState.isServer = false;
    }
}
