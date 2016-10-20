package ch24;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DisplayAuthors {

	public static void main(String [] args) {
		final String DATABASE_URL = "jdbc:derby:books";
		final String SELECT_QUERY = "Select authorID, firstName, lastName from authors";
		final String USER = "deitel";
		final String PASSWORD = "deitel";
		

		try(	
				Connection connection = DriverManager.getConnection(
				DATABASE_URL, USER, PASSWORD);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SELECT_QUERY)){
			
			ResultSetMetaData metaData = resultSet.getMetaData();
			int numberOfCols = metaData.getColumnCount();
			
			System.out.printf("Authors of table of Books databse:%n%n");
			for(int i = 1; i < numberOfCols; i++)
				System.out.printf("%-8s\t",metaData.getColumnName(i));
			
			System.out.println();
			
			while(resultSet.next()){
				for (int i = 1; i <= numberOfCols; i++)
					 System.out.printf("%-8s\t",resultSet.getObject(i));
					 System.out.println();
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
}
