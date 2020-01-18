package Pacman9;


import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;


public class Controller { 
	public Pacman p;
	
	//displays the dashBoard
	public void displayDashBoard(DashBoard db,Graphics g,Image wallImage,Image pacmanImage,Image ghostImage,Image bonusImage ,Controller co, AudioClip sound1,  AudioClip sound2, AudioClip sound3){	
		
		db.displayDashboard(g,wallImage,pacmanImage,ghostImage,bonusImage,co, sound1, sound2,sound3);		
	}
	public void setWallAndGhost(DashBoard db,Graphics g,Image wallImage,Image pacmanImage,Image ghostImage,Image bonusImage ,Controller co,AudioClip sound1, AudioClip sound2, AudioClip sound3){	
		db.displayDashboard(g,wallImage,pacmanImage,ghostImage,bonusImage,co,sound1, sound2,sound3);		
	}

	//creates and returns the pacman object
	public Pacman generatePacman(PacmanGenerator pg,int posX,int posY) {
		Pacman p = pg.createPacman(posX,posY);
		return p;
	}
	
	//displays the pacman on the dashBoard
	public void displayPacman(Pacman pacman,Graphics g,Image pacmanImage,int PacmanPositionX,int PacmanPositionY,int width,int height){
		pacman.displayPacman(g, pacmanImage, PacmanPositionX, PacmanPositionY, width, height);
	}
	
	//displays the ghost on the dashBoard
	public void displayGhost(Ghost ghost,Graphics g,Image ghostImage,int ghostPositionX,int ghostPositionY,int width,int height){
		ghost.displayGhost(ghostImage, ghostPositionX, ghostPositionY, width, height,g );		
	}
	
	//returns the current x-position of the ghost
	public int getghostPositionX(Ghost ghost){
		return ghost.getPositionX();
	}
	
	//returns the current y-position of the ghost
	public int getghostPositionY(Ghost ghost){
		return ghost.getPositionY();
	}
	
	//creates and returns the ghost object
	public Ghost createGhost(GhostGenerator gg,int posX,int posY,Graphics g,MainScreen ms,Cell[][] cellObj,DashBoard d){
		Ghost create=gg.createGhost(posX,posY,this,g,ms,cellObj,d);
		return create;
	}
	
	//returns the X-position of the pacman
	public int getPacmanPositionX(Pacman pacman){
		return pacman.getPacmanPositionX();
	}
	
	//returns the y-position of the pacman
	public int getPacmanPositionY(Pacman pacman){
		return pacman.getPacmanPositionY();
	}
	public void callMessageBox(DashBoard d,Graphics g){
		d.setCollision();
	}
	////creates and returns a bonus object
	public Bonus createBonus(BonusGenerator bp)	{
		Bonus bon = bp.createBonus();
		return bon;
	}

	//moves the pacman to a new position
	public void movePacman(int code,Pacman p,Graphics g,Image pacmanImage,int width,int height,Cell[][] c,MessageBox messageboxObj, AudioClip sound4) {
		System.out.println("in controller"+code);
		p.movePacman(code, g, pacmanImage, width, height,this,c,messageboxObj, sound4);
	}

	//checks whether the passed cell parameter is a wall or not
	public boolean checkWall(int rowNumber,int collNumber,Cell[][] cellObj){
		System.out.println("inside check wall ... roNumber ="+rowNumber+" ,colNumber = "+collNumber);
		return cellObj[rowNumber][collNumber].checkIfNextCellIsWall();
	}
	
	//checks whether the passed cell parameter has a cell or not
	public boolean checkBonus(int rowNumber,int collNumber,Cell[][] cellObj){
		return cellObj[rowNumber][collNumber].checkIfNextCellIsBonus();
	}
	
	public boolean checkPacman(int rowNumber,int collNumber,Cell[][] cellObj){
		return cellObj[rowNumber][collNumber].checkIfNextCellIsPacman();
	}
	public boolean checkGhost(int rowNumber,int collNumber,Cell[][] cellObj){
		return cellObj[rowNumber][collNumber].checkIfNextCellIsGhost();
	}
	//unsets (false) the bonus of the cell passed as parameter
	public void unSetBonus(int rowNumber,int collNumber,Cell[][] cellObj){
		cellObj[rowNumber][collNumber].unSetBonus();
	}
	public void unSetGhost(int rowNumber,int collNumber,Cell[][] cellObj){
		cellObj[rowNumber][collNumber].unsetGhost();
	}
	public void unSetPacman(int rowNumber,int collNumber,Cell[][] cellObj){
		cellObj[rowNumber][collNumber].unsetPacman();
	}
	public void setPacman(int rowNumber,int collNumber,Cell[][] cellObj){
		cellObj[rowNumber][collNumber].setPacmanTrue();
	}
	public void setGhost(int rowNumber,int collNumber,Cell[][] cellObj){
		cellObj[rowNumber][collNumber].setGhostTrue();
	}

	//updates the score board
	public void updateScore(int score,int life,String status,MessageBox messageboxObj){
		messageboxObj.setScore(score,life);
		messageboxObj.setStatus(status);
	}
	
	
	//destroys the ghost thread.
	public void destroyGhost(Ghost g){
		g.destroyGhost();
	}
}