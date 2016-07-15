package ch18;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;



public class KochFractal extends JPanel{

	private int level;
	private Color color;
	
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;


	public KochFractal(int level){
		this.level = level;
		color = Color.BLUE;
		setBackground(Color.WHITE);
		
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(color);
		drawFractal(level, 100,100, 400, 100, g);
	}
	

	public void drawFractal(int level, int x1, int y1, int x2, int y2, Graphics g){
		
		if(level == 0){
			g.drawLine(x1, y1, x2, y2);
			return;
		}else{
			int aX = x1;
			int aY = y1;
			int bX = x2;
			int bY = y2;
			int uX = bX - aX;
			int uY = bY - aY;
			int vX = aY - bY;
			int vY = bX - aX;
			
			int pX = aX + (1/3) * uX;
			int pY = aY + (1/3) * uY;
			int qX = (int) (aX + (1/2) * uX + (Math.sqrt(3)/6) * vX);
			int qY = (int) (aY + (1/2) * uY + (Math.sqrt(3)/6) * vY);
			int rX = aX + (2/3) * uX;
			int rY = aY + (2/3) * uY;
			
			drawFractal(level-1, aX, aY, pX, pY, g);
			drawFractal(level-1, pX, pY, qX, qY, g);
			drawFractal(level-1, qX, qY, rX, rY, g);
			drawFractal(level-1, rX, rY, bX, bY, g);
		}
	}

	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	public Dimension getPreferredSize(){
		return new Dimension(WIDTH, HEIGHT);
	}
	public void setColor(Color color2) {
		this.color = color2;
		
	}
}
