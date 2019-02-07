package driver;
import jdbcconnection.JdbcConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class LibrarianValidation {
	static Connection connection = null;
	static PreparedStatement pt = null;
	public static int librarianValidate() throws SQLException {
		int res = 0;
		String username;
		String pass;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Username : ");
		username = scanner.next();
		System.out.println("Enter Password : ");
		pass = scanner.next();
		connection = JdbcConnection.getConnection();
		pt = connection.prepareStatement("select user_name,password from librarian where user_name=?");


        pt.setString(1, username);
        ResultSet rs = pt.executeQuery();
        String orgUname = "", orPass = "";
        while (rs.next()) {
            orgUname = rs.getString("user_name");
            orPass = rs.getString("password");
        } //end while
        if (orPass.equals(pass))
        {
        	  System.out.println("login Successful :)");
            rs.close();
            return 1;
		}
        else{
        	System.out.println("Login Unsuccessful! Please try again! :(");
        	return -1;
        }
	}
}
