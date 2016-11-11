package ch28;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class FileClient extends JFrame implements Runnable{

	private final String HOST = "127.0.0.1";
	private final int PORT = 12345;
	
	private Socket connection;
	private Scanner input;
	private Formatter output;
	private JTextField inputField;
	private JTextArea outputArea;
	private JButton searchBtn;
	
	public FileClient(){
		super("Client");
		
		inputField = new JTextField(20);
		searchBtn = new JButton("Search");
		JPanel panel = new JPanel(new FlowLayout());
		panel.add(inputField);
		panel.add(searchBtn);
		add(panel,BorderLayout.NORTH);
		
		outputArea = new JTextArea(30, 20);
		outputArea.setEditable(false);
		add(new JScrollPane(outputArea));
		
		searchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				output.format(inputField.getText()+"\n");
				output.flush();
			}
		});
		
		
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setMinimumSize(getPreferredSize());
		startClient();
	}
	
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(350,300);
	}
	

	public void startClient(){
		
		try {
			connection = new Socket(HOST, PORT);			
			input = new Scanner(connection.getInputStream());
			output = new Formatter(connection.getOutputStream());
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Couldn't create connect to server");
			System.exit(1);
		}
		
		ExecutorService worker = Executors.newFixedThreadPool(1);
		worker.execute(this);
	}
	
	@Override
	public void run() {

		while(true){
			if(input.hasNextLine())
				processServerData(input.nextLine());
		}
		
	}//end of run()
	
	private void processServerData(String data){
		displayMessage(data);
	}
	
	private void displayMessage(String message){
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				outputArea.setText("");
				outputArea.append(message+"\n");
				
			}
		});
	}
	
	public static void main(String [] args){
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new FileClient();
				
			}
		});
		
	}
}
