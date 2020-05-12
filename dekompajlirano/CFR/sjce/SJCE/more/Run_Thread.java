/*
 * Decompiled with CFR 0.148.
 */
package SJCE.more;

import SJCE.XChessFrame;
import SJCE.xgui.JPanel.BoardUI;
import java.awt.Dimension;
import java.io.PrintStream;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Run_Thread
extends Thread {
    public Run_Thread() {
        this.start();
    }

    @Override
    public void run() {
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
                XChessFrame.frame.loadBoardTheme();
                System.out.println("boardUI = " + XChessFrame.boardUI.getSize());
                System.out.println("borderPanel = " + XChessFrame.borderPanel.getSize());
            }
        });
    }

}

