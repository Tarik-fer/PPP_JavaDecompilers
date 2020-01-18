/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.Font;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.util.Random;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextArea;
/*     */ import javax.swing.LayoutStyle;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.UnsupportedLookAndFeelException;
/*     */ 
/*     */ public class reflex
/*     */   extends JFrame {
/*     */   public long startTime;
/*     */   public long endTime;
/*     */   public static JButton begin;
/*     */   public static JPanel colorBox;
/*     */   private Box.Filler filler2;
/*     */   public static JTextArea history;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JLabel jLabel3;
/*     */   private JLabel jLabel4;
/*     */   private JLabel jLabel5;
/*     */   private JPanel jPanel1;
/*     */   private JPanel jPanel2;
/*     */   private JPanel jPanel3;
/*     */   private JScrollPane jScrollPane1;
/*     */   public static JLabel react;
/*     */   public static JButton reactBut;
/*     */   public static JButton reset;
/*     */   private JLabel time;
/*     */   
/*  48 */   public long getDuration() { return this.endTime - this.startTime; }
/*     */ 
/*     */   
/*     */   public long start() {
/*  52 */     this.startTime = System.currentTimeMillis();
/*  53 */     return this.startTime;
/*     */   }
/*     */   public long stop() {
/*  56 */     this.endTime = System.currentTimeMillis();
/*  57 */     return this.endTime;
/*     */   }
/*     */   
/*     */   public reflex() throws InterruptedException {
/*  61 */     initComponents();
/*  62 */     reactBut.setEnabled(false);
/*  63 */     reset.setEnabled(false);
/*     */   }
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
/*     */   private void initComponents() {
/*  80 */     this.filler2 = new Box.Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(0, 32767));
/*  81 */     this.jPanel1 = new JPanel();
/*  82 */     this.jLabel1 = new JLabel();
/*  83 */     this.jLabel5 = new JLabel();
/*  84 */     this.jLabel3 = new JLabel();
/*  85 */     this.jPanel2 = new JPanel();
/*  86 */     this.jLabel2 = new JLabel();
/*  87 */     this.jScrollPane1 = new JScrollPane();
/*  88 */     history = new JTextArea();
/*  89 */     this.time = new JLabel();
/*  90 */     this.jLabel4 = new JLabel();
/*  91 */     react = new JLabel();
/*  92 */     this.jPanel3 = new JPanel();
/*  93 */     reset = new JButton();
/*  94 */     colorBox = new JPanel();
/*  95 */     begin = new JButton();
/*  96 */     reactBut = new JButton();
/*     */     
/*  98 */     setDefaultCloseOperation(3);
/*     */     
/* 100 */     this.jPanel1.setBorder(BorderFactory.createTitledBorder(null, "Upute", 0, 0, new Font("Arial", 0, 18)));
/*     */     
/* 102 */     this.jLabel1.setFont(new Font("Arial", 0, 14));
/* 103 */     this.jLabel1.setText("Kada se promijeni boja kvadratića klikni te na gumb \"Klikni ovdje\"");
/*     */     
/* 105 */     this.jLabel5.setFont(new Font("Arial", 0, 14));
/* 106 */     this.jLabel5.setText("Nakon što kliknete \"ponovi\" ponovo kliknite na gumb \"Kreni\"");
/*     */     
/* 108 */     this.jLabel3.setFont(new Font("Arial", 0, 14));
/* 109 */     this.jLabel3.setText("Kliknite na \"Kreni\" kako bi ste započeli i pomaknite miš na gumb \"Klikni ovdje\"");
/*     */     
/* 111 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 112 */     this.jPanel1.setLayout(jPanel1Layout);
/* 113 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout
/* 114 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 115 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 116 */           .addContainerGap()
/* 117 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 118 */             .addComponent(this.jLabel3)
/* 119 */             .addComponent(this.jLabel1)
/* 120 */             .addComponent(this.jLabel5))
/* 121 */           .addContainerGap(-1, 32767)));
/*     */     
/* 123 */     jPanel1Layout.setVerticalGroup(jPanel1Layout
/* 124 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 125 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 126 */           .addContainerGap()
/* 127 */           .addComponent(this.jLabel3)
/* 128 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 129 */           .addComponent(this.jLabel1)
/* 130 */           .addGap(18, 18, 18)
/* 131 */           .addComponent(this.jLabel5)
/* 132 */           .addContainerGap()));
/*     */ 
/*     */     
/* 135 */     this.jPanel2.setBorder(BorderFactory.createTitledBorder(null, "Vrijeme", 0, 0, new Font("Arial", 0, 18)));
/*     */     
/* 137 */     this.jLabel2.setFont(new Font("Tahoma", 0, 12));
/* 138 */     this.jLabel2.setText("[ms]");
/*     */     
/* 140 */     history.setEditable(false);
/* 141 */     history.setColumns(20);
/* 142 */     history.setFont(new Font("Arial", 0, 14));
/* 143 */     history.setRows(5);
/* 144 */     this.jScrollPane1.setViewportView(history);
/*     */     
/* 146 */     this.time.setFont(new Font("Arial", 0, 14));
/* 147 */     this.time.setText("Vrijeme:");
/*     */     
/* 149 */     this.jLabel4.setFont(new Font("Arial", 0, 14));
/* 150 */     this.jLabel4.setText("Povijest rezultata:");
/*     */     
/* 152 */     react.setFont(new Font("Arial", 0, 14));
/*     */     
/* 154 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/* 155 */     this.jPanel2.setLayout(jPanel2Layout);
/* 156 */     jPanel2Layout.setHorizontalGroup(jPanel2Layout
/* 157 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 158 */         .addGroup(jPanel2Layout.createSequentialGroup()
/* 159 */           .addContainerGap()
/* 160 */           .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 161 */             .addComponent(this.jLabel4)
/* 162 */             .addComponent(this.jScrollPane1, -2, -1, -2)
/* 163 */             .addGroup(jPanel2Layout.createSequentialGroup()
/* 164 */               .addComponent(this.time)
/* 165 */               .addGap(2, 2, 2)
/* 166 */               .addComponent(react, -2, 48, -2)
/* 167 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 168 */               .addComponent(this.jLabel2)))
/* 169 */           .addContainerGap()));
/*     */     
/* 171 */     jPanel2Layout.setVerticalGroup(jPanel2Layout
/* 172 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 173 */         .addGroup(jPanel2Layout.createSequentialGroup()
/* 174 */           .addContainerGap()
/* 175 */           .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 176 */             .addComponent(this.time, GroupLayout.Alignment.LEADING)
/* 177 */             .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 178 */               .addComponent(react, -2, 17, -2)
/* 179 */               .addComponent(this.jLabel2)))
/* 180 */           .addGap(18, 18, 18)
/* 181 */           .addComponent(this.jLabel4)
/* 182 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 183 */           .addComponent(this.jScrollPane1, -2, 156, -2)
/* 184 */           .addContainerGap()));
/*     */ 
/*     */     
/* 187 */     this.jPanel3.setBorder(BorderFactory.createTitledBorder(null, "Test", 0, 0, new Font("Arial", 0, 18)));
/*     */     
/* 189 */     reset.setText("Ponovi");
/* 190 */     reset.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 192 */             reflex.this.resetActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 196 */     colorBox.setBackground(new Color(0, 0, 255));
/* 197 */     colorBox.addPropertyChangeListener(new PropertyChangeListener() {
/*     */           public void propertyChange(PropertyChangeEvent evt) {
/* 199 */             reflex.this.colorBoxPropertyChange(evt);
/*     */           }
/*     */         });
/*     */     
/* 203 */     GroupLayout colorBoxLayout = new GroupLayout(colorBox);
/* 204 */     colorBox.setLayout(colorBoxLayout);
/* 205 */     colorBoxLayout.setHorizontalGroup(colorBoxLayout
/* 206 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 207 */         .addGap(0, 100, 32767));
/*     */     
/* 209 */     colorBoxLayout.setVerticalGroup(colorBoxLayout
/* 210 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 211 */         .addGap(0, 100, 32767));
/*     */ 
/*     */     
/* 214 */     begin.setText("Kreni");
/* 215 */     begin.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 217 */             reflex.this.beginActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 221 */     reactBut.setText("Klikni ovdje");
/* 222 */     reactBut.setToolTipText("");
/* 223 */     reactBut.setActionCommand("beg");
/* 224 */     reactBut.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 226 */             reflex.this.reactButActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 230 */     GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
/* 231 */     this.jPanel3.setLayout(jPanel3Layout);
/* 232 */     jPanel3Layout.setHorizontalGroup(jPanel3Layout
/* 233 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 234 */         .addGroup(jPanel3Layout.createSequentialGroup()
/* 235 */           .addContainerGap()
/* 236 */           .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 237 */             .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
/* 238 */               .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 239 */                 .addComponent(begin)
/* 240 */                 .addComponent(reset))
/* 241 */               .addGap(18, 18, 18)
/* 242 */               .addComponent(colorBox, -2, -1, -2))
/* 243 */             .addComponent(reactBut, GroupLayout.Alignment.TRAILING, -2, 140, -2))
/* 244 */           .addContainerGap()));
/*     */     
/* 246 */     jPanel3Layout.setVerticalGroup(jPanel3Layout
/* 247 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 248 */         .addGroup(jPanel3Layout.createSequentialGroup()
/* 249 */           .addContainerGap()
/* 250 */           .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 251 */             .addGroup(jPanel3Layout.createSequentialGroup()
/* 252 */               .addComponent(colorBox, -2, -1, -2)
/* 253 */               .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 254 */               .addComponent(reactBut, -2, 56, -2))
/* 255 */             .addGroup(jPanel3Layout.createSequentialGroup()
/* 256 */               .addComponent(begin)
/* 257 */               .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 258 */               .addComponent(reset)))
/* 259 */           .addContainerGap()));
/*     */ 
/*     */     
/* 262 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 263 */     getContentPane().setLayout(layout);
/* 264 */     layout.setHorizontalGroup(layout
/* 265 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 266 */         .addGroup(layout.createSequentialGroup()
/* 267 */           .addContainerGap()
/* 268 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 269 */             .addGroup(layout.createSequentialGroup()
/* 270 */               .addComponent(this.jPanel2, -2, -1, -2)
/* 271 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767)
/* 272 */               .addComponent(this.jPanel3, -2, -1, -2))
/* 273 */             .addComponent(this.jPanel1, -1, -1, 32767))
/* 274 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 275 */           .addComponent(this.filler2, -2, 10, -2)
/* 276 */           .addGap(22, 22, 22)));
/*     */     
/* 278 */     layout.setVerticalGroup(layout
/* 279 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 280 */         .addGroup(layout.createSequentialGroup()
/* 281 */           .addGap(46, 46, 46)
/* 282 */           .addComponent(this.filler2, -2, 155, -2)
/* 283 */           .addContainerGap(-1, 32767))
/* 284 */         .addGroup(layout.createSequentialGroup()
/* 285 */           .addContainerGap(-1, 32767)
/* 286 */           .addComponent(this.jPanel1, -2, -1, -2)
/* 287 */           .addGap(18, 18, 18)
/* 288 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 289 */             .addComponent(this.jPanel2, -2, -1, -2)
/* 290 */             .addComponent(this.jPanel3, -2, -1, -2))
/* 291 */           .addGap(18, 18, 18)));
/*     */ 
/*     */     
/* 294 */     pack();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void colorBoxPropertyChange(PropertyChangeEvent evt) {}
/*     */ 
/*     */   
/*     */   private void reactButActionPerformed(ActionEvent evt) {
/* 303 */     stop();
/* 304 */     if (getDuration() > 1L) {
/*     */       
/* 306 */       react.setText(String.valueOf(getDuration()));
/* 307 */       history.append(String.valueOf(getDuration()) + "\n");
/* 308 */       reset.setEnabled(true);
/* 309 */       begin.setEnabled(false);
/* 310 */       reactBut.setEnabled(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void beginActionPerformed(ActionEvent evt) {
/* 317 */     Random rand = new Random();
/*     */     try {
/* 319 */       TimeUnit.SECONDS.sleep((rand.nextInt(7) + 1));
/* 320 */     } catch (InterruptedException ex) {
/* 321 */       Logger.getLogger(reflex.class.getName()).log(Level.SEVERE, null, ex);
/*     */     } 
/* 323 */     colorBox.setBackground(Color.RED);
/* 324 */     reactBut.setEnabled(true);
/*     */     
/* 326 */     start();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void resetActionPerformed(ActionEvent evt) {
/* 332 */     colorBox.setBackground(Color.BLUE);
/* 333 */     react.setText("");
/* 334 */     this.startTime = 0L;
/* 335 */     this.endTime = 0L;
/* 336 */     reactBut.setEnabled(false);
/* 337 */     reset.setEnabled(false);
/* 338 */     begin.setEnabled(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/*     */     try {
/* 351 */       for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
/* 352 */         if ("Nimbus".equals(info.getName())) {
/* 353 */           UIManager.setLookAndFeel(info.getClassName());
/*     */           break;
/*     */         } 
/*     */       } 
/* 357 */     } catch (ClassNotFoundException ex) {
/* 358 */       Logger.getLogger(reflex.class.getName()).log(Level.SEVERE, null, ex);
/* 359 */     } catch (InstantiationException ex) {
/* 360 */       Logger.getLogger(reflex.class.getName()).log(Level.SEVERE, null, ex);
/* 361 */     } catch (IllegalAccessException ex) {
/* 362 */       Logger.getLogger(reflex.class.getName()).log(Level.SEVERE, null, ex);
/* 363 */     } catch (UnsupportedLookAndFeelException ex) {
/* 364 */       Logger.getLogger(reflex.class.getName()).log(Level.SEVERE, null, ex);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 369 */     EventQueue.invokeLater(new Runnable() {
/*     */           public void run() {
/*     */             try {
/* 372 */               (new reflex()).setVisible(true);
/* 373 */             } catch (InterruptedException ex) {
/* 374 */               Logger.getLogger(reflex.class.getName()).log(Level.SEVERE, null, ex);
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
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
/* 402 */   private void setText(String ajme) { throw new UnsupportedOperationException("Not supported yet."); }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\reflexTest\dist\reflexTest.jar!\reflex.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */