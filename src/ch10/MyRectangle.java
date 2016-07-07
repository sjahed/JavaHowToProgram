package ch10;

import java.awt.Color;
import java.awt.Graphics;

public class MyRectangle extends MyShape {

	public MyRectangle(int x, int y, int w, int h, Color color){
		
		super(x,y,w,h,color);
		
	}
	
	@Override
	public void draw(Graphics g){
		g.setColor(getColor());
		g.fillRect(getX1(), getY1(), getX2(), getY2());
	}
}
