/*     */ package org.junit;
/*     */ 
/*     */ import org.hamcrest.Matcher;
/*     */ import org.hamcrest.MatcherAssert;
/*     */ import org.junit.internal.ArrayComparisonFailure;
/*     */ import org.junit.internal.ExactComparisonCriteria;
/*     */ import org.junit.internal.InexactComparisonCriteria;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Assert
/*     */ {
/*     */   public static void assertTrue(String message, boolean condition) {
/*  40 */     if (!condition) {
/*  41 */       fail(message);
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
/*  52 */   public static void assertTrue(boolean condition) { assertTrue(null, condition); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   public static void assertFalse(String message, boolean condition) { assertTrue(message, !condition); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  74 */   public static void assertFalse(boolean condition) { assertFalse(null, condition); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void fail(String message) {
/*  85 */     if (message == null) {
/*  86 */       throw new AssertionError();
/*     */     }
/*  88 */     throw new AssertionError(message);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  95 */   public static void fail() { fail(null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void assertEquals(String message, Object expected, Object actual) {
/* 111 */     if (equalsRegardingNull(expected, actual))
/*     */       return; 
/* 113 */     if (expected instanceof String && actual instanceof String) {
/* 114 */       String cleanMessage = (message == null) ? "" : message;
/* 115 */       throw new ComparisonFailure(cleanMessage, (String)expected, (String)actual);
/*     */     } 
/*     */     
/* 118 */     failNotEquals(message, expected, actual);
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean equalsRegardingNull(Object expected, Object actual) {
/* 123 */     if (expected == null) {
/* 124 */       return (actual == null);
/*     */     }
/*     */     
/* 127 */     return isEquals(expected, actual);
/*     */   }
/*     */ 
/*     */   
/* 131 */   private static boolean isEquals(Object expected, Object actual) { return expected.equals(actual); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 144 */   public static void assertEquals(Object expected, Object actual) { assertEquals(null, expected, actual); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void assertNotEquals(String message, Object unexpected, Object actual) {
/* 160 */     if (equalsRegardingNull(unexpected, actual)) {
/* 161 */       failEquals(message, actual);
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
/* 175 */   public static void assertNotEquals(Object unexpected, Object actual) { assertNotEquals(null, unexpected, actual); }
/*     */ 
/*     */   
/*     */   private static void failEquals(String message, Object actual) {
/* 179 */     String formatted = "Values should be different. ";
/* 180 */     if (message != null) {
/* 181 */       formatted = message + ". ";
/*     */     }
/*     */     
/* 184 */     formatted = formatted + "Actual: " + actual;
/* 185 */     fail(formatted);
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
/*     */   public static void assertNotEquals(String message, long unexpected, long actual) {
/* 198 */     if (unexpected == actual) {
/* 199 */       failEquals(message, Long.valueOf(actual));
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
/* 211 */   public static void assertNotEquals(long unexpected, long actual) { assertNotEquals(null, unexpected, actual); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void assertNotEquals(String message, double unexpected, double actual, double delta) {
/* 231 */     if (!doubleIsDifferent(unexpected, actual, delta)) {
/* 232 */       failEquals(message, Double.valueOf(actual));
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
/* 249 */   public static void assertNotEquals(double unexpected, double actual, double delta) { assertNotEquals(null, unexpected, actual, delta); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 265 */   public static void assertNotEquals(float unexpected, float actual, float delta) { assertNotEquals(null, unexpected, actual, delta); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 283 */   public static void assertArrayEquals(String message, Object[] expecteds, Object[] actuals) throws ArrayComparisonFailure { internalArrayEquals(message, expecteds, actuals); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 298 */   public static void assertArrayEquals(Object[] expecteds, Object[] actuals) { assertArrayEquals(null, expecteds, actuals); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 314 */   public static void assertArrayEquals(String message, boolean[] expecteds, boolean[] actuals) throws ArrayComparisonFailure { internalArrayEquals(message, expecteds, actuals); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 327 */   public static void assertArrayEquals(boolean[] expecteds, boolean[] actuals) { assertArrayEquals(null, expecteds, actuals); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 341 */   public static void assertArrayEquals(String message, byte[] expecteds, byte[] actuals) throws ArrayComparisonFailure { internalArrayEquals(message, expecteds, actuals); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 352 */   public static void assertArrayEquals(byte[] expecteds, byte[] actuals) { assertArrayEquals(null, expecteds, actuals); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 366 */   public static void assertArrayEquals(String message, char[] expecteds, char[] actuals) throws ArrayComparisonFailure { internalArrayEquals(message, expecteds, actuals); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 377 */   public static void assertArrayEquals(char[] expecteds, char[] actuals) { assertArrayEquals(null, expecteds, actuals); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 391 */   public static void assertArrayEquals(String message, short[] expecteds, short[] actuals) throws ArrayComparisonFailure { internalArrayEquals(message, expecteds, actuals); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 402 */   public static void assertArrayEquals(short[] expecteds, short[] actuals) { assertArrayEquals(null, expecteds, actuals); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 416 */   public static void assertArrayEquals(String message, int[] expecteds, int[] actuals) throws ArrayComparisonFailure { internalArrayEquals(message, expecteds, actuals); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 427 */   public static void assertArrayEquals(int[] expecteds, int[] actuals) { assertArrayEquals(null, expecteds, actuals); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 441 */   public static void assertArrayEquals(String message, long[] expecteds, long[] actuals) throws ArrayComparisonFailure { internalArrayEquals(message, expecteds, actuals); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 452 */   public static void assertArrayEquals(long[] expecteds, long[] actuals) { assertArrayEquals(null, expecteds, actuals); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 469 */   public static void assertArrayEquals(String message, double[] expecteds, double[] actuals, double delta) throws ArrayComparisonFailure { (new InexactComparisonCriteria(delta)).arrayEquals(message, expecteds, actuals); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 483 */   public static void assertArrayEquals(double[] expecteds, double[] actuals, double delta) { assertArrayEquals(null, expecteds, actuals, delta); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 500 */   public static void assertArrayEquals(String message, float[] expecteds, float[] actuals, float delta) throws ArrayComparisonFailure { (new InexactComparisonCriteria(delta)).arrayEquals(message, expecteds, actuals); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 514 */   public static void assertArrayEquals(float[] expecteds, float[] actuals, float delta) { assertArrayEquals(null, expecteds, actuals, delta); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 532 */   private static void internalArrayEquals(String message, Object expecteds, Object actuals) throws ArrayComparisonFailure { (new ExactComparisonCriteria()).arrayEquals(message, expecteds, actuals); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void assertEquals(String message, double expected, double actual, double delta) {
/* 552 */     if (doubleIsDifferent(expected, actual, delta)) {
/* 553 */       failNotEquals(message, Double.valueOf(expected), Double.valueOf(actual));
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
/*     */ 
/*     */ 
/*     */   
/*     */   public static void assertEquals(String message, float expected, float actual, float delta) {
/* 574 */     if (floatIsDifferent(expected, actual, delta)) {
/* 575 */       failNotEquals(message, Float.valueOf(expected), Float.valueOf(actual));
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
/*     */ 
/*     */ 
/*     */   
/*     */   public static void assertNotEquals(String message, float unexpected, float actual, float delta) {
/* 596 */     if (!floatIsDifferent(unexpected, actual, delta)) {
/* 597 */       failEquals(message, Float.valueOf(actual));
/*     */     }
/*     */   }
/*     */   
/*     */   private static boolean doubleIsDifferent(double d1, double d2, double delta) {
/* 602 */     if (Double.compare(d1, d2) == 0) {
/* 603 */       return false;
/*     */     }
/* 605 */     if (Math.abs(d1 - d2) <= delta) {
/* 606 */       return false;
/*     */     }
/*     */     
/* 609 */     return true;
/*     */   }
/*     */   
/*     */   private static boolean floatIsDifferent(float f1, float f2, float delta) {
/* 613 */     if (Float.compare(f1, f2) == 0) {
/* 614 */       return false;
/*     */     }
/* 616 */     if (Math.abs(f1 - f2) <= delta) {
/* 617 */       return false;
/*     */     }
/*     */     
/* 620 */     return true;
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
/* 631 */   public static void assertEquals(long expected, long actual) { assertEquals(null, expected, actual); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void assertEquals(String message, long expected, long actual) {
/* 644 */     if (expected != actual) {
/* 645 */       failNotEquals(message, Long.valueOf(expected), Long.valueOf(actual));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/* 656 */   public static void assertEquals(double expected, double actual) { assertEquals(null, expected, actual); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/* 667 */   public static void assertEquals(String message, double expected, double actual) { fail("Use assertEquals(expected, actual, delta) to compare floating-point numbers"); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 683 */   public static void assertEquals(double expected, double actual, double delta) { assertEquals(null, expected, actual, delta); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 700 */   public static void assertEquals(float expected, float actual, float delta) { assertEquals(null, expected, actual, delta); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 712 */   public static void assertNotNull(String message, Object object) { assertTrue(message, (object != null)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 722 */   public static void assertNotNull(Object object) { assertNotNull(null, object); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void assertNull(String message, Object object) {
/* 734 */     if (object == null) {
/*     */       return;
/*     */     }
/* 737 */     failNotNull(message, object);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 747 */   public static void assertNull(Object object) { assertNull(null, object); }
/*     */ 
/*     */   
/*     */   private static void failNotNull(String message, Object actual) {
/* 751 */     String formatted = "";
/* 752 */     if (message != null) {
/* 753 */       formatted = message + " ";
/*     */     }
/* 755 */     fail(formatted + "expected null, but was:<" + actual + ">");
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
/*     */   public static void assertSame(String message, Object expected, Object actual) {
/* 768 */     if (expected == actual) {
/*     */       return;
/*     */     }
/* 771 */     failNotSame(message, expected, actual);
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
/* 782 */   public static void assertSame(Object expected, Object actual) { assertSame(null, expected, actual); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void assertNotSame(String message, Object unexpected, Object actual) {
/* 797 */     if (unexpected == actual) {
/* 798 */       failSame(message);
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
/* 811 */   public static void assertNotSame(Object unexpected, Object actual) { assertNotSame(null, unexpected, actual); }
/*     */ 
/*     */   
/*     */   private static void failSame(String message) {
/* 815 */     String formatted = "";
/* 816 */     if (message != null) {
/* 817 */       formatted = message + " ";
/*     */     }
/* 819 */     fail(formatted + "expected not same");
/*     */   }
/*     */ 
/*     */   
/*     */   private static void failNotSame(String message, Object expected, Object actual) {
/* 824 */     String formatted = "";
/* 825 */     if (message != null) {
/* 826 */       formatted = message + " ";
/*     */     }
/* 828 */     fail(formatted + "expected same:<" + expected + "> was not:<" + actual + ">");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 834 */   private static void failNotEquals(String message, Object expected, Object actual) { fail(format(message, expected, actual)); }
/*     */ 
/*     */   
/*     */   static String format(String message, Object expected, Object actual) {
/* 838 */     String formatted = "";
/* 839 */     if (message != null && !message.equals("")) {
/* 840 */       formatted = message + " ";
/*     */     }
/* 842 */     String expectedString = String.valueOf(expected);
/* 843 */     String actualString = String.valueOf(actual);
/* 844 */     if (expectedString.equals(actualString)) {
/* 845 */       return formatted + "expected: " + formatClassAndValue(expected, expectedString) + " but was: " + formatClassAndValue(actual, actualString);
/*     */     }
/*     */ 
/*     */     
/* 849 */     return formatted + "expected:<" + expectedString + "> but was:<" + actualString + ">";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static String formatClassAndValue(Object value, String valueString) {
/* 855 */     String className = (value == null) ? "null" : value.getClass().getName();
/* 856 */     return className + "<" + valueString + ">";
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
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/* 876 */   public static void assertEquals(String message, Object[] expecteds, Object[] actuals) { assertArrayEquals(message, expecteds, actuals); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/* 893 */   public static void assertEquals(Object[] expecteds, Object[] actuals) { assertArrayEquals(expecteds, actuals); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 923 */   public static <T> void assertThat(T actual, Matcher<? super T> matcher) { assertThat("", actual, matcher); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 956 */   public static <T> void assertThat(String reason, T actual, Matcher<? super T> matcher) { MatcherAssert.assertThat(reason, actual, matcher); }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\Assert.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */