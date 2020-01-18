// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.network;

import java.util.Iterator;
import pl.art.lach.mateusz.javaopenchess.utils.GameTypes;
import pl.art.lach.mateusz.javaopenchess.core.players.implementation.NetworkPlayer;
import pl.art.lach.mateusz.javaopenchess.core.players.Player;
import pl.art.lach.mateusz.javaopenchess.core.players.implementation.HumanPlayer;
import pl.art.lach.mateusz.javaopenchess.core.Colors;
import pl.art.lach.mateusz.javaopenchess.utils.GameModes;
import pl.art.lach.mateusz.javaopenchess.utils.Settings;
import java.util.ArrayList;
import java.net.Socket;
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
    private static Map<Integer, Table> tables;
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
            Server.LOG.error("runServer/IOException: " + ex);
        }
        Server.tables = new HashMap<Integer, Table>();
    }
    
    @Override
    public void run() {
        print("listening port: " + Server.port);
        while (true) {
            while (true) {
                Label_0591: {
                    try {
                        Socket s;
                        ObjectInputStream input;
                        ObjectOutputStream output;
                        boolean joinAsPlayer;
                        String nick;
                        Table table;
                        while (true) {
                            s = Server.ss.accept();
                            input = new ObjectInputStream(s.getInputStream());
                            output = new ObjectOutputStream(s.getOutputStream());
                            print("new connection");
                            final int tableID = input.readInt();
                            print("readed table ID: " + tableID);
                            joinAsPlayer = input.readBoolean();
                            print("readed joinAsPlayer: " + joinAsPlayer);
                            nick = input.readUTF();
                            print("readed nick: " + nick);
                            final String password = input.readUTF();
                            print("readed password: " + password);
                            if (!Server.tables.containsKey(tableID)) {
                                print("bad table ID");
                                output.writeInt(connection_info.err_bad_table_ID.getValue());
                                output.flush();
                            }
                            else {
                                table = Server.tables.get(tableID);
                                if (table.password.equals(password)) {
                                    break;
                                }
                                print("bad password");
                                output.writeInt(connection_info.err_bad_password.getValue());
                                output.flush();
                            }
                        }
                        Label_0560: {
                            if (joinAsPlayer) {
                                print("join as player");
                                if (table.isAllPlayers()) {
                                    print("error: was all players at this table");
                                    output.writeInt(connection_info.err_table_is_full.getValue());
                                    output.flush();
                                    break Label_0560;
                                }
                                print("wasn't all players at this table");
                                output.writeInt(connection_info.all_is_ok.getValue());
                                output.flush();
                                table.addPlayer(new Client(s, input, output, nick, table));
                                table.sendMessageToAll("** Gracz " + nick + " do\u0142\u0105czy\u0142 do gry **");
                                if (table.isAllPlayers()) {
                                    table.generateSettings();
                                    print("Send settings to all");
                                    table.sendSettingsToAll();
                                    table.sendMessageToAll("** Nowa gra, zaczna: " + table.clientPlayer1.nick + "**");
                                    break Label_0560;
                                }
                                table.sendMessageToAll("** Oczekiwanie na drugiego gracza **");
                                break Label_0560;
                            }
                            else {
                                print("join as observer");
                                if (!table.canObserversJoin()) {
                                    print("Observers can't join");
                                    output.writeInt(connection_info.err_game_without_observer.getValue());
                                    output.flush();
                                    break Label_0560;
                                }
                                output.writeInt(connection_info.all_is_ok.getValue());
                                output.flush();
                                table.addObserver(new Client(s, input, output, nick, table));
                                if (table.clientPlayer2 != null) {
                                    table.sendSettingsAndMovesToNewObserver();
                                }
                                table.sendMessageToAll("** Obserwator " + nick + " do\u0142\u0105czy\u0142 do gry **");
                                break Label_0560;
                            }
                        }
                        break Label_0591;
                    }
                    catch (IOException ex) {
                        Server.LOG.error("runServer/IOException: " + ex);
                    }
                }
                continue;
            }
        }
    }
    
    private static void print(final String str) {
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
    
    public enum connection_info
    {
        all_is_ok(0), 
        err_bad_table_ID(1), 
        err_table_is_full(2), 
        err_game_without_observer(3), 
        err_bad_password(4);
        
        private int value;
        
        private connection_info(final int value) {
            this.value = value;
        }
        
        public static connection_info get(final int id) {
            switch (id) {
                case 0: {
                    return connection_info.all_is_ok;
                }
                case 1: {
                    return connection_info.err_bad_table_ID;
                }
                case 2: {
                    return connection_info.err_table_is_full;
                }
                case 3: {
                    return connection_info.err_game_without_observer;
                }
                case 4: {
                    return connection_info.err_bad_password;
                }
                default: {
                    return null;
                }
            }
        }
        
        public int getValue() {
            return this.value;
        }
    }
    
    private class Table
    {
        public Client clientPlayer1;
        public Client clientPlayer2;
        public ArrayList<Client> clientObservers;
        public Settings player1Set;
        public Settings player2Set;
        public Settings observerSettings;
        public String password;
        private boolean canObserversJoin;
        private boolean enableChat;
        private ArrayList<Move> movesList;
        
        Table(final String password, final boolean canObserversJoin, final boolean enableChat) {
            this.password = password;
            this.enableChat = enableChat;
            this.canObserversJoin = canObserversJoin;
            if (canObserversJoin) {
                this.clientObservers = new ArrayList<Client>();
            }
            this.movesList = new ArrayList<Move>();
        }
        
        public void generateSettings() {
            this.player1Set = new Settings();
            this.player2Set = new Settings();
            this.player1Set.setGameMode(GameModes.NEW_GAME);
            this.player1Set.setPlayerWhite(new HumanPlayer(this.clientPlayer1.nick, Colors.WHITE));
            this.player1Set.setPlayerBlack(new NetworkPlayer(this.clientPlayer2.nick, Colors.BLACK));
            this.player1Set.setGameType(GameTypes.NETWORK);
            this.player1Set.setUpsideDown(false);
            this.player2Set.setGameMode(GameModes.NEW_GAME);
            this.player2Set.setPlayerWhite(new NetworkPlayer(this.clientPlayer1.nick, Colors.WHITE));
            this.player2Set.setPlayerBlack(new HumanPlayer(this.clientPlayer2.nick, Colors.BLACK));
            this.player2Set.setGameType(GameTypes.NETWORK);
            this.player2Set.setUpsideDown(false);
            if (this.canObserversJoin()) {
                (this.observerSettings = new Settings()).setGameMode(GameModes.NEW_GAME);
                this.observerSettings.setPlayerWhite(new NetworkPlayer(this.clientPlayer1.nick, Colors.BLACK));
                this.observerSettings.setPlayerBlack(new NetworkPlayer(this.clientPlayer2.nick, Colors.BLACK));
                this.observerSettings.setGameType(GameTypes.NETWORK);
                this.observerSettings.setUpsideDown(false);
            }
        }
        
        public void sendSettingsToAll() throws IOException {
            print("running function: sendSettingsToAll()");
            this.clientPlayer1.output.writeUTF("#settings");
            this.clientPlayer1.output.writeObject(this.player1Set);
            this.clientPlayer1.output.flush();
            this.clientPlayer2.output.writeUTF("#settings");
            this.clientPlayer2.output.writeObject(this.player2Set);
            this.clientPlayer2.output.flush();
            if (this.canObserversJoin()) {
                for (final Client observer : this.clientObservers) {
                    observer.output.writeUTF("#settings");
                    observer.output.writeObject(this.observerSettings);
                    observer.output.flush();
                }
            }
        }
        
        public void sendSettingsAndMovesToNewObserver() throws IOException {
            final Client observer = this.clientObservers.get(this.clientObservers.size() - 1);
            observer.output.writeUTF("#settings");
            observer.output.writeObject(this.observerSettings);
            observer.output.flush();
            for (final Move m : this.movesList) {
                observer.output.writeUTF("#move");
                observer.output.writeInt(m.bX);
                observer.output.writeInt(m.bY);
                observer.output.writeInt(m.eX);
                observer.output.writeInt(m.eY);
                observer.output.writeUTF((m.promoted != null) ? m.promoted : "");
            }
            observer.output.flush();
        }
        
        public void sendMoveToOther(final Client sender, final int beginX, final int beginY, final int endX, final int endY, final String promoted) throws IOException {
            print("running function: sendMoveToOther(" + sender.nick + ", " + beginX + ", " + beginY + ", " + endX + ", " + endY + ")");
            if (sender == this.clientPlayer1 || sender == this.clientPlayer2) {
                if (this.clientPlayer1 != sender) {
                    this.clientPlayer1.output.writeUTF("#move");
                    this.clientPlayer1.output.writeInt(beginX);
                    this.clientPlayer1.output.writeInt(beginY);
                    this.clientPlayer1.output.writeInt(endX);
                    this.clientPlayer1.output.writeInt(endY);
                    this.clientPlayer1.output.writeUTF((promoted != null) ? promoted : "");
                    this.clientPlayer1.output.flush();
                }
                if (this.clientPlayer2 != sender) {
                    this.clientPlayer2.output.writeUTF("#move");
                    this.clientPlayer2.output.writeInt(beginX);
                    this.clientPlayer2.output.writeInt(beginY);
                    this.clientPlayer2.output.writeInt(endX);
                    this.clientPlayer2.output.writeInt(endY);
                    this.clientPlayer2.output.writeUTF((promoted != null) ? promoted : "");
                    this.clientPlayer2.output.flush();
                }
                if (this.canObserversJoin()) {
                    for (final Client observer : this.clientObservers) {
                        observer.output.writeUTF("#move");
                        observer.output.writeInt(beginX);
                        observer.output.writeInt(beginY);
                        observer.output.writeInt(endX);
                        observer.output.writeInt(endY);
                        observer.output.writeUTF((promoted != null) ? promoted : "");
                        observer.output.flush();
                    }
                }
                this.movesList.add(new Move(beginX, beginY, endX, endY, promoted));
            }
        }
        
        public void sendMessageToAll(final String str) throws IOException {
            print("running function: sendMessageToAll(" + str + ")");
            if (this.clientPlayer1 != null) {
                this.clientPlayer1.output.writeUTF("#message");
                this.clientPlayer1.output.writeUTF(str);
                this.clientPlayer1.output.flush();
            }
            if (this.clientPlayer2 != null) {
                this.clientPlayer2.output.writeUTF("#message");
                this.clientPlayer2.output.writeUTF(str);
                this.clientPlayer2.output.flush();
            }
            if (this.canObserversJoin()) {
                for (final Client observer : this.clientObservers) {
                    observer.output.writeUTF("#message");
                    observer.output.writeUTF(str);
                    observer.output.flush();
                }
            }
        }
        
        public boolean isAllPlayers() {
            return this.clientPlayer1 != null && this.clientPlayer2 != null;
        }
        
        public boolean isObservers() {
            return !this.clientObservers.isEmpty();
        }
        
        public boolean canObserversJoin() {
            return this.canObserversJoin;
        }
        
        public void addPlayer(final Client client) {
            if (this.clientPlayer1 == null) {
                this.clientPlayer1 = client;
                print("Player1 connected");
            }
            else if (this.clientPlayer2 == null) {
                this.clientPlayer2 = client;
                print("Player2 connected");
            }
        }
        
        public void addObserver(final Client client) {
            this.clientObservers.add(client);
        }
        
        private class Move
        {
            int bX;
            int bY;
            int eX;
            int eY;
            String promoted;
            
            Move(final int bX, final int bY, final int eX, final int eY, final String promoted) {
                this.bX = bX;
                this.bY = bY;
                this.eX = eX;
                this.eY = eY;
                this.promoted = promoted;
            }
        }
    }
    
    private class Client implements Runnable
    {
        private Socket s;
        public ObjectInputStream input;
        public ObjectOutputStream output;
        public String nick;
        private Table table;
        
        Client(final Socket s, final ObjectInputStream input, final ObjectOutputStream output, final String nick, final Table table) {
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
            print("running function: run()");
        Label_0005_Outer:
            while (true) {
                while (true) {
                    try {
                        while (true) {
                            final String in = this.input.readUTF();
                            if (in.equals("#move")) {
                                final int bX = this.input.readInt();
                                final int bY = this.input.readInt();
                                final int eX = this.input.readInt();
                                final int eY = this.input.readInt();
                                final String promoted = this.input.readUTF();
                                this.table.sendMoveToOther(this, bX, bY, eX, eY, promoted);
                            }
                            if (in.equals("#message")) {
                                final String str = this.input.readUTF();
                                this.table.sendMessageToAll(this.nick + ": " + str);
                            }
                        }
                    }
                    catch (IOException ex) {
                        Server.LOG.error("private Client/IOException: " + ex);
                        continue Label_0005_Outer;
                    }
                    continue;
                }
            }
        }
    }
}
