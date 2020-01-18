package Pacman9;

import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;

public class Pacman {
	int life;
	int positionX,positionY,rowNumber,colNumber;// current position x and y of pacman on the dashBoard
	int prevRowNumber,prevColNumber;
	int score=0;
	int MaxScore= 680;
	
	Key keyType;
    public static KeyGenerator generateKeyType;
    int ppositionX,ppositionY;
    
    //pacman constructor
    Pacman(int posX,int posY){
    	//setting pacman initial position
    	this.positionX=posX;
    	this.positionY=posY;

    	System.out.println("inside pacman cons ... x= "+posX+", y = "+posY);
    	//creating an object of key generator
    	generateKeyType =new KeyGenerator();
    	
    }
    
	void deadPacman(){		
	}	
	void collisionBonus(Bonus b, Cell[][] c){		
	}
	void collisionGhost(Ghost g, Cell[][] c){		
	}
		
	void collisionWall(Cell[][] c){		
	}
	//return current X position of pacman
	int getPacmanPositionX(){
		return positionX;
	}
//	return current Y position of pacman
	int getPacmanPositionY(){
		return positionY;
	}
	void movePacman(int keyPressed,Graphics g,Image pacmanImage,int width,int height,Controller c,Cell[][] cellObj,MessageBox messageboxObj, AudioClip sound4){

		System.out.println("in movepacman"+keyPressed);
		//through key pressed code(came from key event listenner) creating key type
		keyType=generateKeyType.createKey(keyPressed);
		//finding out to which row and column of cell pacman is present
		rowNumber =((positionY-10)/height);
		colNumber =((positionX-10)/width);
		prevColNumber=colNumber;
		prevRowNumber=rowNumber;
		System.out.println("rownumber  "+rowNumber+" colNumber "+colNumber);
		//get the new position (rownumber,column number)of pacman depending on key object(up,down,left,right)
		
		keyType.newPosition(rowNumber,colNumber,this);
		System.out.println("New rownumber  "+rowNumber+" New colNumber "+colNumber);
		System.out.println("Conditionnnnnnnnnnn ="+c.checkWall(rowNumber,colNumber,cellObj));
		//first we check whether next cell is wall or not
		if(c.checkWall(rowNumber,colNumber,cellObj)==false){
			c.unSetPacman(prevRowNumber,prevRowNumber, cellObj);
			c.setPacman(rowNumber, colNumber, cellObj);
			//if no wall check whether next cell conatain bonus or not
			if(c.checkBonus(rowNumber,colNumber,cellObj)==true){
				//if cell has bonus- unsetbonus(make cell attribute bonus as false) represent bonus is been eaten
				c.unSetBonus(rowNumber,colNumber,cellObj);
				//now update the score whenever bonus is eaten
				sound4.play();
				score+=10;
				this.setScore(score);
				life=3;
				
				if(this.score==MaxScore)
				{
				
				//call for mesage box saying game is over	
					c.updateScore(this.score,this.life,"Game Over",messageboxObj);
					
				}
				else
				{
					//if score not reaches maximum score jus update score which appear next to dashboard
				c.updateScore(score,life,"Game Started",messageboxObj);
				}
			}
			ppositionX=positionX;
			ppositionY=positionY;
			
			//set pacman new position as per the key pressed
			keyType.setPacmanNewPosition(positionX,positionY,this);
			
		}

		//keyType.setPacmanNewPosition(positionX,positionY,this);
		//return null;
		
		
		System.out.println("pacmanImage = "+pacmanImage);
		
		//this.displayPacman(g, pacmanImage, positionX, positionY, width, height);
		//g.drawRect(positionX+36, positionY, width, height);
		
//*		repaint();
		
	}
	
	void setPosition(int x,int y){
		positionX=x;
		positionY=y;
	}
	void setCellNumber(int x,int y){
		rowNumber=x;
		colNumber=y;
	}
	
	void setScore(int score)
	{
		this.score = score;
	}
	int getScore()
	{
		return score;
	}

	public void displayPacman(Graphics g, Image pacmanImage,int pacmanRow, int pacmanCol,int width,int height) {
		// TODO Auto-generated method stub		
		System.out.println("inside  display pacman ()..........");
		System.out.println("pacmanaiamge = "+pacmanImage);
		System.out.println("pacmanrow = "+pacmanRow);
		System.out.println("pacmancol = "+pacmanCol);
		System.out.println("width = "+width);
		System.out.println("height = "+height);
		g.fillRect(pacmanRow,pacmanCol,width,height);
	 
	//drawing image of cell  
		g.drawImage(pacmanImage,pacmanRow,pacmanCol,width,height,DashBoard.ms);
	
		

		
	}
}
