/*     */ package pl.art.lach.mateusz.javaopenchess.server;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.net.ServerSocket;
/*     */ import java.net.Socket;
/*     */ import java.util.HashMap;
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
/*     */ public class Server
/*     */   implements Runnable
/*     */ {
/*  33 */   private static final Logger LOG = Logger.getLogger(Server.class);
/*     */   
/*     */   public static boolean isPrintEnable = true;
/*     */   public static Map<Integer, Table> tables;
/*  37 */   public static int port = 4449;
/*     */   
/*     */   private static ServerSocket ss;
/*     */   private static boolean isRunning = false;
/*     */   
/*     */   public Server() {
/*  43 */     if (!isRunning) {
/*     */       
/*  45 */       runServer();
/*     */       
/*  47 */       Thread thread = new Thread(this);
/*  48 */       thread.start();
/*     */       
/*  50 */       isRunning = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   public static boolean isRunning() { return isRunning; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void runServer() {
/*     */     try {
/*  65 */       ss = new ServerSocket(port);
/*  66 */       print("running");
/*     */     }
/*  68 */     catch (IOException ex) {
/*     */       
/*  70 */       LOG.error("IOException: " + ex);
/*     */     } 
/*     */     
/*  73 */     tables = new HashMap<>();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/*  79 */     print("listening port: " + port);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     while (true) {
/*     */       try {
/*  88 */         Socket s = ss.accept();
/*  89 */         ObjectInputStream input = new ObjectInputStream(s.getInputStream());
/*  90 */         ObjectOutputStream output = new ObjectOutputStream(s.getOutputStream());
/*     */         
/*  92 */         print("new connection");
/*     */ 
/*     */         
/*  95 */         int tableID = input.readInt();
/*  96 */         print("readed table ID: " + tableID);
/*  97 */         boolean joinAsPlayer = input.readBoolean();
/*  98 */         print("readed joinAsPlayer: " + joinAsPlayer);
/*  99 */         String nick = input.readUTF();
/* 100 */         print("readed nick: " + nick);
/* 101 */         String password = input.readUTF();
/* 102 */         print("readed password: " + password);
/*     */ 
/*     */         
/* 105 */         if (!tables.containsKey(Integer.valueOf(tableID))) {
/*     */           
/* 107 */           print("bad table ID");
/* 108 */           output.writeInt(ConnectionInfo.ERR_WRONG_TABLE_ID.getValue());
/* 109 */           output.flush();
/*     */           continue;
/*     */         } 
/* 112 */         Table table = tables.get(Integer.valueOf(tableID));
/*     */         
/* 114 */         if (!MD5.encrypt(table.password).equals(password)) {
/*     */           
/* 116 */           print("bad password: " + MD5.encrypt(table.password) + " != " + password);
/* 117 */           output.writeInt(ConnectionInfo.ERR_INVALID_PASSWORD.getValue());
/* 118 */           output.flush();
/*     */           
/*     */           continue;
/*     */         } 
/* 122 */         if (joinAsPlayer) {
/*     */           
/* 124 */           print("join as player");
/* 125 */           if (table.isAllPlayers()) {
/*     */             
/* 127 */             print("error: was all players at this table");
/* 128 */             output.writeInt(ConnectionInfo.ERR_TABLE_IS_FULL.getValue());
/* 129 */             output.flush();
/*     */             
/*     */             continue;
/*     */           } 
/*     */           
/* 134 */           print("wasn't all players at this table");
/*     */           
/* 136 */           output.writeInt(ConnectionInfo.EVERYTHING_IS_OK.getValue());
/* 137 */           output.flush();
/*     */           
/* 139 */           table.addPlayer(new SClient(s, input, output, nick, table));
/* 140 */           table.sendMessageToAll("** Gracz " + nick + " dołączył do gry **");
/*     */           
/* 142 */           if (table.isAllPlayers()) {
/*     */             
/* 144 */             table.generateSettings();
/*     */             
/* 146 */             print("Send settings to all");
/* 147 */             table.sendSettingsToAll();
/*     */             
/* 149 */             table.sendMessageToAll("** Nowa gra, zaczna: " + table.clientPlayer1.nick + "**");
/*     */             
/*     */             continue;
/*     */           } 
/* 153 */           table.sendMessageToAll("** Oczekiwanie na drugiego gracza **");
/*     */ 
/*     */           
/*     */           continue;
/*     */         } 
/*     */ 
/*     */         
/* 160 */         print("join as observer");
/* 161 */         if (!table.canObserversJoin()) {
/*     */           
/* 163 */           print("Observers can't join");
/* 164 */           output.writeInt(ConnectionInfo.ERR_GAME_WITHOUT_OBSERVERS.getValue());
/* 165 */           output.flush();
/*     */           
/*     */           continue;
/*     */         } 
/*     */         
/* 170 */         output.writeInt(ConnectionInfo.EVERYTHING_IS_OK.getValue());
/* 171 */         output.flush();
/*     */         
/* 173 */         table.addObserver(new SClient(s, input, output, nick, table));
/*     */         
/* 175 */         if (table.clientPlayer2 != null)
/*     */         {
/* 177 */           table.sendSettingsAndMovesToNewObserver();
/*     */         }
/*     */         
/* 180 */         table.sendMessageToAll("** Obserwator " + nick + " dołączył do gry **");
/*     */ 
/*     */       
/*     */       }
/* 184 */       catch (IOException ex) {
/*     */         
/* 186 */         LOG.error("IOException: " + ex);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void print(String str) {
/* 194 */     if (isPrintEnable)
/*     */     {
/* 196 */       LOG.debug("Server: " + str);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void newTable(int idTable, String password, boolean withObserver, boolean enableChat) {
/* 203 */     print("create new table - id: " + idTable);
/* 204 */     tables.put(Integer.valueOf(idTable), new Table(password, withObserver, enableChat));
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\server\Server.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */