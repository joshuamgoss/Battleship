package battleshipgamefiles;

public class Destroyer extends Ship {

	Destroyer(){
		this.setLength(4);
		this.populateHitArray();
	}
	
	@Override
	String getShipType() {
		// TODO Auto-generated method stub
		return "Destroyer";
	}

}
