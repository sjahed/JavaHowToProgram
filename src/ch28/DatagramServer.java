package ch28;

import java.awt.BorderLayout;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class DatagramServer extends JFrame{

	private JTextArea contentArea;
	private DatagramSocket socket;
	
	public static void main(String []args){
		new DatagramServer();
	}//end of main()
	
	public DatagramServer(){
		super("Datagram Server");
		contentArea = new JTextArea();
		contentArea.setEditable(false);
		add(new JScrollPane(contentArea),BorderLayout.CENTER);
		setSize(400,300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			socket = new DatagramSocket(5000);
			waitForPackets();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}//end of DatagramServer()
	
	public void waitForPackets(){
		while(true){
			
			byte[] data = new byte[100];
			DatagramPacket recvPacket = new DatagramPacket(data, data.length);
			try {
				socket.receive(recvPacket);
				displayMessage("\nPacket Received:\nFrom Host: "+recvPacket.getAddress()+
						"\nOn Port: "+recvPacket.getPort()+
						"\nLength: "+recvPacket.getLength()+
						"\nContaining: "+ new String(recvPacket.getData(), 0, recvPacket.getLength())+"\n");
				sendPacketToClient(recvPacket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				displayMessage(e+"\n");
				e.printStackTrace();
			}
			
		}//end while(true)
	}//end of waitForPackets()
	
	public void sendPacketToClient(DatagramPacket packet) throws IOException{
		displayMessage("\n\nEcho data to client...\n");
		
		DatagramPacket sndPacket = new DatagramPacket(packet.getData(), packet.getLength(),
				packet.getAddress(), packet.getPort());
		socket.send(sndPacket);
		displayMessage("Packet Sent\n");
		
	}//end of sendPacketToClient(packet)
	private void displayMessage(final String msg){
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				contentArea.append(msg);				
			}
		});
	}//end of displayMessage(msg)
}//end of DatagramServer
