package library;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import jdbcconnection.JdbcConnection;

public class AddNewMember
{
	static Connection connection = null;
	static CallableStatement callablestatement = null;
	
	
	public static void addMember() throws SQLException 
	{
		String first_name;
		String last_name;
		String pass;
		int mobile;
		String address;
		Scanner scanner = new Scanner(System.in);
		try
		{
			connection= JdbcConnection.getConnection();

			callablestatement = connection.prepareCall("call library.addNewMember(?,?,?,?,?)");
			System.out.println("Enter the first_name : ");
			first_name =scanner.next();
			System.out.println("Enter the last name : ");
			last_name = scanner.next();
			System.out.println("Enter the password : ");
			pass = scanner.next();
			System.out.println("Enter the mobile number : ");
			mobile = scanner.nextInt();
			System.out.println("Enter the address : ");
			address = scanner.next();
			callablestatement.setString(1, first_name);
			callablestatement.setString(2, last_name);
			callablestatement.setString(3, pass);
			callablestatement.setInt(4, mobile);
			callablestatement.setString(5, address);
			System.out.println(" New Member is added ");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			connection.close();
			callablestatement.close();
			scanner.close();
		}
		
	}

}
