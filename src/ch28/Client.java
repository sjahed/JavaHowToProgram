package ch28;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Client extends JFrame {

	public static void main(String [] args){
		Client application;
		
		if(args.length == 0)
			application = new Client("127.0.0.1");
		else
			application = new Client(args[0]);
		application.runClient();
	}
	private JTextField enterField;
	private JTextArea displayArea;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private String message = "";
	private String chatServer;
	private Socket client;
	
	public Client(String host){
		super("Client");
		
		enterField = new JTextField();
		enterField.setEditable(false);
		enterField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sendData(e.getActionCommand());
				enterField.setText("");
				
			}
		});
		add(enterField,BorderLayout.NORTH);
		displayArea = new JTextArea();
		add(new JScrollPane(displayArea), BorderLayout.CENTER);
		
		
		
		setVisible(true);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	public void runClient(){
		
		try {
			connectToServer();
			getStreams();
			processConnection();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		
	}
	
	private void closeConnection() {
		displayMessage("\nClosing connection");
		setTextFieldEditable(false);
		try{
			output.close();
			input.close();
			client.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}


	private void processConnection() throws IOException {
		setTextFieldEditable(true);
		do{
			try{
				message = (String) input.readObject();
			}catch(ClassNotFoundException e){
				displayMessage("\nUnkonw object type recieved");
			}
		}while(!message.equals("SERVER>>> TERMINATE"));
		
	}


	private void setTextFieldEditable(boolean editable) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				enterField.setEditable(editable);
				
			}
		});
		
	}


	private void getStreams() throws IOException {
		
		output = new ObjectOutputStream(client.getOutputStream());
		output.flush();
		input = new ObjectInputStream(client.getInputStream());
		displayMessage("\nGot I/O Streams\n");
		
	}


	private void connectToServer() throws UnknownHostException, IOException {
		displayMessage("Attempting Connection\n");
		client = new Socket(InetAddress.getByName(chatServer), 12345);
		displayMessage("Connected to: "+ client.getInetAddress().getHostName());
		
	}


	private void displayMessage(String message) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				displayArea.append(message);				
			}
		});
		
	}


	private void sendData(String actionCommand) {
		try{
			output.writeObject("CLIENT>>> "+message);
			output.flush();
			displayMessage("\nCLIENT>>> "+message);
		}catch(IOException e){
			displayArea.append("\nError writing object");
		}
		
	}
	
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(300,150);
	}
}//end of Client class
