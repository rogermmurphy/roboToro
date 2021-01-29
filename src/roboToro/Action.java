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
	//public long timeOutML;

	public Action() {
		// TODO Auto-generated constructor stub
		actionClickPoint = new Point(0, 0);
		alPointList = new ArrayList<Point>();
		alPointList.add(actionClickPoint);
		actionDrag = new Rectangle(0, 0, 0, 0);
		sXML = "<CLICK><LOCATION x=\"" + actionClickPoint.x + "\" y=\"" + actionClickPoint.y
				+ "\" ></LOCATION></CLICK>";
	}

	public void sendGCode() {
		
	}
	
	public Action(Rectangle rec, Point p) {
		// this.image = img;
		this.actionDrag = rec;
		this.actionClickPoint = p;
	}

	public Element toXML() {
		Element rootElement = Toro.doc.createElement("ACTION");
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
		//Element nActionName = (Element) root.getElementsByTagName("ACTION").item(0);
		NodeList nlClicks = root.getElementsByTagName("CLICK");
		sXML = "";
		alPointList = new ArrayList<Point>();
		for (int i = 0; i < nlClicks.getLength(); i++) {
			Element currentElement = (Element) nlClicks.item(i);
			//sXML += currentElement.getParentNode()currentElement.
			String xPoint;
			String yPoint;
			try {
				xPoint = currentElement.getElementsByTagName("XPOINT").item(0).getTextContent();
				yPoint = currentElement.getElementsByTagName("YPOINT").item(0).getTextContent();
				
			} catch (Exception ex) {
				xPoint = "0";// 0;
				yPoint = "0";
			}
			actionClickPoint = new Point((int)Double.parseDouble(xPoint), (int)Double.parseDouble(yPoint));
			sXML += "<CLICK><LOCATION x=\"" + actionClickPoint.x + "\" y=\"" + actionClickPoint.y
					+ "\" ></LOCATION></CLICK>";
			alPointList.add(actionClickPoint);
		}

	}
}
