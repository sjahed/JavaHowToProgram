package ch8;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyPanel extends JPanel {

	private Random random;
	private MyLine[] lines;
	private MyRectangle[] rectangles;
	
	MyPanel(){
		setBackground(Color.white);
		random = new Random();
		lines = new MyLine[5+random.nextInt(5)];
		
		for(int count = 0; count < lines.length; count++){
			int x1 = random.nextInt(300);
			int x2 = random.nextInt(300);
			int y1 = random.nextInt(300);
			int y2 = random.nextInt(300);
			Color color = new Color(random.nextInt(256),random.nextInt(256), random.nextInt(256));
			
			lines[count] = new MyLine(x1,x2,y1,y2,color);
		}
		
		rectangles = new MyRectangle[5+random.nextInt(5)];
		for(int count = 0; count < rectangles.length; count++){
			int x = random.nextInt(300);
			int y = random.nextInt(300);
			int width = random.nextInt(100)+10;
			int height = random.nextInt(100)+10;
			Color color = new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256));
			rectangles[count] = new MyRectangle(x,y,width,height,color);
		}
		
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(MyLine l: lines){
			l.draw(g);
		}
		for(MyRectangle r: rectangles)
			r.draw(g);
	}

	public Dimension getPreferredSize(){
		return new Dimension(300,300);
	}
}
