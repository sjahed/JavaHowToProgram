package ch28;

import java.awt.BorderLayout;
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
	private int HEIGHT = 300;
	private int WIDTH = 400;
	private String SERVER = "127.0.0.1";
	private int PORT = 12345;
	
	private JTextField inputField;
	private JTextArea contentArea;
	
	private Socket connection;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	
	public static void main(String [] args){
		new Client();
	}
	public Client(){
		
		super("Client");
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
		
		add(inputField, BorderLayout.NORTH);
		add(new JScrollPane(contentArea), BorderLayout.CENTER);
		
		setSize(HEIGHT, WIDTH);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			connection = getConnection(SERVER, PORT);
			displayMessage("Connected to: "+connection.getInetAddress().getHostName()+"\n");
			output = getOutputStream(connection);
			output.flush();
			input = getInputStream(connection);
			processConnection(connection, input, output);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			closeConnection(connection,input,output);
		}
		
	}//end of Client()
	private void processConnection(Socket conn, ObjectInputStream input, ObjectOutputStream output){
		setTextFieldEditable(true);
		String message = "";
		do{
			try {
				message = (String) input.readObject();
				displayMessage(message+"\n");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}while(!message.equals("SERVER>>> TERMINATE"));
		
	}//end of processConnection
	
	private void closeConnection(Socket con, ObjectInputStream in, ObjectOutputStream out){
		try {
			in.close();
			out.close();
			con.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setTextFieldEditable(boolean ans){
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				inputField.setEditable(ans);
			}
		});
	}//end of setTextFieldEditable()
	
	private Socket getConnection(String server, int port) throws UnknownHostException, IOException{
		displayMessage("Attempting to connect to server\n");
		return new Socket(InetAddress.getByName(server), port);
	}//end of getConnection(server,port)
	
	private ObjectInputStream getInputStream(Socket conn) throws IOException{
		return new ObjectInputStream(conn.getInputStream());
	}
	
	private ObjectOutputStream getOutputStream(Socket conn) throws IOException{
		return new ObjectOutputStream(conn.getOutputStream());
	}
	
	private void sendData(String message, ObjectOutputStream output){
		try {
			output.writeObject("CLIENT>>> "+message);
			output.flush();
			displayMessage("CLIENT>>> "+message+"\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}//end of sendData(msg,ouputStream)
	
	private void displayMessage(String msg){
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				contentArea.append(msg);
			}
		});
	}//end of displayMessage(msg)
}//end of Client
