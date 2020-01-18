/*    */ package org.junit.runners;
/*    */ 
/*    */ import java.lang.reflect.Method;
/*    */ import java.util.Comparator;
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
/*    */ public enum MethodSorters
/*    */ {
/* 19 */   NAME_ASCENDING(MethodSorter.NAME_ASCENDING),
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 25 */   JVM(null),
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 30 */   DEFAULT(MethodSorter.DEFAULT);
/*    */   
/*    */   private final Comparator<Method> comparator;
/*    */ 
/*    */   
/* 35 */   MethodSorters(Comparator<Method> comparator) { this.comparator = comparator; }
/*    */ 
/*    */ 
/*    */   
/* 39 */   public Comparator<Method> getComparator() { return this.comparator; }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\runners\MethodSorters.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */