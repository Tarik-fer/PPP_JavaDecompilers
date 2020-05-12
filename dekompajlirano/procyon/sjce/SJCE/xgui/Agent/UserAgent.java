// 
// Decompiled by Procyon v0.5.36
// 

package SJCE.xgui.Agent;

import SJCE.xgui.EventObject.MoveEvent;
import java.awt.Component;
import javax.swing.JOptionPane;
import SJCE.xgui.Move;
import java.awt.image.ImageObserver;
import SJCE.xgui.JPanel.ChessClock;
import java.awt.Point;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import SJCE.xgui.Interfaces.IChessContext;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseAdapter;
import SJCE.xgui.Verification;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class UserAgent extends Agent
{
    private static final int HIGHLIGHT_NULL = -1;
    private static MouseEvent expireEvent;
    private int highlight;
    private int pressSquare;
    private int dragPiece;
    private Rectangle dragRect;
    private Image dragImage;
    private int[] cloneBoard;
    private Verification verification;
    private MouseAdapter mouseAdapter;
    private MouseMotionAdapter mouseMotionAdapter;
    
    public UserAgent(final IChessContext context, final String goHuman, final String colorCE, final String ceTip) {
        super(context, goHuman, colorCE, ceTip);
        this.highlight = -1;
        this.pressSquare = -1;
        this.dragPiece = -1;
        this.dragRect = new Rectangle();
        this.cloneBoard = new int[64];
        this.verification = new Verification(this.cloneBoard);
        this.boardUI.addMouseListener(this.mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(final MouseEvent e) {
                if (UserAgent.this.agentTurn == UserAgent.this.chessClock.getTurn() && UserAgent.expireEvent != e) {
                    UserAgent.this.mousePressed(e);
                    UserAgent.expireEvent = e;
                }
            }
            
            @Override
            public void mouseReleased(final MouseEvent e) {
                if (UserAgent.this.agentTurn == UserAgent.this.chessClock.getTurn() && UserAgent.expireEvent != e) {
                    UserAgent.this.mouseReleased(e);
                    UserAgent.expireEvent = e;
                }
            }
        });
        this.boardUI.addMouseMotionListener(this.mouseMotionAdapter = new MouseMotionAdapter() {
            @Override
            public void mouseDragged(final MouseEvent e) {
                if (UserAgent.this.agentTurn == UserAgent.this.chessClock.getTurn() && UserAgent.expireEvent != e) {
                    UserAgent.this.mouseDragged(e);
                    UserAgent.expireEvent = e;
                }
            }
        });
    }
    
    protected void mouseDragged(final MouseEvent e) {
        if (this.dragPiece == -1) {
            return;
        }
        final Point point = e.getPoint();
        this.boardUI.clearImage(this.dragRect);
        point.translate(-this.dragRect.width / 2, -this.dragRect.height / 2);
        this.dragRect.setLocation(point);
        this.boardUI.drawImage(this.dragImage, this.dragRect.getLocation());
    }
    
    private void mousePressed(final MouseEvent e) {
        final Point point = e.getPoint();
        this.pressSquare = this.boardUI.getSquare(point);
        this.dragPiece = this.boardUI.getPiece(this.pressSquare);
        if (this.dragPiece == -1 || ChessClock.getTurn(this.dragPiece) != this.agentTurn) {
            this.dragPiece = -1;
            return;
        }
        this.dragImage = this.boardUI.getChessTheme().getPieceImage(this.dragPiece);
        this.dragRect.setSize(this.dragImage.getWidth(null), this.dragImage.getHeight(null));
        this.dragRect.setLocation(this.boardUI.getSquare(this.pressSquare));
        this.boardUI.removePiece(this.pressSquare, false);
    }
    
    private void mouseReleased(final MouseEvent e) {
        int source = -1;
        final int destination = this.boardUI.getSquare(e.getPoint());
        int piece = -1;
        if (this.dragPiece != -1) {
            this.boardUI.clearImage(this.dragRect);
            this.boardUI.setPiece(this.dragPiece, this.pressSquare);
        }
        if (destination == -10) {
            return;
        }
        if (this.highlight != -1 && this.pressSquare == destination) {
            source = this.highlight;
            piece = this.boardUI.getPiece(source);
            this.boardUI.removeHighlight(this.highlight);
            this.highlight = -1;
            if (source == destination) {
                return;
            }
        }
        else {
            if (this.dragPiece == -1) {
                return;
            }
            this.boardUI.setPiece(this.dragPiece, this.pressSquare, false);
            source = this.pressSquare;
            piece = this.dragPiece;
            this.boardUI.clearImage(this.dragRect);
            if (source == destination) {
                if (this.dragRect.getLocation().equals(this.boardUI.getSquare(destination))) {
                    this.boardUI.addHighlight(0, this.highlight = this.boardUI.getSquare(e.getPoint()));
                }
                return;
            }
        }
        System.arraycopy(this.boardUI.getBoard(), 0, this.cloneBoard, 0, this.cloneBoard.length);
        final Move move = new Move(source, destination, piece);
        final int type = move.doMove(this.boardUI.getBoard());
        this.boardUI.update(move.getAffectedSquares(type));
        int result = this.verification.initialVerify(move, type);
        if (result == -1000) {
            move.undoMove(this.boardUI.getBoard(), type);
            this.boardUI.update(move.getAffectedSquares(type));
            JOptionPane.showMessageDialog(this.boardUI, this.verification.getMessage(result), "117 UserAgent: Illegal Move", 2);
            return;
        }
        System.arraycopy(this.boardUI.getBoard(), 0, this.cloneBoard, 0, this.cloneBoard.length);
        result = this.verification.finalVerify(move);
        if (result == -1000) {
            move.undoMove(this.boardUI.getBoard(), type);
            this.boardUI.update(move.getAffectedSquares(type));
            JOptionPane.showMessageDialog(this.boardUI, this.verification.getMessage(result), "126 UserAgent: Illegal Move", 2);
            return;
        }
        this.moveListUI.addMove(move);
        this.chessClock.stop();
        this.chessClock.switchTurn();
        this.fireMovePerformed(new MoveEvent(this, move));
        if (this.highlight != -1) {
            this.boardUI.removeHighlight(this.highlight);
            this.highlight = -1;
        }
        this.dragPiece = -1;
    }
    
    @Override
    public void moveDeclared(final Move move) {
        this.chessClock.start();
    }
    
    @Override
    public void newGame() {
    }
    
    @Override
    public void dispose() {
        super.dispose();
        this.boardUI.removeMouseListener(this.mouseAdapter);
        this.boardUI.removeMouseMotionListener(this.mouseMotionAdapter);
    }
}
