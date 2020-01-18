/*     */ package org.apache.log4j.spi;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.ResourceBundle;
/*     */ import java.util.Vector;
/*     */ import org.apache.log4j.Appender;
/*     */ import org.apache.log4j.Category;
/*     */ import org.apache.log4j.Level;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.apache.log4j.Priority;
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
/*     */ public final class NOPLogger
/*     */   extends Logger
/*     */ {
/*     */   public NOPLogger(NOPLoggerRepository repo, String name) {
/*  39 */     super(name);
/*  40 */     this.repository = repo;
/*  41 */     this.level = Level.OFF;
/*  42 */     this.parent = (Category)this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAppender(Appender newAppender) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void assertLog(boolean assertion, String msg) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void callAppenders(LoggingEvent event) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void closeNestedAppenders() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void debug(Object message) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void debug(Object message, Throwable t) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void error(Object message) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void error(Object message, Throwable t) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void fatal(Object message) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void fatal(Object message, Throwable t) {}
/*     */ 
/*     */ 
/*     */   
/*  91 */   public Enumeration getAllAppenders() { return (new Vector()).elements(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  96 */   public Appender getAppender(String name) { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 101 */   public Level getEffectiveLevel() { return Level.OFF; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 106 */   public Priority getChainedPriority() { return (Priority)getEffectiveLevel(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 111 */   public ResourceBundle getResourceBundle() { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void info(Object message) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void info(Object message, Throwable t) {}
/*     */ 
/*     */ 
/*     */   
/* 125 */   public boolean isAttached(Appender appender) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 130 */   public boolean isDebugEnabled() { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 135 */   public boolean isEnabledFor(Priority level) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 140 */   public boolean isInfoEnabled() { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void l7dlog(Priority priority, String key, Throwable t) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void l7dlog(Priority priority, String key, Object[] params, Throwable t) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void log(Priority priority, Object message, Throwable t) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void log(Priority priority, Object message) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void log(String callerFQCN, Priority level, Object message, Throwable t) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeAllAppenders() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeAppender(Appender appender) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeAppender(String name) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLevel(Level level) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPriority(Priority priority) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setResourceBundle(ResourceBundle bundle) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void warn(Object message) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void warn(Object message, Throwable t) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void trace(Object message) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void trace(Object message, Throwable t) {}
/*     */ 
/*     */ 
/*     */   
/* 208 */   public boolean isTraceEnabled() { return false; }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\log4j\spi\NOPLogger.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.2
 */