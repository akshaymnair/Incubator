package isolette;

enum modes{
	INIT, ON, FAILED;
}

public class Thermostat {

	private modes regulatorStatus;
	private modes monitorStatus;
	private modes thermostatStatus;
	private boolean alarmControl;
	private boolean heatControl;
	private int currentTemp;
	
	public Thermostat() {
		regulatorStatus = modes.INIT;
		monitorStatus = modes.INIT;
		thermostatStatus = modes.INIT;
		currentTemp = 0;
		heatControl = true;
	}

	public modes getRegulatorStatus() {
		return regulatorStatus;
	}

	public void setRegulatorStatus(modes regulatorStatus) {
		this.regulatorStatus = regulatorStatus;
	}

	public modes getMonitorStatus() {
		return monitorStatus;
	}

	public void setMonitorStatus(modes monitorStatus) {
		this.monitorStatus = monitorStatus;
	}

	public modes getThermostatStatus() {
		return thermostatStatus;
	}

	public void setThermostatStatus(modes thermostatStatus) {
		this.thermostatStatus = thermostatStatus;
	}

	public boolean isalarmControl() {
		return alarmControl;
	}

	public void setalarmControl(boolean alarmControl) {
		this.alarmControl = alarmControl;
	}

	public boolean isHeatControl() {
		return heatControl;
	}

	public void setHeatControl(boolean heatControl) {
		this.heatControl = heatControl;
	}

	public int getCurrentTemp() {
		return currentTemp;
	}

	public void setCurrentTemp(int currentTemp) {
		this.currentTemp = currentTemp;
	}


	public void setStatus(TemperatureSensor tSensor, OperatorInterface opt) {
		if (opt.getLdTempStatus() == status.VALID && opt.getUdTempStatus() == status.VALID)
			regulatorStatus = modes.ON;
		else
			regulatorStatus = modes.FAILED;

		if (opt.getLaTempStatus() == status.VALID && opt.getUaTempStatus() == status.VALID)
			monitorStatus = modes.ON;
		else
			monitorStatus = modes.FAILED;

		if (regulatorStatus == modes.FAILED ||  monitorStatus == modes.FAILED || tSensor.getState() == status.INVALID)
			thermostatStatus = modes.FAILED;
		else
			thermostatStatus = modes.ON;
	}

	public boolean regulator(int ldTemp, int udTemp) {
		if (regulatorStatus == modes.ON) {
			if (currentTemp < ldTemp)
				heatControl = true;
			if (currentTemp > udTemp)
				heatControl = false;
			if(currentTemp >= ldTemp && currentTemp <= udTemp)
				heatControl = heatControl; //no change in the state
		}
		else
			System.out.println("Regulator is not ON");
		
		return heatControl;
	}


	@Override
	public String toString() {
		return "Thermostat [regulatorStatus=" + regulatorStatus + ", monitorStatus=" + monitorStatus
				+ ", thermostatStatus=" + thermostatStatus + ", alarmControl=" + alarmControl + ", heatControl="
				+ heatControl + ", currentTemp=" + currentTemp + "]";
	}

	public boolean monitor(int laTemp, int uaTemp) {
		if (monitorStatus == modes.ON) {
			if (currentTemp < laTemp || currentTemp > uaTemp)
				alarmControl = true;
			else
				alarmControl = false;
		}
		else
			System.out.println("Monitor is not ON");

		return alarmControl;
	}


}
