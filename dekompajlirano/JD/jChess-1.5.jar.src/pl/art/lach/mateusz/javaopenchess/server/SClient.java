/*     */ package pl.art.lach.mateusz.javaopenchess.server;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.net.Socket;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
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
/*     */ 
/*     */ 
/*     */ class SClient
/*     */   implements Runnable
/*     */ {
/*     */   private Socket s;
/*     */   public ObjectInputStream input;
/*     */   public ObjectOutputStream output;
/*     */   public String nick;
/*     */   private Table table;
/*     */   protected boolean wait4undoAnswer = false;
/*     */   
/*     */   SClient(Socket s, ObjectInputStream input, ObjectOutputStream output, String nick, Table table) {
/*  46 */     this.s = s;
/*  47 */     this.input = input;
/*  48 */     this.output = output;
/*  49 */     this.nick = nick;
/*  50 */     this.table = table;
/*     */     
/*  52 */     Thread thread = new Thread(this);
/*  53 */     thread.start();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/*  59 */     Server.print("running function: run()");
/*  60 */     boolean isOK = true;
/*  61 */     while (isOK) {
/*     */ 
/*     */       
/*     */       try {
/*  65 */         String in = this.input.readUTF();
/*     */         
/*  67 */         if (in.equals("#move")) {
/*     */           
/*  69 */           int bX = this.input.readInt();
/*  70 */           int bY = this.input.readInt();
/*  71 */           int eX = this.input.readInt();
/*  72 */           int eY = this.input.readInt();
/*  73 */           String promoted = this.input.readUTF();
/*  74 */           this.table.sendMoveToOther(this, bX, bY, eX, eY, promoted); continue;
/*     */         } 
/*  76 */         if (in.equals("#message")) {
/*     */           
/*  78 */           String str = this.input.readUTF();
/*     */           
/*  80 */           this.table.sendMessageToAll(this.nick + ": " + str); continue;
/*     */         } 
/*  82 */         if (in.equals("#undoAsk") || in.equals("#undoAnswerNegative")) {
/*     */           
/*  84 */           this.table.sendToAll(this, in); continue;
/*     */         } 
/*  86 */         if (in.equals("#undoAnswerPositive"))
/*     */         {
/*  88 */           this.table.sendUndoToAll(this, in);
/*     */         }
/*     */       }
/*  91 */       catch (IOException ex) {
/*     */         
/*  93 */         Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
/*  94 */         isOK = false;
/*     */         
/*     */         try {
/*  97 */           this.table.sendErrorConnectionToOther(this);
/*     */         }
/*  99 */         catch (IOException ex1) {
/*     */           
/* 101 */           Logger.getLogger(SClient.class.getName()).log(Level.SEVERE, null, ex1);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\server\SClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */