/*     */ package org.apache.log4j.lf5.util;
/*     */ 
/*     */ import java.awt.Toolkit;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import org.apache.log4j.lf5.LogLevel;
/*     */ import org.apache.log4j.lf5.LogRecord;
/*     */ import org.apache.log4j.lf5.viewer.LogBrokerMonitor;
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
/*     */ public class LogMonitorAdapter
/*     */ {
/*     */   public static final int LOG4J_LOG_LEVELS = 0;
/*     */   public static final int JDK14_LOG_LEVELS = 1;
/*     */   private LogBrokerMonitor _logMonitor;
/*  49 */   private LogLevel _defaultLevel = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private LogMonitorAdapter(List userDefinedLevels) {
/*  57 */     this._defaultLevel = userDefinedLevels.get(0);
/*  58 */     this._logMonitor = new LogBrokerMonitor(userDefinedLevels);
/*     */     
/*  60 */     this._logMonitor.setFrameSize(getDefaultMonitorWidth(), getDefaultMonitorHeight());
/*     */     
/*  62 */     this._logMonitor.setFontSize(12);
/*  63 */     this._logMonitor.show();
/*     */   }
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
/*     */   public static LogMonitorAdapter newInstance(int loglevels) {
/*     */     LogMonitorAdapter adapter;
/*  78 */     if (loglevels == 1) {
/*  79 */       adapter = newInstance(LogLevel.getJdk14Levels());
/*  80 */       adapter.setDefaultLevel(LogLevel.FINEST);
/*  81 */       adapter.setSevereLevel(LogLevel.SEVERE);
/*     */     } else {
/*  83 */       adapter = newInstance(LogLevel.getLog4JLevels());
/*  84 */       adapter.setDefaultLevel(LogLevel.DEBUG);
/*  85 */       adapter.setSevereLevel(LogLevel.FATAL);
/*     */     } 
/*  87 */     return adapter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static LogMonitorAdapter newInstance(LogLevel[] userDefined) {
/*  99 */     if (userDefined == null) {
/* 100 */       return null;
/*     */     }
/* 102 */     return newInstance(Arrays.asList(userDefined));
/*     */   }
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
/* 114 */   public static LogMonitorAdapter newInstance(List userDefinedLevels) { return new LogMonitorAdapter(userDefinedLevels); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 123 */   public void addMessage(LogRecord record) { this._logMonitor.addMessage(record); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 132 */   public void setMaxNumberOfRecords(int maxNumberOfRecords) { this._logMonitor.setMaxNumberOfLogRecords(maxNumberOfRecords); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 142 */   public void setDefaultLevel(LogLevel level) { this._defaultLevel = level; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 151 */   public LogLevel getDefaultLevel() { return this._defaultLevel; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 160 */   public void setSevereLevel(LogLevel level) { AdapterLogRecord.setSevereLevel(level); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 169 */   public LogLevel getSevereLevel() { return AdapterLogRecord.getSevereLevel(); }
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
/*     */   public void log(String category, LogLevel level, String message, Throwable t, String NDC) {
/* 184 */     AdapterLogRecord record = new AdapterLogRecord();
/* 185 */     record.setCategory(category);
/* 186 */     record.setMessage(message);
/* 187 */     record.setNDC(NDC);
/* 188 */     record.setThrown(t);
/*     */     
/* 190 */     if (level == null) {
/* 191 */       record.setLevel(getDefaultLevel());
/*     */     } else {
/* 193 */       record.setLevel(level);
/*     */     } 
/*     */     
/* 196 */     addMessage(record);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 206 */   public void log(String category, String message) { log(category, null, message); }
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
/* 218 */   public void log(String category, LogLevel level, String message, String NDC) { log(category, level, message, null, NDC); }
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
/* 231 */   public void log(String category, LogLevel level, String message, Throwable t) { log(category, level, message, t, null); }
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
/* 242 */   public void log(String category, LogLevel level, String message) { log(category, level, message, null, null); }
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
/*     */   protected static int getScreenWidth() {
/*     */     try {
/* 255 */       return (Toolkit.getDefaultToolkit().getScreenSize()).width;
/* 256 */     } catch (Throwable t) {
/* 257 */       return 800;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static int getScreenHeight() {
/*     */     try {
/* 268 */       return (Toolkit.getDefaultToolkit().getScreenSize()).height;
/* 269 */     } catch (Throwable t) {
/* 270 */       return 600;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 275 */   protected static int getDefaultMonitorWidth() { return 3 * getScreenWidth() / 4; }
/*     */ 
/*     */ 
/*     */   
/* 279 */   protected static int getDefaultMonitorHeight() { return 3 * getScreenHeight() / 4; }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\log4j\lf\\util\LogMonitorAdapter.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.2
 */