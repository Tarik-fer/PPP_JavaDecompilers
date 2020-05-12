// 
// Decompiled by Procyon v0.5.36
// 

package SJCE.xgui.JPanel;

import SJCE.xgui.PiecesUI;
import javax.swing.BorderFactory;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.util.TimerTask;
import SJCE.more.Actions;
import java.util.Timer;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChessClock extends JPanel
{
    public static final int ON_TURN = 2;
    public static final int OFF_TURN = 3;
    public static final int WHITE_TURN = 0;
    public static final int BLACK_TURN = 1;
    private static final int ONE_SECOND = 1000;
    public JLabel[] label;
    private Timer timer;
    private ClockTask clockTask;
    private long[] time;
    private long systemTime;
    private int turn;
    
    public ChessClock() {
        this.label = new JLabel[4];
        this.timer = new Timer("Timer", true);
        this.time = new long[2];
        this.systemTime = 0L;
        this.turn = 0;
        this.initGUI();
        this.label[0].setOpaque(false);
        this.label[1].setOpaque(false);
        this.label[2].setOpaque(false);
        this.label[3].setOpaque(false);
        this.setTime(0, Actions.Time * 60000);
        this.setTime(1, Actions.Time * 60000);
    }
    
    public synchronized void start() {
        final long remainder = Math.abs(this.time[this.turn] % 1000L);
        this.timer.scheduleAtFixedRate(this.clockTask = new ClockTask(), (remainder == 0L) ? 1000L : remainder, 1000L);
        this.systemTime = System.currentTimeMillis();
    }
    
    public synchronized void stop() {
        if (this.clockTask != null) {
            this.clockTask.cancel();
        }
    }
    
    private void initGUI() {
        try {
            this.setLayout(new GridLayout(2, 2));
            this.setPreferredSize(new Dimension(210, 110));
            for (final int i : new int[] { 0, 1, 2, 3 }) {
                this.add(this.label[i] = new JLabel());
                this.label[i].setHorizontalAlignment(0);
                this.label[i].setBorder(BorderFactory.createEtchedBorder(1));
                this.label[i].setFont(this.label[i].getFont().deriveFont(1, 16.0f));
                this.label[i].setSize(105, 55);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int getTurn() {
        return this.turn;
    }
    
    public static int getTurn(final int piece) {
        return PiecesUI.getColor(piece);
    }
    
    public void setTurn(final int turn) {
        this.turn = turn;
    }
    
    public void switchTurn() {
        this.turn = (this.turn + 1) % 2;
    }
    
    private String toString(final long time) {
        String sign = new String();
        int timeSecond = (int)(time / 1000L);
        if (timeSecond < 0) {
            timeSecond = -timeSecond;
            sign = "-";
        }
        final int hour = timeSecond / 3600;
        final int minute = timeSecond % 3600 / 60;
        final int second = timeSecond % 60;
        return (hour == 0) ? String.format(sign + "%1$02d:%2$02d", minute, second) : String.format(sign + "%1$02d:%2$02d:%3$02d", hour, minute, second);
    }
    
    public long getTime(final int turn) {
        return this.time[turn];
    }
    
    public void setTime(final int turn, final long time) {
        this.time[turn] = time;
        this.label[turn].setText(this.toString(this.time[turn]));
    }
    
    public void setTime(final long time) {
        this.setTime(0, time);
        this.setTime(1, time);
    }
    
    private class ClockTask extends TimerTask
    {
        @Override
        public synchronized void run() {
            ChessClock.this.systemTime = System.currentTimeMillis();
            ChessClock.this.time[ChessClock.this.turn] -= 1000L;
            ChessClock.this.label[ChessClock.this.turn].setText(ChessClock.this.toString(ChessClock.this.time[ChessClock.this.turn]));
        }
        
        @Override
        public synchronized boolean cancel() {
            ChessClock.this.time[ChessClock.this.turn] -= System.currentTimeMillis() - ChessClock.this.systemTime;
            ChessClock.this.label[ChessClock.this.turn].setText(ChessClock.this.toString(ChessClock.this.time[ChessClock.this.turn]));
            return super.cancel();
        }
    }
}
