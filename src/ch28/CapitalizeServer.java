package ch28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class CapitalizeServer{

	public static void main(String [] args) throws IOException{
		System.out.println("The Server is running");
		int counter = 0;
		ServerSocket listener = new ServerSocket(5000);
		try{
			while(true){
				new Capitalizer(listener.accept(), counter++).start();
			}
		}finally{
			listener.close();
		}//end of try-finally

	}//end of main()


	private static class Capitalizer extends Thread{

		private Socket socket;
		private int clientNumber;

		public Capitalizer(Socket socket, int clientNumber){

			this.socket = socket;
			this.clientNumber = clientNumber;

		}//end of Capitalizer()

		@Override
		public void run(){
			try{
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

				out.println("Hello, you are client # "+clientNumber);
				out.println("Enter a line with only period to quit\n");

				while(true){
					String input = in.readLine();
					if(input == null || input.equals("."))
						break;
					out.println(input.toUpperCase());

				}
			}catch(IOException e){

			}finally{
				try{
					socket.close();
				}catch(IOException e1){
					
				}
			}
		}//end of run()

	}//end of Capitalizer

}//end of CapitalizeServer

