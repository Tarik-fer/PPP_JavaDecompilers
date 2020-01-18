/*    */ package pl.art.lach.mateusz.javaopenchess.core.players;
/*    */ 
/*    */ import pl.art.lach.mateusz.javaopenchess.core.Colors;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.players.implementation.ComputerPlayer;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.players.implementation.HumanPlayer;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.players.implementation.NetworkPlayer;
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
/*    */ public class PlayerFactory
/*    */ {
/*    */   public static Player getInstance(String name, Colors color, PlayerType playerType) {
/*    */     ComputerPlayer computerPlayer;
/*    */     HumanPlayer humanPlayer;
/*    */     NetworkPlayer networkPlayer;
/* 30 */     Player player = null;
/* 31 */     switch (playerType) {
/*    */       
/*    */       case LOCAL_USER:
/* 34 */         return (Player)new HumanPlayer(name, color);
/*    */       
/*    */       case NETWORK_USER:
/* 37 */         return (Player)new NetworkPlayer(name, color);
/*    */       
/*    */       case COMPUTER:
/* 40 */         computerPlayer = new ComputerPlayer(name, color);
/* 41 */         computerPlayer.setName("CPU");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 47 */         return (Player)computerPlayer;
/*    */     } 
/*    */     return (Player)new HumanPlayer(name, color);
/*    */   }
/*    */   
/* 52 */   public static Player getInstance(String name, String color, PlayerType playerType) { return getInstance(name, Colors.valueOf(color.toUpperCase()), playerType); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\core\players\PlayerFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */