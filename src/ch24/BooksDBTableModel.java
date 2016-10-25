package ch24;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.AbstractTableModel;

public class BooksDBTableModel extends AbstractTableModel {

	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private ResultSetMetaData metaData;
	private int numRows;
	
	private boolean connectedToDB = false;
	
	public BooksDBTableModel(Connection connection) throws SQLException {
		
		this.connection = connection;
		statement = connection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
		         ResultSet.CONCUR_READ_ONLY);
		connectedToDB = true;
	}

	public void executeQuery(String query) throws IllegalStateException, SQLException{
		if(!connectedToDB)
			throw new IllegalStateException("Not connected To DB");
		resultSet = statement.executeQuery(query);
		metaData = resultSet.getMetaData();
		resultSet.last();
		numRows = resultSet.getRow();
		fireTableStructureChanged();
	
	}//execute query
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return numRows;
	}

	@Override
	public int getColumnCount() {
		if(!connectedToDB)
			throw new IllegalStateException("Not connected To DB");
		try {
			return metaData.getColumnCount();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Class getColumnClass(int index){
		if(!connectedToDB)
			throw new IllegalStateException("Not connected To DB");
		try {
			return metaData.getColumnClassName(index).getClass();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Object.class;
	}
	
	@Override
	public String getColumnName(int index){
		if(!connectedToDB)
			throw new IllegalStateException("Not connected To DB");
		
		try {
			return metaData.getColumnName(index+1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	
	@Override
	public Object getValueAt(int row, int col) {
		if(!connectedToDB)
			throw new IllegalStateException("Not connected To DB");
		
		try {
			resultSet.absolute(row+1);
			return resultSet.getObject(col+1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
	
	public void disconnectFromDB(){
		if(connectedToDB){
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				connectedToDB = false;
			}
			
		}
	}//end of disconnectFromDB()

}
