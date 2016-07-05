package ch9;

import java.awt.BorderLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class LabelDemo {

	static JLabel northLabel;
	static JLabel southLabel;
	static JLabel centerLabel;
	static ImageIcon labelIcon;
	
	
	public static void main(String [] args){
		
		
		northLabel = new JLabel("North Label");
		
		labelIcon = new ImageIcon("picture/GUITip.jpg");
		centerLabel = new JLabel(labelIcon);
		
		southLabel = new JLabel(labelIcon);
		southLabel.setText("South Label");
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(northLabel, BorderLayout.NORTH);
		frame.add(centerLabel, BorderLayout.CENTER);
		frame.add(southLabel, BorderLayout.SOUTH);
		
		//frame.setSize(300, 300);
		frame.pack();
		frame.setVisible(true);
		
		
	}
}
