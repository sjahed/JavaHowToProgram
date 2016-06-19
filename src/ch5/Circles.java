package ch5;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Circles extends JPanel{

	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		
		int width = getWidth()/4;
		int height = getHeight()/4;
		
		for(int i = 10; i > 0; i--){
			if(i%2 == 0)
				g.setColor(Color.red);
			else
				g.setColor(Color.BLACK);
			
			g.fillOval(width, height, i * 10 + 10, i*10 + 10);
			width += 5;
			height += 5;
		}
		
	}
	
	public Dimension getPreferredSize(){
		return new Dimension(250,250);
	}
	
	public static void main(String [] args){
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new Circles());
		frame.pack();
		frame.setVisible(true);
	}
}
