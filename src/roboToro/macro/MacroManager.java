package roboToro.macro;

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

import roboToro.*;

import java.io.File;

import javax.swing.AbstractListModel;

public class MacroManager {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int timeout;

	public String activeMacroName;
	public ArrayList<Macro> alMacroList;
	public Routine currentRoutine;

	public MacroManager() {
		// TODO Auto-generated constructor stub
		alMacroList = new ArrayList<Macro>();
		this.load();
	}

	public String toString() {
		String out = "<MACROS>";
		Macro loop;
		for (int i = 0; i < alMacroList.size(); i++) {
			loop = alMacroList.get(i);
			out += loop.toString();
		}
		out += "</MACROS>";
		return out;

	}

	public void toXML() {
		try {

			// root element
			Element rootElement = Toro.doc.createElement("MACROS");
			Toro.doc.appendChild(rootElement);
			Macro loop;
			for (int i = 0; i < alMacroList.size(); i++) {
				loop = alMacroList.get(i);
				rootElement.appendChild(loop.toXML());
				// out += loop.toXML();
			}
			// supercars element

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(Toro.doc);
			StreamResult result = new StreamResult(
					new File("C:\\Users\\i810980\\Documents\\TSB\\toroMacroConfig.xml"));
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
			File file = new File("C:\\Users\\i810980\\Documents\\TSB\\toroRoutinesConfig.xml");
			// an instance of factory that gives a document builder

			Toro.doc = Toro.dBuilder.parse(file);
			Toro.doc.getDocumentElement().normalize();
			
			org.w3c.dom.NodeList nlRoot = Toro.doc.getElementsByTagName("MACROS");
			
			for (int i = 0; i < nlRoot.getLength(); i++) {
				Macro temp = new Macro();
				temp.load((Element)nlRoot.item(i));
				this.alMacroList.add(temp);
				if(i==0) {
					//currentRoutine = temp;
				}
			}
			return;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	
}
