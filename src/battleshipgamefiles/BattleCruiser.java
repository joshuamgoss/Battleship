package battleshipgamefiles;

public class BattleCruiser extends Ship {

	BattleCruiser(){
		this.setLength(7);
		this.populateHitArray();
	}
	
	@Override
	String getShipType() {
		// TODO Auto-generated method stub
		return "Battle Cruiser";
	}

}
