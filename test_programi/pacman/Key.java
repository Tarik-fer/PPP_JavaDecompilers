package Pacman9;
//main key class which is been extended by keyup,keydown,keyleft,keydown
public abstract class Key {
	protected int posX,posY;
	protected int rowNumber,colNumber;
	public abstract void setPacmanNewPosition(int positionX,int positionY,Pacman p);
	 public abstract void newPosition(int oldRowNumber,int oldColNumber ,Pacman p); 
	/*public void move(int code){
		if(code==38){
			KeyUp kUp=new KeyUp();
			kUp.moveUp();
		}
		else if(code==39){
			KeyRight kRt=new KeyRight();
			kRt.moveRight();
		}
		else if(code == 40){
			KeyDown kDn=new KeyDown();
			kDn.moveDown();
		}
		else if(code==37){
			KeyLeft kLf=new KeyLeft();
			kLf.moveLeft();
		}
		
	}
	*/
	
}
