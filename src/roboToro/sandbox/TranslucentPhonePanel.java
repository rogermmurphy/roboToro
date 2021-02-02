package roboToro.sandbox;

import static java.awt.GraphicsDevice.WindowTranslucency.TRANSLUCENT;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import roboToro.Toro;
import roboToro.UI.ToroUI;
import roboToro.game.tbs2020.FixedUtil;

public class TranslucentPhonePanel extends JFrame {

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
	
	

	public TranslucentPhonePanel() throws AWTException{
		 super("TranslucentWindow");
	        //Add a sample button.
	        add(new JButton("I am a Button"));
	       
	        setLayout(new GridBagLayout());

	        setSize(300,200);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
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
 						//actionClickPoint.x +</LOCATION></CLICK>");
                 repaint();
             }


             @Override
             public void mouseDragged(MouseEvent e) {
            	 System.out.println("mouse Dragged @ " +  e.getPoint());
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
	static TranslucentPhonePanel tw;
	
	 public static void main(String[] args) {
	        // Determine if the GraphicsDevice supports translucency.
	        GraphicsEnvironment ge = 
	            GraphicsEnvironment.getLocalGraphicsEnvironment();
	        GraphicsDevice gd = ge.getDefaultScreenDevice();

	        //If translucent windows aren't supported, exit.
	        if (!gd.isWindowTranslucencySupported(TRANSLUCENT)) {
	            System.err.println(
	                "Translucency is not supported");
	                System.exit(0);
	        }
	        
	        JFrame.setDefaultLookAndFeelDecorated(true);
	        
	     //   ToroUI.toro = new Toro();
	        FixedUtil	fixedUtil = new FixedUtil();
	        
			try {
				//initialize();
				//toro.phonePanelThread = new Thread(() -> fixedUtil.setLiveWindow(this.uxPhonePanel));
				//toro.phonePanelThread.start();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

	        // Create the GUI on the event-dispatching thread
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                try {
						tw = new TranslucentPhonePanel();
					} catch (AWTException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	              //  Thread temp = new Thread(() -> fixedUtil.setTransperentWindow(tw));
	               // temp.run();
	                // Set the window to 55% opaque (45% translucent).
	                tw.setOpacity(0.10f);
	                // Display the window.
	                tw.setVisible(true);
	                
	            }
	        });
	  }

	@Override
	public Dimension getPreferredSize() {
		return image == null ? super.getPreferredSize() : new Dimension(image.getWidth(), image.getHeight());
	}
/*
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null) {
			Graphics2D g2d = (Graphics2D) g.create();
			// g2d.drawImage(image, WIDTH, 0, this);
			if (selection != null) {
				g2d.setColor(new Color(225, 225, 255, 128));
				g2d.fill(selection);
				g2d.setColor(Color.GRAY);
				g2d.draw(selection);
			}
			g2d.dispose();
		}
	}
*/
	public BufferedImage getImageSnip() {
		BufferedImage snip = null;// new BufferedImage(0,0,0);
		if (selection != null && image != null) {
			snip = TranslucentPhonePanel.image.getSubimage(selection.x, selection.y, selection.width, selection.height);
			// this.image.getSubimage(flags, flags, flags, flags)
		}
		return snip;
	}

	/*
	 * public static Rectangle getVirtualBounds() {
	 * 
	 * Rectangle bounds = new Rectangle(0, 0, 0, 0);
	 * 
	 * GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	 * GraphicsDevice lstGDs[] = ge.getScreenDevices(); for (GraphicsDevice gd :
	 * lstGDs) {
	 * 
	 * bounds.add(gd.getDefaultConfiguration().getBounds());
	 * 
	 * }
	 * 
	 * return bounds;
	 * 
	 * }
	 */
}
