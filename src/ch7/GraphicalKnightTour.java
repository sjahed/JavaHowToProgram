package ch7;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class GraphicalKnightTour extends JFrame{
	
	private final int ROWS = 8;
	private final int COLS = 8;
	
	public static void main(String[]args){
		new GraphicalKnightTour();
	}
	Board board;
	public GraphicalKnightTour(){
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board = new Board();
		add(board);
		pack();
		setVisible(true);
		
		
	}

	class Tile extends JPanel{
		
		private final int width = 50;
		private final int height = 50;
		Color tileColor; 
		int xPos, yPos;
		
		
		public Tile(Color tileColor){
			
			this.xPos = 0;
			this.yPos = 0;
			this.tileColor = tileColor;
			
		}
		 public Dimension getPreferredSize(){
			return new Dimension(width, height);
		}
		
		 protected void paintComponent(Graphics g){
			 super.paintComponent(g);
			 g.setColor(tileColor);
			 g.fillRect(xPos, yPos, getWidth(), getHeight());
			
		 }
		
	}
	
	class Board extends JPanel{
		
		private final int width = 400;
		private final int height = 400;
		
		private int numTiles = 8;
		private final Color black = Color.BLACK;
		private final Color white = Color.WHITE;
		
		private final int hGap = 2;
		private final int vGap = 2;
		
		Tile[][] tileList;
		public Board(){
			
			tileList = new Tile [numTiles][numTiles];
			setLayout(new GridLayout(numTiles, numTiles,hGap, vGap));
			setBackground(Color.CYAN);
			
			Color tileColor;
			
			for(int i = 0; i < numTiles; i++){
				
				
				for(int j = 0; j < numTiles; j++){
					if((j % 2 == 0 && i % 2 == 0) ||( j%2 == 1 && i %2 == 1) )
						tileColor = black;
					else
						tileColor = white;
					
	
					tileList[i][j] = new Tile(tileColor);
					add(tileList[i][j]);
					
				}
				
			}
			
			
		}
		
		
		public Dimension getPreferredSize(){
			return new Dimension(width,height);
		}
	}
}
