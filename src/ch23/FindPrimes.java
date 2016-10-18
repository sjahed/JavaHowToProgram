package ch23;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class FindPrimes extends JFrame {

	private final JButton cancelBtn = new JButton("Cancel");
	private final JButton getPrimesBtn = new JButton("Get Primes");
	private final JTextField inputField = new JTextField();
	private final JTextArea resultArea = new JTextArea();
	private final JProgressBar progressBar = new JProgressBar();
	private final JLabel statusLbl = new JLabel();
	private PrimeCalculator calc;
	
	public FindPrimes(){
		super("Find Primes less than");
		setLayout(new BorderLayout());
		
		JPanel northPanel = new JPanel(new FlowLayout());
		northPanel.add(new JLabel("Find primes less than: "));
		inputField.setColumns(5);
		northPanel.add(inputField);
		northPanel.add(getPrimesBtn);
		getPrimesBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				progressBar.setValue(0);
				resultArea.setText("");
				statusLbl.setText("");
				int number;
				
				try{
					number = Integer.parseInt(inputField.getText());
				}catch(NumberFormatException ex){
					statusLbl.setText("Enter an integer");
					return;
				}//end of try-catch
				
				calc = new PrimeCalculator(number, resultArea, cancelBtn, getPrimesBtn, statusLbl);
				calc.addPropertyChangeListener(new PropertyChangeListener() {
					
					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						// TODO Auto-generated method stub
						if(evt.getPropertyName().equals("progress")){
							int newValue = (Integer) evt.getNewValue();
							progressBar.setValue(newValue);
						}
					}//end anonymous inner class
				});//end call to addPropertyChangeListener
				getPrimesBtn.setEnabled(false);
				cancelBtn.setEnabled(true);
				calc.execute();
			}
		});
		
		
		resultArea.setEditable(false);
		add(new JScrollPane(resultArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER));
		
		JPanel southPanel = new JPanel(new GridLayout(1, 3, 10, 10));
		cancelBtn.setEnabled(false);
		progressBar.setStringPainted(true);
		southPanel.add(cancelBtn);
		southPanel.add(progressBar);
		southPanel.add(statusLbl);
		
		cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				calc.cancel(true);
				
			}
		});
		
		add(northPanel, BorderLayout.NORTH);
		add(southPanel, BorderLayout.SOUTH);
		
		
		
		setSize(350,300);
		setVisible(true);
	}
	
	public static void main(String [] args){
		FindPrimes fp = new FindPrimes();
		fp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
}
