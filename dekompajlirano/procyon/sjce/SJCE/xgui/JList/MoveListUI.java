// 
// Decompiled by Procyon v0.5.36
// 

package SJCE.xgui.JList;

import SJCE.xgui.Notation;
import SJCE.more.Msg_Thread;
import SJCE.more.Actions;
import SJCE.XChessFrame;
import javax.swing.ListModel;
import SJCE.xgui.Move;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;

public class MoveListUI extends JList
{
    public static DefaultListModel listModel;
    public static ArrayList<Move> moveList;
    public static int count;
    
    public MoveListUI() {
        this.setModel(MoveListUI.listModel);
    }
    
    public void addMove(final Move move) {
        MoveListUI.moveList.add(move);
        MoveListUI.count = MoveListUI.listModel.size() + 1;
        String notation;
        if (MoveListUI.count % 2 == 1) {
            if (move.getPiece() > 5) {
                final Actions aktion = XChessFrame.aktion;
                Actions.sendEngineCmd("white", "quit");
                final Actions aktion2 = XChessFrame.aktion;
                Actions.sendEngineCmd("black", "quit");
                new Msg_Thread("WHITE RESIGN !");
            }
            notation = String.valueOf(MoveListUI.count / 2 + 1) + ") ";
            XChessFrame.aktion.restColorWhite_moveColorBlack();
            final Actions aktion3 = XChessFrame.aktion;
            Label_0157: {
                if (Actions.useSound.equals("true")) {
                    final Actions aktion4 = XChessFrame.aktion;
                    if (!Actions.gameTip.equals("EH")) {
                        final Actions aktion5 = XChessFrame.aktion;
                        if (!Actions.gameTip.equals("EE")) {
                            break Label_0157;
                        }
                    }
                    final Actions aktion6 = XChessFrame.aktion;
                    Actions.playWAV("ce/wav/honkhonk.wav");
                }
            }
            final Actions aktion7 = XChessFrame.aktion;
            Actions.whiteLastMove = move;
            final Actions aktion8 = XChessFrame.aktion;
            final StringBuilder sb = new StringBuilder();
            final Actions aktion9 = XChessFrame.aktion;
            Actions.whiteRivalMovesString = sb.append(Actions.whiteRivalMovesString).append(" ").append(Notation.toString(move)).toString();
        }
        else {
            if (move.getPiece() < 6) {
                final Actions aktion10 = XChessFrame.aktion;
                Actions.sendEngineCmd("white", "quit");
                final Actions aktion11 = XChessFrame.aktion;
                Actions.sendEngineCmd("black", "quit");
                new Msg_Thread("BLACK RESIGN !");
            }
            notation = "         |----------------> ";
            XChessFrame.aktion.moveColorWhite_restColorBlack();
            final Actions aktion12 = XChessFrame.aktion;
            Label_0311: {
                if (Actions.useSound.equals("true")) {
                    final Actions aktion13 = XChessFrame.aktion;
                    if (!Actions.gameTip.equals("HE")) {
                        final Actions aktion14 = XChessFrame.aktion;
                        if (!Actions.gameTip.equals("EE")) {
                            break Label_0311;
                        }
                    }
                    final Actions aktion15 = XChessFrame.aktion;
                    Actions.playWAV("ce/wav/sndMsg.wav");
                }
            }
            final Actions aktion16 = XChessFrame.aktion;
            Actions.blackLastMove = move;
            final Actions aktion17 = XChessFrame.aktion;
            final StringBuilder sb2 = new StringBuilder();
            final Actions aktion18 = XChessFrame.aktion;
            Actions.blackRivalMovesString = sb2.append(Actions.blackRivalMovesString).append(" ").append(Notation.toString(move)).toString();
        }
        final Actions aktion19 = XChessFrame.aktion;
        if (Actions.promotionCount != 0) {
            final int count = MoveListUI.count;
            final Actions aktion20 = XChessFrame.aktion;
            if (count > Actions.promotionCount + 1) {
                final Actions aktion21 = XChessFrame.aktion;
                Actions.enginePromotionFig = "";
                final Actions aktion22 = XChessFrame.aktion;
                Actions.promotionCount = 0;
                System.out.println("RESET PROMOTION TYPE !");
            }
        }
        switch (move.getPiece()) {
            case 0: {
                MoveListUI.listModel.addElement(notation + " " + "wP_" + Notation.toString(move));
                break;
            }
            case 6: {
                MoveListUI.listModel.addElement(notation + " " + "bP_" + Notation.toString(move));
                break;
            }
            case 1: {
                MoveListUI.listModel.addElement(notation + " " + "wN_" + Notation.toString(move));
                break;
            }
            case 7: {
                MoveListUI.listModel.addElement(notation + " " + "bN_" + Notation.toString(move));
                break;
            }
            case 2: {
                MoveListUI.listModel.addElement(notation + " " + "wB_" + Notation.toString(move));
                break;
            }
            case 8: {
                MoveListUI.listModel.addElement(notation + " " + "bB_" + Notation.toString(move));
                break;
            }
            case 3: {
                MoveListUI.listModel.addElement(notation + " " + "wR_" + Notation.toString(move));
                break;
            }
            case 9: {
                MoveListUI.listModel.addElement(notation + " " + "bR_" + Notation.toString(move));
                break;
            }
            case 4: {
                MoveListUI.listModel.addElement(notation + " " + "wQ_" + Notation.toString(move));
                break;
            }
            case 10: {
                MoveListUI.listModel.addElement(notation + " " + "bQ_" + Notation.toString(move));
                break;
            }
            case 5: {
                MoveListUI.listModel.addElement(notation + " " + "wK_" + Notation.toString(move));
                break;
            }
            case 11: {
                MoveListUI.listModel.addElement(notation + " " + "bK_" + Notation.toString(move));
                break;
            }
            default: {
                MoveListUI.listModel.addElement(notation + " " + Notation.toString(move));
                break;
            }
        }
        if (Notation.toString(move).equals("a1a1")) {
            final Actions aktion23 = XChessFrame.aktion;
            final StringBuilder sb3 = new StringBuilder();
            final Actions aktion24 = XChessFrame.aktion;
            Actions.uciAllMovesString = sb3.append(Actions.uciAllMovesString).append(" null").toString();
        }
        else {
            final Actions aktion25 = XChessFrame.aktion;
            final StringBuilder sb4 = new StringBuilder();
            final Actions aktion26 = XChessFrame.aktion;
            Actions.uciAllMovesString = sb4.append(Actions.uciAllMovesString).append(" ").append(Notation.toString(move)).toString();
        }
        final Actions aktion27 = XChessFrame.aktion;
        Actions.enginePromotionType = "";
        final XChessFrame frame = XChessFrame.frame;
        XChessFrame.boardUI.update(XChessFrame.SQUARES);
    }
    
    public ArrayList<Move> getMoveList() {
        return MoveListUI.moveList;
    }
    
    public void clear() {
        MoveListUI.moveList.clear();
        MoveListUI.listModel.clear();
    }
    
    static {
        MoveListUI.listModel = new DefaultListModel();
        MoveListUI.moveList = new ArrayList<Move>();
    }
}
