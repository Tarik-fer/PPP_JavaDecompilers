// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.display.windows;

import java.awt.Insets;
import javax.swing.AbstractButton;
import java.awt.LayoutManager;
import java.awt.Dimension;
import pl.art.lach.mateusz.javaopenchess.core.Game;
import pl.art.lach.mateusz.javaopenchess.core.players.Player;
import pl.art.lach.mateusz.javaopenchess.core.ai.AIFactory;
import pl.art.lach.mateusz.javaopenchess.utils.GameTypes;
import pl.art.lach.mateusz.javaopenchess.utils.GameModes;
import pl.art.lach.mateusz.javaopenchess.JChessApp;
import pl.art.lach.mateusz.javaopenchess.core.players.PlayerFactory;
import pl.art.lach.mateusz.javaopenchess.core.Colors;
import pl.art.lach.mateusz.javaopenchess.core.players.PlayerType;
import java.awt.Component;
import javax.swing.JOptionPane;
import pl.art.lach.mateusz.javaopenchess.utils.Settings;
import java.awt.event.ActionEvent;
import javax.swing.text.BadLocationException;
import java.awt.event.TextEvent;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import org.apache.log4j.Logger;
import java.awt.event.TextListener;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class DrawLocalSettings extends JPanel implements ActionListener, TextListener
{
    private static final Logger LOG;
    JDialog parent;
    JComboBox color;
    JRadioButton oponentComp;
    JRadioButton oponentHuman;
    ButtonGroup oponentChoos;
    JFrame localPanel;
    JLabel compLevLab;
    JSlider computerLevel;
    JTextField firstName;
    JTextField secondName;
    JLabel firstNameLab;
    JLabel secondNameLab;
    JCheckBox upsideDown;
    GridBagLayout gbl;
    GridBagConstraints gbc;
    Container cont;
    JSeparator sep;
    JButton okButton;
    JCheckBox timeGame;
    JComboBox time4Game;
    String[] colors;
    String[] times;
    
    @Override
    public void textValueChanged(final TextEvent e) {
        final Object target = e.getSource();
        if (target == this.firstName || target == this.secondName) {
            JTextField temp = new JTextField();
            if (target == this.firstName) {
                temp = this.firstName;
            }
            else if (target == this.secondName) {
                temp = this.secondName;
            }
            final int len = temp.getText().length();
            if (len > 8) {
                try {
                    temp.setText(temp.getText(0, 7));
                }
                catch (BadLocationException exc) {
                    DrawLocalSettings.LOG.error("BadLocationException: Something wrong in editables, msg: " + exc.getMessage() + " object: ", exc);
                }
            }
        }
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        final Object target = e.getSource();
        if (target == this.oponentComp) {
            this.computerLevel.setEnabled(true);
            this.secondName.setEnabled(false);
        }
        else if (target == this.oponentHuman) {
            this.secondName.setEnabled(true);
        }
        else if (target == this.okButton) {
            if (this.firstName.getText().length() > 9) {
                this.firstName.setText(this.trimString(this.firstName, 9));
            }
            if (this.secondName.getText().length() > 9) {
                this.secondName.setText(this.trimString(this.secondName, 9));
            }
            if (!this.oponentComp.isSelected() && (this.firstName.getText().length() == 0 || this.secondName.getText().length() == 0)) {
                JOptionPane.showMessageDialog(this, Settings.lang("fill_names"));
                return;
            }
            if (this.oponentComp.isSelected() && this.firstName.getText().length() == 0) {
                JOptionPane.showMessageDialog(this, Settings.lang("fill_name"));
                return;
            }
            final String playerFirstName = this.firstName.getText();
            final String playerSecondName = this.secondName.getText();
            String whiteName;
            String blackName;
            PlayerType whiteType;
            PlayerType blackType;
            if (0 == this.color.getSelectedIndex()) {
                whiteName = playerFirstName;
                blackName = playerSecondName;
                whiteType = PlayerType.LOCAL_USER;
                blackType = (this.oponentComp.isSelected() ? PlayerType.COMPUTER : PlayerType.LOCAL_USER);
            }
            else {
                blackName = playerFirstName;
                whiteName = playerSecondName;
                blackType = PlayerType.LOCAL_USER;
                whiteType = (this.oponentComp.isSelected() ? PlayerType.COMPUTER : PlayerType.LOCAL_USER);
            }
            final Player playerWhite = PlayerFactory.getInstance(whiteName, Colors.WHITE, whiteType);
            final Player playerBlack = PlayerFactory.getInstance(blackName, Colors.BLACK, blackType);
            final Game newGUI = JChessApp.getJavaChessView().addNewTab(playerWhite.getName() + " vs " + playerBlack.getName());
            newGUI.getChat().setEnabled(false);
            final Settings sett = newGUI.getSettings();
            sett.setPlayerWhite(playerWhite);
            sett.setPlayerBlack(playerBlack);
            sett.setGameMode(GameModes.NEW_GAME);
            sett.setGameType(GameTypes.LOCAL);
            sett.setUpsideDown(this.upsideDown.isSelected());
            newGUI.setActivePlayer(playerWhite);
            if (this.timeGame.isSelected()) {
                final String value = this.times[this.time4Game.getSelectedIndex()];
                final Integer val = new Integer(value);
                sett.setTimeForGame(val * 60);
                newGUI.getGameClock().setTimes(sett.getTimeForGame(), sett.getTimeForGame());
                newGUI.getGameClock().start();
            }
            DrawLocalSettings.LOG.debug("this.time4Game.getActionCommand(): " + this.time4Game.getActionCommand());
            DrawLocalSettings.LOG.debug("****************\nStarting new game: " + playerWhite.getName() + " vs. " + playerBlack.getName() + "\ntime 4 game: " + sett.getTimeForGame() + "\ntime limit set: " + sett.isTimeLimitSet() + "\nwhite on top?: " + sett.isUpsideDown() + "\n****************");
            newGUI.newGame();
            this.parent.setVisible(false);
            JChessApp.getJavaChessView().getActiveTabGame().repaint();
            JChessApp.getJavaChessView().setActiveTabGame(JChessApp.getJavaChessView().getNumberOfOpenedTabs() - 1);
            if (this.oponentComp.isSelected()) {
                final Game activeGame = JChessApp.getJavaChessView().getActiveTabGame();
                activeGame.setAi(AIFactory.getAI(this.computerLevel.getValue()));
                if (activeGame.getSettings().isGameAgainstComputer() && activeGame.getSettings().getPlayerWhite().getPlayerType() == PlayerType.COMPUTER) {
                    activeGame.doComputerMove();
                }
            }
        }
    }
    
    public DrawLocalSettings(final JDialog parent) {
        this.colors = new String[] { Settings.lang("white"), Settings.lang("black") };
        this.times = new String[] { "1", "3", "5", "8", "10", "15", "20", "25", "30", "60", "120" };
        this.parent = parent;
        this.color = new JComboBox((E[])this.colors);
        this.gbl = new GridBagLayout();
        this.gbc = new GridBagConstraints();
        this.sep = new JSeparator();
        this.okButton = new JButton(Settings.lang("ok"));
        this.compLevLab = new JLabel(Settings.lang("computer_level"));
        (this.firstName = new JTextField("", 10)).setSize(new Dimension(200, 50));
        (this.secondName = new JTextField("", 10)).setSize(new Dimension(200, 50));
        this.firstNameLab = new JLabel(Settings.lang("first_player_name") + ": ");
        this.secondNameLab = new JLabel(Settings.lang("second_player_name") + ": ");
        this.oponentChoos = new ButtonGroup();
        this.computerLevel = new JSlider();
        this.upsideDown = new JCheckBox(Settings.lang("upside_down"));
        this.timeGame = new JCheckBox(Settings.lang("time_game_min"));
        this.time4Game = new JComboBox((E[])this.times);
        this.oponentComp = new JRadioButton(Settings.lang("against_computer"), false);
        this.oponentHuman = new JRadioButton(Settings.lang("against_other_human"), true);
        this.setLayout(this.gbl);
        this.oponentComp.addActionListener(this);
        this.oponentHuman.addActionListener(this);
        this.okButton.addActionListener(this);
        this.secondName.addActionListener(this);
        this.oponentChoos.add(this.oponentComp);
        this.oponentChoos.add(this.oponentHuman);
        this.computerLevel.setEnabled(false);
        this.computerLevel.setValue(1);
        this.computerLevel.setMaximum(2);
        this.computerLevel.setMinimum(1);
        this.computerLevel.setPaintTicks(true);
        this.computerLevel.setPaintLabels(true);
        this.computerLevel.setMajorTickSpacing(1);
        this.computerLevel.setMinorTickSpacing(1);
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        this.gbc.insets = new Insets(3, 3, 3, 3);
        this.gbl.setConstraints(this.oponentComp, this.gbc);
        this.add(this.oponentComp);
        this.gbc.gridx = 1;
        this.gbl.setConstraints(this.oponentHuman, this.gbc);
        this.add(this.oponentHuman);
        this.gbc.gridx = 0;
        this.gbc.gridy = 1;
        this.gbl.setConstraints(this.firstNameLab, this.gbc);
        this.add(this.firstNameLab);
        this.gbc.gridx = 0;
        this.gbc.gridy = 2;
        this.gbl.setConstraints(this.firstName, this.gbc);
        this.add(this.firstName);
        this.gbc.gridx = 1;
        this.gbc.gridy = 2;
        this.gbl.setConstraints(this.color, this.gbc);
        this.add(this.color);
        this.gbc.gridx = 0;
        this.gbc.gridy = 3;
        this.gbl.setConstraints(this.secondNameLab, this.gbc);
        this.add(this.secondNameLab);
        this.gbc.gridy = 4;
        this.gbl.setConstraints(this.secondName, this.gbc);
        this.add(this.secondName);
        this.gbc.gridy = 5;
        this.gbc.insets = new Insets(0, 0, 0, 0);
        this.gbl.setConstraints(this.compLevLab, this.gbc);
        this.add(this.compLevLab);
        this.gbc.gridy = 6;
        this.gbl.setConstraints(this.computerLevel, this.gbc);
        this.add(this.computerLevel);
        this.gbc.gridy = 7;
        this.gbl.setConstraints(this.upsideDown, this.gbc);
        this.add(this.upsideDown);
        this.gbc.gridy = 8;
        this.gbc.gridwidth = 1;
        this.gbl.setConstraints(this.timeGame, this.gbc);
        this.add(this.timeGame);
        this.gbc.gridx = 1;
        this.gbc.gridy = 8;
        this.gbc.gridwidth = 1;
        this.gbl.setConstraints(this.time4Game, this.gbc);
        this.add(this.time4Game);
        this.gbc.gridx = 1;
        this.gbc.gridy = 9;
        this.gbc.gridwidth = 0;
        this.gbl.setConstraints(this.okButton, this.gbc);
        this.add(this.okButton);
    }
    
    public String trimString(final JTextField txt, final int length) {
        String result = new String();
        try {
            result = txt.getText(0, length);
        }
        catch (BadLocationException exc) {
            DrawLocalSettings.LOG.error("BadLocationException: Something wrong in trimString: \n", exc);
        }
        return result;
    }
    
    static {
        LOG = Logger.getLogger(DrawLocalSettings.class);
    }
}
