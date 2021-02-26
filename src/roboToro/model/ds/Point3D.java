package roboToro.model.ds;

import java.awt.Point;

public class Point3D extends Point {
	
	public Point3D() {
		super();
		this.z = 0; //TapSettingsModel.
	}
	
	public Point3D(long x, long y, long z) {
		// TODO Auto-generated constructor stub
		this.x = (int)x;
		this.y =  (int)y;
		this.z =  (int)z;
	}

	private int z;

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

}
