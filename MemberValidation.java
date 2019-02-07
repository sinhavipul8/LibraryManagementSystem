package driver;
import jdbcconnection.JdbcConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class MemberValidation {
	static Connection connection = null;
	static PreparedStatement preparedstatement = null;
	protected  int validatemem() throws SQLException {
		int memberid=0;
		String pass="";
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Member Id : ");
		memberid = scanner.nextInt();
		System.out.println("Enter Password : ");
		pass = scanner.next();
		connection = JdbcConnection.getConnection();
		preparedstatement = connection.prepareStatement("select member_id,password from members where member_id=?");
		
		preparedstatement.setInt(1,memberid);
		   ResultSet rs = preparedstatement.executeQuery();
		   int memberId = 0;
		   String orPass = "";
	        while (rs.next()) {
	        	memberId = rs.getInt("member_id");
	            orPass = rs.getString("password");
	        } //end while
	        if (orPass.equals(pass))
	        {
	           System.out.println("login Successful :)");
	            rs.close();
	            return memberid;
			}
	        else{
	        	System.out.println("Login Unsuccessful! Please try again! :(");
	        	return -1;
	        }
		}
	}


