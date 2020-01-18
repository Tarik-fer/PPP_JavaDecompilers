package Pacman9;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends Applet implements ActionListener
{
	AudioClip backgroundMusic;
	AudioClip pacDieMusic;
	AudioClip levelCompleteMusic;
	AudioClip eatBonusMusic;
	
	String msg="";
	Graphics bufferGraphics;
	Button playButton,quitButton;
	Controller co;
	DashBoard db;
	boolean play,quit;
	Graphics graphic;
	int height=0, width=0, row, col;
	public Image wallImage,pacmanImage,ghostImage,bonusImage;
	int count=0;
    Dimension dim;
    Image offScreen;
    int curX, curY;
	//called to start the game
	public void playGame(){
		play=true;
		playButton.setVisible(false);
		quitButton.setVisible(false);		
	}
	
	//init method of Applet
	public void init()
	{
	
		System.out.println(getCodeBase());
		backgroundMusic = getAudioClip( getCodeBase() , "C:\\Pacman\\back.au" );
		pacDieMusic = getAudioClip( getCodeBase() , "C:\\Pacman\\pacdie.au" );
		levelCompleteMusic = getAudioClip( getCodeBase() , "C:\\Pacman\\levcompl.au" );
		eatBonusMusic = getAudioClip( getCodeBase() , "C:\\Pacman\\eatBonus.au" );
			
		height = 35;
		width = 35;
		row = 13;
		col = 13;
		co = new Controller();
		dim = getSize(); 
		
		
		playButton = new Button("Play");
		quitButton = new Button("Quit");

		System.out.println("code base  = "+getCodeBase());
		wallImage = getImage(getCodeBase(),"C:\\Pacman\\wall.jpg");
		pacmanImage = getImage(getCodeBase(),"C:\\Pacman\\pacman.jpg");
		ghostImage=getImage(getCodeBase(), "C:\\Pacman\\ghost.jpg");
		bonusImage = getImage(getCodeBase(),"C:\\Pacman\\bonus.jpg");
		
		//call the dashBoard constructor of the DashBoard Class
		db = new DashBoard(height, width, row, col,co,pacmanImage,ghostImage,this,eatBonusMusic);
		
		//set the size of the applet
		setSize(700, 700);
		offScreen = createImage(700,700); 
		bufferGraphics = offScreen.getGraphics(); 
		
		// add play and quit button to the applet
		add(playButton);
		add(quitButton);
		
		playButton.addActionListener(this);
		quitButton.addActionListener(this);
		
		// add a key listener to the dashBoard
		this.addKeyListener(db.dashBoard_KeyListener);
	}
	
	public void displayValue(){
	}
	
	//listener for applet
	public void actionPerformed(ActionEvent ae){
		
		String str = ae.getActionCommand();

		//call the playGame function when play button is pressed		
		if(str.equals("Play")){
			msg = "You have selected the Play Button";
			backgroundMusic.loop();
			playGame();
		}
		
		else{
		//call the quitGame method to quit
			msg = "You want to Quit";
			quitGame();
		}
		repaint();
	}
	public void start()
	{
	}
	
	//called when game is to be quitted
	public void quitGame(){
		System.exit(0);
	}
	//applets paint method
	public void paint(Graphics g){
		if(play==true){
			bufferGraphics.clearRect(0,0,700,700); 
			//call the dashBoard constructor of dashBoard class
			co.displayDashBoard(db,bufferGraphics,wallImage,pacmanImage,ghostImage,bonusImage,co, backgroundMusic, pacDieMusic,levelCompleteMusic);
			count++;
			   g.drawImage(offScreen,0,0,this); 
		}
	}
	  public void update(Graphics g)
	     {
	          paint(g);
	     } 
}