// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.display.windows;

import javax.swing.JCheckBox;
import pl.art.lach.mateusz.javaopenchess.core.players.Player;
import pl.art.lach.mateusz.javaopenchess.core.Game;
import pl.art.lach.mateusz.javaopenchess.core.players.PlayerFactory;
import pl.art.lach.mateusz.javaopenchess.core.players.PlayerType;
import pl.art.lach.mateusz.javaopenchess.core.Colors;
import pl.art.lach.mateusz.javaopenchess.JChessApp;
import pl.art.lach.mateusz.javaopenchess.utils.MD5;
import pl.art.lach.mateusz.javaopenchess.network.Client;
import pl.art.lach.mateusz.javaopenchess.server.Server;
import javax.swing.JOptionPane;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.AbstractButton;
import pl.art.lach.mateusz.javaopenchess.utils.Settings;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JDialog;
import org.apache.log4j.Logger;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class DrawNetworkSettings extends JPanel implements ActionListener
{
    private static final Logger LOG;
    private JDialog parent;
    private GridBagLayout gbl;
    private GridBagConstraints gbc;
    private ButtonGroup serverORclient;
    private JRadioButton radioServer;
    private JRadioButton radioClient;
    private JLabel labelNick;
    private JLabel labelPassword;
    private JLabel labelGameID;
    private JLabel labelOptions;
    private JPanel panelOptions;
    private JTextField textNick;
    private JPasswordField textPassword;
    private JTextField textGameID;
    private JButton buttonStart;
    private ServOptionsPanel servOptions;
    private ClientOptionsPanel clientOptions;
    
    public DrawNetworkSettings(final JDialog parent) {
        this.parent = parent;
        this.radioServer = new JRadioButton(Settings.lang("create_server"), true);
        this.radioClient = new JRadioButton(Settings.lang("connect_2_server"), false);
        (this.serverORclient = new ButtonGroup()).add(this.radioServer);
        this.serverORclient.add(this.radioClient);
        this.radioServer.addActionListener(this);
        this.radioClient.addActionListener(this);
        this.labelNick = new JLabel(Settings.lang("nickname"));
        this.labelPassword = new JLabel(Settings.lang("password"));
        this.labelGameID = new JLabel(Settings.lang("game_id"));
        this.labelOptions = new JLabel(Settings.lang("server_options"));
        this.textNick = new JTextField();
        this.textPassword = new JPasswordField();
        this.textGameID = new JTextField();
        this.panelOptions = new JPanel();
        this.clientOptions = new ClientOptionsPanel();
        this.servOptions = new ServOptionsPanel();
        (this.buttonStart = new JButton(Settings.lang("start"))).addActionListener(this);
        this.gbl = new GridBagLayout();
        this.gbc = new GridBagConstraints();
        this.gbc.fill = 1;
        this.setLayout(this.gbl);
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        this.gbl.setConstraints(this.radioServer, this.gbc);
        this.add(this.radioServer);
        this.gbc.gridx = 1;
        this.gbc.gridy = 0;
        this.gbl.setConstraints(this.radioClient, this.gbc);
        this.add(this.radioClient);
        this.gbc.gridx = 0;
        this.gbc.gridy = 1;
        this.gbc.gridwidth = 2;
        this.gbl.setConstraints(this.labelGameID, this.gbc);
        this.add(this.labelGameID);
        this.gbc.gridx = 0;
        this.gbc.gridy = 2;
        this.gbc.gridwidth = 2;
        this.gbl.setConstraints(this.textGameID, this.gbc);
        this.add(this.textGameID);
        this.gbc.gridx = 0;
        this.gbc.gridy = 3;
        this.gbc.gridwidth = 2;
        this.gbl.setConstraints(this.labelNick, this.gbc);
        this.add(this.labelNick);
        this.gbc.gridx = 0;
        this.gbc.gridy = 4;
        this.gbc.gridwidth = 2;
        this.gbl.setConstraints(this.textNick, this.gbc);
        this.add(this.textNick);
        this.gbc.gridx = 0;
        this.gbc.gridy = 5;
        this.gbc.gridwidth = 2;
        this.gbl.setConstraints(this.labelPassword, this.gbc);
        this.add(this.labelPassword);
        this.gbc.gridx = 0;
        this.gbc.gridy = 6;
        this.gbc.gridwidth = 2;
        this.gbl.setConstraints(this.textPassword, this.gbc);
        this.add(this.textPassword);
        this.gbc.gridx = 0;
        this.gbc.gridy = 7;
        this.gbc.gridwidth = 2;
        this.gbl.setConstraints(this.labelOptions, this.gbc);
        this.add(this.labelOptions);
        this.gbc.gridx = 0;
        this.gbc.gridy = 8;
        this.gbc.gridwidth = 2;
        this.gbl.setConstraints(this.panelOptions, this.gbc);
        this.add(this.panelOptions);
        this.gbc.gridx = 0;
        this.gbc.gridy = 9;
        this.gbc.gridwidth = 2;
        this.gbl.setConstraints(this.buttonStart, this.gbc);
        this.add(this.buttonStart);
        this.panelOptions.add(this.servOptions);
    }
    
    @Override
    public void actionPerformed(final ActionEvent arg0) {
        if (arg0.getSource() == this.radioServer) {
            this.panelOptions.removeAll();
            this.panelOptions.add(this.servOptions);
            this.panelOptions.revalidate();
            this.panelOptions.requestFocus();
            this.panelOptions.repaint();
        }
        else if (arg0.getSource() == this.radioClient) {
            this.panelOptions.removeAll();
            this.panelOptions.add(this.clientOptions);
            this.panelOptions.revalidate();
            this.panelOptions.requestFocus();
            this.panelOptions.repaint();
        }
        else if (arg0.getSource() == this.buttonStart) {
            String error = "";
            if (this.textGameID.getText().isEmpty()) {
                error = Settings.lang("fill_game_id") + "\n";
            }
            if (this.textNick.getText().length() == 0) {
                error = error + Settings.lang("fill_name") + "\n";
            }
            if (this.textPassword.getText().length() <= 4) {
                error = error + Settings.lang("fill_pass_with_more_than_4_signs") + "\n";
            }
            if (this.radioClient.isSelected() && this.clientOptions.textServIP.getText().length() == 0) {
                error = error + Settings.lang("please_fill_field") + " IP \n";
            }
            else if (this.radioClient.isSelected()) {
                final Pattern ipPattern = Pattern.compile("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}");
                if (!ipPattern.matcher(this.clientOptions.textServIP.getText()).matches()) {
                    error = error + Settings.lang("bad_ip_format") + "\n";
                }
            }
            if (error.length() > 0) {
                JOptionPane.showMessageDialog(this, error);
                return;
            }
            final String pass = this.textPassword.getText().toString();
            if (this.radioServer.isSelected()) {
                final Server server = new Server();
                server.newTable(Integer.parseInt(this.textGameID.getText()), pass, !this.servOptions.checkWitchoutObserver.isSelected(), !this.servOptions.checkDisableChat.isSelected());
                this.clientOptions.textServIP.setText("127.0.0.1");
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {
                    DrawNetworkSettings.LOG.error("InterruptedException: " + ex);
                }
            }
            try {
                final Client client = new Client(this.clientOptions.textServIP.getText(), Server.port);
                final boolean isJoining = client.join(Integer.parseInt(this.textGameID.getText()), !this.clientOptions.checkOnlyWatch.isSelected(), this.textNick.getText(), MD5.encrypt(this.textPassword.getText()));
                if (isJoining) {
                    DrawNetworkSettings.LOG.debug("Client connection: succesful");
                    final Game newGUI = JChessApp.getJavaChessView().addNewTab("Network game, table: " + this.textGameID.getText());
                    client.setGame(newGUI);
                    newGUI.getChessboard().repaint();
                    final Settings sett = newGUI.getSettings();
                    final String whiteName = sett.getPlayerWhite().getName();
                    final String blackName = sett.getPlayerBlack().getName();
                    Player whitePlayer = null;
                    Player blackPlayer = null;
                    if (this.radioServer.isSelected()) {
                        whitePlayer = PlayerFactory.getInstance(whiteName, Colors.WHITE, PlayerType.LOCAL_USER);
                        blackPlayer = PlayerFactory.getInstance(blackName, Colors.BLACK, PlayerType.NETWORK_USER);
                    }
                    else {
                        blackPlayer = PlayerFactory.getInstance(blackName, Colors.BLACK, PlayerType.LOCAL_USER);
                        whitePlayer = PlayerFactory.getInstance(whiteName, Colors.WHITE, PlayerType.NETWORK_USER);
                    }
                    sett.setUpsideDown(false);
                    sett.setPlayerBlack(blackPlayer);
                    sett.setPlayerWhite(whitePlayer);
                    newGUI.setSettings(sett);
                    final Thread thread = new Thread(client);
                    thread.start();
                    this.parent.setVisible(false);
                }
                else {
                    JOptionPane.showMessageDialog(this, Settings.lang("error_connecting_to_server"));
                }
            }
            catch (Error err) {
                DrawNetworkSettings.LOG.error("Client connection: failure: " + err);
                JOptionPane.showMessageDialog(this, err);
            }
            JChessApp.getJavaChessView().setLastTabAsActive();
        }
    }
    
    static {
        LOG = Logger.getLogger(DrawNetworkSettings.class);
    }
    
    private class ServOptionsPanel extends JPanel
    {
        private GridBagLayout gbl;
        private GridBagConstraints gbc;
        private JLabel labelGameTime;
        public JTextField textGameTime;
        public JCheckBox checkWitchoutObserver;
        public JCheckBox checkDisableChat;
        
        ServOptionsPanel() {
            this.labelGameTime = new JLabel(Settings.lang("time_game_min"));
            this.textGameTime = new JTextField();
            this.checkWitchoutObserver = new JCheckBox(Settings.lang("without_observers"));
            this.checkDisableChat = new JCheckBox(Settings.lang("without_chat"));
            this.labelGameTime.setEnabled(false);
            this.textGameTime.setEnabled(false);
            this.checkDisableChat.setEnabled(false);
            this.gbl = new GridBagLayout();
            this.gbc = new GridBagConstraints();
            this.gbc.fill = 1;
            this.setLayout(this.gbl);
            this.gbc.gridx = 0;
            this.gbc.gridy = 0;
            this.gbc.gridwidth = 2;
            this.gbl.setConstraints(this.labelGameTime, this.gbc);
            this.add(this.labelGameTime);
            this.gbc.gridx = 0;
            this.gbc.gridy = 1;
            this.gbc.gridwidth = 2;
            this.gbl.setConstraints(this.textGameTime, this.gbc);
            this.add(this.textGameTime);
            this.gbc.gridx = 0;
            this.gbc.gridy = 2;
            this.gbc.gridwidth = 1;
            this.gbl.setConstraints(this.checkWitchoutObserver, this.gbc);
            this.add(this.checkWitchoutObserver);
            this.gbc.gridx = 1;
            this.gbc.gridy = 2;
            this.gbc.gridwidth = 1;
            this.gbl.setConstraints(this.checkDisableChat, this.gbc);
            this.add(this.checkDisableChat);
        }
    }
    
    private class ClientOptionsPanel extends JPanel
    {
        private GridBagLayout gbl;
        private GridBagConstraints gbc;
        private JLabel labelServIP;
        public JTextField textServIP;
        public JCheckBox checkOnlyWatch;
        
        ClientOptionsPanel() {
            this.labelServIP = new JLabel(Settings.lang("server_ip"));
            this.textServIP = new JTextField();
            this.checkOnlyWatch = new JCheckBox(Settings.lang("only_observe"));
            this.gbl = new GridBagLayout();
            this.gbc = new GridBagConstraints();
            this.gbc.fill = 1;
            this.setLayout(this.gbl);
            this.gbc.gridx = 0;
            this.gbc.gridy = 0;
            this.gbl.setConstraints(this.labelServIP, this.gbc);
            this.add(this.labelServIP);
            this.gbc.gridx = 0;
            this.gbc.gridy = 1;
            this.gbl.setConstraints(this.textServIP, this.gbc);
            this.add(this.textServIP);
            this.gbc.gridx = 0;
            this.gbc.gridy = 2;
            this.gbl.setConstraints(this.checkOnlyWatch, this.gbc);
            this.add(this.checkOnlyWatch);
        }
    }
}
