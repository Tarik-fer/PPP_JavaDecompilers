// 
// Decompiled by Procyon v0.5.36
// 

package SJCE.xgui.Agent;

import SJCE.xgui.JList.MoveListUI;
import java.io.IOException;
import SJCE.xgui.JPanel.BoardUI;
import SJCE.xgui.Move;
import java.util.regex.Matcher;
import SJCE.xgui.EventObject.MoveEvent;
import SJCE.xgui.EventObject.EngineEvent;
import SJCE.xgui.Notation;
import SJCE.more.Actions;
import SJCE.XChessFrame;
import SJCE.xgui.Interfaces.IChessContext;
import java.util.regex.Pattern;

public class EngineAgentExt extends EngineAgent
{
    Pattern patternMove;
    Pattern patternIllegal;
    public static String ceTip;
    public static String goEngine;
    public static String colorCE;
    
    public EngineAgentExt(final IChessContext context, final String goEngine, final String colorCE, final String ceTip) {
        super(context, goEngine, colorCE, ceTip);
        this.patternMove = Pattern.compile("(my)?\\s*move\\s*(is)?\\s*[:>=\\-]?\\s*([a-h][1-8][a-h][1-8][nbrq]?)", 2);
        this.patternIllegal = Pattern.compile("(Illegal move.+)|(Error.+)", 2);
        EngineAgentExt.ceTip = ceTip;
        EngineAgentExt.goEngine = goEngine;
        EngineAgentExt.colorCE = colorCE;
    }
    
    @Override
    public void initiate() {
        super.initiate();
        if (EngineAgentExt.ceTip.equals("xboard")) {
            this.writeLine("xboard");
            if (EngineAgentExt.goEngine.equals("Animats") || EngineAgentExt.goEngine.equals("FrankWalter") || EngineAgentExt.goEngine.equals("KennyClassIQ")) {
                this.writeLine("protover 2");
            }
            this.writeLine("new");
            final String goEngine = EngineAgentExt.goEngine;
            switch (goEngine) {
                case "Alf": {
                    final Actions aktion = XChessFrame.aktion;
                    this.writeLine(Actions.Mode);
                    final StringBuilder append = new StringBuilder().append("sd ");
                    final Actions aktion2 = XChessFrame.aktion;
                    this.writeLine(append.append(Actions.Depth).toString());
                    final StringBuilder append2 = new StringBuilder().append("level ");
                    final int n2 = 60;
                    final Actions aktion3 = XChessFrame.aktion;
                    final StringBuilder append3 = append2.append(n2 * Actions.Time).append(" ");
                    final Actions aktion4 = XChessFrame.aktion;
                    this.writeLine(append3.append(Actions.Time).append(" 0").toString());
                    this.writeLine("post");
                    break;
                }
                case "ArabianKnight": {
                    final Actions aktion5 = XChessFrame.aktion;
                    this.writeLine(Actions.Mode);
                    final StringBuilder append4 = new StringBuilder().append("sd ");
                    final Actions aktion6 = XChessFrame.aktion;
                    this.writeLine(append4.append(Actions.Depth).toString());
                    final StringBuilder append5 = new StringBuilder().append("level ");
                    final int n3 = 60;
                    final Actions aktion7 = XChessFrame.aktion;
                    final StringBuilder append6 = append5.append(n3 * Actions.Time).append(" ");
                    final Actions aktion8 = XChessFrame.aktion;
                    this.writeLine(append6.append(Actions.Time).append(" 0").toString());
                    this.writeLine("post");
                    break;
                }
                case "BremboCE": {
                    final StringBuilder append7 = new StringBuilder().append("sd ");
                    final Actions aktion9 = XChessFrame.aktion;
                    this.writeLine(append7.append(Actions.Depth).toString());
                    final StringBuilder append8 = new StringBuilder().append("level ");
                    final int n4 = 60;
                    final Actions aktion10 = XChessFrame.aktion;
                    final StringBuilder append9 = append8.append(n4 * Actions.Time).append(" ");
                    final Actions aktion11 = XChessFrame.aktion;
                    this.writeLine(append9.append(Actions.Time).append(" 0").toString());
                    this.writeLine("post");
                    break;
                }
                case "CupCake": {
                    final Actions aktion12 = XChessFrame.aktion;
                    this.writeLine(Actions.Mode);
                    final StringBuilder append10 = new StringBuilder().append("sd ");
                    final Actions aktion13 = XChessFrame.aktion;
                    this.writeLine(append10.append(Actions.Depth).toString());
                    final StringBuilder append11 = new StringBuilder().append("level ");
                    final int n5 = 60;
                    final Actions aktion14 = XChessFrame.aktion;
                    final StringBuilder append12 = append11.append(n5 * Actions.Time).append(" ");
                    final Actions aktion15 = XChessFrame.aktion;
                    this.writeLine(append12.append(Actions.Time).append(" 0").toString());
                    this.writeLine("post");
                    this.writeLine("new");
                    break;
                }
                case "CaveChess": {
                    final StringBuilder append13 = new StringBuilder().append("sd ");
                    final Actions aktion16 = XChessFrame.aktion;
                    this.writeLine(append13.append(Actions.Depth).toString());
                    break;
                }
                case "DeepBrutePos": {
                    final Actions aktion17 = XChessFrame.aktion;
                    this.writeLine(Actions.Mode);
                    final StringBuilder append14 = new StringBuilder().append("sd ");
                    final Actions aktion18 = XChessFrame.aktion;
                    this.writeLine(append14.append(Actions.Depth).toString());
                    final StringBuilder append15 = new StringBuilder().append("level ");
                    final int n6 = 60;
                    final Actions aktion19 = XChessFrame.aktion;
                    final StringBuilder append16 = append15.append(n6 * Actions.Time).append(" ");
                    final Actions aktion20 = XChessFrame.aktion;
                    this.writeLine(append16.append(Actions.Time).append(" 0").toString());
                    this.writeLine("post");
                    break;
                }
                case "FairyPrincess": {
                    this.writeLine(EngineAgentExt.colorCE);
                    final StringBuilder append17 = new StringBuilder().append("time ");
                    final int n7 = 6000;
                    final Actions aktion21 = XChessFrame.aktion;
                    this.writeLine(append17.append(n7 * Actions.Time).toString());
                    break;
                }
                case "Frittle": {
                    final Actions aktion22 = XChessFrame.aktion;
                    this.writeLine(Actions.Mode);
                    final StringBuilder append18 = new StringBuilder().append("sd ");
                    final Actions aktion23 = XChessFrame.aktion;
                    this.writeLine(append18.append(Actions.Depth).toString());
                    final StringBuilder append19 = new StringBuilder().append("level ");
                    final int n8 = 60;
                    final Actions aktion24 = XChessFrame.aktion;
                    final StringBuilder append20 = append19.append(n8 * Actions.Time).append(" ");
                    final Actions aktion25 = XChessFrame.aktion;
                    this.writeLine(append20.append(Actions.Time).append(" 0").toString());
                    this.writeLine("post");
                    break;
                }
                case "FrankWalter": {
                    final Actions aktion26 = XChessFrame.aktion;
                    this.writeLine(Actions.Mode);
                    final StringBuilder append21 = new StringBuilder().append("sd ");
                    final Actions aktion27 = XChessFrame.aktion;
                    this.writeLine(append21.append(Actions.Depth).toString());
                    final StringBuilder append22 = new StringBuilder().append("level ");
                    final int n9 = 60;
                    final Actions aktion28 = XChessFrame.aktion;
                    final StringBuilder append23 = append22.append(n9 * Actions.Time).append(" ");
                    final Actions aktion29 = XChessFrame.aktion;
                    this.writeLine(append23.append(Actions.Time).append(" 0").toString());
                    this.writeLine("post");
                    break;
                }
                case "Gladiator": {
                    final Actions aktion30 = XChessFrame.aktion;
                    this.writeLine(Actions.Mode);
                    final StringBuilder append24 = new StringBuilder().append("sd ");
                    final Actions aktion31 = XChessFrame.aktion;
                    this.writeLine(append24.append(Actions.Depth).toString());
                    this.writeLine("post");
                    break;
                }
                case "GNU Chess": {
                    final Actions aktion32 = XChessFrame.aktion;
                    this.writeLine(Actions.Mode);
                    final StringBuilder append25 = new StringBuilder().append("depth ");
                    final Actions aktion33 = XChessFrame.aktion;
                    this.writeLine(append25.append(Actions.Depth).toString());
                    final StringBuilder append26 = new StringBuilder().append("time ");
                    final int n10 = 60;
                    final Actions aktion34 = XChessFrame.aktion;
                    this.writeLine(append26.append(n10 * Actions.Time).toString());
                    final StringBuilder append27 = new StringBuilder().append("level ");
                    final int n11 = 60;
                    final Actions aktion35 = XChessFrame.aktion;
                    final StringBuilder append28 = append27.append(n11 * Actions.Time).append(" ");
                    final Actions aktion36 = XChessFrame.aktion;
                    this.writeLine(append28.append(Actions.Time).append(" 0").toString());
                    this.writeLine("post");
                    break;
                }
                case "Javalin": {
                    final Actions aktion37 = XChessFrame.aktion;
                    this.writeLine(Actions.Mode);
                    final StringBuilder append29 = new StringBuilder().append("sd ");
                    final Actions aktion38 = XChessFrame.aktion;
                    this.writeLine(append29.append(Actions.Depth).toString());
                    final StringBuilder append30 = new StringBuilder().append("level ");
                    final int n12 = 60;
                    final Actions aktion39 = XChessFrame.aktion;
                    final StringBuilder append31 = append30.append(n12 * Actions.Time).append(" ");
                    final Actions aktion40 = XChessFrame.aktion;
                    this.writeLine(append31.append(Actions.Time).append(" 0").toString());
                    this.writeLine("post");
                    break;
                }
                case "Jchess": {
                    final StringBuilder append32 = new StringBuilder().append("level ");
                    final int n13 = 60;
                    final Actions aktion41 = XChessFrame.aktion;
                    final StringBuilder append33 = append32.append(n13 * Actions.Time).append(" ");
                    final Actions aktion42 = XChessFrame.aktion;
                    this.writeLine(append33.append(Actions.Time).append(" 0").toString());
                    this.writeLine("post");
                    break;
                }
                case "jChecs": {
                    final Actions aktion43 = XChessFrame.aktion;
                    this.writeLine(Actions.Mode);
                    final StringBuilder append34 = new StringBuilder().append("sd ");
                    final Actions aktion44 = XChessFrame.aktion;
                    this.writeLine(append34.append(Actions.Depth).toString());
                    break;
                }
                case "KingsOut": {
                    final Actions aktion45 = XChessFrame.aktion;
                    this.writeLine(Actions.Mode);
                    final StringBuilder append35 = new StringBuilder().append("sd ");
                    final Actions aktion46 = XChessFrame.aktion;
                    this.writeLine(append35.append(Actions.Depth).toString());
                    final StringBuilder append36 = new StringBuilder().append("level ");
                    final int n14 = 60;
                    final Actions aktion47 = XChessFrame.aktion;
                    final StringBuilder append37 = append36.append(n14 * Actions.Time).append(" ");
                    final Actions aktion48 = XChessFrame.aktion;
                    this.writeLine(append37.append(Actions.Time).append(" 0").toString());
                    this.writeLine("post");
                    break;
                }
                case "KennyClassIQ": {
                    final Actions aktion49 = XChessFrame.aktion;
                    this.writeLine(Actions.Mode);
                    final StringBuilder append38 = new StringBuilder().append("sd ");
                    final Actions aktion50 = XChessFrame.aktion;
                    this.writeLine(append38.append(Actions.Depth).toString());
                    final StringBuilder append39 = new StringBuilder().append("level ");
                    final int n15 = 60;
                    final Actions aktion51 = XChessFrame.aktion;
                    final StringBuilder append40 = append39.append(n15 * Actions.Time).append(" ");
                    final Actions aktion52 = XChessFrame.aktion;
                    this.writeLine(append40.append(Actions.Time).append(" 0").toString());
                    this.writeLine("post");
                    break;
                }
                case "OliThink": {
                    final Actions aktion53 = XChessFrame.aktion;
                    this.writeLine(Actions.Mode);
                    final StringBuilder append41 = new StringBuilder().append("sd ");
                    final Actions aktion54 = XChessFrame.aktion;
                    this.writeLine(append41.append(Actions.Depth).toString());
                    final StringBuilder append42 = new StringBuilder().append("level ");
                    final int n16 = 60;
                    final Actions aktion55 = XChessFrame.aktion;
                    final StringBuilder append43 = append42.append(n16 * Actions.Time).append(" ");
                    final Actions aktion56 = XChessFrame.aktion;
                    this.writeLine(append43.append(Actions.Time).append(" 0").toString());
                    this.writeLine("post");
                    break;
                }
                case "Tiffanys": {
                    final Actions aktion57 = XChessFrame.aktion;
                    this.writeLine(Actions.Mode);
                    break;
                }
                case "Talvmenni": {
                    final Actions aktion58 = XChessFrame.aktion;
                    this.writeLine(Actions.Mode);
                    final StringBuilder append44 = new StringBuilder().append("sd ");
                    final Actions aktion59 = XChessFrame.aktion;
                    this.writeLine(append44.append(Actions.Depth).toString());
                    final StringBuilder append45 = new StringBuilder().append("level ");
                    final int n17 = 60;
                    final Actions aktion60 = XChessFrame.aktion;
                    final StringBuilder append46 = append45.append(n17 * Actions.Time).append(" ");
                    final Actions aktion61 = XChessFrame.aktion;
                    this.writeLine(append46.append(Actions.Time).append(" 0").toString());
                    this.writeLine("post");
                    break;
                }
                case "Tri-OS": {
                    final StringBuilder append47 = new StringBuilder().append("level ");
                    final int n18 = 60;
                    final Actions aktion62 = XChessFrame.aktion;
                    final StringBuilder append48 = append47.append(n18 * Actions.Time).append(" ");
                    final Actions aktion63 = XChessFrame.aktion;
                    this.writeLine(append48.append(Actions.Time).append(" 0").toString());
                    this.writeLine("post");
                    break;
                }
            }
        }
        if (EngineAgentExt.ceTip.equals("uci")) {
            this.writeLine("uci");
            this.writeLine("isready");
            this.writeLine("ucinewgame");
            this.writeLine("isready");
            final Actions aktion64 = XChessFrame.aktion;
            if (Actions.Mode.equals("hard")) {
                this.writeLine("setoption name Ponder value true");
            }
            else {
                this.writeLine("setoption name Ponder value false");
            }
        }
    }
    
    @Override
    public void newGame() {
    }
    
    @Override
    protected void parseCommand() throws IOException {
        final String line = this.readLine();
        if (line == null) {
            return;
        }
        Matcher matcher = this.patternMove.matcher(line);
        if (matcher.matches()) {
            final Move move = Notation.toMove(matcher.group(3).substring(0, 4));
            this.updateContext(move);
            final boolean b = move.getPiece() == 6;
            final Actions aktion = XChessFrame.aktion;
            final boolean b2 = b & Actions.Prohod_White_Event == 1;
            final int source = move.getSource();
            final Actions aktion2 = XChessFrame.aktion;
            final boolean b3 = b2 & Math.abs(source - Actions.Prohod_White_Destination) == 1;
            final int destination = move.getDestination();
            final Actions aktion3 = XChessFrame.aktion;
            if (b3 & Math.abs(destination - Actions.Prohod_White_Destination) == 8) {
                System.out.println("Black Pawn En-Passant !");
                final BoardUI boardUI = this.boardUI;
                final int piece = -1;
                final Actions aktion4 = XChessFrame.aktion;
                boardUI.setPiece(piece, Actions.Prohod_White_Destination);
                final BoardUI boardUI2 = this.boardUI;
                final Actions aktion5 = XChessFrame.aktion;
                boardUI2.update(Actions.Prohod_White_Destination);
                final Actions aktion6 = XChessFrame.aktion;
                Actions.Prohod_White_Event = 0;
                final Actions aktion7 = XChessFrame.aktion;
                Actions.Prohod_White_Destination = -1;
            }
            final boolean b4 = move.getPiece() == 0;
            final Actions aktion8 = XChessFrame.aktion;
            final boolean b5 = b4 & Actions.Prohod_Black_Event == 1;
            final int source2 = move.getSource();
            final Actions aktion9 = XChessFrame.aktion;
            final boolean b6 = b5 & Math.abs(source2 - Actions.Prohod_Black_Destination) == 1;
            final int destination2 = move.getDestination();
            final Actions aktion10 = XChessFrame.aktion;
            if (b6 & Math.abs(destination2 - Actions.Prohod_Black_Destination) == 8) {
                System.out.println("White Pawn En-Passant !");
                final BoardUI boardUI3 = this.boardUI;
                final int piece2 = -1;
                final Actions aktion11 = XChessFrame.aktion;
                boardUI3.setPiece(piece2, Actions.Prohod_Black_Destination);
                final BoardUI boardUI4 = this.boardUI;
                final Actions aktion12 = XChessFrame.aktion;
                boardUI4.update(Actions.Prohod_Black_Destination);
                final Actions aktion13 = XChessFrame.aktion;
                Actions.Prohod_Black_Event = 0;
                final Actions aktion14 = XChessFrame.aktion;
                Actions.Prohod_Black_Destination = -1;
            }
            if (move.getPiece() == 6 & move.getSource() >= 8 & move.getSource() <= 15) {
                System.out.println("Black Pawn Promotion !!!!");
                final Actions aktion15 = XChessFrame.aktion;
                final String enginePromotionFig = Actions.enginePromotionFig;
                switch (enginePromotionFig) {
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
                        break;
                    }
                }
                this.boardUI.update(move.getDestination());
            }
            if (move.getPiece() == 0 & move.getSource() >= 48 & move.getSource() <= 55) {
                System.out.println("White Pawn Promotion !!!!");
                final Actions aktion16 = XChessFrame.aktion;
                final String enginePromotionFig2 = Actions.enginePromotionFig;
                switch (enginePromotionFig2) {
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
                        break;
                    }
                }
                this.boardUI.update(move.getDestination());
            }
            this.fireMovePrinted(new EngineEvent(this, matcher.group(3)));
            this.fireMovePerformed(new MoveEvent(this, move));
        }
        matcher = this.patternIllegal.matcher(line);
        if (matcher.matches()) {
            this.fireIllegalPrinted(new EngineEvent(this));
        }
    }
    
    @Override
    public void userMove(final Move move) {
        final String usercmd = Notation.toString(move);
        if (move.getPiece() == 0 & Math.abs(move.getDestination() - move.getSource()) == 16) {
            System.out.println("White Pawn 2-Prohod !");
            final Actions aktion = XChessFrame.aktion;
            Actions.Prohod_White_Event = 1;
            final Actions aktion2 = XChessFrame.aktion;
            Actions.Prohod_White_Destination = move.getDestination();
        }
        if (move.getPiece() == 6 & Math.abs(move.getDestination() - move.getSource()) == 16) {
            System.out.println("Black Pawn 2-Prohod !");
            final Actions aktion3 = XChessFrame.aktion;
            Actions.Prohod_Black_Event = 1;
            final Actions aktion4 = XChessFrame.aktion;
            Actions.Prohod_Black_Destination = move.getDestination();
        }
        if (move.getPiece() == 0 & move.getSource() >= 48 & move.getSource() <= 55) {
            if (EngineAgentExt.ceTip.equals("xboard")) {
                final String goEngine = EngineAgentExt.goEngine;
                switch (goEngine) {
                    case "Animats": {
                        this.writeLine(usercmd);
                        break;
                    }
                    case "FrankWalter": {
                        this.writeLine("usermove " + usercmd + "q");
                        break;
                    }
                    case "KennyClassIQ": {
                        this.writeLine("usermove " + usercmd + "q");
                        break;
                    }
                    case "Talvmenni": {
                        this.writeLine("usermove " + usercmd + "q");
                        break;
                    }
                    case "FairyPrincess": {
                        this.writeLine("usermove " + usercmd + "q");
                        break;
                    }
                    default: {
                        this.writeLine(usercmd + "q");
                        break;
                    }
                }
            }
            else {
                final Actions aktion5 = XChessFrame.aktion;
                final StringBuilder sb = new StringBuilder();
                final Actions aktion6 = XChessFrame.aktion;
                Actions.uciAllMovesString = sb.append(Actions.uciAllMovesString).append("q").toString();
                final StringBuilder append = new StringBuilder().append("position startpos moves");
                final Actions aktion7 = XChessFrame.aktion;
                this.writeLine(append.append(Actions.uciAllMovesString).toString());
                final Actions aktion8 = XChessFrame.aktion;
                if (Actions.UseClock.equals("true") && !EngineAgentExt.goEngine.equals("Magnum")) {
                    final StringBuilder append2 = new StringBuilder().append("go depth ");
                    final Actions aktion9 = XChessFrame.aktion;
                    final StringBuilder append3 = append2.append(Actions.Depth).append(" wtime ");
                    final XChessFrame frame = XChessFrame.frame;
                    final StringBuilder append4 = append3.append(XChessFrame.chessClock.getTime(0)).append(" btime ");
                    final XChessFrame frame2 = XChessFrame.frame;
                    this.writeLine(append4.append(XChessFrame.chessClock.getTime(1)).append(" winc 0 binc 0").toString());
                }
                else {
                    final StringBuilder append5 = new StringBuilder().append("go depth ");
                    final Actions aktion10 = XChessFrame.aktion;
                    this.writeLine(append5.append(Actions.Depth).toString());
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
                final String goEngine2 = EngineAgentExt.goEngine;
                switch (goEngine2) {
                    case "Animats": {
                        this.writeLine(usercmd);
                        break;
                    }
                    case "FrankWalter": {
                        this.writeLine("usermove " + usercmd + "q");
                        break;
                    }
                    case "KennyClassIQ": {
                        this.writeLine("usermove " + usercmd + "q");
                        break;
                    }
                    case "Talvmenni": {
                        this.writeLine("usermove " + usercmd + "q");
                        break;
                    }
                    case "FairyPrincess": {
                        this.writeLine("usermove " + usercmd + "q");
                        break;
                    }
                    default: {
                        this.writeLine(usercmd + "q");
                        break;
                    }
                }
            }
            else {
                final Actions aktion11 = XChessFrame.aktion;
                final StringBuilder sb2 = new StringBuilder();
                final Actions aktion12 = XChessFrame.aktion;
                Actions.uciAllMovesString = sb2.append(Actions.uciAllMovesString).append("q").toString();
                final StringBuilder append6 = new StringBuilder().append("position startpos moves");
                final Actions aktion13 = XChessFrame.aktion;
                this.writeLine(append6.append(Actions.uciAllMovesString).toString());
                final Actions aktion14 = XChessFrame.aktion;
                if (Actions.UseClock.equals("true") && !EngineAgentExt.goEngine.equals("Magnum")) {
                    final StringBuilder append7 = new StringBuilder().append("go depth ");
                    final Actions aktion15 = XChessFrame.aktion;
                    final StringBuilder append8 = append7.append(Actions.Depth).append(" wtime ");
                    final XChessFrame frame3 = XChessFrame.frame;
                    final StringBuilder append9 = append8.append(XChessFrame.chessClock.getTime(0)).append(" btime ");
                    final XChessFrame frame4 = XChessFrame.frame;
                    this.writeLine(append9.append(XChessFrame.chessClock.getTime(1)).append(" winc 0 binc 0").toString());
                }
                else {
                    final StringBuilder append10 = new StringBuilder().append("go depth ");
                    final Actions aktion16 = XChessFrame.aktion;
                    this.writeLine(append10.append(Actions.Depth).toString());
                }
            }
            System.out.println("Black Pawn to Queen !");
            move.setPiece(10);
            this.boardUI.setPiece(10, move.getDestination());
            this.boardUI.update(move.getDestination());
            return;
        }
        if (EngineAgentExt.goEngine.equals("FairyPrincess") || EngineAgentExt.goEngine.equals("FrankWalter") || EngineAgentExt.goEngine.equals("KennyClassIQ") || EngineAgentExt.goEngine.equals("Talvmenni")) {
            final Actions aktion17 = XChessFrame.aktion;
            if (Actions.gameTip.equals("EE")) {
                this.writeLine(usercmd);
            }
            else {
                this.writeLine("usermove " + usercmd);
            }
            return;
        }
        if (EngineAgentExt.ceTip.equals("xboard")) {
            this.writeLine(usercmd);
            return;
        }
        if (EngineAgentExt.ceTip.equals("uci")) {
            final Actions aktion18 = XChessFrame.aktion;
            if (!Actions.enemyTip.equals("another")) {
                final StringBuilder append11 = new StringBuilder().append("position startpos moves");
                final Actions aktion19 = XChessFrame.aktion;
                this.writeLine(append11.append(Actions.uciAllMovesString).toString());
                final Actions aktion20 = XChessFrame.aktion;
                if (Actions.UseClock.equals("true")) {
                    final Actions aktion21 = XChessFrame.aktion;
                    if (!Actions.whitePlayerCE.equals("Magnum")) {
                        final Actions aktion22 = XChessFrame.aktion;
                        if (!Actions.blackPlayerCE.equals("Magnum")) {
                            final StringBuilder append12 = new StringBuilder().append("go depth ");
                            final Actions aktion23 = XChessFrame.aktion;
                            final StringBuilder append13 = append12.append(Actions.Depth).append(" wtime ");
                            final XChessFrame frame5 = XChessFrame.frame;
                            final StringBuilder append14 = append13.append(XChessFrame.chessClock.getTime(0)).append(" btime ");
                            final XChessFrame frame6 = XChessFrame.frame;
                            this.writeLine(append14.append(XChessFrame.chessClock.getTime(1)).append(" winc 0 binc 0").toString());
                            return;
                        }
                    }
                }
                final StringBuilder append15 = new StringBuilder().append("go depth ");
                final Actions aktion24 = XChessFrame.aktion;
                this.writeLine(append15.append(Actions.Depth).toString());
                return;
            }
        }
        if (EngineAgentExt.ceTip.equals("uci") && EngineAgentExt.colorCE.equals("black")) {
            final Actions aktion25 = XChessFrame.aktion;
            if (Actions.enemyTip.equals("another")) {
                if (MoveListUI.count % 2 == 1) {
                    final StringBuilder append16 = new StringBuilder().append("position startpos moves");
                    final Actions aktion26 = XChessFrame.aktion;
                    this.writeLine(append16.append(Actions.uciAllMovesString).toString());
                    final Actions aktion27 = XChessFrame.aktion;
                    if (Actions.UseClock.equals("true") && !EngineAgentExt.goEngine.equals("Magnum")) {
                        final StringBuilder append17 = new StringBuilder().append("go depth ");
                        final Actions aktion28 = XChessFrame.aktion;
                        final StringBuilder append18 = append17.append(Actions.Depth).append(" wtime ");
                        final XChessFrame frame7 = XChessFrame.frame;
                        final StringBuilder append19 = append18.append(XChessFrame.chessClock.getTime(0)).append(" btime ");
                        final XChessFrame frame8 = XChessFrame.frame;
                        this.writeLine(append19.append(XChessFrame.chessClock.getTime(1)).append(" winc 0 binc 0").toString());
                    }
                    else {
                        final StringBuilder append20 = new StringBuilder().append("go depth ");
                        final Actions aktion29 = XChessFrame.aktion;
                        this.writeLine(append20.append(Actions.Depth).toString());
                    }
                    return;
                }
                if (move.getPiece() == 0 & move.getSource() >= 48 & move.getSource() <= 55) {
                    this.writeLine(usercmd + "q");
                }
                else if (move.getPiece() == 6 & move.getSource() >= 8 & move.getSource() <= 15) {
                    this.writeLine(usercmd + "q");
                }
                else {
                    this.writeLine(usercmd);
                }
            }
        }
    }
    
    private String getTime(long time) {
        time /= 1000L;
        return time / 60L + ":" + time % 60L / 1000L;
    }
    
    @Override
    public void quitEngine() {
        this.writeLine("quit");
    }
}
