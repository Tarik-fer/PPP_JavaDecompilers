package Pacman9;

import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DashBoard {

	AudioClip sound4;
	public static int height=0,width=0, row=0, col=0;
	int x = 25;

	public static Cell [][]cellObj;
	int code,temp = 0;
	//int flag = 0,tank = 1, bul = 0,flag1 = 0;
	int dashBoard[][];
	static public Pacman pacman;

	public PacmanGenerator pg;
	int pacmanPositionX,pacmanPositionY;
	MessageBox messageboxObj;
	public static int  ghostPositionX,ghostPositionY;
	GhostGenerator createGhost;
	Boolean collision;
	public static Ghost[] ghostObj;
	int countGhost;
	int ghostPosX=0,ghostPosY=0;
	Bonus bonusObj[];
	int bonusPosX,bonusPosY;
	int NORMAL_BONUS;
	int Total_Bonus=0;

	BonusGenerator createBonus;
	public static Image pacmanImage,ghostImage;
	public static Graphics g;
	static Controller c;
	public static MainScreen ms;
	Boolean nextScreen;
	//keylistener for pacman 
	public void setCollision(){
		this.collision=true;
	}
	public KeyListener dashBoard_KeyListener=new KeyListener(){

		public void keyPressed(KeyEvent e) {

			int code  = e.getKeyCode();//reads the code of the key pressed

			if (code == 38){
				System.out.println("38 pressed...");
				System.out.println("move pacman up");
			}
			else if (code == 39){
				System.out.println("39 pressed...");
				System.out.println("move pacman right");
			}
			else if (code == 40){
				System.out.println("40 pressed...");
				System.out.println("move pacman down");
			}
			else if (code == 37){
				System.out.println("37 pressed...");
				System.out.println("move pacman  left");
			}
			//calling move pacman as per the key pressed
			c.movePacman(code,pacman, g,pacmanImage,width,height,cellObj,messageboxObj,sound4);
			ms.repaint();		
		}

		public void keyReleased(KeyEvent arg0) {
		}

		public void keyTyped(KeyEvent arg0) {
		}
	};


	DashBoard(int height, int width, int row , int col,Controller co, Image pacmanImage2,Image ghostImage,MainScreen msr, AudioClip sound4){
		this.sound4 = sound4;
		this.ms=msr;
		this.height = height;		
		this.width = width;
		this.c=co;
		this.pacmanImage=pacmanImage2;
		this.ghostImage=ghostImage;
		collision=false;
		nextScreen=false;
		this.row = row;
		this.col = col;
		bonusObj =new Bonus[70];
		messageboxObj =new MessageBox();
		ghostObj= new Ghost[4];
		NORMAL_BONUS=5;

		cellObj = new Cell[row][col];
		pg=new PacmanGenerator();
		//initialization of cell by setting attributes of cell as false
		initializeCell(height,width);
		//initialization of dashboard
		dashBoard =initializeDashBoard();
		//initialization of pacman- is to create pacman
		initializePacman();		
		createGhost=new GhostGenerator();
		//initialization of ghost-creating ghost object
		initializeGhost();
		createBonus =new BonusGenerator();
		//creating bonus object
		initializeBonus();		
	}

	//initialize the Ghost
	private void initializeGhost(){
		int i=0,x=10,y=10;
		for (int rowCount = 0; rowCount<row; rowCount++){
			for (int colCount = 0; colCount<col; colCount++){
				if(dashBoard[rowCount][colCount]==2){
					ghostPosX=x;
					ghostPosY=y;
					cellObj[rowCount][colCount].setGhostTrue();
					System.out.println("inside initializeGhost:");
					//through controller requesting to create ghost
					ghostObj[countGhost]=c.createGhost(createGhost,ghostPosX,ghostPosY,g,ms,cellObj,this);
					i++;
					countGhost++;
				}
				x = x+width;
			}
			y = y + height;
			x= 10;

		}
	}

	//initializes the DashBoard
	private int[][] initializeDashBoard(){
		//basic design of dashboard is been set as an array 
		int dashBoard[][]={
				{1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,5,5,5,5,5,5,5,5,5,5,1,1},
				{1,5,1,1,1,5,5,1,1,1,5,1,1},
				{1,5,1,5,5,5,5,5,5,1,5,1,1},
				{1,5,1,5,1,1,5,1,5,1,5,1,1},
				{1,5,5,5,5,5,5,1,5,5,5,1,1},
				{1,5,5,5,1,2,2,2,2,5,5,1,1},
				{1,5,1,5,1,5,1,1,5,1,5,1,1},
				{1,5,1,5,5,5,5,5,5,1,5,1,1},
				{1,5,1,1,1,5,5,1,1,1,5,1,1},
				{1,5,5,5,5,3,5,5,5,5,5,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1}
		};

		return dashBoard;
	}

	//initializes the Cell
	private void initializeCell(int height,int width)
	{
		int x = 10, y = 10;
		for (int rowCount = 0; rowCount<row; rowCount++){
			for (int colCount = 0; colCount<col; colCount++){
				cellObj[rowCount][colCount] = new Cell(x,y,height,width);
				x = x+width;
			}
			y = y + height;
			x= 10;
		}
	}




	//displays the dashBoard
	public void displayDashboard(Graphics g,Image wallImage,Image pacmanImage, Image ghostImage,Image bonusImage, Controller co,AudioClip sound1, AudioClip sound2, AudioClip sound3){

		this.g=g;
		//get pacman score - if less than 660 then game is not over else game over
		if(pacman.getScore()< 680 ){		
			//drawing cells for dashboard
			if(nextScreen==false){ 
				for (int rowCount = 0; rowCount<row; rowCount++){
					for (int colCount = 0; colCount<col; colCount++){
						cellObj[rowCount][colCount].drawCell(g);
					}
				}

				//creating wall on dashboard-placing walls on cell
				createWall(g,wallImage);		

				//displaying bonus currently available
				for(int i=0;i<Total_Bonus;i++){
					int rowNumber = (bonusObj[i].posY-10)/height;
					int colNumber =(bonusObj[i].posX-10)/width;
					if(cellObj[rowNumber][colNumber].checkIfNextCellIsBonus()==true){
						bonusObj[i].displayBonus(bonusImage, bonusObj[i].posX, bonusObj[i].posY, width, height, g);
					}
				}

				System.out.println("inside displaydashboard x= "+pacmanPositionX+",y="+pacmanPositionY);

//				now display pacman on the dashboard		  

				//display the updated messageBox
				messageboxObj.displayMessage(g);
				//displaying ghost		
				for(int k=0;k<countGhost;k++){
					ghostPositionX=ghostObj[k].getPositionX();
					ghostPositionY=ghostObj[k].getPositionY();
				System.out.println("Ghost positions "+ghostPositionX+"  "+ghostPositionY);
				System.out.println("pacman positions "+c.getPacmanPositionX(pacman)+"  "+c.getPacmanPositionY(pacman));
				c.displayGhost(ghostObj[k], g, ghostImage, ghostPositionX, ghostPositionY, width, height);		
                  if(ghostPositionX==c.getPacmanPositionX(pacman)&&ghostPositionY==c.getPacmanPositionY(pacman))	{
                	 nextScreen=true;
                	 try {
                		sound1.stop(); 
                		sound2.play(); 
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					c.destroyGhost(ghostObj[k]);
                	 collision=true;
                	 ms.repaint();
                 }
				}
			}
			else{
				messageboxObj.gameOver(g);
				sound1.stop();
				
			}
			
			if(collision==false)
				c.displayPacman(pacman,g,pacmanImage,c.getPacmanPositionX(pacman),c.getPacmanPositionY(pacman),width,height);
			
		}
		//when game is over display message box destroy all the ghost(stop the ghost thread)
		else if(pacman.getScore()>= 680){
			sound1.stop();
			sound3.play();
			messageboxObj.gameOver(g);
			for(int k=0;k<countGhost;k++)
				c.destroyGhost(ghostObj[k]);			
		}		
	}


	private void initializePacman() {

		int x = 10, y = 10;
		boolean found=false;
		int pacmanRow=0,pacmanCol=0;

		//	get the location of pacman in  cell array
		for ( pacmanRow = 0; pacmanRow<row; pacmanRow++){
			for (pacmanCol = 0; pacmanCol<col; pacmanCol++){
				if(dashBoard[pacmanRow][pacmanCol]==3){
					//pacman initialize position
					pacmanPositionX=x;
					pacmanPositionY=y;
					cellObj[pacmanRow][pacmanCol].setPacmanTrue();
					System.out.println("fond =true , x ="+pacmanPositionX+" ,y ="+pacmanPositionY);
					found=true;
					break;
				}
				x = x+width;
			}
			y = y + height;
			x= 10;
			if(found==true){
				break;
			}

		}
		System.out.println("inside initializePacman x= "+pacmanPositionX+",y="+pacmanPositionY);
		//create pacman object with its initialize position
		pacman=c.generatePacman(pg,pacmanPositionX,pacmanPositionY);

	}

	//sets the wall attribute of cell to true
	public void createWall(Graphics g,Image wallImage){
		for (int rowCount = 0; rowCount<row; rowCount++){
			for (int colCount = 0; colCount<col; colCount++){
				if(dashBoard[rowCount][colCount]==1){
					//calling setwall function to set wall attribute as true
					cellObj[rowCount][colCount].setWall(g,wallImage);
				}
			}
		}
	}

	//initializes the Bonus
	private void initializeBonus(){
		int i=0,x=10,y=10;
		for (int rowCount = 0; rowCount<row; rowCount++){
			for (int colCount = 0; colCount<col; colCount++){
				if(dashBoard[rowCount][colCount]==5){
					//finding position of bonus
					bonusPosX=x;
					bonusPosY=y;
					cellObj[rowCount][colCount].setBonus();
					//creating bonus object through controller
					bonusObj[i]=c.createBonus(createBonus);
					//setting position of each bonus
					bonusObj[i].posX = bonusPosX;
					bonusObj[i].posY = bonusPosY;
					i++;
					Total_Bonus++;
				}
				x = x+width;
			}
			y = y + height;
			x= 10;

		}
	}	
}
