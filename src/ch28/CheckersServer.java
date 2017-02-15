package ch28;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class CheckersServer extends JFrame{

	public static void main(String [] args){
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new CheckersServer();
				
			}
		});
	}//end of main()
	
	private final int HEIGHT = 400;
	private final int WIDTH = 300;
	private final int PORT = 12345;
	private final int MAX_NUM_PLAYERS = 2;
	private final String[] MARKS = {"WHITE", "BLACK"};
	private final int PLAYER_WHITE = 0;
	private final int PLAYER_BLACKs = 1;
	
	private ServerSocket server;
	private JTextArea outputArea;
	
	public CheckersServer(){
		super("Checkers Server");
		
		outputArea = new JTextArea(30, 10);
		outputArea.setEditable(false);
		add(new JScrollPane(outputArea), BorderLayout.CENTER);
		
		startListening(PORT, MAX_NUM_PLAYERS);
		
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}//end of ChecersServer()
	
	private void startListening(int port, int backlog){
		
		try {
			server = new ServerSocket(port, backlog);
			displayMessage("Server started listening on port "+port);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.print("Couldn't start server Listener");
			System.exit(1);
		}
	}//end of startListening
	
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(WIDTH, HEIGHT);
	}
	
	@Override
	public Dimension getMinimumSize(){
		return getPreferredSize();
	}
	
	protected void displayMessage(String message){
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				outputArea.append(message+"\n");				
			}
		});
	}//end of displayMessage(message)
	
	
}//end of CheckersServer
