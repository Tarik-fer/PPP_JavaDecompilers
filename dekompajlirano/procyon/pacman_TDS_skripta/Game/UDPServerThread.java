// 
// Decompiled by Procyon v0.5.36
// 

package Game;

import java.util.Random;
import java.util.Iterator;
import java.io.IOException;
import java.net.DatagramPacket;
import java.util.Map;
import java.net.SocketException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;

public class UDPServerThread extends Thread
{
    private static HashMap<Integer, InetAddress> portMap;
    private DatagramSocket udpServerSocket;
    private int clientsConnected;
    private int clientsToConnect;
    private byte[] sendData;
    private String sendString;
    private InetAddress clientIP;
    private int clientPort;
    public boolean canSpawnEnemy;
    private boolean isRunning;
    
    public UDPServerThread(final int serverPort, final int clientsToConnect) {
        this.clientsConnected = 0;
        this.sendData = new byte[128];
        this.sendString = "";
        this.clientIP = null;
        this.canSpawnEnemy = true;
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
        UDPServerThread.portMap.clear();
    }
    
    public void isRunning(final boolean isRunning) {
        this.isRunning = isRunning;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                while (true) {
                    Thread.sleep(Game.ENEMY_DELAY);
                    this.connect();
                    this.createPosition();
                    this.sendData = this.sendString.getBytes();
                    for (final Map.Entry<Integer, InetAddress> entry : UDPServerThread.portMap.entrySet()) {
                        this.clientPort = entry.getKey();
                        this.clientIP = entry.getValue();
                        final DatagramPacket sendPacket = new DatagramPacket(this.sendData, this.sendData.length, this.clientIP, this.clientPort);
                        this.udpServerSocket.send(sendPacket);
                    }
                }
            }
            catch (InterruptedException e) {
                System.out.println("Server Error sleep()");
                continue;
            }
            catch (IOException e2) {
                System.out.println("Server Error sending new enemy position");
                continue;
            }
            break;
        }
    }
    
    private void connect() {
        while (this.clientsConnected != this.clientsToConnect) {
            try {
                final byte[] receiveData = new byte[128];
                final DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                this.udpServerSocket.receive(receivePacket);
                final String clientMessage = new String(receivePacket.getData()).trim();
                System.out.println("Client connected - socket address: " + receivePacket.getSocketAddress());
                System.out.println("Client: \"" + clientMessage + "\"");
                UDPServerThread.portMap.put(receivePacket.getPort(), receivePacket.getAddress());
                this.clientsConnected = UDPServerThread.portMap.size();
            }
            catch (IOException | NullPointerException ex2) {
                final Exception ex;
                final Exception e = ex;
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
                break;
            }
        }
        this.sendString = this.sendString + ":" + new Random().nextInt(4);
        this.sendString = this.sendString + ":" + new Random().nextInt(2);
    }
    
    static {
        UDPServerThread.portMap = new HashMap<Integer, InetAddress>();
    }
}
