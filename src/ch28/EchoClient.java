package ch28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {

	public static void main(String[] args){
		String host = "127.0.0.1";
		int port = 5001;
		try(
				Socket echoSocket = new Socket(host,port);
				PrintWriter out = new PrintWriter(echoSocket.getOutputStream());
				BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
				BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
				){
			String userInput;
			System.out.println("Enter Query:");
			while((userInput = stdIn.readLine()) != null){
				out.println(userInput);
				System.out.println("echo: "+in.readLine());
			}
			System.err.println("Closing connection to server"+host);
			in.close();
			out.close();
			echoSocket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
	}
}
