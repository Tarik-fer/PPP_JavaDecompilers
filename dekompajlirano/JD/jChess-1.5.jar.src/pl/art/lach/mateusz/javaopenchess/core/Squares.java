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
/*    */ public enum Squares
/*    */ {
/* 23 */   SQ_A(0),
/*    */   
/* 25 */   SQ_B(1),
/*    */   
/* 27 */   SQ_C(2),
/*    */   
/* 29 */   SQ_D(3),
/*    */   
/* 31 */   SQ_E(4),
/*    */   
/* 33 */   SQ_F(5),
/*    */   
/* 35 */   SQ_G(6),
/*    */   
/* 37 */   SQ_H(7),
/*    */   
/* 39 */   SQ_1(7),
/*    */   
/* 41 */   SQ_2(6),
/*    */   
/* 43 */   SQ_3(5),
/*    */   
/* 45 */   SQ_4(4),
/*    */   
/* 47 */   SQ_5(3),
/*    */   
/* 49 */   SQ_6(2),
/*    */   
/* 51 */   SQ_7(1),
/*    */   
/* 53 */   SQ_8(0);
/*    */ 
/*    */   
/*    */   private int value;
/*    */ 
/*    */   
/* 59 */   Squares(int value) { this.value = value; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 67 */   public int getValue() { return this.value; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 75 */   public void setValue(int value) { this.value = value; }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\core\Squares.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */