/*
 * Decompiled with CFR 0.148.
 */
package SJCE.xgui.Agent;

import SJCE.xgui.Agent.EngineAgent;
import SJCE.xgui.Agent.UserAgent;
import SJCE.xgui.EventObject.MoveEvent;
import SJCE.xgui.Interfaces.IChessContext;
import SJCE.xgui.Interfaces.IMoveListener;
import SJCE.xgui.JList.MoveListUI;
import SJCE.xgui.JPanel.BoardUI;
import SJCE.xgui.JPanel.ChessClock;
import SJCE.xgui.Move;
import java.util.ArrayList;

public abstract class Agent {
    public static final int USER_AGENT = 0;
    public static final int ENGINE_AGENT = 1;
    protected BoardUI boardUI;
    protected MoveListUI moveListUI;
    protected ChessClock chessClock;
    protected int agentTurn;
    private Agent opponentAgent;
    private ArrayList<IMoveListener> listenerList = new ArrayList(2);
    private IMoveListener moveListener;

    public Agent(IChessContext context, String goEngine, String colorCE, String ceTip) {
        this.boardUI = context.getBoardUI();
        this.moveListUI = context.getMoveListUI();
        this.chessClock = context.getChessClock();
    }

    public static Agent createAgent(IChessContext context, int type, String goEngine, String colorCE, String ceTip) {
        switch (type) {
            case 0: {
                return new UserAgent(context, goEngine, colorCE, ceTip);
            }
            case 1: {
                return EngineAgent.createEngine(context, goEngine, colorCE, ceTip);
            }
        }
        return null;
    }

    public void addIMoveListener(IMoveListener l) {
        this.listenerList.add(l);
    }

    public void removeIMoveListener(IMoveListener l) {
        this.listenerList.remove(l);
    }

    protected void fireMovePerformed(MoveEvent e) {
        int count = this.listenerList.size();
        for (int i = 0; i < count; ++i) {
            this.listenerList.get(i).movePerformed(e);
        }
    }

    public Agent getOpponentAgent() {
        return this.opponentAgent;
    }

    public void setOpponentAgent(Agent agent) {
        this.opponentAgent = agent;
        this.moveListener = new IMoveListener(){

            @Override
            public void movePerformed(MoveEvent e) {
                Agent.this.moveDeclared(e.getMove());
            }
        };
        agent.addIMoveListener(this.moveListener);
    }

    public int getTurn() {
        return this.agentTurn;
    }

    public void setTurn(int turn) {
        this.agentTurn = turn;
    }

    public void dispose() {
        this.opponentAgent.removeIMoveListener(this.moveListener);
        this.opponentAgent = null;
    }

    public abstract void newGame();

    public abstract void moveDeclared(Move var1);

}

