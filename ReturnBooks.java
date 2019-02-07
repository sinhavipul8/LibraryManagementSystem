package library;

import java.sql.Date;
import java.sql.Statement;
import java.sql.CallableStatement;
import java.sql.Connection;
import jdbcconnection.JdbcConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.PreparedStatement;


public class ReturnBooks extends ReceiptGeneration{
static Connection connection=null;
static Statement statement=null;
static CallableStatement callablestatement=null; 
static ResultSet resultset=null;

public static void returnBook() throws SQLException {
int bookid,memid,issueid = 0;
Date expreturn=null,returndate = null;
Scanner scanner=new  Scanner(System.in);
try
{
	connection= JdbcConnection.getConnection();

Statement statement=connection.createStatement();
callablestatement=connection.prepareCall("call library.returnBooks(?, ?)");
System.out.println("Enter Member Id : ");
memid=scanner.nextInt();
System.out.println(" Enter Book Id : ");
bookid=scanner.nextInt();
PreparedStatement psmt = connection.prepareStatement("select issue_id from book_issue where member_id=? and book_id=? and return_date is null");
psmt.setInt(1, memid);
psmt.setInt(2, bookid);
System.out.println(psmt.executeUpdate());

resultset = statement.executeQuery("select issue_id from book_issue where member_id="+memid+"and book_id="+bookid+"and return_date is null");
while(resultset.next()){
issueid = resultset.getInt(1);
}


callablestatement.setInt(1, memid);
callablestatement.setInt(2,bookid);
callablestatement.executeUpdate();
resultset = statement.executeQuery("select exp_return , return_date from book_issue where issue_id ="+issueid);
while(resultset.next()){
returndate = resultset.getDate(2);
expreturn = resultset.getDate(1);

}

if(returndate.after(expreturn)){

ReceiptGenerate(issueid);
}
}

catch(Exception e)
{
System.out.print(e);
}
finally
{
connection.close();

}
}

}
