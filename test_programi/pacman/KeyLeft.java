package Pacman9;


public class KeyLeft extends Key{
	

	@Override
//	sets the new position of pacman on click of up arrow right
	public void setPacmanNewPosition(int positionX, int positionY, Pacman p) {
	
		//each cell has width of 35 as size, new position will- jus change in position X
		System.out.println("in keyLEFT");
		 posX=positionX-35;
			posY=positionY;
			System.out.println("posx"+posX+"posy"+posY);
			p.setPosition(posX,posY);
	}
	
	//	new cell position of pacman
	 public void newPosition(int oldRowNumber,int oldColNumber ,Pacman p){
      	 rowNumber =oldRowNumber;
      	 colNumber =oldColNumber-1;
      	 //	new cell position of pacman
      	 p.setCellNumber(rowNumber, colNumber);
 }
	 
}
