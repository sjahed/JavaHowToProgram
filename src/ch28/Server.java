package ch28;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class Server extends JFrame{

	private int HEIGHT = 300;
	private int WIDTH = 400;
	private int PORT_NUMBER = 12345;
	private int NUMBER_OF_CLIENTS = 1;
	
	private JTextField inputField;
	private JTextArea  contentArea;
	private ServerSocket server;
	private Socket connection;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	
	public static void main(String[] args) {
		new Server();
	}

	public Server(){
	
		super("Server");
		
		//initialize values
		inputField = new JTextField();
		inputField.setEditable(false);
		inputField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sendData(e.getActionCommand(), output);
				inputField.setText("");
			}
		});
		contentArea = new JTextArea();
		contentArea.setEditable(false);
		
		//add to frame
		add(inputField, BorderLayout.NORTH);
		add(new JScrollPane(contentArea), BorderLayout.CENTER);
		
		setSize(HEIGHT, WIDTH);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//activate server and get connections
		try {
			server = startServer(PORT_NUMBER, NUMBER_OF_CLIENTS);
			while(true){
				
				try{
					connection = getConnection(server);
					output = getOutputStream(connection);
					output.flush();
					displayMessage("here\n");
					input = getInputStream(connection);
					processConnection(output,input);
				}finally{
					closeConnection(connection, input, output);
				}
				
				
			}//end of while(true)
		} catch (IOException e) {
			e.printStackTrace();
		}//end of try-catch(ioException)-finally
		
		

	}//end of Server()
	
	private ServerSocket startServer(int port, int numClients) throws IOException{
		return new ServerSocket(port, numClients);
	}//end of startServer(port,client)
	
	private Socket getConnection(ServerSocket server) throws IOException{
		Socket conn;
		displayMessage("Waiting for connection\n");
		conn = server.accept();
		displayMessage("Connection received from: "+conn.getInetAddress().getHostName()+"\n");
		return conn;
	}//end of getConnection(server)
	
	private ObjectInputStream getInputStream(Socket conn) throws IOException{
		return new ObjectInputStream(conn.getInputStream());
	}//end of getInputStream(conn)
	
	private ObjectOutputStream getOutputStream(Socket conn) throws IOException{
		return new ObjectOutputStream(conn.getOutputStream());
	}//end of getOutputStream(conn)
	
	private void processConnection(ObjectOutputStream output, ObjectInputStream input){
		String message = "Connection to client Successful";
		sendData(message,output);
		setTextEditable(true);
		
		do{
			try {
				message = (String) input.readObject();
			} catch (ClassNotFoundException e) {
				displayMessage("\nUnknown Object Type Received");
			} catch ( IOException ioe){
				displayMessage("\nCouldn't receive data\n");
			}
		}while(!message.equals("CLIENT>>> TERMINATE"));
		
	}//end of processConnection
	
	private void sendData(String message, ObjectOutputStream output){
		
		try {
			output.writeObject("SERVER>>> "+message);
			output.flush();
			displayMessage("\nSERVER>>> "+message);
		} catch (IOException e) {
			displayMessage("\nError writing object");
		}
		
	}//end of sendData(msg, outputStream)
	
	private void setTextEditable(final boolean answer){
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				inputField.setEditable(answer);
			}
			
		});
	}//end of setTextEditable(boolean)
	
	private void closeConnection(Socket connection, ObjectInputStream input, ObjectOutputStream output){
		displayMessage("\nTerminating Connection\n");
		setTextEditable(false);
		try {
			input.close();
			output.close();
			connection.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//end of closeConnection(conn)
	
	private void displayMessage(final String msg){
		SwingUtilities.invokeLater(
				new Runnable(){
					public void run(){
						contentArea.append(msg);
					}
				}
				);
	}//end of displayMessage(msg)
	
}//end of Server
