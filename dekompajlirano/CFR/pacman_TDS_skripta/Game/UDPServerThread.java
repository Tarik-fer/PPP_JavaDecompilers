/*
 * Decompiled with CFR 0.148.
 */
package Game;

import Game.Game;
import Game.Window;
import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class UDPServerThread
extends Thread {
    private static HashMap<Integer, InetAddress> portMap = new HashMap();
    private DatagramSocket udpServerSocket;
    private int clientsConnected = 0;
    private int clientsToConnect;
    private byte[] sendData = new byte[128];
    private String sendString = "";
    private InetAddress clientIP = null;
    private int clientPort;
    public boolean canSpawnEnemy = true;
    private boolean isRunning;

    public UDPServerThread(int serverPort, int clientsToConnect) {
        this.clientsToConnect = clientsToConnect;
        try {
            this.udpServerSocket = new DatagramSocket(serverPort);
        }
        catch (SocketException e) {
            System.out.println("Error starting server");
        }
        System.out.println("Server started...");
        this.isRunning = true;
    }

    public void close() {
        this.isRunning = false;
        this.udpServerSocket.close();
        portMap.clear();
    }

    public void isRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    @Override
    public void run() {
        do {
            try {
                block4: do {
                    UDPServerThread.sleep(Game.ENEMY_DELAY);
                    this.connect();
                    this.createPosition();
                    this.sendData = this.sendString.getBytes();
                    Iterator<Map.Entry<Integer, InetAddress>> iterator = portMap.entrySet().iterator();
                    do {
                        if (!iterator.hasNext()) continue block4;
                        Map.Entry<Integer, InetAddress> entry = iterator.next();
                        this.clientPort = entry.getKey();
                        this.clientIP = entry.getValue();
                        DatagramPacket sendPacket = new DatagramPacket(this.sendData, this.sendData.length, this.clientIP, this.clientPort);
                        this.udpServerSocket.send(sendPacket);
                    } while (true);
                    break;
                } while (true);
            }
            catch (InterruptedException e) {
                System.out.println("Server Error sleep()");
                continue;
            }
            catch (IOException e) {
                System.out.println("Server Error sending new enemy position");
                continue;
            }
            break;
        } while (true);
    }

    private void connect() {
        while (this.clientsConnected != this.clientsToConnect) {
            try {
                byte[] receiveData = new byte[128];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                this.udpServerSocket.receive(receivePacket);
                String clientMessage = new String(receivePacket.getData()).trim();
                System.out.println("Client connected - socket address: " + receivePacket.getSocketAddress());
                System.out.println("Client: \"" + clientMessage + "\"");
                portMap.put(receivePacket.getPort(), receivePacket.getAddress());
                this.clientsConnected = portMap.size();
            }
            catch (IOException | NullPointerException e) {
                System.out.println("Server Error connecting new clients");
            }
        }
    }

    private void createPosition() {
        switch (new Random().nextInt(4)) {
            case 0: {
                this.sendString = Game.enemyPositionX.nextInt(Window.WIDTH) + ":-90";
                break;
            }
            case 1: {
                this.sendString = Game.enemyPositionX.nextInt(Window.WIDTH) + ":" + (Window.HEIGHT + 90);
                break;
            }
            case 2: {
                this.sendString = "-90:" + Game.enemyPositionY.nextInt(Window.HEIGHT);
                break;
            }
            case 3: {
                this.sendString = Window.WIDTH + 90 + ":" + Game.enemyPositionY.nextInt(Window.HEIGHT);
            }
        }
        this.sendString = this.sendString + ":" + new Random().nextInt(4);
        this.sendString = this.sendString + ":" + new Random().nextInt(2);
    }
}

