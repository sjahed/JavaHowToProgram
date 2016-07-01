package ch8;

import javax.swing.JFrame;

public class MyLineTest {

	
	public static void main(String[] args){
		MyPanel p = new MyPanel();
		JFrame app = new JFrame();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.add(p);
		//app.setSize(300,300);
		app.pack();
		app.setVisible(true);
	}
}
