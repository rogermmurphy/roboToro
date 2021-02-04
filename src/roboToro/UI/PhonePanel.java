package roboToro.UI;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import roboToro.Toro;

public class PhonePanel extends JPanel {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static BufferedImage image;
    public Rectangle selection;
    public Point actionClickPoint;
    public boolean bScreenCapture;
    public boolean bCreateAction;
    public String sCreatedAction;
    public ArrayList<Point> alPoint;
    public boolean liveClick;

     public PhonePanel() throws AWTException{
    	 liveClick = false;
    	 bScreenCapture = false;
    	 bCreateAction = false;
    	 sCreatedAction = "";
    	 alPoint = new ArrayList<Point>();
        // Robot bot = new Robot();
       //  image = bot.createScreenCapture(getVirtualBounds());

         MouseAdapter handler = new MouseAdapter() {
             @Override
             public void mousePressed(MouseEvent e) {
            	 //System.out.println("mouse pressed @ " +  e.getPoint());
            	 if(!bScreenCapture)return;
                 selection = new Rectangle(e.getPoint());
                 repaint();
             }
             
             @Override
             public void mouseClicked(MouseEvent e) {
            	//System.out.println("mouse clicked @ " +  e.getPoint());
            	 if(!bCreateAction) {
            		//this should be done in setter method
            		 sCreatedAction = "";
            		 alPoint = new ArrayList<Point>();
            		 return;
            	 }
            	 actionClickPoint = e.getPoint();
            	 alPoint.add(actionClickPoint);
            	 sCreatedAction += "<CLICK><LOCATION x=\"" + actionClickPoint.x + "\" y=\"" + actionClickPoint.y  + "\" ></LOCATION></CLICK>";
            	 if(liveClick) {
            		 String upClick = "G01 ";// X45 Y98 Z-340.5 W0";
         			String downClick = "G01 ";
         			long x = (long) Math.round(actionClickPoint.x * Toro.ACTUAL_PIXEL_WIDTH);
         			long y = (long) Toro.DIVICE_HEIGTH_MM - Math.round(actionClickPoint.y * Toro.ACTUAL_PIXEL_WIDTH);
         			x += Math.random()*2; //% Toro.xVAR;
         			x -= Math.random()*2;// % Toro.xVAR;
         		//	y += Math.random() % Toro.yVAR;
         		//	y += x += Math.random() % Toro.yVAR;

         			upClick += "X" + x + " Y" + y + " Z" + Toro.DELTA_Z_CORD_UP + " W0";
         			downClick += "X" + x + " Y" + y + " Z" + Toro.DELTA_Z_CORD_DOWN + " W0";

         			//System.out.println(upClick);
         			//System.out.println(downClick);

         			Toro.comClient.sendCommand(upClick);
         			Toro.comClient.sendCommand(downClick);
         			Toro.comClient.sendCommand(upClick);
            	 }
 						//actionClickPoint.x +</LOCATION></CLICK>");
                 repaint();
             }


             @Override
             public void mouseDragged(MouseEvent e) {
            	 //System.out.println("mouse Dragged @ " +  e.getPoint());
            	 if(!bScreenCapture && !bCreateAction)return;
            	 
                 Point p = e.getPoint();
                 int width = Math.max(selection.x - e.getX(), e.getX() - selection.x);
                 int height = Math.max(selection.y - e.getY(), e.getY() - selection.y);
                 selection.setSize(width, height);
                 repaint();
             }
         };

         addMouseListener(handler);
         addMouseMotionListener(handler);     
        
     }
	
     @Override
     public Dimension getPreferredSize() {
         return image == null ? super.getPreferredSize() : new Dimension(image.getWidth(), image.getHeight());
     }

     @Override
     protected void paintComponent(Graphics g) {
         super.paintComponent(g);
         if (image != null) {
             Graphics2D g2d = (Graphics2D) g.create();
             g2d.drawImage(image, WIDTH, 0, this);
             if (selection != null) {
                 g2d.setColor(new Color(225, 225, 255, 128));
                 g2d.fill(selection);
                 g2d.setColor(Color.GRAY);
                 g2d.draw(selection);
             }
             g2d.dispose();
         }
     }
     
     public BufferedImage getImageSnip () {
    	 BufferedImage snip = null;//new BufferedImage(0,0,0);
    	 if(selection != null && image != null) {
    		 snip = PhonePanel.image.getSubimage( selection.x, selection.y, selection.width, selection.height);
    		// this.image.getSubimage(flags, flags, flags, flags)
    	 }
    	 return snip;
     }

     
     
     /*
 public static Rectangle getVirtualBounds() {

     Rectangle bounds = new Rectangle(0, 0, 0, 0);

     GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
     GraphicsDevice lstGDs[] = ge.getScreenDevices();
     for (GraphicsDevice gd : lstGDs) {

         bounds.add(gd.getDefaultConfiguration().getBounds());

     }

     return bounds;

 }*/
}
