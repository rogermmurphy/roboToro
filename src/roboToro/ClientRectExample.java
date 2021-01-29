package roboToro;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ClientRectExample {
	public static String windowName = "Reflector 3 - I810980’s iPhone";


	interface User32ForClientRect extends StdCallLibrary {
		@SuppressWarnings("deprecation")
		User32ForClientRect INSTANCE = Native.loadLibrary("user32", User32ForClientRect.class,
				W32APIOptions.DEFAULT_OPTIONS);

		WinDef.HWND FindWindow(String lpClassName, String lpWindowName);

		boolean GetClientRect(WinDef.HWND hWnd, WinDef.RECT rect);

		boolean ClientToScreen(WinDef.HWND hWnd, WinDef.POINT lpPoint);
	}

	public static void main(String[] args) {
		// while (true) {
		try {
			Rectangle rectangle = ClientRectExample.getClientRect(windowName);
			System.out.println(rectangle);
			Robot robot = new Robot();

			BufferedImage screen = robot.createScreenCapture(rectangle);
			/*
			 * 
			 * System.out.println(img.getRect()[0] + " " + img.getRect()[1] + " " +
			 * img.getRect()[2] + " " + img.getRect()[3]);
			 * 
			 * new Rectangle(img.getRect()[0], img.getRect()[1], img.getRect()[2] -
			 * img.getRect()[0] , img.getRect()[3] - img.getRect()[1]));
			 */
			// robot.createMultiResolutionScreenCapture(screenRect);

			new ScreenCaptureRectangle(screen);

			// Thread.sleep(1000000);
		} // }
		catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Rectangle getClientRect(String startOfWindowName) {
		WinDef.HWND hWnd = User32ForClientRect.INSTANCE.FindWindow(null, startOfWindowName);
		WinDef.POINT getPos = new WinDef.POINT();
		WinDef.RECT rect = new WinDef.RECT();
		User32ForClientRect.INSTANCE.GetClientRect(hWnd, rect);
		User32ForClientRect.INSTANCE.ClientToScreen(hWnd, getPos);

		return new Rectangle(getPos.x, getPos.y, rect.right, rect.bottom);

	}
}