// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.display.windows;

import pl.art.lach.mateusz.javaopenchess.core.GameClock;
import javax.swing.event.ChangeEvent;
import java.awt.Graphics;
import pl.art.lach.mateusz.javaopenchess.core.Game;
import pl.art.lach.mateusz.javaopenchess.JChessView;
import javax.swing.JDialog;
import pl.art.lach.mateusz.javaopenchess.JChessApp;
import java.awt.event.MouseEvent;
import java.awt.Component;
import pl.art.lach.mateusz.javaopenchess.utils.GUI;
import javax.swing.Icon;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Image;
import org.apache.log4j.Logger;
import javax.swing.event.ChangeListener;
import java.awt.image.ImageObserver;
import java.awt.event.MouseListener;
import javax.swing.JTabbedPane;

public class JChessTabbedPane extends JTabbedPane implements MouseListener, ImageObserver, ChangeListener
{
    private static final Logger LOG;
    private TabbedPaneIcon closeIcon;
    private Image addIcon;
    private Image unclickedAddIcon;
    private Rectangle addIconRect;
    public static final Color DEFAULT_COLOR;
    public static final Color EVENT_COLOR;
    
    public JChessTabbedPane() {
        this.addIcon = null;
        this.unclickedAddIcon = null;
        this.addIconRect = null;
        this.closeIcon = new TabbedPaneIcon(this.closeIcon);
        this.unclickedAddIcon = GUI.loadImage("add-tab-icon.png");
        this.addIcon = this.unclickedAddIcon;
        this.setDoubleBuffered(true);
        this.initListeners();
    }
    
    protected final void initListeners() {
        this.addChangeListener(this);
        this.addMouseListener(this);
    }
    
    @Override
    public void addTab(final String title, final Component component) {
        this.addTab(title, component, null);
    }
    
    public void addTab(final String title, final Component component, final Icon closeIcon) {
        super.addTab(title, new TabbedPaneIcon(closeIcon), component);
        JChessTabbedPane.LOG.debug("Present number of tabs: " + this.getTabCount());
        this.updateAddIconRect();
    }
    
    @Override
    public void mouseReleased(final MouseEvent e) {
    }
    
    @Override
    public void mousePressed(final MouseEvent e) {
    }
    
    private void showNewGameWindow() {
        final JChessView jcv = JChessApp.getJavaChessView();
        if (JChessApp.getJavaChessView().getNewGameFrame() == null) {
            jcv.setNewGameFrame(new NewGameWindow());
        }
        JChessApp.getApplication().show(JChessApp.getJavaChessView().getNewGameFrame());
    }
    
    @Override
    public void mouseClicked(final MouseEvent e) {
        final int tabNumber = this.getUI().tabForCoordinate(this, e.getX(), e.getY());
        if (tabNumber >= 0) {
            final Rectangle rect = ((TabbedPaneIcon)this.getIconAt(tabNumber)).getBounds();
            if (rect.contains(e.getX(), e.getY())) {
                JChessTabbedPane.LOG.debug("Removing tab with " + tabNumber + " number!...");
                this.removeTabAt(tabNumber);
                this.updateAddIconRect();
                if (this.getTabCount() == 0) {
                    this.showNewGameWindow();
                }
            }
            if (0 < this.getTabCount() && null != this.getTabComponentAt(this.getSelectedIndex())) {
                this.getTabComponentAt(this.getSelectedIndex()).repaint();
            }
            else if (0 < this.getTabCount() && null != this.getComponent(this.getSelectedIndex())) {
                final Component activeTab = this.getComponent(this.getSelectedIndex());
                this.setForegroundAt(this.getSelectedIndex(), JChessTabbedPane.DEFAULT_COLOR);
                activeTab.repaint();
            }
        }
        else if (this.addIconRect != null && this.addIconRect.contains(e.getX(), e.getY())) {
            JChessTabbedPane.LOG.debug("newGame by + button");
            this.showNewGameWindow();
        }
    }
    
    public void highlightTab(final Game game) {
        final int tabNumber = JChessApp.getJavaChessView().getTabNumber(game);
        this.highlightTab(tabNumber);
    }
    
    public void highlightTab(final int number) {
        if (number < this.getTabCount()) {
            this.setForegroundAt(number, JChessTabbedPane.EVENT_COLOR);
        }
    }
    
    @Override
    public void mouseEntered(final MouseEvent e) {
    }
    
    @Override
    public void mouseExited(final MouseEvent e) {
    }
    
    private void updateAddIconRect() {
        if (this.getTabCount() > 0) {
            final Rectangle rect = this.getBoundsAt(this.getTabCount() - 1);
            this.addIconRect = new Rectangle(rect.x + rect.width + 5, rect.y, this.addIcon.getWidth(this), this.addIcon.getHeight(this));
        }
        else {
            this.addIconRect = null;
        }
    }
    
    private Rectangle getAddIconRect() {
        return this.addIconRect;
    }
    
    @Override
    public boolean imageUpdate(final Image img, final int infoflags, final int x, final int y, final int width, final int height) {
        super.imageUpdate(img, infoflags, x, y, width, height);
        this.updateAddIconRect();
        return true;
    }
    
    @Override
    public void paint(final Graphics g) {
        super.paint(g);
        final Rectangle rect = this.getAddIconRect();
        if (rect != null) {
            g.drawImage(this.addIcon, rect.x, rect.y, null);
        }
    }
    
    @Override
    public void update(final Graphics g) {
        this.repaint();
    }
    
    @Override
    public void stateChanged(final ChangeEvent changeEvent) {
        if (1 != this.getTabCount()) {
            final JTabbedPane sourceTabbedPane = (JTabbedPane)changeEvent.getSource();
            final Game game = (Game)sourceTabbedPane.getSelectedComponent();
            if (null != game) {
                game.resizeGame();
            }
        }
    }
    
    static {
        LOG = Logger.getLogger(GameClock.class);
        DEFAULT_COLOR = Color.BLACK;
        EVENT_COLOR = Color.RED;
    }
}
