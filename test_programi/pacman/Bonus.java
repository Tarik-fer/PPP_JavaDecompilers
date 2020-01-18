package Pacman9;

import java.awt.Graphics;
import java.awt.Image;


public class Bonus {
	int posX;
	int posY;
	
	//displays the bonus on the dashBoard	
	void displayBonus(Image bonusImage,int posx,int posy,int width,int height,Graphics g){
		g.drawImage(bonusImage, posx, posy, width, height, DashBoard.ms);		
	}
}
