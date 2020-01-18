/*    */ package org.junit.runner;
/*    */ 
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
/*    */ public interface FilterFactory
/*    */ {
/*    */   Filter createFilter(FilterFactoryParams paramFilterFactoryParams) throws FilterNotCreatedException;
/*    */   
/*    */   public static class FilterNotCreatedException
/*    */     extends Exception
/*    */   {
/* 21 */     public FilterNotCreatedException(Exception exception) { super(exception.getMessage(), exception); }
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\runner\FilterFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */