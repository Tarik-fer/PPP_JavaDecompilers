// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.server;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import pl.art.lach.mateusz.javaopenchess.utils.MD5;
import org.apache.log4j.Logger;

public class Console
{
    private static final Logger LOG;
    
    public static void main(final String[] args) {
        System.out.println("JChess Server Start!");
        final Server server = new Server();
        Server.isPrintEnable = false;
        boolean isOK = true;
        while (isOK) {
            System.out.println("--------------------");
            System.out.println("[1] New table");
            System.out.println("[2] List of active tables");
            System.out.println("[3] Turn on/off server messages");
            System.out.println("[4] Turn off server");
            System.out.print("-> ");
            final String str = readString();
            if (str.equals("1")) {
                System.out.print("ID of game: ");
                final int gameID = Integer.parseInt(readString());
                System.out.print("Password: ");
                final String pass = MD5.encrypt(readString());
                String observer;
                do {
                    System.out.print("Game with observers?[t/n] (t=YES, n=NO): ");
                    observer = readString();
                } while (!observer.equalsIgnoreCase("t") && !observer.equalsIgnoreCase("n"));
                final boolean canObserver = observer.equalsIgnoreCase("t");
                server.newTable(gameID, pass, canObserver, true);
            }
            else if (str.equals("2")) {
                for (final Map.Entry<Integer, Table> entry : Server.tables.entrySet()) {
                    final Integer id = entry.getKey();
                    final Table table = entry.getValue();
                    String p1;
                    if (table.clientPlayer1 == null || table.clientPlayer1.nick == null) {
                        p1 = "empty";
                    }
                    else {
                        p1 = table.clientPlayer1.nick;
                    }
                    String p2;
                    if (table.clientPlayer2 == null || table.clientPlayer2.nick == null) {
                        p2 = "empty";
                    }
                    else {
                        p2 = table.clientPlayer2.nick;
                    }
                    System.out.println("\t" + id + ": " + p1 + " vs " + p2);
                }
            }
            else if (str.equals("3")) {
                if (!Server.isPrintEnable) {
                    Server.isPrintEnable = true;
                    System.out.println("Messages of server has been turned on");
                }
                else {
                    Server.isPrintEnable = false;
                    System.out.println("Messages of server has been turned off");
                }
            }
            else if (str.equals("4")) {
                isOK = false;
            }
            else {
                System.out.println("Unrecognized command");
            }
        }
        System.exit(0);
    }
    
    public static String readString() {
        final StringBuilder sb = new StringBuilder();
        try {
            int ch;
            while ((ch = System.in.read()) != 10) {
                sb.append((char)ch);
            }
        }
        catch (IOException ex) {
            Console.LOG.error("readString()/IOException: " + ex);
        }
        return sb.toString();
    }
    
    static {
        LOG = Logger.getLogger(Console.class);
    }
}
