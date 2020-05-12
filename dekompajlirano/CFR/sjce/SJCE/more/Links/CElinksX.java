/*
 * Decompiled with CFR 0.148.
 */
package SJCE.more.Links;

import SJCE.XChessFrame;
import SJCE.more.Mail_Url;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.border.Border;

public class CElinksX
extends JDialog {
    public static final int RET_CANCEL = 0;
    public static final int RET_OK = 1;
    private JButton jButton1;
    private JButton jButton10;
    private JButton jButton11;
    private JButton jButton13;
    private JButton jButton14;
    private JButton jButton15;
    private JButton jButton17;
    private JButton jButton18;
    private JButton jButton19;
    private JButton jButton2;
    private JButton jButton21;
    private JButton jButton23;
    private JButton jButton24;
    private JButton jButton25;
    private JButton jButton26;
    private JButton jButton3;
    private JButton jButton36;
    private JButton jButton37;
    private JButton jButton38;
    private JButton jButton39;
    private JButton jButton40;
    private JButton jButton41;
    private JButton jButton42;
    private JButton jButton43;
    private JButton jButton45;
    private JButton jButton46;
    private JButton jButton47;
    private JButton jButton48;
    private JButton jButton49;
    private JButton jButton5;
    private JButton jButton50;
    private JButton jButton51;
    private JButton jButton52;
    private JButton jButton53;
    private JButton jButton54;
    private JButton jButton55;
    private JButton jButton56;
    private JButton jButton6;
    private JButton jButton7;
    private JButton jButton8;
    private JButton jButton9;
    private JToolBar jToolBar2;
    private JToolBar jToolBar3;
    private JToolBar jToolBar5;
    private int returnStatus = 0;

    public CElinksX(Frame parent, boolean modal) {
        super(parent, modal);
        this.initComponents();
        this.setLocationRelativeTo(XChessFrame.frame);
        this.setTitle("Xboard Engines Links and Ratings");
        String cancelName = "cancel";
        InputMap inputMap = this.getRootPane().getInputMap(1);
        inputMap.put(KeyStroke.getKeyStroke(27, 0), cancelName);
        ActionMap actionMap = this.getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction(){

            @Override
            public void actionPerformed(ActionEvent e) {
                CElinksX.this.doClose(0);
            }
        });
    }

    public int getReturnStatus() {
        return this.returnStatus;
    }

    private void initComponents() {
        this.jButton11 = new JButton();
        this.jToolBar3 = new JToolBar();
        this.jToolBar5 = new JToolBar();
        this.jButton18 = new JButton();
        this.jButton10 = new JButton();
        this.jButton19 = new JButton();
        this.jButton5 = new JButton();
        this.jButton17 = new JButton();
        this.jButton21 = new JButton();
        this.jButton15 = new JButton();
        this.jButton8 = new JButton();
        this.jButton23 = new JButton();
        this.jButton1 = new JButton();
        this.jButton6 = new JButton();
        this.jButton13 = new JButton();
        this.jButton26 = new JButton();
        this.jButton9 = new JButton();
        this.jButton24 = new JButton();
        this.jButton25 = new JButton();
        this.jButton7 = new JButton();
        this.jButton14 = new JButton();
        this.jButton2 = new JButton();
        this.jButton3 = new JButton();
        this.jToolBar2 = new JToolBar();
        this.jButton36 = new JButton();
        this.jButton37 = new JButton();
        this.jButton38 = new JButton();
        this.jButton39 = new JButton();
        this.jButton40 = new JButton();
        this.jButton41 = new JButton();
        this.jButton42 = new JButton();
        this.jButton43 = new JButton();
        this.jButton45 = new JButton();
        this.jButton46 = new JButton();
        this.jButton47 = new JButton();
        this.jButton48 = new JButton();
        this.jButton56 = new JButton();
        this.jButton49 = new JButton();
        this.jButton50 = new JButton();
        this.jButton51 = new JButton();
        this.jButton52 = new JButton();
        this.jButton53 = new JButton();
        this.jButton54 = new JButton();
        this.jButton55 = new JButton();
        this.setDefaultCloseOperation(2);
        this.setTitle("Xboard Engines Links and Ratings");
        this.addWindowListener(new WindowAdapter(){

            @Override
            public void windowClosing(WindowEvent evt) {
                CElinksX.this.closeDialog(evt);
            }
        });
        this.jButton11.setText("OK");
        this.jButton11.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                CElinksX.this.jButton11ActionPerformed(evt);
            }
        });
        this.getContentPane().add((Component)this.jButton11, "South");
        this.jToolBar3.setBorder(BorderFactory.createTitledBorder("XBOARD engines"));
        this.jToolBar3.setFloatable(false);
        this.jToolBar5.setFloatable(false);
        this.jToolBar5.setOrientation(1);
        this.jToolBar5.setAlignmentX(0.5f);
        this.jButton18.setText("<html><a href=\"http://kirr.homeunix.org/chess/engines/Jim%20Ablett/ALF/\">Alf v1.09 = http://kirr.homeunix.org/chess/engines/Jim%20Ablett/ALF/</a></html>");
        this.jButton18.setFocusable(false);
        this.jButton18.setHorizontalAlignment(2);
        this.jButton18.setHorizontalTextPosition(0);
        this.jButton18.setVerticalTextPosition(3);
        this.jButton18.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                CElinksX.this.jButton18ActionPerformed(evt);
            }
        });
        this.jToolBar5.add(this.jButton18);
        this.jButton10.setText("<html><a href=\"http://animatschess.sourceforge.net/\">Animats r23 = http://animatschess.sourceforge.net/</a></html>");
        this.jButton10.setFocusable(false);
        this.jButton10.setHorizontalAlignment(2);
        this.jButton10.setHorizontalTextPosition(0);
        this.jButton10.setVerticalTextPosition(3);
        this.jButton10.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                CElinksX.this.jButton10ActionPerformed(evt);
            }
        });
        this.jToolBar5.add(this.jButton10);
        this.jButton19.setText("<html><a href=\"http://computer-chess.org/doku.php?id=computer_chess:wiki:download:engine_download_list\">Arabian Knight v1.55 = http://computer-chess.org/</a></html>");
        this.jButton19.setFocusable(false);
        this.jButton19.setHorizontalAlignment(2);
        this.jButton19.setHorizontalTextPosition(0);
        this.jButton19.setVerticalTextPosition(3);
        this.jButton19.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                CElinksX.this.jButton19ActionPerformed(evt);
            }
        });
        this.jToolBar5.add(this.jButton19);
        this.jButton5.setText("<html><a href=\"http://bremboce.cisana.com/\">BremboCE v0.6.2 = http://bremboce.cisana.com/</a></html>");
        this.jButton5.setFocusable(false);
        this.jButton5.setHorizontalAlignment(2);
        this.jButton5.setHorizontalTextPosition(0);
        this.jButton5.setVerticalTextPosition(3);
        this.jButton5.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                CElinksX.this.jButton5ActionPerformed(evt);
            }
        });
        this.jToolBar5.add(this.jButton5);
        this.jButton17.setText("<html><a href=\"http://cavechess.sourceforge.net/\">CaveChess r61 = http://cavechess.sourceforge.net/</a></html>");
        this.jButton17.setFocusable(false);
        this.jButton17.setHorizontalAlignment(2);
        this.jButton17.setHorizontalTextPosition(0);
        this.jButton17.setVerticalTextPosition(3);
        this.jButton17.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                CElinksX.this.jButton17ActionPerformed(evt);
            }
        });
        this.jToolBar5.add(this.jButton17);
        this.jButton21.setText("<html><a href=\"http://kirr.homeunix.org/chess/engines/Jim%20Ablett/CUPCAKE/\">CupCake v11c = http://kirr.homeunix.org/chess/engines/Jim%20Ablett/CUPCAKE/</a></html>");
        this.jButton21.setFocusable(false);
        this.jButton21.setHorizontalAlignment(2);
        this.jButton21.setHorizontalTextPosition(0);
        this.jButton21.setVerticalTextPosition(3);
        this.jButton21.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                CElinksX.this.jButton21ActionPerformed(evt);
            }
        });
        this.jToolBar5.add(this.jButton21);
        this.jButton15.setText("<html><a href=\"https://github.com/alexandersoto/chess-bot\">ChessBot = https://github.com/alexandersoto/chess-bot</a></html>");
        this.jButton15.setFocusable(false);
        this.jButton15.setHorizontalAlignment(2);
        this.jButton15.setHorizontalTextPosition(0);
        this.jButton15.setVerticalTextPosition(3);
        this.jButton15.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                CElinksX.this.jButton15ActionPerformed(evt);
            }
        });
        this.jToolBar5.add(this.jButton15);
        this.jButton8.setText("<html><a href=\"https://www.vanheusden.com/DeepBrutePos/\">DeepBrutePos v2.1 = https://www.vanheusden.com/DeepBrutePos/</a></html>");
        this.jButton8.setFocusable(false);
        this.jButton8.setHorizontalAlignment(2);
        this.jButton8.setHorizontalTextPosition(0);
        this.jButton8.setVerticalTextPosition(3);
        this.jButton8.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                CElinksX.this.jButton8ActionPerformed(evt);
            }
        });
        this.jToolBar5.add(this.jButton8);
        this.jButton23.setText("<html><a href=\"http://kirr.homeunix.org/chess/engines/Jim%20Ablett/FRANK-WALTER/\">Frank Walter v1.08 = http://kirr.homeunix.org/chess/engines/Jim%20Ablett/FRANK-WALTER/</a></html>");
        this.jButton23.setFocusable(false);
        this.jButton23.setHorizontalAlignment(2);
        this.jButton23.setHorizontalTextPosition(0);
        this.jButton23.setVerticalTextPosition(3);
        this.jButton23.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                CElinksX.this.jButton23ActionPerformed(evt);
            }
        });
        this.jToolBar5.add(this.jButton23);
        this.jButton1.setText("<html><a href=\"http://frittle.sourceforge.net/\">Frittle v1.0 = http://frittle.sourceforge.net/</a></html>");
        this.jButton1.setFocusable(false);
        this.jButton1.setHorizontalAlignment(2);
        this.jButton1.setHorizontalTextPosition(0);
        this.jButton1.setVerticalTextPosition(3);
        this.jButton1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                CElinksX.this.jButton1ActionPerformed(evt);
            }
        });
        this.jToolBar5.add(this.jButton1);
        this.jButton6.setText("<html><a href=\"https://github.com/dagaren/gladiator-chess\">Gladiator v0.0.6 = https://github.com/dagaren/gladiator-chess</a></html>");
        this.jButton6.setFocusable(false);
        this.jButton6.setHorizontalAlignment(2);
        this.jButton6.setHorizontalTextPosition(0);
        this.jButton6.setVerticalTextPosition(3);
        this.jButton6.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                CElinksX.this.jButton6ActionPerformed(evt);
            }
        });
        this.jToolBar5.add(this.jButton6);
        this.jButton13.setText("<html><a href=\"http://www.forthgo.com/chessbox/\">GNU chess 5.0.7 = ChessBox_Gnu4j v1.02 = http://www.forthgo.com/chessbox/</a></html>");
        this.jButton13.setFocusable(false);
        this.jButton13.setHorizontalAlignment(2);
        this.jButton13.setHorizontalTextPosition(0);
        this.jButton13.setVerticalTextPosition(3);
        this.jButton13.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                CElinksX.this.jButton13ActionPerformed(evt);
            }
        });
        this.jToolBar5.add(this.jButton13);
        this.jButton26.setText("<html><a href=\"http://jchecs.free.fr/\">jChecs v0.1.0 = http://jchecs.free.fr/</a></html>");
        this.jButton26.setFocusable(false);
        this.jButton26.setHorizontalAlignment(2);
        this.jButton26.setHorizontalTextPosition(0);
        this.jButton26.setVerticalTextPosition(3);
        this.jButton26.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                CElinksX.this.jButton26ActionPerformed(evt);
            }
        });
        this.jToolBar5.add(this.jButton26);
        this.jButton9.setText("<html><a href=\"https://github.com/bugyvlad/javalin\">Javalin v1.3.0 = https://github.com/bugyvlad/javalin</a></html>");
        this.jButton9.setFocusable(false);
        this.jButton9.setHorizontalAlignment(2);
        this.jButton9.setHorizontalTextPosition(0);
        this.jButton9.setVerticalTextPosition(3);
        this.jButton9.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                CElinksX.this.jButton9ActionPerformed(evt);
            }
        });
        this.jToolBar5.add(this.jButton9);
        this.jButton24.setText("<html><a href=\"http://computer-chess.org/\">JChess v1.0 = http://computer-chess.org/</a></html>");
        this.jButton24.setFocusable(false);
        this.jButton24.setHorizontalAlignment(2);
        this.jButton24.setHorizontalTextPosition(0);
        this.jButton24.setVerticalTextPosition(3);
        this.jButton24.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                CElinksX.this.jButton24ActionPerformed(evt);
            }
        });
        this.jToolBar5.add(this.jButton24);
        this.jButton25.setText("<html><a href=\"http://kirr.homeunix.org/chess/engines/Jim%20Ablett/KINGSOUT/\">King's Out v0.2.42 = http://kirr.homeunix.org/chess/engines/Jim%20Ablett/KINGSOUT/</a></html>");
        this.jButton25.setFocusable(false);
        this.jButton25.setHorizontalAlignment(2);
        this.jButton25.setHorizontalTextPosition(0);
        this.jButton25.setVerticalTextPosition(3);
        this.jButton25.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                CElinksX.this.jButton25ActionPerformed(evt);
            }
        });
        this.jToolBar5.add(this.jButton25);
        this.jButton7.setText("<html><a href=\"http://kirr.homeunix.org/chess/engines/Jim%20Ablett/OLITHINK/\">OliThink v5.3.2 = http://kirr.homeunix.org/chess/engines/Jim%20Ablett/OLITHINK/</a></html>");
        this.jButton7.setFocusable(false);
        this.jButton7.setHorizontalAlignment(2);
        this.jButton7.setHorizontalTextPosition(0);
        this.jButton7.setVerticalTextPosition(3);
        this.jButton7.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                CElinksX.this.jButton7ActionPerformed(evt);
            }
        });
        this.jToolBar5.add(this.jButton7);
        this.jButton14.setText("<html><a href=\"http://talvmenni.sourceforge.net\">Talvmenni v0.0.1 = http://talvmenni.sourceforge.net/</a></html>");
        this.jButton14.setFocusable(false);
        this.jButton14.setHorizontalAlignment(2);
        this.jButton14.setHorizontalTextPosition(0);
        this.jButton14.setVerticalTextPosition(3);
        this.jButton14.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                CElinksX.this.jButton14ActionPerformed(evt);
            }
        });
        this.jToolBar5.add(this.jButton14);
        this.jButton2.setText("<html><a href=\"http://tiffanys.sourceforge.net/\">Tiffanys v0.5 = http://tiffanys.sourceforge.net/</a></html>");
        this.jButton2.setFocusable(false);
        this.jButton2.setHorizontalAlignment(2);
        this.jButton2.setHorizontalTextPosition(0);
        this.jButton2.setVerticalTextPosition(3);
        this.jButton2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                CElinksX.this.jButton2ActionPerformed(evt);
            }
        });
        this.jToolBar5.add(this.jButton2);
        this.jButton3.setText("<html><a href=\"http://chess.dubmun.com/\">Tri-OS/cs4210 = http://chess.dubmun.com/</a></html>");
        this.jButton3.setFocusable(false);
        this.jButton3.setHorizontalAlignment(2);
        this.jButton3.setHorizontalTextPosition(0);
        this.jButton3.setVerticalTextPosition(3);
        this.jButton3.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                CElinksX.this.jButton3ActionPerformed(evt);
            }
        });
        this.jToolBar5.add(this.jButton3);
        this.jToolBar3.add(this.jToolBar5);
        this.jToolBar2.setFloatable(false);
        this.jToolBar2.setOrientation(1);
        this.jButton36.setText(" Rating = 2190");
        this.jButton36.setFocusable(false);
        this.jButton36.setHorizontalTextPosition(0);
        this.jButton36.setVerticalTextPosition(3);
        this.jToolBar2.add(this.jButton36);
        this.jButton37.setText(" Rating = ");
        this.jButton37.setFocusable(false);
        this.jButton37.setHorizontalTextPosition(0);
        this.jButton37.setVerticalTextPosition(3);
        this.jToolBar2.add(this.jButton37);
        this.jButton38.setText(" Rating = 1860");
        this.jButton38.setFocusable(false);
        this.jButton38.setHorizontalTextPosition(0);
        this.jButton38.setVerticalTextPosition(3);
        this.jToolBar2.add(this.jButton38);
        this.jButton39.setText(" Rating = 1771");
        this.jButton39.setFocusable(false);
        this.jButton39.setHorizontalTextPosition(0);
        this.jButton39.setVerticalTextPosition(3);
        this.jToolBar2.add(this.jButton39);
        this.jButton40.setText(" Rating = ");
        this.jButton40.setFocusable(false);
        this.jButton40.setHorizontalTextPosition(0);
        this.jButton40.setVerticalTextPosition(3);
        this.jToolBar2.add(this.jButton40);
        this.jButton41.setText(" Rating = 1994");
        this.jButton41.setFocusable(false);
        this.jButton41.setHorizontalTextPosition(0);
        this.jButton41.setVerticalTextPosition(3);
        this.jToolBar2.add(this.jButton41);
        this.jButton42.setText(" Rating = ");
        this.jButton42.setFocusable(false);
        this.jButton42.setHorizontalTextPosition(0);
        this.jButton42.setVerticalTextPosition(3);
        this.jToolBar2.add(this.jButton42);
        this.jButton43.setText(" Rating = ");
        this.jButton43.setFocusable(false);
        this.jButton43.setHorizontalTextPosition(0);
        this.jButton43.setVerticalTextPosition(3);
        this.jToolBar2.add(this.jButton43);
        this.jButton45.setText(" Rating = 1988");
        this.jButton45.setFocusable(false);
        this.jButton45.setHorizontalTextPosition(0);
        this.jButton45.setVerticalTextPosition(3);
        this.jToolBar2.add(this.jButton45);
        this.jButton46.setText(" Rating = ");
        this.jButton46.setFocusable(false);
        this.jButton46.setHorizontalTextPosition(0);
        this.jButton46.setVerticalTextPosition(3);
        this.jToolBar2.add(this.jButton46);
        this.jButton47.setText(" Rating = ");
        this.jButton47.setFocusable(false);
        this.jButton47.setHorizontalTextPosition(0);
        this.jButton47.setVerticalTextPosition(3);
        this.jToolBar2.add(this.jButton47);
        this.jButton48.setText(" Rating = 2760");
        this.jButton48.setFocusable(false);
        this.jButton48.setHorizontalTextPosition(0);
        this.jButton48.setVerticalTextPosition(3);
        this.jToolBar2.add(this.jButton48);
        this.jButton56.setText(" Rating = 1134");
        this.jButton56.setFocusable(false);
        this.jButton56.setHorizontalTextPosition(0);
        this.jButton56.setVerticalTextPosition(3);
        this.jToolBar2.add(this.jButton56);
        this.jButton49.setText(" Rating = ");
        this.jButton49.setFocusable(false);
        this.jButton49.setHorizontalTextPosition(0);
        this.jButton49.setVerticalTextPosition(3);
        this.jToolBar2.add(this.jButton49);
        this.jButton50.setText(" Rating = 1375");
        this.jButton50.setFocusable(false);
        this.jButton50.setHorizontalTextPosition(0);
        this.jButton50.setVerticalTextPosition(3);
        this.jToolBar2.add(this.jButton50);
        this.jButton51.setText(" Rating = 2059");
        this.jButton51.setFocusable(false);
        this.jButton51.setHorizontalTextPosition(0);
        this.jButton51.setVerticalTextPosition(3);
        this.jToolBar2.add(this.jButton51);
        this.jButton52.setText(" Rating = 2380");
        this.jButton52.setFocusable(false);
        this.jButton52.setHorizontalTextPosition(0);
        this.jButton52.setVerticalTextPosition(3);
        this.jToolBar2.add(this.jButton52);
        this.jButton53.setText(" Rating = 1450");
        this.jButton53.setFocusable(false);
        this.jButton53.setHorizontalTextPosition(0);
        this.jButton53.setVerticalTextPosition(3);
        this.jToolBar2.add(this.jButton53);
        this.jButton54.setText(" Rating = ");
        this.jButton54.setFocusable(false);
        this.jButton54.setHorizontalTextPosition(0);
        this.jButton54.setVerticalTextPosition(3);
        this.jToolBar2.add(this.jButton54);
        this.jButton55.setText(" Rating = ");
        this.jButton55.setFocusable(false);
        this.jButton55.setHorizontalTextPosition(0);
        this.jButton55.setVerticalTextPosition(3);
        this.jToolBar2.add(this.jButton55);
        this.jToolBar3.add(this.jToolBar2);
        this.getContentPane().add((Component)this.jToolBar3, "North");
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private void closeDialog(WindowEvent evt) {
        this.doClose(0);
    }

    private void jButton8ActionPerformed(ActionEvent evt) {
        Mail_Url.goURL("https://www.vanheusden.com/DeepBrutePos/");
    }

    private void jButton7ActionPerformed(ActionEvent evt) {
        Mail_Url.goURL("http://kirr.homeunix.org/chess/engines/Jim%20Ablett/OLITHINK/");
    }

    private void jButton6ActionPerformed(ActionEvent evt) {
        Mail_Url.goURL("https://github.com/dagaren/gladiator-chess");
    }

    private void jButton10ActionPerformed(ActionEvent evt) {
        Mail_Url.goURL("http://animatschess.sourceforge.net");
    }

    private void jButton9ActionPerformed(ActionEvent evt) {
        Mail_Url.goURL("https://github.com/bugyvlad/javalin");
    }

    private void jButton5ActionPerformed(ActionEvent evt) {
        Mail_Url.goURL("http://bremboce.cisana.com/");
    }

    private void jButton2ActionPerformed(ActionEvent evt) {
        Mail_Url.goURL("http://tiffanys.sourceforge.net");
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
        Mail_Url.goURL("http://frittle.sourceforge.net");
    }

    private void jButton13ActionPerformed(ActionEvent evt) {
        Mail_Url.goURL("http://www.forthgo.com/chessbox/");
    }

    private void jButton14ActionPerformed(ActionEvent evt) {
        Mail_Url.goURL("http://talvmenni.sourceforge.net");
    }

    private void jButton15ActionPerformed(ActionEvent evt) {
        Mail_Url.goURL("https://github.com/alexandersoto/chess-bot");
    }

    private void jButton17ActionPerformed(ActionEvent evt) {
        Mail_Url.goURL("http://cavechess.sourceforge.net");
    }

    private void jButton18ActionPerformed(ActionEvent evt) {
        Mail_Url.goURL("http://kirr.homeunix.org/chess/engines/Jim%20Ablett/ALF/");
    }

    private void jButton19ActionPerformed(ActionEvent evt) {
        Mail_Url.goURL("http://computer-chess.org/doku.php?id=computer_chess:wiki:download:engine_download_list");
    }

    private void jButton21ActionPerformed(ActionEvent evt) {
        Mail_Url.goURL("http://kirr.homeunix.org/chess/engines/Jim%20Ablett/CUPCAKE/");
    }

    private void jButton23ActionPerformed(ActionEvent evt) {
        Mail_Url.goURL("http://kirr.homeunix.org/chess/engines/Jim%20Ablett/FRANK-WALTER/");
    }

    private void jButton24ActionPerformed(ActionEvent evt) {
        Mail_Url.goURL("http://computer-chess.org/doku.php?id=computer_chess:wiki:download:engine_download_list");
    }

    private void jButton25ActionPerformed(ActionEvent evt) {
        Mail_Url.goURL("http://kirr.homeunix.org/chess/engines/Jim%20Ablett/KINGSOUT/");
    }

    private void jButton11ActionPerformed(ActionEvent evt) {
        this.doClose(1);
    }

    private void jButton3ActionPerformed(ActionEvent evt) {
        Mail_Url.goURL("http://chess.dubmun.com/");
    }

    private void jButton26ActionPerformed(ActionEvent evt) {
        Mail_Url.goURL("http://jchecs.free.fr/");
    }

    private void doClose(int retStatus) {
        this.returnStatus = retStatus;
        this.setVisible(false);
        this.dispose();
    }

    public static void aboutRun() {
        EventQueue.invokeLater(new Runnable(){

            @Override
            public void run() {
                CElinksX dialog = new CElinksX(new JFrame(), true);
                dialog.addWindowListener(new WindowAdapter(){

                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }

        });
    }

}

