/*    */ package org.apache.log4j.spi;
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
/*    */ public class DefaultRepositorySelector
/*    */   implements RepositorySelector
/*    */ {
/*    */   final LoggerRepository repository;
/*    */   
/* 29 */   public DefaultRepositorySelector(LoggerRepository repository) { this.repository = repository; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 34 */   public LoggerRepository getLoggerRepository() { return this.repository; }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\log4j\spi\DefaultRepositorySelector.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.2
 */