/**
 * 
 */
package roboToro;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;

import roboToro.Main.WindowInfo;
import roboToro.game.Game;
import roboToro.game.tbs2020.FixedUtil;
import roboToro.integration.gcode.GCodeComClient;
import roboToro.macro.Macro;
import roboToro.util.ToroUtils;

/* www.j  a v a 2  s .  c o m*/
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

/**
 * @author I810980
 *
 */
public class Toro {
	//FILE IO Section
	public static DocumentBuilderFactory dbFactory; 
	public static DocumentBuilder dBuilder;
	public static Document doc; 

	public static int MODE = 2;
	public static int ITERATIONS = 3000;
	public static long DEFAULT_STEP_TIMEOUT_ML = 20000; //20 seconds
	// subtract these numbers to focus on the pitching region
	// total image size is 478 x 960
	// 478
	public static int PITCHING_REGION_LEFT = 164;
	public static int PITCHING_REGION_TOP = 210;
	// this is the runway to home plate
	public static int PITCHING_REGION_WIDTH = 150;
	public static int PITCHING_REGION_LENGTH = 450;
	
	public static int LIVE_WINDOW_REFRESH_RATE = 100;
	
	//Window Name
	//public static String windowName = "Reflector 3 - I810980’s iPhone";
	
	//FileLocations
	public static String sWriteFileLocation = "C:\\Users\\roger\\Documents\\TSB\\";
	public static String sReadFileLocation = "C:\\Users\\roger\\Documents\\TSB\\";
	public static String sScreenCaptureLocation = "mime\\";
	public static int xVAR = 4;
	public static int yVAR = 2;
	
	public Robot robot;
	//public ArrayList <Routine> alRoutines;
	public RoutineManager rmRoutineManager;
	
	//Gametype definition
	public Game primeGame;
	public Game wohGame;
	public Game bonsusGame;
	
	//divice size MM
	//140 x 70 or 75
	
	//Threads
	public Thread phonePanelThread;
	public Thread singleMacroTestThread;
	
	//
	public roboToro.macro.Macro singleMacroTest;
	
	
	// variables
//	public Device deviceType;
	public String windowName = "Reflector 3 - I810980’s iPhone";
	//public ArrayLiat al
	//public static String windowName = "Document - WordPad";
	
	//private HWND hWnd;
	//private ToroUtils.WindowInfo gameWindowInfo;// = getWindowInfo(hWnd);
	//public ToroUtils toroUtils;
	
	//divice settings
	public static double DIVICE_WIDTH_MM = 70.9;
	public static double DIVICE_HEIGTH_MM = 143.6;
	
	public static double MAX_WIDTH__PIXEL = 606;
	public static double MAX_HEIGHT_PIXEL = 1218;
	
	public static double ACTUAL_PIXEL_WIDTH = .117;
	
	public static String DELTA_Z_CORD_DOWN = "-368";
	public static String DELTA_Z_CORD_UP = "-330";
	
	//Connections
	public static GCodeComClient comClient;
	
	public Toro() throws ParserConfigurationException, IOException {
		//hWnd = User32.INSTANCE.FindWindow(null, "Reflector 3 - I810980’s iPhone");
		//hWnd = User32.INSTANCE.FindWindow(null, windowName);
		//this.gameWindowInfo = ToroUtils.getWindowInfo(hWnd);
	//	toroUtils = new ToroUtils(windowName);
	//	Toro.ACTUAL_PIXEL_WIDTH = FixedUtil.round1((Toro.DIVICE_HEIGTH_MM / Toro.MAX_HEIGHT_PIXEL),2);
		
		comClient = new GCodeComClient();
		dbFactory = DocumentBuilderFactory.newInstance();
		dBuilder = dbFactory.newDocumentBuilder();
		doc = dBuilder.newDocument();

		rmRoutineManager = new RoutineManager();
		
		singleMacroTest = new Macro();
		//alRoutines = Routine.load();
		
		try {
			robot = new Robot();
		} catch (Exception ex) {

		}
		
	}

	public static void main(String[] args) {
		try {
			Toro toro = new Toro();
			
			ToroUtils img = new ToroUtils(toro.windowName);

			
			
			BufferedImage screen = toro.robot.createScreenCapture(img.rectangle);
				/*	
				 * 
				 * System.out.println(img.getRect()[0] + " " +
					img.getRect()[1] + " " +
					img.getRect()[2] + " " +
					img.getRect()[3]);
					
					new Rectangle(img.getRect()[0], 
							img.getRect()[1], 
							img.getRect()[2] - img.getRect()[0] , 
							img.getRect()[3] - img.getRect()[1]));*/
			// robot.createMultiResolutionScreenCapture(screenRect);

			new ScreenCaptureRectangle(screen);

//			try {
//				img.getWindowLocationAndSize(toro.windowName);
//				System.out.printf("The corner locations for the window \"%s\" are %s", toro.windowName,
//						img.rectangle.toString());
//			} catch (ToroUtils.WindowNotFoundException e) {
//				e.printStackTrace();
//			} catch (ToroUtils.GetWindowRectException e) {
//				e.printStackTrace();
//			}
			// Get Screen Location
		} catch (Exception ex) {

		}
	}
	
	
}



class ScreenCaptureRectangle {
	Rectangle captureRect;
	BufferedImage startStcreen;
	boolean bDragStarted;
	BufferedImage screenCopy;
	//JLabel screenLabel;

	ScreenCaptureRectangle(final BufferedImage screen) {
		screenCopy = new BufferedImage(screen.getWidth(), screen.getHeight(), screen.getType());
		screenCopy = screen;
		JLabel	screenLabel = new JLabel(new ImageIcon(screenCopy));
		JScrollPane screenScroll = new JScrollPane(screenLabel);
		
		if(startStcreen == null) {
			startStcreen = screen;
		}

		//screenScroll.setPreferredSize(new Dimension(1000, 1000));

		//repaint(screen, screenCopy);
		//screenLabel.repaint();

		
		screenLabel.addMouseMotionListener(new MouseMotionAdapter() {
			Point start = new Point();

			@Override
			public void mouseMoved(MouseEvent me) {
				JLabel screenLabel = new JLabel(new ImageIcon(screenCopy));
			// screenLabel.repaint();
			 start = me.getPoint();
				repaint(screenLabel, screen);
				screenLabel.repaint();
				//screenLabel.repaint();
			}

			@Override
			public void mouseDragged(MouseEvent me) {
				Point end = me.getPoint();
				JLabel screenLabel = new JLabel(new ImageIcon(screenCopy));
				captureRect = new Rectangle(start, new Dimension(end.x - start.x, end.y - start.y));
				repaint(screenLabel, screen);
				screenLabel.repaint();
			}
			
			
			
		});
		
		screenLabel.addMouseListener(new MouseListener() {
		
		@Override
		 public void mouseReleased(MouseEvent anEvent) {
	           // getHandler().mouseReleased(anEvent);
			System.out.println(captureRect);
	        }

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		});
		
		
		
		JOptionPane.showMessageDialog(null, screenScroll);
	}

	public void repaint(JLabel orig, BufferedImage copy) {
		Graphics2D g = copy.createGraphics();
	
		/*	g.drawImage(, 0, 0, null);
		g.setColor(Color.RED);
		if (captureRect == null) {
			return;
		}
		g.draw(captureRect);
		//g.setColor(new Color(25, 25, 23, 10));
	//	g.fill(captureRect);
		g.dispose();
		
		*/
		 orig.paintComponents(g);
		//orig.paintComponent(g);
	            if (copy != null) {
	                Graphics2D g2d = (Graphics2D) g.create();
	                g2d.drawImage(copy, 0, 0, null);
	                if (captureRect != null) {
	                    g2d.setColor(new Color(225, 225, 255, 128));
	                    g2d.fill(captureRect);
	                    g2d.setColor(Color.GRAY);
	                    g2d.draw(captureRect);
	                }
	                g2d.dispose();
	            }
	        }

	
}
