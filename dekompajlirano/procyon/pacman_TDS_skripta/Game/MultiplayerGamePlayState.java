// 
// Decompiled by Procyon v0.5.36
// 

package Game;

import java.util.Iterator;
import org.newdawn.slick.Color;
import java.util.ConcurrentModificationException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import java.io.IOException;
import org.newdawn.slick.Input;
import org.newdawn.slick.GameContainer;

public class MultiplayerGamePlayState
{
    public static int canSpawnEnemy;
    static boolean justStarted;
    
    public static void update(final GameContainer gc, final Input input, final int delta, final int mouseX, final int mouseY) throws SlickException {
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
            synchronized (Game.enemyList) {
                Game.enemyList.stream().forEach(enemy -> {
                    if (enemy.getPlayerToFollow() == 0) {
                        if (Game.isServer) {
                            enemy.update(Game.player.getCoordinates(), delta);
                        }
                        else {
                            enemy.update(Game.opponent.getCoordinates(), delta);
                        }
                    }
                    else if (Game.isServer) {
                        enemy.update(Game.opponent.getCoordinates(), delta);
                    }
                    else {
                        enemy.update(Game.player.getCoordinates(), delta);
                    }
                    return;
                });
            }
            Game.bulletList.stream().forEach(bullet -> bullet.update(delta));
            synchronized (Game.opponentBulletList) {
                Game.opponentBulletList.stream().forEach(bullet -> bullet.update(delta));
            }
            Game.player.update(input, delta);
            Game.opponent.update(Game.opponentCoordinates, Game.opponentMouseCoordinates.x, Game.opponentMouseCoordinates.y);
            if (Game.player.isAlive() && input.isMouseButtonDown(0) && Game.canFire <= 0) {
                addBullet(mouseX, mouseY);
                Game.canFire = 200;
            }
            if (MultiplayerGamePlayState.justStarted) {
                Game.Score.resetScore();
                MultiplayerGamePlayState.justStarted = false;
            }
            Game.infoString = "" + Game.player.getX() + ":" + Game.player.getY() + ":" + mouseX + ":" + mouseY + ":" + Game.player.getHealth() + ":" + Game.Score.getScore();
            try {
                Game.connection.send(Game.infoString);
            }
            catch (IOException e) {
                System.out.println("Error while sending coordinates to opponent " + e);
            }
            getOpponentCoordinates();
        }
    }
    
    public static void render(final GameContainer gc, final Graphics g) throws SlickException {
        Game.player.render(g);
        Game.player.detectCollisionWithEnemies(Game.enemyList);
        Game.opponent.render(g);
        Game.opponent.detectCollisionWithEnemies(Game.enemyList);
        final Iterator<Bullet> iter = Game.bulletList.iterator();
        while (iter.hasNext()) {
            final Bullet bullet = iter.next();
            bullet.render();
            if (bullet.isOutOfBounds(gc)) {
                iter.remove();
            }
        }
        synchronized (Game.opponentBulletList) {
            final Iterator<Bullet> oppIter = Game.opponentBulletList.iterator();
            try {
                synchronized (oppIter) {
                    while (oppIter.hasNext()) {
                        final Bullet bullet2 = oppIter.next();
                        synchronized (bullet2) {
                            bullet2.render();
                            if (!bullet2.isOutOfBounds(gc)) {
                                continue;
                            }
                            oppIter.remove();
                        }
                    }
                }
            }
            catch (ConcurrentModificationException e) {
                System.out.println("Error rendering bullets");
            }
        }
        synchronized (Game.enemyList) {
            final Iterator<Enemy> enemyIter = Game.enemyList.iterator();
            try {
                while (enemyIter.hasNext()) {
                    final Enemy enemy = enemyIter.next();
                    enemy.render();
                    if (enemy.isCollidingWithBullets(Game.bulletList)) {
                        enemyIter.remove();
                    }
                    else {
                        if (!enemy.isCollidingWithBullets(Game.opponentBulletList)) {
                            continue;
                        }
                        enemyIter.remove();
                    }
                }
            }
            catch (ConcurrentModificationException e) {
                System.out.println("Error rendering enemies");
            }
        }
        Game.smallFont.drawString("Score: " + Game.Score.getScore(), 12, 12, Color.white);
    }
    
    public static void addBullet(final int x, final int y) {
        try {
            Game.connection.send("1");
        }
        catch (IOException e) {
            System.out.println("Error while sending new bullet");
        }
        Game.bulletList.add(new Bullet(Game.player.getX(), Game.player.getY(), x, y));
    }
    
    public static void addOpponentBullet() {
        synchronized (Game.opponentBulletList) {
            Game.opponentBulletList.add(new Bullet(Game.opponentCoordinates.x, Game.opponentCoordinates.y, Game.opponentMouseCoordinates.x, Game.opponentMouseCoordinates.y));
        }
    }
    
    public static void addEnemy(final int x, final int y, final int imageIndex, final int playerToFollow) {
        Game.enemyList.add(new Enemy(x, y, imageIndex, playerToFollow));
    }
    
    public static void getOpponentCoordinates() {
        final String[] s = Game.connection.getOpponentPosition().split(":");
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
            for (int i = 0; i < 24; ++i) {
                Game.connection.send("3");
            }
        }
        catch (IOException e) {
            System.out.println("Error while sending end game");
        }
        Game.connection.stop();
        if (Game.isServer) {
            Game.server.close();
        }
        Game.sender.interrupt();
        Game.receiver.interrupt();
        Game.canCreateConnection = true;
        MultiplayerGamePlayState.justStarted = true;
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
                for (int i = 0; i < 24; ++i) {
                    Game.connection.send("2");
                }
            }
            catch (IOException e) {
                System.out.println("ERROR WHILE SENDING END GAME");
            }
            Game.connection.stop();
            if (Game.isServer) {
                Game.server.close();
            }
            Game.sender.interrupt();
            Game.receiver.interrupt();
        }
        catch (NullPointerException e2) {
            Game.state = 0;
        }
        MultiplayerMenuState.isServer = false;
        Game.isServer = false;
        MultiplayerGamePlayState.justStarted = true;
        Game.state = 0;
    }
    
    static {
        MultiplayerGamePlayState.canSpawnEnemy = Game.ENEMY_DELAY;
        MultiplayerGamePlayState.justStarted = true;
    }
}
