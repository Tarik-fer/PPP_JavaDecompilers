/*     */ package pl.art.lach.mateusz.javaopenchess.display.windows;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Rectangle;
/*     */ import javax.swing.Icon;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class TabbedPaneIcon
/*     */   implements Icon
/*     */ {
/*     */   private int x_pos;
/*     */   private int y_pos;
/*     */   private int width;
/*     */   private int height;
/*     */   private Icon fileIcon;
/*     */   
/*     */   public TabbedPaneIcon(Icon fileIcon) {
/* 230 */     this.fileIcon = fileIcon;
/* 231 */     this.width = 16;
/* 232 */     this.height = 16;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void paintIcon(Component c, Graphics g, int x, int y) {
/* 238 */     this.x_pos = x;
/* 239 */     this.y_pos = y;
/*     */     
/* 241 */     Color col = g.getColor();
/*     */     
/* 243 */     g.setColor(Color.black);
/* 244 */     int yP = y + 2;
/* 245 */     g.drawLine(x + 3, yP + 3, x + 10, yP + 10);
/* 246 */     g.drawLine(x + 3, yP + 4, x + 9, yP + 10);
/* 247 */     g.drawLine(x + 4, yP + 3, x + 10, yP + 9);
/* 248 */     g.drawLine(x + 10, yP + 3, x + 3, yP + 10);
/* 249 */     g.drawLine(x + 10, yP + 4, x + 4, yP + 10);
/* 250 */     g.drawLine(x + 9, yP + 3, x + 3, yP + 9);
/* 251 */     g.setColor(col);
/* 252 */     if (this.fileIcon != null)
/*     */     {
/* 254 */       this.fileIcon.paintIcon(c, g, x + this.width, yP);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 261 */   public int getIconWidth() { return this.width + ((this.fileIcon != null) ? this.fileIcon.getIconWidth() : 0); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 267 */   public int getIconHeight() { return this.height; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 272 */   public Rectangle getBounds() { return new Rectangle(this.x_pos, this.y_pos, this.width, this.height); }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\display\windows\TabbedPaneIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */