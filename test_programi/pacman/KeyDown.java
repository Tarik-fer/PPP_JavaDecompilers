package Pacman9;

public class KeyDown extends Key{

	void moveDown(){
	
	}

	@Override
	
	public void setPacmanNewPosition(int positionX, int positionY, Pacman p) {
		// TODO Auto-generated method stub
		System.out.println("in keyDown");
	//each cell has height of 35 as size, new position will jus change in position Y 
		posX=positionX;
			posY=positionY+35;
			System.out.println("posx"+posX+"posy"+posY);
			p.setPosition(posX,posY);		
	}
	
	public void newPosition(int oldRowNumber,int oldColNumber ,Pacman p){
      	 rowNumber =oldRowNumber+1;
      	 colNumber =oldColNumber;
//      	new cell position of pacman
      	 p.setCellNumber(rowNumber, colNumber);
 }
}
