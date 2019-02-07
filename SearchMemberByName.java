package library;
import java.sql.CallableStatement; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;
import jdbcconnection.JdbcConnection;

public class SearchMemberByName {
 
	static CallableStatement callablestatement = null;
	static Connection connection = null;
	static ResultSet resultset = null;
	
	public static void SearchMemberByName() throws SQLException 
	{
		Scanner scanner = new Scanner(System.in);
		String membername;
		try
		{
			connection= JdbcConnection.getConnection();
			callablestatement = connection.prepareCall("call library.searchMemberByName(?,?)");
			System.out.println("Enter the member first name OR last name : ");
			membername = scanner.next();
			callablestatement.setString(1,membername);
			callablestatement.registerOutParameter(2, OracleTypes.CURSOR);
			callablestatement.executeUpdate();
			resultset = (ResultSet) callablestatement.getObject(2);
			System.out.println("MEMBER ID \t\t FIRST NAME \t\tLAST NAME \t\t MOBILE \t\tBOOK ISSUED ");
			System.out.println("***********************************************************************************************************");
			while(resultset.next())
			{
				System.out.println(resultset.getInt("member_id")+"\t\t\t"+resultset.getString("first_name")+"\t\t\t"
			+resultset.getString("last_name")+"\t\t\t"+resultset.getLong("mobile")+"\t\t\t"+resultset.getInt("book_issued"));
			}
					
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		finally
		{
			try{
			if(callablestatement!= null)
				callablestatement.close();
			if(resultset!= null)
				resultset.close();
			if(connection!=null)
				connection.close();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}

}
