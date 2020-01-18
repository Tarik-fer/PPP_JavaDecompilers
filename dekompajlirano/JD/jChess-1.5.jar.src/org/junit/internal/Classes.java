/*    */ package org.junit.internal;
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
/*    */ public class Classes
/*    */ {
/* 16 */   public static Class<?> getClass(String className) throws ClassNotFoundException { return Class.forName(className, true, Thread.currentThread().getContextClassLoader()); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\internal\Classes.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */