package battleshipgamefiles;

import java.util.*;
import java.util.regex.*;

public class BattleshipGame {

	/**
	 * This will take the raw move from the input. First it will separate it into
	 * strings by separating at ";" Then a regex will attempt to find two distinct
	 * numbers. If there are more or less than two numbers present return false
	 * otherwise take the shots
	 * 
	 * @param rawString
	 * @return
	 */
	public static boolean move(String rawString, Ocean ocean) {
		String[] separatedString = rawString.split(";");
		int[][] shots = new int[5][2];
		int shotNumber = 0;

		if (separatedString.length != 5) {
			System.out.println("Please input 5 coordiante pairs, separated by semicolons (;)");
			return false;
		}

		Pattern n = Pattern.compile("([0-9]+)");
		for (String s : separatedString) {
			Matcher numbers = n.matcher(s);
			int coordinantCount = 0;
			while (numbers.find()) {
				int coordinant = Integer.parseInt(numbers.group(1));
				if (coordinant < 0 || coordinant > 19 || coordinantCount == 2) {
					System.out.println(
							"There was a problem with your coordinates.  Make sure you know what you're doing.");
					return false;
				}
				shots[shotNumber][coordinantCount] = coordinant;
				coordinantCount++;
			}
			if (coordinantCount == 1) {
				System.out
						.println("There was a problem with your coordinants.  Make sure you know what you are doing.");
				return false;
			}
			shotNumber++;
		}

		for (int[] shot : shots) {
			ocean.shootAt(shot[0], shot[1]);
		}

		return true;
	}

	public static void main(String[] args) {
		Ocean ocean = new Ocean();
		Scanner scanner = new Scanner(System.in);
		boolean gameOver = false;

		ocean.placeAllShipsRandomly();

		while (gameOver == false) {
			boolean validInput = false;
			//uncomment to provide a 'cheat sheet' for debugging purposes
			//ocean.printState(); 
			ocean.print();
			
			System.out.println(
					"You have sunk " + ocean.getShipsSunk() + " out of " + ocean.getToPlace().length + " ships.");
			while (!validInput) {
				System.out.println(
						"Please enter your next move.  Supply five coordinate pairs, separated by semicolons.");
				System.out.println("Pairs should be row first, then column.");
				String attempt = scanner.next();
				validInput = move(attempt, ocean);
			}
			scanner.close();
			gameOver = ocean.isGameOver();
		}

	}

}
