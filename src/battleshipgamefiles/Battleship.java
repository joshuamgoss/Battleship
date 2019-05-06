package battleshipgamefiles;

public class Battleship extends Ship {

	Battleship(){
		this.setLength(8);
		this.populateHitArray();
	}
	
	@Override
	String getShipType() {
		// TODO Auto-generated method stub
		return "Battleship";
	}
	

}
