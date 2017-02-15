package ch28;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Formatter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class MultiThreadedChatClient extends JFrame implements Runnable {

	public static void main(String [] args){
		new Thread(new MultiThreadedChatClient()).start();
		new Thread(new MultiThreadedChatClient()).start();
	}
	private final String SERVER = "127.0.0.1";
	private final int PORT = 12345;
	private final int WIDTH = 300;
	private final int HEIGHT = 400;
	
	private Socket clientSocket;
	private Scanner input;
	private Formatter output;
	private JTextField inputField;
	private JTextArea outputArea;
	private JButton submitButton;
	
	public MultiThreadedChatClient() {
		super("Client");
		
		inputField = new JTextField(10);
		submitButton = new JButton("Submit");
		JPanel northPanel = new JPanel(new FlowLayout());
		northPanel.add(inputField);
		northPanel.add(submitButton);
		
		submitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String userInput = inputField.getText();
				sendData(userInput);
			}
		});
		
		add(northPanel, BorderLayout.NORTH);
		
		outputArea = new JTextArea(30, 15);
		add(new JScrollPane(outputArea), BorderLayout.CENTER);
		
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}//end of MultiThreadedChatClient()
	
	@Override
	public void run() {
		openConnection();
		processSocket();
		displayMessage("Got connected!");
		while(input.hasNext()){
			displayMessage(input.next());
		}
		
	}//end of run()
	
	private void openConnection(){
		try {
			clientSocket = new Socket(SERVER, PORT);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Couldn't connect to server");
			System.exit(1);
		}
	}//end of openConnection()
	
	private void processSocket(){
		
		try {
			input = new Scanner(clientSocket.getInputStream());
			output = new Formatter(clientSocket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("couldn't get input/output socket");
			System.exit(1);
		}
	}//end of processSocket()
	
	private void sendData(String message){
		output.format(message);
		output.flush();
	}
	
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(WIDTH, HEIGHT);
	}
	@Override 
	public Dimension getMinimumSize(){
		return getPreferredSize();
	}
	
	private void displayMessage(String message){
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				outputArea.append(message+"\n");
			}
		});
	}//end of displayMessage(message)
	
}//end of MultiThreadedChatClient class
