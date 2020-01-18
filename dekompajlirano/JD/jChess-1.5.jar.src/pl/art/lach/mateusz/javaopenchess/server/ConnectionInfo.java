/*    */ package pl.art.lach.mateusz.javaopenchess.server;
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
/*    */ public enum ConnectionInfo
/*    */ {
/* 24 */   EVERYTHING_IS_OK(0),
/*    */   
/* 26 */   ERR_WRONG_TABLE_ID(1),
/*    */   
/* 28 */   ERR_TABLE_IS_FULL(2),
/*    */   
/* 30 */   ERR_GAME_WITHOUT_OBSERVERS(3),
/*    */   
/* 32 */   ERR_INVALID_PASSWORD(4);
/*    */ 
/*    */   
/*    */   private int value;
/*    */ 
/*    */   
/* 38 */   ConnectionInfo(int value) { this.value = value; }
/*    */ 
/*    */ 
/*    */   
/*    */   public static ConnectionInfo get(int id) {
/* 43 */     switch (id) {
/*    */       
/*    */       case 0:
/* 46 */         return EVERYTHING_IS_OK;
/*    */       case 1:
/* 48 */         return ERR_WRONG_TABLE_ID;
/*    */       case 2:
/* 50 */         return ERR_TABLE_IS_FULL;
/*    */       case 3:
/* 52 */         return ERR_GAME_WITHOUT_OBSERVERS;
/*    */       case 4:
/* 54 */         return ERR_INVALID_PASSWORD;
/*    */     } 
/* 56 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 62 */   public int getValue() { return this.value; }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\server\ConnectionInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */