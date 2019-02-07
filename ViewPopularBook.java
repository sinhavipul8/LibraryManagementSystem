package members;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import jdbcconnection.JdbcConnection;
import oracle.jdbc.OracleTypes;


public class ViewPopularBook
{
	static CallableStatement callablestatement = null;
	static Connection connection = null;
	static ResultSet resultset = null;
	
	public static void viewPopularBook() throws ClassNotFoundException, SQLException 
	{
		try
		{
			connection = JdbcConnection.getConnection();
	callablestatement = connection.prepareCall("call member.viewPopularBook(?)");
	callablestatement.registerOutParameter(1, OracleTypes.CURSOR);
	callablestatement.executeUpdate();
	resultset = (ResultSet) callablestatement.getObject(1);
	System.out.println("BOOK NAME "+ " \t\t"+"AUTHOR " + "\t\t\t"+"publication");
	System.out.println("*****************************************************************************************");
	while(resultset.next())
	{
		System.out.println(resultset.getString("Book_name")+"\t\t\t"+resultset.getString("author")+"\t\t\t"+resultset.getString("publication"));
		System.out.println("");
	}
		

	}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			try
			{
				if(callablestatement!=null)
					callablestatement.close();
				if(connection!= null)
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
