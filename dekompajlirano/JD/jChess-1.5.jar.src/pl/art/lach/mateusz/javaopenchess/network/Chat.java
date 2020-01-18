/*     */ package pl.art.lach.mateusz.javaopenchess.network;
/*     */ 
/*     */ import java.awt.Font;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextArea;
/*     */ import javax.swing.JTextField;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Chat
/*     */   extends JPanel
/*     */   implements ActionListener
/*     */ {
/*     */   protected Client client;
/*     */   private GridBagLayout gbl;
/*     */   private GridBagConstraints gbc;
/*     */   private JScrollPane scrollPane;
/*     */   private JTextArea textOutput;
/*     */   private JTextField textInput;
/*     */   private JButton buttonSend;
/*     */   private Font font;
/*     */   
/*     */   public Chat() {
/*  56 */     initComponents();
/*  57 */     initScrollPane();
/*  58 */     initInputField();
/*  59 */     initSendButton();
/*     */   }
/*     */ 
/*     */   
/*     */   private void initComponents() {
/*  64 */     this.font = new Font("Arial", 1, 10);
/*  65 */     this.textOutput = new JTextArea();
/*  66 */     setFont(this.font);
/*  67 */     this.textOutput.setFont(this.font);
/*  68 */     this.textOutput.setEditable(false);
/*  69 */     this.scrollPane = new JScrollPane();
/*  70 */     this.scrollPane.setViewportView(this.textOutput);
/*  71 */     this.textInput = new JTextField();
/*  72 */     this.textInput.addActionListener(this);
/*  73 */     this.buttonSend = new JButton("^");
/*  74 */     this.buttonSend.addActionListener(this);
/*     */ 
/*     */     
/*  77 */     this.gbl = new GridBagLayout();
/*  78 */     this.gbc = new GridBagConstraints();
/*  79 */     this.gbc.fill = 1;
/*  80 */     setLayout(this.gbl);
/*     */   }
/*     */ 
/*     */   
/*     */   private void initSendButton() {
/*  85 */     this.gbc.gridx = 1;
/*  86 */     this.gbc.gridy = 1;
/*  87 */     this.gbc.gridwidth = 1;
/*  88 */     this.gbc.gridheight = 1;
/*  89 */     this.gbc.weighty = 0.0D;
/*  90 */     this.gbc.weightx = 0.0D;
/*  91 */     this.gbl.setConstraints(this.buttonSend, this.gbc);
/*  92 */     add(this.buttonSend);
/*     */   }
/*     */ 
/*     */   
/*     */   private void initInputField() {
/*  97 */     this.gbc.gridx = 0;
/*  98 */     this.gbc.gridy = 1;
/*  99 */     this.gbc.gridwidth = 1;
/* 100 */     this.gbc.gridheight = 1;
/* 101 */     this.gbc.weighty = 0.0D;
/* 102 */     this.gbc.weightx = 1.0D;
/* 103 */     this.gbl.setConstraints(this.textInput, this.gbc);
/* 104 */     add(this.textInput);
/*     */   }
/*     */ 
/*     */   
/*     */   private void initScrollPane() {
/* 109 */     this.gbc.gridx = 0;
/* 110 */     this.gbc.gridy = 0;
/* 111 */     this.gbc.gridwidth = 2;
/* 112 */     this.gbc.gridheight = 1;
/* 113 */     this.gbc.weighty = 1.0D;
/* 114 */     this.gbc.weightx = 0.0D;
/* 115 */     this.gbl.setConstraints(this.scrollPane, this.gbc);
/* 116 */     add(this.scrollPane);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addMessage(String str) {
/* 125 */     this.textOutput.append(str + "\n");
/* 126 */     this.textOutput.setCaretPosition(this.textOutput.getDocument().getLength());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent arg0) {
/* 136 */     getClient().sendMassage(this.textInput.getText());
/* 137 */     this.textInput.setText("");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 145 */   public Client getClient() { return this.client; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 153 */   public void setClient(Client client) { this.client = client; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnabled(boolean enabled) {
/* 159 */     super.setEnabled(enabled);
/* 160 */     this.buttonSend.setEnabled(enabled);
/* 161 */     this.textInput.setEnabled(enabled);
/* 162 */     this.textOutput.setEnabled(enabled);
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\network\Chat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */