package ch18;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Fractal extends JFrame {

	private static final int WIDTH = 400;
	private static final int HEIGHT = 480;
	private static final int MIN_LEVEL = 0;
	private static final int MAX_LEVEL = 40;
	
	
	public Fractal(){
		super("Fractal");
		
		final JLabel levelJLabel = new JLabel("Level: 0");
		//final FractalJPanel drawSpace = new FractalJPanel(0);
		//KochFractal drawSpace = new KochFractal(0);
		FiveLineFractal drawSpace = new FiveLineFractal(0);
		final JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		
		final JButton changeColorBtn = new JButton("Color");
		controlPanel.add(changeColorBtn);
		changeColorBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(Fractal.this, "PickColor", Color.BLUE);
				if(color == null)
					color = Color.BLUE;
				drawSpace.setColor(color);
				
			}
		
			
		});
		
		final JButton decreaseLevelBtn = new JButton("Decrease Level");
		controlPanel.add(decreaseLevelBtn);
		decreaseLevelBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int level = drawSpace.getLevel();
				--level;
				
				if((level >= MIN_LEVEL) && (level <= MAX_LEVEL)){
					levelJLabel.setText("Level: "+level);
					drawSpace.setLevel(level);
					repaint();
				}
					
			}
			
		});
		
		final JButton increaseLevelBtn = new JButton("increase Level");
		controlPanel.add(increaseLevelBtn);
		increaseLevelBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int level = drawSpace.getLevel();
				++level;
				
				if((level >= MIN_LEVEL) && (level <= MAX_LEVEL)){
					levelJLabel.setText("Level: "+level);
					drawSpace.setLevel(level);
					repaint();
				}
					
			}
			
		});
		
		controlPanel.add(levelJLabel);
		
		final JPanel mainPanel = new JPanel();
		mainPanel.add(controlPanel);
		mainPanel.add(drawSpace);
		add(mainPanel);
		setSize(WIDTH,HEIGHT);
		pack();
		setVisible(true);
		
	}
	
	public static void main(String [] args){
		Fractal demo = new Fractal();
		demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
