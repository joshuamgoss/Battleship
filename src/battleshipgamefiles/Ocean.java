package battleshipgamefiles;

import java.util.*;

public class Ocean {

	private Ship[][] ships = new Ship[20][20];
	private String[][] attempts = new String[20][20];
	private int shotsFired;
	private int hitCount;
	private int shipsSunk;
	// toPlace is the array containing all of the ships that need to be placed
	// place ships largest to smallest to avoid creating impossible boards
	private Ship[] toPlace = { new Battleship(), new BattleCruiser(), new Cruiser(), new Cruiser(), new LightCruiser(),
			new LightCruiser(), new Destroyer(), new Destroyer(), new Destroyer(), new Submarine(), new Submarine(),
			new Submarine() };

	Ocean() {
		shotsFired = 0;
		hitCount = 0;
		shipsSunk = 0;
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				ships[i][j] = new EmptySea();
				attempts[i][j] = ".";
			}
		}
	}

	/**
	 * Place all ships at random positions and orientations on this ocean's ships 2d
	 * array. It starts with a list of all ships from biggest to smallest and checks
	 * random spots until it finds a valid spot, then it places the ship in that
	 * spot
	 */
	public void placeAllShipsRandomly() {
		Random rand = new Random();
		// Grab random coordinates and orientation and see if they
		// will work for the current ship. Once an acceptable set
		// is found, place the ship and go on to the next ship
		for (Ship s : toPlace) {
			boolean placed = false;
			while (placed == false) {
				int row = rand.nextInt(20);
				int column = rand.nextInt(20);
				boolean horizontal = rand.nextBoolean();
				if (s.okToPlaceShipAt(row, column, horizontal, this)) {
					s.placeShipAt(row, column, horizontal, this);
					placed = true;
				}
			}
		}
	}

	/**
	 * Checks a location in the ships[][] array to see if it is occupied by
	 * something other than EmptySea
	 * 
	 * @param row
	 * @param column
	 * @return true if occupant is not EmptySea
	 */
	public boolean isOccupied(int row, int column) {
		return !this.ships[row][column].getShipType().contentEquals("Empty");
	}

	/**
	 * Checks a location. If the occupant is a ship that has not been sunk, it fires
	 * on that ship and return true. If it is a sunk ship or empty ocean, return
	 * false
	 * 
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean shootAt(int row, int column) {
		Ship s = this.ships[row][column];
		boolean result = false;
		if ((this.isOccupied(row, column)) && (!s.isSunk())) {
			s.shootAt(row, column);
			result = true;
		}
		attempts[row][column] = s.toString();
		return result;
	}

	public boolean isGameOver() {
		return this.getShipsSunk() == 12;
	}

	public Ship[][] getShips() {
		return ships;
	}

	public int getShotsFired() {
		return shotsFired;
	}

	public int getHitCount() {
		return hitCount;
	}

	public int getShipsSunk() {
		shipsSunk = 0;
		for (Ship s : toPlace) {
			if (s.isSunk())
				shipsSunk++;
		}
		return shipsSunk;
	}

	public Ship[] getToPlace() {
		return toPlace;
	}

	public void print() {
		System.out.println("    0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19");
		for (int i = 0; i <= 19; i++) {
			String rowLabel = i < 10 ? " " + i : "" + i;
			System.out.print(rowLabel + " ");
			for (int j = 0; j <= 19; j++) {
				String inPlace = attempts[i][j];
				String currentChar = inPlace.contentEquals(".") ? inPlace : ships[i][j].toString();
				System.out.print(" " + currentChar + " ");
			}
			System.out.println();
		}
		// Uncomment to provide the player bow coordinant and length of first ship
		// placed
		// System.out.println(this.toPlace[0].getBowRow() + ", " +
		// this.toPlace[0].getBowColumn() + " -> " + this.toPlace[0].getLength());
	}

	public void printState() {
		System.out.println("    0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19");
		for (int i = 0; i <= 19; i++) {
			String rowLabel = i < 10 ? " " + i : "" + i;
			System.out.print(rowLabel + " ");
			for (int j = 0; j <= 19; j++) {
				String currentChar = ships[i][j].toString();
				System.out.print(" " + currentChar + " ");
			}
			System.out.println();
		}

	}

}
