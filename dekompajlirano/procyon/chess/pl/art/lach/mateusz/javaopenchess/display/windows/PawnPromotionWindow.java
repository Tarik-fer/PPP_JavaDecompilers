// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.display.windows;

import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import pl.art.lach.mateusz.javaopenchess.utils.GUI;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

public class PawnPromotionWindow extends JDialog implements ActionListener
{
    JButton knightButton;
    JButton bishopButton;
    JButton rookButton;
    JButton queenButton;
    GridBagLayout gbl;
    public String result;
    GridBagConstraints gbc;
    
    public PawnPromotionWindow(final Frame parent, final String color) {
        super(parent);
        this.setTitle("Choose piece");
        this.setMinimumSize(new Dimension(520, 130));
        this.setSize(new Dimension(520, 130));
        this.setMaximumSize(new Dimension(520, 130));
        this.setResizable(false);
        this.setLayout(new GridLayout(1, 4));
        this.gbl = new GridBagLayout();
        this.gbc = new GridBagConstraints();
        this.knightButton = new JButton(new ImageIcon(GUI.loadImage("Knight-" + color + "70.png")));
        this.bishopButton = new JButton(new ImageIcon(GUI.loadImage("Bishop-" + color + "70.png")));
        this.rookButton = new JButton(new ImageIcon(GUI.loadImage("Rook-" + color + "70.png")));
        this.queenButton = new JButton(new ImageIcon(GUI.loadImage("Queen-" + color + "70.png")));
        this.result = "";
        this.knightButton.addActionListener(this);
        this.bishopButton.addActionListener(this);
        this.rookButton.addActionListener(this);
        this.queenButton.addActionListener(this);
        this.add(this.queenButton);
        this.add(this.rookButton);
        this.add(this.bishopButton);
        this.add(this.knightButton);
    }
    
    public void setColor(final String color) {
        this.knightButton.setIcon(new ImageIcon(GUI.loadImage("Knight-" + color + "70.png")));
        this.bishopButton.setIcon(new ImageIcon(GUI.loadImage("Bishop-" + color + "70.png")));
        this.rookButton.setIcon(new ImageIcon(GUI.loadImage("Rook-" + color + "70.png")));
        this.queenButton.setIcon(new ImageIcon(GUI.loadImage("Queen-" + color + "70.png")));
    }
    
    @Override
    public void actionPerformed(final ActionEvent arg0) {
        if (arg0.getSource() == this.queenButton) {
            this.result = "Queen";
        }
        else if (arg0.getSource() == this.rookButton) {
            this.result = "Rook";
        }
        else if (arg0.getSource() == this.bishopButton) {
            this.result = "Bishop";
        }
        else {
            this.result = "Knight";
        }
        this.setVisible(false);
    }
}
