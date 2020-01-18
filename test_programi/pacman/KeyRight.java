package Pacman9;


public class KeyRight extends Key {
	
	@Override
	//sets the new position of pacman on click of up arrow right
	public void setPacmanNewPosition(int positionX, int positionY, Pacman p) {
		
		//	each cell has width of 35 as size, new position will- jus change in position X
		System.out.println("in keyright");
		 posX=positionX+35;
			posY=positionY;
			System.out.println("posx"+posX+"posy"+posY);
			p.setPosition(posX,posY);
			
	}
	//	new cell position of pacman
	 public void newPosition(int oldRowNumber,int oldColNumber ,Pacman p){
      	 rowNumber =oldRowNumber;
      	 colNumber =oldColNumber+1;
      	 //sets the	new cell position of pacman
      	 p.setCellNumber(rowNumber, colNumber);
 }
}
