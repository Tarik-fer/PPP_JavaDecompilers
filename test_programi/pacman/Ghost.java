package Pacman9;
import java.awt.Graphics;
import java.awt.Image;


public class Ghost implements Runnable
{
	int positionX,positionY,rowNumber,colNumber;
	 KeyGenerator generateKeyType;
	 Key keyType;
	int Left=0;
	int Right=1;
	int Up=2;
	int Down=3;
	int previousDirection=0;
	int count;
	boolean  [] direction;
	int width;
	int height;
	MainScreen ms;
	Controller c;
	Cell [][] cellObj;
	Graphics g;
	DashBoard d;
	Thread t;//create a ghost object thread
	public Ghost(int posX,int posY,Controller c,Graphics g,MainScreen ms,Cell[][] cellObj,DashBoard d)
	{
		 t= new Thread(this);

		this.positionX=posX;
    	this.positionY=posY;
    	count =0;
    	this.d=d;
    	System.out.println("inside ghost cons ... x= "+posX+", y = "+posY);
		direction =new boolean[4];
		this.ms=ms;
		this.c=c;
		this.cellObj=cellObj;
		this.g=g;
		if(!t.isAlive())
			t.start();		
	}
	
	//displays the ghost
	void displayGhost(Image ghostImage,int posx,int posy,int width,int height,Graphics g){
		g.drawImage(ghostImage, posx, posy, width, height, DashBoard.ms);
	}
	
	//get the X-position of ghost
	int getPositionX(){
		return positionX;
	}
	
	//gets the y-position of ghost
	int getPositionY(){
		return positionY;
	}
	
	//get any random empty direction
	void getRandomEmptyDirection(int height,int width ){
		boolean up,down,left,right;
		rowNumber =((positionY-10)/height);
		colNumber =((positionX-10)/width);
		System.out.println("Annnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn"+rowNumber+" "+colNumber);
		
		count=0;
		for(int i=0;i<4;i++){
			direction[i]=false;
		}
		System.out.println("checking wall for row = "+rowNumber+" ,col= "+colNumber				);
		
		if((rowNumber-1)>=0 && c.checkWall(rowNumber-1, colNumber, cellObj)==false){
			direction[Up]=true; //up
			up=true;
			System.out.println("enter things...............up");
			count++;
		}
		if((rowNumber+1<=11) && c.checkWall(rowNumber+1, colNumber, cellObj)==false){
			count++;
			System.out.println("enter things...............down");
			direction[Down]=true; //down
			down=true;
		}
		if((colNumber-1>=0) && c.checkWall(rowNumber, colNumber-1, cellObj)==false){
			count++;
			System.out.println("enter things...............left");
			direction[Left]=true; //left
			left=true;
		}
		if((colNumber+1<=11)&& c.checkWall(rowNumber, colNumber+1, cellObj)==false){
			count++;
			System.out.println("enter things...............right");
			direction[Right]=true; //right
			right=true;
		}	
	}
	
	/*
	 * Created on 24th
	 * @ author :Anuj
	 */
	/*void getnextDirection(int height,int width ){
		boolean up,down,left,right;
		rowNumber =((positionY-10)/height);
		colNumber =((positionX-10)/width);
		System.out.println("Anuj Anuj Anuj Anuj Anuj Anuj Anuj Anuj Anuj Anuj Anuj Anuj Anuj Anuj Anuj Anuj Anuj Anuj Anuj Anuj Anuj Anuj Anuj Anuj Anuj Anuj Anuj Anuj Anuj Anuj Anuj Anuj Anuj Anuj Anuj Anuj Anuj Anuj Anuj Anuj Anuj "+rowNumber+" "+colNumber);
		
		count=0;
		for(int i=0;i<4;i++){
			direction[i]=false;
		}
		System.out.println("checking wall for row = "+rowNumber+" ,col= "+colNumber				);
		
		if((rowNumber-1)>=0 && c.checkWall(rowNumber-1, colNumber, cellObj)==false){
			direction[Up]=true; //up
			up=true;
			System.out.println("enter things...............up");
			count++;
		}
		if((rowNumber+1<=11) && c.checkWall(rowNumber+1, colNumber, cellObj)==false){
			count++;
			System.out.println("enter things...............down");
			direction[Down]=true; //down
			down=true;
		}
		if((colNumber-1>=0) && c.checkWall(rowNumber, colNumber-1, cellObj)==false){
			count++;
			System.out.println("enter things...............left");
			direction[Left]=true; //left
			left=true;
		}
		if((colNumber+1<=11)&& c.checkWall(rowNumber, colNumber+1, cellObj)==false){
			count++;
			System.out.println("enter things...............right");
			direction[Right]=true; //right
			right=true;
		}	
	}*/
	
	
	
	
	//move the ghost by obtaining the next position
	void moveGhost(int width,int height){
		int index;
		int counter=0;      
        int i=0;
        this.width=width;
        this.height=height;
        
        //obtain an empty direction randomly
        getRandomEmptyDirection( height, width);

        index = (int) ((Math.random()*10)%count);
        	
		for(i=0;i<4;i++){
			
			//decide the direction of ghost if and only if direction(up,left,right,down) is true 
			//and counter should match with random direction
			if(direction[i]==true && counter == index){
				break;
			}else if(direction[i]==true && counter != index){
				counter++;
			}
		}

		if(i==Up)	System.out.println("move ghost up!!!");
		if(i==Down)	System.out.println("move ghost Down!!!");
		if(i==Left)	System.out.println("move ghost Left!!!");
		if(i==Right)System.out.println("move ghost Right!!!");

		rowNumber =((positionY-10)/height);
		colNumber =((positionX-10)/width);
		//unsetting ghost attribute to false of current row and col
		c.unSetGhost(rowNumber, colNumber, cellObj);
		System.out.println("rownumber  "+rowNumber+" colNumber "+colNumber);
         
		//set the new Position variables for the pacman
		setNewGhostPosition(i);// i tells the direction
		//setting ghost attribute of cell for new column number and rownumber
		c.setGhost(rowNumber, colNumber, cellObj);
		System.out.println("New rownumber  "+rowNumber+" New colNumber "+colNumber);
		System.out.println("Conditionnnnnnnnnnn ="+c.checkWall(rowNumber,colNumber,cellObj));
		/*if(c.checkPacman(rowNumber, colNumber, cellObj)==true){
			
            
            c.callMessageBox(d, g);
            //destroyGhost();
            //g.clearRect(x, y, width, height)
            ms.repaint();
		}*/
	}

	//set the new Position variables for the pacman
	private void setNewGhostPosition(int direction) {
		if(direction==Left)
			colNumber=colNumber-1;
		if(direction==Right)	
			colNumber=colNumber+1;
		if(direction==Up)
			rowNumber=rowNumber-1;
		if(direction==Down)
			rowNumber=rowNumber+1;
		
		positionX=(colNumber*width)+10;
		positionY=(rowNumber*height)+10;
	}

	//destroy the thread for ghost
	public void destroyGhost(){
		t.stop();
	}

	public void run() {
		try{
 			while(true){
 			t.sleep(250);
 			System.out.println("running ghost... ");
 			//move the ghost
 			moveGhost( 35,35);
 			ms.repaint();
			}
		}catch (Exception e) {
			System.out.println("ghost exception !!! e="+e.getMessage());
			e.printStackTrace();
		}
	}
}