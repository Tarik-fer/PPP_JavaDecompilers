/*    */ package org.hamcrest.internal;
/*    */ 
/*    */ import java.lang.reflect.Method;
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
/*    */ public class ReflectiveTypeFinder
/*    */ {
/*    */   private final String methodName;
/*    */   private final int expectedNumberOfParameters;
/*    */   private final int typedParameter;
/*    */   
/*    */   public ReflectiveTypeFinder(String methodName, int expectedNumberOfParameters, int typedParameter) {
/* 36 */     this.methodName = methodName;
/* 37 */     this.expectedNumberOfParameters = expectedNumberOfParameters;
/* 38 */     this.typedParameter = typedParameter;
/*    */   }
/*    */   
/*    */   public Class<?> findExpectedType(Class<?> fromClass) {
/* 42 */     for (Class<?> c = fromClass; c != Object.class; c = c.getSuperclass()) {
/* 43 */       for (Method method : c.getDeclaredMethods()) {
/* 44 */         if (canObtainExpectedTypeFrom(method)) {
/* 45 */           return expectedTypeFrom(method);
/*    */         }
/*    */       } 
/*    */     } 
/* 49 */     throw new Error("Cannot determine correct type for " + this.methodName + "() method.");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 57 */   protected boolean canObtainExpectedTypeFrom(Method method) { return (method.getName().equals(this.methodName) && (method.getParameterTypes()).length == this.expectedNumberOfParameters && !method.isSynthetic()); }
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
/* 68 */   protected Class<?> expectedTypeFrom(Method method) { return method.getParameterTypes()[this.typedParameter]; }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\hamcrest\internal\ReflectiveTypeFinder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */