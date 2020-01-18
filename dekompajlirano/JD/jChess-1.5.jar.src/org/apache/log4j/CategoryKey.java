/*    */ package org.apache.log4j;
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
/*    */ class CategoryKey
/*    */ {
/*    */   String name;
/*    */   int hashCache;
/*    */   
/*    */   CategoryKey(String name) {
/* 31 */     this.name = name;
/* 32 */     this.hashCache = name.hashCode();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 38 */   public final int hashCode() { return this.hashCache; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public final boolean equals(Object rArg) {
/* 44 */     if (this == rArg) {
/* 45 */       return true;
/*    */     }
/* 47 */     if (rArg != null && CategoryKey.class == rArg.getClass()) {
/* 48 */       return this.name.equals(((CategoryKey)rArg).name);
/*    */     }
/* 50 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\log4j\CategoryKey.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.2
 */