/*     */ package pl.art.lach.mateusz.javaopenchess.server;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
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
/*     */ public class Table
/*     */ {
/*     */   public SClient clientPlayer1;
/*     */   public SClient clientPlayer2;
/*     */   public ArrayList<SClient> clientObservers;
/*     */   public Settings player1Set;
/*     */   public Settings player2Set;
/*     */   public Settings observerSettings;
/*     */   public String password;
/*     */   private boolean canObserversJoin;
/*     */   private boolean enableChat;
/*     */   private ArrayList<Move> movesList;
/*     */   
/*     */   Table(String password, boolean canObserversJoin, boolean enableChat) {
/*  47 */     this.password = password;
/*  48 */     this.enableChat = enableChat;
/*  49 */     this.canObserversJoin = canObserversJoin;
/*     */     
/*  51 */     if (canObserversJoin)
/*     */     {
/*  53 */       this.clientObservers = new ArrayList<>();
/*     */     }
/*     */     
/*  56 */     this.movesList = new ArrayList<>();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void generateSettings() {
/*  62 */     this.player1Set = new Settings();
/*  63 */     this.player2Set = new Settings();
/*     */     
/*  65 */     this.player1Set.setGameMode(GameModes.NEW_GAME);
/*  66 */     this.player1Set.setPlayerWhite((Player)new HumanPlayer(this.clientPlayer1.nick, Colors.WHITE));
/*  67 */     this.player1Set.setPlayerBlack((Player)new NetworkPlayer(this.clientPlayer2.nick, Colors.BLACK));
/*  68 */     this.player1Set.setGameType(GameTypes.NETWORK);
/*  69 */     this.player1Set.setUpsideDown(false);
/*     */     
/*  71 */     this.player2Set.setGameMode(GameModes.NEW_GAME);
/*  72 */     this.player2Set.setPlayerWhite((Player)new NetworkPlayer(this.clientPlayer1.nick, Colors.WHITE));
/*  73 */     this.player2Set.setPlayerBlack((Player)new HumanPlayer(this.clientPlayer2.nick, Colors.BLACK));
/*  74 */     this.player2Set.setGameType(GameTypes.NETWORK);
/*  75 */     this.player2Set.setUpsideDown(false);
/*     */     
/*  77 */     if (canObserversJoin()) {
/*     */       
/*  79 */       this.observerSettings = new Settings();
/*     */       
/*  81 */       this.observerSettings.setGameMode(GameModes.NEW_GAME);
/*  82 */       this.observerSettings.setPlayerWhite((Player)new NetworkPlayer(this.clientPlayer1.nick, Colors.WHITE));
/*  83 */       this.observerSettings.setPlayerBlack((Player)new NetworkPlayer(this.clientPlayer2.nick, Colors.BLACK));
/*  84 */       this.observerSettings.setGameType(GameTypes.NETWORK);
/*  85 */       this.observerSettings.setUpsideDown(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendSettingsToAll() throws IOException {
/*  92 */     Server.print("running function: sendSettingsToAll()");
/*     */     
/*  94 */     this.clientPlayer1.output.writeUTF("#settings");
/*  95 */     this.clientPlayer1.output.writeObject(this.player1Set);
/*  96 */     this.clientPlayer1.output.flush();
/*     */     
/*  98 */     this.clientPlayer2.output.writeUTF("#settings");
/*  99 */     this.clientPlayer2.output.writeObject(this.player2Set);
/* 100 */     this.clientPlayer2.output.flush();
/*     */     
/* 102 */     if (canObserversJoin())
/*     */     {
/* 104 */       for (SClient observer : this.clientObservers) {
/*     */         
/* 106 */         observer.output.writeUTF("#settings");
/* 107 */         observer.output.writeObject(this.observerSettings);
/* 108 */         observer.output.flush();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendSettingsAndMovesToNewObserver() throws IOException {
/* 117 */     SClient observer = this.clientObservers.get(this.clientObservers.size() - 1);
/*     */     
/* 119 */     observer.output.writeUTF("#settings");
/* 120 */     observer.output.writeObject(this.observerSettings);
/* 121 */     observer.output.flush();
/*     */     
/* 123 */     for (Move m : this.movesList) {
/*     */       
/* 125 */       observer.output.writeUTF("#move");
/* 126 */       observer.output.writeInt(m.bX);
/* 127 */       observer.output.writeInt(m.bY);
/* 128 */       observer.output.writeInt(m.eX);
/* 129 */       observer.output.writeInt(m.eY);
/* 130 */       observer.output.writeUTF(m.promoted);
/*     */     } 
/* 132 */     observer.output.flush();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendMoveToOther(SClient sender, int beginX, int beginY, int endX, int endY, String promoted) throws IOException {
/* 138 */     Server.print("running function: sendMoveToOther(" + sender.nick + ", " + beginX + ", " + beginY + ", " + endX + ", " + endY + ")");
/*     */     
/* 140 */     if (sender == this.clientPlayer1 || sender == this.clientPlayer2) {
/*     */       
/* 142 */       SClient receiver = (this.clientPlayer1 == sender) ? this.clientPlayer2 : this.clientPlayer1;
/* 143 */       receiver.output.writeUTF("#move");
/* 144 */       receiver.output.writeInt(beginX);
/* 145 */       receiver.output.writeInt(beginY);
/* 146 */       receiver.output.writeInt(endX);
/* 147 */       receiver.output.writeInt(endY);
/* 148 */       receiver.output.writeUTF(promoted);
/* 149 */       receiver.output.flush();
/*     */       
/* 151 */       if (canObserversJoin())
/*     */       {
/* 153 */         for (SClient observer : this.clientObservers) {
/*     */           
/* 155 */           observer.output.writeUTF("#move");
/* 156 */           observer.output.writeInt(beginX);
/* 157 */           observer.output.writeInt(beginY);
/* 158 */           observer.output.writeInt(endX);
/* 159 */           observer.output.writeInt(endY);
/* 160 */           observer.output.writeUTF(promoted);
/* 161 */           observer.output.flush();
/*     */         } 
/*     */       }
/*     */       
/* 165 */       this.movesList.add(new Move(beginX, beginY, endX, endY, promoted));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendUndoToAll(SClient sender, String msg) throws IOException {
/* 171 */     if (sender == this.clientPlayer1 || sender == this.clientPlayer2) {
/*     */       
/* 173 */       sendToAll(sender, msg);
/*     */       
/*     */       try {
/* 176 */         this.movesList.remove(this.movesList.size() - 1);
/*     */       }
/* 178 */       catch (ArrayIndexOutOfBoundsException exc) {
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendToAll(SClient sender, String msg) throws IOException {
/* 188 */     if (sender == this.clientPlayer1 || sender == this.clientPlayer2) {
/*     */       
/* 190 */       SClient receiver = (this.clientPlayer1 == sender) ? this.clientPlayer2 : this.clientPlayer1;
/* 191 */       receiver.output.writeUTF(msg);
/* 192 */       receiver.output.flush();
/*     */       
/* 194 */       if (canObserversJoin())
/*     */       {
/* 196 */         for (SClient observer : this.clientObservers) {
/*     */           
/* 198 */           observer.output.writeUTF(msg);
/* 199 */           observer.output.flush();
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendToOtherPlayer(SClient sender, String msg) throws IOException {
/* 208 */     if (sender == this.clientPlayer1 || sender == this.clientPlayer2) {
/*     */       
/* 210 */       SClient receiver = (this.clientPlayer1 == sender) ? this.clientPlayer2 : this.clientPlayer1;
/* 211 */       receiver.output.writeUTF(msg);
/* 212 */       receiver.output.flush();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendErrorConnectionToOther(SClient sender) throws IOException {
/* 220 */     Server.print("running function: sendErrorConnectionToOther(" + sender.nick + ")");
/*     */     
/* 222 */     if (sender == this.clientPlayer1 || sender == this.clientPlayer2) {
/*     */       
/* 224 */       if (this.clientPlayer1 != sender) {
/*     */         
/* 226 */         this.clientPlayer1.output.writeUTF("#errorConnection");
/* 227 */         this.clientPlayer1.output.flush();
/*     */       } 
/* 229 */       if (this.clientPlayer2 != sender) {
/*     */         
/* 231 */         this.clientPlayer2.output.writeUTF("#errorConnection");
/* 232 */         this.clientPlayer2.output.flush();
/*     */       } 
/*     */       
/* 235 */       if (canObserversJoin())
/*     */       {
/* 237 */         for (SClient observer : this.clientObservers) {
/*     */           
/* 239 */           observer.output.writeUTF("#errorConnection");
/* 240 */           observer.output.flush();
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendMessageToAll(String str) throws IOException {
/* 248 */     Server.print("running function: sendMessageToAll(" + str + ")");
/*     */     
/* 250 */     if (this.clientPlayer1 != null) {
/*     */       
/* 252 */       this.clientPlayer1.output.writeUTF("#message");
/* 253 */       this.clientPlayer1.output.writeUTF(str);
/* 254 */       this.clientPlayer1.output.flush();
/*     */     } 
/*     */     
/* 257 */     if (this.clientPlayer2 != null) {
/*     */       
/* 259 */       this.clientPlayer2.output.writeUTF("#message");
/* 260 */       this.clientPlayer2.output.writeUTF(str);
/* 261 */       this.clientPlayer2.output.flush();
/*     */     } 
/*     */     
/* 264 */     if (canObserversJoin())
/*     */     {
/* 266 */       for (SClient observer : this.clientObservers) {
/*     */         
/* 268 */         observer.output.writeUTF("#message");
/* 269 */         observer.output.writeUTF(str);
/* 270 */         observer.output.flush();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAllPlayers() {
/* 278 */     if (this.clientPlayer1 == null || this.clientPlayer2 == null)
/*     */     {
/* 280 */       return false;
/*     */     }
/* 282 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 288 */   public boolean isObservers() { return !this.clientObservers.isEmpty(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 294 */   public boolean canObserversJoin() { return this.canObserversJoin; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addPlayer(SClient client) {
/* 300 */     if (this.clientPlayer1 == null) {
/*     */       
/* 302 */       this.clientPlayer1 = client;
/* 303 */       Server.print("Player1 connected");
/*     */     }
/* 305 */     else if (this.clientPlayer2 == null) {
/*     */       
/* 307 */       this.clientPlayer2 = client;
/* 308 */       Server.print("Player2 connected");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 315 */   public void addObserver(SClient client) { this.clientObservers.add(client); }
/*     */ 
/*     */   
/*     */   private class Move
/*     */   {
/*     */     int bX;
/*     */     
/*     */     int bY;
/*     */     
/*     */     int eX;
/*     */     
/*     */     int eY;
/*     */     
/*     */     String promoted;
/*     */ 
/*     */     
/*     */     Move(int bX, int bY, int eX, int eY, String promoted) {
/* 332 */       this.bX = bX;
/* 333 */       this.bY = bY;
/* 334 */       this.eX = eX;
/* 335 */       this.eY = eY;
/* 336 */       this.promoted = promoted;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\server\Table.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */