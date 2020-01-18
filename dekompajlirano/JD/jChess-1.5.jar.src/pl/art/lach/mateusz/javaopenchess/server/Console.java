/*     */ package pl.art.lach.mateusz.javaopenchess.server;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Map;
/*     */ import org.apache.log4j.Logger;
/*     */ import pl.art.lach.mateusz.javaopenchess.utils.MD5;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Console
/*     */ {
/*  30 */   private static final Logger LOG = Logger.getLogger(Console.class);
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/*  34 */     System.out.println("JChess Server Start!");
/*     */     
/*  36 */     Server server = new Server();
/*  37 */     Server.isPrintEnable = false;
/*     */     
/*  39 */     boolean isOK = true;
/*  40 */     while (isOK) {
/*     */       
/*  42 */       System.out.println("--------------------");
/*  43 */       System.out.println("[1] New table");
/*  44 */       System.out.println("[2] List of active tables");
/*  45 */       System.out.println("[3] Turn on/off server messages");
/*  46 */       System.out.println("[4] Turn off server");
/*  47 */       System.out.print("-> ");
/*  48 */       String str = readString();
/*     */       
/*  50 */       if (str.equals("1")) {
/*     */         String observer;
/*  52 */         System.out.print("ID of game: ");
/*  53 */         int gameID = Integer.parseInt(readString());
/*     */         
/*  55 */         System.out.print("Password: ");
/*  56 */         String pass = MD5.encrypt(readString());
/*     */ 
/*     */ 
/*     */         
/*     */         do {
/*  61 */           System.out.print("Game with observers?[t/n] (t=YES, n=NO): ");
/*  62 */           observer = readString();
/*     */         }
/*  64 */         while (!observer.equalsIgnoreCase("t") && !observer.equalsIgnoreCase("n"));
/*     */         
/*  66 */         boolean canObserver = observer.equalsIgnoreCase("t");
/*     */         
/*  68 */         server.newTable(gameID, pass, canObserver, true); continue;
/*     */       } 
/*  70 */       if (str.equals("2")) {
/*     */         
/*  72 */         for (Map.Entry<Integer, Table> entry : Server.tables.entrySet()) {
/*     */           String p2, p1;
/*  74 */           Integer id = entry.getKey();
/*  75 */           Table table = entry.getValue();
/*     */ 
/*     */ 
/*     */           
/*  79 */           if (table.clientPlayer1 == null || table.clientPlayer1.nick == null) {
/*     */             
/*  81 */             p1 = "empty";
/*     */           }
/*     */           else {
/*     */             
/*  85 */             p1 = table.clientPlayer1.nick;
/*     */           } 
/*     */           
/*  88 */           if (table.clientPlayer2 == null || table.clientPlayer2.nick == null) {
/*     */             
/*  90 */             p2 = "empty";
/*     */           }
/*     */           else {
/*     */             
/*  94 */             p2 = table.clientPlayer2.nick;
/*     */           } 
/*     */           
/*  97 */           System.out.println("\t" + id + ": " + p1 + " vs " + p2);
/*     */         }  continue;
/*     */       } 
/* 100 */       if (str.equals("3")) {
/*     */         
/* 102 */         if (!Server.isPrintEnable) {
/*     */           
/* 104 */           Server.isPrintEnable = true;
/* 105 */           System.out.println("Messages of server has been turned on");
/*     */           
/*     */           continue;
/*     */         } 
/* 109 */         Server.isPrintEnable = false;
/* 110 */         System.out.println("Messages of server has been turned off");
/*     */         continue;
/*     */       } 
/* 113 */       if (str.equals("4")) {
/*     */         
/* 115 */         isOK = false;
/*     */         
/*     */         continue;
/*     */       } 
/* 119 */       System.out.println("Unrecognized command");
/*     */     } 
/*     */     
/* 122 */     System.exit(0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String readString() {
/* 128 */     StringBuilder sb = new StringBuilder();
/*     */     try {
/*     */       int ch;
/* 131 */       while ((ch = System.in.read()) != 10)
/*     */       {
/* 133 */         sb.append((char)ch);
/*     */       }
/*     */     }
/* 136 */     catch (IOException ex) {
/*     */       
/* 138 */       LOG.error("readString()/IOException: " + ex);
/*     */     } 
/*     */     
/* 141 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\server\Console.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */