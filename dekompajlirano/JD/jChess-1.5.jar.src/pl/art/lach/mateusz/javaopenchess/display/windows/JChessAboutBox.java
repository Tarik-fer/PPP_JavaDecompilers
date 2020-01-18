/*     */ package pl.art.lach.mateusz.javaopenchess.display.windows;
/*     */ 
/*     */ import java.awt.Frame;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.LayoutStyle;
/*     */ import org.jdesktop.application.Action;
/*     */ import org.jdesktop.application.Application;
/*     */ import org.jdesktop.application.ApplicationActionMap;
/*     */ import org.jdesktop.application.ResourceMap;
/*     */ import pl.art.lach.mateusz.javaopenchess.JChessApp;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JChessAboutBox
/*     */   extends JDialog
/*     */ {
/*     */   private JButton closeButton;
/*     */   
/*     */   public JChessAboutBox(Frame parent) {
/*  23 */     super(parent);
/*  24 */     initComponents();
/*  25 */     getRootPane().setDefaultButton(this.closeButton);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Action
/*  31 */   public void closeAboutBox() { dispose(); }
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
/*  42 */     this.closeButton = new JButton();
/*  43 */     JLabel appTitleLabel = new JLabel();
/*  44 */     JLabel versionLabel = new JLabel();
/*  45 */     JLabel appVersionLabel = new JLabel();
/*  46 */     JLabel homepageLabel = new JLabel();
/*  47 */     JLabel appHomepageLabel = new JLabel();
/*  48 */     JLabel appDescLabel = new JLabel();
/*  49 */     JLabel imageLabel = new JLabel();
/*  50 */     JLabel vendorLabel1 = new JLabel();
/*  51 */     JLabel appVendorLabel1 = new JLabel();
/*  52 */     JLabel appHomepageLabel1 = new JLabel();
/*  53 */     JLabel appHomepageLabel2 = new JLabel();
/*  54 */     JLabel vendorLabel2 = new JLabel();
/*  55 */     JLabel appHomepageLabel3 = new JLabel();
/*     */     
/*  57 */     setDefaultCloseOperation(2);
/*  58 */     ResourceMap resourceMap = ((JChessApp)Application.getInstance(JChessApp.class)).getContext().getResourceMap(JChessAboutBox.class);
/*  59 */     setTitle(resourceMap.getString("title", new Object[0]));
/*  60 */     setModal(true);
/*  61 */     setName("aboutBox");
/*  62 */     setResizable(false);
/*     */     
/*  64 */     ApplicationActionMap applicationActionMap = ((JChessApp)Application.getInstance(JChessApp.class)).getContext().getActionMap(JChessAboutBox.class, this);
/*  65 */     this.closeButton.setAction(applicationActionMap.get("closeAboutBox"));
/*  66 */     this.closeButton.setName("closeButton");
/*     */     
/*  68 */     appTitleLabel.setFont(appTitleLabel.getFont().deriveFont(appTitleLabel.getFont().getStyle() | 0x1, (appTitleLabel.getFont().getSize() + 4)));
/*  69 */     appTitleLabel.setText(resourceMap.getString("Application.title", new Object[0]));
/*  70 */     appTitleLabel.setName("appTitleLabel");
/*     */     
/*  72 */     versionLabel.setFont(versionLabel.getFont().deriveFont(versionLabel.getFont().getStyle() | 0x1));
/*  73 */     versionLabel.setText(resourceMap.getString("versionLabel.text", new Object[0]));
/*  74 */     versionLabel.setName("versionLabel");
/*     */     
/*  76 */     appVersionLabel.setText(resourceMap.getString("Application.version", new Object[0]));
/*  77 */     appVersionLabel.setName("appVersionLabel");
/*     */     
/*  79 */     homepageLabel.setFont(homepageLabel.getFont().deriveFont(homepageLabel.getFont().getStyle() | 0x1));
/*  80 */     homepageLabel.setText(resourceMap.getString("homepageLabel.text", new Object[0]));
/*  81 */     homepageLabel.setName("homepageLabel");
/*     */     
/*  83 */     appHomepageLabel.setText(resourceMap.getString("Application.homepage", new Object[0]));
/*  84 */     appHomepageLabel.setName("appHomepageLabel");
/*     */     
/*  86 */     appDescLabel.setText(resourceMap.getString("appDescLabel.text", new Object[0]));
/*  87 */     appDescLabel.setName("appDescLabel");
/*     */     
/*  89 */     imageLabel.setIcon(resourceMap.getIcon("imageLabel.icon"));
/*  90 */     imageLabel.setName("imageLabel");
/*     */     
/*  92 */     vendorLabel1.setFont(vendorLabel1.getFont().deriveFont(vendorLabel1.getFont().getStyle() | 0x1));
/*  93 */     vendorLabel1.setText(resourceMap.getString("vendorLabel1.text", new Object[0]));
/*  94 */     vendorLabel1.setName("vendorLabel1");
/*     */     
/*  96 */     appVendorLabel1.setName("appVendorLabel1");
/*     */     
/*  98 */     appHomepageLabel1.setText(resourceMap.getString("appHomepageLabel1.text", new Object[0]));
/*  99 */     appHomepageLabel1.setName("appHomepageLabel1");
/*     */     
/* 101 */     appHomepageLabel2.setText(resourceMap.getString("appHomepageLabel2.text", new Object[0]));
/* 102 */     appHomepageLabel2.setName("appHomepageLabel2");
/*     */     
/* 104 */     vendorLabel2.setFont(vendorLabel2.getFont().deriveFont(vendorLabel2.getFont().getStyle() | 0x1));
/* 105 */     vendorLabel2.setText(resourceMap.getString("vendorLabel2.text", new Object[0]));
/* 106 */     vendorLabel2.setName("vendorLabel2");
/*     */     
/* 108 */     appHomepageLabel3.setText(resourceMap.getString("appHomepageLabel3.text", new Object[0]));
/* 109 */     appHomepageLabel3.setName("appHomepageLabel3");
/*     */     
/* 111 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 112 */     getContentPane().setLayout(layout);
/* 113 */     layout.setHorizontalGroup(layout
/* 114 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 115 */         .addGroup(layout.createSequentialGroup()
/* 116 */           .addComponent(imageLabel)
/* 117 */           .addGap(18, 18, 18)
/* 118 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 119 */             .addComponent(appTitleLabel, GroupLayout.Alignment.LEADING)
/* 120 */             .addComponent(appDescLabel, GroupLayout.Alignment.LEADING, -1, 691, 32767)
/* 121 */             .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
/* 122 */               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 123 */                 .addComponent(versionLabel)
/* 124 */                 .addComponent(homepageLabel)
/* 125 */                 .addComponent(vendorLabel1)
/* 126 */                 .addComponent(vendorLabel2))
/* 127 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 128 */               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 129 */                 .addGroup(layout.createSequentialGroup()
/* 130 */                   .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 131 */                     .addComponent(appHomepageLabel1)
/* 132 */                     .addComponent(appHomepageLabel3, -1, 466, 32767))
/* 133 */                   .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 134 */                   .addComponent(this.closeButton))
/* 135 */                 .addComponent(appVersionLabel)
/* 136 */                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
/* 137 */                   .addComponent(appVendorLabel1, GroupLayout.Alignment.LEADING, -1, -1, 32767)
/* 138 */                   .addComponent(appHomepageLabel, GroupLayout.Alignment.LEADING, -1, -1, 32767)
/* 139 */                   .addComponent(appHomepageLabel2, -1, -1, 32767)))))
/* 140 */           .addContainerGap()));
/*     */     
/* 142 */     layout.setVerticalGroup(layout
/* 143 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 144 */         .addComponent(imageLabel, -2, 194, 32767)
/* 145 */         .addGroup(layout.createSequentialGroup()
/* 146 */           .addContainerGap()
/* 147 */           .addComponent(appTitleLabel)
/* 148 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 149 */           .addComponent(appDescLabel)
/* 150 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 151 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 152 */             .addComponent(versionLabel)
/* 153 */             .addComponent(appVersionLabel))
/* 154 */           .addGap(27, 27, 27)
/* 155 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 156 */             .addComponent(homepageLabel)
/* 157 */             .addComponent(appHomepageLabel))
/* 158 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 159 */             .addGroup(layout.createSequentialGroup()
/* 160 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 41, 32767)
/* 161 */               .addComponent(this.closeButton)
/* 162 */               .addContainerGap())
/* 163 */             .addGroup(layout.createSequentialGroup()
/* 164 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 165 */               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 166 */                 .addComponent(vendorLabel1)
/* 167 */                 .addComponent(appVendorLabel1)
/* 168 */                 .addComponent(appHomepageLabel2))
/* 169 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 170 */               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 171 */                 .addComponent(appHomepageLabel1)
/* 172 */                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 173 */                   .addComponent(vendorLabel2)
/* 174 */                   .addComponent(appHomepageLabel3)))
/* 175 */               .addGap(36, 36, 36)))));
/*     */ 
/*     */     
/* 178 */     pack();
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\display\windows\JChessAboutBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */