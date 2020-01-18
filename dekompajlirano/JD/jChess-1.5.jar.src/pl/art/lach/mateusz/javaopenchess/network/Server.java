/*     */ package pl.art.lach.mateusz.javaopenchess.network;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.net.ServerSocket;
/*     */ import java.net.Socket;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.log4j.Logger;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Colors;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.players.Player;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.players.implementation.HumanPlayer;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.players.implementation.NetworkPlayer;
/*     */ import pl.art.lach.mateusz.javaopenchess.utils.GameModes;
/*     */ import pl.art.lach.mateusz.javaopenchess.utils.GameTypes;
/*     */ import pl.art.lach.mateusz.javaopenchess.utils.Settings;
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
/*     */ 
/*     */ public class Server
/*     */   implements Runnable
/*     */ {
/*  44 */   private static final Logger LOG = Logger.getLogger(Server.class);
/*     */   
/*     */   public static boolean isPrintEnable = true;
/*     */   
/*     */   private static Map<Integer, Table> tables;
/*  49 */   public static int port = 4449;
/*     */   private static ServerSocket ss;
/*     */   private static boolean isRunning = false;
/*     */   
/*     */   public enum connection_info
/*     */   {
/*  55 */     all_is_ok(0),
/*  56 */     err_bad_table_ID(1),
/*  57 */     err_table_is_full(2),
/*  58 */     err_game_without_observer(3),
/*  59 */     err_bad_password(4);
/*     */ 
/*     */     
/*     */     private int value;
/*     */ 
/*     */     
/*  65 */     connection_info(int value) { this.value = value; }
/*     */ 
/*     */ 
/*     */     
/*     */     public static connection_info get(int id) {
/*  70 */       switch (id) {
/*     */         
/*     */         case 0:
/*  73 */           return all_is_ok;
/*     */         case 1:
/*  75 */           return err_bad_table_ID;
/*     */         case 2:
/*  77 */           return err_table_is_full;
/*     */         case 3:
/*  79 */           return err_game_without_observer;
/*     */         case 4:
/*  81 */           return err_bad_password;
/*     */       } 
/*  83 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  89 */     public int getValue() { return this.value; }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Server() {
/*  95 */     if (!isRunning) {
/*     */       
/*  97 */       runServer();
/*     */       
/*  99 */       Thread thread = new Thread(this);
/* 100 */       thread.start();
/*     */       
/* 102 */       isRunning = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 112 */   public static boolean isRunning() { return isRunning; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void runServer() {
/*     */     try {
/* 124 */       ss = new ServerSocket(port);
/* 125 */       print("running");
/*     */     }
/* 127 */     catch (IOException ex) {
/*     */       
/* 129 */       LOG.error("runServer/IOException: " + ex);
/*     */     } 
/*     */     
/* 132 */     tables = new HashMap<>();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/* 138 */     print("listening port: " + port);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     while (true) {
/*     */       try {
/* 147 */         Socket s = ss.accept();
/* 148 */         ObjectInputStream input = new ObjectInputStream(s.getInputStream());
/* 149 */         ObjectOutputStream output = new ObjectOutputStream(s.getOutputStream());
/*     */         
/* 151 */         print("new connection");
/*     */ 
/*     */         
/* 154 */         int tableID = input.readInt();
/* 155 */         print("readed table ID: " + tableID);
/* 156 */         boolean joinAsPlayer = input.readBoolean();
/* 157 */         print("readed joinAsPlayer: " + joinAsPlayer);
/* 158 */         String nick = input.readUTF();
/* 159 */         print("readed nick: " + nick);
/* 160 */         String password = input.readUTF();
/* 161 */         print("readed password: " + password);
/*     */ 
/*     */         
/* 164 */         if (!tables.containsKey(Integer.valueOf(tableID))) {
/*     */           
/* 166 */           print("bad table ID");
/* 167 */           output.writeInt(connection_info.err_bad_table_ID.getValue());
/* 168 */           output.flush();
/*     */           continue;
/*     */         } 
/* 171 */         Table table = tables.get(Integer.valueOf(tableID));
/*     */         
/* 173 */         if (!table.password.equals(password)) {
/*     */           
/* 175 */           print("bad password");
/* 176 */           output.writeInt(connection_info.err_bad_password.getValue());
/* 177 */           output.flush();
/*     */           
/*     */           continue;
/*     */         } 
/* 181 */         if (joinAsPlayer) {
/*     */           
/* 183 */           print("join as player");
/* 184 */           if (table.isAllPlayers()) {
/*     */             
/* 186 */             print("error: was all players at this table");
/* 187 */             output.writeInt(connection_info.err_table_is_full.getValue());
/* 188 */             output.flush();
/*     */             
/*     */             continue;
/*     */           } 
/* 192 */           print("wasn't all players at this table");
/*     */           
/* 194 */           output.writeInt(connection_info.all_is_ok.getValue());
/* 195 */           output.flush();
/*     */           
/* 197 */           table.addPlayer(new Client(s, input, output, nick, table));
/* 198 */           table.sendMessageToAll("** Gracz " + nick + " dołączył do gry **");
/*     */           
/* 200 */           if (table.isAllPlayers()) {
/*     */             
/* 202 */             table.generateSettings();
/*     */             
/* 204 */             print("Send settings to all");
/* 205 */             table.sendSettingsToAll();
/*     */             
/* 207 */             table.sendMessageToAll("** Nowa gra, zaczna: " + table.clientPlayer1.nick + "**");
/*     */             
/*     */             continue;
/*     */           } 
/* 211 */           table.sendMessageToAll("** Oczekiwanie na drugiego gracza **");
/*     */ 
/*     */           
/*     */           continue;
/*     */         } 
/*     */         
/* 217 */         print("join as observer");
/* 218 */         if (!table.canObserversJoin()) {
/*     */           
/* 220 */           print("Observers can't join");
/* 221 */           output.writeInt(connection_info.err_game_without_observer.getValue());
/* 222 */           output.flush();
/*     */           
/*     */           continue;
/*     */         } 
/* 226 */         output.writeInt(connection_info.all_is_ok.getValue());
/* 227 */         output.flush();
/*     */         
/* 229 */         table.addObserver(new Client(s, input, output, nick, table));
/*     */         
/* 231 */         if (table.clientPlayer2 != null)
/*     */         {
/* 233 */           table.sendSettingsAndMovesToNewObserver();
/*     */         }
/*     */         
/* 236 */         table.sendMessageToAll("** Obserwator " + nick + " dołączył do gry **");
/*     */ 
/*     */       
/*     */       }
/* 240 */       catch (IOException ex) {
/*     */         
/* 242 */         LOG.error("runServer/IOException: " + ex);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void print(String str) {
/* 253 */     if (isPrintEnable)
/*     */     {
/* 255 */       LOG.debug("Server: " + str);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void newTable(int idTable, String password, boolean withObserver, boolean enableChat) {
/* 268 */     print("create new table - id: " + idTable);
/* 269 */     tables.put(Integer.valueOf(idTable), new Table(password, withObserver, enableChat));
/*     */   }
/*     */ 
/*     */   
/*     */   private class Table
/*     */   {
/*     */     public Server.Client clientPlayer1;
/*     */     
/*     */     public Server.Client clientPlayer2;
/*     */     
/*     */     public ArrayList<Server.Client> clientObservers;
/*     */     
/*     */     public Settings player1Set;
/*     */     
/*     */     public Settings player2Set;
/*     */     public Settings observerSettings;
/*     */     public String password;
/*     */     private boolean canObserversJoin;
/*     */     private boolean enableChat;
/*     */     private ArrayList<Move> movesList;
/*     */     
/*     */     Table(String password, boolean canObserversJoin, boolean enableChat) {
/* 291 */       this.password = password;
/* 292 */       this.enableChat = enableChat;
/* 293 */       this.canObserversJoin = canObserversJoin;
/*     */       
/* 295 */       if (canObserversJoin)
/*     */       {
/* 297 */         this.clientObservers = new ArrayList<>();
/*     */       }
/*     */       
/* 300 */       this.movesList = new ArrayList<>();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void generateSettings() {
/* 306 */       this.player1Set = new Settings();
/* 307 */       this.player2Set = new Settings();
/*     */       
/* 309 */       this.player1Set.setGameMode(GameModes.NEW_GAME);
/* 310 */       this.player1Set.setPlayerWhite((Player)new HumanPlayer(this.clientPlayer1.nick, Colors.WHITE));
/* 311 */       this.player1Set.setPlayerBlack((Player)new NetworkPlayer(this.clientPlayer2.nick, Colors.BLACK));
/* 312 */       this.player1Set.setGameType(GameTypes.NETWORK);
/* 313 */       this.player1Set.setUpsideDown(false);
/*     */       
/* 315 */       this.player2Set.setGameMode(GameModes.NEW_GAME);
/* 316 */       this.player2Set.setPlayerWhite((Player)new NetworkPlayer(this.clientPlayer1.nick, Colors.WHITE));
/* 317 */       this.player2Set.setPlayerBlack((Player)new HumanPlayer(this.clientPlayer2.nick, Colors.BLACK));
/* 318 */       this.player2Set.setGameType(GameTypes.NETWORK);
/* 319 */       this.player2Set.setUpsideDown(false);
/*     */       
/* 321 */       if (canObserversJoin()) {
/*     */         
/* 323 */         this.observerSettings = new Settings();
/* 324 */         this.observerSettings.setGameMode(GameModes.NEW_GAME);
/* 325 */         this.observerSettings.setPlayerWhite((Player)new NetworkPlayer(this.clientPlayer1.nick, Colors.BLACK));
/* 326 */         this.observerSettings.setPlayerBlack((Player)new NetworkPlayer(this.clientPlayer2.nick, Colors.BLACK));
/* 327 */         this.observerSettings.setGameType(GameTypes.NETWORK);
/* 328 */         this.observerSettings.setUpsideDown(false);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void sendSettingsToAll() throws IOException {
/* 334 */       Server.print("running function: sendSettingsToAll()");
/*     */       
/* 336 */       this.clientPlayer1.output.writeUTF("#settings");
/* 337 */       this.clientPlayer1.output.writeObject(this.player1Set);
/* 338 */       this.clientPlayer1.output.flush();
/*     */       
/* 340 */       this.clientPlayer2.output.writeUTF("#settings");
/* 341 */       this.clientPlayer2.output.writeObject(this.player2Set);
/* 342 */       this.clientPlayer2.output.flush();
/*     */       
/* 344 */       if (canObserversJoin())
/*     */       {
/* 346 */         for (Server.Client observer : this.clientObservers) {
/*     */           
/* 348 */           observer.output.writeUTF("#settings");
/* 349 */           observer.output.writeObject(this.observerSettings);
/* 350 */           observer.output.flush();
/*     */         } 
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void sendSettingsAndMovesToNewObserver() throws IOException {
/* 359 */       Server.Client observer = this.clientObservers.get(this.clientObservers.size() - 1);
/*     */       
/* 361 */       observer.output.writeUTF("#settings");
/* 362 */       observer.output.writeObject(this.observerSettings);
/* 363 */       observer.output.flush();
/*     */       
/* 365 */       for (Move m : this.movesList) {
/*     */         
/* 367 */         observer.output.writeUTF("#move");
/* 368 */         observer.output.writeInt(m.bX);
/* 369 */         observer.output.writeInt(m.bY);
/* 370 */         observer.output.writeInt(m.eX);
/* 371 */         observer.output.writeInt(m.eY);
/* 372 */         observer.output.writeUTF((m.promoted != null) ? m.promoted : "");
/*     */       } 
/* 374 */       observer.output.flush();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void sendMoveToOther(Server.Client sender, int beginX, int beginY, int endX, int endY, String promoted) throws IOException {
/* 380 */       Server.print("running function: sendMoveToOther(" + sender.nick + ", " + beginX + ", " + beginY + ", " + endX + ", " + endY + ")");
/*     */       
/* 382 */       if (sender == this.clientPlayer1 || sender == this.clientPlayer2) {
/*     */ 
/*     */         
/* 385 */         if (this.clientPlayer1 != sender) {
/*     */           
/* 387 */           this.clientPlayer1.output.writeUTF("#move");
/* 388 */           this.clientPlayer1.output.writeInt(beginX);
/* 389 */           this.clientPlayer1.output.writeInt(beginY);
/* 390 */           this.clientPlayer1.output.writeInt(endX);
/* 391 */           this.clientPlayer1.output.writeInt(endY);
/* 392 */           this.clientPlayer1.output.writeUTF((promoted != null) ? promoted : "");
/* 393 */           this.clientPlayer1.output.flush();
/*     */         } 
/* 395 */         if (this.clientPlayer2 != sender) {
/*     */           
/* 397 */           this.clientPlayer2.output.writeUTF("#move");
/* 398 */           this.clientPlayer2.output.writeInt(beginX);
/* 399 */           this.clientPlayer2.output.writeInt(beginY);
/* 400 */           this.clientPlayer2.output.writeInt(endX);
/* 401 */           this.clientPlayer2.output.writeInt(endY);
/* 402 */           this.clientPlayer2.output.writeUTF((promoted != null) ? promoted : "");
/* 403 */           this.clientPlayer2.output.flush();
/*     */         } 
/*     */         
/* 406 */         if (canObserversJoin())
/*     */         {
/* 408 */           for (Server.Client observer : this.clientObservers) {
/*     */             
/* 410 */             observer.output.writeUTF("#move");
/* 411 */             observer.output.writeInt(beginX);
/* 412 */             observer.output.writeInt(beginY);
/* 413 */             observer.output.writeInt(endX);
/* 414 */             observer.output.writeInt(endY);
/* 415 */             observer.output.writeUTF((promoted != null) ? promoted : "");
/* 416 */             observer.output.flush();
/*     */           } 
/*     */         }
/*     */         
/* 420 */         this.movesList.add(new Move(beginX, beginY, endX, endY, promoted));
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void sendMessageToAll(String str) throws IOException {
/* 426 */       Server.print("running function: sendMessageToAll(" + str + ")");
/*     */       
/* 428 */       if (this.clientPlayer1 != null) {
/*     */         
/* 430 */         this.clientPlayer1.output.writeUTF("#message");
/* 431 */         this.clientPlayer1.output.writeUTF(str);
/* 432 */         this.clientPlayer1.output.flush();
/*     */       } 
/*     */       
/* 435 */       if (this.clientPlayer2 != null) {
/*     */         
/* 437 */         this.clientPlayer2.output.writeUTF("#message");
/* 438 */         this.clientPlayer2.output.writeUTF(str);
/* 439 */         this.clientPlayer2.output.flush();
/*     */       } 
/*     */       
/* 442 */       if (canObserversJoin())
/*     */       {
/* 444 */         for (Server.Client observer : this.clientObservers) {
/*     */           
/* 446 */           observer.output.writeUTF("#message");
/* 447 */           observer.output.writeUTF(str);
/* 448 */           observer.output.flush();
/*     */         } 
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isAllPlayers() {
/* 455 */       if (this.clientPlayer1 == null || this.clientPlayer2 == null)
/*     */       {
/* 457 */         return false;
/*     */       }
/* 459 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 464 */     public boolean isObservers() { return !this.clientObservers.isEmpty(); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 469 */     public boolean canObserversJoin() { return this.canObserversJoin; }
/*     */ 
/*     */ 
/*     */     
/*     */     public void addPlayer(Server.Client client) {
/* 474 */       if (this.clientPlayer1 == null) {
/*     */         
/* 476 */         this.clientPlayer1 = client;
/* 477 */         Server.print("Player1 connected");
/*     */       }
/* 479 */       else if (this.clientPlayer2 == null) {
/*     */         
/* 481 */         this.clientPlayer2 = client;
/* 482 */         Server.print("Player2 connected");
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 488 */     public void addObserver(Server.Client client) { this.clientObservers.add(client); }
/*     */ 
/*     */     
/*     */     private class Move
/*     */     {
/*     */       int bX;
/*     */       
/*     */       int bY;
/*     */       int eX;
/*     */       int eY;
/*     */       String promoted;
/*     */       
/*     */       Move(int bX, int bY, int eX, int eY, String promoted) {
/* 501 */         this.bX = bX;
/* 502 */         this.bY = bY;
/* 503 */         this.eX = eX;
/* 504 */         this.eY = eY;
/* 505 */         this.promoted = promoted;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private class Client
/*     */     implements Runnable
/*     */   {
/*     */     private Socket s;
/*     */     public ObjectInputStream input;
/*     */     public ObjectOutputStream output;
/*     */     public String nick;
/*     */     private Server.Table table;
/*     */     
/*     */     Client(Socket s, ObjectInputStream input, ObjectOutputStream output, String nick, Server.Table table) {
/* 520 */       this.s = s;
/* 521 */       this.input = input;
/* 522 */       this.output = output;
/* 523 */       this.nick = nick;
/* 524 */       this.table = table;
/*     */       
/* 526 */       Thread thread = new Thread(this);
/* 527 */       thread.start();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void run() {
/* 533 */       Server.print("running function: run()");
/*     */ 
/*     */       
/*     */       while (true) {
/*     */         try {
/* 538 */           String in = this.input.readUTF();
/*     */           
/* 540 */           if (in.equals("#move")) {
/*     */             
/* 542 */             int bX = this.input.readInt();
/* 543 */             int bY = this.input.readInt();
/* 544 */             int eX = this.input.readInt();
/* 545 */             int eY = this.input.readInt();
/* 546 */             String promoted = this.input.readUTF();
/* 547 */             this.table.sendMoveToOther(this, bX, bY, eX, eY, promoted);
/*     */           } 
/* 549 */           if (in.equals("#message"))
/*     */           {
/* 551 */             String str = this.input.readUTF();
/*     */             
/* 553 */             this.table.sendMessageToAll(this.nick + ": " + str);
/*     */           }
/*     */         
/* 556 */         } catch (IOException ex) {
/*     */           
/* 558 */           LOG.error("private Client/IOException: " + ex);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\network\Server.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */