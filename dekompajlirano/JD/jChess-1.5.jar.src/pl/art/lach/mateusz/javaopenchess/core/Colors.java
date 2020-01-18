/*    */ package pl.art.lach.mateusz.javaopenchess.core;
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
/*    */ public enum Colors
/*    */ {
/* 23 */   WHITE("white", 'w'),
/* 24 */   BLACK("black", 'b');
/*    */   
/*    */   protected String colorName;
/*    */   
/*    */   protected char symbol;
/*    */ 
/*    */   
/*    */   Colors(String colorName, char symbol) {
/* 32 */     this.colorName = colorName;
/* 33 */     this.symbol = symbol;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 38 */   public String getColorName() { return this.colorName; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 43 */   public char getSymbol() { return this.symbol; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 48 */   public String getSymbolAsString() { return String.valueOf(this.symbol); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\core\Colors.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */