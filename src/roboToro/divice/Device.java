package roboToro.divice;

public abstract class Device {
	
	public abstract int getScreenWidth();
	
	
	public abstract Device Divice();
	
	public static Device getDevice(String s){
		return new Iphone11X();
	}
}
