package ch5;

import java.awt.Dimension;
import java.awt.Graphics;
import java.beans.Transient;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Shapes extends JPanel {

	final int RECTANGLE = 1;
	final int OVAL = 2;
	int shape;
	
	public Shapes(int shape){
		this.shape = shape;
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);

		for(int i=0; i<10; i++){
			switch(shape){
			case RECTANGLE:
				g.drawRect(10+i*10, 10+i*10, 50+i*10,50+i*10);
				break;
			case OVAL:
				g.drawOval(10+i*10, 10+i*10, 50+i*10,50+i*10);
				break;
			}
		}
	}
	
	
	@Override
	@Transient
	public Dimension getPreferredSize() {
		// TODO Auto-generated method stub
		return new Dimension(250,250);
	}

	public static void main(String [] args){
		
		String input = JOptionPane.showInputDialog("Enter 1 or 2");
		Shapes panel = new Shapes(Integer.parseInt(input));
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		//frame.setSize(250,250);
		frame.pack();
		frame.setVisible(true);
	}
	
	
}
