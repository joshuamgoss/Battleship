package battleshipgamefiles;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class OceanTest {
	Ocean ocean;
	@Before
	public void setUp() throws Exception {
		ocean = new Ocean();
	}

	@Test
	public void testPlaceAllShipsRandomly() {
		ocean.placeAllShipsRandomly();
		int countShips = 0;
		for(int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				if (!(ocean.getShips()[i][j].getShipType().contentEquals("Empty"))) countShips ++;
			}
		}
		ocean.printState();
		assertEquals(61, countShips);
	}

	@Test
	public void testIsOccupied() {
		for(int i = 0; i < 20; i ++) {
			for (int j = 0; j < 20; j++) {
				assertEquals(ocean.isOccupied(i, j), !ocean.getShips()[i][j].getShipType().contentEquals("Empty"));
			}
		}
	}

	@Test
	public void testShootAt() {
		for(int i = 0; i < 20; i ++) {
			for (int j = 0; j < 20; j++) {
				Ship s = ocean.getShips()[i][j];
				if (s.getShipType().contentEquals("Empty") || s.isSunk() == true) {
					assertEquals(ocean.shootAt(i, j), false);
				} else if (s.toString().contentEquals("S")) {
					int initialHits = s.hitsLeft();
					boolean shot = ocean.shootAt(i, j);
					assertEquals(shot, true);
					assertEquals(initialHits, s.hitsLeft() - 1);
				}
			}
		}
	}

	@Test
	public void testIsGameOver() {
		boolean over = true;
		for (Ship s : ocean.getToPlace()) {
			if (!s.isSunk()) over = false;
		}
		assertEquals(over, ocean.isGameOver());
	}

}
