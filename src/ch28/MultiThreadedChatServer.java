package ch28;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class MultiThreadedChatServer extends JFrame implements Runnable{

	public static void main(String [] args){
		new Thread(new MultiThreadedChatServer()).start();
	}
	
	private final int PORT = 12345;
	private final int MAX_NUM_CLIENTS = 100;
	private final int WIDTH = 300;
	private final int HEIGHT = 400;
	
	private JTextArea textArea;
	private ServerSocket server;
	private boolean hasStopped = false;
	
	
	public MultiThreadedChatServer(){
		super("Server");
		
		textArea = new JTextArea(30, 15);
		textArea.setEditable(false);
		add(new JScrollPane(textArea), BorderLayout.CENTER);
		
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	}//end of MultiThreadedChatServer
	
	@Override
	public void run() {
		
		openServerSocket();
		displayMessage("Server started");
		
		while(! hasStopped()){
			Socket clientSocket = null;
			
			try {
				clientSocket = server.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			displayMessage(clientSocket.getInetAddress().getHostName()+" got connected!");
			new Thread(new ClientThreadWorker(clientSocket)).start();
		}
		
	}//end of run()
	
	private boolean hasStopped(){
		return hasStopped;
	}
	private void openServerSocket(){
		
		try {
			server = new ServerSocket(PORT,MAX_NUM_CLIENTS);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//end of openServerSocket()
	
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(WIDTH, HEIGHT);
	}
	
	@Override 
	public Dimension getMinimumSize(){
		return getPreferredSize();
	}
	
	public void displayMessage(String message){
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				textArea.append(message+"\n");				
			}
		});
	}
	class ClientThreadWorker implements Runnable {
		
		private Socket clientSocket;
		private Scanner input;
		private Formatter output;
		
		public ClientThreadWorker(Socket listener){
			clientSocket = listener;
			
		}//end of ClientServerThread(listener)
		
		public void run() {
			try {
				
				input = new Scanner(clientSocket.getInputStream());
				output = new Formatter(clientSocket.getOutputStream());
				
				String inputString;
				while(input.hasNext()){
					inputString = input.next();
					displayMessage("Client "+ clientSocket.getInetAddress().getHostName()
							+ " says: "+inputString);
					output.format("Server Replies: ", inputString);
					output.flush(); 
				}//end of while(input.hasNext())
				
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				input.close();
				output.close();
				try {
					clientSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}//end of try-catch-finally
			
		}//end of run()
		
	}//end of ClientServerThread

	
}//end MultiThreadedChatServer
