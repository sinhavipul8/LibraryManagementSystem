package jdbcconnection;
import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcConnection 
{

		private static final String regitertodatabase = "oracle.jdbc.driver.OracleDriver";
		private static final String drivermanager = "jdbc:oracle:thin:@localhost:1521:xe";
		private static final String username = "hr";
		private static final String password ="hr";
		
		public static Connection getConnection()
		{
			Connection connection = null;
		
			try
			{
			Class.forName(regitertodatabase);
			connection = DriverManager.getConnection(drivermanager, username, password);
			if(connection ==null)
			{
				System.out.println("connection is unsuccessful..");
			}
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			
			return connection;
			
		}
	}


