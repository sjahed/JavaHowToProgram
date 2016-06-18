package ch4;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * draws a simple panel with a cross(line)
 */
public class DrawPanel extends JPanel{

	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
		
		int width = getWidth();
		int height = getHeight();
		int zero = 0;
		
		for(int i=0; i<=getWidth(); i += 20){
			g.drawLine(0, i, 20+i, getHeight());
		}
		
		
		for(int i=getWidth(); i>=0; i -= 20){
			g.drawLine(getWidth(), i, i-20, 0);
		}
		int x=20;
		for(int i=getWidth(); i>=0; i -= 20){
			g.drawLine(i,0,0,x);
			x+=20;
		}
		
		height = getHeight()-20;
		for(int i=0; i< getWidth(); i+=20){
			g.drawLine(i, getHeight(), getWidth(), height);
			height -= 20;
		}
		
/*		for(int i = 0; i <= height; i += 20){
			g.drawLine(0, 0, width, i);
			width -= 20;
		}
		
		width = getWidth();
		
		//int dw = width;
		for(int k=0;k <= height;k +=20){
			
			g.drawLine(getWidth(), getHeight(), width, k);
			width -= 20;
	
		}
		
		height = getHeight();
		width = getWidth();
		for(int i=getWidth();i>=0;i -= 20){
			g.drawLine(0,getHeight(),i,height);
			height -= 20;
		}
		
		height = getHeight();
		width = getWidth();
		
		for(int i=getWidth();i>=0;i -= 20){
			g.drawLine(getWidth(),0,width,i);
			width -= 20;
		}*/
		
//		for(int i = 0; i <= height; i += 20){
//			g.drawLine(0, 0, width, i);
//			
//		}
//		for(int k=width;k>=0;k -=20){
//			g.drawLine(0, 0, k, height);
//		}
//		g.drawLine(0, 0, width, height);
//		g.drawLine(0, height, width, 0);
	}

	public Dimension getPreferredSize() {
        return new Dimension(256, 256);
    }
	
	public static void main(String[] args){
		
		DrawPanel panel = new DrawPanel();
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		//frame.setSize(250,250);
		frame.pack();
		frame.setVisible(true);
		
//		DrawPanel2 panel2 = new DrawPanel2();
//		JFrame frame2 = new JFrame();
//		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame2.add(panel2);
//		frame2.pack();
//		frame2.setVisible(true);
		
	}
	
}
