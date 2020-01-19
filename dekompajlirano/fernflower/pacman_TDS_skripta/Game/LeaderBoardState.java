package Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class LeaderBoardState {
   public static GameFont titleFont;
   public static GameFont highscoreFont;
   public static boolean showMultiplayerScores;

   public static void update(GameContainer gc, Input input, int delta, int mouseX, int mouseY) throws SlickException {
      if (input.isMousePressed(0)) {
         if (Game.menuButton.isPressed()) {
            Window.clear(input);
            Game.state = 0;
         }

         if (Game.showMultiplayerScoresButton.isPressed()) {
            Window.clear(input);
            showMultiplayerScores = !showMultiplayerScores;
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
      int i;
      if (!showMultiplayerScores) {
         Game.showMultiplayerScoresButton.render();
         titleFont.drawCenteredString("SINGLE PLAYER", Window.HALF_WIDTH, Window.HEIGHT / 4 - Window.WIDTH / 20, Color.white);
         Game.Score.sortScores();

         for(i = 0; i < Game.Score.scoreStrings.length; ++i) {
            highscoreFont.drawCenteredString(Game.Score.scoreStrings[i], Window.HALF_WIDTH, (int)((double)Window.HEIGHT / 2.6D) + i * 64, Color.white);
         }
      } else {
         Game.showMultiplayerScoresButton.render("SHOW SINGLE PLAYER HIGHSCORES");
         titleFont.drawCenteredString("MULTIPLAYER", Window.HALF_WIDTH, Window.HEIGHT / 4 - Window.WIDTH / 20, Color.white);
         Game.Score.sortMultiplayerScores();

         for(i = 0; i < Game.Score.multiplayerScoreStrings.length; ++i) {
            highscoreFont.drawCenteredString(Game.Score.multiplayerScoreStrings[i], Window.HALF_WIDTH, (int)((double)Window.HEIGHT / 2.6D) + i * 64, Color.white);
         }
      }

      Game.menuButton.render();
   }

   static {
      titleFont = Game.playFont;
      highscoreFont = Game.IPFont;
      showMultiplayerScores = false;
   }
}
