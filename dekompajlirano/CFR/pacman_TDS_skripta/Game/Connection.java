/*
 * Decompiled with CFR 0.148.
 */
package Game;

import Game.Game;
import Game.MultiplayerGamePlayState;
import Game.ScoreManager;
import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;

public class Connection
implements Runnable {
    private DatagramSocket socket;
    private boolean running;
    InetSocketAddress address;
    String opponentPosition = "";

    public void connect(int port, InetSocketAddress address) throws SocketException {
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
        Thread thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        this.running = false;
        this.socket.close();
    }

    @Override
    public void run() {
        byte[] buffer = new byte[128];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        while (this.running) {
            try {
                this.socket.receive(packet);
                if (Game.state == 4) {
                    Game.Score.resetScore();
                }
                switch (this.opponentPosition = new String(buffer, 0, packet.getLength())) {
                    case "2": {
                        MultiplayerGamePlayState.quitGame();
                        break;
                    }
                    case "3": {
                        MultiplayerGamePlayState.resetGame();
                        break;
                    }
                    case "1": {
                        Game.opponentFired = true;
                        break;
                    }
                }
            }
            catch (IOException e) {
                break;
            }
        }
    }

    public void send(String msg) throws IOException {
        try {
            byte[] buffer = msg.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            packet.setSocketAddress(this.address);
            this.socket.send(packet);
        }
        catch (IllegalArgumentException buffer) {
            // empty catch block
        }
    }

    public void send(byte[] ba) throws IOException {
        byte[] buffer = ba;
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
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

