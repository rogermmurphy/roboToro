package roboToro.integration.gcode;

import roboToro.Action;
import roboToro.Toro;

public abstract class GCommand {

	
	/*
	 * ====Vision functions==========

;M98 PpauseCamera

;M98 PresumeCamera

;M98 PcaptureCamera

;M98 PdeleteFirstObject

;M98 PclearObjects

;========================



N05 G28



;Acceleration

N10 M204 A1200

;Speed

N15 G01 F200

G01 X29 Y126 Z-340 W0

G01 X29 Y126 Z-362.5 W0

G01 X29 Y126 Z-340 W0





;Swipe Action

G01 X42 Y24 Z-352.5 W0

G01 X42 Y24 Z-365.5 W0

G01 X45 Y98 Z-365.5 W0

G01 X45 Y98 Z-340.5 W0
	 */
	
	
	public GCommand() {
		// TODO Auto-generated constructor stub
	}
	
	public String performAction (Action a) {
		return null;
	}
	
	public boolean doClick(Action a) {
		String upClick = "G01 " ;//X45 Y98 Z-340.5 W0";
		String downClick = "G01 ";
		double x = Math.round(a.alPointList.get(0).x * Toro.ACTUAL_PIXEL_WIDTH);
		double y = Toro.DIVICE_HEIGTH_MM - Math.round(a.alPointList.get(0).y * Toro.ACTUAL_PIXEL_WIDTH);
		upClick += "X" + x + " Y" + y + " Z" + Toro.DELTA_Z_CORD_UP;
		downClick += "X" + x + " Y" + y + " Z" + Toro.DELTA_Z_CORD_DOWN;
		//move to up location
		
		return true;
	}
	
	public boolean doSwipe() {
	/*	
	 	G01 X42 Y24 Z-352.5 W0
		G01 X42 Y24 Z-365.5 W0
		G01 X45 Y98 Z-365.5 W0
		G01 X45 Y98 Z-340.5 W0
	*/
		
		return true;
		
	}

}
