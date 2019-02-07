package library;
import java.sql.Connection;
import jdbcconnection.JdbcConnection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AddBook 
{
	static Connection connection;
	static CallableStatement callablestatement = null; 
	
	public static void addBook() throws SQLException
	{
		String bookname;
		String author;
		String publication;
		int copiesavailable;
		Scanner scanner = new Scanner(System.in);
		try
		{
			connection= JdbcConnection.getConnection();
			callablestatement = connection.prepareCall("call library.addBooks(?,?,?,?)");
			System.out.println("Enter the Book's Name : ");
			bookname = scanner.next();
			System.out.println("Enter the Author`s name : ");
			author = scanner.next();
			System.out.println("Enter the publication : ");
			publication = scanner.next();
			System.out.println("Enter number of copies available : ");
			copiesavailable = scanner.nextInt();
			callablestatement.setString(1,bookname);
			callablestatement.setString(2,author);
			callablestatement.setString(3,publication);
			callablestatement.setInt(4,copiesavailable);
			callablestatement.executeUpdate();
			System.out.println("Book Inserted!");
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
