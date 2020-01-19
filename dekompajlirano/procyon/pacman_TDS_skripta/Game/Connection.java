// 
// Decompiled by Procyon v0.5.36
// 

package Game;

import java.net.SocketAddress;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.SocketException;
import java.net.InetSocketAddress;
import java.net.DatagramSocket;

public class Connection implements Runnable
{
    private DatagramSocket socket;
    private boolean running;
    InetSocketAddress address;
    String opponentPosition;
    
    public Connection() {
        this.opponentPosition = "";
    }
    
    public void connect(final int port, final InetSocketAddress address) throws SocketException {
        try {
            this.socket = new DatagramSocket(port);
            this.address = address;
        }
        catch (IllegalArgumentException e) {
            System.out.println("INVALID ADDRESS or PORT/S");
        }
    }
    
    public void start() {
        this.running = true;
        final Thread thread = new Thread(this);
        thread.start();
    }
    
    public void stop() {
        this.running = false;
        this.socket.close();
    }
    
    @Override
    public void run() {
        final byte[] buffer = new byte[128];
        final DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        while (this.running) {
            try {
                this.socket.receive(packet);
                if (Game.state == 4) {
                    Game.Score.resetScore();
                }
                this.opponentPosition = new String(buffer, 0, packet.getLength());
                final String opponentPosition = this.opponentPosition;
                switch (opponentPosition) {
                    case "2": {
                        MultiplayerGamePlayState.quitGame();
                        continue;
                    }
                    case "3": {
                        MultiplayerGamePlayState.resetGame();
                        continue;
                    }
                    case "1": {
                        Game.opponentFired = true;
                        continue;
                    }
                }
                continue;
            }
            catch (IOException e) {}
            break;
        }
    }
    
    public void send(final String msg) throws IOException {
        try {
            final byte[] buffer = msg.getBytes();
            final DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            packet.setSocketAddress(this.address);
            this.socket.send(packet);
        }
        catch (IllegalArgumentException ex) {}
    }
    
    public void send(final byte[] ba) throws IOException {
        final byte[] buffer = ba;
        final DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        packet.setSocketAddress(this.address);
        this.socket.send(packet);
    }
    
    public String getOpponentPosition() {
        return this.opponentPosition;
    }
    
    public boolean isRunning() {
        return this.running;
    }
}
