package Pacman9;


public class PacmanGenerator {

	//generate new object of pacman as well as set its position in its onstructor	
	public Pacman createPacman(int posX,int posY) {	
		System.out.println("inside createPacman of pac generator x="+posX+",y= "+posY);
		return new Pacman(posX,posY);		
	}

}
