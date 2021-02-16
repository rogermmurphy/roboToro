package roboToro.model;

import java.util.ArrayList;

public class ToroModel implements GenericToroModel {

	//System Settings Model
	private TapSettingsModel tsmTapSettingsModel;
	private CalibrationSettingsModel csmCalibrationSettingsModel;
	private ApplicationSettingsModel asmApplicationSettingsModel;
	
	//data Model
	private ArrayList<RoutineModel> alRoutineList;

	
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
