/*     */ package pl.art.lach.mateusz.javaopenchess.core;
/*     */ 
/*     */ import pl.art.lach.mateusz.javaopenchess.core.players.Player;
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
/*     */ public class Clock
/*     */ {
/*     */   private int timeLeft;
/*     */   private Player player;
/*     */   
/*  37 */   Clock() { init(this.timeLeft); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   Clock(int time) { init(time); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   public final void init(int time) { this.timeLeft = time; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean decrement() {
/*  62 */     if (this.timeLeft > 0) {
/*     */       
/*  64 */       this.timeLeft--;
/*  65 */       return true;
/*     */     } 
/*  67 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void pause() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  79 */   public int getLeftTime() { return this.timeLeft; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   public void setPlayer(Player player) { this.player = player; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  95 */   public Player getPlayer() { return this.player; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String prepareString() {
/* 103 */     String strMin = "";
/* 104 */     Integer time_min = new Integer(getLeftTime() / 60);
/* 105 */     Integer time_sec = new Integer(getLeftTime() % 60);
/* 106 */     if (time_min.intValue() < 10) {
/*     */       
/* 108 */       strMin = "0" + time_min.toString();
/*     */     }
/*     */     else {
/*     */       
/* 112 */       strMin = time_min.toString();
/*     */     } 
/* 114 */     String result = strMin + ":";
/* 115 */     if (time_sec.intValue() < 10) {
/*     */       
/* 117 */       result = result + "0" + time_sec.toString();
/*     */     }
/*     */     else {
/*     */       
/* 121 */       result = result + time_sec.toString();
/*     */     } 
/*     */     
/* 124 */     return result;
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\core\Clock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */