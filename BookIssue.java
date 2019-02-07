package library;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import jdbcconnection.JdbcConnection;

public class BookIssue {
	static Connection connection = null;
	static CallableStatement callablestatement = null;

	static ResultSet resultset = null;

	public static void bookIssue() throws NoBookAvailableException,
			SQLException, MaximumBookIssuedException {
		Scanner scanner = new Scanner(System.in);
		try {
			int memid;
			int bookid;
			int result = 0;
			connection = JdbcConnection.getConnection();
			Statement statement = connection.createStatement();
			System.out.println("Enter Member Id ");
			memid = scanner.nextInt();
			System.out.println("Enter Book Id ");
			bookid = scanner.nextInt();
			resultset = statement.executeQuery("select copies_available from Books where book_id="+ bookid);
			while (resultset.next()) {
				if (resultset.getInt(1) == 0)
					throw new NoBookAvailableException(resultset.getInt(1));
			}
			resultset = statement
					.executeQuery("select book_issued from members where member_id ="
							+ memid);
			while (resultset.next()) {
				if (resultset.getInt(1) == 5) {
					throw new MaximumBookIssuedException();
				}
			}
			callablestatement = connection
					.prepareCall("CALL library.bookIssues(?,?)");
		
			callablestatement.setInt(1, memid);
			callablestatement.setInt(2, bookid);
			result = callablestatement.executeUpdate();
			if (result == 0)
				System.out.println("Book Issued!");
			else
				System.out.println("Can`t Issue!");
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			connection.close();
			scanner.close();
		}
	}
}


