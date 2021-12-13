package SokobanGame;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Basic Sokoban Game
 * 
 * Deliver the boxes to the targets and win the game
 * In playground;
 * W (Walls),
 * T (Targets),
 * B (Boxes) and
 * C represent the (Character) 
 * 
 * Before starting, you can change the values defined in the class variable
 * above the main method.
 * These are recommended values so things are not complicated :)
 * It would also better to write a function (maybe settingsGame())
 * but I think it's easier that way
 * 
 * There may be bugs in some places.(Don't enter anything other than the integer
 * value from the keyboard when starting the game :)) 
 * 
 * As I said, a simple code and algorithm, some functions could be written.
 * The good thing is that each function does its own thing.
 * I tried to make the function and variable names as descriptive as possible.
 * 
 * Finally
 * Enjoyable games, of course, if this is possible.
 * 
 * */

public class Sokoban {
	
	static String[][] labyrinth = new String[20][20];

	static int numberOfInnerWall = 70;
	
	static int totalNumberOfStep;
	
	static int totalNumberOfBox = 2;
	static int boxCounter;
	
	static int totalNumberOfTarget = 2;
	static int targetCounter;
	
	public static void main(String[] args) {
		
		createOuterWall();
		createRandomWallInArea(); 
		createRandomTargetPoint();
		createRandomBox();
		createCharacter();
		gameMenu();
	
	}
	
	static void createOuterWall() {
		
		for(int x = 0; x < labyrinth.length ; x++ ) {
			
			for (int y = 0; y < labyrinth[x].length ; y++) {
				
				if(x == 0 || x == labyrinth.length-1) {
				
					labyrinth[x][y] = "W";
				
				}else if(x > 0 ) {
		
					if(y == 0 || y == labyrinth.length-1) { 
		
						labyrinth[x][y] = "W";
					
					}
		
					else {
					
						labyrinth[x][y] = " ";
					
					}
		
				}
		
			}
		
		}
		
	}
	
	static void createRandomWallInArea() {
		
		Random xCoordinate = new Random();
		Random yCoordinate = new Random();
		
		for(int i = 0; i < numberOfInnerWall; i++ ) {

			int xCoordinateOfWall = xCoordinate.nextInt(19)+1;
			int yCoordinateOfWall = yCoordinate.nextInt(19)+1;

			labyrinth[xCoordinateOfWall][yCoordinateOfWall] = "W"; 
			
		}
		
	}
 	
	static void createRandomTargetPoint() {
		
		Random xCoordinate = new Random();
		Random yCoordinate = new Random(); 
		
		while(true) {
			
			int xCoordinateOfTarget = xCoordinate.nextInt(19)+1;
			int yCoordinateOfTarget = yCoordinate.nextInt(19)+1;
			
			if(labyrinth[xCoordinateOfTarget][yCoordinateOfTarget] == " " && labyrinth[xCoordinateOfTarget][yCoordinateOfTarget] != "T") {
				
				labyrinth[xCoordinateOfTarget][yCoordinateOfTarget] = "T";
				
				targetCounter++;
				
				if(targetCounter == totalNumberOfTarget) {
					
					break;
				
				}
			
			}
			
		}
		
	}
	
	static void createRandomBox() {
		
		Random xCoordinate = new Random();
		Random yCoordinate = new Random();
	
		while(true) {
			
			int xCoordinateOfBox = xCoordinate.nextInt(18)+2;
			int yCoordinateOfBox = yCoordinate.nextInt(18)+2;
			
			if(labyrinth[xCoordinateOfBox][yCoordinateOfBox] == " " && labyrinth[xCoordinateOfBox][yCoordinateOfBox] != "B" && labyrinth[xCoordinateOfBox-1][yCoordinateOfBox] != "W" && labyrinth[xCoordinateOfBox+1][yCoordinateOfBox] != "W" && labyrinth[xCoordinateOfBox][yCoordinateOfBox-1] != "W" && labyrinth[xCoordinateOfBox][yCoordinateOfBox+1] != "W") {
				
				labyrinth[xCoordinateOfBox][yCoordinateOfBox] = "B";
				
				boxCounter++;
				
				if(boxCounter == totalNumberOfBox) {
					
					break;
				
				}
				
			}
			
		}
		
	}
		
	static void gameMenu() {
		
		System.out.println("Press 1 to start the game.");
		
		int choose;
		 
		
		while(true) {
			
			Scanner scannerObject = new Scanner(System.in);
			
			try {
				choose = scannerObject.nextInt();
				
				if(choose == 1) {
					
					playGame();
					break;
					
				}else {
					
					System.out.println("Wrong choose, try again.");
					
				}
				
			}catch (InputMismatchException e) {
				System.out.println("I said don't press it, I didn't say the program would explode :) Please press 1.");
			}
			 
		}
		
		System.out.println("Congratulations.");
			
	}

	static void createCharacter() {
		
		Random xCoordinate = new Random();
		Random yCoordinate = new Random();
		
		
		while(true) {
				
				int xCoordinateOfCharacter = xCoordinate.nextInt(19)+1;
				int yCoordinateOfCharacter = yCoordinate.nextInt(19)+1;
				
				if(labyrinth[xCoordinateOfCharacter][yCoordinateOfCharacter] == " " ) {
					
					labyrinth[xCoordinateOfCharacter][yCoordinateOfCharacter] = "C"; 
					break;
				
				}
				
			}
		
		 
	
	}
	
	static int[] findCharacterCoordinate() {
		
		int[] characterCoordinate = new int[2];
				
		for(int x = 0; x < labyrinth.length; x++) {
		
			for(int y = 0; y < labyrinth[x].length; y++) {
		
				if(labyrinth[x][y] == "C") {
					
					characterCoordinate[0] = x;
					characterCoordinate[1] = y;
					break;
					
				}
		
			}
		
		}
		
		return characterCoordinate;
		
	}
	
	static void playGame() {
		
		int[] characterCoordinate = new int[2];
		
		System.out.println("Use the w, a, s, d keys to move the character.");
		System.out.println("w Up, s Down, d Right, a Left");
		
		printGameArea();
		
		Scanner movementObject = new Scanner(System.in);
		
		while(true) {
			
			characterCoordinate = findCharacterCoordinate(); 
			
			char directionMovement = movementObject.next().charAt(0);	 	
	
			switch(directionMovement) {
			
			case 'w':goUp(characterCoordinate[0], characterCoordinate[1]);break;
			case 's':goDown(characterCoordinate[0], characterCoordinate[1]);break;
			case 'd':goRight(characterCoordinate[0], characterCoordinate[1]);break;
			case 'a':goLeft(characterCoordinate[0], characterCoordinate[1]);break;
			default:System.out.println("Wrong keystroke, try again.");break;
	
			}
			
			printGameArea();
			
			if(numberOfBoxesReachingTheTarget()) {
	
				break;
	
			}
			
		}
		
	}
 
	static void goUp(int x, int y) {
		
		if(labyrinth[x-1][y] == "B" && labyrinth[x-2][y] == "T") {
			
			labyrinth[x-2][y] = " ";
			labyrinth[x-1][y] = "C";
			labyrinth[x][y] = " ";
			
			System.out.println("You delivered the box to the destination.");
			
			totalNumberOfStep++;
			printTotalNumberOfStep();
			
			totalNumberOfBox--;
			totalNumberOfTarget--;
						
		}else if(labyrinth[x-1][y] == "B" && labyrinth[x-2][y] == "W") {
			
			System.out.println("You can't move forward, there is a wall in front of the box.");
			
		}else if(labyrinth[x-1][y] == "W") {
			
			System.out.println("There's a wall in it, you can't move forward.");
			
		}else if(labyrinth[x-1][y] == "T") {
		
			System.out.println("You didn't deliver the box to the destination.");
			
		}else if(labyrinth[x-1][y] == "B" && labyrinth[x-2][y] == "B") {
		
			System.out.println("You can't push 2 boxes at the same time.");
			
		}else if(labyrinth[x-1][y] == " ") {
			
			labyrinth[x-1][y] = "C";
			labyrinth[x][y] = " ";
			
			System.out.println("You took a step up.");
			
			totalNumberOfStep++;
			printTotalNumberOfStep();
	
		}else if(labyrinth[x-1][y] == "B" && labyrinth[x-2][y] == " ") {
			
			labyrinth[x-2][y] = "B";
			labyrinth[x-1][y] = "C";
			labyrinth[x][y] = " ";
			
			System.out.println("Move the box one step to the up.");
			
			totalNumberOfStep++;
			printTotalNumberOfStep();
				
		}
		
	}
	
	static void goDown(int x, int y) {
		
		if(labyrinth[x+1][y] == "B" && labyrinth[x+2][y] == "T") {
			
			labyrinth[x+2][y] = " ";
			labyrinth[x+1][y] = "C";
			labyrinth[x][y] = " ";
			
			System.out.println("You delivered the box to the destination.");
			
			totalNumberOfStep++;
			printTotalNumberOfStep();
			
			totalNumberOfBox--;
			totalNumberOfTarget--;
						
		}else if(labyrinth[x+1][y] == "B" && labyrinth[x+2][y] == "W") {
			
			System.out.println("You can't move forward, there is a wall in front of the box.");
			
		}else if(labyrinth[x+1][y] == "W") {
			
			System.out.println("There's a wall in it, you can't move forward.");
			
		}else if(labyrinth[x+1][y] == "T") {
		
			System.out.println("You didn't deliver the box to the destination.");
			
		}else if(labyrinth[x+1][y] == "B" && labyrinth[x+2][y] == "B") {
		
			System.out.println("You can't push 2 boxes at the same time.");
			
		}else if(labyrinth[x+1][y] == " ") {
			
			labyrinth[x+1][y] = "C";
			labyrinth[x][y] = " ";
			
			System.out.println("You took a step down."); 
			
			totalNumberOfStep++;
			printTotalNumberOfStep();
	
		}else if(labyrinth[x+1][y] == "B" && labyrinth[x+2][y] == " ") {
			
			labyrinth[x+2][y] = "B";
			labyrinth[x+1][y] = "C";
			labyrinth[x][y] = " ";
			 
			System.out.println("Move the box one step to the down.");
			 
			totalNumberOfStep++;
			printTotalNumberOfStep();
			
		}
		
	}
	
	static void goRight(int x, int y) {
		
		if(labyrinth[x][y+1] == "B" && labyrinth[x][y+2] == "T") {
			
			labyrinth[x][y+2] = " ";
			labyrinth[x][y+1] = "C";
			labyrinth[x][y] = " ";
		
			System.out.println("You delivered the box to the destination.");

			totalNumberOfStep++;
			printTotalNumberOfStep();
			
			totalNumberOfBox--;
			totalNumberOfTarget--;
			
		}else if(labyrinth[x][y+1] == "B" && labyrinth[x][y+2] == "W") {
			
			System.out.println("You can't move forward, there is a wall in front of the box.");
			
		}else if(labyrinth[x][y+1] == "W") {
			
			System.out.println("There's a wall in it, you can't move forward.");
			
		}else if(labyrinth[x][y+1] == "T") {
		
			System.out.println("You didn't deliver the box to the destination.");
			
		}else if(labyrinth[x][y+1] == "B" && labyrinth[x][y+2] == "B") {
		
			System.out.println("You can't push 2 boxes at the same time.");
			
		}else if(labyrinth[x][y+1] == " ") {
			
			labyrinth[x][y+1] = "C";
			labyrinth[x][y] = " ";
	
			System.out.println("You took a step right.");
	
			totalNumberOfStep++;
			printTotalNumberOfStep();
	
		}else if(labyrinth[x][y+1] == "B" && labyrinth[x][y+2] == " ") {
			
			labyrinth[x][y+2] = "B";
			labyrinth[x][y+1] = "C";
			labyrinth[x][y] = " ";
			
			System.out.println("Move the box one step to the right.");
			
			totalNumberOfStep++;
			printTotalNumberOfStep();
			
		}
		
	}
	
	static void goLeft(int x, int y) {
		
		if(labyrinth[x][y-1] == "B" && labyrinth[x][y-2] == "T") {
			
			labyrinth[x][y-2] = " ";
			labyrinth[x][y-1] = "C";
			labyrinth[x][y] = " ";
			
			System.out.println("You delivered the box to the destination.");
			
			totalNumberOfStep++;
			printTotalNumberOfStep();
			
			totalNumberOfBox--;
			totalNumberOfTarget--;
			
		}else if(labyrinth[x][y-1] == "B" && labyrinth[x][y-2] == "W") {
			
			System.out.println("You can't move forward, there is a wall in front of the box.");
			
		}else if(labyrinth[x][y-1] == "W") {
			
			System.out.println("There's a wall in it, you can't move forward.");
			
		}else if(labyrinth[x][y-1] == "T") {
		
			System.out.println("You didn't deliver the box to the destination.");
			
		}else if(labyrinth[x][y-1] == "B" && labyrinth[x][y-2] == "B") {
		
			System.out.println("You can't push 2 boxes at the same time.");
			
		}else if(labyrinth[x][y-1] == " ") {
			
			labyrinth[x][y-1] = "C";
			labyrinth[x][y] = " ";
			
			System.out.println("You took a step left.");
	
			totalNumberOfStep++;
			printTotalNumberOfStep();
	
		}else if(labyrinth[x][y-1] == "B" && labyrinth[x][y-2] == " ") {
			
			labyrinth[x][y-2] = "B";
			labyrinth[x][y-1] = "C";
			labyrinth[x][y] = " ";
			
			System.out.println("Move the box one step to the left.");
			
			totalNumberOfStep++;
			printTotalNumberOfStep();
			
		}
		
		
	}
	
	static boolean numberOfBoxesReachingTheTarget() {
		
		
		if(totalNumberOfBox == 0 && totalNumberOfTarget == 0) {
			
			System.out.println("You delivered the all box to the destination.");
			
			return true;
			
		}else {
			
			System.out.println(totalNumberOfBox + " boxes and " + totalNumberOfTarget + " targets left.");
			
			return false;
		}
	
		
	}
	
	static void printTotalNumberOfStep() {
		
		System.out.println("Total number of steps taken: " + totalNumberOfStep);
		
	}
		
	static void printGameArea() {

		for(int x = 0; x < labyrinth.length ; x++ ) {
			
			for (int y = 0; y < labyrinth[x].length ; y++) {
				
				System.out.print(labyrinth[x][y]);
			
			}
			
			System.out.println("");
		
		}
		
	}

}

















