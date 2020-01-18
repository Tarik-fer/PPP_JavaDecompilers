/*    */ package org.hamcrest;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface Description
/*    */ {
/* 13 */   public static final Description NONE = new NullDescription();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   Description appendText(String paramString);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   Description appendDescriptionOf(SelfDescribing paramSelfDescribing);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   Description appendValue(Object paramObject);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   <T> Description appendValueList(String paramString1, String paramString2, String paramString3, T... paramVarArgs);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   <T> Description appendValueList(String paramString1, String paramString2, String paramString3, Iterable<T> paramIterable);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   Description appendList(String paramString1, String paramString2, String paramString3, Iterable<? extends SelfDescribing> paramIterable);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static final class NullDescription
/*    */     implements Description
/*    */   {
/* 53 */     public Description appendDescriptionOf(SelfDescribing value) { return this; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 59 */     public Description appendList(String start, String separator, String end, Iterable<? extends SelfDescribing> values) { return this; }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 64 */     public Description appendText(String text) { return this; }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 69 */     public Description appendValue(Object value) { return this; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 75 */     public <T> Description appendValueList(String start, String separator, String end, T... values) { return this; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 81 */     public <T> Description appendValueList(String start, String separator, String end, Iterable<T> values) { return this; }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 86 */     public String toString() { return ""; }
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\hamcrest\Description.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */