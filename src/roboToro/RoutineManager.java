package roboToro;

import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NameList;

import java.io.File;

import javax.swing.AbstractListModel;

public class RoutineManager extends AbstractListModel<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int timeout;

	public String routineName;
	public ArrayList<Routine> alRoutineList;
	public Routine currentRoutine;

	public RoutineManager() {
		// TODO Auto-generated constructor stub
		alRoutineList = new ArrayList<Routine>();
		this.load();
	}

	public String toString() {
		String out = "<ROUTINES>";
		Routine loop;
		for (int i = 0; i < alRoutineList.size(); i++) {
			loop = alRoutineList.get(i);
			out += loop.toString();
		}
		out += "</ROUTINES>";
		return out;

	}

	public void toXML() {
		try {

			// root element
			Element rootElement = Toro.doc.createElement("ROUTINES");
			Toro.doc.appendChild(rootElement);
			Routine loop;
			for (int i = 0; i < alRoutineList.size(); i++) {
				loop = alRoutineList.get(i);
				rootElement.appendChild(loop.toXML());
				// out += loop.toXML();
			}
			// supercars element

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(Toro.doc);
			StreamResult result = new StreamResult(
					new File("C:\\Users\\roger\\Documents\\TSB\\toroRoutinesConfig.xml"));
			transformer.transform(source, result);

			// Output to console for testing
			StreamResult consoleResult = new StreamResult(System.out);
			transformer.transform(source, consoleResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void load() {

		try {
			File file = new File("C:\\Users\\roger\\Documents\\TSB\\toroRoutinesConfig.xml");
			// an instance of factory that gives a document builder

			Toro.doc = Toro.dBuilder.parse(file);
			Toro.doc.getDocumentElement().normalize();
			
			org.w3c.dom.NodeList nlRoot = Toro.doc.getElementsByTagName("ROUTINE");
			
			for (int i = 0; i < nlRoot.getLength(); i++) {
				Routine temp = new Routine();
				temp.load((Element)nlRoot.item(i));
				this.alRoutineList.add(temp);
				if(i==0) {
					currentRoutine = temp;
				}
			}
			return;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		/* in this method we will read the XML and load the routines into memory */
		// XmlDocument doc = new XmlDocument();
		// Toro.doc.(new
		// File("C:\\Users\\i810980\\Documents\\TSB\\toroRoutinesConfig.xml"));

		alRoutineList = new ArrayList<Routine>();
		Routine currentRoutine1 = new Routine();
		currentRoutine1.routineName = "zero";
		alRoutineList.add(currentRoutine1);

		// System.out.println("Constructor size: " + alRoutineList.size());

		// alRoutineList = new ArrayList<Routine>();
		currentRoutine = new Routine();
		currentRoutine.routineName = "one";
		alRoutineList.add(currentRoutine);

		// alRoutineList = new ArrayList<Routine>();
		currentRoutine = new Routine();
		currentRoutine.routineName = "two";
		alRoutineList.add(currentRoutine);

		// System.out.println("Constructor size: " + alRoutineList.size());

//		return alRoutineList;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		System.out.println("checking size: " + alRoutineList.size());
		return alRoutineList.size();
	}

	@Override
	public String getElementAt(int index) {
		// TODO Auto-generated method stub
		System.out.println("Returing Routine: " + alRoutineList.get(index).routineName);
		return alRoutineList.get(index).routineName;
	}

}
