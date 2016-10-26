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
	
	public BooksDBTableModel(Connection connection, String query) throws SQLException{
		this.connection = connection;
		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		connectedToDB = true;
		executeQuery(query);
	}
	
//	@Override
//	public boolean isCellEditable(int row, int col){
//		return true;
//		
//	}
	
	public void executeQuery(String query) throws SQLException{
		if(!connectedToDB)
			throw new IllegalStateException("Not connected to DB");
		
		resultSet = statement.executeQuery(query);
		metaData = resultSet.getMetaData();
		
		resultSet.last();
		numRows = resultSet.getRow();
		fireTableStructureChanged();
		
	}//end of executeQuery()
	
	@Override
	public int getRowCount() {
		return numRows;
	}

	@Override
	public int getColumnCount() {
		if(!connectedToDB)
			throw new IllegalStateException("Not connected to DB");
		
		try {
			return metaData.getColumnCount();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
//	@Override 
//	public void setValueAt(Object value, int row, int col){
//		Object oldValue = getValueAt(row, col);
//		System.out.println("Old value = " + oldValue);
//		System.out.println("New value = " + value);
//		
//		if(!oldValue.equals(value)){
//			System.out.println("Value changing for row: " + row);
//		}
//		super.setValueAt(value, row, col);
//		fireTableCellUpdated(row, col);
//		
//	}//end of setValueAt()
	
	
	@Override
	public Object getValueAt(int row, int col) {
		if(!connectedToDB)
			throw new IllegalStateException("Not connected to DB");
		
		try {
			resultSet.absolute(row + 1);
			return resultSet.getObject(col + 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
		return "";
	}//end of getValueAt()
	
	@Override
	public String getColumnName(int col){
		if(!connectedToDB)
			throw new IllegalStateException("Not connected To DB");
		try {
			return metaData.getColumnName(col+1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}//end of getColumnName
	

	@Override 
	public Class getColumnClass(int col){
		if(!connectedToDB)
			throw new IllegalStateException("Not connected to DB");
		
		try {
			return metaData.getColumnClassName(col+1).getClass();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Object.class;
	}//end of getColumnClass()
	
	public void disconnectDB(){
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
	}//end of disconnectDB()

}//end of BooksDBTableModel class
