package Game;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Map.Entry;

public class UDPServerThread extends Thread {
   private static HashMap portMap = new HashMap();
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
      } catch (SocketException var4) {
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

   public void run() {
      while(true) {
         try {
            sleep((long)Game.ENEMY_DELAY);
            this.connect();
            this.createPosition();
            this.sendData = this.sendString.getBytes();
            Iterator var1 = portMap.entrySet().iterator();

            while(var1.hasNext()) {
               Entry entry = (Entry)var1.next();
               this.clientPort = (Integer)entry.getKey();
               this.clientIP = (InetAddress)entry.getValue();
               DatagramPacket sendPacket = new DatagramPacket(this.sendData, this.sendData.length, this.clientIP, this.clientPort);
               this.udpServerSocket.send(sendPacket);
            }
         } catch (InterruptedException var4) {
            System.out.println("Server Error sleep()");
         } catch (IOException var5) {
            System.out.println("Server Error sending new enemy position");
         }
      }
   }

   private void connect() {
      while(this.clientsConnected != this.clientsToConnect) {
         try {
            byte[] receiveData = new byte[128];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            this.udpServerSocket.receive(receivePacket);
            String clientMessage = (new String(receivePacket.getData())).trim();
            System.out.println("Client connected - socket address: " + receivePacket.getSocketAddress());
            System.out.println("Client: \"" + clientMessage + "\"");
            portMap.put(receivePacket.getPort(), receivePacket.getAddress());
            this.clientsConnected = portMap.size();
         } catch (NullPointerException | IOException var4) {
            System.out.println("Server Error connecting new clients");
         }
      }

   }

   private void createPosition() {
      switch((new Random()).nextInt(4)) {
      case 0:
         this.sendString = Game.enemyPositionX.nextInt(Window.WIDTH) + ":-90";
         break;
      case 1:
         this.sendString = Game.enemyPositionX.nextInt(Window.WIDTH) + ":" + (Window.HEIGHT + 90);
         break;
      case 2:
         this.sendString = "-90:" + Game.enemyPositionY.nextInt(Window.HEIGHT);
         break;
      case 3:
         this.sendString = Window.WIDTH + 90 + ":" + Game.enemyPositionY.nextInt(Window.HEIGHT);
      }

      this.sendString = this.sendString + ":" + (new Random()).nextInt(4);
      this.sendString = this.sendString + ":" + (new Random()).nextInt(2);
   }
}
