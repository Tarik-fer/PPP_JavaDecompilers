// 
// Decompiled by Procyon v0.5.36
// 

package SJCE.more;

import java.awt.EventQueue;
import java.awt.Component;
import javax.swing.JOptionPane;
import SJCE.XChessFrame;

public class Msg_Thread implements Runnable
{
    private String msg;
    private Thread go;
    
    public Msg_Thread(final String s) {
        this.msg = s;
        (this.go = new Thread(this)).start();
    }
    
    @Override
    public void run() {
        final Thread th = Thread.currentThread();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (Msg_Thread.this.go == th) {
                    JOptionPane.showMessageDialog(XChessFrame.frame, Msg_Thread.this.msg);
                    Msg_Thread.this.stop();
                }
            }
        });
    }
    
    public void stop() {
        this.go = null;
    }
}
