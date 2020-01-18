/*     */ package org.apache.log4j.spi;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import org.apache.log4j.Appender;
/*     */ import org.apache.log4j.Category;
/*     */ import org.apache.log4j.Level;
/*     */ import org.apache.log4j.Logger;
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
/*     */ public final class NOPLoggerRepository
/*     */   implements LoggerRepository
/*     */ {
/*     */   public void addHierarchyEventListener(HierarchyEventListener listener) {}
/*     */   
/*  43 */   public boolean isDisabled(int level) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setThreshold(Level level) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setThreshold(String val) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void emitNoAppenderWarning(Category cat) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   public Level getThreshold() { return Level.OFF; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   public Logger getLogger(String name) { return new NOPLogger(this, name); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   public Logger getLogger(String name, LoggerFactory factory) { return new NOPLogger(this, name); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  89 */   public Logger getRootLogger() { return new NOPLogger(this, "root"); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  96 */   public Logger exists(String name) { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void shutdown() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 109 */   public Enumeration getCurrentLoggers() { return (new Vector()).elements(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 116 */   public Enumeration getCurrentCategories() { return getCurrentLoggers(); }
/*     */   
/*     */   public void fireAddAppenderEvent(Category logger, Appender appender) {}
/*     */   
/*     */   public void resetConfiguration() {}
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\log4j\spi\NOPLoggerRepository.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.2
 */