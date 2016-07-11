package ch15;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class JFileChooserDemo extends JFrame {

	private final JTextArea outputArea;
	
	public JFileChooserDemo() throws IOException{
		super("JFilechooser demo");
		outputArea = new JTextArea();
		add(new JScrollPane(outputArea));
		
		analyzePath();
	}
	
	public void analyzePath() throws IOException {
		Path path = getFileOrDirectoryPath();
		
		if(path != null && Files.exists(path)){
			StringBuilder builder = new StringBuilder();
			builder.append(String.format("%s:%n", path.getFileName()));
			builder.append(String.format("%s a directory%n",
			Files.isDirectory(path) ? "Is" : "Is not"));
			builder.append(String.format("%s an absolute path%n",
			path.isAbsolute() ? "Is" : "Is not"));
			builder.append(String.format("Last modified: %s%n",
			Files.getLastModifiedTime(path)));
			builder.append(String.format("Size: %s%n", Files.size(path)));
			builder.append(String.format("Path: %s%n", path));
			builder.append(String.format("Absolute path: %s%n",
					path.toAbsolutePath()));
			if(Files.isDirectory(path)){
				builder.append(String.format("%nDirectory contents:%n"));
				// object for iterating through a directory's contents
				DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path);
				for (Path p : directoryStream)
					builder.append(String.format("%s%n", p));
			}
			outputArea.setText(builder.toString());
		}else{
			JOptionPane.showMessageDialog(this, path.getFileName() +
					" does not exist.", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	private Path getFileOrDirectoryPath() {
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int result = fileChooser.showOpenDialog(this);
		if(result == JFileChooser.CANCEL_OPTION)
			System.exit(1);
		
		return fileChooser.getSelectedFile().toPath();
	}
	public static void main(String [] args) throws IOException{
		JFileChooserDemo application = new JFileChooserDemo();
		application.setSize(400, 400);
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.setVisible(true);
	}
}
