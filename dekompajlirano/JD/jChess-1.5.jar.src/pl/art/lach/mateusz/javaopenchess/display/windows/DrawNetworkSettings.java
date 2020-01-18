/*     */ package pl.art.lach.mateusz.javaopenchess.display.windows;
/*     */ 
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.regex.Pattern;
/*     */ import javax.swing.ButtonGroup;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JPasswordField;
/*     */ import javax.swing.JRadioButton;
/*     */ import javax.swing.JTextField;
/*     */ import org.apache.log4j.Logger;
/*     */ import pl.art.lach.mateusz.javaopenchess.JChessApp;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Colors;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Game;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.players.Player;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.players.PlayerFactory;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.players.PlayerType;
/*     */ import pl.art.lach.mateusz.javaopenchess.network.Client;
/*     */ import pl.art.lach.mateusz.javaopenchess.server.Server;
/*     */ import pl.art.lach.mateusz.javaopenchess.utils.MD5;
/*     */ import pl.art.lach.mateusz.javaopenchess.utils.Settings;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DrawNetworkSettings
/*     */   extends JPanel
/*     */   implements ActionListener
/*     */ {
/*  52 */   private static final Logger LOG = Logger.getLogger(DrawNetworkSettings.class);
/*     */   
/*     */   private JDialog parent;
/*     */   
/*     */   private GridBagLayout gbl;
/*     */   
/*     */   private GridBagConstraints gbc;
/*     */   
/*     */   private ButtonGroup serverORclient;
/*     */   
/*     */   private JRadioButton radioServer;
/*     */   private JRadioButton radioClient;
/*     */   private JLabel labelNick;
/*     */   private JLabel labelPassword;
/*     */   private JLabel labelGameID;
/*     */   private JLabel labelOptions;
/*     */   private JPanel panelOptions;
/*     */   private JTextField textNick;
/*     */   private JPasswordField textPassword;
/*     */   private JTextField textGameID;
/*     */   private JButton buttonStart;
/*     */   private ServOptionsPanel servOptions;
/*     */   private ClientOptionsPanel clientOptions;
/*     */   
/*     */   public DrawNetworkSettings(JDialog parent) {
/*  77 */     this.parent = parent;
/*     */     
/*  79 */     this.radioServer = new JRadioButton(Settings.lang("create_server"), true);
/*  80 */     this.radioClient = new JRadioButton(Settings.lang("connect_2_server"), false);
/*  81 */     this.serverORclient = new ButtonGroup();
/*  82 */     this.serverORclient.add(this.radioServer);
/*  83 */     this.serverORclient.add(this.radioClient);
/*  84 */     this.radioServer.addActionListener(this);
/*  85 */     this.radioClient.addActionListener(this);
/*     */     
/*  87 */     this.labelNick = new JLabel(Settings.lang("nickname"));
/*  88 */     this.labelPassword = new JLabel(Settings.lang("password"));
/*  89 */     this.labelGameID = new JLabel(Settings.lang("game_id"));
/*  90 */     this.labelOptions = new JLabel(Settings.lang("server_options"));
/*     */     
/*  92 */     this.textNick = new JTextField();
/*  93 */     this.textPassword = new JPasswordField();
/*  94 */     this.textGameID = new JTextField();
/*     */     
/*  96 */     this.panelOptions = new JPanel();
/*  97 */     this.clientOptions = new ClientOptionsPanel();
/*  98 */     this.servOptions = new ServOptionsPanel();
/*     */     
/* 100 */     this.buttonStart = new JButton(Settings.lang("start"));
/* 101 */     this.buttonStart.addActionListener(this);
/*     */ 
/*     */     
/* 104 */     this.gbl = new GridBagLayout();
/* 105 */     this.gbc = new GridBagConstraints();
/* 106 */     this.gbc.fill = 1;
/* 107 */     setLayout(this.gbl);
/*     */     
/* 109 */     this.gbc.gridx = 0;
/* 110 */     this.gbc.gridy = 0;
/* 111 */     this.gbl.setConstraints(this.radioServer, this.gbc);
/* 112 */     add(this.radioServer);
/*     */     
/* 114 */     this.gbc.gridx = 1;
/* 115 */     this.gbc.gridy = 0;
/* 116 */     this.gbl.setConstraints(this.radioClient, this.gbc);
/* 117 */     add(this.radioClient);
/*     */     
/* 119 */     this.gbc.gridx = 0;
/* 120 */     this.gbc.gridy = 1;
/* 121 */     this.gbc.gridwidth = 2;
/* 122 */     this.gbl.setConstraints(this.labelGameID, this.gbc);
/* 123 */     add(this.labelGameID);
/*     */     
/* 125 */     this.gbc.gridx = 0;
/* 126 */     this.gbc.gridy = 2;
/* 127 */     this.gbc.gridwidth = 2;
/* 128 */     this.gbl.setConstraints(this.textGameID, this.gbc);
/* 129 */     add(this.textGameID);
/*     */     
/* 131 */     this.gbc.gridx = 0;
/* 132 */     this.gbc.gridy = 3;
/* 133 */     this.gbc.gridwidth = 2;
/* 134 */     this.gbl.setConstraints(this.labelNick, this.gbc);
/* 135 */     add(this.labelNick);
/*     */     
/* 137 */     this.gbc.gridx = 0;
/* 138 */     this.gbc.gridy = 4;
/* 139 */     this.gbc.gridwidth = 2;
/* 140 */     this.gbl.setConstraints(this.textNick, this.gbc);
/* 141 */     add(this.textNick);
/*     */     
/* 143 */     this.gbc.gridx = 0;
/* 144 */     this.gbc.gridy = 5;
/* 145 */     this.gbc.gridwidth = 2;
/* 146 */     this.gbl.setConstraints(this.labelPassword, this.gbc);
/* 147 */     add(this.labelPassword);
/*     */     
/* 149 */     this.gbc.gridx = 0;
/* 150 */     this.gbc.gridy = 6;
/* 151 */     this.gbc.gridwidth = 2;
/* 152 */     this.gbl.setConstraints(this.textPassword, this.gbc);
/* 153 */     add(this.textPassword);
/*     */     
/* 155 */     this.gbc.gridx = 0;
/* 156 */     this.gbc.gridy = 7;
/* 157 */     this.gbc.gridwidth = 2;
/* 158 */     this.gbl.setConstraints(this.labelOptions, this.gbc);
/* 159 */     add(this.labelOptions);
/*     */     
/* 161 */     this.gbc.gridx = 0;
/* 162 */     this.gbc.gridy = 8;
/* 163 */     this.gbc.gridwidth = 2;
/* 164 */     this.gbl.setConstraints(this.panelOptions, this.gbc);
/* 165 */     add(this.panelOptions);
/*     */     
/* 167 */     this.gbc.gridx = 0;
/* 168 */     this.gbc.gridy = 9;
/* 169 */     this.gbc.gridwidth = 2;
/* 170 */     this.gbl.setConstraints(this.buttonStart, this.gbc);
/* 171 */     add(this.buttonStart);
/*     */     
/* 173 */     this.panelOptions.add(this.servOptions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent arg0) {
/* 180 */     if (arg0.getSource() == this.radioServer) {
/*     */       
/* 182 */       this.panelOptions.removeAll();
/* 183 */       this.panelOptions.add(this.servOptions);
/* 184 */       this.panelOptions.revalidate();
/* 185 */       this.panelOptions.requestFocus();
/* 186 */       this.panelOptions.repaint();
/*     */     }
/* 188 */     else if (arg0.getSource() == this.radioClient) {
/*     */       
/* 190 */       this.panelOptions.removeAll();
/* 191 */       this.panelOptions.add(this.clientOptions);
/* 192 */       this.panelOptions.revalidate();
/* 193 */       this.panelOptions.requestFocus();
/* 194 */       this.panelOptions.repaint();
/*     */     }
/* 196 */     else if (arg0.getSource() == this.buttonStart) {
/*     */       
/* 198 */       String error = "";
/* 199 */       if (this.textGameID.getText().isEmpty())
/*     */       {
/* 201 */         error = Settings.lang("fill_game_id") + "\n";
/*     */       }
/* 203 */       if (this.textNick.getText().length() == 0)
/*     */       {
/* 205 */         error = error + Settings.lang("fill_name") + "\n";
/*     */       }
/* 207 */       if (this.textPassword.getText().length() <= 4)
/*     */       {
/* 209 */         error = error + Settings.lang("fill_pass_with_more_than_4_signs") + "\n";
/*     */       }
/* 211 */       if (this.radioClient.isSelected() && this.clientOptions.textServIP.getText().length() == 0) {
/*     */         
/* 213 */         error = error + Settings.lang("please_fill_field") + " IP \n";
/*     */       }
/* 215 */       else if (this.radioClient.isSelected()) {
/*     */         
/* 217 */         Pattern ipPattern = Pattern.compile("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}");
/* 218 */         if (!ipPattern.matcher(this.clientOptions.textServIP.getText()).matches())
/*     */         {
/* 220 */           error = error + Settings.lang("bad_ip_format") + "\n";
/*     */         }
/*     */       } 
/* 223 */       if (error.length() > 0) {
/*     */         
/* 225 */         JOptionPane.showMessageDialog(this, error);
/*     */         return;
/*     */       } 
/* 228 */       String pass = this.textPassword.getText().toString();
/* 229 */       if (this.radioServer.isSelected()) {
/*     */         
/* 231 */         Server server = new Server();
/* 232 */         server.newTable(Integer.parseInt(this.textGameID.getText()), pass, !this.servOptions.checkWitchoutObserver.isSelected(), !this.servOptions.checkDisableChat.isSelected());
/*     */         
/* 234 */         this.clientOptions.textServIP.setText("127.0.0.1");
/*     */ 
/*     */         
/*     */         try {
/* 238 */           Thread.sleep(100L);
/*     */         }
/* 240 */         catch (InterruptedException ex) {
/*     */           
/* 242 */           LOG.error("InterruptedException: " + ex);
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/*     */       try {
/* 248 */         Client client = new Client(this.clientOptions.textServIP.getText(), Server.port);
/* 249 */         boolean isJoining = client.join(Integer.parseInt(this.textGameID.getText()), !this.clientOptions.checkOnlyWatch.isSelected(), this.textNick.getText(), MD5.encrypt(this.textPassword.getText()));
/*     */         
/* 251 */         if (isJoining)
/*     */         {
/* 253 */           LOG.debug("Client connection: succesful");
/*     */           
/* 255 */           Game newGUI = JChessApp.getJavaChessView().addNewTab("Network game, table: " + this.textGameID.getText());
/* 256 */           client.setGame(newGUI);
/* 257 */           newGUI.getChessboard().repaint();
/* 258 */           Settings sett = newGUI.getSettings();
/* 259 */           String whiteName = sett.getPlayerWhite().getName();
/* 260 */           String blackName = sett.getPlayerBlack().getName();
/* 261 */           Player whitePlayer = null;
/* 262 */           Player blackPlayer = null;
/* 263 */           if (this.radioServer.isSelected()) {
/*     */             
/* 265 */             whitePlayer = PlayerFactory.getInstance(whiteName, Colors.WHITE, PlayerType.LOCAL_USER);
/* 266 */             blackPlayer = PlayerFactory.getInstance(blackName, Colors.BLACK, PlayerType.NETWORK_USER);
/*     */           }
/*     */           else {
/*     */             
/* 270 */             blackPlayer = PlayerFactory.getInstance(blackName, Colors.BLACK, PlayerType.LOCAL_USER);
/* 271 */             whitePlayer = PlayerFactory.getInstance(whiteName, Colors.WHITE, PlayerType.NETWORK_USER);
/*     */           } 
/* 273 */           sett.setUpsideDown(false);
/* 274 */           sett.setPlayerBlack(blackPlayer);
/* 275 */           sett.setPlayerWhite(whitePlayer);
/* 276 */           newGUI.setSettings(sett);
/* 277 */           Thread thread = new Thread((Runnable)client);
/* 278 */           thread.start();
/*     */           
/* 280 */           this.parent.setVisible(false);
/*     */         }
/*     */         else
/*     */         {
/* 284 */           JOptionPane.showMessageDialog(this, Settings.lang("error_connecting_to_server"));
/*     */         }
/*     */       
/*     */       }
/* 288 */       catch (Error err) {
/*     */         
/* 290 */         LOG.error("Client connection: failure: " + err);
/* 291 */         JOptionPane.showMessageDialog(this, err);
/*     */       } 
/* 293 */       JChessApp.getJavaChessView().setLastTabAsActive();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private class ServOptionsPanel
/*     */     extends JPanel
/*     */   {
/*     */     private GridBagLayout gbl;
/*     */     
/*     */     private GridBagConstraints gbc;
/*     */     
/*     */     private JLabel labelGameTime;
/*     */     
/*     */     public JTextField textGameTime;
/*     */     
/*     */     public JCheckBox checkWitchoutObserver;
/*     */     public JCheckBox checkDisableChat;
/*     */     
/*     */     ServOptionsPanel() {
/* 313 */       this.labelGameTime = new JLabel(Settings.lang("time_game_min"));
/* 314 */       this.textGameTime = new JTextField();
/* 315 */       this.checkWitchoutObserver = new JCheckBox(Settings.lang("without_observers"));
/* 316 */       this.checkDisableChat = new JCheckBox(Settings.lang("without_chat"));
/*     */ 
/*     */       
/* 319 */       this.labelGameTime.setEnabled(false);
/* 320 */       this.textGameTime.setEnabled(false);
/* 321 */       this.checkDisableChat.setEnabled(false);
/*     */ 
/*     */       
/* 324 */       this.gbl = new GridBagLayout();
/* 325 */       this.gbc = new GridBagConstraints();
/* 326 */       this.gbc.fill = 1;
/* 327 */       setLayout(this.gbl);
/*     */       
/* 329 */       this.gbc.gridx = 0;
/* 330 */       this.gbc.gridy = 0;
/* 331 */       this.gbc.gridwidth = 2;
/* 332 */       this.gbl.setConstraints(this.labelGameTime, this.gbc);
/* 333 */       add(this.labelGameTime);
/*     */       
/* 335 */       this.gbc.gridx = 0;
/* 336 */       this.gbc.gridy = 1;
/* 337 */       this.gbc.gridwidth = 2;
/* 338 */       this.gbl.setConstraints(this.textGameTime, this.gbc);
/* 339 */       add(this.textGameTime);
/*     */       
/* 341 */       this.gbc.gridx = 0;
/* 342 */       this.gbc.gridy = 2;
/* 343 */       this.gbc.gridwidth = 1;
/* 344 */       this.gbl.setConstraints(this.checkWitchoutObserver, this.gbc);
/* 345 */       add(this.checkWitchoutObserver);
/*     */       
/* 347 */       this.gbc.gridx = 1;
/* 348 */       this.gbc.gridy = 2;
/* 349 */       this.gbc.gridwidth = 1;
/* 350 */       this.gbl.setConstraints(this.checkDisableChat, this.gbc);
/* 351 */       add(this.checkDisableChat);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private class ClientOptionsPanel
/*     */     extends JPanel
/*     */   {
/*     */     private GridBagLayout gbl;
/*     */     
/*     */     private GridBagConstraints gbc;
/*     */     
/*     */     private JLabel labelServIP;
/*     */     
/*     */     public JTextField textServIP;
/*     */     
/*     */     public JCheckBox checkOnlyWatch;
/*     */     
/*     */     ClientOptionsPanel() {
/* 370 */       this.labelServIP = new JLabel(Settings.lang("server_ip"));
/* 371 */       this.textServIP = new JTextField();
/* 372 */       this.checkOnlyWatch = new JCheckBox(Settings.lang("only_observe"));
/*     */       
/* 374 */       this.gbl = new GridBagLayout();
/* 375 */       this.gbc = new GridBagConstraints();
/* 376 */       this.gbc.fill = 1;
/* 377 */       setLayout(this.gbl);
/*     */       
/* 379 */       this.gbc.gridx = 0;
/* 380 */       this.gbc.gridy = 0;
/* 381 */       this.gbl.setConstraints(this.labelServIP, this.gbc);
/* 382 */       add(this.labelServIP);
/*     */       
/* 384 */       this.gbc.gridx = 0;
/* 385 */       this.gbc.gridy = 1;
/* 386 */       this.gbl.setConstraints(this.textServIP, this.gbc);
/* 387 */       add(this.textServIP);
/*     */       
/* 389 */       this.gbc.gridx = 0;
/* 390 */       this.gbc.gridy = 2;
/* 391 */       this.gbl.setConstraints(this.checkOnlyWatch, this.gbc);
/* 392 */       add(this.checkOnlyWatch);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\display\windows\DrawNetworkSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */