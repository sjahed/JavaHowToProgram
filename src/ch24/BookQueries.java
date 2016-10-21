package ch24;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import com.mysql.jdbc.ResultSetImpl;

public class BookQueries extends AbstractTableModel {

	private final Connection connection;
	private final Statement statement;
	private ResultSet resultSet;
	private ResultSetMetaData metaData;
	private int numberOfRows;
	
	private boolean connectedToDatabase = false;
	private final String ERROR = "Not connected to database";
	
	public BookQueries(String database, String username,
						String password, String defaultQuery) throws SQLException{
		
		connection = DriverManager.getConnection(database, username, password);
		statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
											ResultSet.CONCUR_UPDATABLE);
		connectedToDatabase = true;

		setQuery(defaultQuery);
	
	}//end of BookQueries()

	public void setQuery(String query) throws IllegalStateException, SQLException {
		
		if(!connectedToDatabase)
			throw new IllegalStateException(ERROR);
		
		resultSet = statement.executeQuery(query);
		metaData = resultSet.getMetaData();
		
		resultSet.last();
		numberOfRows = resultSet.getRow();
		
		fireTableDataChanged();
		
	}//end of setQuery()

	@Override
	public int getRowCount() throws IllegalStateException{
		if(!connectedToDatabase)
			throw new IllegalStateException(ERROR);
		
		return numberOfRows;
	}//end of getRowCount()

	@Override
	public int getColumnCount() throws IllegalStateException {
		if(!connectedToDatabase)
			throw new IllegalStateException(ERROR);
	
		try{
			return metaData.getColumnCount();
		}catch(SQLException sqlex){
			sqlex.printStackTrace();
		}
		return 0;
	}//end of getColumnCount()

	public String getColumnName(int columnNumber) throws IllegalStateException{
		if(!connectedToDatabase)
			throw new IllegalStateException(ERROR);
		try{
			return metaData.getColumnName(columnNumber+1);
		}catch(SQLException sqlex){
			sqlex.printStackTrace();
		}
		return "";
	}
	
	public Class getColumnClass(int column) throws IllegalStateException{
		if(!connectedToDatabase)
			throw new IllegalStateException(ERROR);
		
		try {
			String className = metaData.getColumnClassName(column+1);
			return Class.forName(className);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Object.class;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) throws IllegalStateException {
		
		if(!connectedToDatabase)
			throw new IllegalStateException(ERROR);
		
		try {
			resultSet.absolute(rowIndex+1);
			return resultSet.getObject(columnIndex+1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}//end of getValueAt()
	
	public void disconnectFromDatabase(){
		if(connectedToDatabase){
			
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				connectedToDatabase = false;
			}
			
		}
	}
	
}//end of class BookQueries
