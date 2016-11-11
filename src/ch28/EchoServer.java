package ch28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

	public static void main(String[] args) throws IOException{
		int port = 5001;
		ServerSocket serverSocket = new ServerSocket(port);
		
		while(true){
			
			
			Socket clientSocket = serverSocket.accept();
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(
					new InputStreamReader(clientSocket.getInputStream()));
				
			String inputLine;
			while((inputLine = in.readLine()) != null){
				out.println(inputLine);
			}
			in.close();
			out.close();
			clientSocket.close();
			//serverSocket.close();
		
		}

	}
}
