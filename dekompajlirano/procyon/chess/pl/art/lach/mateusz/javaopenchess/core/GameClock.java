// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.core;

import pl.art.lach.mateusz.javaopenchess.core.players.Player;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import pl.art.lach.mateusz.javaopenchess.utils.Settings;
import org.apache.log4j.Logger;
import javax.swing.JPanel;

public class GameClock extends JPanel implements Runnable
{
    private static final Logger LOG;
    private Clock clock1;
    private Clock clock2;
    private Clock runningClock;
    private Settings settings;
    private Thread thread;
    private Game game;
    private Graphics g;
    private String white_clock;
    private String black_clock;
    private BufferedImage background;
    private Graphics bufferedGraphics;
    
    GameClock(final Game game) {
        this.clock1 = new Clock();
        this.clock2 = new Clock();
        this.runningClock = this.clock1;
        this.game = game;
        this.settings = game.getSettings();
        this.background = new BufferedImage(600, 600, 2);
        final int time = this.settings.getTimeForGame();
        this.setTimes(time, time);
        this.setPlayers(this.settings.getPlayerBlack(), this.settings.getPlayerWhite());
        this.thread = new Thread(this);
        if (this.settings.isTimeLimitSet()) {
            this.thread.start();
        }
        this.drawBackground();
        this.setDoubleBuffered(true);
    }
    
    public void start() {
        this.thread.start();
    }
    
    public void stop() {
        this.runningClock = null;
        try {
            this.thread.wait();
        }
        catch (InterruptedException | IllegalMonitorStateException ex2) {
            final Exception ex;
            final Exception exc = ex;
            GameClock.LOG.error("Error blocking thread: ", exc);
        }
    }
    
    void drawBackground() {
        final Graphics gr = this.background.getGraphics();
        final Graphics2D g2d = (Graphics2D)gr;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("Serif", 2, 20);
        g2d.setColor(Color.WHITE);
        g2d.fillRect(5, 30, 80, 30);
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);
        g2d.fillRect(85, 30, 90, 30);
        g2d.drawRect(5, 30, 170, 30);
        g2d.drawRect(5, 60, 170, 30);
        g2d.drawLine(85, 30, 85, 90);
        font = new Font("Serif", 2, 16);
        g2d.drawString(this.settings.getPlayerWhite().getName(), 10, 50);
        g2d.setColor(Color.WHITE);
        g2d.drawString(this.settings.getPlayerBlack().getName(), 100, 50);
        this.bufferedGraphics = this.background.getGraphics();
    }
    
    @Override
    public void paint(final Graphics g) {
        super.paint(g);
        this.white_clock = this.clock1.prepareString();
        this.black_clock = this.clock2.prepareString();
        final Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(this.background, 0, 0, this);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("Serif", 2, 20);
        g2d.drawImage(this.background, 0, 0, this);
        g2d.setColor(Color.WHITE);
        g2d.fillRect(5, 30, 80, 30);
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);
        g2d.fillRect(85, 30, 90, 30);
        g2d.drawRect(5, 30, 170, 30);
        g2d.drawRect(5, 60, 170, 30);
        g2d.drawLine(85, 30, 85, 90);
        font = new Font("Serif", 2, 14);
        g2d.drawImage(this.background, 0, 0, this);
        g2d.setFont(font);
        g.drawString(this.settings.getPlayerWhite().getName(), 10, 50);
        g.setColor(Color.WHITE);
        g.drawString(this.settings.getPlayerBlack().getName(), 100, 50);
        g2d.setFont(font);
        g.setColor(Color.BLACK);
        g2d.drawString(this.white_clock, 10, 80);
        g2d.drawString(this.black_clock, 90, 80);
    }
    
    @Override
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void switchClocks() {
        if (this.runningClock == this.clock1) {
            this.runningClock = this.clock2;
        }
        else {
            this.runningClock = this.clock1;
        }
    }
    
    public void setTimes(final int t1, final int t2) {
        this.clock1.init(t1);
        this.clock2.init(t2);
    }
    
    private void setPlayers(final Player p1, final Player p2) {
        if (p1.getColor() == Colors.WHITE) {
            this.clock1.setPlayer(p1);
            this.clock2.setPlayer(p2);
        }
        else {
            this.clock1.setPlayer(p2);
            this.clock2.setPlayer(p1);
        }
    }
    
    @Override
    public void run() {
        while (true) {
            if (this.runningClock != null) {
                if (this.runningClock.decrement()) {
                    this.repaint();
                    try {
                        final Thread thread = this.thread;
                        Thread.sleep(1000L);
                    }
                    catch (InterruptedException e) {
                        GameClock.LOG.error("Some error in gameClock thread: " + e);
                    }
                }
                if (this.runningClock == null || this.runningClock.getLeftTime() != 0) {
                    continue;
                }
                this.timeOver();
            }
        }
    }
    
    private void timeOver() {
        String color = new String();
        if (this.clock1.getLeftTime() == 0) {
            color = this.clock2.getPlayer().getColor().toString();
        }
        else if (this.clock2.getLeftTime() == 0) {
            color = this.clock1.getPlayer().getColor().toString();
        }
        else {
            GameClock.LOG.debug("Time over called when player got time 2 play");
        }
        this.game.endGame("Time is over! " + color + " player win the game.");
        this.stop();
    }
    
    static {
        LOG = Logger.getLogger(GameClock.class);
    }
}
