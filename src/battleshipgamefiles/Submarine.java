package battleshipgamefiles;

public class Submarine extends Ship{

	Submarine(){
		this.setLength(4);
		this.populateHitArray();
	}
	@Override
	String getShipType() {
		// TODO Auto-generated method stub
		return "Submarine";
	}

}
