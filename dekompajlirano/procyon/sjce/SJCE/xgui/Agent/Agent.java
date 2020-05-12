// 
// Decompiled by Procyon v0.5.36
// 

package SJCE.xgui.Agent;

import SJCE.xgui.Move;
import SJCE.xgui.EventObject.MoveEvent;
import SJCE.xgui.Interfaces.IChessContext;
import SJCE.xgui.Interfaces.IMoveListener;
import java.util.ArrayList;
import SJCE.xgui.JPanel.ChessClock;
import SJCE.xgui.JList.MoveListUI;
import SJCE.xgui.JPanel.BoardUI;

public abstract class Agent
{
    public static final int USER_AGENT = 0;
    public static final int ENGINE_AGENT = 1;
    protected BoardUI boardUI;
    protected MoveListUI moveListUI;
    protected ChessClock chessClock;
    protected int agentTurn;
    private Agent opponentAgent;
    private ArrayList<IMoveListener> listenerList;
    private IMoveListener moveListener;
    
    public Agent(final IChessContext context, final String goEngine, final String colorCE, final String ceTip) {
        this.listenerList = new ArrayList<IMoveListener>(2);
        this.boardUI = context.getBoardUI();
        this.moveListUI = context.getMoveListUI();
        this.chessClock = context.getChessClock();
    }
    
    public static Agent createAgent(final IChessContext context, final int type, final String goEngine, final String colorCE, final String ceTip) {
        switch (type) {
            case 0: {
                return new UserAgent(context, goEngine, colorCE, ceTip);
            }
            case 1: {
                return EngineAgent.createEngine(context, goEngine, colorCE, ceTip);
            }
            default: {
                return null;
            }
        }
    }
    
    public void addIMoveListener(final IMoveListener l) {
        this.listenerList.add(l);
    }
    
    public void removeIMoveListener(final IMoveListener l) {
        this.listenerList.remove(l);
    }
    
    protected void fireMovePerformed(final MoveEvent e) {
        for (int count = this.listenerList.size(), i = 0; i < count; ++i) {
            this.listenerList.get(i).movePerformed(e);
        }
    }
    
    public Agent getOpponentAgent() {
        return this.opponentAgent;
    }
    
    public void setOpponentAgent(final Agent agent) {
        (this.opponentAgent = agent).addIMoveListener(this.moveListener = new IMoveListener() {
            @Override
            public void movePerformed(final MoveEvent e) {
                Agent.this.moveDeclared(e.getMove());
            }
        });
    }
    
    public int getTurn() {
        return this.agentTurn;
    }
    
    public void setTurn(final int turn) {
        this.agentTurn = turn;
    }
    
    public void dispose() {
        this.opponentAgent.removeIMoveListener(this.moveListener);
        this.opponentAgent = null;
    }
    
    public abstract void newGame();
    
    public abstract void moveDeclared(final Move p0);
}
