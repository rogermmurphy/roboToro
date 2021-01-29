package roboToro.util;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Native;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.Win32Exception;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.POINT;
import com.sun.jna.platform.win32.WinDef.RECT;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

import roboToro.Main.WindowInfo;
import roboToro.Main.WndEnumProc;
import roboToro.sandbox.GetWindowRect;
import roboToro.sandbox.GetWindowRect.GetWindowRectException;
import roboToro.sandbox.GetWindowRect.User32;
import roboToro.sandbox.GetWindowRect.WindowNotFoundException;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

import java.awt.*;

public class ToroUtils {

	public Rectangle rectangle;
	public boolean isScreenRecording;
	ArrayList<BufferedImage> alimgScreenRecording;
	// WinDef.RECT rect;// = new WinDef.RECT();

	public ToroUtils(String windowName) {
		isScreenRecording = false;
		HWND hWnd = User32.instance.FindWindowA(null, windowName);

//		// get window info
		WindowInfo w = ToroUtils.getWindowInfo(hWnd);
//		// move to forgournd
		User32.instance.SetForegroundWindow(w.hwnd);
		// rect = new WinDef.RECT();
		// .GetClientRect(hWnd, rect);
		// User32ForClientRect.INSTANCE.ClientToScreen(hWnd, getPos);
		// User32.instance.GetWindowRect(hWnd, rect);
		// User32GetClientRect(hWnd, rect);
		// Rectangle rectangle = w.rect;
		ArrayList<BufferedImage> alImages = new ArrayList<BufferedImage>();
		// WinDef.POINT getPos = new WinDef.POINT();
		rectangle = new Rectangle(w.rect.left, w.rect.left, w.rect.right, w.rect.bottom);
		System.out.println(rectangle);
	}

	// Get a single image capture
	public BufferedImage captureImage() {
		BufferedImage screenCapture = null;

		return screenCapture;
	}

	/*
	 * 
	 * public static Rectangle getClientRect(String startOfWindowName) { WinDef.HWND
	 * hWnd = User32ForClientRect.INSTANCE.FindWindow(null, startOfWindowName);
	 * WinDef.POINT getPos = new WinDef.POINT(); WinDef.RECT rect = new
	 * WinDef.RECT(); User32ForClientRect.INSTANCE.GetClientRect(hWnd, rect);
	 * User32ForClientRect.INSTANCE.ClientToScreen(hWnd, getPos);
	 * 
	 * return new Rectangle(getPos.x, getPos.y, rect.right, rect.bottom);
	 * 
	 * }
	 */

	public boolean saveImage() {
		return true;
	}

	public BufferedImage snipImage(BufferedImage screenCapture) {
		BufferedImage snipedImage = null;
		return snipedImage;
	}

	public ArrayList<BufferedImage> endScreenRecord() {
		if (isScreenRecording) {

		}
		ArrayList<BufferedImage> alImages = new ArrayList<BufferedImage>();
		return alImages;
	}

	public boolean startScreenRecord() {
		isScreenRecording = true;
		return true;
	}

	public static WindowInfo getWindowInfo(HWND hWnd) {
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

		int GetWindowRect(HWND hWnd, RECT r);

		void GetWindowTextA(HWND hWnd, byte[] buffer, int buflen);

		int GetTopWindow(int hWnd);

		int GetWindow(int hWnd, int flag);

		boolean ShowWindow(int hWnd);

		boolean BringWindowToTop(int hWnd);

		int GetActiveWindow();

		boolean SetForegroundWindow(HWND hWnd);

		HWND FindWindowA(String winClass, String title);

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
		HWND hwnd;
		RECT rect;
		String title;

		public WindowInfo(HWND hWnd2, RECT rect, String title) {
			this.hwnd = hWnd2;
			this.rect = rect;
			this.title = title;
		}

		public String toString() {
			return String.format("(%d,%d)-(%d,%d) : \"%s\"", rect.left, rect.top, rect.right, rect.bottom, title);
		}
	}

	public static double compaireImage(BufferedImage img1, BufferedImage img2) {
		//System.out.println("compaireImage function start");
		double avg = 0;
		double percentage = 0;
		long bla;
		int w1 = img1.getWidth();
		int w2 = img2.getWidth();
		int h1 = img1.getHeight();
		int h2 = img2.getHeight();
		if ((w1 != w2) || (h1 != h2)) {
			System.out.println("Both images should have same dimwnsions");
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
			//System.out.println("Difference: " + percentage);
		}
		return percentage;
	}

}
