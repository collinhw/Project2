package project_2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Actor {
	// member variables
	EZImage image;
	EZSound sound;
	int x;
	int y;
	int startX;
	int startY;
	int destX;
	int destY;
	long duration;
	long startTime;
	boolean interpolation;
	double startDegrees;
	
	// constructor
	Actor(String imageFile, String soundFile, int x, int y) {
		image = EZ.addImage(imageFile, x, y);
		sound = EZ.addSound(soundFile);
		image.scaleTo(.4);
		interpolation = false;
	}
	 // boolean for interpolation
	 public boolean moving(){
		 return interpolation;
	 }
	 // member function for moveTo
	 public void moveTo(int posX, int posY, long duration) {
		 destX = posX;
		 destY = posY;
		 duration = duration*1000;
		 startTime = System.currentTimeMillis();
		 startX = image.getXCenter();
		 startY = image.getYCenter();
		 interpolation = true;
		 
		 while (interpolation == true){
			// Normalize the time
			 float normTime = (float) (System.currentTimeMillis() - startTime)/ (float) duration;
			// Calculate the interpolated x and y position of the actor
			x = (int) (startX + ((float) (destX - startX) *  normTime));
			y = (int) (startY + ((float) (destY - startY) *  normTime));
				// ends the interpolation
				if((System.currentTimeMillis() - startTime) >= duration) {
					interpolation = false;
					x = destX;
					y = destY;
			}
				image.translateTo(x,y);
				EZ.refreshScreen();
		 }
	 }
	// member function for turnTo
	 public void turnTo(int rotationAngle, long duration) {
		startDegrees = image.getRotation();
		startTime = System.currentTimeMillis();
		int degrees;
		int finalDegrees = rotationAngle;
		duration = duration*1000;
		interpolation = true;
		 while (interpolation == true){
			// Normalize the time
			 float normTime = (float) (System.currentTimeMillis() - startTime)/ (float) duration;
			// Calculate the angle of the actor
			degrees = (int) (startDegrees + ((float) (finalDegrees - startDegrees) *  normTime));
			// ends the interpolation
				if((System.currentTimeMillis() - startTime) >= duration) {
					interpolation = false;
					degrees = finalDegrees;		
		}
				image.rotateTo(degrees);
				EZ.refreshScreen();
	}	 
}
	// member function for open script file 
	 public void openText(String textFile) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileReader(textFile));
		// variables that are in each of my actor's scripts
		String action;
		int scriptX;
		int scriptY;
		int scriptDuration;
		int scriptDegrees;
	
		// while this function has a next line, the scanner will read it
		while(sc.hasNext()) {
			// reads the action whether it is moveTo, playTo, or play sound
			action = sc.next();
			// if the action is read as moveTo it will do this
			if(action.equals("moveTo")) {
				scriptX = sc.nextInt();
				scriptY = sc.nextInt();
				scriptDuration = sc.nextInt();	
				// moves the function to (X,Y) position in a certain duration
				moveTo(scriptX, scriptY, scriptDuration);
			}
			// if the action is read as turnTo it will do this
			else if(action.equals("turnTo")) {
				scriptDegrees = sc.nextInt();
				scriptDuration = sc.nextInt();
				// roates the function in a certain duration
				turnTo(scriptDegrees, scriptDuration);	
			}
			// if the action is read as play, it will play the sound file
			else if (action.equals("play")) {
				play();
			}
		}
		sc.close();
	 }
	 // member function to play sound
	 public void play() {
		 sound.play();
	 }
}

