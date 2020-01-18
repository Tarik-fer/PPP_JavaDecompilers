/*     */ package pl.art.lach.mateusz.javaopenchess.network;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.net.ConnectException;
/*     */ import java.net.Socket;
/*     */ import java.net.UnknownHostException;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JTabbedPane;
/*     */ import org.apache.log4j.Logger;
/*     */ import pl.art.lach.mateusz.javaopenchess.JChessApp;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Game;
/*     */ import pl.art.lach.mateusz.javaopenchess.display.windows.JChessTabbedPane;
/*     */ import pl.art.lach.mateusz.javaopenchess.server.ConnectionInfo;
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
/*     */ 
/*     */ 
/*     */ public class Client
/*     */   implements Runnable
/*     */ {
/*  44 */   private static final Logger LOG = Logger.getLogger(Client.class);
/*     */   
/*     */   public static boolean isPrintEnable = true;
/*     */   
/*     */   protected Socket socket;
/*     */   
/*     */   protected ObjectOutputStream output;
/*     */   
/*     */   protected ObjectInputStream input;
/*     */   
/*     */   protected String ip;
/*     */   
/*     */   protected int port;
/*     */   
/*     */   protected Game game;
/*     */   
/*     */   protected Settings settings;
/*     */   
/*     */   protected boolean wait4undoAnswer = false;
/*     */   
/*     */   protected boolean isObserver = false;
/*     */ 
/*     */   
/*     */   public Client(String ip, int port) {
/*  68 */     print("running");
/*     */     
/*  70 */     this.ip = ip;
/*  71 */     this.port = port;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean join(int tableID, boolean asPlayer, String nick, String password) throws Error {
/*  79 */     print("running function: join(" + tableID + ", " + asPlayer + ", " + nick + ")");
/*     */     
/*     */     try {
/*  82 */       print("join to server: ip:" + getIp() + " port:" + getPort());
/*  83 */       setIsObserver(!asPlayer);
/*     */       
/*     */       try {
/*  86 */         setSocket(new Socket(getIp(), getPort()));
/*  87 */         setOutput(new ObjectOutputStream(getSocket().getOutputStream()));
/*  88 */         setInput(new ObjectInputStream(getSocket().getInputStream()));
/*     */         
/*  90 */         print("send to server: table ID");
/*  91 */         getOutput().writeInt(tableID);
/*  92 */         print("send to server: player or observer");
/*  93 */         getOutput().writeBoolean(asPlayer);
/*  94 */         print("send to server: player nick");
/*  95 */         getOutput().writeUTF(nick);
/*  96 */         print("send to server: password");
/*  97 */         getOutput().writeUTF(password);
/*  98 */         getOutput().flush();
/*     */         
/* 100 */         int servCode = getInput().readInt();
/* 101 */         print("connection info: " + ConnectionInfo.get(servCode).name());
/* 102 */         if (ConnectionInfo.get(servCode).name().startsWith("err_"))
/*     */         {
/* 104 */           throw new Error(ConnectionInfo.get(servCode).name());
/*     */         }
/* 106 */         if (servCode == ConnectionInfo.EVERYTHING_IS_OK.getValue())
/*     */         {
/* 108 */           return true;
/*     */         }
/*     */ 
/*     */         
/* 112 */         return false;
/*     */       
/*     */       }
/* 115 */       catch (Error err) {
/*     */         
/* 117 */         LOG.error("Error exception, message: " + err.getMessage());
/* 118 */         return false;
/*     */       }
/* 120 */       catch (ConnectException ex) {
/*     */         
/* 122 */         LOG.error("ConnectException, message: " + ex.getMessage() + " object: " + ex);
/* 123 */         return false;
/*     */       }
/*     */     
/*     */     }
/* 127 */     catch (UnknownHostException ex) {
/*     */       
/* 129 */       LOG.error("UnknownHostException, message: " + ex.getMessage() + " object: " + ex);
/* 130 */       return false;
/*     */     }
/* 132 */     catch (IOException ex) {
/*     */       
/* 134 */       LOG.error("UIOException, message: " + ex.getMessage() + " object: " + ex);
/* 135 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/* 143 */     print("running function: run()");
/* 144 */     boolean isOK = true;
/* 145 */     while (isOK) {
/*     */ 
/*     */       
/*     */       try {
/* 149 */         String in = getInput().readUTF();
/* 150 */         print("input code: " + in);
/*     */         
/* 152 */         if (in.equals("#move")) {
/*     */           
/* 154 */           int beginX = getInput().readInt();
/* 155 */           int beginY = getInput().readInt();
/* 156 */           int endX = getInput().readInt();
/* 157 */           int endY = getInput().readInt();
/* 158 */           String promoted = getInput().readUTF();
/* 159 */           getGame().simulateMove(beginX, beginY, endX, endY, promoted);
/* 160 */           int tabNumber = JChessApp.getJavaChessView().getTabNumber(getGame());
/* 161 */           JTabbedPane gamesPane = JChessApp.getJavaChessView().getGamesPane();
/* 162 */           gamesPane.setForegroundAt(tabNumber, JChessTabbedPane.EVENT_COLOR);
/* 163 */           gamesPane.repaint(); continue;
/*     */         } 
/* 165 */         if (in.equals("#message")) {
/*     */           
/* 167 */           String str = getInput().readUTF();
/* 168 */           getGame().getChat().addMessage(str); continue;
/*     */         } 
/* 170 */         if (in.equals("#settings")) {
/*     */ 
/*     */           
/*     */           try {
/* 174 */             setSettings((Settings)getInput().readObject());
/*     */           }
/* 176 */           catch (ClassNotFoundException ex) {
/*     */             
/* 178 */             LOG.error("ClassNotFoundException, message: " + ex.getMessage() + " object: " + ex);
/*     */           } 
/*     */           
/* 181 */           getGame().setSettings(getSettings());
/* 182 */           getGame().setClient(this);
/* 183 */           getGame().getChat().setClient(this);
/* 184 */           getGame().newGame();
/* 185 */           getGame().getChessboard().repaint(); continue;
/*     */         } 
/* 187 */         if (in.equals("#errorConnection")) {
/*     */           
/* 189 */           getGame().getChat().addMessage("** " + Settings.lang("error_connecting_one_of_player") + " **"); continue;
/*     */         } 
/* 191 */         if (in.equals("#undoAsk") && !isIsObserver()) {
/*     */           
/* 193 */           int result = JOptionPane.showConfirmDialog(null, 
/*     */               
/* 195 */               Settings.lang("your_oponent_plase_to_undo_move_do_you_agree"), 
/* 196 */               Settings.lang("confirm_undo_move"), 0);
/*     */ 
/*     */ 
/*     */           
/* 200 */           if (result == 0) {
/*     */             
/* 202 */             getGame().getChessboard().undo();
/* 203 */             getGame().switchActive();
/* 204 */             sendUndoAnswerPositive();
/*     */             
/*     */             continue;
/*     */           } 
/* 208 */           sendUndoAnswerNegative();
/*     */           continue;
/*     */         } 
/* 211 */         if (in.equals("#undoAnswerPositive") && (isWait4undoAnswer() || isIsObserver())) {
/*     */           
/* 213 */           setWait4undoAnswer(false);
/* 214 */           String lastMove = getGame().getMoves().getMoves().get(getGame().getMoves().getMoves().size() - 1);
/* 215 */           getGame().getChat().addMessage("** " + Settings.lang("permision_ok_4_undo_move") + ": " + lastMove + "**");
/* 216 */           getGame().getChessboard().undo(); continue;
/*     */         } 
/* 218 */         if (in.equals("#undoAnswerNegative") && isWait4undoAnswer())
/*     */         {
/* 220 */           getGame().getChat().addMessage(Settings.lang("no_permision_4_undo_move"));
/*     */         }
/*     */       }
/* 223 */       catch (IOException ex) {
/*     */         
/* 225 */         isOK = false;
/* 226 */         getGame().getChat().addMessage("** " + Settings.lang("error_connecting_to_server") + " **");
/* 227 */         LOG.error("IOException, message: " + ex.getMessage() + " object: " + ex);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void print(String str) {
/* 236 */     if (isPrintEnable)
/*     */     {
/* 238 */       LOG.debug("Client: " + str);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendMove(int beginX, int beginY, int endX, int endY, String promotedPiece) {
/* 246 */     print("running function: sendMove(" + beginX + ", " + beginY + ", " + endX + ", " + endY + ")");
/*     */     
/*     */     try {
/* 249 */       getOutput().writeUTF("#move");
/* 250 */       getOutput().writeInt(beginX);
/* 251 */       getOutput().writeInt(beginY);
/* 252 */       getOutput().writeInt(endX);
/* 253 */       getOutput().writeInt(endY);
/* 254 */       getOutput().writeUTF((promotedPiece != null) ? promotedPiece : "");
/* 255 */       getOutput().flush();
/*     */     }
/* 257 */     catch (IOException ex) {
/*     */       
/* 259 */       LOG.error("IOException, message: " + ex.getMessage() + " object: " + ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendUndoAsk() {
/* 265 */     print("sendUndoAsk");
/*     */     
/*     */     try {
/* 268 */       setWait4undoAnswer(true);
/* 269 */       getOutput().writeUTF("#undoAsk");
/* 270 */       getOutput().flush();
/*     */     }
/* 272 */     catch (IOException ex) {
/*     */       
/* 274 */       LOG.error("IOException, message: " + ex.getMessage() + " object: " + ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendUndoAnswerPositive() {
/*     */     try {
/* 282 */       getOutput().writeUTF("#undoAnswerPositive");
/* 283 */       getOutput().flush();
/*     */     }
/* 285 */     catch (IOException ex) {
/*     */       
/* 287 */       LOG.error("IOException, message: " + ex.getMessage() + " object: " + ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendUndoAnswerNegative() {
/*     */     try {
/* 295 */       getOutput().writeUTF("#undoAnswerNegative");
/* 296 */       getOutput().flush();
/*     */     }
/* 298 */     catch (IOException ex) {
/*     */       
/* 300 */       LOG.error("IOException, message: " + ex.getMessage() + " object: " + ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendMassage(String str) {
/* 309 */     print("running function: sendMessage(" + str + ")");
/*     */     
/*     */     try {
/* 312 */       getOutput().writeUTF("#message");
/* 313 */       getOutput().writeUTF(str);
/* 314 */       getOutput().flush();
/*     */     }
/* 316 */     catch (IOException ex) {
/*     */       
/* 318 */       LOG.error("IOException, message: " + ex.getMessage() + " object: " + ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 327 */   public Game getGame() { return this.game; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 335 */   public void setGame(Game game) { this.game = game; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 343 */   public Settings getSettings() { return this.settings; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 351 */   public void setSettings(Settings settings) { this.settings = settings; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 359 */   public Socket getSocket() { return this.socket; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 367 */   public void setSocket(Socket socket) { this.socket = socket; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 375 */   public ObjectOutputStream getOutput() { return this.output; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 383 */   public void setOutput(ObjectOutputStream output) { this.output = output; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 391 */   public ObjectInputStream getInput() { return this.input; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 399 */   public void setInput(ObjectInputStream input) { this.input = input; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 407 */   public String getIp() { return this.ip; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 415 */   public void setIp(String ip) { this.ip = ip; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 423 */   public int getPort() { return this.port; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 431 */   public void setPort(int port) { this.port = port; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 439 */   public boolean isWait4undoAnswer() { return this.wait4undoAnswer; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 447 */   public void setWait4undoAnswer(boolean wait4undoAnswer) { this.wait4undoAnswer = wait4undoAnswer; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 455 */   public boolean isIsObserver() { return this.isObserver; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 463 */   public void setIsObserver(boolean isObserver) { this.isObserver = isObserver; }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\network\Client.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */