// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.network;

import javax.swing.JTabbedPane;
import java.awt.Component;
import javax.swing.JOptionPane;
import pl.art.lach.mateusz.javaopenchess.display.windows.JChessTabbedPane;
import pl.art.lach.mateusz.javaopenchess.JChessApp;
import java.io.IOException;
import java.net.UnknownHostException;
import java.net.ConnectException;
import pl.art.lach.mateusz.javaopenchess.server.ConnectionInfo;
import pl.art.lach.mateusz.javaopenchess.utils.Settings;
import pl.art.lach.mateusz.javaopenchess.core.Game;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import org.apache.log4j.Logger;

public class Client implements Runnable
{
    private static final Logger LOG;
    public static boolean isPrintEnable;
    protected Socket socket;
    protected ObjectOutputStream output;
    protected ObjectInputStream input;
    protected String ip;
    protected int port;
    protected Game game;
    protected Settings settings;
    protected boolean wait4undoAnswer;
    protected boolean isObserver;
    
    public Client(final String ip, final int port) {
        this.wait4undoAnswer = false;
        this.isObserver = false;
        print("running");
        this.ip = ip;
        this.port = port;
    }
    
    public boolean join(final int tableID, final boolean asPlayer, final String nick, final String password) throws Error {
        print("running function: join(" + tableID + ", " + asPlayer + ", " + nick + ")");
        try {
            print("join to server: ip:" + this.getIp() + " port:" + this.getPort());
            this.setIsObserver(!asPlayer);
            try {
                this.setSocket(new Socket(this.getIp(), this.getPort()));
                this.setOutput(new ObjectOutputStream(this.getSocket().getOutputStream()));
                this.setInput(new ObjectInputStream(this.getSocket().getInputStream()));
                print("send to server: table ID");
                this.getOutput().writeInt(tableID);
                print("send to server: player or observer");
                this.getOutput().writeBoolean(asPlayer);
                print("send to server: player nick");
                this.getOutput().writeUTF(nick);
                print("send to server: password");
                this.getOutput().writeUTF(password);
                this.getOutput().flush();
                final int servCode = this.getInput().readInt();
                print("connection info: " + ConnectionInfo.get(servCode).name());
                if (ConnectionInfo.get(servCode).name().startsWith("err_")) {
                    throw new Error(ConnectionInfo.get(servCode).name());
                }
                return servCode == ConnectionInfo.EVERYTHING_IS_OK.getValue();
            }
            catch (Error err) {
                Client.LOG.error("Error exception, message: " + err.getMessage());
                return false;
            }
            catch (ConnectException ex) {
                Client.LOG.error("ConnectException, message: " + ex.getMessage() + " object: " + ex);
                return false;
            }
        }
        catch (UnknownHostException ex2) {
            Client.LOG.error("UnknownHostException, message: " + ex2.getMessage() + " object: " + ex2);
            return false;
        }
        catch (IOException ex3) {
            Client.LOG.error("UIOException, message: " + ex3.getMessage() + " object: " + ex3);
            return false;
        }
    }
    
    @Override
    public void run() {
        print("running function: run()");
        boolean isOK = true;
        while (isOK) {
            try {
                final String in = this.getInput().readUTF();
                print("input code: " + in);
                if (in.equals("#move")) {
                    final int beginX = this.getInput().readInt();
                    final int beginY = this.getInput().readInt();
                    final int endX = this.getInput().readInt();
                    final int endY = this.getInput().readInt();
                    final String promoted = this.getInput().readUTF();
                    this.getGame().simulateMove(beginX, beginY, endX, endY, promoted);
                    final int tabNumber = JChessApp.getJavaChessView().getTabNumber(this.getGame());
                    final JTabbedPane gamesPane = JChessApp.getJavaChessView().getGamesPane();
                    gamesPane.setForegroundAt(tabNumber, JChessTabbedPane.EVENT_COLOR);
                    gamesPane.repaint();
                }
                else if (in.equals("#message")) {
                    final String str = this.getInput().readUTF();
                    this.getGame().getChat().addMessage(str);
                }
                else if (in.equals("#settings")) {
                    try {
                        this.setSettings((Settings)this.getInput().readObject());
                    }
                    catch (ClassNotFoundException ex) {
                        Client.LOG.error("ClassNotFoundException, message: " + ex.getMessage() + " object: " + ex);
                    }
                    this.getGame().setSettings(this.getSettings());
                    this.getGame().setClient(this);
                    this.getGame().getChat().setClient(this);
                    this.getGame().newGame();
                    this.getGame().getChessboard().repaint();
                }
                else if (in.equals("#errorConnection")) {
                    this.getGame().getChat().addMessage("** " + Settings.lang("error_connecting_one_of_player") + " **");
                }
                else if (in.equals("#undoAsk") && !this.isIsObserver()) {
                    final int result = JOptionPane.showConfirmDialog(null, Settings.lang("your_oponent_plase_to_undo_move_do_you_agree"), Settings.lang("confirm_undo_move"), 0);
                    if (result == 0) {
                        this.getGame().getChessboard().undo();
                        this.getGame().switchActive();
                        this.sendUndoAnswerPositive();
                    }
                    else {
                        this.sendUndoAnswerNegative();
                    }
                }
                else if (in.equals("#undoAnswerPositive") && (this.isWait4undoAnswer() || this.isIsObserver())) {
                    this.setWait4undoAnswer(false);
                    final String lastMove = this.getGame().getMoves().getMoves().get(this.getGame().getMoves().getMoves().size() - 1);
                    this.getGame().getChat().addMessage("** " + Settings.lang("permision_ok_4_undo_move") + ": " + lastMove + "**");
                    this.getGame().getChessboard().undo();
                }
                else {
                    if (!in.equals("#undoAnswerNegative") || !this.isWait4undoAnswer()) {
                        continue;
                    }
                    this.getGame().getChat().addMessage(Settings.lang("no_permision_4_undo_move"));
                }
            }
            catch (IOException ex2) {
                isOK = false;
                this.getGame().getChat().addMessage("** " + Settings.lang("error_connecting_to_server") + " **");
                Client.LOG.error("IOException, message: " + ex2.getMessage() + " object: " + ex2);
            }
        }
    }
    
    public static void print(final String str) {
        if (Client.isPrintEnable) {
            Client.LOG.debug("Client: " + str);
        }
    }
    
    public void sendMove(final int beginX, final int beginY, final int endX, final int endY, final String promotedPiece) {
        print("running function: sendMove(" + beginX + ", " + beginY + ", " + endX + ", " + endY + ")");
        try {
            this.getOutput().writeUTF("#move");
            this.getOutput().writeInt(beginX);
            this.getOutput().writeInt(beginY);
            this.getOutput().writeInt(endX);
            this.getOutput().writeInt(endY);
            this.getOutput().writeUTF((promotedPiece != null) ? promotedPiece : "");
            this.getOutput().flush();
        }
        catch (IOException ex) {
            Client.LOG.error("IOException, message: " + ex.getMessage() + " object: " + ex);
        }
    }
    
    public void sendUndoAsk() {
        print("sendUndoAsk");
        try {
            this.setWait4undoAnswer(true);
            this.getOutput().writeUTF("#undoAsk");
            this.getOutput().flush();
        }
        catch (IOException ex) {
            Client.LOG.error("IOException, message: " + ex.getMessage() + " object: " + ex);
        }
    }
    
    public void sendUndoAnswerPositive() {
        try {
            this.getOutput().writeUTF("#undoAnswerPositive");
            this.getOutput().flush();
        }
        catch (IOException ex) {
            Client.LOG.error("IOException, message: " + ex.getMessage() + " object: " + ex);
        }
    }
    
    public void sendUndoAnswerNegative() {
        try {
            this.getOutput().writeUTF("#undoAnswerNegative");
            this.getOutput().flush();
        }
        catch (IOException ex) {
            Client.LOG.error("IOException, message: " + ex.getMessage() + " object: " + ex);
        }
    }
    
    public void sendMassage(final String str) {
        print("running function: sendMessage(" + str + ")");
        try {
            this.getOutput().writeUTF("#message");
            this.getOutput().writeUTF(str);
            this.getOutput().flush();
        }
        catch (IOException ex) {
            Client.LOG.error("IOException, message: " + ex.getMessage() + " object: " + ex);
        }
    }
    
    public Game getGame() {
        return this.game;
    }
    
    public void setGame(final Game game) {
        this.game = game;
    }
    
    public Settings getSettings() {
        return this.settings;
    }
    
    public void setSettings(final Settings settings) {
        this.settings = settings;
    }
    
    public Socket getSocket() {
        return this.socket;
    }
    
    public void setSocket(final Socket socket) {
        this.socket = socket;
    }
    
    public ObjectOutputStream getOutput() {
        return this.output;
    }
    
    public void setOutput(final ObjectOutputStream output) {
        this.output = output;
    }
    
    public ObjectInputStream getInput() {
        return this.input;
    }
    
    public void setInput(final ObjectInputStream input) {
        this.input = input;
    }
    
    public String getIp() {
        return this.ip;
    }
    
    public void setIp(final String ip) {
        this.ip = ip;
    }
    
    public int getPort() {
        return this.port;
    }
    
    public void setPort(final int port) {
        this.port = port;
    }
    
    public boolean isWait4undoAnswer() {
        return this.wait4undoAnswer;
    }
    
    public void setWait4undoAnswer(final boolean wait4undoAnswer) {
        this.wait4undoAnswer = wait4undoAnswer;
    }
    
    public boolean isIsObserver() {
        return this.isObserver;
    }
    
    public void setIsObserver(final boolean isObserver) {
        this.isObserver = isObserver;
    }
    
    static {
        LOG = Logger.getLogger(Client.class);
        Client.isPrintEnable = true;
    }
}
