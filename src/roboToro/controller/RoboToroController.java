package roboToro.controller;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.UIManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import roboToro.Toro;
import roboToro.UI.ToroUI;
import roboToro.game.tbs2020.FixedUtil;

public class RoboToroController {

	public RoboToroController() {
		// TODO Auto-generated constructor stub
	}
	
	//DataModel Variables
	
	
	//Integration Decelerations
	
	
	//UX Decelerations
	
	
	//Thread Decelerations 
	public Thread phonePanelThread;
	public Thread singleMacroTestThread;
	public ArrayList<Thread> tMacroThreads;
//	public Thread phonePanelThread;
	
	//static Variable Decelerations
	public static DocumentBuilderFactory dbFactory;
	public static DocumentBuilder dBuilder;
	public static Document doc;


	
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					ToroUI window = new ToroUI();
					ToroUI.toro = new Toro();
					/*
					 * fixedUtil = new FixedUtil();
					 * 
					 * try { initialize(); phonePanelThread = new Thread(() ->
					 * fixedUtil.setLiveWindow(this.uxPhonePanel)); toro.phonePanelThread.start(); }
					 * catch (Exception ex) { ex.printStackTrace(); }
					 * 
					 * window.frame.setVisible(true);
					 */
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}
	
	/*
	btnStartMacro.addMouseListener(new java.awt.event.MouseAdapter() {
	    public void mouseClicked(java.awt.event.MouseEvent evt) {
	    	System.out.println("Strating Thread");
			toro.singleMacroTestThread = new Thread(toro.singleMacroTest);
			toro.singleMacroTestThread.start();
	    }
	});*/

}
