package Pacman9;
import java.awt.Graphics;

public class GhostGenerator {
	
	//creates and return ghost object 
	public Ghost createGhost(int posX, int posY,Controller c,Graphics g,MainScreen ms,Cell[][] cellObj,DashBoard d) {
		return new Ghost(posX,posY,c,g,ms,cellObj,d);
	}
}
