package Game;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

class UDPReceiverThread extends Thread {
   private DatagramSocket udpClientSocket = null;
   private boolean stopped = false;
   private String serverReply;
   private String[] enemyPosition;

   public UDPReceiverThread(DatagramSocket udpClientSocket) throws SocketException {
      this.udpClientSocket = udpClientSocket;
   }

   public void close() {
      this.stopped = true;
   }

   public void run() {
      byte[] receiveData = new byte[1024];

      while(!this.stopped) {
         DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

         try {
            this.udpClientSocket.receive(receivePacket);
            this.serverReply = new String(receivePacket.getData(), 0, receivePacket.getLength());
            if (!this.serverReply.equals("")) {
               this.enemyPosition = this.serverReply.split(":");
               synchronized(Game.enemyList) {
                  MultiplayerGamePlayState.addEnemy(Integer.parseInt(this.enemyPosition[0]), Integer.parseInt(this.enemyPosition[1]), Integer.parseInt(this.enemyPosition[2]), Integer.parseInt(this.enemyPosition[3]));
               }
            }

            Thread.yield();
         } catch (IOException var6) {
            System.err.println(var6);
         }
      }

   }
}
