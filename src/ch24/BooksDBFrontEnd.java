package ch24;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

public class BooksDBFrontEnd extends JFrame {
	
	private final static String SELECT_AUTHORS_Q = "SELECT * From AUTHORS";
	private final static String SELECT_TITLES_Q = "SELECT * From TITLES";
	private final static String SELECT_ISBN_Q = "SELECT * From AUTHORISBN";
	
	private DBConnection connection;
	
	private JTabbedPane pane;
	private JButton addAuthorBtn, addTitlesBtn, addISBNBtn;
	private JButton deleteAuthorBtn, deletetitlesBtn, deleteISBNBtn;
	private JButton updateAuthorBtn, updateTitlesBtn,  updateISBNBtn;
	private BooksDBTableModel authorsTable, titlesTable, isbnTable;

	private BooksDBTableModel tableModel;
	
	public BooksDBFrontEnd(){
		super("Books Database");
		setLayout(new GridLayout(1,1));
		
		
		try {
			connection = new DBConnection();
			tableModel = new BooksDBTableModel(connection.getConnection());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pane = new JTabbedPane();
		JPanel authorsPanel = new JPanel();
		JPanel titlesPanel = new JPanel();
		JPanel isbnTitlesPanel = new JPanel();
		
		//setup authors,titles, isbn tabs
		authorsPanel.setLayout(new BorderLayout());
		titlesPanel.setLayout(new BorderLayout());
		isbnTitlesPanel.setLayout(new BorderLayout());
		
		JScrollPane scrollPaneAuthors = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JScrollPane scrollPaneTitles = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JScrollPane scrollPaneISBN = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		try {
			
			
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		authorsPanel.add(scrollPaneAuthors, BorderLayout.CENTER);
		titlesPanel.add(scrollPaneTitles, BorderLayout.CENTER);
		isbnTitlesPanel.add(scrollPaneISBN, BorderLayout.CENTER);
		
		JPanel authorsBtnPanel = new JPanel(new GridLayout(1, 3));
		authorsBtnPanel.add((addAuthorBtn = new ModifiedButton("Add Record")));
		authorsBtnPanel.add((deleteAuthorBtn = new ModifiedButton("Delete Record")));
		authorsBtnPanel.add((updateAuthorBtn = new ModifiedButton("Update Record")));
		authorsPanel.add(authorsBtnPanel, BorderLayout.SOUTH);
		
		JPanel titlesBtnPanel = new JPanel(new GridLayout(1,3));
		titlesBtnPanel.add((addTitlesBtn = new ModifiedButton("Add Record")));
		titlesBtnPanel.add((deletetitlesBtn = new ModifiedButton("Delete Record")));
		titlesBtnPanel.add((updateTitlesBtn = new ModifiedButton("Update Record")));
		titlesPanel.add(titlesBtnPanel, BorderLayout.SOUTH);
		
		JPanel isbnBtnPanel = new JPanel(new GridLayout(1,3));
		isbnBtnPanel.add((addISBNBtn = new ModifiedButton("Add Record")));
		isbnBtnPanel.add((deleteISBNBtn = new ModifiedButton("Delete Record")));
		isbnBtnPanel.add((updateTitlesBtn = new ModifiedButton("Update Record")));
		isbnTitlesPanel.add(isbnBtnPanel, BorderLayout.SOUTH);
		
		
		pane.addTab("Authors", authorsPanel);
		pane.addTab("Titles",titlesPanel);
		pane.addTab("ISBN-Title",isbnTitlesPanel);
		add(pane);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}//end of BooksDBFrontEnd()
	
	public static void main(String [] args){
		try {
			EventQueue.invokeAndWait(new Runnable() {
				
				@Override
				public void run() {
					new BooksDBFrontEnd();
					
				}
			});
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//end of main()
	
}//end of BooksDBFrontEnd class

class ModifiedButton extends JButton implements ActionListener{

	public ModifiedButton(String string) {
		// TODO Auto-generated constructor stub
		super(string);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}//end of class ModifiedButton

class DBConnection{
	
	private final static String DATABASE = "jdbc:derby:books";
	private final static String USERNAME = "deitel";
	private final static String PASSWORD = "deitel";
	private Connection connection;
	public Connection getConnection() throws SQLException{
		connection = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
		return connection;
	}
}//end of DBConnection class