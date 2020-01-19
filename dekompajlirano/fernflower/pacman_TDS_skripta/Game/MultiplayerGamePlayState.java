package Game;

import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class MultiplayerGamePlayState {
   public static int canSpawnEnemy;
   static boolean justStarted;

   public static void update(GameContainer gc, Input input, int delta, int mouseX, int mouseY) throws SlickException {
      Game.startDelay -= delta;
      if (Game.startDelay < 0) {
         Game.canSpawnAmmo -= delta;
         if (input.isKeyPressed(1)) {
            quitGame();
         }

         checkIfPlayersDied();
         Game.canFire -= delta;
         if (Game.opponentFired) {
            addOpponentBullet();
            Game.opponentFired = false;
         }

         synchronized(Game.enemyList) {
            Game.enemyList.stream().forEach((enemy) -> {
               if (enemy.getPlayerToFollow() == 0) {
                  if (Game.isServer) {
                     enemy.update(Game.player.getCoordinates(), delta);
                  } else {
                     enemy.update(Game.opponent.getCoordinates(), delta);
                  }
               } else if (Game.isServer) {
                  enemy.update(Game.opponent.getCoordinates(), delta);
               } else {
                  enemy.update(Game.player.getCoordinates(), delta);
               }

            });
         }

         Game.bulletList.stream().forEach((bullet) -> {
            bullet.update(delta);
         });
         synchronized(Game.opponentBulletList) {
            Game.opponentBulletList.stream().forEach((bullet) -> {
               bullet.update(delta);
            });
         }

         Game.player.update(input, delta);
         Game.opponent.update(Game.opponentCoordinates, Game.opponentMouseCoordinates.x, Game.opponentMouseCoordinates.y);
         if (Game.player.isAlive() && input.isMouseButtonDown(0) && Game.canFire <= 0) {
            addBullet(mouseX, mouseY);
            Game.canFire = 200;
         }

         if (justStarted) {
            Game.Score.resetScore();
            justStarted = false;
         }

         Game.infoString = "" + Game.player.getX() + ":" + Game.player.getY() + ":" + mouseX + ":" + mouseY + ":" + Game.player.getHealth() + ":" + Game.Score.getScore();

         try {
            Game.connection.send(Game.infoString);
         } catch (IOException var8) {
            System.out.println("Error while sending coordinates to opponent " + var8);
         }

         getOpponentCoordinates();
      }

   }

   public static void render(GameContainer gc, Graphics g) throws SlickException {
      Game.player.render(g);
      Game.player.detectCollisionWithEnemies(Game.enemyList);
      Game.opponent.render(g);
      Game.opponent.detectCollisionWithEnemies(Game.enemyList);
      Iterator iter = Game.bulletList.iterator();

      while(iter.hasNext()) {
         Bullet bullet = (Bullet)iter.next();
         bullet.render();
         if (bullet.isOutOfBounds(gc)) {
            iter.remove();
         }
      }

      Iterator enemyIter;
      synchronized(Game.opponentBulletList) {
         enemyIter = Game.opponentBulletList.iterator();

         try {
            synchronized(enemyIter) {
               while(enemyIter.hasNext()) {
                  Bullet bullet = (Bullet)enemyIter.next();
                  synchronized(bullet) {
                     bullet.render();
                     if (bullet.isOutOfBounds(gc)) {
                        enemyIter.remove();
                     }
                  }
               }
            }
         } catch (ConcurrentModificationException var16) {
            System.out.println("Error rendering bullets");
         }
      }

      synchronized(Game.enemyList) {
         enemyIter = Game.enemyList.iterator();

         try {
            while(enemyIter.hasNext()) {
               Enemy enemy = (Enemy)enemyIter.next();
               enemy.render();
               if (enemy.isCollidingWithBullets(Game.bulletList)) {
                  enemyIter.remove();
               } else if (enemy.isCollidingWithBullets(Game.opponentBulletList)) {
                  enemyIter.remove();
               }
            }
         } catch (ConcurrentModificationException var13) {
            System.out.println("Error rendering enemies");
         }
      }

      Game.smallFont.drawString("Score: " + Game.Score.getScore(), 12, 12, Color.white);
   }

   public static void addBullet(int x, int y) {
      try {
         Game.connection.send("1");
      } catch (IOException var3) {
         System.out.println("Error while sending new bullet");
      }

      Game.bulletList.add(new Bullet(Game.player.getX(), Game.player.getY(), x, y));
   }

   public static void addOpponentBullet() {
      synchronized(Game.opponentBulletList) {
         Game.opponentBulletList.add(new Bullet(Game.opponentCoordinates.x, Game.opponentCoordinates.y, Game.opponentMouseCoordinates.x, Game.opponentMouseCoordinates.y));
      }
   }

   public static void addEnemy(int x, int y, int imageIndex, int playerToFollow) {
      Game.enemyList.add(new Enemy(x, y, imageIndex, playerToFollow));
   }

   public static void getOpponentCoordinates() {
      String[] s = Game.connection.getOpponentPosition().split(":");
      if (s.length == 6) {
         Game.opponent.setHealth(Integer.parseInt(s[4]));
         Game.opponentCoordinates.x = Integer.parseInt(s[0]);
         Game.opponentCoordinates.y = Integer.parseInt(s[1]);
         Game.opponentMouseCoordinates.x = Integer.parseInt(s[2]);
         Game.opponentMouseCoordinates.y = Integer.parseInt(s[3]);
         if (Game.Score.getScore() < Integer.parseInt(s[5])) {
            Game.Score.setScore(Integer.parseInt(s[5]));
         }
      }

   }

   public static void checkIfPlayersDied() {
      if (!Game.player.isAlive() || !Game.opponent.isAlive()) {
         resetGame();
      }

   }

   public static void resetGame() {
      Game.startDelay = 500;
      Game.winnerString = "SCORE: " + Game.Score.getScore();
      if (Game.Score.checkNewMultiplayerHighScore()) {
         Game.Score.saveScores();
      }

      Game.player.reset();
      Game.opponent.reset();
      Game.bulletList.clear();
      Game.opponentBulletList.clear();
      Game.enemyList.clear();
      Game.Score.resetScore();
      Game.Score.setScore(0);

      try {
         for(int i = 0; i < 24; ++i) {
            Game.connection.send("3");
         }
      } catch (IOException var1) {
         System.out.println("Error while sending end game");
      }

      Game.connection.stop();
      if (Game.isServer) {
         Game.server.close();
      }

      Game.sender.interrupt();
      Game.receiver.interrupt();
      Game.canCreateConnection = true;
      justStarted = true;
      Game.state = 4;
   }

   public static void quitGame() {
      try {
         Game.startDelay = 500;
         Game.player.reset();
         Game.opponent.reset();
         Game.bulletList.clear();
         Game.opponentBulletList.clear();
         Game.enemyList.clear();
         Game.Score.resetScore();
         Game.Score.setScore(0);
         Game.canCreateConnection = true;

         try {
            for(int i = 0; i < 24; ++i) {
               Game.connection.send("2");
            }
         } catch (IOException var1) {
            System.out.println("ERROR WHILE SENDING END GAME");
         }

         Game.connection.stop();
         if (Game.isServer) {
            Game.server.close();
         }

         Game.sender.interrupt();
         Game.receiver.interrupt();
      } catch (NullPointerException var2) {
         Game.state = 0;
      }

      MultiplayerMenuState.isServer = false;
      Game.isServer = false;
      justStarted = true;
      Game.state = 0;
   }

   static {
      canSpawnEnemy = Game.ENEMY_DELAY;
      justStarted = true;
   }
}
