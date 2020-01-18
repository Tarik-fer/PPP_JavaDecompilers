/*     */ package pl.art.lach.mateusz.javaopenchess.core;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.BufferedImage;
/*     */ import javax.swing.JPanel;
/*     */ import org.apache.log4j.Logger;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.players.Player;
/*     */ import pl.art.lach.mateusz.javaopenchess.utils.Settings;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GameClock
/*     */   extends JPanel
/*     */   implements Runnable
/*     */ {
/*  31 */   private static final Logger LOG = Logger.getLogger(GameClock.class);
/*     */ 
/*     */   
/*     */   private Clock clock1;
/*     */   
/*     */   private Clock clock2;
/*     */   
/*     */   private Clock runningClock;
/*     */   
/*     */   private Settings settings;
/*     */   
/*     */   private Thread thread;
/*     */   
/*     */   private Game game;
/*     */   
/*     */   private Graphics g;
/*     */   
/*     */   private String white_clock;
/*     */   
/*     */   private String black_clock;
/*     */   
/*     */   private BufferedImage background;
/*     */   
/*     */   private Graphics bufferedGraphics;
/*     */ 
/*     */   
/*     */   GameClock(Game game) {
/*  58 */     this.clock1 = new Clock();
/*  59 */     this.clock2 = new Clock();
/*  60 */     this.runningClock = this.clock1;
/*  61 */     this.game = game;
/*  62 */     this.settings = game.getSettings();
/*  63 */     this.background = new BufferedImage(600, 600, 2);
/*     */     
/*  65 */     int time = this.settings.getTimeForGame();
/*     */     
/*  67 */     setTimes(time, time);
/*  68 */     setPlayers(this.settings.getPlayerBlack(), this.settings.getPlayerWhite());
/*     */     
/*  70 */     this.thread = new Thread(this);
/*  71 */     if (this.settings.isTimeLimitSet())
/*     */     {
/*  73 */       this.thread.start();
/*     */     }
/*  75 */     drawBackground();
/*  76 */     setDoubleBuffered(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   public void start() { this.thread.start(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stop() {
/*  90 */     this.runningClock = null;
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  95 */       this.thread.wait();
/*     */     }
/*  97 */     catch (InterruptedException|IllegalMonitorStateException exc) {
/*     */       
/*  99 */       LOG.error("Error blocking thread: ", exc);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void drawBackground() {
/* 107 */     Graphics gr = this.background.getGraphics();
/* 108 */     Graphics2D g2d = (Graphics2D)gr;
/* 109 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 110 */     Font font = new Font("Serif", 2, 20);
/*     */     
/* 112 */     g2d.setColor(Color.WHITE);
/* 113 */     g2d.fillRect(5, 30, 80, 30);
/* 114 */     g2d.setFont(font);
/*     */     
/* 116 */     g2d.setColor(Color.BLACK);
/* 117 */     g2d.fillRect(85, 30, 90, 30);
/* 118 */     g2d.drawRect(5, 30, 170, 30);
/* 119 */     g2d.drawRect(5, 60, 170, 30);
/* 120 */     g2d.drawLine(85, 30, 85, 90);
/* 121 */     font = new Font("Serif", 2, 16);
/* 122 */     g2d.drawString(this.settings.getPlayerWhite().getName(), 10, 50);
/* 123 */     g2d.setColor(Color.WHITE);
/* 124 */     g2d.drawString(this.settings.getPlayerBlack().getName(), 100, 50);
/* 125 */     this.bufferedGraphics = this.background.getGraphics();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void paint(Graphics g) {
/* 135 */     super.paint(g);
/* 136 */     this.white_clock = this.clock1.prepareString();
/* 137 */     this.black_clock = this.clock2.prepareString();
/* 138 */     Graphics2D g2d = (Graphics2D)g;
/* 139 */     g2d.drawImage(this.background, 0, 0, this);
/* 140 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */ 
/*     */ 
/*     */     
/* 144 */     Font font = new Font("Serif", 2, 20);
/* 145 */     g2d.drawImage(this.background, 0, 0, this);
/* 146 */     g2d.setColor(Color.WHITE);
/* 147 */     g2d.fillRect(5, 30, 80, 30);
/* 148 */     g2d.setFont(font);
/*     */     
/* 150 */     g2d.setColor(Color.BLACK);
/* 151 */     g2d.fillRect(85, 30, 90, 30);
/* 152 */     g2d.drawRect(5, 30, 170, 30);
/* 153 */     g2d.drawRect(5, 60, 170, 30);
/* 154 */     g2d.drawLine(85, 30, 85, 90);
/* 155 */     font = new Font("Serif", 2, 14);
/* 156 */     g2d.drawImage(this.background, 0, 0, this);
/* 157 */     g2d.setFont(font);
/* 158 */     g.drawString(this.settings.getPlayerWhite().getName(), 10, 50);
/* 159 */     g.setColor(Color.WHITE);
/* 160 */     g.drawString(this.settings.getPlayerBlack().getName(), 100, 50);
/* 161 */     g2d.setFont(font);
/* 162 */     g.setColor(Color.BLACK);
/* 163 */     g2d.drawString(this.white_clock, 10, 80);
/* 164 */     g2d.drawString(this.black_clock, 90, 80);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 174 */   public void update(Graphics g) { paint(g); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void switchClocks() {
/* 183 */     if (this.runningClock == this.clock1) {
/*     */       
/* 185 */       this.runningClock = this.clock2;
/*     */     }
/*     */     else {
/*     */       
/* 189 */       this.runningClock = this.clock1;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTimes(int t1, int t2) {
/* 201 */     this.clock1.init(t1);
/* 202 */     this.clock2.init(t2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setPlayers(Player p1, Player p2) {
/* 211 */     if (p1.getColor() == Colors.WHITE) {
/*     */       
/* 213 */       this.clock1.setPlayer(p1);
/* 214 */       this.clock2.setPlayer(p2);
/*     */     }
/*     */     else {
/*     */       
/* 218 */       this.clock1.setPlayer(p2);
/* 219 */       this.clock2.setPlayer(p1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/*     */     while (true) {
/* 231 */       if (this.runningClock != null) {
/*     */         
/* 233 */         if (this.runningClock.decrement()) {
/*     */           
/* 235 */           repaint();
/*     */           
/*     */           try {
/* 238 */             Thread.sleep(1000L);
/*     */           }
/* 240 */           catch (InterruptedException e) {
/*     */             
/* 242 */             LOG.error("Some error in gameClock thread: " + e);
/*     */           } 
/*     */         } 
/* 245 */         if (this.runningClock != null && this.runningClock.getLeftTime() == 0)
/*     */         {
/* 247 */           timeOver();
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void timeOver() {
/* 257 */     String color = new String();
/* 258 */     if (this.clock1.getLeftTime() == 0) {
/*     */ 
/*     */       
/* 261 */       color = this.clock2.getPlayer().getColor().toString();
/*     */     }
/* 263 */     else if (this.clock2.getLeftTime() == 0) {
/*     */       
/* 265 */       color = this.clock1.getPlayer().getColor().toString();
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 270 */       LOG.debug("Time over called when player got time 2 play");
/*     */     } 
/* 272 */     this.game.endGame("Time is over! " + color + " player win the game.");
/* 273 */     stop();
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\core\GameClock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */