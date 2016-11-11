package ch28;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class ReadServerFile extends JFrame {

	private JTextField urlField;
	private JEditorPane contentArea;
	
	public ReadServerFile(){
		super("Simple Web browser");
		
		urlField = new JTextField("Enter URL");
		urlField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getThePage(e.getActionCommand());
				
			}

			
		});
		add(urlField,BorderLayout.NORTH);
		
		contentArea = new JEditorPane();
		contentArea.setEditable(false);
		contentArea.addHyperlinkListener(new HyperlinkListener() {
			
			@Override
			public void hyperlinkUpdate(HyperlinkEvent e) {
				
				if(e.getEventType() == 
						HyperlinkEvent.EventType.ACTIVATED)
					getThePage(e.getURL().toString());
				
			}
		});
		add(new JScrollPane(contentArea), BorderLayout.CENTER);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		pack();
	}
	
	private void getThePage(String location) {
		try{
			contentArea.setPage(location);
			urlField.setText(location);
		}catch(IOException e){
			JOptionPane.showMessageDialog(this, "Error Retrieving the Page","BAD URL",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(400,300);
	}
	public static void main(String [] args){
		new ReadServerFile();
	}
}//end of ReadServerFile class
