package roboToro.model;

import java.awt.Point;

import roboToro.model.ds.Point3D;

public class MoveModel extends CommandModel {

	public MoveModel() {
		// TODO Auto-generated constructor stub
		pTargetMovePoint = new Point3D(0,0,0);
	}
	
	private Point3D pTargetMovePoint;

	public Point3D getPTargetMovePoint() {
		return pTargetMovePoint;
	}

	public void setPTargetMovePoint(Point3D pTargetMovePoint) {
		this.pTargetMovePoint = pTargetMovePoint;
	}

	public void setPTargetMovePoint(Point point2D) {
		this.pTargetMovePoint = new Point3D(point2D.x, point2D.y, CalibrationSettingsModel.ZUP_DEFAULT);
	}
	
	public void setPTargetMovePoint(long x, long y, long z) {
		this.pTargetMovePoint = new Point3D(x,y,z);
	}
	
	public void setPTargetMovePointDefaultUp(long x, long y) {
		this.pTargetMovePoint = new Point3D(x, y, CalibrationSettingsModel.ZUP_DEFAULT);
	}
	
	public void setPTargetMovePointDefaultDown(long x, long y) {
		this.pTargetMovePoint = new Point3D(x, y, CalibrationSettingsModel.ZDOWN_DEFAULT);
	}

}
