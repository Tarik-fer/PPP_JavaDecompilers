package Game;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

public class ScoreManager implements Serializable {
   private int score = 0;
   private int[] scores = new int[]{0, 0, 0, 0, 0};
   private int[] multiplayerScores = new int[]{0, 0, 0, 0, 0};
   public String[] scoreStrings;
   public String[] multiplayerScoreStrings;

   public ScoreManager() {
      this.scoreStrings = new String[this.scores.length];
      this.multiplayerScoreStrings = new String[this.multiplayerScores.length];
   }

   public void incrementScore() {
      this.score += 10;
   }

   public void resetScore() {
      this.score = 0;
   }

   public int getScore() {
      return this.score;
   }

   public void setScore(int score) {
      this.score = score;
   }

   public boolean checkNewHighScore() {
      for(int i = 0; i < this.scores.length; ++i) {
         if (this.score > this.scores[i]) {
            int min = -1;

            int c;
            for(c = 0; c < this.scores.length; ++c) {
               if (min > this.scores[c]) {
                  min = this.scores[c];
               }
            }

            c = 0;

            for(int j = 0; j < this.scores.length; ++j) {
               if (this.scores[j] == min) {
                  c = j;
                  break;
               }
            }

            this.scores[c] = this.score;
            Arrays.sort(this.scores);
            return true;
         }
      }

      return false;
   }

   public boolean checkNewMultiplayerHighScore() {
      for(int i = 0; i < this.multiplayerScores.length; ++i) {
         if (this.score > this.multiplayerScores[i]) {
            int min = -1;

            int c;
            for(c = 0; c < this.multiplayerScores.length; ++c) {
               if (min > this.multiplayerScores[c]) {
                  min = this.multiplayerScores[c];
               }
            }

            c = 0;

            for(int j = 0; j < this.multiplayerScores.length; ++j) {
               if (this.multiplayerScores[j] == min) {
                  c = j;
                  break;
               }
            }

            this.multiplayerScores[c] = this.score;
            Arrays.sort(this.multiplayerScores);
            return true;
         }
      }

      return false;
   }

   private String getStringFromScore(int score) {
      String scoreString = Integer.toString(score);
      if (scoreString.length() < 8) {
         int diff = 8 - scoreString.length();

         for(int i = 0; i < diff; ++i) {
            scoreString = "0" + scoreString;
         }
      }

      return scoreString;
   }

   public void sortScores() {
      int k = 0;

      for(int i = this.scores.length - 1; i >= 0; --i) {
         this.scoreStrings[k] = this.getStringFromScore(this.scores[i]);
         ++k;
      }

   }

   public void sortMultiplayerScores() {
      int k = 0;

      for(int i = this.multiplayerScores.length - 1; i >= 0; --i) {
         this.multiplayerScoreStrings[k] = this.getStringFromScore(this.multiplayerScores[i]);
         ++k;
      }

   }

   public void saveScores() {
      try {
         ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream("pmmtds.scores"));
         Throwable var2 = null;

         try {
            outStream.writeObject(this);
            System.out.println("SCORES SAVED");
         } catch (Throwable var12) {
            var2 = var12;
            throw var12;
         } finally {
            if (outStream != null) {
               if (var2 != null) {
                  try {
                     outStream.close();
                  } catch (Throwable var11) {
                     var2.addSuppressed(var11);
                  }
               } else {
                  outStream.close();
               }
            }

         }
      } catch (IOException var14) {
         System.out.println("ERROR WHILE SAVING SCORES");
      }

   }
}
