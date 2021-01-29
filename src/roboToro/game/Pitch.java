package roboToro.game;

public class Pitch {
	
	public static final String BALL = "BALL";
	public static final String STRIKE = "STRIKE";
	
	public static final String FAST_BALL = "FAST_BALL";
	public static final String CURVE_BALL = "CURVE_BALL";
	public static final String SLIDER = "SLIDER";
	
	public static final String RH_PITCH = "RH_PITCH";
	public static final String LH_PITCH = "LH_PITCH";
			
	private int pitchspeed;
	private boolean isStrike;
	private boolean isBall;
	private boolean isRight;
	private boolean isLeft;
	private String sPitchType; 
	

	public Pitch() {
		// TODO Auto-generated constructor stub
	}

}
