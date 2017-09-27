package ch28;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class DatagramClient extends JFrame{

	private JTextArea contentArea;
	private JTextField inputField;
	
	private DatagramSocket socket;
	
	public static void main(String[] args){
		new DatagramClient();
	}//end of main()
	
	public DatagramClient(){
		
		super("Datagram Client");
		
		inputField = new JTextField();
		inputField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String message = e.getActionCommand();
				displayMessage(message);
				byte[] data = message.getBytes();
				
				DatagramPacket sendPacket;
				try {
					sendPacket = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 5000);
					socket.send(sendPacket);
					displayMessage("\nPacket Sent\n");
					
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		add(inputField, BorderLayout.NORTH);
		contentArea = new JTextArea();
		contentArea.setEditable(false);
		add(new JScrollPane(contentArea), BorderLayout.CENTER);
		setVisible(true);
		setSize(400,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			socket = new DatagramSocket();
			waitForPackets();
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}//end of DatagramClient()
	
	private void waitForPackets(){
		while(true){
			
			byte[] data = new byte[100];
			
			DatagramPacket recvPacket = new DatagramPacket(data, data.length);
			try {
				socket.receive(recvPacket);
				displayMessage("\nPacket Received: "+
								"\nFrom: "+recvPacket.getAddress()+
								"\nPort: "+recvPacket.getPort()+
								"\nLength: "+recvPacket.getLength()+
								"\nContaining: "+ new String(recvPacket.getData(),0,recvPacket.getLength())+"\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//end of while(true)
	}//end of waitForPackets()
	
	private void displayMessage(String msg){
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				contentArea.append(msg);
			}
		});
	}//end of displayMessage(msg)
}//end of DatagramClient
