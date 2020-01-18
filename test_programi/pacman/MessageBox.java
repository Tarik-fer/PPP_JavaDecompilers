package Pacman9;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class MessageBox {
	
	int score;
	int life;
	String status="";
	String over="Game Over";
	
	public MessageBox(){
		
	}
		
	//display the score in message-Box
	public void displayMessage(Graphics g ){
		g.setFont(new Font("arial",Font.BOLD,15));
		g.setColor(Color.BLACK);
		g.fillRect(470,30,160,70);
		g.setColor(Color.YELLOW);
		g.drawString("Score:  "+score,480,50);
		g.drawString("Status:"+status,480,70);
	//	g.drawString("Life:  "+life,480,90);
	}
	
	//set the score of message box
	public void setScore(int score,int life){
		this.score = score;
		this.life = life;
	}
	
	//sets the status of messageBox
	public void setStatus(String status){
		this.status = status;
	}

	//called when pacman GameOvers
	public void gameOver(Graphics g){		
		g.setFont(new Font("Trebuchet MS",Font.BOLD,20));
		g.setColor(Color.BLACK);
		g.fillRect(10,10,250,200);
		g.setColor(Color.YELLOW);
		g.drawString("Your Score is :  "+score,30,80);
		g.drawString("Game Over",30,120);		
	}
	
}