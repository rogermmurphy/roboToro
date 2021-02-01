package roboToro.macro;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import roboToro.Action;
import roboToro.Routine;
import roboToro.Step;
import roboToro.Toro;

public class Macro implements Runnable {

	public Routine rStart;
	public Routine rMain;
	public Routine rError;
	public int iNumberOfIterations;
	public boolean rErrorCallBack;
	public boolean allowInterupt;
	public boolean isActive;
	public String sMacroName;

	public AtomicBoolean paused;
	public AtomicBoolean kill;

	public Macro() {
		// TODO Auto-generated constructor stub
		paused = new AtomicBoolean(true);
		paused = new AtomicBoolean(false);
		allowInterupt = false;
		rErrorCallBack = false;
		iNumberOfIterations = 5;
	}

	public synchronized Element toXML() throws ParserConfigurationException {

		// root element
		Element rootElement = Toro.doc.createElement("ROUTINE");
		Element name = Toro.doc.createElement("ROUTINENAME");
		rootElement.appendChild(name);
		name.appendChild(Toro.doc.createTextNode(sMacroName));
		// name.setNodeValue(routineName);

		Element routineTimeout = Toro.doc.createElement("TIMEOUT");
		rootElement.appendChild(routineTimeout);
		// routineTimeout.appendChild(Toro.doc.createTextNode(String.valueOf(timeout)));

		// Element element = new Element("ROUTINE");
		return rootElement;
	}

	public synchronized void load(Element root) {
		sMacroName = root.getElementsByTagName("MACRONAME").item(0).getTextContent();

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		// execute rStart;
		// skip initialization for now
		boolean firstTime = false;
		for (int i = 0; i < iNumberOfIterations; i++) {

			if (firstTime) {
				firstTime = false;
				for (int j = 0; i < rStart.alStepList.size(); j++) {
					/*if (rStart.alStepList.get(j).execute() == 3) {
						//Thread.currentThread().interrupt();
						System.out.println("should not reach this code");
					}*/
				}
				
			}
			Step loopCurrentStep;
			Step loopNextStep;
			for (int j = 0; j < rMain.alStepList.size(); j++) {
				loopCurrentStep = rMain.alStepList.get(j);
				if((j+1) <  rMain.alStepList.size()) {
					loopNextStep = rMain.alStepList.get(j+1);
				}else {
					//throw away variable
					loopNextStep = null;
				}
				int loopStepAction = this.doRunStep(loopCurrentStep,loopNextStep);
				if (loopStepAction == 3) {
					System.out.println("Not but should Stoping Thread... go to edit screen and either add error code for this situation or modifiy to fit");
					try {
						Thread.currentThread().wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if(loopStepAction == 2) {
					j++;
					loopNextStep.passAction.sendGCode();
				}
				if(loopStepAction == 1) {
					loopCurrentStep.passAction.sendGCode();
				}
			}
		}
	}

	public int doRunStep(Step currentStep, Step nextStep) {
		long startTime = System.currentTimeMillis();
		long currentDuration = startTime - System.currentTimeMillis();
		while (currentStep.timeOutML > currentDuration) {
			currentDuration = startTime - System.currentTimeMillis();
			if (paused.get()) {
				synchronized (this) {
					// Pause
					try {
						Thread.currentThread().wait();
					} catch (InterruptedException e) {
					}
				}
			}
			if (currentStep.execute()) {
				return 1;
			}

			// Sleep
			try {
				if(currentStep.lookFoward && nextStep!=null) {
					if(nextStep.execute())
						return 2;
				}
				Thread.sleep(500);
				// continue;
			} catch (InterruptedException e) {
			}
		}
		return 3;

	}

}
