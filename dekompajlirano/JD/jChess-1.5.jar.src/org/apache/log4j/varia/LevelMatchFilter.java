/*     */ package org.apache.log4j.varia;
/*     */ 
/*     */ import org.apache.log4j.Level;
/*     */ import org.apache.log4j.helpers.OptionConverter;
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
/*     */ public class LevelMatchFilter
/*     */   extends Filter
/*     */ {
/*     */   boolean acceptOnMatch = true;
/*     */   Level levelToMatch;
/*     */   
/*  54 */   public void setLevelToMatch(String level) { this.levelToMatch = OptionConverter.toLevel(level, null); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   public String getLevelToMatch() { return (this.levelToMatch == null) ? null : this.levelToMatch.toString(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   public void setAcceptOnMatch(boolean acceptOnMatch) { this.acceptOnMatch = acceptOnMatch; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  69 */   public boolean getAcceptOnMatch() { return this.acceptOnMatch; }
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
/*     */   public int decide(LoggingEvent event) {
/*  86 */     if (this.levelToMatch == null) {
/*  87 */       return 0;
/*     */     }
/*     */     
/*  90 */     boolean matchOccured = false;
/*  91 */     if (this.levelToMatch.equals(event.getLevel())) {
/*  92 */       matchOccured = true;
/*     */     }
/*     */     
/*  95 */     if (matchOccured) {
/*  96 */       if (this.acceptOnMatch) {
/*  97 */         return 1;
/*     */       }
/*  99 */       return -1;
/*     */     } 
/* 101 */     return 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\log4j\varia\LevelMatchFilter.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.2
 */