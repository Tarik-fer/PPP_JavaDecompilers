package Game;

import org.newdawn.slick.Color;

public class Button {
   private String text;
   private int x;
   private int y;
   private Color textColor;
   private Color originalColor;
   private Color darkerColor;
   private int fontSize;
   private GameFont font;

   public Button(String text, int x, int y) {
      this.text = text;
      this.x = x;
      this.y = y;
      this.createFont(8);
   }

   public Button(String text, int x, int y, Color textColor) {
      this.text = text;
      this.x = x;
      this.y = y;
      this.textColor = textColor;
      this.originalColor = this.textColor;
      this.darkerColor = new Color(textColor.r * 0.5F, textColor.g * 0.5F, textColor.b * 0.5F);
      this.createFont(8);
   }

   public Button(String text, int x, int y, int fontSize, Color textColor) {
      this.text = text;
      this.x = x;
      this.y = y;
      this.fontSize = fontSize;
      this.textColor = textColor;
      this.originalColor = this.textColor;
      this.darkerColor = new Color(textColor.r * 0.6F, textColor.g * 0.6F, textColor.b * 0.6F, 1.0F);
      this.createFont(this.fontSize);
   }

   public void render() {
      this.font.drawCenteredString(this.text, this.x, this.y, this.textColor);
   }

   public void render(Color textColor) {
      this.font.drawCenteredString(this.text, this.x, this.y, textColor);
   }

   public void render(String text) {
      this.font.drawCenteredString(text, this.x, this.y, this.textColor);
   }

   public void render(String text, int x, int y) {
      this.font.drawCenteredString(text, x, y, this.textColor);
   }

   public void render(int x, int y) {
      this.font.drawCenteredString(this.text, x, y, this.textColor);
   }

   public void setColor(Color textColor) {
      this.textColor = textColor;
      this.originalColor = this.textColor;
   }

   public void setFontSize(int fontSize) {
      this.fontSize = fontSize;
   }

   public void hoverEffect() {
      if (Game.input.getMouseX() > this.x - this.font.getStringWidth(this.text) / 2 && Game.input.getMouseX() < this.x + this.font.getStringWidth(this.text) / 2 && Game.input.getMouseY() > this.y - this.font.getStringHeight(this.text) / 2 && Game.input.getMouseY() < this.y + this.font.getStringHeight(this.text) / 2) {
         this.textColor = this.darkerColor;
      } else {
         this.textColor = this.originalColor;
      }

   }

   public void hoverEffect(int x, int y) {
      if (Game.input.getMouseX() > x - this.font.getStringWidth(this.text) / 2 && Game.input.getMouseX() < x + this.font.getStringWidth(this.text) / 2 && Game.input.getMouseY() > y - this.font.getStringHeight(this.text) / 2 && Game.input.getMouseY() < y + this.font.getStringHeight(this.text) / 2) {
         this.textColor = this.darkerColor;
      } else {
         this.textColor = this.originalColor;
      }

   }

   public boolean isPressed() {
      return Game.input.getMouseX() > this.x - this.font.getStringWidth(this.text) / 2 && Game.input.getMouseX() < this.x + this.font.getStringWidth(this.text) / 2 && Game.input.getMouseY() > this.y - this.font.getStringHeight(this.text) / 2 && Game.input.getMouseY() < this.y + this.font.getStringHeight(this.text) / 2;
   }

   public boolean isPressed(int x, int y) {
      return Game.input.getMouseX() > x - this.font.getStringWidth(this.text) / 2 && Game.input.getMouseX() < x + this.font.getStringWidth(this.text) / 2 && Game.input.getMouseY() > y - this.font.getStringHeight(this.text) / 2 && Game.input.getMouseY() < y + this.font.getStringHeight(this.text) / 2;
   }

   private void createFont(int fontSize) {
      this.font = new GameFont((float)fontSize);
   }

   public void setPosition(int x, int y) {
      this.x = x;
      this.y = y;
   }

   public int getX() {
      return this.x;
   }

   public int getY() {
      return this.y;
   }

   public Color getColor() {
      return this.textColor;
   }
}
