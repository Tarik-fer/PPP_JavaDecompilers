// 
// Decompiled by Procyon v0.5.36
// 

package Game;

import java.io.IOException;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.io.Serializable;

public class ScoreManager implements Serializable
{
    private int score;
    private int[] scores;
    private int[] multiplayerScores;
    public String[] scoreStrings;
    public String[] multiplayerScoreStrings;
    
    public ScoreManager() {
        this.score = 0;
        this.scores = new int[] { 0, 0, 0, 0, 0 };
        this.multiplayerScores = new int[] { 0, 0, 0, 0, 0 };
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
    
    public void setScore(final int score) {
        this.score = score;
    }
    
    public boolean checkNewHighScore() {
        for (int i = 0; i < this.scores.length; ++i) {
            if (this.score > this.scores[i]) {
                int min = -1;
                for (int j = 0; j < this.scores.length; ++j) {
                    if (min > this.scores[j]) {
                        min = this.scores[j];
                    }
                }
                int c = 0;
                for (int k = 0; k < this.scores.length; ++k) {
                    if (this.scores[k] == min) {
                        c = k;
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
        for (int i = 0; i < this.multiplayerScores.length; ++i) {
            if (this.score > this.multiplayerScores[i]) {
                int min = -1;
                for (int j = 0; j < this.multiplayerScores.length; ++j) {
                    if (min > this.multiplayerScores[j]) {
                        min = this.multiplayerScores[j];
                    }
                }
                int c = 0;
                for (int k = 0; k < this.multiplayerScores.length; ++k) {
                    if (this.multiplayerScores[k] == min) {
                        c = k;
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
    
    private String getStringFromScore(final int score) {
        String scoreString = Integer.toString(score);
        if (scoreString.length() < 8) {
            for (int diff = 8 - scoreString.length(), i = 0; i < diff; ++i) {
                scoreString = "0" + scoreString;
            }
        }
        return scoreString;
    }
    
    public void sortScores() {
        int k = 0;
        for (int i = this.scores.length - 1; i >= 0; --i) {
            this.scoreStrings[k] = this.getStringFromScore(this.scores[i]);
            ++k;
        }
    }
    
    public void sortMultiplayerScores() {
        int k = 0;
        for (int i = this.multiplayerScores.length - 1; i >= 0; --i) {
            this.multiplayerScoreStrings[k] = this.getStringFromScore(this.multiplayerScores[i]);
            ++k;
        }
    }
    
    public void saveScores() {
        try (final ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream("pmmtds.scores"))) {
            outStream.writeObject(this);
            System.out.println("SCORES SAVED");
        }
        catch (IOException e) {
            System.out.println("ERROR WHILE SAVING SCORES");
        }
    }
}
