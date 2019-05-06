package battleshipgamefiles;

public class Cruiser extends Ship {

	Cruiser(){
		this.setLength(6);
		this.populateHitArray();
	}
	
	@Override
	String getShipType() {
		// TODO Auto-generated method stub
		return "Cruiser";
	}

}
