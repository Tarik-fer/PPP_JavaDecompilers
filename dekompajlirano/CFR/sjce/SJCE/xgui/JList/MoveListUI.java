/*
 * Decompiled with CFR 0.148.
 */
package SJCE.xgui.JList;

import SJCE.XChessFrame;
import SJCE.more.Actions;
import SJCE.more.Msg_Thread;
import SJCE.xgui.JPanel.BoardUI;
import SJCE.xgui.Move;
import SJCE.xgui.Notation;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListModel;

public class MoveListUI
extends JList {
    public static DefaultListModel listModel = new DefaultListModel();
    public static ArrayList<Move> moveList = new ArrayList();
    public static int count;

    public MoveListUI() {
        this.setModel(listModel);
    }

    public void addMove(Move move) {
        String notation;
        block27: {
            block28: {
                block29: {
                    block24: {
                        block25: {
                            block26: {
                                moveList.add(move);
                                count = listModel.size() + 1;
                                if (count % 2 != 1) break block24;
                                if (move.getPiece() > 5) {
                                    Actions.sendEngineCmd("white", "quit");
                                    Actions.sendEngineCmd("black", "quit");
                                    new Msg_Thread("WHITE RESIGN !");
                                }
                                notation = String.valueOf(count / 2 + 1) + ") ";
                                XChessFrame.aktion.restColorWhite_moveColorBlack();
                                if (!Actions.useSound.equals("true")) break block25;
                                if (Actions.gameTip.equals("EH")) break block26;
                                if (!Actions.gameTip.equals("EE")) break block25;
                            }
                            Actions.playWAV("ce/wav/honkhonk.wav");
                        }
                        Actions.whiteLastMove = move;
                        Actions.whiteRivalMovesString = Actions.whiteRivalMovesString + " " + Notation.toString(move);
                        break block27;
                    }
                    if (move.getPiece() < 6) {
                        Actions.sendEngineCmd("white", "quit");
                        Actions.sendEngineCmd("black", "quit");
                        new Msg_Thread("BLACK RESIGN !");
                    }
                    notation = "         |----------------> ";
                    XChessFrame.aktion.moveColorWhite_restColorBlack();
                    if (!Actions.useSound.equals("true")) break block28;
                    if (Actions.gameTip.equals("HE")) break block29;
                    if (!Actions.gameTip.equals("EE")) break block28;
                }
                Actions.playWAV("ce/wav/sndMsg.wav");
            }
            Actions.blackLastMove = move;
            Actions.blackRivalMovesString = Actions.blackRivalMovesString + " " + Notation.toString(move);
        }
        if (Actions.promotionCount != 0) {
            if (count > Actions.promotionCount + 1) {
                Actions.enginePromotionFig = "";
                Actions.promotionCount = 0;
                System.out.println("RESET PROMOTION TYPE !");
            }
        }
        switch (move.getPiece()) {
            case 0: {
                listModel.addElement(notation + " " + "wP_" + Notation.toString(move));
                break;
            }
            case 6: {
                listModel.addElement(notation + " " + "bP_" + Notation.toString(move));
                break;
            }
            case 1: {
                listModel.addElement(notation + " " + "wN_" + Notation.toString(move));
                break;
            }
            case 7: {
                listModel.addElement(notation + " " + "bN_" + Notation.toString(move));
                break;
            }
            case 2: {
                listModel.addElement(notation + " " + "wB_" + Notation.toString(move));
                break;
            }
            case 8: {
                listModel.addElement(notation + " " + "bB_" + Notation.toString(move));
                break;
            }
            case 3: {
                listModel.addElement(notation + " " + "wR_" + Notation.toString(move));
                break;
            }
            case 9: {
                listModel.addElement(notation + " " + "bR_" + Notation.toString(move));
                break;
            }
            case 4: {
                listModel.addElement(notation + " " + "wQ_" + Notation.toString(move));
                break;
            }
            case 10: {
                listModel.addElement(notation + " " + "bQ_" + Notation.toString(move));
                break;
            }
            case 5: {
                listModel.addElement(notation + " " + "wK_" + Notation.toString(move));
                break;
            }
            case 11: {
                listModel.addElement(notation + " " + "bK_" + Notation.toString(move));
                break;
            }
            default: {
                listModel.addElement(notation + " " + Notation.toString(move));
            }
        }
        Actions.uciAllMovesString = Notation.toString(move).equals("a1a1") ? Actions.uciAllMovesString + " null" : Actions.uciAllMovesString + " " + Notation.toString(move);
        Actions.enginePromotionType = "";
        XChessFrame.boardUI.update(XChessFrame.SQUARES);
    }

    public ArrayList<Move> getMoveList() {
        return moveList;
    }

    public void clear() {
        moveList.clear();
        listModel.clear();
    }
}

