// 
// Decompiled by Procyon v0.5.36
// 

package Game;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.SocketException;
import java.net.DatagramSocket;

class UDPReceiverThread extends Thread
{
    private DatagramSocket udpClientSocket;
    private boolean stopped;
    private String serverReply;
    private String[] enemyPosition;
    
    public UDPReceiverThread(final DatagramSocket udpClientSocket) throws SocketException {
        this.udpClientSocket = null;
        this.stopped = false;
        this.udpClientSocket = udpClientSocket;
    }
    
    public void close() {
        this.stopped = true;
    }
    
    @Override
    public void run() {
        final byte[] receiveData = new byte[1024];
        while (!this.stopped) {
            final DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            try {
                this.udpClientSocket.receive(receivePacket);
                this.serverReply = new String(receivePacket.getData(), 0, receivePacket.getLength());
                if (!this.serverReply.equals("")) {
                    this.enemyPosition = this.serverReply.split(":");
                    synchronized (Game.enemyList) {
                        MultiplayerGamePlayState.addEnemy(Integer.parseInt(this.enemyPosition[0]), Integer.parseInt(this.enemyPosition[1]), Integer.parseInt(this.enemyPosition[2]), Integer.parseInt(this.enemyPosition[3]));
                    }
                }
                Thread.yield();
            }
            catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}
