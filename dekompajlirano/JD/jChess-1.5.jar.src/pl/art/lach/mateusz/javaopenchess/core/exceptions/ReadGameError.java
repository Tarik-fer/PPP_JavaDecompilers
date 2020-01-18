/*    */ package pl.art.lach.mateusz.javaopenchess.core.exceptions;
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
/*    */ public class ReadGameError
/*    */   extends Exception
/*    */ {
/*    */   private String message;
/*    */   private String move;
/*    */   
/* 28 */   public ReadGameError(String message) { this.message = message; }
/*    */ 
/*    */ 
/*    */   
/*    */   public ReadGameError(String message, String move) {
/* 33 */     this(message);
/* 34 */     this.move = move;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 43 */   public String getMessage() { return this.message; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 51 */   public void setMessage(String message) { this.message = message; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 59 */   public String getMove() { return this.move; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 67 */   public void setMove(String move) { this.move = move; }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\core\exceptions\ReadGameError.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */