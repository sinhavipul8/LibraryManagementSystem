package library;

import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.util.Scanner;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;

import jdbcconnection.JdbcConnection;

public class DeleteBookLocations {

	public static void DeleteBookLocations() {

		CallableStatement callablestatement = null;
		ResultSet resultset = null;
		Scanner scanner = new Scanner(System.in);
		Connection connection = null;
		int book_id, floor_no, cupboard_no, rack_no;
		try {

			connection = JdbcConnection.getConnection();

			callablestatement = connection
					.prepareCall("call library.deleteBookLocations(?,?,?,?)");
			System.out.println("Enter the floor number : ");
			floor_no = scanner.nextInt();
			System.out.println("Enter the cupboard number : ");
			cupboard_no = scanner.nextInt();
			System.out.println("Enter the rack number : ");
			rack_no = scanner.nextInt();
			System.out.println("Enter the book id : ");
			book_id = scanner.nextInt();
			callablestatement.setInt(1, floor_no);
			callablestatement.setInt(2, cupboard_no);
			callablestatement.setInt(3, rack_no);
			callablestatement.setInt(4, book_id);
			int value = callablestatement.executeUpdate();
			if (value == 0) {
				System.out.println(" record is deleted ");
			} else
				System.out.println("no record is deleted");

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (callablestatement != null)
					callablestatement.close();
				if (connection != null)
					connection.close();
				if (resultset != null)
					resultset.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}