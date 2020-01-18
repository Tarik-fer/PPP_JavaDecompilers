/*    */ package org.junit.experimental.categories;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import org.junit.runner.manipulation.Filter;
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
/*    */ public final class ExcludeCategories
/*    */   extends CategoryFilterFactory
/*    */ {
/* 35 */   protected Filter createFilter(List<Class<?>> categories) { return new ExcludesAny(categories); }
/*    */   
/*    */   private static class ExcludesAny
/*    */     extends Categories.CategoryFilter
/*    */   {
/* 40 */     public ExcludesAny(List<Class<?>> categories) { this(new HashSet<Class<?>>(categories)); }
/*    */ 
/*    */ 
/*    */     
/* 44 */     public ExcludesAny(Set<Class<?>> categories) { super(true, null, true, categories); }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 49 */     public String describe() { return "excludes " + super.describe(); }
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\experimental\categories\ExcludeCategories.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */