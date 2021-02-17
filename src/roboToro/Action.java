package roboToro;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Action {

	// public BufferedImage image;
	public Rectangle actionDrag;
	public Point actionClickPoint;
	public ArrayList<Point> alPointList;
	public String sXML;
	public boolean bLookAtNextStep;
	public boolean noValidation;
	public long timeOutML;

	public Action() {
		// TODO Auto-generated constructor stub
		bLookAtNextStep = false;
		noValidation = false;
		actionClickPoint = new Point(0, 0);
		alPointList = new ArrayList<Point>();
		alPointList.add(actionClickPoint);
		actionDrag = new Rectangle(0, 0, 0, 0);
		sXML = "<CLICK><LOCATION x=\"" + actionClickPoint.x + "\" y=\"" + actionClickPoint.y
				+ "\" ></LOCATION></CLICK>";
		timeOutML = Toro.DEFAULT_STEP_TIMEOUT_ML;
	}

	public void sendGCode() {
		// sendCommand("G01 X42 Y24 Z-345.5 W0");
		// move to up
		for (int i = 0; i < this.alPointList.size(); i++) {
			Point p = this.alPointList.get(i);
			String upClick = "G01 ";// X45 Y98 Z-340.5 W0";
			String downClick = "G01 ";
			long x = (long) Math.round(p.x * Toro.ACTUAL_PIXEL_WIDTH);
			long y = (long) Math.round(Toro.DIVICE_HEIGTH_MM - (p.y * Toro.ACTUAL_PIXEL_HEIGHT));
			x += Math.random()*2; //% Toro.xVAR;
			x -= Math.random()*2;// % Toro.xVAR;
		//	y += Math.random() % Toro.yVAR;
		//	y += x += Math.random() % Toro.yVAR;

			upClick += "X" + x + " Y" + y + " Z" + Toro.DELTA_Z_CORD_UP + " W0";
			downClick += "X" + x + " Y" + y + " Z" + Toro.DELTA_Z_CORD_DOWN + " W0";
			try {
			System.out.println(upClick);
			System.out.println(downClick);
			Toro.comClient.sendCommand("M205 S3000");
			Toro.comClient.sendCommand(upClick);
			Thread.sleep(30);
			Toro.comClient.sendCommand("M205 S300");
			Toro.comClient.sendCommand(downClick);
			Thread.sleep(10);
			
			
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Toro.comClient.sendCommand(upClick);
			/*
			/*
			
			try {
				//Thread.sleep(150);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
		// "G01 X " + this.actionClickPoint.x + Toro.DELTA_Z_CORD_UP + " W0";
		// move to down
		// move to up again
		// Toro.comClient.sendCommand(sXML)
	}
	
	public void sendGCodeWithPause(long ms) {
		// sendCommand("G01 X42 Y24 Z-345.5 W0");
		// move to up
		for (int i = 0; i < this.alPointList.size(); i++) {
			Point p = this.alPointList.get(i);
			String upClick = "G01 ";// X45 Y98 Z-340.5 W0";
			String downClick = "G01 ";
			long x = (long) Math.round(p.x * Toro.ACTUAL_PIXEL_WIDTH);
			long y = (long) Toro.DIVICE_HEIGTH_MM - Math.round(p.y * Toro.ACTUAL_PIXEL_WIDTH);
			x += Math.random()*2; //% Toro.xVAR;
			x -= Math.random()*2;// % Toro.xVAR;
		//	y += Math.random() % Toro.yVAR;
		//	y += x += Math.random() % Toro.yVAR;

			upClick += "X" + x + " Y" + y + " Z" + Toro.DELTA_Z_CORD_UP + " W0";
			downClick += "X" + x + " Y" + y + " Z" + Toro.DELTA_Z_CORD_DOWN + " W0";

			System.out.println(upClick);
		System.out.println(downClick);

			Toro.comClient.sendCommand(upClick);
			Toro.comClient.sendCommand(downClick);
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Toro.comClient.sendCommand(upClick);
			
			try {
				Thread.sleep(ms);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			
			try {
				//Thread.sleep(150);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
		// "G01 X " + this.actionClickPoint.x + Toro.DELTA_Z_CORD_UP + " W0";
		// move to down
		// move to up again
		// Toro.comClient.sendCommand(sXML)
	}

	public Action(Rectangle rec, Point p) {
		// this.image = img;
		this.actionDrag = rec;
		this.actionClickPoint = p;
	}

	public Element toXML() {
		Element rootElement = Toro.doc.createElement("ACTION");
		Element lookFWD = Toro.doc.createElement("LOOK_FOWARD");
		lookFWD.appendChild(Toro.doc.createTextNode(Boolean.toString(this.bLookAtNextStep)));
		rootElement.appendChild(lookFWD);
		
		Element eNoValidaton = Toro.doc.createElement("NO_VALIDATION");
		eNoValidaton.appendChild(Toro.doc.createTextNode(Boolean.toString(this.noValidation)));
		rootElement.appendChild(eNoValidaton);
		
		// Element name = Toro.doc.createElement("PASS");
		// name.appendChild(Toro.doc.createTextNode(stepName));
		// rootElement.appendChild(name);
		Element loop;
		for (int i = 0; i < alPointList.size(); i++) {
			loop = Toro.doc.createElement("CLICK");
			rootElement.appendChild(loop);
			Node loopX = Toro.doc.createElement("XPOINT");
			loop.appendChild(loopX);
			loopX.appendChild(Toro.doc.createTextNode(Double.toString(alPointList.get(i).getX())));
			// rootElement.appendChild(loop);

			Node loopY = Toro.doc.createElement("YPOINT");
			loop.appendChild(loopY);
			loopY.appendChild(Toro.doc.createTextNode(Double.toString(alPointList.get(i).getY())));
			// rootElement.appendChild(loop);
		}

		return rootElement;
	}

	public void load(Element root) {
		NodeList nlLookFWD = root.getElementsByTagName("LOOK_FOWARD");
		if (nlLookFWD != null && nlLookFWD.getLength() > 0) {
			if (nlLookFWD.item(0).getTextContent().compareTo("true") == 0)
				this.bLookAtNextStep = true;
		}
		NodeList nlNoValidation = root.getElementsByTagName("NO_VALIDATION");
		if (nlNoValidation != null && nlNoValidation.getLength() > 0) {
			//System.out.println(nlNoValidation.item(0).getTextContent().trim().compareToIgnoreCase("true"));
			if (nlNoValidation.item(0).getTextContent().trim().compareToIgnoreCase("true") == 0)
				this.noValidation = true;
		}
		NodeList nlClicks = root.getElementsByTagName("CLICK");
		sXML = "";
		alPointList = new ArrayList<Point>();
		for (int i = 0; i < nlClicks.getLength(); i++) {
			Element currentElement = (Element) nlClicks.item(i);
			// sXML += currentElement.getParentNode()currentElement.
			String xPoint;
			String yPoint;
			try {
				xPoint = currentElement.getElementsByTagName("XPOINT").item(0).getTextContent();
				yPoint = currentElement.getElementsByTagName("YPOINT").item(0).getTextContent();

			} catch (Exception ex) {
				xPoint = "0";// 0;
				yPoint = "0";
			}
			actionClickPoint = new Point((int) Double.parseDouble(xPoint), (int) Double.parseDouble(yPoint));
			sXML += "<CLICK><LOCATION x=\"" + actionClickPoint.x + "\" y=\"" + actionClickPoint.y
					+ "\" ></LOCATION></CLICK>";
			alPointList.add(actionClickPoint);
		}

	}
}
