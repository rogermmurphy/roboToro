package roboToro.model;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class ActionModel {
	public Rectangle actionDrag;
	public Point actionClickPoint;
	public ArrayList<Point> alPointList;
	public String sXML;
	public boolean bLookAtNextStep;
	public boolean noValidation;
	public long timeOutML;
	
	private ClickModel cmClickUpStart;
	private ClickModel cmClickDown;
	private ClickModel cmClickUpEnd;
}
