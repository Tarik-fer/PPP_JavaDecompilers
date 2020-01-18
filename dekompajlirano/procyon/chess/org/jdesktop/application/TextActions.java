// 
// Decompiled by Procyon v0.5.36
// 

package org.jdesktop.application;

import java.beans.PropertyChangeEvent;
import javax.swing.event.CaretEvent;
import java.awt.datatransfer.FlavorEvent;
import java.awt.AWTEvent;
import java.awt.event.InputEvent;
import java.awt.EventQueue;
import javax.swing.ActionMap;
import javax.swing.text.Caret;
import java.awt.datatransfer.DataFlavor;
import javax.swing.text.JTextComponent;
import java.awt.datatransfer.Clipboard;
import javax.swing.JComponent;
import java.awt.datatransfer.FlavorListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.beans.PropertyChangeListener;
import javax.swing.event.CaretListener;

class TextActions extends AbstractBean
{
    private final ApplicationContext context;
    private final CaretListener textComponentCaretListener;
    private final PropertyChangeListener textComponentPCL;
    private final String markerActionKey = "TextActions.markerAction";
    private final Action markerAction;
    private boolean copyEnabled;
    private boolean cutEnabled;
    private boolean pasteEnabled;
    private boolean deleteEnabled;
    
    public TextActions(final ApplicationContext context) {
        this.copyEnabled = false;
        this.cutEnabled = false;
        this.pasteEnabled = false;
        this.deleteEnabled = false;
        this.context = context;
        this.markerAction = new AbstractAction() {
            public void actionPerformed(final ActionEvent actionEvent) {
            }
        };
        this.textComponentCaretListener = new TextComponentCaretListener();
        this.textComponentPCL = new TextComponentPCL();
        this.getClipboard().addFlavorListener(new ClipboardListener());
    }
    
    private ApplicationContext getContext() {
        return this.context;
    }
    
    private JComponent getFocusOwner() {
        return this.getContext().getFocusOwner();
    }
    
    private Clipboard getClipboard() {
        return this.getContext().getClipboard();
    }
    
    void updateFocusOwner(final JComponent component, final JComponent component2) {
        if (component instanceof JTextComponent) {
            final JTextComponent textComponent = (JTextComponent)component;
            textComponent.removeCaretListener(this.textComponentCaretListener);
            textComponent.removePropertyChangeListener(this.textComponentPCL);
        }
        if (component2 instanceof JTextComponent) {
            final JTextComponent textComponent2 = (JTextComponent)component2;
            this.maybeInstallTextActions(textComponent2);
            this.updateTextActions(textComponent2);
            textComponent2.addCaretListener(this.textComponentCaretListener);
            textComponent2.addPropertyChangeListener(this.textComponentPCL);
        }
        else if (component2 == null) {
            this.setCopyEnabled(false);
            this.setCutEnabled(false);
            this.setPasteEnabled(false);
            this.setDeleteEnabled(false);
        }
    }
    
    private void updateTextActions(final JTextComponent textComponent) {
        final Caret caret = textComponent.getCaret();
        final boolean copyEnabled = caret.getDot() != caret.getMark();
        final boolean editable = textComponent.isEditable();
        final boolean dataFlavorAvailable = this.getClipboard().isDataFlavorAvailable(DataFlavor.stringFlavor);
        this.setCopyEnabled(copyEnabled);
        this.setCutEnabled(editable && copyEnabled);
        this.setDeleteEnabled(editable && copyEnabled);
        this.setPasteEnabled(editable && dataFlavorAvailable);
    }
    
    private void maybeInstallTextActions(final JTextComponent textComponent) {
        final ActionMap actionMap = textComponent.getActionMap();
        if (actionMap.get("TextActions.markerAction") == null) {
            actionMap.put("TextActions.markerAction", this.markerAction);
            final ApplicationActionMap actionMap2 = this.getContext().getActionMap(this.getClass(), this);
            for (final Object o : actionMap2.keys()) {
                actionMap.put(o, actionMap2.get(o));
            }
        }
    }
    
    private int getCurrentEventModifiers() {
        int n = 0;
        final AWTEvent currentEvent = EventQueue.getCurrentEvent();
        if (currentEvent instanceof InputEvent) {
            n = ((InputEvent)currentEvent).getModifiers();
        }
        else if (currentEvent instanceof ActionEvent) {
            n = ((ActionEvent)currentEvent).getModifiers();
        }
        return n;
    }
    
    private void invokeTextAction(final JTextComponent textComponent, final String s) {
        textComponent.getActionMap().getParent().get(s).actionPerformed(new ActionEvent(textComponent, 1001, s, EventQueue.getMostRecentEventTime(), this.getCurrentEventModifiers()));
    }
    
    @org.jdesktop.application.Action(enabledProperty = "cutEnabled")
    public void cut(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source instanceof JTextComponent) {
            this.invokeTextAction((JTextComponent)source, "cut");
        }
    }
    
    public boolean isCutEnabled() {
        return this.cutEnabled;
    }
    
    public void setCutEnabled(final boolean cutEnabled) {
        final boolean cutEnabled2 = this.cutEnabled;
        this.cutEnabled = cutEnabled;
        this.firePropertyChange("cutEnabled", cutEnabled2, this.cutEnabled);
    }
    
    @org.jdesktop.application.Action(enabledProperty = "copyEnabled")
    public void copy(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source instanceof JTextComponent) {
            this.invokeTextAction((JTextComponent)source, "copy");
        }
    }
    
    public boolean isCopyEnabled() {
        return this.copyEnabled;
    }
    
    public void setCopyEnabled(final boolean copyEnabled) {
        final boolean copyEnabled2 = this.copyEnabled;
        this.copyEnabled = copyEnabled;
        this.firePropertyChange("copyEnabled", copyEnabled2, this.copyEnabled);
    }
    
    @org.jdesktop.application.Action(enabledProperty = "pasteEnabled")
    public void paste(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source instanceof JTextComponent) {
            this.invokeTextAction((JTextComponent)source, "paste");
        }
    }
    
    public boolean isPasteEnabled() {
        return this.pasteEnabled;
    }
    
    public void setPasteEnabled(final boolean pasteEnabled) {
        final boolean pasteEnabled2 = this.pasteEnabled;
        this.pasteEnabled = pasteEnabled;
        this.firePropertyChange("pasteEnabled", pasteEnabled2, this.pasteEnabled);
    }
    
    @org.jdesktop.application.Action(enabledProperty = "deleteEnabled")
    public void delete(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source instanceof JTextComponent) {
            this.invokeTextAction((JTextComponent)source, "delete-next");
        }
    }
    
    public boolean isDeleteEnabled() {
        return this.deleteEnabled;
    }
    
    public void setDeleteEnabled(final boolean deleteEnabled) {
        final boolean deleteEnabled2 = this.deleteEnabled;
        this.deleteEnabled = deleteEnabled;
        this.firePropertyChange("deleteEnabled", deleteEnabled2, this.deleteEnabled);
    }
    
    private final class ClipboardListener implements FlavorListener
    {
        public void flavorsChanged(final FlavorEvent flavorEvent) {
            final JComponent access$300 = TextActions.this.getFocusOwner();
            if (access$300 instanceof JTextComponent) {
                TextActions.this.updateTextActions((JTextComponent)access$300);
            }
        }
    }
    
    private final class TextComponentCaretListener implements CaretListener
    {
        public void caretUpdate(final CaretEvent caretEvent) {
            TextActions.this.updateTextActions((JTextComponent)caretEvent.getSource());
        }
    }
    
    private final class TextComponentPCL implements PropertyChangeListener
    {
        public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
            final String propertyName = propertyChangeEvent.getPropertyName();
            if (propertyName == null || "editable".equals(propertyName)) {
                TextActions.this.updateTextActions((JTextComponent)propertyChangeEvent.getSource());
            }
        }
    }
}
