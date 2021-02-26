package roboToro.model;

public class CommandModel implements GenericToroModel {

	private long lVelocity;
	private long lAcceleration;
	private long lpauseBefore;
	private long lPauseAfter;
	private boolean bOverRideSystemDefault;
	
	
	@Override
	public boolean doLoad() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean toXML() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean doBind() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public long getlVelocity() {
		return lVelocity;
	}
	public void setlVelocity(long lVelocity) {
		this.lVelocity = lVelocity;
	}
	public long getlAcceleration() {
		return lAcceleration;
	}
	public void setlAcceleration(long lAcceleration) {
		this.lAcceleration = lAcceleration;
	}
	public long getLpauseBefore() {
		return lpauseBefore;
	}
	public void setLpauseBefore(long lpauseBefore) {
		this.lpauseBefore = lpauseBefore;
	}
	public long getlPauseAfter() {
		return lPauseAfter;
	}
	public void setlPauseAfter(long lPauseAfter) {
		this.lPauseAfter = lPauseAfter;
	}
	public boolean isbOverRideSystemDefault() {
		return bOverRideSystemDefault;
	}
	public void setbOverRideSystemDefault(boolean bOverRideSystemDefault) {
		this.bOverRideSystemDefault = bOverRideSystemDefault;
	}
}
