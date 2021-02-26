package roboToro.model;

public class CalibrationSettingsModel {
	
	public static long ZDOWN_DEFAULT = -200;
	public static long ZUP_DEFAULT = -200;
	public static long STARTING_VELOCITY_DEFAULT = 600;
	public static long ACCELERATION_DEFAULT = 400;
	public static long PAUSE_DEFAULT = 100;
			
	private long lZdown;
	private long lZup;
	private long lStartVelocity;
	private long lAcceleration;
	private long lPause;

	private CommandModel cmClickUpStart;

	private CommandModel cmClickDown;

	private CommandModel cmClickUpEnd;

	public CalibrationSettingsModel() {
		super();
	}

	public synchronized final CommandModel getCmClickDown() {
		return cmClickDown;
	}

	public synchronized final CommandModel getCmClickUpEnd() {
		return cmClickUpEnd;
	}

	public synchronized final CommandModel getCmClickUpStart() {
		return cmClickUpStart;
	}

	public synchronized final long getlAcceleration() {
		return lAcceleration;
	}

	public synchronized final long getlPause() {
		return lPause;
	}

	public synchronized final long getlStartVelocity() {
		return lStartVelocity;
	}

	public synchronized final long getlZdown() {
		return lZdown;
	}

	public synchronized final long getlZup() {
		return lZup;
	}

	public synchronized final void setCmClickDown(CommandModel cmClickDown) {
		this.cmClickDown = cmClickDown;
	}

	public synchronized final void setCmClickUpEnd(CommandModel cmClickUpEnd) {
		this.cmClickUpEnd = cmClickUpEnd;
	}

	public synchronized final void setCmClickUpStart(CommandModel cmClickUpStart) {
		this.cmClickUpStart = cmClickUpStart;
	}

	public synchronized final void setlAcceleration(long lAcceleration) {
		this.lAcceleration = lAcceleration;
	}

	public synchronized final void setlPause(long lPause) {
		this.lPause = lPause;
	}

	public synchronized final void setlStartVelocity(long lStartVelocity) {
		this.lStartVelocity = lStartVelocity;
	}
	public synchronized final void setlZdown(long lZdown) {
		this.lZdown = lZdown;
	}
	public synchronized final void setlZup(long lZup) {
		this.lZup = lZup;
	}

}
