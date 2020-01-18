/*    */ package org.junit.internal.runners;
/*    */ 
/*    */ import java.lang.reflect.InvocationTargetException;
/*    */ import java.lang.reflect.Method;
/*    */ import java.util.List;
/*    */ import org.junit.After;
/*    */ import org.junit.Before;
/*    */ import org.junit.Ignore;
/*    */ import org.junit.Test;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class TestMethod
/*    */ {
/*    */   private final Method method;
/*    */   private TestClass testClass;
/*    */   
/*    */   public TestMethod(Method method, TestClass testClass) {
/* 25 */     this.method = method;
/* 26 */     this.testClass = testClass;
/*    */   }
/*    */ 
/*    */   
/* 30 */   public boolean isIgnored() { return (this.method.getAnnotation((Class)Ignore.class) != null); }
/*    */ 
/*    */   
/*    */   public long getTimeout() {
/* 34 */     Test annotation = this.method.getAnnotation(Test.class);
/* 35 */     if (annotation == null) {
/* 36 */       return 0L;
/*    */     }
/* 38 */     long timeout = annotation.timeout();
/* 39 */     return timeout;
/*    */   }
/*    */   
/*    */   protected Class<? extends Throwable> getExpectedException() {
/* 43 */     Test annotation = this.method.getAnnotation(Test.class);
/* 44 */     if (annotation == null || annotation.expected() == Test.None.class) {
/* 45 */       return null;
/*    */     }
/* 47 */     return annotation.expected();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 52 */   boolean isUnexpected(Throwable exception) { return !getExpectedException().isAssignableFrom(exception.getClass()); }
/*    */ 
/*    */ 
/*    */   
/* 56 */   boolean expectsException() { return (getExpectedException() != null); }
/*    */ 
/*    */ 
/*    */   
/* 60 */   List<Method> getBefores() { return this.testClass.getAnnotatedMethods((Class)Before.class); }
/*    */ 
/*    */ 
/*    */   
/* 64 */   List<Method> getAfters() { return this.testClass.getAnnotatedMethods((Class)After.class); }
/*    */ 
/*    */ 
/*    */   
/* 68 */   public void invoke(Object test) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException { this.method.invoke(test, new Object[0]); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\internal\runners\TestMethod.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */