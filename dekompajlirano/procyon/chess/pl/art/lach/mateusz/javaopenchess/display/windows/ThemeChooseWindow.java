// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.display.windows;

import javax.swing.JOptionPane;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionEvent;
import java.util.Properties;
import javax.swing.Icon;
import pl.art.lach.mateusz.javaopenchess.JChessApp;
import java.awt.Component;
import java.awt.Point;
import java.awt.LayoutManager;
import java.awt.Dimension;
import pl.art.lach.mateusz.javaopenchess.utils.Settings;
import java.io.File;
import pl.art.lach.mateusz.javaopenchess.utils.GUI;
import java.awt.Frame;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JList;
import org.apache.log4j.Logger;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

public class ThemeChooseWindow extends JDialog implements ActionListener, ListSelectionListener
{
    private static final Logger LOG;
    JList themesList;
    ImageIcon themePreview;
    GridBagLayout gbl;
    public String result;
    GridBagConstraints gbc;
    JButton themePreviewButton;
    JButton okButton;
    
    public ThemeChooseWindow(final Frame parent) throws Exception {
        super(parent);
        final File dir = new File(GUI.getJarPath() + File.separator + "theme" + File.separator);
        ThemeChooseWindow.LOG.debug("Theme path: " + dir.getPath());
        final File[] files = dir.listFiles();
        if (files != null && dir.exists()) {
            this.setTitle(Settings.lang("choose_theme_window_title"));
            final Dimension winDim = new Dimension(550, 230);
            this.setMinimumSize(winDim);
            this.setMaximumSize(winDim);
            this.setSize(winDim);
            this.setResizable(false);
            this.setLayout(null);
            this.setDefaultCloseOperation(2);
            final String[] dirNames = new String[files.length];
            for (int i = 0; i < files.length; ++i) {
                dirNames[i] = files[i].getName();
            }
            (this.themesList = new JList((E[])dirNames)).setLocation(new Point(10, 10));
            this.themesList.setSize(new Dimension(100, 120));
            this.add(this.themesList);
            this.themesList.setSelectionMode(0);
            this.themesList.addListSelectionListener(this);
            final Properties prp = GUI.getConfigFile();
            this.gbl = new GridBagLayout();
            this.gbc = new GridBagConstraints();
            try {
                this.themePreview = new ImageIcon(GUI.loadImage("Preview.png"));
            }
            catch (NullPointerException exc) {
                ThemeChooseWindow.LOG.error("NullPointerException: Cannot find preview image: ", exc);
                this.themePreview = new ImageIcon(JChessApp.class.getResource("theme/noPreview.png"));
                return;
            }
            this.result = "";
            (this.themePreviewButton = new JButton(this.themePreview)).setLocation(new Point(110, 10));
            this.themePreviewButton.setSize(new Dimension(420, 120));
            this.add(this.themePreviewButton);
            (this.okButton = new JButton("OK")).setLocation(new Point(175, 140));
            this.okButton.setSize(new Dimension(200, 50));
            this.add(this.okButton);
            this.okButton.addActionListener(this);
            this.setModal(true);
            return;
        }
        throw new Exception(Settings.lang("error_when_creating_theme_config_window"));
    }
    
    @Override
    public void valueChanged(final ListSelectionEvent event) {
        final String element = this.themesList.getModel().getElementAt(this.themesList.getSelectedIndex()).toString();
        final String path = GUI.getJarPath() + File.separator + "theme/";
        ThemeChooseWindow.LOG.debug(path + element + "/images/Preview.png");
        this.themePreview = new ImageIcon(path + element + "/images/Preview.png");
        this.themePreviewButton.setIcon(this.themePreview);
    }
    
    @Override
    public void actionPerformed(final ActionEvent evt) {
        if (evt.getSource() == this.okButton) {
            final Properties prp = GUI.getConfigFile();
            final int element = this.themesList.getSelectedIndex();
            final String name = this.themesList.getModel().getElementAt(element).toString();
            if (GUI.themeIsValid(name)) {
                prp.setProperty("THEME", name);
                try {
                    final FileOutputStream fOutStr = new FileOutputStream("config.txt");
                    prp.store(fOutStr, null);
                    fOutStr.flush();
                    fOutStr.close();
                }
                catch (IOException exc) {
                    ThemeChooseWindow.LOG.error("actionPerformed/IOException: ", exc);
                }
                JOptionPane.showMessageDialog(this, Settings.lang("changes_visible_after_restart"));
                this.setVisible(false);
            }
            ThemeChooseWindow.LOG.debug("property theme: " + prp.getProperty("THEME"));
        }
    }
    
    static {
        LOG = Logger.getLogger(ThemeChooseWindow.class);
    }
}
