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
		iNumberOfIterations = 100;
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
		int numberOfFailedAttemps = 0;
		for(int n = 0; this.rStart != null &&n < this.rStart.alStepList.size(); n++) {
			//hard reset is the easiest to code for
			this.doRunStep(this.rStart.alStepList.get(n),null);
		}
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
					numberOfFailedAttemps++;
					if(numberOfFailedAttemps > rMain.alStepList.size()) {
						//run error code
						for(int l = 0; this.rError != null && l < this.rError.alStepList.size(); l++) {
							//hard reset is the easiest to code for
							this.doRunStep(this.rError.alStepList.get(l),null);
						}
						//now we need the visual queue
						for(int m = 0; this.rStart != null && m < this.rStart.alStepList.size(); m++) {
							//hard reset is the easiest to code for
							this.doRunStep(this.rStart.alStepList.get(m),null);
						}
					}
					numberOfFailedAttemps = 0;
					System.out.println("Stoping Thread");
					System.out.println(loopCurrentStep.stepName + " iteration " + iNumberOfIterations + "  main step number: " + i);
					/*	int erroLoopStepAction;
					for(int l = 0; this.rError != null &&l < this.rError.getSize(); l++) {
						Step erroLoopCurrentStep = rError.alStepList.get(l);
						Step errorNextStep = null;
						if((l+1) <  rError.alStepList.size()) {
							errorNextStep = rError.alStepList.get(j+1);
						}else {
							//throw away variable
							errorNextStep = null;
						}
						erroLoopStepAction = this.doRunStep(erroLoopCurrentStep,errorNextStep);
						if(erroLoopStepAction < 3) {
							j--;
							continue continueFor;
							
						}
					}
					paused.set(true);
					synchronized (this) {
						// Pause
						try {
							Thread.currentThread().wait();
							//must call notify fu
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}*/
				}
				if(loopStepAction == 2) {
					j++;
					numberOfFailedAttemps = 0;
					continue;
					//loopNextStep.passAction.sendGCode();
				}
				if(loopStepAction == 1) {
					numberOfFailedAttemps = 0;
				//	loopCurrentStep.passAction.sendGCode();
				}
			}
		}
	}

	public int doRunStep(Step currentStep, Step nextStep) {
		long startTime = System.currentTimeMillis();
		long currentDuration = startTime - System.currentTimeMillis() - startTime;
		while (currentStep.timeOutML > currentDuration) {
			currentDuration =  System.currentTimeMillis() - startTime;
			if (paused.get()) {
				synchronized (this) {
					// Pause
					try {
						Thread.currentThread().wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			if (currentStep.execute()) {
				return 1;
			}
			
			
			

			// Sleep
			try {
				
				Thread.sleep(100);
				// continue;
			} catch (InterruptedException e) {
			}
			if(currentStep.lookFoward && nextStep!=null && currentStep.passAction.bLookAtNextStep) {
				if(nextStep.execute())
					return 2;
			}
			
		}
		

		return 3;

	}

}
