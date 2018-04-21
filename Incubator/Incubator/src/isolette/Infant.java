package isolette;
public class Infant {

	private int infantTemp;
	
	@Override
	public String toString() {
		return "Infant [infantTemp=" + infantTemp + "]";
	}

	public int getInfantTemp(){
		return infantTemp;
	}

	public void setInfantTemp(int infantTemp) {
		this.infantTemp = infantTemp;
	}

}
