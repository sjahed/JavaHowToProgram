package ch23;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.security.SecureRandom;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class BouncingBall {
	
	public static void main(String[] args){
		new BouncingBall();
	}
	
	
	public BouncingBall(){
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try{
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				}catch(ClassNotFoundException ex){	
				}catch(InstantiationException ex){	
				}catch(IllegalAccessException ex){	
				}catch(UnsupportedLookAndFeelException ex){
				}
				
				JFrame frame = new JFrame("Bouncing Balls");
				frame.add(new Balls());
				frame.setVisible(true);
				frame.setSize(400, 400);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
			}
		});
	}

}//end of class BouncingBall

class Balls extends JPanel{
	SecureRandom random = new SecureRandom();
	public Balls(){
		setLayout(null);
		
		addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e){
				
				add(new Ball(e.getX(),e.getY(),
						new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255))));
				repaint();
			}
		});
	}
}//end of class Ballsd


class Ball extends JPanel implements Runnable{

	private final static int RADIUS = 35;
	private final static Color DEF_COLOR = Color.BLUE;
	private int VX = 1, VY = 1;
	
	private int xPos, yPos, radius;
	private Color color;
	
	public Ball(){
		this(0,0,RADIUS,DEF_COLOR);
	}
	
	public Ball(int initXPos, int initYPos, Color color){
		this(initXPos, initYPos, RADIUS, color);
	}
	
	public Ball(int initXPos, int initYPos, int radius, Color color){
		this.xPos = initXPos;
		this.yPos = initYPos;
		this.radius = radius;
		this.color  = color;
		//xPos = xPos - (radius/2);
		//yPos = yPos - (radius/2);
		new Thread(this).start();
	}
	
	@Override 
	protected void paintComponent(Graphics g){
//		g.setColor(color);
//		g.fillOval(xPos, yPos, radius, radius);
		
		super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //int x = getX();
        //int y = getY();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(color);
        g.fillOval(0, 0, radius,radius); //adds color to circle
        g.setColor(Color.black);
        g2.drawOval(0, 0, radius, radius); //draws circle
	}
	
	public void move(){
		int xBorder = getParent().getWidth();
		int yBorder = getParent().getHeight();
		int x = getX();
		int y = getY();
		
		if( x + VX < 0 || x + radius + VX > xBorder)
			VX *= -1;
		if( y + VY < 0 || y + radius + VY > yBorder)
			VY *= -1;
		
		x += VX;
		y += VY;
		
		setSize(getPreferredSize());
		setLocation(x, y);
		
	}//end of move()
	
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(radius,radius);	
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(isVisible()){
			try {
        	  Thread.sleep(100);
          	} catch (InterruptedException e) {
        	  System.out.println("interrupted");
          	}
			move();
			repaint();
		}
//		try{
//			SwingUtilities.invokeAndWait(new Runnable() {
//				
//				@Override
//				public void run() {
//					int x = (int) (Math.round(Math.random() * getParent().getWidth()));
//                    int y = (int) (Math.round(Math.random() * getParent().getHeight()));
//                    setLocation(x, y);
//					
//				}
//			});
//		}catch (InterruptedException exp) {
//            exp.printStackTrace();
//        } catch (InvocationTargetException exp) {
//            exp.printStackTrace();
//        }
//		
//        while (isVisible()) {
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                System.out.println("interrupted");
//            }
//
//            try {
//                SwingUtilities.invokeAndWait(new Runnable() {
//                    @Override
//                    public void run() {
//                        move();
//                        repaint();
//                    }
//                });
//            } catch (InterruptedException exp) {
//                exp.printStackTrace();
//            } catch (InvocationTargetException exp) {
//                exp.printStackTrace();
//            }
//        }
		
	}//end of run()
	
}//end of class Ball







/*class DrawPanel extends JPanel{
	
	private ArrayList<Ball> ballList = new ArrayList<Ball>();
	public DrawPanel(){
		addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e){
				ballList.add(new Ball(e.getX(),e.getY()));
				repaint();
			}
		});
	}//end of DrawPanel()
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		for(Ball ball: ballList){
			ball.draw(g);
		}
	}
	
}//end of class DrawPanel


class Ball{
	
	private int initPosX, initPosY;
	private Color color;
	private final static Color DEF_COLOR = Color.BLUE;
	private final static int RADIUS = 25;
	
	public Ball(int initPosX, int initPosY){
		this(initPosX, initPosY, DEF_COLOR);
	}
	public Ball(int initPosX, int initPosY, Color color){
		this.initPosX = initPosX;
		this.initPosY = initPosY;
		this.color = color;
	}
	
	public void draw(Graphics g){
		g.setColor(color);
		g.fillOval(initPosX, initPosY, RADIUS, RADIUS);
	}
	
}//end of class Ball*/