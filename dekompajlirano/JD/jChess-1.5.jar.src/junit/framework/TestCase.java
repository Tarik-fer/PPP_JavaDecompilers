/*     */ package junit.framework;
/*     */ 
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class TestCase
/*     */   extends Assert
/*     */   implements Test
/*     */ {
/*     */   private String fName;
/*     */   
/*  87 */   public TestCase() { this.fName = null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  94 */   public TestCase(String name) { this.fName = name; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 101 */   public int countTestCases() { return 1; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 110 */   protected TestResult createResult() { return new TestResult(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TestResult run() {
/* 120 */     TestResult result = createResult();
/* 121 */     run(result);
/* 122 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 129 */   public void run(TestResult result) { result.run(this); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void runBare() throws Throwable {
/* 138 */     Throwable exception = null;
/* 139 */     setUp();
/*     */     try {
/* 141 */       runTest();
/* 142 */     } catch (Throwable running) {
/* 143 */       exception = running;
/*     */     } finally {
/*     */       try {
/* 146 */         tearDown();
/* 147 */       } catch (Throwable tearingDown) {
/* 148 */         if (exception == null) exception = tearingDown; 
/*     */       } 
/*     */     } 
/* 151 */     if (exception != null) throw exception;
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void runTest() throws Throwable {
/* 160 */     assertNotNull("TestCase.fName cannot be null", this.fName);
/* 161 */     Method runMethod = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 167 */       runMethod = getClass().getMethod(this.fName, (Class[])null);
/* 168 */     } catch (NoSuchMethodException e) {
/* 169 */       fail("Method \"" + this.fName + "\" not found");
/*     */     } 
/* 171 */     if (!Modifier.isPublic(runMethod.getModifiers())) {
/* 172 */       fail("Method \"" + this.fName + "\" should be public");
/*     */     }
/*     */     
/*     */     try {
/* 176 */       runMethod.invoke(this, new Object[0]);
/* 177 */     } catch (InvocationTargetException e) {
/* 178 */       e.fillInStackTrace();
/* 179 */       throw e.getTargetException();
/* 180 */     } catch (IllegalAccessException e) {
/* 181 */       e.fillInStackTrace();
/* 182 */       throw e;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 192 */   public static void assertTrue(String message, boolean condition) { Assert.assertTrue(message, condition); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 201 */   public static void assertTrue(boolean condition) { Assert.assertTrue(condition); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 210 */   public static void assertFalse(String message, boolean condition) { Assert.assertFalse(message, condition); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 219 */   public static void assertFalse(boolean condition) { Assert.assertFalse(condition); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 227 */   public static void fail(String message) { Assert.fail(message); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 235 */   public static void fail() { Assert.fail(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 244 */   public static void assertEquals(String message, Object expected, Object actual) { Assert.assertEquals(message, expected, actual); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 253 */   public static void assertEquals(Object expected, Object actual) { Assert.assertEquals(expected, actual); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 261 */   public static void assertEquals(String message, String expected, String actual) { Assert.assertEquals(message, expected, actual); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 269 */   public static void assertEquals(String expected, String actual) { Assert.assertEquals(expected, actual); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 279 */   public static void assertEquals(String message, double expected, double actual, double delta) { Assert.assertEquals(message, expected, actual, delta); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 288 */   public static void assertEquals(double expected, double actual, double delta) { Assert.assertEquals(expected, actual, delta); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 298 */   public static void assertEquals(String message, float expected, float actual, float delta) { Assert.assertEquals(message, expected, actual, delta); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 307 */   public static void assertEquals(float expected, float actual, float delta) { Assert.assertEquals(expected, actual, delta); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 316 */   public static void assertEquals(String message, long expected, long actual) { Assert.assertEquals(message, expected, actual); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 324 */   public static void assertEquals(long expected, long actual) { Assert.assertEquals(expected, actual); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 333 */   public static void assertEquals(String message, boolean expected, boolean actual) { Assert.assertEquals(message, expected, actual); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 341 */   public static void assertEquals(boolean expected, boolean actual) { Assert.assertEquals(expected, actual); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 350 */   public static void assertEquals(String message, byte expected, byte actual) { Assert.assertEquals(message, expected, actual); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 358 */   public static void assertEquals(byte expected, byte actual) { Assert.assertEquals(expected, actual); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 367 */   public static void assertEquals(String message, char expected, char actual) { Assert.assertEquals(message, expected, actual); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 375 */   public static void assertEquals(char expected, char actual) { Assert.assertEquals(expected, actual); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 384 */   public static void assertEquals(String message, short expected, short actual) { Assert.assertEquals(message, expected, actual); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 392 */   public static void assertEquals(short expected, short actual) { Assert.assertEquals(expected, actual); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 401 */   public static void assertEquals(String message, int expected, int actual) { Assert.assertEquals(message, expected, actual); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 409 */   public static void assertEquals(int expected, int actual) { Assert.assertEquals(expected, actual); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 417 */   public static void assertNotNull(Object object) { Assert.assertNotNull(object); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 426 */   public static void assertNotNull(String message, Object object) { Assert.assertNotNull(message, object); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 438 */   public static void assertNull(Object object) { Assert.assertNull(object); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 447 */   public static void assertNull(String message, Object object) { Assert.assertNull(message, object); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 456 */   public static void assertSame(String message, Object expected, Object actual) { Assert.assertSame(message, expected, actual); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 465 */   public static void assertSame(Object expected, Object actual) { Assert.assertSame(expected, actual); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 475 */   public static void assertNotSame(String message, Object expected, Object actual) { Assert.assertNotSame(message, expected, actual); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 484 */   public static void assertNotSame(Object expected, Object actual) { Assert.assertNotSame(expected, actual); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 489 */   public static void failSame(String message) { Assert.failSame(message); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 494 */   public static void failNotSame(String message, Object expected, Object actual) { Assert.failNotSame(message, expected, actual); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 499 */   public static void failNotEquals(String message, Object expected, Object actual) { Assert.failNotEquals(message, expected, actual); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 504 */   public static String format(String message, Object expected, Object actual) { return Assert.format(message, expected, actual); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setUp() throws Exception {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void tearDown() throws Exception {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 526 */   public String toString() { return getName() + "(" + getClass().getName() + ")"; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 535 */   public String getName() { return this.fName; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 544 */   public void setName(String name) { this.fName = name; }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\junit\framework\TestCase.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */