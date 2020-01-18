/*     */ package org.apache.log4j.lf5;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.Serializable;
/*     */ import java.io.StringWriter;
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
/*     */ public abstract class LogRecord
/*     */   implements Serializable
/*     */ {
/*  41 */   protected static long _seqCount = 0L;
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
/*  65 */   protected long _millis = System.currentTimeMillis();
/*  66 */   protected String _category = "Debug";
/*  67 */   protected String _message = "";
/*  68 */   protected LogLevel _level = LogLevel.INFO;
/*  69 */   protected long _sequenceNumber = getNextId();
/*  70 */   protected String _thread = Thread.currentThread().toString();
/*  71 */   protected String _ndc = "";
/*  72 */   protected String _location = "";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String _thrownStackTrace;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Throwable _thrown;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   public LogLevel getLevel() { return this._level; }
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
/*  98 */   public void setLevel(LogLevel level) { this._level = level; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract boolean isSevereLevel();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasThrown() {
/* 111 */     Throwable thrown = getThrown();
/* 112 */     if (thrown == null) {
/* 113 */       return false;
/*     */     }
/* 115 */     String thrownString = thrown.toString();
/* 116 */     return (thrownString != null && thrownString.trim().length() != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 123 */   public boolean isFatal() { return (isSevereLevel() || hasThrown()); }
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
/* 134 */   public String getCategory() { return this._category; }
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
/* 156 */   public void setCategory(String category) { this._category = category; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 166 */   public String getMessage() { return this._message; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 176 */   public void setMessage(String message) { this._message = message; }
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
/* 188 */   public long getSequenceNumber() { return this._sequenceNumber; }
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
/* 200 */   public void setSequenceNumber(long number) { this._sequenceNumber = number; }
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
/* 212 */   public long getMillis() { return this._millis; }
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
/* 223 */   public void setMillis(long millis) { this._millis = millis; }
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
/* 236 */   public String getThreadDescription() { return this._thread; }
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
/* 249 */   public void setThreadDescription(String threadDescription) { this._thread = threadDescription; }
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
/* 270 */   public String getThrownStackTrace() { return this._thrownStackTrace; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 280 */   public void setThrownStackTrace(String trace) { this._thrownStackTrace = trace; }
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
/* 291 */   public Throwable getThrown() { return this._thrown; }
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
/*     */   public void setThrown(Throwable thrown) {
/* 304 */     if (thrown == null) {
/*     */       return;
/*     */     }
/* 307 */     this._thrown = thrown;
/* 308 */     StringWriter sw = new StringWriter();
/* 309 */     PrintWriter out = new PrintWriter(sw);
/* 310 */     thrown.printStackTrace(out);
/* 311 */     out.flush();
/* 312 */     this._thrownStackTrace = sw.toString();
/*     */     try {
/* 314 */       out.close();
/* 315 */       sw.close();
/* 316 */     } catch (IOException e) {}
/*     */ 
/*     */     
/* 319 */     out = null;
/* 320 */     sw = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 327 */     StringBuffer buf = new StringBuffer();
/* 328 */     buf.append("LogRecord: [" + this._level + ", " + this._message + "]");
/* 329 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 338 */   public String getNDC() { return this._ndc; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 347 */   public void setNDC(String ndc) { this._ndc = ndc; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 356 */   public String getLocation() { return this._location; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 365 */   public void setLocation(String location) { this._location = location; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 373 */   public static synchronized void resetSequenceNumber() { _seqCount = 0L; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static synchronized long getNextId() {
/* 381 */     _seqCount++;
/* 382 */     return _seqCount;
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\log4j\lf5\LogRecord.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.2
 */