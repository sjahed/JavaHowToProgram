package ch28;

import java.awt.BorderLayout;
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
	
	private JTextField enterField;
	private JEditorPane contentPane;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReadServerFile application = new ReadServerFile();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}//end of main()

	public ReadServerFile(){
		super("Simple Browser");
		
		enterField = new JTextField("Enter Your URL Here");
		enterField.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						getThePage(event.getActionCommand());
					}
				}
				
				);
		add(enterField, BorderLayout.NORTH);
		
		contentPane = new JEditorPane();
		contentPane.setEditable(false);
		contentPane.addHyperlinkListener(
				new HyperlinkListener(){

					@Override
					public void hyperlinkUpdate(HyperlinkEvent e) {
						if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
							getThePage(e.getURL().toString());
					}
					
				}
				
				);//end of contentPane.addHyperlinkListener()
		add(new JScrollPane(contentPane), BorderLayout.CENTER);
		setSize(400,300);
		setVisible(true);
	}//end of ReadServerFile()
	
	private void getThePage(String location) {
		try{
			contentPane.setPage(location);
			enterField.setText(location);
		}catch(IOException ioe){
			JOptionPane.showMessageDialog(this, "Error Retrieving URL", "BAD URL",JOptionPane.ERROR_MESSAGE);
		}
		
	}//end of getThePage(location)
	
}//end of ReadServerFile class
