package ch18;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class KochFractalFrame extends JFrame {

	private static final int MAX_LEVEL =  15;
	private static final int MIN_LEVEL = 0;
	
	private JButton decBtn, incBtn;
	private JLabel levelLbl;
	KochFractal drawPanel;
	JPanel controlPanel, mainPanel;
	
	public KochFractalFrame(){
		super("Koch fractal");
		setLayout(new FlowLayout());
		decBtn = new JButton("Decrease");
		incBtn = new JButton("Increase");
		levelLbl = new JLabel("Level 0");
		drawPanel = new KochFractal(0);
		controlPanel = new JPanel();
		mainPanel = new JPanel();
		controlPanel.add(incBtn);
		controlPanel.add(decBtn);
		controlPanel.add(levelLbl);

		
		incBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int level = drawPanel.getLevel();
				++level;
				if((level <= MAX_LEVEL) && (level >= MIN_LEVEL)){
					drawPanel.setLevel(--level);
					levelLbl.setText("Level: "+level);
					repaint();
					
				}
			}
			
			
		});
		
		decBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int level = drawPanel.getLevel();
				--level;
				if((level <= MAX_LEVEL) && (level >= MIN_LEVEL)){
					drawPanel.setLevel(++level);
					levelLbl.setText("Level: "+level);
					repaint();
					
				}
			}
			
			
		});
		mainPanel.add(controlPanel);
		mainPanel.add(drawPanel);
		add(mainPanel);
		
		setVisible(true);
		pack();
	}
	
	public static void main(String [] args){
		KochFractalFrame frame = new KochFractalFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
