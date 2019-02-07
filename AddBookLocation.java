package library;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.util.Scanner;
import java.sql.SQLException;
import jdbcconnection.JdbcConnection;

public class AddBookLocation {
static Connection connection=null;
static CallableStatement cstmt=null;
public static void addBookLocation() throws SQLException
{
int fno,cno,rno,bno;
try
{
	connection= JdbcConnection.getConnection();
cstmt = connection.prepareCall("call library.addBookLocations(?,?,?,?)");
Scanner sc=new Scanner(System.in);
System.out.println("Enter the floor number");
fno = sc.nextInt();
System.out.println("Enter the cupboard number");
cno = sc.nextInt();
System.out.println("Enter the rack number");
rno= sc.nextInt();
System.out.println("Enter the book id");
bno= sc.nextInt();
cstmt.setInt(1,fno);
cstmt.setInt(2,cno);
cstmt.setInt(3,rno);
cstmt.setInt(4,bno);
cstmt.executeUpdate();

System.out.println("You have updated Book Location");
}
catch(Exception e)
{
System.out.println(e);
}
finally
{
connection.close();
cstmt.close();
}
}

}
