package members;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.sql.ResultSet;
import java.util.Scanner;

import jdbcconnection.JdbcConnection;
import oracle.jdbc.OracleTypes;


public class ViewIssuedBook {
	static Connection connection = null;
	static CallableStatement callablestatement = null;
	static ResultSet resultset;


	
public static void viewIssuedBook(int member_id) throws ClassNotFoundException, SQLException {
try{
	connection = JdbcConnection.getConnection();
callablestatement = connection.prepareCall("{CALL member.viewIssuedBook(?,?)}");
Scanner scanner = new Scanner(System.in);
callablestatement.setInt(1, member_id);
callablestatement.registerOutParameter(2,OracleTypes.CURSOR);
callablestatement.executeUpdate();

resultset=(ResultSet) callablestatement.getObject(2);

System.out.println("BOOK NAME \t\t"+ "ISSUE DATE\t\t\t"+"EXP RETURN\t\t");
System.out.println("************************************************************************************");

while(resultset.next())
{
	System.out.println(resultset.getString("book_name")+"\t\t\t"+resultset.getDate("issue_date")+"\t\t\t"+resultset.getDate("exp_return"));
	System.out.println("");
}

}
catch(Exception e)
{
	System.out.println(e);
}

finally
{
	callablestatement.close();
	connection.close();
}
}

}
