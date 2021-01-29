package roboToro;


import java.awt.AWTException;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import com.sun.jna.Native;
import com.sun.jna.Structure;
import com.sun.jna.win32.StdCallLibrary;

public class Main {
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

	public static void main(String[] args) throws AWTException, IOException, InterruptedException {

		if (true) {
			System.out.println("create recording");
			createRecordingOnly();
			System.out.println("end recording");
			return;
		}
		// Load Data
		// Load Fastball
		// need to make this image a sub image of just the strike zone...
		// ..heat signature changes as the computer slows down from so many comparisons
		BufferedImage fastball = ImageIO.read(new File("C:\\Users\\i810980\\Documents\\TSB\\Fastball\\FastBall1.png"));
		// load curve ball
		// load others

		// get screen handle
		int hWnd = User32.instance.FindWindowA(null, "Reflector 3 - I810980’s iPhone");
		// get window info
		WindowInfo w = getWindowInfo(hWnd);
		// move to forgournd
		User32.instance.SetForegroundWindow(w.hwnd);
		BufferedImage LastCapture = null;
		BufferedImage deltaCapture = null;
		BufferedImage createScreenCapture;
		// BufferedImage ceateScreenCapture;
		double percent = 100;
		for (int i = 0; i < Main.ITERATIONS; i++) {
			try {
				// get screen capture for current screen screen
				// this will need to run in memory and keep in memory
				// 1 for decision making compare screen
				// 2 for active game more determine when to swing

				// full screen capture
				BufferedImage FScreateScreenCapture = new Robot().createScreenCapture(
						new Rectangle(w.rect.left, w.rect.top, w.rect.right - w.rect.left, w.rect.bottom - w.rect.top));

				// strike zone capture
				createScreenCapture = new Robot().createScreenCapture(
						new Rectangle(w.rect.left + Main.PITCHING_REGION_LEFT, w.rect.top + Main.PITCHING_REGION_TOP,
								Main.PITCHING_REGION_WIDTH, Main.PITCHING_REGION_LENGTH));
				ImageIO.write(createScreenCapture, "png",
						new File("C:\\Users\\i810980\\Documents\\TSB\\" + i + "createScreenCapture" + i + ".png"));
				if (Main.MODE == 2) {
					ImageIO.write(createScreenCapture, "png", new File(
							"C:\\Users\\i810980\\Documents\\TSB\\" + i + "RecodeEvent_SubScreen" + i + ".png"));
					ImageIO.write(FScreateScreenCapture, "png", new File(
							"C:\\Users\\i810980\\Documents\\TSB\\" + i + "RecodeEvent_FullScreen" + i + ".png"));
					// continue;
				}
				int amoutofdifference = 0;
				if (LastCapture != null) {
					deltaCapture = getDifferenceImage(LastCapture, createScreenCapture);

					// getDifferenceImage(deltaCapture, fastball, amoutofdifference);
					// percent = getDifferencePercent(fastball, deltaCapture);
					// System.out.println(percent);

					if (percent < 2.41 || Main.MODE == 2) {
						System.out.println(percent);
						// System.out.println(i+" strike " + Main.numberofpixels);
						ImageIO.write(deltaCapture, "png",
								new File("C:\\Users\\i810980\\Documents\\TSB\\" + i + "deltaCapture" + i + ".png"));

						ImageIO.write(createScreenCapture, "png", new File(
								"C:\\Users\\i810980\\Documents\\TSB\\" + i + "createScreenCapture" + i + ".png"));

						ImageIO.write(FScreateScreenCapture, "png", new File(
								"C:\\Users\\i810980\\Documents\\TSB\\" + i + "FScreateScreenCapture" + i + ".png"));

					}
					// ImageIO.write(deltaCapture, "png",
					// new File("C:\\Users\\i810980\\Documents\\TSB\\DeltaImagesSub" + i + ".png"));
				} else {
					// use these dimintions to get a subset image
					// less 185 left
					// less 200 top
					// 150 width
					// 400 length
					// System.out.println(w.rect.left + " " + w.rect.top + " " + ( w.rect.right -
					// w.rect.left) + " " + ( w.rect.bottom - w.rect.top));
				}
				LastCapture = createScreenCapture;
			} catch (Exception e) {
				System.out.println("Exception occured :" + e.getMessage());
			}
			// System.out.println("Images were wbritten succesfully.");
		}

	}

	private static void createRecordingOnly() throws AWTException, IOException, InterruptedException {
		// Load Data
		// Load Fastball
		// need to make this image a sub image of just the strike zone...
		// ..heat signature changes as the computer slows down from so many comparisons
		BufferedImage strikeZoneBI = ImageIO
				.read(new File("C:\\Users\\i810980\\Documents\\TSB\\Images\\StrikeZone.png"));

		BufferedImage FB1 = ImageIO.read(new File("C:\\Users\\i810980\\Documents\\TSB\\Images\\FB1.png"));

		BufferedImage FB2 = ImageIO.read(new File("C:\\Users\\i810980\\Documents\\TSB\\Images\\FB2.png"));

		BufferedImage FB3 = ImageIO.read(new File("C:\\Users\\i810980\\Documents\\TSB\\Images\\FB3.png"));

		BufferedImage FB4 = ImageIO.read(new File("C:\\Users\\i810980\\Documents\\TSB\\Images\\FB4.png"));

		BufferedImage FB5 = ImageIO.read(new File("C:\\Users\\i810980\\Documents\\TSB\\Images\\FB5.png"));
		// load curve ball
		// load others

		// get screen handle
		int hWnd = User32.instance.FindWindowA(null, "Reflector 3 - I810980’s iPhone");
		// get window info
		WindowInfo w = getWindowInfo(hWnd);
		// move to forgournd
		User32.instance.SetForegroundWindow(w.hwnd);
		// ArrayList<BufferedImage> lastCaptureAL = new ArrayList<BufferedImage>();

		ArrayList<BufferedImage> deltaCaptureHighlightAL = new ArrayList<BufferedImage>();
		BufferedImage deltaCaptureHighlight = null;

		ArrayList<BufferedImage> strikeZoneCaptureHighlightAL = new ArrayList<BufferedImage>();
		BufferedImage strikeZoneCaptureHighlight = null;

		ArrayList<BufferedImage> fScreateScreenCaptureAL = new ArrayList<BufferedImage>();
		BufferedImage fScreateScreenCapture;

		ArrayList<BufferedImage> subScreenCaptureNoHighlightAL = new ArrayList<BufferedImage>();
		BufferedImage subScreenCaptureNoHighlight;

		ArrayList<BufferedImage> swingImagesAL = new ArrayList<BufferedImage>();

		BufferedImage lastCaptureNoHighlight = null;
		Robot robot = new Robot();
		// BufferedImage ceateScreenCapture;
		double percent = 100;

		for (int i = 0; i < Main.ITERATIONS; i++) {
			try {
				// System.out.println("Recording: " + i);

				fScreateScreenCapture = robot.createScreenCapture(
						new Rectangle(w.rect.left, w.rect.top, w.rect.right - w.rect.left, w.rect.bottom - w.rect.top));

				// fScreateScreenCaptureAL.add(fScreateScreenCapture);
				subScreenCaptureNoHighlight = fScreateScreenCapture.getSubimage(PITCHING_REGION_LEFT,
						Main.PITCHING_REGION_TOP, Main.PITCHING_REGION_WIDTH, Main.PITCHING_REGION_LENGTH); // , w,
																											// h)(PITCHING_REGION_LEFT,
																											// Main.PITCHING_REGION_TOP,
				// w, h,
				// rgbArray,
				// offset,
				// scansize)
				// subScreenCaptureNoHighlightAL.add(subScreenCaptureNoHighlight);

				strikeZoneCaptureHighlight = getDifferenceImage(subScreenCaptureNoHighlight, strikeZoneBI);
				// strikeZoneCaptureHighlightAL.add(strikeZoneCaptureHighlight);

				int amoutofdifference = 0;
				if (lastCaptureNoHighlight != null) {
					// the dirrerence between last ball and this ball
					deltaCaptureHighlight = getDifferenceImage(lastCaptureNoHighlight, subScreenCaptureNoHighlight);
					// is this a valid hit
					// System.out.println(i + "interation number of generated red px " +
					// Main.numberofpixels);
					// the difference between
					// deltaCaptureHighlightAL.add(deltaCaptureHighlight);

					if (Main.numberofpixels < 500) {

						double matchFB1 = getMatchingRed(deltaCaptureHighlight, FB1);
						// System.out.println(i + " iteration: Number of PX Changed " +
						// Main.numberofpixels + " Number that Match " + matchFB1);
						if ((matchFB1 / Main.numberofpixels) > .3) {
							System.out.println("Fastball Swing FB1");
							swingImagesAL.add(subScreenCaptureNoHighlight);
							swingImagesAL.add(deltaCaptureHighlight);
							swingImagesAL.add(fScreateScreenCapture);
							continue;

						}
						double matchFB2 = getMatchingRed(deltaCaptureHighlight, FB2);

						// System.out.println(i + " iteration: Number of PX Changed " +
						// Main.numberofpixels + " Number that Match " + matchFB1);
						if ((matchFB1 / Main.numberofpixels) > .3) {
							System.out.println("Fastball Swing FB2");
							swingImagesAL.add(subScreenCaptureNoHighlight);
							swingImagesAL.add(deltaCaptureHighlight);
							swingImagesAL.add(fScreateScreenCapture);
							continue;

						}
						double matchFB3 = getMatchingRed(deltaCaptureHighlight, FB3);

						// System.out.println(i + " iteration: Number of PX Changed " +
						// Main.numberofpixels + " Number that Match " + matchFB1);
						if ((matchFB1 / Main.numberofpixels) > .3) {
							System.out.println("Fastball Swing FB3");
							swingImagesAL.add(subScreenCaptureNoHighlight);
							swingImagesAL.add(deltaCaptureHighlight);
							swingImagesAL.add(fScreateScreenCapture);
							continue;

						}
					}
					// compare this ball to the strikezone
					// subScreenCaptureNoHighlightAL.add(subScreenCaptureNoHighlight);
					// getDifferenceImage(deltaCapture, fastball, amoutofdifference); //percent =
					// getDifferencePercent(strikeZoneBI, deltaCapture);
					// System.out.println("Interation: " + i +" Matching Red PX: " +
					// System.out.println(i + " FB1: " + getMatchingRed(deltaCaptureHighlight,
					// FB1));
					// System.out.println(i + " FB2: " + getMatchingRed(deltaCaptureHighlight,
					// FB2));
					// System.out.println(i + " FB3: " + getMatchingRed(deltaCaptureHighlight,
					// FB3));
					// System.out.println(i + " FB4: " + getMatchingRed(deltaCaptureHighlight,
					// FB4));
					// System.out.println(i + " FB5: " + getMatchingRed(deltaCaptureHighlight,
					// FB5));

				}
				lastCaptureNoHighlight = subScreenCaptureNoHighlight;

			} catch (Exception e) {
				System.out.println("Exception occured :" + e.getMessage());
			}
			// System.out.println("Images were wbritten succesfully.");
		}
		for (int i = 0; i < swingImagesAL.size(); i++) {
			ImageIO.write(swingImagesAL.get(i), "png",
					new File("C:\\Users\\i810980\\Documents\\TSB\\" + i + "swingImagesAL.png"));
			/*
			 * System.out.println("Printing: " + i); // System.out.println(percent); //
			 * System.out.println(i+" strike " + Main.numberofpixels);
			 * ImageIO.write(subScreenCaptureNoHighlightAL.get(i), "png", new
			 * File("C:\\Users\\i810980\\Documents\\TSB\\" + i + "
			 * subScreenCaptureNoHighlightAL.png"));
			 * 
			 * ImageIO.write(fScreateScreenCaptureAL.get(i), "png", new
			 * File("C:\\Users\\i810980\\Documents\\TSB\\" + i + "fScreateScreenCaptureAL.
			 * png"));
			 * 
			 * ImageIO.write(strikeZoneCaptureHighlightAL.get(i), "png", new
			 * File("C:\\Users\\i810980\\Documents\\TSB\\" + i + "
			 * strikeZoneCaptureHighlightAL.png"));
			 * 
			 * if(i>0) { ImageIO.write(deltaCaptureHighlightAL.get(i-1), "png", new
			 * File("C:\\Users\\i810980\\Documents\\TSB\\" + i + "deltaCaptureHighlightAL.
			 * png")); }
			 */

		}

	}

	private static double getDifferencePercent(BufferedImage img1, BufferedImage img2) {
		int width = img1.getWidth();
		int height = img1.getHeight();
		int width2 = img2.getWidth();
		int height2 = img2.getHeight();
		if (width != width2 || height != height2) {
			throw new IllegalArgumentException(String.format(
					"Images must have the same dimensions: (%d,%d) vs. (%d,%d)", width, height, width2, height2));
		}

		// only checking every 5 pixels... less accurate but faster
		long diff = 0;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				diff += pixelDiff(img1.getRGB(x, y), img2.getRGB(x, y));
			}
		}
		long maxDiff = 3L * 255 * (width) * (height);

		return 100.0 * diff / maxDiff;
	}

	public static double getMatchingRed(BufferedImage img1, BufferedImage img2) {
		double matchingRed = 0;
		// convert images to pixel arrays...
		final int w = img1.getWidth(), h = img1.getHeight(), highlight = Color.MAGENTA.getRGB();
		final int[] p1 = img1.getRGB(0, 0, w, h, null, 0, w);
		final int[] p2 = img2.getRGB(0, 0, w, h, null, 0, w);
		// compare img1 to img2, pixel by pixel. If different, highlight img1's pixel...
		for (int i = 0; i < p1.length; i++) {
			if (p1[i] == p2[i] && p1[i] == Color.MAGENTA.getRGB()) {
				// p1[i] = highlight;
				matchingRed++;
				// System.out.println(numberofpix + " ");
			}
		}
		// save img1's pixels to a new BufferedImage, and return it...
		// (May require TYPE_INT_ARGB)
		// final BufferedImage out = new BufferedImage(w, h,
		// BufferedImage.TYPE_INT_RGB);
		// out.setRGB(0, 0, w, h, p1, 0, w);
		return matchingRed;
	}

	private static int pixelDiff(int rgb1, int rgb2) {
		int r1 = (rgb1 >> 16) & 0xff;
		int g1 = (rgb1 >> 8) & 0xff;
		int b1 = rgb1 & 0xff;
		int r2 = (rgb2 >> 16) & 0xff;
		int g2 = (rgb2 >> 8) & 0xff;
		int b2 = rgb2 & 0xff;
		return Math.abs(r1 - r2) + Math.abs(g1 - g2) + Math.abs(b1 - b2);
	}

	private static void listAllWindows() throws AWTException, IOException {
		final List<WindowInfo> inflList = new ArrayList<WindowInfo>();
		final List<Integer> order = new ArrayList<Integer>();
		int top = User32.instance.GetTopWindow(0);
		while (top != 0) {
			order.add(top);
			top = User32.instance.GetWindow(top, User32.GW_HWNDNEXT);
		}

		User32.instance.EnumWindows(new WndEnumProc() {
			public boolean callback(int hWnd, int lParam) {
				WindowInfo info = getWindowInfo(hWnd);
				inflList.add(info);
				return true;
			}

		}, 0);
		Collections.sort(inflList, new Comparator<WindowInfo>() {
			public int compare(WindowInfo o1, WindowInfo o2) {
				return order.indexOf(o1.hwnd) - order.indexOf(o2.hwnd);
			}
		});
		for (WindowInfo w : inflList) {
			System.out.println(w);
		}
	}

	public static BufferedImage getDifferenceImage(BufferedImage img1, BufferedImage img2) {
		numberofpixels = 0;
		// convert images to pixel arrays...
		final int w = img1.getWidth(), h = img1.getHeight(), highlight = Color.MAGENTA.getRGB();
		final int[] p1 = img1.getRGB(0, 0, w, h, null, 0, w);
		final int[] p2 = img2.getRGB(0, 0, w, h, null, 0, w);
		// compare img1 to img2, pixel by pixel. If different, highlight img1's pixel...
		for (int i = 0; i < p1.length; i++) {
			if (p1[i] != p2[i]) {
				p1[i] = highlight;
				Main.numberofpixels++;
				// System.out.println(numberofpix + " ");
			}
		}
		// save img1's pixels to a new BufferedImage, and return it...
		// (May require TYPE_INT_ARGB)
		final BufferedImage out = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		out.setRGB(0, 0, w, h, p1, 0, w);
		return out;
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
}