package project_2;

import java.awt.Color;
import java.io.FileNotFoundException;

public class Animator {

	// setting the screen of my window
	static final int MAX_SCREEN_X = 700;
	static final int MAX_SCREEN_Y = 700;
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
	
	// initializing the screen
	EZ.initialize(MAX_SCREEN_X, MAX_SCREEN_Y);
	// setting background
	EZ.addImage("leagueBackground.jpg", 350, 350);
	
	// making a new actor called gnar	
	Actor gnar = new Actor("gnar.png", "gnar.wav", 250,250);
	// making a new actor called alistar
	Actor alistar = new Actor("alistar.png", "alistar.wav", 150, 350);
	// making a new actor called jayce
	Actor jayce = new Actor("jayce.png", "jayce.wav", 400, 600);
	
	// the openText function that allows the actors gnar, alistar, and jayce to move
	gnar.openText("gnar.txt");
	alistar.openText("alistar.txt");
	jayce.openText("jayce.txt");
	}
}
