package roboToro.model;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class ActionModel implements GenericToroModel{
	
	public static String TYPE_DRAG = "DRAG";
	public static String TYPE_CLICK = "CLICK";
	
	/*
	private Rectangle actionDrag;
	private Point actionClickPoint;
	private ArrayList<Point> alPointList;
	private String sXML;
	private boolean bLookAtNextStep;
	private boolean noValidation;
	private long timeOutML;
	*/
	
	private String sActionType;
	private ClickModel alcmClick;
	private SwipeModel alsmSwipe;
	
	public ActionModel() {
		
	}
	
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
}
