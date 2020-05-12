/*
 * Decompiled with CFR 0.148.
 */
package SJCE.xgui.Agent;

import SJCE.XChessFrame;
import SJCE.more.Actions;
import SJCE.more.Msg_Thread;
import SJCE.xgui.Agent.Agent;
import SJCE.xgui.Agent.EngineAgentExt;
import SJCE.xgui.EngineIO;
import SJCE.xgui.EventObject.EngineEvent;
import SJCE.xgui.Interfaces.IChessContext;
import SJCE.xgui.Interfaces.IEngineListener;
import SJCE.xgui.JList.MoveListUI;
import SJCE.xgui.JPanel.BoardUI;
import SJCE.xgui.JPanel.ChessClock;
import SJCE.xgui.Move;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.swing.JTextArea;

public abstract class EngineAgent
extends Agent {
    public static EngineIO engineIOblack;
    public static EngineIO engineIOwhite;
    private ArrayList<IEngineListener> listenerList = new ArrayList(5);
    private Thread parseThread;
    private String name;
    private int protocolId;
    private String forBagatur = "";
    public static String[] argsBagatur1cpu;
    public static String[] argsBagaturNcpu;
    public String ceTip;
    public String goEngine;
    public String colorCE;

    public EngineAgent(IChessContext context, String goEngine, String colorCE, String ceTip) {
        super(context, goEngine, colorCE, ceTip);
        this.ceTip = ceTip;
        this.goEngine = goEngine;
        this.colorCE = colorCE;
        for (int i = 0; i < 9; ++i) {
            this.forBagatur = this.forBagatur + argsBagatur1cpu[i];
        }
        switch (this.goEngine) {
            case "Chess22k": {
                XChessFrame.outputArea.append("Chess22k v1.5, created by Sander Maassen van den Brink (Dutch)\nhttps://github.com/sandermvdb/chess22k\n");
                this.runEngineIO("java -jar ./ce/chess22k-1.5.jar");
                break;
            }
            case "Koedem": {
                XChessFrame.outputArea.append("Koedem v1.1, created by Kolja K\u00fchn (Germany)\nhttp://computer-chess.org/doku.php?id=computer_chess:wiki:lists:chess_engine_list\n");
                this.runEngineIO("java -jar ./ce/Koedem-1.1.jar");
                break;
            }
            case "Alf": {
                XChessFrame.outputArea.append("Alf v1.09, create by Casper Berg\nhttp://kirr.homeunix.org/chess/engines/Jim%20Ablett/ALF/\n");
                this.runEngineIO("java -jar ./ce/Alf109.jar");
                break;
            }
            case "Animats": {
                XChessFrame.outputArea.append("Animats revision 23, create by Stuart Allen\nhttp://animatschess.sourceforge.net/\n");
                this.runEngineIO("java -jar ./ce/AnimatsCE.jar xboard");
                break;
            }
            case "ArabianKnight": {
                XChessFrame.outputArea.append("ArabianKnight v1.55, create by Marcin Gardyjan\n");
                XChessFrame.outputArea.append("http://computer-chess.org/doku.php?id=computer_chess:wiki:download:engine_download_list\n");
                XChessFrame.outputArea.append("http://kirr.homeunix.org/chess/engines/Jim%20Ablett/ARABIAN%20KNIGHT/\n");
                this.runEngineIO("java -jar ./ce/ArabianKnight.jar xboard");
                break;
            }
            case "Bagatur": {
                XChessFrame.outputArea.append("Bagatur v1.4c, create by Krasimir Topchiyski\nhttp://bagaturchess.sourceforge.net\nhttps://sites.google.com/site/bagaturchess/\nhttps://github.com/bagaturchess/\n");
                this.runEngineIO("java -jar ./ce/Bagatur_1.4c.jar" + this.forBagatur);
                break;
            }
            case "BremboCE": {
                XChessFrame.outputArea.append("BremboCE v0.6.2, create by Gianluca Cisana\nhttp://bremboce.cisana.com/download_en.php\n");
                this.runEngineIO("java -jar ./ce/Bremboce.jar");
                break;
            }
            case "Calculon": {
                XChessFrame.outputArea.append("CalculonX v0.4.2, create by Barry Smith\nhttps://code.google.com/p/calculonx/\nhttps://github.com/BarrySW19/CalculonX/\nhttp://computer-chess.org/lib/exe/fetch.php?media=computer_chess:wiki:download:calculon-r258-pn.zip\n");
                this.runEngineIO("java -jar ./ce/code_google_com_archive_p_calculonx.jar");
                break;
            }
            case "Carballo": {
                XChessFrame.outputArea.append("Carballo v1.7, created by Alberto Alonso Ruibal\nhttp://www.alonsoruibal.com/\nhttps://github.com/albertoruibal/carballo/\n");
                this.runEngineIO("java -jar ./ce/carballo-1.7.jar");
                break;
            }
            case "CaveChess": {
                XChessFrame.outputArea.append("Cave release 62, created by \nhttp://cavechess.sourceforge.net\n");
                this.runEngineIO("java -jar ./ce/CaveChess_62.jar");
                break;
            }
            case "ChessBotX": {
                XChessFrame.outputArea.append("ChessBotX v1.02, created by Alexander Soto/Roman Koldaev, see:\nhttps://github.com/alexandersoto/chess-bot\nhttp://alexander.soto.io/chess-bot\n");
                if (Actions.Depth < 5) {
                    this.runEngineIO("java -jar ./ce/ChessBotX.jar easy");
                }
                if (Actions.Depth == 5) {
                    this.runEngineIO("java -jar ./ce/ChessBotX.jar normal");
                }
                if (Actions.Depth == 6) {
                    this.runEngineIO("java -jar ./ce/ChessBotX.jar hard");
                }
                if (Actions.Depth <= 6) break;
                this.runEngineIO("java -jar ./ce/ChessBotX.jar ultra");
                break;
            }
            case "CupCake": {
                XChessFrame.outputArea.append("Cupcake v11, created by Dan Honeycutt\n");
                XChessFrame.outputArea.append("http://kirr.homeunix.org/chess/engines/Jim%20Ablett/CUPCAKE/\n");
                this.runEngineIO("java -jar ./ce/cupcake.jar");
                break;
            }
            case "Cuckoo": {
                XChessFrame.outputArea.append("Cuckoo v1.12, created by Peter Osterlund, see\nhttp://web.comhem.se/petero2home/javachess/index.html");
                XChessFrame.outputArea.append("\nhttp://kirr.homeunix.org/chess/engines/Jim%20Ablett/CUCKOO/\n");
                this.runEngineIO("java -jar ./ce/cuckoo112.jar txt");
                break;
            }
            case "DeepBrutePos": {
                XChessFrame.outputArea.append("DeepBrutePos v2.1, create by Folkert van Heusden\nhttps://www.vanheusden.com/DeepBrutePos/\n");
                this.runEngineIO("java -jar ./ce/DeepBrutePos-2.1.jar --io-mode xboard --depth " + Actions.Depth);
                break;
            }
            case "Eden": {
                XChessFrame.outputArea.append("Eden v0.0.13, created by Nicolai Czempin\n");
                XChessFrame.outputArea.append("http://kirr.homeunix.org/chess/engines/Jim%20Ablett/EDEN/\n");
                this.runEngineIO("java -jar ./ce/eden-0013.jar");
                break;
            }
            case "FairyPrincess": {
                XChessFrame.outputArea.append("Fairy Princess java xboard chess engine\nhttps://github.com/mihaio07/FairyPrincess\n");
                this.runEngineIO("java -jar ./ce/github_mihaio07_FairyPrincess.jar");
                break;
            }
            case "Fischerle": {
                XChessFrame.outputArea.append("Fischerle v0.9.70 SE 32bit, created by Roland Stuckardt\nhttp://www.stuckardt.de/index.php/schachengine-fischerle.html\n");
                this.runEngineIO("java -jar ./ce/Fischerle.jar uci 32bit");
                break;
            }
            case "Flux": {
                XChessFrame.outputArea.append("Flux v2.2.1, created by Phokham Nonava\nhttps://github.com/fluxroot/flux/releases/\n");
                this.runEngineIO("java -jar ./ce/Flux-2.2.1.jar");
                break;
            }
            case "Frittle": {
                XChessFrame.outputArea.append("Frittle v1.0, create by Rohan Padhye\nhttp://frittle.sourceforge.net\n");
                this.runEngineIO("java -jar ./ce/Frittle-1.0.jar");
                break;
            }
            case "FrankWalter": {
                XChessFrame.outputArea.append("FrankWalter v1.0.8, create by Laurens Winkelhagen\nhttp://kirr.homeunix.org/chess/engines/Jim%20Ablett/FRANK-WALTER/\n");
                this.runEngineIO("java -jar ./ce/FrankWalter.jar");
                break;
            }
            case "Gladiator": {
                XChessFrame.outputArea.append("Gladiator v0.0.7, create by David Garcinu\u00f1o Enr\u00edquez\nhttps://github.com/dagaren/gladiator-chess\n");
                this.runEngineIO("java -jar ./ce/gladiator_v0.0.7.jar");
                break;
            }
            case "GNU Chess": {
                XChessFrame.outputArea.append("Chessbox_gnu4j version 1.02 - is a port of GNU Chess 5.0.7 from C to Java.\nCreated by Xan Gregg. See: http://www.forthgo.com/chessbox/,\n");
                XChessFrame.outputArea.append("http://kirr.homeunix.org/chess/engines/Jim%20Ablett/GNUCHESS/\n");
                this.runEngineIO("java -jar ./ce/chessbox_gnu4j.jar");
                break;
            }
            case "Jchess": {
                XChessFrame.outputArea.append("JChess v1.0, created by Tomasz Michniewski - Poland\n");
                XChessFrame.outputArea.append("http://kirr.homeunix.org/chess/engines/Jim%20Ablett/JCHESS/\n");
                XChessFrame.outputArea.append("http://computer-chess.org/doku.php?id=computer_chess:wiki:download:engine_download_list\n");
                this.runEngineIO("java -jar ./ce/Jchess-1.0.jar");
                break;
            }
            case "Javalin": {
                XChessFrame.outputArea.append("Javalin v1.3.1, create by M\u0103nica Vlad Bogdan\nhttps://github.com/bugyvlad/javalin\n");
                this.runEngineIO("java -jar ./ce/javalin_v1.3.1.jar");
                break;
            }
            case "jChecs": {
                XChessFrame.outputArea.append("jChecs v0.1.0.1, create by David Cotton\nhttp://jchecs.free.fr/\nhttp://jchecs.sourceforge.net/\n");
                this.runEngineIO("java -jar ./ce/jchecs_v0.1.0.1.jar " + Actions.jchecsEngineTip);
                break;
            }
            case "Kasparov": {
                XChessFrame.outputArea.append("Kasparov Chess v1.0.0, create by Eric Liu\nhttps://github.com/eliucs/kasparov\n");
                this.runEngineIO("java -jar ./ce/KasparovChess-1.0.0.jar");
                break;
            }
            case "KennyClassIQ": {
                XChessFrame.outputArea.append("http://kennyclassiq.sourceforge.net/\nhttps://github.com/artfuldev/KennyClassIQ/\n");
                this.runEngineIO("java -jar ./ce/KennyClassIQ.jar");
                break;
            }
            case "KingsOut": {
                XChessFrame.outputArea.append("KingsOut v0.2.42, create by Bernd Nuernberger\nhttp://kirr.homeunix.org/chess/engines/Jim%20Ablett/KINGSOUT/\n");
                this.runEngineIO("java -jar ./ce/kingsout.jar");
                break;
            }
            case "Krudo": {
                XChessFrame.outputArea.append("Krudo v0.14b\nhttp://kirr.homeunix.org/chess/engines/Jim%20Ablett/KRUDO/\nhttp://www.g-sei.org/krudo/\n");
                this.runEngineIO("java -jar ./ce/Krudo.jar");
                break;
            }
            case "Magnum": {
                XChessFrame.outputArea.append("Magnum v4.0, create by Eric Stock\nhttps://code.google.com/archive/p/magnumchess/downloads\n");
                this.runEngineIO("java -jar ./ce/MagnumChess.jar");
                break;
            }
            case "Mediocre": {
                XChessFrame.outputArea.append("Mediocre v0.5, create by Jonatan Pettersson\nhttp://mediocrechess.blogspot.no/\nhttp://mediocrechess.sourceforge.net/\n");
                this.runEngineIO("java -jar ./ce/mediocre_v0.5.jar");
                break;
            }
            case "OliThink": {
                XChessFrame.outputArea.append("OliThink v5.3.2, create by Oliver Brausch\n");
                XChessFrame.outputArea.append("http://kirr.homeunix.org/chess/engines/Jim%20Ablett/OLITHINK/\n");
                this.runEngineIO("java -jar ./ce/OliThink532.jar");
                break;
            }
            case "Presbyter": {
                XChessFrame.outputArea.append("Presbyter v1.3.0, create by Jefferson Wilson\n");
                XChessFrame.outputArea.append("https://github.com/jwilson82/presbyter\n");
                this.runEngineIO("java -jar ./ce/presbyter-1.3.0.jar");
                break;
            }
            case "Phoenix": {
                XChessFrame.outputArea.append("Phoenix-Cuckoo v1.13a9, create by Rahul A R\nhttps://github.com/rahular/phoenix\n");
                this.runEngineIO("java -jar ./ce/github_rahular_phoenix.jar txt");
                break;
            }
            case "Pulse": {
                XChessFrame.outputArea.append("Pulse v1.6.1, created by Phokham Nonava\nhttps://github.com/fluxroot/pulse/releases/\nhttp://www.fluxchess.com/pulse/download/\n");
                this.runEngineIO("java -jar ./ce/pulse-1.6.1-java.jar");
                break;
            }
            case "Rival": {
                XChessFrame.outputArea.append("Rival build 0094, written by Russell Newman and Chris Moreton\nhttps://github.com/Netsensia/rival-chess-android-engine\nhttp://www.rivalchess.com/downloads/\n");
                this.runEngineIO("java -jar ./ce/Rival-0094.jar");
                break;
            }
            case "Rumney": {
                XChessFrame.outputArea.append("Rumney Chess v0.2.1\nhttps://github.com/Zaloum/\n");
                this.runEngineIO("java -jar ./ce/github_Zaloum_RumneyChess.jar");
                break;
            }
            case "Talvmenni": {
                XChessFrame.outputArea.append("Talvmenni v0.0.1\nhttp://talvmenni.sourceforge.net\n");
                XChessFrame.outputArea.append("http://kirr.homeunix.org/chess/engines/Jim%20Ablett/TALVMENNI/\n");
                this.runEngineIO("java -jar ./ce/talvmenni.jar");
                break;
            }
            case "Tiffanys": {
                XChessFrame.outputArea.append("Tiffanys v0.5, create by Bernhard von Gunten\nhttp://tiffanys.sourceforge.net\n");
                this.runEngineIO("java -jar ./ce/Tiffanys.jar xboard");
                break;
            }
            case "Tri-OS": {
                XChessFrame.outputArea.append("Tri-OS CS4210's Java xboard Chess Engine\nsee please: http://chess.dubmun.com/\n");
                this.runEngineIO("java -jar ./ce/Tri-OS_CS4210.jar -d 5");
                break;
            }
            case "Unidexter": {
                XChessFrame.outputArea.append("Unidexter v0.0.1, create by Michael Aherne\nhttp://computer-chess.org/lib/exe/fetch.php?media=computer_chess:wiki:download:unidexter-f5fb866-pn.jar\nhttps://github.com/micaherne/unidexter/\n");
                this.runEngineIO("java -jar ./ce/unidexter-f5fb866-pn.jar");
                break;
            }
            case "Ziggy": {
                XChessFrame.outputArea.append("Ziggy v0.7, create by Hrafn Eir\u00edksson\nhttp://kirr.homeunix.org/chess/engines/Jim%20Ablett/ZIGGY/\nhttps://github.com/krummi/ChessEngine/\n");
                this.runEngineIO("java -jar ./ce/ziggy.jar");
            }
        }
        this.initiate();
    }

    public void runEngineIO(String cmd) {
        if (this.colorCE.equals("white")) {
            System.out.println("========================= START ENGINE =============================");
            engineIOwhite = new EngineIO(cmd);
            System.out.println("White = " + cmd);
        } else {
            engineIOblack = new EngineIO(cmd);
            System.out.println("Black = " + cmd);
        }
    }

    public static EngineAgent createEngine(IChessContext context, String goEngine, String colorCE, String ceTip) {
        EngineAgentExt.ceTip = ceTip;
        EngineAgentExt.goEngine = goEngine;
        EngineAgentExt.colorCE = colorCE;
        return new EngineAgentExt(context, goEngine, colorCE, ceTip);
    }

    @Override
    public void moveDeclared(Move move) {
        this.chessClock.start();
        this.userMove(move);
    }

    public void addIEngineListener(IEngineListener l) {
        this.listenerList.add(l);
    }

    public void removeIEngineListener(IEngineListener l) {
        this.listenerList.remove(l);
    }

    protected void fireMovePrinted(EngineEvent e) {
        if (this.listenerList == null) {
            return;
        }
        int count = this.listenerList.size();
        for (int i = 0; i < count; ++i) {
            this.listenerList.get(i).movePrinted(e);
        }
    }

    protected void fireIllegalPrinted(EngineEvent e) {
        if (this.listenerList == null) {
            return;
        }
        int count = this.listenerList.size();
        for (int i = 0; i < count; ++i) {
            this.listenerList.get(i).illegalPrinted(e);
        }
    }

    protected void fireDataPrinted(EngineEvent e) {
        if (this.listenerList == null) {
            return;
        }
        int count = this.listenerList.size();
        for (int i = 0; i < count; ++i) {
            this.listenerList.get(i).dataPrinted(e);
        }
    }

    protected void fireDataEntered(EngineEvent e) {
        if (this.listenerList == null) {
            return;
        }
        int count = this.listenerList.size();
        for (int i = 0; i < count; ++i) {
            this.listenerList.get(i).dataEntered(e);
        }
    }

    protected void updateContext(Move move) {
        this.boardUI.update(move.getAffectedSquares(move.doMove(this.boardUI.getBoard())));
        this.moveListUI.addMove(move);
        this.chessClock.stop();
        this.chessClock.switchTurn();
    }

    public int getProtocolId() {
        return this.protocolId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void initiate() {
        this.parseThread = new Thread(){

            @Override
            public void run() {
                try {
                    do {
                        EngineAgent.this.parseCommand();
                    } while (true);
                }
                catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
        };
        this.parseThread.start();
    }

    public ChessClock getChessClock() {
        return this.chessClock;
    }

    public void setChessClock(ChessClock chessClock) {
        this.chessClock = chessClock;
    }

    public String readLine() throws IOException {
        String line;
        block19: {
            block20: {
                line = "";
                line = this.colorCE.equals("white") ? engineIOwhite.readLine().toLowerCase() : engineIOblack.readLine().toLowerCase();
                if (line.length() > 0) {
                    XChessFrame.outputArea.append("<read from " + this.colorCE.toUpperCase() + ">: " + line + "\n");
                }
                if (line == null || line.length() == 0 || line.startsWith("info")) {
                    return null;
                }
                if (!line.contains("bestmove")) break block19;
                if (Actions.whiteRivalMovesString.endsWith(line.substring(9))) break block20;
                if (!Actions.blackRivalMovesString.endsWith(line.substring(9))) break block19;
            }
            line = "";
            Actions.sendEngineCmd("white", "quit");
            Actions.sendEngineCmd("black", "quit");
            new Msg_Thread(this.colorCE.toUpperCase() + " SAY: end game !");
        }
        if (line.contains("bestmove") && (line.contains("can't move") || line.contains("nomove") || line.contains("0000") || line.contains("null") || line.contains("none") || line.contains("a1a1")) || line.contains("resign") || line.contains("mates") || line.contains("mated") || line.contains("black checkmate") || line.contains("white checkmate") || line.contains("computer wins") || line.contains("stalemate") || line.contains("1/2") && (line.contains("move rule") || line.contains("moves rule") || line.contains("repetition")) || line.contains("{checkmate}") || line.contains("game over")) {
            Actions.sendEngineCmd("white", "quit");
            Actions.sendEngineCmd("black", "quit");
            new Msg_Thread(this.colorCE.toUpperCase() + " SAY: " + line);
        }
        Actions.enginePromotionType = "";
        if (this.ceTip.equals("uci")) {
            if (line.contains("bestmove")) {
                line = line.replaceAll("bestmove", "move");
            }
            if (line.contains("move") && line.contains("ponder")) {
                String[] bf = line.trim().split("\\s+");
                line = bf[0] + " " + bf[1];
            }
        }
        if (line.contains("my move is:")) {
            line = line.replaceAll("my move is:", "move");
        }
        if (line.contains("move")) {
            if (line.endsWith("n") && line.length() == 10) {
                Actions.enginePromotionType = "n";
                Actions.enginePromotionFig = "n";
                Actions.promotionCount = MoveListUI.count;
            }
            if (line.endsWith("b") && line.length() == 10) {
                Actions.enginePromotionType = "b";
                Actions.enginePromotionFig = "b";
                Actions.promotionCount = MoveListUI.count;
            }
            if (line.endsWith("r") && line.length() == 10) {
                Actions.enginePromotionType = "r";
                Actions.enginePromotionFig = "r";
                Actions.promotionCount = MoveListUI.count;
            }
            if (line.endsWith("q") && line.length() == 10) {
                Actions.enginePromotionType = "q";
                Actions.enginePromotionFig = "q";
                Actions.promotionCount = MoveListUI.count;
            }
            if (line.endsWith("o-o") && !line.endsWith("o-o-o")) {
                line = this.colorCE.equals("black") ? "move e8g8" : "move e1g1";
                System.out.println("KingSide Castle o-o = " + line);
            }
            if (line.endsWith("0-0") && !line.endsWith("0-0-0")) {
                line = this.colorCE.equals("black") ? "move e8g8" : "move e1g1";
                System.out.println("KingSide Castle Zero-Zero = " + line);
            }
            if (line.endsWith("o-o-o")) {
                line = this.colorCE.equals("black") ? "move e8c8" : "move e1c1";
                System.out.println("QueenSide Castle o-o-o = " + line);
            }
            if (line.endsWith("0-0-0")) {
                line = this.colorCE.equals("black") ? "move e8c8" : "move e1c1";
                System.out.println("QueenSide Castle Zero-Zero-Zero = " + line);
            }
        }
        this.fireDataPrinted(new EngineEvent(this, line));
        return line;
    }

    public void writeLine(String string) {
        block25: {
            block27: {
                block26: {
                    block20: {
                        block24: {
                            block23: {
                                block22: {
                                    block21: {
                                        XChessFrame.outputArea.append("<write to " + this.colorCE.toUpperCase() + ">: " + string + "\n");
                                        this.fireDataEntered(new EngineEvent(this, string));
                                        if (!this.colorCE.equals("white")) break block20;
                                        if (this.ceTip.equals("uci") && MoveListUI.count % 2 != 1) {
                                            if (!(!Actions.enemyTip.equals("another") || string.length() != 4 || string.equals("easy") || string.equals("hard") || string.equals("post") || string.startsWith("sd"))) {
                                                this.writeLine("position startpos moves" + Actions.uciAllMovesString);
                                                if (Actions.UseClock.equals("true") && !this.goEngine.equals("Magnum")) {
                                                    this.writeLine("go depth " + Actions.Depth + " wtime " + XChessFrame.chessClock.getTime(0) + " btime " + XChessFrame.chessClock.getTime(1) + " winc 0 binc 0");
                                                } else {
                                                    this.writeLine("go depth " + Actions.Depth);
                                                }
                                                return;
                                            }
                                            if (Actions.enemyTip.equals("another") && string.length() == 5 && (string.endsWith("r") || string.endsWith("n") || string.endsWith("b") || string.endsWith("q"))) {
                                                this.writeLine("position startpos moves" + Actions.uciAllMovesString);
                                                if (Actions.UseClock.equals("true") && !this.goEngine.equals("Magnum")) {
                                                    this.writeLine("go depth " + Actions.Depth + " wtime " + XChessFrame.chessClock.getTime(0) + " btime " + XChessFrame.chessClock.getTime(1) + " winc 0 binc 0");
                                                } else {
                                                    this.writeLine("go depth " + Actions.Depth);
                                                }
                                                return;
                                            }
                                        }
                                        if (Actions.whitePlayerCE.equals("FairyPrincess")) break block21;
                                        if (Actions.whitePlayerCE.equals("Talvmenni")) break block21;
                                        if (Actions.whitePlayerCE.equals("FrankWalter")) break block21;
                                        if (!Actions.whitePlayerCE.equals("KennyClassIQ")) break block22;
                                    }
                                    if (!(string.length() != 4 || string.equals("easy") || string.equals("hard") || string.equals("post") || string.startsWith("sd"))) {
                                        engineIOwhite.writeLine("usermove " + string);
                                        return;
                                    }
                                }
                                if (Actions.whitePlayerCE.equals("FairyPrincess")) break block23;
                                if (Actions.whitePlayerCE.equals("Talvmenni")) break block23;
                                if (Actions.whitePlayerCE.equals("FrankWalter")) break block23;
                                if (!Actions.whitePlayerCE.equals("KennyClassIQ")) break block24;
                            }
                            if (string.length() == 5 && (string.endsWith("r") || string.endsWith("n") || string.endsWith("b") || string.endsWith("q"))) {
                                engineIOwhite.writeLine("usermove " + string);
                                return;
                            }
                        }
                        engineIOwhite.writeLine(string);
                        return;
                    }
                    if (!this.colorCE.equals("black")) break block25;
                    if (Actions.blackPlayerCE.equals("FairyPrincess")) break block26;
                    if (Actions.blackPlayerCE.equals("Talvmenni")) break block26;
                    if (Actions.blackPlayerCE.equals("FrankWalter")) break block26;
                    if (!Actions.blackPlayerCE.equals("KennyClassIQ")) break block27;
                }
                if (!(string.length() != 4 || string.equals("easy") || string.equals("hard") || string.equals("post") || string.startsWith("sd"))) {
                    engineIOblack.writeLine("usermove " + string);
                    return;
                }
                if (string.length() == 5 && (string.endsWith("r") || string.endsWith("n") || string.endsWith("b") || string.endsWith("q"))) {
                    engineIOblack.writeLine("usermove " + string);
                    return;
                }
                engineIOblack.writeLine(string);
                return;
            }
            engineIOblack.writeLine(string);
            return;
        }
    }

    protected abstract void parseCommand() throws IOException;

    public abstract void userMove(Move var1);

    public abstract void quitEngine();

    @Override
    public void dispose() {
        super.dispose();
        this.parseThread = null;
        this.quitEngine();
        if (this.colorCE.equals("white")) {
            engineIOwhite.destroy();
        } else {
            engineIOblack.destroy();
        }
    }

    static {
        argsBagatur1cpu = new String[]{" bagaturchess.engines.base.cfg.UCIConfig_BaseImpl", " bagaturchess.search.impl.uci_adaptor.UCISearchAdaptorImpl_PonderingOpponentMove", " bagaturchess.engines.base.cfg.UCISearchAdaptorConfig_BaseImpl", " bagaturchess.search.impl.rootsearch.parallel.MTDParallelSearch", " bagaturchess.engines.base.cfg.RootSearchConfig_BaseImpl_SMP", " bagaturchess.search.impl.alg.impl0.SearchMTD0", " bagaturchess.engines.bagatur.cfg.search.SearchConfigImpl_MTD_SMP", " bagaturchess.engines.bagatur.cfg.board.BoardConfigImpl", " bagaturchess.engines.bagatur.cfg.eval.BagaturEvalConfigImpl_v2"};
        argsBagaturNcpu = new String[]{" bagaturchess.engines.base.cfg.UCIConfig_BaseImpl", " bagaturchess.search.impl.uci_adaptor.UCISearchAdaptorImpl_PonderingOpponentMove", " bagaturchess.engines.base.cfg.UCISearchAdaptorConfig_BaseImpl", " bagaturchess.search.impl.rootsearch.parallel.MTDParallelSearch", " bagaturchess.engines.base.cfg.RootSearchConfig_BaseImpl_SMP", " bagaturchess.search.impl.alg.impl0.SearchMTD0", " bagaturchess.engines.bagatur.cfg.search.SearchConfigImpl_MTD_SMP", " bagaturchess.engines.bagatur.cfg.board.BoardConfigImpl", " bagaturchess.engines.bagatur.cfg.eval.BagaturEvalConfigImpl_v2"};
    }

}

