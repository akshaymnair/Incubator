package isolette;

import java.io.FileNotFoundException;

public class Air {

	private double airTemp;
	
	public void setAirTemp(double airTemp) {
		this.airTemp = airTemp;
	}


	public Air() {
		airTemp = 97.0;
	}


	@Override
	public String toString() {
		return "Air [airTemp=" + airTemp + "]";
	}


	public double getAirTemp(int infantTemp, HeatSource heatSource) throws FileNotFoundException {
		airTemp = ((airTemp + infantTemp)/2)+ heatSource.heatGradient();
		return airTemp;
	}

}
