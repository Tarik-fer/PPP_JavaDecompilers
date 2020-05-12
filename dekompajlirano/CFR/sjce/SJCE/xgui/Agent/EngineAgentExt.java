/*
 * Decompiled with CFR 0.148.
 */
package SJCE.xgui.Agent;

import SJCE.XChessFrame;
import SJCE.more.Actions;
import SJCE.xgui.Agent.EngineAgent;
import SJCE.xgui.EventObject.EngineEvent;
import SJCE.xgui.EventObject.MoveEvent;
import SJCE.xgui.Interfaces.IChessContext;
import SJCE.xgui.JList.MoveListUI;
import SJCE.xgui.JPanel.BoardUI;
import SJCE.xgui.JPanel.ChessClock;
import SJCE.xgui.Move;
import SJCE.xgui.Notation;
import java.io.IOException;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EngineAgentExt
extends EngineAgent {
    Pattern patternMove = Pattern.compile("(my)?\\s*move\\s*(is)?\\s*[:>=\\-]?\\s*([a-h][1-8][a-h][1-8][nbrq]?)", 2);
    Pattern patternIllegal = Pattern.compile("(Illegal move.+)|(Error.+)", 2);
    public static String ceTip;
    public static String goEngine;
    public static String colorCE;

    public EngineAgentExt(IChessContext context, String goEngine, String colorCE, String ceTip) {
        super(context, goEngine, colorCE, ceTip);
        EngineAgentExt.ceTip = ceTip;
        EngineAgentExt.goEngine = goEngine;
        EngineAgentExt.colorCE = colorCE;
    }

    @Override
    public void initiate() {
        super.initiate();
        if (ceTip.equals("xboard")) {
            this.writeLine("xboard");
            if (goEngine.equals("Animats") || goEngine.equals("FrankWalter") || goEngine.equals("KennyClassIQ")) {
                this.writeLine("protover 2");
            }
            this.writeLine("new");
            switch (goEngine) {
                case "Alf": {
                    this.writeLine(Actions.Mode);
                    this.writeLine("sd " + Actions.Depth);
                    this.writeLine("level " + 60 * Actions.Time + " " + Actions.Time + " 0");
                    this.writeLine("post");
                    break;
                }
                case "ArabianKnight": {
                    this.writeLine(Actions.Mode);
                    this.writeLine("sd " + Actions.Depth);
                    this.writeLine("level " + 60 * Actions.Time + " " + Actions.Time + " 0");
                    this.writeLine("post");
                    break;
                }
                case "BremboCE": {
                    this.writeLine("sd " + Actions.Depth);
                    this.writeLine("level " + 60 * Actions.Time + " " + Actions.Time + " 0");
                    this.writeLine("post");
                    break;
                }
                case "CupCake": {
                    this.writeLine(Actions.Mode);
                    this.writeLine("sd " + Actions.Depth);
                    this.writeLine("level " + 60 * Actions.Time + " " + Actions.Time + " 0");
                    this.writeLine("post");
                    this.writeLine("new");
                    break;
                }
                case "CaveChess": {
                    this.writeLine("sd " + Actions.Depth);
                    break;
                }
                case "DeepBrutePos": {
                    this.writeLine(Actions.Mode);
                    this.writeLine("sd " + Actions.Depth);
                    this.writeLine("level " + 60 * Actions.Time + " " + Actions.Time + " 0");
                    this.writeLine("post");
                    break;
                }
                case "FairyPrincess": {
                    this.writeLine(colorCE);
                    this.writeLine("time " + 6000 * Actions.Time);
                    break;
                }
                case "Frittle": {
                    this.writeLine(Actions.Mode);
                    this.writeLine("sd " + Actions.Depth);
                    this.writeLine("level " + 60 * Actions.Time + " " + Actions.Time + " 0");
                    this.writeLine("post");
                    break;
                }
                case "FrankWalter": {
                    this.writeLine(Actions.Mode);
                    this.writeLine("sd " + Actions.Depth);
                    this.writeLine("level " + 60 * Actions.Time + " " + Actions.Time + " 0");
                    this.writeLine("post");
                    break;
                }
                case "Gladiator": {
                    this.writeLine(Actions.Mode);
                    this.writeLine("sd " + Actions.Depth);
                    this.writeLine("post");
                    break;
                }
                case "GNU Chess": {
                    this.writeLine(Actions.Mode);
                    this.writeLine("depth " + Actions.Depth);
                    this.writeLine("time " + 60 * Actions.Time);
                    this.writeLine("level " + 60 * Actions.Time + " " + Actions.Time + " 0");
                    this.writeLine("post");
                    break;
                }
                case "Javalin": {
                    this.writeLine(Actions.Mode);
                    this.writeLine("sd " + Actions.Depth);
                    this.writeLine("level " + 60 * Actions.Time + " " + Actions.Time + " 0");
                    this.writeLine("post");
                    break;
                }
                case "Jchess": {
                    this.writeLine("level " + 60 * Actions.Time + " " + Actions.Time + " 0");
                    this.writeLine("post");
                    break;
                }
                case "jChecs": {
                    this.writeLine(Actions.Mode);
                    this.writeLine("sd " + Actions.Depth);
                    break;
                }
                case "KingsOut": {
                    this.writeLine(Actions.Mode);
                    this.writeLine("sd " + Actions.Depth);
                    this.writeLine("level " + 60 * Actions.Time + " " + Actions.Time + " 0");
                    this.writeLine("post");
                    break;
                }
                case "KennyClassIQ": {
                    this.writeLine(Actions.Mode);
                    this.writeLine("sd " + Actions.Depth);
                    this.writeLine("level " + 60 * Actions.Time + " " + Actions.Time + " 0");
                    this.writeLine("post");
                    break;
                }
                case "OliThink": {
                    this.writeLine(Actions.Mode);
                    this.writeLine("sd " + Actions.Depth);
                    this.writeLine("level " + 60 * Actions.Time + " " + Actions.Time + " 0");
                    this.writeLine("post");
                    break;
                }
                case "Tiffanys": {
                    this.writeLine(Actions.Mode);
                    break;
                }
                case "Talvmenni": {
                    this.writeLine(Actions.Mode);
                    this.writeLine("sd " + Actions.Depth);
                    this.writeLine("level " + 60 * Actions.Time + " " + Actions.Time + " 0");
                    this.writeLine("post");
                    break;
                }
                case "Tri-OS": {
                    this.writeLine("level " + 60 * Actions.Time + " " + Actions.Time + " 0");
                    this.writeLine("post");
                }
            }
        }
        if (ceTip.equals("uci")) {
            this.writeLine("uci");
            this.writeLine("isready");
            this.writeLine("ucinewgame");
            this.writeLine("isready");
            if (Actions.Mode.equals("hard")) {
                this.writeLine("setoption name Ponder value true");
            } else {
                this.writeLine("setoption name Ponder value false");
            }
        }
    }

    @Override
    public void newGame() {
    }

    @Override
    protected void parseCommand() throws IOException {
        String line = this.readLine();
        if (line == null) {
            return;
        }
        Matcher matcher = this.patternMove.matcher(line);
        if (matcher.matches()) {
            Move move = Notation.toMove(matcher.group(3).substring(0, 4));
            this.updateContext(move);
            if (move.getPiece() == 6 & Actions.Prohod_White_Event == 1 & Math.abs(move.getSource() - Actions.Prohod_White_Destination) == 1 & Math.abs(move.getDestination() - Actions.Prohod_White_Destination) == 8) {
                System.out.println("Black Pawn En-Passant !");
                this.boardUI.setPiece(-1, Actions.Prohod_White_Destination);
                this.boardUI.update(Actions.Prohod_White_Destination);
                Actions.Prohod_White_Event = 0;
                Actions.Prohod_White_Destination = -1;
            }
            if (move.getPiece() == 0 & Actions.Prohod_Black_Event == 1 & Math.abs(move.getSource() - Actions.Prohod_Black_Destination) == 1 & Math.abs(move.getDestination() - Actions.Prohod_Black_Destination) == 8) {
                System.out.println("White Pawn En-Passant !");
                this.boardUI.setPiece(-1, Actions.Prohod_Black_Destination);
                this.boardUI.update(Actions.Prohod_Black_Destination);
                Actions.Prohod_Black_Event = 0;
                Actions.Prohod_Black_Destination = -1;
            }
            if (move.getPiece() == 6 & move.getSource() >= 8 & move.getSource() <= 15) {
                System.out.println("Black Pawn Promotion !!!!");
                switch (Actions.enginePromotionFig) {
                    case "n": {
                        move.setPiece(7);
                        this.boardUI.setPiece(7, move.getDestination());
                        break;
                    }
                    case "b": {
                        move.setPiece(8);
                        this.boardUI.setPiece(8, move.getDestination());
                        break;
                    }
                    case "r": {
                        move.setPiece(9);
                        this.boardUI.setPiece(9, move.getDestination());
                        break;
                    }
                    case "q": {
                        move.setPiece(10);
                        this.boardUI.setPiece(10, move.getDestination());
                        break;
                    }
                    default: {
                        move.setPiece(10);
                        this.boardUI.setPiece(10, move.getDestination());
                    }
                }
                this.boardUI.update(move.getDestination());
            }
            if (move.getPiece() == 0 & move.getSource() >= 48 & move.getSource() <= 55) {
                System.out.println("White Pawn Promotion !!!!");
                switch (Actions.enginePromotionFig) {
                    case "n": {
                        move.setPiece(1);
                        this.boardUI.setPiece(1, move.getDestination());
                        break;
                    }
                    case "b": {
                        move.setPiece(2);
                        this.boardUI.setPiece(2, move.getDestination());
                        break;
                    }
                    case "r": {
                        move.setPiece(3);
                        this.boardUI.setPiece(3, move.getDestination());
                        break;
                    }
                    case "q": {
                        move.setPiece(4);
                        this.boardUI.setPiece(4, move.getDestination());
                        break;
                    }
                    default: {
                        move.setPiece(4);
                        this.boardUI.setPiece(4, move.getDestination());
                    }
                }
                this.boardUI.update(move.getDestination());
            }
            this.fireMovePrinted(new EngineEvent(this, matcher.group(3)));
            this.fireMovePerformed(new MoveEvent(this, move));
        }
        if ((matcher = this.patternIllegal.matcher(line)).matches()) {
            this.fireIllegalPrinted(new EngineEvent(this));
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public void userMove(Move move) {
        block55: {
            block54: {
                usercmd = Notation.toString(move);
                if (move.getPiece() == 0 & Math.abs(move.getDestination() - move.getSource()) == 16) {
                    System.out.println("White Pawn 2-Prohod !");
                    Actions.Prohod_White_Event = 1;
                    Actions.Prohod_White_Destination = move.getDestination();
                }
                if (move.getPiece() == 6 & Math.abs(move.getDestination() - move.getSource()) == 16) {
                    System.out.println("Black Pawn 2-Prohod !");
                    Actions.Prohod_Black_Event = 1;
                    Actions.Prohod_Black_Destination = move.getDestination();
                }
                if (move.getPiece() == 0 & move.getSource() >= 48 & move.getSource() <= 55) {
                    if (EngineAgentExt.ceTip.equals("xboard")) {
                        var3_3 = EngineAgentExt.goEngine;
                        var4_5 = -1;
                        switch (var3_3.hashCode()) {
                            case 807717583: {
                                if (!var3_3.equals("Animats")) break;
                                var4_5 = 0;
                                break;
                            }
                            case 103201297: {
                                if (!var3_3.equals("FrankWalter")) break;
                                var4_5 = 1;
                                break;
                            }
                            case 1539590305: {
                                if (!var3_3.equals("KennyClassIQ")) break;
                                var4_5 = 2;
                                break;
                            }
                            case 594729882: {
                                if (!var3_3.equals("Talvmenni")) break;
                                var4_5 = 3;
                                break;
                            }
                            case -817136066: {
                                if (!var3_3.equals("FairyPrincess")) break;
                                var4_5 = 4;
                                break;
                            }
                        }
                        switch (var4_5) {
                            case 0: {
                                this.writeLine(usercmd);
                                ** break;
                            }
                            case 1: {
                                this.writeLine("usermove " + usercmd + "q");
                                ** break;
                            }
                            case 2: {
                                this.writeLine("usermove " + usercmd + "q");
                                ** break;
                            }
                            case 3: {
                                this.writeLine("usermove " + usercmd + "q");
                                ** break;
                            }
                            case 4: {
                                this.writeLine("usermove " + usercmd + "q");
                                ** break;
                            }
                        }
                        this.writeLine(usercmd + "q");
                        ** break;
lbl57:
                        // 6 sources
                    } else {
                        Actions.uciAllMovesString = Actions.uciAllMovesString + "q";
                        this.writeLine("position startpos moves" + Actions.uciAllMovesString);
                        if (Actions.UseClock.equals("true") && !EngineAgentExt.goEngine.equals("Magnum")) {
                            this.writeLine("go depth " + Actions.Depth + " wtime " + XChessFrame.chessClock.getTime(0) + " btime " + XChessFrame.chessClock.getTime(1) + " winc 0 binc 0");
                        } else {
                            this.writeLine("go depth " + Actions.Depth);
                        }
                    }
                    System.out.println("White Pawn to Queen !");
                    move.setPiece(4);
                    this.boardUI.setPiece(4, move.getDestination());
                    this.boardUI.update(move.getDestination());
                    return;
                }
                if (move.getPiece() == 6 & move.getSource() >= 8 & move.getSource() <= 15) {
                    if (EngineAgentExt.ceTip.equals("xboard")) {
                        var3_4 = EngineAgentExt.goEngine;
                        var4_6 = -1;
                        switch (var3_4.hashCode()) {
                            case 807717583: {
                                if (!var3_4.equals("Animats")) break;
                                var4_6 = 0;
                                break;
                            }
                            case 103201297: {
                                if (!var3_4.equals("FrankWalter")) break;
                                var4_6 = 1;
                                break;
                            }
                            case 1539590305: {
                                if (!var3_4.equals("KennyClassIQ")) break;
                                var4_6 = 2;
                                break;
                            }
                            case 594729882: {
                                if (!var3_4.equals("Talvmenni")) break;
                                var4_6 = 3;
                                break;
                            }
                            case -817136066: {
                                if (!var3_4.equals("FairyPrincess")) break;
                                var4_6 = 4;
                                break;
                            }
                        }
                        switch (var4_6) {
                            case 0: {
                                this.writeLine(usercmd);
                                ** break;
                            }
                            case 1: {
                                this.writeLine("usermove " + usercmd + "q");
                                ** break;
                            }
                            case 2: {
                                this.writeLine("usermove " + usercmd + "q");
                                ** break;
                            }
                            case 3: {
                                this.writeLine("usermove " + usercmd + "q");
                                ** break;
                            }
                            case 4: {
                                this.writeLine("usermove " + usercmd + "q");
                                ** break;
                            }
                        }
                        this.writeLine(usercmd + "q");
                        ** break;
lbl117:
                        // 6 sources
                    } else {
                        Actions.uciAllMovesString = Actions.uciAllMovesString + "q";
                        this.writeLine("position startpos moves" + Actions.uciAllMovesString);
                        if (Actions.UseClock.equals("true") && !EngineAgentExt.goEngine.equals("Magnum")) {
                            this.writeLine("go depth " + Actions.Depth + " wtime " + XChessFrame.chessClock.getTime(0) + " btime " + XChessFrame.chessClock.getTime(1) + " winc 0 binc 0");
                        } else {
                            this.writeLine("go depth " + Actions.Depth);
                        }
                    }
                    System.out.println("Black Pawn to Queen !");
                    move.setPiece(10);
                    this.boardUI.setPiece(10, move.getDestination());
                    this.boardUI.update(move.getDestination());
                    return;
                }
                if (EngineAgentExt.goEngine.equals("FairyPrincess") || EngineAgentExt.goEngine.equals("FrankWalter") || EngineAgentExt.goEngine.equals("KennyClassIQ")) break block54;
                if (!EngineAgentExt.goEngine.equals("Talvmenni")) break block55;
            }
            if (Actions.gameTip.equals("EE")) {
                this.writeLine(usercmd);
                return;
            }
            this.writeLine("usermove " + usercmd);
            return;
        }
        if (EngineAgentExt.ceTip.equals("xboard")) {
            this.writeLine(usercmd);
            return;
        }
        if (EngineAgentExt.ceTip.equals("uci")) {
            if (!Actions.enemyTip.equals("another")) {
                this.writeLine("position startpos moves" + Actions.uciAllMovesString);
                if (Actions.UseClock.equals("true")) {
                    if (!Actions.whitePlayerCE.equals("Magnum")) {
                        if (!Actions.blackPlayerCE.equals("Magnum")) {
                            this.writeLine("go depth " + Actions.Depth + " wtime " + XChessFrame.chessClock.getTime(0) + " btime " + XChessFrame.chessClock.getTime(1) + " winc 0 binc 0");
                            return;
                        }
                    }
                }
                this.writeLine("go depth " + Actions.Depth);
                return;
            }
        }
        if (EngineAgentExt.ceTip.equals("uci") == false) return;
        if (EngineAgentExt.colorCE.equals("black") == false) return;
        if (Actions.enemyTip.equals("another") == false) return;
        if (MoveListUI.count % 2 == 1) {
            this.writeLine("position startpos moves" + Actions.uciAllMovesString);
            if (Actions.UseClock.equals("true") && !EngineAgentExt.goEngine.equals("Magnum")) {
                this.writeLine("go depth " + Actions.Depth + " wtime " + XChessFrame.chessClock.getTime(0) + " btime " + XChessFrame.chessClock.getTime(1) + " winc 0 binc 0");
                return;
            }
            this.writeLine("go depth " + Actions.Depth);
            return;
        }
        if (move.getPiece() == 0 & move.getSource() >= 48 & move.getSource() <= 55) {
            this.writeLine(usercmd + "q");
            return;
        }
        if (move.getPiece() == 6 & move.getSource() >= 8 & move.getSource() <= 15) {
            this.writeLine(usercmd + "q");
            return;
        }
        this.writeLine(usercmd);
    }

    private String getTime(long time) {
        return (time /= 1000L) / 60L + ":" + time % 60L / 1000L;
    }

    @Override
    public void quitEngine() {
        this.writeLine("quit");
    }
}

