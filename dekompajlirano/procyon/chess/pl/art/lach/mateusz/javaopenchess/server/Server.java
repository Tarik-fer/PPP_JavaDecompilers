// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.server;

import java.net.Socket;
import pl.art.lach.mateusz.javaopenchess.utils.MD5;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Map;
import org.apache.log4j.Logger;

public class Server implements Runnable
{
    private static final Logger LOG;
    public static boolean isPrintEnable;
    public static Map<Integer, Table> tables;
    public static int port;
    private static ServerSocket ss;
    private static boolean isRunning;
    
    public Server() {
        if (!Server.isRunning) {
            runServer();
            final Thread thread = new Thread(this);
            thread.start();
            Server.isRunning = true;
        }
    }
    
    public static boolean isRunning() {
        return Server.isRunning;
    }
    
    private static void runServer() {
        try {
            Server.ss = new ServerSocket(Server.port);
            print("running");
        }
        catch (IOException ex) {
            Server.LOG.error("IOException: " + ex);
        }
        Server.tables = new HashMap<Integer, Table>();
    }
    
    @Override
    public void run() {
        print("listening port: " + Server.port);
    Label_0024_Outer:
        while (true) {
            while (true) {
                Label_0629: {
                    try {
                    Label_0595:
                        while (true) {
                            final Socket s = Server.ss.accept();
                            final ObjectInputStream input = new ObjectInputStream(s.getInputStream());
                            final ObjectOutputStream output = new ObjectOutputStream(s.getOutputStream());
                            print("new connection");
                            final int tableID = input.readInt();
                            print("readed table ID: " + tableID);
                            final boolean joinAsPlayer = input.readBoolean();
                            print("readed joinAsPlayer: " + joinAsPlayer);
                            final String nick = input.readUTF();
                            print("readed nick: " + nick);
                            final String password = input.readUTF();
                            print("readed password: " + password);
                            if (!Server.tables.containsKey(tableID)) {
                                print("bad table ID");
                                output.writeInt(ConnectionInfo.ERR_WRONG_TABLE_ID.getValue());
                                output.flush();
                            }
                            else {
                                final Table table = Server.tables.get(tableID);
                                if (!MD5.encrypt(table.password).equals(password)) {
                                    print("bad password: " + MD5.encrypt(table.password) + " != " + password);
                                    output.writeInt(ConnectionInfo.ERR_INVALID_PASSWORD.getValue());
                                    output.flush();
                                }
                                else if (joinAsPlayer) {
                                    print("join as player");
                                    if (table.isAllPlayers()) {
                                        print("error: was all players at this table");
                                        output.writeInt(ConnectionInfo.ERR_TABLE_IS_FULL.getValue());
                                        output.flush();
                                    }
                                    else {
                                        print("wasn't all players at this table");
                                        output.writeInt(ConnectionInfo.EVERYTHING_IS_OK.getValue());
                                        output.flush();
                                        table.addPlayer(new SClient(s, input, output, nick, table));
                                        table.sendMessageToAll("** Gracz " + nick + " do\u0142\u0105czy\u0142 do gry **");
                                        if (table.isAllPlayers()) {
                                            table.generateSettings();
                                            print("Send settings to all");
                                            table.sendSettingsToAll();
                                            table.sendMessageToAll("** Nowa gra, zaczna: " + table.clientPlayer1.nick + "**");
                                            break Label_0595;
                                        }
                                        table.sendMessageToAll("** Oczekiwanie na drugiego gracza **");
                                        break Label_0595;
                                    }
                                }
                                else {
                                    print("join as observer");
                                    if (table.canObserversJoin()) {
                                        output.writeInt(ConnectionInfo.EVERYTHING_IS_OK.getValue());
                                        output.flush();
                                        table.addObserver(new SClient(s, input, output, nick, table));
                                        if (table.clientPlayer2 != null) {
                                            table.sendSettingsAndMovesToNewObserver();
                                        }
                                        table.sendMessageToAll("** Obserwator " + nick + " do\u0142\u0105czy\u0142 do gry **");
                                        break Label_0595;
                                    }
                                    print("Observers can't join");
                                    output.writeInt(ConnectionInfo.ERR_GAME_WITHOUT_OBSERVERS.getValue());
                                    output.flush();
                                }
                            }
                        }
                        break Label_0629;
                    }
                    catch (IOException ex) {
                        Server.LOG.error("IOException: " + ex);
                        continue Label_0024_Outer;
                    }
                }
                continue;
            }
        }
    }
    
    public static void print(final String str) {
        if (Server.isPrintEnable) {
            Server.LOG.debug("Server: " + str);
        }
    }
    
    public void newTable(final int idTable, final String password, final boolean withObserver, final boolean enableChat) {
        print("create new table - id: " + idTable);
        Server.tables.put(idTable, new Table(password, withObserver, enableChat));
    }
    
    static {
        LOG = Logger.getLogger(Server.class);
        Server.isPrintEnable = true;
        Server.port = 4449;
        Server.isRunning = false;
    }
}
