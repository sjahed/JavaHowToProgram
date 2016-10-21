package ch24;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;


public class JdbcRowSetTest {

	private static final String DATABASE_URL = "jdbc:derby:books";
	private static final String USERNAME = "deitel";
	private static final String PASSWORD = "deitel";
	
	public static void main(String [] args){
		try(JdbcRowSet rowSet = RowSetProvider.newFactory().createJdbcRowSet()){
			
			rowSet.setUrl(DATABASE_URL);
			rowSet.setUsername(USERNAME);
			rowSet.setPassword(PASSWORD);
			rowSet.setCommand("Select * from authors");
			rowSet.execute();
			
			ResultSetMetaData metaData =  rowSet.getMetaData();
			int numberOfColumns = metaData.getColumnCount();
			
			System.out.printf("Authors Table of Books%n%n");
			
			for(int i = 1; i <= numberOfColumns; i++){
				System.out.printf("%-8s\t", metaData.getColumnName(i));
			}
			System.out.println();
			
			while(rowSet.next()){
				for(int i = 1; i <= numberOfColumns; i++)
					System.out.printf("%-8s\t", rowSet.getObject(i));
				System.out.println();
			}
		}catch(SQLException sqlex){
			sqlex.printStackTrace();
			System.exit(1);
		}
	}
}
