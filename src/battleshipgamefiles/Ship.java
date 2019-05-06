package battleshipgamefiles;

public abstract class Ship {
	private int bowRow;
	private int bowColumn;
	private int length;
	private boolean[] hit;
	private boolean horizontal;
	
	abstract String getShipType();
	
	/**
	 * This is called in the constructor of most subclasses
	 * in order to initialize the hit array.  Everything
	 * starts at false to indicate it has not been hit.
	 */
	public void populateHitArray() {
		this.setHit(new boolean[getLength()]);
		for(int i = 0; i < this.getLength(); i++) {
			this.getHit()[i] = false;
		}
	}
	
	/**
	 * This takes in a location for the bow and a boolean value describing
	 * whether or not the ship is oriented vertically or horizontally.
	 * It will check the provided ocean to make sure the ship does not
	 * extend past the edge of the board, and to make sure the ship would not
	 * be placed too close to any other ship.
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 * @return True if placement is valid
	 */
	public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		// Make sure the piece would not extend past the edge of the board
		boolean fits = horizontal? (column + this.length - 1 <= 19) : (row + this.length - 1 <= 19);
		if(fits == false) return false;
		
		// Make sure that the space the piece would occupy is empty, and that all 
		// adjacent spaces are either empty or off the map.
		for (int i = -1; i <= 1; i ++) {
			for (int j = -1; j <= this.length; j++) {
				// change by i should be against axis, change by j should be along axis
				// where axis is determined by 'horizontal'
				int thisRow = horizontal ? row + i : row + j;
				int thisColumn = horizontal ? column + j : column + i;
				// It doesn't matter if the rectangle being checked extends off the ocean
				// because we already checked that the piece itself won't
				if (thisRow < 0 || thisRow > 19 || thisColumn < 0 || thisColumn > 19) continue;
				if (!(ocean.getShips()[thisRow][thisColumn].getShipType().equals("Empty"))) {
					return false;
				}
			}
		}
		
		return fits;
	}
	
	/**
	 * This places a ship with the bow in the row / column specified with
	 * the orientation corresponding to horizontal.  It will register to
	 * every space in the ocean's grid that would contain part of the ship - 
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		for(int i = 0; i < this.length; i ++) {
			this.bowRow = row;
			this.bowColumn = column;
			this.horizontal = horizontal;
			int currentRow = horizontal ? row : row + i;
			int currentColumn = horizontal ? column + i : column;
			ocean.getShips()[currentRow][currentColumn] = this;
		}
	}
	
	/**
	 * For the given row / column, check the current ship.  If it is already sunk
	 * return false.  Otherwise return true and register the hit to the hit[] array
	 * @param row
	 * @param column
	 * @return true if un-sunk ship is hit
	 */
	boolean shootAt(int row, int column) {
		if (this.isSunk()) return false;
		int hitArrayIndex = row - this.bowRow + column - this.bowColumn;
		hit[hitArrayIndex] = true;
		return true;
	}
	
	/**
	 * Checks to see if the ship is already sunk
	 * @return True if sunk
	 */
	boolean isSunk() {
		for(boolean space : hit) {
			if (!space) return false;
		}
		return true;
	}
	
	public int hitsLeft() {
		int result = this.length;
		for(int i = 0; i < this.length; i++) {
			if (hit[i]) result--;
		}
		return result;
	}
	
	//Getters and Setters
	
	
	@Override
	public String toString() {
		String result = this.isSunk()? "x" : "S";
		return result;
	}

	public int getBowRow() {
		return bowRow;
	}

	public void setBowRow(int bowRow) {
		this.bowRow = bowRow;
	}

	public int getBowColumn() {
		return bowColumn;
	}

	public void setBowColumn(int bowColumn) {
		this.bowColumn = bowColumn;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public boolean[] getHit() {
		return hit;
	}

	public void setHit(boolean[] hit) {
		this.hit = hit;
	}

	public boolean isHorizontal() {
		return horizontal;
	}

	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}
	

	
	

}
