/*    */ package org.junit.experimental.runners;
/*    */ 
/*    */ import java.lang.reflect.Modifier;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.junit.runners.Suite;
/*    */ import org.junit.runners.model.RunnerBuilder;
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
/*    */ public class Enclosed
/*    */   extends Suite
/*    */ {
/* 31 */   public Enclosed(Class<?> klass, RunnerBuilder builder) throws Throwable { super(builder, klass, filterAbstractClasses(klass.getClasses())); }
/*    */ 
/*    */   
/*    */   private static Class<?>[] filterAbstractClasses(Class<?>[] classes) {
/* 35 */     List<Class<?>> filteredList = new ArrayList<Class<?>>(classes.length);
/*    */     
/* 37 */     for (Class<?> clazz : classes) {
/* 38 */       if (!Modifier.isAbstract(clazz.getModifiers())) {
/* 39 */         filteredList.add(clazz);
/*    */       }
/*    */     } 
/*    */     
/* 43 */     return (Class[])filteredList.toArray((Class<?>[][])new Class[filteredList.size()]);
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\experimental\runners\Enclosed.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */