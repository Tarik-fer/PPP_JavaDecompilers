/*     */ package org.apache.log4j.varia;
/*     */ 
/*     */ import org.apache.log4j.Level;
/*     */ import org.apache.log4j.Priority;
/*     */ import org.apache.log4j.spi.Filter;
/*     */ import org.apache.log4j.spi.LoggingEvent;
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
/*     */ public class LevelRangeFilter
/*     */   extends Filter
/*     */ {
/*     */   boolean acceptOnMatch = false;
/*     */   Level levelMin;
/*     */   Level levelMax;
/*     */   
/*     */   public int decide(LoggingEvent event) {
/*  69 */     if (this.levelMin != null && 
/*  70 */       !event.getLevel().isGreaterOrEqual((Priority)this.levelMin))
/*     */     {
/*  72 */       return -1;
/*     */     }
/*     */ 
/*     */     
/*  76 */     if (this.levelMax != null && 
/*  77 */       event.getLevel().toInt() > this.levelMax.toInt())
/*     */     {
/*     */ 
/*     */ 
/*     */       
/*  82 */       return -1;
/*     */     }
/*     */ 
/*     */     
/*  86 */     if (this.acceptOnMatch)
/*     */     {
/*     */       
/*  89 */       return 1;
/*     */     }
/*     */ 
/*     */     
/*  93 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 101 */   public Level getLevelMax() { return this.levelMax; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 109 */   public Level getLevelMin() { return this.levelMin; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 117 */   public boolean getAcceptOnMatch() { return this.acceptOnMatch; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 125 */   public void setLevelMax(Level levelMax) { this.levelMax = levelMax; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 133 */   public void setLevelMin(Level levelMin) { this.levelMin = levelMin; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 141 */   public void setAcceptOnMatch(boolean acceptOnMatch) { this.acceptOnMatch = acceptOnMatch; }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\log4j\varia\LevelRangeFilter.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.2
 */