package members;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import jdbcconnection.JdbcConnection;

public class ChangePassword {
	static Connection connection = null;
	static CallableStatement callablestatement = null;

	public static void changePassword(int member_id) throws SQLException {
		try {
			String password1;
			String confirmpassword;

			connection = JdbcConnection.getConnection();
			callablestatement = connection
					.prepareCall("{CALL member.changePassword(?,?,?)}");
			System.out.println("Enter password : ");
			Scanner sc = new Scanner(System.in);
			password1 = sc.next();
			System.out.println("Enter password again : ");
			confirmpassword = sc.next();
			if (password1.equals(confirmpassword)) {
				callablestatement.setString(1, password1);
				callablestatement.setString(2, confirmpassword);
				callablestatement.setInt(3, member_id);
				callablestatement.executeUpdate();
				System.out.println("Password changed.");
			} else {
				System.out.println("Passwords do not match.");
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {

			callablestatement.close();
			connection.close();
		}
	}
}
