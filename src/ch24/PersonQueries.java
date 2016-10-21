package ch24;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PersonQueries {
	
	private static final String DATABASE_URL ="jdbc:derby:addressbook";
	private static final String USERNAME = "deitel";
	private static final String PASSWORD = "deitel";
	
	
	private Connection connection;
	private PreparedStatement selectAllPeople;
	private PreparedStatement selectPeopleByLastName;
	private PreparedStatement insertNewPerson;
	
	public PersonQueries(){
		try{
			
			connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
			selectAllPeople = connection.prepareStatement("Select * from addresses");
			selectPeopleByLastName = 
					connection.prepareStatement("select * from addresses where LastName = ?");
			insertNewPerson = 
					connection.prepareStatement("insert into addresses"
							+ " (FirstName, LastName, Email, PhoneNumber)"
							+ "Values (?,?,?,?)");
			
		}catch(SQLException sqlex){
			sqlex.printStackTrace();
			System.exit(1);
		}
	}//end of constructor

	public List<Person> getAllPeople(){
		List<Person> results = null;
		ResultSet resultSet = null;
		
		try{
			resultSet = selectAllPeople.executeQuery();
			results = new ArrayList<Person>();
			while(resultSet.next()){
				results.add(new Person(
						resultSet.getInt("addressID"),
						resultSet.getString("FirstName"),
						resultSet.getString("LastName"),
						resultSet.getString("Email"),
						resultSet.getString("PhoneNumber")
						));
			}//end of while(resultSet.next())
		}catch(SQLException sqlex){
			sqlex.printStackTrace();
		}finally{
			try{
				resultSet.close();
			}catch(SQLException sqlex2){
				sqlex2.printStackTrace();
				
			}
		}//end of try-catch-fianlly
		return results;
	}//end of getAllPeople()
	
	public List<Person> getPeopleByLastName(String name){
		List<Person> results = null;
		ResultSet resultSet = null;
		try{
			resultSet = selectPeopleByLastName.executeQuery();
			results = new ArrayList<Person>();
			while(resultSet.next()){
				results.add(new Person(
						resultSet.getInt("addressID"),
						resultSet.getString("FirstName"),
						resultSet.getString("LastName"),
						resultSet.getString("Email"),
						resultSet.getString("PhoneNumber")
						));
			}//end while(resultSet.next())
		}catch(SQLException sqlex){
			sqlex.printStackTrace();
		}finally{
			try {
				resultSet.close();
			} catch (SQLException sqlex2) {
				// TODO Auto-generated catch block
				sqlex2.printStackTrace();
				close();
			}
		}//end of try-catch-finally
		return results;
	}//end of getAllPeopleByLastName
	
	public int addPerson(String fName, String lName, String email, String num){
		int result = 0;
		try{
			insertNewPerson.setString(1, fName);
			insertNewPerson.setString(2, lName);
			insertNewPerson.setString(3, email);
			insertNewPerson.setString(4, num);
			result = insertNewPerson.executeUpdate();
		}catch(SQLException sqlex){
			sqlex.printStackTrace();
			close();
		}
		return result;
	}//end of addPerson
	
	public void close(){
		try{
			connection.close();
		}catch(SQLException sqlex){
			sqlex.printStackTrace();
		}
	}//end of close 
}//end of class PersonQueries
