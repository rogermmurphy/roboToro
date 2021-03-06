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

	public boolean checkMainAll;

	public AtomicBoolean paused;
	public AtomicBoolean kill;

	public Macro() {
		// TODO Auto-generated constructor stub
		paused = new AtomicBoolean(true);
		paused = new AtomicBoolean(false);
		allowInterupt = false;
		rErrorCallBack = false;
		iNumberOfIterations = 1000;
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

	private int doRunLinear() {
		long startTime = System.currentTimeMillis();
		long currentDuration = startTime - System.currentTimeMillis() - startTime;

		// TODO Auto-generated method stub
		for (int j = 0; j < rMain.alStepList.size(); j++) {
			Step loopCurrentStep = rMain.alStepList.get(j);
			if (loopCurrentStep.execute()) {
				return 1;
			}

		}

		return 0;
	}
	
	private boolean doRunLinearWithPause(ArrayList<Step> slSteps) {
		for (int j = 0; j < slSteps.size(); j++) {
			Step loopCurrentStep = slSteps.get(j);
			if (loopCurrentStep.executeWithPause(1200)) {
				
			}
			try {
				Thread.sleep(10000);
			}catch (Exception ex) {
				ex.printStackTrace();
			}

		}
		
		
		return true;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		// execute rStart;
		// skip initialization for now
		boolean firstTime = false;
		int numberOfFailedAttemps = 0;
		for (int n = 0; this.rStart != null && n < this.rStart.alStepList.size(); n++) {
			// hard reset is the easiest to code for
			this.doRunStep(this.rStart.alStepList.get(n), null);
		}

		for (int i = 0; i < iNumberOfIterations; i++) {
			if (Toro.runLinerar) {
				double timestart = System.currentTimeMillis();
				boolean runErrorCode = true;
				while (Toro.DEFAULT_STEP_TIMEOUT_ML > System.currentTimeMillis() - timestart) {
					// throw away variable
					if (this.doRunLinear() == 1) {
						runErrorCode = false;
					}
					// break;
				}
				if(runErrorCode && this.rError != null) {
					this.doRunLinearWithPause(this.rError.alStepList);
				}
			} else {

				Step loopCurrentStep;
				Step loopNextStep = null;

				for (int j = 0; j < rMain.alStepList.size(); j++) {
					loopCurrentStep = rMain.alStepList.get(j);
					if ((j + 1) < rMain.alStepList.size()) {
						loopNextStep = rMain.alStepList.get(j + 1);
					}

					try {
						Thread.sleep(150);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					int loopStepAction = this.doRunStep(loopCurrentStep, loopNextStep);
					if (loopStepAction == 3) {
						numberOfFailedAttemps++;
						if (numberOfFailedAttemps > rMain.alStepList.size()) {
							numberOfFailedAttemps = 0;
							// run error code
							for (int l = 0; this.rError != null && l < this.rError.alStepList.size(); l++) {
								// hard reset is the easiest to code for
								this.doRunStep(this.rError.alStepList.get(l), null);
								try {
									Thread.sleep(150);
								} catch (Exception ex) {
									ex.printStackTrace();
								}
							}
							// now we need the visual queue
							for (int m = 0; this.rStart != null && m < this.rStart.alStepList.size(); m++) {
								// hard reset is the easiest to code for
								this.doRunStep(this.rStart.alStepList.get(m), null);
							}
						}

						System.out.println("Stoping Thread");
						System.out.println(loopCurrentStep.stepName + " iteration " + iNumberOfIterations
								+ "  main step number: " + i);
						/*
						 * int erroLoopStepAction; for(int l = 0; this.rError != null &&l <
						 * this.rError.getSize(); l++) { Step erroLoopCurrentStep =
						 * rError.alStepList.get(l); Step errorNextStep = null; if((l+1) <
						 * rError.alStepList.size()) { errorNextStep = rError.alStepList.get(j+1); }else
						 * { //throw away variable errorNextStep = null; } erroLoopStepAction =
						 * this.doRunStep(erroLoopCurrentStep,errorNextStep); if(erroLoopStepAction < 3)
						 * { j--; continue continueFor;
						 * 
						 * } } paused.set(true); synchronized (this) { // Pause try {
						 * Thread.currentThread().wait(); //must call notify fu } catch
						 * (InterruptedException e) { e.printStackTrace(); } }
						 */
					}
					if (loopStepAction == 2) {
						j++;
						numberOfFailedAttemps = 0;
						continue;
						// loopNextStep.passAction.sendGCode();
					}
					if (loopStepAction == 1) {
						numberOfFailedAttemps = 0;
						// loopCurrentStep.passAction.sendGCode();
					}
				}
			}
		}
	}

	public int doRunStep(Step currentStep, Step nextStep) {
		long startTime = System.currentTimeMillis();
		long currentDuration = startTime - System.currentTimeMillis() - startTime;
		while (currentStep.passAction.timeOutML > currentDuration) {
			currentDuration = System.currentTimeMillis() - startTime;
			if (paused.get()) {
				synchronized (this) {
					// Pause
					try {
						// Thread.currentThread().wait();
						// toro.singleMacroTest
						Thread.sleep(100);

					} catch (InterruptedException e) {
						// e.printStackTrace();
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
			if (currentStep.lookFoward && nextStep != null && currentStep.passAction.bLookAtNextStep) {
				if (nextStep.execute())
					return 2;
			}

		}

		return 3;
	}

}
