package ch10;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestShape extends JPanel{

	MyShape[] shapes;
	
	public static void main(String [] args){
		TestShape ts = new TestShape();
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(ts);
		f.setVisible(true);
		f.pack();
	}
	
	public TestShape(){
		shapes = new MyShape[2];
		shapes[0] = new MyLine(0, 0, 100, 100, Color.red);
		shapes[1] = new MyRectangle(100, 100, 20,20, Color.red);
	}
	
	@Override
	public void paintComponent(Graphics g){
		shapes[0].draw(g);
		shapes[1].draw(g);
	}
	
	public Dimension getPreferredSize(){
		return new Dimension(300,300);
	}
}
