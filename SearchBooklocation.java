package library;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;
import jdbcconnection.JdbcConnection;

public class SearchBooklocation {

	static  CallableStatement callablestatement = null;
	static Connection connection = null;
	static ResultSet resultset = null;
	
	public static void searchBooklocation()
	{
	
		Scanner scanner = new Scanner(System.in);
		String book_name;
		try
		{
			connection= JdbcConnection.getConnection();
		callablestatement = connection.prepareCall("call library.searchBooklocation(?,?)");
		System.out.println("Enter the book name : ");
		book_name = scanner.next();
		callablestatement.setString(1, book_name);
		callablestatement.registerOutParameter(2, OracleTypes.CURSOR);
		callablestatement.executeUpdate();
		resultset = (ResultSet) callablestatement.getObject(2);
	
		System.out.println("BOOK ID\t\t\t"+"BOOK NAME \t\t\t"+"AUTHOR\t\t\t"+"PUBLICATION\t\t\t"+"FLOOR NUMBER\t\t"+"CUPBOARD NUMBER\t\t"+"RACK NUMBER");
		while(resultset.next())
		{
			System.out.println(resultset.getString("book_id")+"\t\t"+resultset.getString("book_name")+"\t\t"+resultset.getString("author")
					+"\t\t"+resultset.getString("publication")+"\t\t"+resultset.getInt("floor_no")+"\t\t"+resultset.getInt("cupboard_no")+"\t\t"+resultset.getInt("rack_no"));
		}
		
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			try{
			if(callablestatement!=null)
				callablestatement.close();
			if(connection!=null)
				connection.close();
			if(resultset!=null)
				resultset.close();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}

}
