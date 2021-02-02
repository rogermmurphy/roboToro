package roboToro.sandbox;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.*;

import roboToro.game.tbs2020.FixedUtil;

import static java.awt.GraphicsDevice.WindowTranslucency.*;

public class TranslucentWindowDemo extends JFrame {
	
	private static final long serialVersionUID = 1L;
	public static BufferedImage image;
	public Rectangle selection;
	public Point actionClickPoint;
	public boolean bScreenCapture;
	public boolean bCreateAction;
	public String sCreatedAction;
	public ArrayList<Point> alPoint;
	
    public TranslucentWindowDemo() {
        super("TranslucentWindow");
        setLayout(new GridBagLayout());

        setSize(300,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add a sample button.
        add(new JButton("I am a Button"));
        MouseAdapter handler = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
           	 System.out.println("mouse pressed @ " +  e.getPoint());
           	 if(!bScreenCapture)return;
                selection = new Rectangle(e.getPoint());
                repaint();
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
           	System.out.println("mouse clicked @ " +  e.getPoint());
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
       // super.pai

        addMouseListener(handler);
        addMouseMotionListener(handler);     
    
    }
    
    @Override
	public void repaint() {
		super.repaint();
		if (image != null) {
			Graphics2D g2d = (Graphics2D) this.getGraphics().create();
			this.setOpacity(0);
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
        
        FixedUtil fixedUtil = new FixedUtil();
        
        JFrame.setDefaultLookAndFeelDecorated(true);

        // Create the GUI on the event-dispatching thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TranslucentWindowDemo tw = new TranslucentWindowDemo();
                fixedUtil.setTransperentWindow(tw);
                // Set the window to 55% opaque (45% translucent).
                tw.setOpacity(0.10f);

                // Display the window.
                tw.setVisible(true);
                //tw.set(true);
                tw.toFront();
                tw.repaint();
            }
        });
        
    }
}
