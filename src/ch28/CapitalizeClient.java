package ch28;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CapitalizeClient{

	private BufferedReader in;
	private PrintWriter out;
	private JFrame frame = new JFrame("Capitalize CapitalizeClient");
	private JTextField dataField = new JTextField(40);
	private JTextArea messageArea  = new JTextArea(8,60);
	
	public CapitalizeClient(){
		messageArea.setEditable(false);
		frame.getContentPane().add(dataField, BorderLayout.NORTH);
		frame.getContentPane().add(new JScrollPane(messageArea), BorderLayout.CENTER);
		
		dataField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				out.println(dataField.getText());
				String response;
				try{
					response = in.readLine();
					if(response == null || response.equals(""))
						System.exit(0);
					
				}catch(IOException ex){
					response = "Error" + ex;
				}
				messageArea.append(response + "\n");
				dataField.selectAll();
			}
		});
		
	}//end of capitalizeClient()

	
	public void connectToServer() throws IOException{
		String serverAddress = JOptionPane.showInputDialog(frame,
															"Enter IP address of the server",
															"Welcome to capitalization of program",
															JOptionPane.QUESTION_MESSAGE);
		Socket socket = new Socket(serverAddress, 5000);
		in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
		
		for(int i = 0; i < 3 ; i++)
			messageArea.append(in.readLine() + "\n");
	}//end of connectToServer()
	
	
	public static void main(String [] args) throws IOException{
		CapitalizeClient client = new CapitalizeClient();
		client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		client.frame.pack();
		client.frame.setVisible(true);
		client.connectToServer();
	}
}//end of CapitalizeClient