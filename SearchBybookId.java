package library;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;
import jdbcconnection.JdbcConnection;

public class SearchBybookId {

	static CallableStatement callablestatement = null;
	static Connection connection = null;
	static ResultSet resultset = null;
	
	public static void searchBybookId() {
		Scanner scanner = new Scanner(System.in);
	
		try
		{
			int book_id;
			connection= JdbcConnection.getConnection();
			callablestatement = connection.prepareCall("call library.searchBybookId(?,?)");
			System.out.println("Enter the book id : ");
			book_id = scanner.nextInt();
			callablestatement.setInt(1, book_id);
			callablestatement.registerOutParameter(2, OracleTypes.CURSOR);
			callablestatement.executeUpdate();
			resultset = (ResultSet) callablestatement.getObject(2);
			
			System.out.println("BOOK NAME \t\t\t"+"AUTHOR\t\t\t"+"PUBLICATION\t\t\t"+"FLOOR NUMBER\t\t"+"CUPBOARD NUMBER\t\t"+"RACK NUMBER");
			while(resultset.next())
			{
				System.out.println(resultset.getString("book_name")+"\t\t"+resultset.getString("author")
						+"\t\t"+resultset.getString("publication")+"\t\t"+resultset.getInt("floor_no")+"\t\t"+resultset.getInt("cupboard_no")+"\t\t"+resultset.getInt("rack_no"));
			}
			
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			finally{
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
