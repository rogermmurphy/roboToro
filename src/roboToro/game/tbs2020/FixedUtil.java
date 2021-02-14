package roboToro.game.tbs2020;

import java.io.StringReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.jna.Native;
import com.sun.jna.Structure;
import com.sun.jna.win32.StdCallLibrary;

import roboToro.Toro;
import roboToro.UI.PhonePanel;
import roboToro.sandbox.TranslucentPhonePanel;
import roboToro.sandbox.TranslucentWindowDemo;

public class FixedUtil {

	public static int MODE = 2;
	public static int ITERATIONS = 3000;
	// subtract these numbers to focus on the pitching region
	// total image size is 478 x 960
	// 478
	public static int PITCHING_REGION_LEFT = 164;
	public static int PITCHING_REGION_TOP = 210;
	// this is the runway to home plate
	public static int PITCHING_REGION_WIDTH = 150;
	public static int PITCHING_REGION_LENGTH = 450;

	public static int numberofpixels;

	public boolean isRecord;
	public boolean bShowLiveScreen;

	ArrayList<BufferedImage> capturedRecording;
	BufferedImage deltaCaptureHighlight;

	ArrayList<BufferedImage> strikeZoneCaptureHighlightAL;
	BufferedImage strikeZoneCaptureHighlight;

	public BufferedImage fScreateScreenCapture;
	public Robot robot;
	public WindowInfo windowInfo;
	private boolean lookforwindoweverytime;
	public static String windowName = "Reflector 3 - iPhone";

	public FixedUtil() {
		isRecord = false;
		bShowLiveScreen = true;

		capturedRecording = new ArrayList<BufferedImage>();
		deltaCaptureHighlight = null;

		strikeZoneCaptureHighlightAL = new ArrayList<BufferedImage>();
		strikeZoneCaptureHighlight = null;

		lookforwindoweverytime = false;
		// TODO Auto-generated constructor stub
		int hWnd = User32.instance.FindWindowA(null, windowName);
		// get window info
		windowInfo = getWindowInfo(hWnd);
		// move to forgournd
		User32.instance.SetForegroundWindow(windowInfo.hwnd);
		// Robot robot;
		try {
			robot = new Robot();
			fScreateScreenCapture = robot.createScreenCapture(new Rectangle(windowInfo.rect.left, windowInfo.rect.top,
					windowInfo.rect.right - windowInfo.rect.left, windowInfo.rect.bottom - windowInfo.rect.top));

		} catch (Exception ex) {
ex.printStackTrace();
		}
		// Robot robot = new Robot();

	}

	public static double round1(double input, int scale) {
		BigDecimal bigDecimal = new BigDecimal(input).setScale(scale, RoundingMode.HALF_EVEN);
		return bigDecimal.doubleValue();
	}

	public void setScreenCapture() {

		if (lookforwindoweverytime) {
			int hWnd = User32.instance.FindWindowA(null, windowName);
			// get window info
			windowInfo = getWindowInfo(hWnd);
		}
		try {

			fScreateScreenCapture = robot.createScreenCapture(new Rectangle(windowInfo.rect.left, windowInfo.rect.top,
					windowInfo.rect.right - windowInfo.rect.left, windowInfo.rect.bottom - windowInfo.rect.top));

		} catch (Exception ex) {
			ex.printStackTrace();

		}
	}

	public void setRecordWindow(JPanel uxImagePanel) {
		try {
			capturedRecording = new ArrayList<BufferedImage>();

			while (isRecord) {

				this.setScreenCapture();
				capturedRecording.add(this.fScreateScreenCapture);
				// uxImagePanel.removeAll();
				JLabel screenLabel = new JLabel(new ImageIcon(this.fScreateScreenCapture));
				uxImagePanel.removeAll();
				uxImagePanel.add(screenLabel);
				uxImagePanel.revalidate();
				uxImagePanel.repaint();

				// Thread.currentThread().wait(10);
			}

		} catch (Exception ex) {
			ex.printStackTrace();

		}
	}

	public void setLiveWindow(PhonePanel uxImagePanel) {
		try {
			capturedRecording = new ArrayList<BufferedImage>();

			while (bShowLiveScreen) {

				this.setScreenCapture();
				// capturedRecording.add(this.fScreateScreenCapture);
				// uxImagePanel.removeAll();
				// JLabel screenLabel = new JLabel(new ImageIcon(this.fScreateScreenCapture));
				if (this.fScreateScreenCapture == null) {
					bShowLiveScreen = false;
					System.out.println("UX window is not found Runing UX Fix Scropt");
				}
				uxImagePanel.image = this.fScreateScreenCapture;
				// uxImagePanel.removeAll();
				// uxImagePanel.add(screenLabel);
				uxImagePanel.revalidate();
				uxImagePanel.repaint();
				// Thread.currentThread();
			//	Thread.sleep(Toro.LIVE_WINDOW_REFRESH_RATE);
				// Thread.scurrentThread().wait(10);
			}

		} catch (Exception ex) {
			ex.printStackTrace();

		}
	}

	public void setTransperentWindow(TranslucentWindowDemo tw) {
		try {
			capturedRecording = new ArrayList<BufferedImage>();

			//while (bShowLiveScreen) {

				this.setScreenCapture();
				// capturedRecording.add(this.fScreateScreenCapture);
				// uxImagePanel.removeAll();
				// JLabel screenLabel = new JLabel(new ImageIcon(this.fScreateScreenCapture));
				if (this.fScreateScreenCapture == null) {
					bShowLiveScreen = false;
					System.out.println("UX window is not found Runing UX Fix Scropt");
				}
				
				tw.setBounds(windowInfo.rect.left, windowInfo.rect.top,
						windowInfo.rect.right - windowInfo.rect.left, windowInfo.rect.bottom - windowInfo.rect.top);
				//uxImagePanel.set
				
			//	tw.image = this.fScreateScreenCapture;
				// uxImagePanel.removeAll();
				// uxImagePanel.add(screenLabel);
				tw.revalidate();
				tw.repaint();
				// Thread.currentThread();
			//	Thread.sleep(Toro.LIVE_WINDOW_REFRESH_RATE);
				// Thread.scurrentThread().wait(10);
			//}

		} catch (Exception ex) {
			ex.printStackTrace();

		}
	}

	public static double compaireImage(BufferedImage img1, BufferedImage img2) {
		// System.out.println("compaireImage function start");
		double avg = 0;
		double percentage = 0;
		long bla;
		int w1 = img1.getWidth();
		int w2 = img2.getWidth();
		int h1 = img1.getHeight();
		int h2 = img2.getHeight();
		if ((w1 != w2) || (h1 != h2)) {
			//System.out.println("Both images should have same dimwnsions: w1:" + w1 + "  w2" + w2);
			percentage = 3000;
		} else {
			long diff = 0;
			for (int j = 0; j < h1; j++) {
				for (int i = 0; i < w1; i++) {
					// Getting the RGB values of a pixel
					int pixel1 = img1.getRGB(i, j);
					Color color1 = new Color(pixel1, true);
					int r1 = color1.getRed();
					int g1 = color1.getGreen();
					int b1 = color1.getBlue();
					int pixel2 = img2.getRGB(i, j);
					Color color2 = new Color(pixel2, true);
					int r2 = color2.getRed();
					int g2 = color2.getGreen();
					int b2 = color2.getBlue();
					// sum of differences of RGB values of the two images
					long data = Math.abs(r1 - r2) + Math.abs(g1 - g2) + Math.abs(b1 - b2);
					diff = diff + data;
				}
			}
			avg = diff / (w1 * h1 * 3);
			percentage = (avg / 255) * 100;
			// System.out.println("Difference: " + percentage);
		}
		return percentage;
	}

	public static WindowInfo getWindowInfo(int hWnd) {
		RECT r = new RECT();
		User32.instance.GetWindowRect(hWnd, r);
		byte[] buffer = new byte[1024];
		User32.instance.GetWindowTextA(hWnd, buffer, buffer.length);
		String title = Native.toString(buffer);
		WindowInfo info = new WindowInfo(hWnd, r, title);
		return info;
	}

	public static interface WndEnumProc extends StdCallLibrary.StdCallCallback {
		boolean callback(int hWnd, int lParam);
	}

	public static interface User32 extends StdCallLibrary {
		public static final String SHELL_TRAY_WND = "Shell_TrayWnd";
		public static final int WM_COMMAND = 0x111;
		public static final int MIN_ALL = 0x1a3;
		public static final int MIN_ALL_UNDO = 0x1a0;

		final User32 instance = (User32) Native.loadLibrary("user32", User32.class);

		boolean EnumWindows(WndEnumProc wndenumproc, int lParam);

		boolean IsWindowVisible(int hWnd);

		int GetWindowRect(int hWnd, RECT r);

		void GetWindowTextA(int hWnd, byte[] buffer, int buflen);

		int GetTopWindow(int hWnd);

		int GetWindow(int hWnd, int flag);

		boolean ShowWindow(int hWnd);

		boolean BringWindowToTop(int hWnd);

		int GetActiveWindow();

		boolean SetForegroundWindow(int hWnd);

		int FindWindowA(String winClass, String title);

		long SendMessageA(int hWnd, int msg, int num1, int num2);

		final int GW_HWNDNEXT = 2;
	}

	public static class RECT extends Structure {
		public int left, top, right, bottom;

		@Override
		protected List<String> getFieldOrder() {
			List<String> order = new ArrayList<>();
			order.add("left");
			order.add("top");
			order.add("right");
			order.add("bottom");
			return order;
		}
	}

	public static class WindowInfo {
		int hwnd;
		RECT rect;
		String title;

		public WindowInfo(int hwnd, RECT rect, String title) {
			this.hwnd = hwnd;
			this.rect = rect;
			this.title = title;
		}

		public String toString() {
			return String.format("(%d,%d)-(%d,%d) : \"%s\"", rect.left, rect.top, rect.right, rect.bottom, title);
		}
	}

	public static Document convertStringToXMLDocument(String xmlString) {
		// Parser that produces DOM object trees from XML content

		try {
			// Create DocumentBuilder with default configuration

			// factory.
			// builder.par

			// Parse the content to Document object
			// doc = //roboToro.Toro.b.parse((new StringReader(xmlString)));
			// Toro.doc.
			return Toro.doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
