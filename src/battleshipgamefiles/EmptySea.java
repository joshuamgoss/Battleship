package battleshipgamefiles;

public class EmptySea extends Ship {

	EmptySea(){
		this.setLength(1);
	}
	
	@Override
	String getShipType() {
		return "Empty";
	}
	
	@Override
	public String toString() {
		return "-";
	}

}
