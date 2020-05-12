/*
 * Decompiled with CFR 0.148.
 */
package SJCE.xgui.Agent;

import SJCE.xgui.Agent.Agent;
import SJCE.xgui.ChessTheme;
import SJCE.xgui.EventObject.MoveEvent;
import SJCE.xgui.Interfaces.IChessContext;
import SJCE.xgui.JList.MoveListUI;
import SJCE.xgui.JPanel.BoardUI;
import SJCE.xgui.JPanel.ChessClock;
import SJCE.xgui.Move;
import SJCE.xgui.Verification;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.image.ImageObserver;
import javax.swing.JOptionPane;

public class UserAgent
extends Agent {
    private static final int HIGHLIGHT_NULL = -1;
    private static MouseEvent expireEvent;
    private int highlight = -1;
    private int pressSquare = -1;
    private int dragPiece = -1;
    private Rectangle dragRect = new Rectangle();
    private Image dragImage;
    private int[] cloneBoard = new int[64];
    private Verification verification = new Verification(this.cloneBoard);
    private MouseAdapter mouseAdapter = new MouseAdapter(){

        @Override
        public void mousePressed(MouseEvent e) {
            if (UserAgent.this.agentTurn == UserAgent.this.chessClock.getTurn() && expireEvent != e) {
                UserAgent.this.mousePressed(e);
                expireEvent = e;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (UserAgent.this.agentTurn == UserAgent.this.chessClock.getTurn() && expireEvent != e) {
                UserAgent.this.mouseReleased(e);
                expireEvent = e;
            }
        }
    };
    private MouseMotionAdapter mouseMotionAdapter;

    public UserAgent(IChessContext context, String goHuman, String colorCE, String ceTip) {
        super(context, goHuman, colorCE, ceTip);
        this.boardUI.addMouseListener(this.mouseAdapter);
        this.mouseMotionAdapter = new MouseMotionAdapter(){

            @Override
            public void mouseDragged(MouseEvent e) {
                if (UserAgent.this.agentTurn == UserAgent.this.chessClock.getTurn() && expireEvent != e) {
                    UserAgent.this.mouseDragged(e);
                    expireEvent = e;
                }
            }
        };
        this.boardUI.addMouseMotionListener(this.mouseMotionAdapter);
    }

    protected void mouseDragged(MouseEvent e) {
        if (this.dragPiece == -1) {
            return;
        }
        Point point = e.getPoint();
        this.boardUI.clearImage(this.dragRect);
        point.translate(-this.dragRect.width / 2, -this.dragRect.height / 2);
        this.dragRect.setLocation(point);
        this.boardUI.drawImage(this.dragImage, this.dragRect.getLocation());
    }

    private void mousePressed(MouseEvent e) {
        Point point = e.getPoint();
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

    private void mouseReleased(MouseEvent e) {
        int source = -1;
        int destination = this.boardUI.getSquare(e.getPoint());
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
        } else if (this.dragPiece != -1) {
            this.boardUI.setPiece(this.dragPiece, this.pressSquare, false);
            source = this.pressSquare;
            piece = this.dragPiece;
            this.boardUI.clearImage(this.dragRect);
            if (source == destination) {
                if (this.dragRect.getLocation().equals(this.boardUI.getSquare(destination))) {
                    this.highlight = this.boardUI.getSquare(e.getPoint());
                    this.boardUI.addHighlight(0, this.highlight);
                }
                return;
            }
        } else {
            return;
        }
        System.arraycopy(this.boardUI.getBoard(), 0, this.cloneBoard, 0, this.cloneBoard.length);
        Move move = new Move(source, destination, piece);
        int type = move.doMove(this.boardUI.getBoard());
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
    public void moveDeclared(Move move) {
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

