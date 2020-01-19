/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  org.newdawn.slick.Color
 *  org.newdawn.slick.GameContainer
 *  org.newdawn.slick.Graphics
 *  org.newdawn.slick.Input
 *  org.newdawn.slick.SlickException
 */
package Game;

import Game.Button;
import Game.Game;
import Game.GameFont;
import Game.ScoreManager;
import Game.Window;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class LeaderBoardState {
    public static GameFont titleFont = Game.playFont;
    public static GameFont highscoreFont = Game.IPFont;
    public static boolean showMultiplayerScores = false;

    public static void update(GameContainer gc, Input input, int delta, int mouseX, int mouseY) throws SlickException {
        if (input.isMousePressed(0)) {
            if (Game.menuButton.isPressed()) {
                Window.clear(input);
                Game.state = 0;
            }
            if (Game.showMultiplayerScoresButton.isPressed()) {
                Window.clear(input);
                boolean bl = showMultiplayerScores = !showMultiplayerScores;
            }
        }
        if (input.isKeyPressed(1)) {
            Window.clear(input);
            Game.state = 0;
        }
        Game.menuButton.hoverEffect();
        Game.showMultiplayerScoresButton.hoverEffect();
    }

    public static void render(GameContainer gc, Graphics g) throws SlickException {
        titleFont.drawCenteredString("HIGHSCORES:", Window.HALF_WIDTH, Window.HEIGHT / 4, Color.white);
        if (!showMultiplayerScores) {
            Game.showMultiplayerScoresButton.render();
            titleFont.drawCenteredString("SINGLE PLAYER", Window.HALF_WIDTH, Window.HEIGHT / 4 - Window.WIDTH / 20, Color.white);
            Game.Score.sortScores();
            for (int i = 0; i < Game.Score.scoreStrings.length; ++i) {
                highscoreFont.drawCenteredString(Game.Score.scoreStrings[i], Window.HALF_WIDTH, (int)((double)Window.HEIGHT / 2.6) + i * 64, Color.white);
            }
        } else {
            Game.showMultiplayerScoresButton.render("SHOW SINGLE PLAYER HIGHSCORES");
            titleFont.drawCenteredString("MULTIPLAYER", Window.HALF_WIDTH, Window.HEIGHT / 4 - Window.WIDTH / 20, Color.white);
            Game.Score.sortMultiplayerScores();
            for (int i = 0; i < Game.Score.multiplayerScoreStrings.length; ++i) {
                highscoreFont.drawCenteredString(Game.Score.multiplayerScoreStrings[i], Window.HALF_WIDTH, (int)((double)Window.HEIGHT / 2.6) + i * 64, Color.white);
            }
        }
        Game.menuButton.render();
    }
}

