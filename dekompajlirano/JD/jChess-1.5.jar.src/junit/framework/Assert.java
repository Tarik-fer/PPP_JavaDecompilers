/*     */ package junit.framework;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public class Assert
/*     */ {
/*     */   public static void assertTrue(String message, boolean condition) {
/*  21 */     if (!condition) {
/*  22 */       fail(message);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  31 */   public static void assertTrue(boolean condition) { assertTrue(null, condition); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  39 */   public static void assertFalse(String message, boolean condition) { assertTrue(message, !condition); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  47 */   public static void assertFalse(boolean condition) { assertFalse(null, condition); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void fail(String message) {
/*  54 */     if (message == null) {
/*  55 */       throw new AssertionFailedError();
/*     */     }
/*  57 */     throw new AssertionFailedError(message);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   public static void fail() { fail(null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void assertEquals(String message, Object expected, Object actual) {
/*  72 */     if (expected == null && actual == null) {
/*     */       return;
/*     */     }
/*  75 */     if (expected != null && expected.equals(actual)) {
/*     */       return;
/*     */     }
/*  78 */     failNotEquals(message, expected, actual);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   public static void assertEquals(Object expected, Object actual) { assertEquals(null, expected, actual); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void assertEquals(String message, String expected, String actual) {
/*  93 */     if (expected == null && actual == null) {
/*     */       return;
/*     */     }
/*  96 */     if (expected != null && expected.equals(actual)) {
/*     */       return;
/*     */     }
/*  99 */     String cleanMessage = (message == null) ? "" : message;
/* 100 */     throw new ComparisonFailure(cleanMessage, expected, actual);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 107 */   public static void assertEquals(String expected, String actual) { assertEquals(null, expected, actual); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void assertEquals(String message, double expected, double actual, double delta) {
/* 116 */     if (Double.compare(expected, actual) == 0) {
/*     */       return;
/*     */     }
/* 119 */     if (Math.abs(expected - actual) > delta) {
/* 120 */       failNotEquals(message, new Double(expected), new Double(actual));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 129 */   public static void assertEquals(double expected, double actual, double delta) { assertEquals(null, expected, actual, delta); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void assertEquals(String message, float expected, float actual, float delta) {
/* 138 */     if (Float.compare(expected, actual) == 0) {
/*     */       return;
/*     */     }
/* 141 */     if (Math.abs(expected - actual) > delta) {
/* 142 */       failNotEquals(message, new Float(expected), new Float(actual));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 151 */   public static void assertEquals(float expected, float actual, float delta) { assertEquals(null, expected, actual, delta); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 159 */   public static void assertEquals(String message, long expected, long actual) { assertEquals(message, Long.valueOf(expected), Long.valueOf(actual)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 166 */   public static void assertEquals(long expected, long actual) { assertEquals(null, expected, actual); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 174 */   public static void assertEquals(String message, boolean expected, boolean actual) { assertEquals(message, Boolean.valueOf(expected), Boolean.valueOf(actual)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 181 */   public static void assertEquals(boolean expected, boolean actual) { assertEquals(null, expected, actual); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 189 */   public static void assertEquals(String message, byte expected, byte actual) { assertEquals(message, Byte.valueOf(expected), Byte.valueOf(actual)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 196 */   public static void assertEquals(byte expected, byte actual) { assertEquals(null, expected, actual); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 204 */   public static void assertEquals(String message, char expected, char actual) { assertEquals(message, Character.valueOf(expected), Character.valueOf(actual)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 211 */   public static void assertEquals(char expected, char actual) { assertEquals(null, expected, actual); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 219 */   public static void assertEquals(String message, short expected, short actual) { assertEquals(message, Short.valueOf(expected), Short.valueOf(actual)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 226 */   public static void assertEquals(short expected, short actual) { assertEquals(null, expected, actual); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 234 */   public static void assertEquals(String message, int expected, int actual) { assertEquals(message, Integer.valueOf(expected), Integer.valueOf(actual)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 241 */   public static void assertEquals(int expected, int actual) { assertEquals(null, expected, actual); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 248 */   public static void assertNotNull(Object object) { assertNotNull(null, object); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 256 */   public static void assertNotNull(String message, Object object) { assertTrue(message, (object != null)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void assertNull(Object object) {
/* 267 */     if (object != null) {
/* 268 */       assertNull("Expected: <null> but was: " + object.toString(), object);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 277 */   public static void assertNull(String message, Object object) { assertTrue(message, (object == null)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void assertSame(String message, Object expected, Object actual) {
/* 285 */     if (expected == actual) {
/*     */       return;
/*     */     }
/* 288 */     failNotSame(message, expected, actual);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 296 */   public static void assertSame(Object expected, Object actual) { assertSame(null, expected, actual); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void assertNotSame(String message, Object expected, Object actual) {
/* 305 */     if (expected == actual) {
/* 306 */       failSame(message);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 315 */   public static void assertNotSame(Object expected, Object actual) { assertNotSame(null, expected, actual); }
/*     */ 
/*     */   
/*     */   public static void failSame(String message) {
/* 319 */     String formatted = (message != null) ? (message + " ") : "";
/* 320 */     fail(formatted + "expected not same");
/*     */   }
/*     */   
/*     */   public static void failNotSame(String message, Object expected, Object actual) {
/* 324 */     String formatted = (message != null) ? (message + " ") : "";
/* 325 */     fail(formatted + "expected same:<" + expected + "> was not:<" + actual + ">");
/*     */   }
/*     */ 
/*     */   
/* 329 */   public static void failNotEquals(String message, Object expected, Object actual) { fail(format(message, expected, actual)); }
/*     */ 
/*     */   
/*     */   public static String format(String message, Object expected, Object actual) {
/* 333 */     String formatted = "";
/* 334 */     if (message != null && message.length() > 0) {
/* 335 */       formatted = message + " ";
/*     */     }
/* 337 */     return formatted + "expected:<" + expected + "> but was:<" + actual + ">";
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\junit\framework\Assert.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */