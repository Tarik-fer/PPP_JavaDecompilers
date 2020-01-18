/*    */ package org.junit.internal.matchers;
/*    */ 
/*    */ import java.lang.reflect.Method;
/*    */ import org.hamcrest.BaseMatcher;
/*    */ import org.junit.internal.MethodSorter;
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
/*    */ @Deprecated
/*    */ public abstract class TypeSafeMatcher<T>
/*    */   extends BaseMatcher<T>
/*    */ {
/*    */   private Class<?> expectedType;
/*    */   
/*    */   public abstract boolean matchesSafely(T paramT);
/*    */   
/* 27 */   protected TypeSafeMatcher() { this.expectedType = findExpectedType(getClass()); }
/*    */ 
/*    */   
/*    */   private static Class<?> findExpectedType(Class<?> fromClass) {
/* 31 */     for (Class<?> c = fromClass; c != Object.class; c = c.getSuperclass()) {
/* 32 */       for (Method method : MethodSorter.getDeclaredMethods(c)) {
/* 33 */         if (isMatchesSafelyMethod(method)) {
/* 34 */           return method.getParameterTypes()[0];
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 39 */     throw new Error("Cannot determine correct type for matchesSafely() method.");
/*    */   }
/*    */ 
/*    */   
/* 43 */   private static boolean isMatchesSafelyMethod(Method method) { return (method.getName().equals("matchesSafely") && (method.getParameterTypes()).length == 1 && !method.isSynthetic()); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 49 */   protected TypeSafeMatcher(Class<T> expectedType) { this.expectedType = expectedType; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 59 */   public final boolean matches(Object item) { return (item != null && this.expectedType.isInstance(item) && matchesSafely((T)item)); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\internal\matchers\TypeSafeMatcher.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */