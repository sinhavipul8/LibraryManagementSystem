package library;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class ReceiptGeneration {
	public static void ReceiptGenerate(int issueid) throws ClassNotFoundException, SQLException
	{
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
		//Statement statement = connection.createStatement();
		PreparedStatement preparedstatement = connection.prepareStatement("select receipt_id,issue_date,return_date,book_name,first_name,last_name,fine from book_issue bi,receipt r, books b, members m where r.member_id = m.member_id and r.book_id = b.book_id and r.issue_id = bi.issue_id and r.issue_id = ?");
		preparedstatement.setInt(1,issueid);
		ResultSet resultset = preparedstatement.executeQuery();
		while(resultset.next()){
			System.out.println("Receipt for Late Return:");
			System.out.println("");
			System.out.println("");
			System.out.print("Receipt No : ");
			System.out.println(resultset.getInt(1));
			System.out.println("Name : "+resultset.getString(5)+" "+resultset.getString(6));
			System.out.println("Book Name\t\tIssue Date\t\tReturn Date\t\tAmount");
			System.out.println("----------------------------------------------------------------------------------------------------");
			System.out.println("");
			System.out.println(resultset.getString(4)+"\t\t\t"+resultset.getDate(2)+"\t\t\t"+resultset.getDate(3)+"\t\t\t"+resultset.getInt(7));
			
		}
		
		
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally{
			//connection.close();
		}
	}

}
