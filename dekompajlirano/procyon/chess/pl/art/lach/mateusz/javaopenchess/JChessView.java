// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess;

import java.beans.PropertyChangeEvent;
import java.awt.event.ComponentEvent;
import java.net.URISyntaxException;
import java.net.URI;
import java.awt.Desktop;
import javax.swing.ActionMap;
import org.jdesktop.application.ResourceMap;
import javax.swing.JComponent;
import javax.swing.LayoutStyle;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.KeyStroke;
import org.jdesktop.application.Application;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.GroupLayout;
import java.awt.Dimension;
import javax.swing.JSeparator;
import pl.art.lach.mateusz.javaopenchess.display.windows.JChessTabbedPane;
import org.jdesktop.application.Action;
import javax.swing.JFrame;
import pl.art.lach.mateusz.javaopenchess.display.windows.JChessAboutBox;
import org.jdesktop.application.SingleFrameApplication;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import pl.art.lach.mateusz.javaopenchess.core.data_transfer.TransferFormat;
import pl.art.lach.mateusz.javaopenchess.core.data_transfer.DataImporter;
import pl.art.lach.mateusz.javaopenchess.core.exceptions.ReadGameError;
import org.apache.commons.io.FileUtils;
import pl.art.lach.mateusz.javaopenchess.display.windows.NewGameWindow;
import pl.art.lach.mateusz.javaopenchess.core.data_transfer.DataExporter;
import java.io.File;
import javax.swing.JFileChooser;
import pl.art.lach.mateusz.javaopenchess.core.data_transfer.DataTransferFactory;
import java.io.IOException;
import pl.art.lach.mateusz.javaopenchess.utils.Settings;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import java.awt.Frame;
import pl.art.lach.mateusz.javaopenchess.display.windows.ThemeChooseWindow;
import java.awt.event.ActionEvent;
import java.awt.Component;
import pl.art.lach.mateusz.javaopenchess.core.Game;
import pl.art.lach.mateusz.javaopenchess.display.windows.PawnPromotionWindow;
import javax.swing.JDialog;
import javax.swing.Icon;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JMenu;
import pl.art.lach.mateusz.javaopenchess.utils.GUI;
import org.apache.log4j.Logger;
import java.awt.event.ComponentListener;
import java.awt.event.ActionListener;
import org.jdesktop.application.FrameView;

public class JChessView extends FrameView implements ActionListener, ComponentListener
{
    private static final Logger LOG;
    private static final String TAB_LABEL_STRING_FORMAT = "%s vs %s";
    protected static GUI gui;
    private JMenu gameMenu;
    private JTabbedPane gamesPane;
    private JMenuItem loadGameItem;
    public JPanel mainPanel;
    private JMenuBar menuBar;
    private JMenuItem moveBackItem;
    private JMenuItem moveForwardItem;
    private JMenuItem newGameItem;
    private JMenu optionsMenu;
    private JProgressBar progressBar;
    private JMenuItem rewindToBegin;
    private JMenuItem rewindToEnd;
    private JMenuItem saveGameItem;
    private JLabel statusAnimationLabel;
    private JLabel statusMessageLabel;
    private JPanel statusPanel;
    private JMenuItem themeSettingsMenu;
    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons;
    private int busyIconIndex;
    private JDialog aboutBox;
    private PawnPromotionWindow promotionBox;
    private JDialog newGameFrame;
    
    public static GUI getGui() {
        return JChessView.gui;
    }
    
    public Game addNewTab(final String title) {
        final Game game = new Game();
        this.gamesPane.addTab(title, game);
        return game;
    }
    
    public void addNewTab(final Game game) {
        if (null != game) {
            final String title = String.format("%s vs %s", game.getSettings().getPlayerWhite().getName(), game.getSettings().getPlayerBlack().getName());
            this.gamesPane.addTab(title, game);
        }
    }
    
    public Component getTabComponent(final Game game) {
        final int tabNumber = this.getTabNumber(game);
        if (0 <= tabNumber) {
            return this.gamesPane.getComponent(tabNumber);
        }
        return null;
    }
    
    public int getTabNumber(final Game game) {
        for (int i = 0; i < this.gamesPane.getTabCount(); ++i) {
            final Component component = this.gamesPane.getComponent(i);
            if (game == component) {
                return i;
            }
        }
        return -1;
    }
    
    @Override
    public void actionPerformed(final ActionEvent event) {
        final Object target = event.getSource();
        if (target == this.newGameItem) {
            this.newGame();
        }
        else if (target == this.saveGameItem) {
            this.saveGame();
        }
        else if (target == this.loadGameItem) {
            this.loadGame();
        }
        else if (target == this.themeSettingsMenu) {
            this.runThemeSettingsWindow();
        }
    }
    
    private void runThemeSettingsWindow() throws HeadlessException {
        try {
            final ThemeChooseWindow choose = new ThemeChooseWindow(this.getFrame());
            JChessApp.getApplication().show(choose);
        }
        catch (Exception exc) {
            JOptionPane.showMessageDialog(JChessApp.getApplication().getMainFrame(), exc.getMessage());
            JChessView.LOG.error("Something wrong creating window - perhaps themeList is null: ", exc);
        }
    }
    
    private boolean saveGame() throws HeadlessException {
        if (this.gamesPane.getTabCount() == 0) {
            JOptionPane.showMessageDialog(null, Settings.lang("save_not_called_for_tab"));
            return true;
        }
        JFileChooser fc;
        File selFile;
        Game tempGUI;
        while (true) {
            fc = this.initFileChooser();
            final int retVal = fc.showSaveDialog(this.gamesPane);
            if (retVal == 0) {
                selFile = fc.getSelectedFile();
                tempGUI = (Game)this.gamesPane.getComponentAt(this.gamesPane.getSelectedIndex());
                if (!selFile.exists()) {
                    try {
                        selFile.createNewFile();
                    }
                    catch (IOException exc) {
                        JChessView.LOG.error("error creating file: ", exc);
                    }
                    break;
                }
                if (!selFile.exists()) {
                    break;
                }
                final int opt = JOptionPane.showConfirmDialog(tempGUI, Settings.lang("file_exists"), Settings.lang("file_exists"), 0);
                if (opt == 1) {
                    continue;
                }
                break;
            }
            else {
                if (retVal == 1) {
                    return false;
                }
                continue;
            }
        }
        if (selFile.canWrite()) {
            try {
                final DataExporter dataExporter = DataTransferFactory.getExporterInstance(this.getDataTransfer(selFile));
                tempGUI.saveGame(selFile, dataExporter);
            }
            catch (IllegalArgumentException exc2) {
                JChessView.LOG.error(exc2);
                JOptionPane.showMessageDialog(null, Settings.lang("unknown_format"));
            }
        }
        JChessView.LOG.debug(fc.getSelectedFile().isFile());
        return false;
    }
    
    private void newGame() {
        this.setNewGameFrame(new NewGameWindow());
        JChessApp.getApplication().show(this.getNewGameFrame());
    }
    
    private void loadGame() throws HeadlessException {
        final JFileChooser fc = this.initFileChooser();
        final int retVal = fc.showOpenDialog(this.gamesPane);
        if (retVal == 0) {
            final File file = fc.getSelectedFile();
            if (file.exists() && file.canRead()) {
                final DataImporter di = DataTransferFactory.getImporterInstance(this.getDataTransfer(file));
                try {
                    final Game game = di.importData(FileUtils.readFileToString(file));
                    this.addNewTab(game);
                    if (null != JChessApp.getJavaChessView()) {
                        JChessApp.getJavaChessView().setLastTabAsActive();
                    }
                }
                catch (IOException exc) {
                    JChessView.LOG.error(exc);
                    JOptionPane.showMessageDialog(null, Settings.lang("error_writing_to_file") + ": " + exc);
                }
                catch (ReadGameError exc2) {
                    JChessView.LOG.error(exc2);
                    JOptionPane.showMessageDialog(null, exc2.getMessage());
                }
                catch (IllegalArgumentException exc3) {
                    JChessView.LOG.error(exc3);
                    JOptionPane.showMessageDialog(null, Settings.lang("unknown_format"));
                }
            }
        }
    }
    
    private TransferFormat getDataTransfer(final File file) {
        final String name = file.getName();
        final String[] nameParts = name.split("\\.");
        return TransferFormat.valueOf(nameParts[nameParts.length - 1].toUpperCase());
    }
    
    private JFileChooser initFileChooser() {
        final JFileChooser fc = new JFileChooser();
        final FileFilter pgnFilter = new FileNameExtensionFilter(Settings.lang("pgn_file"), new String[] { "pgn" });
        final FileFilter fenFilter = new FileNameExtensionFilter(Settings.lang("fen_file"), new String[] { "fen" });
        fc.setFileFilter(fenFilter);
        fc.setFileFilter(pgnFilter);
        return fc;
    }
    
    public JChessView(final SingleFrameApplication app) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: aload_1         /* app */
        //     2: invokespecial   org/jdesktop/application/FrameView.<init>:(Lorg/jdesktop/application/Application;)V
        //     5: aload_0         /* this */
        //     6: bipush          15
        //     8: anewarray       Ljavax/swing/Icon;
        //    11: putfield        pl/art/lach/mateusz/javaopenchess/JChessView.busyIcons:[Ljavax/swing/Icon;
        //    14: aload_0         /* this */
        //    15: iconst_0       
        //    16: putfield        pl/art/lach/mateusz/javaopenchess/JChessView.busyIconIndex:I
        //    19: aload_0         /* this */
        //    20: invokespecial   pl/art/lach/mateusz/javaopenchess/JChessView.initComponents:()V
        //    23: aload_0         /* this */
        //    24: invokevirtual   pl/art/lach/mateusz/javaopenchess/JChessView.getResourceMap:()Lorg/jdesktop/application/ResourceMap;
        //    27: astore_2        /* resourceMap */
        //    28: aload_2         /* resourceMap */
        //    29: ldc             "StatusBar.messageTimeout"
        //    31: invokevirtual   org/jdesktop/application/ResourceMap.getInteger:(Ljava/lang/String;)Ljava/lang/Integer;
        //    34: invokevirtual   java/lang/Integer.intValue:()I
        //    37: istore_3        /* messageTimeout */
        //    38: aload_0         /* this */
        //    39: new             Ljavax/swing/Timer;
        //    42: dup            
        //    43: iload_3         /* messageTimeout */
        //    44: aload_0         /* this */
        //    45: invokedynamic   BootstrapMethod #0, actionPerformed:(Lpl/art/lach/mateusz/javaopenchess/JChessView;)Ljava/awt/event/ActionListener;
        //    50: invokespecial   javax/swing/Timer.<init>:(ILjava/awt/event/ActionListener;)V
        //    53: putfield        pl/art/lach/mateusz/javaopenchess/JChessView.messageTimer:Ljavax/swing/Timer;
        //    56: aload_0         /* this */
        //    57: getfield        pl/art/lach/mateusz/javaopenchess/JChessView.messageTimer:Ljavax/swing/Timer;
        //    60: iconst_0       
        //    61: invokevirtual   javax/swing/Timer.setRepeats:(Z)V
        //    64: aload_2         /* resourceMap */
        //    65: ldc             "StatusBar.busyAnimationRate"
        //    67: invokevirtual   org/jdesktop/application/ResourceMap.getInteger:(Ljava/lang/String;)Ljava/lang/Integer;
        //    70: invokevirtual   java/lang/Integer.intValue:()I
        //    73: istore          busyAnimationRate
        //    75: iconst_0       
        //    76: istore          i
        //    78: iload           i
        //    80: aload_0         /* this */
        //    81: getfield        pl/art/lach/mateusz/javaopenchess/JChessView.busyIcons:[Ljavax/swing/Icon;
        //    84: arraylength    
        //    85: if_icmpge       130
        //    88: aload_0         /* this */
        //    89: getfield        pl/art/lach/mateusz/javaopenchess/JChessView.busyIcons:[Ljavax/swing/Icon;
        //    92: iload           i
        //    94: aload_2         /* resourceMap */
        //    95: new             Ljava/lang/StringBuilder;
        //    98: dup            
        //    99: invokespecial   java/lang/StringBuilder.<init>:()V
        //   102: ldc             "StatusBar.busyIcons["
        //   104: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   107: iload           i
        //   109: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   112: ldc             "]"
        //   114: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   117: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   120: invokevirtual   org/jdesktop/application/ResourceMap.getIcon:(Ljava/lang/String;)Ljavax/swing/Icon;
        //   123: aastore        
        //   124: iinc            i, 1
        //   127: goto            78
        //   130: aload_0         /* this */
        //   131: new             Ljavax/swing/Timer;
        //   134: dup            
        //   135: iload           busyAnimationRate
        //   137: aload_0         /* this */
        //   138: invokedynamic   BootstrapMethod #1, actionPerformed:(Lpl/art/lach/mateusz/javaopenchess/JChessView;)Ljava/awt/event/ActionListener;
        //   143: invokespecial   javax/swing/Timer.<init>:(ILjava/awt/event/ActionListener;)V
        //   146: putfield        pl/art/lach/mateusz/javaopenchess/JChessView.busyIconTimer:Ljavax/swing/Timer;
        //   149: aload_0         /* this */
        //   150: aload_2         /* resourceMap */
        //   151: ldc             "StatusBar.idleIcon"
        //   153: invokevirtual   org/jdesktop/application/ResourceMap.getIcon:(Ljava/lang/String;)Ljavax/swing/Icon;
        //   156: putfield        pl/art/lach/mateusz/javaopenchess/JChessView.idleIcon:Ljavax/swing/Icon;
        //   159: aload_0         /* this */
        //   160: getfield        pl/art/lach/mateusz/javaopenchess/JChessView.statusAnimationLabel:Ljavax/swing/JLabel;
        //   163: aload_0         /* this */
        //   164: getfield        pl/art/lach/mateusz/javaopenchess/JChessView.idleIcon:Ljavax/swing/Icon;
        //   167: invokevirtual   javax/swing/JLabel.setIcon:(Ljavax/swing/Icon;)V
        //   170: aload_0         /* this */
        //   171: getfield        pl/art/lach/mateusz/javaopenchess/JChessView.progressBar:Ljavax/swing/JProgressBar;
        //   174: iconst_0       
        //   175: invokevirtual   javax/swing/JProgressBar.setVisible:(Z)V
        //   178: new             Lorg/jdesktop/application/TaskMonitor;
        //   181: dup            
        //   182: aload_0         /* this */
        //   183: invokevirtual   pl/art/lach/mateusz/javaopenchess/JChessView.getApplication:()Lorg/jdesktop/application/Application;
        //   186: invokevirtual   org/jdesktop/application/Application.getContext:()Lorg/jdesktop/application/ApplicationContext;
        //   189: invokespecial   org/jdesktop/application/TaskMonitor.<init>:(Lorg/jdesktop/application/ApplicationContext;)V
        //   192: astore          taskMonitor
        //   194: aload           taskMonitor
        //   196: aload_0         /* this */
        //   197: invokedynamic   BootstrapMethod #2, propertyChange:(Lpl/art/lach/mateusz/javaopenchess/JChessView;)Ljava/beans/PropertyChangeListener;
        //   202: invokevirtual   org/jdesktop/application/TaskMonitor.addPropertyChangeListener:(Ljava/beans/PropertyChangeListener;)V
        //   205: return         
        //    StackMapTable: 00 02 FF 00 4E 00 06 07 01 A7 07 01 C9 07 01 CA 01 01 01 00 00 FA 00 33
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.languages.java.ast.NameVariables.generateNameForVariable(NameVariables.java:264)
        //     at com.strobel.decompiler.languages.java.ast.NameVariables.assignNamesToVariables(NameVariables.java:198)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:276)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:713)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:549)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Action
    public void showAboutBox() {
        if (this.aboutBox == null) {
            final JFrame mainFrame = JChessApp.getApplication().getMainFrame();
            (this.aboutBox = new JChessAboutBox(mainFrame)).setLocationRelativeTo(mainFrame);
        }
        JChessApp.getApplication().show(this.aboutBox);
    }
    
    public String showPawnPromotionBox(final String color) {
        if (this.promotionBox == null) {
            final JFrame mainFrame = JChessApp.getApplication().getMainFrame();
            (this.promotionBox = new PawnPromotionWindow(mainFrame, color)).setLocationRelativeTo(mainFrame);
            this.promotionBox.setModal(true);
        }
        this.promotionBox.setColor(color);
        JChessApp.getApplication().show(this.promotionBox);
        return this.promotionBox.result;
    }
    
    private void initComponents() {
        this.mainPanel = new JPanel();
        this.gamesPane = new JChessTabbedPane();
        this.menuBar = new JMenuBar();
        final JMenu fileMenu = new JMenu();
        this.newGameItem = new JMenuItem();
        this.loadGameItem = new JMenuItem();
        this.saveGameItem = new JMenuItem();
        final JMenuItem exitMenuItem = new JMenuItem();
        this.gameMenu = new JMenu();
        this.moveBackItem = new JMenuItem();
        this.moveForwardItem = new JMenuItem();
        this.rewindToBegin = new JMenuItem();
        this.rewindToEnd = new JMenuItem();
        this.optionsMenu = new JMenu();
        this.themeSettingsMenu = new JMenuItem();
        final JMenu helpMenu = new JMenu();
        final JMenuItem aboutMenuItem = new JMenuItem();
        final JMenuItem donateMenuItem = new JMenuItem();
        this.statusPanel = new JPanel();
        final JSeparator statusPanelSeparator = new JSeparator();
        this.statusMessageLabel = new JLabel();
        this.statusAnimationLabel = new JLabel();
        this.progressBar = new JProgressBar();
        this.mainPanel.setMaximumSize(new Dimension(800, 600));
        this.mainPanel.setMinimumSize(new Dimension(800, 600));
        this.mainPanel.setName("mainPanel");
        this.mainPanel.setPreferredSize(new Dimension(800, 600));
        this.gamesPane.setName("gamesPane");
        final GroupLayout mainPanelLayout = new GroupLayout(this.mainPanel);
        this.mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(mainPanelLayout.createSequentialGroup().addContainerGap().addComponent(this.gamesPane, -1, 776, 32767).addContainerGap()));
        mainPanelLayout.setVerticalGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(mainPanelLayout.createSequentialGroup().addContainerGap().addComponent(this.gamesPane, -1, 580, 32767)));
        this.menuBar.setName("menuBar");
        final ResourceMap resourceMap = Application.getInstance(JChessApp.class).getContext().getResourceMap(JChessView.class);
        fileMenu.setText(resourceMap.getString("fileMenu.text", new Object[0]));
        fileMenu.setName("fileMenu");
        this.newGameItem.setAccelerator(KeyStroke.getKeyStroke(78, 2));
        this.newGameItem.setText(resourceMap.getString("newGameItem.text", new Object[0]));
        this.newGameItem.setName("newGameItem");
        fileMenu.add(this.newGameItem);
        this.newGameItem.addActionListener(this);
        this.loadGameItem.setAccelerator(KeyStroke.getKeyStroke(76, 2));
        this.loadGameItem.setText(resourceMap.getString("loadGameItem.text", new Object[0]));
        this.loadGameItem.setName("loadGameItem");
        fileMenu.add(this.loadGameItem);
        this.loadGameItem.addActionListener(this);
        this.saveGameItem.setAccelerator(KeyStroke.getKeyStroke(83, 2));
        this.saveGameItem.setText(resourceMap.getString("saveGameItem.text", new Object[0]));
        this.saveGameItem.setName("saveGameItem");
        fileMenu.add(this.saveGameItem);
        this.saveGameItem.addActionListener(this);
        final ActionMap actionMap = Application.getInstance(JChessApp.class).getContext().getActionMap(JChessView.class, this);
        exitMenuItem.setAction(actionMap.get("quit"));
        exitMenuItem.setName("exitMenuItem");
        fileMenu.add(exitMenuItem);
        this.menuBar.add(fileMenu);
        this.gameMenu.setText(resourceMap.getString("gameMenu.text", new Object[0]));
        this.gameMenu.setName("gameMenu");
        this.moveBackItem.setAccelerator(KeyStroke.getKeyStroke(90, 2));
        this.moveBackItem.setText(resourceMap.getString("moveBackItem.text", new Object[0]));
        this.moveBackItem.setName("moveBackItem");
        this.moveBackItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent evt) {
                JChessView.this.moveBackItemMouseClicked(evt);
            }
        });
        this.moveBackItem.addActionListener(evt -> this.moveBackItemActionPerformed(evt));
        this.gameMenu.add(this.moveBackItem);
        this.moveForwardItem.setAccelerator(KeyStroke.getKeyStroke(89, 2));
        this.moveForwardItem.setText(resourceMap.getString("moveForwardItem.text", new Object[0]));
        this.moveForwardItem.setName("moveForwardItem");
        this.moveForwardItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent evt) {
                JChessView.this.moveForwardItemMouseClicked(evt);
            }
        });
        this.moveForwardItem.addActionListener(evt -> this.moveForwardItemActionPerformed(evt));
        this.gameMenu.add(this.moveForwardItem);
        this.rewindToBegin.setAccelerator(KeyStroke.getKeyStroke(90, 3));
        this.rewindToBegin.setText(resourceMap.getString("rewindToBegin.text", new Object[0]));
        this.rewindToBegin.setName("rewindToBegin");
        this.rewindToBegin.addActionListener(evt -> this.rewindToBeginActionPerformed(evt));
        this.gameMenu.add(this.rewindToBegin);
        this.rewindToEnd.setAccelerator(KeyStroke.getKeyStroke(89, 3));
        this.rewindToEnd.setText(resourceMap.getString("rewindToEnd.text", new Object[0]));
        this.rewindToEnd.setName("rewindToEnd");
        this.rewindToEnd.addActionListener(evt -> this.rewindToEndActionPerformed(evt));
        this.gameMenu.add(this.rewindToEnd);
        this.menuBar.add(this.gameMenu);
        this.optionsMenu.setText(resourceMap.getString("optionsMenu.text", new Object[0]));
        this.optionsMenu.setName("optionsMenu");
        this.themeSettingsMenu.setText(resourceMap.getString("themeSettingsMenu.text", new Object[0]));
        this.themeSettingsMenu.setName("themeSettingsMenu");
        this.optionsMenu.add(this.themeSettingsMenu);
        this.themeSettingsMenu.addActionListener(this);
        this.menuBar.add(this.optionsMenu);
        helpMenu.setText(resourceMap.getString("helpMenu.text", new Object[0]));
        helpMenu.setName("helpMenu");
        aboutMenuItem.setAction(actionMap.get("showAboutBox"));
        aboutMenuItem.setName("aboutMenuItem");
        donateMenuItem.setText(resourceMap.getString("donateMenu.text", new Object[0]));
        donateMenuItem.setName("donateMenu");
        donateMenuItem.addActionListener(event -> this.showDonateWindow());
        helpMenu.add(aboutMenuItem);
        this.menuBar.add(helpMenu);
        this.menuBar.add(donateMenuItem);
        this.statusPanel.setName("statusPanel");
        statusPanelSeparator.setName("statusPanelSeparator");
        this.statusMessageLabel.setName("statusMessageLabel");
        this.statusAnimationLabel.setHorizontalAlignment(2);
        this.statusAnimationLabel.setName("statusAnimationLabel");
        this.progressBar.setName("progressBar");
        final GroupLayout statusPanelLayout = new GroupLayout(this.statusPanel);
        this.statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(statusPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(statusPanelSeparator, -1, 800, 32767).addGroup(statusPanelLayout.createSequentialGroup().addContainerGap().addComponent(this.statusMessageLabel).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 616, 32767).addComponent(this.progressBar, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.statusAnimationLabel).addContainerGap()));
        statusPanelLayout.setVerticalGroup(statusPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(statusPanelLayout.createSequentialGroup().addComponent(statusPanelSeparator, -2, 2, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addGroup(statusPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.statusMessageLabel).addComponent(this.statusAnimationLabel).addComponent(this.progressBar, -2, -1, -2)).addGap(3, 3, 3)));
        this.setComponent(this.mainPanel);
        this.setMenuBar(this.menuBar);
        this.setStatusBar(this.statusPanel);
    }
    
    private void moveBackItemActionPerformed(final ActionEvent evt) {
        if (getGui() != null && getGui().getGame() != null) {
            getGui().getGame().undo();
        }
        else {
            try {
                final Game activeGame = this.getActiveTabGame();
                if (!activeGame.undo()) {
                    JOptionPane.showMessageDialog(null, Settings.lang("noMoreUndoMovesInMemory"));
                }
            }
            catch (ArrayIndexOutOfBoundsException exc2) {
                JOptionPane.showMessageDialog(null, Settings.lang("activeTabDoesNotExists"));
            }
            catch (UnsupportedOperationException exc) {
                JOptionPane.showMessageDialog(null, exc.getMessage());
            }
        }
    }
    
    private void moveBackItemMouseClicked(final MouseEvent evt) {
    }
    
    private void moveForwardItemMouseClicked(final MouseEvent evt) {
    }
    
    private void moveForwardItemActionPerformed(final ActionEvent evt) {
        if (getGui() != null && getGui().getGame() != null) {
            getGui().getGame().redo();
        }
        else {
            try {
                final Game activeGame = this.getActiveTabGame();
                if (!activeGame.redo()) {
                    JOptionPane.showMessageDialog(null, Settings.lang("noMoreRedoMovesInMemory"));
                }
            }
            catch (ArrayIndexOutOfBoundsException exc2) {
                JOptionPane.showMessageDialog(null, Settings.lang("activeTabDoesNotExists"));
            }
            catch (UnsupportedOperationException exc) {
                JOptionPane.showMessageDialog(null, exc.getMessage());
            }
        }
    }
    
    private void rewindToBeginActionPerformed(final ActionEvent evt) {
        try {
            final Game activeGame = this.getActiveTabGame();
            if (!activeGame.rewindToBegin()) {
                JOptionPane.showMessageDialog(null, Settings.lang("noMoreRedoMovesInMemory"));
            }
        }
        catch (ArrayIndexOutOfBoundsException exc2) {
            JOptionPane.showMessageDialog(null, Settings.lang("activeTabDoesNotExists"));
        }
        catch (UnsupportedOperationException exc) {
            JOptionPane.showMessageDialog(null, exc.getMessage());
        }
    }
    
    private void showDonateWindow() {
        if (Desktop.isDesktopSupported()) {
            try {
                final ResourceMap resourceMap = Application.getInstance(JChessApp.class).getContext().getResourceMap(JChessApp.class);
                Desktop.getDesktop().browse(new URI(resourceMap.getString("Application.donateUrl", new Object[0])));
            }
            catch (URISyntaxException | IOException ex3) {
                final Exception ex2;
                final Exception ex = ex2;
                JChessView.LOG.error(ex.getMessage());
            }
        }
    }
    
    private void rewindToEndActionPerformed(final ActionEvent evt) {
        try {
            final Game activeGame = this.getActiveTabGame();
            if (!activeGame.rewindToEnd()) {
                JOptionPane.showMessageDialog(null, Settings.lang("noMoreUndoMovesInMemory"));
            }
        }
        catch (ArrayIndexOutOfBoundsException exc2) {
            JOptionPane.showMessageDialog(null, Settings.lang("activeTabDoesNotExists"));
        }
        catch (UnsupportedOperationException exc) {
            JOptionPane.showMessageDialog(null, exc.getMessage());
        }
    }
    
    @Override
    public void componentResized(final ComponentEvent e) {
        JChessView.LOG.debug("jchessView has been resized !");
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public Game getActiveTabGame() throws ArrayIndexOutOfBoundsException {
        final Game activeGame = (Game)this.gamesPane.getComponentAt(this.gamesPane.getSelectedIndex());
        return activeGame;
    }
    
    public void setActiveTabGame(final int index) throws ArrayIndexOutOfBoundsException {
        this.gamesPane.setSelectedIndex(index);
    }
    
    public void setLastTabAsActive() {
        this.gamesPane.setSelectedIndex(this.gamesPane.getTabCount() - 1);
    }
    
    public int getNumberOfOpenedTabs() {
        return this.gamesPane.getTabCount();
    }
    
    @Override
    public void componentMoved(final ComponentEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void componentShown(final ComponentEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void componentHidden(final ComponentEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public JDialog getNewGameFrame() {
        return this.newGameFrame;
    }
    
    public void setNewGameFrame(final JDialog newGameFrame) {
        this.newGameFrame = newGameFrame;
    }
    
    public JTabbedPane getGamesPane() {
        return this.gamesPane;
    }
    
    static {
        LOG = Logger.getLogger(JChessView.class);
        JChessView.gui = null;
    }
}
