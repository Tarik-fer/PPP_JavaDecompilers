/*     */ package pl.art.lach.mateusz.javaopenchess.display.windows;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Image;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.image.ImageObserver;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JTabbedPane;
/*     */ import javax.swing.event.ChangeEvent;
/*     */ import javax.swing.event.ChangeListener;
/*     */ import org.apache.log4j.Logger;
/*     */ import pl.art.lach.mateusz.javaopenchess.JChessApp;
/*     */ import pl.art.lach.mateusz.javaopenchess.JChessView;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.Game;
/*     */ import pl.art.lach.mateusz.javaopenchess.core.GameClock;
/*     */ import pl.art.lach.mateusz.javaopenchess.utils.GUI;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JChessTabbedPane
/*     */   extends JTabbedPane
/*     */   implements MouseListener, ImageObserver, ChangeListener
/*     */ {
/*  37 */   private static final Logger LOG = Logger.getLogger(GameClock.class);
/*     */   
/*     */   private TabbedPaneIcon closeIcon;
/*     */   
/*  41 */   private Image addIcon = null;
/*     */   
/*  43 */   private Image unclickedAddIcon = null;
/*     */   
/*  45 */   private Rectangle addIconRect = null;
/*     */   
/*  47 */   public static final Color DEFAULT_COLOR = Color.BLACK;
/*     */   
/*  49 */   public static final Color EVENT_COLOR = Color.RED;
/*     */ 
/*     */ 
/*     */   
/*     */   public JChessTabbedPane() {
/*  54 */     this.closeIcon = new TabbedPaneIcon(this.closeIcon);
/*  55 */     this.unclickedAddIcon = GUI.loadImage("add-tab-icon.png");
/*  56 */     this.addIcon = this.unclickedAddIcon;
/*  57 */     setDoubleBuffered(true);
/*  58 */     initListeners();
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void initListeners() {
/*  63 */     addChangeListener(this);
/*  64 */     addMouseListener(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  70 */   public void addTab(String title, Component component) { addTab(title, component, null); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addTab(String title, Component component, Icon closeIcon) {
/*  75 */     addTab(title, new TabbedPaneIcon(closeIcon), component);
/*  76 */     LOG.debug("Present number of tabs: " + getTabCount());
/*  77 */     updateAddIconRect();
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseReleased(MouseEvent e) {}
/*     */ 
/*     */   
/*     */   public void mousePressed(MouseEvent e) {}
/*     */ 
/*     */   
/*     */   private void showNewGameWindow() {
/*  88 */     JChessView jcv = JChessApp.getJavaChessView();
/*  89 */     if (JChessApp.getJavaChessView().getNewGameFrame() == null)
/*     */     {
/*  91 */       jcv.setNewGameFrame(new NewGameWindow());
/*     */     }
/*  93 */     JChessApp.getApplication().show(JChessApp.getJavaChessView().getNewGameFrame());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseClicked(MouseEvent e) {
/* 100 */     int tabNumber = getUI().tabForCoordinate(this, e.getX(), e.getY());
/* 101 */     if (tabNumber >= 0) {
/*     */       
/* 103 */       Rectangle rect = ((TabbedPaneIcon)getIconAt(tabNumber)).getBounds();
/* 104 */       if (rect.contains(e.getX(), e.getY())) {
/*     */         
/* 106 */         LOG.debug("Removing tab with " + tabNumber + " number!...");
/* 107 */         removeTabAt(tabNumber);
/* 108 */         updateAddIconRect();
/* 109 */         if (getTabCount() == 0)
/*     */         {
/* 111 */           showNewGameWindow();
/*     */         }
/*     */       } 
/* 114 */       if (0 < getTabCount() && null != getTabComponentAt(getSelectedIndex()))
/*     */       {
/* 116 */         getTabComponentAt(getSelectedIndex()).repaint();
/*     */       }
/* 118 */       else if (0 < getTabCount() && null != getComponent(getSelectedIndex()))
/*     */       {
/* 120 */         Component activeTab = getComponent(getSelectedIndex());
/* 121 */         setForegroundAt(getSelectedIndex(), DEFAULT_COLOR);
/* 122 */         activeTab.repaint();
/*     */       }
/*     */     
/* 125 */     } else if (this.addIconRect != null && this.addIconRect.contains(e.getX(), e.getY())) {
/*     */       
/* 127 */       LOG.debug("newGame by + button");
/* 128 */       showNewGameWindow();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void highlightTab(Game game) {
/* 134 */     int tabNumber = JChessApp.getJavaChessView().getTabNumber(game);
/* 135 */     highlightTab(tabNumber);
/*     */   }
/*     */ 
/*     */   
/*     */   public void highlightTab(int number) {
/* 140 */     if (number < getTabCount())
/*     */     {
/* 142 */       setForegroundAt(number, EVENT_COLOR);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseEntered(MouseEvent e) {}
/*     */ 
/*     */   
/*     */   public void mouseExited(MouseEvent e) {}
/*     */ 
/*     */   
/*     */   private void updateAddIconRect() {
/* 154 */     if (getTabCount() > 0) {
/*     */       
/* 156 */       Rectangle rect = getBoundsAt(getTabCount() - 1);
/* 157 */       this
/*     */ 
/*     */ 
/*     */         
/* 161 */         .addIconRect = new Rectangle(rect.x + rect.width + 5, rect.y, this.addIcon.getWidth(this), this.addIcon.getHeight(this));
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 166 */       this.addIconRect = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 172 */   private Rectangle getAddIconRect() { return this.addIconRect; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
/* 178 */     super.imageUpdate(img, infoflags, x, y, width, height);
/* 179 */     updateAddIconRect();
/* 180 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void paint(Graphics g) {
/* 186 */     super.paint(g);
/* 187 */     Rectangle rect = getAddIconRect();
/* 188 */     if (rect != null)
/*     */     {
/* 190 */       g.drawImage(this.addIcon, rect.x, rect.y, null);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 197 */   public void update(Graphics g) { repaint(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stateChanged(ChangeEvent changeEvent) {
/* 203 */     if (1 != getTabCount()) {
/*     */       
/* 205 */       JTabbedPane sourceTabbedPane = (JTabbedPane)changeEvent.getSource();
/* 206 */       Game game = (Game)sourceTabbedPane.getSelectedComponent();
/* 207 */       if (null != game)
/*     */       {
/* 209 */         game.resizeGame();
/*     */       }
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\display\windows\JChessTabbedPane.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */