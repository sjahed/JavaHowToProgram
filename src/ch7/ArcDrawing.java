package ch7;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.beans.Transient;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ArcDrawing extends JPanel {
	
	private final static Color VIOLET = new Color(128, 0, 128);
	private final static Color INDIGO = new Color(75, 0, 130);
	
	private Color [] colors = {
			Color.white, Color.white, VIOLET, INDIGO, Color.blue,
			Color.green, Color.yellow, Color.orange, Color.red
			};
	
	private int width = 500, height = 500;
	
	public ArcDrawing(){
		setBackground(Color.white);
	}
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		
		int radius = 20;
		int centerX = getWidth()/2;
		int centerY = getHeight()/2;
		
		for(int i = colors.length; i > 0; i--){
			g.setColor(colors[i-1]);
			g.fillArc(centerX - i * radius,
					centerY - i * radius,
					i * radius * 2, i * radius * 2,
					0, 180);
		}
	}
	

	
	@Override
	@Transient
	public Dimension getPreferredSize() {
		// TODO Auto-generated method stub
		return new Dimension(width, height);
	}
	public static void main(String []args){
		ArcDrawing panel = new ArcDrawing();
		
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(panel);
		//f.setSize(250,250);
		f.pack();
		f.setVisible(true);
	}
}
