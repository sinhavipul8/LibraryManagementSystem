package library;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;
import jdbcconnection.JdbcConnection;

public class ViewIssueBookRecord {
	static CallableStatement callablestatement = null;
	static Connection connection = null;
	static ResultSet resultset = null;

	public static void viewIssueBookRecord() throws SQLException 
	{
		Scanner scanner = new Scanner(System.in);
		int book_id;
		try
		{
			connection= JdbcConnection.getConnection();
			callablestatement = connection.prepareCall("call library.viewIssueBookRecord(?,?)");
			System.out.println("Enter the book id : ");
			book_id = scanner.nextInt();
			callablestatement.setInt(1, book_id);
			callablestatement.registerOutParameter(2,OracleTypes.CURSOR);
			callablestatement.executeUpdate();
			resultset=(ResultSet) callablestatement.getObject(2);
			
			System.out.println("BOOK NAME \t\t\t\t"+ "MEMBER ID\t\t\t"+"FIRST NAME\t\t\t"+"LAST NAME\t\t\t"+"EXP RETURN ");
			System.out.println("**********************************************************************************************************");
			while(resultset.next())
			{
				System.out.println(resultset.getString("book_name")+"\t\t\t"+resultset.getInt("member_id")+"\t\t\t"+
			resultset.getString("first_name")+"\t\t\t"+resultset.getString("last_name")+"\t\t\t"+resultset.getDate("exp_return"));
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
