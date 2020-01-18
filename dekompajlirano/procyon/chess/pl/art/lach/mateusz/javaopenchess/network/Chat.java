// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.network;

import java.awt.event.ActionEvent;
import java.awt.LayoutManager;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class Chat extends JPanel implements ActionListener
{
    protected Client client;
    private GridBagLayout gbl;
    private GridBagConstraints gbc;
    private JScrollPane scrollPane;
    private JTextArea textOutput;
    private JTextField textInput;
    private JButton buttonSend;
    private Font font;
    
    public Chat() {
        this.initComponents();
        this.initScrollPane();
        this.initInputField();
        this.initSendButton();
    }
    
    private void initComponents() {
        this.font = new Font("Arial", 1, 10);
        this.textOutput = new JTextArea();
        this.setFont(this.font);
        this.textOutput.setFont(this.font);
        this.textOutput.setEditable(false);
        (this.scrollPane = new JScrollPane()).setViewportView(this.textOutput);
        (this.textInput = new JTextField()).addActionListener(this);
        (this.buttonSend = new JButton("^")).addActionListener(this);
        this.gbl = new GridBagLayout();
        this.gbc = new GridBagConstraints();
        this.gbc.fill = 1;
        this.setLayout(this.gbl);
    }
    
    private void initSendButton() {
        this.gbc.gridx = 1;
        this.gbc.gridy = 1;
        this.gbc.gridwidth = 1;
        this.gbc.gridheight = 1;
        this.gbc.weighty = 0.0;
        this.gbc.weightx = 0.0;
        this.gbl.setConstraints(this.buttonSend, this.gbc);
        this.add(this.buttonSend);
    }
    
    private void initInputField() {
        this.gbc.gridx = 0;
        this.gbc.gridy = 1;
        this.gbc.gridwidth = 1;
        this.gbc.gridheight = 1;
        this.gbc.weighty = 0.0;
        this.gbc.weightx = 1.0;
        this.gbl.setConstraints(this.textInput, this.gbc);
        this.add(this.textInput);
    }
    
    private void initScrollPane() {
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        this.gbc.gridwidth = 2;
        this.gbc.gridheight = 1;
        this.gbc.weighty = 1.0;
        this.gbc.weightx = 0.0;
        this.gbl.setConstraints(this.scrollPane, this.gbc);
        this.add(this.scrollPane);
    }
    
    public void addMessage(final String str) {
        this.textOutput.append(str + "\n");
        this.textOutput.setCaretPosition(this.textOutput.getDocument().getLength());
    }
    
    @Override
    public void actionPerformed(final ActionEvent arg0) {
        this.getClient().sendMassage(this.textInput.getText());
        this.textInput.setText("");
    }
    
    public Client getClient() {
        return this.client;
    }
    
    public void setClient(final Client client) {
        this.client = client;
    }
    
    @Override
    public void setEnabled(final boolean enabled) {
        super.setEnabled(enabled);
        this.buttonSend.setEnabled(enabled);
        this.textInput.setEnabled(enabled);
        this.textOutput.setEnabled(enabled);
    }
}
