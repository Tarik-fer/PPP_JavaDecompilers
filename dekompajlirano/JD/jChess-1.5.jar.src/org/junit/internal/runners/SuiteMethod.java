/*    */ package org.junit.internal.runners;
/*    */ 
/*    */ import java.lang.reflect.InvocationTargetException;
/*    */ import java.lang.reflect.Method;
/*    */ import java.lang.reflect.Modifier;
/*    */ import junit.framework.Test;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SuiteMethod
/*    */   extends JUnit38ClassRunner
/*    */ {
/* 24 */   public SuiteMethod(Class<?> klass) throws Throwable { super(testFromSuiteMethod(klass)); }
/*    */ 
/*    */   
/*    */   public static Test testFromSuiteMethod(Class<?> klass) throws Throwable {
/* 28 */     Method suiteMethod = null;
/* 29 */     Test suite = null;
/*    */     try {
/* 31 */       suiteMethod = klass.getMethod("suite", new Class[0]);
/* 32 */       if (!Modifier.isStatic(suiteMethod.getModifiers())) {
/* 33 */         throw new Exception(klass.getName() + ".suite() must be static");
/*    */       }
/* 35 */       suite = (Test)suiteMethod.invoke(null, new Object[0]);
/* 36 */     } catch (InvocationTargetException e) {
/* 37 */       throw e.getCause();
/*    */     } 
/* 39 */     return suite;
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\internal\runners\SuiteMethod.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */