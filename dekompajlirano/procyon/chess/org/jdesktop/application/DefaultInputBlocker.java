// 
// Decompiled by Procyon v0.5.36
// 

package org.jdesktop.application;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.event.MouseInputAdapter;
import java.awt.Cursor;
import javax.swing.Timer;
import javax.swing.JMenuBar;
import javax.swing.JComponent;
import javax.swing.InputVerifier;
import javax.swing.RootPaneContainer;
import java.util.concurrent.TimeUnit;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JProgressBar;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Insets;
import javax.swing.JTextArea;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.util.Iterator;
import java.util.ArrayList;
import java.awt.Container;
import java.util.List;
import java.awt.Component;
import javax.swing.Action;
import javax.swing.JDialog;
import java.util.logging.Logger;

final class DefaultInputBlocker extends Task.InputBlocker
{
    private static final Logger logger;
    private JDialog modalDialog;
    
    DefaultInputBlocker(final Task task, final Task.BlockingScope blockingScope, final Object o, final ApplicationAction applicationAction) {
        super(task, blockingScope, o, applicationAction);
        this.modalDialog = null;
    }
    
    private void setActionTargetBlocked(final boolean b) {
        ((Action)this.getTarget()).setEnabled(!b);
    }
    
    private void setComponentTargetBlocked(final boolean b) {
        ((Component)this.getTarget()).setEnabled(!b);
    }
    
    private void blockingDialogComponents(final Component component, final List<Component> list) {
        final String name = component.getName();
        if (name != null && name.startsWith("BlockingDialog")) {
            list.add(component);
        }
        if (component instanceof Container) {
            final Component[] components = ((Container)component).getComponents();
            for (int length = components.length, i = 0; i < length; ++i) {
                this.blockingDialogComponents(components[i], list);
            }
        }
    }
    
    private List<Component> blockingDialogComponents(final Component component) {
        final ArrayList<Component> list = new ArrayList<Component>();
        this.blockingDialogComponents(component, list);
        return list;
    }
    
    private void injectBlockingDialogComponents(final Component component) {
        final ResourceMap resourceMap = this.getTask().getResourceMap();
        if (resourceMap != null) {
            resourceMap.injectComponents(component);
        }
        final ApplicationAction action = this.getAction();
        if (action != null) {
            final ResourceMap resourceMap2 = action.getResourceMap();
            final String name = action.getName();
            for (final Component component2 : this.blockingDialogComponents(component)) {
                component2.setName(name + "." + component2.getName());
            }
            resourceMap2.injectComponents(component);
        }
    }
    
    private JDialog createBlockingDialog() {
        final JOptionPane optionPane = new JOptionPane();
        if (this.getTask().getUserCanCancel()) {
            final JButton button = new JButton();
            button.setName("BlockingDialog.cancelButton");
            button.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent actionEvent) {
                    DefaultInputBlocker.this.getTask().cancel(true);
                }
            });
            optionPane.setOptions(new Object[] { button });
        }
        else {
            optionPane.setOptions(new Object[0]);
        }
        final Component component = (Component)this.getTarget();
        final String title = this.getTask().getTitle();
        final JDialog dialog = optionPane.createDialog(component, (title == null) ? "BlockingDialog" : title);
        dialog.setModal(true);
        dialog.setName("BlockingDialog");
        dialog.setDefaultCloseOperation(0);
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent windowEvent) {
                if (DefaultInputBlocker.this.getTask().getUserCanCancel()) {
                    DefaultInputBlocker.this.getTask().cancel(true);
                    dialog.setVisible(false);
                }
            }
        });
        optionPane.setName("BlockingDialog.optionPane");
        this.injectBlockingDialogComponents(dialog);
        this.recreateOptionPaneMessage(optionPane);
        dialog.pack();
        return dialog;
    }
    
    private void recreateOptionPaneMessage(final JOptionPane optionPane) {
        final Object message = optionPane.getMessage();
        if (message instanceof String) {
            final Font font = optionPane.getFont();
            final JTextArea textArea = new JTextArea((String)message);
            textArea.setFont(font);
            textArea.setMargin(new Insets(0, 0, textArea.getFontMetrics(font).getHeight(), 24));
            textArea.setEditable(false);
            textArea.setWrapStyleWord(true);
            textArea.setBackground(optionPane.getBackground());
            final JPanel message2 = new JPanel(new BorderLayout());
            message2.add(textArea, "Center");
            final JProgressBar progressBar = new JProgressBar();
            progressBar.setName("BlockingDialog.progressBar");
            progressBar.setIndeterminate(true);
            this.getTask().addPropertyChangeListener(new PropertyChangeListener() {
                public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
                    if ("progress".equals(propertyChangeEvent.getPropertyName())) {
                        progressBar.setIndeterminate(false);
                        progressBar.setValue((int)propertyChangeEvent.getNewValue());
                        DefaultInputBlocker.this.updateStatusBarString(progressBar);
                    }
                    else if ("message".equals(propertyChangeEvent.getPropertyName())) {
                        textArea.setText((String)propertyChangeEvent.getNewValue());
                    }
                }
            });
            message2.add(progressBar, "South");
            this.injectBlockingDialogComponents(message2);
            optionPane.setMessage(message2);
        }
    }
    
    private void updateStatusBarString(final JProgressBar progressBar) {
        if (!progressBar.isStringPainted()) {
            return;
        }
        final String s = "progressBarStringFormat";
        if (progressBar.getClientProperty(s) == null) {
            progressBar.putClientProperty(s, progressBar.getString());
        }
        final String s2 = (String)progressBar.getClientProperty(s);
        if (progressBar.getValue() <= 0) {
            progressBar.setString("");
        }
        else if (s2 == null) {
            progressBar.setString(null);
        }
        else {
            final double n = progressBar.getValue() / 100.0;
            final long executionDuration = this.getTask().getExecutionDuration(TimeUnit.SECONDS);
            final long n2 = executionDuration / 60L;
            final long n3 = (long)(0.5 + executionDuration / n) - executionDuration;
            final long n4 = n3 / 60L;
            progressBar.setString(String.format(s2, n2, executionDuration - n2 * 60L, n4, n3 - n4 * 60L));
        }
    }
    
    private void showBusyGlassPane(final boolean b) {
        RootPaneContainer rootPaneContainer = null;
        for (Component parent = (Component)this.getTarget(); parent != null; parent = parent.getParent()) {
            if (parent instanceof RootPaneContainer) {
                rootPaneContainer = (RootPaneContainer)parent;
                break;
            }
        }
        if (rootPaneContainer != null) {
            if (b) {
                final JMenuBar jMenuBar = rootPaneContainer.getRootPane().getJMenuBar();
                if (jMenuBar != null) {
                    jMenuBar.putClientProperty(this, jMenuBar.isEnabled());
                    jMenuBar.setEnabled(false);
                }
                final BusyGlassPane glassPane = new BusyGlassPane();
                glassPane.setInputVerifier(new InputVerifier() {
                    @Override
                    public boolean verify(final JComponent component) {
                        return !component.isVisible();
                    }
                });
                rootPaneContainer.getRootPane().putClientProperty(this, rootPaneContainer.getGlassPane());
                rootPaneContainer.setGlassPane(glassPane);
                glassPane.setVisible(true);
                glassPane.revalidate();
            }
            else {
                final JMenuBar jMenuBar2 = rootPaneContainer.getRootPane().getJMenuBar();
                if (jMenuBar2 != null) {
                    final boolean booleanValue = (boolean)jMenuBar2.getClientProperty(this);
                    jMenuBar2.putClientProperty(this, null);
                    jMenuBar2.setEnabled(booleanValue);
                }
                final Component glassPane2 = (Component)rootPaneContainer.getRootPane().getClientProperty(this);
                rootPaneContainer.getRootPane().putClientProperty(this, null);
                if (!glassPane2.isVisible()) {
                    rootPaneContainer.getGlassPane().setVisible(false);
                }
                rootPaneContainer.setGlassPane(glassPane2);
            }
        }
    }
    
    private int blockingDialogDelay() {
        Integer n = null;
        final String s = "BlockingDialogTimer.delay";
        final ApplicationAction action = this.getAction();
        if (action != null) {
            n = action.getResourceMap().getInteger(action.getName() + "." + s);
        }
        final ResourceMap resourceMap = this.getTask().getResourceMap();
        if (n == null && resourceMap != null) {
            n = resourceMap.getInteger(s);
        }
        return (n == null) ? 0 : n;
    }
    
    private void showBlockingDialog(final boolean b) {
        if (b) {
            if (this.modalDialog != null) {
                DefaultInputBlocker.logger.warning(String.format("unexpected InputBlocker state [%s] %s", b, this));
                this.modalDialog.dispose();
            }
            this.modalDialog = this.createBlockingDialog();
            final Timer timer = new Timer(this.blockingDialogDelay(), new ActionListener() {
                public void actionPerformed(final ActionEvent actionEvent) {
                    if (DefaultInputBlocker.this.modalDialog != null) {
                        DefaultInputBlocker.this.modalDialog.setVisible(true);
                    }
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
        else if (this.modalDialog != null) {
            this.modalDialog.dispose();
            this.modalDialog = null;
        }
        else {
            DefaultInputBlocker.logger.warning(String.format("unexpected InputBlocker state [%s] %s", b, this));
        }
    }
    
    @Override
    protected void block() {
        switch (this.getScope()) {
            case ACTION: {
                this.setActionTargetBlocked(true);
                break;
            }
            case COMPONENT: {
                this.setComponentTargetBlocked(true);
                break;
            }
            case WINDOW:
            case APPLICATION: {
                this.showBusyGlassPane(true);
                this.showBlockingDialog(true);
                break;
            }
        }
    }
    
    @Override
    protected void unblock() {
        switch (this.getScope()) {
            case ACTION: {
                this.setActionTargetBlocked(false);
                break;
            }
            case COMPONENT: {
                this.setComponentTargetBlocked(false);
                break;
            }
            case WINDOW:
            case APPLICATION: {
                this.showBusyGlassPane(false);
                this.showBlockingDialog(false);
                break;
            }
        }
    }
    
    static {
        logger = Logger.getLogger(DefaultInputBlocker.class.getName());
    }
    
    private static class BusyGlassPane extends JPanel
    {
        BusyGlassPane() {
            super(null, false);
            this.setVisible(false);
            this.setOpaque(false);
            this.setCursor(Cursor.getPredefinedCursor(3));
            final MouseInputAdapter mouseInputAdapter = new MouseInputAdapter() {};
            this.addMouseMotionListener(mouseInputAdapter);
            this.addMouseListener(mouseInputAdapter);
        }
    }
}
