// 
// Decompiled by Procyon v0.5.36
// 

package SJCE.more.Links;

import java.awt.EventQueue;
import javax.swing.JFrame;
import SJCE.more.Mail_Url;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.Action;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import java.awt.Component;
import SJCE.XChessFrame;
import java.awt.Frame;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JDialog;

public class CElinksU extends JDialog
{
    public static final int RET_CANCEL = 0;
    public static final int RET_OK = 1;
    private JButton jButton1;
    private JButton jButton11;
    private JButton jButton12;
    private JButton jButton16;
    private JButton jButton17;
    private JButton jButton20;
    private JButton jButton22;
    private JButton jButton23;
    private JButton jButton24;
    private JButton jButton27;
    private JButton jButton28;
    private JButton jButton29;
    private JButton jButton3;
    private JButton jButton30;
    private JButton jButton31;
    private JButton jButton32;
    private JButton jButton33;
    private JButton jButton34;
    private JButton jButton35;
    private JButton jButton36;
    private JButton jButton4;
    private JButton jButton44;
    private JButton jButton55;
    private JButton jButton56;
    private JButton jButton57;
    private JButton jButton58;
    private JButton jButton59;
    private JButton jButton61;
    private JButton jButton62;
    private JButton jButton63;
    private JButton jButton64;
    private JButton jButton65;
    private JButton jButton66;
    private JButton jButton67;
    private JButton jButton68;
    private JButton jButton69;
    private JButton jButton70;
    private JButton jButton71;
    private JButton jButton72;
    private JToolBar jToolBar1;
    private JToolBar jToolBar4;
    private JToolBar jToolBar6;
    private int returnStatus;
    
    public CElinksU(final Frame parent, final boolean modal) {
        super(parent, modal);
        this.returnStatus = 0;
        this.initComponents();
        this.setLocationRelativeTo(XChessFrame.frame);
        this.setTitle("Uci Engines Links and Ratings");
        final String cancelName = "cancel";
        final InputMap inputMap = this.getRootPane().getInputMap(1);
        inputMap.put(KeyStroke.getKeyStroke(27, 0), cancelName);
        final ActionMap actionMap = this.getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                CElinksU.this.doClose(0);
            }
        });
    }
    
    public int getReturnStatus() {
        return this.returnStatus;
    }
    
    private void initComponents() {
        this.jButton11 = new JButton();
        this.jToolBar6 = new JToolBar();
        this.jToolBar1 = new JToolBar();
        this.jButton32 = new JButton();
        this.jButton36 = new JButton();
        this.jButton35 = new JButton();
        this.jButton22 = new JButton();
        this.jButton4 = new JButton();
        this.jButton34 = new JButton();
        this.jButton20 = new JButton();
        this.jButton3 = new JButton();
        this.jButton33 = new JButton();
        this.jButton27 = new JButton();
        this.jButton28 = new JButton();
        this.jButton31 = new JButton();
        this.jButton29 = new JButton();
        this.jButton30 = new JButton();
        this.jButton12 = new JButton();
        this.jButton16 = new JButton();
        this.jButton17 = new JButton();
        this.jButton23 = new JButton();
        this.jButton24 = new JButton();
        this.jToolBar4 = new JToolBar();
        this.jButton55 = new JButton();
        this.jButton1 = new JButton();
        this.jButton56 = new JButton();
        this.jButton57 = new JButton();
        this.jButton44 = new JButton();
        this.jButton58 = new JButton();
        this.jButton59 = new JButton();
        this.jButton61 = new JButton();
        this.jButton62 = new JButton();
        this.jButton63 = new JButton();
        this.jButton64 = new JButton();
        this.jButton65 = new JButton();
        this.jButton66 = new JButton();
        this.jButton67 = new JButton();
        this.jButton68 = new JButton();
        this.jButton69 = new JButton();
        this.jButton70 = new JButton();
        this.jButton71 = new JButton();
        this.jButton72 = new JButton();
        this.setDefaultCloseOperation(2);
        this.setTitle("Java Chess Engines Links and Ratings");
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent evt) {
                CElinksU.this.closeDialog(evt);
            }
        });
        this.jButton11.setText("OK");
        this.jButton11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CElinksU.this.jButton11ActionPerformed(evt);
            }
        });
        this.getContentPane().add(this.jButton11, "South");
        this.jToolBar6.setBorder(BorderFactory.createTitledBorder("UCI engines"));
        this.jToolBar6.setFloatable(false);
        this.jToolBar1.setFloatable(false);
        this.jToolBar1.setOrientation(1);
        this.jButton32.setText("<html><a href=\"http://bagaturchess.sourceforge.net/\">Bagatur v1.4c = http://bagaturchess.sourceforge.net/</a></html>");
        this.jButton32.setFocusable(false);
        this.jButton32.setHorizontalAlignment(2);
        this.jButton32.setHorizontalTextPosition(0);
        this.jButton32.setVerticalTextPosition(3);
        this.jButton32.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CElinksU.this.jButton32ActionPerformed(evt);
            }
        });
        this.jToolBar1.add(this.jButton32);
        this.jButton36.setText("<html><a href=\"https://code.google.com/p/calculonx/\">Calculon v0.4.2 = https://code.google.com/p/calculonx/</a></html>");
        this.jButton36.setFocusable(false);
        this.jButton36.setHorizontalAlignment(2);
        this.jButton36.setHorizontalTextPosition(0);
        this.jButton36.setVerticalTextPosition(3);
        this.jButton36.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CElinksU.this.jButton36ActionPerformed(evt);
            }
        });
        this.jToolBar1.add(this.jButton36);
        this.jButton35.setText("<html><a href=\"https://github.com/albertoruibal/carballo/\">Carballo v1.7 = https://github.com/albertoruibal/carballo/</a></html>");
        this.jButton35.setFocusable(false);
        this.jButton35.setHorizontalAlignment(2);
        this.jButton35.setHorizontalTextPosition(0);
        this.jButton35.setVerticalTextPosition(3);
        this.jButton35.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CElinksU.this.jButton35ActionPerformed(evt);
            }
        });
        this.jToolBar1.add(this.jButton35);
        this.jButton22.setText("<html><a href=\"http://web.comhem.se/petero2home/javachess/index.html\">Cuckoo v1.12 = http://web.comhem.se/petero2home/javachess/index.html</a></html>");
        this.jButton22.setFocusable(false);
        this.jButton22.setHorizontalAlignment(2);
        this.jButton22.setHorizontalTextPosition(0);
        this.jButton22.setVerticalTextPosition(3);
        this.jButton22.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CElinksU.this.jButton22ActionPerformed(evt);
            }
        });
        this.jToolBar1.add(this.jButton22);
        this.jButton4.setText("<html><a href=\"http://kirr.homeunix.org/chess/engines/Jim%20Ablett/EDEN/\">Eden v0.0.13 = http://kirr.homeunix.org/chess/engines/Jim%20Ablett/EDEN/</a></html>");
        this.jButton4.setFocusable(false);
        this.jButton4.setHorizontalAlignment(2);
        this.jButton4.setHorizontalTextPosition(0);
        this.jButton4.setVerticalTextPosition(3);
        this.jButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CElinksU.this.jButton4ActionPerformed(evt);
            }
        });
        this.jToolBar1.add(this.jButton4);
        this.jButton34.setText("<html><a href=\"http://www.stuckardt.de/index.php/schachengine-fischerle.html\">Fischerle v0.9.70 SE 32bit = http://www.stuckardt.de/index.php/schachengine-fischerle.html</a></html>");
        this.jButton34.setFocusable(false);
        this.jButton34.setHorizontalAlignment(2);
        this.jButton34.setHorizontalTextPosition(0);
        this.jButton34.setVerticalTextPosition(3);
        this.jButton34.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CElinksU.this.jButton34ActionPerformed(evt);
            }
        });
        this.jToolBar1.add(this.jButton34);
        this.jButton20.setText("<html><a href=\"https://github.com/fluxroot/flux/releases\">Flux v2.2.1 = https://github.com/fluxroot/flux/releases</a></html>");
        this.jButton20.setFocusable(false);
        this.jButton20.setHorizontalAlignment(2);
        this.jButton20.setHorizontalTextPosition(0);
        this.jButton20.setVerticalTextPosition(3);
        this.jButton20.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CElinksU.this.jButton20ActionPerformed(evt);
            }
        });
        this.jToolBar1.add(this.jButton20);
        this.jButton3.setText("<html><a href=\"https://code.google.com/archive/p/magnumchess/downloads\">Magnum v4.0 = https://code.google.com/archive/p/magnumchess/downloads</a></html>");
        this.jButton3.setFocusable(false);
        this.jButton3.setHorizontalAlignment(2);
        this.jButton3.setHorizontalTextPosition(0);
        this.jButton3.setVerticalTextPosition(3);
        this.jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CElinksU.this.jButton3ActionPerformed(evt);
            }
        });
        this.jToolBar1.add(this.jButton3);
        this.jButton33.setText("<html><a href=\"http://mediocrechess.sourceforge.net/\">Mediocre v0.5 = http://mediocrechess.sourceforge.net/</a></html>");
        this.jButton33.setFocusable(false);
        this.jButton33.setHorizontalAlignment(2);
        this.jButton33.setHorizontalTextPosition(0);
        this.jButton33.setVerticalTextPosition(3);
        this.jButton33.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CElinksU.this.jButton33ActionPerformed(evt);
            }
        });
        this.jToolBar1.add(this.jButton33);
        this.jButton27.setText("<html><a href=\"https://github.com/jwilson82/presbyter\">Presbyter v1.3.0 = https://github.com/jwilson82/presbyter</a></html>");
        this.jButton27.setFocusable(false);
        this.jButton27.setHorizontalAlignment(2);
        this.jButton27.setHorizontalTextPosition(0);
        this.jButton27.setVerticalTextPosition(3);
        this.jButton27.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CElinksU.this.jButton27ActionPerformed(evt);
            }
        });
        this.jToolBar1.add(this.jButton27);
        this.jButton28.setText("<html><a href=\"https://github.com/rahular/phoenix\">Phoenix-Cuckoo v1.13a9 = https://github.com/rahular/phoenix</a></html>");
        this.jButton28.setFocusable(false);
        this.jButton28.setHorizontalAlignment(2);
        this.jButton28.setHorizontalTextPosition(0);
        this.jButton28.setVerticalTextPosition(3);
        this.jButton28.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CElinksU.this.jButton28ActionPerformed(evt);
            }
        });
        this.jToolBar1.add(this.jButton28);
        this.jButton31.setText("<html><a href=\"https://github.com/fluxroot/pulse/releases/\">Pulse v1.6.1 = https://github.com/fluxroot/pulse/releases/</a></html>");
        this.jButton31.setFocusable(false);
        this.jButton31.setHorizontalAlignment(2);
        this.jButton31.setHorizontalTextPosition(0);
        this.jButton31.setVerticalTextPosition(3);
        this.jButton31.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CElinksU.this.jButton31ActionPerformed(evt);
            }
        });
        this.jToolBar1.add(this.jButton31);
        this.jButton29.setText("<html><a href=\"http://www.rivalchess.com/downloads/\">Rival build 0094 = http://www.rivalchess.com/downloads/</a></html>");
        this.jButton29.setFocusable(false);
        this.jButton29.setHorizontalAlignment(2);
        this.jButton29.setHorizontalTextPosition(0);
        this.jButton29.setVerticalTextPosition(3);
        this.jButton29.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CElinksU.this.jButton29ActionPerformed(evt);
            }
        });
        this.jToolBar1.add(this.jButton29);
        this.jButton30.setText("<html><a href=\"https://github.com/Zaloum/\">Rumney v0.2.1 = https://github.com/Zaloum/</a></html>");
        this.jButton30.setFocusable(false);
        this.jButton30.setHorizontalAlignment(2);
        this.jButton30.setHorizontalTextPosition(0);
        this.jButton30.setVerticalTextPosition(3);
        this.jButton30.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CElinksU.this.jButton30ActionPerformed(evt);
            }
        });
        this.jToolBar1.add(this.jButton30);
        this.jButton12.setText("<html><a href=\"https://github.com/micaherne/unidexter/\">Unidexter v0.0.1= https://github.com/micaherne/unidexter/</a></html>");
        this.jButton12.setFocusable(false);
        this.jButton12.setHorizontalAlignment(2);
        this.jButton12.setHorizontalTextPosition(0);
        this.jButton12.setVerticalTextPosition(3);
        this.jButton12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CElinksU.this.jButton12ActionPerformed(evt);
            }
        });
        this.jToolBar1.add(this.jButton12);
        this.jButton16.setText("<html><a href=\"https://github.com/krummi/ChessEngine/\">Ziggy v0.7 = https://github.com/krummi/ChessEngine/</a></html>");
        this.jButton16.setFocusable(false);
        this.jButton16.setHorizontalAlignment(2);
        this.jButton16.setHorizontalTextPosition(0);
        this.jButton16.setVerticalTextPosition(3);
        this.jButton16.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CElinksU.this.jButton16ActionPerformed(evt);
            }
        });
        this.jToolBar1.add(this.jButton16);
        this.jButton17.setText("<html><a href=\"https://github.com/sandermvdb/chess22k/\">Chess22k v1.5 = https://github.com/sandermvdb/chess22k</a></html>");
        this.jButton17.setFocusable(false);
        this.jButton17.setHorizontalAlignment(2);
        this.jButton17.setHorizontalTextPosition(0);
        this.jButton17.setVerticalTextPosition(3);
        this.jButton17.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CElinksU.this.jButton17ActionPerformed(evt);
            }
        });
        this.jToolBar1.add(this.jButton17);
        this.jButton23.setText("<html><a href=\"https://github.com/eliucs/kasparov/\">Kasparov Chess v1.0.0 = https://github.com/eliucs/kasparov/</a></html>");
        this.jButton23.setFocusable(false);
        this.jButton23.setHorizontalAlignment(2);
        this.jButton23.setHorizontalTextPosition(0);
        this.jButton23.setVerticalTextPosition(3);
        this.jButton23.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CElinksU.this.jButton23ActionPerformed(evt);
            }
        });
        this.jToolBar1.add(this.jButton23);
        this.jButton24.setText("<html><a href=\"http://computer-chess.org/doku.php?id=computer_chess:wiki:lists:chess_engine_list\">Koedem v1.1 = http://computer-chess.org/doku.php?id=computer_chess:wiki:lists:chess_engine_list</a></html>");
        this.jButton24.setFocusable(false);
        this.jButton24.setHorizontalAlignment(2);
        this.jButton24.setHorizontalTextPosition(0);
        this.jButton24.setVerticalTextPosition(3);
        this.jButton24.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CElinksU.this.jButton24ActionPerformed(evt);
            }
        });
        this.jToolBar1.add(this.jButton24);
        this.jToolBar6.add(this.jToolBar1);
        this.jToolBar4.setFloatable(false);
        this.jToolBar4.setOrientation(1);
        this.jButton55.setText(" Rating = 2323");
        this.jButton55.setFocusable(false);
        this.jButton55.setHorizontalTextPosition(0);
        this.jButton55.setVerticalTextPosition(3);
        this.jToolBar4.add(this.jButton55);
        this.jButton1.setText(" Rating = 1750");
        this.jButton1.setFocusable(false);
        this.jButton1.setHorizontalTextPosition(0);
        this.jButton1.setVerticalTextPosition(3);
        this.jToolBar4.add(this.jButton1);
        this.jButton56.setText(" Rating = 2455");
        this.jButton56.setFocusable(false);
        this.jButton56.setHorizontalTextPosition(0);
        this.jButton56.setVerticalTextPosition(3);
        this.jToolBar4.add(this.jButton56);
        this.jButton57.setText(" Rating = 2588");
        this.jButton57.setFocusable(false);
        this.jButton57.setHorizontalTextPosition(0);
        this.jButton57.setVerticalTextPosition(3);
        this.jToolBar4.add(this.jButton57);
        this.jButton44.setText(" Rating = 1372");
        this.jButton44.setFocusable(false);
        this.jButton44.setHorizontalTextPosition(0);
        this.jButton44.setVerticalTextPosition(3);
        this.jToolBar4.add(this.jButton44);
        this.jButton58.setText(" Rating = 2294");
        this.jButton58.setFocusable(false);
        this.jButton58.setHorizontalTextPosition(0);
        this.jButton58.setVerticalTextPosition(3);
        this.jToolBar4.add(this.jButton58);
        this.jButton59.setText(" Rating = 2347");
        this.jButton59.setFocusable(false);
        this.jButton59.setHorizontalTextPosition(0);
        this.jButton59.setVerticalTextPosition(3);
        this.jToolBar4.add(this.jButton59);
        this.jButton61.setText(" Rating = ");
        this.jButton61.setFocusable(false);
        this.jButton61.setHorizontalTextPosition(0);
        this.jButton61.setVerticalTextPosition(3);
        this.jToolBar4.add(this.jButton61);
        this.jButton62.setText(" Rating = 2319");
        this.jButton62.setFocusable(false);
        this.jButton62.setHorizontalTextPosition(0);
        this.jButton62.setVerticalTextPosition(3);
        this.jToolBar4.add(this.jButton62);
        this.jButton63.setText(" Rating = ");
        this.jButton63.setFocusable(false);
        this.jButton63.setHorizontalTextPosition(0);
        this.jButton63.setVerticalTextPosition(3);
        this.jToolBar4.add(this.jButton63);
        this.jButton64.setText(" Rating = 2546");
        this.jButton64.setFocusable(false);
        this.jButton64.setHorizontalTextPosition(0);
        this.jButton64.setVerticalTextPosition(3);
        this.jToolBar4.add(this.jButton64);
        this.jButton65.setText(" Rating = 1706");
        this.jButton65.setFocusable(false);
        this.jButton65.setHorizontalTextPosition(0);
        this.jButton65.setVerticalTextPosition(3);
        this.jToolBar4.add(this.jButton65);
        this.jButton66.setText(" Rating = 1968");
        this.jButton66.setFocusable(false);
        this.jButton66.setHorizontalTextPosition(0);
        this.jButton66.setVerticalTextPosition(3);
        this.jToolBar4.add(this.jButton66);
        this.jButton67.setText(" Rating = ");
        this.jButton67.setFocusable(false);
        this.jButton67.setHorizontalTextPosition(0);
        this.jButton67.setVerticalTextPosition(3);
        this.jToolBar4.add(this.jButton67);
        this.jButton68.setText(" Rating = ");
        this.jButton68.setFocusable(false);
        this.jButton68.setHorizontalTextPosition(0);
        this.jButton68.setVerticalTextPosition(3);
        this.jToolBar4.add(this.jButton68);
        this.jButton69.setText(" Rating = 1706");
        this.jButton69.setFocusable(false);
        this.jButton69.setHorizontalTextPosition(0);
        this.jButton69.setVerticalTextPosition(3);
        this.jToolBar4.add(this.jButton69);
        this.jButton70.setText(" Rating = 2400");
        this.jButton70.setFocusable(false);
        this.jButton70.setHorizontalTextPosition(0);
        this.jButton70.setVerticalTextPosition(3);
        this.jToolBar4.add(this.jButton70);
        this.jButton71.setText(" Rating = ");
        this.jButton71.setFocusable(false);
        this.jButton71.setHorizontalTextPosition(0);
        this.jButton71.setVerticalTextPosition(3);
        this.jToolBar4.add(this.jButton71);
        this.jButton72.setText(" Rating = ");
        this.jButton72.setFocusable(false);
        this.jButton72.setHorizontalTextPosition(0);
        this.jButton72.setVerticalTextPosition(3);
        this.jToolBar4.add(this.jButton72);
        this.jToolBar6.add(this.jToolBar4);
        this.getContentPane().add(this.jToolBar6, "Center");
        this.pack();
        this.setLocationRelativeTo(null);
    }
    
    private void closeDialog(final WindowEvent evt) {
        this.doClose(0);
    }
    
    private void jButton22ActionPerformed(final ActionEvent evt) {
        Mail_Url.goURL("http://web.comhem.se/petero2home/javachess/index.html");
    }
    
    private void jButton4ActionPerformed(final ActionEvent evt) {
        Mail_Url.goURL("http://kirr.homeunix.org/chess/engines/Jim%20Ablett/EDEN/");
    }
    
    private void jButton3ActionPerformed(final ActionEvent evt) {
        Mail_Url.goURL("https://code.google.com/archive/p/magnumchess/downloads");
    }
    
    private void jButton27ActionPerformed(final ActionEvent evt) {
        Mail_Url.goURL("https://github.com/jwilson82/presbyter");
    }
    
    private void jButton28ActionPerformed(final ActionEvent evt) {
        Mail_Url.goURL("https://github.com/rahular/phoenix");
    }
    
    private void jButton29ActionPerformed(final ActionEvent evt) {
        Mail_Url.goURL("http://www.rivalchess.com/downloads/");
    }
    
    private void jButton30ActionPerformed(final ActionEvent evt) {
        Mail_Url.goURL("https://github.com/Zaloum/");
    }
    
    private void jButton11ActionPerformed(final ActionEvent evt) {
        this.doClose(1);
    }
    
    private void jButton12ActionPerformed(final ActionEvent evt) {
        Mail_Url.goURL("https://github.com/micaherne/unidexter/");
    }
    
    private void jButton16ActionPerformed(final ActionEvent evt) {
        Mail_Url.goURL("https://github.com/krummi/ChessEngine/");
    }
    
    private void jButton20ActionPerformed(final ActionEvent evt) {
        Mail_Url.goURL("https://github.com/fluxroot/flux/releases");
    }
    
    private void jButton31ActionPerformed(final ActionEvent evt) {
        Mail_Url.goURL("https://github.com/fluxroot/pulse/releases/");
    }
    
    private void jButton32ActionPerformed(final ActionEvent evt) {
        Mail_Url.goURL("http://bagaturchess.sourceforge.net/");
    }
    
    private void jButton33ActionPerformed(final ActionEvent evt) {
        Mail_Url.goURL("http://mediocrechess.sourceforge.net/");
    }
    
    private void jButton34ActionPerformed(final ActionEvent evt) {
        Mail_Url.goURL("http://www.stuckardt.de/index.php/schachengine-fischerle.html");
    }
    
    private void jButton35ActionPerformed(final ActionEvent evt) {
        Mail_Url.goURL("https://github.com/albertoruibal/carballo/");
    }
    
    private void jButton36ActionPerformed(final ActionEvent evt) {
        Mail_Url.goURL("https://code.google.com/p/calculonx/");
    }
    
    private void jButton17ActionPerformed(final ActionEvent evt) {
        Mail_Url.goURL("https://github.com/sandermvdb/chess22k/");
    }
    
    private void jButton23ActionPerformed(final ActionEvent evt) {
        Mail_Url.goURL("https://github.com/eliucs/kasparov/");
    }
    
    private void jButton24ActionPerformed(final ActionEvent evt) {
        Mail_Url.goURL("http://computer-chess.org/doku.php?id=computer_chess:wiki:lists:chess_engine_list");
    }
    
    private void doClose(final int retStatus) {
        this.returnStatus = retStatus;
        this.setVisible(false);
        this.dispose();
    }
    
    public static void aboutRun() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                final CElinksU dialog = new CElinksU(new JFrame(), true);
                dialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(final WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
}
