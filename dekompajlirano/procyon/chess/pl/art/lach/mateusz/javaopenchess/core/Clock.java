// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.core;

import pl.art.lach.mateusz.javaopenchess.core.players.Player;

public class Clock
{
    private int timeLeft;
    private Player player;
    
    Clock() {
        this.init(this.timeLeft);
    }
    
    Clock(final int time) {
        this.init(time);
    }
    
    public final void init(final int time) {
        this.timeLeft = time;
    }
    
    public boolean decrement() {
        if (this.timeLeft > 0) {
            --this.timeLeft;
            return true;
        }
        return false;
    }
    
    public void pause() {
    }
    
    public int getLeftTime() {
        return this.timeLeft;
    }
    
    public void setPlayer(final Player player) {
        this.player = player;
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public String prepareString() {
        String strMin = "";
        final Integer time_min = new Integer(this.getLeftTime() / 60);
        final Integer time_sec = new Integer(this.getLeftTime() % 60);
        if (time_min < 10) {
            strMin = "0" + time_min.toString();
        }
        else {
            strMin = time_min.toString();
        }
        String result = strMin + ":";
        if (time_sec < 10) {
            result = result + "0" + time_sec.toString();
        }
        else {
            result += time_sec.toString();
        }
        return result;
    }
}
