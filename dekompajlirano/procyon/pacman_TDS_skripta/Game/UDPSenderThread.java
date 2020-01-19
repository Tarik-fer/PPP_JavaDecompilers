// 
// Decompiled by Procyon v0.5.36
// 

package Game;

import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.SocketException;
import java.net.DatagramSocket;
import java.net.InetAddress;

class UDPSenderThread extends Thread
{
    private InetAddress serverIPAddress;
    private DatagramSocket udpClientSocket;
    private int serverPort;
    
    public UDPSenderThread(final InetAddress address, final int serverPort) throws SocketException {
        this.serverIPAddress = address;
        this.serverPort = serverPort;
        (this.udpClientSocket = new DatagramSocket()).connect(this.serverIPAddress, this.serverPort);
    }
    
    public DatagramSocket getSocket() {
        return this.udpClientSocket;
    }
    
    @Override
    public void run() {
        try {
            byte[] data = new byte[1024];
            data = "Connected".getBytes();
            final DatagramPacket connectionPacket = new DatagramPacket(data, data.length, this.serverIPAddress, this.serverPort);
            this.udpClientSocket.send(connectionPacket);
            final BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                final String clientMessage = inFromUser.readLine();
                if (clientMessage.equals(".")) {
                    break;
                }
                byte[] sendData = new byte[1024];
                sendData = clientMessage.getBytes();
                final DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, this.serverIPAddress, this.serverPort);
                this.udpClientSocket.send(sendPacket);
                Thread.yield();
            }
        }
        catch (IOException e) {
            System.err.println(e);
        }
    }
}
