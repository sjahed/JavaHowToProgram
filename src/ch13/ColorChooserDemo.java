package ch13;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorChooserDemo extends JFrame {

	private JPanel colorPanel;
	private JButton pickColorBtn;
	private Color color = Color.LIGHT_GRAY;
	
	public ColorChooserDemo(){
		
		pickColorBtn = new JButton("Choose Color");
		colorPanel = new JPanel();
		colorPanel.setBackground(color);
		
		setLayout(new BorderLayout());
		add(colorPanel, BorderLayout.CENTER);
		add(pickColorBtn, BorderLayout.SOUTH);
		
		pickColorBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				color = JColorChooser.showDialog(ColorChooserDemo.this, "Choose A color", color);
				
				if(color == null){
					color = Color.LIGHT_GRAY;
				}
				
				colorPanel.setBackground(color);
			}
			
		});
		
		//setSize(400,200);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
	}
	
	public static void main(String [] args){
		new ColorChooserDemo();
	}
}
