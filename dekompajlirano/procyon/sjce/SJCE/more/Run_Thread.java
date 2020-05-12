// 
// Decompiled by Procyon v0.5.36
// 

package SJCE.more;

import javax.swing.SwingUtilities;
import SJCE.XChessFrame;

public class Run_Thread extends Thread
{
    public Run_Thread() {
        this.start();
    }
    
    @Override
    public void run() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                XChessFrame.frame.loadBoardTheme();
                System.out.println("boardUI = " + XChessFrame.boardUI.getSize());
                System.out.println("borderPanel = " + XChessFrame.borderPanel.getSize());
            }
        });
    }
}
