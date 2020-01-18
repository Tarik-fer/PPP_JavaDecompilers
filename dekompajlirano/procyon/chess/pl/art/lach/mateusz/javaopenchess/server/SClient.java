// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.server;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

class SClient implements Runnable
{
    private Socket s;
    public ObjectInputStream input;
    public ObjectOutputStream output;
    public String nick;
    private Table table;
    protected boolean wait4undoAnswer;
    
    SClient(final Socket s, final ObjectInputStream input, final ObjectOutputStream output, final String nick, final Table table) {
        this.wait4undoAnswer = false;
        this.s = s;
        this.input = input;
        this.output = output;
        this.nick = nick;
        this.table = table;
        final Thread thread = new Thread(this);
        thread.start();
    }
    
    @Override
    public void run() {
        Server.print("running function: run()");
        boolean isOK = true;
        while (isOK) {
            try {
                final String in = this.input.readUTF();
                if (in.equals("#move")) {
                    final int bX = this.input.readInt();
                    final int bY = this.input.readInt();
                    final int eX = this.input.readInt();
                    final int eY = this.input.readInt();
                    final String promoted = this.input.readUTF();
                    this.table.sendMoveToOther(this, bX, bY, eX, eY, promoted);
                }
                else if (in.equals("#message")) {
                    final String str = this.input.readUTF();
                    this.table.sendMessageToAll(this.nick + ": " + str);
                }
                else if (in.equals("#undoAsk") || in.equals("#undoAnswerNegative")) {
                    this.table.sendToAll(this, in);
                }
                else {
                    if (!in.equals("#undoAnswerPositive")) {
                        continue;
                    }
                    this.table.sendUndoToAll(this, in);
                }
            }
            catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                isOK = false;
                try {
                    this.table.sendErrorConnectionToOther(this);
                }
                catch (IOException ex2) {
                    Logger.getLogger(SClient.class.getName()).log(Level.SEVERE, null, ex2);
                }
            }
        }
    }
}
