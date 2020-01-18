/*    */ package pl.art.lach.mateusz.javaopenchess.core.ai;
/*    */ 
/*    */ import pl.art.lach.mateusz.javaopenchess.core.ai.joc_ai.Level1;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.ai.joc_ai.Level2;
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
/*    */ public class AIFactory
/*    */ {
/*    */   public static final AI getAI(int level) {
/* 27 */     Object object = new Level1();
/* 28 */     if (1 == level) {
/*    */       
/* 30 */       object = new Level1();
/*    */     }
/* 32 */     else if (2 == level) {
/*    */       
/* 34 */       object = new Level2();
/*    */     } 
/* 36 */     return (AI)object;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 41 */   public static final AI getAI(String level) { return getAI(Integer.parseInt(level)); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\core\ai\AIFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */