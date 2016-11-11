package ch28;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.Scanner;

public class FileServer {
	private Path SEARCH_DIR = Paths.get("");
	private final int PORT = 12345;
	private ServerSocket server;
	private Socket listenerSocket;
	private Scanner input;
	private Formatter output;
	
	public FileServer(){
		
		try {
			server = new ServerSocket(PORT,1);
			listenerSocket = server.accept();
			input = new Scanner(listenerSocket.getInputStream());
			output = new Formatter(listenerSocket.getOutputStream());
			
			String request;
			while(input.hasNextLine()){
				
				request = input.nextLine();
				System.out.println("Client wants the file: "+request);
				
				Path searchFor = SEARCH_DIR.resolve(request);
				
				if(request != null){
					if(fileExists(searchFor) &
							isFileRegular(searchFor)){
						Scanner readFile = new Scanner(new File(searchFor.toString()));
						while(readFile.hasNext()){
							output.format(readFile.nextLine()+"\n");
							output.flush();
						}
					}else{
						output.format("File doesn't exist!"+"\n");
						output.flush();
					}
						
					
				}//end of if(request!=null)
				
			}//end of while(input.hasNextLine())
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}finally{
			input.close();
			output.close();
			try {
				listenerSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public boolean fileExists(Path filePath){
		
		if(filePath.toFile().exists())
			return true;
		else
			return false;
		
	}//end of fileExists(fileName)
	
	public boolean isFileRegular(Path filePath){

		if(Files.isRegularFile(filePath, LinkOption.NOFOLLOW_LINKS) &
				Files.isReadable(filePath) &
				Files.isWritable(filePath))
			return true;
		else 
			return false;
	}//end of isFileRegular()
	
	public static void main(String [] args){
		new FileServer();
	}
}
