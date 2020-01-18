// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.server;

import java.io.IOException;
import java.util.Iterator;
import pl.art.lach.mateusz.javaopenchess.utils.GameTypes;
import pl.art.lach.mateusz.javaopenchess.core.players.implementation.NetworkPlayer;
import pl.art.lach.mateusz.javaopenchess.core.players.Player;
import pl.art.lach.mateusz.javaopenchess.core.players.implementation.HumanPlayer;
import pl.art.lach.mateusz.javaopenchess.core.Colors;
import pl.art.lach.mateusz.javaopenchess.utils.GameModes;
import pl.art.lach.mateusz.javaopenchess.utils.Settings;
import java.util.ArrayList;

public class Table
{
    public SClient clientPlayer1;
    public SClient clientPlayer2;
    public ArrayList<SClient> clientObservers;
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
            this.clientObservers = new ArrayList<SClient>();
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
            this.observerSettings.setPlayerWhite(new NetworkPlayer(this.clientPlayer1.nick, Colors.WHITE));
            this.observerSettings.setPlayerBlack(new NetworkPlayer(this.clientPlayer2.nick, Colors.BLACK));
            this.observerSettings.setGameType(GameTypes.NETWORK);
            this.observerSettings.setUpsideDown(false);
        }
    }
    
    public void sendSettingsToAll() throws IOException {
        Server.print("running function: sendSettingsToAll()");
        this.clientPlayer1.output.writeUTF("#settings");
        this.clientPlayer1.output.writeObject(this.player1Set);
        this.clientPlayer1.output.flush();
        this.clientPlayer2.output.writeUTF("#settings");
        this.clientPlayer2.output.writeObject(this.player2Set);
        this.clientPlayer2.output.flush();
        if (this.canObserversJoin()) {
            for (final SClient observer : this.clientObservers) {
                observer.output.writeUTF("#settings");
                observer.output.writeObject(this.observerSettings);
                observer.output.flush();
            }
        }
    }
    
    public void sendSettingsAndMovesToNewObserver() throws IOException {
        final SClient observer = this.clientObservers.get(this.clientObservers.size() - 1);
        observer.output.writeUTF("#settings");
        observer.output.writeObject(this.observerSettings);
        observer.output.flush();
        for (final Move m : this.movesList) {
            observer.output.writeUTF("#move");
            observer.output.writeInt(m.bX);
            observer.output.writeInt(m.bY);
            observer.output.writeInt(m.eX);
            observer.output.writeInt(m.eY);
            observer.output.writeUTF(m.promoted);
        }
        observer.output.flush();
    }
    
    public void sendMoveToOther(final SClient sender, final int beginX, final int beginY, final int endX, final int endY, final String promoted) throws IOException {
        Server.print("running function: sendMoveToOther(" + sender.nick + ", " + beginX + ", " + beginY + ", " + endX + ", " + endY + ")");
        if (sender == this.clientPlayer1 || sender == this.clientPlayer2) {
            final SClient receiver = (this.clientPlayer1 == sender) ? this.clientPlayer2 : this.clientPlayer1;
            receiver.output.writeUTF("#move");
            receiver.output.writeInt(beginX);
            receiver.output.writeInt(beginY);
            receiver.output.writeInt(endX);
            receiver.output.writeInt(endY);
            receiver.output.writeUTF(promoted);
            receiver.output.flush();
            if (this.canObserversJoin()) {
                for (final SClient observer : this.clientObservers) {
                    observer.output.writeUTF("#move");
                    observer.output.writeInt(beginX);
                    observer.output.writeInt(beginY);
                    observer.output.writeInt(endX);
                    observer.output.writeInt(endY);
                    observer.output.writeUTF(promoted);
                    observer.output.flush();
                }
            }
            this.movesList.add(new Move(beginX, beginY, endX, endY, promoted));
        }
    }
    
    public void sendUndoToAll(final SClient sender, final String msg) throws IOException {
        if (sender == this.clientPlayer1 || sender == this.clientPlayer2) {
            this.sendToAll(sender, msg);
            try {
                this.movesList.remove(this.movesList.size() - 1);
            }
            catch (ArrayIndexOutOfBoundsException exc) {}
        }
    }
    
    public void sendToAll(final SClient sender, final String msg) throws IOException {
        if (sender == this.clientPlayer1 || sender == this.clientPlayer2) {
            final SClient receiver = (this.clientPlayer1 == sender) ? this.clientPlayer2 : this.clientPlayer1;
            receiver.output.writeUTF(msg);
            receiver.output.flush();
            if (this.canObserversJoin()) {
                for (final SClient observer : this.clientObservers) {
                    observer.output.writeUTF(msg);
                    observer.output.flush();
                }
            }
        }
    }
    
    public void sendToOtherPlayer(final SClient sender, final String msg) throws IOException {
        if (sender == this.clientPlayer1 || sender == this.clientPlayer2) {
            final SClient receiver = (this.clientPlayer1 == sender) ? this.clientPlayer2 : this.clientPlayer1;
            receiver.output.writeUTF(msg);
            receiver.output.flush();
        }
    }
    
    public void sendErrorConnectionToOther(final SClient sender) throws IOException {
        Server.print("running function: sendErrorConnectionToOther(" + sender.nick + ")");
        if (sender == this.clientPlayer1 || sender == this.clientPlayer2) {
            if (this.clientPlayer1 != sender) {
                this.clientPlayer1.output.writeUTF("#errorConnection");
                this.clientPlayer1.output.flush();
            }
            if (this.clientPlayer2 != sender) {
                this.clientPlayer2.output.writeUTF("#errorConnection");
                this.clientPlayer2.output.flush();
            }
            if (this.canObserversJoin()) {
                for (final SClient observer : this.clientObservers) {
                    observer.output.writeUTF("#errorConnection");
                    observer.output.flush();
                }
            }
        }
    }
    
    public void sendMessageToAll(final String str) throws IOException {
        Server.print("running function: sendMessageToAll(" + str + ")");
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
            for (final SClient observer : this.clientObservers) {
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
    
    public void addPlayer(final SClient client) {
        if (this.clientPlayer1 == null) {
            this.clientPlayer1 = client;
            Server.print("Player1 connected");
        }
        else if (this.clientPlayer2 == null) {
            this.clientPlayer2 = client;
            Server.print("Player2 connected");
        }
    }
    
    public void addObserver(final SClient client) {
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
