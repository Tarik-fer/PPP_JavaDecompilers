/*     */ package pl.art.lach.mateusz.javaopenchess;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.Desktop;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.HeadlessException;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.ComponentListener;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuBar;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JProgressBar;
/*     */ import javax.swing.JSeparator;
/*     */ import javax.swing.JTabbedPane;
/*     */ import javax.swing.KeyStroke;
/*     */ import javax.swing.LayoutStyle;
/*     */ import javax.swing.Timer;
/*     */ import javax.swing.filechooser.FileFilter;
/*     */ import javax.swing.filechooser.FileNameExtensionFilter;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.jdesktop.application.Action;
/*     */ import org.jdesktop.application.Application;
/*     */ import org.jdesktop.application.ApplicationActionMap;
/*     */ import org.jdesktop.application.FrameView;
/*     */ import org.jdesktop.application.ResourceMap;
/*     */ import org.jdesktop.application.SingleFrameApplication;
/*     */ import org.jdesktop.application.TaskMonitor;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Game;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.data_transfer.DataExporter;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.data_transfer.DataImporter;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.data_transfer.DataTransferFactory;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.data_transfer.TransferFormat;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.exceptions.ReadGameError;
/*     */ import pl.art.lach.mateusz.javaopenchess.display.windows.JChessAboutBox;
/*     */ import pl.art.lach.mateusz.javaopenchess.display.windows.JChessTabbedPane;
/*     */ import pl.art.lach.mateusz.javaopenchess.display.windows.NewGameWindow;
/*     */ import pl.art.lach.mateusz.javaopenchess.display.windows.PawnPromotionWindow;
/*     */ import pl.art.lach.mateusz.javaopenchess.display.windows.ThemeChooseWindow;
/*     */ import pl.art.lach.mateusz.javaopenchess.utils.GUI;
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
/*     */ public class JChessView
/*     */   extends FrameView
/*     */   implements ActionListener, ComponentListener
/*     */ {
/*  82 */   private static final Logger LOG = Logger.getLogger(JChessView.class);
/*     */   
/*     */   private static final String TAB_LABEL_STRING_FORMAT = "%s vs %s";
/*     */   
/*  86 */   protected static GUI gui = null; private JMenu gameMenu; private JTabbedPane gamesPane; private JMenuItem loadGameItem; public JPanel mainPanel; private JMenuBar menuBar; private JMenuItem moveBackItem; private JMenuItem moveForwardItem; private JMenuItem newGameItem; private JMenu optionsMenu; private JProgressBar progressBar; private JMenuItem rewindToBegin; private JMenuItem rewindToEnd; private JMenuItem saveGameItem; private JLabel statusAnimationLabel; private JLabel statusMessageLabel; private JPanel statusPanel; private JMenuItem themeSettingsMenu; private final Timer messageTimer; private final Timer busyIconTimer; private final Icon idleIcon;
/*     */   private final Icon[] busyIcons;
/*     */   private int busyIconIndex;
/*     */   private JDialog aboutBox;
/*     */   private PawnPromotionWindow promotionBox;
/*     */   private JDialog newGameFrame;
/*     */   
/*  93 */   public static GUI getGui() { return gui; }
/*     */ 
/*     */ 
/*     */   
/*     */   public Game addNewTab(String title) {
/*  98 */     Game game = new Game();
/*  99 */     this.gamesPane.addTab(title, (Component)game);
/* 100 */     return game;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addNewTab(Game game) {
/* 105 */     if (null != game) {
/*     */       
/* 107 */       String title = String.format("%s vs %s", new Object[] { game
/* 108 */             .getSettings().getPlayerWhite().getName(), game
/* 109 */             .getSettings().getPlayerBlack().getName() });
/*     */       
/* 111 */       this.gamesPane.addTab(title, (Component)game);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Component getTabComponent(Game game) {
/* 117 */     int tabNumber = getTabNumber(game);
/* 118 */     if (0 <= tabNumber)
/*     */     {
/* 120 */       return this.gamesPane.getComponent(tabNumber);
/*     */     }
/* 122 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTabNumber(Game game) {
/* 127 */     for (int i = 0; i < this.gamesPane.getTabCount(); i++) {
/*     */       
/* 129 */       Component component = this.gamesPane.getComponent(i);
/* 130 */       if (game == component)
/*     */       {
/* 132 */         return i;
/*     */       }
/*     */     } 
/* 135 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent event) {
/* 141 */     Object target = event.getSource();
/* 142 */     if (target == this.newGameItem) {
/*     */       
/* 144 */       newGame();
/*     */     }
/* 146 */     else if (target == this.saveGameItem) {
/*     */       
/* 148 */       saveGame();
/*     */     }
/* 150 */     else if (target == this.loadGameItem) {
/*     */       
/* 152 */       loadGame();
/*     */     }
/* 154 */     else if (target == this.themeSettingsMenu) {
/*     */       
/* 156 */       runThemeSettingsWindow();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void runThemeSettingsWindow() throws HeadlessException {
/*     */     try {
/* 165 */       ThemeChooseWindow choose = new ThemeChooseWindow(getFrame());
/* 166 */       JChessApp.getApplication().show((JDialog)choose);
/*     */     }
/* 168 */     catch (Exception exc) {
/*     */       
/* 170 */       JOptionPane.showMessageDialog(
/* 171 */           JChessApp.getApplication().getMainFrame(), exc
/* 172 */           .getMessage());
/*     */       
/* 174 */       LOG.error("Something wrong creating window - perhaps themeList is null: ", exc);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean saveGame() throws HeadlessException {
/* 180 */     if (this.gamesPane.getTabCount() == 0) {
/*     */       
/* 182 */       JOptionPane.showMessageDialog(null, Settings.lang("save_not_called_for_tab"));
/* 183 */       return true;
/*     */     } 
/*     */     
/*     */     while (true) {
/* 187 */       JFileChooser fc = initFileChooser();
/* 188 */       int retVal = fc.showSaveDialog(this.gamesPane);
/* 189 */       if (retVal == 0) {
/*     */         
/* 191 */         File selFile = fc.getSelectedFile();
/* 192 */         Game tempGUI = (Game)this.gamesPane.getComponentAt(this.gamesPane.getSelectedIndex());
/* 193 */         if (!selFile.exists()) {
/*     */           
/*     */           try
/*     */           {
/* 197 */             selFile.createNewFile();
/*     */           }
/* 199 */           catch (IOException exc)
/*     */           {
/* 201 */             LOG.error("error creating file: ", exc);
/*     */           }
/*     */         
/* 204 */         } else if (selFile.exists()) {
/*     */           
/* 206 */           int opt = JOptionPane.showConfirmDialog((Component)tempGUI, Settings.lang("file_exists"), Settings.lang("file_exists"), 0);
/* 207 */           if (opt == 1) {
/*     */             continue;
/*     */           }
/*     */         } 
/*     */         
/* 212 */         if (selFile.canWrite()) {
/*     */           
/*     */           try {
/*     */             
/* 216 */             DataExporter dataExporter = DataTransferFactory.getExporterInstance(
/* 217 */                 getDataTransfer(selFile));
/*     */             
/* 219 */             tempGUI.saveGame(selFile, dataExporter);
/*     */           }
/* 221 */           catch (IllegalArgumentException exc) {
/*     */             
/* 223 */             LOG.error(exc);
/* 224 */             JOptionPane.showMessageDialog(null, Settings.lang("unknown_format"));
/*     */           } 
/*     */         }
/* 227 */         LOG.debug(Boolean.valueOf(fc.getSelectedFile().isFile()));
/*     */         break;
/*     */       } 
/* 230 */       if (retVal == 1) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 236 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private void newGame() {
/* 241 */     setNewGameFrame((JDialog)new NewGameWindow());
/* 242 */     JChessApp.getApplication().show(getNewGameFrame());
/*     */   }
/*     */ 
/*     */   
/*     */   private void loadGame() throws HeadlessException {
/* 247 */     JFileChooser fc = initFileChooser();
/* 248 */     int retVal = fc.showOpenDialog(this.gamesPane);
/* 249 */     if (retVal == 0) {
/*     */       
/* 251 */       File file = fc.getSelectedFile();
/* 252 */       if (file.exists() && file.canRead()) {
/*     */         
/* 254 */         DataImporter di = DataTransferFactory.getImporterInstance(
/* 255 */             getDataTransfer(file));
/*     */ 
/*     */ 
/*     */         
/*     */         try {
/* 260 */           Game game = di.importData(FileUtils.readFileToString(file));
/* 261 */           addNewTab(game);
/* 262 */           if (null != JChessApp.getJavaChessView())
/*     */           {
/*     */             
/* 265 */             JChessApp.getJavaChessView().setLastTabAsActive();
/*     */           }
/*     */         }
/* 268 */         catch (IOException exc) {
/*     */           
/* 270 */           LOG.error(exc);
/* 271 */           JOptionPane.showMessageDialog(null, Settings.lang("error_writing_to_file") + ": " + exc);
/*     */         }
/* 273 */         catch (ReadGameError exc) {
/*     */           
/* 275 */           LOG.error(exc);
/* 276 */           JOptionPane.showMessageDialog(null, exc.getMessage());
/*     */         }
/* 278 */         catch (IllegalArgumentException exc) {
/*     */           
/* 280 */           LOG.error(exc);
/* 281 */           JOptionPane.showMessageDialog(null, Settings.lang("unknown_format"));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private TransferFormat getDataTransfer(File file)
/*     */   {
/* 289 */     String name = file.getName();
/* 290 */     String[] nameParts = name.split("\\.");
/* 291 */     return TransferFormat.valueOf(nameParts[nameParts.length - 1].toUpperCase()); } @Action public void showAboutBox() { if (this.aboutBox == null) { JFrame mainFrame = JChessApp.getApplication().getMainFrame(); this.aboutBox = (JDialog)new JChessAboutBox(mainFrame); this.aboutBox.setLocationRelativeTo(mainFrame); }
/*     */      JChessApp.getApplication().show(this.aboutBox); } public String showPawnPromotionBox(String color) { if (this.promotionBox == null) { JFrame mainFrame = JChessApp.getApplication().getMainFrame(); this.promotionBox = new PawnPromotionWindow(mainFrame, color); this.promotionBox.setLocationRelativeTo(mainFrame); this.promotionBox.setModal(true); }
/*     */      this.promotionBox.setColor(color); JChessApp.getApplication().show((JDialog)this.promotionBox); return this.promotionBox.result; } private void initComponents() { this.mainPanel = new JPanel(); this.gamesPane = (JTabbedPane)new JChessTabbedPane(); this.menuBar = new JMenuBar(); JMenu fileMenu = new JMenu(); this.newGameItem = new JMenuItem(); this.loadGameItem = new JMenuItem(); this.saveGameItem = new JMenuItem(); JMenuItem exitMenuItem = new JMenuItem(); this.gameMenu = new JMenu(); this.moveBackItem = new JMenuItem(); this.moveForwardItem = new JMenuItem(); this.rewindToBegin = new JMenuItem(); this.rewindToEnd = new JMenuItem(); this.optionsMenu = new JMenu(); this.themeSettingsMenu = new JMenuItem(); JMenu helpMenu = new JMenu(); JMenuItem aboutMenuItem = new JMenuItem(); JMenuItem donateMenuItem = new JMenuItem(); this.statusPanel = new JPanel(); JSeparator statusPanelSeparator = new JSeparator(); this.statusMessageLabel = new JLabel(); this.statusAnimationLabel = new JLabel(); this.progressBar = new JProgressBar(); this.mainPanel.setMaximumSize(new Dimension(800, 600)); this.mainPanel.setMinimumSize(new Dimension(800, 600)); this.mainPanel.setName("mainPanel"); this.mainPanel.setPreferredSize(new Dimension(800, 600)); this.gamesPane.setName("gamesPane"); GroupLayout mainPanelLayout = new GroupLayout(this.mainPanel); this.mainPanel.setLayout(mainPanelLayout); mainPanelLayout.setHorizontalGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(mainPanelLayout.createSequentialGroup().addContainerGap().addComponent(this.gamesPane, -1, 776, 32767).addContainerGap())); mainPanelLayout.setVerticalGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(mainPanelLayout.createSequentialGroup().addContainerGap().addComponent(this.gamesPane, -1, 580, 32767))); this.menuBar.setName("menuBar"); ResourceMap resourceMap = ((JChessApp)Application.getInstance(JChessApp.class)).getContext().getResourceMap(JChessView.class); fileMenu.setText(resourceMap.getString("fileMenu.text", new Object[0])); fileMenu.setName("fileMenu"); this.newGameItem.setAccelerator(KeyStroke.getKeyStroke(78, 2)); this.newGameItem.setText(resourceMap.getString("newGameItem.text", new Object[0])); this.newGameItem.setName("newGameItem"); fileMenu.add(this.newGameItem); this.newGameItem.addActionListener(this); this.loadGameItem.setAccelerator(KeyStroke.getKeyStroke(76, 2)); this.loadGameItem.setText(resourceMap.getString("loadGameItem.text", new Object[0])); this.loadGameItem.setName("loadGameItem"); fileMenu.add(this.loadGameItem); this.loadGameItem.addActionListener(this); this.saveGameItem.setAccelerator(KeyStroke.getKeyStroke(83, 2)); this.saveGameItem.setText(resourceMap.getString("saveGameItem.text", new Object[0])); this.saveGameItem.setName("saveGameItem"); fileMenu.add(this.saveGameItem); this.saveGameItem.addActionListener(this); ApplicationActionMap applicationActionMap = ((JChessApp)Application.getInstance(JChessApp.class)).getContext().getActionMap(JChessView.class, this); exitMenuItem.setAction(applicationActionMap.get("quit")); exitMenuItem.setName("exitMenuItem"); fileMenu.add(exitMenuItem); this.menuBar.add(fileMenu); this.gameMenu.setText(resourceMap.getString("gameMenu.text", new Object[0])); this.gameMenu.setName("gameMenu"); this.moveBackItem.setAccelerator(KeyStroke.getKeyStroke(90, 2)); this.moveBackItem.setText(resourceMap.getString("moveBackItem.text", new Object[0])); this.moveBackItem.setName("moveBackItem"); this.moveBackItem.addMouseListener(new MouseAdapter() { public void mouseClicked(MouseEvent evt) { JChessView.this.moveBackItemMouseClicked(evt); } }
/*     */       ); this.moveBackItem.addActionListener(evt -> moveBackItemActionPerformed(evt)); this.gameMenu.add(this.moveBackItem); this.moveForwardItem.setAccelerator(KeyStroke.getKeyStroke(89, 2)); this.moveForwardItem.setText(resourceMap.getString("moveForwardItem.text", new Object[0])); this.moveForwardItem.setName("moveForwardItem"); this.moveForwardItem.addMouseListener(new MouseAdapter() {
/*     */           public void mouseClicked(MouseEvent evt) { JChessView.this.moveForwardItemMouseClicked(evt); }
/* 296 */         }); this.moveForwardItem.addActionListener(evt -> moveForwardItemActionPerformed(evt)); this.gameMenu.add(this.moveForwardItem); this.rewindToBegin.setAccelerator(KeyStroke.getKeyStroke(90, 3)); this.rewindToBegin.setText(resourceMap.getString("rewindToBegin.text", new Object[0])); this.rewindToBegin.setName("rewindToBegin"); this.rewindToBegin.addActionListener(evt -> rewindToBeginActionPerformed(evt)); this.gameMenu.add(this.rewindToBegin); this.rewindToEnd.setAccelerator(KeyStroke.getKeyStroke(89, 3)); this.rewindToEnd.setText(resourceMap.getString("rewindToEnd.text", new Object[0])); this.rewindToEnd.setName("rewindToEnd"); this.rewindToEnd.addActionListener(evt -> rewindToEndActionPerformed(evt)); this.gameMenu.add(this.rewindToEnd); this.menuBar.add(this.gameMenu); this.optionsMenu.setText(resourceMap.getString("optionsMenu.text", new Object[0])); this.optionsMenu.setName("optionsMenu"); this.themeSettingsMenu.setText(resourceMap.getString("themeSettingsMenu.text", new Object[0])); this.themeSettingsMenu.setName("themeSettingsMenu"); this.optionsMenu.add(this.themeSettingsMenu); this.themeSettingsMenu.addActionListener(this); this.menuBar.add(this.optionsMenu); helpMenu.setText(resourceMap.getString("helpMenu.text", new Object[0])); helpMenu.setName("helpMenu"); aboutMenuItem.setAction(applicationActionMap.get("showAboutBox")); aboutMenuItem.setName("aboutMenuItem"); donateMenuItem.setText(resourceMap.getString("donateMenu.text", new Object[0])); donateMenuItem.setName("donateMenu"); donateMenuItem.addActionListener(event -> showDonateWindow()); helpMenu.add(aboutMenuItem); this.menuBar.add(helpMenu); this.menuBar.add(donateMenuItem); this.statusPanel.setName("statusPanel"); statusPanelSeparator.setName("statusPanelSeparator"); this.statusMessageLabel.setName("statusMessageLabel"); this.statusAnimationLabel.setHorizontalAlignment(2); this.statusAnimationLabel.setName("statusAnimationLabel"); this.progressBar.setName("progressBar"); GroupLayout statusPanelLayout = new GroupLayout(this.statusPanel); this.statusPanel.setLayout(statusPanelLayout); statusPanelLayout.setHorizontalGroup(statusPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(statusPanelSeparator, -1, 800, 32767).addGroup(statusPanelLayout.createSequentialGroup().addContainerGap().addComponent(this.statusMessageLabel).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 616, 32767).addComponent(this.progressBar, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.statusAnimationLabel).addContainerGap())); statusPanelLayout.setVerticalGroup(statusPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(statusPanelLayout.createSequentialGroup().addComponent(statusPanelSeparator, -2, 2, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addGroup(statusPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.statusMessageLabel).addComponent(this.statusAnimationLabel).addComponent(this.progressBar, -2, -1, -2)).addGap(3, 3, 3))); setComponent(this.mainPanel); setMenuBar(this.menuBar); setStatusBar(this.statusPanel); } private JFileChooser initFileChooser() { JFileChooser fc = new JFileChooser();
/*     */     
/* 298 */     FileFilter pgnFilter = new FileNameExtensionFilter(Settings.lang("pgn_file"), new String[] { "pgn" });
/*     */ 
/*     */ 
/*     */     
/* 302 */     FileFilter fenFilter = new FileNameExtensionFilter(Settings.lang("fen_file"), new String[] { "fen" });
/*     */ 
/*     */     
/* 305 */     fc.setFileFilter(fenFilter);
/* 306 */     fc.setFileFilter(pgnFilter);
/* 307 */     return fc; }
/*     */ 
/*     */ 
/*     */   
/*     */   public JChessView(SingleFrameApplication app) {
/* 312 */     super((Application)app);
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
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 754 */     this.busyIcons = new Icon[15];
/* 755 */     this.busyIconIndex = 0; initComponents(); ResourceMap resourceMap = getResourceMap(); int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout").intValue(); this.messageTimer = new Timer(messageTimeout, e -> this.statusMessageLabel.setText("")); this.messageTimer.setRepeats(false); int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate").intValue(); for (int i = 0; i < this.busyIcons.length; i++)
/*     */       this.busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");  this.busyIconTimer = new Timer(busyAnimationRate, e -> { this.busyIconIndex = (this.busyIconIndex + 1) % this.busyIcons.length; this.statusAnimationLabel.setIcon(this.busyIcons[this.busyIconIndex]); }); this.idleIcon = resourceMap.getIcon("StatusBar.idleIcon"); this.statusAnimationLabel.setIcon(this.idleIcon); this.progressBar.setVisible(false); TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext()); taskMonitor.addPropertyChangeListener(evt -> { int value; String text, propertyName = evt.getPropertyName(); switch (propertyName) { case "started": if (!this.busyIconTimer.isRunning()) { this.statusAnimationLabel.setIcon(this.busyIcons[0]); this.busyIconIndex = 0; this.busyIconTimer.start(); }  this.progressBar.setVisible(true); this.progressBar.setIndeterminate(true); break;
/*     */             case "done": this.busyIconTimer.stop(); this.statusAnimationLabel.setIcon(this.idleIcon); this.progressBar.setVisible(false); this.progressBar.setValue(0); break;
/*     */             case "message":
/*     */               text = (String)evt.getNewValue(); this.statusMessageLabel.setText((text == null) ? "" : text); this.messageTimer.restart(); break;
/*     */             case "progress":
/*     */               value = ((Integer)evt.getNewValue()).intValue(); this.progressBar.setVisible(true); this.progressBar.setIndeterminate(false); this.progressBar.setValue(value); break; }  }); } private void moveBackItemActionPerformed(ActionEvent evt) { if (getGui() != null && getGui().getGame() != null) { getGui().getGame().undo(); } else { try { Game activeGame = getActiveTabGame(); if (!activeGame.undo())
/*     */           JOptionPane.showMessageDialog(null, Settings.lang("noMoreUndoMovesInMemory"));  } catch (ArrayIndexOutOfBoundsException exc) { JOptionPane.showMessageDialog(null, Settings.lang("activeTabDoesNotExists")); } catch (UnsupportedOperationException exc) { JOptionPane.showMessageDialog(null, exc.getMessage()); }  }  } private void moveBackItemMouseClicked(MouseEvent evt) {}
/* 763 */   public void componentResized(ComponentEvent e) { LOG.debug("jchessView has been resized !");
/* 764 */     throw new UnsupportedOperationException("Not supported yet."); } private void moveForwardItemMouseClicked(MouseEvent evt) {} private void moveForwardItemActionPerformed(ActionEvent evt) { if (getGui() != null && getGui().getGame() != null) { getGui().getGame().redo(); } else { try { Game activeGame = getActiveTabGame(); if (!activeGame.redo()) JOptionPane.showMessageDialog(null, Settings.lang("noMoreRedoMovesInMemory"));  } catch (ArrayIndexOutOfBoundsException exc) { JOptionPane.showMessageDialog(null, Settings.lang("activeTabDoesNotExists")); } catch (UnsupportedOperationException exc) { JOptionPane.showMessageDialog(null, exc.getMessage()); }  }  }
/*     */   private void rewindToBeginActionPerformed(ActionEvent evt) { try { Game activeGame = getActiveTabGame(); if (!activeGame.rewindToBegin()) JOptionPane.showMessageDialog(null, Settings.lang("noMoreRedoMovesInMemory"));  } catch (ArrayIndexOutOfBoundsException exc) { JOptionPane.showMessageDialog(null, Settings.lang("activeTabDoesNotExists")); } catch (UnsupportedOperationException exc) { JOptionPane.showMessageDialog(null, exc.getMessage()); }  }
/*     */   private void showDonateWindow() { if (Desktop.isDesktopSupported()) try { ResourceMap resourceMap = ((JChessApp)Application.getInstance(JChessApp.class)).getContext().getResourceMap(JChessApp.class); Desktop.getDesktop().browse(new URI(resourceMap.getString("Application.donateUrl", new Object[0]))); } catch (URISyntaxException|IOException ex) { LOG.error(ex.getMessage()); }   }
/*     */   private void rewindToEndActionPerformed(ActionEvent evt) { try { Game activeGame = getActiveTabGame(); if (!activeGame.rewindToEnd())
/*     */         JOptionPane.showMessageDialog(null, Settings.lang("noMoreUndoMovesInMemory"));  } catch (ArrayIndexOutOfBoundsException exc) { JOptionPane.showMessageDialog(null, Settings.lang("activeTabDoesNotExists")); } catch (UnsupportedOperationException exc) { JOptionPane.showMessageDialog(null, exc.getMessage()); }  }
/* 769 */   public Game getActiveTabGame() throws ArrayIndexOutOfBoundsException { Game activeGame = (Game)this.gamesPane.getComponentAt(this.gamesPane.getSelectedIndex());
/* 770 */     return activeGame; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 775 */   public void setActiveTabGame(int index) throws ArrayIndexOutOfBoundsException { this.gamesPane.setSelectedIndex(index); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 780 */   public void setLastTabAsActive() { this.gamesPane.setSelectedIndex(this.gamesPane.getTabCount() - 1); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 785 */   public int getNumberOfOpenedTabs() { return this.gamesPane.getTabCount(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 790 */   public void componentMoved(ComponentEvent e) { throw new UnsupportedOperationException("Not supported yet."); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 795 */   public void componentShown(ComponentEvent e) { throw new UnsupportedOperationException("Not supported yet."); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 800 */   public void componentHidden(ComponentEvent e) { throw new UnsupportedOperationException("Not supported yet."); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 808 */   public JDialog getNewGameFrame() { return this.newGameFrame; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 816 */   public void setNewGameFrame(JDialog newGameFrame) { this.newGameFrame = newGameFrame; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 821 */   public JTabbedPane getGamesPane() { return this.gamesPane; }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\JChessView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */