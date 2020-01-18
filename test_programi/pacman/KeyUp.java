package Pacman9;

class KeyUp extends Key {
	//sets the new position of pacman on click of up arrow key
	 public void setPacmanNewPosition(int positionX,int positionY,Pacman p){
			System.out.println("in keyup");
//			each cell has height of 35 as size, new position will- jus change in position Y
			posX=positionX;
			posY=positionY-35;
			System.out.println("posx"+posX+"posy"+posY);
			p.setPosition(posX,posY);
		}
	 
	 // sets new cell position of pacman
	 public void newPosition(int oldRowNumber,int oldColNumber ,Pacman p){
	      	 rowNumber =oldRowNumber-1;
	      	 colNumber =oldColNumber;
	      	 //new cell position of pacman
	      	 p.setCellNumber(rowNumber, colNumber);
	 }
}