package Pacman9;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Cell {
	
	int height=0,width=0;	
	int x,y;
	boolean pacman=false;
	boolean ghost=false;
	boolean wall=false;
	boolean bonus=false;
	
	//cell class constructor
	Cell(int x,int y, int height, int width){
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
	}
	
	//draws the cell on the dashBoard
	public void drawCell(Graphics g){
			    
		g.setColor(Color.black);
		g.fillRect(x,y,width,height);
		//g.setColor(Color.black);
		g.drawRect(x,y,width,height);
	}
	public void setPacmanTrue(){
		this.pacman=true;
	}
	public void setGhostTrue(){
		this.ghost=true;
	}
	public void unsetPacman(){
		this.pacman=false;
	}
	public void unsetGhost(){
		this.ghost=false;
	}
	
	//sets the wall attribute of this cell to true
	public void setWall(Graphics g,Image wallImage){		
		g.drawImage(wallImage, x, y, width, height, DashBoard.ms);		
		this.wall=true;
	}
	
	//sets the bonus of this cell to true
	public void setBonus(){
		this.bonus=true;
	}
	
	//sets the bonus of this cell to false.
	public void unSetBonus(){
		this.bonus=false;
	}
	
	//checks whether the next cell is a wall 
	public boolean checkIfNextCellIsWall(){	
		return this.wall;
	}
	//check whether the next cell is a bonus
	public boolean checkIfNextCellIsBonus(){
		return bonus;
	}
	public boolean checkIfNextCellIsGhost(){
		return ghost;
	}
	public boolean checkIfNextCellIsPacman(){
		return pacman;
	}
}
