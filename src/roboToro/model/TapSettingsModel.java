package roboToro.model;

public class TapSettingsModel implements GenericToroModel {

	/*
	 * iPhone 12 Pro Max Display: 6.7" Super Retina XDR (OLED) Screen resolution
	 * (points): 428 x 926 Native resolution (pixels): 1284 x 2778 (458 ppi) Scale
	 * factor: 3x Portrait size classes: w: Compact, h: Regular Landscape size
	 * classes: w: Regular, h: Compact
	 */
	// viewport
	// Width = (1248 / 458) *25.4
	// Height = (2778 / 458) * 25.4
	private double lDeviceWidth;// = (1284 / 458); // / 25.4; //PX/inch 1.inch/25.4MM
	private double lDeviceHeight;// = (2778 / 458); // / 25.4;
	private double lImageNumberOfPXWidth;// = 653;
	private double lImageNumberOfPXHeight;// = 1400;
	private double dScreenWidthCM;// = lDeviceWidth *25.4;
	private double dScreenHeightCM;// = lDeviceHeight *25.4;
	private double lCalculatedPXWidth;// = dScreenWidthCM / lImageNumberOfPXWidth;
	private double lCalculatedPXHeight;// = dScreenHeightCM / lImageNumberOfPXHeight;

	public TapSettingsModel() {
		super();
		// TODO Auto-generated constructor stub
		lDeviceWidth = 1284.0 / 458.0; // / 25.4; //PX/inch 1.inch/25.4MM
		lDeviceHeight = 2778.0 / 458.0; // / 25.4;
		lImageNumberOfPXWidth = 653.0;
		lImageNumberOfPXHeight = 1400.0;
		dScreenWidthCM = lDeviceWidth * 25.4;
		dScreenHeightCM = lDeviceHeight * 25.4;
		lCalculatedPXWidth = dScreenWidthCM / lImageNumberOfPXWidth;
		lCalculatedPXHeight = dScreenHeightCM / lImageNumberOfPXHeight;
	}

	public static void main(String[] args) {
		TapSettingsModel tsm = new TapSettingsModel();
		System.out.println("lDeviceWidth " + Double.toString(tsm.lDeviceWidth) + " " + 1284 / 458
				+ " lDeviceWidth/lImage " + tsm.lCalculatedPXWidth + " multiply image back in "
				+ tsm.lCalculatedPXWidth * tsm.lImageNumberOfPXWidth);
		System.out.println("PX Width: " + tsm.lCalculatedPXWidth + " PX Height: " + tsm.lCalculatedPXHeight);
		System.out.println("Device Width: " + (tsm.lCalculatedPXWidth * tsm.lImageNumberOfPXWidth) + " Device Height: "
				+ tsm.lCalculatedPXHeight * tsm.lImageNumberOfPXHeight);

	}

	private double lOffsetX;
	private double lOffsetY;
	private double lOffsetRadians;

	public boolean doLoad() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean toXML() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean doBind() {
		// TODO Auto-generated method stub
		return false;
	}

}
