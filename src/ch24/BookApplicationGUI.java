package ch24;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class BookApplicationGUI extends JFrame{

	private final static String DATABASE_URL = "jdbc:derby:books";
	private final static String USERNAME = "deitel";
	private final static String PASSWORD = "deitel";
	
	private final static String[] DEF_QUERIES_LIST = {"Show All Authors", 
											   "Show All Titles of an Author",
											   "Show All Authors of a Title"};
	private final static String[] DEF_QUERIES_SQL_LIST = {"SELECT * from AUTHORS",
														  "SELECT * from TITLES AS t "
														  + "INNER JOIN AUTHORISBN AS ai "
														  + "on ai.ISBN = t.ISBN "
														  + "INNER JOIN AUTHORS AS a "
														  + "on ai.AUTHORID = a.AUTHORID "
														  + "Where a.FIRSTNAME = ?",
														  "SELECT * from AUTHORS AS a "
														  + "INNER JOIN AUHTORISBN AS ai "
														  + "on a.AUTHORID = ai.AUTHORID "
														  + "INNER JOIN TITLES AS t "
														  + "on ai.ISBN = t.ISBN "
														  + "where t.TITLE = ? "
														  
	};
	
	private JTextArea queryArea;
	private JButton submitQueryBtn, updateBtn, insertBtn;
	private JComboBox queryList;
	private JTable table;
	private BookQueries bookQueries;
	
	public BookApplicationGUI(){
		super("Books Database Query Application");
		setLayout(new BorderLayout());
		
		try {
			bookQueries = new BookQueries(DATABASE_URL, USERNAME, PASSWORD, DEF_QUERIES_SQL_LIST[0]);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//configure and set the query area, combo box and button
		queryList = new JComboBox<>(DEF_QUERIES_LIST);
		queryArea = new JTextArea(3, 30);
		queryArea.setText(DEF_QUERIES_SQL_LIST[queryList.getSelectedIndex()]);
		queryArea.setWrapStyleWord(true);
		queryArea.setLineWrap(true);
		JScrollPane queryAreaScrollPane = new JScrollPane(queryArea, 
														ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
														ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		submitQueryBtn = new JButton("Submit Query");
		submitQueryBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					bookQueries.setQuery(queryArea.getText());
					
				} catch (IllegalStateException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException sqlException) {
					 JOptionPane.showMessageDialog(null, 
		                        sqlException.getMessage(), "Database error", 
		                        JOptionPane.ERROR_MESSAGE);
					 try {
						bookQueries.setQuery(DEF_QUERIES_SQL_LIST[0]);
						queryArea.setText(DEF_QUERIES_SQL_LIST[0]);
					} catch (IllegalStateException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException sqlException2) {
						JOptionPane.showMessageDialog(null, 
		                        sqlException2.getMessage(), "Database error", 
		                        JOptionPane.ERROR_MESSAGE);
						bookQueries.disconnectFromDatabase();
						System.exit(1);
					}
				}
			}
		});//end of submitQueryBtn.addActionListener
		
		
		JPanel northPanel = new JPanel(new BorderLayout());
		//panel for query area - center it in north part
		JPanel queryAreaPanel = new JPanel();
		queryAreaPanel.add(queryAreaScrollPane);
		northPanel.add(queryAreaPanel, BorderLayout.CENTER);
		//buttons panel that contains combobox and submit button
		JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
		buttonPanel.add(queryList);
		buttonPanel.add(submitQueryBtn);
		northPanel.add(buttonPanel, BorderLayout.EAST);
		queryList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				queryArea.setText(DEF_QUERIES_SQL_LIST[queryList.getSelectedIndex()]);				
			}
		});
		add(northPanel, BorderLayout.NORTH);
		
		//set the jtable and all its connection to database
		table = new JTable(bookQueries);
		
		add(new JScrollPane(table,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER),
				BorderLayout.CENTER);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent event)
            {
               bookQueries.disconnectFromDatabase();
               System.exit(0);
            } 
		});
		
		updateBtn = new JButton("Update Record");
		insertBtn = new JButton("New Record");
		JPanel southPanel = new JPanel(new GridLayout(1, 2));
		southPanel.add(updateBtn);
		southPanel.add(insertBtn);
		add(southPanel,BorderLayout.SOUTH);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		pack();
	}//end of BookApplicationGUI
	
	
	
	public static void main(String [] args){
		
		try {
			EventQueue.invokeAndWait(new Runnable() {
				
				@Override
				public void run() {
					new BookApplicationGUI();
					
				}
			});
		} catch (InvocationTargetException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}//end of BookApplicationGUI 
