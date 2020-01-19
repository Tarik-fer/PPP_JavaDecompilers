// 
// Decompiled by Procyon v0.5.36
// 

package Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;
import org.newdawn.slick.GameContainer;

public class LeaderBoardState
{
    public static GameFont titleFont;
    public static GameFont highscoreFont;
    public static boolean showMultiplayerScores;
    
    public static void update(final GameContainer gc, final Input input, final int delta, final int mouseX, final int mouseY) throws SlickException {
        if (input.isMousePressed(0)) {
            if (Game.menuButton.isPressed()) {
                Window.clear(input);
                Game.state = 0;
            }
            if (Game.showMultiplayerScoresButton.isPressed()) {
                Window.clear(input);
                LeaderBoardState.showMultiplayerScores = !LeaderBoardState.showMultiplayerScores;
            }
        }
        if (input.isKeyPressed(1)) {
            Window.clear(input);
            Game.state = 0;
        }
        Game.menuButton.hoverEffect();
        Game.showMultiplayerScoresButton.hoverEffect();
    }
    
    public static void render(final GameContainer gc, final Graphics g) throws SlickException {
        LeaderBoardState.titleFont.drawCenteredString("HIGHSCORES:", Window.HALF_WIDTH, Window.HEIGHT / 4, Color.white);
        if (!LeaderBoardState.showMultiplayerScores) {
            Game.showMultiplayerScoresButton.render();
            LeaderBoardState.titleFont.drawCenteredString("SINGLE PLAYER", Window.HALF_WIDTH, Window.HEIGHT / 4 - Window.WIDTH / 20, Color.white);
            Game.Score.sortScores();
            for (int i = 0; i < Game.Score.scoreStrings.length; ++i) {
                LeaderBoardState.highscoreFont.drawCenteredString(Game.Score.scoreStrings[i], Window.HALF_WIDTH, (int)(Window.HEIGHT / 2.6) + i * 64, Color.white);
            }
        }
        else {
            Game.showMultiplayerScoresButton.render("SHOW SINGLE PLAYER HIGHSCORES");
            LeaderBoardState.titleFont.drawCenteredString("MULTIPLAYER", Window.HALF_WIDTH, Window.HEIGHT / 4 - Window.WIDTH / 20, Color.white);
            Game.Score.sortMultiplayerScores();
            for (int i = 0; i < Game.Score.multiplayerScoreStrings.length; ++i) {
                LeaderBoardState.highscoreFont.drawCenteredString(Game.Score.multiplayerScoreStrings[i], Window.HALF_WIDTH, (int)(Window.HEIGHT / 2.6) + i * 64, Color.white);
            }
        }
        Game.menuButton.render();
    }
    
    static {
        LeaderBoardState.titleFont = Game.playFont;
        LeaderBoardState.highscoreFont = Game.IPFont;
        LeaderBoardState.showMultiplayerScores = false;
    }
}
