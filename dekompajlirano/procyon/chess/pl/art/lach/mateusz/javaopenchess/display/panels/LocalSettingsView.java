// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.display.panels;

import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Insets;
import pl.art.lach.mateusz.javaopenchess.utils.Settings;
import java.awt.LayoutManager;
import pl.art.lach.mateusz.javaopenchess.core.Game;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class LocalSettingsView extends JPanel implements ActionListener
{
    private JCheckBox isUpsideDown;
    private JCheckBox isDisplayLegalMovesEnabled;
    private JCheckBox isRenderLabelsEnabled;
    private GridBagConstraints gbc;
    private GridBagLayout gbl;
    private Game game;
    
    public LocalSettingsView(final Game game) {
        this.game = game;
        this.gbc = new GridBagConstraints();
        this.setLayout(this.gbl = new GridBagLayout());
        this.initUpsideDownControl();
        this.initDisplayLegalMovesControl();
        this.initRenderLabelsControl();
        this.refreshCheckBoxesState();
    }
    
    private void initUpsideDownControl() {
        (this.isUpsideDown = new JCheckBox()).setText(Settings.lang("upside_down"));
        this.isUpsideDown.setSize(this.isUpsideDown.getHeight(), this.getWidth());
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        this.gbc.insets = new Insets(3, 3, 3, 3);
        this.gbl.setConstraints(this.isUpsideDown, this.gbc);
        this.add(this.isUpsideDown);
        this.isUpsideDown.addActionListener(this);
    }
    
    private void initDisplayLegalMovesControl() {
        (this.isDisplayLegalMovesEnabled = new JCheckBox()).setText(Settings.lang("display_legal_moves"));
        this.gbc.gridx = 0;
        this.gbc.gridy = 1;
        this.gbl.setConstraints(this.isDisplayLegalMovesEnabled, this.gbc);
        this.add(this.isDisplayLegalMovesEnabled);
        this.isDisplayLegalMovesEnabled.addActionListener(this);
    }
    
    private void initRenderLabelsControl() {
        (this.isRenderLabelsEnabled = new JCheckBox()).setText(Settings.lang("display_labels"));
        this.gbc.gridx = 0;
        this.gbc.gridy = 2;
        this.gbl.setConstraints(this.isRenderLabelsEnabled, this.gbc);
        this.add(this.isRenderLabelsEnabled);
        this.isRenderLabelsEnabled.addActionListener(this);
    }
    
    private void refreshCheckBoxesState() {
        if (this.isInitiatedCorrectly()) {
            this.isUpsideDown.setSelected(this.game.getSettings().isUpsideDown());
            this.isDisplayLegalMovesEnabled.setSelected(this.game.getSettings().isDisplayLegalMovesEnabled());
            this.isRenderLabelsEnabled.setSelected(this.game.getSettings().isRenderLabels());
        }
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        final JCheckBox clickedComponent = (JCheckBox)e.getSource();
        if (clickedComponent == this.isUpsideDown) {
            this.game.getSettings().setUpsideDown(this.isUpsideDown.isSelected());
        }
        else if (clickedComponent == this.isDisplayLegalMovesEnabled) {
            this.game.getSettings().setDisplayLegalMovesEnabled(this.isDisplayLegalMovesEnabled.isSelected());
        }
        else if (clickedComponent == this.isRenderLabelsEnabled) {
            this.game.getSettings().setRenderLabels(this.isRenderLabelsEnabled.isSelected());
            this.game.resizeGame();
        }
        this.game.repaint();
    }
    
    @Override
    public void repaint() {
        this.refreshCheckBoxesState();
        super.repaint();
    }
    
    private boolean isInitiatedCorrectly() {
        return null != this.isUpsideDown && null != this.isDisplayLegalMovesEnabled && null != this.isRenderLabelsEnabled;
    }
}
