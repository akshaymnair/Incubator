package isolette;
public class HeatSource {

	private double heatOut;
	private boolean heatControl;
	
	public HeatSource() {
		heatOut = 0;
		heatControl = true;
	}
	
	public HeatSource(boolean heatMode) {
		heatControl = heatMode;
	}

	@Override
	public String toString() {
		return "HeatSource [heatOut=" + heatOut + ", heatControl=" + heatControl + "]";
	}

	public double heatGradient() {
		if (heatControl)
			heatOut = 0.1;
		else
			heatOut = -0.1;
		return heatOut;
	}

	public double getHeatOut() {
		return heatOut;
	}

	public void setHeatOut(double heatOut) {
		this.heatOut = heatOut;
	}

	public boolean isHeatControl() {
		return heatControl;
	}

	public void setHeatControl(boolean heatControl) {
		this.heatControl = heatControl;
	}

}
