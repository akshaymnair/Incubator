package isolette;

import java.io.FileNotFoundException;

enum status{
	VALID, INVALID;
}
public class TemperatureSensor {

	private int currentTemp;
	private status state;

	Air air = new Air();

	public TemperatureSensor() {
		currentTemp = 0;
		state = status.VALID;
	}
	public int getCurrentTemp(int infantTemp, HeatSource heatSource) throws FileNotFoundException {
		currentTemp = (int)Math.round(air.getAirTemp(infantTemp, heatSource));
		return currentTemp;
	}
	public status getState() {
		return state;
	}
	public void setState(status state) {
		this.state = state;
	}
	public void setCurrentTemp(int currentTemp) {
		this.currentTemp = currentTemp;
	}
	@Override
	public String toString() {
		return "TemperatureSensor [currentTemp=" + currentTemp + ", state=" + state + ", air=" + air + "]";
	}
	
}
