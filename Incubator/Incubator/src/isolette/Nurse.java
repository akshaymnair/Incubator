package isolette;

public class Nurse {

	@Override
	public String toString() {
		return "Nurse [ldTemp=" + ldTemp + ", udTemp=" + udTemp + ", laTemp=" + laTemp + ", uaTemp=" + uaTemp
				+ ", control=" + control + "]";
	}

	private int ldTemp;
	private int udTemp;
	private int laTemp;
	private int uaTemp;
	private boolean control;
	
	public Nurse() {
		ldTemp = 0;
		udTemp = 0;
		laTemp = 0;
		uaTemp = 0;
		control = false;
	}

	public int getLdTemp() {
		return ldTemp;
	}

	public void setLdTemp(int ldTemp) {
		this.ldTemp = ldTemp;
	}

	public int getUdTemp() {
		return udTemp;
	}

	public void setUdTemp(int udTemp) {
		this.udTemp = udTemp;
	}

	public int getLaTemp() {
		return laTemp;
	}

	public void setLaTemp(int laTemp) {
		this.laTemp = laTemp;
	}

	public int getUaTemp() {
		return uaTemp;
	}

	public void setUaTemp(int uaTemp) {
		this.uaTemp = uaTemp;
	}

	public boolean isControl() {
		return control;
	}

	public void setControl(boolean control) {
		this.control = control;
	}
	

}
