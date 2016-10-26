package ch24;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorQueries {

	private Connection connection;
	private PreparedStatement insertNewAuthor;
	private PreparedStatement editAuthor;
	private PreparedStatement deleteAuthor;
	
	public AuthorQueries(Connection connection){
		try{
			this.connection = connection;
			insertNewAuthor = connection.prepareStatement("INSERT INTO Authors"
					+ "(FirstName, LastName)"
					+ "values(?, ?)");
		}catch(SQLException e){
			e.printStackTrace();
		}
	}//end of AuthorQueries(connection)
	
	public int insertNewAuthor(String firstName, String lastName){
		int result = 0;
		try {
			insertNewAuthor.setString(1, firstName);
			insertNewAuthor.setString(2, lastName);
			result = insertNewAuthor.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}//end of insertNewAuthor(firstName,lastName)
	
}//end of AuthorQueries class
