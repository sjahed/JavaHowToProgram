package ch28;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Server extends JFrame {

	public static void main(String [] args){
		Server server = new Server();
		server.runServer();
	}
	
	private JTextField enterField;
	private JTextArea displayArea;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ServerSocket server;
	private Socket connection;
	private int counter = -1;
	
	public Server(){
		super("Server");
		
		enterField = new JTextField();
		enterField.setEditable(false);
		enterField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sendData(e.getActionCommand());
				enterField.setText("");
			}
		});
		
		add(enterField, BorderLayout.NORTH);
		
		displayArea = new JTextArea();
		add(new JScrollPane(displayArea), BorderLayout.CENTER);
		
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}//end of Server()
	
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(300,140);
	}
	
	public void runServer(){
		try {
			server = new ServerSocket(12345,100);
			while(true){
				try{
					waitForConnection();
					getStreams();
					processConnection();
				}catch(EOFException eofe){
					displayMessage("\nServer terminated connection.");
				}finally{
					closeConnection();
					++counter;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}//end of runServer()
	
	private void getStreams() throws IOException {
		
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		displayMessage("\nGot I/O Stream\n");
		
	}

	private void closeConnection() {
		displayMessage("\nTerminating Connection.\n");
		setTextFieldEditable(false);
		try{
			output.close();
			input.close();
			connection.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}

	private void displayMessage(final String message) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				displayArea.append(message);				
			}
		});
		
	}

	private void processConnection() throws IOException {
		String message = "Connection Successful";
		sendData(message);
		
		setTextFieldEditable(true);
		do{
			try{
				message = (String) input.readObject();
				displayMessage("\n"+message);
			}catch(ClassNotFoundException cnfe){
				displayMessage("\nUnknown object type recieved.\n");
			}
		}while(!message.equals("CLIENT>>> TERMINATE"));
		
	}

	private void setTextFieldEditable(final boolean editable) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				enterField.setEditable(editable);
				
			}
		});
		
	}

	private void waitForConnection() throws IOException {
		displayMessage("Waiting for connection\n");
		connection = server.accept();
		displayMessage("Connection "+ counter + "received from: "+
					connection.getInetAddress().getHostAddress());
		
	}

	public void sendData(String message){
		try{
			output.writeObject("SERVER>>> "+message);
			output.flush();
			displayMessage("\nSERVER>>>"+message);
		}catch(IOException e){
			displayArea.append("\nError writing object");
		}
	}
}//end of Server class
