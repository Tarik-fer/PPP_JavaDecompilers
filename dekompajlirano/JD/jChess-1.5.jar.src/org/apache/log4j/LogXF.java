/*     */ package org.apache.log4j;
/*     */ 
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
/*     */ public abstract class LogXF
/*     */ {
/*  33 */   protected static final Level TRACE = new Level(5000, "TRACE", 7);
/*     */ 
/*     */ 
/*     */   
/*  37 */   private static final String FQCN = LogXF.class.getName();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static Boolean valueOf(boolean b) {
/*  50 */     if (b) {
/*  51 */       return Boolean.TRUE;
/*     */     }
/*  53 */     return Boolean.FALSE;
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
/*  64 */   protected static Character valueOf(char c) { return new Character(c); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   protected static Byte valueOf(byte b) { return new Byte(b); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   protected static Short valueOf(short b) { return new Short(b); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   protected static Integer valueOf(int b) { return new Integer(b); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   protected static Long valueOf(long b) { return new Long(b); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 119 */   protected static Float valueOf(float b) { return new Float(b); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 130 */   protected static Double valueOf(double b) { return new Double(b); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 140 */   protected static Object[] toArray(Object param1) { return new Object[] { param1 }; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 154 */   protected static Object[] toArray(Object param1, Object param2) { return new Object[] { param1, param2 }; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 170 */   protected static Object[] toArray(Object param1, Object param2, Object param3) { return new Object[] { param1, param2, param3 }; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 188 */   protected static Object[] toArray(Object param1, Object param2, Object param3, Object param4) { return new Object[] { param1, param2, param3, param4 }; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void entering(Logger logger, String sourceClass, String sourceMethod) {
/* 203 */     if (logger.isDebugEnabled()) {
/* 204 */       logger.callAppenders(new LoggingEvent(FQCN, logger, Level.DEBUG, sourceClass + "." + sourceMethod + " ENTRY", null));
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
/*     */   
/*     */   public static void entering(Logger logger, String sourceClass, String sourceMethod, String param) {
/* 221 */     if (logger.isDebugEnabled()) {
/* 222 */       String msg = sourceClass + "." + sourceMethod + " ENTRY " + param;
/* 223 */       logger.callAppenders(new LoggingEvent(FQCN, logger, Level.DEBUG, msg, null));
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
/*     */   
/*     */   public static void entering(Logger logger, String sourceClass, String sourceMethod, Object param) {
/* 240 */     if (logger.isDebugEnabled()) {
/* 241 */       String msg = sourceClass + "." + sourceMethod + " ENTRY ";
/* 242 */       if (param == null) {
/* 243 */         msg = msg + "null";
/*     */       } else {
/*     */         try {
/* 246 */           msg = msg + param;
/* 247 */         } catch (Throwable ex) {
/* 248 */           msg = msg + "?";
/*     */         } 
/*     */       } 
/* 251 */       logger.callAppenders(new LoggingEvent(FQCN, logger, Level.DEBUG, msg, null));
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
/*     */   
/*     */   public static void entering(Logger logger, String sourceClass, String sourceMethod, Object[] params) {
/* 268 */     if (logger.isDebugEnabled()) {
/* 269 */       String msg = sourceClass + "." + sourceMethod + " ENTRY ";
/* 270 */       if (params != null && params.length > 0) {
/* 271 */         String delim = "{";
/* 272 */         for (int i = 0; i < params.length; i++) {
/*     */           try {
/* 274 */             msg = msg + delim + params[i];
/* 275 */           } catch (Throwable ex) {
/* 276 */             msg = msg + delim + "?";
/*     */           } 
/* 278 */           delim = ",";
/*     */         } 
/* 280 */         msg = msg + "}";
/*     */       } else {
/* 282 */         msg = msg + "{}";
/*     */       } 
/* 284 */       logger.callAppenders(new LoggingEvent(FQCN, logger, Level.DEBUG, msg, null));
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
/*     */   public static void exiting(Logger logger, String sourceClass, String sourceMethod) {
/* 299 */     if (logger.isDebugEnabled()) {
/* 300 */       logger.callAppenders(new LoggingEvent(FQCN, logger, Level.DEBUG, sourceClass + "." + sourceMethod + " RETURN", null));
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
/*     */ 
/*     */   
/*     */   public static void exiting(Logger logger, String sourceClass, String sourceMethod, String result) {
/* 318 */     if (logger.isDebugEnabled()) {
/* 319 */       logger.callAppenders(new LoggingEvent(FQCN, logger, Level.DEBUG, sourceClass + "." + sourceMethod + " RETURN " + result, null));
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
/*     */ 
/*     */   
/*     */   public static void exiting(Logger logger, String sourceClass, String sourceMethod, Object result) {
/* 337 */     if (logger.isDebugEnabled()) {
/* 338 */       String msg = sourceClass + "." + sourceMethod + " RETURN ";
/* 339 */       if (result == null) {
/* 340 */         msg = msg + "null";
/*     */       } else {
/*     */         try {
/* 343 */           msg = msg + result;
/* 344 */         } catch (Throwable ex) {
/* 345 */           msg = msg + "?";
/*     */         } 
/*     */       } 
/* 348 */       logger.callAppenders(new LoggingEvent(FQCN, logger, Level.DEBUG, msg, null));
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
/*     */ 
/*     */   
/*     */   public static void throwing(Logger logger, String sourceClass, String sourceMethod, Throwable thrown) {
/* 366 */     if (logger.isDebugEnabled())
/* 367 */       logger.callAppenders(new LoggingEvent(FQCN, logger, Level.DEBUG, sourceClass + "." + sourceMethod + " THROW", thrown)); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\log4j\LogXF.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.2
 */