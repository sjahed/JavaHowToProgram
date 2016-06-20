package ch6;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawSmiley extends JPanel {

	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		
		//face
		g.setColor(Color.yellow);
		g.fillOval(50, 50, 150, 150);
		//face border
		g.setColor(Color.black);
		g.drawOval(49,49,151,151);
		
		g.fillOval(80, 80, 30, 30);
		g.fillOval(140, 80, 30, 30);
		
		g.fillOval(90, 155, 66, 30);
		
		g.setColor(Color.yellow);
		g.fillRect(90, 120, 66, 45);
		g.fillOval(90, 140, 66, 35);
	}
	
	public Dimension getPreferredSize(){
		return new Dimension(300,300);
	}
	
	public static void main(String [] args){
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new DrawSmiley());
		frame.pack();
		frame.setVisible(true);
	}
}
