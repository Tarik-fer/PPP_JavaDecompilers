/*     */ package pl.art.lach.mateusz.javaopenchess.display.windows;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Point;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.Properties;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.event.ListSelectionEvent;
/*     */ import javax.swing.event.ListSelectionListener;
/*     */ import org.apache.log4j.Logger;
/*     */ import pl.art.lach.mateusz.javaopenchess.JChessApp;
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
/*     */ public class ThemeChooseWindow
/*     */   extends JDialog
/*     */   implements ActionListener, ListSelectionListener
/*     */ {
/*  40 */   private static final Logger LOG = Logger.getLogger(ThemeChooseWindow.class);
/*     */   
/*     */   JList themesList;
/*     */   
/*     */   ImageIcon themePreview;
/*     */   GridBagLayout gbl;
/*     */   public String result;
/*     */   GridBagConstraints gbc;
/*     */   JButton themePreviewButton;
/*     */   JButton okButton;
/*     */   
/*     */   public ThemeChooseWindow(Frame parent) throws Exception {
/*  52 */     super(parent);
/*     */     
/*  54 */     File dir = new File(GUI.getJarPath() + File.separator + "theme" + File.separator);
/*     */     
/*  56 */     LOG.debug("Theme path: " + dir.getPath());
/*     */     
/*  58 */     File[] files = dir.listFiles();
/*  59 */     if (files != null && dir.exists()) {
/*     */       
/*  61 */       setTitle(Settings.lang("choose_theme_window_title"));
/*  62 */       Dimension winDim = new Dimension(550, 230);
/*  63 */       setMinimumSize(winDim);
/*  64 */       setMaximumSize(winDim);
/*  65 */       setSize(winDim);
/*  66 */       setResizable(false);
/*  67 */       setLayout(null);
/*  68 */       setDefaultCloseOperation(2);
/*  69 */       String[] dirNames = new String[files.length];
/*  70 */       for (int i = 0; i < files.length; i++)
/*     */       {
/*  72 */         dirNames[i] = files[i].getName();
/*     */       }
/*  74 */       this.themesList = new JList<>(dirNames);
/*  75 */       this.themesList.setLocation(new Point(10, 10));
/*  76 */       this.themesList.setSize(new Dimension(100, 120));
/*  77 */       add(this.themesList);
/*  78 */       this.themesList.setSelectionMode(0);
/*  79 */       this.themesList.addListSelectionListener(this);
/*  80 */       Properties prp = GUI.getConfigFile();
/*     */       
/*  82 */       this.gbl = new GridBagLayout();
/*  83 */       this.gbc = new GridBagConstraints();
/*     */       
/*     */       try {
/*  86 */         this.themePreview = new ImageIcon(GUI.loadImage("Preview.png"));
/*     */       }
/*  88 */       catch (NullPointerException exc) {
/*     */         
/*  90 */         LOG.error("NullPointerException: Cannot find preview image: ", exc);
/*  91 */         this.themePreview = new ImageIcon(JChessApp.class.getResource("theme/noPreview.png"));
/*     */         return;
/*     */       } 
/*  94 */       this.result = "";
/*  95 */       this.themePreviewButton = new JButton(this.themePreview);
/*  96 */       this.themePreviewButton.setLocation(new Point(110, 10));
/*  97 */       this.themePreviewButton.setSize(new Dimension(420, 120));
/*  98 */       add(this.themePreviewButton);
/*  99 */       this.okButton = new JButton("OK");
/* 100 */       this.okButton.setLocation(new Point(175, 140));
/* 101 */       this.okButton.setSize(new Dimension(200, 50));
/* 102 */       add(this.okButton);
/* 103 */       this.okButton.addActionListener(this);
/* 104 */       setModal(true);
/*     */     }
/*     */     else {
/*     */       
/* 108 */       throw new Exception(Settings.lang("error_when_creating_theme_config_window"));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void valueChanged(ListSelectionEvent event) {
/* 116 */     String element = this.themesList.getModel().getElementAt(this.themesList.getSelectedIndex()).toString();
/* 117 */     String path = GUI.getJarPath() + File.separator + "theme/";
/*     */ 
/*     */     
/* 120 */     LOG.debug(path + element + "/images/Preview.png");
/*     */     
/* 122 */     this.themePreview = new ImageIcon(path + element + "/images/Preview.png");
/* 123 */     this.themePreviewButton.setIcon(this.themePreview);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent evt) {
/* 131 */     if (evt.getSource() == this.okButton) {
/*     */       
/* 133 */       Properties prp = GUI.getConfigFile();
/* 134 */       int element = this.themesList.getSelectedIndex();
/* 135 */       String name = this.themesList.getModel().getElementAt(element).toString();
/* 136 */       if (GUI.themeIsValid(name)) {
/*     */         
/* 138 */         prp.setProperty("THEME", name);
/*     */         
/*     */         try {
/* 141 */           FileOutputStream fOutStr = new FileOutputStream("config.txt");
/* 142 */           prp.store(fOutStr, null);
/* 143 */           fOutStr.flush();
/* 144 */           fOutStr.close();
/*     */         }
/* 146 */         catch (IOException exc) {
/*     */           
/* 148 */           LOG.error("actionPerformed/IOException: ", exc);
/*     */         } 
/* 150 */         JOptionPane.showMessageDialog(this, Settings.lang("changes_visible_after_restart"));
/* 151 */         setVisible(false);
/*     */       } 
/*     */       
/* 154 */       LOG.debug("property theme: " + prp.getProperty("THEME"));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\display\windows\ThemeChooseWindow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */