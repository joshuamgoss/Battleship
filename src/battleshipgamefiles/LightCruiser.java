package battleshipgamefiles;

public class LightCruiser extends Ship{

	LightCruiser(){
		this.setLength(5);
		this.populateHitArray();
	}
	
	@Override
	String getShipType() {
		// TODO Auto-generated method stub
		return "Light Cruiser";
	}

}
