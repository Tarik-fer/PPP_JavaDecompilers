/*     */ package org.apache.log4j.jdbc;
/*     */ 
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import org.apache.log4j.Appender;
/*     */ import org.apache.log4j.AppenderSkeleton;
/*     */ import org.apache.log4j.Layout;
/*     */ import org.apache.log4j.PatternLayout;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JDBCAppender
/*     */   extends AppenderSkeleton
/*     */   implements Appender
/*     */ {
/*  84 */   protected String databaseURL = "jdbc:odbc:myDB";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  89 */   protected String databaseUser = "me";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  94 */   protected String databasePassword = "mypassword";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 103 */   protected Connection connection = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 114 */   protected String sqlStatement = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 120 */   protected int bufferSize = 1;
/*     */ 
/*     */ 
/*     */   
/*     */   protected ArrayList buffer;
/*     */ 
/*     */ 
/*     */   
/*     */   protected ArrayList removes;
/*     */ 
/*     */   
/*     */   private boolean locationInfo = false;
/*     */ 
/*     */ 
/*     */   
/*     */   public JDBCAppender() {
/* 136 */     this.buffer = new ArrayList(this.bufferSize);
/* 137 */     this.removes = new ArrayList(this.bufferSize);
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
/* 148 */   public boolean getLocationInfo() { return this.locationInfo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 166 */   public void setLocationInfo(boolean flag) { this.locationInfo = flag; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void append(LoggingEvent event) {
/* 174 */     event.getNDC();
/* 175 */     event.getThreadName();
/*     */     
/* 177 */     event.getMDCCopy();
/* 178 */     if (this.locationInfo) {
/* 179 */       event.getLocationInformation();
/*     */     }
/* 181 */     event.getRenderedMessage();
/* 182 */     event.getThrowableStrRep();
/* 183 */     this.buffer.add(event);
/*     */     
/* 185 */     if (this.buffer.size() >= this.bufferSize) {
/* 186 */       flushBuffer();
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
/* 198 */   protected String getLogStatement(LoggingEvent event) { return getLayout().format(event); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void execute(String sql) throws SQLException {
/* 211 */     Connection con = null;
/* 212 */     Statement stmt = null;
/*     */     
/*     */     try {
/* 215 */       con = getConnection();
/*     */       
/* 217 */       stmt = con.createStatement();
/* 218 */       stmt.executeUpdate(sql);
/*     */     } finally {
/* 220 */       if (stmt != null) {
/* 221 */         stmt.close();
/*     */       }
/* 223 */       closeConnection(con);
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
/*     */   protected void closeConnection(Connection con) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Connection getConnection() throws SQLException {
/* 247 */     if (!DriverManager.getDrivers().hasMoreElements()) {
/* 248 */       setDriver("sun.jdbc.odbc.JdbcOdbcDriver");
/*     */     }
/* 250 */     if (this.connection == null) {
/* 251 */       this.connection = DriverManager.getConnection(this.databaseURL, this.databaseUser, this.databasePassword);
/*     */     }
/*     */ 
/*     */     
/* 255 */     return this.connection;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/* 264 */     flushBuffer();
/*     */     
/*     */     try {
/* 267 */       if (this.connection != null && !this.connection.isClosed())
/* 268 */         this.connection.close(); 
/* 269 */     } catch (SQLException e) {
/* 270 */       this.errorHandler.error("Error closing connection", e, 0);
/*     */     } 
/* 272 */     this.closed = true;
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
/*     */   public void flushBuffer() {
/* 284 */     this.removes.ensureCapacity(this.buffer.size());
/* 285 */     for (Iterator i = this.buffer.iterator(); i.hasNext(); ) {
/* 286 */       LoggingEvent logEvent = i.next();
/*     */       try {
/* 288 */         String sql = getLogStatement(logEvent);
/* 289 */         execute(sql);
/*     */       }
/* 291 */       catch (SQLException e) {
/* 292 */         this.errorHandler.error("Failed to excute sql", e, 2);
/*     */       } finally {
/*     */         
/* 295 */         this.removes.add(logEvent);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 300 */     this.buffer.removeAll(this.removes);
/*     */ 
/*     */     
/* 303 */     this.removes.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 309 */   public void finalize() { close(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 317 */   public boolean requiresLayout() { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSql(String s) {
/* 325 */     this.sqlStatement = s;
/* 326 */     if (getLayout() == null) {
/* 327 */       setLayout((Layout)new PatternLayout(s));
/*     */     } else {
/*     */       
/* 330 */       ((PatternLayout)getLayout()).setConversionPattern(s);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 339 */   public String getSql() { return this.sqlStatement; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 344 */   public void setUser(String user) { this.databaseUser = user; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 349 */   public void setURL(String url) { this.databaseURL = url; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 354 */   public void setPassword(String password) { this.databasePassword = password; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBufferSize(int newBufferSize) {
/* 359 */     this.bufferSize = newBufferSize;
/* 360 */     this.buffer.ensureCapacity(this.bufferSize);
/* 361 */     this.removes.ensureCapacity(this.bufferSize);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 366 */   public String getUser() { return this.databaseUser; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 371 */   public String getURL() { return this.databaseURL; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 376 */   public String getPassword() { return this.databasePassword; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 381 */   public int getBufferSize() { return this.bufferSize; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDriver(String driverClass) {
/*     */     try {
/* 391 */       Class.forName(driverClass);
/* 392 */     } catch (Exception e) {
/* 393 */       this.errorHandler.error("Failed to load driver", e, 0);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\log4j\jdbc\JDBCAppender.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.2
 */