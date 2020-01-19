package Game;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class Connection implements Runnable {
   private DatagramSocket socket;
   private boolean running;
   InetSocketAddress address;
   String opponentPosition = "";

   public void connect(int port, InetSocketAddress address) throws SocketException {
      try {
         this.socket = new DatagramSocket(port);
         this.address = address;
      } catch (IllegalArgumentException var4) {
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

   public void run() {
      byte[] buffer = new byte[128];
      DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

      while(this.running) {
         try {
            this.socket.receive(packet);
            if (Game.state == 4) {
               Game.Score.resetScore();
            }

            this.opponentPosition = new String(buffer, 0, packet.getLength());
            String var3 = this.opponentPosition;
            byte var4 = -1;
            switch(var3.hashCode()) {
            case 49:
               if (var3.equals("1")) {
                  var4 = 2;
               }
               break;
            case 50:
               if (var3.equals("2")) {
                  var4 = 0;
               }
               break;
            case 51:
               if (var3.equals("3")) {
                  var4 = 1;
               }
            }

            switch(var4) {
            case 0:
               MultiplayerGamePlayState.quitGame();
               break;
            case 1:
               MultiplayerGamePlayState.resetGame();
               break;
            case 2:
               Game.opponentFired = true;
            }
         } catch (IOException var5) {
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
      } catch (IllegalArgumentException var4) {
      }

   }

   public void send(byte[] ba) throws IOException {
      DatagramPacket packet = new DatagramPacket(ba, ba.length);
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
