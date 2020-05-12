// 
// Decompiled by Procyon v0.5.36
// 

package SJCE.xgui.Agent;

import SJCE.xgui.JList.MoveListUI;
import SJCE.more.Msg_Thread;
import SJCE.xgui.JPanel.ChessClock;
import java.io.IOException;
import SJCE.xgui.EventObject.EngineEvent;
import SJCE.xgui.Move;
import SJCE.more.Actions;
import SJCE.XChessFrame;
import SJCE.xgui.Interfaces.IChessContext;
import SJCE.xgui.Interfaces.IEngineListener;
import java.util.ArrayList;
import SJCE.xgui.EngineIO;

public abstract class EngineAgent extends Agent
{
    public static EngineIO engineIOblack;
    public static EngineIO engineIOwhite;
    private ArrayList<IEngineListener> listenerList;
    private Thread parseThread;
    private String name;
    private int protocolId;
    private String forBagatur;
    public static String[] argsBagatur1cpu;
    public static String[] argsBagaturNcpu;
    public String ceTip;
    public String goEngine;
    public String colorCE;
    
    public EngineAgent(final IChessContext context, final String goEngine, final String colorCE, final String ceTip) {
        super(context, goEngine, colorCE, ceTip);
        this.listenerList = new ArrayList<IEngineListener>(5);
        this.forBagatur = "";
        this.ceTip = ceTip;
        this.goEngine = goEngine;
        this.colorCE = colorCE;
        for (int i = 0; i < 9; ++i) {
            this.forBagatur += EngineAgent.argsBagatur1cpu[i];
        }
        final String goEngine2 = this.goEngine;
        switch (goEngine2) {
            case "Chess22k": {
                final XChessFrame frame = XChessFrame.frame;
                XChessFrame.outputArea.append("Chess22k v1.5, created by Sander Maassen van den Brink (Dutch)\nhttps://github.com/sandermvdb/chess22k\n");
                this.runEngineIO("java -jar ./ce/chess22k-1.5.jar");
                break;
            }
            case "Koedem": {
                final XChessFrame frame2 = XChessFrame.frame;
                XChessFrame.outputArea.append("Koedem v1.1, created by Kolja K\u00fchn (Germany)\nhttp://computer-chess.org/doku.php?id=computer_chess:wiki:lists:chess_engine_list\n");
                this.runEngineIO("java -jar ./ce/Koedem-1.1.jar");
                break;
            }
            case "Alf": {
                final XChessFrame frame3 = XChessFrame.frame;
                XChessFrame.outputArea.append("Alf v1.09, create by Casper Berg\nhttp://kirr.homeunix.org/chess/engines/Jim%20Ablett/ALF/\n");
                this.runEngineIO("java -jar ./ce/Alf109.jar");
                break;
            }
            case "Animats": {
                final XChessFrame frame4 = XChessFrame.frame;
                XChessFrame.outputArea.append("Animats revision 23, create by Stuart Allen\nhttp://animatschess.sourceforge.net/\n");
                this.runEngineIO("java -jar ./ce/AnimatsCE.jar xboard");
                break;
            }
            case "ArabianKnight": {
                final XChessFrame frame5 = XChessFrame.frame;
                XChessFrame.outputArea.append("ArabianKnight v1.55, create by Marcin Gardyjan\n");
                final XChessFrame frame6 = XChessFrame.frame;
                XChessFrame.outputArea.append("http://computer-chess.org/doku.php?id=computer_chess:wiki:download:engine_download_list\n");
                final XChessFrame frame7 = XChessFrame.frame;
                XChessFrame.outputArea.append("http://kirr.homeunix.org/chess/engines/Jim%20Ablett/ARABIAN%20KNIGHT/\n");
                this.runEngineIO("java -jar ./ce/ArabianKnight.jar xboard");
                break;
            }
            case "Bagatur": {
                final XChessFrame frame8 = XChessFrame.frame;
                XChessFrame.outputArea.append("Bagatur v1.4c, create by Krasimir Topchiyski\nhttp://bagaturchess.sourceforge.net\nhttps://sites.google.com/site/bagaturchess/\nhttps://github.com/bagaturchess/\n");
                this.runEngineIO("java -jar ./ce/Bagatur_1.4c.jar" + this.forBagatur);
                break;
            }
            case "BremboCE": {
                final XChessFrame frame9 = XChessFrame.frame;
                XChessFrame.outputArea.append("BremboCE v0.6.2, create by Gianluca Cisana\nhttp://bremboce.cisana.com/download_en.php\n");
                this.runEngineIO("java -jar ./ce/Bremboce.jar");
                break;
            }
            case "Calculon": {
                final XChessFrame frame10 = XChessFrame.frame;
                XChessFrame.outputArea.append("CalculonX v0.4.2, create by Barry Smith\nhttps://code.google.com/p/calculonx/\nhttps://github.com/BarrySW19/CalculonX/\nhttp://computer-chess.org/lib/exe/fetch.php?media=computer_chess:wiki:download:calculon-r258-pn.zip\n");
                this.runEngineIO("java -jar ./ce/code_google_com_archive_p_calculonx.jar");
                break;
            }
            case "Carballo": {
                final XChessFrame frame11 = XChessFrame.frame;
                XChessFrame.outputArea.append("Carballo v1.7, created by Alberto Alonso Ruibal\nhttp://www.alonsoruibal.com/\nhttps://github.com/albertoruibal/carballo/\n");
                this.runEngineIO("java -jar ./ce/carballo-1.7.jar");
                break;
            }
            case "CaveChess": {
                final XChessFrame frame12 = XChessFrame.frame;
                XChessFrame.outputArea.append("Cave release 62, created by \nhttp://cavechess.sourceforge.net\n");
                this.runEngineIO("java -jar ./ce/CaveChess_62.jar");
                break;
            }
            case "ChessBotX": {
                final XChessFrame frame13 = XChessFrame.frame;
                XChessFrame.outputArea.append("ChessBotX v1.02, created by Alexander Soto/Roman Koldaev, see:\nhttps://github.com/alexandersoto/chess-bot\nhttp://alexander.soto.io/chess-bot\n");
                final Actions aktion = XChessFrame.aktion;
                if (Actions.Depth < 5) {
                    this.runEngineIO("java -jar ./ce/ChessBotX.jar easy");
                }
                final Actions aktion2 = XChessFrame.aktion;
                if (Actions.Depth == 5) {
                    this.runEngineIO("java -jar ./ce/ChessBotX.jar normal");
                }
                final Actions aktion3 = XChessFrame.aktion;
                if (Actions.Depth == 6) {
                    this.runEngineIO("java -jar ./ce/ChessBotX.jar hard");
                }
                final Actions aktion4 = XChessFrame.aktion;
                if (Actions.Depth > 6) {
                    this.runEngineIO("java -jar ./ce/ChessBotX.jar ultra");
                    break;
                }
                break;
            }
            case "CupCake": {
                final XChessFrame frame14 = XChessFrame.frame;
                XChessFrame.outputArea.append("Cupcake v11, created by Dan Honeycutt\n");
                final XChessFrame frame15 = XChessFrame.frame;
                XChessFrame.outputArea.append("http://kirr.homeunix.org/chess/engines/Jim%20Ablett/CUPCAKE/\n");
                this.runEngineIO("java -jar ./ce/cupcake.jar");
                break;
            }
            case "Cuckoo": {
                final XChessFrame frame16 = XChessFrame.frame;
                XChessFrame.outputArea.append("Cuckoo v1.12, created by Peter Osterlund, see\nhttp://web.comhem.se/petero2home/javachess/index.html");
                final XChessFrame frame17 = XChessFrame.frame;
                XChessFrame.outputArea.append("\nhttp://kirr.homeunix.org/chess/engines/Jim%20Ablett/CUCKOO/\n");
                this.runEngineIO("java -jar ./ce/cuckoo112.jar txt");
                break;
            }
            case "DeepBrutePos": {
                final XChessFrame frame18 = XChessFrame.frame;
                XChessFrame.outputArea.append("DeepBrutePos v2.1, create by Folkert van Heusden\nhttps://www.vanheusden.com/DeepBrutePos/\n");
                final StringBuilder append = new StringBuilder().append("java -jar ./ce/DeepBrutePos-2.1.jar --io-mode xboard --depth ");
                final Actions aktion5 = XChessFrame.aktion;
                this.runEngineIO(append.append(Actions.Depth).toString());
                break;
            }
            case "Eden": {
                final XChessFrame frame19 = XChessFrame.frame;
                XChessFrame.outputArea.append("Eden v0.0.13, created by Nicolai Czempin\n");
                final XChessFrame frame20 = XChessFrame.frame;
                XChessFrame.outputArea.append("http://kirr.homeunix.org/chess/engines/Jim%20Ablett/EDEN/\n");
                this.runEngineIO("java -jar ./ce/eden-0013.jar");
                break;
            }
            case "FairyPrincess": {
                final XChessFrame frame21 = XChessFrame.frame;
                XChessFrame.outputArea.append("Fairy Princess java xboard chess engine\nhttps://github.com/mihaio07/FairyPrincess\n");
                this.runEngineIO("java -jar ./ce/github_mihaio07_FairyPrincess.jar");
                break;
            }
            case "Fischerle": {
                final XChessFrame frame22 = XChessFrame.frame;
                XChessFrame.outputArea.append("Fischerle v0.9.70 SE 32bit, created by Roland Stuckardt\nhttp://www.stuckardt.de/index.php/schachengine-fischerle.html\n");
                this.runEngineIO("java -jar ./ce/Fischerle.jar uci 32bit");
                break;
            }
            case "Flux": {
                final XChessFrame frame23 = XChessFrame.frame;
                XChessFrame.outputArea.append("Flux v2.2.1, created by Phokham Nonava\nhttps://github.com/fluxroot/flux/releases/\n");
                this.runEngineIO("java -jar ./ce/Flux-2.2.1.jar");
                break;
            }
            case "Frittle": {
                final XChessFrame frame24 = XChessFrame.frame;
                XChessFrame.outputArea.append("Frittle v1.0, create by Rohan Padhye\nhttp://frittle.sourceforge.net\n");
                this.runEngineIO("java -jar ./ce/Frittle-1.0.jar");
                break;
            }
            case "FrankWalter": {
                final XChessFrame frame25 = XChessFrame.frame;
                XChessFrame.outputArea.append("FrankWalter v1.0.8, create by Laurens Winkelhagen\nhttp://kirr.homeunix.org/chess/engines/Jim%20Ablett/FRANK-WALTER/\n");
                this.runEngineIO("java -jar ./ce/FrankWalter.jar");
                break;
            }
            case "Gladiator": {
                final XChessFrame frame26 = XChessFrame.frame;
                XChessFrame.outputArea.append("Gladiator v0.0.7, create by David Garcinu\u00f1o Enr\u00edquez\nhttps://github.com/dagaren/gladiator-chess\n");
                this.runEngineIO("java -jar ./ce/gladiator_v0.0.7.jar");
                break;
            }
            case "GNU Chess": {
                final XChessFrame frame27 = XChessFrame.frame;
                XChessFrame.outputArea.append("Chessbox_gnu4j version 1.02 - is a port of GNU Chess 5.0.7 from C to Java.\nCreated by Xan Gregg. See: http://www.forthgo.com/chessbox/,\n");
                final XChessFrame frame28 = XChessFrame.frame;
                XChessFrame.outputArea.append("http://kirr.homeunix.org/chess/engines/Jim%20Ablett/GNUCHESS/\n");
                this.runEngineIO("java -jar ./ce/chessbox_gnu4j.jar");
                break;
            }
            case "Jchess": {
                final XChessFrame frame29 = XChessFrame.frame;
                XChessFrame.outputArea.append("JChess v1.0, created by Tomasz Michniewski - Poland\n");
                final XChessFrame frame30 = XChessFrame.frame;
                XChessFrame.outputArea.append("http://kirr.homeunix.org/chess/engines/Jim%20Ablett/JCHESS/\n");
                final XChessFrame frame31 = XChessFrame.frame;
                XChessFrame.outputArea.append("http://computer-chess.org/doku.php?id=computer_chess:wiki:download:engine_download_list\n");
                this.runEngineIO("java -jar ./ce/Jchess-1.0.jar");
                break;
            }
            case "Javalin": {
                final XChessFrame frame32 = XChessFrame.frame;
                XChessFrame.outputArea.append("Javalin v1.3.1, create by M\u0103nica Vlad Bogdan\nhttps://github.com/bugyvlad/javalin\n");
                this.runEngineIO("java -jar ./ce/javalin_v1.3.1.jar");
                break;
            }
            case "jChecs": {
                final XChessFrame frame33 = XChessFrame.frame;
                XChessFrame.outputArea.append("jChecs v0.1.0.1, create by David Cotton\nhttp://jchecs.free.fr/\nhttp://jchecs.sourceforge.net/\n");
                final StringBuilder append2 = new StringBuilder().append("java -jar ./ce/jchecs_v0.1.0.1.jar ");
                final Actions aktion6 = XChessFrame.aktion;
                this.runEngineIO(append2.append(Actions.jchecsEngineTip).toString());
                break;
            }
            case "Kasparov": {
                final XChessFrame frame34 = XChessFrame.frame;
                XChessFrame.outputArea.append("Kasparov Chess v1.0.0, create by Eric Liu\nhttps://github.com/eliucs/kasparov\n");
                this.runEngineIO("java -jar ./ce/KasparovChess-1.0.0.jar");
                break;
            }
            case "KennyClassIQ": {
                final XChessFrame frame35 = XChessFrame.frame;
                XChessFrame.outputArea.append("http://kennyclassiq.sourceforge.net/\nhttps://github.com/artfuldev/KennyClassIQ/\n");
                this.runEngineIO("java -jar ./ce/KennyClassIQ.jar");
                break;
            }
            case "KingsOut": {
                final XChessFrame frame36 = XChessFrame.frame;
                XChessFrame.outputArea.append("KingsOut v0.2.42, create by Bernd Nuernberger\nhttp://kirr.homeunix.org/chess/engines/Jim%20Ablett/KINGSOUT/\n");
                this.runEngineIO("java -jar ./ce/kingsout.jar");
                break;
            }
            case "Krudo": {
                final XChessFrame frame37 = XChessFrame.frame;
                XChessFrame.outputArea.append("Krudo v0.14b\nhttp://kirr.homeunix.org/chess/engines/Jim%20Ablett/KRUDO/\nhttp://www.g-sei.org/krudo/\n");
                this.runEngineIO("java -jar ./ce/Krudo.jar");
                break;
            }
            case "Magnum": {
                final XChessFrame frame38 = XChessFrame.frame;
                XChessFrame.outputArea.append("Magnum v4.0, create by Eric Stock\nhttps://code.google.com/archive/p/magnumchess/downloads\n");
                this.runEngineIO("java -jar ./ce/MagnumChess.jar");
                break;
            }
            case "Mediocre": {
                final XChessFrame frame39 = XChessFrame.frame;
                XChessFrame.outputArea.append("Mediocre v0.5, create by Jonatan Pettersson\nhttp://mediocrechess.blogspot.no/\nhttp://mediocrechess.sourceforge.net/\n");
                this.runEngineIO("java -jar ./ce/mediocre_v0.5.jar");
                break;
            }
            case "OliThink": {
                final XChessFrame frame40 = XChessFrame.frame;
                XChessFrame.outputArea.append("OliThink v5.3.2, create by Oliver Brausch\n");
                final XChessFrame frame41 = XChessFrame.frame;
                XChessFrame.outputArea.append("http://kirr.homeunix.org/chess/engines/Jim%20Ablett/OLITHINK/\n");
                this.runEngineIO("java -jar ./ce/OliThink532.jar");
                break;
            }
            case "Presbyter": {
                final XChessFrame frame42 = XChessFrame.frame;
                XChessFrame.outputArea.append("Presbyter v1.3.0, create by Jefferson Wilson\n");
                final XChessFrame frame43 = XChessFrame.frame;
                XChessFrame.outputArea.append("https://github.com/jwilson82/presbyter\n");
                this.runEngineIO("java -jar ./ce/presbyter-1.3.0.jar");
                break;
            }
            case "Phoenix": {
                final XChessFrame frame44 = XChessFrame.frame;
                XChessFrame.outputArea.append("Phoenix-Cuckoo v1.13a9, create by Rahul A R\nhttps://github.com/rahular/phoenix\n");
                this.runEngineIO("java -jar ./ce/github_rahular_phoenix.jar txt");
                break;
            }
            case "Pulse": {
                final XChessFrame frame45 = XChessFrame.frame;
                XChessFrame.outputArea.append("Pulse v1.6.1, created by Phokham Nonava\nhttps://github.com/fluxroot/pulse/releases/\nhttp://www.fluxchess.com/pulse/download/\n");
                this.runEngineIO("java -jar ./ce/pulse-1.6.1-java.jar");
                break;
            }
            case "Rival": {
                final XChessFrame frame46 = XChessFrame.frame;
                XChessFrame.outputArea.append("Rival build 0094, written by Russell Newman and Chris Moreton\nhttps://github.com/Netsensia/rival-chess-android-engine\nhttp://www.rivalchess.com/downloads/\n");
                this.runEngineIO("java -jar ./ce/Rival-0094.jar");
                break;
            }
            case "Rumney": {
                final XChessFrame frame47 = XChessFrame.frame;
                XChessFrame.outputArea.append("Rumney Chess v0.2.1\nhttps://github.com/Zaloum/\n");
                this.runEngineIO("java -jar ./ce/github_Zaloum_RumneyChess.jar");
                break;
            }
            case "Talvmenni": {
                final XChessFrame frame48 = XChessFrame.frame;
                XChessFrame.outputArea.append("Talvmenni v0.0.1\nhttp://talvmenni.sourceforge.net\n");
                final XChessFrame frame49 = XChessFrame.frame;
                XChessFrame.outputArea.append("http://kirr.homeunix.org/chess/engines/Jim%20Ablett/TALVMENNI/\n");
                this.runEngineIO("java -jar ./ce/talvmenni.jar");
                break;
            }
            case "Tiffanys": {
                final XChessFrame frame50 = XChessFrame.frame;
                XChessFrame.outputArea.append("Tiffanys v0.5, create by Bernhard von Gunten\nhttp://tiffanys.sourceforge.net\n");
                this.runEngineIO("java -jar ./ce/Tiffanys.jar xboard");
                break;
            }
            case "Tri-OS": {
                final XChessFrame frame51 = XChessFrame.frame;
                XChessFrame.outputArea.append("Tri-OS CS4210's Java xboard Chess Engine\nsee please: http://chess.dubmun.com/\n");
                this.runEngineIO("java -jar ./ce/Tri-OS_CS4210.jar -d 5");
                break;
            }
            case "Unidexter": {
                final XChessFrame frame52 = XChessFrame.frame;
                XChessFrame.outputArea.append("Unidexter v0.0.1, create by Michael Aherne\nhttp://computer-chess.org/lib/exe/fetch.php?media=computer_chess:wiki:download:unidexter-f5fb866-pn.jar\nhttps://github.com/micaherne/unidexter/\n");
                this.runEngineIO("java -jar ./ce/unidexter-f5fb866-pn.jar");
                break;
            }
            case "Ziggy": {
                final XChessFrame frame53 = XChessFrame.frame;
                XChessFrame.outputArea.append("Ziggy v0.7, create by Hrafn Eir\u00edksson\nhttp://kirr.homeunix.org/chess/engines/Jim%20Ablett/ZIGGY/\nhttps://github.com/krummi/ChessEngine/\n");
                this.runEngineIO("java -jar ./ce/ziggy.jar");
                break;
            }
        }
        this.initiate();
    }
    
    public void runEngineIO(final String cmd) {
        if (this.colorCE.equals("white")) {
            System.out.println("========================= START ENGINE =============================");
            EngineAgent.engineIOwhite = new EngineIO(cmd);
            System.out.println("White = " + cmd);
        }
        else {
            EngineAgent.engineIOblack = new EngineIO(cmd);
            System.out.println("Black = " + cmd);
        }
    }
    
    public static EngineAgent createEngine(final IChessContext context, final String goEngine, final String colorCE, final String ceTip) {
        EngineAgentExt.ceTip = ceTip;
        EngineAgentExt.goEngine = goEngine;
        EngineAgentExt.colorCE = colorCE;
        return new EngineAgentExt(context, goEngine, colorCE, ceTip);
    }
    
    @Override
    public void moveDeclared(final Move move) {
        this.chessClock.start();
        this.userMove(move);
    }
    
    public void addIEngineListener(final IEngineListener l) {
        this.listenerList.add(l);
    }
    
    public void removeIEngineListener(final IEngineListener l) {
        this.listenerList.remove(l);
    }
    
    protected void fireMovePrinted(final EngineEvent e) {
        if (this.listenerList == null) {
            return;
        }
        for (int count = this.listenerList.size(), i = 0; i < count; ++i) {
            this.listenerList.get(i).movePrinted(e);
        }
    }
    
    protected void fireIllegalPrinted(final EngineEvent e) {
        if (this.listenerList == null) {
            return;
        }
        for (int count = this.listenerList.size(), i = 0; i < count; ++i) {
            this.listenerList.get(i).illegalPrinted(e);
        }
    }
    
    protected void fireDataPrinted(final EngineEvent e) {
        if (this.listenerList == null) {
            return;
        }
        for (int count = this.listenerList.size(), i = 0; i < count; ++i) {
            this.listenerList.get(i).dataPrinted(e);
        }
    }
    
    protected void fireDataEntered(final EngineEvent e) {
        if (this.listenerList == null) {
            return;
        }
        for (int count = this.listenerList.size(), i = 0; i < count; ++i) {
            this.listenerList.get(i).dataEntered(e);
        }
    }
    
    protected void updateContext(final Move move) {
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
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void initiate() {
        (this.parseThread = new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        EngineAgent.this.parseCommand();
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    
    public ChessClock getChessClock() {
        return this.chessClock;
    }
    
    public void setChessClock(final ChessClock chessClock) {
        this.chessClock = chessClock;
    }
    
    public String readLine() throws IOException {
        String line = "";
        if (this.colorCE.equals("white")) {
            line = EngineAgent.engineIOwhite.readLine().toLowerCase();
        }
        else {
            line = EngineAgent.engineIOblack.readLine().toLowerCase();
        }
        if (line.length() > 0) {
            final XChessFrame frame = XChessFrame.frame;
            XChessFrame.outputArea.append("<read from " + this.colorCE.toUpperCase() + ">: " + line + "\n");
        }
        if (line == null || line.length() == 0 || line.startsWith("info")) {
            return null;
        }
        Label_0221: {
            if (line.contains("bestmove")) {
                final Actions aktion = XChessFrame.aktion;
                if (!Actions.whiteRivalMovesString.endsWith(line.substring(9))) {
                    final Actions aktion2 = XChessFrame.aktion;
                    if (!Actions.blackRivalMovesString.endsWith(line.substring(9))) {
                        break Label_0221;
                    }
                }
                line = "";
                final Actions aktion3 = XChessFrame.aktion;
                Actions.sendEngineCmd("white", "quit");
                final Actions aktion4 = XChessFrame.aktion;
                Actions.sendEngineCmd("black", "quit");
                new Msg_Thread(this.colorCE.toUpperCase() + " SAY: end game !");
            }
        }
        if ((line.contains("bestmove") && (line.contains("can't move") || line.contains("nomove") || line.contains("0000") || line.contains("null") || line.contains("none") || line.contains("a1a1"))) || line.contains("resign") || line.contains("mates") || line.contains("mated") || line.contains("black checkmate") || line.contains("white checkmate") || line.contains("computer wins") || line.contains("stalemate") || (line.contains("1/2") && (line.contains("move rule") || line.contains("moves rule") || line.contains("repetition"))) || line.contains("{checkmate}") || line.contains("game over")) {
            final Actions aktion5 = XChessFrame.aktion;
            Actions.sendEngineCmd("white", "quit");
            final Actions aktion6 = XChessFrame.aktion;
            Actions.sendEngineCmd("black", "quit");
            new Msg_Thread(this.colorCE.toUpperCase() + " SAY: " + line);
        }
        final Actions aktion7 = XChessFrame.aktion;
        Actions.enginePromotionType = "";
        if (this.ceTip.equals("uci")) {
            if (line.contains("bestmove")) {
                line = line.replaceAll("bestmove", "move");
            }
            if (line.contains("move") && line.contains("ponder")) {
                final String[] bf = line.trim().split("\\s+");
                line = bf[0] + " " + bf[1];
            }
        }
        if (line.contains("my move is:")) {
            line = line.replaceAll("my move is:", "move");
        }
        if (line.contains("move")) {
            if (line.endsWith("n") && line.length() == 10) {
                final Actions aktion8 = XChessFrame.aktion;
                Actions.enginePromotionType = "n";
                final Actions aktion9 = XChessFrame.aktion;
                Actions.enginePromotionFig = "n";
                final Actions aktion10 = XChessFrame.aktion;
                Actions.promotionCount = MoveListUI.count;
            }
            if (line.endsWith("b") && line.length() == 10) {
                final Actions aktion11 = XChessFrame.aktion;
                Actions.enginePromotionType = "b";
                final Actions aktion12 = XChessFrame.aktion;
                Actions.enginePromotionFig = "b";
                final Actions aktion13 = XChessFrame.aktion;
                Actions.promotionCount = MoveListUI.count;
            }
            if (line.endsWith("r") && line.length() == 10) {
                final Actions aktion14 = XChessFrame.aktion;
                Actions.enginePromotionType = "r";
                final Actions aktion15 = XChessFrame.aktion;
                Actions.enginePromotionFig = "r";
                final Actions aktion16 = XChessFrame.aktion;
                Actions.promotionCount = MoveListUI.count;
            }
            if (line.endsWith("q") && line.length() == 10) {
                final Actions aktion17 = XChessFrame.aktion;
                Actions.enginePromotionType = "q";
                final Actions aktion18 = XChessFrame.aktion;
                Actions.enginePromotionFig = "q";
                final Actions aktion19 = XChessFrame.aktion;
                Actions.promotionCount = MoveListUI.count;
            }
            if (line.endsWith("o-o") && !line.endsWith("o-o-o")) {
                if (this.colorCE.equals("black")) {
                    line = "move e8g8";
                }
                else {
                    line = "move e1g1";
                }
                System.out.println("KingSide Castle o-o = " + line);
            }
            if (line.endsWith("0-0") && !line.endsWith("0-0-0")) {
                if (this.colorCE.equals("black")) {
                    line = "move e8g8";
                }
                else {
                    line = "move e1g1";
                }
                System.out.println("KingSide Castle Zero-Zero = " + line);
            }
            if (line.endsWith("o-o-o")) {
                if (this.colorCE.equals("black")) {
                    line = "move e8c8";
                }
                else {
                    line = "move e1c1";
                }
                System.out.println("QueenSide Castle o-o-o = " + line);
            }
            if (line.endsWith("0-0-0")) {
                if (this.colorCE.equals("black")) {
                    line = "move e8c8";
                }
                else {
                    line = "move e1c1";
                }
                System.out.println("QueenSide Castle Zero-Zero-Zero = " + line);
            }
        }
        this.fireDataPrinted(new EngineEvent(this, line));
        return line;
    }
    
    public void writeLine(final String string) {
        final XChessFrame frame = XChessFrame.frame;
        XChessFrame.outputArea.append("<write to " + this.colorCE.toUpperCase() + ">: " + string + "\n");
        this.fireDataEntered(new EngineEvent(this, string));
        if (this.colorCE.equals("white")) {
            if (this.ceTip.equals("uci") && MoveListUI.count % 2 != 1) {
                final Actions aktion = XChessFrame.aktion;
                if (Actions.enemyTip.equals("another") && string.length() == 4 && !string.equals("easy") && !string.equals("hard") && !string.equals("post") && !string.startsWith("sd")) {
                    final StringBuilder append = new StringBuilder().append("position startpos moves");
                    final Actions aktion2 = XChessFrame.aktion;
                    this.writeLine(append.append(Actions.uciAllMovesString).toString());
                    final Actions aktion3 = XChessFrame.aktion;
                    if (Actions.UseClock.equals("true") && !this.goEngine.equals("Magnum")) {
                        final StringBuilder append2 = new StringBuilder().append("go depth ");
                        final Actions aktion4 = XChessFrame.aktion;
                        final StringBuilder append3 = append2.append(Actions.Depth).append(" wtime ");
                        final XChessFrame frame2 = XChessFrame.frame;
                        final StringBuilder append4 = append3.append(XChessFrame.chessClock.getTime(0)).append(" btime ");
                        final XChessFrame frame3 = XChessFrame.frame;
                        this.writeLine(append4.append(XChessFrame.chessClock.getTime(1)).append(" winc 0 binc 0").toString());
                    }
                    else {
                        final StringBuilder append5 = new StringBuilder().append("go depth ");
                        final Actions aktion5 = XChessFrame.aktion;
                        this.writeLine(append5.append(Actions.Depth).toString());
                    }
                    return;
                }
                final Actions aktion6 = XChessFrame.aktion;
                if (Actions.enemyTip.equals("another") && string.length() == 5 && (string.endsWith("r") || string.endsWith("n") || string.endsWith("b") || string.endsWith("q"))) {
                    final StringBuilder append6 = new StringBuilder().append("position startpos moves");
                    final Actions aktion7 = XChessFrame.aktion;
                    this.writeLine(append6.append(Actions.uciAllMovesString).toString());
                    final Actions aktion8 = XChessFrame.aktion;
                    if (Actions.UseClock.equals("true") && !this.goEngine.equals("Magnum")) {
                        final StringBuilder append7 = new StringBuilder().append("go depth ");
                        final Actions aktion9 = XChessFrame.aktion;
                        final StringBuilder append8 = append7.append(Actions.Depth).append(" wtime ");
                        final XChessFrame frame4 = XChessFrame.frame;
                        final StringBuilder append9 = append8.append(XChessFrame.chessClock.getTime(0)).append(" btime ");
                        final XChessFrame frame5 = XChessFrame.frame;
                        this.writeLine(append9.append(XChessFrame.chessClock.getTime(1)).append(" winc 0 binc 0").toString());
                    }
                    else {
                        final StringBuilder append10 = new StringBuilder().append("go depth ");
                        final Actions aktion10 = XChessFrame.aktion;
                        this.writeLine(append10.append(Actions.Depth).toString());
                    }
                    return;
                }
            }
            final Actions aktion11 = XChessFrame.aktion;
            Label_0697: {
                if (!Actions.whitePlayerCE.equals("FairyPrincess")) {
                    final Actions aktion12 = XChessFrame.aktion;
                    if (!Actions.whitePlayerCE.equals("Talvmenni")) {
                        final Actions aktion13 = XChessFrame.aktion;
                        if (!Actions.whitePlayerCE.equals("FrankWalter")) {
                            final Actions aktion14 = XChessFrame.aktion;
                            if (!Actions.whitePlayerCE.equals("KennyClassIQ")) {
                                break Label_0697;
                            }
                        }
                    }
                }
                if (string.length() == 4 && !string.equals("easy") && !string.equals("hard") && !string.equals("post") && !string.startsWith("sd")) {
                    EngineAgent.engineIOwhite.writeLine("usermove " + string);
                    return;
                }
            }
            final Actions aktion15 = XChessFrame.aktion;
            Label_0834: {
                if (!Actions.whitePlayerCE.equals("FairyPrincess")) {
                    final Actions aktion16 = XChessFrame.aktion;
                    if (!Actions.whitePlayerCE.equals("Talvmenni")) {
                        final Actions aktion17 = XChessFrame.aktion;
                        if (!Actions.whitePlayerCE.equals("FrankWalter")) {
                            final Actions aktion18 = XChessFrame.aktion;
                            if (!Actions.whitePlayerCE.equals("KennyClassIQ")) {
                                break Label_0834;
                            }
                        }
                    }
                }
                if (string.length() == 5 && (string.endsWith("r") || string.endsWith("n") || string.endsWith("b") || string.endsWith("q"))) {
                    EngineAgent.engineIOwhite.writeLine("usermove " + string);
                    return;
                }
            }
            EngineAgent.engineIOwhite.writeLine(string);
            return;
        }
        if (!this.colorCE.equals("black")) {
            return;
        }
        final Actions aktion19 = XChessFrame.aktion;
        if (!Actions.blackPlayerCE.equals("FairyPrincess")) {
            final Actions aktion20 = XChessFrame.aktion;
            if (!Actions.blackPlayerCE.equals("Talvmenni")) {
                final Actions aktion21 = XChessFrame.aktion;
                if (!Actions.blackPlayerCE.equals("FrankWalter")) {
                    final Actions aktion22 = XChessFrame.aktion;
                    if (!Actions.blackPlayerCE.equals("KennyClassIQ")) {
                        EngineAgent.engineIOblack.writeLine(string);
                        return;
                    }
                }
            }
        }
        if (string.length() == 4 && !string.equals("easy") && !string.equals("hard") && !string.equals("post") && !string.startsWith("sd")) {
            EngineAgent.engineIOblack.writeLine("usermove " + string);
            return;
        }
        if (string.length() == 5 && (string.endsWith("r") || string.endsWith("n") || string.endsWith("b") || string.endsWith("q"))) {
            EngineAgent.engineIOblack.writeLine("usermove " + string);
            return;
        }
        EngineAgent.engineIOblack.writeLine(string);
    }
    
    protected abstract void parseCommand() throws IOException;
    
    public abstract void userMove(final Move p0);
    
    public abstract void quitEngine();
    
    @Override
    public void dispose() {
        super.dispose();
        this.parseThread = null;
        this.quitEngine();
        if (this.colorCE.equals("white")) {
            EngineAgent.engineIOwhite.destroy();
        }
        else {
            EngineAgent.engineIOblack.destroy();
        }
    }
    
    static {
        EngineAgent.argsBagatur1cpu = new String[] { " bagaturchess.engines.base.cfg.UCIConfig_BaseImpl", " bagaturchess.search.impl.uci_adaptor.UCISearchAdaptorImpl_PonderingOpponentMove", " bagaturchess.engines.base.cfg.UCISearchAdaptorConfig_BaseImpl", " bagaturchess.search.impl.rootsearch.parallel.MTDParallelSearch", " bagaturchess.engines.base.cfg.RootSearchConfig_BaseImpl_SMP", " bagaturchess.search.impl.alg.impl0.SearchMTD0", " bagaturchess.engines.bagatur.cfg.search.SearchConfigImpl_MTD_SMP", " bagaturchess.engines.bagatur.cfg.board.BoardConfigImpl", " bagaturchess.engines.bagatur.cfg.eval.BagaturEvalConfigImpl_v2" };
        EngineAgent.argsBagaturNcpu = new String[] { " bagaturchess.engines.base.cfg.UCIConfig_BaseImpl", " bagaturchess.search.impl.uci_adaptor.UCISearchAdaptorImpl_PonderingOpponentMove", " bagaturchess.engines.base.cfg.UCISearchAdaptorConfig_BaseImpl", " bagaturchess.search.impl.rootsearch.parallel.MTDParallelSearch", " bagaturchess.engines.base.cfg.RootSearchConfig_BaseImpl_SMP", " bagaturchess.search.impl.alg.impl0.SearchMTD0", " bagaturchess.engines.bagatur.cfg.search.SearchConfigImpl_MTD_SMP", " bagaturchess.engines.bagatur.cfg.board.BoardConfigImpl", " bagaturchess.engines.bagatur.cfg.eval.BagaturEvalConfigImpl_v2" };
    }
}
