package isolette;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;  
import java.util.Date;

public class OperatorInterface {

	private boolean alarmStatus;
	private status ldTempStatus;
	private status udTempStatus;
	private status laTempStatus;
	private status uaTempStatus;
	private int displayTemp;


	public OperatorInterface() {
		alarmStatus = false;
		displayTemp = 0;
		ldTempStatus = status.INVALID;
		udTempStatus = status.INVALID;
		laTempStatus = status.INVALID;
		uaTempStatus = status.INVALID;
	}
	public boolean isAlarmStatus() {
		return alarmStatus;
	}
	public void setAlarmStatus(boolean alarmStatus) {
		this.alarmStatus = alarmStatus;
	}
	public status getLdTempStatus() {
		return ldTempStatus;
	}
	public void setLdTempStatus(status ldTempStatus) {
		this.ldTempStatus = ldTempStatus;
	}
	public status getUdTempStatus() {
		return udTempStatus;
	}
	public void setUdTempStatus(status udTempStatus) {
		this.udTempStatus = udTempStatus;
	}
	public status getLaTempStatus() {
		return laTempStatus;
	}
	public void setLaTempStatus(status laTempStatus) {
		this.laTempStatus = laTempStatus;
	}
	public status getUaTempStatus() {
		return uaTempStatus;
	}
	public void setUaTempStatus(status uaTempStatus) {
		this.uaTempStatus = uaTempStatus;
	}
	public int getDisplayTemp() {
		return displayTemp;
	}
	public void setDisplayTemp(int displayTemp) {
		this.displayTemp = displayTemp;
	}
	
	public void writeOutputFile(Thermostat thermostat) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		Date date = new Date();
		alarmStatus = thermostat.isalarmControl();
		displayTemp = thermostat.getCurrentTemp();

		try {

			FileWriter fileWriter = new FileWriter("C:\\Users\\mraks\\Dropbox\\CSE564_Project\\DesignAndFiles\\IsoletteOutput.txt", true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			bufferedWriter.write("--------------------------------------------------------");
			bufferedWriter.newLine();
			bufferedWriter.write("Display Temperature: "+ displayTemp+"'F");
			bufferedWriter.newLine();
			bufferedWriter.write("Alarm Status: \t\t "+ ((alarmStatus)?"ON":"OFF"));
			bufferedWriter.newLine();
			bufferedWriter.write("Thermostat Status: \t "+ thermostat.getThermostatStatus());
			bufferedWriter.newLine();
			bufferedWriter.write("Timestamp: \t\t\t "+formatter.format(date));
			bufferedWriter.newLine();
			bufferedWriter.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void validateInput(Nurse nurse) {
		if (!(nurse.getLaTemp() < 93 || nurse.getLaTemp() > 98 || (nurse.getLdTemp() - nurse.getLaTemp()) > 1))
			laTempStatus = status.VALID;

		if (!(nurse.getLdTemp() < 97 || nurse.getLdTemp() > 99 || (nurse.getUdTemp() - nurse.getLdTemp()) > 1 
				||(nurse.getLdTemp() - nurse.getLaTemp()) > 1))
			ldTempStatus = status.VALID;
		
		if (!(nurse.getUdTemp() < 98 || nurse.getUdTemp() >100 || (nurse.getUdTemp() - nurse.getLdTemp()) > 1 
				|| (nurse.getUaTemp() - nurse.getUdTemp()) > 1))
			udTempStatus = status.VALID;
		
		if (!(nurse.getUaTemp() < 99 || nurse.getUaTemp() >103 || (nurse.getUaTemp() - nurse.getUdTemp()) > 1))
			uaTempStatus = status.VALID;
	}
	@Override
	public String toString() {
		return "OperatorInterface [alarmStatus=" + alarmStatus + ", ldTempStatus=" + ldTempStatus + ", udTempStatus="
				+ udTempStatus + ", laTempStatus=" + laTempStatus + ", uaTempStatus=" + uaTempStatus + ", displayTemp="
				+ displayTemp + "]";
	}

}
