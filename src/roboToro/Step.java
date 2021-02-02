package roboToro;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import roboToro.UI.PhonePanel;
import roboToro.game.tbs2020.FixedUtil;

import org.w3c.dom.*;

public class Step {

	
	public String stepName;
	public Action passAction;
	public Action failAction;
	public String stepType;
	public BufferedImage image;
	public Rectangle subImageLocation;
	public long timeOutML;
	public boolean lookFoward;
	
	static String ACTION_FAIL_GO_TO_NEXT_STEP = "GO_TO_NEXT_STEP";
	static String ACTION_PASS_GO_TO_NEXT_STEP = "GO_TO_NEXT_STEP";
	
	public Step() {
		// TODO Auto-generated constructor stub
		stepName = "Edit Step Name";
		stepType = "COMPAIR_IMG";
		passAction = new Action();
		failAction = new Action();
		image = new BufferedImage(200,200,BufferedImage.TYPE_INT_RGB);
		subImageLocation = new Rectangle();
		timeOutML = Toro.DEFAULT_STEP_TIMEOUT_ML;
		lookFoward = true;
	}
	

	
	public double Validate(BufferedImage img1) {
	//	System.out.println(subImageLocation.x + " " + subImageLocation.y + " " +  subImageLocation.width + " " + subImageLocation.height);
		return FixedUtil.compaireImage(img1.getSubimage(subImageLocation.x, subImageLocation.y, subImageLocation.width, subImageLocation.height), image);
	}
	
	public Element toXML() throws ParserConfigurationException {
	
		// root element
		Element rootElement = Toro.doc.createElement("STEP");
		Element name = Toro.doc.createElement("STEPNAME");
		name.appendChild(Toro.doc.createTextNode(stepName));
		rootElement.appendChild(name);
		
		Element stepTypeElement = Toro.doc.createElement("STEPTYPE");
		stepTypeElement.appendChild(Toro.doc.createTextNode(stepType));
		rootElement.appendChild(stepTypeElement);
		
		Element passElement = Toro.doc.createElement("PASSACTION");
		passElement.appendChild(passAction.toXML());
		rootElement.appendChild(passElement);

		
		Element failElement = Toro.doc.createElement("FAILCTION");
		failElement.appendChild(failAction.toXML());
		rootElement.appendChild(failElement);
		
		try {

			String sImageFileName = System.currentTimeMillis() + "_" + stepName;
			
			Element nImageFileName = Toro.doc.createElement("IMAGE_FILE_NAME");
			nImageFileName.appendChild(Toro.doc.createTextNode(sImageFileName));
			
		    File outputfile = new File(Toro.sWriteFileLocation + Toro.sScreenCaptureLocation + sImageFileName);
		    ImageIO.write(image, "png", outputfile);
		    
		    Element nSubImageLocation = Toro.doc.createElement("SUB_IMAGE_LOCATION");
		    nSubImageLocation.appendChild(Toro.doc.createTextNode(subImageLocation.x + "|" + subImageLocation.y+  "|" +subImageLocation.width + "|" + subImageLocation.height));
		    
		    
		    rootElement.appendChild(nImageFileName);
		    rootElement.appendChild(nSubImageLocation);
		} catch (IOException e) {
		    // handle exception
			//e.addSuppressed(exception);
		}
	
		return rootElement;
	}
	
	public void load(Element rootStepNode) {
		try {
		stepName = rootStepNode.getElementsByTagName("STEPNAME").item(0).getTextContent();
		stepType = rootStepNode.getElementsByTagName("STEPTYPE").item(0).getTextContent();
		rootStepNode.getElementsByTagName("STEPNAME").item(0).getTextContent();
		Element loadPassActionNode =  (Element)rootStepNode.getElementsByTagName("PASSACTION").item(0);
		passAction.load(loadPassActionNode);
		Element loadFailActionNode =  (Element)rootStepNode.getElementsByTagName("FAILCTION").item(0);
		failAction.load(loadFailActionNode);
		
		String[] loadSubImageLocation = {"0","0","0","0"};
		try {
			loadSubImageLocation =  rootStepNode.getElementsByTagName("SUB_IMAGE_LOCATION").item(0).getTextContent().split("\\|");
			//System.out.println("sub image location: " + loadSubImageLocation);
		}catch(Exception ex) {
			//loadSubImageLocation = String[];
		}
		subImageLocation = new Rectangle(Integer.parseInt(loadSubImageLocation[0]),Integer.parseInt(loadSubImageLocation[1]),Integer.parseInt(loadSubImageLocation[2]),Integer.parseInt(loadSubImageLocation[3]));
		String limageFileName =  rootStepNode.getElementsByTagName("IMAGE_FILE_NAME").item(0).getTextContent();
		 File inputFile = new File(Toro.sWriteFileLocation + Toro.sScreenCaptureLocation + limageFileName);
		// ImageIO.read(image,)//(image, "png", limageFileName);
		image =  ImageIO.read(inputFile);
		
	//	rootStepNode
		}catch(Exception ex) {
			//corrupt load file
			stepName = "Edit Step Name";
			stepType = "COMPAIR_IMG";
			passAction = new Action();
			failAction = new Action();
			image = new BufferedImage(200,200,BufferedImage.TYPE_INT_RGB);
			subImageLocation = new Rectangle();
			System.out.println("Corupt Configuration File Error");
			ex.printStackTrace();
		}
	}



	public boolean execute() {
		// TODO Auto-generated method stub
		if(this.Validate(PhonePanel.image) == 0) {
			while(this.Validate(PhonePanel.image) == 0) {
				//repeat click sometimes you have to click 3 or 4 times
				System.out.println("Execution True Step: " + this.stepName);
				//Toro.comClient.sendCommand(null)
				passAction.sendGCode();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return true;
		}
		if(this.Validate(PhonePanel.image) < .8) {
			while(this.Validate(PhonePanel.image) < .8) {
				//repeat click sometimes you have to click 3 or 4 times
				System.out.println("Almost Match True Step: " + this.stepName);
				//Toro.comClient.sendCommand(null)
				passAction.sendGCode();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return true;
		}

		
	
			return false;
		
	}

}
