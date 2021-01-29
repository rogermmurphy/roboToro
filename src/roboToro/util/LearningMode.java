package roboToro.util;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import roboToro.Main;
import roboToro.Main.User32;
import roboToro.Main.WindowInfo;

public class LearningMode {

//	public LearningMode() {
//		// TODO Auto-generated constructor stub
//		private static void createRecordingOnly() throws AWTException, IOException, InterruptedException {
//			// Load Data
//			// Load Fastball
//			// need to make this image a sub image of just the strike zone...
//			// ..heat signature changes as the computer slows down from so many comparisons
//			BufferedImage strikeZoneBI = ImageIO
//					.read(new File("C:\\Users\\i810980\\Documents\\TSB\\Images\\StrikeZone.png"));
//
//			BufferedImage FB1 = ImageIO.read(new File("C:\\Users\\i810980\\Documents\\TSB\\Images\\FB1.png"));
//
//			BufferedImage FB2 = ImageIO.read(new File("C:\\Users\\i810980\\Documents\\TSB\\Images\\FB2.png"));
//
//			BufferedImage FB3 = ImageIO.read(new File("C:\\Users\\i810980\\Documents\\TSB\\Images\\FB3.png"));
//
//			BufferedImage FB4 = ImageIO.read(new File("C:\\Users\\i810980\\Documents\\TSB\\Images\\FB4.png"));
//
//			BufferedImage FB5 = ImageIO.read(new File("C:\\Users\\i810980\\Documents\\TSB\\Images\\FB5.png"));
//			// load curve ball
//			// load others
//
//			// get screen handle
//			int hWnd = User32.instance.FindWindowA(null, "Reflector 3 - I810980’s iPhone");
//			// get window info
//			WindowInfo w = getWindowInfo(hWnd);
//			// move to forgournd
//			User32.instance.SetForegroundWindow(w.hwnd);
//			// ArrayList<BufferedImage> lastCaptureAL = new ArrayList<BufferedImage>();
//
//			ArrayList<BufferedImage> deltaCaptureHighlightAL = new ArrayList<BufferedImage>();
//			BufferedImage deltaCaptureHighlight = null;
//
//			ArrayList<BufferedImage> strikeZoneCaptureHighlightAL = new ArrayList<BufferedImage>();
//			BufferedImage strikeZoneCaptureHighlight = null;
//
//			ArrayList<BufferedImage> fScreateScreenCaptureAL = new ArrayList<BufferedImage>();
//			BufferedImage fScreateScreenCapture;
//
//			ArrayList<BufferedImage> subScreenCaptureNoHighlightAL = new ArrayList<BufferedImage>();
//			BufferedImage subScreenCaptureNoHighlight;
//
//			ArrayList<BufferedImage> swingImagesAL = new ArrayList<BufferedImage>();
//
//			BufferedImage lastCaptureNoHighlight = null;
//			Robot robot = new Robot();
//			// BufferedImage ceateScreenCapture;
//			double percent = 100;
//
//			for (int i = 0; i < Main.ITERATIONS; i++) {
//				try {
//					// System.out.println("Recording: " + i);
//
//					fScreateScreenCapture = robot.createScreenCapture(
//							new Rectangle(w.rect.left, w.rect.top, w.rect.right - w.rect.left, w.rect.bottom - w.rect.top));
//
//					// fScreateScreenCaptureAL.add(fScreateScreenCapture);
//					subScreenCaptureNoHighlight = fScreateScreenCapture.getSubimage(PITCHING_REGION_LEFT,
//							Main.PITCHING_REGION_TOP, Main.PITCHING_REGION_WIDTH, Main.PITCHING_REGION_LENGTH); // , w,
//																												// h)(PITCHING_REGION_LEFT,
//																												// Main.PITCHING_REGION_TOP,
//					// w, h,
//					// rgbArray,
//					// offset,
//					// scansize)
//					// subScreenCaptureNoHighlightAL.add(subScreenCaptureNoHighlight);
//
//					strikeZoneCaptureHighlight = getDifferenceImage(subScreenCaptureNoHighlight, strikeZoneBI);
//					// strikeZoneCaptureHighlightAL.add(strikeZoneCaptureHighlight);
//
//					int amoutofdifference = 0;
//					if (lastCaptureNoHighlight != null) {
//						// the dirrerence between last ball and this ball
//						deltaCaptureHighlight = getDifferenceImage(lastCaptureNoHighlight, subScreenCaptureNoHighlight);
//						// is this a valid hit
//						// System.out.println(i + "interation number of generated red px " +
//						// Main.numberofpixels);
//						// the difference between
//						// deltaCaptureHighlightAL.add(deltaCaptureHighlight);
//
//						if (Main.numberofpixels < 500) {
//
//							double matchFB1 = getMatchingRed(deltaCaptureHighlight, FB1);
//							// System.out.println(i + " iteration: Number of PX Changed " +
//							// Main.numberofpixels + " Number that Match " + matchFB1);
//							if ((matchFB1 / Main.numberofpixels) > .3) {
//								System.out.println("Fastball Swing FB1");
//								swingImagesAL.add(subScreenCaptureNoHighlight);
//								swingImagesAL.add(deltaCaptureHighlight);
//								swingImagesAL.add(fScreateScreenCapture);
//								continue;
//
//							}
//							double matchFB2 = getMatchingRed(deltaCaptureHighlight, FB2);
//
//							// System.out.println(i + " iteration: Number of PX Changed " +
//							// Main.numberofpixels + " Number that Match " + matchFB1);
//							if ((matchFB1 / Main.numberofpixels) > .3) {
//								System.out.println("Fastball Swing FB2");
//								swingImagesAL.add(subScreenCaptureNoHighlight);
//								swingImagesAL.add(deltaCaptureHighlight);
//								swingImagesAL.add(fScreateScreenCapture);
//								continue;
//
//							}
//							double matchFB3 = getMatchingRed(deltaCaptureHighlight, FB3);
//
//							// System.out.println(i + " iteration: Number of PX Changed " +
//							// Main.numberofpixels + " Number that Match " + matchFB1);
//							if ((matchFB1 / Main.numberofpixels) > .3) {
//								System.out.println("Fastball Swing FB3");
//								swingImagesAL.add(subScreenCaptureNoHighlight);
//								swingImagesAL.add(deltaCaptureHighlight);
//								swingImagesAL.add(fScreateScreenCapture);
//								continue;
//
//							}
//						}
//						// compare this ball to the strikezone
//						// subScreenCaptureNoHighlightAL.add(subScreenCaptureNoHighlight);
//						// getDifferenceImage(deltaCapture, fastball, amoutofdifference); //percent =
//						// getDifferencePercent(strikeZoneBI, deltaCapture);
//						// System.out.println("Interation: " + i +" Matching Red PX: " +
//						// System.out.println(i + " FB1: " + getMatchingRed(deltaCaptureHighlight,
//						// FB1));
//						// System.out.println(i + " FB2: " + getMatchingRed(deltaCaptureHighlight,
//						// FB2));
//						// System.out.println(i + " FB3: " + getMatchingRed(deltaCaptureHighlight,
//						// FB3));
//						// System.out.println(i + " FB4: " + getMatchingRed(deltaCaptureHighlight,
//						// FB4));
//						// System.out.println(i + " FB5: " + getMatchingRed(deltaCaptureHighlight,
//						// FB5));
//
//					}
//					lastCaptureNoHighlight = subScreenCaptureNoHighlight;
//
//				} catch (Exception e) {
//					System.out.println("Exception occured :" + e.getMessage());
//				}
//				// System.out.println("Images were wbritten succesfully.");
//			}
//			for (int i = 0; i < swingImagesAL.size(); i++) {
//				ImageIO.write(swingImagesAL.get(i), "png",
//						new File("C:\\Users\\i810980\\Documents\\TSB\\" + i + "swingImagesAL.png"));
//				/*
//				 * System.out.println("Printing: " + i); // System.out.println(percent); //
//				 * System.out.println(i+" strike " + Main.numberofpixels);
//				 * ImageIO.write(subScreenCaptureNoHighlightAL.get(i), "png", new
//				 * File("C:\\Users\\i810980\\Documents\\TSB\\" + i + "
//				 * subScreenCaptureNoHighlightAL.png"));
//				 * 
//				 * ImageIO.write(fScreateScreenCaptureAL.get(i), "png", new
//				 * File("C:\\Users\\i810980\\Documents\\TSB\\" + i + "fScreateScreenCaptureAL.
//				 * png"));
//				 * 
//				 * ImageIO.write(strikeZoneCaptureHighlightAL.get(i), "png", new
//				 * File("C:\\Users\\i810980\\Documents\\TSB\\" + i + "
//				 * strikeZoneCaptureHighlightAL.png"));
//				 * 
//				 * if(i>0) { ImageIO.write(deltaCaptureHighlightAL.get(i-1), "png", new
//				 * File("C:\\Users\\i810980\\Documents\\TSB\\" + i + "deltaCaptureHighlightAL.
//				 * png")); }
//				 */
//
//			}
//
//		}
//
//	}

}
