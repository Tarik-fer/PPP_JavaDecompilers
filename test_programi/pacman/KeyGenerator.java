package Pacman9;



public class KeyGenerator {
	public int keyPressedCounter=0;
	
	KeyGenerator(){
		keyPressedCounter=0;
	}
	
	// creates a Key object
	public Key createKey(int code){
		Key keyPressed = null;
		System.out.println("in createkey"+code);
		//increment the key pressed counter , to keep track  of first ket pressed
		keyPressedCounter++;
		
		//create a Key object depending on the key pressed
		if(code==38){
			keyPressed=new KeyUp();
			//kUp.moveUp();
		}
		else if(code==39){
			keyPressed=new KeyRight();
			//kRt.moveRight();
		}
		else if(code == 40){
			keyPressed=new KeyDown();
			//kDn.moveDown();
		}
		else if(code==37){
			keyPressed=new KeyLeft();
			//kLf.moveLeft();
		}
		return keyPressed;
		
	}

}
