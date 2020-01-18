/*     */ package pl.art.lach.mateusz.javaopenchess.display.windows;
/*     */ 
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.TextEvent;
/*     */ import java.awt.event.TextListener;
/*     */ import javax.swing.ButtonGroup;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JRadioButton;
/*     */ import javax.swing.JSeparator;
/*     */ import javax.swing.JSlider;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.text.BadLocationException;
/*     */ import org.apache.log4j.Logger;
/*     */ import pl.art.lach.mateusz.javaopenchess.JChessApp;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Colors;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Game;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.ai.AIFactory;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.players.Player;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.players.PlayerFactory;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.players.PlayerType;
/*     */ import pl.art.lach.mateusz.javaopenchess.utils.GameModes;
/*     */ import pl.art.lach.mateusz.javaopenchess.utils.GameTypes;
/*     */ import pl.art.lach.mateusz.javaopenchess.utils.Settings;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DrawLocalSettings
/*     */   extends JPanel
/*     */   implements ActionListener, TextListener
/*     */ {
/*  44 */   private static final Logger LOG = Logger.getLogger(DrawLocalSettings.class);
/*     */   
/*     */   JDialog parent;
/*     */   JComboBox color;
/*     */   JRadioButton oponentComp;
/*     */   JRadioButton oponentHuman;
/*     */   ButtonGroup oponentChoos;
/*     */   JFrame localPanel;
/*     */   JLabel compLevLab;
/*     */   JSlider computerLevel;
/*     */   JTextField firstName;
/*     */   JTextField secondName;
/*     */   JLabel firstNameLab;
/*     */   JLabel secondNameLab;
/*     */   JCheckBox upsideDown;
/*     */   GridBagLayout gbl;
/*     */   GridBagConstraints gbc;
/*     */   Container cont;
/*     */   JSeparator sep;
/*     */   JButton okButton;
/*     */   JCheckBox timeGame;
/*     */   JComboBox time4Game;
/*  66 */   String[] colors = new String[] {
/*     */       
/*  68 */       Settings.lang("white"), Settings.lang("black")
/*     */     };
/*     */   
/*  71 */   String[] times = new String[] { "1", "3", "5", "8", "10", "15", "20", "25", "30", "60", "120" };
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
/*     */   public void textValueChanged(TextEvent e) {
/*  83 */     Object target = e.getSource();
/*  84 */     if (target == this.firstName || target == this.secondName) {
/*     */       
/*  86 */       JTextField temp = new JTextField();
/*  87 */       if (target == this.firstName) {
/*     */         
/*  89 */         temp = this.firstName;
/*     */       }
/*  91 */       else if (target == this.secondName) {
/*     */         
/*  93 */         temp = this.secondName;
/*     */       } 
/*     */       
/*  96 */       int len = temp.getText().length();
/*  97 */       if (len > 8) {
/*     */         
/*     */         try {
/*     */           
/* 101 */           temp.setText(temp.getText(0, 7));
/*     */         }
/* 103 */         catch (BadLocationException exc) {
/*     */           
/* 105 */           LOG.error("BadLocationException: Something wrong in editables, msg: " + exc.getMessage() + " object: ", exc);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 118 */     Object target = e.getSource();
/* 119 */     if (target == this.oponentComp) {
/*     */       
/* 121 */       this.computerLevel.setEnabled(true);
/* 122 */       this.secondName.setEnabled(false);
/*     */     }
/* 124 */     else if (target == this.oponentHuman) {
/*     */ 
/*     */       
/* 127 */       this.secondName.setEnabled(true);
/*     */     }
/* 129 */     else if (target == this.okButton) {
/*     */       PlayerType blackType, whiteType; String blackName, whiteName;
/* 131 */       if (this.firstName.getText().length() > 9)
/*     */       {
/* 133 */         this.firstName.setText(trimString(this.firstName, 9));
/*     */       }
/* 135 */       if (this.secondName.getText().length() > 9)
/*     */       {
/* 137 */         this.secondName.setText(trimString(this.secondName, 9));
/*     */       }
/* 139 */       if (!this.oponentComp.isSelected() && (this.firstName
/* 140 */         .getText().length() == 0 || this.secondName.getText().length() == 0)) {
/*     */         
/* 142 */         JOptionPane.showMessageDialog(this, Settings.lang("fill_names"));
/*     */         return;
/*     */       } 
/* 145 */       if (this.oponentComp.isSelected() && this.firstName.getText().length() == 0) {
/*     */         
/* 147 */         JOptionPane.showMessageDialog(this, Settings.lang("fill_name"));
/*     */         return;
/*     */       } 
/* 150 */       String playerFirstName = this.firstName.getText();
/* 151 */       String playerSecondName = this.secondName.getText();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 157 */       if (0 == this.color.getSelectedIndex()) {
/*     */         
/* 159 */         whiteName = playerFirstName;
/* 160 */         blackName = playerSecondName;
/* 161 */         whiteType = PlayerType.LOCAL_USER;
/* 162 */         blackType = this.oponentComp.isSelected() ? PlayerType.COMPUTER : PlayerType.LOCAL_USER;
/*     */       }
/*     */       else {
/*     */         
/* 166 */         blackName = playerFirstName;
/* 167 */         whiteName = playerSecondName;
/* 168 */         blackType = PlayerType.LOCAL_USER;
/* 169 */         whiteType = this.oponentComp.isSelected() ? PlayerType.COMPUTER : PlayerType.LOCAL_USER;
/*     */       } 
/* 171 */       Player playerWhite = PlayerFactory.getInstance(whiteName, Colors.WHITE, whiteType);
/* 172 */       Player playerBlack = PlayerFactory.getInstance(blackName, Colors.BLACK, blackType);
/* 173 */       Game newGUI = JChessApp.getJavaChessView().addNewTab(playerWhite.getName() + " vs " + playerBlack.getName());
/* 174 */       newGUI.getChat().setEnabled(false);
/* 175 */       Settings sett = newGUI.getSettings();
/* 176 */       sett.setPlayerWhite(playerWhite);
/* 177 */       sett.setPlayerBlack(playerBlack);
/* 178 */       sett.setGameMode(GameModes.NEW_GAME);
/* 179 */       sett.setGameType(GameTypes.LOCAL);
/* 180 */       sett.setUpsideDown(this.upsideDown.isSelected());
/* 181 */       newGUI.setActivePlayer(playerWhite);
/* 182 */       if (this.timeGame.isSelected()) {
/*     */         
/* 184 */         String value = this.times[this.time4Game.getSelectedIndex()];
/* 185 */         Integer val = new Integer(value);
/* 186 */         sett.setTimeForGame(val.intValue() * 60);
/* 187 */         newGUI.getGameClock().setTimes(sett.getTimeForGame(), sett.getTimeForGame());
/* 188 */         newGUI.getGameClock().start();
/*     */       } 
/* 190 */       LOG.debug("this.time4Game.getActionCommand(): " + this.time4Game.getActionCommand());
/*     */       
/* 192 */       LOG.debug("****************\nStarting new game: " + playerWhite.getName() + " vs. " + playerBlack.getName() + "\ntime 4 game: " + sett
/* 193 */           .getTimeForGame() + "\ntime limit set: " + sett.isTimeLimitSet() + "\nwhite on top?: " + sett
/* 194 */           .isUpsideDown() + "\n****************");
/*     */       
/* 196 */       newGUI.newGame();
/* 197 */       this.parent.setVisible(false);
/*     */       
/* 199 */       JChessApp.getJavaChessView().getActiveTabGame().repaint();
/* 200 */       JChessApp.getJavaChessView().setActiveTabGame(JChessApp.getJavaChessView().getNumberOfOpenedTabs() - 1);
/* 201 */       if (this.oponentComp.isSelected()) {
/*     */         
/* 203 */         Game activeGame = JChessApp.getJavaChessView().getActiveTabGame();
/* 204 */         activeGame.setAi(AIFactory.getAI(this.computerLevel.getValue()));
/* 205 */         if (activeGame.getSettings().isGameAgainstComputer() && activeGame
/* 206 */           .getSettings().getPlayerWhite().getPlayerType() == PlayerType.COMPUTER)
/*     */         {
/* 208 */           activeGame.doComputerMove();
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DrawLocalSettings(JDialog parent) {
/* 218 */     this.parent = parent;
/* 219 */     this.color = new JComboBox<>(this.colors);
/* 220 */     this.gbl = new GridBagLayout();
/* 221 */     this.gbc = new GridBagConstraints();
/* 222 */     this.sep = new JSeparator();
/* 223 */     this.okButton = new JButton(Settings.lang("ok"));
/* 224 */     this.compLevLab = new JLabel(Settings.lang("computer_level"));
/*     */     
/* 226 */     this.firstName = new JTextField("", 10);
/* 227 */     this.firstName.setSize(new Dimension(200, 50));
/* 228 */     this.secondName = new JTextField("", 10);
/* 229 */     this.secondName.setSize(new Dimension(200, 50));
/* 230 */     this.firstNameLab = new JLabel(Settings.lang("first_player_name") + ": ");
/* 231 */     this.secondNameLab = new JLabel(Settings.lang("second_player_name") + ": ");
/* 232 */     this.oponentChoos = new ButtonGroup();
/* 233 */     this.computerLevel = new JSlider();
/* 234 */     this.upsideDown = new JCheckBox(Settings.lang("upside_down"));
/* 235 */     this.timeGame = new JCheckBox(Settings.lang("time_game_min"));
/* 236 */     this.time4Game = new JComboBox<>(this.times);
/*     */     
/* 238 */     this.oponentComp = new JRadioButton(Settings.lang("against_computer"), false);
/* 239 */     this.oponentHuman = new JRadioButton(Settings.lang("against_other_human"), true);
/*     */     
/* 241 */     setLayout(this.gbl);
/* 242 */     this.oponentComp.addActionListener(this);
/* 243 */     this.oponentHuman.addActionListener(this);
/* 244 */     this.okButton.addActionListener(this);
/*     */     
/* 246 */     this.secondName.addActionListener(this);
/*     */     
/* 248 */     this.oponentChoos.add(this.oponentComp);
/* 249 */     this.oponentChoos.add(this.oponentHuman);
/* 250 */     this.computerLevel.setEnabled(false);
/* 251 */     this.computerLevel.setValue(1);
/* 252 */     this.computerLevel.setMaximum(2);
/* 253 */     this.computerLevel.setMinimum(1);
/* 254 */     this.computerLevel.setPaintTicks(true);
/* 255 */     this.computerLevel.setPaintLabels(true);
/* 256 */     this.computerLevel.setMajorTickSpacing(1);
/* 257 */     this.computerLevel.setMinorTickSpacing(1);
/*     */     
/* 259 */     this.gbc.gridx = 0;
/* 260 */     this.gbc.gridy = 0;
/* 261 */     this.gbc.insets = new Insets(3, 3, 3, 3);
/* 262 */     this.gbl.setConstraints(this.oponentComp, this.gbc);
/* 263 */     add(this.oponentComp);
/* 264 */     this.gbc.gridx = 1;
/* 265 */     this.gbl.setConstraints(this.oponentHuman, this.gbc);
/* 266 */     add(this.oponentHuman);
/* 267 */     this.gbc.gridx = 0;
/* 268 */     this.gbc.gridy = 1;
/* 269 */     this.gbl.setConstraints(this.firstNameLab, this.gbc);
/* 270 */     add(this.firstNameLab);
/* 271 */     this.gbc.gridx = 0;
/* 272 */     this.gbc.gridy = 2;
/* 273 */     this.gbl.setConstraints(this.firstName, this.gbc);
/* 274 */     add(this.firstName);
/* 275 */     this.gbc.gridx = 1;
/* 276 */     this.gbc.gridy = 2;
/* 277 */     this.gbl.setConstraints(this.color, this.gbc);
/* 278 */     add(this.color);
/* 279 */     this.gbc.gridx = 0;
/* 280 */     this.gbc.gridy = 3;
/* 281 */     this.gbl.setConstraints(this.secondNameLab, this.gbc);
/* 282 */     add(this.secondNameLab);
/* 283 */     this.gbc.gridy = 4;
/* 284 */     this.gbl.setConstraints(this.secondName, this.gbc);
/* 285 */     add(this.secondName);
/* 286 */     this.gbc.gridy = 5;
/* 287 */     this.gbc.insets = new Insets(0, 0, 0, 0);
/* 288 */     this.gbl.setConstraints(this.compLevLab, this.gbc);
/* 289 */     add(this.compLevLab);
/* 290 */     this.gbc.gridy = 6;
/* 291 */     this.gbl.setConstraints(this.computerLevel, this.gbc);
/* 292 */     add(this.computerLevel);
/* 293 */     this.gbc.gridy = 7;
/* 294 */     this.gbl.setConstraints(this.upsideDown, this.gbc);
/* 295 */     add(this.upsideDown);
/* 296 */     this.gbc.gridy = 8;
/* 297 */     this.gbc.gridwidth = 1;
/* 298 */     this.gbl.setConstraints(this.timeGame, this.gbc);
/* 299 */     add(this.timeGame);
/* 300 */     this.gbc.gridx = 1;
/* 301 */     this.gbc.gridy = 8;
/* 302 */     this.gbc.gridwidth = 1;
/* 303 */     this.gbl.setConstraints(this.time4Game, this.gbc);
/* 304 */     add(this.time4Game);
/* 305 */     this.gbc.gridx = 1;
/* 306 */     this.gbc.gridy = 9;
/* 307 */     this.gbc.gridwidth = 0;
/* 308 */     this.gbl.setConstraints(this.okButton, this.gbc);
/* 309 */     add(this.okButton);
/*     */   }
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
/*     */   public String trimString(JTextField txt, int length) {
/* 322 */     String result = new String();
/*     */     
/*     */     try {
/* 325 */       result = txt.getText(0, length);
/*     */     }
/* 327 */     catch (BadLocationException exc) {
/*     */       
/* 329 */       LOG.error("BadLocationException: Something wrong in trimString: \n", exc);
/*     */     } 
/* 331 */     return result;
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\display\windows\DrawLocalSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */