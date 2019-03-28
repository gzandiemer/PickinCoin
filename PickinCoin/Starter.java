package PickinCoin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Random;

public class Starter {

	public static void main(String[] args) throws IOException {
		
		//  About game: 
		//  Coin picking game
		//  Human Brain against Algorithms!
		//  Human decides the number of coins on the table, max and min number of coins that the players can pick each time.
		//  Computer starts.
		//  Who picks the last coin, wins the game.
		  
		
		int move_PC;
		int move_Human;
		
		boolean gameOver = false;
		
		int gameSettings[] = gameSettings();
		
		int nCoins = gameSettings[0];
		int coinPickMin = gameSettings[1];
		int coinPickMax = gameSettings[2];
		
		int moduloBreakpointNumber = coinPickMax + coinPickMin;
		
		int coinsLeft = nCoins;	
		
		while (true) {
						
			String winner;
			
			if ( (coinsLeft % moduloBreakpointNumber) > coinPickMax ) {
				move_PC = coinPickMax;
			}
			
			else if ( (coinsLeft % moduloBreakpointNumber) < coinPickMin ) {
				move_PC = coinPickMin;
			} 
			
			else {
				move_PC = ( coinsLeft % moduloBreakpointNumber );
			}
			
			System.out.println("I take: " + move_PC + " coins. " + (coinsLeft - move_PC) + " coins remained.");
			
			// update coinsLeft 
			coinsLeft = coinsLeft - move_PC;
			
			if (checkForWin(coinsLeft, coinPickMin, coinPickMax, winner = "Algorithms") == true) {
				System.err.println("Game Over!");
				break;
			}
			
			// open reader to catch readLines for game settings
			BufferedReader playerHumanFirstInputReader = new BufferedReader(new InputStreamReader(System.in));
						
			// playerHuman input			
			System.out.println("Please write how many coins you pick between " + coinPickMin + " and " + coinPickMax + " :");
						
		    //check for empty string
			String playerHumanPickInput = checkForEmptyString(playerHumanFirstInputReader.readLine());
						
			// checkForLegitInt
			move_Human = checkForLegitInput(Integer.parseInt(playerHumanPickInput), coinPickMin, coinPickMax);
			System.out.println("You took: " + move_Human+ " coins. " + (coinsLeft - move_Human) + " coins remained.");			
						
			// update coinsLeft 
			coinsLeft = coinsLeft - move_Human;
						
			// check for winner			
			if (checkForWin(coinsLeft, coinPickMin, coinPickMax, winner = "Human") == true) {
				System.err.println("Congrats!");
				break;
			}
			
		}//while loop
			
	}//public static void
		
		

private static int[] gameSettings() throws NumberFormatException, IOException {
	
	int nCoins;
	int coinPickMin;
	int coinPickMax;
	// input for game settings
	
	// open reader to catch readLines for game settings
	BufferedReader coinReader = new BufferedReader(new InputStreamReader(System.in));
	
	// input nCoins
	System.out.println("Hello Human! I know your kind likes to feel in control. So be my guest to do the settings of this coin game.");
	System.out.println("Enter the total number of coins (min.10, max.1000): ");
	nCoins = checkForLegitInput(Integer.parseInt(checkForEmptyString(coinReader.readLine())), 10, 1000);
	System.out.println("There are " + nCoins+ " coins on the table.");

	// input maxCoinPick
	if ((nCoins / 4) == 2 || (nCoins / 4) < 2) {
		System.out.println("In this case the maximum number can only be 2.");
		coinPickMax = 2;
	} else {
	System.out.println("Enter the maximum number of coins that one can pick: (min. 2, max. " + nCoins / 4 + "): ");
	String coinPickMaxInput = checkForEmptyString(coinReader.readLine());
	coinPickMax = checkForLegitInput(Integer.parseInt(coinPickMaxInput), 2, nCoins / 4);
	System.out.println("We can pick maximum " + coinPickMax + " coins.");
	}
	// input minCoinPick
	if ((coinPickMax/2 -1) < 2 ) {
		System.out.println("In this case the minimum number can only be 1.");
		coinPickMin = 1;
	} else {
	System.out.println("Now enter the minimum number of coins that one can pick: (min. 1, max. " + ( coinPickMax / 2 - 1) + "):");
	String coinPickMinInput = checkForEmptyString(coinReader.readLine());
	coinPickMin = checkForLegitInput(Integer.parseInt(coinPickMinInput), 1, coinPickMax - 1);
	System.out.println("We can pick minimum: " + coinPickMin + " coins.");
	}

	System.out.println("Alright. You decided for the settings. Therefore I begin ;)");
	// return array for group of returns
	return new int[] {nCoins, coinPickMin, coinPickMax};
	
}

private static String checkForEmptyString(String readLine) {
	while (readLine.trim().isEmpty()) {
		System.err.println("Empty field or input with whitespaces. Please enter a plausible number: ");
		BufferedReader fixEmptyStringReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			readLine = fixEmptyStringReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//check for empty string
	return readLine;
}

  
	private static boolean checkForWin (int coinsLeft, int coinPickMax, int coinPickMin, String winner) {
		
		//int moduloBreakpointNumber = coinPickMax + coinPickMin;
		
		if (coinsLeft == 0) {
			// next player has won automatically
			System.err.println("Winner is: " + winner);
			return true;

		} else {
			return false;
		}
		
		
	} // check for win
	
	// check INT INPUT to be legit or not
	private static int checkForLegitInput (int toCheck, int minValue, int maxValue) throws IOException {
			
			while (toCheck < minValue || toCheck > maxValue) {
				System.out.println("Please enter a plausible number between " + minValue + " & " + maxValue + ":");	
				// open reader to catch new readLine
				BufferedReader fixIntReader = new BufferedReader(new InputStreamReader(System.in));
				toCheck = Integer.parseInt(checkForEmptyString(fixIntReader.readLine()));
			} 
			
			return toCheck;
		}
		
	} // public class Starter
	
