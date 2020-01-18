/*     */ package org.apache.log4j.lf5;
/*     */ 
/*     */ import java.awt.Toolkit;
/*     */ import org.apache.log4j.AppenderSkeleton;
/*     */ import org.apache.log4j.lf5.viewer.LogBrokerMonitor;
/*     */ import org.apache.log4j.spi.LocationInfo;
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
/*     */ public class LF5Appender
/*     */   extends AppenderSkeleton
/*     */ {
/*     */   protected LogBrokerMonitor _logMonitor;
/*     */   protected static LogBrokerMonitor _defaultLogMonitor;
/*     */   protected static AppenderFinalizer _finalizer;
/*     */   
/*  68 */   public LF5Appender() { this(getDefaultInstance()); }
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
/*     */   public LF5Appender(LogBrokerMonitor monitor) {
/*  82 */     if (monitor != null) {
/*  83 */       this._logMonitor = monitor;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void append(LoggingEvent event) {
/*  99 */     String category = event.getLoggerName();
/* 100 */     String logMessage = event.getRenderedMessage();
/* 101 */     String nestedDiagnosticContext = event.getNDC();
/* 102 */     String threadDescription = event.getThreadName();
/* 103 */     String level = event.getLevel().toString();
/* 104 */     long time = event.timeStamp;
/* 105 */     LocationInfo locationInfo = event.getLocationInformation();
/*     */ 
/*     */     
/* 108 */     Log4JLogRecord record = new Log4JLogRecord();
/*     */     
/* 110 */     record.setCategory(category);
/* 111 */     record.setMessage(logMessage);
/* 112 */     record.setLocation(locationInfo.fullInfo);
/* 113 */     record.setMillis(time);
/* 114 */     record.setThreadDescription(threadDescription);
/*     */     
/* 116 */     if (nestedDiagnosticContext != null) {
/* 117 */       record.setNDC(nestedDiagnosticContext);
/*     */     } else {
/* 119 */       record.setNDC("");
/*     */     } 
/*     */     
/* 122 */     if (event.getThrowableInformation() != null) {
/* 123 */       record.setThrownStackTrace(event.getThrowableInformation());
/*     */     }
/*     */     
/*     */     try {
/* 127 */       record.setLevel(LogLevel.valueOf(level));
/* 128 */     } catch (LogLevelFormatException e) {
/*     */ 
/*     */       
/* 131 */       record.setLevel(LogLevel.WARN);
/*     */     } 
/*     */     
/* 134 */     if (this._logMonitor != null) {
/* 135 */       this._logMonitor.addMessage(record);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 152 */   public boolean requiresLayout() { return false; }
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
/* 169 */   public void setCallSystemExitOnClose(boolean callSystemExitOnClose) { this._logMonitor.setCallSystemExitOnClose(callSystemExitOnClose); }
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
/* 183 */   public boolean equals(LF5Appender compareTo) { return (this._logMonitor == compareTo.getLogBrokerMonitor()); }
/*     */ 
/*     */ 
/*     */   
/* 187 */   public LogBrokerMonitor getLogBrokerMonitor() { return this._logMonitor; }
/*     */ 
/*     */ 
/*     */   
/* 191 */   public static void main(String[] args) { new LF5Appender(); }
/*     */ 
/*     */ 
/*     */   
/* 195 */   public void setMaxNumberOfRecords(int maxNumberOfRecords) { _defaultLogMonitor.setMaxNumberOfLogRecords(maxNumberOfRecords); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static synchronized LogBrokerMonitor getDefaultInstance() {
/* 205 */     if (_defaultLogMonitor == null) {
/*     */       try {
/* 207 */         _defaultLogMonitor = new LogBrokerMonitor(LogLevel.getLog4JLevels());
/*     */         
/* 209 */         _finalizer = new AppenderFinalizer(_defaultLogMonitor);
/*     */         
/* 211 */         _defaultLogMonitor.setFrameSize(getDefaultMonitorWidth(), getDefaultMonitorHeight());
/*     */         
/* 213 */         _defaultLogMonitor.setFontSize(12);
/* 214 */         _defaultLogMonitor.show();
/*     */       }
/* 216 */       catch (SecurityException e) {
/* 217 */         _defaultLogMonitor = null;
/*     */       } 
/*     */     }
/*     */     
/* 221 */     return _defaultLogMonitor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static int getScreenWidth() {
/*     */     try {
/* 231 */       return (Toolkit.getDefaultToolkit().getScreenSize()).width;
/* 232 */     } catch (Throwable t) {
/* 233 */       return 800;
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
/* 244 */       return (Toolkit.getDefaultToolkit().getScreenSize()).height;
/* 245 */     } catch (Throwable t) {
/* 246 */       return 600;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 251 */   protected static int getDefaultMonitorWidth() { return 3 * getScreenWidth() / 4; }
/*     */ 
/*     */ 
/*     */   
/* 255 */   protected static int getDefaultMonitorHeight() { return 3 * getScreenHeight() / 4; }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\log4j\lf5\LF5Appender.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.2
 */