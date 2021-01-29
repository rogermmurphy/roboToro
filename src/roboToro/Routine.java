package roboToro;

import java.util.ArrayList;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;
import javax.swing.AbstractListModel;

public class Routine extends AbstractListModel<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static String TYPE_IMAGE_COMPAIR = "Image Compair";
	
	public int timeout;
	
	public String routineName;
	public ArrayList<Step> alStepList;
	public Step currentStep;
			
	public Routine() {
		// TODO Auto-generated constructor stub
		alStepList = new ArrayList<Step>();
		currentStep = new Step();
		timeout = 0;
		alStepList.add(currentStep);
		this.routineName = "New Routine";
	}
	
	
	static void routineDropdownLoader() {
		
	}
	
	public Element toXML() throws ParserConfigurationException {
	

		// root element
		Element rootElement = Toro.doc.createElement("ROUTINE");
		Element name = Toro.doc.createElement("ROUTINENAME");
		rootElement.appendChild(name);
		name.appendChild(Toro.doc.createTextNode(routineName));
		//name.setNodeValue(routineName);
		
		
		Element routineTimeout = Toro.doc.createElement("TIMEOUT");
		rootElement.appendChild(routineTimeout);
		routineTimeout.appendChild(Toro.doc.createTextNode(String.valueOf(timeout)));
		
		Step loop;
	//	doc.
		for(int i = 0; i < alStepList.size(); i++) {
			loop = alStepList.get(i);
			rootElement.appendChild(loop.toXML());
		}
		
		//Element element = new Element("ROUTINE");
		return rootElement;
	}

	public void load(Element root){
		routineName = root.getElementsByTagName("ROUTINENAME").item(0).getTextContent();
		timeout = Integer.parseInt(root.getElementsByTagName("TIMEOUT").item(0).getTextContent());
		NodeList stepList = root.getElementsByTagName("STEP");
		alStepList = new ArrayList<Step>();
		for (int i = 0; i < stepList.getLength(); i++) {
			Step temp = new Step();
			temp.load((Element)stepList.item(i));
			alStepList.add(temp);
			if(i == 0) {
				this.currentStep = temp;
			}
		}
		
	//	ArrayList<Routine> returnList = new ArrayList<Routine>();
	//	Routine test = new Routine();
	//	returnList.add(test);
		return;
	}

	public static ArrayList<Routine> load(){
		ArrayList<Routine> returnList = new ArrayList<Routine>();
		Routine test = new Routine();
		returnList.add(test);
		return returnList;
	}
	
	//not going to use this...
	public boolean execute() {
		for (int i = 0; i < alStepList.size(); i++) {
			if(!alStepList.get(i).execute()) {
				return false;
			}
		}
		return true;
	}


	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return alStepList.size();
	}


	@Override
	public String getElementAt(int index) {
		// TODO Auto-generated method stub
		return alStepList.get(0).stepName;
	}
	
	
}
