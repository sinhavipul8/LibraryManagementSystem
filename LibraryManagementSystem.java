package driver;
import java.sql.SQLException;
import java.util.Scanner;

import library.AddBook;
import library.AddBookLocation;
import library.AddNewMember;
import library.BookIssue;
import library.DeleteBookLocations;
import library.MaximumBookIssuedException;
import library.NoBookAvailableException;
import library.ReturnBooks;
import library.SearchBooklocation;
import library.SearchBybookId;
import library.SearchMemberById;
import library.SearchMemberByName;
import library.ViewIssueBookRecord;
import members.ChangePassword;
import members.ViewHistory;
import members.ViewIssuedBook;
import members.ViewPopularBook;


//import jdbcconnection.JdbcConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LibraryManagementSystem
{

	public static void main(String[] args) throws SQLException, NoBookAvailableException, MaximumBookIssuedException, ClassNotFoundException, IOException
	{
		Scanner scanner = new Scanner(System.in);
		BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(System.in));
		int choice_as_lib_Member, choice_as_lib=0,choice_as_member,member_id,libvalue;
		int search_book,search_member;
		
		do{
		System.out.println("1.MEMBER \t\t\t 2.LIBRARIAN\t\t\t 3.EXIT ");
		choice_as_lib_Member = scanner.nextInt();
		
		switch(choice_as_lib_Member)
		{
		case 1 : MemberValidation membervalidation = new MemberValidation();
				 member_id = membervalidation.validatemem();
				if(member_id != -1)
				{
					do
					{
					System.out.println("WELCOME TO MEMBER INTERFACE ");
					System.out.println("1.View History\n2.View Issued Book\n3.View popular book\n4.Change password\n5.EXIT");
					choice_as_member = scanner.nextInt();
					
					switch(choice_as_member)
					{
					case 1 :ViewHistory.viewHistory(member_id);
							break;
					case 2:ViewIssuedBook.viewIssuedBook(member_id);	
							break;
					case 3:ViewPopularBook.viewPopularBook();
							break;
					case 4:ChangePassword.changePassword(member_id);
							break;
					case 5 :System.exit(0);		
					}
					
				}while(choice_as_member!=5);
				}
				break;
		case 2 :libvalue = LibrarianValidation.librarianValidate();
			if(libvalue == 1){
				do
				{
				System.out.println();
				System.out.println("WELCOME TO LIBRARIAN INTERFACE ");
				System.out.println("1.Book Issue\n2.Search book\n3.Delete book location\n4.Search Member\n5.View issued book\n6.Add Book\n7.Add Book Location\n8.Return Book\n9.Add New Member\n10.Exit");
		        
		        choice_as_lib = scanner.nextInt();
		        switch(choice_as_lib)
		        {
		        case 1 :BookIssue.bookIssue();
		        		break;
		        case 2 :System.out.println();
		        		System.out.println("1. Search by Name \t\t 2.Search by ID \t\t 3.EXIT");
		        		search_book = scanner.nextInt();
		        		switch(search_book)
		        		{
		        		case 1 : SearchBooklocation.searchBooklocation();
		        				 break;	
		        		case 2 : SearchBybookId.searchBybookId();
		        				 break;
		        		case 3 :break;		 
		        		}
		         
		        case 3 :DeleteBookLocations.DeleteBookLocations();
		        		break ;
		        case 4 :System.out.println();
        				System.out.println("1. Search by Name \t\t 2.Search by ID \t\t 3.EXIT");
        				search_member = scanner.nextInt();
        				switch(search_member)
        				{
        				case 1 : SearchMemberByName.SearchMemberByName();	
		        				break;
        				case 2 : SearchMemberById.searchMemberById();
		        				break;
        				case 3 :break;		 
        				}

		        case 5 :ViewIssueBookRecord.viewIssueBookRecord();
		        		break;
		        case 6:AddBook.addBook();
		        		break;
		        case 7:	AddBookLocation.addBookLocation();
		        		break;
		        case 8: ReturnBooks.returnBook();
		        		break;
		        case 9: AddNewMember.addMember();
		        case 10:System.exit(0);	
		        }
				}while(choice_as_lib!=10);
			}
		case 3 :System.exit(0);        
		}
		
	}while(choice_as_lib_Member!=3);
	}	
}
