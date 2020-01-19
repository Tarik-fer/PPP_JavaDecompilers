// 
// Decompiled by Procyon v0.5.36
// 

package Game;

import java.util.Random;
import java.util.Iterator;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.Input;
import org.newdawn.slick.GameContainer;

public class GamePlayState
{
    public static void update(final GameContainer gc, final Input input, final int delta, final int mouseX, final int mouseY) throws SlickException {
        Game.startDelay -= delta;
        if (Game.startDelay < 0) {
            Game.canFire -= delta;
            Game.canSpawnEnemy -= delta;
            Game.canSpawnAmmo -= delta;
            Game.increaseDifficulty -= delta;
            Game.enemyList.stream().forEach(enemy -> enemy.update(Game.player.getCoordinates(), delta));
            Game.bulletList.stream().forEach(bullet -> bullet.update(delta));
            Game.player.update(input, delta);
            if (input.isKeyPressed(1)) {
                Game.startDelay = 300;
                Game.state = 6;
            }
            if (Game.player.isAlive() && input.isMouseButtonDown(0) && Game.canFire <= 0) {
                addBullet(mouseX, mouseY);
            }
            if (!Game.player.isAlive()) {
                new Sound("Sounds/pacmanDeath.wav").play();
                gameOver(input);
            }
            if (Game.canSpawnEnemy <= 0) {
                addEnemy();
                Game.canSpawnEnemy = Game.ENEMY_DELAY;
            }
            if (Game.canSpawnAmmo <= 0) {
                if (Game.ammosCount == 10) {
                    Game.ammosCount = 0;
                }
                Game.ammos[Game.ammosCount] = new Ammo(gc);
                ++Game.ammosCount;
                Game.canSpawnAmmo = 10000;
            }
            if (Game.increaseDifficulty <= 0) {
                if (Game.ENEMY_DELAY > 349) {
                    Game.ENEMY_DELAY -= 30;
                }
                Game.increaseDifficulty = 15000;
            }
        }
    }
    
    public static void render(final GameContainer gc, final Graphics g) throws SlickException {
        final Iterator<Bullet> iter = Game.bulletList.iterator();
        while (iter.hasNext()) {
            final Bullet bullet = iter.next();
            bullet.render();
            if (bullet.isOutOfBounds(gc)) {
                iter.remove();
            }
        }
        final Iterator<Enemy> enemyIter = Game.enemyList.iterator();
        while (enemyIter.hasNext()) {
            final Enemy enemy = enemyIter.next();
            enemy.render();
            if (enemy.isCollidingWithBullets(Game.bulletList)) {
                enemyIter.remove();
            }
        }
        for (final Ammo ammo : Game.ammos) {
            ammo.render();
        }
        Game.player.detectCollisionWithEnemies(Game.enemyList);
        Game.player.checkIfPickedUpAmmos(Game.ammos);
        Game.player.render(g);
        Game.smallFont.drawString("Score: " + Game.Score.getScore(), 12, 12, Color.white);
        Game.smallFont.drawString("Ammos: " + Game.player.getAmmos(), 12, 36, Color.yellow);
        Game.smallFont.drawCenteredString("PAUSE (ESC)", Window.HALF_WIDTH, 24, Color.red);
    }
    
    public static void addBullet(final int x, final int y) {
        if (Game.player.getAmmos() > 0) {
            Game.bulletList.add(new Bullet(Game.player.getX(), Game.player.getY(), x, y));
            Game.player.removeAmmo();
            Game.shootSound.play(1.0f, 0.3f);
            Game.canFire = 200;
        }
    }
    
    public static void addEnemy() {
        switch (new Random().nextInt(4)) {
            case 0: {
                Game.enemyList.add(new Enemy(Game.enemyPositionX.nextInt(Window.WIDTH), -90));
                break;
            }
            case 1: {
                Game.enemyList.add(new Enemy(Game.enemyPositionX.nextInt(Window.WIDTH), Window.HEIGHT + 90));
                break;
            }
            case 2: {
                Game.enemyList.add(new Enemy(-90, Game.enemyPositionY.nextInt(Window.HEIGHT)));
                break;
            }
            case 3: {
                Game.enemyList.add(new Enemy(Window.WIDTH + 90, Game.enemyPositionY.nextInt(Window.HEIGHT)));
                break;
            }
        }
    }
    
    public static void gameOver(final Input input) {
        Window.clear(input);
        if (Game.Score.checkNewHighScore()) {
            Game.Score.saveScores();
        }
        Game.startDelay = 500;
        Game.canSpawnAmmo = 10000;
        Game.paused = false;
        Game.player.reset();
        Game.enemyList.clear();
        Game.bulletList.clear();
        for (final Ammo ammo : Game.ammos) {
            ammo.pick();
        }
        Game.Score.resetScore();
        Game.state = 7;
    }
}
