package ch7;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.beans.Transient;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawingSpiral extends JPanel{

	private final int width = 500, height = 500;
	
	private enum DIR {DOWN,LEFT,UP,RIGHT};
	private static DIR direction;
	private static int dirCounter = 0;
	
	public DrawingSpiral(){
		dirCounter = 1;
	}
	@Override
	@Transient
	public Dimension getPreferredSize() {
		// TODO Auto-generated method stub
		return new Dimension(width, height);
	}

	private DIR nextDir(){
		switch(dirCounter){
		case 1:
			dirCounter = 2;
			return DIR.DOWN;
		case 2: 
			dirCounter = 3;
			return DIR.LEFT;
		case 3: 
			dirCounter = 4;
			return DIR.UP;
		case 4:
			dirCounter = 1;
			return DIR.RIGHT;
		}
		//never reaches
		return DIR.DOWN;
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.setColor(Color.red);
		
		int centerX = getWidth()/2;
		int centerY = getHeight()/2;
		
		int lengthX = 10;
		int lengthY = 10;
		int counter = 0;
		
		for(int i = 0; i<100; i++){
			
			if(counter == 2){
				counter = 0;
				lengthX += 5;
				lengthY += 5;
			}
			switch(nextDir()){
			case DOWN:
				g.drawLine(centerX, centerY, centerX, centerY + lengthY);
				centerY += lengthY;
				break;
			case LEFT:
				g.drawLine(centerX, centerY, centerX-lengthX, centerY);
				centerX -= lengthX;
				break;
			case UP:
				g.drawLine(centerX, centerY, centerX, centerY-lengthY);
				centerY -= lengthY;
				break;
			case RIGHT:
				g.drawLine(centerX, centerY, centerX+lengthX,centerY);
				centerX += lengthX;
				break;
			}
			
			counter++;
		}
	}
	
	public static void main(String [] args){
		DrawingSpiral panel = new DrawingSpiral();
		JFrame f = new JFrame();
		f.add(panel);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.pack();
	}
}
