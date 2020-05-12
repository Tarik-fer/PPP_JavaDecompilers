/*
 * Decompiled with CFR 0.148.
 */
package SJCE.more;

import SJCE.XChessFrame;
import java.awt.EventQueue;
import javax.swing.JOptionPane;

public class Msg_Thread
implements Runnable {
    private String msg;
    private Thread go;

    public Msg_Thread(String s) {
        this.msg = s;
        this.go = new Thread(this);
        this.go.start();
    }

    @Override
    public void run() {
        final Thread th = Thread.currentThread();
        EventQueue.invokeLater(new Runnable(){

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

